/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capapresentacion;
import capadatos.CDLogin;
import capalogica.CLLogin;
import com.placeholder.PlaceHolder;
import java.awt.Color;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
///Se importo una libreria placeHolder para el diseño
/**
 *
 * @author Manrique
 */
public class JFraLogin extends javax.swing.JFrame {

    /**
     * Creates new form JFraLogin
     * pego
     */
    public JFraLogin() {
        initComponents();
        this.setLocationRelativeTo(null);
        PlaceHolder u = new PlaceHolder(jTfUsuario, 
                                        new Color(55,73,87),
                                        Color.BLACK,
                                        "Usuario",
                                        false,
                                        "Gotham XLight",
                                        18);
        
        PlaceHolder c = new PlaceHolder(jPFContraseña, 
                                        new Color(55,73,87),
                                        Color.BLACK,
                                        "Contraseña",
                                        false,
                                        "Gotham XLight",
                                        18);
        jLblNoVer.setVisible(false);
    }
    
    public void iniciarSesion() throws SQLException{
        if(this.jTfUsuario.getText().equals("Usuario") && this.jPFContraseña.getText().equals("Contraseña")){
            JOptionPane.showMessageDialog(null,
                                          "Por favor ingrese los datos que se le solicitan",
                                          "SAJA",
                                          JOptionPane.INFORMATION_MESSAGE);
        }else{
            if(this.jTfUsuario.getText().trim().length() > 0 && this.jPFContraseña.getText().trim().length() > 0){
                CLLogin ll = new CLLogin();
                
                try{
                    CDLogin cdl = new CDLogin();
                    
                    ll.setNombreUsuario(this.jTfUsuario.getText().trim());
                    ll.setContra(this.jPFContraseña.getText().trim());
                    
                    String[] valor = cdl.iniciarSesion(ll).split("-");
                    ll.setIdUsuario(Integer.parseInt(valor[0]));
                    ll.setNombreUsuario(valor[1]);
                    
                   if(ll.getIdUsuario() == 0 && ll.getNombreUsuario().equals("null")){
                        JOptionPane.showMessageDialog(null,
                                                      "Usuario o contraseña incorrectos.",
                                                      "SAJA",
                                                      JOptionPane.INFORMATION_MESSAGE);                    
                    } else {                        
                        JFraMenu jfm = new JFraMenu();
                        jfm.usuarioSesion(ll.getNombreUsuario());
                        jfm.setVisible(true);
                        this.dispose();                        
                    }
                } catch(SQLException e){
                    throw new SQLException(e.getMessage());
                }
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

        jPFondo = new javax.swing.JPanel();
        jBtnIngresar = new javax.swing.JButton();
        jPSaludoAzulIzquierda = new javax.swing.JPanel();
        LOGO = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPUsuario = new javax.swing.JPanel();
        jTfUsuario = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLblNoVer = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jPFContraseña = new javax.swing.JPasswordField();
        jLblPrueba = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Iniciar sesión");
        setMinimumSize(new java.awt.Dimension(700, 500));
        setResizable(false);

        jPFondo.setBackground(new java.awt.Color(255, 255, 255));
        jPFondo.setMaximumSize(new java.awt.Dimension(704, 500));
        jPFondo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jBtnIngresar.setBackground(new java.awt.Color(41, 128, 185));
        jBtnIngresar.setFont(new java.awt.Font("Gotham Black", 0, 18)); // NOI18N
        jBtnIngresar.setForeground(new java.awt.Color(255, 255, 255));
        jBtnIngresar.setText("INGRESAR");
        jBtnIngresar.setBorder(null);
        jBtnIngresar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jBtnIngresar.setOpaque(false);
        jBtnIngresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnIngresarActionPerformed(evt);
            }
        });
        jPFondo.add(jBtnIngresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 360, 290, 50));

        jPSaludoAzulIzquierda.setBackground(new java.awt.Color(41, 128, 185));

        LOGO.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/LOGO SAJA.png"))); // NOI18N

        jLabel3.setFont(new java.awt.Font("HelveticaNowDisplay ExtraBold", 0, 35)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Bienvenido");
        jLabel3.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        jLabel9.setBackground(new java.awt.Color(255, 255, 255));
        jLabel9.setFont(new java.awt.Font("HelveticaNowDisplay ExtraBold", 0, 20)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("al Sistema ");
        jLabel9.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        jLabel10.setFont(new java.awt.Font("HelveticaNowDisplay ExtraBold", 0, 20)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Administrador de");
        jLabel10.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        jLabel11.setFont(new java.awt.Font("HelveticaNowDisplay ExtraBold", 0, 20)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Juntas de Agua");
        jLabel11.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        javax.swing.GroupLayout jPSaludoAzulIzquierdaLayout = new javax.swing.GroupLayout(jPSaludoAzulIzquierda);
        jPSaludoAzulIzquierda.setLayout(jPSaludoAzulIzquierdaLayout);
        jPSaludoAzulIzquierdaLayout.setHorizontalGroup(
            jPSaludoAzulIzquierdaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPSaludoAzulIzquierdaLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPSaludoAzulIzquierdaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(LOGO, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(28, Short.MAX_VALUE))
        );
        jPSaludoAzulIzquierdaLayout.setVerticalGroup(
            jPSaludoAzulIzquierdaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPSaludoAzulIzquierdaLayout.createSequentialGroup()
                .addGap(78, 78, 78)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(56, 56, 56)
                .addComponent(LOGO)
                .addContainerGap(125, Short.MAX_VALUE))
        );

        jPFondo.add(jPSaludoAzulIzquierda, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 270, 500));

        jLabel2.setFont(new java.awt.Font("HelveticaNowDisplay ExtraBold", 0, 40)); // NOI18N
        jLabel2.setText("Iniciar sesión");
        jPFondo.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 80, 300, 40));

        jLabel4.setFont(new java.awt.Font("HelveticaNowDisplay ExtraBold", 0, 20)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(77, 77, 77));
        jLabel4.setText("usuario, abajo.");
        jLabel4.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPFondo.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 140, 290, 20));

        jPUsuario.setBackground(new java.awt.Color(223, 234, 228));

        jTfUsuario.setBackground(new java.awt.Color(223, 234, 228));
        jTfUsuario.setFont(new java.awt.Font("Gotham XLight", 1, 18)); // NOI18N
        jTfUsuario.setToolTipText("Ingrese su usuario");
        jTfUsuario.setBorder(null);

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/usuario.png"))); // NOI18N
        jLabel6.setText("jLabel6");
        jLabel6.setToolTipText("Ingrese su usuario");
        jLabel6.setPreferredSize(new java.awt.Dimension(32, 32));

        javax.swing.GroupLayout jPUsuarioLayout = new javax.swing.GroupLayout(jPUsuario);
        jPUsuario.setLayout(jPUsuarioLayout);
        jPUsuarioLayout.setHorizontalGroup(
            jPUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPUsuarioLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTfUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPUsuarioLayout.setVerticalGroup(
            jPUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPUsuarioLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTfUsuario)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPFondo.add(jPUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 200, 290, 50));

        jPanel2.setBackground(new java.awt.Color(223, 234, 228));

        jLblNoVer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/ojoVer.png"))); // NOI18N
        jLblNoVer.setToolTipText("Ver la contraseña ingresada");
        jLblNoVer.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLblNoVer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLblNoVerMouseClicked(evt);
            }
        });

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/contra.png"))); // NOI18N
        jLabel8.setToolTipText("Ingrese su contraseña de usuario");

        jPFContraseña.setBackground(new java.awt.Color(223, 234, 228));
        jPFContraseña.setFont(new java.awt.Font("Gotham XLight", 1, 18)); // NOI18N
        jPFContraseña.setToolTipText("Ingrese su contraseña de usuario");
        jPFContraseña.setBorder(null);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPFContraseña, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLblNoVer)
                .addGap(34, 34, 34))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
            .addComponent(jPFContraseña, javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(jLblNoVer, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        jPFondo.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 280, 290, 50));

        jLblPrueba.setFont(new java.awt.Font("HelveticaNowDisplay ExtraBold", 0, 20)); // NOI18N
        jLblPrueba.setForeground(new java.awt.Color(77, 77, 77));
        jLblPrueba.setText("Ingrese su información de");
        jLblPrueba.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPFondo.add(jLblPrueba, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 120, 290, 20));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPFondo, javax.swing.GroupLayout.PREFERRED_SIZE, 700, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPFondo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBtnIngresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnIngresarActionPerformed
        try {
            // TODO add your handling code here:
            this.iniciarSesion();
        } catch (SQLException ex) {
            Logger.getLogger(JFraLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jBtnIngresarActionPerformed

    private void jLblNoVerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLblNoVerMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLblNoVerMouseClicked

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
            java.util.logging.Logger.getLogger(JFraLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFraLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFraLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFraLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JFraLogin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel LOGO;
    private javax.swing.JButton jBtnIngresar;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLblNoVer;
    private javax.swing.JLabel jLblPrueba;
    private javax.swing.JPasswordField jPFContraseña;
    private javax.swing.JPanel jPFondo;
    private javax.swing.JPanel jPSaludoAzulIzquierda;
    private javax.swing.JPanel jPUsuario;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField jTfUsuario;
    // End of variables declaration//GEN-END:variables
}
