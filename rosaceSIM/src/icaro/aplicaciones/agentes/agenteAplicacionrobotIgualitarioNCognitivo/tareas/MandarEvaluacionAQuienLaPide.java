/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package icaro.aplicaciones.agentes.agenteAplicacionrobotIgualitarioNCognitivo.tareas;
import icaro.aplicaciones.Rosace.informacion.Coordinate;
import icaro.aplicaciones.Rosace.informacion.*;
import icaro.aplicaciones.Rosace.objetivosComunes.AyudarVictima;
import icaro.aplicaciones.agentes.agenteAplicacionAsignadorTareasCognitivo.objetivos.DecidirQuienVa;
import icaro.aplicaciones.agentes.agenteAplicacionrobotIgualitarioNCognitivo.informacion.InfoParaDecidirQuienVa;
import icaro.infraestructura.entidadesBasicas.NombresPredefinidos;
import icaro.infraestructura.entidadesBasicas.interfaces.InterfazUsoAgente;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.MisObjetivos;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.Objetivo;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.TareaSincrona;
import icaro.infraestructura.recursosOrganizacion.recursoTrazas.imp.componentes.InfoTraza;
import icaro.infraestructura.recursosOrganizacion.repositorioInterfaces.ItfUsoRepositorioInterfaces;
import java.util.ArrayList;

/**
 *
 * @author Francisco J Garijo
 */
public class MandarEvaluacionAQuienLaPide  extends TareaSincrona {

	/**  */
    private InterfazUsoAgente agenteReceptor;
    private ArrayList agentesEquipo, respuestasAgentes,confirmacionesAgentes,nuevasEvaluacionesAgentes,empates;//resto de agentes que forman mi equipo
    private int mi_eval, mi_eval_nueva;
    private String nombreAgenteEmisor;
    private PeticionAgente  peticionRecibida;
//    private ItfUsoRecursoTrazas trazas;
    private InfoParaDecidirQuienVa infoDecision;
    private MisObjetivos misObjtvs;
    private String identObjEvaluacion;
    private String nombreAgenteQuePideLaEvaluacion ;
    private Integer miEvalDeRespuesta;
    private Integer valorDisuasorioParaElquePideAcepteQueSoyYoElResponsable = 5000000;
    private  Integer valorParaExcluirmeDelObjetivo = -5000 ;
    private VictimsToRescue victimasRecibidas ;
    private Victim victimEnPeticion ;
    private RobotStatus1 robot;
    private Coordinate robotLocation;
    //private TimeOutRespuestas tiempoSinRecibirRespuesta; //no usado
    
	@Override
	public void ejecutar(Object... params) {
		
  //        trazas = NombresPredefinidos.RECURSO_TRAZAS_OBJ;
          Objetivo objetivoEjecutantedeTarea = (Objetivo)params[0];
          infoDecision = (InfoParaDecidirQuienVa)params[1];
          peticionRecibida = (PeticionAgente) params[2]; 
          misObjtvs = (MisObjetivos) params[3];
          robot = (RobotStatus1)params[4];
          victimasRecibidas = (VictimsToRescue) params[5];
                       //      EvaluacionAgente miEvaluacion = (EvaluacionAgente) params[2];
          nombreAgenteEmisor = this.getIdentAgente();
          agentesEquipo = infoDecision.getIdentsAgentesEquipo();
          identObjEvaluacion = peticionRecibida.getidentObjectRefPeticion();
          nombreAgenteQuePideLaEvaluacion= peticionRecibida.getIdentAgente();
         robotLocation= robot.getRobotCoordinate();
          try {
              trazas.aceptaNuevaTraza(new InfoTraza(nombreAgenteEmisor, "Se Ejecuta la Tarea :"+ identTarea , InfoTraza.NivelTraza.debug));
      // si el identificador esta entre mis objetivos es que esta resuelto , le mando un valor para que se desanime
     // en  otro caso puede ser que sea otro el que tenga el objetivo, en este caso él le mandará un valor grande
     // si no tengo noticias del objetivo le mando un valor pequeno para que vaya él
     // si coincide con el que estoy trabajando le mando el valor 
               if (identObjEvaluacion.equals(infoDecision.idElementoDecision)) miEvalDeRespuesta = infoDecision.getMi_eval();
                else if( misObjtvs.existeObjetivoConEsteIdentRef(identObjEvaluacion))miEvalDeRespuesta = valorDisuasorioParaElquePideAcepteQueSoyYoElResponsable;
                      else { // Miro en la tabla de vicitimas si tengo la victima, 
                        victimEnPeticion = victimasRecibidas.getVictimToRescue(identObjEvaluacion);
                        if(victimEnPeticion != null){ // tengo la victima miro si tengo el valor estimado
                            if (victimEnPeticion.isCostEstimated()) miEvalDeRespuesta = victimEnPeticion.getEstimatedCost();
                            else{ // calculo el coste y lo guardo en la victima
                               miEvalDeRespuesta= calcularCosteEstimadoVictima();
                               victimEnPeticion.setEstimatedCost(miEvalDeRespuesta);
                               this.getEnvioHechos().actualizarHechoWithoutFireRules(victimEnPeticion);
                            }
                                }
                        else {// la victima no existe -> no se ha recibido el mensaje del CC. Calculo el coste y  meto los objetivos y la victima
                            victimEnPeticion = (Victim) peticionRecibida.getJustificacion();
                            miEvalDeRespuesta= calcularCosteEstimadoVictima();
                            victimEnPeticion.setEstimatedCost(miEvalDeRespuesta);
                            AyudarVictima newAyudarVictima = new AyudarVictima (identObjEvaluacion);
                            newAyudarVictima.setPriority(victimEnPeticion.getPriority());
                            victimasRecibidas.addVictimToRescue(victimEnPeticion);
                            DecidirQuienVa newDecision = new DecidirQuienVa(identObjEvaluacion);
                            newDecision.setSolving();   
                            this.getEnvioHechos().actualizarHechoWithoutFireRules(victimasRecibidas);
                            this.getEnvioHechos().insertarHechoWithoutFireRules(victimEnPeticion);
                            this.getEnvioHechos().insertarHechoWithoutFireRules(newAyudarVictima);
                            this.getEnvioHechos().insertarHechoWithoutFireRules(newDecision);
                        }
                }
              this.getEnvioHechos().eliminarHecho(peticionRecibida);
              EvaluacionAgente miEvaluacion = new EvaluacionAgente (nombreAgenteEmisor, miEvalDeRespuesta );
                               miEvaluacion.setObjectEvaluationId(identObjEvaluacion);
              trazas.aceptaNuevaTraza(new InfoTraza(nombreAgenteEmisor, "Enviamos la evaluacion " + miEvaluacion, InfoTraza.NivelTraza.info));
                              
              this.getComunicator().enviarInfoAotroAgente(miEvaluacion, nombreAgenteQuePideLaEvaluacion);
              this.generarInformeOK(identTarea, objetivoEjecutantedeTarea, nombreAgenteEmisor, VocabularioRosace.ResEjTaskMiEvaluacionEnviadaAlAgtesQLaPide+ nombreAgenteQuePideLaEvaluacion);
              
              trazas.aceptaNuevaTraza(new InfoTraza(nombreAgenteEmisor, "EvaluacionEnviadaAlAgente : "+ nombreAgenteQuePideLaEvaluacion, InfoTraza.NivelTraza.info));
                       //            tiempoSinRecibirRespuesta.start();

		  } catch (Exception e) {
			  e.printStackTrace();
          }
   }
        private int calcularCosteEstimadoVictima(){
          
//          try{    		   
//                ItfUsoRepositorioInterfaces itfUsoRepositorioInterfaces = NombresPredefinidos.REPOSITORIO_INTERFACES_OBJ;
//                ItfUsoRecursoMorse morseResourceRef;
//       		 morseResourceRef = (ItfUsoRecursoMorse) itfUsoRepositorioInterfaces.obtenerInterfaz(NombresPredefinidos.ITF_USO + 
//       				                      "RecursoMorse1");
//       		  robotLocation = morseResourceRef.getGPSInfo(nombreAgenteEmisor);
//       		           
//       	          }
//   	              catch (Exception ex){
//       		              ex.printStackTrace();
//       	          }  
          Coste coste = new Coste();
          return coste.CalculoCosteAyudarVictima(nombreAgenteEmisor, robotLocation, robot, victimEnPeticion, victimasRecibidas, misObjtvs, "FuncionEvaluacion3");
                              
        }
 	
}
