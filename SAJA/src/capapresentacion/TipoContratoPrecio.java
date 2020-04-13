/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capapresentacion;

import capadatos.CDAñadirContrato;
import capadatos.CDTipoContratoServicio;
import capalogica.CLAñadirContrato;
import capalogica.CLTipoContratoServicio;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author CLIENTE
 */
public class TipoContratoPrecio extends javax.swing.JFrame {

    /**
     * Creates new form TipoContratoPrecio
     */
    public TipoContratoPrecio() throws SQLException {
        initComponents();
        cargarComboServicios();
        cargarComboTipoContrato();
        llenarTablaTipoContratoServicio();
        this.jBtnEditar.setEnabled(false);
        this.jBtnCancelar.setVisible(false);
        
    }
    private void limpiar(){
        this.jCboServicio.setSelectedIndex(0);
        this.jCboTipoContrato.setSelectedIndex(0);
        this.jTfprecio.setText("");
    }
    private void cargarDatos(){
        if(this.jTblPrecio.getSelectedRow()!= -1){
            
            this.jCboTipoContrato.setSelectedItem(String.valueOf(this.jTblPrecio.getValueAt(this.jTblPrecio.getSelectedRow(), 0)));
            this.jCboServicio.setSelectedItem(String.valueOf(this.jTblPrecio.getValueAt(this.jTblPrecio.getSelectedRow(), 1)));
            this.jTfprecio.setText(String.valueOf(this.jTblPrecio.getValueAt(this.jTblPrecio.getSelectedRow(), 2)));
           
        }
    }
    
    private void insertarTipoContratoServicio(){
        try{ 
            
            CDTipoContratoServicio cda = new CDTipoContratoServicio();
            CLTipoContratoServicio cla = new CLTipoContratoServicio();

            cla.setNombreServicio(this.jCboServicio.getSelectedItem().toString());
            cla.setTipoContrato(this.jCboTipoContrato.getSelectedItem().toString());
            cla.setPrecio(Float.parseFloat(this.jTfprecio.getText()));
            cda.insertarTipoContratoPrecio(cla);
            
            JOptionPane.showMessageDialog(null, "El registro se ha creado con exito...", "SAJA", 
                    JOptionPane.INFORMATION_MESSAGE);
            
            
            llenarTablaTipoContratoServicio();
            
        } catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error al crear el registro: " + ex);
        }
    }
    
    private void editarTipoContratoServicio(){
        try {
            
            CDTipoContratoServicio cda = new CDTipoContratoServicio();
            CLTipoContratoServicio cla = new CLTipoContratoServicio();
            

            cla.setNombreServicio(this.jCboServicio.getSelectedItem().toString());
            cla.setTipoContrato(this.jCboTipoContrato.getSelectedItem().toString());
            cla.setPrecio(Float.parseFloat(this.jTfprecio.getText()));
            cda.editarTipoContratoPrecio(cla);
            
            llenarTablaTipoContratoServicio();

            JOptionPane.showMessageDialog(null, "El registro se ha modificado con exito...", "SAJA",
                    JOptionPane.INFORMATION_MESSAGE);
            

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al editar el registro: " + ex);
        }
    }
   
     private void clearTable() {
        DefaultTableModel dtm = (DefaultTableModel) this.jTblPrecio.getModel();
        while (dtm.getRowCount() > 0) {
            dtm.removeRow(0);
        }
    }
    private void llenarTablaTipoContratoServicio() throws SQLException{
        clearTable();
        
        CDTipoContratoServicio cda = new CDTipoContratoServicio();
        
        List<CLTipoContratoServicio> miLista;
        miLista = cda.listaTipoContratoServicio();
        DefaultTableModel temp = (DefaultTableModel) this.jTblPrecio.getModel();

        miLista.stream().map((cla) -> {
            Object[] fila = new Object[4];
            fila[0] = cla.getTipoContrato();
            fila[1] = cla.getNombreServicio();            
            fila[2] = cla.getPrecio();
            fila[3] = cla.getFechaRegistro();
            
            return fila;
        }).forEachOrdered((fila) -> {
            temp.addRow(fila);
        });
    }
    
    private void llenarTablaPorTipoContrato(String Tipo) throws SQLException{
        clearTable();

        CDTipoContratoServicio cda = new CDTipoContratoServicio();
        List<CLTipoContratoServicio> miLista;
        miLista = cda.listaTipoContratoServicioBusqueda(Tipo);
        DefaultTableModel temp = (DefaultTableModel) this.jTblPrecio.getModel();

        miLista.stream().map((cla) -> {
            Object[] fila = new Object[4];
            fila[0] = cla.getTipoContrato();
            fila[1] = cla.getNombreServicio();            
            fila[2] = cla.getPrecio();
            fila[3] = cla.getFechaRegistro();
            

            return fila;
        }).forEachOrdered((fila) -> {
            temp.addRow(fila);
        });
    }
//Metodos para llenar combobox de servicio y tipo contrato
    private void cargarComboTipoContrato() throws SQLException {

        CDTipoContratoServicio cda = new CDTipoContratoServicio();

        String[] Tipo = new String[cda.loadTipoContrato().size()];
        Tipo = cda.loadTipoContrato().toArray(Tipo);

        DefaultComboBoxModel modeloContrato = new DefaultComboBoxModel(Tipo);
        this.jCboTipoContrato.setModel(modeloContrato);
    }
    private void cargarComboServicios() throws SQLException {

        CDTipoContratoServicio cda = new CDTipoContratoServicio();

        String[] Tipo = new String[cda.loadServicio().size()];
        Tipo = cda.loadServicio().toArray(Tipo);

        DefaultComboBoxModel modeloServicio = new DefaultComboBoxModel(Tipo);
        this.jCboServicio.setModel(modeloServicio);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPnlPrecioServicioTipoContrato = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLblTitulo = new javax.swing.JLabel();
        jLblMenu = new javax.swing.JLabel();
        jLblNombre = new javax.swing.JLabel();
        jBtnGuardar = new javax.swing.JButton();
        jBtnEditar = new javax.swing.JButton();
        jPnlSeparator = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTblPrecio = new javax.swing.JTable();
        jPnlBuscar = new javax.swing.JPanel();
        jTfBuscar = new javax.swing.JTextField();
        jBtnBuscar = new javax.swing.JLabel();
        jBtnCancelar = new javax.swing.JLabel();
        jLblNombre1 = new javax.swing.JLabel();
        jLblNombre2 = new javax.swing.JLabel();
        jCboServicio = new javax.swing.JComboBox<>();
        jCboTipoContrato = new javax.swing.JComboBox<>();
        jTfprecio = new javax.swing.JTextField();
        jLblNombre3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPnlPrecioServicioTipoContrato.setBackground(new java.awt.Color(255, 255, 255));
        jPnlPrecioServicioTipoContrato.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(9, 132, 227));

        jLblTitulo.setFont(new java.awt.Font("HelveticaNowDisplay ExtraBold", 0, 30)); // NOI18N
        jLblTitulo.setForeground(new java.awt.Color(255, 255, 255));
        jLblTitulo.setText("Precio de Servicios");

        jLblMenu.setFont(new java.awt.Font("HelveticaNowDisplay ExtraBold", 0, 25)); // NOI18N
        jLblMenu.setForeground(new java.awt.Color(255, 255, 255));
        jLblMenu.setText("Menú");
        jLblMenu.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLblTitulo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 446, Short.MAX_VALUE)
                .addComponent(jLblMenu)
                .addGap(30, 30, 30))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLblMenu)
                    .addComponent(jLblTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPnlPrecioServicioTipoContrato.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 820, 60));

        jLblNombre.setBackground(new java.awt.Color(255, 255, 255));
        jLblNombre.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 1, 28)); // NOI18N
        jLblNombre.setForeground(new java.awt.Color(0, 0, 0));
        jLblNombre.setText("L.");
        jPnlPrecioServicioTipoContrato.add(jLblNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 280, 30, 40));

        jBtnGuardar.setBackground(new java.awt.Color(9, 132, 227));
        jBtnGuardar.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 0, 16)); // NOI18N
        jBtnGuardar.setForeground(new java.awt.Color(255, 255, 255));
        jBtnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/guardar-white24.png"))); // NOI18N
        jBtnGuardar.setText("GUARDAR");
        jBtnGuardar.setBorder(null);
        jBtnGuardar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jBtnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnGuardarActionPerformed(evt);
            }
        });
        jPnlPrecioServicioTipoContrato.add(jBtnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 430, 150, 50));

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
        jPnlPrecioServicioTipoContrato.add(jBtnEditar, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 430, 150, 50));

        jPnlSeparator.setAlignmentX(0.1F);
        jPnlSeparator.setPreferredSize(new java.awt.Dimension(1, 360));

        javax.swing.GroupLayout jPnlSeparatorLayout = new javax.swing.GroupLayout(jPnlSeparator);
        jPnlSeparator.setLayout(jPnlSeparatorLayout);
        jPnlSeparatorLayout.setHorizontalGroup(
            jPnlSeparatorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1, Short.MAX_VALUE)
        );
        jPnlSeparatorLayout.setVerticalGroup(
            jPnlSeparatorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 398, Short.MAX_VALUE)
        );

        jPnlPrecioServicioTipoContrato.add(jPnlSeparator, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 80, -1, 398));

        jTblPrecio.setBackground(new java.awt.Color(255, 255, 255));
        jTblPrecio.setFont(new java.awt.Font("HelveticaNowDisplay Regular", 0, 15)); // NOI18N
        jTblPrecio.setForeground(new java.awt.Color(0, 0, 0));
        jTblPrecio.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tipo de Contrato", "Servicio", "Precio", "Fecha de registro"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTblPrecio.setRowHeight(30);
        jTblPrecio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTblPrecioMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTblPrecio);

        jPnlPrecioServicioTipoContrato.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 130, 380, 310));

        jPnlBuscar.setBackground(new java.awt.Color(255, 255, 255));
        jPnlBuscar.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTfBuscar.setBackground(new java.awt.Color(255, 255, 255));
        jTfBuscar.setFont(new java.awt.Font("HelveticaNowDisplay Regular", 0, 18)); // NOI18N
        jTfBuscar.setForeground(new java.awt.Color(0, 0, 0));
        jTfBuscar.setBorder(null);
        jTfBuscar.setSelectionColor(new java.awt.Color(0, 153, 153));
        jTfBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTfBuscarKeyReleased(evt);
            }
        });

        jBtnBuscar.setBackground(new java.awt.Color(9, 132, 227));
        jBtnBuscar.setForeground(new java.awt.Color(9, 132, 227));
        jBtnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/search-black24.png"))); // NOI18N
        jBtnBuscar.setToolTipText("Click para buscar");
        jBtnBuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout jPnlBuscarLayout = new javax.swing.GroupLayout(jPnlBuscar);
        jPnlBuscar.setLayout(jPnlBuscarLayout);
        jPnlBuscarLayout.setHorizontalGroup(
            jPnlBuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPnlBuscarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTfBuscar, javax.swing.GroupLayout.DEFAULT_SIZE, 314, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jBtnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPnlBuscarLayout.setVerticalGroup(
            jPnlBuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTfBuscar)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPnlBuscarLayout.createSequentialGroup()
                .addComponent(jBtnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPnlPrecioServicioTipoContrato.add(jPnlBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 80, 380, 40));

        jBtnCancelar.setBackground(new java.awt.Color(255, 255, 255));
        jBtnCancelar.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 1, 14)); // NOI18N
        jBtnCancelar.setForeground(new java.awt.Color(9, 132, 227));
        jBtnCancelar.setText("Cancelar Acción");
        jBtnCancelar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jBtnCancelar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jBtnCancelarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jBtnCancelarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jBtnCancelarMouseExited(evt);
            }
        });
        jPnlPrecioServicioTipoContrato.add(jBtnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 330, 110, -1));

        jLblNombre1.setBackground(new java.awt.Color(255, 255, 255));
        jLblNombre1.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 1, 24)); // NOI18N
        jLblNombre1.setForeground(new java.awt.Color(0, 0, 0));
        jLblNombre1.setText("Tipo de contrato");
        jPnlPrecioServicioTipoContrato.add(jLblNombre1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, 200, 30));

        jLblNombre2.setBackground(new java.awt.Color(255, 255, 255));
        jLblNombre2.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 1, 24)); // NOI18N
        jLblNombre2.setForeground(new java.awt.Color(0, 0, 0));
        jLblNombre2.setText("Servicio");
        jPnlPrecioServicioTipoContrato.add(jLblNombre2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 170, 110, 30));

        jCboServicio.setBackground(new java.awt.Color(255, 255, 255));
        jCboServicio.setFont(new java.awt.Font("HelveticaNowDisplay Regular", 0, 14)); // NOI18N
        jCboServicio.setForeground(new java.awt.Color(0, 0, 0));
        jPnlPrecioServicioTipoContrato.add(jCboServicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 200, 320, 40));

        jCboTipoContrato.setBackground(new java.awt.Color(255, 255, 255));
        jCboTipoContrato.setFont(new java.awt.Font("HelveticaNowDisplay Regular", 0, 14)); // NOI18N
        jCboTipoContrato.setForeground(new java.awt.Color(0, 0, 0));
        jPnlPrecioServicioTipoContrato.add(jCboTipoContrato, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, 320, 40));

        jTfprecio.setBackground(new java.awt.Color(255, 255, 255));
        jTfprecio.setFont(new java.awt.Font("HelveticaNowDisplay Regular", 0, 16)); // NOI18N
        jTfprecio.setForeground(new java.awt.Color(0, 0, 0));
        jPnlPrecioServicioTipoContrato.add(jTfprecio, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 280, 290, 40));

        jLblNombre3.setBackground(new java.awt.Color(255, 255, 255));
        jLblNombre3.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 1, 24)); // NOI18N
        jLblNombre3.setForeground(new java.awt.Color(0, 0, 0));
        jLblNombre3.setText("Precio ");
        jPnlPrecioServicioTipoContrato.add(jLblNombre3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 250, 180, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPnlPrecioServicioTipoContrato, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPnlPrecioServicioTipoContrato, javax.swing.GroupLayout.DEFAULT_SIZE, 502, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBtnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnGuardarActionPerformed
       insertarTipoContratoServicio();
       limpiar();
    }//GEN-LAST:event_jBtnGuardarActionPerformed

    private void jBtnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnEditarActionPerformed
       editarTipoContratoServicio();
       limpiar();
       this.jBtnEditar.setEnabled(false);
       this.jBtnGuardar.setEnabled(true);
       this.jBtnCancelar.setVisible(false);
       this.jCboTipoContrato.setEnabled(true);
       this.jCboServicio.setEnabled(true);
    }//GEN-LAST:event_jBtnEditarActionPerformed

    private void jTblPrecioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTblPrecioMouseClicked
       this.jBtnEditar.setEnabled(true);
       this.jBtnGuardar.setEnabled(false);
       this.jBtnCancelar.setVisible(true);
       this.jCboTipoContrato.setEnabled(false);
       this.jCboServicio.setEnabled(false);
       cargarDatos();
    }//GEN-LAST:event_jTblPrecioMouseClicked

    private void jTfBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTfBuscarKeyReleased
        try {
            llenarTablaPorTipoContrato(this.jTfBuscar.getText());
        } catch (SQLException ex) {
            Logger.getLogger(TipoContratoPrecio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jTfBuscarKeyReleased

    private void jBtnCancelarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBtnCancelarMouseClicked
        this.jBtnEditar.setEnabled(false);
       this.jBtnGuardar.setEnabled(true);
       this.jBtnCancelar.setVisible(false);
       this.jCboTipoContrato.setEnabled(true);
       this.jCboServicio.setEnabled(true);
    }//GEN-LAST:event_jBtnCancelarMouseClicked

    private void jBtnCancelarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBtnCancelarMouseEntered
    
    }//GEN-LAST:event_jBtnCancelarMouseEntered

    private void jBtnCancelarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBtnCancelarMouseExited
        
    }//GEN-LAST:event_jBtnCancelarMouseExited

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
            java.util.logging.Logger.getLogger(TipoContratoPrecio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TipoContratoPrecio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TipoContratoPrecio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TipoContratoPrecio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new TipoContratoPrecio().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(TipoContratoPrecio.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jBtnBuscar;
    private javax.swing.JLabel jBtnCancelar;
    private javax.swing.JButton jBtnEditar;
    private javax.swing.JButton jBtnGuardar;
    private javax.swing.JComboBox<String> jCboServicio;
    private javax.swing.JComboBox<String> jCboTipoContrato;
    private javax.swing.JLabel jLblMenu;
    private javax.swing.JLabel jLblNombre;
    private javax.swing.JLabel jLblNombre1;
    private javax.swing.JLabel jLblNombre2;
    private javax.swing.JLabel jLblNombre3;
    private javax.swing.JLabel jLblTitulo;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPnlBuscar;
    private javax.swing.JPanel jPnlPrecioServicioTipoContrato;
    private javax.swing.JPanel jPnlSeparator;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTblPrecio;
    private javax.swing.JTextField jTfBuscar;
    private javax.swing.JTextField jTfprecio;
    // End of variables declaration//GEN-END:variables
}
