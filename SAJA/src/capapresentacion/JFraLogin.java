/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capapresentacion;
import com.placeholder.PlaceHolder ;
import java.awt.Color;

/**
 *
 * @author Manrique
 */
public class JFraLogin extends javax.swing.JFrame {

    /**
     * Creates new form JFraLogin
     */
    public JFraLogin() {
        initComponents();
        this.setLocationRelativeTo(null);
        PlaceHolder u = new PlaceHolder(jTxtUsuario, 
                                        new Color(55,73,87),
                                        Color.BLACK,
                                        "Usuario",
                                        false,
                                        "Gotham XLight",
                                        18);
        
        PlaceHolder c = new PlaceHolder(jTxtContra, 
                                        new Color(55,73,87),
                                        Color.BLACK,
                                        "Contraseña",
                                        false,
                                        "Gotham XLight",
                                        18);
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
        jPSaludoAzulIzquierda = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        LOGO = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPUsuario = new javax.swing.JPanel();
        jTxtUsuario = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jTxtContra = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jBtnIngresar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Iniciar sesión");
        setMaximumSize(new java.awt.Dimension(700, 500));
        setMinimumSize(new java.awt.Dimension(700, 500));
        setResizable(false);

        jPFondo.setBackground(new java.awt.Color(255, 255, 255));
        jPFondo.setMaximumSize(new java.awt.Dimension(704, 500));
        jPFondo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPSaludoAzulIzquierda.setBackground(new java.awt.Color(9, 132, 227));

        jLabel1.setFont(new java.awt.Font("Gotham Black", 0, 20)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("<html>\nal Sistema \nAdministrador de \nJuntas de Agua\n</html>");
        jLabel1.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        LOGO.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/LOGO SAJA.png"))); // NOI18N

        jLabel3.setFont(new java.awt.Font("Gotham Black", 0, 35)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Bienvenido");

        javax.swing.GroupLayout jPSaludoAzulIzquierdaLayout = new javax.swing.GroupLayout(jPSaludoAzulIzquierda);
        jPSaludoAzulIzquierda.setLayout(jPSaludoAzulIzquierdaLayout);
        jPSaludoAzulIzquierdaLayout.setHorizontalGroup(
            jPSaludoAzulIzquierdaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPSaludoAzulIzquierdaLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPSaludoAzulIzquierdaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE)
                    .addGroup(jPSaludoAzulIzquierdaLayout.createSequentialGroup()
                        .addGroup(jPSaludoAzulIzquierdaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(LOGO)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPSaludoAzulIzquierdaLayout.setVerticalGroup(
            jPSaludoAzulIzquierdaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPSaludoAzulIzquierdaLayout.createSequentialGroup()
                .addGap(78, 78, 78)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50)
                .addComponent(LOGO)
                .addContainerGap(125, Short.MAX_VALUE))
        );

        jPFondo.add(jPSaludoAzulIzquierda, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 270, 500));

        jLabel2.setFont(new java.awt.Font("Gotham Black", 0, 40)); // NOI18N
        jLabel2.setText("Iniciar sesión");
        jPFondo.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 70, 300, 58));

        jLabel4.setFont(new java.awt.Font("Gotham XLight", 1, 20)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(77, 77, 77));
        jLabel4.setText("<html>Ingrese su información de\nusuario, abajo.</html>\n");
        jLabel4.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPFondo.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 120, 290, 80));

        jPUsuario.setBackground(new java.awt.Color(223, 234, 228));

        jTxtUsuario.setBackground(new java.awt.Color(223, 234, 228));
        jTxtUsuario.setFont(new java.awt.Font("Gotham XLight", 1, 18)); // NOI18N
        jTxtUsuario.setForeground(new java.awt.Color(55, 73, 87));
        jTxtUsuario.setToolTipText("");
        jTxtUsuario.setBorder(null);

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/usuario.png"))); // NOI18N
        jLabel6.setText("jLabel6");
        jLabel6.setPreferredSize(new java.awt.Dimension(32, 32));

        javax.swing.GroupLayout jPUsuarioLayout = new javax.swing.GroupLayout(jPUsuario);
        jPUsuario.setLayout(jPUsuarioLayout);
        jPUsuarioLayout.setHorizontalGroup(
            jPUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPUsuarioLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTxtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );
        jPUsuarioLayout.setVerticalGroup(
            jPUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPUsuarioLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTxtUsuario)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPFondo.add(jPUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 200, 290, 50));

        jPanel2.setBackground(new java.awt.Color(223, 234, 228));

        jTxtContra.setBackground(new java.awt.Color(223, 234, 228));
        jTxtContra.setFont(new java.awt.Font("Gotham XLight", 1, 20)); // NOI18N
        jTxtContra.setForeground(new java.awt.Color(55, 73, 87));
        jTxtContra.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        jTxtContra.setBorder(null);
        jTxtContra.setPreferredSize(new java.awt.Dimension(0, 20));

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/contra.png"))); // NOI18N

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/ojoVer.png"))); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTxtContra, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addComponent(jLabel7)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
            .addComponent(jTxtContra, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPFondo.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 280, 290, 50));

        jBtnIngresar.setBackground(new java.awt.Color(9, 132, 227));
        jBtnIngresar.setFont(new java.awt.Font("Gotham Black", 0, 18)); // NOI18N
        jBtnIngresar.setForeground(new java.awt.Color(255, 255, 255));
        jBtnIngresar.setText("Ingresar");
        jBtnIngresar.setBorder(null);
        jBtnIngresar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jBtnIngresar.setOpaque(false);
        jPFondo.add(jBtnIngresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 340, 290, 50));

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
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPFondo;
    private javax.swing.JPanel jPSaludoAzulIzquierda;
    private javax.swing.JPanel jPUsuario;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField jTxtContra;
    private javax.swing.JTextField jTxtUsuario;
    // End of variables declaration//GEN-END:variables
}