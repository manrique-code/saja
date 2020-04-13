/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capapresentacion;

import AppPackage.AnimationClass;
import capadatos.CDMora;
import capalogica.CLMora;
import java.awt.Color;
import static java.lang.Integer.parseInt;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import java.awt.Frame;

/**
 *
 * @author Manrique
 */
public class JFraMora extends javax.swing.JFrame {

    /**
     * Creates new form jFraMora
     * @throws java.sql.SQLException
     */
    public JFraMora() throws SQLException {
        initComponents();
        llenarTablaListaMora();
        this.jLblVerMora.setVisible(false);
        this.getContentPane().setBackground(Color.white);
        this.setLocationRelativeTo(null);
        this.jSideBar1.getViewport().setBackground(Color.getColor("52, 74, 94"));
        this.jBtnSideBar1.setVisible(false);
    }
    
    AnimationClass sideBar = new AnimationClass();
    
    //Colores del formulario
    Color celeste = new Color(52, 152, 219);
    Color azul = new Color(52, 73, 94);
    
    // Metodo para limpiar las tablas
    private void limpiarTabla() {
        DefaultTableModel dtm = (DefaultTableModel) this.jTblListaMora.getModel();

        while (dtm.getRowCount() > 0) {
            dtm.removeRow(0);
        }
    }
    
    // Metodo para limpiar las tablas de meses en mora
    private void limpiarTablasMesesMora() {
        DefaultTableModel dtm = (DefaultTableModel) this.jTblMesesMorosos.getModel();
        
        while (dtm.getRowCount() > 0) {
            dtm.removeRow(0);
        }  
    }
    
    // Metodo para limpiar las tablas de meses pagados
    private void limpiarTablasMesesPagados() {
        DefaultTableModel dtm = (DefaultTableModel) this.jTblMesesPagados.getModel();
        
        while (dtm.getRowCount() > 0) {
            dtm.removeRow(0);
        }
    }
    
    // Metodo para limpiar la tabla de los meses y su valor
    private void limpiarTablasTotalMora() {
        DefaultTableModel dtm = (DefaultTableModel) this.jTblTotalMora.getModel();
        
        while (dtm.getRowCount() > 0) {
            dtm.removeRow(0);
        }
    }
    
    // Metodo para llenar la tabla mora 
    private void llenarTablaListaMora() throws SQLException {
        limpiarTabla();

        CDMora cdm = new CDMora();

        List<CLMora> miLista = cdm.ListaMora();
        DefaultTableModel temp = (DefaultTableModel) this.jTblListaMora.getModel();

        for (CLMora clm : miLista) {
            Object[] fila = new Object[8];
            fila[0] = clm.getIdContrato();
            fila[1] = clm.getCodAbonado();
            fila[2] = clm.getNombre();
            fila[3] = clm.getBloque();
            fila[4] = clm.getEtapa();
            fila[5] = clm.getCasa();
            fila[6] = clm.getMes();
            fila[7] = clm.getValor();
            temp.addRow(fila);
        };
    }
    
    // Metodo para llenar la tabla meses pagados
    private void ListaMesesPagados(int idContrato) throws SQLException{
        limpiarTablasMesesPagados();
        
        CDMora cdm = new CDMora();
        List<CLMora> miLista = cdm.ListaMesesPagados(idContrato);
        DefaultTableModel temp = (DefaultTableModel) this.jTblMesesPagados.getModel();
        
        miLista.stream().map((cl) -> {
            Object[] fila = new Object[2];
            fila[0] = cl.getIdMes2();
            fila[1] = cl.getMes();
            return fila;
        }).forEachOrdered((fila) -> {
            temp.addRow(fila);
        });
    }
    
    // Metodo para mostrar los meses morosos
    private void ListaMesesMorosos(int idContrato) throws SQLException{
        limpiarTablasMesesMora();
        
        CDMora cdm = new CDMora();
        List<CLMora> miLista = cdm.ListaMesesMoroso(idContrato);
        DefaultTableModel temp = (DefaultTableModel) this.jTblMesesMorosos.getModel();
        
        miLista.stream().map((cl) -> {
            Object[] fila = new Object[2];
            fila[0] = cl.getIdMes2();
            fila[1] = cl.getMes();
            return fila;
        }).forEachOrdered((fila) -> {
            temp.addRow(fila);
        });
    }
    
    // Metodo para mostrar la tabla el total mora
    private void ListaTotalMora(int idContrato) throws SQLException{
        limpiarTablasTotalMora();
        
        CDMora cdm = new CDMora();
        List<CLMora> miLista = cdm.ListaValorMesesMorosos(idContrato);
        DefaultTableModel temp = (DefaultTableModel) this.jTblTotalMora.getModel();
        
        miLista.stream().map((clm) -> {
            Object[] fila = new Object[3];
            fila[0] = clm.getIdContrato();
            fila[1] = clm.getMes();
            fila[2] = clm.getValor();
            return fila;
        }).forEachOrdered((fila) -> {
            temp.addRow(fila);
        });
    }
    
    // Metodo para mostrar abonados morosos por bloque
    private void mostrarMoraPorBloque(int bloque) throws SQLException{
        limpiarTabla();
        
        CDMora cda = new CDMora();
        List<CLMora> miLista = cda.mostrarMoraPorBloque(bloque);
        DefaultTableModel temp = (DefaultTableModel) this.jTblListaMora.getModel();
        
        miLista.stream().map((clm) -> {
            Object[] fila = new Object[8];
            fila[0] = clm.getIdContrato();
            fila[1] = clm.getCodAbonado();
            fila[2] = clm.getNombre();
            fila[3] = clm.getBloque();
            fila[4] = clm.getEtapa();
            fila[5] = clm.getCasa();
            fila[6] = clm.getMes();
            fila[7] = clm.getValor();
            return fila;
        }).forEachOrdered((fila) -> {
            temp.addRow(fila);
        });
    }
    
    // Metodo para mostrar abonador por etapa
    private void mostrarMoraPorEtapa(int etapa) throws SQLException{
        limpiarTabla();
        
        CDMora cda = new CDMora();
        List<CLMora> miLista = cda.mostrarMoraPorEtapa(etapa);
        DefaultTableModel temp = (DefaultTableModel) this.jTblListaMora.getModel();
        
        miLista.stream().map((clm) -> {
            Object[] fila = new Object[8];
            fila[0] = clm.getIdContrato();
            fila[1] = clm.getCodAbonado();
            fila[2] = clm.getNombre();
            fila[3] = clm.getBloque();
            fila[4] = clm.getEtapa();
            fila[5] = clm.getCasa();
            fila[6] = clm.getMes();
            fila[7] = clm.getValor();
            return fila;
        }).forEachOrdered((fila) -> {
            temp.addRow(fila);
        });
    }
    
    // Metodo para mostrar el total de todos los meses morosos
    private void totalMoraPagar(int idContrato) throws SQLException{
        CDMora cdm = new CDMora();
        CLMora clm = new CLMora();
        this.jTfToltaMora.setText(String.valueOf(cdm.mostrarMoraTotalPagar(idContrato)));
    }
    
    // Metodo para seleccionar la mora
    private void seleccionarMoraAbonado() throws ParseException {
        if (this.jTblListaMora.getSelectedRow() != -1) {
            this.jLblNumeroIdentidad.setText(String.valueOf(this.jTblListaMora.getValueAt(this.jTblListaMora.getSelectedRow(), 1)));
            this.jLblNombreCompleto.setText(String.valueOf(this.jTblListaMora.getValueAt(this.jTblListaMora.getSelectedRow(), 2)));
            this.jLblBloque.setText(String.valueOf(this.jTblListaMora.getValueAt(this.jTblListaMora.getSelectedRow(), 3)));
            this.jLblCasa.setText(String.valueOf(this.jTblListaMora.getValueAt(this.jTblListaMora.getSelectedRow(), 5)));
            this.jLblEtapa.setText(String.valueOf(this.jTblListaMora.getValueAt(this.jTblListaMora.getSelectedRow(), 4)));
            this.jLblVerMora.setVisible(true);
            
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
        jScrollPane5 = new javax.swing.JScrollPane();
        jList4 = new javax.swing.JList<>();
        jLblIdentificador63 = new javax.swing.JLabel();
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
        jSBListaMora = new javax.swing.JPanel();
        jLblIdentificador = new javax.swing.JLabel();
        jLblIdentificador1 = new javax.swing.JLabel();
        jSBDetalleMora = new javax.swing.JPanel();
        jLblIdentificador2 = new javax.swing.JLabel();
        jLblIdentificador3 = new javax.swing.JLabel();
        jSBContrato4 = new javax.swing.JPanel();
        jLblIdentificador9 = new javax.swing.JLabel();
        jLblIdentificador10 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLblIdentificador5 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jSBListadoContrato1 = new javax.swing.JPanel();
        jLblIdentificador4 = new javax.swing.JLabel();
        jLblIdentificador6 = new javax.swing.JLabel();
        jSBReporteMora = new javax.swing.JPanel();
        jLblIdentificador7 = new javax.swing.JLabel();
        jLblIdentificador8 = new javax.swing.JLabel();
        jTabContrato = new javax.swing.JTabbedPane();
        jPnlListadoPagos = new javax.swing.JPanel();
        jLblIdentificador11 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTblListaMora = new javax.swing.JTable();
        jRbBloque = new javax.swing.JRadioButton();
        jRbEtapa = new javax.swing.JRadioButton();
        jPnlBuscar = new javax.swing.JPanel();
        jTfBuscar = new javax.swing.JTextField();
        jBtnBuscar = new javax.swing.JLabel();
        jLblVerMora = new javax.swing.JLabel();
        jPnlReportes = new javax.swing.JPanel();
        jPnlDetalleMora = new javax.swing.JPanel();
        jLblIdentificador43 = new javax.swing.JLabel();
        jLblBuscarMora = new javax.swing.JLabel();
        jSeparatorY2 = new javax.swing.JPanel();
        jLblIdentificador45 = new javax.swing.JLabel();
        jLblNumeroIdentidad = new javax.swing.JLabel();
        jLblIdentificador47 = new javax.swing.JLabel();
        jLblNombreCompleto = new javax.swing.JLabel();
        jLblIdentificador49 = new javax.swing.JLabel();
        jLblBloque = new javax.swing.JLabel();
        jLblIdentificador51 = new javax.swing.JLabel();
        jLblCasa = new javax.swing.JLabel();
        jLblIdentificador53 = new javax.swing.JLabel();
        jLblEtapa = new javax.swing.JLabel();
        jLblIdentificador55 = new javax.swing.JLabel();
        jLblIdentificador56 = new javax.swing.JLabel();
        jLblIdentificador57 = new javax.swing.JLabel();
        jLblIdentificador59 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTblMesesPagados = new javax.swing.JTable();
        jLblIdentificador60 = new javax.swing.JLabel();
        jTfToltaMora = new javax.swing.JTextField();
        jLblGenerarPlanPago = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTblTotalMora = new javax.swing.JTable();
        jLblIdentificador62 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTblMesesMorosos = new javax.swing.JTable();

        jList4.setFont(new java.awt.Font("HelveticaNowDisplay Regular", 0, 14)); // NOI18N
        jList4.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane5.setViewportView(jList4);

        jLblIdentificador63.setBackground(new java.awt.Color(102, 102, 102));
        jLblIdentificador63.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 1, 20)); // NOI18N
        jLblIdentificador63.setText("Meses pagados");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

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
        jLblTitulo2.setText("Mora");
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

        jSBListaMora.setBackground(new java.awt.Color(52, 152, 219));
        jSBListaMora.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jSBListaMora.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jSBListaMoraMouseClicked(evt);
            }
        });

        jLblIdentificador.setBackground(new java.awt.Color(102, 102, 102));
        jLblIdentificador.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 1, 20)); // NOI18N
        jLblIdentificador.setForeground(new java.awt.Color(255, 255, 255));
        jLblIdentificador.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/tabla-blanco32.png"))); // NOI18N
        jLblIdentificador.setToolTipText("Contratos");

        jLblIdentificador1.setBackground(new java.awt.Color(102, 102, 102));
        jLblIdentificador1.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 1, 20)); // NOI18N
        jLblIdentificador1.setForeground(new java.awt.Color(255, 255, 255));
        jLblIdentificador1.setText("Lista de mora");

        javax.swing.GroupLayout jSBListaMoraLayout = new javax.swing.GroupLayout(jSBListaMora);
        jSBListaMora.setLayout(jSBListaMoraLayout);
        jSBListaMoraLayout.setHorizontalGroup(
            jSBListaMoraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jSBListaMoraLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLblIdentificador1, javax.swing.GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jLblIdentificador, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jSBListaMoraLayout.setVerticalGroup(
            jSBListaMoraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jSBListaMoraLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jSBListaMoraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLblIdentificador, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
                    .addComponent(jLblIdentificador1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jSideBar.add(jSBListaMora, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 225, 260, -1));

        jSBDetalleMora.setBackground(new java.awt.Color(52, 73, 94));
        jSBDetalleMora.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jSBDetalleMora.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jSBDetalleMoraMouseClicked(evt);
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
        jLblIdentificador3.setText("Detalle mora");

        javax.swing.GroupLayout jSBDetalleMoraLayout = new javax.swing.GroupLayout(jSBDetalleMora);
        jSBDetalleMora.setLayout(jSBDetalleMoraLayout);
        jSBDetalleMoraLayout.setHorizontalGroup(
            jSBDetalleMoraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jSBDetalleMoraLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLblIdentificador3, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLblIdentificador2)
                .addGap(10, 10, 10))
        );
        jSBDetalleMoraLayout.setVerticalGroup(
            jSBDetalleMoraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jSBDetalleMoraLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jSBDetalleMoraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLblIdentificador2, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
                    .addComponent(jLblIdentificador3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jSideBar.add(jSBDetalleMora, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 299, 260, -1));

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

        jSBListadoContrato1.setBackground(new java.awt.Color(52, 73, 94));
        jSBListadoContrato1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jLblIdentificador4.setBackground(new java.awt.Color(102, 102, 102));
        jLblIdentificador4.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 1, 20)); // NOI18N
        jLblIdentificador4.setForeground(new java.awt.Color(255, 255, 255));
        jLblIdentificador4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/pregunta-blanco32.png"))); // NOI18N
        jLblIdentificador4.setToolTipText("¿Qué se debo hacer aquí?");

        jLblIdentificador6.setBackground(new java.awt.Color(102, 102, 102));
        jLblIdentificador6.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 1, 20)); // NOI18N
        jLblIdentificador6.setForeground(new java.awt.Color(255, 255, 255));
        jLblIdentificador6.setText("Ayuda");

        javax.swing.GroupLayout jSBListadoContrato1Layout = new javax.swing.GroupLayout(jSBListadoContrato1);
        jSBListadoContrato1.setLayout(jSBListadoContrato1Layout);
        jSBListadoContrato1Layout.setHorizontalGroup(
            jSBListadoContrato1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jSBListadoContrato1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLblIdentificador6, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLblIdentificador4)
                .addGap(16, 16, 16))
        );
        jSBListadoContrato1Layout.setVerticalGroup(
            jSBListadoContrato1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jSBListadoContrato1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jSBListadoContrato1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLblIdentificador4, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
                    .addComponent(jLblIdentificador6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jSideBar.add(jSBListadoContrato1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 560, 260, -1));

        jSBReporteMora.setBackground(new java.awt.Color(52, 73, 94));
        jSBReporteMora.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jSBReporteMora.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jSBReporteMoraMouseClicked(evt);
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
        jLblIdentificador8.setText("Reportes de mora");

        javax.swing.GroupLayout jSBReporteMoraLayout = new javax.swing.GroupLayout(jSBReporteMora);
        jSBReporteMora.setLayout(jSBReporteMoraLayout);
        jSBReporteMoraLayout.setHorizontalGroup(
            jSBReporteMoraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jSBReporteMoraLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLblIdentificador8, javax.swing.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLblIdentificador7)
                .addGap(16, 16, 16))
        );
        jSBReporteMoraLayout.setVerticalGroup(
            jSBReporteMoraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jSBReporteMoraLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jSBReporteMoraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLblIdentificador7, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
                    .addComponent(jLblIdentificador8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jSideBar.add(jSBReporteMora, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 370, 260, -1));

        jSideBar1.setViewportView(jSideBar);

        jPanel1.add(jSideBar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(-210, 0, 270, 625));

        jTabContrato.setBackground(new java.awt.Color(255, 255, 255));
        jTabContrato.setTabPlacement(javax.swing.JTabbedPane.RIGHT);

        jPnlListadoPagos.setBackground(new java.awt.Color(255, 255, 255));

        jLblIdentificador11.setBackground(new java.awt.Color(255, 255, 255));
        jLblIdentificador11.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 1, 20)); // NOI18N
        jLblIdentificador11.setForeground(new java.awt.Color(0, 0, 0));
        jLblIdentificador11.setText("Ver mora por:");

        jTblListaMora.setFont(new java.awt.Font("HelveticaNowDisplay Regular", 0, 14)); // NOI18N
        jTblListaMora.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "IDContrato", "Número de identidad", "Nombre", "Bloque", "Etapa", "Casa", "Mes", "Cantidad"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTblListaMora.setRowHeight(30);
        jTblListaMora.getTableHeader().setReorderingAllowed(false);
        jTblListaMora.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTblListaMoraMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(jTblListaMora);

        jRbBloque.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(jRbBloque);
        jRbBloque.setFont(new java.awt.Font("HelveticaNowDisplay Light", 0, 14)); // NOI18N
        jRbBloque.setForeground(new java.awt.Color(0, 0, 0));
        jRbBloque.setSelected(true);
        jRbBloque.setText("Bloque");

        jRbEtapa.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(jRbEtapa);
        jRbEtapa.setFont(new java.awt.Font("HelveticaNowDisplay Light", 0, 14)); // NOI18N
        jRbEtapa.setForeground(new java.awt.Color(0, 0, 0));
        jRbEtapa.setText("Etapa");

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
                .addComponent(jTfBuscar)
                .addGap(18, 18, 18)
                .addComponent(jBtnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPnlBuscarLayout.setVerticalGroup(
            jPnlBuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTfBuscar)
            .addComponent(jBtnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jLblVerMora.setBackground(new java.awt.Color(102, 102, 102));
        jLblVerMora.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 0, 16)); // NOI18N
        jLblVerMora.setForeground(new java.awt.Color(41, 128, 185));
        jLblVerMora.setText("Ver mora abonado");
        jLblVerMora.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLblVerMora.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLblVerMoraMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPnlListadoPagosLayout = new javax.swing.GroupLayout(jPnlListadoPagos);
        jPnlListadoPagos.setLayout(jPnlListadoPagosLayout);
        jPnlListadoPagosLayout.setHorizontalGroup(
            jPnlListadoPagosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPnlListadoPagosLayout.createSequentialGroup()
                .addGroup(jPnlListadoPagosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPnlListadoPagosLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLblVerMora))
                    .addGroup(jPnlListadoPagosLayout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addGroup(jPnlListadoPagosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPnlListadoPagosLayout.createSequentialGroup()
                                .addComponent(jLblIdentificador11)
                                .addGap(18, 18, 18)
                                .addComponent(jRbBloque)
                                .addGap(18, 18, 18)
                                .addComponent(jRbEtapa)
                                .addGap(149, 149, 149)
                                .addComponent(jPnlBuscar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 874, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(79, Short.MAX_VALUE))
        );
        jPnlListadoPagosLayout.setVerticalGroup(
            jPnlListadoPagosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPnlListadoPagosLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(jPnlListadoPagosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPnlBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPnlListadoPagosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLblIdentificador11, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jRbBloque, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jRbEtapa, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jLblVerMora)
                .addGap(5, 5, 5)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        jTabContrato.addTab("tab4", jPnlListadoPagos);

        jPnlReportes.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPnlReportesLayout = new javax.swing.GroupLayout(jPnlReportes);
        jPnlReportes.setLayout(jPnlReportesLayout);
        jPnlReportesLayout.setHorizontalGroup(
            jPnlReportesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 988, Short.MAX_VALUE)
        );
        jPnlReportesLayout.setVerticalGroup(
            jPnlReportesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 566, Short.MAX_VALUE)
        );

        jTabContrato.addTab("tab3", jPnlReportes);

        jPnlDetalleMora.setBackground(new java.awt.Color(255, 255, 255));

        jLblIdentificador43.setBackground(new java.awt.Color(255, 255, 255));
        jLblIdentificador43.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 1, 20)); // NOI18N
        jLblIdentificador43.setForeground(new java.awt.Color(0, 0, 0));
        jLblIdentificador43.setText("Número de identidad");

        jLblBuscarMora.setBackground(new java.awt.Color(102, 102, 102));
        jLblBuscarMora.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 1, 20)); // NOI18N
        jLblBuscarMora.setForeground(new java.awt.Color(41, 128, 185));
        jLblBuscarMora.setText("Buscar mora aquí");
        jLblBuscarMora.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLblBuscarMora.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLblBuscarMoraMouseClicked(evt);
            }
        });

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

        jLblIdentificador45.setBackground(new java.awt.Color(255, 255, 255));
        jLblIdentificador45.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 1, 20)); // NOI18N
        jLblIdentificador45.setForeground(new java.awt.Color(0, 0, 0));
        jLblIdentificador45.setText("Información personal");

        jLblNumeroIdentidad.setBackground(new java.awt.Color(255, 255, 255));
        jLblNumeroIdentidad.setFont(new java.awt.Font("HelveticaNowDisplay Regular", 0, 18)); // NOI18N
        jLblNumeroIdentidad.setForeground(new java.awt.Color(0, 0, 0));
        jLblNumeroIdentidad.setText("Número de identidad");

        jLblIdentificador47.setBackground(new java.awt.Color(255, 255, 255));
        jLblIdentificador47.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 1, 20)); // NOI18N
        jLblIdentificador47.setForeground(new java.awt.Color(0, 0, 0));
        jLblIdentificador47.setText("Nombre completo");

        jLblNombreCompleto.setBackground(new java.awt.Color(255, 255, 255));
        jLblNombreCompleto.setFont(new java.awt.Font("HelveticaNowDisplay Regular", 0, 18)); // NOI18N
        jLblNombreCompleto.setForeground(new java.awt.Color(0, 0, 0));
        jLblNombreCompleto.setText("Nombre completo");

        jLblIdentificador49.setBackground(new java.awt.Color(255, 255, 255));
        jLblIdentificador49.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 1, 20)); // NOI18N
        jLblIdentificador49.setForeground(new java.awt.Color(0, 0, 0));
        jLblIdentificador49.setText("Bloque");

        jLblBloque.setBackground(new java.awt.Color(255, 255, 255));
        jLblBloque.setFont(new java.awt.Font("HelveticaNowDisplay Regular", 0, 18)); // NOI18N
        jLblBloque.setForeground(new java.awt.Color(0, 0, 0));
        jLblBloque.setText("Bloque");

        jLblIdentificador51.setBackground(new java.awt.Color(255, 255, 255));
        jLblIdentificador51.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 1, 20)); // NOI18N
        jLblIdentificador51.setForeground(new java.awt.Color(0, 0, 0));
        jLblIdentificador51.setText("Casa");

        jLblCasa.setBackground(new java.awt.Color(255, 255, 255));
        jLblCasa.setFont(new java.awt.Font("HelveticaNowDisplay Regular", 0, 18)); // NOI18N
        jLblCasa.setForeground(new java.awt.Color(0, 0, 0));
        jLblCasa.setText("Casa");

        jLblIdentificador53.setBackground(new java.awt.Color(255, 255, 255));
        jLblIdentificador53.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 1, 20)); // NOI18N
        jLblIdentificador53.setForeground(new java.awt.Color(0, 0, 0));
        jLblIdentificador53.setText("Etapa");

        jLblEtapa.setBackground(new java.awt.Color(255, 255, 255));
        jLblEtapa.setFont(new java.awt.Font("HelveticaNowDisplay Regular", 0, 18)); // NOI18N
        jLblEtapa.setForeground(new java.awt.Color(0, 0, 0));
        jLblEtapa.setText("Etapa");

        jLblIdentificador55.setBackground(new java.awt.Color(102, 102, 102));
        jLblIdentificador55.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 1, 20)); // NOI18N
        jLblIdentificador55.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/hombre-joven-64.png"))); // NOI18N
        jLblIdentificador55.setToolTipText("Masculino");

        jLblIdentificador56.setBackground(new java.awt.Color(255, 255, 255));
        jLblIdentificador56.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 1, 20)); // NOI18N
        jLblIdentificador56.setForeground(new java.awt.Color(0, 0, 0));
        jLblIdentificador56.setText("Resumen de mora");

        jLblIdentificador57.setBackground(new java.awt.Color(255, 255, 255));
        jLblIdentificador57.setFont(new java.awt.Font("HelveticaNowDisplay Medium", 0, 20)); // NOI18N
        jLblIdentificador57.setForeground(new java.awt.Color(0, 0, 0));
        jLblIdentificador57.setText("Meses pagados");

        jLblIdentificador59.setBackground(new java.awt.Color(255, 255, 255));
        jLblIdentificador59.setFont(new java.awt.Font("HelveticaNowDisplay Medium", 0, 20)); // NOI18N
        jLblIdentificador59.setForeground(new java.awt.Color(0, 0, 0));
        jLblIdentificador59.setText("Mora total");

        jTblMesesPagados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "#", "Mes"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTblMesesPagados.getTableHeader().setReorderingAllowed(false);
        jScrollPane3.setViewportView(jTblMesesPagados);

        jLblIdentificador60.setBackground(new java.awt.Color(255, 255, 255));
        jLblIdentificador60.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 1, 20)); // NOI18N
        jLblIdentificador60.setForeground(new java.awt.Color(0, 0, 0));
        jLblIdentificador60.setText("Total a pagar: ");

        jTfToltaMora.setEditable(false);
        jTfToltaMora.setBackground(new java.awt.Color(255, 255, 255));
        jTfToltaMora.setFont(new java.awt.Font("HelveticaNowDisplay Regular", 0, 16)); // NOI18N
        jTfToltaMora.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTfToltaMora.setSelectionColor(new java.awt.Color(0, 153, 153));

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

        jTblTotalMora.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Num Contrato", "Mes", "Valor"
            }
        ));
        jTblTotalMora.getTableHeader().setReorderingAllowed(false);
        jScrollPane4.setViewportView(jTblTotalMora);

        jLblIdentificador62.setBackground(new java.awt.Color(255, 255, 255));
        jLblIdentificador62.setFont(new java.awt.Font("HelveticaNowDisplay Medium", 0, 20)); // NOI18N
        jLblIdentificador62.setForeground(new java.awt.Color(0, 0, 0));
        jLblIdentificador62.setText("Meses adeudados");

        jTblMesesMorosos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "#", "Mes"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTblMesesMorosos.getTableHeader().setReorderingAllowed(false);
        jScrollPane7.setViewportView(jTblMesesMorosos);

        javax.swing.GroupLayout jPnlDetalleMoraLayout = new javax.swing.GroupLayout(jPnlDetalleMora);
        jPnlDetalleMora.setLayout(jPnlDetalleMoraLayout);
        jPnlDetalleMoraLayout.setHorizontalGroup(
            jPnlDetalleMoraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPnlDetalleMoraLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(jPnlDetalleMoraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLblBuscarMora, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLblIdentificador45, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLblIdentificador43, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLblNumeroIdentidad, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLblIdentificador47, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLblNombreCompleto, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLblIdentificador49, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLblBloque, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLblIdentificador51, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLblCasa, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLblIdentificador53, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLblEtapa, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPnlDetalleMoraLayout.createSequentialGroup()
                        .addComponent(jLblIdentificador55)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(40, 40, 40)
                .addComponent(jSeparatorY2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addGroup(jPnlDetalleMoraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPnlDetalleMoraLayout.createSequentialGroup()
                        .addComponent(jLblIdentificador56, javax.swing.GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE)
                        .addGap(250, 250, 250))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPnlDetalleMoraLayout.createSequentialGroup()
                        .addGroup(jPnlDetalleMoraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPnlDetalleMoraLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLblIdentificador60)
                                .addGap(18, 18, 18)
                                .addComponent(jTfToltaMora, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPnlDetalleMoraLayout.createSequentialGroup()
                                .addGroup(jPnlDetalleMoraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPnlDetalleMoraLayout.createSequentialGroup()
                                        .addGroup(jPnlDetalleMoraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                            .addComponent(jLblIdentificador57, javax.swing.GroupLayout.DEFAULT_SIZE, 285, Short.MAX_VALUE))
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addComponent(jLblIdentificador59, javax.swing.GroupLayout.DEFAULT_SIZE, 332, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPnlDetalleMoraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLblIdentificador62, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLblGenerarPlanPago, javax.swing.GroupLayout.Alignment.TRAILING)))
                            .addComponent(jScrollPane4))
                        .addGap(64, 64, 64))))
        );
        jPnlDetalleMoraLayout.setVerticalGroup(
            jPnlDetalleMoraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPnlDetalleMoraLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(jPnlDetalleMoraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPnlDetalleMoraLayout.createSequentialGroup()
                        .addComponent(jLblIdentificador56, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPnlDetalleMoraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPnlDetalleMoraLayout.createSequentialGroup()
                                .addComponent(jLblIdentificador57, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPnlDetalleMoraLayout.createSequentialGroup()
                                .addComponent(jLblIdentificador62, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPnlDetalleMoraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLblGenerarPlanPago, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLblIdentificador59, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPnlDetalleMoraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLblIdentificador60, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTfToltaMora, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPnlDetalleMoraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jSeparatorY2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPnlDetalleMoraLayout.createSequentialGroup()
                            .addComponent(jLblIdentificador45, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLblBuscarMora, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(jLblIdentificador55)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLblIdentificador43, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLblNumeroIdentidad, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(jLblIdentificador47, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLblNombreCompleto, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(jLblIdentificador49, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLblBloque, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(jLblIdentificador51, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLblCasa, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(jLblIdentificador53, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLblEtapa, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(35, Short.MAX_VALUE))
        );

        jTabContrato.addTab("tab2", jPnlDetalleMora);

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

    private void jSBDetalleMoraMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jSBDetalleMoraMouseClicked
        // TODO add your handling code here:
        this.sideBar.jTextAreaXLeft(0, -210, 5, 5, jSideBar1);
        this.jTabContrato.setSelectedIndex(2);
        this.jBtnSideBar1.setVisible(false);
        this.jBtnSideBar.setVisible(true);
        this.jSBDetalleMora.setBackground(celeste);
        this.jSBListaMora.setBackground(azul);
        this.jSBReporteMora.setBackground(azul);
    }//GEN-LAST:event_jSBDetalleMoraMouseClicked

    private void jSBReporteMoraMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jSBReporteMoraMouseClicked
        // TODO add your handling code here:
        this.sideBar.jTextAreaXLeft(0, -210, 5, 5, jSideBar1);
        this.jTabContrato.setSelectedIndex(1);
        this.jBtnSideBar1.setVisible(false);
        this.jBtnSideBar.setVisible(true);
        this.jSBDetalleMora.setBackground(azul);
        this.jSBListaMora.setBackground(azul);
        this.jSBReporteMora.setBackground(celeste);
    }//GEN-LAST:event_jSBReporteMoraMouseClicked

    private void jSBListaMoraMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jSBListaMoraMouseClicked
        // TODO add your handling code here:
        this.sideBar.jTextAreaXLeft(0, -210, 5, 5, jSideBar1);
        this.jTabContrato.setSelectedIndex(0);
        this.jBtnSideBar1.setVisible(false);
        this.jBtnSideBar.setVisible(true);
        this.jSBDetalleMora.setBackground(azul);
        this.jSBListaMora.setBackground(celeste);
        this.jSBReporteMora.setBackground(azul);
    }//GEN-LAST:event_jSBListaMoraMouseClicked

    private void jTblListaMoraMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTblListaMoraMouseClicked
        try {
            // TODO add your handling code here:
            String idContrato;
            idContrato = (String.valueOf(this.jTblListaMora.getValueAt(this.jTblListaMora.getSelectedRow(), 0)));
            ListaMesesPagados(parseInt(idContrato));
            ListaMesesMorosos(parseInt(idContrato));
            ListaTotalMora(parseInt(idContrato));
            totalMoraPagar(parseInt(idContrato));
            seleccionarMoraAbonado();
        } catch (ParseException ex) {
            Logger.getLogger(JFraMora.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(JFraMora.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jTblListaMoraMouseClicked

    private void jLblVerMoraMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLblVerMoraMouseClicked
        // TODO add your handling code here:
        this.sideBar.jTextAreaXLeft(0, -210, 5, 5, jSideBar1);
        this.jTabContrato.setSelectedIndex(2);
        this.jBtnSideBar1.setVisible(false);
        this.jBtnSideBar.setVisible(true);
        this.jSBDetalleMora.setBackground(celeste);
        this.jSBListaMora.setBackground(azul);
        this.jSBReporteMora.setBackground(azul);
    }//GEN-LAST:event_jLblVerMoraMouseClicked

    private void jTfBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTfBuscarKeyReleased
        // TODO add your handling code here:
        try {
            if(this.jRbBloque.isSelected()){
                if(this.jTfBuscar.getText().isEmpty()){
                    llenarTablaListaMora();
                }else{
                    String bloque;
                    bloque = this.jTfBuscar.getText();
                    mostrarMoraPorBloque(parseInt(bloque));
                }
                }else if(this.jRbEtapa.isSelected()){
                    String identidad;
                    identidad = this.jTfBuscar.getText();
                    mostrarMoraPorEtapa(parseInt(identidad));
                }
            
        } catch (SQLException ex) {
            Logger.getLogger(JFraAbonados.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jTfBuscarKeyReleased

    private void jLblBuscarMoraMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLblBuscarMoraMouseClicked
        this.sideBar.jTextAreaXLeft(0, -210, 5, 5, jSideBar1);
        this.jTabContrato.setSelectedIndex(0);
        this.jBtnSideBar1.setVisible(false);
        this.jBtnSideBar.setVisible(true);
        this.jSBDetalleMora.setBackground(azul);
        this.jSBListaMora.setBackground(celeste);
        this.jSBReporteMora.setBackground(azul);
    }//GEN-LAST:event_jLblBuscarMoraMouseClicked

    private void jLblMinimizar2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLblMinimizar2MouseClicked
        this.setState(Frame.ICONIFIED);
    }//GEN-LAST:event_jLblMinimizar2MouseClicked

    private void jLblSalirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLblSalirMouseClicked
        this.dispose();
    }//GEN-LAST:event_jLblSalirMouseClicked

    private void jLblGenerarPlanPagoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLblGenerarPlanPagoMouseClicked
        JFraPlanPago jfpp;
        try {
            jfpp = new JFraPlanPago();
            this.dispose();
            jfpp.mostratVentana(true, jLblNumeroIdentidad.getText(), jLblNombreCompleto.getText(), jLblBloque.getText(), jLblCasa.getText());
        } catch (SQLException ex) {
            Logger.getLogger(JFraMora.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }//GEN-LAST:event_jLblGenerarPlanPagoMouseClicked

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
            java.util.logging.Logger.getLogger(JFraMora.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFraMora.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFraMora.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFraMora.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new JFraMora().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(JFraMora.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jBtnBuscar;
    private javax.swing.JLabel jBtnSideBar;
    private javax.swing.JLabel jBtnSideBar1;
    private javax.swing.JLabel jLblBloque;
    private javax.swing.JLabel jLblBuscarMora;
    private javax.swing.JLabel jLblCasa;
    private javax.swing.JLabel jLblEtapa;
    private javax.swing.JLabel jLblGenerarPlanPago;
    private javax.swing.JLabel jLblIdentificador;
    private javax.swing.JLabel jLblIdentificador1;
    private javax.swing.JLabel jLblIdentificador10;
    private javax.swing.JLabel jLblIdentificador11;
    private javax.swing.JLabel jLblIdentificador2;
    private javax.swing.JLabel jLblIdentificador3;
    private javax.swing.JLabel jLblIdentificador4;
    private javax.swing.JLabel jLblIdentificador43;
    private javax.swing.JLabel jLblIdentificador45;
    private javax.swing.JLabel jLblIdentificador47;
    private javax.swing.JLabel jLblIdentificador49;
    private javax.swing.JLabel jLblIdentificador5;
    private javax.swing.JLabel jLblIdentificador51;
    private javax.swing.JLabel jLblIdentificador53;
    private javax.swing.JLabel jLblIdentificador55;
    private javax.swing.JLabel jLblIdentificador56;
    private javax.swing.JLabel jLblIdentificador57;
    private javax.swing.JLabel jLblIdentificador59;
    private javax.swing.JLabel jLblIdentificador6;
    private javax.swing.JLabel jLblIdentificador60;
    private javax.swing.JLabel jLblIdentificador62;
    private javax.swing.JLabel jLblIdentificador63;
    private javax.swing.JLabel jLblIdentificador7;
    private javax.swing.JLabel jLblIdentificador8;
    private javax.swing.JLabel jLblIdentificador9;
    private javax.swing.JLabel jLblMinimizar2;
    private javax.swing.JLabel jLblNombreCompleto;
    private javax.swing.JLabel jLblNumeroIdentidad;
    private javax.swing.JLabel jLblSalir;
    private javax.swing.JLabel jLblTitulo2;
    private javax.swing.JLabel jLblVerMora;
    private javax.swing.JList<String> jList4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPnlBuscar;
    private javax.swing.JPanel jPnlDetalleMora;
    private javax.swing.JPanel jPnlListadoPagos;
    private javax.swing.JPanel jPnlReportes;
    private javax.swing.JRadioButton jRbBloque;
    private javax.swing.JRadioButton jRbEtapa;
    private javax.swing.JPanel jSBContrato4;
    private javax.swing.JPanel jSBDetalleMora;
    private javax.swing.JPanel jSBListaMora;
    private javax.swing.JPanel jSBListadoContrato1;
    private javax.swing.JPanel jSBReporteMora;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JPanel jSeparatorY2;
    private javax.swing.JPanel jSideBar;
    private javax.swing.JScrollPane jSideBar1;
    private javax.swing.JTabbedPane jTabContrato;
    private javax.swing.JTable jTblListaMora;
    private javax.swing.JTable jTblMesesMorosos;
    private javax.swing.JTable jTblMesesPagados;
    private javax.swing.JTable jTblTotalMora;
    private javax.swing.JTextField jTfBuscar;
    private javax.swing.JTextField jTfToltaMora;
    // End of variables declaration//GEN-END:variables
}
