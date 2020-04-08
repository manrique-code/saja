/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capapresentacion;

import capalogica.CLLogin;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

/**
 *
 * @author Manrique
 */
public class JFraMenu extends javax.swing.JFrame {

    /**
     * Creates new form JFraMenu
     */
    
    Color c = new Color(241, 242, 246);
    
    public JFraMenu() {
        initComponents();
        this.setLocationRelativeTo(null);
        
        // Cambiar de color al JFrame
        this.getContentPane().setBackground(c);
        
        // Maximizar el JFrame
        this.setExtendedState(this.getExtendedState() | JFrame.MAXIMIZED_BOTH);
        
        // Cambiar el color del Scroll del SideBar
        this.jSideBarMenu.getViewport().setBackground(Color.getColor("223, 228, 234"));
        
        // Cambiar el color del Scroll del Menú
        this.jScrollMenu.getViewport().setBackground(c);

        // Mostrar el nombre del usuario
        this.jLblUsuario.setText(cll.getNombreUsuario());
    }
    
    // Instancias de los formularios de SAJA
    JFraAbonados jfAbonados = new JFraAbonados();
    JFraCobros jfCbros = new JFraCobros();
    JFraCodos jfCodos = new JFraCodos();
    JFraContratos jfContratos = new JFraContratos();
    JFraCortes jfCortes = new JFraCortes();
    JFraEgresos jfEgresos = new JFraEgresos();
    JFraLogin jfLogIn = new JFraLogin();
    JFraMora jfMora = new JFraMora();
    JFraPlanPago jfPlanPago = new JFraPlanPago();
    
    // Cambiar de icono al pasar el mouse
    BufferedImage image;
    String pathImagenes = "C:\\Users\\Manrique\\Desktop\\Desarrollo de Software\\3 Parcial\\SAJA JAVA\\SAJA JAVA\\SAJA\\src\\img\\";
    
    // Instancias de la capa lógica
    CLLogin cll = new CLLogin();
    
    public void cambiarIconoAbonado(boolean mouseEncima){
        if(mouseEncima){
            try {
                image = ImageIO.read(new File(pathImagenes + "abonadoSeleccionado.png"));
                ImageIcon i = new ImageIcon(image);
                this.jAbonados.setIcon(i);

            } catch (IOException ex) {
                Logger.getLogger(JFraMenu.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            try {
                image = ImageIO.read(new File(pathImagenes + "abonadosSAJA.png"));

                ImageIcon i = new ImageIcon(image);

                this.jAbonados.setIcon(i);
            } catch (IOException ex) {
                Logger.getLogger(JFraMenu.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void cambiarIconoContrato(boolean mouseEncima){
        if(mouseEncima){
            try {
                image = ImageIO.read(new File(pathImagenes + "contratosSeleccionado.png"));
                ImageIcon i = new ImageIcon(image);
                this.jContratos.setIcon(i);

            } catch (IOException ex) {
                Logger.getLogger(JFraMenu.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        } else {
            try {
                image = ImageIO.read(new File(pathImagenes + "contratos.png"));
                ImageIcon i = new ImageIcon(image);
                this.jContratos.setIcon(i);
                
            } catch (IOException ex) {
                Logger.getLogger(JFraMenu.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    } 
    
    public void cambiarIconoPagos(boolean mouseEncima){
        if(mouseEncima){
            try {
                image = ImageIO.read(new File(pathImagenes + "pagoSeleccionado.png"));
                ImageIcon i = new ImageIcon(image);
                this.jPago.setIcon(i);

            } catch (IOException ex) {
                Logger.getLogger(JFraMenu.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        } else {
            try {
                image = ImageIO.read(new File(pathImagenes + "pagos.png"));
                ImageIcon i = new ImageIcon(image);
                this.jPago.setIcon(i);
                
            } catch (IOException ex) {
                Logger.getLogger(JFraMenu.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    } 
    
    public void cambiarIconoMora(boolean mouseEncima){
        if(mouseEncima){
            try {
                image = ImageIO.read(new File(pathImagenes + "moraSeleccionado.png"));
                ImageIcon i = new ImageIcon(image);
                this.jMoras.setIcon(i);

            } catch (IOException ex) {
                Logger.getLogger(JFraMenu.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        } else {
            try {
                image = ImageIO.read(new File(pathImagenes + "moras.png"));
                ImageIcon i = new ImageIcon(image);
                this.jMoras.setIcon(i);
                
            } catch (IOException ex) {
                Logger.getLogger(JFraMenu.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void cambiarIconoPlanPago(boolean mouseEncima){
        if(mouseEncima){
            try {
                image = ImageIO.read(new File(pathImagenes + "planpagoSeleccionado.png"));
                ImageIcon i = new ImageIcon(image);
                this.jPlanPagos.setIcon(i);

            } catch (IOException ex) {
                Logger.getLogger(JFraMenu.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        } else {
            try {
                image = ImageIO.read(new File(pathImagenes + "planpagos.png"));
                ImageIcon i = new ImageIcon(image);
                this.jPlanPagos.setIcon(i);
                
            } catch (IOException ex) {
                Logger.getLogger(JFraMenu.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    } 
    
    public void cambiarIconoCortes(boolean mouseEncima){
        if(mouseEncima){
            try {
                image = ImageIO.read(new File(pathImagenes + "corteSeleccionado.png"));
                ImageIcon i = new ImageIcon(image);
                this.jCortes.setIcon(i);

            } catch (IOException ex) {
                Logger.getLogger(JFraMenu.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        } else {
            try {
                image = ImageIO.read(new File(pathImagenes + "cortes.png"));
                ImageIcon i = new ImageIcon(image);
                this.jCortes.setIcon(i);
                
            } catch (IOException ex) {
                Logger.getLogger(JFraMenu.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    } 

    public void cambiarIconoEgreso(boolean mouseEncima){
        if(mouseEncima){
            try {
                image = ImageIO.read(new File(pathImagenes + "egresoSeleccionado.png"));
                ImageIcon i = new ImageIcon(image);
                this.jEgresos.setIcon(i);

            } catch (IOException ex) {
                Logger.getLogger(JFraMenu.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        } else {
            try {
                image = ImageIO.read(new File(pathImagenes + "egresos.png"));
                ImageIcon i = new ImageIcon(image);
                this.jEgresos.setIcon(i);
                
            } catch (IOException ex) {
                Logger.getLogger(JFraMenu.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void cambiarIconoCodos(boolean mouseEncima){
        if(mouseEncima){
            try {
                image = ImageIO.read(new File(pathImagenes + "codoSeleccionado.png"));
                ImageIcon i = new ImageIcon(image);
                this.jCodos.setIcon(i);

            } catch (IOException ex) {
                Logger.getLogger(JFraMenu.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        } else {
            try {
                image = ImageIO.read(new File(pathImagenes + "codos.png"));
                ImageIcon i = new ImageIcon(image);
                this.jCodos.setIcon(i);
                
            } catch (IOException ex) {
                Logger.getLogger(JFraMenu.class.getName()).log(Level.SEVERE, null, ex);
            }
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

        jSideBarMenu = new javax.swing.JScrollPane();
        jPanel3 = new javax.swing.JPanel();
        jLblIdentificador16 = new javax.swing.JLabel();
        jLblUsuario = new javax.swing.JLabel();
        jScrollMenu = new javax.swing.JScrollPane();
        jPnlMenu = new javax.swing.JPanel();
        jLblIdentificador15 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jAbonados = new javax.swing.JLabel();
        jContratos = new javax.swing.JLabel();
        jPago = new javax.swing.JLabel();
        jMoras = new javax.swing.JLabel();
        jPlanPagos = new javax.swing.JLabel();
        jCortes = new javax.swing.JLabel();
        jEgresos = new javax.swing.JLabel();
        jCodos = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLblTitulo2 = new javax.swing.JLabel();
        jLblTitulo4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jSideBarMenu.setBackground(new java.awt.Color(255, 255, 255));
        jSideBarMenu.setBorder(null);
        jSideBarMenu.setForeground(new java.awt.Color(0, 0, 0));
        jSideBarMenu.setHorizontalScrollBar(null);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setPreferredSize(new java.awt.Dimension(250, 605));

        jLblIdentificador16.setBackground(new java.awt.Color(102, 102, 102));
        jLblIdentificador16.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 1, 24)); // NOI18N
        jLblIdentificador16.setForeground(new java.awt.Color(0, 0, 0));
        jLblIdentificador16.setText("Bienvenido(a)");

        jLblUsuario.setBackground(new java.awt.Color(102, 102, 102));
        jLblUsuario.setFont(new java.awt.Font("HelveticaNowDisplay Medium", 0, 24)); // NOI18N
        jLblUsuario.setForeground(new java.awt.Color(0, 0, 0));
        jLblUsuario.setText("Usuario");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLblUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLblIdentificador16, javax.swing.GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE))
                .addContainerGap(14, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jLblIdentificador16, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLblUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(538, Short.MAX_VALUE))
        );

        jSideBarMenu.setViewportView(jPanel3);

        jScrollMenu.setBorder(null);
        jScrollMenu.setHorizontalScrollBar(null);

        jPnlMenu.setBackground(new java.awt.Color(241, 242, 246));

        jLblIdentificador15.setBackground(new java.awt.Color(102, 102, 102));
        jLblIdentificador15.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 1, 24)); // NOI18N
        jLblIdentificador15.setForeground(new java.awt.Color(0, 0, 0));
        jLblIdentificador15.setText("Menú");

        jPanel2.setBackground(new java.awt.Color(102, 102, 102));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 820, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1, Short.MAX_VALUE)
        );

        jAbonados.setBackground(new java.awt.Color(102, 102, 102));
        jAbonados.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 1, 24)); // NOI18N
        jAbonados.setForeground(new java.awt.Color(0, 0, 0));
        jAbonados.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/abonadosSAJA.png"))); // NOI18N
        jAbonados.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jAbonados.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jAbonadosMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jAbonadosMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jAbonadosMouseExited(evt);
            }
        });

        jContratos.setBackground(new java.awt.Color(102, 102, 102));
        jContratos.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 1, 24)); // NOI18N
        jContratos.setForeground(new java.awt.Color(0, 0, 0));
        jContratos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/contratos.png"))); // NOI18N
        jContratos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jContratos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jContratosMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jContratosMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jContratosMouseExited(evt);
            }
        });

        jPago.setBackground(new java.awt.Color(102, 102, 102));
        jPago.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 1, 24)); // NOI18N
        jPago.setForeground(new java.awt.Color(0, 0, 0));
        jPago.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/pagos.png"))); // NOI18N
        jPago.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPago.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPagoMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPagoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPagoMouseExited(evt);
            }
        });

        jMoras.setBackground(new java.awt.Color(102, 102, 102));
        jMoras.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 1, 24)); // NOI18N
        jMoras.setForeground(new java.awt.Color(0, 0, 0));
        jMoras.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/moras.png"))); // NOI18N
        jMoras.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jMoras.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMorasMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jMorasMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jMorasMouseExited(evt);
            }
        });

        jPlanPagos.setBackground(new java.awt.Color(102, 102, 102));
        jPlanPagos.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 1, 24)); // NOI18N
        jPlanPagos.setForeground(new java.awt.Color(0, 0, 0));
        jPlanPagos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/planpagos.png"))); // NOI18N
        jPlanPagos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPlanPagos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPlanPagosMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPlanPagosMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPlanPagosMouseExited(evt);
            }
        });

        jCortes.setBackground(new java.awt.Color(102, 102, 102));
        jCortes.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 1, 24)); // NOI18N
        jCortes.setForeground(new java.awt.Color(0, 0, 0));
        jCortes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/cortes.png"))); // NOI18N
        jCortes.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jCortes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jCortesMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jCortesMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jCortesMouseExited(evt);
            }
        });

        jEgresos.setBackground(new java.awt.Color(102, 102, 102));
        jEgresos.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 1, 24)); // NOI18N
        jEgresos.setForeground(new java.awt.Color(0, 0, 0));
        jEgresos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/egresos.png"))); // NOI18N
        jEgresos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jEgresos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jEgresosMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jEgresosMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jEgresosMouseExited(evt);
            }
        });

        jCodos.setBackground(new java.awt.Color(102, 102, 102));
        jCodos.setFont(new java.awt.Font("HelveticaNowDisplay Bold", 1, 24)); // NOI18N
        jCodos.setForeground(new java.awt.Color(0, 0, 0));
        jCodos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/codos.png"))); // NOI18N
        jCodos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jCodos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jCodosMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jCodosMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jCodosMouseExited(evt);
            }
        });

        javax.swing.GroupLayout jPnlMenuLayout = new javax.swing.GroupLayout(jPnlMenu);
        jPnlMenu.setLayout(jPnlMenuLayout);
        jPnlMenuLayout.setHorizontalGroup(
            jPnlMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPnlMenuLayout.createSequentialGroup()
                .addGroup(jPnlMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPnlMenuLayout.createSequentialGroup()
                        .addComponent(jPlanPagos)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCortes)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jEgresos)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCodos))
                    .addGroup(jPnlMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPnlMenuLayout.createSequentialGroup()
                            .addGap(50, 50, 50)
                            .addComponent(jLblIdentificador15)
                            .addGap(40, 40, 40)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPnlMenuLayout.createSequentialGroup()
                            .addGap(40, 40, 40)
                            .addComponent(jAbonados)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jContratos)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jPago)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jMoras))))
                .addContainerGap(268, Short.MAX_VALUE))
        );
        jPnlMenuLayout.setVerticalGroup(
            jPnlMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPnlMenuLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(jPnlMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLblIdentificador15, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPnlMenuLayout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPnlMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jAbonados)
                    .addComponent(jContratos)
                    .addComponent(jPago)
                    .addComponent(jMoras))
                .addGap(18, 18, 18)
                .addGroup(jPnlMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPlanPagos)
                    .addComponent(jCortes)
                    .addComponent(jEgresos)
                    .addComponent(jCodos))
                .addContainerGap(611, Short.MAX_VALUE))
        );

        jScrollMenu.setViewportView(jPnlMenu);

        jPanel1.setBackground(new java.awt.Color(41, 128, 185));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLblTitulo2.setFont(new java.awt.Font("HelveticaNowDisplay ExtraBold", 0, 24)); // NOI18N
        jLblTitulo2.setForeground(new java.awt.Color(255, 255, 255));
        jLblTitulo2.setText("7 de abril del 2020");
        jPanel1.add(jLblTitulo2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 0, 220, 60));

        jLblTitulo4.setFont(new java.awt.Font("HelveticaNowDisplay ExtraBold", 0, 24)); // NOI18N
        jLblTitulo4.setForeground(new java.awt.Color(255, 255, 255));
        jLblTitulo4.setText("Sistema Administrador de Juntas de Agua");
        jPanel1.add(jLblTitulo4, new org.netbeans.lib.awtextra.AbsoluteConstraints(44, 0, 480, 60));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jSideBarMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jSideBarMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 654, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jAbonadosMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jAbonadosMouseEntered
        this.cambiarIconoAbonado(true);
    }//GEN-LAST:event_jAbonadosMouseEntered

    private void jAbonadosMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jAbonadosMouseExited
        this.cambiarIconoAbonado(false);
    }//GEN-LAST:event_jAbonadosMouseExited

    private void jAbonadosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jAbonadosMouseClicked
        // TODO add your handling code here:
        
        jfAbonados.setVisible(true);
    }//GEN-LAST:event_jAbonadosMouseClicked

    private void jPlanPagosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPlanPagosMouseClicked
        this.jfPlanPago.setVisible(true);
    }//GEN-LAST:event_jPlanPagosMouseClicked

    private void jPlanPagosMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPlanPagosMouseEntered
        this.cambiarIconoPlanPago(true);
    }//GEN-LAST:event_jPlanPagosMouseEntered

    private void jPlanPagosMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPlanPagosMouseExited
        this.cambiarIconoPlanPago(false);
    }//GEN-LAST:event_jPlanPagosMouseExited

    private void jContratosMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jContratosMouseEntered
        this.cambiarIconoContrato(true);
    }//GEN-LAST:event_jContratosMouseEntered

    private void jContratosMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jContratosMouseExited
        this.cambiarIconoContrato(false);
    }//GEN-LAST:event_jContratosMouseExited

    private void jPagoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPagoMouseEntered
        this.cambiarIconoPagos(true);
    }//GEN-LAST:event_jPagoMouseEntered

    private void jPagoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPagoMouseExited
        this.cambiarIconoPagos(false);
    }//GEN-LAST:event_jPagoMouseExited

    private void jContratosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jContratosMouseClicked
        this.jfContratos.setVisible(true);
    }//GEN-LAST:event_jContratosMouseClicked

    private void jPagoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPagoMouseClicked
        this.jfCbros.setVisible(true);
    }//GEN-LAST:event_jPagoMouseClicked

    private void jMorasMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMorasMouseEntered
        this.cambiarIconoMora(true);
    }//GEN-LAST:event_jMorasMouseEntered

    private void jMorasMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMorasMouseExited
        this.cambiarIconoMora(false);
    }//GEN-LAST:event_jMorasMouseExited

    private void jMorasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMorasMouseClicked
        this.jfMora.setVisible(true);
    }//GEN-LAST:event_jMorasMouseClicked

    private void jCortesMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jCortesMouseEntered
        this.cambiarIconoCortes(true);
    }//GEN-LAST:event_jCortesMouseEntered

    private void jCortesMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jCortesMouseExited
        this.cambiarIconoCortes(false);
    }//GEN-LAST:event_jCortesMouseExited

    private void jCortesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jCortesMouseClicked
        this.jfCortes.setVisible(true);
    }//GEN-LAST:event_jCortesMouseClicked

    private void jEgresosMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jEgresosMouseEntered
        this.cambiarIconoEgreso(true);
    }//GEN-LAST:event_jEgresosMouseEntered

    private void jEgresosMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jEgresosMouseExited
        this.cambiarIconoEgreso(false);
    }//GEN-LAST:event_jEgresosMouseExited

    private void jEgresosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jEgresosMouseClicked
        this.jfEgresos.setVisible(true);
    }//GEN-LAST:event_jEgresosMouseClicked

    private void jCodosMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jCodosMouseEntered
        this.cambiarIconoCodos(true);
    }//GEN-LAST:event_jCodosMouseEntered

    private void jCodosMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jCodosMouseExited
        this.cambiarIconoCodos(false);
    }//GEN-LAST:event_jCodosMouseExited

    private void jCodosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jCodosMouseClicked
        this.jCodos.setVisible(true);
    }//GEN-LAST:event_jCodosMouseClicked

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
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(JFraMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFraMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFraMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFraMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JFraMenu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jAbonados;
    private javax.swing.JLabel jCodos;
    private javax.swing.JLabel jContratos;
    private javax.swing.JLabel jCortes;
    private javax.swing.JLabel jEgresos;
    private javax.swing.JLabel jLblIdentificador15;
    private javax.swing.JLabel jLblIdentificador16;
    private javax.swing.JLabel jLblTitulo2;
    private javax.swing.JLabel jLblTitulo4;
    private javax.swing.JLabel jLblUsuario;
    private javax.swing.JLabel jMoras;
    private javax.swing.JLabel jPago;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel jPlanPagos;
    private javax.swing.JPanel jPnlMenu;
    private javax.swing.JScrollPane jScrollMenu;
    private javax.swing.JScrollPane jSideBarMenu;
    // End of variables declaration//GEN-END:variables
}
