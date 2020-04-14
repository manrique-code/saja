/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capapresentacion;

import AppPackage.AnimationClass;
import capadatos.CDControlPago;
import capadatos.CDEtapa;
import capalogica.CLControlPago;
import com.placeholder.PlaceHolder;
import java.awt.Color;
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
public class JFraCortes extends javax.swing.JFrame {

    int etapa = 0;
    int mes = 0;
    String buscar;
    
    /**
     * Creates new form Cortes
     * @throws java.sql.SQLException
     */
    public JFraCortes() throws SQLException {
        initComponents();
        this.getContentPane().setBackground(Color.white);
        this.setLocationRelativeTo(null);
        this.jSideBar1.getViewport().setBackground(Color.getColor("52, 74, 94"));
        this.jBtnSideBarCerrar.setVisible(false);
        
        // --------------------------------------------------------------------
        cargarCboEtapas();
        
        
        etapa = Integer.parseInt(this.jCboEtapas.getSelectedItem().toString());
        mes = Integer.parseInt(this.jCboMeses.getSelectedItem().toString());
        this.cargarTblCorte(etapa, mes);
        
        p = new PlaceHolder(jTfBuscar, 
                            new Color(55,73,87),
                            Color.BLACK,
                            "Buscar...",
                            false,
                            "HelveticaNowDisplay Regular Plain",
                            14);
    }
    
    //Instancia para darle animación al SideBar
    AnimationClass sideBar = new AnimationClass();
    
    //Colores del formulario
    Color celeste = new Color(52,152,219);
    Color azulmarino = new Color(52,73,94);    
    Color azul = new Color(41,128,185);
    
    //clase de placeHolder
    PlaceHolder p;
    
    // mostrando el sidebar
    boolean mostrandoSideBar = false;
    
    // Instancia del menú
    JFraMenu jfm = new JFraMenu();
    
    // Método para las acciones del SideBar
    
    
    // Método para cargar el ComboBox de las ETAPAS
    public void cargarCboEtapas() throws SQLException{
        CDEtapa cde = new CDEtapa();
        
        String[] etapa = new String[cde.cargarEtapas().size()];
        etapa = cde.cargarEtapas().toArray(etapa);
        
        DefaultComboBoxModel modeloEtapa = new DefaultComboBoxModel(etapa);
        this.jCboEtapas.setModel(modeloEtapa);
    }
    
    // Método para limpiar la tabla
    private void limpiarTabla(){
        DefaultTableModel dtm = (DefaultTableModel) this.jTblCortes.getModel();
        
        while(dtm.getRowCount() > 0){
            dtm.removeRow(0);
        }
    }
    
    // Método para cargar la tabla de cortes
    public void cargarTblCorte(int etapa, int mesAdeudado) throws SQLException{
        this.limpiarTabla();
                
        CDControlPago cdcp = new CDControlPago();
        List<CLControlPago> listaCorte = cdcp.obtenerListadoCorte(etapa, mesAdeudado);
        DefaultTableModel dtm = (DefaultTableModel) this.jTblCortes.getModel();
        
        listaCorte.stream().map((clc) -> {
            Object[] columna = new Object[6];
            columna[0] = clc.getNumLista();
            columna[1] = clc.getNumBloque();
            columna[2] = clc.getNumCasa();
            columna[3] = clc.getRTN();
            columna[4] = clc.getNombreCompleto();
            columna[5] = clc.getMesAdeudado();
            return columna;
        }).forEachOrdered((columna) -> {
            dtm.addRow(columna);
        });        
        
    }
    
    public void cargarTblCortePorBloque(int etapa, int mesAdeudado, String bloque) throws SQLException{
        this.limpiarTabla();
                
        CDControlPago cdcp = new CDControlPago();
        List<CLControlPago> listaCorte = cdcp.obtenerListadoCortePorBloque(etapa, mesAdeudado, bloque);
        DefaultTableModel dtm = (DefaultTableModel) this.jTblCortes.getModel();
        
        listaCorte.stream().map((clc) -> {
            Object[] columna = new Object[6];
            columna[0] = clc.getNumLista();
            columna[1] = clc.getNumBloque();
            columna[2] = clc.getNumCasa();
            columna[3] = clc.getRTN();
            columna[4] = clc.getNombreCompleto();
            columna[5] = clc.getMesAdeudado();
            return columna;
        }).forEachOrdered((columna) -> {
            dtm.addRow(columna);
        });        
        
    }
    
    public void cargarTblCortePorAbonado(int etapa, int mesAdeudado, String rtn) throws SQLException{
        this.limpiarTabla();
                
        CDControlPago cdcp = new CDControlPago();
        List<CLControlPago> listaCorte = cdcp.obtenerListadoCortePorRTN(etapa, mesAdeudado, rtn);
        DefaultTableModel dtm = (DefaultTableModel) this.jTblCortes.getModel();
        
        listaCorte.stream().map((clc) -> {
            Object[] columna = new Object[6];
            columna[0] = clc.getNumLista();
            columna[1] = clc.getNumBloque();
            columna[2] = clc.getNumCasa();
            columna[3] = clc.getRTN();
            columna[4] = clc.getNombreCompleto();
            columna[5] = clc.getMesAdeudado();
            return columna;
        }).forEachOrdered((columna) -> {
            dtm.addRow(columna);
        });        
        
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
        jDialog1 = new javax.swing.JDialog();
        jDfMenu2 = new javax.swing.JDialog();
        jPanel17 = new javax.swing.JPanel();
        jLblIdentificador58 = new javax.swing.JLabel();
        jLblIdentificador75 = new javax.swing.JLabel();
        jLblIdentificador76 = new javax.swing.JLabel();
        jBtnSi2 = new javax.swing.JButton();
        JBtnNo = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jBtnSideBarCerrar = new javax.swing.JLabel();
        jBtnSideBar = new javax.swing.JLabel();
        jLblMinimizar = new javax.swing.JLabel();
        jLblTitulo5 = new javax.swing.JLabel();
        jLblSalir = new javax.swing.JLabel();
        jSideBar1 = new javax.swing.JScrollPane();
        jSideBar = new javax.swing.JPanel();
        jSBListadoCorte = new javax.swing.JPanel();
        jLblListadoCorte = new javax.swing.JLabel();
        jLblIdentificador1 = new javax.swing.JLabel();
        jSBReporteCortes = new javax.swing.JPanel();
        jLblReporteCortes = new javax.swing.JLabel();
        jLblIdentificador3 = new javax.swing.JLabel();
        jSBMenu = new javax.swing.JPanel();
        jLblIdentificador9 = new javax.swing.JLabel();
        jLblIdentificador10 = new javax.swing.JLabel();
        jPnlSeparatorX = new javax.swing.JPanel();
        jLblIdentificador5 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jSBAyuda = new javax.swing.JPanel();
        jLblIdentificador4 = new javax.swing.JLabel();
        jLblIdentificador6 = new javax.swing.JLabel();
        jTabContrato = new javax.swing.JTabbedPane();
        jPnlListadoCorte = new javax.swing.JPanel();
        jLblIdentificador11 = new javax.swing.JLabel();
        jRdBtnBloque = new javax.swing.JRadioButton();
        jRdBtnRTN = new javax.swing.JRadioButton();
        jPnlBuscar = new javax.swing.JPanel();
        jTfBuscar = new javax.swing.JTextField();
        jBtnBuscar = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTblCortes = new javax.swing.JTable();
        jCboEtapas = new javax.swing.JComboBox<>();
        jLblIdentificador12 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLblIdentificador13 = new javax.swing.JLabel();
        jPnlBuscar3 = new javax.swing.JPanel();
        jBtnBuscar3 = new javax.swing.JLabel();
        jCboMeses = new javax.swing.JComboBox<>();
        jLblGenerarReporte = new javax.swing.JLabel();
        jPnlReportesCorte = new javax.swing.JPanel();

        javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        jDfMenu2.setTitle("Confirmar acción");
        jDfMenu2.setAlwaysOnTop(true);
        jDfMenu2.setModal(true);
        jDfMenu2.setModalExclusionType(java.awt.Dialog.ModalExclusionType.APPLICATION_EXCLUDE);
        jDfMenu2.setUndecorated(true);
        jDfMenu2.setSize(new java.awt.Dimension(300, 200));

        jPanel17.setBackground(new java.awt.Color(255, 255, 255));
        jPanel17.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jLblIdentificador58.setBackground(new java.awt.Color(255, 255, 255));
        jLblIdentificador58.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 1, 20)); // NOI18N
        jLblIdentificador58.setForeground(new java.awt.Color(0, 0, 0));
        jLblIdentificador58.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/menu-negro24.png"))); // NOI18N
        jLblIdentificador58.setText("¿Regresar al menú?");
        jLblIdentificador58.setIconTextGap(10);

        jLblIdentificador75.setBackground(new java.awt.Color(255, 255, 255));
        jLblIdentificador75.setFont(new java.awt.Font("HelveticaNowDisplay Regular", 0, 16)); // NOI18N
        jLblIdentificador75.setForeground(new java.awt.Color(0, 0, 0));
        jLblIdentificador75.setText("Si no guardó el registro actual, su");

        jLblIdentificador76.setBackground(new java.awt.Color(255, 255, 255));
        jLblIdentificador76.setFont(new java.awt.Font("HelveticaNowDisplay Regular", 0, 16)); // NOI18N
        jLblIdentificador76.setForeground(new java.awt.Color(0, 0, 0));
        jLblIdentificador76.setText("progreso se perderá.");

        jBtnSi2.setBackground(new java.awt.Color(41, 128, 185));
        jBtnSi2.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 0, 16)); // NOI18N
        jBtnSi2.setForeground(new java.awt.Color(255, 255, 255));
        jBtnSi2.setText("SI");
        jBtnSi2.setBorder(null);
        jBtnSi2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jBtnSi2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnSi2ActionPerformed(evt);
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

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLblIdentificador76)
                    .addComponent(jLblIdentificador58)
                    .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel17Layout.createSequentialGroup()
                            .addComponent(jBtnSi2, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(JBtnNo, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jLblIdentificador75)))
                .addContainerGap(58, Short.MAX_VALUE))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLblIdentificador58)
                .addGap(18, 18, 18)
                .addComponent(jLblIdentificador75)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLblIdentificador76)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBtnSi2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JBtnNo, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24))
        );

        javax.swing.GroupLayout jDfMenu2Layout = new javax.swing.GroupLayout(jDfMenu2.getContentPane());
        jDfMenu2.getContentPane().setLayout(jDfMenu2Layout);
        jDfMenu2Layout.setHorizontalGroup(
            jDfMenu2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jDfMenu2Layout.setVerticalGroup(
            jDfMenu2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
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

        jBtnSideBarCerrar.setFont(new java.awt.Font("HelveticaNowDisplay ExtraBold", 0, 25)); // NOI18N
        jBtnSideBarCerrar.setForeground(new java.awt.Color(255, 255, 255));
        jBtnSideBarCerrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/x-blanca24.png"))); // NOI18N
        jBtnSideBarCerrar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jBtnSideBarCerrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jBtnSideBarCerrarMouseClicked(evt);
            }
        });
        jPanel3.add(jBtnSideBarCerrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, -1, 60));

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

        jLblMinimizar.setFont(new java.awt.Font("HelveticaNowDisplay ExtraBold", 0, 30)); // NOI18N
        jLblMinimizar.setForeground(new java.awt.Color(255, 255, 255));
        jLblMinimizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/minimizar-blanco24.png"))); // NOI18N
        jLblMinimizar.setToolTipText("Minimizar la ventana");
        jLblMinimizar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLblMinimizar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLblMinimizarMouseClicked(evt);
            }
        });
        jPanel3.add(jLblMinimizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 0, -1, 60));

        jLblTitulo5.setFont(new java.awt.Font("HelveticaNowDisplay ExtraBold", 0, 30)); // NOI18N
        jLblTitulo5.setForeground(new java.awt.Color(255, 255, 255));
        jLblTitulo5.setText("Corte");
        jPanel3.add(jLblTitulo5, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 10, -1, 50));

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
        jSideBar1.setForeground(new java.awt.Color(0, 0, 0));

        jSideBar.setBackground(new java.awt.Color(52, 73, 94));
        jSideBar.setPreferredSize(new java.awt.Dimension(260, 600));
        jSideBar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jSBListadoCorte.setBackground(new java.awt.Color(52, 152, 219));
        jSBListadoCorte.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jSBListadoCorte.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jSBListadoCorteMouseClicked(evt);
            }
        });

        jLblListadoCorte.setBackground(new java.awt.Color(102, 102, 102));
        jLblListadoCorte.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 1, 20)); // NOI18N
        jLblListadoCorte.setForeground(new java.awt.Color(255, 255, 255));
        jLblListadoCorte.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/tabla-blanco32.png"))); // NOI18N
        jLblListadoCorte.setToolTipText("Contratos");

        jLblIdentificador1.setBackground(new java.awt.Color(102, 102, 102));
        jLblIdentificador1.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 1, 20)); // NOI18N
        jLblIdentificador1.setForeground(new java.awt.Color(255, 255, 255));
        jLblIdentificador1.setText("Listado de corte");

        javax.swing.GroupLayout jSBListadoCorteLayout = new javax.swing.GroupLayout(jSBListadoCorte);
        jSBListadoCorte.setLayout(jSBListadoCorteLayout);
        jSBListadoCorteLayout.setHorizontalGroup(
            jSBListadoCorteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jSBListadoCorteLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLblIdentificador1, javax.swing.GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jLblListadoCorte, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jSBListadoCorteLayout.setVerticalGroup(
            jSBListadoCorteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jSBListadoCorteLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jSBListadoCorteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLblListadoCorte, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
                    .addComponent(jLblIdentificador1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jSideBar.add(jSBListadoCorte, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 225, 260, -1));

        jSBReporteCortes.setBackground(new java.awt.Color(52, 73, 94));
        jSBReporteCortes.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jSBReporteCortes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jSBReporteCortesMouseClicked(evt);
            }
        });

        jLblReporteCortes.setBackground(new java.awt.Color(102, 102, 102));
        jLblReporteCortes.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 1, 20)); // NOI18N
        jLblReporteCortes.setForeground(new java.awt.Color(255, 255, 255));
        jLblReporteCortes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/reportes-blanco32.png"))); // NOI18N
        jLblReporteCortes.setToolTipText("Generar reporte de cortes");

        jLblIdentificador3.setBackground(new java.awt.Color(102, 102, 102));
        jLblIdentificador3.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 1, 20)); // NOI18N
        jLblIdentificador3.setForeground(new java.awt.Color(255, 255, 255));
        jLblIdentificador3.setText("Reporte de cortes");

        javax.swing.GroupLayout jSBReporteCortesLayout = new javax.swing.GroupLayout(jSBReporteCortes);
        jSBReporteCortes.setLayout(jSBReporteCortesLayout);
        jSBReporteCortesLayout.setHorizontalGroup(
            jSBReporteCortesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jSBReporteCortesLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLblIdentificador3, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLblReporteCortes)
                .addGap(10, 10, 10))
        );
        jSBReporteCortesLayout.setVerticalGroup(
            jSBReporteCortesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jSBReporteCortesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jSBReporteCortesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLblReporteCortes, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
                    .addComponent(jLblIdentificador3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jSideBar.add(jSBReporteCortes, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 299, 260, -1));

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

        jPnlSeparatorX.setBackground(new java.awt.Color(255, 255, 255));
        jPnlSeparatorX.setPreferredSize(new java.awt.Dimension(0, 1));

        javax.swing.GroupLayout jPnlSeparatorXLayout = new javax.swing.GroupLayout(jPnlSeparatorX);
        jPnlSeparatorX.setLayout(jPnlSeparatorXLayout);
        jPnlSeparatorXLayout.setHorizontalGroup(
            jPnlSeparatorXLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPnlSeparatorXLayout.setVerticalGroup(
            jPnlSeparatorXLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1, Short.MAX_VALUE)
        );

        jSideBar.add(jPnlSeparatorX, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 113, 255, -1));

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

        jTabContrato.setBackground(new java.awt.Color(255, 255, 255));
        jTabContrato.setForeground(new java.awt.Color(0, 0, 0));
        jTabContrato.setTabPlacement(javax.swing.JTabbedPane.RIGHT);

        jPnlListadoCorte.setBackground(new java.awt.Color(255, 255, 255));
        jPnlListadoCorte.setForeground(new java.awt.Color(0, 0, 0));

        jLblIdentificador11.setBackground(new java.awt.Color(102, 102, 102));
        jLblIdentificador11.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 1, 20)); // NOI18N
        jLblIdentificador11.setForeground(new java.awt.Color(0, 0, 0));
        jLblIdentificador11.setText("Buscar por:");

        jRdBtnBloque.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(jRdBtnBloque);
        jRdBtnBloque.setFont(new java.awt.Font("HelveticaNowDisplay Light", 0, 14)); // NOI18N
        jRdBtnBloque.setForeground(new java.awt.Color(0, 0, 0));
        jRdBtnBloque.setSelected(true);
        jRdBtnBloque.setText("Bloque");

        jRdBtnRTN.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(jRdBtnRTN);
        jRdBtnRTN.setFont(new java.awt.Font("HelveticaNowDisplay Light", 0, 14)); // NOI18N
        jRdBtnRTN.setForeground(new java.awt.Color(0, 0, 0));
        jRdBtnRTN.setText("Num. Identidad");

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
                .addComponent(jTfBuscar, javax.swing.GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jBtnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPnlBuscarLayout.setVerticalGroup(
            jPnlBuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTfBuscar)
            .addComponent(jBtnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jTblCortes.setBackground(new java.awt.Color(255, 255, 255));
        jTblCortes.setFont(new java.awt.Font("HelveticaNowDisplay Regular", 0, 14)); // NOI18N
        jTblCortes.setForeground(new java.awt.Color(0, 0, 0));
        jTblCortes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "#", "Bloque", "Casa", "Número de identidad", "Nombre Completo", "Meses adeudados"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTblCortes.setRowHeight(30);
        jScrollPane6.setViewportView(jTblCortes);

        jCboEtapas.setBackground(new java.awt.Color(255, 255, 255));
        jCboEtapas.setFont(new java.awt.Font("HelveticaNowDisplay Regular", 0, 14)); // NOI18N
        jCboEtapas.setForeground(new java.awt.Color(0, 0, 0));
        jCboEtapas.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCboEtapasItemStateChanged(evt);
            }
        });

        jLblIdentificador12.setBackground(new java.awt.Color(102, 102, 102));
        jLblIdentificador12.setFont(new java.awt.Font("HelveticaNowDisplay Medium", 0, 20)); // NOI18N
        jLblIdentificador12.setForeground(new java.awt.Color(0, 0, 0));
        jLblIdentificador12.setText("Etapa:");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jLblIdentificador13.setBackground(new java.awt.Color(102, 102, 102));
        jLblIdentificador13.setFont(new java.awt.Font("HelveticaNowDisplay Medium", 0, 20)); // NOI18N
        jLblIdentificador13.setForeground(new java.awt.Color(0, 0, 0));
        jLblIdentificador13.setText("Meses debidos:");

        jPnlBuscar3.setBackground(new java.awt.Color(255, 255, 255));
        jPnlBuscar3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jBtnBuscar3.setBackground(new java.awt.Color(9, 132, 227));
        jBtnBuscar3.setFont(new java.awt.Font("HelveticaNowDisplay Regular", 0, 14)); // NOI18N
        jBtnBuscar3.setForeground(new java.awt.Color(0, 0, 0));
        jBtnBuscar3.setText("Meses:");
        jBtnBuscar3.setToolTipText("Click para buscar");
        jBtnBuscar3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jCboMeses.setBackground(new java.awt.Color(255, 255, 255));
        jCboMeses.setFont(new java.awt.Font("HelveticaNowDisplay Regular", 0, 14)); // NOI18N
        jCboMeses.setForeground(new java.awt.Color(0, 0, 0));
        jCboMeses.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" }));
        jCboMeses.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCboMesesItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPnlBuscar3Layout = new javax.swing.GroupLayout(jPnlBuscar3);
        jPnlBuscar3.setLayout(jPnlBuscar3Layout);
        jPnlBuscar3Layout.setHorizontalGroup(
            jPnlBuscar3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPnlBuscar3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jBtnBuscar3, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCboMeses, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPnlBuscar3Layout.setVerticalGroup(
            jPnlBuscar3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPnlBuscar3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jBtnBuscar3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jCboMeses, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jLblGenerarReporte.setBackground(new java.awt.Color(102, 102, 102));
        jLblGenerarReporte.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 1, 20)); // NOI18N
        jLblGenerarReporte.setForeground(new java.awt.Color(41, 128, 185));
        jLblGenerarReporte.setText("Generar reporte");
        jLblGenerarReporte.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLblGenerarReporte.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLblGenerarReporteMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLblGenerarReporteMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLblGenerarReporteMouseExited(evt);
            }
        });

        javax.swing.GroupLayout jPnlListadoCorteLayout = new javax.swing.GroupLayout(jPnlListadoCorte);
        jPnlListadoCorte.setLayout(jPnlListadoCorteLayout);
        jPnlListadoCorteLayout.setHorizontalGroup(
            jPnlListadoCorteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPnlListadoCorteLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPnlListadoCorteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPnlListadoCorteLayout.createSequentialGroup()
                        .addGroup(jPnlListadoCorteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jCboEtapas, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLblIdentificador12, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPnlListadoCorteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPnlBuscar3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLblIdentificador13, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLblGenerarReporte))
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPnlListadoCorteLayout.createSequentialGroup()
                        .addGap(63, 281, Short.MAX_VALUE)
                        .addComponent(jLblIdentificador11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jRdBtnBloque)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jRdBtnRTN)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPnlBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(80, 80, 80))
        );
        jPnlListadoCorteLayout.setVerticalGroup(
            jPnlListadoCorteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPnlListadoCorteLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(jPnlListadoCorteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPnlListadoCorteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLblIdentificador11, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jRdBtnBloque, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jRdBtnRTN, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPnlBuscar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(jPnlListadoCorteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPnlListadoCorteLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPnlListadoCorteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLblIdentificador13, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLblIdentificador12, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPnlListadoCorteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jCboEtapas, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPnlBuscar3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPnlListadoCorteLayout.createSequentialGroup()
                        .addGap(74, 74, 74)
                        .addComponent(jLblGenerarReporte)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 367, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );

        jTabContrato.addTab("tab1", jPnlListadoCorte);

        jPnlReportesCorte.setBackground(new java.awt.Color(255, 255, 255));
        jPnlReportesCorte.setForeground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout jPnlReportesCorteLayout = new javax.swing.GroupLayout(jPnlReportesCorte);
        jPnlReportesCorte.setLayout(jPnlReportesCorteLayout);
        jPnlReportesCorteLayout.setHorizontalGroup(
            jPnlReportesCorteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 988, Short.MAX_VALUE)
        );
        jPnlReportesCorteLayout.setVerticalGroup(
            jPnlReportesCorteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 566, Short.MAX_VALUE)
        );

        jTabContrato.addTab("tab2", jPnlReportesCorte);

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

    private void jBtnSideBarCerrarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBtnSideBarCerrarMouseClicked
        // TODO add your handling code here:
        this.sideBar.jTextAreaXLeft(0, -210 , 5, 5, jSideBar1);
        this.jBtnSideBarCerrar.setVisible(false);
        this.jBtnSideBar.setVisible(true);
        this.mostrandoSideBar = false;
    }//GEN-LAST:event_jBtnSideBarCerrarMouseClicked

    private void jBtnSideBarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBtnSideBarMouseClicked
        // TODO add your handling code here:
        this.sideBar.jTextAreaXRight(-210, 0 , 5, 5, jSideBar1);
        this.jBtnSideBar.setVisible(false);
        this.jBtnSideBarCerrar.setVisible(true);
        this.mostrandoSideBar = true;
    }//GEN-LAST:event_jBtnSideBarMouseClicked

    private void jSBReporteCortesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jSBReporteCortesMouseClicked
        // TODO add your handling code here:        
        if(this.mostrandoSideBar){
            this.sideBar.jTextAreaXLeft(0, -210 , 5, 5, jSideBar1);
            this.jBtnSideBarCerrar.setVisible(false);
            this.jBtnSideBar.setVisible(true);            
        }        
        this.jTabContrato.setSelectedIndex(1);
        this.jSBReporteCortes.setBackground(celeste);
        this.jSBListadoCorte.setBackground(azulmarino);
    }//GEN-LAST:event_jSBReporteCortesMouseClicked

    private void jSBAyudaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jSBAyudaMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jSBAyudaMouseClicked

    private void jCboMesesItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCboMesesItemStateChanged
        // tomar los datos del combobox de meses
        etapa = Integer.parseInt(this.jCboEtapas.getSelectedItem().toString());
        mes = Integer.parseInt(this.jCboMeses.getSelectedItem().toString());
        try {
            this.cargarTblCorte(etapa, mes);
        } catch (SQLException ex) {
            Logger.getLogger(JFraCortes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jCboMesesItemStateChanged

    private void jCboEtapasItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCboEtapasItemStateChanged
        etapa = Integer.parseInt(this.jCboEtapas.getSelectedItem().toString());
        mes = Integer.parseInt(this.jCboMeses.getSelectedItem().toString());
        try {
            this.cargarTblCorte(etapa, mes);
        } catch (SQLException ex) {
            Logger.getLogger(JFraCortes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jCboEtapasItemStateChanged

    private void jTfBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTfBuscarKeyReleased
        etapa = Integer.parseInt(this.jCboEtapas.getSelectedItem().toString());
        mes = Integer.parseInt(this.jCboMeses.getSelectedItem().toString());
        buscar = this.jTfBuscar.getText();
        
        try{
            // Evaluamos si el Radio button de bloque esta seleccionado
            if(this.jRdBtnBloque.isSelected()){
                this.cargarTblCortePorBloque(etapa, mes, buscar);
            } else if(this.jRdBtnRTN.isSelected()){
                this.cargarTblCortePorAbonado(etapa, mes, buscar);
            } else {
               JOptionPane.showMessageDialog(null, "Seleccione una opción."); 
            }            
            
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }//GEN-LAST:event_jTfBuscarKeyReleased

    private void jLblMinimizarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLblMinimizarMouseClicked
        this.setState(Frame.ICONIFIED);
    }//GEN-LAST:event_jLblMinimizarMouseClicked

    private void jLblSalirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLblSalirMouseClicked
        this.dispose();
    }//GEN-LAST:event_jLblSalirMouseClicked

    private void jSBListadoCorteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jSBListadoCorteMouseClicked
        this.sideBar.jTextAreaXLeft(0, -210 , 5, 5, jSideBar1);
        this.jTabContrato.setSelectedIndex(0);
        this.jSBReporteCortes.setBackground(azulmarino);
        this.jSBListadoCorte.setBackground(celeste);
    }//GEN-LAST:event_jSBListadoCorteMouseClicked

    private void jLblGenerarReporteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLblGenerarReporteMouseClicked
        this.jTabContrato.setSelectedIndex(1);
        this.jSBReporteCortes.setBackground(celeste);
        this.jSBListadoCorte.setBackground(azulmarino);
    }//GEN-LAST:event_jLblGenerarReporteMouseClicked

    private void jLblGenerarReporteMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLblGenerarReporteMouseEntered
        this.jLblGenerarReporte.setForeground(Color.blue);
    }//GEN-LAST:event_jLblGenerarReporteMouseEntered

    private void jLblGenerarReporteMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLblGenerarReporteMouseExited
        this.jLblGenerarReporte.setForeground(azul);
    }//GEN-LAST:event_jLblGenerarReporteMouseExited

    private void jBtnSi2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnSi2ActionPerformed
        this.dispose();
        this.jDfMenu2.dispose();
        jfm.setVisible(true);
    }//GEN-LAST:event_jBtnSi2ActionPerformed

    private void JBtnNoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBtnNoActionPerformed
        this.jDfMenu2.dispose();
    }//GEN-LAST:event_JBtnNoActionPerformed

    private void jSBMenuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jSBMenuMouseClicked
        this.jDfMenu2.setLocationRelativeTo(null);
        this.jDfMenu2.setVisible(true);
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
            java.util.logging.Logger.getLogger(JFraCortes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFraCortes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFraCortes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFraCortes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new JFraCortes().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(JFraCortes.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton JBtnNo;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jBtnBuscar;
    private javax.swing.JLabel jBtnBuscar3;
    private javax.swing.JButton jBtnSi;
    private javax.swing.JButton jBtnSi1;
    private javax.swing.JButton jBtnSi2;
    private javax.swing.JLabel jBtnSideBar;
    private javax.swing.JLabel jBtnSideBarCerrar;
    private javax.swing.JComboBox<String> jCboEtapas;
    private javax.swing.JComboBox<String> jCboMeses;
    private javax.swing.JDialog jDfMenu;
    private javax.swing.JDialog jDfMenu1;
    private javax.swing.JDialog jDfMenu2;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JLabel jLblGenerarReporte;
    private javax.swing.JLabel jLblIdentificador1;
    private javax.swing.JLabel jLblIdentificador10;
    private javax.swing.JLabel jLblIdentificador11;
    private javax.swing.JLabel jLblIdentificador12;
    private javax.swing.JLabel jLblIdentificador13;
    private javax.swing.JLabel jLblIdentificador3;
    private javax.swing.JLabel jLblIdentificador4;
    private javax.swing.JLabel jLblIdentificador5;
    private javax.swing.JLabel jLblIdentificador58;
    private javax.swing.JLabel jLblIdentificador6;
    private javax.swing.JLabel jLblIdentificador60;
    private javax.swing.JLabel jLblIdentificador61;
    private javax.swing.JLabel jLblIdentificador71;
    private javax.swing.JLabel jLblIdentificador72;
    private javax.swing.JLabel jLblIdentificador73;
    private javax.swing.JLabel jLblIdentificador74;
    private javax.swing.JLabel jLblIdentificador75;
    private javax.swing.JLabel jLblIdentificador76;
    private javax.swing.JLabel jLblIdentificador9;
    private javax.swing.JLabel jLblListadoCorte;
    private javax.swing.JLabel jLblMinimizar;
    private javax.swing.JLabel jLblReporteCortes;
    private javax.swing.JLabel jLblSalir;
    private javax.swing.JLabel jLblTitulo5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPnlBuscar;
    private javax.swing.JPanel jPnlBuscar3;
    private javax.swing.JPanel jPnlListadoCorte;
    private javax.swing.JPanel jPnlReportesCorte;
    private javax.swing.JPanel jPnlSeparatorX;
    private javax.swing.JRadioButton jRdBtnBloque;
    private javax.swing.JRadioButton jRdBtnRTN;
    private javax.swing.JPanel jSBAyuda;
    private javax.swing.JPanel jSBListadoCorte;
    private javax.swing.JPanel jSBMenu;
    private javax.swing.JPanel jSBReporteCortes;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JPanel jSideBar;
    private javax.swing.JScrollPane jSideBar1;
    private javax.swing.JTabbedPane jTabContrato;
    private javax.swing.JTable jTblCortes;
    private javax.swing.JTextField jTfBuscar;
    // End of variables declaration//GEN-END:variables
}
