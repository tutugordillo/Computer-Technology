/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ControlCenterGui2.java
 *
 * F Garijo
 */
package icaro.aplicaciones.recursos.recursoVisualizadorEntornosSimulacion.imp;

import icaro.aplicaciones.Rosace.informacion.RobotStatus1;
import icaro.aplicaciones.Rosace.informacion.Coordinate;
import icaro.aplicaciones.Rosace.informacion.Victim;
import icaro.aplicaciones.Rosace.informacion.VocabularioRosace;
import icaro.aplicaciones.recursos.recursoPersistenciaEntornosSimulacion.imp.ReadXMLTestRobots;
import icaro.aplicaciones.recursos.recursoPersistenciaEntornosSimulacion.imp.ReadXMLTestSequence;
import icaro.infraestructura.entidadesBasicas.NombresPredefinidos;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.Point;
import java.io.File;
import java.io.IOException;
import static java.lang.Math.abs;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.SwingConstants;
import javax.swing.event.PopupMenuEvent;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.netbeans.api.visual.action.ActionFactory;
import org.netbeans.api.visual.action.WidgetAction;
import org.netbeans.api.visual.animator.SceneAnimator;
import org.netbeans.api.visual.model.ObjectScene;
import org.netbeans.api.visual.widget.LayerWidget;
import org.netbeans.api.visual.widget.Widget;
import org.netbeans.api.visual.widget.general.IconNodeWidget;
import org.openide.util.Exceptions;
import org.openide.util.Utilities;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 *
 * @author FGarijo
 */
public class VisorMovimientoEscenario extends javax.swing.JFrame {

    /** Creates new form ControlCenterGui2 */
    private NotificadorInfoUsuarioSimulador notifEvts;
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
    private Map<String, JLabel> tablaEntidadesEnEscenario;
    private ArrayList <JLabel> listaEntidadesEnEscenario;
    private JPanel panelVisor;
    private String modeloOrganizativo;
    private String identEquipoActual;
    private  int mumeroVictimas=0;
    private  int numeroRobots=0;
    private  EscenarioSimulacionRobtsVictms escenarioActual;
    private boolean escenarioActualAbierto =false;

     public  VisorMovimientoEscenario(EscenarioSimulacionRobtsVictms infoEscenario) throws Exception {
 
        initComponents();
        initEscenario();
        escenarioActual = infoEscenario;
        directorioTrabajo = System.getProperty("user.dir");
        numeroRobots=0;  mumeroVictimas=0;
//        tablaEntidadesEnEscenario = new HashMap<String, JLabel>();
        listaEntidadesEnEscenario = new ArrayList < JLabel>();
    }
   
    private void initEscenario(){
        String rutaIconoRobot =   rutapaqueteConstructorEscenariosROSACE + imageniconoRobot;
        IMAGErobot = Utilities.loadImage (rutaIconoRobot);
        IMAGEmujerRes = Utilities.loadImage ( rutapaqueteConstructorEscenariosROSACE +imageniconoMujerRescatada); 
        IMAGEmujer = Utilities.loadImage ( rutapaqueteConstructorEscenariosROSACE +imageniconoMujer);
        tablaEntidadesEnEscenario= new HashMap<String, JLabel>();
         
//        JLabel label = new javax.swing.JLabel("RobotPrueba");
//            String rutaIconoRobot = directorioTrabajo + "/" + rutassrc + rutapaqueteConstructorEscenariosROSACE + imageniconoRobot;

//       label.setIcon(new javax.swing.ImageIcon(rutaIconoRobot));     //System.out.println("Ruta->" + rutaIconoRobot);
//       label.createImage(10,10);
//            this.getRootPane().add(label);
//            this.repaint();
    }

        

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDialogAvisoErrorDefNumEntidades = new javax.swing.JDialog();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jOptionPane1 = new javax.swing.JOptionPane();
        jTextFieldModeloOrganizacion = new javax.swing.JTextField();
        robotIcon = new javax.swing.JLabel();
        intervalNumRobots = new javax.swing.JTextField();
        victimaIcon1 = new javax.swing.JLabel();
        intervalNumVictimas = new javax.swing.JTextField();
        jTextFieldIdentEquipo = new javax.swing.JTextField();
        jSeparator7 = new javax.swing.JSeparator();
        jLabelOrganizacion = new javax.swing.JLabel();
        jLabelIdentEquipo = new javax.swing.JLabel();
        GestionEscenarios = new javax.swing.JMenuBar();

        jDialogAvisoErrorDefNumEntidades.setTitle("Error: Definici�n de entidades en el escenario");
        jDialogAvisoErrorDefNumEntidades.setBounds(new java.awt.Rectangle(20, 20, 335, 88));
        jDialogAvisoErrorDefNumEntidades.setType(java.awt.Window.Type.POPUP);

        jButton1.setText("Aceptar");

        jLabel1.setText("El numero de entidades no puede ser mayor que 20");

        javax.swing.GroupLayout jDialogAvisoErrorDefNumEntidadesLayout = new javax.swing.GroupLayout(jDialogAvisoErrorDefNumEntidades.getContentPane());
        jDialogAvisoErrorDefNumEntidades.getContentPane().setLayout(jDialogAvisoErrorDefNumEntidadesLayout);
        jDialogAvisoErrorDefNumEntidadesLayout.setHorizontalGroup(
            jDialogAvisoErrorDefNumEntidadesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialogAvisoErrorDefNumEntidadesLayout.createSequentialGroup()
                .addGap(122, 122, 122)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jDialogAvisoErrorDefNumEntidadesLayout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jDialogAvisoErrorDefNumEntidadesLayout.setVerticalGroup(
            jDialogAvisoErrorDefNumEntidadesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDialogAvisoErrorDefNumEntidadesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Escenario de simulaci�n");
        setMinimumSize(new java.awt.Dimension(30, 30));
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });

        jTextFieldModeloOrganizacion.setName("Modelo Organizacion"); // NOI18N

        robotIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/utilsDiseniaEscenariosRosace/Robot.png"))); // NOI18N
        robotIcon.setText("Robots");
        robotIcon.setIconTextGap(2);

        intervalNumRobots.setText("0");
        intervalNumRobots.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                intervalNumRobotsActionPerformed(evt);
            }
        });

        victimaIcon1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        victimaIcon1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/utilsDiseniaEscenariosRosace/Mujer.png"))); // NOI18N
        victimaIcon1.setText("Victimas");
        victimaIcon1.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        victimaIcon1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        victimaIcon1.setIconTextGap(2);

        intervalNumVictimas.setText("0");
        intervalNumVictimas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                intervalNumVictimasActionPerformed(evt);
            }
        });

        jTextFieldIdentEquipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldIdentEquipoActionPerformed(evt);
            }
        });

        jLabelOrganizacion.setText("Organizaci�n");

        jLabelIdentEquipo.setText("Escenario");
        setJMenuBar(GestionEscenarios);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabelIdentEquipo, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldIdentEquipo, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelOrganizacion, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldModeloOrganizacion, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(robotIcon)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(intervalNumRobots, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(victimaIcon1, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(intervalNumVictimas, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 475, Short.MAX_VALUE))
                    .addComponent(jSeparator7, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(intervalNumVictimas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(victimaIcon1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(intervalNumRobots, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(robotIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldModeloOrganizacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelOrganizacion)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFieldIdentEquipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelIdentEquipo))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(560, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        // TODO add your handling code here
//        if (evt.getButton()==3){
//            jPopupMenuAddEntidades.show(this,evt.getX(),evt.getY());
//            ultimoPuntoClic = new Point(evt.getX(),evt.getY());
//        }else {
//        String tipoEntidad="Robot";
//         if(intencionUsuarioCrearVictima)tipoEntidad="Victima";
//         Point puntoCursor = MouseInfo.getPointerInfo().getLocation();
//         this.crearIconoRobVict(tipoEntidad,puntoCursor.x,puntoCursor.y );
//        }
    }//GEN-LAST:event_formMouseClicked

    private void intervalNumRobotsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_intervalNumRobotsActionPerformed
        // TODO add your handling code here:
        int num1 = Integer.parseInt(intervalNumRobots.getText());
        System.out.println("Numero de robots");
                if (num1>20 ){
//                    JOptionPane.showInputDialog(rootPane,"El numero de robots tiene que ser menor que 20");
           JOptionPane.showMessageDialog(rootPane,"El numero de robots tiene que ser menor que 20","Error en Numero Entidades", JOptionPane.ERROR_MESSAGE);
               
                }else System.out.println("Definido el numero de robots : "+ num1);

    }//GEN-LAST:event_intervalNumRobotsActionPerformed

    private void intervalNumVictimasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_intervalNumVictimasActionPerformed
        // TODO add your handling code here:
        int num1 = Integer.parseInt(intervalNumVictimas.getText());
        System.out.println("Numero de victimas");
                if (num1>20 ){
                    JOptionPane.showMessageDialog(rootPane,"El numero de victimas tiene que ser menor que 20","Error en Numero Entidades", JOptionPane.ERROR_MESSAGE);
                    
                }else System.out.println("Definido el numero de victimas : "+ num1);
    }//GEN-LAST:event_intervalNumVictimasActionPerformed

    private void jTextFieldIdentEquipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldIdentEquipoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldIdentEquipoActionPerformed
    
     

    public void visualizarIdentsEquipoRobot ( ArrayList<String> equipoIds){
//        eqipoIds = eqipoIds.toArray();
        identsRobotsEquipo = equipoIds;
//        this.listaComponentes.setListData(identsRobotsEquipo.toArray());
//        listaComponentes.setVisible(true);
}
    
 public void addEntidadEnEscenario (String rutaIcono, String idEntidad, Point puntoLoc){
        
        if(!tablaEntidadesEnEscenario.containsKey(idEntidad)){
        JLabel label = new JLabel();
        label.setBounds(10, 10, 300, 300);
//        Icon icon  = new ImageIcon(rutaIcono);
//        JLabel label;
//        label = new JLabel(idEntidad,icon , SwingConstants.CENTER);
        label.setHorizontalTextPosition(SwingConstants.RIGHT);
        label.setText(idEntidad);
        this.add(label);
        label.setVisible(true);
        label.setIcon(new ImageIcon(rutaIcono));
        label.setLocation(puntoLoc);
        this.listaEntidadesEnEscenario.add(label);
        this.tablaEntidadesEnEscenario.put(idEntidad, label);
        System.out.println( "Se crea la entidad : "+label.getText()+ " Punto : =" + puntoLoc );
        }else System.out.println( "la  entidad : "+idEntidad+ " Ya existe No se incluye en el escenario" );
    }
    public void actualizarInfoEquipoEnEscenario (){
//        jTextFieldIdentEquipo= escenrioSimComp.get
        jTextFieldModeloOrganizacion.setText(""+escenarioActual.getmodeloOrganizativo());
        jTextFieldIdentEquipo.setText(""+escenarioActual.getIdentEscenario());
        intervalNumRobots.setText(""+escenarioActual.getNumRobots());
        intervalNumVictimas.setText(""+escenarioActual.getNumVictimas());
//        intervalNumVictimas.setVisible(true);
    }
//    private void setEscenarioActualComp(EscenarioSimulacionRobtsVictms escenActualComp){
//        escenarioActual = escenActualComp;
//    }
//     private void setPersistencia(PersistenciaVisualizadorEscenarios persistEscenario) {
////        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    persistencia = persistEscenario;
//     }
//     public void visualizarEscenario(EscenarioSimulacionRobtsVictms infoEscenario ){
    public void visualizarEscenario( ){
        if (!escenarioActualAbierto){
         int numRobots = escenarioActual.getNumRobots();
         int numVictims = escenarioActual.getNumVictimas();
         jTextFieldIdentEquipo.setText(""+escenarioActual.getIdentEscenario());
         jTextFieldModeloOrganizacion.setText(""+escenarioActual.getmodeloOrganizativo());
         intervalNumRobots.setText(""+numRobots);
         intervalNumVictimas.setText(""+numVictims);
         
         String rutaImagen;
         Set entidades;
         Iterator entries;
         if (numRobots>0) {
         rutaImagen=directorioTrabajo+rutaIconos+imageniconoRobot;
          entidades = escenarioActual.getRobots();
//          entidades.remove("robotInit");
          entries = entidades.iterator();
          Entry thisEntry;
          RobotStatus1 robotInfo;
        while (entries.hasNext()) {
             thisEntry = (Entry) entries.next();
             robotInfo = (RobotStatus1)thisEntry.getValue();
//            addEntidadEnEscenario(rutaImagen,(String)thisEntry.getKey(),(Point)thisEntry.getValue());
            addEntidadEnEscenario(rutaImagen,(String)thisEntry.getKey(),robotInfo.getLocPoint());
//         intervalNumRobots.setText(""+infoEscenario.getNumRobots());
//         intervalNumVictimas.setText(""+escenrioSimComp.getNumVictimas());
        }
         }
         if (numVictims>0) {
            rutaImagen=directorioTrabajo+rutaIconos+imageniconoMujer;
            entidades = escenarioActual.getSetVictims();
//            entidades.remove("victimInit");
            entries = entidades.iterator();
            Entry thisEntry ;
            Victim victimInfo ;
            while (entries.hasNext()) {
                 thisEntry = (Entry) entries.next();
                 victimInfo = (Victim)thisEntry.getValue();
                addEntidadEnEscenario(rutaImagen,(String)thisEntry.getKey(),victimInfo.getLocPoint());
//         intervalNumRobots.setText(""+infoEscenario.getNumRobots());
//         intervalNumVictimas.setText(""+escenrioSimComp.getNumVictimas());
            }
         }
         this.setLocation(100,100);
         this.setVisible(true);
         escenarioActualAbierto = true;
        }
     }
//     private void peticionGuardarEscenario (){
//         setLocationRelativeTo(this);
////        escenarioActualComp.setIdentEscenario(jTextFieldIdentEquipo.getText());
//        escenarioActualComp.setIdentificadorNormalizado();
//      
//        jTextFieldIdentEquipo.setText(escenarioActualComp.getIdentEscenario());
//        String smsg = "Se va a guardar el escenario: " + escenarioActualComp.getIdentEscenario();
//       int respuesta= JOptionPane.showConfirmDialog(rootPane, smsg,"Confirmar GuardarEscenario",JOptionPane.OK_CANCEL_OPTION );
//        //         jOptionPaneAvisoError.setToolTipText(smsg);
//       if (respuesta==JOptionPane.OK_OPTION){
//           gestionEscComp.addEscenario(escenarioActualComp);
//             persistencia.guardarInfoEscenarioSimulacion(directorioPersistencia, escenarioActualComp);
//       }
//     }
//     public int confirmarPeticionGuardarEscenario (String msgConfirmacion){
//         escenarioActualComp.setIdentificadorNormalizado();
//         jTextFieldIdentEquipo.setText(escenarioActualComp.getIdentEscenario());
//        String smsg = msgConfirmacion + jTextFieldIdentEquipo.getText();
////       int respuesta= JOptionPane.showConfirmDialog(rootPane, smsg,"Confirmar GuardarEscenario",JOptionPane.OK_CANCEL_OPTION );
//        //         jOptionPaneAvisoError.setToolTipText(smsg);
//       return JOptionPane.showConfirmDialog(rootPane, smsg,"Confirmar GuardarEscenario",JOptionPane.OK_CANCEL_OPTION );
////       if (respuesta==JOptionPane.OK_OPTION){
////           gestionEscComp.addEscenario(escenarioActualComp);
////             persistencia.guardarInfoEscenarioSimulacion(directorioPersistencia, escenarioActualComp);
////       }
//     }
  public synchronized void cambiarPosicionRobot(String idRobot, int nueva_coordx, int nueva_coordy) {

//        String numeroRobot = getNumeroRobot(idRobot);
//       System.out.println("Peticiones de cambio de posicion  Robot : "+ idRobot+ " CoordX: " +nueva_coordx + " CoordY: " +nueva_coordy );
        //JLabel jlabelRobot = new JLabel();
        if (tablaEntidadesEnEscenario!=null){
        JLabel jlabelRobot = tablaEntidadesEnEscenario.get(idRobot);

        if (jlabelRobot != null) {
//            JOptionPane.showMessageDialog(panelVisor, "Se llama idRobot:"+valor_idRobot+" X:"+nueva_coordx+ "Y:"+nueva_coordy);
//            jlabelRobot.setBounds(jlabelRobot.getX()+10, jlabelRobot.getY()+10, jlabelRobot.getWidth(), jlabelRobot.getHeight());
//            jlabelRobot.setBounds(nueva_coordx, nueva_coordy, jlabelRobot.getWidth(), jlabelRobot.getHeight());
            jlabelRobot.setLocation(nueva_coordx, nueva_coordy);
//            this.notifyAll();

            //Eliminar de la visualizacion
//            jlabelRobot.setVisible(false);
//            panelVisor.remove(jlabelRobot);
            //Eliminar de la variable mapa que almacena identificadores y posiciones de los robots
//            robotslabel.remove(numeroRobot);

            //Crear una nueva label en la nueva posicion
            //crear el label y posicionarlo en el JPanel
//            JLabel label = new JLabel("");
//            String rutaIconoRobot = directorioTrabajo + "/" + rutassrc + rutapaqueteConstructorEscenariosROSACE + imageniconoRobot;
//
//            label.setIcon(new javax.swing.ImageIcon(rutaIconoRobot));
//
//            label.setText(numeroRobot);
//            label.setEnabled(true);
//            label.setVisible(true);
//
//            Dimension size = label.getPreferredSize();
//            label.setBounds(nueva_coordx, nueva_coordy, size.width, size.height);
//
//            panelVisor.add(label);
//
//            robotslabel.put(numeroRobot, label);   //Lo anoto en el Map: la clave es el numero del robot y contenido es el label creado

//            System.out.println("NUEVA Localizacion del robot " + label.getText() + "-> (" + label.getLocation().x + "," + label.getLocation().y + ")");
        } else 
            System.out.println("jlabel nulo");
        }else {
             System.out.println("Tabla de robots y victimas null ");
                }


//        System.out.println("Localizacion del robot " + jlabelRobot.getText() + "-> " + jlabelRobot.getBounds());
//        System.out.println("Localizacion del robot " + jlabelRobot.getText() + "-> " + jlabelRobot.getLocationOnScreen());
    }

    public void cambiarIconoEntidad(String idEntidad, String rutaImagen) {
// en vez de pasar la ruta habria que pasar el identificador de la imagen
//        String numeroVictima = getNumeroVictima(valor_idVictima);

//        int numeroIdVictima = Integer.parseInt(numeroVictima);

//        JLabel jlabelEntidad = new JLabel();

       JLabel jlabelEntidad = tablaEntidadesEnEscenario.get(idEntidad);

        if (idEntidad != null) {
//            jlabelEntidad.setEnabled(false);
            ImageIcon icono = new ImageIcon (IMAGEmujerRes);
//            new ImageIcon (rutaImagen)
//            Icon icon = jlabelEntidad.getIcon();
//            jlabelEntidad.setDisabledIcon(icon);
            jlabelEntidad.setIcon(icono);
            
            
//            jlabelEntidad.setVisible(true);
            //String rutaAbsolutaIconoVictima = jlabelVictima.getIcon().toString();			
            //System.out.println("victima " + numeroVictima + "  , " + jlabelVictima.getIcon().toString());
            
//            if (numeroIdVictima % 2 == 0) {
//                jlabelVictima.setIcon(new javax.swing.ImageIcon(directorioTrabajo + "/" + rutassrc + rutapaqueteConstructorEscenariosROSACE + "HombreRescatado.png"));
//                //System.out.println("Es un hombre");
//            } else {
//                jlabelVictima.setIcon(new javax.swing.ImageIcon(directorioTrabajo + "/" + rutassrc + rutapaqueteConstructorEscenariosROSACE + "MujerRescatada.png"));
//                //System.out.println("Es una mujer");
//            }

        } else {
            System.out.println("jlabelEntidad nulo");
        }
    }
  
    
//     private void eliminarEntidadesEscenario(){
////         Set entidades;
////         Iterator entries;
////         if (infoEscenario.getNumRobots()>0) {
////         rutaImagen=directorioTrabajo+rutaIconos+imageniconoRobot;
////          entidades = infoEscenario.getRobots();
////          entries = entidades.iterator();
////            while (entries.hasNext()) {
////                Entry thisEntry = (Entry) entries.next();
////                Point punto =(Point)thisEntry.getValue();
////            addEntidadEnEscenario(rutaImagen,(String)thisEntry.getKey(),(Point)thisEntry.getValue());
////             ((JLabel) this.findComponentAt((Point)thisEntry.getValue())).setVisible(false);
//         JLabel labelActual;             
//         for( Component comp : this.getContentPane().getComponents() ){
//                 if (comp instanceof JLabel){
//                      labelActual = (JLabel)comp;
//                     if (!labelActual.equals(jLabelIdentEquipo)&&!labelActual.equals(jLabelOrganizacion)
//                             &&!labelActual.equals(robotIcon)&&!labelActual.equals(victimaIcon1)){                    
//                         comp.setVisible(false);
//                         remove(comp);
//                 System.out.println( "Se borra la entidad : "+ " Coordenadas :  =" + comp.getLocation() );         
//                     }
//                };
//         }     
//         intervalNumRobots.setText(""+infoEscenario.getNumRobots());
//         intervalNumVictimas.setText(""+escenrioSimComp.getNumVictimas());
//            }
//        }
//     }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VisorMovimientoEscenario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VisorMovimientoEscenario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VisorMovimientoEscenario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VisorMovimientoEscenario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
            String  directorioPersistencia = VocabularioRosace.NombreDirectorioPersistenciaEscenarios+File.separator;
            VisorMovimientoEscenario visor;
//            PersistenciaVisualizadorEscenarios persistencia= new PersistenciaVisualizadorEscenarios();
//            GestionEscenariosSimulacion gestionEscComp= new GestionEscenariosSimulacion();
//            gestionEscComp.setIdentsEscenariosSimulacion(persistencia.obtenerIdentsEscenarioSimulacion(directorioPersistencia));
//                try {
//                    gestionEscComp = new GestionEscenariosSimulacion();
//                    gestionEscComp.setIdentsEscenariosSimulacion(persistencia.obtenerIdentsEscenarioSimulacion(directorioPersistencia));
////        escenarioActualComp = gestionEscComp.crearEscenarioSimulaci�n();
////                    visor = new VisorCreacionEscenarios1(new ControladorVisualizacionSimulRosace(notifEvts));
////             
//////                    persistencia= new PersistenciaVisualizadorEscenarios();
////                    visor.setPersistencia(persistencia);
////                    visor.setGestorEscenarionComp(gestionEscComp);
////                    visor.setEscenarioActualComp(gestionEscComp.crearEscenarioSimulaci�n());
////                    visor.actualizarInfoEquipoEnEscenario();
////                    visor.setVisible(true);
//                } catch (Exception ex) {
//                    Exceptions.printStackTrace(ex);
//                }
                
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuBar GestionEscenarios;
    private javax.swing.JTextField intervalNumRobots;
    private javax.swing.JTextField intervalNumVictimas;
    private javax.swing.JButton jButton1;
    private javax.swing.JDialog jDialogAvisoErrorDefNumEntidades;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabelIdentEquipo;
    private javax.swing.JLabel jLabelOrganizacion;
    private javax.swing.JOptionPane jOptionPane1;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JTextField jTextFieldIdentEquipo;
    private javax.swing.JTextField jTextFieldModeloOrganizacion;
    private javax.swing.JLabel robotIcon;
    private javax.swing.JLabel victimaIcon1;
    // End of variables declaration//GEN-END:variables

//    private void setGestorEscenarionComp(GestionEscenariosSimulacion gestEscComp) {
////        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    this.gestionEscComp=gestEscComp;
//    }

public void visualizarConsejo (String titulo, String msgConsejo, String recomendacion){
         JOptionPane.showMessageDialog(rootPane,msgConsejo + "  "+ recomendacion, titulo,2);
     }


    void visualizarEscenario(EscenarioSimulacionRobtsVictms escenarioComp) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        escenarioActual = escenarioComp;
//        directorioTrabajo = System.getProperty("user.dir");
        numeroRobots=0;  mumeroVictimas=0;
        escenarioActualAbierto = false;
        this.visualizarEscenario();
//        tablaEntidadesEnEscenario = new HashMap<String, JLabel>();
//        listaEntidadesEnEscenario = new ArrayList < JLabel>();
        
    
    }


}
