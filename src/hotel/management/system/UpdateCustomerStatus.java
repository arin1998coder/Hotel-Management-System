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
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


/**
 *
 * @author arin1
 */
public class UpdateCustomerStatus extends javax.swing.JFrame{

    private JLabel image;
    long pending=0;
    long originaltotal=0;
   String originalPaid="";
    boolean checkInPriceCalled;
    DateTimeFormatter dateFormatter ;
    long paid=0;
    public UpdateCustomerStatus() {
        
        initComponents();
        txtIdNum.setEditable(false);
        cbIdType.setEnabled(false);
        txtName.setEditable(false);
        txtContact.setEditable(false);
        txtEmail.setEditable(false);
        txtAddress.setEditable(false);
        txtRoomNo.setEditable(false);
        txtCheckIn.setEditable(false);
        dateFormatter = DateTimeFormatter.BASIC_ISO_DATE;
       
        
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    
    //to compare the Check In Date with current system date
    public boolean compareDates(String d1)
    {
        java.util.Date date = Calendar.getInstance().getTime();  
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");  
        String DateNow = dateFormat.format(date);  
        try{
            // If you already have date objects then skip 1

            //1
            // Create 2 dates starts
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
            java.util.Date date1 = sdf.parse(d1);
            java.util.Date date2 = sdf.parse(DateNow);

            System.out.println("Date1"+sdf.format(date1));
            System.out.println("Date2"+sdf.format(date2));
            System.out.println();

            // Create 2 dates ends
            //1

            // Date object is having 3 methods namely after,before and equals for comparing
            // after() will return true if and only if date1 is after date 2
            if(date1.after(date2)){
                System.out.println("Date1 is after Date2");
                return true;
            }
            // before() will return true if and only if date1 is before date2
            if(date1.before(date2)){
                System.out.println("Date1 is before Date2");
                return false;
            }

            //equals() returns true if both the dates are equal
            if(date1.equals(date2)){
                System.out.println("Date1 is equal Date2");
                return true;
            }

            System.out.println();
        }
        catch(ParseException ex){
            ex.printStackTrace();
        }
        return true;
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
    //based on the searched phone no , details of the customer linked to that phone no is displayed
    public void searchCustomerByPhoneNo(){
        String phno=txtPhoneNoSearch.getText();
        try {
            DbConnection c=new DbConnection();
            String sql="select * from customer where contact='"+phno+"' and checkOutDate is NULL";
            PreparedStatement pst = c.con.prepareStatement(sql);
            ResultSet rs=pst.executeQuery();
            boolean ableToFetch=false;
           
            while(rs.next()){
                ableToFetch = true;
                txtIdNum.setText(rs.getString(3));
                cbIdType.setSelectedItem(rs.getString(2));
                txtName.setText(rs.getString(4));
                txtContact.setText(rs.getString(5));
                txtEmail.setText(rs.getString(6));
                txtAddress.setText(rs.getString(9));
                txtRoomNo.setText(rs.getString(10));
                txtCheckIn.setText(rs.getString(11));
            }
            if(ableToFetch){
                txtPhoneNoSearch.setEditable(false);
                txtIdNum.setEditable(true);
            cbIdType.setEnabled(true);
            txtName.setEditable(true);
            txtContact.setEditable(true);
            txtEmail.setEditable(true);
            txtAddress.setEditable(true);
            }
            else{
                JOptionPane.showMessageDialog(null, "No Records Found");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    //Update the customer details
    public void update(){
         String phno=txtPhoneNoSearch.getText();
         String idtype=cbIdType.getSelectedItem().toString();
         String idNum=txtIdNum.getText();
         String name=txtName.getText();
         String contact=txtContact.getText();
         String email=txtEmail.getText();
         String address=txtAddress.getText();
         
         //Validations
         String regex = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+(?:\\.[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+)*@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$";  
        //Compile regular expression to get the pattern  
         Pattern pattern = Pattern.compile(regex);
         Matcher matcher = pattern.matcher(email);
        
        
          if(idNum.equals("") || name.equals("")||
                contact.equals("") || address.equals("") ){
            JOptionPane.showMessageDialog(null, "Please fill all the details");
            return;
        }
        else if(contact.equals("") || isStringContainsCharacter(contact)){
            JOptionPane.showMessageDialog(null, "Please provide a valid Contact Number");
            return;
        }
        
         else if(!matcher.matches()){
             JOptionPane.showMessageDialog(null, "Please provide a valid email address");
            return;
         }
         try {
            DbConnection c= new DbConnection();
            String sql="update customer set document='"+idtype+"',docNumber='"+idNum+"',name='"+name+"',contact='"+contact+"',email='"+email+"',address='"+address+"' where contact='"+txtPhoneNoSearch.getText()+"' and checkOutDate is NULL";
            PreparedStatement pst = c.con.prepareStatement(sql);
            int rowCount=pst.executeUpdate();
            if(rowCount>0){
                JOptionPane.showMessageDialog(null, "Customer Details Updated");
                this.setVisible(false);
                this.dispose();
                new UpdateCustomerStatus().setVisible(true);
            }
            else{
                JOptionPane.showMessageDialog(null, "Updation Failed");
            }
            
        } catch (Exception e) {
            
        }
    }
    
  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        btnUpdate = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtPhoneNoSearch = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtIdNum = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtContact = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtAddress = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtRoomNo = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtCheckIn = new javax.swing.JTextField();
        cbIdType = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(204, 255, 204));
        getContentPane().setLayout(null);

        jLabel2.setFont(new java.awt.Font("Segoe UI Black", 1, 24)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel2, org.openide.util.NbBundle.getMessage(UpdateCustomerStatus.class, "UpdateCustomerStatus.jLabel2.text")); // NOI18N
        getContentPane().add(jLabel2);
        jLabel2.setBounds(490, 30, 190, 30);

        org.openide.awt.Mnemonics.setLocalizedText(btnUpdate, org.openide.util.NbBundle.getMessage(UpdateCustomerStatus.class, "UpdateCustomerStatus.btnUpdate.text")); // NOI18N
        btnUpdate.setBackground(new java.awt.Color(0, 0, 0));
        btnUpdate.setForeground(new java.awt.Color(255, 255, 255));
        btnUpdate.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                btnUpdateFocusLost(evt);
            }
        });
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });
        getContentPane().add(btnUpdate);
        btnUpdate.setBounds(70, 570, 130, 40);

        org.openide.awt.Mnemonics.setLocalizedText(btnClear, org.openide.util.NbBundle.getMessage(UpdateCustomerStatus.class, "UpdateCustomerStatus.btnClear.text")); // NOI18N
        btnClear.setBackground(new java.awt.Color(0, 0, 0));
        btnClear.setForeground(new java.awt.Color(255, 255, 255));
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });
        getContentPane().add(btnClear);
        btnClear.setBounds(220, 570, 130, 40);

        org.openide.awt.Mnemonics.setLocalizedText(btnCancel, org.openide.util.NbBundle.getMessage(UpdateCustomerStatus.class, "UpdateCustomerStatus.btnCancel.text")); // NOI18N
        btnCancel.setBackground(new java.awt.Color(0, 0, 0));
        btnCancel.setForeground(new java.awt.Color(255, 255, 255));
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });
        getContentPane().add(btnCancel);
        btnCancel.setBounds(390, 570, 130, 40);

        jLabel1.setFont(new java.awt.Font("Tw Cen MT", 1, 18)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(UpdateCustomerStatus.class, "UpdateCustomerStatus.jLabel1.text")); // NOI18N
        getContentPane().add(jLabel1);
        jLabel1.setBounds(70, 230, 100, 40);

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Hotel Management System/src/icons/searchRoom.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel3, org.openide.util.NbBundle.getMessage(UpdateCustomerStatus.class, "UpdateCustomerStatus.jLabel3.text")); // NOI18N
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel3);
        jLabel3.setBounds(400, 110, 70, 70);

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel4, org.openide.util.NbBundle.getMessage(UpdateCustomerStatus.class, "UpdateCustomerStatus.jLabel4.text")); // NOI18N
        getContentPane().add(jLabel4);
        jLabel4.setBounds(70, 130, 100, 40);

        txtPhoneNoSearch.setText(org.openide.util.NbBundle.getMessage(UpdateCustomerStatus.class, "UpdateCustomerStatus.txtPhoneNoSearch.text")); // NOI18N
        txtPhoneNoSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPhoneNoSearchActionPerformed(evt);
            }
        });
        getContentPane().add(txtPhoneNoSearch);
        txtPhoneNoSearch.setBounds(160, 130, 220, 30);

        jLabel5.setFont(new java.awt.Font("Tw Cen MT", 1, 18)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel5, org.openide.util.NbBundle.getMessage(UpdateCustomerStatus.class, "UpdateCustomerStatus.jLabel5.text")); // NOI18N
        getContentPane().add(jLabel5);
        jLabel5.setBounds(70, 300, 100, 40);

        txtIdNum.setText(org.openide.util.NbBundle.getMessage(UpdateCustomerStatus.class, "UpdateCustomerStatus.txtIdNum.text")); // NOI18N
        txtIdNum.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdNumActionPerformed(evt);
            }
        });
        getContentPane().add(txtIdNum);
        txtIdNum.setBounds(210, 300, 240, 40);

        jLabel6.setFont(new java.awt.Font("Tw Cen MT", 1, 18)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel6, org.openide.util.NbBundle.getMessage(UpdateCustomerStatus.class, "UpdateCustomerStatus.jLabel6.text")); // NOI18N
        getContentPane().add(jLabel6);
        jLabel6.setBounds(70, 370, 100, 40);

        txtName.setText(org.openide.util.NbBundle.getMessage(UpdateCustomerStatus.class, "UpdateCustomerStatus.txtName.text")); // NOI18N
        txtName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNameActionPerformed(evt);
            }
        });
        getContentPane().add(txtName);
        txtName.setBounds(210, 370, 240, 40);

        jLabel7.setFont(new java.awt.Font("Tw Cen MT", 1, 18)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel7, org.openide.util.NbBundle.getMessage(UpdateCustomerStatus.class, "UpdateCustomerStatus.jLabel7.text")); // NOI18N
        getContentPane().add(jLabel7);
        jLabel7.setBounds(70, 440, 100, 40);

        txtContact.setText(org.openide.util.NbBundle.getMessage(UpdateCustomerStatus.class, "UpdateCustomerStatus.txtContact.text")); // NOI18N
        txtContact.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtContactActionPerformed(evt);
            }
        });
        getContentPane().add(txtContact);
        txtContact.setBounds(210, 440, 240, 40);

        jLabel8.setFont(new java.awt.Font("Tw Cen MT", 1, 18)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel8, org.openide.util.NbBundle.getMessage(UpdateCustomerStatus.class, "UpdateCustomerStatus.jLabel8.text")); // NOI18N
        getContentPane().add(jLabel8);
        jLabel8.setBounds(560, 230, 100, 40);

        txtEmail.setText(org.openide.util.NbBundle.getMessage(UpdateCustomerStatus.class, "UpdateCustomerStatus.txtEmail.text")); // NOI18N
        txtEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEmailActionPerformed(evt);
            }
        });
        getContentPane().add(txtEmail);
        txtEmail.setBounds(700, 230, 240, 40);

        jLabel9.setFont(new java.awt.Font("Tw Cen MT", 1, 18)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel9, org.openide.util.NbBundle.getMessage(UpdateCustomerStatus.class, "UpdateCustomerStatus.jLabel9.text")); // NOI18N
        getContentPane().add(jLabel9);
        jLabel9.setBounds(560, 300, 100, 40);

        txtAddress.setText(org.openide.util.NbBundle.getMessage(UpdateCustomerStatus.class, "UpdateCustomerStatus.txtAddress.text")); // NOI18N
        txtAddress.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAddressActionPerformed(evt);
            }
        });
        getContentPane().add(txtAddress);
        txtAddress.setBounds(700, 300, 240, 40);

        jLabel10.setFont(new java.awt.Font("Tw Cen MT", 1, 18)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel10, org.openide.util.NbBundle.getMessage(UpdateCustomerStatus.class, "UpdateCustomerStatus.jLabel10.text")); // NOI18N
        getContentPane().add(jLabel10);
        jLabel10.setBounds(560, 370, 100, 40);

        txtRoomNo.setText(org.openide.util.NbBundle.getMessage(UpdateCustomerStatus.class, "UpdateCustomerStatus.txtRoomNo.text")); // NOI18N
        txtRoomNo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtRoomNoActionPerformed(evt);
            }
        });
        getContentPane().add(txtRoomNo);
        txtRoomNo.setBounds(700, 370, 240, 40);

        jLabel11.setFont(new java.awt.Font("Tw Cen MT", 1, 18)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel11, org.openide.util.NbBundle.getMessage(UpdateCustomerStatus.class, "UpdateCustomerStatus.jLabel11.text")); // NOI18N
        getContentPane().add(jLabel11);
        jLabel11.setBounds(560, 440, 100, 40);

        txtCheckIn.setText(org.openide.util.NbBundle.getMessage(UpdateCustomerStatus.class, "UpdateCustomerStatus.txtCheckIn.text")); // NOI18N
        txtCheckIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCheckInActionPerformed(evt);
            }
        });
        getContentPane().add(txtCheckIn);
        txtCheckIn.setBounds(700, 440, 240, 40);

        cbIdType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Passport", "Voter ID", "Driving License", "Pan Card", "Aadhar" }));
        getContentPane().add(cbIdType);
        cbIdType.setBounds(210, 230, 240, 40);

        setSize(new java.awt.Dimension(1214, 753));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        update();

    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        this.dispose();
        new UpdateCustomerStatus().setVisible(true);
    }//GEN-LAST:event_btnClearActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        this.dispose();
        
    }//GEN-LAST:event_btnCancelActionPerformed

    private void btnUpdateFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_btnUpdateFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_btnUpdateFocusLost

    private void txtPhoneNoSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPhoneNoSearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPhoneNoSearchActionPerformed

    private void txtIdNumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdNumActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdNumActionPerformed

    private void txtNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNameActionPerformed

    private void txtContactActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtContactActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtContactActionPerformed

    private void txtEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEmailActionPerformed

    private void txtAddressActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAddressActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAddressActionPerformed

    private void txtRoomNoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtRoomNoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtRoomNoActionPerformed

    private void txtCheckInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCheckInActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCheckInActionPerformed

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
       searchCustomerByPhoneNo();
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
            java.util.logging.Logger.getLogger(UpdateCustomerStatus.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UpdateCustomerStatus.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UpdateCustomerStatus.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UpdateCustomerStatus.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UpdateCustomerStatus().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JComboBox<String> cbIdType;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTextField txtAddress;
    private javax.swing.JTextField txtCheckIn;
    private javax.swing.JTextField txtContact;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtIdNum;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtPhoneNoSearch;
    private javax.swing.JTextField txtRoomNo;
    // End of variables declaration//GEN-END:variables

}
