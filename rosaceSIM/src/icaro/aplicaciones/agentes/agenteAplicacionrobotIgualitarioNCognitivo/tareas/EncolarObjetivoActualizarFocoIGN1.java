/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package icaro.aplicaciones.agentes.agenteAplicacionrobotIgualitarioNCognitivo.tareas;

import icaro.aplicaciones.Rosace.informacion.Victim;
import icaro.aplicaciones.Rosace.informacion.VictimsToRescue;
import icaro.aplicaciones.Rosace.informacion.VocabularioRosace;
import icaro.aplicaciones.agentes.componentesInternos.movimientoCtrl.InfoCompMovimiento;
import icaro.aplicaciones.agentes.componentesInternos.movimientoCtrl.ItfUsoMovimientoCtrl;
import icaro.aplicaciones.agentes.agenteAplicacionrobotIgualitarioNCognitivo.informacion.InfoParaDecidirQuienVa;
import icaro.aplicaciones.agentes.componentesInternos.movimientoCtrl.imp.MaquinaEstadoMovimientoCtrl;
import icaro.aplicaciones.agentes.componentesInternos.movimientoCtrl.imp.MaquinaEstadoMovimientoCtrl.EstadoMovimientoRobot;
import icaro.infraestructura.entidadesBasicas.comunicacion.InfoContEvtMsgAgteReactivo;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.Focus;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.MisObjetivos;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.Objetivo;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.TareaSincrona;

/**
 *
 * @author FGarijo
 * rule "Encolar el objetivo Ayudar nueva victima despues de obtener el acuerdo de todos"
***when
    victimaCC:Victim(idVict:name)
    misObjs : MisObjetivos()
    infoComMov :InfoCompMovimiento ()
    victims2R:VictimsToRescue(victims2Rescue!=null)
    obj1 :AyudarVictima(state == Objetivo.PENDING, victimId == idVict)   
    obj2: DecidirQuienVa (state == Objetivo.SOLVED,objectDecisionId == idVict )
    obj:ConfirmacionParaIrYo(state==Objetivo.SOLVED,objectConfirmationId==idVict)
    infoDecision: InfoParaDecidirQuienVa(idElementoDecision == idVict)
    focoActual:Focus(foco == obj)
 then
 */
public class EncolarObjetivoActualizarFocoIGN1 extends TareaSincrona {
        private  enum EstadoMovimientoRobot {Indefinido,RobotParado, RobotEnMovimiento, RobotBloqueado,RobotavanceImposible,enDestino,  error}
        private ItfUsoMovimientoCtrl itfcompMov;
	private Victim victima;
	private int velocidadCruceroPordefecto;
    @Override
    public void ejecutar(Object... params) {
        
        //    ItfUsoRecursoEstadistica itfUsoRecursoEstadistica=null;
         velocidadCruceroPordefecto = 1;// metros por segundo
        //Para recoger estadisticas del instante de envio de victimas desde el centro de control

        try {
//             trazas = NombresPredefinidos.RECURSO_TRAZAS_OBJ;
            MisObjetivos misObjs = (MisObjetivos) params[0];
            Objetivo objetivoAsignado = (Objetivo) params[1];// AyudarVictima .pending
            InfoParaDecidirQuienVa infoDecision = (InfoParaDecidirQuienVa) params[2];
            Focus focoActual = (Focus) params[3];
            InfoCompMovimiento infoComMov = (InfoCompMovimiento) params[4];
             victima = (Victim) params[5];
            VictimsToRescue victimas =(VictimsToRescue) params[6];
            String nombreAgenteEmisor = this.getIdentAgente();

            //Para anotar en el fichero cual es mi coste
            int coste = 0;   //El coste se define como el MAYOR ENTERO - VALOR DE LA FUNCION DE EVALUACION
            //El que menor coste tiene es el que se hace cargo de la victima o dicho de otra manera
            //El que mayor funcionn de evaluacionn tiene es el que se hace cargo de la victima
            int miEvaluacion = infoDecision.getMi_eval();
            if (miEvaluacion != -1) {
                coste = Integer.MAX_VALUE - miEvaluacion;
            } else {
                coste = miEvaluacion;    //SI EL COSTE EL -1 INDICARIA QUE SE HA HECHO CARGO PERO QUE NO PUEDE IR (NO TIENE RECURSOS)
            }
            //ACTUALIZAR ESTADISTICAS
            //Inicializar y recuperar la referencia al recurso de estadisticas        	
            long tiempoActual = System.currentTimeMillis();
            String refVictima = objetivoAsignado.getobjectReferenceId();
            //      	 itfUsoRecursoEstadistica.escribeEstadisticaFicheroTextoPlanoTRealAsignacionVictimasRobots(tiempoActual, refVictima, nombreAgenteEmisor, coste);
            ////////////////////////////////////////////////////////
            //ENVIAR INFORMACION AL AGENTE CONTROLADOR DEL SIMULADOR           
            Object[] valoresParametrosAccion = new Object[4];
            valoresParametrosAccion[0] = tiempoActual;
            valoresParametrosAccion[1] = refVictima;
            valoresParametrosAccion[2] = nombreAgenteEmisor;
            valoresParametrosAccion[3] = miEvaluacion;
            InfoContEvtMsgAgteReactivo msg = new InfoContEvtMsgAgteReactivo("victimaAsignadaARobot", valoresParametrosAccion);
            this.getComunicator().enviarInfoAotroAgente(msg, VocabularioRosace.IdentAgteControladorSimulador);
            // verificamos que no se esta ayudando a esa victima. Comprobamos que el ident no esta en ninguno de los objetivos 
            // Se compara con los objetivos pendientes
            // Miramos si la cola de objetivos esta o no vacia 
            Objetivo nuevoObj = misObjs.getobjetivoMasPrioritario();
              itfcompMov = (ItfUsoMovimientoCtrl) infoComMov.getitfAccesoComponente();
//             while( nuevoObj != null&& nuevoObj.getPriority()>0 ){
//                 if (nuevoObj.getState()== Objetivo.SOLVED){  
//                                    nuevoObj.setPriority(-1);
//                                    misObjs.cambiarPrioridad(obj1);
//                                    nuevoObj=misObjs.getobjetivoMasPrioritario();
//                             }
//             }
             Thread t = new Thread(){
				
				public void run(){
					
					itfcompMov.moverAdestino(victima.getName(), victima.getCoordinateVictim(), velocidadCruceroPordefecto); 
				}
			};
             
             if ( nuevoObj == null||nuevoObj.getState()==Objetivo.SOLVED){// se pone el objetivo actual a solving y se da orden para que se empiece a mover
                objetivoAsignado.setSolving();
                misObjs.addObjetivo(objetivoAsignado);
//                focoActual.setFoco(obj1);
//                itfcompMov.moverAdestino(objetivoAsignado.getobjectReferenceId(), victima.getCoordinateVictim(), velocidadCruceroPordefecto);
                t.run();
                infoComMov.setidentDestino(objetivoAsignado.getobjectReferenceId());
                infoComMov.setidentEstadoRobot(itfcompMov.getIdentEstadoMovRobot());
                  

                this.getEnvioHechos().actualizarHechoWithoutFireRules(infoComMov);
                this.getEnvioHechos().actualizarHechoWithoutFireRules(objetivoAsignado);
                this.getEnvioHechos().actualizarHechoWithoutFireRules(misObjs);
                trazas.aceptaNuevaTrazaEjecReglas(identAgente, "No hay objetivos anteriores Objetivo considerado : "+ objetivoAsignado.toString()+
                        "Se ejecuta la tarea : " + identTarea + " Se actualiza el  foco al objetivo:  " + objetivoAsignado +
                        "estado del robot : "+infoComMov.getidentEstadoRobot()+"\n");
            
            }else{
                
        // hay un objetivo en curso ayudar victima comparamos prioridades
                if(objetivoAsignado.getPriority()<= nuevoObj.getPriority()){ // tiene menor prioridad  encolamos el objetivo
                                    misObjs.addObjetivo(objetivoAsignado);
                                    this.getEnvioHechos().actualizarHecho(misObjs);
                 trazas.aceptaNuevaTrazaEjecReglas(identAgente, "Se ejecuta la tarea : " + identTarea + "El objetivo asignado : "+ objetivoAsignado.toString()+
                       "Tiene menor o igual prioridad que el objetivo en curso  :  " + nuevoObj +
                        "estado del robot : "+infoComMov.getidentEstadoRobot()+"\n");                   
                }
                else {// El objetivo actual tiene mayor prioridad
                    // se  mira si el robot se esta moviendo a rescatar la victima                      
                    victima = victimas.getVictimToRescue(nuevoObj.getobjectReferenceId());
                    if (itfcompMov.getIdentEstadoMovRobot().equalsIgnoreCase(EstadoMovimientoRobot.RobotEnMovimiento.name())){   
                       itfcompMov.cambiaDestino(nuevoObj.getobjectReferenceId(), victima.getCoordinateVictim());
                       infoComMov.setidentDestino(nuevoObj.getobjectReferenceId());
                       objetivoAsignado.setSolving(); // interrumpimos la ejecuci�n y la sustituimos por el nuevo objetivo
                       nuevoObj.setPending();
                       this.getEnvioHechos().actualizarHechoWithoutFireRules(objetivoAsignado);
                       this.getEnvioHechos().actualizarHechoWithoutFireRules(nuevoObj);
                       trazas.aceptaNuevaTrazaEjecReglas(identAgente,"Se ejecuta la tarea : " + identTarea +"  El objetivo asignado : "+ objetivoAsignado.toString()+
                       "Tiene mayor  prioridad que el objetivo en curso. Se cambia de objetivo. Se pone a solving Objetivo1 : "+ objetivoAsignado.toString()+ " Se actualiza el  foco al objetivo:  " + nuevoObj + "\n");
                    }
                     else {
                    trazas.aceptaNuevaTrazaEjecReglas(identAgente, "Robot parado. Se espera el tratamiento de la llegada a destino : "+ objetivoAsignado.toString()+ "Se ejecuta la tarea : " + identTarea + " Objetivo en curso:  " + nuevoObj + "\n");
                    }// verificar si esta parado en destino Se espera a que llegue y se trate la notificacion de llegada 
                       
                       
                       
//                       itfcompMov.moverAdestino(objetivoAsignado.getobjectReferenceId(), victima.getCoordinateVictim(),velocidadCruceroPordefecto);
//                       infoComMov.setitfAccesoComponente(itfcompMov);
//                       this.getEnvioHechos().actualizarHechoWithoutFireRules(infoComMov);
//                       focoActual.setFoco(nuevoObj);
                       
                }
            }

            
            System.out.println("\n" + identAgente + "Se ejecuta la tarea " + identTarea + " Se actualiza el  objetivo:  " + objetivoAsignado + "\n\n");
//            }
            if (infoDecision != null) {
                this.getEnvioHechos().eliminarHechoWithoutFireRules(infoDecision);
            }
//            this.getEnvioHechos().actualizarHecho(obj1);
           String estadoMovRobot= itfcompMov.getIdentEstadoMovRobot();
           infoComMov.setidentEstadoRobot(estadoMovRobot);
            
            this.getEnvioHechos().actualizarHechoWithoutFireRules(infoComMov);
//            this.getEnvioHechos().actualizarHechoWithoutFireRules(misObjs);
            // El foco debe ponerse en la ultima decision pendiente o en su caso al ultimo objetivo solving
            // esto implica que una vez conseguido el objetivo continua con lo que estaba haciendo
//            focoActual.setfaseProcesoConsecObjetivos("DecisionAsignacionVictima");
            focoActual.refocusUltimoObjetivoSolving();
           
            this.getEnvioHechos().actualizarHecho(focoActual);
            trazas.aceptaNuevaTrazaEjecReglas(identAgente,"Posicion Robot : "+itfcompMov.getCoordenadasActuales()+
                    "estado del Movimiento del Robot: "+estadoMovRobot+" Victima objetivo  :  "+infoComMov.getidentDestino()+
                    " Victima asignada  :  "+ victima + " El foco actual es : "+ focoActual+ "\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

