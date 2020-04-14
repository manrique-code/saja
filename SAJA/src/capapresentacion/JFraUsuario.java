/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capapresentacion;

import AppPackage.AnimationClass;
import capadatos.CDUsuario;
import capalogica.CLUsuario;
import java.awt.Color;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.awt.Frame;


/**
 *
 * @author Manrique
 */
public class JFraUsuario extends javax.swing.JFrame {

    /**
     * Creates new form JFraUsuario
     */
    public JFraUsuario() throws SQLException {
        initComponents();
        this.getContentPane().setBackground(Color.white);
        this.setLocationRelativeTo(null);
        this.jBtnSideBar1.setVisible(false);
        this.jBtnModificarUsuario.setForeground(azul);
        llenarTablaUsuarios();
        cargarComboTipoUsuario();
        this.jLblIdUsuario.setVisible(false);
        this.jBtnEditar.setVisible(false);
        this.jBtnGuardar.setVisible(true);
       
        
    }
    
    AnimationClass sideBar = new AnimationClass();
    
    Color celeste = new Color(52,152,219);
    Color azul = new Color(52,73,94);
    
    JFraMenu jfm = new JFraMenu(); 
    JFraConfiguraciones jfc = new JFraConfiguraciones();
    //METODO PARA DAR PERMISO DE MODIFICACION
    
    private boolean permisoUsuario() throws SQLException{
        CDUsuario cdu = new CDUsuario();
        CLUsuario clu = new CLUsuario();
        clu.setAdministrador(this.jTfUsuarioAdmin.getText());
        clu.setContraseñaAdmin(this.jPassUsuarioAdmin.getText());
        if(cdu.verificarUsuario(clu) == "acceso"){
            return true;
        }else{
            JOptionPane.showMessageDialog(null,
                                          "Ingrese un usuario y contraseña validos",
                                          "SAJA",
                                          JOptionPane.INFORMATION_MESSAGE);
            return false;
            
        }
    }
    
    //ESTE METODO VALIDA TODAS LAS ENTRADAS ANTES DE REALIZAR UNA ACCION
    private boolean validarEntradas(){
        boolean estado;
        if(this.jTfNombre.getText() ==""
        ||this.jTfApellidos.getText()==""
        ||this.jTADireccion.getText()==""
        ||this.jTfNumeroTelefono.getText()==""
        ||this.jTfNumIdentidad.getText()==""
        ||this.jCboTipoUsuario.getSelectedItem() == "--Seleccione--"
        ||this.jTfNombreUsuario.getText()==""
        ||this.jPfContra.getText() ==""){
            estado = false;
        }else{
            estado = true;
        }
        return estado;
        
    }
    //ESTE METODO LIMPIA TODAS LAS ENTRADAS EN GENERAL PARA REGISTRAR O MODIFICAR UN USUARIO
    private void limpiarEntradas(){
        this.jLblIdUsuario.setText("");
        this.jTfNombre.setText("");
        this.jTfApellidos.setText("");
        this.jTADireccion.setText("");
        this.jTfNumeroTelefono.setText("");
        this.jTfNumIdentidad.setText("");
        this.jCboTipoUsuario.setSelectedIndex(0);
        this.jTfNombreUsuario.setText("");
        this.jChkUsuarioActivo.setSelected(false);
        
    }
    //ESTE METODO LLIMIA LA TABLA USUARIO
    private void limpiarTablaUsuarios(){
        DefaultTableModel dtm = (DefaultTableModel) this.jTblUsuarios.getModel();
        while (dtm.getRowCount() > 0) {
            dtm.removeRow(0);
        }
    }
    //ESTE METODO CARGA LA TABLA DE USUARIOS
    private void llenarTablaUsuarios() throws SQLException  {
        
        limpiarTablaUsuarios();
        CDUsuario cda = new CDUsuario();
        List<CLUsuario> miLista;
        miLista = cda.ListaUsuarios();
        DefaultTableModel temp = (DefaultTableModel) this.jTblUsuarios.getModel();

        miLista.stream().map((cla) -> {
            Object[] fila = new Object[9];
            fila[0] = cla.getIdUsuario();
            fila[1] = cla.getCodEmpleado();
            fila[2] = cla.getNombres();            
            fila[3] = cla.getApellidos();
            fila[4] = cla.getNombreUsuario();
            fila[5] = cla.getEstadoUsuario();
            fila[6] = cla.getTipoUsuario();
            fila[7] = cla.getDireccion();
            fila[8] = cla.getTelefono();


            return fila;
        }).forEachOrdered((fila) -> {
            temp.addRow(fila);
        });
    }
    //METODO PARA CARGAR LA TABLA CON CODEMPLEADO
    private void llenarTablaUsuariosPorCodEmpleado(String codEmpleado) throws SQLException  {
        
        limpiarTablaUsuarios();
        CDUsuario cda = new CDUsuario();
        List<CLUsuario> miLista;
        miLista = cda.ListaUsuariosPorCodEmpleado(codEmpleado);
        DefaultTableModel temp = (DefaultTableModel) this.jTblUsuarios.getModel();

        miLista.stream().map((cla) -> {
            Object[] fila = new Object[9];
            fila[0] = cla.getIdUsuario();
            fila[1] = cla.getCodEmpleado();
            fila[2] = cla.getNombres();            
            fila[3] = cla.getApellidos();
            fila[4] = cla.getNombreUsuario();
            fila[5] = cla.getEstadoUsuario();
            fila[6] = cla.getTipoUsuario();
            fila[7] = cla.getDireccion();
            fila[8] = cla.getTelefono();

            return fila;
        }).forEachOrdered((fila) -> {
            temp.addRow(fila);
        });
    }
    //ESTE METODO SE ENCARGAR DE INSERTAR UN EMPLEADO
    private void insertarEmpleado() throws SQLException{
        try{ 
            
            CDUsuario cda = new CDUsuario();
            CLUsuario cla = new CLUsuario();
            
            
            
            cla.setCodEmpleado(this.jTfNumIdentidad.getText());
            cla.setNombres(this.jTfNombre.getText());
            cla.setApellidos(this.jTfApellidos.getText());
            cla.setDireccion(this.jTADireccion.getText());
            cla.setTelefono(this.jTfNumeroTelefono.getText());
            
            cda.insertarEmpleado(cla);
            
            JOptionPane.showMessageDialog(null, "El registro se ha creado con exito...", "SAJA", 
                    JOptionPane.INFORMATION_MESSAGE);
            //clearTextField();
            
            llenarTablaUsuarios();
            
        } catch(SQLException ex){
            
        }
    }
    //ESTE METODO SE ENCARGA DE INSERTAR UN USUARIO AL EMPLEADO QUE SE ESTA CREANDO
    private void insertarUsuario() throws SQLException{
        try{ 
            
            CDUsuario cda = new CDUsuario();
            CLUsuario cla = new CLUsuario();
            //CLLogin login = new CLLogin();
            
            
            cla.setCodEmpleado(this.jTfNumIdentidad.getText());
            cla.setNombreUsuario(this.jTfNombreUsuario.getText());
            cla.setContraseña(String.valueOf(this.jPfContra.getText()));
            if(this.jChkUsuarioActivo.isSelected()){
                cla.setEstadoUsuario("Activo");
            }else{
                cla.setEstadoUsuario("Inactivo");
            }
            cla.setTipoUsuario(this.jCboTipoUsuario.getSelectedItem().toString());
            //cla.setUsuario(login.getIdUsuario()); //HABILITAR AL PODER USAR EL MENU
            cda.insertarUsuario(cla);
            
            JOptionPane.showMessageDialog(null, "Se ha creado un nuevo Usuario...", "SAJA", 
                    JOptionPane.INFORMATION_MESSAGE);
            //clearTextField();
            
            llenarTablaUsuarios();
            
        } catch(SQLException ex){
            
        }
    }
    //ESTE METODO SE ENCARGA DE CARGAR LOS DATOS DEL COMBOBOX TIPOUSUARIO
    private void cargarComboTipoUsuario() throws SQLException {

        CDUsuario cda = new CDUsuario();

        String[] Tipo = new String[cda.cargarTipoUsuario().size()];
        Tipo = cda.cargarTipoUsuario().toArray(Tipo);

        DefaultComboBoxModel modeloContrato = new DefaultComboBoxModel(Tipo);
        this.jCboTipoUsuario.setModel(modeloContrato);
    }
    //ESTE METODO TOMA TODOS LOS DATOS DE UN REGISTRO A EDITAR Y LOS CARGA EN CADA ENTRADA 
    //PREPARANDO CADA DATO PARA SER MODIFICADO
    private void cargarDatos(){
        if(this.jTblUsuarios.getSelectedRow()!= -1){
            this.jLblIdUsuario.setText(String.valueOf(this.jTblUsuarios.getValueAt(this.jTblUsuarios.getSelectedRow(), 0)));
            this.jTfNombre.setText(String.valueOf(this.jTblUsuarios.getValueAt(this.jTblUsuarios.getSelectedRow(), 2)));
            this.jTfApellidos.setText(String.valueOf(this.jTblUsuarios.getValueAt(this.jTblUsuarios.getSelectedRow(), 3)));
            this.jTADireccion.setText(String.valueOf(this.jTblUsuarios.getValueAt(this.jTblUsuarios.getSelectedRow(), 7)));
            this.jTfNumeroTelefono.setText(String.valueOf(this.jTblUsuarios.getValueAt(this.jTblUsuarios.getSelectedRow(), 8)));
            this.jTfNumIdentidad.setText((String.valueOf(this.jTblUsuarios.getValueAt(this.jTblUsuarios.getSelectedRow(), 1))));
            this.jCboTipoUsuario.setSelectedItem(String.valueOf(this.jTblUsuarios.getValueAt(this.jTblUsuarios.getSelectedRow(), 6)));
            this.jTfNombreUsuario.setText(String.valueOf(this.jTblUsuarios.getValueAt(this.jTblUsuarios.getSelectedRow(), 4)));
            String estado = String.valueOf(this.jTblUsuarios.getValueAt(this.jTblUsuarios.getSelectedRow(),5));
            if(String.valueOf(this.jTblUsuarios.getValueAt(this.jTblUsuarios.getSelectedRow(),5)) == "Activo"){
                this.jChkUsuarioActivo.setSelected(true);
            }else{
                this.jChkUsuarioActivo.setSelected(false);
            }
   
        }   
    }
    
    //ESTE METODO MODIFICA UN EMPLEADO 
    private void modificarEmpleado(){
        try{ 
            
            CDUsuario cda = new CDUsuario();
            CLUsuario cla = new CLUsuario();

            cla.setCodEmpleado(this.jTfNumIdentidad.getText());
            cla.setNombres(this.jTfNombre.getText());
            cla.setApellidos(this.jTfApellidos.getText());
            cla.setDireccion(this.jTADireccion.getText());
            cla.setTelefono(this.jTfNumeroTelefono.getText());
            cda.modificarEmpleado(cla);
            llenarTablaUsuarios();
            
        } catch(SQLException ex){
            
        }
    }
    
    //ESTE MEDOTO MODIFICA UN USUARIO
    private void modificarUsuario(){
        try{ 
            
            CDUsuario cda = new CDUsuario();
            CLUsuario cla = new CLUsuario();
            cla.setIdUsuario(Integer.parseInt(this.jLblIdUsuario.getText()));
            cla.setNombreUsuario(this.jTfNombreUsuario.getText());
            cla.setContraseña(String.valueOf(this.jPfContra.getText()));
            if(this.jChkUsuarioActivo.isSelected()){
                cla.setEstadoUsuario("Activo");
            }else{
                cla.setEstadoUsuario("Inactivo");
            }
            cla.setTipoUsuario(this.jCboTipoUsuario.getSelectedItem().toString());
            cda.modificarUsuario(cla);
            
            JOptionPane.showMessageDialog(null, "El registro se ha modifiacado con exito...", "SAJA", 
                    JOptionPane.INFORMATION_MESSAGE);
            
            llenarTablaUsuarios();
            
        } catch(SQLException ex){
            
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

        jDfMenu = new javax.swing.JDialog();
        jPanel8 = new javax.swing.JPanel();
        jLblIdentificador57 = new javax.swing.JLabel();
        jLblIdentificador58 = new javax.swing.JLabel();
        jLblIdentificador59 = new javax.swing.JLabel();
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
        jSBNuevoUsuario = new javax.swing.JPanel();
        jLblUsuarioNuevo = new javax.swing.JLabel();
        jLblIdentificador1 = new javax.swing.JLabel();
        jSBListaUsuarios = new javax.swing.JPanel();
        jLblIdentificador2 = new javax.swing.JLabel();
        jLblIdentificador3 = new javax.swing.JLabel();
        jSBMenu = new javax.swing.JPanel();
        jLblIdentificador9 = new javax.swing.JLabel();
        jLblIdentificador10 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLblIdentificador5 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jSBAyuda = new javax.swing.JPanel();
        jLblIdentificador4 = new javax.swing.JLabel();
        jLblIdentificador6 = new javax.swing.JLabel();
        jTabUsuarios = new javax.swing.JTabbedPane();
        jPnlUsuarios = new javax.swing.JPanel();
        jLblIdentificador43 = new javax.swing.JLabel();
        jLblIdentificador44 = new javax.swing.JLabel();
        jTfNombre = new javax.swing.JTextField();
        jLblIdentificador45 = new javax.swing.JLabel();
        jTfApellidos = new javax.swing.JTextField();
        jLblIdentificador46 = new javax.swing.JLabel();
        jTfNumeroTelefono = new javax.swing.JTextField();
        jLblIdentificador47 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTADireccion = new javax.swing.JTextArea();
        jBtnEditar = new javax.swing.JButton();
        jCboTipoUsuario = new javax.swing.JComboBox<>();
        jLblIdentificador48 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLblIdentificador49 = new javax.swing.JLabel();
        jTfUsuarioAdmin = new javax.swing.JTextField();
        jLblIdentificador50 = new javax.swing.JLabel();
        jPassUsuarioAdmin = new javax.swing.JPasswordField();
        jLblIdentificador51 = new javax.swing.JLabel();
        jLblIdentificador52 = new javax.swing.JLabel();
        jTfNombreUsuario = new javax.swing.JTextField();
        jLblIdentificador53 = new javax.swing.JLabel();
        jPfContra = new javax.swing.JPasswordField();
        jPanel7 = new javax.swing.JPanel();
        jLblIdentificador55 = new javax.swing.JLabel();
        jTfNumIdentidad = new javax.swing.JTextField();
        jChkUsuarioActivo = new javax.swing.JCheckBox();
        jBtnGuardar = new javax.swing.JButton();
        jLblIdUsuario = new javax.swing.JLabel();
        jBtnCancelar = new javax.swing.JButton();
        jPnlDesactivarUsuario = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTblUsuarios = new javax.swing.JTable();
        jPnlBuscar = new javax.swing.JPanel();
        jTfBuscar = new javax.swing.JTextField();
        jBtnBuscar = new javax.swing.JLabel();
        jBtnModificarUsuario = new javax.swing.JLabel();
        jLblIdentificador56 = new javax.swing.JLabel();
        jLblIdentificador61 = new javax.swing.JLabel();

        jDfMenu.setTitle("Confirmar acción");
        jDfMenu.setAlwaysOnTop(true);
        jDfMenu.setModal(true);
        jDfMenu.setModalExclusionType(java.awt.Dialog.ModalExclusionType.APPLICATION_EXCLUDE);
        jDfMenu.setUndecorated(true);
        jDfMenu.setSize(new java.awt.Dimension(300, 200));

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jLblIdentificador57.setBackground(new java.awt.Color(255, 255, 255));
        jLblIdentificador57.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 1, 20)); // NOI18N
        jLblIdentificador57.setForeground(new java.awt.Color(0, 0, 0));
        jLblIdentificador57.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/menu-negro24.png"))); // NOI18N
        jLblIdentificador57.setText("¿Regresar al menú?");
        jLblIdentificador57.setIconTextGap(10);

        jLblIdentificador58.setBackground(new java.awt.Color(255, 255, 255));
        jLblIdentificador58.setFont(new java.awt.Font("HelveticaNowDisplay Regular", 0, 16)); // NOI18N
        jLblIdentificador58.setForeground(new java.awt.Color(0, 0, 0));
        jLblIdentificador58.setText("Si no guardó el registro actual, su");

        jLblIdentificador59.setBackground(new java.awt.Color(255, 255, 255));
        jLblIdentificador59.setFont(new java.awt.Font("HelveticaNowDisplay Regular", 0, 16)); // NOI18N
        jLblIdentificador59.setForeground(new java.awt.Color(0, 0, 0));
        jLblIdentificador59.setText("progreso se perderá.");

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

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLblIdentificador59)
                    .addComponent(jLblIdentificador57)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel8Layout.createSequentialGroup()
                            .addComponent(jBtnSi, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(JBtnNo, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jLblIdentificador58)))
                .addContainerGap(58, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLblIdentificador57)
                .addGap(18, 18, 18)
                .addComponent(jLblIdentificador58)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLblIdentificador59)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBtnSi, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JBtnNo, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24))
        );

        javax.swing.GroupLayout jDfMenuLayout = new javax.swing.GroupLayout(jDfMenu.getContentPane());
        jDfMenu.getContentPane().setLayout(jDfMenuLayout);
        jDfMenuLayout.setHorizontalGroup(
            jDfMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jDfMenuLayout.setVerticalGroup(
            jDfMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
        jLblTitulo2.setText("Usuarios");
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

        jSBNuevoUsuario.setBackground(new java.awt.Color(52, 152, 219));
        jSBNuevoUsuario.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jSBNuevoUsuario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jSBNuevoUsuarioMouseClicked(evt);
            }
        });

        jLblUsuarioNuevo.setBackground(new java.awt.Color(102, 102, 102));
        jLblUsuarioNuevo.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 1, 20)); // NOI18N
        jLblUsuarioNuevo.setForeground(new java.awt.Color(255, 255, 255));
        jLblUsuarioNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/user-blanco32.png"))); // NOI18N
        jLblUsuarioNuevo.setToolTipText("Contratos");
        jLblUsuarioNuevo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLblUsuarioNuevoMouseClicked(evt);
            }
        });

        jLblIdentificador1.setBackground(new java.awt.Color(102, 102, 102));
        jLblIdentificador1.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 1, 20)); // NOI18N
        jLblIdentificador1.setForeground(new java.awt.Color(255, 255, 255));
        jLblIdentificador1.setText("Añadir usuario");

        javax.swing.GroupLayout jSBNuevoUsuarioLayout = new javax.swing.GroupLayout(jSBNuevoUsuario);
        jSBNuevoUsuario.setLayout(jSBNuevoUsuarioLayout);
        jSBNuevoUsuarioLayout.setHorizontalGroup(
            jSBNuevoUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jSBNuevoUsuarioLayout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addComponent(jLblIdentificador1, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLblUsuarioNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12))
        );
        jSBNuevoUsuarioLayout.setVerticalGroup(
            jSBNuevoUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jSBNuevoUsuarioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jSBNuevoUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLblUsuarioNuevo, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
                    .addComponent(jLblIdentificador1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jSideBar.add(jSBNuevoUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 225, 260, -1));

        jSBListaUsuarios.setBackground(new java.awt.Color(52, 73, 94));
        jSBListaUsuarios.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jSBListaUsuarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jSBListaUsuariosMouseClicked(evt);
            }
        });

        jLblIdentificador2.setBackground(new java.awt.Color(102, 102, 102));
        jLblIdentificador2.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 1, 20)); // NOI18N
        jLblIdentificador2.setForeground(new java.awt.Color(255, 255, 255));
        jLblIdentificador2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/desactivarusuario-blanco32.png"))); // NOI18N
        jLblIdentificador2.setToolTipText("Listado de contratos");
        jLblIdentificador2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLblIdentificador2MouseClicked(evt);
            }
        });

        jLblIdentificador3.setBackground(new java.awt.Color(102, 102, 102));
        jLblIdentificador3.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 1, 20)); // NOI18N
        jLblIdentificador3.setForeground(new java.awt.Color(255, 255, 255));
        jLblIdentificador3.setText("Administrar Usuarios");

        javax.swing.GroupLayout jSBListaUsuariosLayout = new javax.swing.GroupLayout(jSBListaUsuarios);
        jSBListaUsuarios.setLayout(jSBListaUsuariosLayout);
        jSBListaUsuariosLayout.setHorizontalGroup(
            jSBListaUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jSBListaUsuariosLayout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addComponent(jLblIdentificador3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLblIdentificador2)
                .addContainerGap())
        );
        jSBListaUsuariosLayout.setVerticalGroup(
            jSBListaUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLblIdentificador2, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
            .addComponent(jLblIdentificador3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jSideBar.add(jSBListaUsuarios, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 300, 260, -1));

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
                .addComponent(jLblIdentificador10, javax.swing.GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLblIdentificador9)
                .addGap(14, 14, 14))
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

        jTabUsuarios.setBackground(new java.awt.Color(255, 255, 255));
        jTabUsuarios.setTabPlacement(javax.swing.JTabbedPane.RIGHT);

        jPnlUsuarios.setBackground(new java.awt.Color(255, 255, 255));
        jPnlUsuarios.setLayout(null);

        jLblIdentificador43.setBackground(new java.awt.Color(255, 255, 255));
        jLblIdentificador43.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 1, 20)); // NOI18N
        jLblIdentificador43.setForeground(new java.awt.Color(0, 0, 0));
        jLblIdentificador43.setText("Añadir usuario");
        jPnlUsuarios.add(jLblIdentificador43);
        jLblIdentificador43.setBounds(30, 20, 580, 24);

        jLblIdentificador44.setBackground(new java.awt.Color(255, 255, 255));
        jLblIdentificador44.setFont(new java.awt.Font("HelveticaNowDisplay Medium", 0, 20)); // NOI18N
        jLblIdentificador44.setForeground(new java.awt.Color(0, 0, 0));
        jLblIdentificador44.setText("Nombres");
        jPnlUsuarios.add(jLblIdentificador44);
        jLblIdentificador44.setBounds(30, 70, 272, 24);

        jTfNombre.setBackground(new java.awt.Color(255, 255, 255));
        jTfNombre.setFont(new java.awt.Font("HelveticaNowDisplay Regular", 0, 16)); // NOI18N
        jTfNombre.setForeground(new java.awt.Color(0, 0, 0));
        jTfNombre.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTfNombre.setSelectionColor(new java.awt.Color(0, 153, 153));
        jPnlUsuarios.add(jTfNombre);
        jTfNombre.setBounds(30, 100, 272, 35);

        jLblIdentificador45.setBackground(new java.awt.Color(255, 255, 255));
        jLblIdentificador45.setFont(new java.awt.Font("HelveticaNowDisplay Medium", 0, 20)); // NOI18N
        jLblIdentificador45.setForeground(new java.awt.Color(0, 0, 0));
        jLblIdentificador45.setText("Número de identidad");
        jPnlUsuarios.add(jLblIdentificador45);
        jLblIdentificador45.setBounds(370, 70, 272, 24);

        jTfApellidos.setBackground(new java.awt.Color(255, 255, 255));
        jTfApellidos.setFont(new java.awt.Font("HelveticaNowDisplay Regular", 0, 16)); // NOI18N
        jTfApellidos.setForeground(new java.awt.Color(0, 0, 0));
        jTfApellidos.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTfApellidos.setSelectionColor(new java.awt.Color(0, 153, 153));
        jPnlUsuarios.add(jTfApellidos);
        jTfApellidos.setBounds(30, 180, 272, 35);

        jLblIdentificador46.setBackground(new java.awt.Color(255, 255, 255));
        jLblIdentificador46.setFont(new java.awt.Font("HelveticaNowDisplay Medium", 0, 20)); // NOI18N
        jLblIdentificador46.setForeground(new java.awt.Color(0, 0, 0));
        jLblIdentificador46.setText("Número de teléfono");
        jPnlUsuarios.add(jLblIdentificador46);
        jLblIdentificador46.setBounds(30, 390, 272, 24);

        jTfNumeroTelefono.setBackground(new java.awt.Color(255, 255, 255));
        jTfNumeroTelefono.setFont(new java.awt.Font("HelveticaNowDisplay Regular", 0, 16)); // NOI18N
        jTfNumeroTelefono.setForeground(new java.awt.Color(0, 0, 0));
        jTfNumeroTelefono.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTfNumeroTelefono.setSelectionColor(new java.awt.Color(0, 153, 153));
        jPnlUsuarios.add(jTfNumeroTelefono);
        jTfNumeroTelefono.setBounds(30, 420, 272, 35);

        jLblIdentificador47.setBackground(new java.awt.Color(255, 255, 255));
        jLblIdentificador47.setFont(new java.awt.Font("HelveticaNowDisplay Medium", 0, 20)); // NOI18N
        jLblIdentificador47.setForeground(new java.awt.Color(0, 0, 0));
        jLblIdentificador47.setText("Dirección");
        jPnlUsuarios.add(jLblIdentificador47);
        jLblIdentificador47.setBounds(30, 240, 272, 24);

        jTADireccion.setBackground(new java.awt.Color(255, 255, 255));
        jTADireccion.setColumns(20);
        jTADireccion.setFont(new java.awt.Font("HelveticaNowDisplay Regular", 0, 14)); // NOI18N
        jTADireccion.setForeground(new java.awt.Color(0, 0, 0));
        jTADireccion.setRows(5);
        jScrollPane1.setViewportView(jTADireccion);

        jPnlUsuarios.add(jScrollPane1);
        jScrollPane1.setBounds(30, 270, 272, 90);

        jBtnEditar.setBackground(new java.awt.Color(41, 128, 185));
        jBtnEditar.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 0, 16)); // NOI18N
        jBtnEditar.setForeground(new java.awt.Color(255, 255, 255));
        jBtnEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/guardar-white24.png"))); // NOI18N
        jBtnEditar.setText("MODIFICAR USUARIO");
        jBtnEditar.setBorder(null);
        jBtnEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jBtnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnEditarActionPerformed(evt);
            }
        });
        jPnlUsuarios.add(jBtnEditar);
        jBtnEditar.setBounds(30, 480, 272, 50);

        jCboTipoUsuario.setBackground(new java.awt.Color(255, 255, 255));
        jCboTipoUsuario.setFont(new java.awt.Font("HelveticaNowDisplay Regular", 0, 16)); // NOI18N
        jCboTipoUsuario.setForeground(new java.awt.Color(0, 0, 0));
        jPnlUsuarios.add(jCboTipoUsuario);
        jCboTipoUsuario.setBounds(370, 210, 272, 35);

        jLblIdentificador48.setBackground(new java.awt.Color(255, 255, 255));
        jLblIdentificador48.setFont(new java.awt.Font("HelveticaNowDisplay Medium", 0, 20)); // NOI18N
        jLblIdentificador48.setForeground(new java.awt.Color(0, 0, 0));
        jLblIdentificador48.setText("Tipo de usuario");
        jPnlUsuarios.add(jLblIdentificador48);
        jLblIdentificador48.setBounds(370, 180, 272, 24);

        jPanel6.setBackground(new java.awt.Color(102, 102, 102));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 461, Short.MAX_VALUE)
        );

        jPnlUsuarios.add(jPanel6);
        jPanel6.setBounds(684, 72, 1, 461);

        jLblIdentificador49.setBackground(new java.awt.Color(255, 255, 255));
        jLblIdentificador49.setFont(new java.awt.Font("HelveticaNowDisplay Medium", 0, 20)); // NOI18N
        jLblIdentificador49.setForeground(new java.awt.Color(0, 0, 0));
        jLblIdentificador49.setText("Usuario");
        jPnlUsuarios.add(jLblIdentificador49);
        jLblIdentificador49.setBounds(703, 114, 207, 24);

        jTfUsuarioAdmin.setBackground(new java.awt.Color(255, 255, 255));
        jTfUsuarioAdmin.setFont(new java.awt.Font("HelveticaNowDisplay Regular", 0, 16)); // NOI18N
        jTfUsuarioAdmin.setForeground(new java.awt.Color(0, 0, 0));
        jTfUsuarioAdmin.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTfUsuarioAdmin.setSelectionColor(new java.awt.Color(0, 153, 153));
        jPnlUsuarios.add(jTfUsuarioAdmin);
        jTfUsuarioAdmin.setBounds(703, 144, 207, 35);

        jLblIdentificador50.setBackground(new java.awt.Color(255, 255, 255));
        jLblIdentificador50.setFont(new java.awt.Font("HelveticaNowDisplay Medium", 0, 20)); // NOI18N
        jLblIdentificador50.setForeground(new java.awt.Color(0, 0, 0));
        jLblIdentificador50.setText("Contraseña");
        jPnlUsuarios.add(jLblIdentificador50);
        jLblIdentificador50.setBounds(703, 197, 207, 24);

        jPassUsuarioAdmin.setBackground(new java.awt.Color(255, 255, 255));
        jPassUsuarioAdmin.setForeground(new java.awt.Color(0, 0, 0));
        jPassUsuarioAdmin.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPnlUsuarios.add(jPassUsuarioAdmin);
        jPassUsuarioAdmin.setBounds(703, 227, 207, 35);

        jLblIdentificador51.setBackground(new java.awt.Color(255, 255, 255));
        jLblIdentificador51.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 1, 20)); // NOI18N
        jLblIdentificador51.setForeground(new java.awt.Color(0, 0, 0));
        jLblIdentificador51.setText("Cuenta administrador");
        jPnlUsuarios.add(jLblIdentificador51);
        jLblIdentificador51.setBounds(703, 72, 207, 24);

        jLblIdentificador52.setBackground(new java.awt.Color(255, 255, 255));
        jLblIdentificador52.setFont(new java.awt.Font("HelveticaNowDisplay Medium", 0, 20)); // NOI18N
        jLblIdentificador52.setForeground(new java.awt.Color(0, 0, 0));
        jLblIdentificador52.setText("Usuario");
        jPnlUsuarios.add(jLblIdentificador52);
        jLblIdentificador52.setBounds(370, 260, 272, 24);

        jTfNombreUsuario.setBackground(new java.awt.Color(255, 255, 255));
        jTfNombreUsuario.setFont(new java.awt.Font("HelveticaNowDisplay Regular", 0, 16)); // NOI18N
        jTfNombreUsuario.setForeground(new java.awt.Color(0, 0, 0));
        jTfNombreUsuario.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTfNombreUsuario.setSelectionColor(new java.awt.Color(0, 153, 153));
        jPnlUsuarios.add(jTfNombreUsuario);
        jTfNombreUsuario.setBounds(370, 290, 272, 35);

        jLblIdentificador53.setBackground(new java.awt.Color(255, 255, 255));
        jLblIdentificador53.setFont(new java.awt.Font("HelveticaNowDisplay Medium", 0, 20)); // NOI18N
        jLblIdentificador53.setForeground(new java.awt.Color(0, 0, 0));
        jLblIdentificador53.setText("Contraseña");
        jPnlUsuarios.add(jLblIdentificador53);
        jLblIdentificador53.setBounds(370, 340, 272, 24);

        jPfContra.setBackground(new java.awt.Color(255, 255, 255));
        jPfContra.setForeground(new java.awt.Color(0, 0, 0));
        jPnlUsuarios.add(jPfContra);
        jPfContra.setBounds(370, 370, 272, 35);

        jPanel7.setBackground(new java.awt.Color(153, 153, 153));

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 272, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1, Short.MAX_VALUE)
        );

        jPnlUsuarios.add(jPanel7);
        jPanel7.setBounds(370, 160, 272, 1);

        jLblIdentificador55.setBackground(new java.awt.Color(255, 255, 255));
        jLblIdentificador55.setFont(new java.awt.Font("HelveticaNowDisplay Medium", 0, 20)); // NOI18N
        jLblIdentificador55.setForeground(new java.awt.Color(0, 0, 0));
        jLblIdentificador55.setText("Apellidos");
        jPnlUsuarios.add(jLblIdentificador55);
        jLblIdentificador55.setBounds(30, 150, 272, 24);

        jTfNumIdentidad.setBackground(new java.awt.Color(255, 255, 255));
        jTfNumIdentidad.setFont(new java.awt.Font("HelveticaNowDisplay Regular", 0, 16)); // NOI18N
        jTfNumIdentidad.setForeground(new java.awt.Color(0, 0, 0));
        jTfNumIdentidad.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTfNumIdentidad.setSelectionColor(new java.awt.Color(0, 153, 153));
        jPnlUsuarios.add(jTfNumIdentidad);
        jTfNumIdentidad.setBounds(370, 100, 272, 35);

        jChkUsuarioActivo.setBackground(new java.awt.Color(255, 255, 255));
        jChkUsuarioActivo.setFont(new java.awt.Font("HelveticaNowDisplay Regular", 0, 14)); // NOI18N
        jChkUsuarioActivo.setForeground(new java.awt.Color(0, 0, 0));
        jChkUsuarioActivo.setText("Usuario Activo");
        jPnlUsuarios.add(jChkUsuarioActivo);
        jChkUsuarioActivo.setBounds(370, 420, 120, 30);

        jBtnGuardar.setBackground(new java.awt.Color(41, 128, 185));
        jBtnGuardar.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 0, 16)); // NOI18N
        jBtnGuardar.setForeground(new java.awt.Color(255, 255, 255));
        jBtnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/guardar-white24.png"))); // NOI18N
        jBtnGuardar.setText("GUARDAR USUARIO");
        jBtnGuardar.setBorder(null);
        jBtnGuardar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jBtnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnGuardarActionPerformed(evt);
            }
        });
        jPnlUsuarios.add(jBtnGuardar);
        jBtnGuardar.setBounds(30, 480, 272, 50);

        jLblIdUsuario.setText("jLabel1");
        jPnlUsuarios.add(jLblIdUsuario);
        jLblIdUsuario.setBounds(630, 30, 41, 16);

        jBtnCancelar.setBackground(new java.awt.Color(41, 128, 185));
        jBtnCancelar.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 0, 16)); // NOI18N
        jBtnCancelar.setForeground(new java.awt.Color(255, 255, 255));
        jBtnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/x-blanca24.png"))); // NOI18N
        jBtnCancelar.setText("CANCELAR");
        jBtnCancelar.setBorder(null);
        jBtnCancelar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jBtnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnCancelarActionPerformed(evt);
            }
        });
        jPnlUsuarios.add(jBtnCancelar);
        jBtnCancelar.setBounds(370, 480, 272, 50);

        jTabUsuarios.addTab("tab1", jPnlUsuarios);

        jPnlDesactivarUsuario.setBackground(new java.awt.Color(255, 255, 255));
        jPnlDesactivarUsuario.setLayout(null);

        jTblUsuarios.setBackground(new java.awt.Color(255, 255, 255));
        jTblUsuarios.setFont(new java.awt.Font("HelveticaNowDisplay Regular", 0, 14)); // NOI18N
        jTblUsuarios.setForeground(new java.awt.Color(0, 0, 0));
        jTblUsuarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "#", "RTN", "Nombres", "Apellidos", "Usuario", "Estado Usuario", "Tipo Usuario", "Direccion", "Telefono"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTblUsuarios.setRowHeight(20);
        jScrollPane2.setViewportView(jTblUsuarios);

        jPnlDesactivarUsuario.add(jScrollPane2);
        jScrollPane2.setBounds(35, 153, 880, 350);

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
                .addComponent(jTfBuscar, javax.swing.GroupLayout.DEFAULT_SIZE, 286, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jBtnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPnlBuscarLayout.setVerticalGroup(
            jPnlBuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTfBuscar)
            .addComponent(jBtnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jPnlDesactivarUsuario.add(jPnlBuscar);
        jPnlBuscar.setBounds(40, 100, 348, 40);

        jBtnModificarUsuario.setBackground(new java.awt.Color(102, 102, 102));
        jBtnModificarUsuario.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 1, 20)); // NOI18N
        jBtnModificarUsuario.setForeground(new java.awt.Color(41, 128, 185));
        jBtnModificarUsuario.setText("Modificar usuario");
        jBtnModificarUsuario.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jBtnModificarUsuario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jBtnModificarUsuarioMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jBtnModificarUsuarioMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jBtnModificarUsuarioMouseExited(evt);
            }
        });
        jPnlDesactivarUsuario.add(jBtnModificarUsuario);
        jBtnModificarUsuario.setBounds(750, 120, 157, 24);

        jLblIdentificador56.setBackground(new java.awt.Color(255, 255, 255));
        jLblIdentificador56.setFont(new java.awt.Font("HelveticaNowDisplay Medium", 0, 20)); // NOI18N
        jLblIdentificador56.setForeground(new java.awt.Color(0, 0, 0));
        jLblIdentificador56.setText("Número de identidad");
        jPnlDesactivarUsuario.add(jLblIdentificador56);
        jLblIdentificador56.setBounds(40, 70, 203, 24);

        jLblIdentificador61.setBackground(new java.awt.Color(102, 102, 102));
        jLblIdentificador61.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 1, 20)); // NOI18N
        jLblIdentificador61.setText("Información del usuario");
        jPnlDesactivarUsuario.add(jLblIdentificador61);
        jLblIdentificador61.setBounds(40, 30, 442, 24);

        jTabUsuarios.addTab("tab2", jPnlDesactivarUsuario);

        jPanel1.add(jTabUsuarios, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 60, 1030, 570));

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

    private void jSBListaUsuariosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jSBListaUsuariosMouseClicked
        this.jTabUsuarios.setSelectedIndex(1);
        this.jSBNuevoUsuario.setBackground(azul);
        this.jSBListaUsuarios.setBackground(celeste);
        this.jBtnSideBar.setVisible(true);
        this.jBtnSideBar1.setVisible(false);
    }//GEN-LAST:event_jSBListaUsuariosMouseClicked

    private void jSBAyudaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jSBAyudaMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jSBAyudaMouseClicked

    private void jSBNuevoUsuarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jSBNuevoUsuarioMouseClicked
        this.jTabUsuarios.setSelectedIndex(0);
        this.jSBNuevoUsuario.setBackground(celeste);
        this.jSBListaUsuarios.setBackground(azul);
        this.jBtnSideBar.setVisible(true);
        this.jBtnSideBar1.setVisible(false);
    }//GEN-LAST:event_jSBNuevoUsuarioMouseClicked

    private void jLblUsuarioNuevoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLblUsuarioNuevoMouseClicked
        this.jTabUsuarios.setSelectedIndex(0);
        this.jSBNuevoUsuario.setBackground(celeste);
        this.jSBListaUsuarios.setBackground(azul);
        this.jBtnSideBar.setVisible(true);
        this.jBtnSideBar1.setVisible(false);
    }//GEN-LAST:event_jLblUsuarioNuevoMouseClicked

    private void jLblIdentificador2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLblIdentificador2MouseClicked
        this.jTabUsuarios.setSelectedIndex(1);
        this.jSBNuevoUsuario.setBackground(azul);
        this.jSBListaUsuarios.setBackground(celeste);
        this.jBtnSideBar.setVisible(true);
        this.jBtnSideBar1.setVisible(false);
    }//GEN-LAST:event_jLblIdentificador2MouseClicked

    private void jBtnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnGuardarActionPerformed
        try {
            if(validarEntradas() && permisoUsuario()){
                try {
                    insertarEmpleado();
                    insertarUsuario();
                    llenarTablaUsuarios();
                    limpiarEntradas();
                } catch (SQLException ex) {
                    Logger.getLogger(JFraUsuario.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(JFraUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jBtnGuardarActionPerformed

    private void jBtnModificarUsuarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBtnModificarUsuarioMouseClicked
       this.jTabUsuarios.setSelectedIndex(0);
       this.jBtnEditar.setVisible(true);
       this.jBtnGuardar.setVisible(false);
       this.jTfNumIdentidad.setEnabled(false);
       cargarDatos();
        
    }//GEN-LAST:event_jBtnModificarUsuarioMouseClicked

    private void jBtnModificarUsuarioMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBtnModificarUsuarioMouseEntered
        this.jBtnModificarUsuario.setForeground(celeste);
    }//GEN-LAST:event_jBtnModificarUsuarioMouseEntered

    private void jBtnModificarUsuarioMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBtnModificarUsuarioMouseExited
        this.jBtnModificarUsuario.setForeground(azul);
    }//GEN-LAST:event_jBtnModificarUsuarioMouseExited

    private void jBtnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnEditarActionPerformed
        try {
            if(validarEntradas() && permisoUsuario()){
                try {
                    modificarEmpleado();
                    modificarUsuario();
                    llenarTablaUsuarios();
                    limpiarEntradas();
                    this.jTfNumIdentidad.setEnabled(true);
                    this.jBtnEditar.setVisible(false);
                    this.jBtnGuardar.setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(JFraUsuario.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(JFraUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jBtnEditarActionPerformed

    private void jTfBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTfBuscarKeyReleased
        try {
            llenarTablaUsuariosPorCodEmpleado(this.jTfBuscar.getText());
        } catch (SQLException ex) {
            Logger.getLogger(JFraUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jTfBuscarKeyReleased

    private void jBtnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnCancelarActionPerformed
        this.jBtnEditar.setVisible(false);
        this.jBtnGuardar.setVisible(true);
        this.jTfNumIdentidad.setEnabled(true);
        limpiarEntradas();
    }//GEN-LAST:event_jBtnCancelarActionPerformed

    private void jLblMinimizar2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLblMinimizar2MouseClicked
        this.setState(Frame.ICONIFIED);
    }//GEN-LAST:event_jLblMinimizar2MouseClicked

    private void jLblSalirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLblSalirMouseClicked
        jfc.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jLblSalirMouseClicked

    private void jSBMenuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jSBMenuMouseClicked
        this.jDfMenu.setLocationRelativeTo(null);
        this.jDfMenu.setVisible(true);             
    }//GEN-LAST:event_jSBMenuMouseClicked

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
            java.util.logging.Logger.getLogger(JFraUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFraUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFraUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFraUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new JFraUsuario().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(JFraUsuario.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton JBtnNo;
    private javax.swing.JLabel jBtnBuscar;
    private javax.swing.JButton jBtnCancelar;
    private javax.swing.JButton jBtnEditar;
    private javax.swing.JButton jBtnGuardar;
    private javax.swing.JLabel jBtnModificarUsuario;
    private javax.swing.JButton jBtnSi;
    private javax.swing.JLabel jBtnSideBar;
    private javax.swing.JLabel jBtnSideBar1;
    private javax.swing.JComboBox<String> jCboTipoUsuario;
    private javax.swing.JCheckBox jChkUsuarioActivo;
    private javax.swing.JDialog jDfMenu;
    private javax.swing.JLabel jLblIdUsuario;
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
    private javax.swing.JLabel jLblIdentificador55;
    private javax.swing.JLabel jLblIdentificador56;
    private javax.swing.JLabel jLblIdentificador57;
    private javax.swing.JLabel jLblIdentificador58;
    private javax.swing.JLabel jLblIdentificador59;
    private javax.swing.JLabel jLblIdentificador6;
    private javax.swing.JLabel jLblIdentificador61;
    private javax.swing.JLabel jLblIdentificador9;
    private javax.swing.JLabel jLblMinimizar2;
    private javax.swing.JLabel jLblSalir;
    private javax.swing.JLabel jLblTitulo2;
    private javax.swing.JLabel jLblUsuarioNuevo;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPasswordField jPassUsuarioAdmin;
    private javax.swing.JPasswordField jPfContra;
    private javax.swing.JPanel jPnlBuscar;
    private javax.swing.JPanel jPnlDesactivarUsuario;
    private javax.swing.JPanel jPnlUsuarios;
    private javax.swing.JPanel jSBAyuda;
    private javax.swing.JPanel jSBListaUsuarios;
    private javax.swing.JPanel jSBMenu;
    private javax.swing.JPanel jSBNuevoUsuario;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel jSideBar;
    private javax.swing.JScrollPane jSideBar1;
    private javax.swing.JTextArea jTADireccion;
    private javax.swing.JTabbedPane jTabUsuarios;
    private javax.swing.JTable jTblUsuarios;
    private javax.swing.JTextField jTfApellidos;
    private javax.swing.JTextField jTfBuscar;
    private javax.swing.JTextField jTfNombre;
    private javax.swing.JTextField jTfNombreUsuario;
    private javax.swing.JTextField jTfNumIdentidad;
    private javax.swing.JTextField jTfNumeroTelefono;
    private javax.swing.JTextField jTfUsuarioAdmin;
    // End of variables declaration//GEN-END:variables

    private void toArray(String[] Tipo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
