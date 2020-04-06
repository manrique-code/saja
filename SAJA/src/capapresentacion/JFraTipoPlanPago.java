/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capapresentacion;

import capadatos.CDTipoPlanPago;
import capalogica.CLTipoPlanPago;
import com.placeholder.PlaceHolder;
import java.awt.Color;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Manrique
 */
public class JFraTipoPlanPago extends javax.swing.JFrame {

    /**
     * Creates new form JFraTipoPlanPago
     */
    public JFraTipoPlanPago() throws SQLException{
        initComponents();
        this.setLocationRelativeTo(null);
        this.jTxtNombreTipoPlanPago.requestFocus();
        llenarTabla();
        habilitarBotones(true, false, false, true);
        siguienteId();
        PlaceHolder ph = new PlaceHolder(this.jTfBuscar, 
                                        new Color(153,153,153),
                                        Color.BLACK,
                                        "Buscar por nombre o identificador",
                                        false,
                                        "HelveticaNowDisplay Regular",
                                        16);
    }
    
    private boolean estadEditando = false;
    
    public void vaciarTabla(){
        DefaultTableModel dtm = (DefaultTableModel) this.jTblTipoPlanPago.getModel();
        
        while(dtm.getRowCount() > 0){
            dtm.removeRow(0);
        } 
    }
    
    public void llenarTabla() throws SQLException{
        vaciarTabla(); 
        
        CDTipoPlanPago cdtpp = new CDTipoPlanPago();
        
        List<CLTipoPlanPago> miLista = cdtpp.mostrarTodoTipoPlanPago();
        DefaultTableModel dtm = (DefaultTableModel) this.jTblTipoPlanPago.getModel();
        
        for(CLTipoPlanPago cltpp: miLista){
            Object[] fila = new Object[2];
            fila[0] = cltpp.getIdTipoPlanPago();
            fila[1] = cltpp.getNombreTipoPlanPago();
            dtm.addRow(fila);  
        }    
    }
    
    public void llenarTablaPorID(String nombreTPP) throws SQLException{
        vaciarTabla(); 
        
        CDTipoPlanPago cdtpp = new CDTipoPlanPago();
        
        List<CLTipoPlanPago> miLista = cdtpp.mostrarTipoPlanPagoPorID(nombreTPP);
        DefaultTableModel dtm = (DefaultTableModel) this.jTblTipoPlanPago.getModel();
        
        for(CLTipoPlanPago cltpp: miLista){
            Object[] fila = new Object[2];
            fila[0] = cltpp.getIdTipoPlanPago();
            fila[1] = cltpp.getNombreTipoPlanPago();
            dtm.addRow(fila);  
        } 
    }
    
    public void habilitarBotones(boolean guardar, boolean editar, boolean eliminar, boolean buscar){
        this.jBtnGuardar.setEnabled(guardar);
        this.jBtnGuardar.setVisible(guardar);
        
        this.jBtnEditar.setEnabled(editar);
        this.jBtnEditar.setVisible(editar);
        
        this.jBtnEliminar.setEnabled(eliminar);
        
        this.jBtnBuscar.setEnabled(buscar);
    }
    
    public void siguienteId() throws SQLException{
        CDTipoPlanPago cdtpp = new CDTipoPlanPago();
        CLTipoPlanPago cltpp = new CLTipoPlanPago();
        
        cltpp.setIdTipoPlanPago(cdtpp.aiTipoPlanPago());
        
        this.jTxtIdTipoPlanPago.setText(String.valueOf(cltpp.getIdTipoPlanPago()));
    }
    
    public void filaSeleccionada(){
        if(this.jTblTipoPlanPago.getSelectedRow() != -1){
            this.jTxtIdTipoPlanPago.setText(String.valueOf(this.jTblTipoPlanPago.getValueAt(this.jTblTipoPlanPago.getSelectedRow(), 0)));
            this.jTxtNombreTipoPlanPago.setText(String.valueOf(this.jTblTipoPlanPago.getValueAt(this.jTblTipoPlanPago.getSelectedRow(), 1)));
            //this.jTxtNombreTipoPlanPago.setBorder(BorderFactory.createLineBorder(Color.blue, 1));
            //this.jTblTipoPlanPago.setEnabled(false);
        }
    }
    
    public void insertarTipoPlanPago(){
        try{
            CDTipoPlanPago cdtpp = new CDTipoPlanPago();
            CLTipoPlanPago cltpp = new CLTipoPlanPago();
            
            cltpp.setNombreTipoPlanPago(this.jTxtNombreTipoPlanPago.getText().trim());
            cdtpp.insertarTipoPlanPago(cltpp);
            
            JOptionPane.showMessageDialog(null,
                                          String.format("Se guardó: %s, exitosamente", this.jTxtNombreTipoPlanPago.getText()),
                                          "SAJA",
                                          JOptionPane.INFORMATION_MESSAGE);
            this.jTxtNombreTipoPlanPago.setText("");
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error al guardar" + e);
        }
    }
    
    public void actualizarTipoPlanPago(){
        try{
            
            CDTipoPlanPago cdtpp = new CDTipoPlanPago();
            CLTipoPlanPago cltpp = new CLTipoPlanPago();
            
            cltpp.setNombreTipoPlanPago(this.jTxtNombreTipoPlanPago.getText().trim());
            cltpp.setIdTipoPlanPago(Integer.parseInt(this.jTxtIdTipoPlanPago.getText().trim()));
            cdtpp.actualizarTipoPlanPago(cltpp);
            
            JOptionPane.showMessageDialog(null,
                                          String.format("Se guardó: %s, exitosamente", this.jTxtNombreTipoPlanPago.getText()),
                                          "SAJA",
                                          JOptionPane.INFORMATION_MESSAGE);
            this.jTxtNombreTipoPlanPago.setText("");
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error al guardar" + e);
        }
    }
    
    public void eliminarTipoPlanPago(){
        try{
            int result = JOptionPane.showConfirmDialog(null,
                                                      String.format("¿Desea cancelar la edición: \"%s\"?", this.jTxtNombreTipoPlanPago.getText()), 
                                                       "SAJA",
                                                       JOptionPane.YES_NO_OPTION);
            if(result == JOptionPane.YES_OPTION){
                CDTipoPlanPago cdtpp = new CDTipoPlanPago();
                CLTipoPlanPago cltpp = new CLTipoPlanPago();

                cltpp.setIdTipoPlanPago(Integer.parseInt(this.jTxtIdTipoPlanPago.getText().trim()));
                cdtpp.eliminarTipoPlanPago(cltpp);

                JOptionPane.showMessageDialog(null,
                                              String.format("Se guardó: %s, exitosamente", this.jTxtNombreTipoPlanPago.getText()),
                                              "SAJA",
                                              JOptionPane.INFORMATION_MESSAGE);
                this.jTxtNombreTipoPlanPago.setText("");
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error al guardar" + e);
        }
    }
    

    public void limpiarFormulario() throws SQLException{
        this.siguienteId();
        this.jTxtNombreTipoPlanPago.setText("");
        this.habilitarBotones(true, false, false, true);
        this.jTxtNombreTipoPlanPago.requestFocus();
        this.jTblTipoPlanPago.clearSelection();
                    
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jRadioButton1 = new javax.swing.JRadioButton();
        jPnlCancelar = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLblTitulo = new javax.swing.JLabel();
        jLblTitulo1 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jTxtNombreTipoPlanPago = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jBtnGuardar = new javax.swing.JButton();
        jBtnEditar = new javax.swing.JButton();
        jBtnEliminar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTblTipoPlanPago = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jTfBuscar = new javax.swing.JTextField();
        jBtnBuscar = new javax.swing.JLabel();
        jTxtIdTipoPlanPago = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();

        jRadioButton1.setText("jRadioButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPnlCancelar.setBackground(new java.awt.Color(255, 255, 255));
        jPnlCancelar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPnlCancelarMouseClicked(evt);
            }
        });
        jPnlCancelar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(9, 132, 227));

        jLblTitulo.setFont(new java.awt.Font("HelveticaNowDisplay ExtraBold", 0, 30)); // NOI18N
        jLblTitulo.setForeground(new java.awt.Color(255, 255, 255));
        jLblTitulo.setText("Tipo de planes de pago");

        jLblTitulo1.setFont(new java.awt.Font("HelveticaNowDisplay ExtraBold", 0, 25)); // NOI18N
        jLblTitulo1.setForeground(new java.awt.Color(255, 255, 255));
        jLblTitulo1.setText("Menú");
        jLblTitulo1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addComponent(jLblTitulo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 333, Short.MAX_VALUE)
                .addComponent(jLblTitulo1)
                .addGap(32, 32, 32))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLblTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
                    .addComponent(jLblTitulo1)))
        );

        jPnlCancelar.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 820, 60));

        jLabel1.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Nombre");
        jPnlCancelar.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 260, 280, 30));

        jTxtNombreTipoPlanPago.setBackground(new java.awt.Color(255, 255, 255));
        jTxtNombreTipoPlanPago.setFont(new java.awt.Font("HelveticaNowDisplay Regular", 0, 18)); // NOI18N
        jTxtNombreTipoPlanPago.setForeground(new java.awt.Color(0, 0, 0));
        jTxtNombreTipoPlanPago.setToolTipText("Ingrese un tipo de plan de pago");
        jTxtNombreTipoPlanPago.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTxtNombreTipoPlanPago.setSelectionColor(new java.awt.Color(0, 153, 153));
        jPnlCancelar.add(jTxtNombreTipoPlanPago, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 290, 302, 40));

        jLabel5.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(153, 153, 153));
        jLabel5.setText("eliminarlo.");
        jPnlCancelar.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 150, 280, 20));

        jBtnGuardar.setBackground(new java.awt.Color(9, 132, 227));
        jBtnGuardar.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 0, 16)); // NOI18N
        jBtnGuardar.setForeground(new java.awt.Color(255, 255, 255));
        jBtnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/guardar-white24.png"))); // NOI18N
        jBtnGuardar.setText(" GUARDAR");
        jBtnGuardar.setToolTipText("Guardar la información.");
        jBtnGuardar.setBorder(null);
        jBtnGuardar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jBtnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnGuardarActionPerformed(evt);
            }
        });
        jPnlCancelar.add(jBtnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 390, 140, 50));

        jBtnEditar.setBackground(new java.awt.Color(9, 132, 227));
        jBtnEditar.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 0, 16)); // NOI18N
        jBtnEditar.setForeground(new java.awt.Color(255, 255, 255));
        jBtnEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/editar-blanco32.png"))); // NOI18N
        jBtnEditar.setText("EDITAR");
        jBtnEditar.setBorder(null);
        jBtnEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jBtnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnEditarActionPerformed(evt);
            }
        });
        jPnlCancelar.add(jBtnEditar, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 390, 140, 50));

        jBtnEliminar.setBackground(new java.awt.Color(9, 132, 227));
        jBtnEliminar.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 0, 16)); // NOI18N
        jBtnEliminar.setForeground(new java.awt.Color(255, 255, 255));
        jBtnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/eliminar-blanco24.png"))); // NOI18N
        jBtnEliminar.setText("ELIMINAR");
        jBtnEliminar.setBorder(null);
        jBtnEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jBtnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnEliminarActionPerformed(evt);
            }
        });
        jPnlCancelar.add(jBtnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 390, 140, 50));

        jTblTipoPlanPago.setFont(new java.awt.Font("HelveticaNowDisplay Light", 0, 15)); // NOI18N
        jTblTipoPlanPago.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "#", "Nombre"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTblTipoPlanPago.setRowHeight(30);
        jTblTipoPlanPago.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTblTipoPlanPagoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTblTipoPlanPago);

        jPnlCancelar.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 170, 350, 270));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 360, Short.MAX_VALUE)
        );

        jPnlCancelar.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 80, 1, 360));

        jLabel6.setBackground(new java.awt.Color(102, 102, 102));
        jLabel6.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 1, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Cód. Tipo Pago");
        jPnlCancelar.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 150, 280, 30));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTfBuscar.setBackground(new java.awt.Color(255, 255, 255));
        jTfBuscar.setFont(new java.awt.Font("HelveticaNowDisplay Regular", 0, 18)); // NOI18N
        jTfBuscar.setForeground(new java.awt.Color(0, 0, 0));
        jTfBuscar.setBorder(null);
        jTfBuscar.setSelectionColor(new java.awt.Color(0, 153, 153));
        jTfBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTfBuscarKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTfBuscarKeyTyped(evt);
            }
        });

        jBtnBuscar.setBackground(new java.awt.Color(9, 132, 227));
        jBtnBuscar.setForeground(new java.awt.Color(9, 132, 227));
        jBtnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/search-black24.png"))); // NOI18N
        jBtnBuscar.setToolTipText("Click para buscar");
        jBtnBuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTfBuscar, javax.swing.GroupLayout.DEFAULT_SIZE, 288, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jBtnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTfBuscar)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addComponent(jBtnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPnlCancelar.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 80, 350, 40));

        jTxtIdTipoPlanPago.setEditable(false);
        jTxtIdTipoPlanPago.setBackground(new java.awt.Color(255, 255, 255));
        jTxtIdTipoPlanPago.setFont(new java.awt.Font("HelveticaNowDisplay Regular", 0, 18)); // NOI18N
        jTxtIdTipoPlanPago.setForeground(new java.awt.Color(0, 0, 0));
        jTxtIdTipoPlanPago.setText(" 1");
        jTxtIdTipoPlanPago.setToolTipText("");
        jTxtIdTipoPlanPago.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTxtIdTipoPlanPago.setSelectionColor(new java.awt.Color(0, 153, 153));
        jPnlCancelar.add(jTxtIdTipoPlanPago, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 180, 302, 40));

        jLabel8.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(153, 153, 153));
        jLabel8.setText("Seleccione un elemento de la tabla para editarlo o");
        jPnlCancelar.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 130, -1, 20));

        jLabel9.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(153, 153, 153));
        jLabel9.setText("Ingrese aquí los tipos de planes de pago que");
        jPnlCancelar.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 80, -1, 20));

        jLabel7.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(153, 153, 153));
        jLabel7.setText("desea ofrecer a los abonados.");
        jPnlCancelar.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 100, 280, 20));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPnlCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPnlCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTblTipoPlanPagoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTblTipoPlanPagoMouseClicked
        // TODO add your handling code here:
        filaSeleccionada();
        habilitarBotones(false, true, true, true);
        estadEditando = true;
    }//GEN-LAST:event_jTblTipoPlanPagoMouseClicked

    private void jBtnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnGuardarActionPerformed
        // TODO add your handling code here:
        
        try {
            
            if(!this.jTxtNombreTipoPlanPago.getText().trim().isEmpty()){
                insertarTipoPlanPago();
                siguienteId();
                llenarTabla();
                habilitarBotones(true, false, false, true);
            } else {
                
                JOptionPane.showMessageDialog(null,
                                              "Por favor, ingrese un nombre.", 
                                              "SAJA",
                                               JOptionPane.INFORMATION_MESSAGE);
                this.jTxtNombreTipoPlanPago.requestFocus();             
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(JFraTipoPlanPago.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }//GEN-LAST:event_jBtnGuardarActionPerformed

    private void jPnlCancelarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPnlCancelarMouseClicked
        // TODO add your handling code here:
        if(estadEditando){
            int result = JOptionPane.showConfirmDialog(null,
                                                       "¿Desea cancelar la edición?", 
                                                       "SAJA",
                                                       JOptionPane.YES_NO_OPTION);
            
            if(result == JOptionPane.YES_OPTION) {
                try {
                    limpiarFormulario();
                    estadEditando = false;
                } catch (SQLException ex) {
                    Logger.getLogger(JFraTipoPlanPago.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        }else{
            return;
        }
    }//GEN-LAST:event_jPnlCancelarMouseClicked

    private void jBtnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnEditarActionPerformed
        // TODO add your handling code here:
        try {
            
            if(!this.jTxtNombreTipoPlanPago.getText().trim().isEmpty()){
                actualizarTipoPlanPago();
                siguienteId();
                llenarTabla();
                habilitarBotones(true, false, false, true);
            } else {
                
                JOptionPane.showMessageDialog(null,
                                              "Por favor, ingrese un nombre.", 
                                              "SAJA",
                                               JOptionPane.INFORMATION_MESSAGE);
                this.jTxtNombreTipoPlanPago.requestFocus();                   
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(JFraTipoPlanPago.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }//GEN-LAST:event_jBtnEditarActionPerformed

    private void jBtnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnEliminarActionPerformed
        // TODO add your handling code here:
        try {           

            eliminarTipoPlanPago();
            siguienteId();
            llenarTabla();
            habilitarBotones(true, false, false, true);
            
        } catch (SQLException ex) {
            Logger.getLogger(JFraTipoPlanPago.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jBtnEliminarActionPerformed

    private void jTfBuscarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTfBuscarKeyTyped
        // TODO add your handling code here:        
        /*try {
            this.llenarTablaPorID(this.jTfBuscar.getText());
        } catch (SQLException ex) {
            Logger.getLogger(JFraTipoPlanPago.class.getName()).log(Level.SEVERE, null, ex);
        }*/
    }//GEN-LAST:event_jTfBuscarKeyTyped

    private void jTfBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTfBuscarKeyReleased
        // TODO add your handling code here:
        try {
            this.llenarTablaPorID(this.jTfBuscar.getText());
        } catch (SQLException ex) {
            Logger.getLogger(JFraTipoPlanPago.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jTfBuscarKeyReleased

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
            java.util.logging.Logger.getLogger(JFraTipoPlanPago.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFraTipoPlanPago.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFraTipoPlanPago.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFraTipoPlanPago.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new JFraTipoPlanPago().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(JFraTipoPlanPago.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jBtnBuscar;
    private javax.swing.JButton jBtnEditar;
    private javax.swing.JButton jBtnEliminar;
    private javax.swing.JButton jBtnGuardar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLblTitulo;
    private javax.swing.JLabel jLblTitulo1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPnlCancelar;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTblTipoPlanPago;
    private javax.swing.JTextField jTfBuscar;
    private javax.swing.JTextField jTxtIdTipoPlanPago;
    private javax.swing.JTextField jTxtNombreTipoPlanPago;
    // End of variables declaration//GEN-END:variables
}
