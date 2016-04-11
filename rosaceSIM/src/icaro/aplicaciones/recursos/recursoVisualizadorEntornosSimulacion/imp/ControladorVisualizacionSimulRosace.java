/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package icaro.aplicaciones.recursos.recursoVisualizadorEntornosSimulacion.imp;

import icaro.aplicaciones.Rosace.informacion.RobotCapability;
import icaro.aplicaciones.Rosace.informacion.RobotStatus1;
import icaro.aplicaciones.Rosace.informacion.VocabularioRosace;
import icaro.aplicaciones.recursos.recursoPersistenciaEntornosSimulacion.ItfUsoRecursoPersistenciaEntornosSimulacion;
import icaro.infraestructura.entidadesBasicas.comunicacion.InfoContEvtMsgAgteReactivo;
import java.awt.Image;
import java.awt.Point;
import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.netbeans.api.visual.action.ActionFactory;
import org.netbeans.api.visual.action.WidgetAction;
import org.netbeans.api.visual.animator.SceneAnimator;
import org.netbeans.api.visual.model.ObjectScene;
import org.netbeans.api.visual.widget.LayerWidget;
import org.openide.util.Exceptions;

/**
 *
 * @author FGarijo
 */
public class ControladorVisualizacionSimulRosace {
     private NotificadorInfoUsuarioSimulador notifEvts;
    private int intervaloSecuencia = 10000; // valor por defecto. Eso deberia ponerse en otro sitio
    private int numMensajesEnviar = 3;
    private boolean primeraVictima = true;
    private VisorControlSimuladorRosace visorControlSim;
    private ArrayList identsRobotsEquipo ;
    private javax.swing.JLabel jLabelAux;
    private String directorioTrabajo;
     private String tituloVentanaVisor = "ROSACE Scenario Visor";
    private String rutassrc = "src/";   //poner "src/main/java" si el proyecto de icaro se monta en un proyecto maven
    private String rutapaqueteConstructorEscenariosROSACE = "utilsDiseniaEscenariosRosace/";
    private static  Image IMAGErobot,IMAGEmujer,IMAGEmujerRes ;
    private String rutaIconos = "\\src\\utilsDiseniaEscenariosRosace\\";
//    private String rutaPersistenciaEscenario = "\\src\\persistenciaEscenarios\\";
    private String directorioPersistencia = VocabularioRosace.NombreDirectorioPersistenciaEscenarios+File.separator;
     private String imageniconoHombre = "Hombre.png";
    private String imageniconoMujer = "Mujer.png";
    private String imageniconoMujerRescatada = "MujerRescatada.png";
    private String imageniconoHombreRescatado = "HombreRescatado.png";
    private String imageniconoRobot = "Robot.png";
    private String modeloOrganizativoInicial = "Igualitario";
    private String tituloAvisoEscenarioNoDefinido= "Escenario indefinido";
    private String mensajeEscenarioNoDefinido= "El esceneraio de simulaci�n no esta definido ";
    private String recomendacionDefinirEscenario= " Abrir un escenario con el menu de edicion o crear un escenario nuevo";
    private String mensajeEscenarioNoSeleccionado= "No se ha seleccionado el esceneraio de simulaci�n ";
    private String tituloAvisoEscenSinRobotsDefinidos= "Escenario sin Robots definidos";
    private String mensajeEscenarioSinRobots= "No se han definido Robots en el escenario ";
    private String recomendacionDefinirRobots= " Definir Robots y Victimas con el bot�n derecho para poder guardar el escenario ";
    private Map<String, JLabel> tablaEntidadesEnEscenario;
    private ArrayList <JLabel> listaEntidadesEnEscenario;
    private JPanel panelVisor;
    JLabel entidadSeleccionada=null;
    private WidgetAction moveAction = ActionFactory.createMoveAction ();
    private Point ultimoPuntoClic ;
    private boolean intencionUsuarioCrearRobot;
    private boolean intencionUsuarioCrearVictima;
    private boolean entidadSeleccionadaParaMover;
    private boolean escenarioActualAbierto = false ;
    private int numeroRobots, mumeroVictimas;
    private volatile GestionEscenariosSimulacion gestionEscComp;
    private volatile EscenarioSimulacionRobtsVictms escenarioEdicionComp, escenarioSimulComp;
    private volatile PersistenciaVisualizadorEscenarios persistenciaLocal;
    private String modeloOrganizativo;
    private String identEquipoActual;
    private VisorCreacionEscenarios1 visorEditorEscen;
    private VisorMovimientoEscenario visorMovimientoEscen;
    private ItfUsoRecursoPersistenciaEntornosSimulacion itfPersistenciaSimul;
    private boolean visorControlSimuladorIniciado;
    private boolean visorMovientoIniciado;
//    private boolean escenarioSimulacionAbierto;
    private boolean peticionObtenerEscenarioValido;
    private boolean escenarioValidoObtenido;
    private boolean robotsVisualizados;
    private boolean victimasVisualizadas;
//    private boolean simulando;
//    private boolean editando;
    private javax.swing.JFileChooser jFileChooser1;
    int maxIntentosPeticionSeleccionEscenario = 2;
    private ArrayList identsVictims;
    private boolean avisoSeleccionVictima;
    
    public  ControladorVisualizacionSimulRosace (NotificadorInfoUsuarioSimulador notificadorInfoUsuarioSimulador){
        notifEvts=notificadorInfoUsuarioSimulador;
        
    }
    public void setIftRecPersistencia(ItfUsoRecursoPersistenciaEntornosSimulacion itfPersisSimul){
        itfPersistenciaSimul = itfPersisSimul;
    }
    
    public void initModelosYvistas(){
//       String  directorioPersistencia = VocabularioRosace.IdentDirectorioPersistenciaEscenarios+File.separator;
//            VisorControlSimuladorRosace visorSc;
         gestionEscComp= new GestionEscenariosSimulacion();
         HashSet setIdentsEscenarios;
       try {
//        if(itfPersistenciaSimul == null){ 
//            persistenciaLocal= new PersistenciaVisualizadorEscenarios();
//             setIdentsEscenarios = persistenciaLocal.obtenerIdentsEscenarioSimulacion(directorioPersistencia);
//        }else
            setIdentsEscenarios = itfPersistenciaSimul.obtenerIdentsEscenarioSimulacion();
              gestionEscComp.setIdentsEscenariosSimulacion(setIdentsEscenarios);
              visorControlSim = new VisorControlSimuladorRosace(this);
              visorEditorEscen= new VisorCreacionEscenarios1(this);
              visorEditorEscen.setGestorEscenarionComp(gestionEscComp);
              visorControlSim.setDirectorioPersistencia(directorioPersistencia);
              visorControlSim.setVisible(true);
                } catch (Exception ex) {
                    Exceptions.printStackTrace(ex);
                    
                }
                     
    }

  public synchronized void peticionComenzarSimulacion(String identEscenarioActual, int intervaloSecuencia) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    if ( escenarioSimulComp ==null)visorControlSim.visualizarConsejo(tituloAvisoEscenarioNoDefinido,mensajeEscenarioNoDefinido,recomendacionDefinirEscenario);
    else if (identEscenarioActual== null)visorControlSim.setIdentEscenarioActual(escenarioEdicionComp.getIdentEscenario());
    else if (intervaloSecuencia <=0)visorControlSim.solicitarDefinicionItervaloSecuencia();
        else notifEvts.sendPeticionSimulacionSecuenciaVictimasToRobotTeam(intervaloSecuencia);
    }

  public synchronized void peticionCrearEscenario() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  
      escenarioEdicionComp = gestionEscComp.crearEscenarioSimulacion();

      escenarioEdicionComp.initEscenario();

//      visorEditorEscen.setEscenarioActualComp(escenarioActualComp);
// System.out.println("Desde Peticion Lista de capacidades definidas del robot  : " + robotInfo.getIdRobot()+" Robot Info : "+ robotInfo.toString() );
      visorEditorEscen.visualizarEscenario(escenarioEdicionComp);
      visorEditorEscen.setVisible(true);
//        ArrayList<String> robotNombres = escenarioActualComp.getListIdentsRobots();
//             for (String ideRobot:robotNombres){
////                 String ideRobot = (String)robtIter.next();
//              RobotInfoEnEscenario1 infoRobot = (RobotInfoEnEscenario1) escenarioActualComp.getRobotInfo(ideRobot);
//              List<RobotCapability> capacidades=infoRobot.getRobotCapabilities();
//                 System.out.println("Despues de  la visualizacion Lista de capacidades a guardar del robot  : " + ideRobot+" Capacidades : "+ capacidades.toString() );
//                }
  }

   public  void peticionAbrirEscenario() {
//        throw new UnsupportedOperationException("Not supported yet."); 
//    File ficheroSeleccionado=   visorControlSim.solicitarSeleccionFichero(directorioPersistencia);
       if (!visorControlSim.hayFicherosCreados()){
           this.visorControlSim.visualizarConsejo("Sin Escenarios de simulacion", "No hay escenarios creados" , "Abrimos el editor de escenarios para definir Robots y Victimas");
           peticionCrearEscenario();
       }else {
       File ficheroSeleccionado=   visorControlSim.solicitarSeleccionFichero();
    if (ficheroSeleccionado==null)visorControlSim.visualizarConsejo(tituloAvisoEscenarioNoDefinido, mensajeEscenarioNoSeleccionado,recomendacionDefinirEscenario);
    else{
           try {
               escenarioEdicionComp = itfPersistenciaSimul.obtenerInfoEscenarioSimulacion(ficheroSeleccionado.getName());
           } catch (Exception ex) {
               Exceptions.printStackTrace(ex);
               System.out.println("Desde peticion Abrir escenario . No se encuetra el fichero Ident Fichero : " + ficheroSeleccionado.getAbsolutePath() );
           }
        escenarioEdicionComp.setGestorEscenarios(gestionEscComp);
        identEquipoActual=escenarioEdicionComp.getIdentEscenario();
        visorControlSim.setIdentEscenarioActual(identEquipoActual);
        identsRobotsEquipo=escenarioEdicionComp.getListIdentsRobots();
        if(escenarioActualAbierto){
            visorEditorEscen.setVisible(false);
            escenarioActualAbierto=false;
        }
        if( identsRobotsEquipo!=null) visorControlSim.visualizarIdentsEquipoRobot(identsRobotsEquipo);
    }
    }
   } 

   public void peticionEliminarEscenario() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

  public  void peticionPararRobot(String identRobotSeleccionado) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


// private void consejoUsuario(String mensajeConsejo, String recomendacion) {
////        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//     visorControlSim.visualizarConsejo(tituloAvisoEscenarioNoDefinido,mensajeConsejo,recomendacion);
// }
     public static void main(String args[]) {
        
        new ControladorVisualizacionSimulRosace(new NotificadorInfoUsuarioSimulador("prueba1", "agente2")).initModelosYvistas();
      
     }

public  void peticionPararSimulacion() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

public  void peticionGuardarEscenario(EscenarioSimulacionRobtsVictms escenarioComp) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    // si no hay un escenario actual definido indicamos al usuario que no hay escenario definido
    // si en el escenario a guardar no hay robots ni victimas se lo decimos
        escenarioEdicionComp = escenarioComp;
        if (escenarioEdicionComp.getNumRobots()<=0)visorEditorEscen.visualizarConsejo(tituloAvisoEscenSinRobotsDefinidos, mensajeEscenarioSinRobots, recomendacionDefinirRobots);
        else{
            int respuesta =visorEditorEscen.confirmarPeticionGuardarEscenario("Se va a guardar el escenario : ");
            if (respuesta==JOptionPane.OK_OPTION){
                
                 ArrayList<String> robotNombres = escenarioComp.getListIdentsRobots();
             for (String ideRobot:robotNombres){
//                 String ideRobot = (String)robtIter.next();
              RobotStatus1 infoRobot = (RobotStatus1) escenarioComp.getRobotInfo(ideRobot);
              List<RobotCapability> capacidades=infoRobot.getRobotCapabilities();
                 System.out.println("Desde peticion Guardar Lista de capacidades a guardar del robot  : " + ideRobot+"Capacidades : "+ capacidades.toString() );
             }
             System.out.println("Desde peticion Guardar Numero de Robots  : " + escenarioEdicionComp.getNumRobots()+" Numero de victimas : "+ escenarioEdicionComp.getNumVictimas());
//             if (itfPersistenciaSimul==null)   
//                persistenciaLocal.guardarInfoEscenarioSimulacion(directorioPersistencia, escenarioEdicionComp);
//             else 
                 try {
                     itfPersistenciaSimul.guardarInfoEscenarioSimulacion(escenarioEdicionComp);
                 } catch (Exception ex) {
                     Exceptions.printStackTrace(ex);
                 }
                if ( peticionObtenerEscenarioValido&&!escenarioValidoObtenido){
                    // se envia el escenario al agente controlador que puede estar esperandolo
                    notifEvts.sendInfoEscenarioSeleccionado(escenarioEdicionComp);
                }
            }
            if (!visorMovientoIniciado){
            visorControlSim.visualizarIdentsEquipoRobot(escenarioComp.getListIdentsRobots());
            visorControlSim.setIdentEscenarioActual(escenarioComp.getIdentEscenario());
            }
        } 
    }
public  void peticionMostrarEscenarioMovimiento(EscenarioSimulacionRobtsVictms escenarioComp) {
    if(escenarioComp!=null){
        try {
            if (visorEditorEscen.isShowing())visorEditorEscen.setVisible(false);
            if (visorMovimientoEscen == null)visorMovimientoEscen = new VisorMovimientoEscenario(escenarioComp);
            else visorMovimientoEscen.visualizarEscenario(escenarioComp);
            visorMovimientoEscen.visualizarEscenario();
            escenarioSimulComp = escenarioComp;
            visorMovientoIniciado = true;
        } catch (Exception ex) {
            Exceptions.printStackTrace(ex);
        }
    }
}
public  void peticionMostrarEscenarioActual() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    if (escenarioSimulComp != null)visorMovimientoEscen.setVisible(true);
    else if ( escenarioEdicionComp ==null)visorControlSim.visualizarConsejo(tituloAvisoEscenarioNoDefinido,mensajeEscenarioNoDefinido,recomendacionDefinirEscenario);
    else{
        visorEditorEscen.visualizarEscenario(escenarioEdicionComp);
        escenarioActualAbierto=true;
    }
    }

public  boolean abrirVisorConEscenario(String identFicheroEscenarioSimulacion) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    if(!visorControlSimuladorIniciado){
//        initModelosYvistas();
        if (identFicheroEscenarioSimulacion==null){ // Se abre el visor sin 
            visorEditorEscen.setVisible(true);
        }
        if(identFicheroEscenarioSimulacion !=null)
        try {
            escenarioEdicionComp= itfPersistenciaSimul.obtenerInfoEscenarioSimulacion(identFicheroEscenarioSimulacion);
        } catch (Exception ex) {
            Exceptions.printStackTrace(ex);
        }
       if (escenarioEdicionComp ==null){
           this.visorControlSim.visualizarConsejo("fichero no encontrado ", "verifique el identificador del fichero ", "Puede abrir  un escenario existente o crear un nuevo escenario");
           return false ;
       }
        visorControlSim.setIdentEscenarioActual(identFicheroEscenarioSimulacion);
        visorControlSim.visualizarIdentsEquipoRobot(escenarioEdicionComp.getListIdentsRobots());
        visorControlSimuladorIniciado=true;
        visorEditorEscen.visualizarEscenario(escenarioEdicionComp);
    }
    return true;
    }
 public  boolean abrirVisorMovimientoConEscenario(String identFicheroEscenarioSimulacion) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    if(!visorMovientoIniciado){
        try {
            //        initModelosYvistas();
            escenarioEdicionComp= itfPersistenciaSimul.obtenerInfoEscenarioSimulacion(identFicheroEscenarioSimulacion);
            if (escenarioEdicionComp ==null){
                this.visorControlSim.visualizarConsejo("fichero no encontrado ", "verifique el identificador del ficero", null);
                return false ;
            }
            visorMovimientoEscen = new VisorMovimientoEscenario(escenarioEdicionComp);
            visorMovimientoEscen.visualizarEscenario();
//            visorControlSim.visualizarIdentsEquipoRobot(escenarioActualComp.getListIdentsRobots());
            visorControlSimuladorIniciado=true;
            visorMovientoIniciado = true;
        } catch (Exception ex) {
            Exceptions.printStackTrace(ex);
        }
    }
    return true;
    }
 public VisorMovimientoEscenario getVisorMovimiento(){
    if(visorMovientoIniciado) return visorMovimientoEscen;
    else return null;
 } 

public void peticionVisualizarIdentsRobots(ArrayList identList) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
   // si los identificadores recibidos son diferentes a los que hay en el escenario se cambian los del escenario
   // esto es un poco raro pero resuelve temporalmente el problema
    // cambiar los nombres en el computacional y tambien las etiquetas en el visualizador
    
    if (identList!=null){
        visorControlSim.visualizarIdentsEquipoRobot(identList);
        visorControlSim.setIdentEscenarioActual(escenarioEdicionComp.getIdentEscenario());
        this.robotsVisualizados = true;
        if (escenarioEdicionComp.getListIdentsRobots().size()!= identList.size() )
            visorControlSim.visualizarConsejo("Imposible cambiar los identificadores" , "El numero de robots en el escenario actual y el recibido es diferente",
            "revisar las definiciones de los escenarios");
        
        else{
        this.escenarioEdicionComp.renombrarIdentRobts(identList);
         ArrayList<String> robotNombres = escenarioEdicionComp.getListIdentsRobots();
             for (String ideRobot:robotNombres){
//                 String ideRobot = (String)robtIter.next();
              RobotStatus1 infoRobot = (RobotStatus1) escenarioEdicionComp.getRobotInfo(ideRobot);
//              List<RobotCapability> capacidades=infoRobot.getRobotCapabilities();
                 System.out.println("Desde peticionVisualizarIdentsRobots  nuevo ident Robot : " + ideRobot );
             } 
             Object[] victimasIdents = escenarioEdicionComp.getVictims().keySet().toArray();
             for( int i=0; i<victimasIdents.length;i++){
                 String idVictima = (String)victimasIdents[i];
                 System.out.println("Desde peticionVisualizarIdentsRobots  nuevo ident Vicitima : " + idVictima );
             }
             
        visorEditorEscen.visualizarEscenario(escenarioEdicionComp);
        }
    }
    }
public  void peticionSalvarVictima() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    // verificar si hay un escenario de simulacion seleccionado valido y los robots estan visualizados
    // obtener las victimas del escenario, se visualizan y se le pide al usuario que seleccione una
    // situaciones a anotar : escenario valido seleccionado, robots en el escenario visualizados,victimas visualizadas 
    if(robotsVisualizados&&escenarioValidoObtenido){
        if(!victimasVisualizadas){
            identsVictims =  escenarioSimulComp.getListIdentsVictims();
            visorControlSim.visualizarIdentsVictimas(identsVictims);
        }
         visorControlSim.visualizarConsejo("Seleccion de Victima", "Debe seleccionar la victima a rescatar ", "Haga doble clic en el identificador de la victima");
    }
   
      System.out.println("Desde peticionVisualizarIdentsRobots   : " + escenarioValidoObtenido + robotsVisualizados);   
}
 public boolean verificarCaracteristicasEscenarioAbierto (String orgModelo,int numRobots){
         return (this.numeroRobots==numRobots) &&(this.modeloOrganizativo.equalsIgnoreCase(orgModelo));
    }
 public boolean verificarCaracteristicasEscenarioSeleccionado ( File ficheroSeleccionado,String orgModelo,int numRobots ){
         try {
             escenarioEdicionComp = itfPersistenciaSimul.obtenerInfoEscenarioSimulacion(ficheroSeleccionado.getName());
         } catch (Exception ex) {
             Exceptions.printStackTrace(ex);
         }
        if(escenarioEdicionComp == null ) return false;
     identEquipoActual=escenarioEdicionComp.getIdentEscenario();
        numeroRobots = escenarioEdicionComp.getNumRobots();
        modeloOrganizativo= escenarioEdicionComp.getmodeloOrganizativo();
       if(this.numeroRobots==numRobots &&this.modeloOrganizativo.equalsIgnoreCase(orgModelo)){
           // se envia notificacion al agente controlador con el computacional obtenido
        escenarioEdicionComp.setGestorEscenarios(gestionEscComp);  
        escenarioValidoObtenido = true;
        return true;
       }else {
           visorControlSim.visualizarConsejo("Fichero seleccionado No valido ", "El modelo organizativo del fichero seleccionado: "+orgModelo
            + " o el  numero de Robots = : "+ numRobots + " No coinciden ", "Seleccione otro fichero o  cree uno nuevo ");
           return false;
        }
 }

 public   boolean hayEscenarioAbierto() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    return escenarioActualAbierto;
    }

public void peticionObtenerEscenarioSimulacion(String modOrganizativo, int numRobots) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    // casos 
    // caso 1 no hay ningun escenario abierto se informa al usuario que debe seleccionar o crear un escenario para poder hacer la simulacion
    if( escenarioActualAbierto){
          if(this.verificarCaracteristicasEscenarioAbierto(modOrganizativo,numRobots))
            if (  visorControlSim.solicitarConfirmacion("Desea utilizar el escenario actual abierto como escenario de simulacion ?"))
                notifEvts.sendInfoEscenarioSeleccionado(escenarioEdicionComp);
    }
        peticionObtenerEscenarioValido= true;
        escenarioValidoObtenido = false;
         if (!visorControlSim.hayFicherosCreados()) {
             visorControlSim.visualizarConsejo("Creacion de Fichero ", " No hay ficheros de simulacion creados",
                     "Debe crear un fichero de modelo organizativo: "+modOrganizativo+ " y numero de Robots = : "+ numRobots);
             this.peticionCrearEscenario();
             notifEvts.sendNotifAgteControlador("EscenarioEnfaseDeCreacion");
         } else { // hay ficheros definidos le pedimos que seleccione uno 
        
        visorControlSim.visualizarConsejo("Seleccion de Fichero ", "Seleccionar un fichero de modelo organizativo: "+modOrganizativo
            + " y numero de Robots = : "+ numRobots, "Si no tiene ninguno de estas caracteristicas cree uno nuevo ");
        File ficheroSeleccionado = this.peticionSeleccionarEscenario();
        int numeroIntentos=0; boolean ficheroSeleccionadoValido= false;
        while (numeroIntentos< maxIntentosPeticionSeleccionEscenario &&!ficheroSeleccionadoValido  ){
            if (ficheroSeleccionado !=null)
                if(!verificarCaracteristicasEscenarioSeleccionado(ficheroSeleccionado,modOrganizativo, numRobots)){
                 visorControlSim.visualizarConsejo("Fichero no valido ", "Se debe seleccionar un fichero de modelo organizativo: "+modOrganizativo
                + " y numero de Robots = : "+ numRobots, "Si no tiene ninguno de estas caracteristicas cree uno nuevo ");
                ficheroSeleccionado = this.peticionSeleccionarEscenario();
                // Se podrian considerar mas intentos pero lo dejamos aqui
                
            } else {
                ficheroSeleccionadoValido = true;
//              notifEvts.sendInfoEscenarioSeleccionado(escenarioActualComp);
            }else // no se ha seleccionado ningun fichero 
            visorControlSim.visualizarConsejo("Sin seleccion de fichero ", "Se debe seleccionar un fichero de modelo organizativo: "+modOrganizativo
            + " y numero de Robots = : "+ numRobots, "Sin fichero que defina el escenario no se puede iniciar la simulacion ");
            numeroIntentos ++;
        }
       if (ficheroSeleccionadoValido){
           notifEvts.sendInfoEscenarioSeleccionado(escenarioEdicionComp);
           escenarioValidoObtenido = true;
           peticionObtenerEscenarioValido = false;
       }
       else notifEvts.sendNotifAgteControlador("escenarioNoDefinidoTrasAgotarLosIntentos");
    }
  
 // caso 3 un escenario abierto y el suario pulsa la opcion simular.
 //     es necesario validarlo  y si es valido se envia una notificacion al agente con el computacional
//        si no es v�lido hay que pedir que se seleccione otro : noticar que el escenario tiene que tener el mismo
//        numero de robots que el definido en la configuracion y esperar que seleccione o cree uno nuevo
//        una vez conseguido el escenrio valido se cambian los identificadores de los robots para ajustarlos a la configuracion
    
   
    
    }  
    public File peticionSeleccionarEscenario(){
     // suponemos que hay escenarios creados , auque lo podemos verificar de nuevo   
         int numerointentos = 0;
         File ficheroSeleccionado = null;
       while ( numerointentos<maxIntentosPeticionSeleccionEscenario && ficheroSeleccionado==null ){
         ficheroSeleccionado=   visorControlSim.solicitarSeleccionFichero();
          if (ficheroSeleccionado==null)visorControlSim.visualizarConsejo(tituloAvisoEscenarioNoDefinido, mensajeEscenarioNoSeleccionado,recomendacionDefinirEscenario);
         numerointentos++;
        }
       return ficheroSeleccionado;
//       this.notifEvt.informaraOtroAgenteReactivo(new InfoContEvtMsgAgteReactivo("escenarioDefinidoPorUsuario", escenarioActual), identAgenteaReportar);
    }

//    void notificacionUsuario(String textoNoticiacion) {
//        visorControlSim.visualizarConsejo("Seleccion de Fichero ", "Seleccionar un fichero de modelo organizativo: "+modOrganizativo
//            + " y numero de Robots = : "+ numRobots, "Si no tiene ninguno de estas caracteristicas cree uno nuevo ");
//    }

    void notifRecomendacionUsuario(String titulo, String motivo, String recomendacion) {
       visorControlSim.visualizarConsejo(titulo, motivo,recomendacion);
    }

    void victimaSeleccionadaParaSimulacion(String identVictimaSeleccionada) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
     if (!avisoSeleccionVictima){
       this.visorControlSim.solicitarConfirmacion(" Se va a proceder a simular salvar a la victima : "+ identVictimaSeleccionada);
    }
        this.avisoSeleccionVictima=true;
    this.notifEvts.sendPeticionSimulacionVictimToRobotTeam(identVictimaSeleccionada);
    }

    void peticionCambiarPosicionRobot(String identRobot, Integer coordX, Integer coordY) {
       this.visorMovimientoEscen.cambiarPosicionRobot(identRobot, coordX, coordY);
    }

    void peticionMostrarVictimaRescatada(String identVictima) {
        this.visorMovimientoEscen.cambiarIconoEntidad(identVictima, rutaIconos+imageniconoMujerRescatada);
        
    }

    void abrirVisorConEscenarioComp(EscenarioSimulacionRobtsVictms escenarioSimulacion) {
       if(!visorControlSimuladorIniciado){
//        initModelosYvistas();
        // Se abre el visor sin 
            visorControlSim.setVisible(true);
        }
        if(escenarioSimulacion !=null){
           this.escenarioSimulComp = escenarioSimulacion;
            visorControlSim.setIdentEscenarioActual(escenarioSimulacion.getIdentEscenario());
            visorControlSim.visualizarIdentsEquipoRobot(escenarioSimulacion.getListIdentsRobots());
            visorControlSim.visualizarIdentsVictimas(escenarioSimulacion.getListIdentsVictims());
            escenarioValidoObtenido= true;
            robotsVisualizados= true;
            victimasVisualizadas = true;
            if(visorMovimientoEscen== null)
                try {
                    visorMovimientoEscen= new VisorMovimientoEscenario(escenarioSimulComp);
                    visorMovientoIniciado = true;
                }
                catch (Exception ex) {
                    Exceptions.printStackTrace(ex);
                }
            visorMovimientoEscen.visualizarEscenario(escenarioSimulComp);  
       }
         visorControlSimuladorIniciado=true;     
    }
    
//    private EscenarioSimulacionRobtsVictms obtenerEscenarioDesdePersistencia (String identFichero){
//       try {
//        if(itfPersistenciaSimul == null){
//           return persistenciaLocal.obtenerInfoEscenarioSimulacion(identFichero);
//        }else{
//           return itfPersistenciaSimul.obtenerInfoEscenarioSimulacion(identFichero);
//        }
//        } catch (Exception ex) {
//            Exceptions.printStackTrace(ex);
//        }
//       return null;
//    }
    
}
