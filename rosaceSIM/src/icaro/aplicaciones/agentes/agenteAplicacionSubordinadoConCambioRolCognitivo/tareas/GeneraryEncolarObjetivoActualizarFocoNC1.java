/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package icaro.aplicaciones.agentes.agenteAplicacionSubordinadoConCambioRolCognitivo.tareas;

import icaro.aplicaciones.Rosace.informacion.AceptacionPropuesta;
import icaro.aplicaciones.agentes.agenteAplicacionrobotIgualitarioNCognitivo.tareas.*;
import icaro.aplicaciones.Rosace.informacion.Victim;
import icaro.aplicaciones.Rosace.informacion.VictimsToRescue;
import icaro.aplicaciones.Rosace.informacion.VocabularioRosace;
import icaro.aplicaciones.Rosace.objetivosComunes.AyudarVictima;
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
public class GeneraryEncolarObjetivoActualizarFocoNC1 extends TareaSincrona {
private  enum EstadoMovimientoRobot {Indefinido,RobotParado, RobotEnMovimiento, RobotBloqueado,RobotavanceImposible,enDestino,  error}
private ItfUsoMovimientoCtrl itfcompMov;
	private Victim victima;
	private int velocidadCruceroPordefecto= 1;
    @Override
    public void ejecutar(Object... params) {
        
        //    ItfUsoRecursoEstadistica itfUsoRecursoEstadistica=null;
       // int velocidadCruceroPordefecto = 1;// metros por segundo
        //Para recoger estadisticas del instante de envio de victimas desde el centro de control

        try {
//             trazas = NombresPredefinidos.RECURSO_TRAZAS_OBJ;
            MisObjetivos misObjs = (MisObjetivos) params[0];
             Focus focoActual = (Focus)params[1];
//             Victim victima = (Victim) params[2];
              victima = (Victim) params[2];
             AceptacionPropuesta propuestaAceptada = (AceptacionPropuesta) params[3];
             InfoCompMovimiento infoComMov  = (InfoCompMovimiento)params[4];
             VictimsToRescue victimas = (VictimsToRescue)params[5];
            String nombreAgenteEmisor = this.getIdentAgente();

            //Para anotar en el fichero cual es mi coste
            int miEvaluacion = victima.getEstimatedCost();
             if (miEvaluacion!=-1) miEvaluacion = Integer.MAX_VALUE - miEvaluacion;
             else miEvaluacion = miEvaluacion;    //SI EL COSTE EL -1 INDICARIA QUE SE HA HECHO CARGO PERO QUE NO PUEDE IR (NO TIENE RECURSOS)
//            Objetivo objetivoAsignado = focoActual.getFoco();
            //ACTUALIZAR ESTADISTICAS
            //Inicializar y recuperar la referencia al recurso de estadisticas        	
            long tiempoActual = System.currentTimeMillis();
            String refVictima = victima.getName();
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
            Thread t = new Thread(){
				
				public void run(){
					
					itfcompMov.moverAdestino(victima.getName(), victima.getCoordinateVictim(), velocidadCruceroPordefecto); 
				}
			};
            AyudarVictima objetivoAsignado = new AyudarVictima(refVictima);
                objetivoAsignado.setPriority(victima.getPriority());
                objetivoAsignado.setSolving() ;
                victimas.addVictimToRescue(victima);
//                misObjs.addObjetivo(objetivoAsignado);
            Objetivo objetivoActual = misObjs.getobjetivoMasPrioritario();
            String estadoMovRobot= null;
//             while( objetivoActual != null&& objetivoActual.getPriority()>0 ){
//                 if (objetivoActual.getState()== Objetivo.SOLVED){  
//                                    objetivoActual.setPriority(-1);
//                                    misObjs.cambiarPrioridad(objetivoActual);
//                                    objetivoActual=misObjs.getobjetivoMasPrioritario();
//                            }
//             }
//              ItfUsoMovimientoCtrl itfcompMov = (ItfUsoMovimientoCtrl) infoComMov.getitfAccesoComponente();
               itfcompMov = (ItfUsoMovimientoCtrl) infoComMov.getitfAccesoComponente();
               estadoMovRobot=itfcompMov.getIdentEstadoMovRobot();
//             } if ( nuevoObj == null||nuevoObj.getState()==Objetivo.SOLVED){
             if ( objetivoActual==null||objetivoActual.getState()==Objetivo.SOLVED){// se pone el objetivo actual a solving y se da orden para que se empiece a mover
                misObjs.addObjetivo(objetivoAsignado);
                focoActual.setFoco(objetivoAsignado);
                infoComMov.setidentDestino(objetivoAsignado.getobjectReferenceId());
//                itfcompMov.moverAdestino(objetivoAsignado.getobjectReferenceId(), victima.getCoordinateVictim(), velocidadCruceroPordefecto);
                t.run();
                estadoMovRobot = EstadoMovimientoRobot.RobotEnMovimiento.name();
//                infoComMov.setidentEstadoRobot(estadoMovRobot);
//                this.getEnvioHechos().actualizarHechoWithoutFireRules(infoComMov);
                this.getEnvioHechos().actualizarHechoWithoutFireRules(objetivoAsignado);
                this.getEnvioHechos().actualizarHechoWithoutFireRules(misObjs);
                
                trazas.aceptaNuevaTrazaEjecReglas(identAgente, "No hay objetivos anteriores Objetivo considerado : "+ objetivoAsignado.toString()+
                        "Se ejecuta la tarea : " + identTarea + " Se actualiza el  foco al objetivo:  " + objetivoAsignado +
                        "estado del robot : "+estadoMovRobot+"\n");
            
            }else{       
        // hay un objetivo en curso ayudar victima comparamos prioridades
                if(objetivoAsignado.getPriority()<= objetivoActual.getPriority()){ // tiene menor prioridad  encolamos el objetivo
                                    misObjs.addObjetivo(objetivoAsignado);
                                    this.getEnvioHechos().actualizarHecho(misObjs);
                 trazas.aceptaNuevaTrazaEjecReglas(identAgente, "Se ejecuta la tarea : " + identTarea + "El objetivo asignado : "+ objetivoAsignado.toString()+
                       "Tiene menor o igual prioridad que el objetivo en curso  :  " + objetivoActual +
                        "estado del robot : "+estadoMovRobot+"\n");                   
                }
                else {// El objetivo actual tiene mayor prioridad
                    // se  mira si el robot se esta moviendo a rescatar la victima                      
                    victima = victimas.getVictimToRescue(objetivoActual.getobjectReferenceId());
                    focoActual.setFoco(objetivoActual);
                    if (estadoMovRobot.equalsIgnoreCase(EstadoMovimientoRobot.RobotEnMovimiento.name())){   
                       itfcompMov.cambiaDestino(objetivoActual.getobjectReferenceId(), victima.getCoordinateVictim());
                       infoComMov.setidentDestino(objetivoActual.getobjectReferenceId());
////                       objetivoAsignado.setSolving(); // interrumpimos la ejecuci�n y la sustituimos por el nuevo objetivo
                       objetivoActual.setPending();
                       this.getEnvioHechos().actualizarHechoWithoutFireRules(objetivoAsignado);
                       this.getEnvioHechos().actualizarHechoWithoutFireRules(objetivoActual);
                      
                       trazas.aceptaNuevaTrazaEjecReglas(identAgente,"Se ejecuta la tarea : " + identTarea +"  El objetivo asignado : "+ objetivoAsignado.toString()+
                       "Tiene mayor  prioridad que el objetivo en curso. Se cambia de objetivo. Se pone a solving Objetivo1 : "+ objetivoAsignado.toString()+ " Se actualiza el  foco al objetivo:  " + objetivoActual + "\n");
                    }
                     else {
                    trazas.aceptaNuevaTrazaEjecReglas(identAgente, "Robot parado. Se espera el tratamiento de la llegada a destino : "+ objetivoAsignado.toString()+ "Se ejecuta la tarea : " + identTarea + " Objetivo en curso:  " + objetivoActual + "\n");
                    infoComMov.setidentDestino(objetivoAsignado.getobjectReferenceId());
                    itfcompMov.moverAdestino(objetivoAsignado.getobjectReferenceId(), victima.getCoordinateVictim(), velocidadCruceroPordefecto);
                    }// verificar si esta parado en destino Se espera a que llegue y se trate la notificacion de llegada 
                }
            }
            System.out.println("\n" + identAgente + "Se ejecuta la tarea " + identTarea + " Se actualiza el  objetivo:  " + objetivoAsignado + "\n\n");
//            trazas.aceptaNuevaTrazaEjecReglas(identAgente,"Posicion Robot despues de ordenar movimiento : "+itfcompMov.getCoordenadasActuales()+
//                    "estado del Movimiento del Robot: "+estadoMovRobot+" Victima objetivo  :  "+infoComMov.getidentDestino()+
//                    " Victima asignada  :  "+ victima + " El foco actual es : "+ focoActual+ "\n");
           this.getEnvioHechos().eliminarHechoWithoutFireRules(propuestaAceptada);
            this.getEnvioHechos().actualizarHecho(focoActual);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
}

