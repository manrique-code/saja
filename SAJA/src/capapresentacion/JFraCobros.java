/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capapresentacion;

import AppPackage.AnimationClass;
import capadatos.CDCobros;
import capalogica.CLCobros;
import java.awt.Color;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.awt.Frame;

/**
 *
 * @author Manrique
 */
public class JFraCobros extends javax.swing.JFrame {

    /**
     * Creates new form jFraCobros
     */
    public JFraCobros() throws SQLException{
        initComponents();
        this.getContentPane().setBackground(Color.white);
        this.setLocationRelativeTo(null);
        this.jDialog1.setLocationRelativeTo(null);
        this.jDialog2.setLocationRelativeTo(null);
        this.jBtnSideBar1.setVisible(false);
        this.jLblNumPagado.setVisible(true);
        this.jPnlRangoFecha.setVisible(false);
        this.jPnlRangoFecha.setVisible(false);
        this.jPnlBuscar.setVisible(true);
        this.jTfRTN.setVisible(false);
        this.jLblRTN.setVisible(false);
        this.jTfRTN1.setVisible(false);
        this.jLblRTN1.setVisible(false);
        this.jLblCancelarEdicion.setVisible(false);
    }
    AnimationClass sideBar = new AnimationClass();
    Color celeste = new Color(52,152,219);
    Color azul = new Color(52,73,94);
    private boolean estadEditando = false;
    
    JFraMenu jfm = new JFraMenu();
    
    // Método para limpiar la tabla contratos de la ventana modal jDialog1
    private void limpiarTablaDatosContrato(){
        DefaultTableModel dtm = (DefaultTableModel) this.jTblDatosContrato.getModel();
        
        while(dtm.getRowCount() > 0){
            dtm.removeRow(0);
        }
    }
    
    // Método para limpiar la tabla de contratos de la ventana modal jDialog2
    private void limpiarTablaDatosContrato1(){
        DefaultTableModel dtm = (DefaultTableModel) this.jTblDatosContrato1.getModel();
        
        while(dtm.getRowCount() > 0){
            dtm.removeRow(0);
        }
    }
    
    // Método para limpiar la tabla de pagos
    private void limpiarTablaPagos(){
        DefaultTableModel dtm = (DefaultTableModel) this.jTblPagos.getModel();
        
        while(dtm.getRowCount() > 0){
            dtm.removeRow(0);
        }
    }
    
    // Método para limpiar las cajas de texto
    private void limpiarCajasDeTexto() {
        this.jTfIdentidad14.setText("");
        this.jTfNombreCompleto2.setText("");
        this.jTfBloque2.setText("");
        this.jTfCasa2.setText("");   
    }
    
    // Método para llenar la tabla de contratos por bloque y casa de de jDialog1
    private void llenarTablaDatosContratoBC(String bloque, String casa) throws SQLException{
        limpiarTablaDatosContrato();
        
        CDCobros cdc = new CDCobros();
        List<CLCobros> miLista = cdc.mostrarCobrosPorBloqueYCasa(bloque, casa);
        DefaultTableModel temp = (DefaultTableModel) this.jTblDatosContrato.getModel();
        
       for(CLCobros clc: miLista) {
            Object[] fila = new Object[4];
            fila[0] = clc.getNumIdentidad();
            fila[1] = clc.getNombreCompleto();
            fila[2] = clc.getBloque();
            fila[3] = clc.getCasa();
            temp.addRow(fila);
        }
    }
    
    // Método para llenar la tabla de contratos por bloque y casa de jDialog2
    private void llenarTablaDatosContratoBC1(String bloque, String casa) throws SQLException{
        limpiarTablaDatosContrato();
        
        CDCobros cdc = new CDCobros();
        List<CLCobros> miLista = cdc.mostrarCobrosPorBloqueYCasa(bloque, casa);
        DefaultTableModel temp = (DefaultTableModel) this.jTblDatosContrato1.getModel();
        
       for(CLCobros clc: miLista) {
            Object[] fila = new Object[4];
            fila[0] = clc.getNumIdentidad();
            fila[1] = clc.getNombreCompleto();
            fila[2] = clc.getBloque();
            fila[3] = clc.getCasa();
            temp.addRow(fila);
        }
    }
    
    // Método para llenar la tabla de contratos por RTN de jDialog1
    private void llenarTablaDatosContratoRTN(String RTN) throws SQLException{
        limpiarTablaDatosContrato();
        
        CDCobros cdc = new CDCobros();
        List<CLCobros> miLista = cdc.mostrarCobrosPorRTN(RTN);
        DefaultTableModel temp = (DefaultTableModel) this.jTblDatosContrato.getModel();
        
       for(CLCobros clc: miLista) {
            Object[] fila = new Object[4];
            fila[0] = clc.getNumIdentidad();
            fila[1] = clc.getNombreCompleto();
            fila[2] = clc.getBloque();
            fila[3] = clc.getCasa();
            temp.addRow(fila);
        }
    }
    
    // Método para llenar la tabla de contratos por RTN de jDialog2
    private void llenarTablaDatosContratoRTN1(String RTN) throws SQLException{
        limpiarTablaDatosContrato();
        
        CDCobros cdc = new CDCobros();
        List<CLCobros> miLista = cdc.mostrarCobrosPorRTN(RTN);
        DefaultTableModel temp = (DefaultTableModel) this.jTblDatosContrato1.getModel();
        
       for(CLCobros clc: miLista) {
            Object[] fila = new Object[4];
            fila[0] = clc.getNumIdentidad();
            fila[1] = clc.getNombreCompleto();
            fila[2] = clc.getBloque();
            fila[3] = clc.getCasa();
            temp.addRow(fila);
        }
    }
    
    // Método para llenar la tabla de meses pagados
    private void llenarTablaMesesPagados(String RTN) throws SQLException{
        limpiarTablaDatosContrato();
        
        CDCobros cdc = new CDCobros();
        List<CLCobros> miLista = cdc.mostrarMesesPagados(RTN);
        DefaultTableModel temp = (DefaultTableModel) this.jTblMesesPagados.getModel();
        
      /* for(CLCobros clc: miLista) {
            Object[] fila = new Object[2];
            fila[0] = clc.getMesPagado();
            fila[1] = clc.getPrecio();
            temp.addRow(fila);
        }*/
    }
    
    // Método para llenar la tabla de pago meses por mes
    private void mostrarPagosMes(String mes) throws SQLException{
        limpiarTablaPagos();
        
        CDCobros cdc = new CDCobros();
        List<CLCobros> miLista = cdc.mostrarPagosMes(mes);
        DefaultTableModel temp = (DefaultTableModel) this.jTblPagos.getModel();
        
       for(CLCobros cle: miLista) {
            Object[] fila = new Object[7];
            fila[0] = cle.getNumIdentidad();
            fila[1] = cle.getNombreCompleto();
            fila[2] = cle.getBloque();
            fila[3] = cle.getCasa();
            fila[4] = cle.getTipoContrato();
            fila[5] = cle.getMes();
            fila[6] = cle.getValor();
            temp.addRow(fila);
        }
    }
    
    // Método para llenar la tabla de pago meses por rango de fecha
    private void mostrarPagosPorRangoFecha(String fecha1, String fecha2) throws SQLException{
        limpiarTablaPagos();
        
        CDCobros cdc = new CDCobros();
        List<CLCobros> miLista = cdc.mostrarPagosPorRangoFecha(fecha1, fecha2);
        DefaultTableModel temp = (DefaultTableModel) this.jTblPagos.getModel();
        
       for(CLCobros cle: miLista) {
            Object[] fila = new Object[7];
            fila[0] = cle.getNumIdentidad();
            fila[1] = cle.getNombreCompleto();
            fila[2] = cle.getBloque();
            fila[3] = cle.getCasa();
            fila[4] = cle.getTipoContrato();
            fila[5] = cle.getMes();
            fila[6] = cle.getValor();
            temp.addRow(fila);
        }
    }
    
    // Método para mostrar el número de meses pagados
    private void numMesPagado(String RTN) throws SQLException{        
        CDCobros cdc = new CDCobros();
        CLCobros clc = new CLCobros();
        this.jLblNumPagado.setText(String.valueOf(cdc.numMesesPagados(RTN)));
    }
    
    // Metodo para seleccionar filas de datos contrato de jDialog1
    private void seleccionarFilas() {
        if (this.jTblDatosContrato.getSelectedRow() != -1) {
            this.jTfIdentidad.setText(String.valueOf(this.jTblDatosContrato.getValueAt(this.jTblDatosContrato.getSelectedRow(), 0)));
            this.jTfNombreCompleto.setText(String.valueOf(this.jTblDatosContrato.getValueAt(this.jTblDatosContrato.getSelectedRow(), 1)));
            this.jTfBloque.setText(String.valueOf(this.jTblDatosContrato.getValueAt(this.jTblDatosContrato.getSelectedRow(), 2)));
            this.jTfCasa.setText(String.valueOf(this.jTblDatosContrato.getValueAt(this.jTblDatosContrato.getSelectedRow(), 3)));
        }
    }
    
    // Metodo para seleccionar filas de datos contrato de jDialog2
    private void seleccionarFilas2() {
        if (this.jTblDatosContrato1.getSelectedRow() != -1) {
            this.jTfIdentidad14.setText(String.valueOf(this.jTblDatosContrato1.getValueAt(this.jTblDatosContrato1.getSelectedRow(), 0)));
            this.jTfNombreCompleto2.setText(String.valueOf(this.jTblDatosContrato1.getValueAt(this.jTblDatosContrato1.getSelectedRow(), 1)));
            this.jTfBloque2.setText(String.valueOf(this.jTblDatosContrato1.getValueAt(this.jTblDatosContrato1.getSelectedRow(), 2)));
            this.jTfCasa2.setText(String.valueOf(this.jTblDatosContrato1.getValueAt(this.jTblDatosContrato1.getSelectedRow(), 3)));
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

        jSeparatorX1 = new javax.swing.JPanel();
        jBtnGuardar3 = new javax.swing.JButton();
        jBtnEditar = new javax.swing.JButton();
        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jDialog1 = new javax.swing.JDialog();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTblDatosContrato = new javax.swing.JTable();
        jLblIdentificador44 = new javax.swing.JLabel();
        jBtnSeleccionar = new javax.swing.JButton();
        jBtnCancelar = new javax.swing.JButton();
        jLblCasa = new javax.swing.JLabel();
        jTfCasaB = new javax.swing.JTextField();
        jLblBloque = new javax.swing.JLabel();
        jTfBloqueB = new javax.swing.JTextField();
        jBtnBuscarContratoBC = new javax.swing.JButton();
        jLblRTN = new javax.swing.JLabel();
        jTfRTN = new javax.swing.JTextField();
        jBtnBuscarContratoRTN = new javax.swing.JButton();
        jDialog2 = new javax.swing.JDialog();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTblDatosContrato1 = new javax.swing.JTable();
        jLblIdentificador45 = new javax.swing.JLabel();
        jBtnSeleccionar1 = new javax.swing.JButton();
        jBtnCancelar1 = new javax.swing.JButton();
        jLblCasa1 = new javax.swing.JLabel();
        jTfCasaB1 = new javax.swing.JTextField();
        jLblBloque1 = new javax.swing.JLabel();
        jTfBloqueB1 = new javax.swing.JTextField();
        jBtnBuscarContratoBC3 = new javax.swing.JButton();
        jLblRTN1 = new javax.swing.JLabel();
        jTfRTN1 = new javax.swing.JTextField();
        jBtnBuscarContratoRTN3 = new javax.swing.JButton();
        buttonGroup3 = new javax.swing.ButtonGroup();
        jDfMenu = new javax.swing.JDialog();
        jPanel15 = new javax.swing.JPanel();
        jLblIdentificador59 = new javax.swing.JLabel();
        jLblIdentificador71 = new javax.swing.JLabel();
        jLblIdentificador73 = new javax.swing.JLabel();
        jBtnSi = new javax.swing.JButton();
        JBtnNo = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLblTitulo2 = new javax.swing.JLabel();
        jBtnSideBar1 = new javax.swing.JLabel();
        jBtnSideBar = new javax.swing.JLabel();
        jLblMinimizar2 = new javax.swing.JLabel();
        jLblSalir = new javax.swing.JLabel();
        jSideBar1 = new javax.swing.JScrollPane();
        jSideBar = new javax.swing.JPanel();
        jSBAMECobros = new javax.swing.JPanel();
        jLblIdentificador = new javax.swing.JLabel();
        jLblIdentificador1 = new javax.swing.JLabel();
        jSBFinanzaAbonado = new javax.swing.JPanel();
        jLblIdentificador2 = new javax.swing.JLabel();
        jLblIdentificador3 = new javax.swing.JLabel();
        jSBContrato4 = new javax.swing.JPanel();
        jLblIdentificador9 = new javax.swing.JLabel();
        jLblIdentificador10 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLblIdentificador5 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jSBListadoPagos = new javax.swing.JPanel();
        jLblIdentificador7 = new javax.swing.JLabel();
        jLblIdentificador8 = new javax.swing.JLabel();
        jSBAyuda = new javax.swing.JPanel();
        jLblIdentificador4 = new javax.swing.JLabel();
        jLblIdentificador6 = new javax.swing.JLabel();
        jTabContrato = new javax.swing.JTabbedPane();
        jPnlAMECobros = new javax.swing.JPanel();
        jLblIdentificador23 = new javax.swing.JLabel();
        jBtnGuardar5 = new javax.swing.JButton();
        jSeparatorY1 = new javax.swing.JPanel();
        jLblIdentificador33 = new javax.swing.JLabel();
        jLblIdentificador36 = new javax.swing.JLabel();
        jLblIdentificador39 = new javax.swing.JLabel();
        jBtnBuscarContratoBC4 = new javax.swing.JButton();
        jLblIdentificador40 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jBtnGuardar7 = new javax.swing.JButton();
        jLblIdentificador41 = new javax.swing.JLabel();
        jSeparatorX3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jBtnGuardar8 = new javax.swing.JButton();
        jLblIdentificador42 = new javax.swing.JLabel();
        jTfIdentidad7 = new javax.swing.JTextField();
        jTfIdentidad14 = new javax.swing.JTextField();
        jTfNombreCompleto2 = new javax.swing.JTextField();
        jTfCasa2 = new javax.swing.JTextField();
        jTfBloque2 = new javax.swing.JTextField();
        jBtnBuscarContratoRTN4 = new javax.swing.JButton();
        jRBBC = new javax.swing.JRadioButton();
        jRBRTN2 = new javax.swing.JRadioButton();
        jLblCancelarEdicion = new javax.swing.JLabel();
        jPnlFinanzaAbonado = new javax.swing.JPanel();
        jLblIdentificador43 = new javax.swing.JLabel();
        jSeparatorY2 = new javax.swing.JPanel();
        jLblIdentificador46 = new javax.swing.JLabel();
        jTfIdentidad = new javax.swing.JTextField();
        jTfNombreCompleto = new javax.swing.JTextField();
        jLblIdentificador47 = new javax.swing.JLabel();
        jLblIdentificador48 = new javax.swing.JLabel();
        jTfBloque = new javax.swing.JTextField();
        jTfCasa = new javax.swing.JTextField();
        jLblIdentificador49 = new javax.swing.JLabel();
        jLblIdentificador50 = new javax.swing.JLabel();
        jLblIdentificador51 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTxtAEstado = new javax.swing.JTextArea();
        jLblIdentificador52 = new javax.swing.JLabel();
        jLblGenerarPlanPago = new javax.swing.JLabel();
        jLblIdentificador54 = new javax.swing.JLabel();
        jBtnEditar1 = new javax.swing.JButton();
        jLblIdentificador55 = new javax.swing.JLabel();
        jRBBloqueYCasa = new javax.swing.JRadioButton();
        jRBRTN = new javax.swing.JRadioButton();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTblMesesPagados = new javax.swing.JTable();
        jLblNumPagado = new javax.swing.JLabel();
        jBtnBuscarContratoBC1 = new javax.swing.JButton();
        jBtnBuscarContratoRTN1 = new javax.swing.JButton();
        jPnlListadoPagos = new javax.swing.JPanel();
        jLblIdentificador11 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTblPagos = new javax.swing.JTable();
        jRBPagosMes = new javax.swing.JRadioButton();
        jRBPagosRangoFecha = new javax.swing.JRadioButton();
        jPnlBuscar = new javax.swing.JPanel();
        jTfBuscar = new javax.swing.JTextField();
        jBtnBuscar = new javax.swing.JLabel();
        jPnlRangoFecha = new javax.swing.JPanel();
        jBtnBuscarRangoFecha = new javax.swing.JLabel();
        jDCFecha1 = new com.toedter.calendar.JDateChooser();
        jDCFecha2 = new com.toedter.calendar.JDateChooser();
        jLblIdentificador57 = new javax.swing.JLabel();
        jLblIdentificador58 = new javax.swing.JLabel();
        jPnlAMECobros1 = new javax.swing.JPanel();
        jLblIdentificador15 = new javax.swing.JLabel();
        jLblIdentificador16 = new javax.swing.JLabel();
        jTfIdentidad2 = new javax.swing.JTextField();
        jLblIdentificador17 = new javax.swing.JLabel();
        jTfIdentidad3 = new javax.swing.JTextField();
        jBtnGuardar = new javax.swing.JButton();
        jSeparatorY = new javax.swing.JPanel();
        jLblIdentificador18 = new javax.swing.JLabel();
        jLblIdentificador19 = new javax.swing.JLabel();
        jLblIdentificador20 = new javax.swing.JLabel();
        jLblIdentificador21 = new javax.swing.JLabel();
        jLblIdentificador22 = new javax.swing.JLabel();
        jLblIdentificador24 = new javax.swing.JLabel();
        jLblIdentificador25 = new javax.swing.JLabel();
        jBtnGuardar1 = new javax.swing.JButton();
        jLblIdentificador26 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jBtnGuardar2 = new javax.swing.JButton();
        jLblIdentificador27 = new javax.swing.JLabel();
        jSeparatorX2 = new javax.swing.JPanel();
        jTfIdentidad5 = new javax.swing.JTextField();
        jLblIdentificador28 = new javax.swing.JLabel();
        jBtnGuardar4 = new javax.swing.JButton();
        jLblIdentificador29 = new javax.swing.JLabel();
        jLblIdentificador30 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jList2 = new javax.swing.JList<>();

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

        jBtnGuardar3.setBackground(new java.awt.Color(41, 128, 185));
        jBtnGuardar3.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 0, 16)); // NOI18N
        jBtnGuardar3.setForeground(new java.awt.Color(255, 255, 255));
        jBtnGuardar3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/guardar-white24.png"))); // NOI18N
        jBtnGuardar3.setText("GUARDAR");
        jBtnGuardar3.setBorder(null);
        jBtnGuardar3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jBtnEditar.setBackground(new java.awt.Color(41, 128, 185));
        jBtnEditar.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 0, 16)); // NOI18N
        jBtnEditar.setForeground(new java.awt.Color(255, 255, 255));
        jBtnEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/editar-blanco32.png"))); // NOI18N
        jBtnEditar.setText("EDITAR");
        jBtnEditar.setBorder(null);
        jBtnEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jDialog1.setAlwaysOnTop(true);
        jDialog1.setModal(true);
        jDialog1.setModalExclusionType(java.awt.Dialog.ModalExclusionType.APPLICATION_EXCLUDE);
        jDialog1.setName("jDialog1"); // NOI18N
        jDialog1.setSize(new java.awt.Dimension(570, 420));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setLayout(null);

        jTblDatosContrato.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Numero de identidad", "Nombre Completo", "Bloque", "Casa"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTblDatosContrato.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jTblDatosContrato);

        jPanel6.add(jScrollPane1);
        jScrollPane1.setBounds(10, 110, 507, 190);

        jLblIdentificador44.setBackground(new java.awt.Color(102, 102, 102));
        jLblIdentificador44.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 1, 20)); // NOI18N
        jLblIdentificador44.setText("Búsqueda de contrato");
        jPanel6.add(jLblIdentificador44);
        jLblIdentificador44.setBounds(10, 11, 245, 21);

        jBtnSeleccionar.setBackground(new java.awt.Color(9, 132, 227));
        jBtnSeleccionar.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 0, 18)); // NOI18N
        jBtnSeleccionar.setForeground(new java.awt.Color(255, 255, 255));
        jBtnSeleccionar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/cruz-blanca24.png"))); // NOI18N
        jBtnSeleccionar.setText("Seleccionar");
        jBtnSeleccionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnSeleccionarActionPerformed(evt);
            }
        });
        jPanel6.add(jBtnSeleccionar);
        jBtnSeleccionar.setBounds(10, 308, 180, 52);

        jBtnCancelar.setBackground(new java.awt.Color(9, 132, 227));
        jBtnCancelar.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 0, 18)); // NOI18N
        jBtnCancelar.setForeground(new java.awt.Color(255, 255, 255));
        jBtnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/x-blanca24.png"))); // NOI18N
        jBtnCancelar.setText("Cancelar");
        jBtnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnCancelarActionPerformed(evt);
            }
        });
        jPanel6.add(jBtnCancelar);
        jBtnCancelar.setBounds(337, 310, 180, 49);

        jLblCasa.setBackground(new java.awt.Color(102, 102, 102));
        jLblCasa.setFont(new java.awt.Font("HelveticaNowDisplay Medium", 0, 20)); // NOI18N
        jLblCasa.setText("Casa");
        jPanel6.add(jLblCasa);
        jLblCasa.setBounds(170, 40, 100, 24);

        jTfCasaB.setFont(new java.awt.Font("HelveticaNowDisplay Regular", 0, 16)); // NOI18N
        jTfCasaB.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTfCasaB.setSelectionColor(new java.awt.Color(0, 153, 153));
        jPanel6.add(jTfCasaB);
        jTfCasaB.setBounds(170, 70, 100, 30);

        jLblBloque.setBackground(new java.awt.Color(102, 102, 102));
        jLblBloque.setFont(new java.awt.Font("HelveticaNowDisplay Medium", 0, 20)); // NOI18N
        jLblBloque.setText("Bloque");
        jPanel6.add(jLblBloque);
        jLblBloque.setBounds(30, 40, 74, 24);

        jTfBloqueB.setFont(new java.awt.Font("HelveticaNowDisplay Regular", 0, 16)); // NOI18N
        jTfBloqueB.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTfBloqueB.setSelectionColor(new java.awt.Color(0, 153, 153));
        jPanel6.add(jTfBloqueB);
        jTfBloqueB.setBounds(30, 70, 100, 30);

        jBtnBuscarContratoBC.setBackground(new java.awt.Color(41, 128, 185));
        jBtnBuscarContratoBC.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 0, 16)); // NOI18N
        jBtnBuscarContratoBC.setForeground(new java.awt.Color(255, 255, 255));
        jBtnBuscarContratoBC.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/search-blanco24.png"))); // NOI18N
        jBtnBuscarContratoBC.setText("BUSCAR CONTRATO");
        jBtnBuscarContratoBC.setBorder(null);
        jBtnBuscarContratoBC.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jBtnBuscarContratoBC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnBuscarContratoBCActionPerformed(evt);
            }
        });
        jPanel6.add(jBtnBuscarContratoBC);
        jBtnBuscarContratoBC.setBounds(280, 70, 210, 30);

        jLblRTN.setBackground(new java.awt.Color(102, 102, 102));
        jLblRTN.setFont(new java.awt.Font("HelveticaNowDisplay Medium", 0, 20)); // NOI18N
        jLblRTN.setText("RTN");
        jPanel6.add(jLblRTN);
        jLblRTN.setBounds(10, 40, 53, 20);

        jTfRTN.setFont(new java.awt.Font("HelveticaNowDisplay Regular", 0, 16)); // NOI18N
        jTfRTN.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTfRTN.setSelectionColor(new java.awt.Color(0, 153, 153));
        jPanel6.add(jTfRTN);
        jTfRTN.setBounds(10, 70, 240, 30);
        jTfRTN.getAccessibleContext().setAccessibleDescription("");

        jBtnBuscarContratoRTN.setBackground(new java.awt.Color(41, 128, 185));
        jBtnBuscarContratoRTN.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 0, 16)); // NOI18N
        jBtnBuscarContratoRTN.setForeground(new java.awt.Color(255, 255, 255));
        jBtnBuscarContratoRTN.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/search-blanco24.png"))); // NOI18N
        jBtnBuscarContratoRTN.setText("BUSCAR CONTRATO");
        jBtnBuscarContratoRTN.setBorder(null);
        jBtnBuscarContratoRTN.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jBtnBuscarContratoRTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnBuscarContratoRTNActionPerformed(evt);
            }
        });
        jPanel6.add(jBtnBuscarContratoRTN);
        jBtnBuscarContratoRTN.setBounds(280, 70, 210, 30);

        javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, 550, Short.MAX_VALUE)
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, 412, Short.MAX_VALUE)
        );

        jDialog2.setAlwaysOnTop(true);
        jDialog2.setModal(true);
        jDialog2.setModalExclusionType(java.awt.Dialog.ModalExclusionType.APPLICATION_EXCLUDE);
        jDialog2.setName("jDialog1"); // NOI18N
        jDialog2.setSize(new java.awt.Dimension(570, 420));

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setLayout(null);

        jTblDatosContrato1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Numero de identidad", "Nombre Completo", "Bloque", "Casa"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTblDatosContrato1.getTableHeader().setReorderingAllowed(false);
        jScrollPane4.setViewportView(jTblDatosContrato1);

        jPanel7.add(jScrollPane4);
        jScrollPane4.setBounds(10, 110, 507, 190);

        jLblIdentificador45.setBackground(new java.awt.Color(102, 102, 102));
        jLblIdentificador45.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 1, 20)); // NOI18N
        jLblIdentificador45.setText("Búsqueda de contrato");
        jPanel7.add(jLblIdentificador45);
        jLblIdentificador45.setBounds(10, 11, 245, 21);

        jBtnSeleccionar1.setBackground(new java.awt.Color(9, 132, 227));
        jBtnSeleccionar1.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 0, 18)); // NOI18N
        jBtnSeleccionar1.setForeground(new java.awt.Color(255, 255, 255));
        jBtnSeleccionar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/cruz-blanca24.png"))); // NOI18N
        jBtnSeleccionar1.setText("Seleccionar");
        jBtnSeleccionar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnSeleccionar1ActionPerformed(evt);
            }
        });
        jPanel7.add(jBtnSeleccionar1);
        jBtnSeleccionar1.setBounds(10, 308, 180, 52);

        jBtnCancelar1.setBackground(new java.awt.Color(9, 132, 227));
        jBtnCancelar1.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 0, 18)); // NOI18N
        jBtnCancelar1.setForeground(new java.awt.Color(255, 255, 255));
        jBtnCancelar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/x-blanca24.png"))); // NOI18N
        jBtnCancelar1.setText("Cancelar");
        jBtnCancelar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnCancelar1ActionPerformed(evt);
            }
        });
        jPanel7.add(jBtnCancelar1);
        jBtnCancelar1.setBounds(337, 310, 180, 49);

        jLblCasa1.setBackground(new java.awt.Color(102, 102, 102));
        jLblCasa1.setFont(new java.awt.Font("HelveticaNowDisplay Medium", 0, 20)); // NOI18N
        jLblCasa1.setText("Casa");
        jPanel7.add(jLblCasa1);
        jLblCasa1.setBounds(170, 40, 100, 24);

        jTfCasaB1.setFont(new java.awt.Font("HelveticaNowDisplay Regular", 0, 16)); // NOI18N
        jTfCasaB1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTfCasaB1.setSelectionColor(new java.awt.Color(0, 153, 153));
        jPanel7.add(jTfCasaB1);
        jTfCasaB1.setBounds(170, 70, 100, 30);

        jLblBloque1.setBackground(new java.awt.Color(102, 102, 102));
        jLblBloque1.setFont(new java.awt.Font("HelveticaNowDisplay Medium", 0, 20)); // NOI18N
        jLblBloque1.setText("Bloque");
        jPanel7.add(jLblBloque1);
        jLblBloque1.setBounds(30, 40, 74, 24);

        jTfBloqueB1.setFont(new java.awt.Font("HelveticaNowDisplay Regular", 0, 16)); // NOI18N
        jTfBloqueB1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTfBloqueB1.setSelectionColor(new java.awt.Color(0, 153, 153));
        jPanel7.add(jTfBloqueB1);
        jTfBloqueB1.setBounds(30, 70, 100, 30);

        jBtnBuscarContratoBC3.setBackground(new java.awt.Color(41, 128, 185));
        jBtnBuscarContratoBC3.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 0, 16)); // NOI18N
        jBtnBuscarContratoBC3.setForeground(new java.awt.Color(255, 255, 255));
        jBtnBuscarContratoBC3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/search-blanco24.png"))); // NOI18N
        jBtnBuscarContratoBC3.setText("BUSCAR CONTRATO");
        jBtnBuscarContratoBC3.setBorder(null);
        jBtnBuscarContratoBC3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jBtnBuscarContratoBC3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnBuscarContratoBC3ActionPerformed(evt);
            }
        });
        jPanel7.add(jBtnBuscarContratoBC3);
        jBtnBuscarContratoBC3.setBounds(280, 70, 210, 30);

        jLblRTN1.setBackground(new java.awt.Color(102, 102, 102));
        jLblRTN1.setFont(new java.awt.Font("HelveticaNowDisplay Medium", 0, 20)); // NOI18N
        jLblRTN1.setText("RTN");
        jPanel7.add(jLblRTN1);
        jLblRTN1.setBounds(10, 40, 53, 20);

        jTfRTN1.setFont(new java.awt.Font("HelveticaNowDisplay Regular", 0, 16)); // NOI18N
        jTfRTN1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTfRTN1.setSelectionColor(new java.awt.Color(0, 153, 153));
        jPanel7.add(jTfRTN1);
        jTfRTN1.setBounds(10, 70, 240, 30);

        jBtnBuscarContratoRTN3.setBackground(new java.awt.Color(41, 128, 185));
        jBtnBuscarContratoRTN3.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 0, 16)); // NOI18N
        jBtnBuscarContratoRTN3.setForeground(new java.awt.Color(255, 255, 255));
        jBtnBuscarContratoRTN3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/search-blanco24.png"))); // NOI18N
        jBtnBuscarContratoRTN3.setText("BUSCAR CONTRATO");
        jBtnBuscarContratoRTN3.setBorder(null);
        jBtnBuscarContratoRTN3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jBtnBuscarContratoRTN3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnBuscarContratoRTN3ActionPerformed(evt);
            }
        });
        jPanel7.add(jBtnBuscarContratoRTN3);
        jBtnBuscarContratoRTN3.setBounds(280, 70, 210, 30);

        javax.swing.GroupLayout jDialog2Layout = new javax.swing.GroupLayout(jDialog2.getContentPane());
        jDialog2.getContentPane().setLayout(jDialog2Layout);
        jDialog2Layout.setHorizontalGroup(
            jDialog2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, 550, Short.MAX_VALUE)
        );
        jDialog2Layout.setVerticalGroup(
            jDialog2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, 412, Short.MAX_VALUE)
        );

        jDfMenu.setTitle("Confirmar acción");
        jDfMenu.setAlwaysOnTop(true);
        jDfMenu.setModal(true);
        jDfMenu.setModalExclusionType(java.awt.Dialog.ModalExclusionType.APPLICATION_EXCLUDE);
        jDfMenu.setUndecorated(true);
        jDfMenu.setSize(new java.awt.Dimension(300, 200));

        jPanel15.setBackground(new java.awt.Color(255, 255, 255));
        jPanel15.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jLblIdentificador59.setBackground(new java.awt.Color(255, 255, 255));
        jLblIdentificador59.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 1, 20)); // NOI18N
        jLblIdentificador59.setForeground(new java.awt.Color(0, 0, 0));
        jLblIdentificador59.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/menu-negro24.png"))); // NOI18N
        jLblIdentificador59.setText("¿Regresar al menú?");
        jLblIdentificador59.setIconTextGap(10);

        jLblIdentificador71.setBackground(new java.awt.Color(255, 255, 255));
        jLblIdentificador71.setFont(new java.awt.Font("HelveticaNowDisplay Regular", 0, 16)); // NOI18N
        jLblIdentificador71.setForeground(new java.awt.Color(0, 0, 0));
        jLblIdentificador71.setText("Si no guardó el registro actual, su");

        jLblIdentificador73.setBackground(new java.awt.Color(255, 255, 255));
        jLblIdentificador73.setFont(new java.awt.Font("HelveticaNowDisplay Regular", 0, 16)); // NOI18N
        jLblIdentificador73.setForeground(new java.awt.Color(0, 0, 0));
        jLblIdentificador73.setText("progreso se perderá.");

        jBtnSi.setBackground(new java.awt.Color(41, 128, 185));
        jBtnSi.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 0, 16)); // NOI18N
        jBtnSi.setForeground(new java.awt.Color(255, 255, 255));
        jBtnSi.setText("SI");
        jBtnSi.setBorder(null);
        jBtnSi.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jBtnSi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnSiActionPerformed(evt);
            }
        });

        JBtnNo.setBackground(new java.awt.Color(41, 128, 185));
        JBtnNo.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 0, 16)); // NOI18N
        JBtnNo.setForeground(new java.awt.Color(255, 255, 255));
        JBtnNo.setText("NO");
        JBtnNo.setBorder(null);
        JBtnNo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        JBtnNo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBtnNoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLblIdentificador73)
                    .addComponent(jLblIdentificador59)
                    .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel15Layout.createSequentialGroup()
                            .addComponent(jBtnSi, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(JBtnNo, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jLblIdentificador71)))
                .addContainerGap(58, Short.MAX_VALUE))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLblIdentificador59)
                .addGap(18, 18, 18)
                .addComponent(jLblIdentificador71)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLblIdentificador73)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBtnSi, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JBtnNo, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24))
        );

        javax.swing.GroupLayout jDfMenuLayout = new javax.swing.GroupLayout(jDfMenu.getContentPane());
        jDfMenu.getContentPane().setLayout(jDfMenuLayout);
        jDfMenuLayout.setHorizontalGroup(
            jDfMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jDfMenuLayout.setVerticalGroup(
            jDfMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

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
        jLblTitulo2.setText("Pagos del servicio");
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

        jLblMinimizar2.setFont(new java.awt.Font("HelveticaNowDisplay ExtraBold", 0, 30)); // NOI18N
        jLblMinimizar2.setForeground(new java.awt.Color(255, 255, 255));
        jLblMinimizar2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/minimizar-blanco24.png"))); // NOI18N
        jLblMinimizar2.setToolTipText("Minimizar la ventana");
        jLblMinimizar2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLblMinimizar2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLblMinimizar2MouseClicked(evt);
            }
        });
        jPanel3.add(jLblMinimizar2, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 0, -1, 60));

        jLblSalir.setFont(new java.awt.Font("HelveticaNowDisplay ExtraBold", 0, 30)); // NOI18N
        jLblSalir.setForeground(new java.awt.Color(255, 255, 255));
        jLblSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/cerrar-blanco24.png"))); // NOI18N
        jLblSalir.setToolTipText("Cerrar la ventana");
        jLblSalir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLblSalir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLblSalirMouseClicked(evt);
            }
        });
        jPanel3.add(jLblSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 0, -1, 60));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1000, 60));

        jSideBar1.setBackground(new java.awt.Color(52, 73, 94));
        jSideBar1.setBorder(null);

        jSideBar.setBackground(new java.awt.Color(52, 73, 94));
        jSideBar.setPreferredSize(new java.awt.Dimension(260, 600));
        jSideBar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jSBAMECobros.setBackground(new java.awt.Color(52, 152, 219));
        jSBAMECobros.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jSBAMECobros.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jSBAMECobrosMouseClicked(evt);
            }
        });

        jLblIdentificador.setBackground(new java.awt.Color(102, 102, 102));
        jLblIdentificador.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 1, 20)); // NOI18N
        jLblIdentificador.setForeground(new java.awt.Color(255, 255, 255));
        jLblIdentificador.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/cobros-blanco32.png"))); // NOI18N
        jLblIdentificador.setToolTipText("Contratos");

        jLblIdentificador1.setBackground(new java.awt.Color(102, 102, 102));
        jLblIdentificador1.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 1, 20)); // NOI18N
        jLblIdentificador1.setForeground(new java.awt.Color(255, 255, 255));
        jLblIdentificador1.setText("Registrar pago");

        javax.swing.GroupLayout jSBAMECobrosLayout = new javax.swing.GroupLayout(jSBAMECobros);
        jSBAMECobros.setLayout(jSBAMECobrosLayout);
        jSBAMECobrosLayout.setHorizontalGroup(
            jSBAMECobrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jSBAMECobrosLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLblIdentificador1, javax.swing.GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jLblIdentificador, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jSBAMECobrosLayout.setVerticalGroup(
            jSBAMECobrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jSBAMECobrosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jSBAMECobrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLblIdentificador, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
                    .addComponent(jLblIdentificador1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jSideBar.add(jSBAMECobros, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 225, 260, -1));

        jSBFinanzaAbonado.setBackground(new java.awt.Color(52, 73, 94));
        jSBFinanzaAbonado.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jSBFinanzaAbonado.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jSBFinanzaAbonadoMouseClicked(evt);
            }
        });

        jLblIdentificador2.setBackground(new java.awt.Color(102, 102, 102));
        jLblIdentificador2.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 1, 20)); // NOI18N
        jLblIdentificador2.setForeground(new java.awt.Color(255, 255, 255));
        jLblIdentificador2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/pagoabonado-blanco32.png"))); // NOI18N
        jLblIdentificador2.setToolTipText("Listado de contratos");

        jLblIdentificador3.setBackground(new java.awt.Color(102, 102, 102));
        jLblIdentificador3.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 1, 20)); // NOI18N
        jLblIdentificador3.setForeground(new java.awt.Color(255, 255, 255));
        jLblIdentificador3.setText("Pagos del abonado");

        javax.swing.GroupLayout jSBFinanzaAbonadoLayout = new javax.swing.GroupLayout(jSBFinanzaAbonado);
        jSBFinanzaAbonado.setLayout(jSBFinanzaAbonadoLayout);
        jSBFinanzaAbonadoLayout.setHorizontalGroup(
            jSBFinanzaAbonadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jSBFinanzaAbonadoLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLblIdentificador3, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLblIdentificador2)
                .addGap(10, 10, 10))
        );
        jSBFinanzaAbonadoLayout.setVerticalGroup(
            jSBFinanzaAbonadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jSBFinanzaAbonadoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jSBFinanzaAbonadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLblIdentificador2, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
                    .addComponent(jLblIdentificador3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jSideBar.add(jSBFinanzaAbonado, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 299, 260, -1));

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

        jSBListadoPagos.setBackground(new java.awt.Color(52, 73, 94));
        jSBListadoPagos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jSBListadoPagos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jSBListadoPagosMouseClicked(evt);
            }
        });

        jLblIdentificador7.setBackground(new java.awt.Color(102, 102, 102));
        jLblIdentificador7.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 1, 20)); // NOI18N
        jLblIdentificador7.setForeground(new java.awt.Color(255, 255, 255));
        jLblIdentificador7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/tabla-blanco32.png"))); // NOI18N
        jLblIdentificador7.setToolTipText("Listado de contratos");

        jLblIdentificador8.setBackground(new java.awt.Color(102, 102, 102));
        jLblIdentificador8.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 1, 20)); // NOI18N
        jLblIdentificador8.setForeground(new java.awt.Color(255, 255, 255));
        jLblIdentificador8.setText("Listado de pagos");

        javax.swing.GroupLayout jSBListadoPagosLayout = new javax.swing.GroupLayout(jSBListadoPagos);
        jSBListadoPagos.setLayout(jSBListadoPagosLayout);
        jSBListadoPagosLayout.setHorizontalGroup(
            jSBListadoPagosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jSBListadoPagosLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLblIdentificador8, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                .addComponent(jLblIdentificador7)
                .addContainerGap())
        );
        jSBListadoPagosLayout.setVerticalGroup(
            jSBListadoPagosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jSBListadoPagosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jSBListadoPagosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLblIdentificador7, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
                    .addComponent(jLblIdentificador8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jSideBar.add(jSBListadoPagos, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 370, 260, -1));

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

        jSideBar1.setViewportView(jSideBar);

        jPanel1.add(jSideBar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(-210, 0, 270, 625));

        jTabContrato.setBackground(new java.awt.Color(255, 255, 255));
        jTabContrato.setTabPlacement(javax.swing.JTabbedPane.RIGHT);

        jPnlAMECobros.setBackground(new java.awt.Color(255, 255, 255));
        jPnlAMECobros.setLayout(null);

        jLblIdentificador23.setBackground(new java.awt.Color(255, 255, 255));
        jLblIdentificador23.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 1, 20)); // NOI18N
        jLblIdentificador23.setForeground(new java.awt.Color(0, 0, 0));
        jLblIdentificador23.setText("Pegue a registrar el pago:");
        jPnlAMECobros.add(jLblIdentificador23);
        jLblIdentificador23.setBounds(35, 35, 250, 24);

        jBtnGuardar5.setBackground(new java.awt.Color(39, 174, 96));
        jBtnGuardar5.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 0, 16)); // NOI18N
        jBtnGuardar5.setForeground(new java.awt.Color(255, 255, 255));
        jBtnGuardar5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/cruz-blanca24.png"))); // NOI18N
        jBtnGuardar5.setToolTipText("Añadir mes");
        jBtnGuardar5.setBorder(null);
        jBtnGuardar5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPnlAMECobros.add(jBtnGuardar5);
        jBtnGuardar5.setBounds(407, 256, 45, 44);

        jSeparatorY1.setBackground(new java.awt.Color(153, 153, 153));

        javax.swing.GroupLayout jSeparatorY1Layout = new javax.swing.GroupLayout(jSeparatorY1);
        jSeparatorY1.setLayout(jSeparatorY1Layout);
        jSeparatorY1Layout.setHorizontalGroup(
            jSeparatorY1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1, Short.MAX_VALUE)
        );
        jSeparatorY1Layout.setVerticalGroup(
            jSeparatorY1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 496, Short.MAX_VALUE)
        );

        jPnlAMECobros.add(jSeparatorY1);
        jSeparatorY1.setBounds(320, 35, 1, 496);

        jLblIdentificador33.setBackground(new java.awt.Color(255, 255, 255));
        jLblIdentificador33.setFont(new java.awt.Font("HelveticaNowDisplay Medium", 0, 20)); // NOI18N
        jLblIdentificador33.setForeground(new java.awt.Color(0, 0, 0));
        jLblIdentificador33.setText("RTN");
        jPnlAMECobros.add(jLblIdentificador33);
        jLblIdentificador33.setBounds(356, 35, 259, 24);

        jLblIdentificador36.setBackground(new java.awt.Color(255, 255, 255));
        jLblIdentificador36.setFont(new java.awt.Font("HelveticaNowDisplay Medium", 0, 20)); // NOI18N
        jLblIdentificador36.setForeground(new java.awt.Color(0, 0, 0));
        jLblIdentificador36.setText("Bloque");
        jPnlAMECobros.add(jLblIdentificador36);
        jLblIdentificador36.setBounds(356, 124, 259, 24);

        jLblIdentificador39.setBackground(new java.awt.Color(255, 255, 255));
        jLblIdentificador39.setFont(new java.awt.Font("HelveticaNowDisplay Medium", 0, 20)); // NOI18N
        jLblIdentificador39.setForeground(new java.awt.Color(0, 0, 0));
        jLblIdentificador39.setText("Nombre completo");
        jPnlAMECobros.add(jLblIdentificador39);
        jLblIdentificador39.setBounds(640, 35, 265, 24);

        jBtnBuscarContratoBC4.setBackground(new java.awt.Color(41, 128, 185));
        jBtnBuscarContratoBC4.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 0, 16)); // NOI18N
        jBtnBuscarContratoBC4.setForeground(new java.awt.Color(255, 255, 255));
        jBtnBuscarContratoBC4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/search-blanco24.png"))); // NOI18N
        jBtnBuscarContratoBC4.setText("BUSCAR CONTRATO");
        jBtnBuscarContratoBC4.setBorder(null);
        jBtnBuscarContratoBC4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jBtnBuscarContratoBC4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnBuscarContratoBC4ActionPerformed(evt);
            }
        });
        jPnlAMECobros.add(jBtnBuscarContratoBC4);
        jBtnBuscarContratoBC4.setBounds(40, 110, 248, 50);

        jLblIdentificador40.setBackground(new java.awt.Color(255, 255, 255));
        jLblIdentificador40.setFont(new java.awt.Font("HelveticaNowDisplay Medium", 0, 20)); // NOI18N
        jLblIdentificador40.setForeground(new java.awt.Color(0, 0, 0));
        jLblIdentificador40.setText("Meses");
        jPnlAMECobros.add(jLblIdentificador40);
        jLblIdentificador40.setBounds(356, 226, 278, 24);

        jComboBox2.setBackground(new java.awt.Color(255, 255, 255));
        jComboBox2.setFont(new java.awt.Font("HelveticaNowDisplay Regular", 0, 16)); // NOI18N
        jComboBox2.setForeground(new java.awt.Color(0, 0, 0));
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Enero", "Febrero", "Marzo ", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre" }));
        jPnlAMECobros.add(jComboBox2);
        jComboBox2.setBounds(458, 256, 150, 44);

        jBtnGuardar7.setBackground(new java.awt.Color(192, 57, 43));
        jBtnGuardar7.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 0, 16)); // NOI18N
        jBtnGuardar7.setForeground(new java.awt.Color(255, 255, 255));
        jBtnGuardar7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/x-blanca24.png"))); // NOI18N
        jBtnGuardar7.setToolTipText("Quitar mes");
        jBtnGuardar7.setBorder(null);
        jBtnGuardar7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPnlAMECobros.add(jBtnGuardar7);
        jBtnGuardar7.setBounds(356, 256, 45, 44);

        jLblIdentificador41.setBackground(new java.awt.Color(255, 255, 255));
        jLblIdentificador41.setFont(new java.awt.Font("HelveticaNowDisplay Medium", 0, 20)); // NOI18N
        jLblIdentificador41.setForeground(new java.awt.Color(0, 0, 0));
        jLblIdentificador41.setText("Casa");
        jPnlAMECobros.add(jLblIdentificador41);
        jLblIdentificador41.setBounds(640, 124, 265, 24);

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

        jPnlAMECobros.add(jSeparatorX3);
        jSeparatorX3.setBounds(356, 207, 549, 1);

        jTable1.setBackground(new java.awt.Color(255, 255, 255));
        jTable1.setForeground(new java.awt.Color(0, 0, 0));
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "RTN", "Nombre completo", "Bloque", "Casa", "Mes", "Pago"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.getTableHeader().setReorderingAllowed(false);
        jScrollPane3.setViewportView(jTable1);

        jPnlAMECobros.add(jScrollPane3);
        jScrollPane3.setBounds(356, 318, 549, 169);

        jBtnGuardar8.setBackground(new java.awt.Color(41, 128, 185));
        jBtnGuardar8.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 0, 16)); // NOI18N
        jBtnGuardar8.setForeground(new java.awt.Color(255, 255, 255));
        jBtnGuardar8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/guardar-white24.png"))); // NOI18N
        jBtnGuardar8.setText("GUARDAR PAGO");
        jBtnGuardar8.setBorder(null);
        jBtnGuardar8.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPnlAMECobros.add(jBtnGuardar8);
        jBtnGuardar8.setBounds(356, 493, 191, 50);

        jLblIdentificador42.setBackground(new java.awt.Color(255, 255, 255));
        jLblIdentificador42.setFont(new java.awt.Font("HelveticaNowDisplay Medium", 0, 20)); // NOI18N
        jLblIdentificador42.setForeground(new java.awt.Color(0, 0, 0));
        jLblIdentificador42.setText("Total a pagar");
        jPnlAMECobros.add(jLblIdentificador42);
        jLblIdentificador42.setBounds(640, 226, 265, 24);

        jTfIdentidad7.setEditable(false);
        jTfIdentidad7.setBackground(new java.awt.Color(255, 255, 255));
        jTfIdentidad7.setFont(new java.awt.Font("HelveticaNowDisplay Regular", 0, 16)); // NOI18N
        jTfIdentidad7.setForeground(new java.awt.Color(0, 0, 0));
        jTfIdentidad7.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTfIdentidad7.setSelectionColor(new java.awt.Color(0, 153, 153));
        jPnlAMECobros.add(jTfIdentidad7);
        jTfIdentidad7.setBounds(640, 256, 265, 44);

        jTfIdentidad14.setBackground(new java.awt.Color(255, 255, 255));
        jTfIdentidad14.setFont(new java.awt.Font("HelveticaNowDisplay Regular", 0, 16)); // NOI18N
        jTfIdentidad14.setForeground(new java.awt.Color(0, 0, 0));
        jTfIdentidad14.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTfIdentidad14.setSelectionColor(new java.awt.Color(0, 153, 153));
        jPnlAMECobros.add(jTfIdentidad14);
        jTfIdentidad14.setBounds(356, 65, 259, 35);

        jTfNombreCompleto2.setBackground(new java.awt.Color(255, 255, 255));
        jTfNombreCompleto2.setFont(new java.awt.Font("HelveticaNowDisplay Regular", 0, 16)); // NOI18N
        jTfNombreCompleto2.setForeground(new java.awt.Color(0, 0, 0));
        jTfNombreCompleto2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTfNombreCompleto2.setSelectionColor(new java.awt.Color(0, 153, 153));
        jPnlAMECobros.add(jTfNombreCompleto2);
        jTfNombreCompleto2.setBounds(640, 65, 265, 35);

        jTfCasa2.setBackground(new java.awt.Color(255, 255, 255));
        jTfCasa2.setFont(new java.awt.Font("HelveticaNowDisplay Regular", 0, 16)); // NOI18N
        jTfCasa2.setForeground(new java.awt.Color(0, 0, 0));
        jTfCasa2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTfCasa2.setSelectionColor(new java.awt.Color(0, 153, 153));
        jPnlAMECobros.add(jTfCasa2);
        jTfCasa2.setBounds(640, 154, 259, 35);

        jTfBloque2.setBackground(new java.awt.Color(255, 255, 255));
        jTfBloque2.setFont(new java.awt.Font("HelveticaNowDisplay Regular", 0, 16)); // NOI18N
        jTfBloque2.setForeground(new java.awt.Color(0, 0, 0));
        jTfBloque2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTfBloque2.setSelectionColor(new java.awt.Color(0, 153, 153));
        jPnlAMECobros.add(jTfBloque2);
        jTfBloque2.setBounds(356, 154, 259, 35);

        jBtnBuscarContratoRTN4.setBackground(new java.awt.Color(41, 128, 185));
        jBtnBuscarContratoRTN4.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 0, 16)); // NOI18N
        jBtnBuscarContratoRTN4.setForeground(new java.awt.Color(255, 255, 255));
        jBtnBuscarContratoRTN4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/search-blanco24.png"))); // NOI18N
        jBtnBuscarContratoRTN4.setText("BUSCAR CONTRATO");
        jBtnBuscarContratoRTN4.setBorder(null);
        jBtnBuscarContratoRTN4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jBtnBuscarContratoRTN4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnBuscarContratoRTN4ActionPerformed(evt);
            }
        });
        jPnlAMECobros.add(jBtnBuscarContratoRTN4);
        jBtnBuscarContratoRTN4.setBounds(40, 110, 248, 50);

        jRBBC.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup3.add(jRBBC);
        jRBBC.setFont(new java.awt.Font("HelveticaNowDisplay Regular", 0, 12)); // NOI18N
        jRBBC.setForeground(new java.awt.Color(0, 0, 0));
        jRBBC.setSelected(true);
        jRBBC.setText("Bloque y casa");
        jRBBC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRBBCActionPerformed(evt);
            }
        });
        jPnlAMECobros.add(jRBBC);
        jRBBC.setBounds(40, 70, 103, 28);

        jRBRTN2.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup3.add(jRBRTN2);
        jRBRTN2.setFont(new java.awt.Font("HelveticaNowDisplay Regular", 0, 12)); // NOI18N
        jRBRTN2.setForeground(new java.awt.Color(0, 0, 0));
        jRBRTN2.setText("RTN");
        jRBRTN2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRBRTN2ActionPerformed(evt);
            }
        });
        jPnlAMECobros.add(jRBRTN2);
        jRBRTN2.setBounds(170, 70, 55, 28);

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
        jPnlAMECobros.add(jLblCancelarEdicion);
        jLblCancelarEdicion.setBounds(40, 170, 150, 19);

        jTabContrato.addTab("tab1", jPnlAMECobros);

        jPnlFinanzaAbonado.setBackground(new java.awt.Color(255, 255, 255));
        jPnlFinanzaAbonado.setLayout(null);

        jLblIdentificador43.setBackground(new java.awt.Color(255, 255, 255));
        jLblIdentificador43.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 1, 20)); // NOI18N
        jLblIdentificador43.setForeground(new java.awt.Color(0, 0, 0));
        jLblIdentificador43.setText("Buscar un contrato:");
        jPnlFinanzaAbonado.add(jLblIdentificador43);
        jLblIdentificador43.setBounds(27, 26, 240, 24);

        jSeparatorY2.setBackground(new java.awt.Color(153, 153, 153));

        javax.swing.GroupLayout jSeparatorY2Layout = new javax.swing.GroupLayout(jSeparatorY2);
        jSeparatorY2.setLayout(jSeparatorY2Layout);
        jSeparatorY2Layout.setHorizontalGroup(
            jSeparatorY2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1, Short.MAX_VALUE)
        );
        jSeparatorY2Layout.setVerticalGroup(
            jSeparatorY2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 496, Short.MAX_VALUE)
        );

        jPnlFinanzaAbonado.add(jSeparatorY2);
        jSeparatorY2.setBounds(305, 26, 1, 496);

        jLblIdentificador46.setBackground(new java.awt.Color(255, 255, 255));
        jLblIdentificador46.setFont(new java.awt.Font("HelveticaNowDisplay Medium", 0, 20)); // NOI18N
        jLblIdentificador46.setForeground(new java.awt.Color(0, 0, 0));
        jLblIdentificador46.setText("Nombre completo");
        jPnlFinanzaAbonado.add(jLblIdentificador46);
        jLblIdentificador46.setBounds(639, 22, 280, 21);

        jTfIdentidad.setEditable(false);
        jTfIdentidad.setBackground(new java.awt.Color(255, 255, 255));
        jTfIdentidad.setFont(new java.awt.Font("HelveticaNowDisplay Regular", 0, 16)); // NOI18N
        jTfIdentidad.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTfIdentidad.setSelectionColor(new java.awt.Color(0, 153, 153));
        jPnlFinanzaAbonado.add(jTfIdentidad);
        jTfIdentidad.setBounds(423, 55, 190, 35);

        jTfNombreCompleto.setEditable(false);
        jTfNombreCompleto.setBackground(new java.awt.Color(255, 255, 255));
        jTfNombreCompleto.setFont(new java.awt.Font("HelveticaNowDisplay Regular", 0, 16)); // NOI18N
        jTfNombreCompleto.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTfNombreCompleto.setSelectionColor(new java.awt.Color(0, 153, 153));
        jPnlFinanzaAbonado.add(jTfNombreCompleto);
        jTfNombreCompleto.setBounds(639, 55, 280, 35);

        jLblIdentificador47.setBackground(new java.awt.Color(255, 255, 255));
        jLblIdentificador47.setFont(new java.awt.Font("HelveticaNowDisplay Medium", 0, 20)); // NOI18N
        jLblIdentificador47.setForeground(new java.awt.Color(0, 0, 0));
        jLblIdentificador47.setText("Bloque");
        jPnlFinanzaAbonado.add(jLblIdentificador47);
        jLblIdentificador47.setBounds(340, 110, 272, 21);

        jLblIdentificador48.setBackground(new java.awt.Color(255, 255, 255));
        jLblIdentificador48.setFont(new java.awt.Font("HelveticaNowDisplay Medium", 0, 20)); // NOI18N
        jLblIdentificador48.setForeground(new java.awt.Color(0, 0, 0));
        jLblIdentificador48.setText("Número de identidad");
        jPnlFinanzaAbonado.add(jLblIdentificador48);
        jLblIdentificador48.setBounds(423, 26, 190, 21);

        jTfBloque.setEditable(false);
        jTfBloque.setBackground(new java.awt.Color(255, 255, 255));
        jTfBloque.setFont(new java.awt.Font("HelveticaNowDisplay Regular", 0, 16)); // NOI18N
        jTfBloque.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTfBloque.setSelectionColor(new java.awt.Color(0, 153, 153));
        jPnlFinanzaAbonado.add(jTfBloque);
        jTfBloque.setBounds(340, 140, 272, 35);

        jTfCasa.setEditable(false);
        jTfCasa.setBackground(new java.awt.Color(255, 255, 255));
        jTfCasa.setFont(new java.awt.Font("HelveticaNowDisplay Regular", 0, 16)); // NOI18N
        jTfCasa.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTfCasa.setSelectionColor(new java.awt.Color(0, 153, 153));
        jPnlFinanzaAbonado.add(jTfCasa);
        jTfCasa.setBounds(640, 140, 277, 35);

        jLblIdentificador49.setBackground(new java.awt.Color(255, 255, 255));
        jLblIdentificador49.setFont(new java.awt.Font("HelveticaNowDisplay Medium", 0, 20)); // NOI18N
        jLblIdentificador49.setForeground(new java.awt.Color(0, 0, 0));
        jLblIdentificador49.setText("Casa");
        jPnlFinanzaAbonado.add(jLblIdentificador49);
        jLblIdentificador49.setBounds(640, 110, 277, 21);

        jLblIdentificador50.setBackground(new java.awt.Color(255, 255, 255));
        jLblIdentificador50.setFont(new java.awt.Font("HelveticaNowDisplay Medium", 0, 20)); // NOI18N
        jLblIdentificador50.setForeground(new java.awt.Color(0, 0, 0));
        jLblIdentificador50.setText("Estado");
        jPnlFinanzaAbonado.add(jLblIdentificador50);
        jLblIdentificador50.setBounds(639, 197, 70, 21);

        jLblIdentificador51.setBackground(new java.awt.Color(255, 255, 255));
        jLblIdentificador51.setFont(new java.awt.Font("HelveticaNowDisplay Medium", 0, 20)); // NOI18N
        jLblIdentificador51.setForeground(new java.awt.Color(0, 0, 0));
        jLblIdentificador51.setText("Meses pagados");
        jPnlFinanzaAbonado.add(jLblIdentificador51);
        jLblIdentificador51.setBounds(339, 197, 267, 21);

        jTxtAEstado.setEditable(false);
        jTxtAEstado.setBackground(java.awt.Color.white);
        jTxtAEstado.setColumns(20);
        jTxtAEstado.setFont(new java.awt.Font("HelveticaNowDisplay Regular", 0, 16)); // NOI18N
        jTxtAEstado.setForeground(new java.awt.Color(0, 0, 0));
        jTxtAEstado.setRows(5);
        jTxtAEstado.setText("Este abonado se encuentra en una \nmora de 3 meses.");
        jTxtAEstado.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jScrollPane5.setViewportView(jTxtAEstado);

        jPnlFinanzaAbonado.add(jScrollPane5);
        jScrollPane5.setBounds(639, 224, 276, 150);

        jLblIdentificador52.setBackground(new java.awt.Color(102, 102, 102));
        jLblIdentificador52.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 1, 20)); // NOI18N
        jLblIdentificador52.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/hombre-joven-64.png"))); // NOI18N
        jLblIdentificador52.setToolTipText("Masculino");
        jPnlFinanzaAbonado.add(jLblIdentificador52);
        jLblIdentificador52.setBounds(341, 26, 64, 64);

        jLblGenerarPlanPago.setBackground(new java.awt.Color(102, 102, 102));
        jLblGenerarPlanPago.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 1, 20)); // NOI18N
        jLblGenerarPlanPago.setForeground(new java.awt.Color(41, 128, 185));
        jLblGenerarPlanPago.setText("Generar plan de pago");
        jLblGenerarPlanPago.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLblGenerarPlanPago.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLblGenerarPlanPagoMouseClicked(evt);
            }
        });
        jPnlFinanzaAbonado.add(jLblGenerarPlanPago);
        jLblGenerarPlanPago.setBounds(720, 380, 195, 21);

        jLblIdentificador54.setBackground(new java.awt.Color(102, 102, 102));
        jLblIdentificador54.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 1, 20)); // NOI18N
        jLblIdentificador54.setForeground(new java.awt.Color(41, 128, 185));
        jLblIdentificador54.setText("Ver estado de mora");
        jLblIdentificador54.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPnlFinanzaAbonado.add(jLblIdentificador54);
        jLblIdentificador54.setBounds(736, 412, 179, 21);

        jBtnEditar1.setBackground(new java.awt.Color(41, 128, 185));
        jBtnEditar1.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 0, 16)); // NOI18N
        jBtnEditar1.setForeground(new java.awt.Color(255, 255, 255));
        jBtnEditar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/editar-blanco32.png"))); // NOI18N
        jBtnEditar1.setText("EDITAR PAGO");
        jBtnEditar1.setBorder(null);
        jBtnEditar1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPnlFinanzaAbonado.add(jBtnEditar1);
        jBtnEditar1.setBounds(339, 472, 272, 50);

        jLblIdentificador55.setBackground(new java.awt.Color(102, 102, 102));
        jLblIdentificador55.setFont(new java.awt.Font("HelveticaNowDisplay Medium", 0, 14)); // NOI18N
        jLblIdentificador55.setForeground(new java.awt.Color(153, 153, 153));
        jLblIdentificador55.setText("Me equivoqué en el registro de un pago");
        jLblIdentificador55.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPnlFinanzaAbonado.add(jLblIdentificador55);
        jLblIdentificador55.setBounds(339, 451, 272, 15);

        jRBBloqueYCasa.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup2.add(jRBBloqueYCasa);
        jRBBloqueYCasa.setFont(new java.awt.Font("HelveticaNowDisplay Regular", 0, 12)); // NOI18N
        jRBBloqueYCasa.setForeground(new java.awt.Color(0, 0, 0));
        jRBBloqueYCasa.setSelected(true);
        jRBBloqueYCasa.setText("Bloque y  casa");
        jRBBloqueYCasa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRBBloqueYCasaActionPerformed(evt);
            }
        });
        jPnlFinanzaAbonado.add(jRBBloqueYCasa);
        jRBBloqueYCasa.setBounds(30, 60, 105, 28);

        jRBRTN.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup2.add(jRBRTN);
        jRBRTN.setFont(new java.awt.Font("HelveticaNowDisplay Regular", 0, 12)); // NOI18N
        jRBRTN.setForeground(new java.awt.Color(0, 0, 0));
        jRBRTN.setText("RTN");
        jRBRTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRBRTNActionPerformed(evt);
            }
        });
        jPnlFinanzaAbonado.add(jRBRTN);
        jRBRTN.setBounds(180, 60, 100, 30);

        jTblMesesPagados.setForeground(new java.awt.Color(0, 0, 0));
        jTblMesesPagados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mes", "Precio"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane7.setViewportView(jTblMesesPagados);

        jPnlFinanzaAbonado.add(jScrollPane7);
        jScrollPane7.setBounds(339, 224, 267, 150);
        jPnlFinanzaAbonado.add(jLblNumPagado);
        jLblNumPagado.setBounds(730, 200, 20, 20);

        jBtnBuscarContratoBC1.setBackground(new java.awt.Color(41, 128, 185));
        jBtnBuscarContratoBC1.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 0, 16)); // NOI18N
        jBtnBuscarContratoBC1.setForeground(new java.awt.Color(255, 255, 255));
        jBtnBuscarContratoBC1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/search-blanco24.png"))); // NOI18N
        jBtnBuscarContratoBC1.setText("BUSCAR CONTRATO");
        jBtnBuscarContratoBC1.setBorder(null);
        jBtnBuscarContratoBC1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jBtnBuscarContratoBC1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnBuscarContratoBC1ActionPerformed(evt);
            }
        });
        jPnlFinanzaAbonado.add(jBtnBuscarContratoBC1);
        jBtnBuscarContratoBC1.setBounds(30, 100, 250, 50);

        jBtnBuscarContratoRTN1.setBackground(new java.awt.Color(41, 128, 185));
        jBtnBuscarContratoRTN1.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 0, 16)); // NOI18N
        jBtnBuscarContratoRTN1.setForeground(new java.awt.Color(255, 255, 255));
        jBtnBuscarContratoRTN1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/search-blanco24.png"))); // NOI18N
        jBtnBuscarContratoRTN1.setText("BUSCAR CONTRATO");
        jBtnBuscarContratoRTN1.setBorder(null);
        jBtnBuscarContratoRTN1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jBtnBuscarContratoRTN1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnBuscarContratoRTN1ActionPerformed(evt);
            }
        });
        jPnlFinanzaAbonado.add(jBtnBuscarContratoRTN1);
        jBtnBuscarContratoRTN1.setBounds(30, 100, 250, 50);

        jTabContrato.addTab("tab2", jPnlFinanzaAbonado);

        jPnlListadoPagos.setBackground(new java.awt.Color(255, 255, 255));
        jPnlListadoPagos.setLayout(null);

        jLblIdentificador11.setBackground(new java.awt.Color(255, 255, 255));
        jLblIdentificador11.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 1, 20)); // NOI18N
        jLblIdentificador11.setForeground(new java.awt.Color(0, 0, 0));
        jLblIdentificador11.setText("Ver pagos por:");
        jPnlListadoPagos.add(jLblIdentificador11);
        jLblIdentificador11.setBounds(35, 35, 134, 40);

        jTblPagos.setBackground(new java.awt.Color(255, 255, 255));
        jTblPagos.setFont(new java.awt.Font("HelveticaNowDisplay Regular", 0, 14)); // NOI18N
        jTblPagos.setForeground(new java.awt.Color(0, 0, 0));
        jTblPagos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Número de identidad", "Nombre Completo", "Bloque", "Casa", "Tipo de Contrato", "Mes", "Valor Pago"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane6.setViewportView(jTblPagos);

        jPnlListadoPagos.add(jScrollPane6);
        jScrollPane6.setBounds(35, 95, 874, 440);

        jRBPagosMes.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(jRBPagosMes);
        jRBPagosMes.setFont(new java.awt.Font("HelveticaNowDisplay Light", 0, 14)); // NOI18N
        jRBPagosMes.setForeground(new java.awt.Color(0, 0, 0));
        jRBPagosMes.setSelected(true);
        jRBPagosMes.setText("Pagos en el mes");
        jRBPagosMes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRBPagosMesActionPerformed(evt);
            }
        });
        jPnlListadoPagos.add(jRBPagosMes);
        jRBPagosMes.setBounds(187, 37, 132, 40);

        jRBPagosRangoFecha.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(jRBPagosRangoFecha);
        jRBPagosRangoFecha.setFont(new java.awt.Font("HelveticaNowDisplay Light", 0, 14)); // NOI18N
        jRBPagosRangoFecha.setForeground(new java.awt.Color(0, 0, 0));
        jRBPagosRangoFecha.setText("Rango de fecha");
        jRBPagosRangoFecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRBPagosRangoFechaActionPerformed(evt);
            }
        });
        jPnlListadoPagos.add(jRBPagosRangoFecha);
        jRBPagosRangoFecha.setBounds(330, 37, 126, 40);

        jPnlBuscar.setBackground(new java.awt.Color(255, 255, 255));
        jPnlBuscar.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTfBuscar.setFont(new java.awt.Font("HelveticaNowDisplay Regular", 0, 16)); // NOI18N
        jTfBuscar.setBorder(null);
        jTfBuscar.setSelectionColor(new java.awt.Color(0, 153, 153));

        jBtnBuscar.setBackground(new java.awt.Color(9, 132, 227));
        jBtnBuscar.setFont(new java.awt.Font("HelveticaNowDisplay Regular", 1, 18)); // NOI18N
        jBtnBuscar.setForeground(new java.awt.Color(9, 132, 227));
        jBtnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/search-black24.png"))); // NOI18N
        jBtnBuscar.setToolTipText("Click para buscar");
        jBtnBuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jBtnBuscar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jBtnBuscarMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPnlBuscarLayout = new javax.swing.GroupLayout(jPnlBuscar);
        jPnlBuscar.setLayout(jPnlBuscarLayout);
        jPnlBuscarLayout.setHorizontalGroup(
            jPnlBuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPnlBuscarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTfBuscar)
                .addGap(18, 18, 18)
                .addComponent(jBtnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPnlBuscarLayout.setVerticalGroup(
            jPnlBuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTfBuscar)
            .addComponent(jBtnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jPnlListadoPagos.add(jPnlBuscar);
        jPnlBuscar.setBounds(592, 35, 317, 40);

        jPnlRangoFecha.setBackground(new java.awt.Color(255, 255, 255));
        jPnlRangoFecha.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jBtnBuscarRangoFecha.setBackground(new java.awt.Color(9, 132, 227));
        jBtnBuscarRangoFecha.setFont(new java.awt.Font("HelveticaNowDisplay Regular", 0, 18)); // NOI18N
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

        jLblIdentificador57.setBackground(new java.awt.Color(255, 255, 255));
        jLblIdentificador57.setFont(new java.awt.Font("HelveticaNowDisplay Regular", 0, 14)); // NOI18N
        jLblIdentificador57.setForeground(new java.awt.Color(0, 0, 0));
        jLblIdentificador57.setText("Fecha 1");

        jLblIdentificador58.setBackground(new java.awt.Color(255, 255, 255));
        jLblIdentificador58.setFont(new java.awt.Font("HelveticaNowDisplay Regular", 0, 14)); // NOI18N
        jLblIdentificador58.setForeground(new java.awt.Color(0, 0, 0));
        jLblIdentificador58.setText("Fecha 2");

        javax.swing.GroupLayout jPnlRangoFechaLayout = new javax.swing.GroupLayout(jPnlRangoFecha);
        jPnlRangoFecha.setLayout(jPnlRangoFechaLayout);
        jPnlRangoFechaLayout.setHorizontalGroup(
            jPnlRangoFechaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPnlRangoFechaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPnlRangoFechaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jDCFecha1, javax.swing.GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE)
                    .addGroup(jPnlRangoFechaLayout.createSequentialGroup()
                        .addComponent(jLblIdentificador57, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPnlRangoFechaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jDCFecha2, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLblIdentificador58, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                            .addComponent(jLblIdentificador57, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLblIdentificador58, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
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

        jPnlListadoPagos.add(jPnlRangoFecha);
        jPnlRangoFecha.setBounds(580, 20, 338, 69);

        jTabContrato.addTab("tab4", jPnlListadoPagos);

        jPnlAMECobros1.setBackground(new java.awt.Color(255, 255, 255));

        jLblIdentificador15.setBackground(new java.awt.Color(102, 102, 102));
        jLblIdentificador15.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 1, 20)); // NOI18N
        jLblIdentificador15.setText("Pegue a registrar el pago:");

        jLblIdentificador16.setBackground(new java.awt.Color(102, 102, 102));
        jLblIdentificador16.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 1, 20)); // NOI18N
        jLblIdentificador16.setText("Bloque");

        jTfIdentidad2.setFont(new java.awt.Font("HelveticaNowDisplay Regular", 0, 16)); // NOI18N
        jTfIdentidad2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTfIdentidad2.setSelectionColor(new java.awt.Color(0, 153, 153));

        jLblIdentificador17.setBackground(new java.awt.Color(102, 102, 102));
        jLblIdentificador17.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 1, 20)); // NOI18N
        jLblIdentificador17.setText("Casa");

        jTfIdentidad3.setFont(new java.awt.Font("HelveticaNowDisplay Regular", 0, 16)); // NOI18N
        jTfIdentidad3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTfIdentidad3.setSelectionColor(new java.awt.Color(0, 153, 153));

        jBtnGuardar.setBackground(new java.awt.Color(39, 174, 96));
        jBtnGuardar.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 0, 16)); // NOI18N
        jBtnGuardar.setForeground(new java.awt.Color(255, 255, 255));
        jBtnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/cruz-blanca24.png"))); // NOI18N
        jBtnGuardar.setToolTipText("Añadir mes");
        jBtnGuardar.setBorder(null);
        jBtnGuardar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jSeparatorY.setBackground(new java.awt.Color(153, 153, 153));

        javax.swing.GroupLayout jSeparatorYLayout = new javax.swing.GroupLayout(jSeparatorY);
        jSeparatorY.setLayout(jSeparatorYLayout);
        jSeparatorYLayout.setHorizontalGroup(
            jSeparatorYLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1, Short.MAX_VALUE)
        );
        jSeparatorYLayout.setVerticalGroup(
            jSeparatorYLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 496, Short.MAX_VALUE)
        );

        jLblIdentificador18.setBackground(new java.awt.Color(102, 102, 102));
        jLblIdentificador18.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 1, 20)); // NOI18N
        jLblIdentificador18.setText("RTN");

        jLblIdentificador19.setBackground(new java.awt.Color(102, 102, 102));
        jLblIdentificador19.setFont(new java.awt.Font("HelveticaNowDisplay Regular", 0, 20)); // NOI18N
        jLblIdentificador19.setText("06XX-19XX-00XXX");

        jLblIdentificador20.setBackground(new java.awt.Color(102, 102, 102));
        jLblIdentificador20.setFont(new java.awt.Font("HelveticaNowDisplay Regular", 0, 20)); // NOI18N
        jLblIdentificador20.setText("36");

        jLblIdentificador21.setBackground(new java.awt.Color(102, 102, 102));
        jLblIdentificador21.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 1, 20)); // NOI18N
        jLblIdentificador21.setText("Bloque");

        jLblIdentificador22.setBackground(new java.awt.Color(102, 102, 102));
        jLblIdentificador22.setFont(new java.awt.Font("HelveticaNowDisplay Regular", 0, 20)); // NOI18N
        jLblIdentificador22.setText("299");

        jLblIdentificador24.setBackground(new java.awt.Color(102, 102, 102));
        jLblIdentificador24.setFont(new java.awt.Font("HelveticaNowDisplay Regular", 0, 20)); // NOI18N
        jLblIdentificador24.setText("Nombre del abonado aquí");

        jLblIdentificador25.setBackground(new java.awt.Color(102, 102, 102));
        jLblIdentificador25.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 1, 20)); // NOI18N
        jLblIdentificador25.setText("Nombre completo");

        jBtnGuardar1.setBackground(new java.awt.Color(41, 128, 185));
        jBtnGuardar1.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 0, 16)); // NOI18N
        jBtnGuardar1.setForeground(new java.awt.Color(255, 255, 255));
        jBtnGuardar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/search-blanco24.png"))); // NOI18N
        jBtnGuardar1.setText("BUSCAR CONTRATO");
        jBtnGuardar1.setBorder(null);
        jBtnGuardar1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jLblIdentificador26.setBackground(new java.awt.Color(102, 102, 102));
        jLblIdentificador26.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 1, 20)); // NOI18N
        jLblIdentificador26.setText("Meses");

        jComboBox1.setFont(new java.awt.Font("HelveticaNowDisplay Thin", 0, 14)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Enero", "Febrero", "Marzo ", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre" }));

        jBtnGuardar2.setBackground(new java.awt.Color(192, 57, 43));
        jBtnGuardar2.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 0, 16)); // NOI18N
        jBtnGuardar2.setForeground(new java.awt.Color(255, 255, 255));
        jBtnGuardar2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/x-blanca24.png"))); // NOI18N
        jBtnGuardar2.setToolTipText("Quitar mes");
        jBtnGuardar2.setBorder(null);
        jBtnGuardar2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jLblIdentificador27.setBackground(new java.awt.Color(102, 102, 102));
        jLblIdentificador27.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 1, 20)); // NOI18N
        jLblIdentificador27.setText("Casa");

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

        jTfIdentidad5.setEditable(false);
        jTfIdentidad5.setBackground(new java.awt.Color(255, 255, 255));
        jTfIdentidad5.setFont(new java.awt.Font("HelveticaNowDisplay Regular", 0, 16)); // NOI18N
        jTfIdentidad5.setText("L. 480.00");
        jTfIdentidad5.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTfIdentidad5.setSelectionColor(new java.awt.Color(0, 153, 153));

        jLblIdentificador28.setBackground(new java.awt.Color(102, 102, 102));
        jLblIdentificador28.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 1, 20)); // NOI18N
        jLblIdentificador28.setText("Total a pagar");

        jBtnGuardar4.setBackground(new java.awt.Color(41, 128, 185));
        jBtnGuardar4.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 0, 16)); // NOI18N
        jBtnGuardar4.setForeground(new java.awt.Color(255, 255, 255));
        jBtnGuardar4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/guardar-white24.png"))); // NOI18N
        jBtnGuardar4.setText("GUARDAR PAGO");
        jBtnGuardar4.setBorder(null);
        jBtnGuardar4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jLblIdentificador29.setBackground(new java.awt.Color(102, 102, 102));
        jLblIdentificador29.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 1, 20)); // NOI18N
        jLblIdentificador29.setText("Meses a pagar");

        jLblIdentificador30.setBackground(new java.awt.Color(102, 102, 102));
        jLblIdentificador30.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 1, 20)); // NOI18N
        jLblIdentificador30.setText("Subtotal");

        jList2.setFont(new java.awt.Font("HelveticaNowDisplay Regular", 0, 14)); // NOI18N
        jList2.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Mensualidad del agua: L.300", "Vigilancia: L.90" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane2.setViewportView(jList2);

        javax.swing.GroupLayout jPnlAMECobros1Layout = new javax.swing.GroupLayout(jPnlAMECobros1);
        jPnlAMECobros1.setLayout(jPnlAMECobros1Layout);
        jPnlAMECobros1Layout.setHorizontalGroup(
            jPnlAMECobros1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPnlAMECobros1Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(jPnlAMECobros1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPnlAMECobros1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jTfIdentidad3)
                        .addComponent(jLblIdentificador17, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPnlAMECobros1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jTfIdentidad2)
                        .addComponent(jLblIdentificador16, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                        .addComponent(jLblIdentificador15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(35, 35, 35)
                .addComponent(jSeparatorY, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addGroup(jPnlAMECobros1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPnlAMECobros1Layout.createSequentialGroup()
                        .addGroup(jPnlAMECobros1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLblIdentificador26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPnlAMECobros1Layout.createSequentialGroup()
                                .addComponent(jBtnGuardar2, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jBtnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLblIdentificador29, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPnlAMECobros1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTfIdentidad5)
                            .addComponent(jLblIdentificador30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPnlAMECobros1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jBtnGuardar4, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLblIdentificador28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 271, Short.MAX_VALUE)))
                    .addComponent(jSeparatorX2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPnlAMECobros1Layout.createSequentialGroup()
                        .addGroup(jPnlAMECobros1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLblIdentificador20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLblIdentificador21, javax.swing.GroupLayout.DEFAULT_SIZE, 259, Short.MAX_VALUE)
                            .addComponent(jLblIdentificador18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLblIdentificador19, javax.swing.GroupLayout.DEFAULT_SIZE, 259, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPnlAMECobros1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLblIdentificador22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLblIdentificador27, javax.swing.GroupLayout.DEFAULT_SIZE, 265, Short.MAX_VALUE)
                            .addComponent(jLblIdentificador24, javax.swing.GroupLayout.DEFAULT_SIZE, 265, Short.MAX_VALUE)
                            .addComponent(jLblIdentificador25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(72, Short.MAX_VALUE))
            .addGroup(jPnlAMECobros1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPnlAMECobros1Layout.createSequentialGroup()
                    .addGap(37, 37, 37)
                    .addComponent(jBtnGuardar1, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(703, Short.MAX_VALUE)))
        );
        jPnlAMECobros1Layout.setVerticalGroup(
            jPnlAMECobros1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPnlAMECobros1Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(jPnlAMECobros1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPnlAMECobros1Layout.createSequentialGroup()
                        .addComponent(jLblIdentificador15, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLblIdentificador16, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTfIdentidad2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLblIdentificador17, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTfIdentidad3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPnlAMECobros1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(jPnlAMECobros1Layout.createSequentialGroup()
                            .addGroup(jPnlAMECobros1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPnlAMECobros1Layout.createSequentialGroup()
                                    .addComponent(jLblIdentificador18, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLblIdentificador19, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPnlAMECobros1Layout.createSequentialGroup()
                                    .addComponent(jLblIdentificador25, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLblIdentificador24, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGap(18, 18, 18)
                            .addGroup(jPnlAMECobros1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPnlAMECobros1Layout.createSequentialGroup()
                                    .addComponent(jLblIdentificador21, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLblIdentificador20, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPnlAMECobros1Layout.createSequentialGroup()
                                    .addGap(6, 6, 6)
                                    .addComponent(jLblIdentificador27, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLblIdentificador22, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGap(18, 18, Short.MAX_VALUE)
                            .addComponent(jSeparatorX2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addGroup(jPnlAMECobros1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLblIdentificador26, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLblIdentificador30, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPnlAMECobros1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPnlAMECobros1Layout.createSequentialGroup()
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                                    .addComponent(jLblIdentificador28, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jTfIdentidad5, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jBtnGuardar4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPnlAMECobros1Layout.createSequentialGroup()
                                    .addGroup(jPnlAMECobros1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jBtnGuardar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jComboBox1)
                                        .addComponent(jBtnGuardar2, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(18, 18, 18)
                                    .addComponent(jLblIdentificador29, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(0, 0, Short.MAX_VALUE))))
                        .addComponent(jSeparatorY, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(31, Short.MAX_VALUE))
            .addGroup(jPnlAMECobros1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPnlAMECobros1Layout.createSequentialGroup()
                    .addGap(253, 253, 253)
                    .addComponent(jBtnGuardar1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(263, Short.MAX_VALUE)))
        );

        jTabContrato.addTab("tab1", jPnlAMECobros1);

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

    private void jRBBloqueYCasaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRBBloqueYCasaActionPerformed
        if(this.jRBBloqueYCasa.isSelected()== true){
            this.jLblBloque.setVisible(true);
            this.jLblCasa.setVisible(true);
            this.jTfBloqueB.setVisible(true);
            this.jLblRTN.setVisible(false);
            this.jTfCasaB.setVisible(true);
            this.jTfRTN.setVisible(false);
            this.jBtnBuscarContratoBC1.setVisible(true);
            this.jBtnBuscarContratoBC.setVisible(true);
            this.jBtnBuscarContratoRTN1.setVisible(false);
            this.jBtnBuscarContratoRTN.setVisible(false);
            this.jTfRTN.setText("");
        }
    }//GEN-LAST:event_jRBBloqueYCasaActionPerformed

    private void jRBRTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRBRTNActionPerformed
        if(this.jRBRTN.isSelected()== true){
            this.jLblBloque.setVisible(false);
            this.jTfBloqueB.setVisible(false);
            this.jTfBloqueB.setText("");
            this.jLblCasa.setVisible(false);
            this.jTfCasaB.setVisible(false);
            this.jTfCasaB.setText("");
            this.jLblRTN.setVisible(true);
            this.jTfRTN.setVisible(true);
            this.jBtnBuscarContratoBC1.setVisible(false);
            this.jBtnBuscarContratoBC.setVisible(false);
            this.jBtnBuscarContratoRTN1.setVisible(true);
            this.jBtnBuscarContratoRTN.setVisible(true);
        }
    }//GEN-LAST:event_jRBRTNActionPerformed

    private void jBtnBuscarContratoBCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnBuscarContratoBCActionPerformed
        String numBloque, numCasa;

        numBloque = this.jTfBloqueB.getText();
        numCasa = this.jTfCasaB.getText();
        
        if (this.jTfBloqueB.getText().trim().equals("")) {
            //JOptionPane.showMessageDialog(null, "Ingrese el número de bloque", "Saja", JOptionPane.INFORMATION_MESSAGE);
            this.jTfBloqueB.requestFocus();
        } else if(this.jTfCasaB.getText().trim().equals("")){
            //JOptionPane.showMessageDialog(null, "Ingrese el número de casa", "Saja", JOptionPane.INFORMATION_MESSAGE);
            this.jTfCasaB.requestFocus();
        }else {
            try {
                llenarTablaDatosContratoBC(numBloque, numCasa);
                this.jDialog1.setVisible(true);
                this.jTfBloqueB.setText("");
                this.jTfCasaB.setText("");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error fill table: " + ex);
            }
        }
    }//GEN-LAST:event_jBtnBuscarContratoBCActionPerformed

    private void jBtnBuscarContratoRTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnBuscarContratoRTNActionPerformed
        String RTN;

        RTN = this.jTfRTN.getText();
        
        if (this.jTfRTN.getText().trim().equals("")) {
            //JOptionPane.showMessageDialog(null, "Ingrese el RTN", "Saja", JOptionPane.INFORMATION_MESSAGE);
            this.jTfRTN.requestFocus();
        } else {
            try {
                llenarTablaDatosContratoRTN(RTN);
                this.jDialog1.setVisible(true);
                this.jTfRTN.setText("");

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error fill table: " + ex);
            }
        }
    }//GEN-LAST:event_jBtnBuscarContratoRTNActionPerformed

    private void jBtnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnCancelarActionPerformed
        limpiarTablaDatosContrato();
        this.jDialog1.setVisible(false);
    }//GEN-LAST:event_jBtnCancelarActionPerformed

    private void jBtnSeleccionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnSeleccionarActionPerformed
        seleccionarFilas();
        try {
            llenarTablaMesesPagados(this.jTfIdentidad.getText());
            numMesPagado(this.jTfIdentidad.getText());
        } catch (SQLException ex) {
            Logger.getLogger(JFraCobros.class.getName()).log(Level.SEVERE, null, ex);
        }
        limpiarTablaDatosContrato();
        this.jDialog1.setVisible(false);
    }//GEN-LAST:event_jBtnSeleccionarActionPerformed

    private void jRBPagosMesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRBPagosMesActionPerformed
        if(this.jRBPagosMes.isSelected()== true){
            this.jPnlBuscar.setVisible(true);
            this.jPnlRangoFecha.setVisible(false);
            this.jBtnBuscarRangoFecha.setVisible(false);
            this.jBtnBuscar.setVisible(true);
        }
    }//GEN-LAST:event_jRBPagosMesActionPerformed

    private void jRBPagosRangoFechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRBPagosRangoFechaActionPerformed
        if(this.jRBPagosRangoFecha.isSelected()== true){
            this.jPnlBuscar.setVisible(false);
            this.jPnlRangoFecha.setVisible(true);
            this.jBtnBuscar.setVisible(false);
            this.jBtnBuscarRangoFecha.setVisible(true);
        }
    }//GEN-LAST:event_jRBPagosRangoFechaActionPerformed

    private void jBtnBuscarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBtnBuscarMouseClicked
        String mes;

        mes = this.jTfBuscar.getText();
        
        if (this.jTfBuscar.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Ingrese el mes que desea filtrar", "Saja", JOptionPane.INFORMATION_MESSAGE);
            this.jTfBuscar.requestFocus();
        } else {
            try {
                mostrarPagosMes(mes);
                this.jTfBuscar.setText("");

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error fill table: " + ex);
            }
        }
    }//GEN-LAST:event_jBtnBuscarMouseClicked

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
                mostrarPagosPorRangoFecha(oDateFormat.format(fecha1), oDateFormat.format(fecha2));

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error fill table: " + ex);
            }
        }
    }//GEN-LAST:event_jBtnBuscarRangoFechaMouseClicked

    private void jSBAMECobrosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jSBAMECobrosMouseClicked
        this.jTabContrato.setSelectedIndex(0);
        this.jBtnSideBar.setVisible(true);
        this.jBtnSideBar1.setVisible(false);
        this.jSBListadoPagos.setBackground(azul);
        this.jSBAyuda.setBackground(azul);
        this.jSBFinanzaAbonado.setBackground(azul);
        this.jSBAMECobros.setBackground(celeste);
    }//GEN-LAST:event_jSBAMECobrosMouseClicked

    private void jSBFinanzaAbonadoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jSBFinanzaAbonadoMouseClicked
        this.jTabContrato.setSelectedIndex(1);
        this.jBtnSideBar.setVisible(true);
        this.jBtnSideBar1.setVisible(false);
        this.jSBListadoPagos.setBackground(azul);
        this.jSBAyuda.setBackground(azul);
        this.jSBFinanzaAbonado.setBackground(celeste);
        this.jSBAMECobros.setBackground(azul);
    }//GEN-LAST:event_jSBFinanzaAbonadoMouseClicked

    private void jSBListadoPagosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jSBListadoPagosMouseClicked
        this.jTabContrato.setSelectedIndex(2);
        this.jBtnSideBar.setVisible(true);
        this.jBtnSideBar1.setVisible(false);
        this.jSBListadoPagos.setBackground(celeste);
        this.jSBAyuda.setBackground(azul);
        this.jSBFinanzaAbonado.setBackground(azul);
        this.jSBAMECobros.setBackground(azul);
    }//GEN-LAST:event_jSBListadoPagosMouseClicked

    private void jSBAyudaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jSBAyudaMouseClicked
        this.jTabContrato.setSelectedIndex(3);
        this.jBtnSideBar.setVisible(true);
        this.jBtnSideBar1.setVisible(false);
        this.jSBListadoPagos.setBackground(azul);
        this.jSBAyuda.setBackground(celeste);
        this.jSBFinanzaAbonado.setBackground(azul);
        this.jSBAMECobros.setBackground(azul);
    }//GEN-LAST:event_jSBAyudaMouseClicked

    private void jBtnBuscarContratoBC1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnBuscarContratoBC1ActionPerformed
        this.jDialog1.setVisible(true);
    }//GEN-LAST:event_jBtnBuscarContratoBC1ActionPerformed

    private void jBtnBuscarContratoRTN1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnBuscarContratoRTN1ActionPerformed
        this.jDialog1.setVisible(true);
    }//GEN-LAST:event_jBtnBuscarContratoRTN1ActionPerformed

    private void jBtnSeleccionar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnSeleccionar1ActionPerformed
        seleccionarFilas2();
        limpiarTablaDatosContrato1();
        this.jDialog2.setVisible(false);
        this.jLblCancelarEdicion.setVisible(true);
        estadEditando = true;
    }//GEN-LAST:event_jBtnSeleccionar1ActionPerformed

    private void jBtnCancelar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnCancelar1ActionPerformed
        limpiarTablaDatosContrato1();
        this.jDialog2.setVisible(false);
    }//GEN-LAST:event_jBtnCancelar1ActionPerformed

    private void jBtnBuscarContratoBC3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnBuscarContratoBC3ActionPerformed
        String numBloque, numCasa;

        numBloque = this.jTfBloqueB1.getText();
        numCasa = this.jTfCasaB1.getText();
        
        if (this.jTfBloqueB1.getText().trim().equals("")) {
            //JOptionPane.showMessageDialog(null, "Ingrese el número de bloque", "Saja", JOptionPane.INFORMATION_MESSAGE);
            this.jTfBloqueB1.requestFocus();
        } else if(this.jTfCasaB1.getText().trim().equals("")){
            //JOptionPane.showMessageDialog(null, "Ingrese el número de casa", "Saja", JOptionPane.INFORMATION_MESSAGE);
            this.jTfCasaB1.requestFocus();
        }else {
            try {
                llenarTablaDatosContratoBC1(numBloque, numCasa);
                this.jDialog2.setVisible(true);
                this.jTfBloqueB1.setText("");
                this.jTfCasaB1.setText("");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error fill table: " + ex);
            }
        }
    }//GEN-LAST:event_jBtnBuscarContratoBC3ActionPerformed

    private void jBtnBuscarContratoRTN3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnBuscarContratoRTN3ActionPerformed
        String RTN;

        RTN = this.jTfRTN1.getText();
        
        if (this.jTfRTN1.getText().trim().equals("")) {
            //JOptionPane.showMessageDialog(null, "Ingrese el RTN", "Saja", JOptionPane.INFORMATION_MESSAGE);
            this.jTfRTN1.requestFocus();
        } else {
            try {
                llenarTablaDatosContratoRTN1(RTN);
                this.jDialog2.setVisible(true);
                this.jTfRTN1.setText("");

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error fill table: " + ex);
            }
        }
    }//GEN-LAST:event_jBtnBuscarContratoRTN3ActionPerformed

    private void jRBBCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRBBCActionPerformed
        if(this.jRBBC.isSelected()== true){
            this.jLblBloque1.setVisible(true);
            this.jLblCasa1.setVisible(true);
            this.jTfBloqueB1.setVisible(true);
            this.jLblRTN1.setVisible(false);
            this.jTfCasaB1.setVisible(true);
            this.jTfRTN1.setVisible(false);
            this.jBtnBuscarContratoBC4.setVisible(true);
            this.jBtnBuscarContratoBC3.setVisible(true);
            this.jBtnBuscarContratoRTN4.setVisible(false);
            this.jBtnBuscarContratoRTN3.setVisible(false);
            this.jTfRTN1.setText("");
        }
    }//GEN-LAST:event_jRBBCActionPerformed

    private void jRBRTN2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRBRTN2ActionPerformed
        if(this.jRBRTN2.isSelected()== true){
            this.jLblBloque1.setVisible(false);
            this.jTfBloqueB1.setVisible(false);
            this.jTfBloqueB1.setText("");
            this.jLblCasa1.setVisible(false);
            this.jTfCasaB1.setVisible(false);
            this.jTfCasaB1.setText("");
            this.jLblRTN1.setVisible(true);
            this.jTfRTN1.setVisible(true);
            this.jBtnBuscarContratoBC4.setVisible(false);
            this.jBtnBuscarContratoBC3.setVisible(false);
            this.jBtnBuscarContratoRTN4.setVisible(true);
            this.jBtnBuscarContratoRTN3.setVisible(true);
        }
    }//GEN-LAST:event_jRBRTN2ActionPerformed

    private void jBtnBuscarContratoBC4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnBuscarContratoBC4ActionPerformed
        this.jDialog2.setVisible(true);
    }//GEN-LAST:event_jBtnBuscarContratoBC4ActionPerformed

    private void jBtnBuscarContratoRTN4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnBuscarContratoRTN4ActionPerformed
        this.jDialog2.setVisible(true);
    }//GEN-LAST:event_jBtnBuscarContratoRTN4ActionPerformed

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

    private void jLblMinimizar2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLblMinimizar2MouseClicked
        this.setState(Frame.ICONIFIED);
    }//GEN-LAST:event_jLblMinimizar2MouseClicked

    private void jLblSalirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLblSalirMouseClicked
        this.dispose();
    }//GEN-LAST:event_jLblSalirMouseClicked

    private void jLblGenerarPlanPagoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLblGenerarPlanPagoMouseClicked
        try {
            JFraPlanPago jfpp = new JFraPlanPago();
            this.dispose();
            jfpp.mostratVentana(true, 
                                jTfIdentidad.getText(),
                                jTfNombreCompleto.getText(),
                                jTfBloque.getText(),
                                jTfCasa.getText());
            jfpp.setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(JFraCobros.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_jLblGenerarPlanPagoMouseClicked

    private void jBtnSiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnSiActionPerformed
        this.dispose();
        this.jDfMenu.dispose();
        jfm.setVisible(true);
    }//GEN-LAST:event_jBtnSiActionPerformed

    private void JBtnNoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBtnNoActionPerformed
        this.jDfMenu.dispose();
    }//GEN-LAST:event_JBtnNoActionPerformed

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
            java.util.logging.Logger.getLogger(JFraCobros.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFraCobros.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFraCobros.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFraCobros.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new JFraCobros().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(JFraCobros.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton JBtnNo;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.JLabel jBtnBuscar;
    private javax.swing.JButton jBtnBuscarContratoBC;
    private javax.swing.JButton jBtnBuscarContratoBC1;
    private javax.swing.JButton jBtnBuscarContratoBC3;
    private javax.swing.JButton jBtnBuscarContratoBC4;
    private javax.swing.JButton jBtnBuscarContratoRTN;
    private javax.swing.JButton jBtnBuscarContratoRTN1;
    private javax.swing.JButton jBtnBuscarContratoRTN3;
    private javax.swing.JButton jBtnBuscarContratoRTN4;
    private javax.swing.JLabel jBtnBuscarRangoFecha;
    private javax.swing.JButton jBtnCancelar;
    private javax.swing.JButton jBtnCancelar1;
    private javax.swing.JButton jBtnEditar;
    private javax.swing.JButton jBtnEditar1;
    private javax.swing.JButton jBtnGuardar;
    private javax.swing.JButton jBtnGuardar1;
    private javax.swing.JButton jBtnGuardar2;
    private javax.swing.JButton jBtnGuardar3;
    private javax.swing.JButton jBtnGuardar4;
    private javax.swing.JButton jBtnGuardar5;
    private javax.swing.JButton jBtnGuardar7;
    private javax.swing.JButton jBtnGuardar8;
    private javax.swing.JButton jBtnSeleccionar;
    private javax.swing.JButton jBtnSeleccionar1;
    private javax.swing.JButton jBtnSi;
    private javax.swing.JLabel jBtnSideBar;
    private javax.swing.JLabel jBtnSideBar1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private com.toedter.calendar.JDateChooser jDCFecha1;
    private com.toedter.calendar.JDateChooser jDCFecha2;
    private javax.swing.JDialog jDfMenu;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JDialog jDialog2;
    private javax.swing.JLabel jLblBloque;
    private javax.swing.JLabel jLblBloque1;
    private javax.swing.JLabel jLblCancelarEdicion;
    private javax.swing.JLabel jLblCasa;
    private javax.swing.JLabel jLblCasa1;
    private javax.swing.JLabel jLblGenerarPlanPago;
    private javax.swing.JLabel jLblIdentificador;
    private javax.swing.JLabel jLblIdentificador1;
    private javax.swing.JLabel jLblIdentificador10;
    private javax.swing.JLabel jLblIdentificador11;
    private javax.swing.JLabel jLblIdentificador15;
    private javax.swing.JLabel jLblIdentificador16;
    private javax.swing.JLabel jLblIdentificador17;
    private javax.swing.JLabel jLblIdentificador18;
    private javax.swing.JLabel jLblIdentificador19;
    private javax.swing.JLabel jLblIdentificador2;
    private javax.swing.JLabel jLblIdentificador20;
    private javax.swing.JLabel jLblIdentificador21;
    private javax.swing.JLabel jLblIdentificador22;
    private javax.swing.JLabel jLblIdentificador23;
    private javax.swing.JLabel jLblIdentificador24;
    private javax.swing.JLabel jLblIdentificador25;
    private javax.swing.JLabel jLblIdentificador26;
    private javax.swing.JLabel jLblIdentificador27;
    private javax.swing.JLabel jLblIdentificador28;
    private javax.swing.JLabel jLblIdentificador29;
    private javax.swing.JLabel jLblIdentificador3;
    private javax.swing.JLabel jLblIdentificador30;
    private javax.swing.JLabel jLblIdentificador33;
    private javax.swing.JLabel jLblIdentificador36;
    private javax.swing.JLabel jLblIdentificador39;
    private javax.swing.JLabel jLblIdentificador4;
    private javax.swing.JLabel jLblIdentificador40;
    private javax.swing.JLabel jLblIdentificador41;
    private javax.swing.JLabel jLblIdentificador42;
    private javax.swing.JLabel jLblIdentificador43;
    private javax.swing.JLabel jLblIdentificador44;
    private javax.swing.JLabel jLblIdentificador45;
    private javax.swing.JLabel jLblIdentificador46;
    private javax.swing.JLabel jLblIdentificador47;
    private javax.swing.JLabel jLblIdentificador48;
    private javax.swing.JLabel jLblIdentificador49;
    private javax.swing.JLabel jLblIdentificador5;
    private javax.swing.JLabel jLblIdentificador50;
    private javax.swing.JLabel jLblIdentificador51;
    private javax.swing.JLabel jLblIdentificador52;
    private javax.swing.JLabel jLblIdentificador54;
    private javax.swing.JLabel jLblIdentificador55;
    private javax.swing.JLabel jLblIdentificador57;
    private javax.swing.JLabel jLblIdentificador58;
    private javax.swing.JLabel jLblIdentificador59;
    private javax.swing.JLabel jLblIdentificador6;
    private javax.swing.JLabel jLblIdentificador7;
    private javax.swing.JLabel jLblIdentificador71;
    private javax.swing.JLabel jLblIdentificador73;
    private javax.swing.JLabel jLblIdentificador8;
    private javax.swing.JLabel jLblIdentificador9;
    private javax.swing.JLabel jLblMinimizar2;
    private javax.swing.JLabel jLblNumPagado;
    private javax.swing.JLabel jLblRTN;
    private javax.swing.JLabel jLblRTN1;
    private javax.swing.JLabel jLblSalir;
    private javax.swing.JLabel jLblTitulo2;
    private javax.swing.JList<String> jList2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPnlAMECobros;
    private javax.swing.JPanel jPnlAMECobros1;
    private javax.swing.JPanel jPnlBuscar;
    private javax.swing.JPanel jPnlFinanzaAbonado;
    private javax.swing.JPanel jPnlListadoPagos;
    private javax.swing.JPanel jPnlRangoFecha;
    private javax.swing.JRadioButton jRBBC;
    private javax.swing.JRadioButton jRBBloqueYCasa;
    private javax.swing.JRadioButton jRBPagosMes;
    private javax.swing.JRadioButton jRBPagosRangoFecha;
    private javax.swing.JRadioButton jRBRTN;
    private javax.swing.JRadioButton jRBRTN2;
    private javax.swing.JPanel jSBAMECobros;
    private javax.swing.JPanel jSBAyuda;
    private javax.swing.JPanel jSBContrato4;
    private javax.swing.JPanel jSBFinanzaAbonado;
    private javax.swing.JPanel jSBListadoPagos;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JPanel jSeparatorX1;
    private javax.swing.JPanel jSeparatorX2;
    private javax.swing.JPanel jSeparatorX3;
    private javax.swing.JPanel jSeparatorY;
    private javax.swing.JPanel jSeparatorY1;
    private javax.swing.JPanel jSeparatorY2;
    private javax.swing.JPanel jSideBar;
    private javax.swing.JScrollPane jSideBar1;
    private javax.swing.JTabbedPane jTabContrato;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTblDatosContrato;
    private javax.swing.JTable jTblDatosContrato1;
    private javax.swing.JTable jTblMesesPagados;
    private javax.swing.JTable jTblPagos;
    private javax.swing.JTextField jTfBloque;
    private javax.swing.JTextField jTfBloque2;
    private javax.swing.JTextField jTfBloqueB;
    private javax.swing.JTextField jTfBloqueB1;
    private javax.swing.JTextField jTfBuscar;
    private javax.swing.JTextField jTfCasa;
    private javax.swing.JTextField jTfCasa2;
    private javax.swing.JTextField jTfCasaB;
    private javax.swing.JTextField jTfCasaB1;
    private javax.swing.JTextField jTfIdentidad;
    private javax.swing.JTextField jTfIdentidad14;
    private javax.swing.JTextField jTfIdentidad2;
    private javax.swing.JTextField jTfIdentidad3;
    private javax.swing.JTextField jTfIdentidad5;
    private javax.swing.JTextField jTfIdentidad7;
    private javax.swing.JTextField jTfNombreCompleto;
    private javax.swing.JTextField jTfNombreCompleto2;
    private javax.swing.JTextField jTfRTN;
    private javax.swing.JTextField jTfRTN1;
    private javax.swing.JTextArea jTxtAEstado;
    // End of variables declaration//GEN-END:variables
}
