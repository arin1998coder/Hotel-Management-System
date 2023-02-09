/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package hotel.management.system;

import java.awt.Color;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.sql.*;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


/**
 *
 * @author arin1
 */
public class AddDriver extends javax.swing.JFrame{

    private JLabel image;
   
    
    public AddDriver() {
        
        initComponents();
         
        getContentPane().setBackground(Color.white);
        
        //setting the image of car driver
        
        ImageIcon addCarImage = new ImageIcon(ClassLoader.getSystemResource("icons/eleven.jpg"));
        //scalling the image to fit the label
        ImageIcon scaledCarImage = new ImageIcon(addCarImage.getImage().getScaledInstance(550, 500, Image.SCALE_DEFAULT));
        image = new JLabel(scaledCarImage);
        image.setBounds(520, 100, 550, 450);
        add(image);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    
     //check if string contains anyother character except +ve integer
    public boolean isStringContainsCharacter(String s){
        for(int i=0;i<s.length();i++){
            //if any char of string is not a digit then return true
            if(!(s.charAt(i)-'0'>=0 && s.charAt(i)-'0'<=9)){
                return true;
            }
        }
        return false;
    }

    //insert driver details into the driver table
    public boolean addDriver(){
        boolean isAdded=false;
        
        String name = txtDriverName.getText();
        String age=txtDriverAge.getText();
        String gender = (String)cbDriverGender.getSelectedItem();
        String carCompany=txtCarCompany.getText();
        String carModel = txtCarModel.getText();
        String availability=(String)cbAvailable.getSelectedItem();
        String location = txtLocation.getText();
        
        if(name.equals("")||age.equals("")||carCompany.equals("")||carModel.equals("")||location.equals("")){
            JOptionPane.showMessageDialog(null, "Please Fill All the Details");
            return false;
        }
        if(isStringContainsCharacter(age)){
            JOptionPane.showMessageDialog(null, "Please provide a valid age");
            return false;
        }
        try{
            DbConnection c = new DbConnection();
            String query = "Insert into driver(Name,Age,Gender,CarCompany,CarModel,Availability,Location) values (?,?,?,?,?,?,?)";
            PreparedStatement pst = c.con.prepareStatement(query);
            
            //set the parameters in the query
            pst.setString(1, name);
            pst.setString(2, age);
            pst.setString(3 ,gender);
            pst.setString(4, carCompany);
            pst.setString(5, carModel);
            pst.setString(6, availability);
            pst.setString(7, location);
            
            //execute the query
            int rowsAffected = pst.executeUpdate();
            if(rowsAffected>0){
                isAdded=true;
                
            }
            else{
                isAdded=false;
                JOptionPane.showMessageDialog(null, "Driver Details Addition Failed");
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
            
            return isAdded;
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtLocation = new javax.swing.JTextField();
        txtDriverName = new javax.swing.JTextField();
        txtDriverAge = new javax.swing.JTextField();
        txtCarCompany = new javax.swing.JTextField();
        txtCarModel = new javax.swing.JTextField();
        cbAvailable = new javax.swing.JComboBox<>();
        cbDriverGender = new javax.swing.JComboBox<>();
        btnCancel = new javax.swing.JButton();
        btnAddDriver = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(204, 255, 204));
        getContentPane().setLayout(null);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(AddDriver.class, "AddDriver.jLabel1.text")); // NOI18N
        getContentPane().add(jLabel1);
        jLabel1.setBounds(110, 460, 90, 30);

        jLabel2.setFont(new java.awt.Font("Segoe UI Black", 1, 24)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel2, org.openide.util.NbBundle.getMessage(AddDriver.class, "AddDriver.jLabel2.text")); // NOI18N
        getContentPane().add(jLabel2);
        jLabel2.setBounds(200, 30, 160, 30);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel3, org.openide.util.NbBundle.getMessage(AddDriver.class, "AddDriver.jLabel3.text")); // NOI18N
        getContentPane().add(jLabel3);
        jLabel3.setBounds(110, 100, 90, 30);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel4, org.openide.util.NbBundle.getMessage(AddDriver.class, "AddDriver.jLabel4.text")); // NOI18N
        getContentPane().add(jLabel4);
        jLabel4.setBounds(110, 160, 90, 30);

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel5, org.openide.util.NbBundle.getMessage(AddDriver.class, "AddDriver.jLabel5.text")); // NOI18N
        getContentPane().add(jLabel5);
        jLabel5.setBounds(110, 210, 90, 30);

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel6, org.openide.util.NbBundle.getMessage(AddDriver.class, "AddDriver.jLabel6.text")); // NOI18N
        getContentPane().add(jLabel6);
        jLabel6.setBounds(110, 270, 90, 30);

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel7, org.openide.util.NbBundle.getMessage(AddDriver.class, "AddDriver.jLabel7.text")); // NOI18N
        getContentPane().add(jLabel7);
        jLabel7.setBounds(110, 330, 90, 30);

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel8, org.openide.util.NbBundle.getMessage(AddDriver.class, "AddDriver.jLabel8.text")); // NOI18N
        getContentPane().add(jLabel8);
        jLabel8.setBounds(110, 390, 90, 30);

        txtLocation.setText(org.openide.util.NbBundle.getMessage(AddDriver.class, "AddDriver.txtLocation.text")); // NOI18N
        getContentPane().add(txtLocation);
        txtLocation.setBounds(230, 460, 160, 30);

        txtDriverName.setText(org.openide.util.NbBundle.getMessage(AddDriver.class, "AddDriver.txtDriverName.text")); // NOI18N
        getContentPane().add(txtDriverName);
        txtDriverName.setBounds(230, 100, 160, 30);

        txtDriverAge.setText(org.openide.util.NbBundle.getMessage(AddDriver.class, "AddDriver.txtDriverAge.text")); // NOI18N
        txtDriverAge.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDriverAgeActionPerformed(evt);
            }
        });
        getContentPane().add(txtDriverAge);
        txtDriverAge.setBounds(230, 160, 160, 30);

        txtCarCompany.setText(org.openide.util.NbBundle.getMessage(AddDriver.class, "AddDriver.txtCarCompany.text")); // NOI18N
        getContentPane().add(txtCarCompany);
        txtCarCompany.setBounds(230, 270, 160, 30);

        txtCarModel.setText(org.openide.util.NbBundle.getMessage(AddDriver.class, "AddDriver.txtCarModel.text")); // NOI18N
        getContentPane().add(txtCarModel);
        txtCarModel.setBounds(230, 330, 160, 30);

        cbAvailable.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Available", "Not-Available" }));
        getContentPane().add(cbAvailable);
        cbAvailable.setBounds(230, 390, 160, 30);

        cbDriverGender.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Male", "Female" }));
        getContentPane().add(cbDriverGender);
        cbDriverGender.setBounds(230, 210, 160, 30);

        btnCancel.setBackground(new java.awt.Color(0, 0, 0));
        btnCancel.setForeground(new java.awt.Color(255, 255, 255));
        org.openide.awt.Mnemonics.setLocalizedText(btnCancel, org.openide.util.NbBundle.getMessage(AddDriver.class, "AddDriver.btnCancel.text")); // NOI18N
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });
        getContentPane().add(btnCancel);
        btnCancel.setBounds(260, 540, 130, 40);

        btnAddDriver.setBackground(new java.awt.Color(0, 0, 0));
        btnAddDriver.setForeground(new java.awt.Color(255, 255, 255));
        org.openide.awt.Mnemonics.setLocalizedText(btnAddDriver, org.openide.util.NbBundle.getMessage(AddDriver.class, "AddDriver.btnAddDriver.text")); // NOI18N
        btnAddDriver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddDriverActionPerformed(evt);
            }
        });
        getContentPane().add(btnAddDriver);
        btnAddDriver.setBounds(100, 540, 120, 40);

        setSize(new java.awt.Dimension(1214, 674));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtDriverAgeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDriverAgeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDriverAgeActionPerformed

    private void btnAddDriverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddDriverActionPerformed
            if(addDriver()){
                JOptionPane.showMessageDialog(null, "Driver Details Added Successfully");
                setVisible(false);
                this.dispose();
            }
           
    }//GEN-LAST:event_btnAddDriverActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        this.setVisible(false);
        this.dispose();
    }//GEN-LAST:event_btnCancelActionPerformed

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
            java.util.logging.Logger.getLogger(AddDriver.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddDriver.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddDriver.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddDriver.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AddDriver().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddDriver;
    private javax.swing.JButton btnCancel;
    private javax.swing.JComboBox<String> cbAvailable;
    private javax.swing.JComboBox<String> cbDriverGender;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JTextField txtCarCompany;
    private javax.swing.JTextField txtCarModel;
    private javax.swing.JTextField txtDriverAge;
    private javax.swing.JTextField txtDriverName;
    private javax.swing.JTextField txtLocation;
    // End of variables declaration//GEN-END:variables

}
