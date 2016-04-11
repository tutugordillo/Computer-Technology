/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ControlCenterGui2.java
 *
 * Created on 29-dic-2011, 20:42:57
 */
package icaro.aplicaciones.recursos.recursoVisualizadorEntornosSimulacion.imp;

import icaro.aplicaciones.Rosace.informacion.VocabularioRosace;
import java.io.File;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author FGarijo
 */
public class VisorControlSimuladorRosace extends javax.swing.JFrame {

    /** Creates new form ControlCenterGui2 */
    private NotificadorInfoUsuarioSimulador notifEvts;
    private int intervaloSecuencia = 10000; // valor por defecto. Eso deberia ponerse en otro sitio
    private int numMensajesEnviar = 3;
    private boolean primeraVictima = true;
    private ArrayList identsRobotsEquipo ;
    private ArrayList identsVictimas ;
    private ControladorVisualizacionSimulRosace controladorEscSim;
    private String identEscenarioActual;
    private String identRobotSeleccionado;
    private String identVictimaSeleccionada;
     
    
    public VisorControlSimuladorRosace(ControladorVisualizacionSimulRosace controlador) {
        controladorEscSim= controlador;
        initComponents();
//         jButtonStart.setEnabled(false);
//         listaComponentes.setVisible(false);
       
    }
//    public VisorControlSimuladorRosace( ) {
////        this.notifEvts = notifEvt;
//        this.identsRobotsEquipo =identsRobotsEquipo;
// //       this.cgen = cgenRec;
// //       this.visorSc = visorScn;
//        initComponents();
//         jButtonStart.setEnabled(false);
//         listaComponentes.setVisible(false);
//    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenuAcionesRobots = new javax.swing.JPopupMenu();
        jMenuItemParar = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        jMenuItemVerEstado = new javax.swing.JMenuItem();
        jFileChooser1 = new javax.swing.JFileChooser();
        jButtonSendVictim = new javax.swing.JButton();
        jButtonSendVictimsSequence = new javax.swing.JButton();
        jtextTextFieldIntervaloEnvioMensajes = new javax.swing.JTextField();
        jButtonComenzar = new javax.swing.JButton();
        jPararEnvioMensajes = new javax.swing.JButton();
        jButtonMostrarEscenarioActual = new javax.swing.JButton();
        jScrollPanelistaIdentsVictimas = new javax.swing.JScrollPane();
        jListIdentsVictims = new javax.swing.JList();
        jScrollPaneIdentsRobots = new javax.swing.JScrollPane();
        jlistIdentsRobots = new javax.swing.JList();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTextFieldIdentEscenarioActual = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jMenuBarEditorEsc = new javax.swing.JMenuBar();
        jMenuEditarEscenario = new javax.swing.JMenu();
        jMenuItemAbrirEscenario = new javax.swing.JMenuItem();
        jSeparator5 = new javax.swing.JPopupMenu.Separator();
        jMenuItemCrearEscenario = new javax.swing.JMenuItem();
        jSeparator6 = new javax.swing.JPopupMenu.Separator();
        jMenuItemEliminarEscenario = new javax.swing.JMenuItem();
        jSeparator8 = new javax.swing.JPopupMenu.Separator();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jMenuItemSalir = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();

        jPopupMenuAcionesRobots.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
                jPopupMenuAcionesRobotsPopupMenuWillBecomeVisible(evt);
            }
        });

        jMenuItemParar.setText("jMenuItem3");
        jMenuItemParar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemPararActionPerformed(evt);
            }
        });
        jPopupMenuAcionesRobots.add(jMenuItemParar);
        jPopupMenuAcionesRobots.add(jSeparator2);

        jMenuItemVerEstado.setText("jMenuItem3");
        jPopupMenuAcionesRobots.add(jMenuItemVerEstado);

        jFileChooser1.setDialogTitle("Seleccion de escenario");
        jFileChooser1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFileChooser1ActionPerformed(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Centro de Control");

        jButtonSendVictim.setText("Salvar Victima");
        jButtonSendVictim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSendVictimActionPerformed(evt);
            }
        });

        jButtonSendVictimsSequence.setText("Salvar Victimas del escenario");
        jButtonSendVictimsSequence.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSendVictimsSequenceActionPerformed(evt);
            }
        });

        jButtonComenzar.setText("Comenzar");
        jButtonComenzar.setNextFocusableComponent(jPararEnvioMensajes);
        jButtonComenzar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonComenzarActionPerformed(evt);
            }
        });

        jPararEnvioMensajes.setText("Parar");
        jPararEnvioMensajes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPararEnvioMensajesActionPerformed(evt);
            }
        });

        jButtonMostrarEscenarioActual.setText("Mostrar Escenario actual");
        jButtonMostrarEscenarioActual.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonMostrarEscenarioActualActionPerformed(evt);
            }
        });

        jListIdentsVictims.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jListIdentsVictimsMouseClicked(evt);
            }
        });
        jScrollPanelistaIdentsVictimas.setViewportView(jListIdentsVictims);

        jlistIdentsRobots.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jlistIdentsRobotsMouseClicked(evt);
            }
        });
        jScrollPaneIdentsRobots.setViewportView(jlistIdentsRobots);
        jlistIdentsRobots.getAccessibleContext().setAccessibleDescription("");

        jLabel3.setText("Robots ");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel4.setText("Ident Escenario de Simulaci�n:");

        jTextFieldIdentEscenarioActual.setEditable(false);
        jTextFieldIdentEscenarioActual.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldIdentEscenarioActualActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel5.setText("Centro de Control");

        jLabel6.setText("<html><body>Intervalo de envio  (ms)  <br>de mensajes </body></html>");

        jLabel7.setText("Victimas");

        jMenuEditarEscenario.setText("Editor Escenarios");

        jMenuItemAbrirEscenario.setText("Abrir Escenario");
        jMenuItemAbrirEscenario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemAbrirEscenarioActionPerformed(evt);
            }
        });
        jMenuEditarEscenario.add(jMenuItemAbrirEscenario);
        jMenuEditarEscenario.add(jSeparator5);

        jMenuItemCrearEscenario.setText("Crear Escenario");
        jMenuItemCrearEscenario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemCrearEscenarioActionPerformed(evt);
            }
        });
        jMenuEditarEscenario.add(jMenuItemCrearEscenario);
        jMenuEditarEscenario.add(jSeparator6);

        jMenuItemEliminarEscenario.setText("Eliminar este Escenario");
        jMenuItemEliminarEscenario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemEliminarEscenarioActionPerformed(evt);
            }
        });
        jMenuEditarEscenario.add(jMenuItemEliminarEscenario);
        jMenuEditarEscenario.add(jSeparator8);
        jMenuEditarEscenario.add(jSeparator1);

        jMenuItemSalir.setText("Salir");
        jMenuEditarEscenario.add(jMenuItemSalir);

        jMenuBarEditorEsc.add(jMenuEditarEscenario);

        jMenu2.setText("Simulador");

        jMenuItem1.setText("Mostrar Resultados simulacion");
        jMenu2.add(jMenuItem1);

        jMenuItem2.setText("Guardar resultados simulacion");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem2);

        jMenuBarEditorEsc.add(jMenu2);

        jMenu3.setText("Trazas");
        jMenuBarEditorEsc.add(jMenu3);

        setJMenuBar(jMenuBarEditorEsc);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonComenzar))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPararEnvioMensajes)
                            .addComponent(jtextTextFieldIntervaloEnvioMensajes, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButtonMostrarEscenarioActual)
                        .addGap(45, 45, 45)
                        .addComponent(jLabel4))
                    .addComponent(jButtonSendVictim)
                    .addComponent(jButtonSendVictimsSequence))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 12, Short.MAX_VALUE)
                        .addComponent(jScrollPaneIdentsRobots, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jTextFieldIdentEscenarioActual))
                .addGap(58, 58, 58))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(274, 274, 274))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jScrollPanelistaIdentsVictimas, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 79, Short.MAX_VALUE))
                        .addGap(120, 120, 120)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(156, 156, 156))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTextFieldIdentEscenarioActual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonMostrarEscenarioActual))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(jScrollPaneIdentsRobots))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPanelistaIdentsVictimas)))
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 107, Short.MAX_VALUE)
                        .addComponent(jButtonSendVictim)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonSendVictimsSequence)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jtextTextFieldIntervaloEnvioMensajes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(106, 106, 106))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jButtonComenzar)
                                    .addComponent(jPararEnvioMensajes))
                                .addGap(55, 55, 55))))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonSendVictimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSendVictimActionPerformed
          
  //        notifEvts.sendInitialRobotCoordenates();
        // peticion salvar victim se delega en el agente controlador. Se podria implementar primero en local
        // El controlador local, mira si el usuario puede seleccionar una victima y si puede le envia la peticion
        // con la victima seleccionada
//        jListIdentsVictims.setListData(identsRobotsEquipo.toArray());
//            notifEvts.sendPeticionSimulacionVictimToRobotTeam();
        this.controladorEscSim.peticionSalvarVictima();
   //         primeraVictima = false;
  //          sendVictimButton.setEnabled(false); 
           
           
    }//GEN-LAST:event_jButtonSendVictimActionPerformed

    private void jButtonSendVictimsSequenceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSendVictimsSequenceActionPerformed
         // Se deja al visualizador que obtenga el intervalo en ms . Si no hay valor definido lo pide
        // por medio del Joptionpane. Le manda lo obtenido al controlador que debe verificarlo
        // Si no son validos los valore debe ordenar que salga el JoptionPane con  los valores que correspondan y luego enviarle los valores 
        // de vuelta al controlador . En definitiva hay que desdoblar esto
         System.out.println("Ha pulsado el boton send victims");
         this.solicitarDefinicionItervaloSecuencia();
         controladorEscSim.peticionComenzarSimulacion(identEscenarioActual, intervaloSecuencia);
//         jButtonStart.setEnabled(true);
         
//         String valorIntervaloDefinido=  jtextTextFieldIntervaloEnvioMensajes.getText();
//         if(valorIntervaloDefinido.length()==0)this.solicitarDefinicionItervaloSecuencia();
//         else intervaloSecuencia = Integer.parseInt(valorIntervaloDefinido);
//         setLocationRelativeTo(this);
//         String msgUsuarioIntervaloEnvioPetcionesNoNull= " El valor del intervalo en ms debe tener un valor positivo. Introduzca de nuevo el valor del intervalo";
//        while( intervaloSecuencia<=0){ 
//         valorIntervaloDefinido=JOptionPane.showInputDialog(rootPane, msgUsuarioIntervaloEnvioPetcionesNoNull);
//         if(valorIntervaloDefinido!=null)
//                    intervaloSecuencia = Integer.parseInt(valorIntervaloDefinido);
//                   
////        intervaloSecuencia = Integer.parseInt(showInputDialog(rootPane, msgUsuarioIntervaloEnvioPetcionesNoNull, intervaloSecuencia));
//        }
//          jtextTextFieldIntervaloEnvioMensajes.setText(""+intervaloSecuencia);
        // se obtiene el valor del campo de texto donde se define el intervalo 
//         intervaloSecuencia = Integer.parseInt(jtextTextFieldIntervaloEnvioMensajes.getText());

//         String smsg = "Este es el valor en milisegundos leido de la propiedad global \nintervaloMilisegundosEnvioMensajesDesdeCC establecido \n en la descripcion de la organizacion lanzada";         		 
//         valor = JOptionPane.showInputDialog(rootPane, smsg, intervaloSecuencia);


          //         String valor = JOptionPane.showInputDialog("Introduce el valor en milisegundos en que deben enviarse las coordenadas");
         //intervalTextField.setText(valor);
        // int v = 0;
//          if(valor!=null){
//                 try{
//                    intervaloSecuencia = Integer.parseInt(valor);
//                    jtextTextFieldIntervaloEnvioMensajes.setText(""+intervaloSecuencia);
//                 }catch(Exception e){
//                     System.out.println("El valor debe ser  un numero entero");
//                     intervaloSecuencia = 0;
//         while (intervaloSecuencia == 0 ){
//             valor = JOptionPane.showInputDialog(rootPane, "El valor debe ser  un numero entero. Introduzca de nuevo el valor del intervalo");
//             System.out.println("El valor ha sido : "+valor);
//             //intervaloSecuencia = Integer.getInteger(valor) ;
//             //if (intervaloSecuencia == null)valor = "";
//             if(valor!=null){
//                 try{
//                    intervaloSecuencia = Integer.parseInt(valor);
//                    jtextTextFieldIntervaloEnvioMensajes.setText(""+intervaloSecuencia);
//                 }catch(Exception e){
//                     System.out.println("El valor debe ser  un numero entero");
//                     intervaloSecuencia = 0;
//                 }
//             }else {
//                 intervaloSecuencia = 1;// si es null es que se ha cancelado y hay que salir
//             }
//         }
//         if(valor==null){System.out.println("Se ha cancelado");};
         
         //System.out.println("El valor ha sido : "+valor);
         
         
//            intervalTextField.setOpaque(true);
//            intervalTextField.setVisible(true);
//        }
//         intervalTextField.requestFocusInWindow();
//         notifEvts.sendInitialRobotCoordenates();
//         notifEvts.sendFirstSimulatedVictimToRobotTeam();
//         sendVictimsSequence.setEnabled(false); 
    }//GEN-LAST:event_jButtonSendVictimsSequenceActionPerformed

    private void jButtonComenzarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonComenzarActionPerformed
        // TODO add your handling code here:
        this.solicitarDefinicionItervaloSecuencia();
        controladorEscSim.peticionComenzarSimulacion(identEscenarioActual, intervaloSecuencia); 
//        notifEvts.sendPeticionSimulacionSecuenciaVictimasToRobotTeam(intervaloSecuencia);
    }//GEN-LAST:event_jButtonComenzarActionPerformed

    private void jPararEnvioMensajesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPararEnvioMensajesActionPerformed
        // TODO add your handling code here:
        
        controladorEscSim.peticionPararSimulacion();
    }//GEN-LAST:event_jPararEnvioMensajesActionPerformed

    private void jButtonMostrarEscenarioActualActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonMostrarEscenarioActualActionPerformed
        // TODO add your handling code here:
        controladorEscSim.peticionMostrarEscenarioActual();
    }//GEN-LAST:event_jButtonMostrarEscenarioActualActionPerformed

    private void jlistIdentsRobotsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlistIdentsRobotsMouseClicked
    if (evt.getClickCount() == 2) {
    	            int index = jlistIdentsRobots.locationToIndex(evt.getPoint());
//    	        notifEvts.sendPeticionPararAgente ((String)identsRobotsEquipo.get(index));
    	       //      clasificadorV.muestraVentanaEspecifica(listaComponentes.getSelectedValue().toString());
                    identRobotSeleccionado = (String)identsRobotsEquipo.get(index);
                    jPopupMenuAcionesRobots.setVisible(true);
    	          }
    }//GEN-LAST:event_jlistIdentsRobotsMouseClicked

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItemCrearEscenarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemCrearEscenarioActionPerformed
        // Usuario quiere crear un escenario
        // se abre una  ventana vacia , si tiene otra abierta se le deber�a avisar de que se guardar
        // lo que tiene
        controladorEscSim.peticionCrearEscenario();
//        if (escenarioActualComp.getNumRobots()> 0){
//            peticionGuardarEscenario();
//            // Se avisa de que el escenario actual se va a guardar antes de abrir el nuevo
//            //            escenarioActualComp.setIdentEscenario(jTextFieldIdentEquipo.getText());
//            //
//            //
//            //        //         String smsg = "Puede cambiar el valor en milisegundos en que deben enviarse las coordenadas";
//            //
//            //        String smsg = "Se va a guardar el escenario: " +jTextFieldIdentEquipo.getText() ;
//            //        JOptionPane.showConfirmDialog(rootPane, smsg,"Confirmar GuardarEscenario",JOptionPane.OK_CANCEL_OPTION );
//            //         persistencia.guardarInfoEscenarioSimulacion(directorioPersistencia, escenarioActualComp);
//        }
//        escenarioActualComp = gestionEscComp.crearEscenarioSimulaci�n();
//        //        jTextFieldIdentEquipo.setText()
//        eliminarEntidadesEscenario();
//        jTextFieldIdentEquipo.setText(escenarioActualComp.getIdentEscenario());
//        intervalNumRobots.setText(""+0);
//        intervalNumVictimas.setText(""+0);

    }//GEN-LAST:event_jMenuItemCrearEscenarioActionPerformed

    private void jMenuItemAbrirEscenarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemAbrirEscenarioActionPerformed
        // TODO add your handling code here:
        controladorEscSim.peticionAbrirEscenario();
//         FileNameExtensionFilter filter = new FileNameExtensionFilter("ficheros xml","xml","txt" );
//   
//      jFileChooser1.setFileFilter(filter);
//      jFileChooser1.setCurrentDirectory(new File(directorioPersistencia));
//      jFileChooser1.setFileSelectionMode(JFileChooser.FILES_ONLY);
//       int returnVal = jFileChooser1.showOpenDialog(this);
//    if (returnVal == JFileChooser.APPROVE_OPTION) {
//        File selectedFile = jFileChooser1.getSelectedFile();
//        escenarioActualComp = persistencia.obtenerInfoEscenarioSimulacion(selectedFile.getAbsolutePath());
//        escenarioActualComp.setGestorEscenarios(gestionEscComp);
//        visualizarEscenario(escenarioActualComp);
//        
////               fileName = selectedFile.getName();
//        // enviariamos el fichero a la persistencia para que nos diera el contenido
//        // se visualiza un escenario a partir de la informaci�n almacenada
//        System.out.println("Ejecuto  accion sobre el fichero "+selectedFile.getAbsolutePath());
//    } else {
//        System.out.println("File access cancelled by user.");
//    }
    }//GEN-LAST:event_jMenuItemAbrirEscenarioActionPerformed

    private void jMenuItemEliminarEscenarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemEliminarEscenarioActionPerformed
        // TODO add your handling code here:
        controladorEscSim.peticionEliminarEscenario();
    }//GEN-LAST:event_jMenuItemEliminarEscenarioActionPerformed

    private void jMenuItemPararActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemPararActionPerformed
        // TODO add your handling code here:
        controladorEscSim.peticionPararRobot(identRobotSeleccionado);
    }//GEN-LAST:event_jMenuItemPararActionPerformed

    private void jPopupMenuAcionesRobotsPopupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_jPopupMenuAcionesRobotsPopupMenuWillBecomeVisible
        // TODO add your handling code here:
    }//GEN-LAST:event_jPopupMenuAcionesRobotsPopupMenuWillBecomeVisible

    private void jTextFieldIdentEscenarioActualActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldIdentEscenarioActualActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldIdentEscenarioActualActionPerformed

    private void jFileChooser1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFileChooser1ActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_jFileChooser1ActionPerformed

    private void jListIdentsVictimsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jListIdentsVictimsMouseClicked
        // TODO add your handling code here:
        if (evt.getClickCount() == 2) {
    	            int index = jListIdentsVictims.locationToIndex(evt.getPoint());
//    	        notifEvts.sendPeticionPararAgente ((String)identsRobotsEquipo.get(index));
    	       //      clasificadorV.muestraVentanaEspecifica(listaComponentes.getSelectedValue().toString());
                    identVictimaSeleccionada = (String)identsVictimas.get(index);
                   controladorEscSim.victimaSeleccionadaParaSimulacion(identVictimaSeleccionada);
    	          }
        
    }//GEN-LAST:event_jListIdentsVictimsMouseClicked
    private void setIntervaloEnvioMensajesDesdeCC(int intervalo){
		intervaloSecuencia = intervalo ;
		int intervaloEnvioMensajesDesdeCC = 1000;
		String strintervaloEnvioMensajesDesdeCC = "";
    }
    public void setIdentEscenarioActual(String escenarioId){
        identEscenarioActual = escenarioId;
        jTextFieldIdentEscenarioActual.setText(escenarioId);
    }
    public void visualizarIdentsEquipoRobot ( ArrayList<String> equipoIds){
//        eqipoIds = eqipoIds.toArray();
        identsRobotsEquipo = equipoIds;
        this.jlistIdentsRobots.setListData(identsRobotsEquipo.toArray());
        jlistIdentsRobots.setVisible(true);
}
    public void visualizarIdentsVictimas ( ArrayList<String> victimasIds){
//        eqipoIds = eqipoIds.toArray();
        identsVictimas = victimasIds;
        this.jListIdentsVictims.setListData(victimasIds.toArray());
        jlistIdentsRobots.setVisible(true);
}
     public void visualizarIdEscenarioActual (String identEquipo){
//         this.identEquipoActual =identEquipo;
         jTextFieldIdentEscenarioActual.setText(identEquipo);
         
     }
     public boolean solicitarConfirmacion(String texto){
        return( JOptionPane.showConfirmDialog(rootPane, texto)==JOptionPane.OK_OPTION);  
     }
     
     public void visualizarConsejo (String titulo, String msgConsejo, String recomendacion){
         JOptionPane.showMessageDialog(rootPane,msgConsejo + "  "+ recomendacion, titulo,2);
     }
     
     public void solicitarDefinicionItervaloSecuencia(){
         String valorIntervaloDefinido=  jtextTextFieldIntervaloEnvioMensajes.getText();
         if(valorIntervaloDefinido.equals(""))intervaloSecuencia=0;
         else intervaloSecuencia = Integer.parseInt(valorIntervaloDefinido);
         setLocationRelativeTo(this);
         boolean ventanaAbierta=true;
         String msgUsuarioIntervaloEnvioPetcionesNoNull= " El valor del intervalo en ms debe tener un valor positivo. Introduzca de nuevo el valor del intervalo";
        while( intervaloSecuencia<=0&&ventanaAbierta){ 
         valorIntervaloDefinido=JOptionPane.showInputDialog(rootPane, msgUsuarioIntervaloEnvioPetcionesNoNull);
         if(valorIntervaloDefinido==null)ventanaAbierta=false;
         else if(valorIntervaloDefinido.equals(""))intervaloSecuencia=0;
                   else intervaloSecuencia = Integer.parseInt(valorIntervaloDefinido);
        }
        jtextTextFieldIntervaloEnvioMensajes.setText(valorIntervaloDefinido);
     }
     public boolean setDirectorioPersistencia(String dirPersistencia){
         FileNameExtensionFilter filter = new FileNameExtensionFilter("ficheros xml","xml","txt" );
          jFileChooser1.setFileFilter(filter);
        jFileChooser1.setFileSelectionMode(JFileChooser.FILES_ONLY);
        try {
       jFileChooser1.setCurrentDirectory(new File (dirPersistencia));
       return true;
        } catch (Exception ex) {
            return false;
        }
     }
//     public File solicitarSeleccionFichero(String directorio){
      public boolean hayFicherosCreados(){
          File dir = jFileChooser1.getCurrentDirectory();
      int numFiles = dir.list().length ;
      return(numFiles > 0);
      }
     
     public File solicitarSeleccionFichero(){
      FileNameExtensionFilter filter = new FileNameExtensionFilter("ficheros xml","xml","txt" );
      jFileChooser1.setFileFilter(filter);
      File dir = jFileChooser1.getCurrentDirectory();
     int returnVal = jFileChooser1.showOpenDialog(this);
      jFileChooser1.setFileSelectionMode(JFileChooser.FILES_ONLY);
       jFileChooser1.setCurrentDirectory(dir);
//       int returnVal = jFileChooser1.showOpenDialog(this);
    if (returnVal == JFileChooser.APPROVE_OPTION) {
        return jFileChooser1.getSelectedFile();
     }else return null; // no ha seleccionado nada
     }
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
            java.util.logging.Logger.getLogger(VisorControlSimuladorRosace.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VisorControlSimuladorRosace.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VisorControlSimuladorRosace.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VisorControlSimuladorRosace.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
//                new VisorControlSimuladorRosace(new ControladorVisualizacionSimulRosace()).setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonComenzar;
    private javax.swing.JButton jButtonMostrarEscenarioActual;
    private javax.swing.JButton jButtonSendVictim;
    private javax.swing.JButton jButtonSendVictimsSequence;
    private javax.swing.JFileChooser jFileChooser1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JList jListIdentsVictims;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBarEditorEsc;
    private javax.swing.JMenu jMenuEditarEscenario;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItemAbrirEscenario;
    private javax.swing.JMenuItem jMenuItemCrearEscenario;
    private javax.swing.JMenuItem jMenuItemEliminarEscenario;
    private javax.swing.JMenuItem jMenuItemParar;
    private javax.swing.JMenuItem jMenuItemSalir;
    private javax.swing.JMenuItem jMenuItemVerEstado;
    private javax.swing.JButton jPararEnvioMensajes;
    private javax.swing.JPopupMenu jPopupMenuAcionesRobots;
    private javax.swing.JScrollPane jScrollPaneIdentsRobots;
    private javax.swing.JScrollPane jScrollPanelistaIdentsVictimas;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator5;
    private javax.swing.JPopupMenu.Separator jSeparator6;
    private javax.swing.JPopupMenu.Separator jSeparator8;
    private javax.swing.JTextField jTextFieldIdentEscenarioActual;
    private javax.swing.JList jlistIdentsRobots;
    private javax.swing.JTextField jtextTextFieldIntervaloEnvioMensajes;
    // End of variables declaration//GEN-END:variables
}