/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package hotel.management.system;
import java.awt.Color;
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.Timer;

public class ForgotPassword extends javax.swing.JFrame {

    private static final int TIME_VISIBLE = 3000;
    String email,answer,newPassword,securityQue;
    
    
    public ForgotPassword() {
        initComponents();
        txtAnswer.setEditable(false);
        txtNewPwd.setEditable(false);
        
    }

//      //validate this login page
//    public boolean validateLogin(){
//        String email=txtEmail.getText();
//        String pwd=txtPassword.getText();
//        
//        //if emptry username provided, then give validation msg
//        if(email.equals("")){
//            JOptionPane.showMessageDialog(this, "Please Enter Email");
//            return false;
//        }
//        //if empty password provided
//        if(pwd.equals("")){
//            JOptionPane.showMessageDialog(this, "Please Enter password");
//            return false;
//        }
//        return true;
//    }
    
    public boolean searchEmailAndShowSecurityQuestion(){
        
        email=txtEmail.getText();
        boolean isValid=false;
        
        try {
            DbConnection c=new DbConnection();
            String query="Select * from users where email='"+email+"'";
            PreparedStatement pst = c.con.prepareStatement(query);
            
            ResultSet rs = pst.executeQuery();
            
            if(rs.next()){
                txtSecQuestion.setText(rs.getString(10)); //displays the securoty question connected with the provided email address
                securityQue=txtSecQuestion.getText();
                txtEmail.setEditable(false); 
                txtNewPwd.setEditable(true);
                txtAnswer.setEditable(true);
                
                isValid=true;
                
            }
            else{
                JOptionPane.showMessageDialog(null, "Please provide valid Email");
                isValid=false;
            }
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isValid;
    }
    
    //validate user by checking if the user has provided the correct ans to securtiy ques
    //then update the new password for this user
    public boolean validateAnswerAndUpdatePassword(){
        
        boolean flag =true; //to check whether user has provided valid entries in all the fields
        answer=txtAnswer.getText();
        newPassword=txtNewPwd.getText();
        //if the email is valid then only proceed
        if(searchEmailAndShowSecurityQuestion()){
            
            if(answer.equals("")|| newPassword.equals("")){
                JOptionPane.showMessageDialog(null, "Please fill all the fields");
                return false;
            }
            else{ 
                try {
                    DbConnection c = new DbConnection();
                    //query to get the credentials if the answer matches the security Question associated with the email
                    String query="Select * from users where answer ='"+answer+"' and securityQuestion ='"+securityQue+"' and email='"+email+"'";
                    PreparedStatement pst = c.con.prepareStatement(query);
                    
                    ResultSet rs =pst.executeQuery();
                    //user has provided correct answer
                    if(rs.next()){
                       
                        try {
                            String update="update users set password = '"+newPassword+"' where email='"+email+"'"; //updates the password of the user to new password
                            PreparedStatement pst2= c.con.prepareStatement(update);
                            
                            int rowaffected = pst2.executeUpdate();
                            if(rowaffected>0){
                                JOptionPane.showMessageDialog(null, "Password updated successfully");
                            }
                            
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Incorrect Answer! Try Again!");
                        flag = false;
                    }  
                    
                }
                
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
            
        }
        else{
            return false;
        }
        return flag;
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        btnSignUp = new javax.swing.JButton();
        btnLogin = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        lblNewPwd = new javax.swing.JLabel();
        txtNewPwd = new javax.swing.JPasswordField();
        txtEmail = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtSecQuestion = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtAnswer = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        btnNewSearch = new javax.swing.JButton();
        btnSearch = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(51, 51, 51));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        org.openide.awt.Mnemonics.setLocalizedText(btnSignUp, org.openide.util.NbBundle.getMessage(ForgotPassword.class, "ForgotPassword.btnSignUp.text")); // NOI18N
        btnSignUp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSignUpActionPerformed(evt);
            }
        });
        jPanel2.add(btnSignUp, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 420, 110, 30));

        org.openide.awt.Mnemonics.setLocalizedText(btnLogin, org.openide.util.NbBundle.getMessage(ForgotPassword.class, "ForgotPassword.btnLogin.text")); // NOI18N
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });
        jPanel2.add(btnLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 420, 110, 30));

        org.openide.awt.Mnemonics.setLocalizedText(btnSave, org.openide.util.NbBundle.getMessage(ForgotPassword.class, "ForgotPassword.btnSave.text")); // NOI18N
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });
        jPanel2.add(btnSave, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 420, 110, 30));

        lblNewPwd.setForeground(new java.awt.Color(255, 255, 255));
        org.openide.awt.Mnemonics.setLocalizedText(lblNewPwd, org.openide.util.NbBundle.getMessage(ForgotPassword.class, "ForgotPassword.lblNewPwd.text")); // NOI18N
        jPanel2.add(lblNewPwd, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 340, 80, 20));

        txtNewPwd.setText(org.openide.util.NbBundle.getMessage(ForgotPassword.class, "ForgotPassword.txtNewPwd.text")); // NOI18N
        txtNewPwd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNewPwdActionPerformed(evt);
            }
        });
        jPanel2.add(txtNewPwd, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 330, 340, 30));

        txtEmail.setText(org.openide.util.NbBundle.getMessage(ForgotPassword.class, "ForgotPassword.txtEmail.text")); // NOI18N
        txtEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEmailActionPerformed(evt);
            }
        });
        jPanel2.add(txtEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 120, 340, 30));

        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        org.openide.awt.Mnemonics.setLocalizedText(jLabel4, org.openide.util.NbBundle.getMessage(ForgotPassword.class, "ForgotPassword.jLabel4.text")); // NOI18N
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 130, 80, 20));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        org.openide.awt.Mnemonics.setLocalizedText(jLabel2, org.openide.util.NbBundle.getMessage(ForgotPassword.class, "ForgotPassword.jLabel2.text")); // NOI18N
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 30, 170, 30));

        txtSecQuestion.setEditable(false);
        txtSecQuestion.setText(org.openide.util.NbBundle.getMessage(ForgotPassword.class, "ForgotPassword.txtSecQuestion.text")); // NOI18N
        txtSecQuestion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSecQuestionActionPerformed(evt);
            }
        });
        jPanel2.add(txtSecQuestion, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 180, 340, 30));

        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        org.openide.awt.Mnemonics.setLocalizedText(jLabel6, org.openide.util.NbBundle.getMessage(ForgotPassword.class, "ForgotPassword.jLabel6.text")); // NOI18N
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 190, 100, 20));

        txtAnswer.setText(org.openide.util.NbBundle.getMessage(ForgotPassword.class, "ForgotPassword.txtAnswer.text")); // NOI18N
        txtAnswer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAnswerActionPerformed(evt);
            }
        });
        jPanel2.add(txtAnswer, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 250, 340, 30));

        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        org.openide.awt.Mnemonics.setLocalizedText(jLabel7, org.openide.util.NbBundle.getMessage(ForgotPassword.class, "ForgotPassword.jLabel7.text")); // NOI18N
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 260, 80, 20));

        org.openide.awt.Mnemonics.setLocalizedText(btnNewSearch, org.openide.util.NbBundle.getMessage(ForgotPassword.class, "ForgotPassword.btnNewSearch.text")); // NOI18N
        btnNewSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewSearchActionPerformed(evt);
            }
        });
        jPanel2.add(btnNewSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 180, 100, 30));

        org.openide.awt.Mnemonics.setLocalizedText(btnSearch, org.openide.util.NbBundle.getMessage(ForgotPassword.class, "ForgotPassword.btnSearch.text")); // NOI18N
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });
        jPanel2.add(btnSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 120, 100, 30));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 170, 730, 500));

        jButton4.setBackground(new java.awt.Color(0, 0, 0));
        jButton4.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        org.openide.awt.Mnemonics.setLocalizedText(jButton4, org.openide.util.NbBundle.getMessage(ForgotPassword.class, "ForgotPassword.jButton4.text")); // NOI18N
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1380, 70, 120, 60));

        setSize(new java.awt.Dimension(1614, 907));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnSignUpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSignUpActionPerformed
           this.dispose();
           new Signup().setVisible(true);
    }//GEN-LAST:event_btnSignUpActionPerformed

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
        this.dispose();
        new Login().setVisible(true);
    }//GEN-LAST:event_btnLoginActionPerformed

    private void txtNewPwdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNewPwdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNewPwdActionPerformed

    private void txtEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEmailActionPerformed

    private void txtSecQuestionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSecQuestionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSecQuestionActionPerformed

    private void txtAnswerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAnswerActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAnswerActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        if(validateAnswerAndUpdatePassword()){
            this.dispose();
            new ForgotPassword().setVisible(true);
        }
    }//GEN-LAST:event_btnSaveActionPerformed
     //clears all the fields in the page
    private void btnNewSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewSearchActionPerformed
        txtEmail.setText("");
        txtAnswer.setText("");
        txtNewPwd.setText("");
        txtSecQuestion.setText("");
        txtEmail.setEditable(true);
        txtAnswer.setEditable(false);
        txtNewPwd.setEditable(false);
        
    }//GEN-LAST:event_btnNewSearchActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        // TODO add your handling code here:
        searchEmailAndShowSecurityQuestion();
    }//GEN-LAST:event_btnSearchActionPerformed

    
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
            java.util.logging.Logger.getLogger(ForgotPassword.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ForgotPassword.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ForgotPassword.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ForgotPassword.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ForgotPassword().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLogin;
    private javax.swing.JButton btnNewSearch;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnSignUp;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lblNewPwd;
    private javax.swing.JTextField txtAnswer;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JPasswordField txtNewPwd;
    private javax.swing.JTextField txtSecQuestion;
    // End of variables declaration//GEN-END:variables
}
