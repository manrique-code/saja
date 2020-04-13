/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capapresentacion;

import AppPackage.AnimationClass;
import capadatos.CDPlanPago;
import capadatos.CDTipoPlanPago;
import capalogica.CLLogin;
import capalogica.CLPlanPago;
import capalogica.CLTipoPlanPago;
import com.placeholder.PlaceHolder;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.Frame;
import java.sql.SQLException;
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
public class JFraPlanPago extends javax.swing.JFrame {

    /**
     * Creates new form jFraPlanPago
     */
    public JFraPlanPago() throws SQLException {
        initComponents();
        this.setLocationRelativeTo(null);
        this.jSideBar1.getViewport().setBackground(Color.getColor("52, 74, 94"));
        this.jBtnSideBar1.setVisible(false);
        this.jDlgContrato.setLocationRelativeTo(null);
        this.habilitarControles(false, true, true);
        this.siguienteId();
        this.cargarCboTipoPlanPago();
        this.jTfBloqueBuscar.requestFocus();
        this.permitirGuardar(false);
        this.jTblDetallePago.setEnabled(false);
    }
    
    //Instancia de la capa logica
    CLPlanPago clpp = new CLPlanPago();
    CLLogin cll = new CLLogin();
    
    // Instancias de la animacion del sidebar
    AnimationClass sideBar = new AnimationClass();
    
    // Colores del formulario
    Color celeste = new Color(52,152,219);
    Color azul = new Color(52,152,219);
    Color verde = new Color(46, 204, 113);
    Color azulmarino = new Color(52,73,94);
    
    // Instancia del menú
    JFraMenu jfm = new JFraMenu();
    
    // Instancia del placeholder
    PlaceHolder p;
    
    // Método para habilitar o deshabilitar la visibilidad de controles del formulario de agregar plan pago
    public void habilitarControles(boolean jTfRTNBuscar,                                   
                                   boolean jTfBloqueBuscar,
                                   boolean jTfCasaBuscar){
        this.jTfRTNBuscar.setVisible(jTfRTNBuscar);
        this.jTfBloqueBuscar.setVisible(jTfBloqueBuscar);
        this.jTfCasaBuscar.setVisible(jTfCasaBuscar);
    }
    
    // Método para habilitar los controles del formulario listado de plan de pago
    public void habilitarControlesListadoPago(boolean jTfBuscarTodoPlanPago,
                                              boolean jTfBuscarBloque,
                                              boolean jTfBuscarCasa,
                                              boolean jLblFechaActual,
                                              boolean jLblDe,
                                              boolean jLblHasta,
                                              boolean jDtChsrIncio,
                                              boolean jDtChsrFinal,
                                              boolean jLblVerDetallePlanPago){
        this.jTfBuscarTodoPlanPago.setVisible(jTfBuscarTodoPlanPago);
        this.jTfBuscarBloque.setVisible(jTfBuscarBloque);
        this.jTfBuscarCasa.setVisible(jTfBuscarCasa);
        this.jLblFechaActual.setVisible(jLblFechaActual);
        this.jLblDe.setVisible(jLblDe);
        this.jLblHasta.setVisible(jLblHasta);
        this.jDtChsrIncio.setVisible(jDtChsrIncio);
        this.jDtChsrFinal.setVisible(jDtChsrFinal);
        this.jLblVerDetallePlanPago.setVisible(jLblVerDetallePlanPago);
    }
    
    // Método para limpiar la tabla de la ventana modal
    public void limpiarTablaDialog(){
        DefaultTableModel dtm = (DefaultTableModel) this.jTblDialog.getModel();

        while (dtm.getRowCount() > 0) {
            dtm.removeRow(0);
        }
    }
    
    public void limpiarTablaPlanPago(){
        DefaultTableModel dtm = (DefaultTableModel) this.jTblDetallePago.getModel();

        while (dtm.getRowCount() > 0) {
            dtm.removeRow(0);
        }
    }
    
    public void limpiarTablaTodoPlanPago(){
        DefaultTableModel dtm = (DefaultTableModel) this.jTblTodoPlanPago.getModel();

        while (dtm.getRowCount() > 0) {
            dtm.removeRow(0);
        }
    }
    
    // Método para cargar la tabla de la ventana modal según la busqueda hecha por el número de identidad
    public void cargarContratoRTNDialog() throws SQLException{
        String rtn = this.jTfRTNBuscar.getText().trim();   
        
        this.limpiarTablaDialog();
        CDPlanPago cdpp = new CDPlanPago();
        List<CLPlanPago> contrato = cdpp.obtenerContratoPorRtn(rtn);
        DefaultTableModel dtm = (DefaultTableModel) this.jTblDialog.getModel();

        contrato.stream().map((clpp) -> {
            this.jLblIdContrato.setText(String.valueOf(clpp.getIdContrato()));
            Object[] fila = new Object[5];
            fila[0] = clpp.getNum();
            fila[1] = clpp.getRtn();
            fila[2] = clpp.getNombre() + ' ' + clpp.getApellidos();
            fila[3] = clpp.getBloque();
            fila[4] = clpp.getCasa();
            return fila;
        }).forEachOrdered((fila) -> {
            dtm.addRow(fila);
        });
        
        //this.jTblDialog.setRowSelectionInterval(0, 0);
        //this.jTfBuscarDialog.requestFocus(false);
    }
    
    // Método para cargar la busqueda segun el pegue en la tabla de la ventana modal
    public void cargarContratoPegueDialog() throws SQLException{
        String numBloque = this.jTfBloqueBuscar.getText().trim();
        String numCasa = this.jTfCasaBuscar.getText().trim();
        

        this.limpiarTablaDialog();
        CDPlanPago cdpp = new CDPlanPago();
        List<CLPlanPago> contrato = cdpp.obtenerContratoPorBloqueCasa(numBloque, numCasa);
        DefaultTableModel dtm = (DefaultTableModel) this.jTblDialog.getModel();

        contrato.stream().map((clpp) -> {
            this.jLblIdContrato.setText(String.valueOf(clpp.getIdContrato()));
            Object[] fila = new Object[5];
            fila[0] = clpp.getNum();
            fila[1] = clpp.getRtn();
            fila[2] = clpp.getNombre() + ' ' + clpp.getApellidos();
            fila[3] = clpp.getBloque();
            fila[4] = clpp.getCasa();
            return fila;
        }).forEachOrdered((fila) -> {
            dtm.addRow(fila);
        });
        
        //this.jTblDialog.setRowSelectionInterval(0, 0);
    }
    
    public void cargarTodosContratos() throws SQLException{       
        
        this.limpiarTablaDialog();
        CDPlanPago cdpp = new CDPlanPago();
        List<CLPlanPago> contrato = cdpp.obtenerTodosContratos();
        DefaultTableModel dtm = (DefaultTableModel) this.jTblDialog.getModel();

        contrato.stream().map((clpp) -> {
            this.jLblIdContrato.setText(String.valueOf(clpp.getIdContrato()));
            Object[] fila = new Object[5];
            fila[0] = clpp.getNum();
            fila[1] = clpp.getRtn();
            fila[2] = clpp.getNombre() + ' ' + clpp.getApellidos();
            fila[3] = clpp.getBloque();
            fila[4] = clpp.getCasa();
            return fila;
        }).forEachOrdered((fila) -> {
            dtm.addRow(fila);
        });
        
        //this.jTblDialog.setRowSelectionInterval(0, 0);
    }
    
    // Método para seleccionar la fila de tabla de la ventana modal
    public void seleccionarFila(){
        if(this.jTblDialog.getSelectedRow() != 1){
            jTfIdContrato.setText(this.jLblIdContrato.getText());
            this.jTfRTN.setText(String.valueOf(this.jTblDialog.getValueAt(this.jTblDialog.getSelectedRow(), 1)));
            this.jTfNombreCompleto.setText(String.valueOf(this.jTblDialog.getValueAt(this.jTblDialog.getSelectedRow(), 2)));
            this.jTfBloque.setText(String.valueOf(this.jTblDialog.getValueAt(this.jTblDialog.getSelectedRow(), 3)));
            this.jTfCasa.setText(String.valueOf(this.jTblDialog.getValueAt(this.jTblDialog.getSelectedRow(), 4)));
        }
    }
    
    /**
     *
     * @throws SQLException
     */
    
    // Método para retornar el siguiente id del plan de pago
    public void siguienteId() throws SQLException{
        CDPlanPago cdpp = new CDPlanPago();
        CLPlanPago clpp = new CLPlanPago();
        
        clpp.setIdPlanPago(cdpp.aiPlanPago());
        jTfCodPlanPago.setText(String.valueOf(clpp.getIdPlanPago()));
    }
    
    // Método para el ComboBox del tipo de plan de pago
    public void cargarCboTipoPlanPago() throws SQLException{
        CDTipoPlanPago cdtpp = new CDTipoPlanPago();
        
        String[] tipoPlanPago = new String[cdtpp.mostrarTodoTipoPlanPagoActivo().size()];
        tipoPlanPago = cdtpp.mostrarTodoTipoPlanPagoActivo().toArray(tipoPlanPago);
        
        DefaultComboBoxModel cboTipoPlanPago = new DefaultComboBoxModel(tipoPlanPago);
        this.jCboTipoPlanPago.setModel(cboTipoPlanPago);
    }
    
    
    // Método para calcular el valor de los plazos de pago
    public void calcularValorPlazo(boolean mesesVacios) throws SQLException{
        float monto, meses, valorPlazos;
        
        monto = Float.parseFloat(jFmtTfMontoAdeudado.getText());
        
        if(mesesVacios){
            valorPlazos = monto%monto;
            jFmtTfValorMontoPlazo.setText(String.valueOf(Math.ceil(valorPlazos)));
        } else {
            meses = Float.parseFloat(this.jTfPlazosPago.getText());
            valorPlazos = monto/meses;
            jFmtTfValorMontoPlazo.setText(String.valueOf(Math.ceil(valorPlazos)));
        }
        
    }
    
    // Método para permitir al usuario guardar un plan de pago
    public void permitirGuardar(boolean permitir){
        this.jBtnGuardar.setEnabled(permitir);
        this.jBtnCancelar.setVisible(permitir);
        JLblSeleccionarContrato.setVisible(!permitir);
    }
    
    // Método para generar el plan de pago en la tabla
    public void cargarTblPlanPago() {
        this.limpiarTablaPlanPago();        
        DefaultTableModel dtm = (DefaultTableModel) this.jTblDetallePago.getModel();
        
        String[] nombre;
        nombre = jTfNombreCompleto.getText().split(" ");
        String[] tipoPlanPago;
        tipoPlanPago = jCboTipoPlanPago.getSelectedItem().toString().split(". ");
        System.out.println(tipoPlanPago[0].trim());
        System.out.println(tipoPlanPago[1].trim());
        
        clpp.setRtn(jTfRTN.getText());
        clpp.setNombre(nombre[0].trim());
        clpp.setApellidos(nombre[1].trim());
        clpp.setBloque(jTfBloque.getText());
        clpp.setCasa(jTfCasa.getText());
        clpp.setTipoPlanPago(tipoPlanPago[1].trim());
        clpp.setPlazo(Integer.parseInt(jTfPlazosPago.getText()));
        clpp.setIdTipoPlanPago(Integer.parseInt(tipoPlanPago[0].trim()));
        clpp.setValorPlazo(Float.parseFloat(jFmtTfValorMontoPlazo.getText()));
        clpp.setIdContrato(Integer.parseInt(jTfIdContrato.getText()));
        clpp.setIdPlanPago(Integer.parseInt(jTfCodPlanPago.getText()));
        
        for(int i = 1; i <= Integer.parseInt(this.jTfPlazosPago.getText()); i++){
            Object[] fila = new Object[7];
            fila[0] = clpp.getRtn();
            fila[1] = jTfNombreCompleto.getText();
            fila[2] = clpp.getBloque();
            fila[3] = clpp.getCasa();
            fila[4] = tipoPlanPago[1].trim();
            fila[5] = i;
            fila[6] = "L." + jFmtTfValorMontoPlazo.getText();
            dtm.addRow(fila);
        }
    }
    
    // Método para cargar el plan de pago en la tabla de pago de plan de pago
    public void cargarTodosPlanPagoPorAbonado(String nombre) throws SQLException{       
        
        this.limpiarTablaTodoPlanPago();
        CDPlanPago cdpp = new CDPlanPago();
        List<CLPlanPago> contrato = cdpp.obtenerTodoPlanesPagoPorAbonado(nombre);
        DefaultTableModel dtm = (DefaultTableModel) this.jTblTodoPlanPago.getModel();

        contrato.stream().map((clpp) -> {
            Object[] fila = new Object[7];
            fila[0] = clpp.getNum();
            fila[1] = clpp.getRtn();
            fila[2] = clpp.getNombre() + ' ' + clpp.getApellidos();
            fila[3] = clpp.getBloque();
            fila[4] = clpp.getCasa();
            fila[5] = clpp.getPlazo();
            fila[6] = "L." + clpp.getValorPlazo();
            return fila;
        }).forEachOrdered((fila) -> {
            dtm.addRow(fila);
        });
        
        //this.jTblDialog.setRowSelectionInterval(0, 0);
    }
    
    // Método para mostrar todos los planes de pago
    public void cargarTodosPlanPago() throws SQLException{               
        this.limpiarTablaDialog();
        CDPlanPago cdpp = new CDPlanPago();
        List<CLPlanPago> contrato = cdpp.obtenerTodoPlanes();
        DefaultTableModel dtm = (DefaultTableModel) this.jTblTodoPlanPago.getModel();

        contrato.stream().map((clpp) -> {
            Object[] fila = new Object[7];
            fila[0] = clpp.getNum();
            fila[1] = clpp.getRtn();
            fila[2] = clpp.getNombre() + ' ' + clpp.getApellidos();
            fila[3] = clpp.getBloque();
            fila[4] = clpp.getCasa();
            fila[5] = clpp.getPlazo();
            fila[6] = "L." + clpp.getValorPlazo();
            return fila;
        }).forEachOrdered((fila) -> {
            dtm.addRow(fila);
        });
        
        //this.jTblDialog.setRowSelectionInterval(0, 0);
    }
    
    public void mostratVentana(boolean mostrando,
                               String rtn,
                               String nombreAbonado,
                               String bloque,
                               String casa){
        if(mostrando){
            jTabPlanPago.setSelectedIndex(0);
            jTfRTN.setText(rtn);
            jTfNombreCompleto.setText(nombreAbonado);
            jTfBloque.setText(bloque);
            jTfCasa.setText(casa);
            jCboTipoPlanPago.requestFocus();
            jTfIdContrato.setText("2");
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

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        buttonGroup1 = new javax.swing.ButtonGroup();
        btnGpListadoPlanPagos = new javax.swing.ButtonGroup();
        jLblIdentificador44 = new javax.swing.JLabel();
        jDlgContrato = new javax.swing.JDialog();
        jPnlDialog = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTblDialog = new javax.swing.JTable();
        jBtnSeleccionarDialog = new javax.swing.JButton();
        jBtnCancelarDialog = new javax.swing.JButton();
        jLblIdContrato = new javax.swing.JLabel();
        jRdBtnPegueBuscar1 = new javax.swing.JRadioButton();
        jRdBtnRTNBuscar1 = new javax.swing.JRadioButton();
        jPanel7 = new javax.swing.JPanel();
        jTfRTNBuscar = new javax.swing.JFormattedTextField();
        jTfCasaBuscar = new javax.swing.JTextField();
        jTfBloqueBuscar = new javax.swing.JTextField();
        jLblBloqueORTN = new javax.swing.JLabel();
        jLblCasaBuscar = new javax.swing.JLabel();
        jLblCasaBuscar1 = new javax.swing.JLabel();
        jDfMenu = new javax.swing.JDialog();
        jPanel15 = new javax.swing.JPanel();
        jLblIdentificador58 = new javax.swing.JLabel();
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
        jLblSalir = new javax.swing.JLabel();
        jLblMinimizar2 = new javax.swing.JLabel();
        jSideBar1 = new javax.swing.JScrollPane();
        jSideBar = new javax.swing.JPanel();
        jSBAMEPlanPago = new javax.swing.JPanel();
        jLblIdentificador = new javax.swing.JLabel();
        jLblIdentificador1 = new javax.swing.JLabel();
        jSBMenu = new javax.swing.JPanel();
        jLblIdentificador9 = new javax.swing.JLabel();
        jLblIdentificador10 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLblIdentificador5 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jSBAyudaPlanPago = new javax.swing.JPanel();
        jLblIdentificador4 = new javax.swing.JLabel();
        jLblIdentificador6 = new javax.swing.JLabel();
        jSBVerDetallePlanPago = new javax.swing.JPanel();
        jLblIdentificador2 = new javax.swing.JLabel();
        jLblIdentificador3 = new javax.swing.JLabel();
        jSBListadoPlanPago = new javax.swing.JPanel();
        jLblIdentificador7 = new javax.swing.JLabel();
        jLblIdentificador8 = new javax.swing.JLabel();
        jTabPlanPago = new javax.swing.JTabbedPane();
        jPnlAñadirPlanPago = new javax.swing.JPanel();
        jLblIdentificador45 = new javax.swing.JLabel();
        jCboTipoPlanPago = new javax.swing.JComboBox<>();
        jLblIdentificador46 = new javax.swing.JLabel();
        jBtnGuardar = new javax.swing.JButton();
        jPnlPlazosPago = new javax.swing.JPanel();
        jTfIdentidad4 = new javax.swing.JTextField();
        jTfPlazosPago = new javax.swing.JTextField();
        jLblIdentificador47 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jTfIdentidad7 = new javax.swing.JTextField();
        jFmtTfValorMontoPlazo = new javax.swing.JTextField();
        jBtnBuscarContrato = new javax.swing.JButton();
        jLblIdentificador43 = new javax.swing.JLabel();
        jSeparatorY = new javax.swing.JPanel();
        jLblIdentificador48 = new javax.swing.JLabel();
        jLblIdentificador49 = new javax.swing.JLabel();
        jTfRTN = new javax.swing.JTextField();
        jTfBloque = new javax.swing.JTextField();
        jLblIdentificador50 = new javax.swing.JLabel();
        jTfCasa = new javax.swing.JTextField();
        jLblIdentificador51 = new javax.swing.JLabel();
        jLblIdentificador52 = new javax.swing.JLabel();
        jTfNombreCompleto = new javax.swing.JTextField();
        jLblIdentificador53 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTblDetallePago = new javax.swing.JTable();
        jBtnCancelar = new javax.swing.JButton();
        jPanel11 = new javax.swing.JPanel();
        jTfIdentidad16 = new javax.swing.JTextField();
        jFmtTfValorMontoPlazo1 = new javax.swing.JFormattedTextField();
        jLblIdentificador54 = new javax.swing.JLabel();
        jPnlSeparator = new javax.swing.JPanel();
        jLblIdentificador55 = new javax.swing.JLabel();
        jTfCodPlanPago = new javax.swing.JTextField();
        jPanel10 = new javax.swing.JPanel();
        jTfIdentidad8 = new javax.swing.JTextField();
        jFmtTfMontoAdeudado = new javax.swing.JTextField();
        jLblIdentificador72 = new javax.swing.JLabel();
        JLblSeleccionarContrato = new javax.swing.JLabel();
        jTfIdContrato = new javax.swing.JTextField();
        jPnlPlazos = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTblPlazosPago = new javax.swing.JTable();
        jBtnCancelarPlanPago = new javax.swing.JButton();
        jLblIdentificador59 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jLblIdentificador60 = new javax.swing.JLabel();
        jLblIdentificador61 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        jLblIdentificador62 = new javax.swing.JLabel();
        jTfNPlazo = new javax.swing.JTextField();
        jLblIdentificador63 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        jLblIdentificador64 = new javax.swing.JLabel();
        jFmtTfValorPagoPlazo = new javax.swing.JFormattedTextField();
        jLblIdentificador65 = new javax.swing.JLabel();
        jTfCodPlanPagoPlazos = new javax.swing.JTextField();
        jBtnPagarPlazo = new javax.swing.JButton();
        jLblIdentificador66 = new javax.swing.JLabel();
        jTfRTNPlazos = new javax.swing.JTextField();
        jTfNombreCompletoPlazos = new javax.swing.JTextField();
        jLblIdentificador67 = new javax.swing.JLabel();
        jLblIdentificador68 = new javax.swing.JLabel();
        jLblIdentificador69 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jTfBloquePlazos = new javax.swing.JTextField();
        jTfCasaPlazos = new javax.swing.JTextField();
        jLblIdentificador70 = new javax.swing.JLabel();
        jDtChsrFechaPago = new com.toedter.calendar.JDateChooser();
        jLblFechaActual = new javax.swing.JLabel();
        jPnlListadoPlanPago = new javax.swing.JPanel();
        jLblIdentificador57 = new javax.swing.JLabel();
        jRdBtnAbonado = new javax.swing.JRadioButton();
        jRdBtnPegue = new javax.swing.JRadioButton();
        jRdBtnRecientes = new javax.swing.JRadioButton();
        jRdBtnRangoFecha = new javax.swing.JRadioButton();
        jPnlBuscar = new javax.swing.JPanel();
        jTfBuscarTodoPlanPago = new javax.swing.JTextField();
        jBtnBuscar = new javax.swing.JLabel();
        jTfBuscarBloque = new javax.swing.JTextField();
        jTfBuscarCasa = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTblTodoPlanPago = new javax.swing.JTable();
        jLblVerDetallePlanPago = new javax.swing.JLabel();
        jDtChsrIncio = new com.toedter.calendar.JDateChooser();
        jLblDe = new javax.swing.JLabel();
        jDtChsrFinal = new com.toedter.calendar.JDateChooser();
        jLblHasta = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jPanel14 = new javax.swing.JPanel();
        jSeparatorX1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jLblIdentificador44.setBackground(new java.awt.Color(102, 102, 102));
        jLblIdentificador44.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 0, 20)); // NOI18N
        jLblIdentificador44.setForeground(new java.awt.Color(0, 0, 0));
        jLblIdentificador44.setText("Buscar un contrato:");

        jDlgContrato.setTitle("Elija un contrato");
        jDlgContrato.setAlwaysOnTop(true);
        jDlgContrato.setModal(true);
        jDlgContrato.setModalExclusionType(java.awt.Dialog.ModalExclusionType.APPLICATION_EXCLUDE);
        jDlgContrato.setSize(new java.awt.Dimension(570, 450));

        jPnlDialog.setBackground(new java.awt.Color(255, 255, 255));
        jPnlDialog.setForeground(new java.awt.Color(0, 0, 0));

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 0, 20)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Buscar contrato");

        jTblDialog.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "#", "Número identidad", "Nombre Completo", "Bloque", "Casa"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTblDialog.getTableHeader().setReorderingAllowed(false);
        jScrollPane6.setViewportView(jTblDialog);

        jBtnSeleccionarDialog.setBackground(new java.awt.Color(41, 128, 185));
        jBtnSeleccionarDialog.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 0, 16)); // NOI18N
        jBtnSeleccionarDialog.setForeground(new java.awt.Color(255, 255, 255));
        jBtnSeleccionarDialog.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/cruz-blanca24.png"))); // NOI18N
        jBtnSeleccionarDialog.setText("SELECCIONAR");
        jBtnSeleccionarDialog.setBorder(null);
        jBtnSeleccionarDialog.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jBtnSeleccionarDialog.setIconTextGap(10);
        jBtnSeleccionarDialog.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnSeleccionarDialogActionPerformed(evt);
            }
        });

        jBtnCancelarDialog.setBackground(new java.awt.Color(41, 128, 185));
        jBtnCancelarDialog.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 0, 16)); // NOI18N
        jBtnCancelarDialog.setForeground(new java.awt.Color(255, 255, 255));
        jBtnCancelarDialog.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/x-blanca24.png"))); // NOI18N
        jBtnCancelarDialog.setText("CANCELAR");
        jBtnCancelarDialog.setBorder(null);
        jBtnCancelarDialog.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jBtnCancelarDialog.setIconTextGap(10);
        jBtnCancelarDialog.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnCancelarDialogActionPerformed(evt);
            }
        });

        jLblIdContrato.setBackground(new java.awt.Color(255, 255, 255));
        jLblIdContrato.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 0, 20)); // NOI18N
        jLblIdContrato.setForeground(new java.awt.Color(0, 0, 0));

        jRdBtnPegueBuscar1.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(jRdBtnPegueBuscar1);
        jRdBtnPegueBuscar1.setFont(new java.awt.Font("HelveticaNowDisplay Regular", 0, 12)); // NOI18N
        jRdBtnPegueBuscar1.setForeground(new java.awt.Color(0, 0, 0));
        jRdBtnPegueBuscar1.setSelected(true);
        jRdBtnPegueBuscar1.setText("Pegue");
        jRdBtnPegueBuscar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRdBtnPegueBuscar1ActionPerformed(evt);
            }
        });

        jRdBtnRTNBuscar1.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(jRdBtnRTNBuscar1);
        jRdBtnRTNBuscar1.setFont(new java.awt.Font("HelveticaNowDisplay Regular", 0, 12)); // NOI18N
        jRdBtnRTNBuscar1.setForeground(new java.awt.Color(0, 0, 0));
        jRdBtnRTNBuscar1.setText("Número identidad");
        jRdBtnRTNBuscar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRdBtnRTNBuscar1ActionPerformed(evt);
            }
        });

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setForeground(new java.awt.Color(0, 0, 0));
        jPanel7.setLayout(null);

        jTfRTNBuscar.setBackground(new java.awt.Color(255, 255, 255));
        jTfRTNBuscar.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTfRTNBuscar.setForeground(new java.awt.Color(0, 0, 0));
        try {
            jTfRTNBuscar.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("####-####-#####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jTfRTNBuscar.setFont(new java.awt.Font("HelveticaNowDisplay Regular", 0, 16)); // NOI18N
        jPanel7.add(jTfRTNBuscar);
        jTfRTNBuscar.setBounds(5, 31, 180, 34);

        jTfCasaBuscar.setBackground(new java.awt.Color(255, 255, 255));
        jTfCasaBuscar.setFont(new java.awt.Font("HelveticaNowDisplay Regular", 0, 16)); // NOI18N
        jTfCasaBuscar.setForeground(new java.awt.Color(0, 0, 0));
        jTfCasaBuscar.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTfCasaBuscar.setSelectionColor(new java.awt.Color(0, 153, 153));
        jPanel7.add(jTfCasaBuscar);
        jTfCasaBuscar.setBounds(100, 33, 75, 29);

        jTfBloqueBuscar.setBackground(new java.awt.Color(255, 255, 255));
        jTfBloqueBuscar.setFont(new java.awt.Font("HelveticaNowDisplay Regular", 0, 16)); // NOI18N
        jTfBloqueBuscar.setForeground(new java.awt.Color(0, 0, 0));
        jTfBloqueBuscar.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTfBloqueBuscar.setSelectionColor(new java.awt.Color(0, 153, 153));
        jPanel7.add(jTfBloqueBuscar);
        jTfBloqueBuscar.setBounds(6, 33, 75, 29);

        jLblBloqueORTN.setBackground(new java.awt.Color(102, 102, 102));
        jLblBloqueORTN.setFont(new java.awt.Font("HelveticaNowDisplay Regular", 0, 20)); // NOI18N
        jLblBloqueORTN.setForeground(new java.awt.Color(0, 0, 0));
        jLblBloqueORTN.setText("Bloque");
        jPanel7.add(jLblBloqueORTN);
        jLblBloqueORTN.setBounds(6, 6, 75, 21);

        jLblCasaBuscar.setBackground(new java.awt.Color(102, 102, 102));
        jLblCasaBuscar.setFont(new java.awt.Font("HelveticaNowDisplay Regular", 0, 20)); // NOI18N
        jLblCasaBuscar.setForeground(new java.awt.Color(0, 0, 0));
        jLblCasaBuscar.setText("Casa");
        jPanel7.add(jLblCasaBuscar);
        jLblCasaBuscar.setBounds(100, 6, 75, 21);

        jLblCasaBuscar1.setBackground(new java.awt.Color(102, 102, 102));
        jLblCasaBuscar1.setFont(new java.awt.Font("HelveticaNowDisplay Regular", 0, 20)); // NOI18N
        jLblCasaBuscar1.setForeground(new java.awt.Color(0, 0, 0));
        jLblCasaBuscar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/search-black24.png"))); // NOI18N
        jLblCasaBuscar1.setToolTipText("Buscar");
        jLblCasaBuscar1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel7.add(jLblCasaBuscar1);
        jLblCasaBuscar1.setBounds(197, 30, 30, 32);

        javax.swing.GroupLayout jPnlDialogLayout = new javax.swing.GroupLayout(jPnlDialog);
        jPnlDialog.setLayout(jPnlDialogLayout);
        jPnlDialogLayout.setHorizontalGroup(
            jPnlDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPnlDialogLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPnlDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPnlDialogLayout.createSequentialGroup()
                        .addGroup(jPnlDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addGroup(jPnlDialogLayout.createSequentialGroup()
                                .addComponent(jRdBtnPegueBuscar1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jRdBtnRTNBuscar1)))
                        .addGap(70, 70, 70)
                        .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPnlDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPnlDialogLayout.createSequentialGroup()
                            .addComponent(jBtnSeleccionarDialog, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(180, 180, 180)
                            .addComponent(jBtnCancelarDialog, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(639, 639, 639)
                .addComponent(jLblIdContrato, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPnlDialogLayout.setVerticalGroup(
            jPnlDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPnlDialogLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPnlDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPnlDialogLayout.createSequentialGroup()
                        .addGroup(jPnlDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLblIdContrato, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPnlDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jRdBtnRTNBuscar1)
                            .addComponent(jRdBtnPegueBuscar1)))
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPnlDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBtnSeleccionarDialog, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBtnCancelarDialog, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jDlgContratoLayout = new javax.swing.GroupLayout(jDlgContrato.getContentPane());
        jDlgContrato.getContentPane().setLayout(jDlgContratoLayout);
        jDlgContratoLayout.setHorizontalGroup(
            jDlgContratoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPnlDialog, javax.swing.GroupLayout.PREFERRED_SIZE, 560, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jDlgContratoLayout.setVerticalGroup(
            jDlgContratoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPnlDialog, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jDfMenu.setTitle("Confirmar acción");
        jDfMenu.setAlwaysOnTop(true);
        jDfMenu.setModal(true);
        jDfMenu.setModalExclusionType(java.awt.Dialog.ModalExclusionType.APPLICATION_EXCLUDE);
        jDfMenu.setUndecorated(true);
        jDfMenu.setSize(new java.awt.Dimension(300, 200));

        jPanel15.setBackground(new java.awt.Color(255, 255, 255));
        jPanel15.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jLblIdentificador58.setBackground(new java.awt.Color(255, 255, 255));
        jLblIdentificador58.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 1, 20)); // NOI18N
        jLblIdentificador58.setForeground(new java.awt.Color(0, 0, 0));
        jLblIdentificador58.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/menu-negro24.png"))); // NOI18N
        jLblIdentificador58.setText("¿Regresar al menú?");
        jLblIdentificador58.setIconTextGap(10);

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
                    .addComponent(jLblIdentificador58)
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
                .addComponent(jLblIdentificador58)
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
        setTitle("Planes de pago");
        setUndecorated(true);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setForeground(new java.awt.Color(0, 0, 0));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.setMinimumSize(new java.awt.Dimension(1000, 700));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(41, 128, 185));
        jPanel3.setPreferredSize(new java.awt.Dimension(800, 60));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLblTitulo2.setFont(new java.awt.Font("HelveticaNowDisplay ExtraBold", 0, 30)); // NOI18N
        jLblTitulo2.setForeground(new java.awt.Color(255, 255, 255));
        jLblTitulo2.setText("Planes de pago");
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

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1000, 60));

        jSideBar1.setBackground(new java.awt.Color(52, 73, 94));
        jSideBar1.setBorder(null);
        jSideBar1.setForeground(new java.awt.Color(0, 0, 0));

        jSideBar.setBackground(new java.awt.Color(52, 73, 94));
        jSideBar.setPreferredSize(new java.awt.Dimension(260, 600));
        jSideBar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jSBAMEPlanPago.setBackground(new java.awt.Color(52, 152, 219));
        jSBAMEPlanPago.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jLblIdentificador.setBackground(new java.awt.Color(102, 102, 102));
        jLblIdentificador.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 1, 20)); // NOI18N
        jLblIdentificador.setForeground(new java.awt.Color(255, 255, 255));
        jLblIdentificador.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/planpago-blanco32.png"))); // NOI18N
        jLblIdentificador.setToolTipText("Contratos");

        jLblIdentificador1.setBackground(new java.awt.Color(102, 102, 102));
        jLblIdentificador1.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 1, 20)); // NOI18N
        jLblIdentificador1.setForeground(new java.awt.Color(255, 255, 255));
        jLblIdentificador1.setText("Añadir plan pago");

        javax.swing.GroupLayout jSBAMEPlanPagoLayout = new javax.swing.GroupLayout(jSBAMEPlanPago);
        jSBAMEPlanPago.setLayout(jSBAMEPlanPagoLayout);
        jSBAMEPlanPagoLayout.setHorizontalGroup(
            jSBAMEPlanPagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jSBAMEPlanPagoLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLblIdentificador1, javax.swing.GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jLblIdentificador, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jSBAMEPlanPagoLayout.setVerticalGroup(
            jSBAMEPlanPagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jSBAMEPlanPagoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jSBAMEPlanPagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLblIdentificador, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
                    .addComponent(jLblIdentificador1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jSideBar.add(jSBAMEPlanPago, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 225, 260, -1));

        jSBMenu.setBackground(new java.awt.Color(52, 73, 94));
        jSBMenu.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jSBMenu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jSBMenuMouseClicked(evt);
            }
        });

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

        javax.swing.GroupLayout jSBMenuLayout = new javax.swing.GroupLayout(jSBMenu);
        jSBMenu.setLayout(jSBMenuLayout);
        jSBMenuLayout.setHorizontalGroup(
            jSBMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jSBMenuLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLblIdentificador10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLblIdentificador9)
                .addContainerGap())
        );
        jSBMenuLayout.setVerticalGroup(
            jSBMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jSBMenuLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jSBMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLblIdentificador9, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
                    .addComponent(jLblIdentificador10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jSideBar.add(jSBMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 132, 260, -1));

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

        jSBAyudaPlanPago.setBackground(new java.awt.Color(52, 73, 94));
        jSBAyudaPlanPago.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jSBAyudaPlanPago.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jSBAyudaPlanPagoMouseClicked(evt);
            }
        });

        jLblIdentificador4.setBackground(new java.awt.Color(102, 102, 102));
        jLblIdentificador4.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 1, 20)); // NOI18N
        jLblIdentificador4.setForeground(new java.awt.Color(255, 255, 255));
        jLblIdentificador4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/pregunta-blanco32.png"))); // NOI18N
        jLblIdentificador4.setToolTipText("¿Qué debo hacer aquí?");

        jLblIdentificador6.setBackground(new java.awt.Color(102, 102, 102));
        jLblIdentificador6.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 1, 20)); // NOI18N
        jLblIdentificador6.setForeground(new java.awt.Color(255, 255, 255));
        jLblIdentificador6.setText("Ayuda");

        javax.swing.GroupLayout jSBAyudaPlanPagoLayout = new javax.swing.GroupLayout(jSBAyudaPlanPago);
        jSBAyudaPlanPago.setLayout(jSBAyudaPlanPagoLayout);
        jSBAyudaPlanPagoLayout.setHorizontalGroup(
            jSBAyudaPlanPagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jSBAyudaPlanPagoLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLblIdentificador6, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLblIdentificador4)
                .addGap(16, 16, 16))
        );
        jSBAyudaPlanPagoLayout.setVerticalGroup(
            jSBAyudaPlanPagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jSBAyudaPlanPagoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jSBAyudaPlanPagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLblIdentificador4, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
                    .addComponent(jLblIdentificador6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jSideBar.add(jSBAyudaPlanPago, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 560, 260, -1));

        jSBVerDetallePlanPago.setBackground(new java.awt.Color(52, 73, 94));
        jSBVerDetallePlanPago.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jSBVerDetallePlanPago.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jSBVerDetallePlanPagoMouseClicked(evt);
            }
        });

        jLblIdentificador2.setBackground(new java.awt.Color(102, 102, 102));
        jLblIdentificador2.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 1, 20)); // NOI18N
        jLblIdentificador2.setForeground(new java.awt.Color(255, 255, 255));
        jLblIdentificador2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/verplanpago-blanco32.png"))); // NOI18N
        jLblIdentificador2.setToolTipText("Listado de contratos");

        jLblIdentificador3.setBackground(new java.awt.Color(102, 102, 102));
        jLblIdentificador3.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 1, 20)); // NOI18N
        jLblIdentificador3.setForeground(new java.awt.Color(255, 255, 255));
        jLblIdentificador3.setText("Ver plan pago");

        javax.swing.GroupLayout jSBVerDetallePlanPagoLayout = new javax.swing.GroupLayout(jSBVerDetallePlanPago);
        jSBVerDetallePlanPago.setLayout(jSBVerDetallePlanPagoLayout);
        jSBVerDetallePlanPagoLayout.setHorizontalGroup(
            jSBVerDetallePlanPagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jSBVerDetallePlanPagoLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLblIdentificador3, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLblIdentificador2)
                .addGap(10, 10, 10))
        );
        jSBVerDetallePlanPagoLayout.setVerticalGroup(
            jSBVerDetallePlanPagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jSBVerDetallePlanPagoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jSBVerDetallePlanPagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLblIdentificador2, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
                    .addComponent(jLblIdentificador3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jSideBar.add(jSBVerDetallePlanPago, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 299, 260, -1));

        jSBListadoPlanPago.setBackground(new java.awt.Color(52, 73, 94));
        jSBListadoPlanPago.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jSBListadoPlanPago.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jSBListadoPlanPagoMouseClicked(evt);
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
        jLblIdentificador8.setText("Listado plan pago");

        javax.swing.GroupLayout jSBListadoPlanPagoLayout = new javax.swing.GroupLayout(jSBListadoPlanPago);
        jSBListadoPlanPago.setLayout(jSBListadoPlanPagoLayout);
        jSBListadoPlanPagoLayout.setHorizontalGroup(
            jSBListadoPlanPagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jSBListadoPlanPagoLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLblIdentificador8, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                .addComponent(jLblIdentificador7)
                .addGap(15, 15, 15))
        );
        jSBListadoPlanPagoLayout.setVerticalGroup(
            jSBListadoPlanPagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jSBListadoPlanPagoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jSBListadoPlanPagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLblIdentificador7, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
                    .addComponent(jLblIdentificador8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jSideBar.add(jSBListadoPlanPago, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 370, 270, -1));

        jSideBar1.setViewportView(jSideBar);

        jPanel1.add(jSideBar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(-210, 0, 270, 625));

        jTabPlanPago.setBackground(new java.awt.Color(255, 255, 255));
        jTabPlanPago.setForeground(new java.awt.Color(0, 0, 0));
        jTabPlanPago.setTabPlacement(javax.swing.JTabbedPane.RIGHT);

        jPnlAñadirPlanPago.setBackground(new java.awt.Color(255, 255, 255));
        jPnlAñadirPlanPago.setForeground(new java.awt.Color(0, 0, 0));
        jPnlAñadirPlanPago.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLblIdentificador45.setBackground(new java.awt.Color(102, 102, 102));
        jLblIdentificador45.setFont(new java.awt.Font("HelveticaNowDisplay Medium", 0, 20)); // NOI18N
        jLblIdentificador45.setForeground(new java.awt.Color(0, 0, 0));
        jLblIdentificador45.setText("Plazos de pago ");
        jPnlAñadirPlanPago.add(jLblIdentificador45, new org.netbeans.lib.awtextra.AbsoluteConstraints(342, 162, 270, 24));

        jCboTipoPlanPago.setBackground(new java.awt.Color(255, 255, 255));
        jCboTipoPlanPago.setFont(new java.awt.Font("HelveticaNowDisplay Regular", 0, 16)); // NOI18N
        jCboTipoPlanPago.setForeground(new java.awt.Color(0, 0, 0));
        jCboTipoPlanPago.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jCboTipoPlanPagoFocusGained(evt);
            }
        });
        jPnlAñadirPlanPago.add(jCboTipoPlanPago, new org.netbeans.lib.awtextra.AbsoluteConstraints(342, 109, 270, 35));

        jLblIdentificador46.setBackground(new java.awt.Color(102, 102, 102));
        jLblIdentificador46.setFont(new java.awt.Font("HelveticaNowDisplay Medium", 0, 20)); // NOI18N
        jLblIdentificador46.setForeground(new java.awt.Color(0, 0, 0));
        jLblIdentificador46.setText("Tipo de plan de pago");
        jPnlAñadirPlanPago.add(jLblIdentificador46, new org.netbeans.lib.awtextra.AbsoluteConstraints(342, 79, 270, 24));

        jBtnGuardar.setBackground(new java.awt.Color(41, 128, 185));
        jBtnGuardar.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 0, 16)); // NOI18N
        jBtnGuardar.setForeground(new java.awt.Color(255, 255, 255));
        jBtnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/guardar-white24.png"))); // NOI18N
        jBtnGuardar.setText("GUARDAR PLAN PAGO");
        jBtnGuardar.setBorder(null);
        jBtnGuardar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jBtnGuardar.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jBtnGuardarFocusGained(evt);
            }
        });
        jBtnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnGuardarActionPerformed(evt);
            }
        });
        jPnlAñadirPlanPago.add(jBtnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(342, 481, 251, 50));

        jPnlPlazosPago.setBackground(new java.awt.Color(255, 255, 255));
        jPnlPlazosPago.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPnlPlazosPago.setForeground(new java.awt.Color(0, 0, 0));

        jTfIdentidad4.setEditable(false);
        jTfIdentidad4.setBackground(new java.awt.Color(255, 255, 255));
        jTfIdentidad4.setFont(new java.awt.Font("HelveticaNowDisplay Regular", 0, 18)); // NOI18N
        jTfIdentidad4.setForeground(new java.awt.Color(0, 0, 0));
        jTfIdentidad4.setText("Meses");
        jTfIdentidad4.setBorder(null);
        jTfIdentidad4.setSelectionColor(new java.awt.Color(0, 153, 153));

        jTfPlazosPago.setBackground(new java.awt.Color(255, 255, 255));
        jTfPlazosPago.setFont(new java.awt.Font("HelveticaNowDisplay Regular", 0, 16)); // NOI18N
        jTfPlazosPago.setForeground(new java.awt.Color(0, 0, 0));
        jTfPlazosPago.setBorder(null);
        jTfPlazosPago.setSelectionColor(new java.awt.Color(0, 153, 153));
        jTfPlazosPago.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTfPlazosPagoKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPnlPlazosPagoLayout = new javax.swing.GroupLayout(jPnlPlazosPago);
        jPnlPlazosPago.setLayout(jPnlPlazosPagoLayout);
        jPnlPlazosPagoLayout.setHorizontalGroup(
            jPnlPlazosPagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPnlPlazosPagoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTfPlazosPago, javax.swing.GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTfIdentidad4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPnlPlazosPagoLayout.setVerticalGroup(
            jPnlPlazosPagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPnlPlazosPagoLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPnlPlazosPagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTfIdentidad4, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTfPlazosPago, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jPnlAñadirPlanPago.add(jPnlPlazosPago, new org.netbeans.lib.awtextra.AbsoluteConstraints(342, 192, 270, -1));

        jLblIdentificador47.setBackground(new java.awt.Color(102, 102, 102));
        jLblIdentificador47.setFont(new java.awt.Font("HelveticaNowDisplay Medium", 0, 20)); // NOI18N
        jLblIdentificador47.setForeground(new java.awt.Color(0, 0, 0));
        jLblIdentificador47.setText("Valor de pago por plazo");
        jPnlAñadirPlanPago.add(jLblIdentificador47, new org.netbeans.lib.awtextra.AbsoluteConstraints(648, 162, 269, 24));

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel8.setForeground(new java.awt.Color(0, 0, 0));

        jTfIdentidad7.setEditable(false);
        jTfIdentidad7.setBackground(new java.awt.Color(255, 255, 255));
        jTfIdentidad7.setFont(new java.awt.Font("HelveticaNowDisplay Regular", 0, 18)); // NOI18N
        jTfIdentidad7.setForeground(new java.awt.Color(0, 0, 0));
        jTfIdentidad7.setText("L.");
        jTfIdentidad7.setBorder(null);
        jTfIdentidad7.setSelectionColor(new java.awt.Color(0, 153, 153));

        jFmtTfValorMontoPlazo.setBackground(new java.awt.Color(255, 255, 255));
        jFmtTfValorMontoPlazo.setFont(new java.awt.Font("HelveticaNowDisplay Regular", 0, 16)); // NOI18N
        jFmtTfValorMontoPlazo.setForeground(new java.awt.Color(0, 0, 0));
        jFmtTfValorMontoPlazo.setBorder(null);
        jFmtTfValorMontoPlazo.setSelectionColor(new java.awt.Color(0, 153, 153));

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTfIdentidad7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(248, Short.MAX_VALUE))
            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                    .addContainerGap(30, Short.MAX_VALUE)
                    .addComponent(jFmtTfValorMontoPlazo, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap()))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jTfIdentidad7, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel8Layout.createSequentialGroup()
                    .addGap(2, 2, 2)
                    .addComponent(jFmtTfValorMontoPlazo)
                    .addGap(2, 2, 2)))
        );

        jPnlAñadirPlanPago.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(647, 192, -1, -1));

        jBtnBuscarContrato.setBackground(new java.awt.Color(41, 128, 185));
        jBtnBuscarContrato.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 0, 16)); // NOI18N
        jBtnBuscarContrato.setForeground(new java.awt.Color(255, 255, 255));
        jBtnBuscarContrato.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/search-blanco24.png"))); // NOI18N
        jBtnBuscarContrato.setText("BUSCAR");
        jBtnBuscarContrato.setBorder(null);
        jBtnBuscarContrato.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jBtnBuscarContrato.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnBuscarContratoActionPerformed(evt);
            }
        });
        jPnlAñadirPlanPago.add(jBtnBuscarContrato, new org.netbeans.lib.awtextra.AbsoluteConstraints(37, 60, 234, 50));

        jLblIdentificador43.setBackground(new java.awt.Color(102, 102, 102));
        jLblIdentificador43.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 0, 20)); // NOI18N
        jLblIdentificador43.setForeground(new java.awt.Color(0, 0, 0));
        jLblIdentificador43.setText("Buscar un contrato:");
        jPnlAñadirPlanPago.add(jLblIdentificador43, new org.netbeans.lib.awtextra.AbsoluteConstraints(35, 26, 214, 24));

        jSeparatorY.setBackground(new java.awt.Color(153, 153, 153));

        javax.swing.GroupLayout jSeparatorYLayout = new javax.swing.GroupLayout(jSeparatorY);
        jSeparatorY.setLayout(jSeparatorYLayout);
        jSeparatorYLayout.setHorizontalGroup(
            jSeparatorYLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1, Short.MAX_VALUE)
        );
        jSeparatorYLayout.setVerticalGroup(
            jSeparatorYLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPnlAñadirPlanPago.add(jSeparatorY, new org.netbeans.lib.awtextra.AbsoluteConstraints(301, 26, -1, 505));

        jLblIdentificador48.setBackground(new java.awt.Color(102, 102, 102));
        jLblIdentificador48.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 0, 20)); // NOI18N
        jLblIdentificador48.setForeground(new java.awt.Color(0, 0, 0));
        jLblIdentificador48.setText("Relizando plan de pago a:");
        jPnlAñadirPlanPago.add(jLblIdentificador48, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 140, 234, 24));

        jLblIdentificador49.setBackground(new java.awt.Color(102, 102, 102));
        jLblIdentificador49.setFont(new java.awt.Font("HelveticaNowDisplay Medium", 0, 20)); // NOI18N
        jLblIdentificador49.setForeground(new java.awt.Color(0, 0, 0));
        jLblIdentificador49.setText("Nombre Completo");
        jPnlAñadirPlanPago.add(jLblIdentificador49, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 260, 234, 24));

        jTfRTN.setEditable(false);
        jTfRTN.setBackground(new java.awt.Color(255, 255, 255));
        jTfRTN.setFont(new java.awt.Font("HelveticaNowDisplay Regular", 0, 16)); // NOI18N
        jTfRTN.setForeground(new java.awt.Color(0, 0, 0));
        jTfRTN.setBorder(null);
        jTfRTN.setSelectionColor(new java.awt.Color(0, 153, 153));
        jPnlAñadirPlanPago.add(jTfRTN, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 200, 234, 35));

        jTfBloque.setEditable(false);
        jTfBloque.setBackground(new java.awt.Color(255, 255, 255));
        jTfBloque.setFont(new java.awt.Font("HelveticaNowDisplay Regular", 0, 16)); // NOI18N
        jTfBloque.setForeground(new java.awt.Color(0, 0, 0));
        jTfBloque.setBorder(null);
        jTfBloque.setSelectionColor(new java.awt.Color(0, 153, 153));
        jPnlAñadirPlanPago.add(jTfBloque, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 360, 100, 35));

        jLblIdentificador50.setBackground(new java.awt.Color(102, 102, 102));
        jLblIdentificador50.setFont(new java.awt.Font("HelveticaNowDisplay Medium", 0, 20)); // NOI18N
        jLblIdentificador50.setForeground(new java.awt.Color(0, 0, 0));
        jLblIdentificador50.setText("Bloque");
        jPnlAñadirPlanPago.add(jLblIdentificador50, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 330, 100, 24));

        jTfCasa.setEditable(false);
        jTfCasa.setBackground(new java.awt.Color(255, 255, 255));
        jTfCasa.setFont(new java.awt.Font("HelveticaNowDisplay Regular", 0, 16)); // NOI18N
        jTfCasa.setForeground(new java.awt.Color(0, 0, 0));
        jTfCasa.setBorder(null);
        jTfCasa.setSelectionColor(new java.awt.Color(0, 153, 153));
        jPnlAñadirPlanPago.add(jTfCasa, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 360, 100, 35));

        jLblIdentificador51.setBackground(new java.awt.Color(102, 102, 102));
        jLblIdentificador51.setFont(new java.awt.Font("HelveticaNowDisplay Medium", 0, 20)); // NOI18N
        jLblIdentificador51.setForeground(new java.awt.Color(0, 0, 0));
        jLblIdentificador51.setText("Casa");
        jPnlAñadirPlanPago.add(jLblIdentificador51, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 330, 100, 24));

        jLblIdentificador52.setBackground(new java.awt.Color(102, 102, 102));
        jLblIdentificador52.setFont(new java.awt.Font("HelveticaNowDisplay Medium", 0, 20)); // NOI18N
        jLblIdentificador52.setForeground(new java.awt.Color(0, 0, 0));
        jLblIdentificador52.setText("RTN");
        jPnlAñadirPlanPago.add(jLblIdentificador52, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 170, 234, 24));

        jTfNombreCompleto.setEditable(false);
        jTfNombreCompleto.setBackground(new java.awt.Color(255, 255, 255));
        jTfNombreCompleto.setFont(new java.awt.Font("HelveticaNowDisplay Regular", 0, 16)); // NOI18N
        jTfNombreCompleto.setForeground(new java.awt.Color(0, 0, 0));
        jTfNombreCompleto.setBorder(null);
        jTfNombreCompleto.setSelectionColor(new java.awt.Color(0, 153, 153));
        jPnlAñadirPlanPago.add(jTfNombreCompleto, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 290, 234, 35));

        jLblIdentificador53.setBackground(new java.awt.Color(102, 102, 102));
        jLblIdentificador53.setFont(new java.awt.Font("HelveticaNowDisplay Medium", 0, 20)); // NOI18N
        jLblIdentificador53.setForeground(new java.awt.Color(0, 0, 0));
        jLblIdentificador53.setText("Información del plan de pago:");
        jLblIdentificador53.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLblIdentificador53MouseClicked(evt);
            }
        });
        jPnlAñadirPlanPago.add(jLblIdentificador53, new org.netbeans.lib.awtextra.AbsoluteConstraints(342, 252, 300, 24));

        jTblDetallePago.setFont(new java.awt.Font("HelveticaNowDisplay Regular", 0, 14)); // NOI18N
        jTblDetallePago.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Numero de Identidad", "Nombre", "Bloque", "Casa", "Tipo Plan Pago", "Plazo", "Valor"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTblDetallePago.setRowHeight(30);
        jTblDetallePago.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(jTblDetallePago);

        jPnlAñadirPlanPago.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(342, 282, 575, 176));

        jBtnCancelar.setBackground(new java.awt.Color(41, 128, 185));
        jBtnCancelar.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 0, 16)); // NOI18N
        jBtnCancelar.setForeground(new java.awt.Color(255, 255, 255));
        jBtnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/cancelar-blanco24.png"))); // NOI18N
        jBtnCancelar.setText("CANCELAR PLAN PAGO");
        jBtnCancelar.setBorder(null);
        jBtnCancelar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPnlAñadirPlanPago.add(jBtnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(672, 481, 245, 50));

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));
        jPanel11.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel11.setForeground(new java.awt.Color(0, 0, 0));

        jTfIdentidad16.setEditable(false);
        jTfIdentidad16.setBackground(new java.awt.Color(255, 255, 255));
        jTfIdentidad16.setFont(new java.awt.Font("HelveticaNowDisplay Regular", 0, 18)); // NOI18N
        jTfIdentidad16.setForeground(new java.awt.Color(0, 0, 0));
        jTfIdentidad16.setText("L.");
        jTfIdentidad16.setBorder(null);
        jTfIdentidad16.setSelectionColor(new java.awt.Color(0, 153, 153));

        jFmtTfValorMontoPlazo1.setBackground(new java.awt.Color(255, 255, 255));
        jFmtTfValorMontoPlazo1.setBorder(null);
        jFmtTfValorMontoPlazo1.setForeground(new java.awt.Color(0, 0, 0));
        jFmtTfValorMontoPlazo1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,###.00"))));
        jFmtTfValorMontoPlazo1.setFont(new java.awt.Font("HelveticaNowDisplay Regular", 0, 16)); // NOI18N

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTfIdentidad16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jFmtTfValorMontoPlazo1)
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jTfIdentidad16, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jFmtTfValorMontoPlazo1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPnlAñadirPlanPago.add(jPanel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(704, 249, 213, -1));

        jLblIdentificador54.setBackground(new java.awt.Color(102, 102, 102));
        jLblIdentificador54.setFont(new java.awt.Font("HelveticaNowDisplay Medium", 0, 20)); // NOI18N
        jLblIdentificador54.setForeground(new java.awt.Color(0, 0, 0));
        jLblIdentificador54.setText("Total:");
        jPnlAñadirPlanPago.add(jLblIdentificador54, new org.netbeans.lib.awtextra.AbsoluteConstraints(648, 252, -1, 24));

        jPnlSeparator.setBackground(new java.awt.Color(153, 153, 153));

        javax.swing.GroupLayout jPnlSeparatorLayout = new javax.swing.GroupLayout(jPnlSeparator);
        jPnlSeparator.setLayout(jPnlSeparatorLayout);
        jPnlSeparatorLayout.setHorizontalGroup(
            jPnlSeparatorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPnlSeparatorLayout.setVerticalGroup(
            jPnlSeparatorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1, Short.MAX_VALUE)
        );

        jPnlAñadirPlanPago.add(jPnlSeparator, new org.netbeans.lib.awtextra.AbsoluteConstraints(37, 120, 234, -1));

        jLblIdentificador55.setBackground(new java.awt.Color(102, 102, 102));
        jLblIdentificador55.setFont(new java.awt.Font("HelveticaNowDisplay Medium", 0, 20)); // NOI18N
        jLblIdentificador55.setForeground(new java.awt.Color(0, 0, 0));
        jLblIdentificador55.setText("Cód. Plan de pago:");
        jPnlAñadirPlanPago.add(jLblIdentificador55, new org.netbeans.lib.awtextra.AbsoluteConstraints(342, 30, -1, 24));

        jTfCodPlanPago.setEditable(false);
        jTfCodPlanPago.setBackground(new java.awt.Color(255, 255, 255));
        jTfCodPlanPago.setFont(new java.awt.Font("HelveticaNowDisplay Regular", 0, 20)); // NOI18N
        jTfCodPlanPago.setForeground(new java.awt.Color(0, 0, 0));
        jTfCodPlanPago.setBorder(null);
        jTfCodPlanPago.setSelectionColor(new java.awt.Color(0, 153, 153));
        jPnlAñadirPlanPago.add(jTfCodPlanPago, new org.netbeans.lib.awtextra.AbsoluteConstraints(522, 26, 90, 35));

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));
        jPanel10.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel10.setForeground(new java.awt.Color(0, 0, 0));

        jTfIdentidad8.setEditable(false);
        jTfIdentidad8.setBackground(new java.awt.Color(255, 255, 255));
        jTfIdentidad8.setFont(new java.awt.Font("HelveticaNowDisplay Regular", 0, 18)); // NOI18N
        jTfIdentidad8.setForeground(new java.awt.Color(0, 0, 0));
        jTfIdentidad8.setText("L.");
        jTfIdentidad8.setBorder(null);
        jTfIdentidad8.setSelectionColor(new java.awt.Color(0, 153, 153));

        jFmtTfMontoAdeudado.setBackground(new java.awt.Color(255, 255, 255));
        jFmtTfMontoAdeudado.setFont(new java.awt.Font("HelveticaNowDisplay Regular", 0, 16)); // NOI18N
        jFmtTfMontoAdeudado.setForeground(new java.awt.Color(0, 0, 0));
        jFmtTfMontoAdeudado.setBorder(null);
        jFmtTfMontoAdeudado.setSelectionColor(new java.awt.Color(0, 153, 153));

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTfIdentidad8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jFmtTfMontoAdeudado, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addComponent(jTfIdentidad8, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 2, Short.MAX_VALUE))
            .addComponent(jFmtTfMontoAdeudado)
        );

        jPnlAñadirPlanPago.add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(647, 109, -1, 35));

        jLblIdentificador72.setBackground(new java.awt.Color(102, 102, 102));
        jLblIdentificador72.setFont(new java.awt.Font("HelveticaNowDisplay Medium", 0, 20)); // NOI18N
        jLblIdentificador72.setForeground(new java.awt.Color(0, 0, 0));
        jLblIdentificador72.setText("Monto adeudado");
        jPnlAñadirPlanPago.add(jLblIdentificador72, new org.netbeans.lib.awtextra.AbsoluteConstraints(647, 79, 269, 24));

        JLblSeleccionarContrato.setBackground(new java.awt.Color(102, 102, 102));
        JLblSeleccionarContrato.setFont(new java.awt.Font("HelveticaNowDisplay Medium", 0, 12)); // NOI18N
        JLblSeleccionarContrato.setForeground(new java.awt.Color(41, 128, 185));
        JLblSeleccionarContrato.setText("Por favor, busque un contrato.");
        JLblSeleccionarContrato.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPnlAñadirPlanPago.add(JLblSeleccionarContrato, new org.netbeans.lib.awtextra.AbsoluteConstraints(342, 468, 250, -1));

        jTfIdContrato.setEditable(false);
        jTfIdContrato.setBackground(new java.awt.Color(255, 255, 255));
        jTfIdContrato.setFont(new java.awt.Font("HelveticaNowDisplay Regular", 0, 16)); // NOI18N
        jTfIdContrato.setForeground(new java.awt.Color(0, 0, 0));
        jTfIdContrato.setBorder(null);
        jTfIdContrato.setSelectionColor(new java.awt.Color(0, 153, 153));
        jPnlAñadirPlanPago.add(jTfIdContrato, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 520, 50, 20));

        jTabPlanPago.addTab("tab1", jPnlAñadirPlanPago);

        jPnlPlazos.setBackground(new java.awt.Color(255, 255, 255));
        jPnlPlazos.setForeground(new java.awt.Color(0, 0, 0));

        jTblPlazosPago.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Numero identidad", "Nombre Completo", "Bloque", "Casa", "Plazo", "Pago"
            }
        ));
        jScrollPane4.setViewportView(jTblPlazosPago);

        jBtnCancelarPlanPago.setBackground(new java.awt.Color(41, 128, 185));
        jBtnCancelarPlanPago.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 0, 16)); // NOI18N
        jBtnCancelarPlanPago.setForeground(new java.awt.Color(255, 255, 255));
        jBtnCancelarPlanPago.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/eliminar-blanco24.png"))); // NOI18N
        jBtnCancelarPlanPago.setText("CANCELAR PLAN PAGO");
        jBtnCancelarPlanPago.setBorder(null);
        jBtnCancelarPlanPago.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jLblIdentificador59.setBackground(new java.awt.Color(102, 102, 102));
        jLblIdentificador59.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 0, 20)); // NOI18N
        jLblIdentificador59.setForeground(new java.awt.Color(0, 0, 0));
        jLblIdentificador59.setText("Plan de pago con sus plazos:");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1, Short.MAX_VALUE)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jLblIdentificador60.setBackground(new java.awt.Color(102, 102, 102));
        jLblIdentificador60.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 0, 20)); // NOI18N
        jLblIdentificador60.setForeground(new java.awt.Color(0, 0, 0));
        jLblIdentificador60.setText("Información del plazo");

        jLblIdentificador61.setBackground(new java.awt.Color(102, 102, 102));
        jLblIdentificador61.setFont(new java.awt.Font("HelveticaNowDisplay Medium", 0, 20)); // NOI18N
        jLblIdentificador61.setForeground(new java.awt.Color(0, 0, 0));
        jLblIdentificador61.setText("Número de plazo");

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));
        jPanel12.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel12.setForeground(new java.awt.Color(0, 0, 0));

        jLblIdentificador62.setBackground(new java.awt.Color(102, 102, 102));
        jLblIdentificador62.setFont(new java.awt.Font("HelveticaNowDisplay Regular", 0, 20)); // NOI18N
        jLblIdentificador62.setForeground(new java.awt.Color(0, 0, 0));
        jLblIdentificador62.setText("Mes");

        jTfNPlazo.setEditable(false);
        jTfNPlazo.setBackground(new java.awt.Color(255, 255, 255));
        jTfNPlazo.setFont(new java.awt.Font("HelveticaNowDisplay Regular", 0, 16)); // NOI18N
        jTfNPlazo.setForeground(new java.awt.Color(0, 0, 0));
        jTfNPlazo.setBorder(null);
        jTfNPlazo.setSelectionColor(new java.awt.Color(0, 153, 153));

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLblIdentificador62)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTfNPlazo)
                .addContainerGap())
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLblIdentificador62, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTfNPlazo, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLblIdentificador63.setBackground(new java.awt.Color(102, 102, 102));
        jLblIdentificador63.setFont(new java.awt.Font("HelveticaNowDisplay Medium", 0, 20)); // NOI18N
        jLblIdentificador63.setForeground(new java.awt.Color(0, 0, 0));
        jLblIdentificador63.setText("Valor del pago");

        jPanel13.setBackground(new java.awt.Color(255, 255, 255));
        jPanel13.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel13.setForeground(new java.awt.Color(0, 0, 0));

        jLblIdentificador64.setBackground(new java.awt.Color(102, 102, 102));
        jLblIdentificador64.setFont(new java.awt.Font("HelveticaNowDisplay Regular", 0, 20)); // NOI18N
        jLblIdentificador64.setForeground(new java.awt.Color(0, 0, 0));
        jLblIdentificador64.setText("L.");

        jFmtTfValorPagoPlazo.setBackground(new java.awt.Color(255, 255, 255));
        jFmtTfValorPagoPlazo.setBorder(null);
        jFmtTfValorPagoPlazo.setForeground(new java.awt.Color(0, 0, 0));
        jFmtTfValorPagoPlazo.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,###.00"))));
        jFmtTfValorPagoPlazo.setFont(new java.awt.Font("HelveticaNowDisplay Regular", 0, 16)); // NOI18N

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLblIdentificador64)
                .addContainerGap(188, Short.MAX_VALUE))
            .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                    .addContainerGap(30, Short.MAX_VALUE)
                    .addComponent(jFmtTfValorPagoPlazo, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap()))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addComponent(jLblIdentificador64, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel13Layout.createSequentialGroup()
                    .addComponent(jFmtTfValorPagoPlazo, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 15, Short.MAX_VALUE)))
        );

        jLblIdentificador65.setBackground(new java.awt.Color(102, 102, 102));
        jLblIdentificador65.setFont(new java.awt.Font("HelveticaNowDisplay Medium", 0, 20)); // NOI18N
        jLblIdentificador65.setForeground(new java.awt.Color(0, 0, 0));
        jLblIdentificador65.setText("Cód. Plan Pago:");

        jTfCodPlanPagoPlazos.setEditable(false);
        jTfCodPlanPagoPlazos.setBackground(new java.awt.Color(255, 255, 255));
        jTfCodPlanPagoPlazos.setFont(new java.awt.Font("HelveticaNowDisplay Regular", 0, 16)); // NOI18N
        jTfCodPlanPagoPlazos.setForeground(new java.awt.Color(0, 0, 0));
        jTfCodPlanPagoPlazos.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTfCodPlanPagoPlazos.setSelectionColor(new java.awt.Color(0, 153, 153));

        jBtnPagarPlazo.setBackground(new java.awt.Color(41, 128, 185));
        jBtnPagarPlazo.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 0, 16)); // NOI18N
        jBtnPagarPlazo.setForeground(new java.awt.Color(255, 255, 255));
        jBtnPagarPlazo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/pago-blanco24.png"))); // NOI18N
        jBtnPagarPlazo.setText("PLAZO PAGADO");
        jBtnPagarPlazo.setBorder(null);
        jBtnPagarPlazo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jLblIdentificador66.setBackground(new java.awt.Color(102, 102, 102));
        jLblIdentificador66.setFont(new java.awt.Font("HelveticaNowDisplay Medium", 0, 20)); // NOI18N
        jLblIdentificador66.setForeground(new java.awt.Color(0, 0, 0));
        jLblIdentificador66.setText("RTN");

        jTfRTNPlazos.setEditable(false);
        jTfRTNPlazos.setBackground(new java.awt.Color(255, 255, 255));
        jTfRTNPlazos.setFont(new java.awt.Font("HelveticaNowDisplay Regular", 0, 16)); // NOI18N
        jTfRTNPlazos.setForeground(new java.awt.Color(0, 0, 0));
        jTfRTNPlazos.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTfRTNPlazos.setSelectionColor(new java.awt.Color(0, 153, 153));

        jTfNombreCompletoPlazos.setEditable(false);
        jTfNombreCompletoPlazos.setBackground(new java.awt.Color(255, 255, 255));
        jTfNombreCompletoPlazos.setFont(new java.awt.Font("HelveticaNowDisplay Regular", 0, 16)); // NOI18N
        jTfNombreCompletoPlazos.setForeground(new java.awt.Color(0, 0, 0));
        jTfNombreCompletoPlazos.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTfNombreCompletoPlazos.setSelectionColor(new java.awt.Color(0, 153, 153));

        jLblIdentificador67.setBackground(new java.awt.Color(102, 102, 102));
        jLblIdentificador67.setFont(new java.awt.Font("HelveticaNowDisplay Medium", 0, 20)); // NOI18N
        jLblIdentificador67.setForeground(new java.awt.Color(0, 0, 0));
        jLblIdentificador67.setText("Nombre completo");

        jLblIdentificador68.setBackground(new java.awt.Color(102, 102, 102));
        jLblIdentificador68.setFont(new java.awt.Font("HelveticaNowDisplay Medium", 0, 20)); // NOI18N
        jLblIdentificador68.setForeground(new java.awt.Color(0, 0, 0));
        jLblIdentificador68.setText("Bloque");

        jLblIdentificador69.setBackground(new java.awt.Color(102, 102, 102));
        jLblIdentificador69.setFont(new java.awt.Font("HelveticaNowDisplay Medium", 0, 20)); // NOI18N
        jLblIdentificador69.setForeground(new java.awt.Color(0, 0, 0));
        jLblIdentificador69.setText("Casa");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1, Short.MAX_VALUE)
        );

        jTfBloquePlazos.setEditable(false);
        jTfBloquePlazos.setBackground(new java.awt.Color(255, 255, 255));
        jTfBloquePlazos.setFont(new java.awt.Font("HelveticaNowDisplay Regular", 0, 16)); // NOI18N
        jTfBloquePlazos.setForeground(new java.awt.Color(0, 0, 0));
        jTfBloquePlazos.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTfBloquePlazos.setSelectionColor(new java.awt.Color(0, 153, 153));

        jTfCasaPlazos.setEditable(false);
        jTfCasaPlazos.setBackground(new java.awt.Color(255, 255, 255));
        jTfCasaPlazos.setFont(new java.awt.Font("HelveticaNowDisplay Regular", 0, 16)); // NOI18N
        jTfCasaPlazos.setForeground(new java.awt.Color(0, 0, 0));
        jTfCasaPlazos.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTfCasaPlazos.setSelectionColor(new java.awt.Color(0, 153, 153));

        jLblIdentificador70.setBackground(new java.awt.Color(102, 102, 102));
        jLblIdentificador70.setFont(new java.awt.Font("HelveticaNowDisplay Medium", 0, 20)); // NOI18N
        jLblIdentificador70.setForeground(new java.awt.Color(0, 0, 0));
        jLblIdentificador70.setText("Fecha  de pago");

        jLblFechaActual.setBackground(new java.awt.Color(102, 102, 102));
        jLblFechaActual.setFont(new java.awt.Font("HelveticaNowDisplay Medium", 0, 12)); // NOI18N
        jLblFechaActual.setForeground(new java.awt.Color(41, 128, 185));
        jLblFechaActual.setText("Hoy");
        jLblFechaActual.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout jPnlPlazosLayout = new javax.swing.GroupLayout(jPnlPlazos);
        jPnlPlazos.setLayout(jPnlPlazosLayout);
        jPnlPlazosLayout.setHorizontalGroup(
            jPnlPlazosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPnlPlazosLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(jPnlPlazosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jLblIdentificador59, javax.swing.GroupLayout.DEFAULT_SIZE, 316, Short.MAX_VALUE)
                    .addGroup(jPnlPlazosLayout.createSequentialGroup()
                        .addComponent(jLblIdentificador65)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTfCodPlanPagoPlazos, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jBtnCancelarPlanPago, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(40, 40, 40)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addGroup(jPnlPlazosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLblIdentificador60, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPnlPlazosLayout.createSequentialGroup()
                        .addGroup(jPnlPlazosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPnlPlazosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPnlPlazosLayout.createSequentialGroup()
                                    .addComponent(jLblIdentificador70, javax.swing.GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLblFechaActual, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jBtnPagarPlazo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLblIdentificador66, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel13, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLblIdentificador63, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jTfRTNPlazos, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLblIdentificador68, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jTfBloquePlazos, javax.swing.GroupLayout.Alignment.LEADING))
                            .addComponent(jDtChsrFechaPago, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPnlPlazosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPnlPlazosLayout.createSequentialGroup()
                                .addGap(43, 43, 43)
                                .addGroup(jPnlPlazosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTfNombreCompletoPlazos)
                                    .addComponent(jLblIdentificador67, javax.swing.GroupLayout.DEFAULT_SIZE, 213, Short.MAX_VALUE)
                                    .addComponent(jLblIdentificador69, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jTfCasaPlazos)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPnlPlazosLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPnlPlazosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jPanel12, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLblIdentificador61, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 213, Short.MAX_VALUE))))))
                .addContainerGap(87, Short.MAX_VALUE))
        );
        jPnlPlazosLayout.setVerticalGroup(
            jPnlPlazosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPnlPlazosLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(jPnlPlazosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPnlPlazosLayout.createSequentialGroup()
                        .addComponent(jLblIdentificador60, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPnlPlazosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLblIdentificador66, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLblIdentificador67, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPnlPlazosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTfRTNPlazos, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTfNombreCompletoPlazos, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPnlPlazosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLblIdentificador68, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLblIdentificador69, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPnlPlazosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTfBloquePlazos, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTfCasaPlazos, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPnlPlazosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPnlPlazosLayout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPnlPlazosLayout.createSequentialGroup()
                                .addGroup(jPnlPlazosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLblFechaActual, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(jPnlPlazosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLblIdentificador70, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLblIdentificador61, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jDtChsrFechaPago, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addComponent(jLblIdentificador63, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jBtnPagarPlazo, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPnlPlazosLayout.createSequentialGroup()
                        .addComponent(jLblIdentificador59, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPnlPlazosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLblIdentificador65, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTfCodPlanPagoPlazos, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 329, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(jBtnCancelarPlanPago, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(31, Short.MAX_VALUE))
        );

        jTabPlanPago.addTab("tab3", jPnlPlazos);

        jPnlListadoPlanPago.setBackground(new java.awt.Color(255, 255, 255));
        jPnlListadoPlanPago.setForeground(new java.awt.Color(0, 0, 0));

        jLblIdentificador57.setBackground(new java.awt.Color(102, 102, 102));
        jLblIdentificador57.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 0, 20)); // NOI18N
        jLblIdentificador57.setForeground(new java.awt.Color(0, 0, 0));
        jLblIdentificador57.setText("Buscar plan de pago por:");

        jRdBtnAbonado.setBackground(new java.awt.Color(255, 255, 255));
        btnGpListadoPlanPagos.add(jRdBtnAbonado);
        jRdBtnAbonado.setFont(new java.awt.Font("HelveticaNowDisplay Light", 0, 14)); // NOI18N
        jRdBtnAbonado.setForeground(new java.awt.Color(0, 0, 0));
        jRdBtnAbonado.setSelected(true);
        jRdBtnAbonado.setText("Abonado");
        jRdBtnAbonado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRdBtnAbonadoActionPerformed(evt);
            }
        });

        jRdBtnPegue.setBackground(new java.awt.Color(255, 255, 255));
        btnGpListadoPlanPagos.add(jRdBtnPegue);
        jRdBtnPegue.setFont(new java.awt.Font("HelveticaNowDisplay Light", 0, 14)); // NOI18N
        jRdBtnPegue.setForeground(new java.awt.Color(0, 0, 0));
        jRdBtnPegue.setText("Pegue");
        jRdBtnPegue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRdBtnPegueActionPerformed(evt);
            }
        });

        jRdBtnRecientes.setBackground(new java.awt.Color(255, 255, 255));
        btnGpListadoPlanPagos.add(jRdBtnRecientes);
        jRdBtnRecientes.setFont(new java.awt.Font("HelveticaNowDisplay Light", 0, 14)); // NOI18N
        jRdBtnRecientes.setForeground(new java.awt.Color(0, 0, 0));
        jRdBtnRecientes.setText("Recientes");

        jRdBtnRangoFecha.setBackground(new java.awt.Color(255, 255, 255));
        btnGpListadoPlanPagos.add(jRdBtnRangoFecha);
        jRdBtnRangoFecha.setFont(new java.awt.Font("HelveticaNowDisplay Light", 0, 14)); // NOI18N
        jRdBtnRangoFecha.setForeground(new java.awt.Color(0, 0, 0));
        jRdBtnRangoFecha.setText("Rango fecha");

        jPnlBuscar.setBackground(new java.awt.Color(255, 255, 255));
        jPnlBuscar.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPnlBuscar.setLayout(null);

        jTfBuscarTodoPlanPago.setBackground(new java.awt.Color(255, 255, 255));
        jTfBuscarTodoPlanPago.setFont(new java.awt.Font("HelveticaNowDisplay Regular", 0, 18)); // NOI18N
        jTfBuscarTodoPlanPago.setForeground(new java.awt.Color(0, 0, 0));
        jTfBuscarTodoPlanPago.setBorder(null);
        jTfBuscarTodoPlanPago.setSelectionColor(new java.awt.Color(0, 153, 153));
        jTfBuscarTodoPlanPago.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTfBuscarTodoPlanPagoKeyReleased(evt);
            }
        });
        jPnlBuscar.add(jTfBuscarTodoPlanPago);
        jTfBuscarTodoPlanPago.setBounds(8, 2, 182, 30);

        jBtnBuscar.setBackground(new java.awt.Color(9, 132, 227));
        jBtnBuscar.setForeground(new java.awt.Color(9, 132, 227));
        jBtnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/search-black24.png"))); // NOI18N
        jBtnBuscar.setToolTipText("Click para buscar");
        jBtnBuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPnlBuscar.add(jBtnBuscar);
        jBtnBuscar.setBounds(212, 2, 30, 30);

        jTfBuscarBloque.setBackground(new java.awt.Color(255, 255, 255));
        jTfBuscarBloque.setFont(new java.awt.Font("HelveticaNowDisplay Regular", 0, 18)); // NOI18N
        jTfBuscarBloque.setForeground(new java.awt.Color(0, 0, 0));
        jTfBuscarBloque.setBorder(null);
        jTfBuscarBloque.setSelectionColor(new java.awt.Color(0, 153, 153));
        jPnlBuscar.add(jTfBuscarBloque);
        jTfBuscarBloque.setBounds(8, 2, 80, 30);

        jTfBuscarCasa.setBackground(new java.awt.Color(255, 255, 255));
        jTfBuscarCasa.setFont(new java.awt.Font("HelveticaNowDisplay Regular", 0, 18)); // NOI18N
        jTfBuscarCasa.setForeground(new java.awt.Color(0, 0, 0));
        jTfBuscarCasa.setBorder(null);
        jTfBuscarCasa.setSelectionColor(new java.awt.Color(0, 153, 153));
        jPnlBuscar.add(jTfBuscarCasa);
        jTfBuscarCasa.setBounds(100, 2, 80, 30);

        jTblTodoPlanPago.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "#", "Número de identidad", "Nombre Completo", "Bloque", "Casa", "Plazos", "Valor"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(jTblTodoPlanPago);

        jLblVerDetallePlanPago.setBackground(new java.awt.Color(255, 255, 255));
        jLblVerDetallePlanPago.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 0, 20)); // NOI18N
        jLblVerDetallePlanPago.setForeground(new java.awt.Color(41, 128, 185));
        jLblVerDetallePlanPago.setText("Ver plan de pago");

        jDtChsrIncio.setBackground(new java.awt.Color(255, 255, 255));
        jDtChsrIncio.setForeground(new java.awt.Color(0, 0, 0));
        jDtChsrIncio.setToolTipText("Inicio de rango de la fecha");
        jDtChsrIncio.setFont(new java.awt.Font("HelveticaNowDisplay Regular", 0, 16)); // NOI18N

        jLblDe.setBackground(new java.awt.Color(102, 102, 102));
        jLblDe.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 0, 20)); // NOI18N
        jLblDe.setForeground(new java.awt.Color(0, 0, 0));
        jLblDe.setText("De:");

        jDtChsrFinal.setBackground(new java.awt.Color(255, 255, 255));
        jDtChsrFinal.setForeground(new java.awt.Color(0, 0, 0));
        jDtChsrFinal.setToolTipText("Inicio de rango de la fecha");
        jDtChsrFinal.setFont(new java.awt.Font("HelveticaNowDisplay Regular", 0, 16)); // NOI18N

        jLblHasta.setBackground(new java.awt.Color(102, 102, 102));
        jLblHasta.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 0, 20)); // NOI18N
        jLblHasta.setForeground(new java.awt.Color(0, 0, 0));
        jLblHasta.setText("Hasta:");

        javax.swing.GroupLayout jPnlListadoPlanPagoLayout = new javax.swing.GroupLayout(jPnlListadoPlanPago);
        jPnlListadoPlanPago.setLayout(jPnlListadoPlanPagoLayout);
        jPnlListadoPlanPagoLayout.setHorizontalGroup(
            jPnlListadoPlanPagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPnlListadoPlanPagoLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(jPnlListadoPlanPagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane3)
                    .addGroup(jPnlListadoPlanPagoLayout.createSequentialGroup()
                        .addGroup(jPnlListadoPlanPagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPnlListadoPlanPagoLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLblDe)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jDtChsrIncio, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLblIdentificador57, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPnlListadoPlanPagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPnlListadoPlanPagoLayout.createSequentialGroup()
                                .addComponent(jRdBtnAbonado)
                                .addGap(18, 18, 18)
                                .addComponent(jRdBtnPegue)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jRdBtnRecientes))
                            .addGroup(jPnlListadoPlanPagoLayout.createSequentialGroup()
                                .addComponent(jLblHasta)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jDtChsrFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jRdBtnRangoFecha)
                        .addGroup(jPnlListadoPlanPagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPnlListadoPlanPagoLayout.createSequentialGroup()
                                .addGap(116, 116, 116)
                                .addComponent(jLblVerDetallePlanPago))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPnlListadoPlanPagoLayout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addComponent(jPnlBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(85, 85, 85))
        );
        jPnlListadoPlanPagoLayout.setVerticalGroup(
            jPnlListadoPlanPagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPnlListadoPlanPagoLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPnlListadoPlanPagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPnlListadoPlanPagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLblIdentificador57, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jRdBtnAbonado)
                        .addComponent(jRdBtnPegue)
                        .addComponent(jRdBtnRecientes)
                        .addComponent(jRdBtnRangoFecha))
                    .addComponent(jPnlBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                .addGroup(jPnlListadoPlanPagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLblVerDetallePlanPago, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jDtChsrIncio, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(jDtChsrFinal, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLblHasta, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLblDe, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(11, 11, 11)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 408, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35))
        );

        jTabPlanPago.addTab("tab2", jPnlListadoPlanPago);

        jPanel14.setBackground(new java.awt.Color(255, 255, 255));
        jPanel14.setForeground(new java.awt.Color(0, 0, 0));

        jSeparatorX1.setBackground(new java.awt.Color(153, 153, 153));

        javax.swing.GroupLayout jSeparatorX1Layout = new javax.swing.GroupLayout(jSeparatorX1);
        jSeparatorX1.setLayout(jSeparatorX1Layout);
        jSeparatorX1Layout.setHorizontalGroup(
            jSeparatorX1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 303, Short.MAX_VALUE)
        );
        jSeparatorX1Layout.setVerticalGroup(
            jSeparatorX1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1, Short.MAX_VALUE)
        );

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 0, 20)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Ventana de añadir plan de pago");

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparatorX1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(342, 342, 342))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(jSeparatorX1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(jLabel2)))
                .addContainerGap(515, Short.MAX_VALUE))
        );

        jScrollPane5.setViewportView(jPanel14);

        jTabPlanPago.addTab("tab4", jScrollPane5);

        jPanel1.add(jTabPlanPago, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 60, 1030, 570));

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
    
    // Evento que le esconde el SideBar
    private void jBtnSideBar1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBtnSideBar1MouseClicked
        // TODO add your handling code here:
        this.sideBar.jTextAreaXLeft(0, -210 , 5, 5, jSideBar1);
        this.jBtnSideBar1.setVisible(false);
        this.jBtnSideBar.setVisible(true);
    }//GEN-LAST:event_jBtnSideBar1MouseClicked
    
    // Evento que muestra el SideBar
    private void jBtnSideBarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBtnSideBarMouseClicked
        // TODO add your handling code here:
        this.sideBar.jTextAreaXRight(-210, 0 , 5, 5, jSideBar1);
        this.jBtnSideBar.setVisible(false);
        this.jBtnSideBar1.setVisible(true);
    }//GEN-LAST:event_jBtnSideBarMouseClicked

    private void jSBAyudaPlanPagoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jSBAyudaPlanPagoMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jSBAyudaPlanPagoMouseClicked

    private void jSBVerDetallePlanPagoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jSBVerDetallePlanPagoMouseClicked
        this.jTabPlanPago.setSelectedIndex(1);
        this.jSBVerDetallePlanPago.setBackground(celeste);
        this.jSBAMEPlanPago.setBackground(azulmarino);
        this.jSBListadoPlanPago.setBackground(azulmarino);
    }//GEN-LAST:event_jSBVerDetallePlanPagoMouseClicked

    private void jSBListadoPlanPagoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jSBListadoPlanPagoMouseClicked
        this.habilitarControlesListadoPago(true, false, false, true, false, false, false, false, false);
        this.jTabPlanPago.setSelectedIndex(2);
        this.jSBListadoPlanPago.setBackground(celeste);
        this.jSBVerDetallePlanPago.setBackground(azulmarino);
        this.jSBAMEPlanPago.setBackground(azulmarino);
        try {
            this.cargarTodosPlanPago();
        } catch (SQLException ex) {
            Logger.getLogger(JFraPlanPago.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jSBListadoPlanPagoMouseClicked

    private void jLblSalirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLblSalirMouseClicked
        this.dispose();
    }//GEN-LAST:event_jLblSalirMouseClicked

    private void jLblMinimizar2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLblMinimizar2MouseClicked
        this.setState(Frame.ICONIFIED);
    }//GEN-LAST:event_jLblMinimizar2MouseClicked

    private void jBtnBuscarContratoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnBuscarContratoActionPerformed
        
        try {
            this.cargarTodosContratos();
        } catch (SQLException ex) {
            Logger.getLogger(JFraPlanPago.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        /*try {*/
            /*if(this.jRdBtnPegueBuscar1.isSelected()){
                //this.cargarContratoPegueDialog();
            }else if(this.jRdBtnAbonado.isSelected()){
                //this.cargarContratoRTNDialog();
            } */         
            
            // Mostramos la ventana modal
            //this.jDlgContrato.add(this.jPnlDialog);
            this.jDlgContrato.setVisible(true);
            this.jDlgContrato.setAlwaysOnTop(true);
            
        /*} catch (SQLException ex) {
            Logger.getLogger(JFraPlanPago.class.getName()).log(Level.SEVERE, null, ex);
        }*/
    }//GEN-LAST:event_jBtnBuscarContratoActionPerformed

    private void jBtnSeleccionarDialogActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnSeleccionarDialogActionPerformed
        this.seleccionarFila();
        this.jDlgContrato.setVisible(false);
        this.jDlgContrato.setAlwaysOnTop(false);
        this.jCboTipoPlanPago.requestFocus();
        this.jCboTipoPlanPago.requestFocus();
    }//GEN-LAST:event_jBtnSeleccionarDialogActionPerformed

    private void jBtnCancelarDialogActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnCancelarDialogActionPerformed
        this.jDlgContrato.setVisible(false);
        this.jDlgContrato.setAlwaysOnTop(false);
    }//GEN-LAST:event_jBtnCancelarDialogActionPerformed

    private void jCboTipoPlanPagoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jCboTipoPlanPagoFocusGained
        if(jTfRTN.getText().equals("")){
            this.permitirGuardar(false);
            this.jTblDetallePago.setEnabled(true);
        } else {
            this.permitirGuardar(true);
        }
    }//GEN-LAST:event_jCboTipoPlanPagoFocusGained

    private void jLblIdentificador53MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLblIdentificador53MouseClicked
        //cargarTblPlanPago();
    }//GEN-LAST:event_jLblIdentificador53MouseClicked

    private void jBtnGuardarFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jBtnGuardarFocusGained
        //cargarTblPlanPago();
    }//GEN-LAST:event_jBtnGuardarFocusGained

    private void jTfPlazosPagoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTfPlazosPagoKeyReleased
        try{
            if(this.jTfPlazosPago.getText().equals("")){
                this.calcularValorPlazo(true);
                this.limpiarTablaPlanPago();
                System.out.println("Esta vacio");
            } else {
                this.calcularValorPlazo(false);
                cargarTblPlanPago();
            }

        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e.toString());
        }
    }//GEN-LAST:event_jTfPlazosPagoKeyReleased

    private void jRdBtnPegueBuscar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRdBtnPegueBuscar1ActionPerformed
        this.habilitarControles(false, true, true);
        this.jLblBloqueORTN.setText("Bloque");
        this.jLblCasaBuscar.setText("Casa");
        this.jTfRTNBuscar.setText("");

    }//GEN-LAST:event_jRdBtnPegueBuscar1ActionPerformed

    private void jRdBtnRTNBuscar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRdBtnRTNBuscar1ActionPerformed
        this.habilitarControles(true, false, false);
        this.jLblBloqueORTN.setText("Número de identidad");
        this.jLblCasaBuscar.setText("");
        this.jTfRTNBuscar.requestFocus();
        this.jTfBloqueBuscar.setText("");
        this.jTfCasaBuscar.setText("");
    }//GEN-LAST:event_jRdBtnRTNBuscar1ActionPerformed

    private void jBtnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnGuardarActionPerformed
        try {
            CDPlanPago cdpp = new CDPlanPago();
            cll.setIdUsuario(2);
            cdpp.insertarPlanPago(clpp, cll);
            JLblSeleccionarContrato.setVisible(true);
            JLblSeleccionarContrato.setForeground(verde);
            JLblSeleccionarContrato.setText(String.format("!Se guardó el planpago a: %s¡", clpp.getNombre()+' '+clpp.getApellidos()));
        } catch (SQLException ex) {
            Logger.getLogger(JFraPlanPago.class.getName()).log(Level.SEVERE, null, ex);
        }       
        
    }//GEN-LAST:event_jBtnGuardarActionPerformed

    private void jRdBtnAbonadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRdBtnAbonadoActionPerformed
        this.habilitarControlesListadoPago(true, false, false, true, false, false, false, false, false);
    }//GEN-LAST:event_jRdBtnAbonadoActionPerformed

    private void jTfBuscarTodoPlanPagoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTfBuscarTodoPlanPagoKeyReleased
        try {
            this.cargarTodosPlanPagoPorAbonado(this.jTfBuscarTodoPlanPago.getText());
        } catch (SQLException ex) {
            Logger.getLogger(JFraPlanPago.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }//GEN-LAST:event_jTfBuscarTodoPlanPagoKeyReleased

    private void jRdBtnPegueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRdBtnPegueActionPerformed
        this.habilitarControlesListadoPago(false, true, true, true, false, false, false, false, false);
    }//GEN-LAST:event_jRdBtnPegueActionPerformed

    private void jBtnSiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnSiActionPerformed
        this.dispose();
        this.jDfMenu.dispose();
        jfm.setVisible(true);

    }//GEN-LAST:event_jBtnSiActionPerformed

    private void JBtnNoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBtnNoActionPerformed
        this.jDfMenu.dispose();
    }//GEN-LAST:event_JBtnNoActionPerformed

    private void jSBMenuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jSBMenuMouseClicked
        this.jDfMenu.setLocationRelativeTo(null);
        this.jDfMenu.setVisible(true);
    }//GEN-LAST:event_jSBMenuMouseClicked

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
            java.util.logging.Logger.getLogger(JFraPlanPago.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFraPlanPago.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFraPlanPago.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFraPlanPago.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new JFraPlanPago().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(JFraPlanPago.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton JBtnNo;
    private javax.swing.JLabel JLblSeleccionarContrato;
    private javax.swing.ButtonGroup btnGpListadoPlanPagos;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jBtnBuscar;
    private javax.swing.JButton jBtnBuscarContrato;
    private javax.swing.JButton jBtnCancelar;
    private javax.swing.JButton jBtnCancelarDialog;
    private javax.swing.JButton jBtnCancelarPlanPago;
    private javax.swing.JButton jBtnGuardar;
    private javax.swing.JButton jBtnPagarPlazo;
    private javax.swing.JButton jBtnSeleccionarDialog;
    private javax.swing.JButton jBtnSi;
    private javax.swing.JLabel jBtnSideBar;
    private javax.swing.JLabel jBtnSideBar1;
    private javax.swing.JComboBox<String> jCboTipoPlanPago;
    private javax.swing.JDialog jDfMenu;
    private javax.swing.JDialog jDlgContrato;
    private com.toedter.calendar.JDateChooser jDtChsrFechaPago;
    private com.toedter.calendar.JDateChooser jDtChsrFinal;
    private com.toedter.calendar.JDateChooser jDtChsrIncio;
    private javax.swing.JTextField jFmtTfMontoAdeudado;
    private javax.swing.JTextField jFmtTfValorMontoPlazo;
    private javax.swing.JFormattedTextField jFmtTfValorMontoPlazo1;
    private javax.swing.JFormattedTextField jFmtTfValorPagoPlazo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLblBloqueORTN;
    private javax.swing.JLabel jLblCasaBuscar;
    private javax.swing.JLabel jLblCasaBuscar1;
    private javax.swing.JLabel jLblDe;
    private javax.swing.JLabel jLblFechaActual;
    private javax.swing.JLabel jLblHasta;
    private javax.swing.JLabel jLblIdContrato;
    private javax.swing.JLabel jLblIdentificador;
    private javax.swing.JLabel jLblIdentificador1;
    private javax.swing.JLabel jLblIdentificador10;
    private javax.swing.JLabel jLblIdentificador2;
    private javax.swing.JLabel jLblIdentificador3;
    private javax.swing.JLabel jLblIdentificador4;
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
    private javax.swing.JLabel jLblIdentificador53;
    private javax.swing.JLabel jLblIdentificador54;
    private javax.swing.JLabel jLblIdentificador55;
    private javax.swing.JLabel jLblIdentificador57;
    private javax.swing.JLabel jLblIdentificador58;
    private javax.swing.JLabel jLblIdentificador59;
    private javax.swing.JLabel jLblIdentificador6;
    private javax.swing.JLabel jLblIdentificador60;
    private javax.swing.JLabel jLblIdentificador61;
    private javax.swing.JLabel jLblIdentificador62;
    private javax.swing.JLabel jLblIdentificador63;
    private javax.swing.JLabel jLblIdentificador64;
    private javax.swing.JLabel jLblIdentificador65;
    private javax.swing.JLabel jLblIdentificador66;
    private javax.swing.JLabel jLblIdentificador67;
    private javax.swing.JLabel jLblIdentificador68;
    private javax.swing.JLabel jLblIdentificador69;
    private javax.swing.JLabel jLblIdentificador7;
    private javax.swing.JLabel jLblIdentificador70;
    private javax.swing.JLabel jLblIdentificador71;
    private javax.swing.JLabel jLblIdentificador72;
    private javax.swing.JLabel jLblIdentificador73;
    private javax.swing.JLabel jLblIdentificador8;
    private javax.swing.JLabel jLblIdentificador9;
    private javax.swing.JLabel jLblMinimizar2;
    private javax.swing.JLabel jLblSalir;
    private javax.swing.JLabel jLblTitulo2;
    private javax.swing.JLabel jLblVerDetallePlanPago;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPanel jPnlAñadirPlanPago;
    private javax.swing.JPanel jPnlBuscar;
    private javax.swing.JPanel jPnlDialog;
    private javax.swing.JPanel jPnlListadoPlanPago;
    private javax.swing.JPanel jPnlPlazos;
    private javax.swing.JPanel jPnlPlazosPago;
    private javax.swing.JPanel jPnlSeparator;
    private javax.swing.JRadioButton jRdBtnAbonado;
    private javax.swing.JRadioButton jRdBtnPegue;
    private javax.swing.JRadioButton jRdBtnPegueBuscar1;
    private javax.swing.JRadioButton jRdBtnRTNBuscar1;
    private javax.swing.JRadioButton jRdBtnRangoFecha;
    private javax.swing.JRadioButton jRdBtnRecientes;
    private javax.swing.JPanel jSBAMEPlanPago;
    private javax.swing.JPanel jSBAyudaPlanPago;
    private javax.swing.JPanel jSBListadoPlanPago;
    private javax.swing.JPanel jSBMenu;
    private javax.swing.JPanel jSBVerDetallePlanPago;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JPanel jSeparatorX1;
    private javax.swing.JPanel jSeparatorY;
    private javax.swing.JPanel jSideBar;
    private javax.swing.JScrollPane jSideBar1;
    private javax.swing.JTabbedPane jTabPlanPago;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTblDetallePago;
    private javax.swing.JTable jTblDialog;
    private javax.swing.JTable jTblPlazosPago;
    private javax.swing.JTable jTblTodoPlanPago;
    private javax.swing.JTextField jTfBloque;
    private javax.swing.JTextField jTfBloqueBuscar;
    private javax.swing.JTextField jTfBloquePlazos;
    private javax.swing.JTextField jTfBuscarBloque;
    private javax.swing.JTextField jTfBuscarCasa;
    private javax.swing.JTextField jTfBuscarTodoPlanPago;
    private javax.swing.JTextField jTfCasa;
    private javax.swing.JTextField jTfCasaBuscar;
    private javax.swing.JTextField jTfCasaPlazos;
    private javax.swing.JTextField jTfCodPlanPago;
    private javax.swing.JTextField jTfCodPlanPagoPlazos;
    private javax.swing.JTextField jTfIdContrato;
    private javax.swing.JTextField jTfIdentidad16;
    private javax.swing.JTextField jTfIdentidad4;
    private javax.swing.JTextField jTfIdentidad7;
    private javax.swing.JTextField jTfIdentidad8;
    private javax.swing.JTextField jTfNPlazo;
    private javax.swing.JTextField jTfNombreCompleto;
    private javax.swing.JTextField jTfNombreCompletoPlazos;
    private javax.swing.JTextField jTfPlazosPago;
    private javax.swing.JTextField jTfRTN;
    private javax.swing.JFormattedTextField jTfRTNBuscar;
    private javax.swing.JTextField jTfRTNPlazos;
    // End of variables declaration//GEN-END:variables
}
