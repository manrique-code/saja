/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capapresentacion;

import AppPackage.AnimationClass;
import capadatos.CDAbonado;
import capalogica.CLAbonado;
import java.awt.Color;
import com.placeholder.PlaceHolder;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Manrique
 */
public class JFraAbonados extends javax.swing.JFrame {

    /**
     * Creates new form JFraAñadirAbonados
     *
     * @throws java.sql.SQLException
     */
    public JFraAbonados() throws SQLException {
        initComponents();
        llenarTablaAbonados();
        this.jLblCancelarEdicion.setVisible(false);
        this.jLblModificar1.setEnabled(false);
        this.jLblModificar2.setEnabled(false);
        this.jLblVerAbonado.setEnabled(false);
        habilitarBotones(true, false);
        Date fechaActual = new Date();
        this.jDtFechaNacimiento.setDate(fechaActual);
        this.jTblAbonados.getColumnModel().getColumn(8).setMaxWidth(1);
        this.jTblAbonados.getColumnModel().getColumn(8).setMinWidth(1);
        this.jTblAbonados.getColumnModel().getColumn(8).setPreferredWidth(1);
        this.getContentPane().setBackground(Color.white);
        this.setLocationRelativeTo(null);
        PlaceHolder ph = new PlaceHolder(this.jTfBuscar,
                new Color(153, 153, 153),
                Color.BLACK,
                "Buscar por RTN",
                false,
                "HelveticaNowDisplay Regular",
                18);
        this.jCboSexo.setOpaque(false);
        this.jLblNombreAbonado.setHorizontalAlignment(SwingConstants.CENTER);
    }

    AnimationClass sideBar = new AnimationClass();

    Color celeste = new Color(52, 152, 219);
    Color azul = new Color(52, 73, 94);
    String codAbonado;
    
    boolean modificar1 = false, modificar2 = false, verAbonado = false;

    
    // Metodo para limpiar la tabla abonados
    private void limpiarTabla() {
        DefaultTableModel dtm = (DefaultTableModel) this.jTblAbonados.getModel();

        while (dtm.getRowCount() > 0) {
            dtm.removeRow(0);
        }
    }
    
    // Metodo para limpiar la tabla contratos
    private void limpiarTablaContratos() {
        DefaultTableModel dtm = (DefaultTableModel) this.jTblContratos.getModel();

        while (dtm.getRowCount() > 0) {
            dtm.removeRow(0);
        }
    }

    // Metodo para limpiar los textfield del formulario
    public void limpiarFormulario() throws SQLException {
        this.jFtfIdentidad.setText("");
        this.jTfNombres.setText("");
        this.jTfApellidos.setText("");
        this.jFtfTelefono.setText("");
        this.jTxaDireccion.setText("");
        this.jTfCorreo.setText("");
        this.jCboSexo.setSelectedIndex(0);

        this.habilitarBotones(true, false);
        this.jFtfIdentidad.requestFocus();
        this.jTblAbonados.clearSelection();
    }

    // Metodo para habilitar los botones de edicion y guardar
    public void habilitarBotones(boolean guardar, boolean editar) {
        this.jBtnGuardar.setEnabled(guardar);
        this.jBtnModificar.setEnabled(editar);
    }

    // Metodo para los labels del contrato vuelve a su texto original
    public void textoOriginal() {
        this.jLblNombreAbonado.setText("NOMBRE DEL ABONADO AQUÍ");
        this.jLblFechaNacimiento.setText("Fecha nacimiento");
        this.jLblTelefono.setText("Número de teléfono");
        this.jLblCorreo.setText("Correo electrónico");
        this.jTxaDireccionAbonado.setText("");
    }

    // Metodo para validar que no vayan datos vacios
    public boolean validarTextField() {
        if (this.jFtfIdentidad.getText().length() < 14 || this.jTfApellidos.getText().isEmpty()
                || this.jTfNombres.getText().isEmpty() || this.jTxaDireccion.getText().isEmpty()
                || this.jFtfTelefono.getText().length() < 8) {
            return false;
        } else {
            return true;
        }
    }
    
    // Metodo para llenar la tabla abonados
    private void llenarTablaAbonados() throws SQLException {
        limpiarTabla();

        CDAbonado cda = new CDAbonado();

        List<CLAbonado> miLista = cda.ListaAbonados();
        DefaultTableModel temp = (DefaultTableModel) this.jTblAbonados.getModel();

        for (CLAbonado cla : miLista) {
            Object[] fila = new Object[9];
            fila[0] = cla.getCodAbonado();
            fila[1] = cla.getNombres();
            fila[2] = cla.getApellidos();
            fila[3] = cla.getFechaNacimiento();
            fila[4] = cla.getTelefono();
            fila[5] = cla.getCorreoElectronico();
            fila[6] = cla.getIdSexo();
            fila[7] = cla.getDireccion();
            fila[8] = cla.getNombreCompleto();
            temp.addRow(fila);
        };
    }

    // Metodo para llenar la tabla contratos
    private void llenarTablaContratoAbonados() throws SQLException {
        CDAbonado cda = new CDAbonado();
        String codAbonado = (String.valueOf(this.jTblAbonados.getValueAt(this.jTblAbonados.getSelectedRow(), 0)));

        List<CLAbonado> miLista = cda.listaContratoAbonado(codAbonado);
        DefaultTableModel temp = (DefaultTableModel) this.jTblContratos.getModel();

        for (CLAbonado cla : miLista) {
            Object[] fila = new Object[5];
            fila[0] = cla.getIdContrato();
            fila[1] = cla.getNumCasa();
            fila[2] = cla.getBloque();
            fila[3] = cla.getTipoContrato();
            fila[4] = cla.getEstadoContrato();

            temp.addRow(fila);
        };
    }

    // Metodo para buscar abonados por nombre
    private void mostrarAbonadoPorNombre(String nombre) throws SQLException {
        limpiarTabla();

        CDAbonado cda = new CDAbonado();
        List<CLAbonado> miLista = cda.mostrarAbonadoPorNombre(nombre);
        DefaultTableModel temp = (DefaultTableModel) this.jTblAbonados.getModel();

        miLista.stream().map((cl) -> {
            Object[] fila = new Object[8];
            fila[0] = cl.getCodAbonado();
            fila[1] = cl.getNombres();
            fila[2] = cl.getApellidos();
            fila[3] = cl.getFechaNacimiento();
            fila[4] = cl.getTelefono();
            fila[5] = cl.getCorreoElectronico();
            fila[6] = cl.getIdSexo();
            fila[7] = cl.getDireccion();
            return fila;
        }).forEachOrdered((fila) -> {
            temp.addRow(fila);
        });
    }

    // Metodo para mostrar aboandos por numero de identidad
    private void mostrarAbonadoPorCod(String codAbonado) throws SQLException {
        limpiarTabla();

        CDAbonado cda = new CDAbonado();
        List<CLAbonado> miLista = cda.mostrarAbonadoPorCod(codAbonado);
        DefaultTableModel temp = (DefaultTableModel) this.jTblAbonados.getModel();

        miLista.stream().map((cl) -> {
            Object[] fila = new Object[8];
            fila[0] = cl.getCodAbonado();
            fila[1] = cl.getNombres();
            fila[2] = cl.getApellidos();
            fila[3] = cl.getFechaNacimiento();
            fila[4] = cl.getTelefono();
            fila[5] = cl.getCorreoElectronico();
            fila[6] = cl.getIdSexo();
            fila[7] = cl.getDireccion();
            return fila;
        }).forEachOrdered((fila) -> {
            temp.addRow(fila);
        });
    }

    // Metodo para seleccionar los datos del abonado
    private void seleccionarAbonado() throws ParseException {
        try {
            if (this.jTblAbonados.getSelectedRow() != -1) {
                this.jFtfIdentidad.setText(String.valueOf(this.jTblAbonados.getValueAt(this.jTblAbonados.getSelectedRow(), 0)));
                this.jTfNombres.setText(String.valueOf(this.jTblAbonados.getValueAt(this.jTblAbonados.getSelectedRow(), 1)));
                this.jTfApellidos.setText(String.valueOf(this.jTblAbonados.getValueAt(this.jTblAbonados.getSelectedRow(), 2)));
                DefaultTableModel model = (DefaultTableModel) jTblAbonados.getModel();
                Date date = new SimpleDateFormat("yyyy-MM-dd").parse((String) model.getValueAt(this.jTblAbonados.getSelectedRow(), 3));
                this.jDtFechaNacimiento.setDate(date);
                this.jFtfTelefono.setText(String.valueOf(this.jTblAbonados.getValueAt(this.jTblAbonados.getSelectedRow(), 4)));
                this.jTfCorreo.setText(String.valueOf(this.jTblAbonados.getValueAt(this.jTblAbonados.getSelectedRow(), 5)));
                this.jCboSexo.setSelectedItem(String.valueOf(this.jTblAbonados.getValueAt(this.jTblAbonados.getSelectedRow(), 6)));
                this.jTxaDireccion.setText(String.valueOf(this.jTblAbonados.getValueAt(this.jTblAbonados.getSelectedRow(), 7)));
                codAbonado = this.jFtfIdentidad.getText().trim();
            }
        } catch (ParseException ex) {
            Logger.getLogger(JFraAbonados.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Metodo para llenar los datos de los contratos del abonado
    private void seleccionarContratoAbonado() throws ParseException {
        try {
            if (this.jTblAbonados.getSelectedRow() != -1) {
                this.jLblNombreAbonado.setText(String.valueOf(this.jTblAbonados.getValueAt(this.jTblAbonados.getSelectedRow(), 8)));
                DefaultTableModel model = (DefaultTableModel) jTblAbonados.getModel();
                Date date = new SimpleDateFormat("yyyy-MM-dd").parse((String) model.getValueAt(this.jTblAbonados.getSelectedRow(), 3));
                Date myDate = new Date();
                this.jLblFechaNacimiento.setText(new SimpleDateFormat("yyyy-MM-dd").format(date));
                this.jLblTelefono.setText(String.valueOf(this.jTblAbonados.getValueAt(this.jTblAbonados.getSelectedRow(), 4)));
                this.jLblCorreo.setText(String.valueOf(this.jTblAbonados.getValueAt(this.jTblAbonados.getSelectedRow(), 5)));
                this.jTxaDireccionAbonado.setText(String.valueOf(this.jTblAbonados.getValueAt(this.jTblAbonados.getSelectedRow(), 7)));
            }
        } catch (ParseException ex) {
            Logger.getLogger(JFraAbonados.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Metodo para registrar un abonado
    private void insertarAbonado() {
        try {

            CDAbonado cda = new CDAbonado();
            CLAbonado cla = new CLAbonado();

            cla.setCodAbonado(this.jFtfIdentidad.getText().trim());
            cla.setNombres(this.jTfNombres.getText().trim());
            cla.setApellidos(this.jTfApellidos.getText().trim());
            java.util.Date fecha = jDtFechaNacimiento.getDate();
            SimpleDateFormat oDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            cla.setFechaNacimiento(oDateFormat.format(fecha));
            cla.setTelefono(this.jFtfTelefono.getText().trim());
            cla.setCorreoElectronico(this.jTfCorreo.getText().trim());
            cla.setSexo(this.jCboSexo.getSelectedItem().toString());
            cla.setDireccion(this.jTxaDireccion.getText().trim());

            cda.insertar(cla);

            JOptionPane.showMessageDialog(null,
                    "Se ha registrado un abonado...",
                    "SAJA",
                    JOptionPane.INFORMATION_MESSAGE);
            limpiarFormulario();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al registrar el abonado: " + e);
        }
    }

    // Metodo para actualizar un abonado
    private void actualizarAbonado() {
        try {
            CDAbonado cda = new CDAbonado();
            CLAbonado cla = new CLAbonado();

            cla.setCodAbonado(codAbonado);
            cla.setCodAbonadoModificable(this.jFtfIdentidad.getText().trim());
            cla.setNombres(this.jTfNombres.getText().trim());
            cla.setApellidos(this.jTfApellidos.getText().trim());
            java.util.Date fecha = jDtFechaNacimiento.getDate();
            SimpleDateFormat oDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            cla.setFechaNacimiento(oDateFormat.format(fecha));
            cla.setTelefono(this.jFtfTelefono.getText().trim());
            cla.setCorreoElectronico(this.jTfCorreo.getText().trim());
            cla.setSexo(this.jCboSexo.getSelectedItem().toString());
            cla.setDireccion(this.jTxaDireccion.getText().trim());

            cda.actualizarAbonado(cla);

            JOptionPane.showMessageDialog(null,
                    "Se ha actualizado un abonado...",
                    "SAJA",
                    JOptionPane.INFORMATION_MESSAGE);
            limpiarFormulario();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar el abonado: " + e);
        }
    }

    // Metodo para validar si un correo es valido
    public boolean isEmail(String correo) {
        Pattern pat = null;
        Matcher mat = null;
        pat = Pattern.compile("^[\\w\\-\\_\\+]+(\\.[\\w\\-\\_]+)*@([A-Za-z0-9-]+\\.)+[A-Za-z]{2,4}$");
        mat = pat.matcher(correo);
        if (mat.find()) {
            return true;
        } else {
            return false;
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
        jRadioButton1 = new javax.swing.JRadioButton();
        jPanel4 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLblTitulo2 = new javax.swing.JLabel();
        jBtnSideBar = new javax.swing.JLabel();
        jBtnSideBar1 = new javax.swing.JLabel();
        jSideBar1 = new javax.swing.JScrollPane();
        jSideBar = new javax.swing.JPanel();
        jSBContrato = new javax.swing.JPanel();
        jLblIdentificador8 = new javax.swing.JLabel();
        jLblIdentificador9 = new javax.swing.JLabel();
        jSBListadoContrato = new javax.swing.JPanel();
        jLblIdentificador10 = new javax.swing.JLabel();
        jLblIdentificador11 = new javax.swing.JLabel();
        jSBContrato4 = new javax.swing.JPanel();
        jLblIdentificador12 = new javax.swing.JLabel();
        jLblIdentificador13 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLblIdentificador14 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jSBListadoContrato1 = new javax.swing.JPanel();
        jLblIdentificador28 = new javax.swing.JLabel();
        jLblIdentificador29 = new javax.swing.JLabel();
        jTbdAbonados = new javax.swing.JTabbedPane();
        jPnlAbandos = new javax.swing.JPanel();
        jLblIdentificador = new javax.swing.JLabel();
        jTfNombres = new javax.swing.JTextField();
        jLblIdentificador1 = new javax.swing.JLabel();
        jTfApellidos = new javax.swing.JTextField();
        jLblIdentificador2 = new javax.swing.JLabel();
        jDtFechaNacimiento = new com.toedter.calendar.JDateChooser();
        jLblIdentificador3 = new javax.swing.JLabel();
        jCboSexo = new javax.swing.JComboBox<>();
        jLblIdentificador4 = new javax.swing.JLabel();
        jPnlSeparator = new javax.swing.JPanel();
        jLblIdentificador5 = new javax.swing.JLabel();
        jLblIdentificador6 = new javax.swing.JLabel();
        jTfCorreo = new javax.swing.JTextField();
        jBtnGuardar = new javax.swing.JButton();
        jLblIdentificador15 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTxaDireccion = new javax.swing.JTextArea();
        jBtnModificar = new javax.swing.JButton();
        jFtfIdentidad = new javax.swing.JFormattedTextField();
        jFtfTelefono = new javax.swing.JFormattedTextField();
        jLblCancelarEdicion = new javax.swing.JLabel();
        jPnlDetalleAbonado = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLblNombreAbonado = new javax.swing.JLabel();
        jLblIdentificador18 = new javax.swing.JLabel();
        jLblFechaNacimiento = new javax.swing.JLabel();
        jLblIdentificador20 = new javax.swing.JLabel();
        jLblTelefono = new javax.swing.JLabel();
        jLblIdentificador22 = new javax.swing.JLabel();
        jLblCorreo = new javax.swing.JLabel();
        jLblIdentificador24 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTxaDireccionAbonado = new javax.swing.JTextArea();
        jPanel1 = new javax.swing.JPanel();
        jLblIdentificador25 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTblContratos = new javax.swing.JTable();
        jLblVerContrato = new javax.swing.JLabel();
        jLblModificar1 = new javax.swing.JLabel();
        jPnlListadoAbonados = new javax.swing.JPanel();
        jPnlBuscar = new javax.swing.JPanel();
        jTfBuscar = new javax.swing.JTextField();
        jBtnBuscar = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTblAbonados = new javax.swing.JTable();
        jLblIdentificador7 = new javax.swing.JLabel();
        jRbNombre = new javax.swing.JRadioButton();
        jRbIdentidad = new javax.swing.JRadioButton();
        jLblVerAbonado = new javax.swing.JLabel();
        jLblModificar2 = new javax.swing.JLabel();

        jRadioButton1.setText("jRadioButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setResizable(false);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setMinimumSize(new java.awt.Dimension(800, 700));
        jPanel4.setPreferredSize(new java.awt.Dimension(800, 700));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(41, 128, 185));
        jPanel3.setPreferredSize(new java.awt.Dimension(800, 60));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLblTitulo2.setFont(new java.awt.Font("HelveticaNowDisplay ExtraBold", 0, 30)); // NOI18N
        jLblTitulo2.setForeground(new java.awt.Color(255, 255, 255));
        jLblTitulo2.setText("Gestión del abonado");
        jPanel3.add(jLblTitulo2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 10, -1, 50));

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

        jPanel4.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 900, 60));

        jSideBar1.setBackground(new java.awt.Color(52, 73, 94));
        jSideBar1.setBorder(null);

        jSideBar.setBackground(new java.awt.Color(52, 73, 94));
        jSideBar.setPreferredSize(new java.awt.Dimension(260, 600));
        jSideBar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jSBContrato.setBackground(new java.awt.Color(52, 152, 219));
        jSBContrato.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jSBContrato.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jSBContratoMouseClicked(evt);
            }
        });

        jLblIdentificador8.setBackground(new java.awt.Color(102, 102, 102));
        jLblIdentificador8.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 1, 20)); // NOI18N
        jLblIdentificador8.setForeground(new java.awt.Color(255, 255, 255));
        jLblIdentificador8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/abonados-blanco32.png"))); // NOI18N
        jLblIdentificador8.setToolTipText("Gestión de abonados");

        jLblIdentificador9.setBackground(new java.awt.Color(102, 102, 102));
        jLblIdentificador9.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 1, 20)); // NOI18N
        jLblIdentificador9.setForeground(new java.awt.Color(255, 255, 255));
        jLblIdentificador9.setText("Gestión abonados");

        javax.swing.GroupLayout jSBContratoLayout = new javax.swing.GroupLayout(jSBContrato);
        jSBContrato.setLayout(jSBContratoLayout);
        jSBContratoLayout.setHorizontalGroup(
            jSBContratoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jSBContratoLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLblIdentificador9, javax.swing.GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jLblIdentificador8, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jSBContratoLayout.setVerticalGroup(
            jSBContratoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jSBContratoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jSBContratoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLblIdentificador8, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
                    .addComponent(jLblIdentificador9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jSideBar.add(jSBContrato, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 225, 260, -1));

        jSBListadoContrato.setBackground(new java.awt.Color(52, 73, 94));
        jSBListadoContrato.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jSBListadoContrato.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jSBListadoContratoMouseClicked(evt);
            }
        });

        jLblIdentificador10.setBackground(new java.awt.Color(102, 102, 102));
        jLblIdentificador10.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 1, 20)); // NOI18N
        jLblIdentificador10.setForeground(new java.awt.Color(255, 255, 255));
        jLblIdentificador10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/verabonado-blanco32.png"))); // NOI18N
        jLblIdentificador10.setToolTipText("Listado de abonados");

        jLblIdentificador11.setBackground(new java.awt.Color(102, 102, 102));
        jLblIdentificador11.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 1, 20)); // NOI18N
        jLblIdentificador11.setForeground(new java.awt.Color(255, 255, 255));
        jLblIdentificador11.setText("Ver abonado");

        javax.swing.GroupLayout jSBListadoContratoLayout = new javax.swing.GroupLayout(jSBListadoContrato);
        jSBListadoContrato.setLayout(jSBListadoContratoLayout);
        jSBListadoContratoLayout.setHorizontalGroup(
            jSBListadoContratoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jSBListadoContratoLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLblIdentificador11, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLblIdentificador10)
                .addGap(10, 10, 10))
        );
        jSBListadoContratoLayout.setVerticalGroup(
            jSBListadoContratoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jSBListadoContratoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jSBListadoContratoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLblIdentificador10, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
                    .addComponent(jLblIdentificador11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jSideBar.add(jSBListadoContrato, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 299, 260, -1));

        jSBContrato4.setBackground(new java.awt.Color(52, 73, 94));
        jSBContrato4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jLblIdentificador12.setBackground(new java.awt.Color(102, 102, 102));
        jLblIdentificador12.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 1, 20)); // NOI18N
        jLblIdentificador12.setForeground(new java.awt.Color(255, 255, 255));
        jLblIdentificador12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/menu-blanco32.png"))); // NOI18N
        jLblIdentificador12.setToolTipText("Menú");
        jLblIdentificador12.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jLblIdentificador13.setBackground(new java.awt.Color(102, 102, 102));
        jLblIdentificador13.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 1, 20)); // NOI18N
        jLblIdentificador13.setForeground(new java.awt.Color(255, 255, 255));
        jLblIdentificador13.setText("Menú");

        javax.swing.GroupLayout jSBContrato4Layout = new javax.swing.GroupLayout(jSBContrato4);
        jSBContrato4.setLayout(jSBContrato4Layout);
        jSBContrato4Layout.setHorizontalGroup(
            jSBContrato4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jSBContrato4Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLblIdentificador13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLblIdentificador12)
                .addContainerGap())
        );
        jSBContrato4Layout.setVerticalGroup(
            jSBContrato4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jSBContrato4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jSBContrato4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLblIdentificador12, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
                    .addComponent(jLblIdentificador13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jSideBar.add(jSBContrato4, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 132, 260, -1));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setPreferredSize(new java.awt.Dimension(0, 1));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1, Short.MAX_VALUE)
        );

        jSideBar.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 113, 255, -1));

        jLblIdentificador14.setBackground(new java.awt.Color(102, 102, 102));
        jLblIdentificador14.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 0, 12)); // NOI18N
        jLblIdentificador14.setForeground(new java.awt.Color(255, 255, 255));
        jLblIdentificador14.setText("SAJA");
        jLblIdentificador14.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jSideBar.add(jLblIdentificador14, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 78, 248, -1));

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

        jSBListadoContrato1.setBackground(new java.awt.Color(52, 73, 94));
        jSBListadoContrato1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jSBListadoContrato1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jSBListadoContrato1MouseClicked(evt);
            }
        });

        jLblIdentificador28.setBackground(new java.awt.Color(102, 102, 102));
        jLblIdentificador28.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 1, 20)); // NOI18N
        jLblIdentificador28.setForeground(new java.awt.Color(255, 255, 255));
        jLblIdentificador28.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/tabla-blanco32.png"))); // NOI18N
        jLblIdentificador28.setToolTipText("Listado de abonados");

        jLblIdentificador29.setBackground(new java.awt.Color(102, 102, 102));
        jLblIdentificador29.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 1, 20)); // NOI18N
        jLblIdentificador29.setForeground(new java.awt.Color(255, 255, 255));
        jLblIdentificador29.setText("Lista de abonados");

        javax.swing.GroupLayout jSBListadoContrato1Layout = new javax.swing.GroupLayout(jSBListadoContrato1);
        jSBListadoContrato1.setLayout(jSBListadoContrato1Layout);
        jSBListadoContrato1Layout.setHorizontalGroup(
            jSBListadoContrato1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jSBListadoContrato1Layout.createSequentialGroup()
                .addContainerGap(10, Short.MAX_VALUE)
                .addComponent(jLblIdentificador29, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLblIdentificador28)
                .addGap(16, 16, 16))
        );
        jSBListadoContrato1Layout.setVerticalGroup(
            jSBListadoContrato1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jSBListadoContrato1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jSBListadoContrato1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLblIdentificador28, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
                    .addComponent(jLblIdentificador29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jSideBar.add(jSBListadoContrato1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 370, 260, -1));

        jSideBar1.setViewportView(jSideBar);

        jPanel4.add(jSideBar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(-210, 0, 270, 610));

        jTbdAbonados.setBackground(new java.awt.Color(255, 255, 255));
        jTbdAbonados.setTabPlacement(javax.swing.JTabbedPane.RIGHT);
        jTbdAbonados.setFont(new java.awt.Font("HelveticaNowDisplay Hairline", 1, 12)); // NOI18N
        jTbdAbonados.setPreferredSize(new java.awt.Dimension(800, 700));

        jPnlAbandos.setBackground(new java.awt.Color(255, 255, 255));
        jPnlAbandos.setPreferredSize(new java.awt.Dimension(800, 540));

        jLblIdentificador.setBackground(new java.awt.Color(255, 255, 255));
        jLblIdentificador.setFont(new java.awt.Font("HelveticaNowDisplay Medium", 0, 20)); // NOI18N
        jLblIdentificador.setForeground(new java.awt.Color(0, 0, 0));
        jLblIdentificador.setText("Número de identidad");

        jTfNombres.setBackground(null);
        jTfNombres.setFont(new java.awt.Font("HelveticaNowDisplay Regular", 0, 16)); // NOI18N
        jTfNombres.setForeground(new java.awt.Color(0, 0, 0));
        jTfNombres.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTfNombres.setSelectionColor(new java.awt.Color(0, 153, 153));

        jLblIdentificador1.setBackground(new java.awt.Color(255, 255, 255));
        jLblIdentificador1.setFont(new java.awt.Font("HelveticaNowDisplay Medium", 0, 20)); // NOI18N
        jLblIdentificador1.setForeground(new java.awt.Color(0, 0, 0));
        jLblIdentificador1.setText("Nombres");

        jTfApellidos.setBackground(null);
        jTfApellidos.setFont(new java.awt.Font("HelveticaNowDisplay Regular", 0, 16)); // NOI18N
        jTfApellidos.setForeground(new java.awt.Color(0, 0, 0));
        jTfApellidos.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTfApellidos.setSelectionColor(new java.awt.Color(0, 153, 153));

        jLblIdentificador2.setBackground(new java.awt.Color(255, 255, 255));
        jLblIdentificador2.setFont(new java.awt.Font("HelveticaNowDisplay Medium", 0, 20)); // NOI18N
        jLblIdentificador2.setForeground(new java.awt.Color(0, 0, 0));
        jLblIdentificador2.setText("Apellidos");

        jDtFechaNacimiento.setBackground(null);
        jDtFechaNacimiento.setForeground(new java.awt.Color(0, 0, 0));
        jDtFechaNacimiento.setToolTipText("Elija la fecha de nacimiento del abonado");
        jDtFechaNacimiento.setDateFormatString("dd-MM-yyyy");
        jDtFechaNacimiento.setFont(new java.awt.Font("HelveticaNowDisplay Regular", 0, 14)); // NOI18N

        jLblIdentificador3.setBackground(new java.awt.Color(255, 255, 255));
        jLblIdentificador3.setFont(new java.awt.Font("HelveticaNowDisplay Medium", 0, 20)); // NOI18N
        jLblIdentificador3.setForeground(new java.awt.Color(0, 0, 0));
        jLblIdentificador3.setText("Sexo");

        jCboSexo.setBackground(null);
        jCboSexo.setFont(new java.awt.Font("HelveticaNowDisplay Regular", 0, 16)); // NOI18N
        jCboSexo.setForeground(new java.awt.Color(0, 0, 0));
        jCboSexo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Femenino", "Masculino" }));
        jCboSexo.setBorder(null);

        jLblIdentificador4.setBackground(new java.awt.Color(255, 255, 255));
        jLblIdentificador4.setFont(new java.awt.Font("HelveticaNowDisplay Medium", 0, 20)); // NOI18N
        jLblIdentificador4.setForeground(new java.awt.Color(0, 0, 0));
        jLblIdentificador4.setText("Fecha de nacimiento");

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

        jLblIdentificador5.setBackground(new java.awt.Color(255, 255, 255));
        jLblIdentificador5.setFont(new java.awt.Font("HelveticaNowDisplay Medium", 0, 20)); // NOI18N
        jLblIdentificador5.setForeground(new java.awt.Color(0, 0, 0));
        jLblIdentificador5.setText("Número de telefono");

        jLblIdentificador6.setBackground(new java.awt.Color(255, 255, 255));
        jLblIdentificador6.setFont(new java.awt.Font("HelveticaNowDisplay Medium", 0, 20)); // NOI18N
        jLblIdentificador6.setForeground(new java.awt.Color(0, 0, 0));
        jLblIdentificador6.setText("Correo eletrónico");

        jTfCorreo.setBackground(null);
        jTfCorreo.setFont(new java.awt.Font("HelveticaNowDisplay Regular", 0, 16)); // NOI18N
        jTfCorreo.setForeground(new java.awt.Color(0, 0, 0));
        jTfCorreo.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTfCorreo.setSelectionColor(new java.awt.Color(0, 153, 153));

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

        jLblIdentificador15.setBackground(new java.awt.Color(255, 255, 255));
        jLblIdentificador15.setFont(new java.awt.Font("HelveticaNowDisplay Medium", 0, 20)); // NOI18N
        jLblIdentificador15.setForeground(new java.awt.Color(0, 0, 0));
        jLblIdentificador15.setText("Dirección");

        jTxaDireccion.setBackground(null);
        jTxaDireccion.setColumns(20);
        jTxaDireccion.setFont(new java.awt.Font("HelveticaNowDisplay Regular", 0, 14)); // NOI18N
        jTxaDireccion.setForeground(new java.awt.Color(0, 0, 0));
        jTxaDireccion.setRows(5);
        jTxaDireccion.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jScrollPane1.setViewportView(jTxaDireccion);

        jBtnModificar.setBackground(new java.awt.Color(9, 132, 227));
        jBtnModificar.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 0, 16)); // NOI18N
        jBtnModificar.setForeground(new java.awt.Color(255, 255, 255));
        jBtnModificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/editar-blanco32.png"))); // NOI18N
        jBtnModificar.setText("MODIFICAR");
        jBtnModificar.setBorder(null);
        jBtnModificar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jBtnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnModificarActionPerformed(evt);
            }
        });

        jFtfIdentidad.setBackground(null);
        jFtfIdentidad.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jFtfIdentidad.setColumns(15);
        jFtfIdentidad.setForeground(new java.awt.Color(0, 0, 0));
        try {
            jFtfIdentidad.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("####-####-#####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jFtfIdentidad.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N

        jFtfTelefono.setBackground(null);
        jFtfTelefono.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jFtfTelefono.setForeground(new java.awt.Color(0, 0, 0));
        try {
            jFtfTelefono.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jFtfTelefono.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N

        jLblCancelarEdicion.setBackground(new java.awt.Color(102, 102, 102));
        jLblCancelarEdicion.setFont(new java.awt.Font("HelveticaNowDisplay Medium", 0, 14)); // NOI18N
        jLblCancelarEdicion.setForeground(new java.awt.Color(41, 128, 185));
        jLblCancelarEdicion.setText("Cancelar edición");
        jLblCancelarEdicion.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLblCancelarEdicion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLblCancelarEdicionMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPnlAbandosLayout = new javax.swing.GroupLayout(jPnlAbandos);
        jPnlAbandos.setLayout(jPnlAbandosLayout);
        jPnlAbandosLayout.setHorizontalGroup(
            jPnlAbandosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPnlAbandosLayout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addGroup(jPnlAbandosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPnlAbandosLayout.createSequentialGroup()
                        .addGroup(jPnlAbandosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPnlAbandosLayout.createSequentialGroup()
                                .addGroup(jPnlAbandosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLblIdentificador1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLblIdentificador4, javax.swing.GroupLayout.DEFAULT_SIZE, 327, Short.MAX_VALUE)
                                    .addComponent(jDtFechaNacimiento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jTfNombres))
                                .addGap(40, 40, 40)
                                .addGroup(jPnlAbandosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPnlAbandosLayout.createSequentialGroup()
                                        .addComponent(jLblIdentificador3, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addComponent(jLblIdentificador2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jTfApellidos)
                                    .addComponent(jCboSexo, 0, 306, Short.MAX_VALUE)))
                            .addComponent(jPnlSeparator, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPnlAbandosLayout.createSequentialGroup()
                                .addGroup(jPnlAbandosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jScrollPane1)
                                    .addComponent(jLblIdentificador5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLblIdentificador15, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 330, Short.MAX_VALUE)
                                    .addComponent(jFtfTelefono))
                                .addGroup(jPnlAbandosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPnlAbandosLayout.createSequentialGroup()
                                        .addGap(41, 41, 41)
                                        .addGroup(jPnlAbandosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jTfCorreo)
                                            .addComponent(jLblIdentificador6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addGroup(jPnlAbandosLayout.createSequentialGroup()
                                                .addComponent(jBtnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                                                .addComponent(jBtnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGroup(jPnlAbandosLayout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLblCancelarEdicion)))))
                        .addGap(100, 100, 100))
                    .addGroup(jPnlAbandosLayout.createSequentialGroup()
                        .addGroup(jPnlAbandosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLblIdentificador, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jFtfIdentidad, javax.swing.GroupLayout.PREFERRED_SIZE, 327, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPnlAbandosLayout.setVerticalGroup(
            jPnlAbandosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPnlAbandosLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLblIdentificador, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jFtfIdentidad, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addGroup(jPnlAbandosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLblIdentificador2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPnlAbandosLayout.createSequentialGroup()
                        .addComponent(jLblIdentificador1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPnlAbandosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTfNombres, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTfApellidos, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(jPnlAbandosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLblIdentificador4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLblIdentificador3, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPnlAbandosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jCboSexo, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jDtFechaNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jPnlSeparator, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPnlAbandosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLblIdentificador5, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLblIdentificador6, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPnlAbandosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTfCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jFtfTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLblIdentificador15, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addGroup(jPnlAbandosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPnlAbandosLayout.createSequentialGroup()
                        .addComponent(jLblCancelarEdicion, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPnlAbandosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jBtnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jBtnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(36, Short.MAX_VALUE))
        );

        jTbdAbonados.addTab("Registrar abonados     ", new javax.swing.ImageIcon(getClass().getResource("/img/user-negro16.png")), jPnlAbandos); // NOI18N

        jPnlDetalleAbonado.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/woman-128.png"))); // NOI18N

        jLblNombreAbonado.setBackground(new java.awt.Color(255, 255, 255));
        jLblNombreAbonado.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 1, 22)); // NOI18N
        jLblNombreAbonado.setForeground(new java.awt.Color(0, 0, 0));
        jLblNombreAbonado.setText("NOMBRE DEL ABONADO AQUÍ");

        jLblIdentificador18.setBackground(new java.awt.Color(255, 255, 255));
        jLblIdentificador18.setFont(new java.awt.Font("HelveticaNowDisplay Medium", 0, 20)); // NOI18N
        jLblIdentificador18.setForeground(new java.awt.Color(0, 0, 0));
        jLblIdentificador18.setText("Fecha de nacimiento");

        jLblFechaNacimiento.setBackground(new java.awt.Color(255, 255, 255));
        jLblFechaNacimiento.setFont(new java.awt.Font("HelveticaNowDisplay Regular", 0, 18)); // NOI18N
        jLblFechaNacimiento.setForeground(new java.awt.Color(0, 0, 0));
        jLblFechaNacimiento.setText("Fecha de nacimiento");

        jLblIdentificador20.setBackground(new java.awt.Color(255, 255, 255));
        jLblIdentificador20.setFont(new java.awt.Font("HelveticaNowDisplay Medium", 0, 20)); // NOI18N
        jLblIdentificador20.setForeground(new java.awt.Color(0, 0, 0));
        jLblIdentificador20.setText("Número de teléfono");

        jLblTelefono.setBackground(new java.awt.Color(255, 255, 255));
        jLblTelefono.setFont(new java.awt.Font("HelveticaNowDisplay Regular", 0, 18)); // NOI18N
        jLblTelefono.setForeground(new java.awt.Color(0, 0, 0));
        jLblTelefono.setText("Número de teléfono");

        jLblIdentificador22.setBackground(new java.awt.Color(255, 255, 255));
        jLblIdentificador22.setFont(new java.awt.Font("HelveticaNowDisplay Medium", 0, 20)); // NOI18N
        jLblIdentificador22.setForeground(new java.awt.Color(0, 0, 0));
        jLblIdentificador22.setText("Correo electrónico");

        jLblCorreo.setBackground(new java.awt.Color(255, 255, 255));
        jLblCorreo.setFont(new java.awt.Font("HelveticaNowDisplay Regular", 0, 18)); // NOI18N
        jLblCorreo.setForeground(new java.awt.Color(0, 0, 0));
        jLblCorreo.setText("Correo electrónico");

        jLblIdentificador24.setBackground(new java.awt.Color(255, 255, 255));
        jLblIdentificador24.setFont(new java.awt.Font("HelveticaNowDisplay Medium", 0, 20)); // NOI18N
        jLblIdentificador24.setForeground(new java.awt.Color(0, 0, 0));
        jLblIdentificador24.setText("Dirección");

        jTxaDireccionAbonado.setEditable(false);
        jTxaDireccionAbonado.setBackground(new java.awt.Color(255, 255, 255));
        jTxaDireccionAbonado.setColumns(20);
        jTxaDireccionAbonado.setFont(new java.awt.Font("HelveticaNowDisplay Regular", 0, 14)); // NOI18N
        jTxaDireccionAbonado.setForeground(new java.awt.Color(0, 0, 0));
        jTxaDireccionAbonado.setRows(5);
        jScrollPane3.setViewportView(jTxaDireccionAbonado);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jLblIdentificador25.setBackground(new java.awt.Color(255, 255, 255));
        jLblIdentificador25.setFont(new java.awt.Font("HelveticaNowDisplay Medium", 0, 20)); // NOI18N
        jLblIdentificador25.setForeground(new java.awt.Color(0, 0, 0));
        jLblIdentificador25.setText("Contratos que posee el abonado");

        jTblContratos.setBackground(new java.awt.Color(255, 255, 255));
        jTblContratos.setFont(new java.awt.Font("HelveticaNowDisplay Regular", 0, 14)); // NOI18N
        jTblContratos.setForeground(new java.awt.Color(0, 0, 0));
        jTblContratos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "#", "Casa", "Bloque", "Tipo Contrato", "Estado"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTblContratos.getTableHeader().setReorderingAllowed(false);
        jScrollPane7.setViewportView(jTblContratos);

        jLblVerContrato.setBackground(new java.awt.Color(102, 102, 102));
        jLblVerContrato.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 0, 20)); // NOI18N
        jLblVerContrato.setForeground(new java.awt.Color(41, 128, 185));
        jLblVerContrato.setText("Ver contrato");
        jLblVerContrato.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jLblModificar1.setBackground(new java.awt.Color(102, 102, 102));
        jLblModificar1.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 0, 20)); // NOI18N
        jLblModificar1.setForeground(new java.awt.Color(41, 128, 185));
        jLblModificar1.setText("Modificar abonado");
        jLblModificar1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLblModificar1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLblModificar1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPnlDetalleAbonadoLayout = new javax.swing.GroupLayout(jPnlDetalleAbonado);
        jPnlDetalleAbonado.setLayout(jPnlDetalleAbonadoLayout);
        jPnlDetalleAbonadoLayout.setHorizontalGroup(
            jPnlDetalleAbonadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPnlDetalleAbonadoLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(jPnlDetalleAbonadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPnlDetalleAbonadoLayout.createSequentialGroup()
                        .addComponent(jLblModificar1)
                        .addGap(180, 180, 180)
                        .addComponent(jLabel2))
                    .addGroup(jPnlDetalleAbonadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLblNombreAbonado, javax.swing.GroupLayout.PREFERRED_SIZE, 772, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPnlDetalleAbonadoLayout.createSequentialGroup()
                            .addGroup(jPnlDetalleAbonadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 320, Short.MAX_VALUE)
                                .addComponent(jLblIdentificador24, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLblCorreo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLblIdentificador22, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLblTelefono, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLblIdentificador20, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLblFechaNacimiento, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLblIdentificador18, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGap(18, 18, 18)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addGroup(jPnlDetalleAbonadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                .addComponent(jLblIdentificador25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPnlDetalleAbonadoLayout.createSequentialGroup()
                                    .addGap(0, 0, Short.MAX_VALUE)
                                    .addComponent(jLblVerContrato))))))
                .addGap(41, 41, 41))
        );
        jPnlDetalleAbonadoLayout.setVerticalGroup(
            jPnlDetalleAbonadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPnlDetalleAbonadoLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPnlDetalleAbonadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addComponent(jLblModificar1))
                .addGap(18, 18, 18)
                .addComponent(jLblNombreAbonado, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPnlDetalleAbonadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPnlDetalleAbonadoLayout.createSequentialGroup()
                        .addComponent(jLblIdentificador18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLblFechaNacimiento)
                        .addGap(18, 18, 18)
                        .addComponent(jLblIdentificador20)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLblTelefono)
                        .addGap(18, 18, 18)
                        .addComponent(jLblIdentificador22)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLblCorreo)
                        .addGap(18, 18, 18)
                        .addComponent(jLblIdentificador24)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPnlDetalleAbonadoLayout.createSequentialGroup()
                        .addComponent(jLblIdentificador25)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLblVerContrato)
                        .addGap(7, 7, 7)
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTbdAbonados.addTab("tab3", jPnlDetalleAbonado);

        jPnlListadoAbonados.setBackground(new java.awt.Color(255, 255, 255));
        jPnlListadoAbonados.setPreferredSize(new java.awt.Dimension(800, 540));

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
                .addComponent(jTfBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jBtnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPnlBuscarLayout.setVerticalGroup(
            jPnlBuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTfBuscar)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPnlBuscarLayout.createSequentialGroup()
                .addComponent(jBtnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jTblAbonados.setBackground(new java.awt.Color(255, 255, 255));
        jTblAbonados.setFont(new java.awt.Font("HelveticaNowDisplay Regular", 0, 14)); // NOI18N
        jTblAbonados.setForeground(new java.awt.Color(0, 0, 0));
        jTblAbonados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "RTN", "Nombres", "Apellidos", "FechaNacimiento", "Teléfono", "Correo", "Sexo", "Dirección", "NombreCompleto"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTblAbonados.setGridColor(new java.awt.Color(0, 0, 0));
        jTblAbonados.setRowHeight(30);
        jTblAbonados.setRowMargin(2);
        jTblAbonados.getTableHeader().setReorderingAllowed(false);
        jTblAbonados.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTblAbonadosMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTblAbonados);

        jLblIdentificador7.setBackground(new java.awt.Color(255, 255, 255));
        jLblIdentificador7.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 1, 20)); // NOI18N
        jLblIdentificador7.setForeground(new java.awt.Color(0, 0, 0));
        jLblIdentificador7.setText("Ver abonados por:");

        jRbNombre.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(jRbNombre);
        jRbNombre.setFont(new java.awt.Font("HelveticaNowDisplay Light", 0, 14)); // NOI18N
        jRbNombre.setForeground(new java.awt.Color(0, 0, 0));
        jRbNombre.setSelected(true);
        jRbNombre.setText("A - Z");

        jRbIdentidad.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(jRbIdentidad);
        jRbIdentidad.setFont(new java.awt.Font("HelveticaNowDisplay Light", 0, 14)); // NOI18N
        jRbIdentidad.setForeground(new java.awt.Color(0, 0, 0));
        jRbIdentidad.setText("Identidad");

        jLblVerAbonado.setBackground(new java.awt.Color(255, 255, 255));
        jLblVerAbonado.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 1, 20)); // NOI18N
        jLblVerAbonado.setForeground(new java.awt.Color(41, 128, 185));
        jLblVerAbonado.setText("Ver abonado");
        jLblVerAbonado.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLblVerAbonado.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLblVerAbonadoMouseClicked(evt);
            }
        });

        jLblModificar2.setBackground(new java.awt.Color(255, 255, 255));
        jLblModificar2.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 1, 20)); // NOI18N
        jLblModificar2.setForeground(new java.awt.Color(41, 128, 185));
        jLblModificar2.setText("Modificar abonado");
        jLblModificar2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLblModificar2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLblModificar2MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPnlListadoAbonadosLayout = new javax.swing.GroupLayout(jPnlListadoAbonados);
        jPnlListadoAbonados.setLayout(jPnlListadoAbonadosLayout);
        jPnlListadoAbonadosLayout.setHorizontalGroup(
            jPnlListadoAbonadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPnlListadoAbonadosLayout.createSequentialGroup()
                .addGroup(jPnlListadoAbonadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPnlListadoAbonadosLayout.createSequentialGroup()
                        .addContainerGap(35, Short.MAX_VALUE)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 773, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPnlListadoAbonadosLayout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(jLblIdentificador7)
                        .addGap(18, 18, 18)
                        .addComponent(jRbNombre)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jRbIdentidad, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPnlListadoAbonadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPnlListadoAbonadosLayout.createSequentialGroup()
                                .addComponent(jLblVerAbonado)
                                .addGap(18, 18, 18)
                                .addComponent(jLblModificar2))
                            .addComponent(jPnlBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(40, 40, 40))
        );
        jPnlListadoAbonadosLayout.setVerticalGroup(
            jPnlListadoAbonadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPnlListadoAbonadosLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPnlListadoAbonadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPnlListadoAbonadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLblIdentificador7, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jRbNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jRbIdentidad, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPnlBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPnlListadoAbonadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLblModificar2, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLblVerAbonado, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 394, Short.MAX_VALUE)
                .addGap(34, 34, 34))
        );

        jTbdAbonados.addTab("Listado de abonados     ", new javax.swing.ImageIcon(getClass().getResource("/img/list-negro16.png")), jPnlListadoAbonados); // NOI18N

        jPanel4.add(jTbdAbonados, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 60, 1010, 550));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, 900, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 607, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBtnSideBarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBtnSideBarMouseClicked
        // TODO add your handling code here:
        this.sideBar.jTextAreaXRight(-210, 0, 5, 5, jSideBar1);
        this.jBtnSideBar.setVisible(false);
        this.jBtnSideBar1.setVisible(true);
    }//GEN-LAST:event_jBtnSideBarMouseClicked

    private void jBtnSideBar1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBtnSideBar1MouseClicked
        // TODO add your handling code here:
        this.sideBar.jTextAreaXLeft(0, -210, 5, 5, jSideBar1);
        this.jTbdAbonados.setSelectedIndex(0);
        this.jBtnSideBar1.setVisible(false);
        this.jBtnSideBar.setVisible(true);
    }//GEN-LAST:event_jBtnSideBar1MouseClicked

    private void jSBListadoContrato1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jSBListadoContrato1MouseClicked
        // TODO add your handling code here:
        this.sideBar.jTextAreaXLeft(0, -210, 5, 5, jSideBar1);
        this.jTbdAbonados.setSelectedIndex(2);
        this.jBtnSideBar1.setVisible(false);
        this.jBtnSideBar.setVisible(true);
        this.jSBListadoContrato1.setBackground(celeste);
        this.jSBListadoContrato.setBackground(azul);
        this.jSBContrato.setBackground(azul);
    }//GEN-LAST:event_jSBListadoContrato1MouseClicked

    private void jSBListadoContratoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jSBListadoContratoMouseClicked
        // TODO add your handling code here:
        this.sideBar.jTextAreaXLeft(0, -210, 5, 5, jSideBar1);
        this.jTbdAbonados.setSelectedIndex(1);
        this.jBtnSideBar1.setVisible(false);
        this.jBtnSideBar.setVisible(true);
        this.jSBListadoContrato1.setBackground(azul);
        this.jSBListadoContrato.setBackground(celeste);
        this.jSBContrato.setBackground(azul);
    }//GEN-LAST:event_jSBListadoContratoMouseClicked

    private void jSBContratoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jSBContratoMouseClicked
        // TODO add your handling code here:
        this.sideBar.jTextAreaXLeft(0, -210, 5, 5, jSideBar1);
        this.jTbdAbonados.setSelectedIndex(0);
        this.jBtnSideBar1.setVisible(false);
        this.jBtnSideBar.setVisible(true);
        this.jSBListadoContrato1.setBackground(azul);
        this.jSBListadoContrato.setBackground(azul);
        this.jSBContrato.setBackground(celeste);
    }//GEN-LAST:event_jSBContratoMouseClicked

    private void jBtnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnGuardarActionPerformed
        try {
            if (validarTextField()) {
                limpiarTabla();
                insertarAbonado();
                llenarTablaAbonados();
            } else {
                JOptionPane.showMessageDialog(null,
                        "No debe haber ningun campo vacio.",
                        "SAJA",
                        JOptionPane.INFORMATION_MESSAGE);
            }

        } catch (SQLException ex) {
            Logger.getLogger(JFraAbonados.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jBtnGuardarActionPerformed

    private void jBtnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnModificarActionPerformed
        try {
            if (validarTextField()) {
                this.jLblCancelarEdicion.setVisible(false);
                limpiarTabla();
                actualizarAbonado();
                llenarTablaAbonados();
                habilitarBotones(true, false);
                modificar1 = false;
                modificar2 = false;
                verAbonado = false;
                this.jLblModificar1.setEnabled(false);
                this.jLblModificar2.setEnabled(false);
                this.jLblVerAbonado.setEnabled(false);
                this.jTxaDireccionAbonado.setText("");
            } else {
                JOptionPane.showMessageDialog(null,
                        "No debe haber ningun campo vacio.",
                        "SAJA",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException ex) {
            Logger.getLogger(JFraAbonados.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jBtnModificarActionPerformed

    private void jTblAbonadosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTblAbonadosMouseClicked
        try {
            modificar1 = true; 
            modificar2 = true; 
            verAbonado = true;
            this.jLblModificar1.setEnabled(true);
            this.jLblModificar2.setEnabled(true);
            this.jLblVerAbonado.setEnabled(true);
            limpiarTablaContratos();
            seleccionarContratoAbonado();
            llenarTablaContratoAbonados();
            habilitarBotones(false, true);
        } catch (SQLException | ParseException ex) {
            Logger.getLogger(JFraAbonados.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jTblAbonadosMouseClicked

    private void jLblModificar1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLblModificar1MouseClicked
        try {
            if(modificar1 == true){
                this.jLblCancelarEdicion.setVisible(true);
                seleccionarAbonado();
                llenarTablaAbonados();
                habilitarBotones(false, true);
                this.sideBar.jTextAreaXLeft(0, -210, 5, 5, jSideBar1);
                this.jTbdAbonados.setSelectedIndex(0);
                this.jBtnSideBar1.setVisible(false);
                this.jBtnSideBar.setVisible(true);
                this.jSBListadoContrato1.setBackground(azul);
                this.jSBListadoContrato.setBackground(azul);
                this.jSBContrato.setBackground(celeste);
            }
        } catch (ParseException | SQLException ex) {
            Logger.getLogger(JFraAbonados.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jLblModificar1MouseClicked

    private void jLblModificar2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLblModificar2MouseClicked
        try {
            if(modificar1 == true){
                this.jLblCancelarEdicion.setVisible(true);
                seleccionarAbonado();
                habilitarBotones(false, true);
                textoOriginal();
                this.sideBar.jTextAreaXLeft(0, -210, 5, 5, jSideBar1);
                this.jTbdAbonados.setSelectedIndex(0);
                this.jBtnSideBar1.setVisible(false);
                this.jBtnSideBar.setVisible(true);
                this.jSBListadoContrato1.setBackground(azul);
                this.jSBListadoContrato.setBackground(azul);
                this.jSBContrato.setBackground(celeste);
            }
        } catch (ParseException ex) {
            Logger.getLogger(JFraAbonados.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jLblModificar2MouseClicked

    private void jLblVerAbonadoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLblVerAbonadoMouseClicked
        try {
            if (this.verAbonado == true){
                seleccionarContratoAbonado();
                this.sideBar.jTextAreaXLeft(0, -210, 5, 5, jSideBar1);
                this.jTbdAbonados.setSelectedIndex(1);
                this.jBtnSideBar1.setVisible(false);
                this.jBtnSideBar.setVisible(true);
                this.jSBListadoContrato1.setBackground(azul);
                this.jSBListadoContrato.setBackground(celeste);
                this.jSBContrato.setBackground(azul);
            }
        } catch (ParseException ex) {
            Logger.getLogger(JFraAbonados.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jLblVerAbonadoMouseClicked

    private void jTfBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTfBuscarKeyReleased
        try {
            if (this.jRbNombre.isSelected()) {
                String nombre;
                nombre = this.jTfBuscar.getText();
                mostrarAbonadoPorNombre(nombre);
            } else if (this.jRbIdentidad.isSelected()) {
                String identidad;
                identidad = this.jTfBuscar.getText();
                mostrarAbonadoPorCod(identidad);
            }

        } catch (SQLException ex) {
            Logger.getLogger(JFraAbonados.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jTfBuscarKeyReleased

    private void jLblCancelarEdicionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLblCancelarEdicionMouseClicked
        try {
            limpiarTablaContratos();
            limpiarFormulario();
            modificar1 = false; 
            modificar2 = false; 
            verAbonado = false;
            this.jLblModificar1.setEnabled(false);
            this.jLblModificar2.setEnabled(false);
            this.jLblVerAbonado.setEnabled(false);
            this.jLblCancelarEdicion.setVisible(false);
            textoOriginal();
        } catch (SQLException ex) {
            Logger.getLogger(JFraAbonados.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jLblCancelarEdicionMouseClicked

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
            java.util.logging.Logger.getLogger(JFraAbonados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFraAbonados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFraAbonados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFraAbonados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new JFraAbonados().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(JFraAbonados.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jBtnBuscar;
    private javax.swing.JButton jBtnGuardar;
    private javax.swing.JButton jBtnModificar;
    private javax.swing.JLabel jBtnSideBar;
    private javax.swing.JLabel jBtnSideBar1;
    private javax.swing.JComboBox<String> jCboSexo;
    private com.toedter.calendar.JDateChooser jDtFechaNacimiento;
    private javax.swing.JFormattedTextField jFtfIdentidad;
    private javax.swing.JFormattedTextField jFtfTelefono;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLblCancelarEdicion;
    private javax.swing.JLabel jLblCorreo;
    private javax.swing.JLabel jLblFechaNacimiento;
    private javax.swing.JLabel jLblIdentificador;
    private javax.swing.JLabel jLblIdentificador1;
    private javax.swing.JLabel jLblIdentificador10;
    private javax.swing.JLabel jLblIdentificador11;
    private javax.swing.JLabel jLblIdentificador12;
    private javax.swing.JLabel jLblIdentificador13;
    private javax.swing.JLabel jLblIdentificador14;
    private javax.swing.JLabel jLblIdentificador15;
    private javax.swing.JLabel jLblIdentificador18;
    private javax.swing.JLabel jLblIdentificador2;
    private javax.swing.JLabel jLblIdentificador20;
    private javax.swing.JLabel jLblIdentificador22;
    private javax.swing.JLabel jLblIdentificador24;
    private javax.swing.JLabel jLblIdentificador25;
    private javax.swing.JLabel jLblIdentificador28;
    private javax.swing.JLabel jLblIdentificador29;
    private javax.swing.JLabel jLblIdentificador3;
    private javax.swing.JLabel jLblIdentificador4;
    private javax.swing.JLabel jLblIdentificador5;
    private javax.swing.JLabel jLblIdentificador6;
    private javax.swing.JLabel jLblIdentificador7;
    private javax.swing.JLabel jLblIdentificador8;
    private javax.swing.JLabel jLblIdentificador9;
    private javax.swing.JLabel jLblModificar1;
    private javax.swing.JLabel jLblModificar2;
    private javax.swing.JLabel jLblNombreAbonado;
    private javax.swing.JLabel jLblTelefono;
    private javax.swing.JLabel jLblTitulo2;
    private javax.swing.JLabel jLblVerAbonado;
    private javax.swing.JLabel jLblVerContrato;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPnlAbandos;
    private javax.swing.JPanel jPnlBuscar;
    private javax.swing.JPanel jPnlDetalleAbonado;
    private javax.swing.JPanel jPnlListadoAbonados;
    private javax.swing.JPanel jPnlSeparator;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRbIdentidad;
    private javax.swing.JRadioButton jRbNombre;
    private javax.swing.JPanel jSBContrato;
    private javax.swing.JPanel jSBContrato4;
    private javax.swing.JPanel jSBListadoContrato;
    private javax.swing.JPanel jSBListadoContrato1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JPanel jSideBar;
    private javax.swing.JScrollPane jSideBar1;
    private javax.swing.JTabbedPane jTbdAbonados;
    private javax.swing.JTable jTblAbonados;
    private javax.swing.JTable jTblContratos;
    private javax.swing.JTextField jTfApellidos;
    private javax.swing.JTextField jTfBuscar;
    private javax.swing.JTextField jTfCorreo;
    private javax.swing.JTextField jTfNombres;
    private javax.swing.JTextArea jTxaDireccion;
    private javax.swing.JTextArea jTxaDireccionAbonado;
    // End of variables declaration//GEN-END:variables
}
