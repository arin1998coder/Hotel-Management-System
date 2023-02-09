/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package hotel.management.system;


import java.sql.*;
import javax.swing.JOptionPane;
public class Signup extends javax.swing.JFrame {

    /**
     * Creates new form Signup
     */
    public Signup() {
        initComponents();
    }

    //adds the sign up details of the user to the users table in the db
    public boolean addSignUpDetails(){
        boolean isAdded=false;
        String name=txtName.getText();
        String age=txtAge.getText();
        String idType=cbId.getSelectedItem().toString();
        String idNum=txtIdNumber.getText();
        String department=cbDepartment.getSelectedItem().toString();
        String contact=txtPhoneNo.getText();
        String email=txtEmail.getText();
        String pass=txtPassword.getText();
        String securityQuestion=(String)cbSecurityAns.getSelectedItem();
        String answer=txtAnswer.getText();
        String addres=txtAddress.getText();
        String status="Not Approved";
        
        if(age.equals("")||idNum.equals("")|| contact.equals("")||name.equals("")|| email.equals("") || pass.equals("")|| answer.equals("") || addres.equals("") || status.equals("")){
            JOptionPane.showMessageDialog(null, "Please Enter All the Details");
            return false;
           
        }
        
         //email inputeed should be a valid email address
         if(!email.matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")){
             JOptionPane.showMessageDialog(this, "Pleas Enter Valid Email");
             return false;
         }
         if(checkDuplicateUser()){
             JOptionPane.showMessageDialog(null, "User with provided Email Allready Exists!");
             return false;
         }
         
        try {
            String query="Insert into users (name,age,IdType,IdNumber,department,contact,email,password,securityQuestion,answer,address,status) values (?,?,?,?,?,?,?,?,?,?,?,?)";
            String msg="Sign Up Successfull";
            PreparedStatement pst=new DbConnection().con.prepareStatement(query);
        
            pst.setString(1, name);
            pst.setString(2, age);
            pst.setString(3, idType);
            pst.setString(4, idNum);
            pst.setString(5, department);
            pst.setString(6, contact);
            pst.setString(7, email);
            pst.setString(8, pass);
            pst.setString(9, securityQuestion);
            pst.setString(10, answer);
            pst.setString(11, addres);
            pst.setString(12, status);
            
            
            int rowaffecred=pst.executeUpdate();
            if(rowaffecred>0){
                isAdded=true;
                JOptionPane.showMessageDialog(null, msg);
            }
            else{
                JOptionPane.showMessageDialog(null, "Sign Up Failed");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }   
        return isAdded;
        
    }
    //to check duplicate users
    public boolean checkDuplicateUser(){
        boolean isExists=false;
        String email=txtEmail.getText();
        //trying to fetch the existing username's from the db
        //if it allready exists- we have tp show msg "username allready exists"
        try {
            DbConnection c=new DbConnection(); //establishes the connection with db
            //writing a query to the MY SQL db to fetch the records from the email col where email=email provided by curr user
            PreparedStatement pst=c.con.prepareStatement("select * from users where email=?");
            pst.setString(1,email); 
            ResultSet rs=pst.executeQuery();//storing the records equal to the email provided by the user
            //rs.next() true means- user with curr email allready exists
            if(rs.next()){
                isExists=true;
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isExists;
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        txtAddress = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        cbId = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtPassword = new javax.swing.JPasswordField();
        txtEmail = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtAnswer = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        cbSecurityAns = new javax.swing.JComboBox<>();
        txtPhoneNo = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        cbDepartment = new javax.swing.JComboBox<>();
        txtIdNumber = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtAge = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtAddress.setText(org.openide.util.NbBundle.getMessage(Signup.class, "Signup.txtAddress.text")); // NOI18N
        jPanel1.add(txtAddress, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 590, 340, 30));

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(Signup.class, "Signup.jLabel1.text")); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 600, 110, 20));

        org.openide.awt.Mnemonics.setLocalizedText(jButton2, org.openide.util.NbBundle.getMessage(Signup.class, "Signup.jButton2.text")); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 670, 90, 30));

        org.openide.awt.Mnemonics.setLocalizedText(jButton3, org.openide.util.NbBundle.getMessage(Signup.class, "Signup.jButton3.text")); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 670, 100, 30));

        org.openide.awt.Mnemonics.setLocalizedText(jButton1, org.openide.util.NbBundle.getMessage(Signup.class, "Signup.jButton1.text")); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 670, -1, 30));

        cbId.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Aadhar", "Voter Id", "Driving License", "Pan Card", "Passport" }));
        jPanel1.add(cbId, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 190, 340, 30));

        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        org.openide.awt.Mnemonics.setLocalizedText(jLabel6, org.openide.util.NbBundle.getMessage(Signup.class, "Signup.jLabel6.text")); // NOI18N
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 500, 110, 20));

        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        org.openide.awt.Mnemonics.setLocalizedText(jLabel5, org.openide.util.NbBundle.getMessage(Signup.class, "Signup.jLabel5.text")); // NOI18N
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 450, 80, 20));

        txtPassword.setText(org.openide.util.NbBundle.getMessage(Signup.class, "Signup.txtPassword.text")); // NOI18N
        txtPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPasswordActionPerformed(evt);
            }
        });
        jPanel1.add(txtPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 440, 340, 30));

        txtEmail.setText(org.openide.util.NbBundle.getMessage(Signup.class, "Signup.txtEmail.text")); // NOI18N
        txtEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEmailActionPerformed(evt);
            }
        });
        jPanel1.add(txtEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 390, 340, 30));

        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        org.openide.awt.Mnemonics.setLocalizedText(jLabel4, org.openide.util.NbBundle.getMessage(Signup.class, "Signup.jLabel4.text")); // NOI18N
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 400, 80, 20));

        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        org.openide.awt.Mnemonics.setLocalizedText(jLabel3, org.openide.util.NbBundle.getMessage(Signup.class, "Signup.jLabel3.text")); // NOI18N
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 90, 90, 20));

        txtName.setText(org.openide.util.NbBundle.getMessage(Signup.class, "Signup.txtName.text")); // NOI18N
        jPanel1.add(txtName, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 90, 340, 30));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        org.openide.awt.Mnemonics.setLocalizedText(jLabel2, org.openide.util.NbBundle.getMessage(Signup.class, "Signup.jLabel2.text")); // NOI18N
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 30, 90, 30));

        txtAnswer.setText(org.openide.util.NbBundle.getMessage(Signup.class, "Signup.txtAnswer.text")); // NOI18N
        jPanel1.add(txtAnswer, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 540, 340, 30));

        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        org.openide.awt.Mnemonics.setLocalizedText(jLabel8, org.openide.util.NbBundle.getMessage(Signup.class, "Signup.jLabel8.text")); // NOI18N
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 550, 110, 20));

        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        org.openide.awt.Mnemonics.setLocalizedText(jLabel9, org.openide.util.NbBundle.getMessage(Signup.class, "Signup.jLabel9.text")); // NOI18N
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 200, 120, 20));

        cbSecurityAns.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "What is the name of your first pet?", "What was your first car?", "What elementary school did you attend?", "What is the name of the town where you were born?" }));
        jPanel1.add(cbSecurityAns, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 490, 340, 30));

        txtPhoneNo.setText(org.openide.util.NbBundle.getMessage(Signup.class, "Signup.txtPhoneNo.text")); // NOI18N
        txtPhoneNo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPhoneNoActionPerformed(evt);
            }
        });
        jPanel1.add(txtPhoneNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 290, 340, 30));

        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        org.openide.awt.Mnemonics.setLocalizedText(jLabel10, org.openide.util.NbBundle.getMessage(Signup.class, "Signup.jLabel10.text")); // NOI18N
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 300, 80, 20));

        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        org.openide.awt.Mnemonics.setLocalizedText(jLabel11, org.openide.util.NbBundle.getMessage(Signup.class, "Signup.jLabel11.text")); // NOI18N
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 340, 110, 20));

        cbDepartment.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Reception", "Driver", "Room Cleaner", "Kitchen", "Room Service", "Guest" }));
        jPanel1.add(cbDepartment, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 340, 340, 30));

        txtIdNumber.setText(org.openide.util.NbBundle.getMessage(Signup.class, "Signup.txtIdNumber.text")); // NOI18N
        txtIdNumber.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdNumberActionPerformed(evt);
            }
        });
        jPanel1.add(txtIdNumber, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 240, 340, 30));

        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        org.openide.awt.Mnemonics.setLocalizedText(jLabel12, org.openide.util.NbBundle.getMessage(Signup.class, "Signup.jLabel12.text")); // NOI18N
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 250, 80, 20));

        txtAge.setText(org.openide.util.NbBundle.getMessage(Signup.class, "Signup.txtAge.text")); // NOI18N
        txtAge.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAgeActionPerformed(evt);
            }
        });
        jPanel1.add(txtAge, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 140, 340, 30));

        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        org.openide.awt.Mnemonics.setLocalizedText(jLabel13, org.openide.util.NbBundle.getMessage(Signup.class, "Signup.jLabel13.text")); // NOI18N
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 150, 80, 20));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 50, 640, 760));

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Hotel Management System/src/icons/shutdownbest11838.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel14, org.openide.util.NbBundle.getMessage(Signup.class, "Signup.jLabel14.text")); // NOI18N
        jLabel14.setPreferredSize(new java.awt.Dimension(200, 300));
        jLabel14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel14MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(1380, 30, 90, 100));

        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Hotel Management System/src/icons/pexelsBest.jpg"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel15, org.openide.util.NbBundle.getMessage(Signup.class, "Signup.jLabel15.text")); // NOI18N
        getContentPane().add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1600, 900));

        setSize(new java.awt.Dimension(1600, 900));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEmailActionPerformed

    private void txtPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPasswordActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_txtPasswordActionPerformed
    //button to signup user
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if(addSignUpDetails()){
            new Signup().setVisible(true);
            this.dispose();
        }
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void txtPhoneNoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPhoneNoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPhoneNoActionPerformed

    private void txtIdNumberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdNumberActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdNumberActionPerformed

    private void txtAgeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAgeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAgeActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        this.dispose();
        new Login().setVisible(true);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        this.dispose();
        new ForgotPassword().setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jLabel14MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel14MouseClicked
        this.dispose();
    }//GEN-LAST:event_jLabel14MouseClicked

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
            java.util.logging.Logger.getLogger(Signup.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Signup.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Signup.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Signup.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Signup().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cbDepartment;
    private javax.swing.JComboBox<String> cbId;
    private javax.swing.JComboBox<String> cbSecurityAns;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField txtAddress;
    private javax.swing.JTextField txtAge;
    private javax.swing.JTextField txtAnswer;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtIdNumber;
    private javax.swing.JTextField txtName;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtPhoneNo;
    // End of variables declaration//GEN-END:variables
}
