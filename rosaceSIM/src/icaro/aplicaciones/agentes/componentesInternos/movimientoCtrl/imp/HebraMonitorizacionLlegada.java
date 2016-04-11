package icaro.aplicaciones.agentes.componentesInternos.movimientoCtrl.imp;


import icaro.aplicaciones.Rosace.informacion.Coordinate;
import icaro.aplicaciones.Rosace.informacion.VocabularioRosace;
import icaro.aplicaciones.agentes.componentesInternos.movimientoCtrl.ItfUsoMovimientoCtrl;
import icaro.aplicaciones.recursos.recursoVisualizadorEntornosSimulacion.ItfUsoRecursoVisualizadorEntornosSimulacion;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.Informe;
import icaro.infraestructura.patronAgenteCognitivo.percepcion.imp.PercepcionAgenteCognitivoImp;
import icaro.infraestructura.patronAgenteCognitivo.procesadorObjetivos.factoriaEInterfacesPrObj.ItfProcesadorObjetivos;
import org.apache.log4j.Logger;
import org.openide.util.Exceptions;


/**
* Clase interna que se encarga de generar eventos de monitorizacin cada cierto tiempo
*
* 
*/
public class HebraMonitorizacionLlegada extends Thread {
    /**
	 * Milisegundos que esperar antes de lanzar otra monitorizacin
	 * @uml.property  name="milis"
	 */
    protected long milis;

    /**
	 * Indica cundo debe dejar de monitorizar
	 * @uml.property  name="finalizar"
	 */
    protected volatile boolean finalizar = false;

    /**
	 * Agente reactivo al que se pasan los eventos de monitorizacin
	 * @uml.property  name="agente"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
//     protected ItfUsoMovimientoCtrl controladorMovimiento;
    public MaquinaEstadoMovimientoCtrl controladorMovimiento;
    private Logger log = Logger.getLogger(this.getClass().getSimpleName());
     /**
	 * Evento a producir
	 * @uml.property  name="evento"
	 */
     private String identDestino, identRobot;
     private   volatile Coordinate coordActuales;
     private volatile Coordinate coordDestino;
     private volatile Coordinate coordIncremento;
     private double velocidadRobot; // en metros por segundo 
//     protected int intervaloEnvioInformacion = 1000; // por defecto en ms . Deberia ser configurable
     private volatile double pendienteRecta;
     private volatile boolean estamosEnDestino;
     private double espacioRecorrido ;
//     protected double distanciaDestino ;
    private boolean pendienteInfinita = false ;
    private volatile boolean parar = false ;
//    private volatile boolean iniciar = false ;
    private volatile boolean enDestino = false ;
    private float distanciaArecorrer ;
    private float b ; // punto corte recta con eje Y
    private int dirX =0, dirY=0,incrementoDistancia=0;
    private int intervaloEnvioInformesMs ;
    private int distanciaRecorridaEnIntervaloInformes ;
    private long tiempoParaAlcanzarDestino =3000;
    public ItfUsoRecursoVisualizadorEntornosSimulacion itfusoRecVisSimulador;
    public ItfProcesadorObjetivos itfProcObjetivos;
//    private int numeroPuntos = 20;
    /**
     * Constructor
     *
     * @param milis Milisegundos a esperar entre cada monitorizacion
     */
    public HebraMonitorizacionLlegada(String idRobot,MaquinaEstadoMovimientoCtrl contrMovimiento,  ItfUsoRecursoVisualizadorEntornosSimulacion itfRecVisSimulador) {
      super("HebraMonitorizacion "+idRobot);  
      controladorMovimiento =contrMovimiento;
      this.itfusoRecVisSimulador = itfRecVisSimulador;
      identRobot = idRobot;
      itfProcObjetivos = contrMovimiento.itfProcObjetivos;
    }
  public synchronized void inicializarDestino (String idDestino,Coordinate coordRobot,Coordinate coordDest, double velocidad ){    
//      this.finalizar= false;
      coordActuales =coordRobot ;
      coordDestino = coordDest;
      velocidadRobot = velocidad; // 
//      intervaloEnvioInformacion= intervEnvioInformacion; 
      espacioRecorrido = 0;
      identDestino = idDestino;
//      this. pendienteRecta = (float) ((coordDestino.y-coordActuales.y)/(coordDestino.x-coordActuales.x));
      log.debug ("Coord Robot " + identRobot + " iniciales -> ("+this.coordActuales.getX() + " , " + this.coordActuales.getY() + ")");
      log.debug ("Coord Robot " + identRobot + " destino -> ("+this.coordDestino.getX() + " , " + this.coordDestino.getY() + ")");
      this.setDaemon(true);
//      coordIncremento = this.calcularIncrementosCoordenadasAvelocidadConstante(intervaloEnvioInformacion);
 //     this.evento = notificacionAProducir;
      this.finalizar= false;
      this.enDestino = false;
//       distanciaDestino = this.distanciaEuclidC1toC2(coordActuales, coordDestino);
       double incrX=(coordDestino.getX()-coordActuales.getX());
       double incrY=(coordDestino.getY()-coordActuales.getY());
       if (incrX>0)dirX=1 ;
               else dirX=-1;
       if (incrY>0)dirY=1 ;
               else dirY=-1;
       if (incrX==0 &&incrY!=0){pendienteInfinita= true;
                     distanciaArecorrer = (float)incrY;
                }else if (incrX==0 &&incrY==0) finalizar=true;
       else { pendienteRecta = (float) Math.abs(incrY/incrX);
        this.distanciaArecorrer =(float) Math.sqrt(incrX*incrX+incrY*incrY);
        this.b = (float) (coordActuales.y -pendienteRecta * coordActuales.x ) ;
        
        
       }
//        this.incrementoDistancia= (int)distanciaArecorrer/numeroPuntos;
//        tiempoParaAlcanzarDestino = (long)(distanciaArecorrer/velocidadRobot); // en milisegundos
//       int intervaloEnvioInformesMs = (int)tiempoParaAlcanzarDestino/numeroPuntos;
//       intervaloEnvioInformesMs = 40;
//        distanciaRecorridaEnIntervaloInformes = (long)(1+velocidadRobot*intervaloEnvioInformesMs/50);
//       coordIncremento = this.calcularIncrementosCoordenadasAvelocidadConstante(intervaloEnvioInformacion);
        intervaloEnvioInformesMs = (int)velocidadRobot* 50;
        distanciaRecorridaEnIntervaloInformes = 1;
        
//        try {
//            this.itfusoRecVisSimulador.inicializarDestinoRobot(identRobot, coordActuales, identDestino,coordDestino, velocidadRobot);
//        } catch (Exception ex) {
//            Exceptions.printStackTrace(ex);
//        }
    }


    /**
     * Termina la monitorizacin
     */
    public synchronized void finalizar() {
	this.finalizar= true;
        this.notifyAll();
        
    }
    public synchronized void setCoordRobot(Coordinate robotCoord) {
	this.coordActuales= robotCoord;
    }
    public synchronized Coordinate getCoordRobot() {
	return coordActuales;
    }
    public synchronized void setCoordDestino(Coordinate destCoord) {
        try {
            this.coordDestino= destCoord;
            if (itfusoRecVisSimulador != null)
                this.itfusoRecVisSimulador.inicializarDestinoRobot(identRobot, coordActuales, identDestino,coordDestino, velocidadRobot);
        } catch (Exception ex) {
            Exceptions.printStackTrace(ex);
        }
    }
    public synchronized Coordinate getCoordDestino() {
	return coordDestino;
    }
    public synchronized void setVelocidadRobot(double velRobot) {
	this.velocidadRobot= velRobot;
    }
    // se informa al control de que estamos en el destino. Se cambia el estado a parar
//        estadoActual = this.cambiarEstado(MaquinaEstadoMovimientoCtrl.EstadoMovimientoRobot.RobotParado);
//        this.estadoActual.identDestino = identDest;
//        Informe informeLlegada = new Informe (identComponente,identDest, VocabularioRosace.MsgeLlegadaDestino);
//        Temporizador informeTemp = new Temporizador (500,itfProcObjetivos,informeLlegada);
//        robotposicionActual = monitorizacionLlegadaDestino.getCoordDestino();

    @Override
    public synchronized void run() {
      
//       double espacioRecorridoEnIntervalo = velocidadRobot*intervaloEnvioInformacion;
        log.debug ("Coord Robot " + identRobot + " iniciales -> ("+this.coordActuales.getX() + " , " + this.coordActuales.getY() + ")");
        log.debug ("Coord Robot " + identRobot + " destino -> ("+this.coordDestino.getX() + " , " + this.coordDestino.getY() + ")");
//       System.out.println("Coord Robot " + identRobot + " iniciales -> ("+this.coordActuales.x + " , " + this.coordActuales.y + ")");
//      this.itfusoRecVisSimulador.mostrarMovimientoAdestino(identRobot,identDestino, coordActuales,velocidadRobot);
 log.debug("Inicio ciclo de envio de coordenadas  " + identRobot + " en destino -> ("+this.coordActuales.getX() + " , " + this.coordActuales.getY() + ")");           
        while (!this.finalizar && (!enDestino)) {
	  try {  
//            Thread.sleep(intervaloEnvioInformesMs);
	  Thread.sleep(intervaloEnvioInformesMs);
                     calcularNuevasCoordenadas (distanciaRecorridaEnIntervaloInformes);                      
//                     log.debug("Coord Robot " + identRobot + " calculadas -> ("+this.coordActuales.getX() + " , " + this.coordActuales.getY() + ")");  
                     enDestino = ((coordActuales.getX()-coordDestino.getX())*dirX>=0 &&(coordActuales.getY()-coordDestino.getY())*dirY>=0);
                     finalizar = (coordActuales.x<0.5 || coordActuales.y<0.5 );
                     if (itfusoRecVisSimulador != null)
                         this.controladorMovimiento.setCoordenadasActuales(coordActuales);
                        this.itfusoRecVisSimulador.mostrarPosicionRobot(identRobot, coordActuales);
                       
//                        this.notifyAll();
        } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
      }
		}
      if (enDestino ){
          finalizar = true;
            try {
                Thread.sleep(tiempoParaAlcanzarDestino);
                this.controladorMovimiento.estamosEnDestino(identDestino,coordDestino );
                log.debug("Coord Robot En thread  " + identRobot + " en destino -> ("+this.coordActuales.getX() + " , " + this.coordActuales.getY() + ")");
          System.out.println("Coord Robot En thread  " + identRobot + " en destino -> ("+this.coordActuales.x + " , " + this.coordActuales.y + ")");       
                this.controladorMovimiento.setCoordenadasActuales(coordDestino);
                // se informa al control de que estamos en el destino. Se cambia el estado a parar

        Informe informeLlegada = new Informe (this.identRobot,this.identDestino, VocabularioRosace.MsgeLlegadaDestino);
//        Temporizador informeTemp = new Temporizador (500,itfProcObjetivos,informeLlegada);
        itfProcObjetivos.insertarHecho(informeLlegada);
        this.notifyAll();     
            } catch (Exception ex) {
                log.error( ex);
            }
      }
              }
 
private void calcularNuevasCoordenadas (long incrementoDistancia){
    // suponemos avance en linea recta 
    // formula aplicada x1 = xo + sqrt( espacioRecorrido**2 / (1 + pendienteRecta**2))
    //  y1 = y0 +(x1-x0)*pendienteRecta
    
        if (pendienteInfinita){
//            constIncrX = 0;
//            constIncrY= incrementoDistancia;
             this.coordActuales.setY(coordActuales.getY() + incrementoDistancia*dirY);
        }
        else {
            // incremmento de x respecto a distancia recorrida 
            this.coordActuales.setY(coordActuales.getY() + pendienteRecta*incrementoDistancia*dirY);
            this.coordActuales.setX(coordActuales.getX() + incrementoDistancia*dirX);
//            if ((coordActuales.getX()-coordDestino.getX())*dirX>=0 &&(coordActuales.getY()-coordDestino.getY())*dirY>=0){
//                enDestino = true;
////                System.out.println("Coord Robot En Calculo  " + identRobot + " en destino -> ("+this.coordActuales.x + " , " + this.coordActuales.y + ")");
//             log.debug("Coord Robot En Calculo  " + identRobot + " en destino -> ("+this.coordActuales.x + " , " + this.coordActuales.y + ")");   
//            }
//          i
        }
}

 //   public synchronized boolean getOrdenInicio() {
 //        return iniciar;
//    }
//
//    public void setOrdenInicio(boolean ordenInicio) {
//       iniciar= ordenInicio;
//    }

}
