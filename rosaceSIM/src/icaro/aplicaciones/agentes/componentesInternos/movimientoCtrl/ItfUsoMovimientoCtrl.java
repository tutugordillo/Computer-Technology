package icaro.aplicaciones.agentes.componentesInternos.movimientoCtrl;

import icaro.aplicaciones.Rosace.informacion.Coordinate;
import icaro.aplicaciones.agentes.componentesInternos.movimientoCtrl.imp.EstadoAbstractoMovRobot;

public interface ItfUsoMovimientoCtrl {
	public void inicializarInfoMovimiento(Coordinate coordInicial, float velocidadInicial) ;
        public void moverAdestino(String identDest,Coordinate coordDestino, float velocidadCrucero) ;
        public void cambiaVelocidad( float nuevaVelocidadCrucero) ;
        public void cambiaDestino(String identDest,Coordinate coordDestino) ;
 //       public void actualizarCoordenadas(Coordinate nuevasCoordenadas) ;
        public Coordinate getCoordenadasActuales() ;
        public String getIdentEstadoMovRobot();
        public  void setCoordenadasActuales(Coordinate nuevasCoordenadas) ;
        public void parar();
        public void bloquear();
        public void continuar();
        public boolean paradoEnDestino(String identDestino);
        public void imposibleAvanzarADestino();
        
}