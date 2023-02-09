/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package hotel.management.system;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

/**
 *
 * @author arin1
 */
public class Login extends javax.swing.JFrame {

    /**
     * Creates new form Login
     */
    public Login() {
        initComponents();
    }

    
    //validate this login page
    public boolean validateLogin(){
        String email=txtEmail.getText();
        String pwd=txtPassword.getText();
        
        //if emptry username provided, then give validation msg
        if(email.equals("")){
            JOptionPane.showMessageDialog(this, "Please Enter Email");
            return false;
        }
        //if empty password provided
        if(pwd.equals("")){
            JOptionPane.showMessageDialog(this, "Please Enter password");
            return false;
        }
        return true;
    }
    
    //verify login credentials
    public void checkLoginCreds(){
        String email=txtEmail.getText();
        String pwd=txtPassword.getText();
        String department=cbDepartment.getSelectedItem().toString();
        
        //if admin credentials provided then open admin frame(log in as admin)
        if(email.equals("admin") && pwd.equals("1234")){
            JOptionPane.showMessageDialog(null, "Logged in as the Admin");
            this.dispose();
            new Admin().setVisible(true);
            return;
        }
        else if(department.equals("Admin")){
            JOptionPane.showMessageDialog(null, "Please provide Admin Credentials to Login as Admin");
            return;
        }
        try {
            DbConnection c= new DbConnection();
            PreparedStatement pst=c.con.prepareStatement("select * from users where email=? and password =?");
            pst.setString(1, email);
            pst.setString(2, pwd);
            
           ResultSet rs= pst.executeQuery(); //stores the result fetched from the sql query, in this case email and password
           //if the entered e mail & password is valid, that is credentials matches and status "Not Approved " then it will show pop up "Wait for Admin Approval"
           if(rs.next()){
               
               if(rs.getString(13).equals("Not Approved")){
                   JOptionPane.showMessageDialog(this, "Please Wait For Admin Approval");
               }
               else{
                   JOptionPane.showMessageDialog(this, "Log in Successfull");
                   //based on the department selected by the user trying to login
                   //the selected department frame will open
                   if(department.equals("Reception")){
                       this.dispose();
                       new Reception().setVisible(true);
                   }
                    if(department.equals("Guest")){
                       this.dispose();
                       new Reception().setVisible(true);
                   }
                     if(department.equals("Room Cleaner")){
                       this.dispose();
                       new Reception().setVisible(true);
                   }
                      if(department.equals("Driver")){
                       this.dispose();
                       new Reception().setVisible(true);
                   }
                       if(department.equals("Room Service")){
                       this.dispose();
                       new Reception().setVisible(true);
                   }
                     if(department.equals("Kitchen")){
                       this.dispose();
                       new Reception().setVisible(true);
                   }   
                   
               }
           }
           else{
               JOptionPane.showMessageDialog(this, "Login Failed! Please provide valid credentials");
           }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        btnSignUp = new javax.swing.JButton();
        btnLogin = new javax.swing.JButton();
        btnForgotPass = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        txtPassword = new javax.swing.JPasswordField();
        txtEmail = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        cbDepartment = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 150, -1, 350));

        jPanel2.setBackground(new java.awt.Color(51, 51, 51));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        org.openide.awt.Mnemonics.setLocalizedText(btnSignUp, org.openide.util.NbBundle.getMessage(Login.class, "Login.btnSignUp.text")); // NOI18N
        btnSignUp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSignUpActionPerformed(evt);
            }
        });
        jPanel2.add(btnSignUp, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 290, 110, 30));

        org.openide.awt.Mnemonics.setLocalizedText(btnLogin, org.openide.util.NbBundle.getMessage(Login.class, "Login.btnLogin.text")); // NOI18N
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });
        jPanel2.add(btnLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 290, 110, 30));

        org.openide.awt.Mnemonics.setLocalizedText(btnForgotPass, org.openide.util.NbBundle.getMessage(Login.class, "Login.btnForgotPass.text")); // NOI18N
        btnForgotPass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnForgotPassActionPerformed(evt);
            }
        });
        jPanel2.add(btnForgotPass, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 290, -1, 30));

        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        org.openide.awt.Mnemonics.setLocalizedText(jLabel5, org.openide.util.NbBundle.getMessage(Login.class, "Login.jLabel5.text")); // NOI18N
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 170, 80, 20));

        txtPassword.setText(org.openide.util.NbBundle.getMessage(Login.class, "Login.txtPassword.text")); // NOI18N
        txtPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPasswordActionPerformed(evt);
            }
        });
        jPanel2.add(txtPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 160, 340, 30));

        txtEmail.setText(org.openide.util.NbBundle.getMessage(Login.class, "Login.txtEmail.text")); // NOI18N
        txtEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEmailActionPerformed(evt);
            }
        });
        jPanel2.add(txtEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 100, 340, 30));

        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        org.openide.awt.Mnemonics.setLocalizedText(jLabel4, org.openide.util.NbBundle.getMessage(Login.class, "Login.jLabel4.text")); // NOI18N
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 110, 80, 20));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        org.openide.awt.Mnemonics.setLocalizedText(jLabel2, org.openide.util.NbBundle.getMessage(Login.class, "Login.jLabel2.text")); // NOI18N
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 30, 70, 30));

        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        org.openide.awt.Mnemonics.setLocalizedText(jLabel6, org.openide.util.NbBundle.getMessage(Login.class, "Login.jLabel6.text")); // NOI18N
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 230, 80, 20));

        cbDepartment.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Admin", "Reception", "Driver", "Room Cleaner", "Kitchen", "Guest", "Room Service" }));
        jPanel2.add(cbDepartment, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 220, 340, 30));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 230, 650, 370));

        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(Login.class, "Login.jLabel1.text")); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 190, 270));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Hotel Management System/src/icons/shutdownbest11838.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel3, org.openide.util.NbBundle.getMessage(Login.class, "Login.jLabel3.text")); // NOI18N
        jLabel3.setPreferredSize(new java.awt.Dimension(200, 300));
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1380, 30, 90, 100));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Hotel Management System/src/icons/loginwallpaper.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel7, org.openide.util.NbBundle.getMessage(Login.class, "Login.jLabel7.text")); // NOI18N
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(-2, -4, 1600, 910));

        setSize(new java.awt.Dimension(1600, 900));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEmailActionPerformed

    private void txtPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPasswordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPasswordActionPerformed

    private void btnSignUpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSignUpActionPerformed
        this.dispose();
        new Signup().setVisible(true);
    }//GEN-LAST:event_btnSignUpActionPerformed

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
        if(validateLogin()){
            checkLoginCreds();
        }
    }//GEN-LAST:event_btnLoginActionPerformed

    private void btnForgotPassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnForgotPassActionPerformed
        this.dispose();
        new ForgotPassword().setVisible(true);
    }//GEN-LAST:event_btnForgotPassActionPerformed

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        this.dispose();
    }//GEN-LAST:event_jLabel3MouseClicked

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
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnForgotPass;
    private javax.swing.JButton btnLogin;
    private javax.swing.JButton btnSignUp;
    private javax.swing.JComboBox<String> cbDepartment;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JPasswordField txtPassword;
    // End of variables declaration//GEN-END:variables
}
