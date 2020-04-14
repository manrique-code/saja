/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capapresentacion;

import capadatos.CDServicios;
import capalogica.CLServicios;
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
 * @author Pablo
 */
public class JFraServicios extends javax.swing.JFrame {

    /**
     * Creates new form JFraServicios
     */
    public JFraServicios() throws SQLException {
        initComponents();
        this.jBtnCancelar.setVisible(false);
        this.jBtnGuardar.setVisible(true);
        this.jBtnEditar.setVisible(false);
        this.setLocationRelativeTo(null);
        findCorrelative();
        enabledButtons(true,true,false,true);
        this.jTfNombreServicio.requestFocus();
        
        llenarTablaServicios();
        PlaceHolder ph = new PlaceHolder(this.jTfBuscar, 
                                        new Color(153,153,153),
                                        Color.BLACK,
                                        "Buscar",
                                        false,
                                        "HelveticaNowDisplay Regular",
                                        18);
    }
    // Colores del formulario
    Color celeste = new Color(52,152,219);
    Color azul = new Color(52,73,94);
    
    JFraConfiguraciones jfc = new JFraConfiguraciones();
    
        // Method to clear the jTable.
    private void clearTable(){
        DefaultTableModel dtm = (DefaultTableModel) this.jTblServicio.getModel();
        
        while(dtm.getRowCount() > 0){
            dtm.removeRow(0);
        }
    }
    
    // LLENAR TABLA SERVICIOS
    private void llenarTablaServicios() throws SQLException{
        clearTable();
        String status;
        CDServicios cdc = new CDServicios();
        List<CLServicios> miLista = cdc.listaServicios();
        DefaultTableModel temp = (DefaultTableModel) this.jTblServicio.getModel();
        
       for(CLServicios cl: miLista) {
            Object[] fila = new Object[3];
            fila[0] = cl.getIdServicio();
            fila[1] = cl.getNombreServicio();
            if(cl.getEstadoServicio() == 1){
                status = "Activo";
            }else{
                status ="Inactivo";
            }
            fila[2]= status;
            temp.addRow(fila);
        }
    }
    
    // LLENAR TABLA POR SERVICIO
    private void llenarTablaNombreServicio(String idServicio) throws SQLException{
        clearTable();
        
        CDServicios cdc = new CDServicios();
        List<CLServicios> miLista = cdc.listaServicioID(idServicio);
        DefaultTableModel temp = (DefaultTableModel) this.jTblServicio.getModel();
        
        miLista.stream().map((cl) -> {
            String status;
            Object[] fila = new Object[3];
            fila[0] = cl.getIdServicio();
            fila[1] = cl.getNombreServicio();
            if(cl.getEstadoServicio() == 1){
                status = "Activo";
            }else{
                status ="Inactivo";
            }
            fila[2]= status;
            return fila;
        }).forEachOrdered((fila) -> {
            temp.addRow(fila);
        });
    }
    
    // Method to enabled the buttons.
    private void enabledButtons(boolean add, boolean modify, boolean delete,boolean cancel){
        this.jBtnGuardar.setEnabled(add);
        this.jBtnEditar.setEnabled(modify);
        this.jBtnEliminar.setEnabled(delete);
        this.jBtnCancelar.setEnabled(cancel);
        
    }
    
    // Method to fill the TextField with the selected a row of the JTable.
    private void selectedRow(){
        if(this.jTblServicio.getSelectedRow() != -1){
            this.jTfIdServicio.setText(String.valueOf(this.jTblServicio.getValueAt(this.jTblServicio.getSelectedRow(), 0)));
            this.jTfNombreServicio.setText(String.valueOf(this.jTblServicio.getValueAt(this.jTblServicio.getSelectedRow(), 1)));
            String estado =  (String.valueOf(this.jTblServicio.getValueAt(this.jTblServicio.getSelectedRow(), 2)));
            
            if(estado == "Activo"){
               this.jChkActivo.setSelected(true);
            }else{
                this.jChkActivo.setSelected(false);
            }
        }
    }
    
    // Method to consult the correlative ID of the color.
    private void findCorrelative() throws SQLException{
        
        CDServicios cds = new CDServicios();
        CLServicios cls = new CLServicios();
        
        cls.setIdServicio(cds.autoIncrementServicioID());
        this.jTfIdServicio.setText(String.valueOf(cls.getIdServicio()));
        
    }
    
    // Method to clear the TextFields.
    private void clearTextField() throws SQLException{
        this.jTfIdServicio.setText("");
        this.jTfNombreServicio.setText("");
        
    }
    
    // Method to insert the color in the DB.
    private void insertarServicio(){
        try{
            CDServicios cds = new CDServicios();
            CLServicios cls = new CLServicios();
            cls.setNombreServicio(this.jTfNombreServicio.getText().trim());
            if(this.jChkActivo.isSelected()){
                cls.setEstadoServicio(1);
            }else{
                cls.setEstadoServicio(0);
            }
            cds.insertarServicio(cls);
            
            JOptionPane.showMessageDialog(null, "Se agregó un servicio satisfactoriamente...", "SAJA", 
                    JOptionPane.INFORMATION_MESSAGE);
            clearTextField();
            llenarTablaServicios();
        } catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error al guardar el servicio: " + ex);
        }
    }
    
    // Method to update the color in the DB.
    private void editarServicio(){
        try{
            CDServicios cds = new CDServicios();
            CLServicios cls = new CLServicios();
            cls.setIdServicio(Integer.parseInt(this.jTfIdServicio.getText().trim()));
            cls.setNombreServicio(this.jTfNombreServicio.getText().trim());
            if(this.jChkActivo.isSelected()){
                cls.setEstadoServicio(1);
            }else{
                cls.setEstadoServicio(0);
            }
            cds.editarServicio(cls);
            
            JOptionPane.showMessageDialog(null,"Se modificó un servicio satisfactoriamente...", "SAJA", 
                    JOptionPane.INFORMATION_MESSAGE);
            
            clearTextField();
            llenarTablaServicios();
        } catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error al editar el servicio: " + ex);
        }
    }
    
    // Method to delete the color in the DB.
    private void eliminarServicio(){
        try{
            CDServicios cds = new CDServicios();
            CLServicios cls = new CLServicios();
            cls.setIdServicio(Integer.parseInt(this.jTfIdServicio.getText().trim()));
            cds.eliminarServicio(cls);
            
            JOptionPane.showMessageDialog(null, "Se eliminó un servicio satisfactoriamente...", "SAJA", 
                    JOptionPane.INFORMATION_MESSAGE);
            clearTextField();
            llenarTablaServicios();
        } catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error al eliminar el servicio: " + ex);
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
        jTfIdServicio = new javax.swing.JTextField();
        jLblNombre = new javax.swing.JLabel();
        jTfNombreServicio = new javax.swing.JTextField();
        jBtnGuardar = new javax.swing.JButton();
        jBtnEditar = new javax.swing.JButton();
        jBtnEliminar = new javax.swing.JButton();
        jPnlSeparator = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTblServicio = new javax.swing.JTable();
        jPnlBuscar = new javax.swing.JPanel();
        jTfBuscar = new javax.swing.JTextField();
        jBtnBuscar = new javax.swing.JLabel();
        jBtnCancelar = new javax.swing.JLabel();
        jChkActivo = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPnlCancelar.setBackground(new java.awt.Color(255, 255, 255));
        jPnlCancelar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(9, 132, 227));

        jLblTitulo.setFont(new java.awt.Font("HelveticaNowDisplay ExtraBold", 0, 30)); // NOI18N
        jLblTitulo.setForeground(new java.awt.Color(255, 255, 255));
        jLblTitulo.setText("Servicios de la Junta de Agua");

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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 56, Short.MAX_VALUE)
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
                .addGap(15, 15, 15))
        );

        jPnlCancelar.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 820, 60));

        jLblIdentificador.setBackground(new java.awt.Color(255, 255, 255));
        jLblIdentificador.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 1, 24)); // NOI18N
        jLblIdentificador.setForeground(new java.awt.Color(0, 0, 0));
        jLblIdentificador.setText("Identificador");
        jPnlCancelar.add(jLblIdentificador, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 80, 300, 30));

        jTfIdServicio.setEditable(false);
        jTfIdServicio.setBackground(new java.awt.Color(255, 255, 255));
        jTfIdServicio.setFont(new java.awt.Font("HelveticaNowDisplay Regular", 0, 18)); // NOI18N
        jTfIdServicio.setForeground(new java.awt.Color(0, 0, 0));
        jTfIdServicio.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTfIdServicio.setSelectionColor(new java.awt.Color(0, 153, 153));
        jPnlCancelar.add(jTfIdServicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 110, 302, 40));

        jLblNombre.setBackground(new java.awt.Color(255, 255, 255));
        jLblNombre.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 1, 24)); // NOI18N
        jLblNombre.setForeground(new java.awt.Color(0, 0, 0));
        jLblNombre.setText("Nombre");
        jPnlCancelar.add(jLblNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 190, 300, 30));

        jTfNombreServicio.setBackground(new java.awt.Color(255, 255, 255));
        jTfNombreServicio.setFont(new java.awt.Font("HelveticaNowDisplay Regular", 0, 18)); // NOI18N
        jTfNombreServicio.setForeground(new java.awt.Color(0, 0, 0));
        jTfNombreServicio.setToolTipText("Ingrese aqui un servicio");
        jTfNombreServicio.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTfNombreServicio.setSelectionColor(new java.awt.Color(0, 153, 153));
        jPnlCancelar.add(jTfNombreServicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 220, 302, 40));

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

        jTblServicio.setBackground(new java.awt.Color(255, 255, 255));
        jTblServicio.setFont(new java.awt.Font("HelveticaNowDisplay Light", 0, 15)); // NOI18N
        jTblServicio.setForeground(new java.awt.Color(0, 0, 0));
        jTblServicio.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nombre", "Estado"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTblServicio.setRowHeight(30);
        jTblServicio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTblServicioMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTblServicio);

        jPnlCancelar.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 130, 350, 310));

        jPnlBuscar.setBackground(new java.awt.Color(255, 255, 255));
        jPnlBuscar.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTfBuscar.setBackground(new java.awt.Color(255, 255, 255));
        jTfBuscar.setFont(new java.awt.Font("HelveticaNowDisplay Regular", 0, 16)); // NOI18N
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
        jPnlCancelar.add(jBtnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 270, 110, -1));

        jChkActivo.setBackground(new java.awt.Color(255, 255, 255));
        jChkActivo.setFont(new java.awt.Font("HelveticaNowDisplay Light", 0, 14)); // NOI18N
        jChkActivo.setForeground(new java.awt.Color(0, 0, 0));
        jChkActivo.setText("Activo");
        jPnlCancelar.add(jChkActivo, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 340, 70, -1));

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

    private void jBtnCancelarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBtnCancelarMouseEntered
        this.jBtnCancelar.setForeground(celeste);
    }//GEN-LAST:event_jBtnCancelarMouseEntered

    private void jBtnCancelarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBtnCancelarMouseExited
        this.jBtnCancelar.setForeground(azul);
    }//GEN-LAST:event_jBtnCancelarMouseExited

    private void jTblServicioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTblServicioMouseClicked
        this.jBtnCancelar.setVisible(true);
        this.jBtnGuardar.setVisible(false);
        this.jBtnEditar.setVisible(true);
        enabledButtons(true,true,true,true);
        
        selectedRow();
    }//GEN-LAST:event_jTblServicioMouseClicked

    private void jBtnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnGuardarActionPerformed
        if(this.jTfNombreServicio.getText().trim() !=""){
            insertarServicio();
            try {
                findCorrelative();
            } catch (SQLException ex) {
                Logger.getLogger(JFraServicios.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }//GEN-LAST:event_jBtnGuardarActionPerformed

    private void jBtnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnEditarActionPerformed
        this.jBtnCancelar.setVisible(false);
        this.jBtnGuardar.setVisible(true);
        this.jBtnEditar.setVisible(false);
        if(this.jTfNombreServicio.getText()!= ""){
            editarServicio();
            try {
                findCorrelative();
            } catch (SQLException ex) {
                Logger.getLogger(JFraServicios.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        
    }//GEN-LAST:event_jBtnEditarActionPerformed

    private void jBtnCancelarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBtnCancelarMouseClicked
        this.jBtnGuardar.setVisible(true);
        this.jBtnCancelar.setVisible(false);
        this.jBtnEditar.setVisible(false);
        enabledButtons(true,true,false,true);
        try {
            clearTextField();
            findCorrelative();
        } catch (SQLException ex) {
            Logger.getLogger(JFraServicios.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jBtnCancelarMouseClicked

    private void jBtnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnEliminarActionPerformed
        eliminarServicio();
        try {
            findCorrelative();
        } catch (SQLException ex) {
            Logger.getLogger(JFraServicios.class.getName()).log(Level.SEVERE, null, ex);
        }
        enabledButtons(true,true,false,true);
    }//GEN-LAST:event_jBtnEliminarActionPerformed

    private void jTfBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTfBuscarKeyReleased
        try {
            llenarTablaNombreServicio(this.jTfBuscar.getText());
        } catch (SQLException ex) {
            Logger.getLogger(JFraServicios.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jTfBuscarKeyReleased

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
            java.util.logging.Logger.getLogger(JFraServicios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFraServicios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFraServicios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFraServicios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new JFraServicios().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(JFraServicios.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jBtnBuscar;
    private javax.swing.JLabel jBtnCancelar;
    private javax.swing.JButton jBtnEditar;
    private javax.swing.JButton jBtnEliminar;
    private javax.swing.JButton jBtnGuardar;
    private javax.swing.JCheckBox jChkActivo;
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
    private javax.swing.JTable jTblServicio;
    private javax.swing.JTextField jTfBuscar;
    private javax.swing.JTextField jTfIdServicio;
    private javax.swing.JTextField jTfNombreServicio;
    // End of variables declaration//GEN-END:variables
}
