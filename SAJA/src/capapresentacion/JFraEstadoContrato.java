/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capapresentacion;

import capadatos.CDEstadoContrato;
import capalogica.CLEstadoContrato;
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
 * @author Juan
 */
public class JFraEstadoContrato extends javax.swing.JFrame {

    /**
     * Creates new form JFraEstadoContrato
     */
    public JFraEstadoContrato() throws SQLException {
        initComponents();
        this.jLblCancelar.setVisible(false);
        this.setLocationRelativeTo(null);
        this.jTfNombreEstadoContrato.requestFocus();
        PlaceHolder ph = new PlaceHolder(this.jTfBuscar, 
                                        new Color(153,153,153),
                                        Color.BLACK,
                                        "Buscar",
                                        false,
                                        "HelveticaNowDisplay Regular",
                                        18);
        llenarTablaEstados();
        ultimoIDEstado();
        habilitarBotones(true, false, false, true);
    }
    
    private boolean estadEditando = false;
    
    private void limpiarTabla(){
        DefaultTableModel dtm = (DefaultTableModel) this.jTblEstadoContrato.getModel();
        
        while(dtm.getRowCount() > 0){
            dtm.removeRow(0);
        }
    }
    
    private void llenarTablaEstados() throws SQLException{
        limpiarTabla();
        
        CDEstadoContrato cdec = new CDEstadoContrato();
        
        List<CLEstadoContrato> miLista = cdec.ListaEstadoContrato();
        DefaultTableModel temp = (DefaultTableModel) this.jTblEstadoContrato.getModel();
        
        for(CLEstadoContrato cl: miLista) {
            Object[] fila = new Object[2];
            fila[0] = cl.getIdEstadoContrato();
            fila[1] = cl.getEstadoContrato();
            temp.addRow(fila);
        };
    }
    
    private void buscarEstadoContratoPorNombre(String estadoContrato) throws SQLException{
        limpiarTabla();
        
        CDEstadoContrato cdc = new CDEstadoContrato();
        List<CLEstadoContrato> miLista = cdc.mostrarEstadoContratoPorNombre(estadoContrato);
        DefaultTableModel temp = (DefaultTableModel) this.jTblEstadoContrato.getModel();
        
        miLista.stream().map((cl) -> {
            Object[] fila = new Object[2];
            fila[0] = cl.getIdEstadoContrato();
            fila[1] = cl.getEstadoContrato();
            return fila;
        }).forEachOrdered((fila) -> {
            temp.addRow(fila);
        });
    }
    
    public void limpiarFormulario() throws SQLException{
        this.ultimoIDEstado();
        this.jTfNombreEstadoContrato.setText("");
        this.jTfBuscar.setText("");
        this.habilitarBotones(true, false, false, true);
        this.jTfNombreEstadoContrato.requestFocus();
        this.jTblEstadoContrato.clearSelection();
    }
    
    private void insertarEstadoContrato(){
        try{
            CDEstadoContrato cdec = new CDEstadoContrato();
            CLEstadoContrato clec = new CLEstadoContrato();
            
            clec.setEstadoContrato(this.jTfNombreEstadoContrato.getText().trim());
            
            cdec.insertar(clec);
            
            JOptionPane.showMessageDialog(null, 
                                          "Se ha registrado un estado...",
                                          "SAJA",
                                          JOptionPane.INFORMATION_MESSAGE);
            limpiarFormulario();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error al registrar el estado: " + e);      
        }
    }
    
    private void actualizarEstadoContrato(){
        try{
            CDEstadoContrato cdec = new CDEstadoContrato();
            CLEstadoContrato clec = new CLEstadoContrato();            
            
            clec.setEstadoContrato(this.jTfNombreEstadoContrato.getText().trim());
            clec.setIdEstadoContrato(Integer.parseInt(this.jTfEstadoContrato.getText().trim()));
            
            cdec.actualizarEstadoContrato(clec);
            
            JOptionPane.showMessageDialog(null, 
                                          "Se ha actualizado un estado...",
                                          "SAJA",
                                          JOptionPane.INFORMATION_MESSAGE);
            limpiarFormulario();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error al actualizar el estado: " + e);      
        }
    }
    
    private void eliminarEstadoContrato(){
        try{
            CDEstadoContrato cdec = new CDEstadoContrato();
            CLEstadoContrato clec = new CLEstadoContrato();
            
            clec.setIdEstadoContrato(Integer.parseInt(this.jTfEstadoContrato.getText()));
            
            cdec.eliminarEstadoContrato(clec);
            
            JOptionPane.showMessageDialog(null, 
                                          "Se ha eliminado un estado...",
                                          "SAJA",
                                          JOptionPane.INFORMATION_MESSAGE);
            limpiarFormulario();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error al eliminar el estado: " + e);      
        }
    }
    
    private void ultimoIDEstado() throws SQLException{
        CDEstadoContrato cdec = new CDEstadoContrato();
        CLEstadoContrato clec = new CLEstadoContrato();
        
        clec.setIdEstadoContrato(cdec.autoIncrementarEstadoContrato());
        
        this.jTfEstadoContrato.setText(String.valueOf(clec.getIdEstadoContrato()));
    }

    private void seleccionarEstado(){
        if(this.jTblEstadoContrato.getSelectedRow() != -1){
            this.jTfEstadoContrato.setText(String.valueOf(this.jTblEstadoContrato.getValueAt(this.jTblEstadoContrato.getSelectedRow(), 0)));
            this.jTfNombreEstadoContrato.setText(String.valueOf(this.jTblEstadoContrato.getValueAt(this.jTblEstadoContrato.getSelectedRow(), 1)));
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
        jTfEstadoContrato = new javax.swing.JTextField();
        jLblNombre = new javax.swing.JLabel();
        jTfNombreEstadoContrato = new javax.swing.JTextField();
        jBtnGuardar = new javax.swing.JButton();
        jBtnEditar = new javax.swing.JButton();
        jBtnEliminar = new javax.swing.JButton();
        jPnlSeparator = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTblEstadoContrato = new javax.swing.JTable();
        jPnlBuscar = new javax.swing.JPanel();
        jTfBuscar = new javax.swing.JTextField();
        jBtnBuscar = new javax.swing.JLabel();
        jLblCancelar = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        setSize(new java.awt.Dimension(820, 500));

        jPnlCancelar.setBackground(new java.awt.Color(255, 255, 255));
        jPnlCancelar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(9, 132, 227));

        jLblTitulo.setFont(new java.awt.Font("HelveticaNowDisplay ExtraBold", 0, 30)); // NOI18N
        jLblTitulo.setForeground(new java.awt.Color(255, 255, 255));
        jLblTitulo.setText("Estado del contrato");

        jLblRegresar.setFont(new java.awt.Font("HelveticaNowDisplay ExtraBold", 0, 25)); // NOI18N
        jLblRegresar.setForeground(new java.awt.Color(255, 255, 255));
        jLblRegresar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/regresar-blanco24.png"))); // NOI18N
        jLblRegresar.setText("Regresar");
        jLblRegresar.setToolTipText("Regresar a las configuraciones");
        jLblRegresar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 195, Short.MAX_VALUE)
                .addComponent(jLblRegresar)
                .addGap(53, 53, 53)
                .addComponent(jLblMinimizar)
                .addGap(18, 18, 18)
                .addComponent(jLblCerrar)
                .addGap(31, 31, 31))
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
                .addGap(14, 14, 14))
        );

        jPnlCancelar.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 820, 60));

        jLblIdentificador.setBackground(new java.awt.Color(102, 102, 102));
        jLblIdentificador.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 1, 24)); // NOI18N
        jLblIdentificador.setForeground(new java.awt.Color(0, 0, 0));
        jLblIdentificador.setText("Identificador");
        jPnlCancelar.add(jLblIdentificador, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 80, 320, 30));

        jTfEstadoContrato.setEditable(false);
        jTfEstadoContrato.setBackground(new java.awt.Color(255, 255, 255));
        jTfEstadoContrato.setFont(new java.awt.Font("HelveticaNowDisplay Regular", 0, 18)); // NOI18N
        jTfEstadoContrato.setForeground(new java.awt.Color(0, 0, 0));
        jTfEstadoContrato.setText(" 1");
        jTfEstadoContrato.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTfEstadoContrato.setSelectionColor(new java.awt.Color(0, 153, 153));
        jPnlCancelar.add(jTfEstadoContrato, new org.netbeans.lib.awtextra.AbsoluteConstraints(52, 110, 320, 40));

        jLblNombre.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 1, 24)); // NOI18N
        jLblNombre.setForeground(new java.awt.Color(0, 0, 0));
        jLblNombre.setText("Nombre");
        jPnlCancelar.add(jLblNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 190, 300, 30));

        jTfNombreEstadoContrato.setBackground(new java.awt.Color(255, 255, 255));
        jTfNombreEstadoContrato.setFont(new java.awt.Font("HelveticaNowDisplay Regular", 0, 18)); // NOI18N
        jTfNombreEstadoContrato.setForeground(new java.awt.Color(0, 0, 0));
        jTfNombreEstadoContrato.setToolTipText("Ingrese aquí un estado del contrato");
        jTfNombreEstadoContrato.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTfNombreEstadoContrato.setSelectionColor(new java.awt.Color(0, 153, 153));
        jPnlCancelar.add(jTfNombreEstadoContrato, new org.netbeans.lib.awtextra.AbsoluteConstraints(52, 220, 320, 40));

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

        jTblEstadoContrato.setBackground(new java.awt.Color(255, 255, 255));
        jTblEstadoContrato.setFont(new java.awt.Font("HelveticaNowDisplay Light", 0, 15)); // NOI18N
        jTblEstadoContrato.setForeground(new java.awt.Color(0, 0, 0));
        jTblEstadoContrato.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nombre"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTblEstadoContrato.setRowHeight(30);
        jTblEstadoContrato.getTableHeader().setReorderingAllowed(false);
        jTblEstadoContrato.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTblEstadoContratoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTblEstadoContrato);

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

        jLblCancelar.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 1, 14)); // NOI18N
        jLblCancelar.setForeground(new java.awt.Color(41, 128, 185));
        jLblCancelar.setText("Cancelar edición");
        jLblCancelar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLblCancelar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLblCancelarMouseClicked(evt);
            }
        });
        jPnlCancelar.add(jLblCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 260, -1, 20));

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
        try {
            if(this.jTfNombreEstadoContrato.getText().isEmpty()){
                JOptionPane.showMessageDialog(null,
                                                       "Ingrese un nombre", 
                                                       "SAJA",
                                                       JOptionPane.INFORMATION_MESSAGE);
            }else{
                insertarEstadoContrato();
                llenarTablaEstados();
                ultimoIDEstado();        
            }    
        } catch (SQLException ex) {
            Logger.getLogger(JFraEstadoContrato.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jBtnGuardarActionPerformed

    private void jBtnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnEditarActionPerformed
        try {
            actualizarEstadoContrato();
            llenarTablaEstados();
            ultimoIDEstado();
            estadEditando = false;
        } catch (SQLException ex) {
            Logger.getLogger(JFraEstadoContrato.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jBtnEditarActionPerformed

    private void jBtnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnEliminarActionPerformed
        int result = JOptionPane.showConfirmDialog(null,
                                                    "¿Desea eliminar el estado?",
                                                    "SAJA",
                                                    JOptionPane.YES_NO_OPTION);
        if(result == JOptionPane.YES_OPTION) {
            try {
                eliminarEstadoContrato();
                llenarTablaEstados();
                ultimoIDEstado();
                estadEditando = false;
            } catch (SQLException ex) {
                Logger.getLogger(JFraTipoPlanPago.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jBtnEliminarActionPerformed

    private void jTblEstadoContratoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTblEstadoContratoMouseClicked
        seleccionarEstado();
        habilitarBotones(false, true, true, true);
        estadEditando = true;
        this.jLblCancelar.setVisible(true);
    }//GEN-LAST:event_jTblEstadoContratoMouseClicked

    private void jLblCancelarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLblCancelarMouseClicked
        // TODO add your handling code here:
        try {
            limpiarFormulario();
            habilitarBotones(true, false, false, false);
            this.jLblCancelar.setVisible(false);
            llenarTablaEstados();
        } catch (SQLException ex) {
            Logger.getLogger(JFraEstadoContrato.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jLblCancelarMouseClicked

    private void jTfBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTfBuscarKeyReleased
        // TODO add your handling code here:
        String estadoContrato;
        
        try {
            estadoContrato = this.jTfBuscar.getText();
            buscarEstadoContratoPorNombre(estadoContrato);
            this.jLblCancelar.setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(JFraEstadoContrato.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jTfBuscarKeyReleased

    private void jLblMinimizarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLblMinimizarMouseClicked
        this.setState(Frame.ICONIFIED);
    }//GEN-LAST:event_jLblMinimizarMouseClicked

    private void jLblCerrarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLblCerrarMouseClicked
        this.dispose();
    }//GEN-LAST:event_jLblCerrarMouseClicked

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
            java.util.logging.Logger.getLogger(JFraEstadoContrato.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFraEstadoContrato.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFraEstadoContrato.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFraEstadoContrato.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new JFraEstadoContrato().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(JFraEstadoContrato.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jBtnBuscar;
    private javax.swing.JButton jBtnEditar;
    private javax.swing.JButton jBtnEliminar;
    private javax.swing.JButton jBtnGuardar;
    private javax.swing.JLabel jLblCancelar;
    private javax.swing.JLabel jLblCerrar;
    private javax.swing.JLabel jLblIdentificador;
    private javax.swing.JLabel jLblMinimizar;
    private javax.swing.JLabel jLblNombre;
    private javax.swing.JLabel jLblRegresar;
    private javax.swing.JLabel jLblTitulo;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPnlBuscar;
    private javax.swing.JPanel jPnlCancelar;
    private javax.swing.JPanel jPnlSeparator;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTblEstadoContrato;
    private javax.swing.JTextField jTfBuscar;
    private javax.swing.JTextField jTfEstadoContrato;
    private javax.swing.JTextField jTfNombreEstadoContrato;
    // End of variables declaration//GEN-END:variables
}
