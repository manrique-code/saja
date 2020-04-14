/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capapresentacion;

import capadatos.CDTipoContrato;
import capalogica.CLTipoContrato;
import com.placeholder.PlaceHolder;
import java.awt.Color;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.awt.Frame;

/**
 *
 * @author Carlos
 */
public class JFraTipoContrato extends javax.swing.JFrame {

    /**
     * Creates new form JFraTipoContrato
     */
    public JFraTipoContrato() throws SQLException {
        initComponents();
        this.setLocationRelativeTo(null);
        this.jTfNombreTipoContrato.requestFocus();
        PlaceHolder ph = new PlaceHolder(this.jTfBuscar, 
                                        new Color(153,153,153),
                                        Color.BLACK,
                                        "Buscar",
                                        false,
                                        "HelveticaNowDisplay Regular",
                                        18);
        mostrarTablaTipoContrato();
        habilitarBotones(true, false, false);
        encontrarCorrelativo();
        this.jTfNombreTipoContrato.requestFocus();
        this.jLblCancelarEdicion.setVisible(false);
    }
    
    private boolean estadEditando = false;
    JFraConfiguraciones jfc = new JFraConfiguraciones();
    
    private void limpiarTabla(){
        DefaultTableModel dtm = (DefaultTableModel) this.jTblTipoContrato.getModel();
        
        while(dtm.getRowCount() > 0){
            dtm.removeRow(0);
        }
    }
    
    private void mostrarTablaTipoContrato() throws SQLException{
        limpiarTabla();
        CDTipoContrato cdtp = new CDTipoContrato();
        
        List<CLTipoContrato> miLista = cdtp.obtenerListaTipoContrato();
        DefaultTableModel temp = (DefaultTableModel) this.jTblTipoContrato.getModel();
        
        for(CLTipoContrato cltp: miLista) {
            Object[] fila = new Object[2];
            fila[0] = cltp.getIDTipoContrato();
            fila[1] = cltp.getTipoContrato();
            temp.addRow(fila);
        };
    }
    
    private void mostrarTablaPorNombreTipoContrato(String nombreTipoContrato) throws SQLException{
        limpiarTabla();
        
        CDTipoContrato cdtp = new CDTipoContrato();
        
        List<CLTipoContrato> miLista = cdtp.obtenerListaNombreTipoContrato(nombreTipoContrato);
        DefaultTableModel temp = (DefaultTableModel) this.jTblTipoContrato.getModel();
        
        for(CLTipoContrato cltp: miLista) {
            Object[] fila = new Object[2];
            fila[0] = cltp.getIDTipoContrato();
            fila[1] = cltp.getTipoContrato();
            temp.addRow(fila);
        };
    }
    
    private void habilitarBotones(boolean guardar, boolean editar, boolean eliminar){        
        this.jBtnGuardar.setEnabled(guardar);
        this.jBtnGuardar.setVisible(guardar);
        
        this.jBtnEditar.setEnabled(editar);
        this.jBtnEditar.setVisible(editar);
        
        this.jBtnEliminar.setEnabled(eliminar);       
    }
    
    private void seleccionarFila(){
        if(this.jTblTipoContrato.getSelectedRow() != -1){
            this.jTfIdTipoContrato.setText(String.valueOf(this.jTblTipoContrato.getValueAt(this.jTblTipoContrato.getSelectedRow(), 0)));
            this.jTfNombreTipoContrato.setText(String.valueOf(this.jTblTipoContrato.getValueAt(this.jTblTipoContrato.getSelectedRow(), 1)));
            this.jLblCancelarEdicion.setVisible(true);
        }
    }
    
    private void encontrarCorrelativo() throws SQLException{
        CDTipoContrato cdtc = new CDTipoContrato();
        CLTipoContrato cltc = new CLTipoContrato();
        
        cltc.setIDTipoContrato(cdtc.autoIncrementableIDTipoContrato());
        
        this.jTfIdTipoContrato.setText(String.valueOf(cltc.getIDTipoContrato()));
    }
    
    private void limpiarCajasDeTexto(){
        this.jTfIdTipoContrato.setText("");
        this.jTfNombreTipoContrato.setText("");
    }
    
    public void limpiarFormulario() throws SQLException{
        this.encontrarCorrelativo();
        this.jTfNombreTipoContrato.setText("");
        this.habilitarBotones(true, false, false);
        this.jTfNombreTipoContrato.requestFocus();
        this.jTblTipoContrato.clearSelection();
                    
    }
    
    private void insertarTipoContrato(){
        try{
            CDTipoContrato cdtc = new CDTipoContrato();
            CLTipoContrato cltc = new CLTipoContrato();
            
            cltc.setTipoContrato(this.jTfNombreTipoContrato.getText().trim());
            cdtc.insertarTipoContrato(cltc);
            
            JOptionPane.showMessageDialog(null, 
                                          "Se guardó el registro satisfactoriamente...",
                                          "Saja",
                                          JOptionPane.INFORMATION_MESSAGE);
            limpiarCajasDeTexto();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error al guardar el tipo de contrato: " + e);      
        }
    }
    
    private void modificarTipoContrato(){
        try{
            CDTipoContrato cdtc = new CDTipoContrato();
            CLTipoContrato cltc = new CLTipoContrato();          
            
            cltc.setTipoContrato(this.jTfNombreTipoContrato.getText().trim());
            cltc.setIDTipoContrato(Integer.parseInt(this.jTfIdTipoContrato.getText().trim()));
            
            cdtc.modificarTipoContrato(cltc);
            
            JOptionPane.showMessageDialog(null, 
                                          "Se modificó el registro satisfactoriamente...",
                                          "Saja",
                                          JOptionPane.INFORMATION_MESSAGE);
            limpiarCajasDeTexto();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error al modificar el tipo de contrato: " + e);      
        }
    }
    
    
    private void eliminarTipoContrato(){
        try{
            CDTipoContrato cdtc = new CDTipoContrato();
            CLTipoContrato cltc = new CLTipoContrato(); 
            
            cltc.setIDTipoContrato(Integer.parseInt(this.jTfIdTipoContrato.getText()));
            
            cdtc.eliminarTipoContrato(cltc);
            
            JOptionPane.showMessageDialog(null, 
                                          "Se eliminó el registro satisfactoriamente...",
                                          "Saja",
                                          JOptionPane.INFORMATION_MESSAGE);
            limpiarCajasDeTexto();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error al eliminar el tipo de contrato: " + e);      
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPnlCancelar = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLblTitulo = new javax.swing.JLabel();
        jLblRegresar = new javax.swing.JLabel();
        jLblMinimizar = new javax.swing.JLabel();
        jLblCerrar = new javax.swing.JLabel();
        jLblIdentificador = new javax.swing.JLabel();
        jTfIdTipoContrato = new javax.swing.JTextField();
        jLblCancelarEdicion = new javax.swing.JLabel();
        jTfNombreTipoContrato = new javax.swing.JTextField();
        jBtnGuardar = new javax.swing.JButton();
        jBtnEditar = new javax.swing.JButton();
        jBtnEliminar = new javax.swing.JButton();
        jPnlSeparator = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTblTipoContrato = new javax.swing.JTable();
        jPnlBuscar = new javax.swing.JPanel();
        jTfBuscar = new javax.swing.JTextField();
        jBtnBuscar = new javax.swing.JLabel();
        jLblNombre1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPnlCancelar.setBackground(new java.awt.Color(255, 255, 255));
        jPnlCancelar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(9, 132, 227));

        jLblTitulo.setFont(new java.awt.Font("HelveticaNowDisplay ExtraBold", 0, 30)); // NOI18N
        jLblTitulo.setForeground(new java.awt.Color(255, 255, 255));
        jLblTitulo.setText("Tipo de contrato");

        jLblRegresar.setFont(new java.awt.Font("HelveticaNowDisplay ExtraBold", 0, 25)); // NOI18N
        jLblRegresar.setForeground(new java.awt.Color(255, 255, 255));
        jLblRegresar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/regresar-blanco24.png"))); // NOI18N
        jLblRegresar.setText("Regresar");
        jLblRegresar.setToolTipText("Regresar a las configuraciones");
        jLblRegresar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLblRegresar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLblRegresarMouseClicked(evt);
            }
        });

        jLblMinimizar.setFont(new java.awt.Font("HelveticaNowDisplay ExtraBold", 0, 25)); // NOI18N
        jLblMinimizar.setForeground(new java.awt.Color(255, 255, 255));
        jLblMinimizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/minimizar-blanco24.png"))); // NOI18N
        jLblMinimizar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLblMinimizar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLblMinimizarMouseClicked(evt);
            }
        });

        jLblCerrar.setFont(new java.awt.Font("HelveticaNowDisplay ExtraBold", 0, 25)); // NOI18N
        jLblCerrar.setForeground(new java.awt.Color(255, 255, 255));
        jLblCerrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/cerrar-blanco24.png"))); // NOI18N
        jLblCerrar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLblCerrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLblCerrarMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addComponent(jLblTitulo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 236, Short.MAX_VALUE)
                .addComponent(jLblRegresar)
                .addGap(53, 53, 53)
                .addComponent(jLblMinimizar)
                .addGap(18, 18, 18)
                .addComponent(jLblCerrar)
                .addGap(34, 34, 34))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLblTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLblRegresar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLblCerrar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLblMinimizar, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15))
        );

        jPnlCancelar.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 820, 60));

        jLblIdentificador.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 1, 24)); // NOI18N
        jLblIdentificador.setForeground(new java.awt.Color(0, 0, 0));
        jLblIdentificador.setText("Identificador");
        jPnlCancelar.add(jLblIdentificador, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 80, 280, 30));

        jTfIdTipoContrato.setEditable(false);
        jTfIdTipoContrato.setBackground(java.awt.Color.white);
        jTfIdTipoContrato.setFont(new java.awt.Font("HelveticaNowDisplay Regular", 0, 18)); // NOI18N
        jTfIdTipoContrato.setForeground(new java.awt.Color(0, 0, 0));
        jTfIdTipoContrato.setText(" 1");
        jTfIdTipoContrato.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTfIdTipoContrato.setSelectionColor(new java.awt.Color(0, 153, 153));
        jPnlCancelar.add(jTfIdTipoContrato, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 110, 302, 40));

        jLblCancelarEdicion.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 1, 14)); // NOI18N
        jLblCancelarEdicion.setForeground(new java.awt.Color(9, 132, 227));
        jLblCancelarEdicion.setText("Cancelar edición");
        jLblCancelarEdicion.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLblCancelarEdicion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLblCancelarEdicionMouseClicked(evt);
            }
        });
        jPnlCancelar.add(jLblCancelarEdicion, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 260, 110, 30));

        jTfNombreTipoContrato.setBackground(java.awt.Color.white);
        jTfNombreTipoContrato.setFont(new java.awt.Font("HelveticaNowDisplay Regular", 0, 18)); // NOI18N
        jTfNombreTipoContrato.setForeground(new java.awt.Color(0, 0, 0));
        jTfNombreTipoContrato.setToolTipText("Ingrese un tipo de plan de pago");
        jTfNombreTipoContrato.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTfNombreTipoContrato.setSelectionColor(new java.awt.Color(0, 153, 153));
        jPnlCancelar.add(jTfNombreTipoContrato, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 220, 302, 40));

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

        javax.swing.GroupLayout jPnlSeparatorLayout = new javax.swing.GroupLayout(jPnlSeparator);
        jPnlSeparator.setLayout(jPnlSeparatorLayout);
        jPnlSeparatorLayout.setHorizontalGroup(
            jPnlSeparatorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1, Short.MAX_VALUE)
        );
        jPnlSeparatorLayout.setVerticalGroup(
            jPnlSeparatorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 360, Short.MAX_VALUE)
        );

        jPnlCancelar.add(jPnlSeparator, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 80, 1, 360));

        jTblTipoContrato.setBackground(new java.awt.Color(255, 255, 255));
        jTblTipoContrato.setFont(new java.awt.Font("HelveticaNowDisplay Light", 0, 15)); // NOI18N
        jTblTipoContrato.setForeground(new java.awt.Color(0, 0, 0));
        jTblTipoContrato.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nombre"
            }
        ));
        jTblTipoContrato.setRowHeight(30);
        jTblTipoContrato.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTblTipoContratoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTblTipoContrato);

        jPnlCancelar.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 130, 350, 310));

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
                .addComponent(jTfBuscar, javax.swing.GroupLayout.DEFAULT_SIZE, 288, Short.MAX_VALUE)
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

        jPnlCancelar.add(jPnlBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 80, 350, 40));

        jLblNombre1.setBackground(java.awt.Color.white);
        jLblNombre1.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 1, 24)); // NOI18N
        jLblNombre1.setForeground(new java.awt.Color(0, 0, 0));
        jLblNombre1.setText("Nombre");
        jPnlCancelar.add(jLblNombre1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 190, 280, 30));

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

    private void jBtnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnGuardarActionPerformed
        if(this.jTfNombreTipoContrato.getText().trim().equals("")){
            JOptionPane.showMessageDialog(null, "Ingrese el nombre del tipo de contrato", "Saja", JOptionPane.INFORMATION_MESSAGE);
            try {
                this.encontrarCorrelativo();
                this.habilitarBotones(true, false, false);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error al guardar el registro: " + ex);
            }
        }else{
            try {
                this.insertarTipoContrato();
                this.encontrarCorrelativo();
                this.mostrarTablaTipoContrato();
                this.habilitarBotones(true, false, false);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error al guardar el registro: " + ex);
            }
        }

    }//GEN-LAST:event_jBtnGuardarActionPerformed

    private void jBtnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnEliminarActionPerformed
        if(this.jTfNombreTipoContrato.getText().trim().equals("")){
            JOptionPane.showMessageDialog(null, "Ingrese el nombre del tipo de contrato que desea eliminar", "Saja", JOptionPane.INFORMATION_MESSAGE);
            try {
                this.encontrarCorrelativo();
                this.habilitarBotones(true, false, false);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error al eliminar el registro: " + ex);
            }
        }else{
            try {
                this.eliminarTipoContrato();
                this.encontrarCorrelativo();
                this.mostrarTablaTipoContrato();
                this.habilitarBotones(true, false, false);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error al eliminar el registro: " + ex);
            }
        }
    }//GEN-LAST:event_jBtnEliminarActionPerformed

    private void jBtnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnEditarActionPerformed
        if(this.jTfNombreTipoContrato.getText().trim().equals("")){
            JOptionPane.showMessageDialog(null, "Ingrese el nuevo nombre del tipo de contrato", "Saja", JOptionPane.INFORMATION_MESSAGE);
            try {
                this.encontrarCorrelativo();
                this.habilitarBotones(true, false, false);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error al editar el registro: " + ex);
            }
        }else{
            try {
                this.modificarTipoContrato();
                this.encontrarCorrelativo();
                this.mostrarTablaTipoContrato();
                this.habilitarBotones(true, false, false);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error al editar el registro: " + ex);
            }
        }
    }//GEN-LAST:event_jBtnEditarActionPerformed

    private void jTblTipoContratoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTblTipoContratoMouseClicked
        seleccionarFila();
        habilitarBotones(false, true, true);
        estadEditando = true;
    }//GEN-LAST:event_jTblTipoContratoMouseClicked

    private void jTfBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTfBuscarKeyReleased
        try{
            this.mostrarTablaPorNombreTipoContrato(this.jTfBuscar.getText());
        }catch (SQLException ex){
            Logger.getLogger(JFraTipoPlanPago.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jTfBuscarKeyReleased

    private void jLblCancelarEdicionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLblCancelarEdicionMouseClicked
        if(estadEditando){
            int result = JOptionPane.showConfirmDialog(null,
                                                       "¿Desea cancelar la edición?", 
                                                       "SAJA",
                                                       JOptionPane.YES_NO_OPTION);
            
            if(result == JOptionPane.YES_OPTION) {
                try {
                    limpiarFormulario();
                    estadEditando = false;
                    this.jLblCancelarEdicion.setVisible(false);
                } catch (SQLException ex) {
                    Logger.getLogger(JFraTipoPlanPago.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        }else{
            return;
        }
    }//GEN-LAST:event_jLblCancelarEdicionMouseClicked

    private void jLblMinimizarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLblMinimizarMouseClicked
        this.setState(Frame.ICONIFIED);
    }//GEN-LAST:event_jLblMinimizarMouseClicked

    private void jLblCerrarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLblCerrarMouseClicked
        this.dispose();
    }//GEN-LAST:event_jLblCerrarMouseClicked

    private void jLblRegresarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLblRegresarMouseClicked
        jfc.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jLblRegresarMouseClicked

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
            java.util.logging.Logger.getLogger(JFraTipoContrato.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFraTipoContrato.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFraTipoContrato.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFraTipoContrato.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new JFraTipoContrato().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(JFraTipoContrato.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jBtnBuscar;
    private javax.swing.JButton jBtnEditar;
    private javax.swing.JButton jBtnEliminar;
    private javax.swing.JButton jBtnGuardar;
    private javax.swing.JLabel jLblCancelarEdicion;
    private javax.swing.JLabel jLblCerrar;
    private javax.swing.JLabel jLblIdentificador;
    private javax.swing.JLabel jLblMinimizar;
    private javax.swing.JLabel jLblNombre1;
    private javax.swing.JLabel jLblRegresar;
    private javax.swing.JLabel jLblTitulo;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPnlBuscar;
    private javax.swing.JPanel jPnlCancelar;
    private javax.swing.JPanel jPnlSeparator;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTblTipoContrato;
    private javax.swing.JTextField jTfBuscar;
    private javax.swing.JTextField jTfIdTipoContrato;
    private javax.swing.JTextField jTfNombreTipoContrato;
    // End of variables declaration//GEN-END:variables
}
