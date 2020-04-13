/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capapresentacion;

import AppPackage.AnimationClass;
import capadatos.CDEgresos;
import capalogica.CLEgresos;
import java.awt.Color;
import java.util.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Manrique
 */
public class JFraEgresos extends javax.swing.JFrame {

    /**
     * Creates new form jFraIngresosEgresos
     */
    public JFraEgresos() throws SQLException {
        initComponents();
        this.setLocationRelativeTo(null);
        llenarTablaEgresosHoy();
        cargarCBOTipoEgreso();
        this.jLblIDEgreso.setVisible(false);
        this.jTfBuscar.setEditable(true);
        this.jBtnEditar.setVisible(false);
        this.jBtnSideBar1.setVisible(false);
        this.jBtnBuscarAño.setVisible(false);
        this.jPnlRangoFecha.setVisible(false);
        Date date = new Date();
        this.jDCFechaEgreso.setDate(date);
        this.jLblCancelarEdicion.setVisible(false);
    }
    AnimationClass sideBar = new AnimationClass();
    Color celeste = new Color(52,152,219);
    Color azul = new Color(52,73,94);
    private boolean estadEditando = false;
    
    // Método para limpiar la tabla de egresos por día
    private void limpiarTabla(){
        DefaultTableModel dtm = (DefaultTableModel) this.jTblEgresosHoy.getModel();
        
        while(dtm.getRowCount() > 0){
            dtm.removeRow(0);
        }
    }
    
    // Método para limpiar la tabla de egresos por mes, año y rango de fechas
    private void limpiarTablaMesAñoRango(){
        DefaultTableModel dtm = (DefaultTableModel) this.jTblEgresos.getModel();
        
        while(dtm.getRowCount() > 0){
            dtm.removeRow(0);
        }
    }
    
    // Método para cargar el Combo Box TipoEgreso
    private void cargarCBOTipoEgreso() throws SQLException{
        
        CDEgresos cde = new CDEgresos();
        
        String[] motivo = new String[cde.cargarTipoEgreso().size()];
        motivo = cde.cargarTipoEgreso().toArray(motivo);
        
        DefaultComboBoxModel modeloTipoEgreso = new DefaultComboBoxModel(motivo);
        this.jCboMotivoEgreso.setModel(modeloTipoEgreso);
        
    }
    
    //Método para llenar la tabla de egresos por día en el formulario
    private void llenarTablaEgresosHoy() throws SQLException{
        limpiarTabla();
        
        CDEgresos cdc = new CDEgresos();
        List<CLEgresos> miLista = cdc.mostrarEgresosHoy();
        DefaultTableModel temp = (DefaultTableModel) this.jTblEgresosHoy.getModel();
        
       for(CLEgresos cle: miLista) {
            Object[] fila = new Object[5];
            fila[0] = cle.getIdControlEgreso();
            fila[1] = cle.getFechaEgreso();
            fila[2] = cle.getValor();
            fila[3] = cle.getObservacion();
            fila[4] = cle.getMotivo();
            temp.addRow(fila);
        }
    }
    
    //Método para llenar la tabla de egresos por mes
    private void llenarTablaEgresosMes(String mes) throws SQLException{
        limpiarTablaMesAñoRango();
        
        CDEgresos cdc = new CDEgresos();
        List<CLEgresos> miLista = cdc.mostrarEgresosMes(mes);
        DefaultTableModel temp = (DefaultTableModel) this.jTblEgresos.getModel();
        
       for(CLEgresos cle: miLista) {
            Object[] fila = new Object[5];
            fila[0] = cle.getIdControlEgreso();
            fila[1] = cle.getFechaEgreso();
            fila[2] = cle.getValor();
            fila[3] = cle.getObservacion();
            fila[4] = cle.getMotivo();
            temp.addRow(fila);
        }
    }
    
    //Método para llenar la tabla de egresos por año
    private void llenarTablaEgresosAños(String año) throws SQLException{
        limpiarTablaMesAñoRango();
        
        CDEgresos cdc = new CDEgresos();
        List<CLEgresos> miLista = cdc.mostrarEgresosAño(año);
        DefaultTableModel temp = (DefaultTableModel) this.jTblEgresos.getModel();
        
       for(CLEgresos cle: miLista) {
            Object[] fila = new Object[5];
            fila[0] = cle.getIdControlEgreso();
            fila[1] = cle.getFechaEgreso();
            fila[2] = cle.getValor();
            fila[3] = cle.getObservacion();
            fila[4] = cle.getMotivo();
            temp.addRow(fila);
        }
    }
    
    //Método para llenar la tabla de egresos por un rango de fechas
    private void llenarTablaEgresosPorRangoFecha(String fecha1, String fecha2) throws SQLException{
        limpiarTablaMesAñoRango();
        
        CDEgresos cdc = new CDEgresos();
        List<CLEgresos> miLista = cdc.mostrarEgresosPorRangoFecha(fecha1, fecha2);
        DefaultTableModel temp = (DefaultTableModel) this.jTblEgresos.getModel();
        
       for(CLEgresos cle: miLista) {
            Object[] fila = new Object[5];
            fila[0] = cle.getIdControlEgreso();
            fila[1] = cle.getFechaEgreso();
            fila[2] = cle.getValor();
            fila[3] = cle.getObservacion();
            fila[4] = cle.getMotivo();
            temp.addRow(fila);
        }
    }
     // Método para seleccionar las filas de la tabla jTblEgresosHoy por día
    private void seleccionarFilas() {
        if (this.jTblEgresosHoy.getSelectedRow() != -1) {
            this.jLblIDEgreso.setText(String.valueOf(this.jTblEgresosHoy.getValueAt(this.jTblEgresosHoy.getSelectedRow(), 0)));
            this.jTfValor.setText(String.valueOf(this.jTblEgresosHoy.getValueAt(this.jTblEgresosHoy.getSelectedRow(), 2)));
            this.jTAObservacion.setText(String.valueOf(this.jTblEgresosHoy.getValueAt(this.jTblEgresosHoy.getSelectedRow(), 3)));
            this.jCboMotivoEgreso.setSelectedItem(String.valueOf(this.jTblEgresosHoy.getValueAt(this.jTblEgresosHoy.getSelectedRow(), 4)));
            this.jLblCancelarEdicion.setVisible(true);
        }
    }
    
    // Método para limpiar las cajas de texto
    private void limpiarCajasDeTexto() {
        this.jCboMotivoEgreso.setSelectedIndex(0);
        this.jTfValor.setText("");
        Date date = new Date();
        this.jDCFechaEgreso.setDate(date);
        this.jTAObservacion.setText("");   
    }
    
    // Metodo para insertar un egreso
    private void insertarEgreso(){
        try{
            CDEgresos cde = new CDEgresos ();
            CLEgresos  cle = new CLEgresos ();
            
            cle.setMotivo(this.jCboMotivoEgreso.getSelectedItem().toString());
            cle.setValor(Float.parseFloat(this.jTfValor.getText().trim()));
            java.util.Date fechaEgreso = jDCFechaEgreso.getDate();
            SimpleDateFormat oDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            cle.setFechaEgreso(oDateFormat.format(fechaEgreso));
            cle.setObservacion(this.jTAObservacion.getText().trim());
            cle.setIdUsuario(2);
            cde.insertarControlEgreso(cle);
            
            JOptionPane.showMessageDialog(null, "Se registró satisfactoriamente...", "SAJA", 
                    JOptionPane.INFORMATION_MESSAGE);
            
        } catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error al insertar el egreso: " + ex);
        }
    }
    
    // Método para modificar un egreso
    private void modificarEgreso(){
        try{
            CDEgresos cde = new CDEgresos ();
            CLEgresos  cle = new CLEgresos ();
            
            cle.setIdControlEgreso(Integer.parseInt(this.jLblIDEgreso.getText()));
            cle.setMotivo(this.jCboMotivoEgreso.getSelectedItem().toString());
            cle.setValor(Float.parseFloat(this.jTfValor.getText().trim()));
            java.util.Date fechaEgreso = jDCFechaEgreso.getDate();
            SimpleDateFormat oDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            cle.setFechaEgreso(oDateFormat.format(fechaEgreso));
            cle.setObservacion(this.jTAObservacion.getText().trim());
            cle.setIdUsuario(2);
            cde.modificarControlEgreso(cle);
            
            JOptionPane.showMessageDialog(null, "Se modificó satisfactoriamente...", "SAJA", 
                    JOptionPane.INFORMATION_MESSAGE);
            
        } catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error al modificar el egreso: " + ex);
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLblTitulo2 = new javax.swing.JLabel();
        jBtnSideBar1 = new javax.swing.JLabel();
        jBtnSideBar = new javax.swing.JLabel();
        jSideBar1 = new javax.swing.JScrollPane();
        jSideBar = new javax.swing.JPanel();
        jSBAMEEgresos = new javax.swing.JPanel();
        jLblIdentificador = new javax.swing.JLabel();
        jLblIdentificador1 = new javax.swing.JLabel();
        jSBListadoEgresos = new javax.swing.JPanel();
        jLblIdentificador2 = new javax.swing.JLabel();
        jLblIdentificador3 = new javax.swing.JLabel();
        jSBContrato4 = new javax.swing.JPanel();
        jLblIdentificador9 = new javax.swing.JLabel();
        jLblIdentificador10 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLblIdentificador5 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jSBAyuda = new javax.swing.JPanel();
        jLblIdentificador4 = new javax.swing.JLabel();
        jLblIdentificador6 = new javax.swing.JLabel();
        jSBReportesEgresos = new javax.swing.JPanel();
        jLblIdentificador7 = new javax.swing.JLabel();
        jLblIdentificador8 = new javax.swing.JLabel();
        jTabContrato = new javax.swing.JTabbedPane();
        jPnlEgresos = new javax.swing.JPanel();
        jLblIdentificador39 = new javax.swing.JLabel();
        jCboMotivoEgreso = new javax.swing.JComboBox<>();
        jLblIdentificador40 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jTfIdentidad4 = new javax.swing.JTextField();
        jTfValor = new javax.swing.JTextField();
        jLblIdentificador41 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTAObservacion = new javax.swing.JTextArea();
        jLblIdentificador42 = new javax.swing.JLabel();
        jDCFechaEgreso = new com.toedter.calendar.JDateChooser();
        jLblIdentificador43 = new javax.swing.JLabel();
        jBtnGuardar = new javax.swing.JButton();
        jBtnEditar = new javax.swing.JButton();
        jPnlSeparatorY = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTblEgresosHoy = new javax.swing.JTable();
        jLblVerEgresos = new javax.swing.JLabel();
        jLblIdentificador45 = new javax.swing.JLabel();
        jLblIDEgreso = new javax.swing.JLabel();
        jLblCancelarEdicion = new javax.swing.JLabel();
        jPnlTablaEgresos = new javax.swing.JPanel();
        jRBAño = new javax.swing.JRadioButton();
        jRBMes = new javax.swing.JRadioButton();
        jRBRangoFecha = new javax.swing.JRadioButton();
        jPnlBuscar = new javax.swing.JPanel();
        jTfBuscar = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTblEgresos = new javax.swing.JTable();
        jLblIdentificador23 = new javax.swing.JLabel();
        jBtnBuscarMes = new javax.swing.JLabel();
        jBtnBuscarAño = new javax.swing.JLabel();
        jLblIdentificador50 = new javax.swing.JLabel();
        jPnlRangoFecha = new javax.swing.JPanel();
        jBtnBuscarRangoFecha = new javax.swing.JLabel();
        jDCFecha1 = new com.toedter.calendar.JDateChooser();
        jDCFecha2 = new com.toedter.calendar.JDateChooser();
        jLblIdentificador46 = new javax.swing.JLabel();
        jLblIdentificador51 = new javax.swing.JLabel();
        jPnlReportes = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jBtnGuardar8 = new javax.swing.JButton();
        jLblIdentificador32 = new javax.swing.JLabel();
        jLblIdentificador33 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jBtnGuardar9 = new javax.swing.JButton();
        jLblIdentificador34 = new javax.swing.JLabel();
        jLblIdentificador35 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jBtnGuardar10 = new javax.swing.JButton();
        jLblIdentificador36 = new javax.swing.JLabel();
        jLblIdentificador37 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jBtnGuardar11 = new javax.swing.JButton();
        jLblIdentificador38 = new javax.swing.JLabel();
        jLblIdentificador47 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jBtnGuardar12 = new javax.swing.JButton();
        jLblIdentificador48 = new javax.swing.JLabel();
        jLblIdentificador49 = new javax.swing.JLabel();
        jPnlAyuda = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jPanel17 = new javax.swing.JPanel();
        jUsuarios = new javax.swing.JPanel();
        jBtnEditar1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jEstadosContrato = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jBtnEditar2 = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jTiposContrato = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jBtnEditar3 = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        jMotivoEgreso = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jBtnEditar4 = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jTipoPlanPago = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jBtnEditar5 = new javax.swing.JButton();
        jServiciosJunta = new javax.swing.JPanel();
        jBtnEditar7 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jSeparatorX1 = new javax.swing.JPanel();
        jLabel29 = new javax.swing.JLabel();
        jSeparatorX2 = new javax.swing.JPanel();
        jLabel30 = new javax.swing.JLabel();
        jSeparatorX3 = new javax.swing.JPanel();
        jLabel31 = new javax.swing.JLabel();
        jSeparatorX4 = new javax.swing.JPanel();
        jLabel32 = new javax.swing.JLabel();
        jSeparatorX5 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.setMinimumSize(new java.awt.Dimension(1000, 700));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(41, 128, 185));
        jPanel3.setPreferredSize(new java.awt.Dimension(800, 60));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLblTitulo2.setFont(new java.awt.Font("HelveticaNowDisplay ExtraBold", 0, 30)); // NOI18N
        jLblTitulo2.setForeground(new java.awt.Color(255, 255, 255));
        jLblTitulo2.setText("Egresos de la Junta de Agua");
        jPanel3.add(jLblTitulo2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 10, -1, 50));

        jBtnSideBar1.setFont(new java.awt.Font("HelveticaNowDisplay ExtraBold", 0, 25)); // NOI18N
        jBtnSideBar1.setForeground(new java.awt.Color(255, 255, 255));
        jBtnSideBar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/x-blanca24.png"))); // NOI18N
        jBtnSideBar1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jBtnSideBar1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jBtnSideBar1MouseClicked(evt);
            }
        });
        jPanel3.add(jBtnSideBar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, -1, 60));

        jBtnSideBar.setFont(new java.awt.Font("HelveticaNowDisplay ExtraBold", 0, 25)); // NOI18N
        jBtnSideBar.setForeground(new java.awt.Color(255, 255, 255));
        jBtnSideBar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/sidebar2-blanco24.png"))); // NOI18N
        jBtnSideBar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jBtnSideBar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jBtnSideBarMouseClicked(evt);
            }
        });
        jPanel3.add(jBtnSideBar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, -1, 60));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1000, 60));

        jSideBar1.setBackground(new java.awt.Color(52, 73, 94));
        jSideBar1.setBorder(null);

        jSideBar.setBackground(new java.awt.Color(52, 73, 94));
        jSideBar.setPreferredSize(new java.awt.Dimension(260, 600));
        jSideBar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jSBAMEEgresos.setBackground(new java.awt.Color(52, 152, 219));
        jSBAMEEgresos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jSBAMEEgresos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jSBAMEEgresosMouseClicked(evt);
            }
        });

        jLblIdentificador.setBackground(new java.awt.Color(102, 102, 102));
        jLblIdentificador.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 1, 20)); // NOI18N
        jLblIdentificador.setForeground(new java.awt.Color(255, 255, 255));
        jLblIdentificador.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/egreso-blanco32.png"))); // NOI18N
        jLblIdentificador.setToolTipText("Contratos");

        jLblIdentificador1.setBackground(new java.awt.Color(102, 102, 102));
        jLblIdentificador1.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 1, 20)); // NOI18N
        jLblIdentificador1.setForeground(new java.awt.Color(255, 255, 255));
        jLblIdentificador1.setText("Registrar egresos");

        javax.swing.GroupLayout jSBAMEEgresosLayout = new javax.swing.GroupLayout(jSBAMEEgresos);
        jSBAMEEgresos.setLayout(jSBAMEEgresosLayout);
        jSBAMEEgresosLayout.setHorizontalGroup(
            jSBAMEEgresosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jSBAMEEgresosLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLblIdentificador1, javax.swing.GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jLblIdentificador, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jSBAMEEgresosLayout.setVerticalGroup(
            jSBAMEEgresosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jSBAMEEgresosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jSBAMEEgresosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLblIdentificador, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
                    .addComponent(jLblIdentificador1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jLblIdentificador.getAccessibleContext().setAccessibleDescription("Egresos");

        jSideBar.add(jSBAMEEgresos, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 225, 260, -1));

        jSBListadoEgresos.setBackground(new java.awt.Color(52, 73, 94));
        jSBListadoEgresos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jSBListadoEgresos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jSBListadoEgresosMouseClicked(evt);
            }
        });

        jLblIdentificador2.setBackground(new java.awt.Color(102, 102, 102));
        jLblIdentificador2.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 1, 20)); // NOI18N
        jLblIdentificador2.setForeground(new java.awt.Color(255, 255, 255));
        jLblIdentificador2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/tabla-blanco32.png"))); // NOI18N
        jLblIdentificador2.setToolTipText("Listado de contratos");

        jLblIdentificador3.setBackground(new java.awt.Color(102, 102, 102));
        jLblIdentificador3.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 1, 20)); // NOI18N
        jLblIdentificador3.setForeground(new java.awt.Color(255, 255, 255));
        jLblIdentificador3.setText("Listado egresos");

        javax.swing.GroupLayout jSBListadoEgresosLayout = new javax.swing.GroupLayout(jSBListadoEgresos);
        jSBListadoEgresos.setLayout(jSBListadoEgresosLayout);
        jSBListadoEgresosLayout.setHorizontalGroup(
            jSBListadoEgresosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jSBListadoEgresosLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLblIdentificador3, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLblIdentificador2)
                .addGap(10, 10, 10))
        );
        jSBListadoEgresosLayout.setVerticalGroup(
            jSBListadoEgresosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jSBListadoEgresosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jSBListadoEgresosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLblIdentificador2, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
                    .addComponent(jLblIdentificador3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jLblIdentificador2.getAccessibleContext().setAccessibleDescription("Listado de egresos");

        jSideBar.add(jSBListadoEgresos, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 299, 260, -1));

        jSBContrato4.setBackground(new java.awt.Color(52, 73, 94));
        jSBContrato4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jLblIdentificador9.setBackground(new java.awt.Color(102, 102, 102));
        jLblIdentificador9.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 1, 20)); // NOI18N
        jLblIdentificador9.setForeground(new java.awt.Color(255, 255, 255));
        jLblIdentificador9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/menu-blanco32.png"))); // NOI18N
        jLblIdentificador9.setToolTipText("Menú");
        jLblIdentificador9.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jLblIdentificador10.setBackground(new java.awt.Color(102, 102, 102));
        jLblIdentificador10.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 1, 20)); // NOI18N
        jLblIdentificador10.setForeground(new java.awt.Color(255, 255, 255));
        jLblIdentificador10.setText("Menú");

        javax.swing.GroupLayout jSBContrato4Layout = new javax.swing.GroupLayout(jSBContrato4);
        jSBContrato4.setLayout(jSBContrato4Layout);
        jSBContrato4Layout.setHorizontalGroup(
            jSBContrato4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jSBContrato4Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLblIdentificador10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLblIdentificador9)
                .addContainerGap())
        );
        jSBContrato4Layout.setVerticalGroup(
            jSBContrato4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jSBContrato4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jSBContrato4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLblIdentificador9, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
                    .addComponent(jLblIdentificador10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jSideBar.add(jSBContrato4, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 132, 260, -1));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setPreferredSize(new java.awt.Dimension(0, 1));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1, Short.MAX_VALUE)
        );

        jSideBar.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 113, 255, -1));

        jLblIdentificador5.setBackground(new java.awt.Color(102, 102, 102));
        jLblIdentificador5.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 0, 12)); // NOI18N
        jLblIdentificador5.setForeground(new java.awt.Color(255, 255, 255));
        jLblIdentificador5.setText("SAJA");
        jLblIdentificador5.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jSideBar.add(jLblIdentificador5, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 78, 248, -1));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setPreferredSize(new java.awt.Dimension(0, 1));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1, Short.MAX_VALUE)
        );

        jSideBar.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 206, 255, -1));

        jSBAyuda.setBackground(new java.awt.Color(52, 73, 94));
        jSBAyuda.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jSBAyuda.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jSBAyudaMouseClicked(evt);
            }
        });

        jLblIdentificador4.setBackground(new java.awt.Color(102, 102, 102));
        jLblIdentificador4.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 1, 20)); // NOI18N
        jLblIdentificador4.setForeground(new java.awt.Color(255, 255, 255));
        jLblIdentificador4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/pregunta-blanco32.png"))); // NOI18N
        jLblIdentificador4.setToolTipText("¿Qué se debo hacer aquí?");

        jLblIdentificador6.setBackground(new java.awt.Color(102, 102, 102));
        jLblIdentificador6.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 1, 20)); // NOI18N
        jLblIdentificador6.setForeground(new java.awt.Color(255, 255, 255));
        jLblIdentificador6.setText("Ayuda");

        javax.swing.GroupLayout jSBAyudaLayout = new javax.swing.GroupLayout(jSBAyuda);
        jSBAyuda.setLayout(jSBAyudaLayout);
        jSBAyudaLayout.setHorizontalGroup(
            jSBAyudaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jSBAyudaLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLblIdentificador6, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLblIdentificador4)
                .addGap(16, 16, 16))
        );
        jSBAyudaLayout.setVerticalGroup(
            jSBAyudaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jSBAyudaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jSBAyudaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLblIdentificador4, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
                    .addComponent(jLblIdentificador6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jSideBar.add(jSBAyuda, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 560, 260, -1));
        jSBAyuda.getAccessibleContext().setAccessibleDescription("Ayuda");

        jSBReportesEgresos.setBackground(new java.awt.Color(52, 73, 94));
        jSBReportesEgresos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jSBReportesEgresos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jSBReportesEgresosMouseClicked(evt);
            }
        });

        jLblIdentificador7.setBackground(new java.awt.Color(102, 102, 102));
        jLblIdentificador7.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 1, 20)); // NOI18N
        jLblIdentificador7.setForeground(new java.awt.Color(255, 255, 255));
        jLblIdentificador7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/reportes-blanco32.png"))); // NOI18N
        jLblIdentificador7.setToolTipText("Listado de contratos");

        jLblIdentificador8.setBackground(new java.awt.Color(102, 102, 102));
        jLblIdentificador8.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 1, 20)); // NOI18N
        jLblIdentificador8.setForeground(new java.awt.Color(255, 255, 255));
        jLblIdentificador8.setText("Reportes egresos");

        javax.swing.GroupLayout jSBReportesEgresosLayout = new javax.swing.GroupLayout(jSBReportesEgresos);
        jSBReportesEgresos.setLayout(jSBReportesEgresosLayout);
        jSBReportesEgresosLayout.setHorizontalGroup(
            jSBReportesEgresosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jSBReportesEgresosLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLblIdentificador8, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                .addComponent(jLblIdentificador7)
                .addContainerGap())
        );
        jSBReportesEgresosLayout.setVerticalGroup(
            jSBReportesEgresosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jSBReportesEgresosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jSBReportesEgresosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLblIdentificador7, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
                    .addComponent(jLblIdentificador8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jSideBar.add(jSBReportesEgresos, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 370, 260, -1));

        jSideBar1.setViewportView(jSideBar);

        jPanel1.add(jSideBar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(-210, 0, 270, 625));

        jTabContrato.setBackground(new java.awt.Color(255, 255, 255));
        jTabContrato.setTabPlacement(javax.swing.JTabbedPane.RIGHT);

        jPnlEgresos.setBackground(new java.awt.Color(255, 255, 255));

        jLblIdentificador39.setBackground(new java.awt.Color(255, 255, 255));
        jLblIdentificador39.setFont(new java.awt.Font("HelveticaNowDisplay Medium", 0, 20)); // NOI18N
        jLblIdentificador39.setForeground(new java.awt.Color(0, 0, 0));
        jLblIdentificador39.setText("Fecha del egreso");

        jCboMotivoEgreso.setBackground(new java.awt.Color(255, 255, 255));
        jCboMotivoEgreso.setFont(new java.awt.Font("HelveticaNowDisplay Regular", 0, 16)); // NOI18N
        jCboMotivoEgreso.setForeground(new java.awt.Color(0, 0, 0));

        jLblIdentificador40.setBackground(new java.awt.Color(255, 255, 255));
        jLblIdentificador40.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 1, 20)); // NOI18N
        jLblIdentificador40.setForeground(new java.awt.Color(0, 0, 0));
        jLblIdentificador40.setText("Egresos registrados hoy");

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTfIdentidad4.setBackground(new java.awt.Color(255, 255, 255));
        jTfIdentidad4.setFont(new java.awt.Font("HelveticaNowDisplay Regular", 0, 18)); // NOI18N
        jTfIdentidad4.setForeground(new java.awt.Color(0, 0, 0));
        jTfIdentidad4.setText("L.");
        jTfIdentidad4.setBorder(null);
        jTfIdentidad4.setSelectionColor(new java.awt.Color(0, 153, 153));

        jTfValor.setBackground(new java.awt.Color(255, 255, 255));
        jTfValor.setFont(new java.awt.Font("HelveticaNowDisplay Regular", 0, 16)); // NOI18N
        jTfValor.setForeground(new java.awt.Color(0, 0, 0));
        jTfValor.setBorder(null);
        jTfValor.setSelectionColor(new java.awt.Color(0, 153, 153));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTfIdentidad4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                    .addContainerGap(27, Short.MAX_VALUE)
                    .addComponent(jTfValor, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap()))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTfIdentidad4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jTfValor, javax.swing.GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE))
        );

        jLblIdentificador41.setBackground(new java.awt.Color(255, 255, 255));
        jLblIdentificador41.setFont(new java.awt.Font("HelveticaNowDisplay Medium", 0, 20)); // NOI18N
        jLblIdentificador41.setForeground(new java.awt.Color(0, 0, 0));
        jLblIdentificador41.setText("Observación");

        jTAObservacion.setBackground(new java.awt.Color(255, 255, 255));
        jTAObservacion.setColumns(20);
        jTAObservacion.setFont(new java.awt.Font("HelveticaNowDisplay Regular", 0, 16)); // NOI18N
        jTAObservacion.setForeground(new java.awt.Color(0, 0, 0));
        jTAObservacion.setRows(5);
        jScrollPane1.setViewportView(jTAObservacion);

        jLblIdentificador42.setBackground(new java.awt.Color(255, 255, 255));
        jLblIdentificador42.setFont(new java.awt.Font("HelveticaNowDisplay Medium", 0, 20)); // NOI18N
        jLblIdentificador42.setForeground(new java.awt.Color(0, 0, 0));
        jLblIdentificador42.setText("Valor del egreso");

        jDCFechaEgreso.setBackground(new java.awt.Color(255, 255, 255));
        jDCFechaEgreso.setForeground(new java.awt.Color(0, 0, 0));
        jDCFechaEgreso.setFont(new java.awt.Font("HelveticaNowDisplay Regular", 0, 14)); // NOI18N

        jLblIdentificador43.setBackground(new java.awt.Color(41, 128, 185));
        jLblIdentificador43.setFont(new java.awt.Font("HelveticaNowDisplay Regular", 0, 14)); // NOI18N
        jLblIdentificador43.setForeground(new java.awt.Color(41, 128, 185));
        jLblIdentificador43.setText("Hoy");
        jLblIdentificador43.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

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

        javax.swing.GroupLayout jPnlSeparatorYLayout = new javax.swing.GroupLayout(jPnlSeparatorY);
        jPnlSeparatorY.setLayout(jPnlSeparatorYLayout);
        jPnlSeparatorYLayout.setHorizontalGroup(
            jPnlSeparatorYLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1, Short.MAX_VALUE)
        );
        jPnlSeparatorYLayout.setVerticalGroup(
            jPnlSeparatorYLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jTblEgresosHoy.setFont(new java.awt.Font("HelveticaNowDisplay Regular", 0, 14)); // NOI18N
        jTblEgresosHoy.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id Egreso", "Fecha egreso", "Valor del egreso", "Observación", "Tipo egreso"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTblEgresosHoy.setRowHeight(30);
        jTblEgresosHoy.getTableHeader().setReorderingAllowed(false);
        jTblEgresosHoy.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTblEgresosHoyMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTblEgresosHoy);

        jLblVerEgresos.setBackground(new java.awt.Color(41, 128, 185));
        jLblVerEgresos.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 1, 18)); // NOI18N
        jLblVerEgresos.setForeground(new java.awt.Color(41, 128, 185));
        jLblVerEgresos.setText("Ver todos los egresos");
        jLblVerEgresos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLblVerEgresos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLblVerEgresosMouseClicked(evt);
            }
        });

        jLblIdentificador45.setBackground(new java.awt.Color(255, 255, 255));
        jLblIdentificador45.setFont(new java.awt.Font("HelveticaNowDisplay Medium", 0, 20)); // NOI18N
        jLblIdentificador45.setForeground(new java.awt.Color(0, 0, 0));
        jLblIdentificador45.setText("Tipo de egreso");

        jLblCancelarEdicion.setBackground(new java.awt.Color(41, 128, 185));
        jLblCancelarEdicion.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 1, 18)); // NOI18N
        jLblCancelarEdicion.setForeground(new java.awt.Color(41, 128, 185));
        jLblCancelarEdicion.setText("Cancelar edición");
        jLblCancelarEdicion.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLblCancelarEdicion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLblCancelarEdicionMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPnlEgresosLayout = new javax.swing.GroupLayout(jPnlEgresos);
        jPnlEgresos.setLayout(jPnlEgresosLayout);
        jPnlEgresosLayout.setHorizontalGroup(
            jPnlEgresosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPnlEgresosLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jPnlEgresosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jDCFechaEgreso, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jCboMotivoEgreso, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLblIdentificador39, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLblIdentificador41, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLblIdentificador42, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPnlEgresosLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLblIdentificador43))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPnlEgresosLayout.createSequentialGroup()
                        .addComponent(jBtnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                        .addComponent(jBtnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLblIdentificador45, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(45, 45, 45)
                .addComponent(jPnlSeparatorY, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44)
                .addGroup(jPnlEgresosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPnlEgresosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 491, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPnlEgresosLayout.createSequentialGroup()
                            .addComponent(jLblCancelarEdicion)
                            .addGap(109, 109, 109)
                            .addComponent(jLblIDEgreso, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLblVerEgresos)))
                    .addComponent(jLblIdentificador40, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 491, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(78, 78, 78))
        );
        jPnlEgresosLayout.setVerticalGroup(
            jPnlEgresosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPnlEgresosLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPnlEgresosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPnlEgresosLayout.createSequentialGroup()
                        .addComponent(jLblIdentificador45, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCboMotivoEgreso, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLblIdentificador42, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLblIdentificador39, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1)
                        .addComponent(jLblIdentificador43)
                        .addGap(4, 4, 4)
                        .addComponent(jDCFechaEgreso, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLblIdentificador41, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPnlEgresosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jBtnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jBtnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jPnlSeparatorY, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPnlEgresosLayout.createSequentialGroup()
                        .addGroup(jPnlEgresosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPnlEgresosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLblVerEgresos)
                                .addComponent(jLblIDEgreso, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPnlEgresosLayout.createSequentialGroup()
                                .addComponent(jLblIdentificador40, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLblCancelarEdicion)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 434, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(39, Short.MAX_VALUE))
        );

        jTabContrato.addTab("tab1", jPnlEgresos);

        jPnlTablaEgresos.setBackground(new java.awt.Color(255, 255, 255));
        jPnlTablaEgresos.setLayout(null);

        jRBAño.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(jRBAño);
        jRBAño.setFont(new java.awt.Font("HelveticaNowDisplay Light", 0, 14)); // NOI18N
        jRBAño.setForeground(new java.awt.Color(0, 0, 0));
        jRBAño.setText("Año");
        jRBAño.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRBAñoActionPerformed(evt);
            }
        });
        jPnlTablaEgresos.add(jRBAño);
        jRBAño.setBounds(300, 40, 56, 40);

        jRBMes.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(jRBMes);
        jRBMes.setFont(new java.awt.Font("HelveticaNowDisplay Light", 0, 14)); // NOI18N
        jRBMes.setForeground(new java.awt.Color(0, 0, 0));
        jRBMes.setSelected(true);
        jRBMes.setText("Mes");
        jRBMes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRBMesActionPerformed(evt);
            }
        });
        jPnlTablaEgresos.add(jRBMes);
        jRBMes.setBounds(210, 40, 59, 40);

        jRBRangoFecha.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(jRBRangoFecha);
        jRBRangoFecha.setFont(new java.awt.Font("HelveticaNowDisplay Light", 0, 14)); // NOI18N
        jRBRangoFecha.setForeground(new java.awt.Color(0, 0, 0));
        jRBRangoFecha.setText("Rango de fecha");
        jRBRangoFecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRBRangoFechaActionPerformed(evt);
            }
        });
        jPnlTablaEgresos.add(jRBRangoFecha);
        jRBRangoFecha.setBounds(380, 40, 126, 40);

        jPnlBuscar.setBackground(new java.awt.Color(255, 255, 255));
        jPnlBuscar.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTfBuscar.setFont(new java.awt.Font("HelveticaNowDisplay Regular", 0, 18)); // NOI18N
        jTfBuscar.setBorder(null);
        jTfBuscar.setSelectionColor(new java.awt.Color(0, 153, 153));

        javax.swing.GroupLayout jPnlBuscarLayout = new javax.swing.GroupLayout(jPnlBuscar);
        jPnlBuscar.setLayout(jPnlBuscarLayout);
        jPnlBuscarLayout.setHorizontalGroup(
            jPnlBuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPnlBuscarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTfBuscar, javax.swing.GroupLayout.DEFAULT_SIZE, 246, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPnlBuscarLayout.setVerticalGroup(
            jPnlBuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTfBuscar, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
        );

        jPnlTablaEgresos.add(jPnlBuscar);
        jPnlBuscar.setBounds(550, 30, 270, 40);

        jTblEgresos.setBackground(new java.awt.Color(255, 255, 255));
        jTblEgresos.setForeground(new java.awt.Color(0, 0, 0));
        jTblEgresos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id Egreso", "Fecha egreso", "Valor", "Observación", "Motivo"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTblEgresos.getTableHeader().setReorderingAllowed(false);
        jScrollPane3.setViewportView(jTblEgresos);

        jPnlTablaEgresos.add(jScrollPane3);
        jScrollPane3.setBounds(35, 120, 870, 408);

        jLblIdentificador23.setBackground(new java.awt.Color(102, 102, 102));
        jLblIdentificador23.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 1, 18)); // NOI18N
        jLblIdentificador23.setForeground(new java.awt.Color(41, 128, 185));
        jLblIdentificador23.setText("Generar reporte de egresos");
        jLblIdentificador23.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLblIdentificador23.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLblIdentificador23MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLblIdentificador23MouseExited(evt);
            }
        });
        jPnlTablaEgresos.add(jLblIdentificador23);
        jLblIdentificador23.setBounds(663, 87, 228, 27);

        jBtnBuscarMes.setBackground(new java.awt.Color(9, 132, 227));
        jBtnBuscarMes.setForeground(new java.awt.Color(9, 132, 227));
        jBtnBuscarMes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/search-black24.png"))); // NOI18N
        jBtnBuscarMes.setToolTipText("Click para buscar");
        jBtnBuscarMes.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jBtnBuscarMes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jBtnBuscarMesMouseClicked(evt);
            }
        });
        jPnlTablaEgresos.add(jBtnBuscarMes);
        jBtnBuscarMes.setBounds(820, 30, 34, 36);

        jBtnBuscarAño.setBackground(new java.awt.Color(9, 132, 227));
        jBtnBuscarAño.setForeground(new java.awt.Color(9, 132, 227));
        jBtnBuscarAño.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/search-black24.png"))); // NOI18N
        jBtnBuscarAño.setToolTipText("Click para buscar");
        jBtnBuscarAño.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jBtnBuscarAño.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jBtnBuscarAñoMouseClicked(evt);
            }
        });
        jPnlTablaEgresos.add(jBtnBuscarAño);
        jBtnBuscarAño.setBounds(820, 30, 34, 36);

        jLblIdentificador50.setBackground(new java.awt.Color(255, 255, 255));
        jLblIdentificador50.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 1, 20)); // NOI18N
        jLblIdentificador50.setForeground(new java.awt.Color(0, 0, 0));
        jLblIdentificador50.setText("Ver egresos por:");
        jPnlTablaEgresos.add(jLblIdentificador50);
        jLblIdentificador50.setBounds(40, 50, 151, 24);

        jPnlRangoFecha.setBackground(new java.awt.Color(255, 255, 255));
        jPnlRangoFecha.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jBtnBuscarRangoFecha.setBackground(new java.awt.Color(9, 132, 227));
        jBtnBuscarRangoFecha.setForeground(new java.awt.Color(9, 132, 227));
        jBtnBuscarRangoFecha.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/search-black24.png"))); // NOI18N
        jBtnBuscarRangoFecha.setToolTipText("Click para buscar");
        jBtnBuscarRangoFecha.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jBtnBuscarRangoFecha.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jBtnBuscarRangoFechaMouseClicked(evt);
            }
        });

        jDCFecha1.setBackground(new java.awt.Color(255, 255, 255));
        jDCFecha1.setForeground(new java.awt.Color(0, 0, 0));
        jDCFecha1.setDateFormatString("yyyy-MM-dd");
        jDCFecha1.setFont(new java.awt.Font("HelveticaNowDisplay Regular", 0, 14)); // NOI18N

        jDCFecha2.setBackground(new java.awt.Color(255, 255, 255));
        jDCFecha2.setForeground(new java.awt.Color(0, 0, 0));
        jDCFecha2.setDateFormatString("yyyy-MM-dd");
        jDCFecha2.setFont(new java.awt.Font("HelveticaNowDisplay Regular", 0, 14)); // NOI18N

        jLblIdentificador46.setBackground(new java.awt.Color(102, 102, 102));
        jLblIdentificador46.setFont(new java.awt.Font("HelveticaNowDisplay Regular", 0, 14)); // NOI18N
        jLblIdentificador46.setText("Fecha 1");

        jLblIdentificador51.setBackground(new java.awt.Color(102, 102, 102));
        jLblIdentificador51.setFont(new java.awt.Font("HelveticaNowDisplay Regular", 0, 14)); // NOI18N
        jLblIdentificador51.setText("Fecha 2");

        javax.swing.GroupLayout jPnlRangoFechaLayout = new javax.swing.GroupLayout(jPnlRangoFecha);
        jPnlRangoFecha.setLayout(jPnlRangoFechaLayout);
        jPnlRangoFechaLayout.setHorizontalGroup(
            jPnlRangoFechaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPnlRangoFechaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPnlRangoFechaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jDCFecha1, javax.swing.GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE)
                    .addGroup(jPnlRangoFechaLayout.createSequentialGroup()
                        .addComponent(jLblIdentificador46, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPnlRangoFechaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jDCFecha2, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLblIdentificador51, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBtnBuscarRangoFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6))
        );
        jPnlRangoFechaLayout.setVerticalGroup(
            jPnlRangoFechaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPnlRangoFechaLayout.createSequentialGroup()
                .addGroup(jPnlRangoFechaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPnlRangoFechaLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jBtnBuscarRangoFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPnlRangoFechaLayout.createSequentialGroup()
                        .addGroup(jPnlRangoFechaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLblIdentificador46, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLblIdentificador51, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPnlRangoFechaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPnlRangoFechaLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jDCFecha1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPnlRangoFechaLayout.createSequentialGroup()
                                .addComponent(jDCFecha2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );

        jPnlTablaEgresos.add(jPnlRangoFecha);
        jPnlRangoFecha.setBounds(550, 20, 338, 69);

        jTabContrato.addTab("tab2", jPnlTablaEgresos);

        jPnlReportes.setBackground(new java.awt.Color(255, 255, 255));

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jBtnGuardar8.setBackground(new java.awt.Color(41, 128, 185));
        jBtnGuardar8.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 0, 16)); // NOI18N
        jBtnGuardar8.setForeground(new java.awt.Color(255, 255, 255));
        jBtnGuardar8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/generarreporte-blanco24.png"))); // NOI18N
        jBtnGuardar8.setText("GENERAR REPORTE");
        jBtnGuardar8.setBorder(null);
        jBtnGuardar8.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jLblIdentificador32.setBackground(new java.awt.Color(255, 255, 255));
        jLblIdentificador32.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 1, 20)); // NOI18N
        jLblIdentificador32.setForeground(new java.awt.Color(0, 0, 0));
        jLblIdentificador32.setText("Reporte de egresos");

        jLblIdentificador33.setBackground(new java.awt.Color(255, 255, 255));
        jLblIdentificador33.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 1, 20)); // NOI18N
        jLblIdentificador33.setForeground(new java.awt.Color(0, 0, 0));
        jLblIdentificador33.setText("del mes");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jBtnGuardar8, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLblIdentificador32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLblIdentificador33, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLblIdentificador32, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLblIdentificador33, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jBtnGuardar8, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jBtnGuardar9.setBackground(new java.awt.Color(41, 128, 185));
        jBtnGuardar9.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 0, 16)); // NOI18N
        jBtnGuardar9.setForeground(new java.awt.Color(255, 255, 255));
        jBtnGuardar9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/generarreporte-blanco24.png"))); // NOI18N
        jBtnGuardar9.setText("GENERAR REPORTE");
        jBtnGuardar9.setBorder(null);
        jBtnGuardar9.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jLblIdentificador34.setBackground(new java.awt.Color(255, 255, 255));
        jLblIdentificador34.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 1, 20)); // NOI18N
        jLblIdentificador34.setForeground(new java.awt.Color(0, 0, 0));
        jLblIdentificador34.setText("Reporte de egresos");

        jLblIdentificador35.setBackground(new java.awt.Color(255, 255, 255));
        jLblIdentificador35.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 1, 20)); // NOI18N
        jLblIdentificador35.setForeground(new java.awt.Color(0, 0, 0));
        jLblIdentificador35.setText("de los ultimos tres meses");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jBtnGuardar9, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLblIdentificador34, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLblIdentificador35, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLblIdentificador34, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLblIdentificador35, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jBtnGuardar9, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jBtnGuardar10.setBackground(new java.awt.Color(41, 128, 185));
        jBtnGuardar10.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 0, 16)); // NOI18N
        jBtnGuardar10.setForeground(new java.awt.Color(255, 255, 255));
        jBtnGuardar10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/generarreporte-blanco24.png"))); // NOI18N
        jBtnGuardar10.setText("GENERAR REPORTE");
        jBtnGuardar10.setBorder(null);
        jBtnGuardar10.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jLblIdentificador36.setBackground(new java.awt.Color(255, 255, 255));
        jLblIdentificador36.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 1, 20)); // NOI18N
        jLblIdentificador36.setForeground(new java.awt.Color(0, 0, 0));
        jLblIdentificador36.setText("Reporte de egresos");

        jLblIdentificador37.setBackground(new java.awt.Color(255, 255, 255));
        jLblIdentificador37.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 1, 20)); // NOI18N
        jLblIdentificador37.setForeground(new java.awt.Color(0, 0, 0));
        jLblIdentificador37.setText("del año");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                        .addGap(0, 45, Short.MAX_VALUE)
                        .addComponent(jBtnGuardar10, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLblIdentificador36, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLblIdentificador37, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLblIdentificador36, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLblIdentificador37, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 126, Short.MAX_VALUE)
                .addComponent(jBtnGuardar10, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));
        jPanel10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jBtnGuardar11.setBackground(new java.awt.Color(41, 128, 185));
        jBtnGuardar11.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 0, 16)); // NOI18N
        jBtnGuardar11.setForeground(new java.awt.Color(255, 255, 255));
        jBtnGuardar11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/generarreporte-blanco24.png"))); // NOI18N
        jBtnGuardar11.setText("GENERAR REPORTE");
        jBtnGuardar11.setBorder(null);
        jBtnGuardar11.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jLblIdentificador38.setBackground(new java.awt.Color(255, 255, 255));
        jLblIdentificador38.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 1, 20)); // NOI18N
        jLblIdentificador38.setForeground(new java.awt.Color(0, 0, 0));
        jLblIdentificador38.setText("Reporte de egresos");

        jLblIdentificador47.setBackground(new java.awt.Color(255, 255, 255));
        jLblIdentificador47.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 1, 20)); // NOI18N
        jLblIdentificador47.setForeground(new java.awt.Color(0, 0, 0));
        jLblIdentificador47.setText("por rango de fecha");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                        .addGap(0, 45, Short.MAX_VALUE)
                        .addComponent(jBtnGuardar11, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLblIdentificador38, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLblIdentificador47, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLblIdentificador38, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLblIdentificador47, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 123, Short.MAX_VALUE)
                .addComponent(jBtnGuardar11, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));
        jPanel11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jBtnGuardar12.setBackground(new java.awt.Color(41, 128, 185));
        jBtnGuardar12.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 0, 16)); // NOI18N
        jBtnGuardar12.setForeground(new java.awt.Color(255, 255, 255));
        jBtnGuardar12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/generarreporte-blanco24.png"))); // NOI18N
        jBtnGuardar12.setText("GENERAR REPORTE");
        jBtnGuardar12.setBorder(null);
        jBtnGuardar12.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jLblIdentificador48.setBackground(new java.awt.Color(255, 255, 255));
        jLblIdentificador48.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 1, 20)); // NOI18N
        jLblIdentificador48.setForeground(new java.awt.Color(0, 0, 0));
        jLblIdentificador48.setText("Reporte de egresos");

        jLblIdentificador49.setBackground(new java.awt.Color(255, 255, 255));
        jLblIdentificador49.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 1, 20)); // NOI18N
        jLblIdentificador49.setForeground(new java.awt.Color(0, 0, 0));
        jLblIdentificador49.setText("del mes");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                        .addGap(0, 45, Short.MAX_VALUE)
                        .addComponent(jBtnGuardar12, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLblIdentificador48, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLblIdentificador49, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLblIdentificador48, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLblIdentificador49, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 123, Short.MAX_VALUE)
                .addComponent(jBtnGuardar12, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPnlReportesLayout = new javax.swing.GroupLayout(jPnlReportes);
        jPnlReportes.setLayout(jPnlReportesLayout);
        jPnlReportesLayout.setHorizontalGroup(
            jPnlReportesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPnlReportesLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(jPnlReportesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addGroup(jPnlReportesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(108, Short.MAX_VALUE))
        );
        jPnlReportesLayout.setVerticalGroup(
            jPnlReportesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPnlReportesLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPnlReportesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPnlReportesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(35, 35, 35))
        );

        jTabContrato.addTab("tab3", jPnlReportes);

        jPnlAyuda.setBackground(new java.awt.Color(255, 255, 255));

        jScrollPane4.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane4.setBorder(null);
        jScrollPane4.setHorizontalScrollBar(null);

        jPanel17.setBackground(new java.awt.Color(255, 255, 255));

        jUsuarios.setBackground(new java.awt.Color(255, 255, 255));
        jUsuarios.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jBtnEditar1.setBackground(new java.awt.Color(41, 128, 185));
        jBtnEditar1.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 0, 16)); // NOI18N
        jBtnEditar1.setForeground(new java.awt.Color(255, 255, 255));
        jBtnEditar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/user-blanco24.png"))); // NOI18N
        jBtnEditar1.setText("USUARIOS");
        jBtnEditar1.setBorder(null);
        jBtnEditar1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setFont(new java.awt.Font("HelveticaNowDisplay Regular", 0, 14)); // NOI18N
        jLabel2.setText("Añada, modifique o desactive");

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("HelveticaNowDisplay Regular", 0, 14)); // NOI18N
        jLabel3.setText("usarios que estan utilizando");

        jLabel4.setBackground(new java.awt.Color(255, 255, 255));
        jLabel4.setFont(new java.awt.Font("HelveticaNowDisplay Regular", 0, 14)); // NOI18N
        jLabel4.setText("el Sistema Administrador de ");

        jLabel5.setBackground(new java.awt.Color(255, 255, 255));
        jLabel5.setFont(new java.awt.Font("HelveticaNowDisplay Regular", 0, 14)); // NOI18N
        jLabel5.setText("Juntas de Agua");

        jLabel28.setBackground(new java.awt.Color(255, 255, 255));
        jLabel28.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 0, 14)); // NOI18N
        jLabel28.setText("USARIOS");

        javax.swing.GroupLayout jUsuariosLayout = new javax.swing.GroupLayout(jUsuarios);
        jUsuarios.setLayout(jUsuariosLayout);
        jUsuariosLayout.setHorizontalGroup(
            jUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jUsuariosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jUsuariosLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jBtnEditar1, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jUsuariosLayout.setVerticalGroup(
            jUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jUsuariosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel28)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                .addComponent(jBtnEditar1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jEstadosContrato.setBackground(new java.awt.Color(255, 255, 255));
        jEstadosContrato.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel7.setBackground(new java.awt.Color(255, 255, 255));
        jLabel7.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 0, 14)); // NOI18N
        jLabel7.setText("ESTADOS DEL CONTRATO");

        jBtnEditar2.setBackground(new java.awt.Color(41, 128, 185));
        jBtnEditar2.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 0, 16)); // NOI18N
        jBtnEditar2.setForeground(new java.awt.Color(255, 255, 255));
        jBtnEditar2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/contrato-blanco24.png"))); // NOI18N
        jBtnEditar2.setText("ESTADO");
        jBtnEditar2.setBorder(null);
        jBtnEditar2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jLabel11.setBackground(new java.awt.Color(255, 255, 255));
        jLabel11.setFont(new java.awt.Font("HelveticaNowDisplay Regular", 0, 14)); // NOI18N
        jLabel11.setText("contrato");

        jLabel8.setBackground(new java.awt.Color(255, 255, 255));
        jLabel8.setFont(new java.awt.Font("HelveticaNowDisplay Regular", 0, 14)); // NOI18N
        jLabel8.setText("Añada, modifique o elimine");

        jLabel10.setBackground(new java.awt.Color(255, 255, 255));
        jLabel10.setFont(new java.awt.Font("HelveticaNowDisplay Regular", 0, 14)); // NOI18N
        jLabel10.setText("que puede permanecer un");

        jLabel9.setBackground(new java.awt.Color(255, 255, 255));
        jLabel9.setFont(new java.awt.Font("HelveticaNowDisplay Regular", 0, 14)); // NOI18N
        jLabel9.setText("los estados posibles en");

        javax.swing.GroupLayout jEstadosContratoLayout = new javax.swing.GroupLayout(jEstadosContrato);
        jEstadosContrato.setLayout(jEstadosContratoLayout);
        jEstadosContratoLayout.setHorizontalGroup(
            jEstadosContratoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jEstadosContratoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jEstadosContratoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jEstadosContratoLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jBtnEditar2, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18))
        );
        jEstadosContratoLayout.setVerticalGroup(
            jEstadosContratoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jEstadosContratoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                .addComponent(jBtnEditar2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTiposContrato.setBackground(new java.awt.Color(255, 255, 255));
        jTiposContrato.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel13.setBackground(new java.awt.Color(255, 255, 255));
        jLabel13.setFont(new java.awt.Font("HelveticaNowDisplay Regular", 0, 14)); // NOI18N
        jLabel13.setText("que ofrece la Junta ");

        jLabel12.setBackground(new java.awt.Color(255, 255, 255));
        jLabel12.setFont(new java.awt.Font("HelveticaNowDisplay Regular", 0, 14)); // NOI18N
        jLabel12.setText("Administradora de Agua");

        jLabel14.setBackground(new java.awt.Color(255, 255, 255));
        jLabel14.setFont(new java.awt.Font("HelveticaNowDisplay Regular", 0, 14)); // NOI18N
        jLabel14.setText("los tipos de contratos");

        jLabel16.setBackground(new java.awt.Color(255, 255, 255));
        jLabel16.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 0, 14)); // NOI18N
        jLabel16.setText("TIPOS DE CONTRATO");

        jBtnEditar3.setBackground(new java.awt.Color(41, 128, 185));
        jBtnEditar3.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 0, 16)); // NOI18N
        jBtnEditar3.setForeground(new java.awt.Color(255, 255, 255));
        jBtnEditar3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/contrato-blanco24.png"))); // NOI18N
        jBtnEditar3.setText("TIPO");
        jBtnEditar3.setBorder(null);
        jBtnEditar3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jLabel15.setBackground(new java.awt.Color(255, 255, 255));
        jLabel15.setFont(new java.awt.Font("HelveticaNowDisplay Regular", 0, 14)); // NOI18N
        jLabel15.setText("Añada, modifique o elimine");

        javax.swing.GroupLayout jTiposContratoLayout = new javax.swing.GroupLayout(jTiposContrato);
        jTiposContrato.setLayout(jTiposContratoLayout);
        jTiposContratoLayout.setHorizontalGroup(
            jTiposContratoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jTiposContratoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jTiposContratoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(16, 16, 16))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jTiposContratoLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jBtnEditar3, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jTiposContratoLayout.setVerticalGroup(
            jTiposContratoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jTiposContratoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                .addComponent(jBtnEditar3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jMotivoEgreso.setBackground(new java.awt.Color(255, 255, 255));
        jMotivoEgreso.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel20.setBackground(new java.awt.Color(255, 255, 255));
        jLabel20.setFont(new java.awt.Font("HelveticaNowDisplay Regular", 0, 14)); // NOI18N
        jLabel20.setText("Añada, modifique o elimine");

        jLabel18.setBackground(new java.awt.Color(255, 255, 255));
        jLabel18.setFont(new java.awt.Font("HelveticaNowDisplay Regular", 0, 14)); // NOI18N
        jLabel18.setText("se debe realizar un gasto");

        jBtnEditar4.setBackground(new java.awt.Color(41, 128, 185));
        jBtnEditar4.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 0, 16)); // NOI18N
        jBtnEditar4.setForeground(new java.awt.Color(255, 255, 255));
        jBtnEditar4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/egreso-blanco24.png"))); // NOI18N
        jBtnEditar4.setText("MOTIVO");
        jBtnEditar4.setBorder(null);
        jBtnEditar4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jLabel17.setBackground(new java.awt.Color(255, 255, 255));
        jLabel17.setFont(new java.awt.Font("HelveticaNowDisplay Regular", 0, 14)); // NOI18N
        jLabel17.setText("en la Junta Administradora");

        jLabel19.setBackground(new java.awt.Color(255, 255, 255));
        jLabel19.setFont(new java.awt.Font("HelveticaNowDisplay Regular", 0, 14)); // NOI18N
        jLabel19.setText("los motivos por los cuales");

        jLabel21.setBackground(new java.awt.Color(255, 255, 255));
        jLabel21.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 0, 14)); // NOI18N
        jLabel21.setText("MOTIVO EGRESO");

        jLabel22.setBackground(new java.awt.Color(255, 255, 255));
        jLabel22.setFont(new java.awt.Font("HelveticaNowDisplay Regular", 0, 14)); // NOI18N
        jLabel22.setText("de Agua");

        javax.swing.GroupLayout jMotivoEgresoLayout = new javax.swing.GroupLayout(jMotivoEgreso);
        jMotivoEgreso.setLayout(jMotivoEgresoLayout);
        jMotivoEgresoLayout.setHorizontalGroup(
            jMotivoEgresoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jMotivoEgresoLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jBtnEditar4, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jMotivoEgresoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jMotivoEgresoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jMotivoEgresoLayout.createSequentialGroup()
                        .addGroup(jMotivoEgresoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
                            .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18))
                    .addGroup(jMotivoEgresoLayout.createSequentialGroup()
                        .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        jMotivoEgresoLayout.setVerticalGroup(
            jMotivoEgresoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jMotivoEgresoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel21)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel20)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel22)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addComponent(jBtnEditar4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTipoPlanPago.setBackground(new java.awt.Color(255, 255, 255));
        jTipoPlanPago.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel23.setBackground(new java.awt.Color(255, 255, 255));
        jLabel23.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 0, 14)); // NOI18N
        jLabel23.setText("TIPO PLAN DE PAGO");

        jLabel24.setBackground(new java.awt.Color(255, 255, 255));
        jLabel24.setFont(new java.awt.Font("HelveticaNowDisplay Regular", 0, 14)); // NOI18N
        jLabel24.setText("Añada, modifique o elimine");

        jLabel25.setBackground(new java.awt.Color(255, 255, 255));
        jLabel25.setFont(new java.awt.Font("HelveticaNowDisplay Regular", 0, 14)); // NOI18N
        jLabel25.setText("los estados posibles en");

        jLabel26.setBackground(new java.awt.Color(255, 255, 255));
        jLabel26.setFont(new java.awt.Font("HelveticaNowDisplay Regular", 0, 14)); // NOI18N
        jLabel26.setText("que puede permanecer un");

        jLabel27.setBackground(new java.awt.Color(255, 255, 255));
        jLabel27.setFont(new java.awt.Font("HelveticaNowDisplay Regular", 0, 14)); // NOI18N
        jLabel27.setText("contrato");

        jBtnEditar5.setBackground(new java.awt.Color(41, 128, 185));
        jBtnEditar5.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 0, 16)); // NOI18N
        jBtnEditar5.setForeground(new java.awt.Color(255, 255, 255));
        jBtnEditar5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/planpago-blanco24.png"))); // NOI18N
        jBtnEditar5.setText("TIPO");
        jBtnEditar5.setBorder(null);
        jBtnEditar5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout jTipoPlanPagoLayout = new javax.swing.GroupLayout(jTipoPlanPago);
        jTipoPlanPago.setLayout(jTipoPlanPagoLayout);
        jTipoPlanPagoLayout.setHorizontalGroup(
            jTipoPlanPagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jTipoPlanPagoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jTipoPlanPagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE)
                    .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jTipoPlanPagoLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jBtnEditar5, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jTipoPlanPagoLayout.setVerticalGroup(
            jTipoPlanPagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jTipoPlanPagoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel23)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel24)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel25)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel26)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel27)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                .addComponent(jBtnEditar5, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jServiciosJunta.setBackground(new java.awt.Color(255, 255, 255));
        jServiciosJunta.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jBtnEditar7.setBackground(new java.awt.Color(41, 128, 185));
        jBtnEditar7.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 0, 16)); // NOI18N
        jBtnEditar7.setForeground(new java.awt.Color(255, 255, 255));
        jBtnEditar7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/servicios-blanco24.png"))); // NOI18N
        jBtnEditar7.setText("SERVICIOS");
        jBtnEditar7.setBorder(null);
        jBtnEditar7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jLabel6.setBackground(new java.awt.Color(255, 255, 255));
        jLabel6.setFont(new java.awt.Font("HelveticaNowDisplay Regular", 0, 14)); // NOI18N
        jLabel6.setText("Agua");

        jLabel33.setBackground(new java.awt.Color(255, 255, 255));
        jLabel33.setFont(new java.awt.Font("HelveticaNowDisplay Regular", 0, 14)); // NOI18N
        jLabel33.setText("la Junta Admnistradora de");

        jLabel34.setBackground(new java.awt.Color(255, 255, 255));
        jLabel34.setFont(new java.awt.Font("HelveticaNowDisplay Regular", 0, 14)); // NOI18N
        jLabel34.setText("los servicios ofrecidos por");

        jLabel35.setBackground(new java.awt.Color(255, 255, 255));
        jLabel35.setFont(new java.awt.Font("HelveticaNowDisplay Regular", 0, 14)); // NOI18N
        jLabel35.setText("Añada, modifique o elimine");

        jLabel36.setBackground(new java.awt.Color(255, 255, 255));
        jLabel36.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 0, 14)); // NOI18N
        jLabel36.setText("SERVICIOS DE LA JUNTA");

        javax.swing.GroupLayout jServiciosJuntaLayout = new javax.swing.GroupLayout(jServiciosJunta);
        jServiciosJunta.setLayout(jServiciosJuntaLayout);
        jServiciosJuntaLayout.setHorizontalGroup(
            jServiciosJuntaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jServiciosJuntaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jServiciosJuntaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jServiciosJuntaLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jBtnEditar7, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel36, javax.swing.GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE)
                    .addComponent(jLabel35, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel34, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel33, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jServiciosJuntaLayout.setVerticalGroup(
            jServiciosJuntaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jServiciosJuntaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel36)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel35)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel34)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel33)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                .addComponent(jBtnEditar7, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 0, 20)); // NOI18N
        jLabel1.setText("Usuarios");

        jSeparatorX1.setBackground(new java.awt.Color(153, 153, 153));

        javax.swing.GroupLayout jSeparatorX1Layout = new javax.swing.GroupLayout(jSeparatorX1);
        jSeparatorX1.setLayout(jSeparatorX1Layout);
        jSeparatorX1Layout.setHorizontalGroup(
            jSeparatorX1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jSeparatorX1Layout.setVerticalGroup(
            jSeparatorX1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1, Short.MAX_VALUE)
        );

        jLabel29.setBackground(new java.awt.Color(255, 255, 255));
        jLabel29.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 0, 20)); // NOI18N
        jLabel29.setText("Servicios");

        jSeparatorX2.setBackground(new java.awt.Color(153, 153, 153));

        javax.swing.GroupLayout jSeparatorX2Layout = new javax.swing.GroupLayout(jSeparatorX2);
        jSeparatorX2.setLayout(jSeparatorX2Layout);
        jSeparatorX2Layout.setHorizontalGroup(
            jSeparatorX2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jSeparatorX2Layout.setVerticalGroup(
            jSeparatorX2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1, Short.MAX_VALUE)
        );

        jLabel30.setBackground(new java.awt.Color(255, 255, 255));
        jLabel30.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 0, 20)); // NOI18N
        jLabel30.setText("Contratos");

        jSeparatorX3.setBackground(new java.awt.Color(153, 153, 153));

        javax.swing.GroupLayout jSeparatorX3Layout = new javax.swing.GroupLayout(jSeparatorX3);
        jSeparatorX3.setLayout(jSeparatorX3Layout);
        jSeparatorX3Layout.setHorizontalGroup(
            jSeparatorX3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jSeparatorX3Layout.setVerticalGroup(
            jSeparatorX3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1, Short.MAX_VALUE)
        );

        jLabel31.setBackground(new java.awt.Color(255, 255, 255));
        jLabel31.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 0, 20)); // NOI18N
        jLabel31.setText("Planes de pago");

        jSeparatorX4.setBackground(new java.awt.Color(153, 153, 153));

        javax.swing.GroupLayout jSeparatorX4Layout = new javax.swing.GroupLayout(jSeparatorX4);
        jSeparatorX4.setLayout(jSeparatorX4Layout);
        jSeparatorX4Layout.setHorizontalGroup(
            jSeparatorX4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jSeparatorX4Layout.setVerticalGroup(
            jSeparatorX4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1, Short.MAX_VALUE)
        );

        jLabel32.setBackground(new java.awt.Color(255, 255, 255));
        jLabel32.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 0, 20)); // NOI18N
        jLabel32.setText("Egresos");

        jSeparatorX5.setBackground(new java.awt.Color(153, 153, 153));

        javax.swing.GroupLayout jSeparatorX5Layout = new javax.swing.GroupLayout(jSeparatorX5);
        jSeparatorX5.setLayout(jSeparatorX5Layout);
        jSeparatorX5Layout.setHorizontalGroup(
            jSeparatorX5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 514, Short.MAX_VALUE)
        );
        jSeparatorX5Layout.setVerticalGroup(
            jSeparatorX5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jMotivoEgreso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel17Layout.createSequentialGroup()
                            .addComponent(jLabel32)
                            .addGap(18, 18, 18)
                            .addComponent(jSeparatorX5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(jPanel17Layout.createSequentialGroup()
                            .addComponent(jLabel31)
                            .addGap(18, 18, 18)
                            .addComponent(jSeparatorX4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(jPanel17Layout.createSequentialGroup()
                            .addComponent(jEstadosContrato, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(30, 30, 30)
                            .addComponent(jTiposContrato, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel17Layout.createSequentialGroup()
                            .addComponent(jLabel30)
                            .addGap(18, 18, 18)
                            .addComponent(jSeparatorX3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(jPanel17Layout.createSequentialGroup()
                            .addComponent(jLabel29)
                            .addGap(18, 18, 18)
                            .addComponent(jSeparatorX2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addComponent(jUsuarios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTipoPlanPago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel17Layout.createSequentialGroup()
                            .addComponent(jLabel1)
                            .addGap(18, 18, 18)
                            .addComponent(jSeparatorX1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addComponent(jServiciosJunta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(260, Short.MAX_VALUE))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(jSeparatorX1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)))
                .addComponent(jUsuarios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel29)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(jSeparatorX2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jServiciosJunta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel30)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(jSeparatorX3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jEstadosContrato, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTiposContrato, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(jLabel31)
                        .addGap(18, 18, 18))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparatorX4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)))
                .addComponent(jTipoPlanPago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel32)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jSeparatorX5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jMotivoEgreso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(54, Short.MAX_VALUE))
        );

        jScrollPane4.setViewportView(jPanel17);

        javax.swing.GroupLayout jPnlAyudaLayout = new javax.swing.GroupLayout(jPnlAyuda);
        jPnlAyuda.setLayout(jPnlAyudaLayout);
        jPnlAyudaLayout.setHorizontalGroup(
            jPnlAyudaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPnlAyudaLayout.createSequentialGroup()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 936, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 51, Short.MAX_VALUE))
        );
        jPnlAyudaLayout.setVerticalGroup(
            jPnlAyudaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 565, Short.MAX_VALUE)
        );

        jTabContrato.addTab("tab3", jPnlAyuda);

        jPanel1.add(jTabContrato, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 60, 1030, 570));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1000, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 625, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //---------------- Eventos de los controles del formulario ----------------
    
    private void jBtnSideBar1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBtnSideBar1MouseClicked
        // TODO add your handling code here:
        this.sideBar.jTextAreaXLeft(0, -210 , 5, 5, jSideBar1);
        this.jBtnSideBar1.setVisible(false);
        this.jBtnSideBar.setVisible(true);
    }//GEN-LAST:event_jBtnSideBar1MouseClicked

    private void jBtnSideBarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBtnSideBarMouseClicked
        // TODO add your handling code here:
        this.sideBar.jTextAreaXRight(-210, 0 , 5, 5, jSideBar1);
        this.jBtnSideBar.setVisible(false);
        this.jBtnSideBar1.setVisible(true);
    }//GEN-LAST:event_jBtnSideBarMouseClicked

    private void jSBListadoEgresosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jSBListadoEgresosMouseClicked
        // TODO add your handling code here:
        this.jTabContrato.setSelectedIndex(1);
        this.jBtnSideBar.setVisible(true);
        this.jBtnSideBar1.setVisible(false);
        this.jSBAMEEgresos.setBackground(azul);
        this.jSBReportesEgresos.setBackground(azul);
        this.jSBAyuda.setBackground(azul);
        this.jSBListadoEgresos.setBackground(celeste);
    }//GEN-LAST:event_jSBListadoEgresosMouseClicked

    private void jSBAyudaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jSBAyudaMouseClicked
        this.jTabContrato.setSelectedIndex(3);
        this.jBtnSideBar.setVisible(true);
        this.jBtnSideBar1.setVisible(false);
        this.jSBAMEEgresos.setBackground(azul);
        this.jSBListadoEgresos.setBackground(azul);
        this.jSBReportesEgresos.setBackground(azul);
        this.jSBAyuda.setBackground(celeste);
    }//GEN-LAST:event_jSBAyudaMouseClicked

    private void jSBReportesEgresosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jSBReportesEgresosMouseClicked
        this.jTabContrato.setSelectedIndex(2);
        this.jBtnSideBar.setVisible(true);
        this.jBtnSideBar1.setVisible(false);
        this.jSBAMEEgresos.setBackground(azul);
        this.jSBListadoEgresos.setBackground(azul);
        this.jSBAyuda.setBackground(azul);
        this.jSBReportesEgresos.setBackground(celeste);
    }//GEN-LAST:event_jSBReportesEgresosMouseClicked

    private void jLblIdentificador23MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLblIdentificador23MouseEntered
        // TODO add your handling code here:
        this.jLblIdentificador23.setForeground(Color.BLUE);
    }//GEN-LAST:event_jLblIdentificador23MouseEntered

    private void jLblIdentificador23MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLblIdentificador23MouseExited
        // TODO add your handling code here:
        this.jLblIdentificador23.setForeground(azul);
    }//GEN-LAST:event_jLblIdentificador23MouseExited

    private void jSBAMEEgresosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jSBAMEEgresosMouseClicked
        this.jTabContrato.setSelectedIndex(0);
        this.jBtnSideBar.setVisible(true);
        this.jBtnSideBar1.setVisible(false);
        this.jSBReportesEgresos.setBackground(azul);
        this.jSBAyuda.setBackground(azul);
        this.jSBListadoEgresos.setBackground(azul);
        this.jSBAMEEgresos.setBackground(celeste);
    }//GEN-LAST:event_jSBAMEEgresosMouseClicked

    private void jRBMesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRBMesActionPerformed
        limpiarTablaMesAñoRango();
        if(this.jRBMes.isSelected()== true){
            this.jPnlRangoFecha.setVisible(false);
            this.jPnlBuscar.setVisible(true);
            this.jBtnBuscarAño.setVisible(false);
            this.jBtnBuscarMes.setVisible(true);
        }
    }//GEN-LAST:event_jRBMesActionPerformed

    private void jRBAñoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRBAñoActionPerformed
        limpiarTablaMesAñoRango();
        if(this.jRBAño.isSelected()== true){
            this.jPnlRangoFecha.setVisible(false);
            this.jPnlBuscar.setVisible(true);
            this.jBtnBuscarMes.setVisible(false);
            this.jBtnBuscarAño.setVisible(true);
        }
    }//GEN-LAST:event_jRBAñoActionPerformed

    private void jRBRangoFechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRBRangoFechaActionPerformed
        limpiarTablaMesAñoRango();
        if(this.jRBRangoFecha.isSelected()== true){
            this.jTfBuscar.setEditable(false);
            this.jPnlBuscar.setVisible(false);
            this.jBtnBuscarMes.setVisible(false);
            this.jBtnBuscarAño.setVisible(false);
            this.jPnlRangoFecha.setVisible(true);
        }
    }//GEN-LAST:event_jRBRangoFechaActionPerformed

    private void jBtnBuscarMesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBtnBuscarMesMouseClicked
        String mes;

        mes = this.jTfBuscar.getText();
        
        if (this.jTfBuscar.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Ingrese el mes que desea filtrar", "Saja", JOptionPane.INFORMATION_MESSAGE);
            this.jTfBuscar.requestFocus();
        } else {
            try {
                llenarTablaEgresosMes(mes);
                this.jTfBuscar.setText("");

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error fill table: " + ex);
            }
        }
    }//GEN-LAST:event_jBtnBuscarMesMouseClicked

    private void jBtnBuscarAñoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBtnBuscarAñoMouseClicked
        String año;

        año = this.jTfBuscar.getText();
        
        if (this.jTfBuscar.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Ingrese el año que desea filtrar", "Saja", JOptionPane.INFORMATION_MESSAGE);
            this.jTfBuscar.requestFocus();
        } else {
            try {
                llenarTablaEgresosAños(año);
                this.jTfBuscar.setText("");

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error fill table: " + ex);
            }
        }
    }//GEN-LAST:event_jBtnBuscarAñoMouseClicked

    private void jBtnBuscarRangoFechaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBtnBuscarRangoFechaMouseClicked
        java.util.Date fecha1 = jDCFecha1.getDate();
        java.util.Date fecha2 = jDCFecha2.getDate();
        SimpleDateFormat oDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        
        if (this.jDCFecha1.getDate() == null) {
            JOptionPane.showMessageDialog(null, "Ingrese la primer fecha", "Saja", JOptionPane.INFORMATION_MESSAGE);
        } else if (this.jDCFecha2.getDate() == null) {
            JOptionPane.showMessageDialog(null, "Ingrese la segunda fecha", "Saja", JOptionPane.INFORMATION_MESSAGE);
        } else {
            try {
                llenarTablaEgresosPorRangoFecha(oDateFormat.format(fecha1), oDateFormat.format(fecha2));

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error fill table: " + ex);
            }
        }
    }//GEN-LAST:event_jBtnBuscarRangoFechaMouseClicked

    private void jBtnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnGuardarActionPerformed
        if(this.jCboMotivoEgreso.getSelectedItem().equals("")){
            JOptionPane.showMessageDialog(null, "Seleccione el tipo de egreso", "Saja", JOptionPane.INFORMATION_MESSAGE);
        }else if(this.jTfValor.getText().trim().equals("")){
            JOptionPane.showMessageDialog(null, "Ingrese el valor del egreso", "Saja", JOptionPane.INFORMATION_MESSAGE);
        } else if(this.jDCFechaEgreso.getDate() == null){
            JOptionPane.showMessageDialog(null, "Ingrese la fecha del egreso", "Saja", JOptionPane.INFORMATION_MESSAGE);
        } else if(this.jTAObservacion.getText().trim().equals("")){
            JOptionPane.showMessageDialog(null, "Ingrese la observación del egreso", "Saja", JOptionPane.INFORMATION_MESSAGE);
        } else{
            try {
                insertarEgreso();
                llenarTablaEgresosHoy();
                limpiarCajasDeTexto();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error fill table: " + ex);;
            }
        }
    }//GEN-LAST:event_jBtnGuardarActionPerformed

    private void jTblEgresosHoyMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTblEgresosHoyMouseClicked
        seleccionarFilas();
        this.jBtnGuardar.setVisible(false);
        this.jBtnEditar.setVisible(true);
        estadEditando = true;
    }//GEN-LAST:event_jTblEgresosHoyMouseClicked

    private void jBtnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnEditarActionPerformed
        if(this.jCboMotivoEgreso.getSelectedItem().equals("")){
            JOptionPane.showMessageDialog(null, "Seleccione el tipo de egreso", "Saja", JOptionPane.INFORMATION_MESSAGE);
        }else if(this.jTfValor.getText().trim().equals("")){
            JOptionPane.showMessageDialog(null, "Ingrese el valor del egreso", "Saja", JOptionPane.INFORMATION_MESSAGE);
        } else if(this.jDCFechaEgreso.getDate() == null){
            JOptionPane.showMessageDialog(null, "Ingrese la fecha del egreso", "Saja", JOptionPane.INFORMATION_MESSAGE);
        } else if(this.jTAObservacion.getText().trim().equals("")){
            JOptionPane.showMessageDialog(null, "Ingrese la observación del egreso", "Saja", JOptionPane.INFORMATION_MESSAGE);
        } else{
            try {
                modificarEgreso();
                llenarTablaEgresosHoy();
                limpiarCajasDeTexto();
                this.jBtnGuardar.setVisible(true);
                this.jBtnEditar.setVisible(false);
                this.jLblCancelarEdicion.setVisible(false);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error fill table: " + ex);
            }
        }
    }//GEN-LAST:event_jBtnEditarActionPerformed

    private void jLblCancelarEdicionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLblCancelarEdicionMouseClicked
        if(estadEditando){
            int result = JOptionPane.showConfirmDialog(null,
                                                       "¿Desea cancelar la edición?", 
                                                       "SAJA",
                                                       JOptionPane.YES_NO_OPTION);

            if (result == JOptionPane.YES_OPTION) {
                
                limpiarCajasDeTexto();
                estadEditando = false;
                this.jLblCancelarEdicion.setVisible(false);
                this.jBtnGuardar.setVisible(true);
                this.jBtnEditar.setVisible(false);
            }

        } else {
            return;
        }
    }//GEN-LAST:event_jLblCancelarEdicionMouseClicked

    private void jLblVerEgresosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLblVerEgresosMouseClicked
        this.jTabContrato.setSelectedIndex(1);
        this.jSBAMEEgresos.setBackground(azul);
        this.jSBReportesEgresos.setBackground(azul);
        this.jSBAyuda.setBackground(azul);
        this.jSBListadoEgresos.setBackground(celeste);
    }//GEN-LAST:event_jLblVerEgresosMouseClicked

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
            java.util.logging.Logger.getLogger(JFraEgresos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFraEgresos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFraEgresos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFraEgresos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new JFraEgresos().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(JFraEgresos.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jBtnBuscarAño;
    private javax.swing.JLabel jBtnBuscarMes;
    private javax.swing.JLabel jBtnBuscarRangoFecha;
    private javax.swing.JButton jBtnEditar;
    private javax.swing.JButton jBtnEditar1;
    private javax.swing.JButton jBtnEditar2;
    private javax.swing.JButton jBtnEditar3;
    private javax.swing.JButton jBtnEditar4;
    private javax.swing.JButton jBtnEditar5;
    private javax.swing.JButton jBtnEditar7;
    private javax.swing.JButton jBtnGuardar;
    private javax.swing.JButton jBtnGuardar10;
    private javax.swing.JButton jBtnGuardar11;
    private javax.swing.JButton jBtnGuardar12;
    private javax.swing.JButton jBtnGuardar8;
    private javax.swing.JButton jBtnGuardar9;
    private javax.swing.JLabel jBtnSideBar;
    private javax.swing.JLabel jBtnSideBar1;
    private javax.swing.JComboBox<String> jCboMotivoEgreso;
    private com.toedter.calendar.JDateChooser jDCFecha1;
    private com.toedter.calendar.JDateChooser jDCFecha2;
    private com.toedter.calendar.JDateChooser jDCFechaEgreso;
    private javax.swing.JPanel jEstadosContrato;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLblCancelarEdicion;
    private javax.swing.JLabel jLblIDEgreso;
    private javax.swing.JLabel jLblIdentificador;
    private javax.swing.JLabel jLblIdentificador1;
    private javax.swing.JLabel jLblIdentificador10;
    private javax.swing.JLabel jLblIdentificador2;
    private javax.swing.JLabel jLblIdentificador23;
    private javax.swing.JLabel jLblIdentificador3;
    private javax.swing.JLabel jLblIdentificador32;
    private javax.swing.JLabel jLblIdentificador33;
    private javax.swing.JLabel jLblIdentificador34;
    private javax.swing.JLabel jLblIdentificador35;
    private javax.swing.JLabel jLblIdentificador36;
    private javax.swing.JLabel jLblIdentificador37;
    private javax.swing.JLabel jLblIdentificador38;
    private javax.swing.JLabel jLblIdentificador39;
    private javax.swing.JLabel jLblIdentificador4;
    private javax.swing.JLabel jLblIdentificador40;
    private javax.swing.JLabel jLblIdentificador41;
    private javax.swing.JLabel jLblIdentificador42;
    private javax.swing.JLabel jLblIdentificador43;
    private javax.swing.JLabel jLblIdentificador45;
    private javax.swing.JLabel jLblIdentificador46;
    private javax.swing.JLabel jLblIdentificador47;
    private javax.swing.JLabel jLblIdentificador48;
    private javax.swing.JLabel jLblIdentificador49;
    private javax.swing.JLabel jLblIdentificador5;
    private javax.swing.JLabel jLblIdentificador50;
    private javax.swing.JLabel jLblIdentificador51;
    private javax.swing.JLabel jLblIdentificador6;
    private javax.swing.JLabel jLblIdentificador7;
    private javax.swing.JLabel jLblIdentificador8;
    private javax.swing.JLabel jLblIdentificador9;
    private javax.swing.JLabel jLblTitulo2;
    private javax.swing.JLabel jLblVerEgresos;
    private javax.swing.JPanel jMotivoEgreso;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPanel jPnlAyuda;
    private javax.swing.JPanel jPnlBuscar;
    private javax.swing.JPanel jPnlEgresos;
    private javax.swing.JPanel jPnlRangoFecha;
    private javax.swing.JPanel jPnlReportes;
    private javax.swing.JPanel jPnlSeparatorY;
    private javax.swing.JPanel jPnlTablaEgresos;
    private javax.swing.JRadioButton jRBAño;
    private javax.swing.JRadioButton jRBMes;
    private javax.swing.JRadioButton jRBRangoFecha;
    private javax.swing.JPanel jSBAMEEgresos;
    private javax.swing.JPanel jSBAyuda;
    private javax.swing.JPanel jSBContrato4;
    private javax.swing.JPanel jSBListadoEgresos;
    private javax.swing.JPanel jSBReportesEgresos;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JPanel jSeparatorX1;
    private javax.swing.JPanel jSeparatorX2;
    private javax.swing.JPanel jSeparatorX3;
    private javax.swing.JPanel jSeparatorX4;
    private javax.swing.JPanel jSeparatorX5;
    private javax.swing.JPanel jServiciosJunta;
    private javax.swing.JPanel jSideBar;
    private javax.swing.JScrollPane jSideBar1;
    private javax.swing.JTextArea jTAObservacion;
    private javax.swing.JTabbedPane jTabContrato;
    private javax.swing.JTable jTblEgresos;
    private javax.swing.JTable jTblEgresosHoy;
    private javax.swing.JTextField jTfBuscar;
    private javax.swing.JTextField jTfIdentidad4;
    private javax.swing.JTextField jTfValor;
    private javax.swing.JPanel jTipoPlanPago;
    private javax.swing.JPanel jTiposContrato;
    private javax.swing.JPanel jUsuarios;
    // End of variables declaration//GEN-END:variables
}
