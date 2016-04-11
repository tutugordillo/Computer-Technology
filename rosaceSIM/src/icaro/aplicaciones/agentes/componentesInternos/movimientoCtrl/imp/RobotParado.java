/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package icaro.aplicaciones.agentes.componentesInternos.movimientoCtrl.imp;

import icaro.aplicaciones.Rosace.informacion.Coordinate;
import icaro.aplicaciones.Rosace.informacion.VocabularioRosace;
import icaro.aplicaciones.agentes.componentesInternos.movimientoCtrl.ItfUsoMovimientoCtrl;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.Informe;
import icaro.infraestructura.recursosOrganizacion.recursoTrazas.imp.componentes.InfoTraza;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author FGarijo
 */
public class RobotParado extends EstadoAbstractoMovRobot implements ItfUsoMovimientoCtrl{
   private Semaphore semaforo;
    public  RobotParado (MaquinaEstadoMovimientoCtrl maquinaEstados){
       
   // this.Inicializar(itfProcObjetivos);
  //  MovimientoCtrlImp estado = estadosCreados.get(EstadoMovimientoRobot.RobotParado);
     super (maquinaEstados,MaquinaEstadoMovimientoCtrl.EstadoMovimientoRobot.RobotParado);
     semaforo=new Semaphore(1);
//     estadoActual = this;
    //  this.Inicializar(itfProcObjetivos) ; 
 
    }
//   @Override
//    public  void inicializarInfoMovimiento(Coordinate coordInicial, Integer velocidadInicial){
       
//   } 
    @Override
    public void inicializarInfoMovimiento(Coordinate coordInicial, float velocidadInicial){
        this.robotposicionActual = coordInicial;
        this.maquinaEstados.setCoordenadasActuales(coordInicial);
        this.maquinaEstados.setVelocidadInicial(velocidadInicial);
    } 
    @Override
        public void moverAdestino(String identDest,Coordinate coordDestino, float velocidadCrucero) {
            try {
			semaforo.acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
         if (coordDestino!= null)  {
             if (identDest.equals(identDestino))
              this.trazas.trazar (this.identComponente, " No ha variado el destino en el que estoy : " +robotposicionActual + "  Destino :"+identDest , InfoTraza.NivelTraza.error);
              else {
           this.destinoCoord = coordDestino;
           this.identDestino = identDest; 
           if (velocidadCrucero >0){ this.velocidadCrucero = velocidadCrucero;
        //    this.distanciaDestino = this.distanciaEuclidC1toC2(this.robotposicionActual, destinoCoord);
        //    long tiempoParaAlcanzarDestino = (long)(distanciaDestino/velocidadCrucero);
        //    int intervaloEnvioInformes = (int)tiempoParaAlcanzarDestino/10; // 10 informes maximo
           this.robotposicionActual = this.maquinaEstados.getCoordenadasActuales();
//           this.robotposicionActual = this.getCoordenadasActuales();  
           if (monitorizacionLlegadaDestino!= null)monitorizacionLlegadaDestino.finalizar();
           trazas.trazar(identComponente, "Estoy parado en la posicion : "+robotposicionActual +
                  "  Me muevo al destino  : " + identDestino +" Coordenadas:  " + destinoCoord, InfoTraza.NivelTraza.error);
               this.monitorizacionLlegadaDestino = new HebraMonitorizacionLlegada (this.identAgente,maquinaEstados,this.itfusoRecVisSimulador);       
               monitorizacionLlegadaDestino.inicializarDestino(this.identDestino,robotposicionActual,this.destinoCoord,this.velocidadCrucero); 
//               monitorizacionLlegadaDestino.start();
               monitorizacionLlegadaDestino.start();
           this.maquinaEstados.cambiarEstado(MaquinaEstadoMovimientoCtrl.EstadoMovimientoRobot.RobotEnMovimiento);
           }else trazas.trazar(identComponente, "La velocidad debe ser mayor que cero. Se ignora la operacion", InfoTraza.NivelTraza.error);
           
        }
             }
         this.semaforo.release();
    }
    @Override
    public synchronized boolean estamosEnDestino(String destinoId){
//        if(identDestino==null)return false;
         return (destinoId.equals(identDestino) );
    }      
    @Override
        public void cambiaVelocidad( float nuevaVelocidadCrucero) {
            this.velocidadCrucero = nuevaVelocidadCrucero;
        }
    @Override
        public void cambiaDestino(String identDest,Coordinate coordDestino) {
            this.destinoCoord = coordDestino;
            this.identDestino = identDest;
        }
    @Override
        public void parar(){
//        if (monitorizacionLlegadaDestino != null)monitorizacionLlegadaDestino.finalizar();
//        Informe informeParada = new Informe (identComponente,this.identDestino, VocabularioRosace.MsgeRobotParado);
//        this.itfProcObjetivos.insertarHecho(informeParada);
        trazas.trazar(identAgente, "Se recibe orden de parada cuando El robot esta en el estado :"+ identEstadoActual, InfoTraza.NivelTraza.debug);
        
        }
    @Override
        public void bloquear(){
//            if (monitorizacionLlegadaDestino!=null ) this.monitorizacionLlegadaDestino.finalizar();
            this.maquinaEstados.cambiarEstado (MaquinaEstadoMovimientoCtrl.EstadoMovimientoRobot.RobotBloqueado);
    }
    @Override
        public void continuar(){    
        if (distanciaDestino > 0){
            // calcular el tiempo al destino y poner en marcha el reloj de llegada
           estadoActual=  this.maquinaEstados.cambiarEstado(MaquinaEstadoMovimientoCtrl.EstadoMovimientoRobot.RobotEnMovimiento);
            this.maquinaEstados.moverAdestino(identDestino,destinoCoord, velocidadCrucero);
        }
            this.trazas.trazar (this.identAgente +"."+this.getClass().getSimpleName(), " ignoro la operacion porque estoy parado ", InfoTraza.NivelTraza.debug); 
        }
        @Override
    public  Coordinate getCoordenadasActuales(){
        if (monitorizacionLlegadaDestino!=null ) return this.monitorizacionLlegadaDestino.getCoordRobot();
        else return robotposicionActual;
    }
    @Override
     public  String getIdentEstadoMovRobot(){
         return MaquinaEstadoMovimientoCtrl.EstadoMovimientoRobot.RobotParado.name();
     }

    @Override
    public EstadoAbstractoMovRobot getEstadoActual() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    return  maquinaEstados.getEstadoActual();
    
    }

    @Override
    public boolean paradoEnDestino(String identDestino) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
