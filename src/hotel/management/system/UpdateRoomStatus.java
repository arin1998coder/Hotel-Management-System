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
import javax.swing.JFrame;
import javax.swing.JOptionPane;


/**
 *
 * @author arin1
 */
public class UpdateRoomStatus extends javax.swing.JFrame{

    private JLabel image;
    String roomno="";
    String cleanStatus="";
    DateTimeFormatter dateFormatter ;
    public UpdateRoomStatus() {
        
//        cbCleanStatus
        initComponents();
        AutoCompletion.enable(cbRoomNum); //makes the combobox editable and lets the user search for the item by entering in the text field
        lblcustomerRoomNo.setVisible(false);
        
        dateFormatter = DateTimeFormatter.BASIC_ISO_DATE;
        getContentPane().setBackground(Color.white);
        showAvailableRooms();
        
        
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    


    
     
    //gets the list of avaialable rooms and shows in the dropdown Allocated Room
    public void showAvailableRooms(){
        
         try{
             DbConnection c=new DbConnection();
             //query to display all the roomId's whose availability status is "available"
             String sql="Select RoomId from room";
             Statement st = c.con.createStatement();
             
             ResultSet rs = st.executeQuery(sql);
             
             while(rs.next()){
                String roomId=rs.getString("RoomId");
                cbRoomNum.addItem(roomId);
             }
             
             
         }
         catch(Exception e){
             e.printStackTrace();
         }
    }
    
    //when selected the document number from id number dropdown it shows the details of the customer havin the selected id num in the respective fields
    public boolean displayCustomerDetails(){
        boolean isValid=false;
        roomno=cbRoomNum.getSelectedItem().toString();
        //validation
        if(cbRoomNum.getSelectedItem().equals(null)){
            JOptionPane.showMessageDialog(null, "Please select a Room Num!");
            return false;
        }
        lblcustomerRoomNo.setText(cbRoomNum.getSelectedItem().toString());
        cbRoomNum.setVisible(false);
        lblcustomerRoomNo.setVisible(true);
        String sql="";
        String sql2="select count(*) from customer where allocatedRoom ='"+roomno+"'";
        int count=0;
        try {
            
            DbConnection c=new DbConnection();
            ResultSet rs=null;
            PreparedStatement pst=c.con.prepareStatement(sql2);
            rs=pst.executeQuery();
            while(rs.next()){
                count =rs.getInt(1);
            }
            System.out.println(count+" count");
            
            if(count>0){
                sql="Select c.name,c.docNumber,c.checkInDate,c.checkOutDate,r.CleaningStatus FROM customer as c INNER JOIN room as r ON c.allocatedRoom=r.RoomId where allocatedRoom ='"+roomno+"'";
            }
            else{
                sql="select * from room where RoomId ='"+roomno+"'";
            }
            pst = c.con.prepareStatement(sql);
            rs=pst.executeQuery();
             
            while(count==0 && rs.next()){
                isValid=true;
                lblName.setText("N/A");
                lblDocNum.setText("N/A");
                lblCheckIn.setText("N/A");
                lblCheckOut.setText("N/A");
                lblCurrentCleanStatus.setText(rs.getString("CleaningStatus"));
                
            }
            while(count>0 && rs.next()){
                isValid=true;
                lblName.setText(rs.getString(1));
                lblDocNum.setText(rs.getString(2));
                lblCheckIn.setText(rs.getString(3));
                lblCheckOut.setText(rs.getString(4));
                lblCurrentCleanStatus.setText(rs.getString(5));
                
            }
            
            
           
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isValid;
    }
    
    public boolean update(){
        boolean isUpdated = false;
        roomno=lblcustomerRoomNo.getText();
        cleanStatus = cbCleanStatus.getSelectedItem().toString();
        
        if(roomno.equals("")){
            JOptionPane.showMessageDialog(null, "Please select a Room Num and Click Get Details");
            return false;
        }
        
        try{
            DbConnection c= new DbConnection();
            PreparedStatement pst = c.con.prepareStatement("UPDATE room SET CleaningStatus ='"+cleanStatus+"' WHERE RoomId = '"+roomno+"'");
            int rowcount = pst.executeUpdate();
            if(rowcount>0){
                isUpdated=true;
                JOptionPane.showMessageDialog(null, "Cleaning Status Updated Successfully");
            }
            else{
                JOptionPane.showMessageDialog(null, "Cleaning Status Updattion Failed");
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return isUpdated;
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

  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        btnUpdate = new javax.swing.JButton();
        btnGetDetails = new javax.swing.JButton();
        lblDocNum = new javax.swing.JLabel();
        btnClear = new javax.swing.JButton();
        cbRoomNum = new javax.swing.JComboBox<>();
        btnCancel = new javax.swing.JButton();
        lblCheckIn = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        lblName = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        cbCleanStatus = new javax.swing.JComboBox<>();
        lblcustomerRoomNo = new javax.swing.JLabel();
        lblTotalPrice = new javax.swing.JLabel();
        lblCheckOut = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        lblCurrentCleanStatus = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(204, 255, 204));
        getContentPane().setLayout(null);

        jLabel2.setFont(new java.awt.Font("Segoe UI Black", 1, 24)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel2, org.openide.util.NbBundle.getMessage(UpdateRoomStatus.class, "UpdateRoomStatus.jLabel2.text")); // NOI18N
        getContentPane().add(jLabel2);
        jLabel2.setBounds(200, 30, 270, 30);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel3, org.openide.util.NbBundle.getMessage(UpdateRoomStatus.class, "UpdateRoomStatus.jLabel3.text")); // NOI18N
        getContentPane().add(jLabel3);
        jLabel3.setBounds(110, 270, 90, 30);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel4, org.openide.util.NbBundle.getMessage(UpdateRoomStatus.class, "UpdateRoomStatus.jLabel4.text")); // NOI18N
        getContentPane().add(jLabel4);
        jLabel4.setBounds(110, 220, 90, 30);

        org.openide.awt.Mnemonics.setLocalizedText(jLabel6, org.openide.util.NbBundle.getMessage(UpdateRoomStatus.class, "UpdateRoomStatus.jLabel6.text")); // NOI18N
        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        getContentPane().add(jLabel6);
        jLabel6.setBounds(110, 450, 90, 30);

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel7, org.openide.util.NbBundle.getMessage(UpdateRoomStatus.class, "UpdateRoomStatus.jLabel7.text")); // NOI18N
        getContentPane().add(jLabel7);
        jLabel7.setBounds(110, 510, 110, 30);

        org.openide.awt.Mnemonics.setLocalizedText(btnUpdate, org.openide.util.NbBundle.getMessage(UpdateRoomStatus.class, "UpdateRoomStatus.btnUpdate.text")); // NOI18N
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
        btnUpdate.setBounds(240, 630, 130, 40);

        org.openide.awt.Mnemonics.setLocalizedText(btnGetDetails, org.openide.util.NbBundle.getMessage(UpdateRoomStatus.class, "UpdateRoomStatus.btnGetDetails.text")); // NOI18N
        btnGetDetails.setBackground(new java.awt.Color(0, 0, 0));
        btnGetDetails.setForeground(new java.awt.Color(255, 255, 255));
        btnGetDetails.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGetDetailsActionPerformed(evt);
            }
        });
        getContentPane().add(btnGetDetails);
        btnGetDetails.setBounds(100, 630, 120, 40);

        org.openide.awt.Mnemonics.setLocalizedText(lblDocNum, org.openide.util.NbBundle.getMessage(UpdateRoomStatus.class, "UpdateRoomStatus.lblDocNum.text")); // NOI18N
        lblDocNum.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        getContentPane().add(lblDocNum);
        lblDocNum.setBounds(310, 270, 300, 30);

        org.openide.awt.Mnemonics.setLocalizedText(btnClear, org.openide.util.NbBundle.getMessage(UpdateRoomStatus.class, "UpdateRoomStatus.btnClear.text")); // NOI18N
        btnClear.setBackground(new java.awt.Color(0, 0, 0));
        btnClear.setForeground(new java.awt.Color(255, 255, 255));
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });
        getContentPane().add(btnClear);
        btnClear.setBounds(390, 630, 130, 40);

        cbRoomNum.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbRoomNumActionPerformed(evt);
            }
        });
        getContentPane().add(cbRoomNum);
        cbRoomNum.setBounds(310, 170, 300, 30);

        org.openide.awt.Mnemonics.setLocalizedText(btnCancel, org.openide.util.NbBundle.getMessage(UpdateRoomStatus.class, "UpdateRoomStatus.btnCancel.text")); // NOI18N
        btnCancel.setBackground(new java.awt.Color(0, 0, 0));
        btnCancel.setForeground(new java.awt.Color(255, 255, 255));
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });
        getContentPane().add(btnCancel);
        btnCancel.setBounds(540, 630, 130, 40);

        org.openide.awt.Mnemonics.setLocalizedText(lblCheckIn, org.openide.util.NbBundle.getMessage(UpdateRoomStatus.class, "UpdateRoomStatus.lblCheckIn.text")); // NOI18N
        getContentPane().add(lblCheckIn);
        lblCheckIn.setBounds(310, 450, 300, 30);

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel9, org.openide.util.NbBundle.getMessage(UpdateRoomStatus.class, "UpdateRoomStatus.jLabel9.text")); // NOI18N
        getContentPane().add(jLabel9);
        jLabel9.setBounds(110, 170, 100, 30);

        org.openide.awt.Mnemonics.setLocalizedText(lblName, org.openide.util.NbBundle.getMessage(UpdateRoomStatus.class, "UpdateRoomStatus.lblName.text")); // NOI18N
        getContentPane().add(lblName);
        lblName.setBounds(310, 220, 300, 30);

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel12, org.openide.util.NbBundle.getMessage(UpdateRoomStatus.class, "UpdateRoomStatus.jLabel12.text")); // NOI18N
        getContentPane().add(jLabel12);
        jLabel12.setBounds(110, 320, 160, 30);

        cbCleanStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Cleaned", "Dirty" }));
        getContentPane().add(cbCleanStatus);
        cbCleanStatus.setBounds(310, 320, 300, 30);

        org.openide.awt.Mnemonics.setLocalizedText(lblcustomerRoomNo, org.openide.util.NbBundle.getMessage(UpdateRoomStatus.class, "UpdateRoomStatus.lblcustomerRoomNo.text")); // NOI18N
        getContentPane().add(lblcustomerRoomNo);
        lblcustomerRoomNo.setBounds(310, 170, 300, 30);

        org.openide.awt.Mnemonics.setLocalizedText(lblTotalPrice, org.openide.util.NbBundle.getMessage(UpdateRoomStatus.class, "UpdateRoomStatus.lblTotalPrice.text")); // NOI18N
        getContentPane().add(lblTotalPrice);
        lblTotalPrice.setBounds(270, 620, 310, 30);

        org.openide.awt.Mnemonics.setLocalizedText(lblCheckOut, org.openide.util.NbBundle.getMessage(UpdateRoomStatus.class, "UpdateRoomStatus.lblCheckOut.text")); // NOI18N
        getContentPane().add(lblCheckOut);
        lblCheckOut.setBounds(310, 510, 300, 30);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(UpdateRoomStatus.class, "UpdateRoomStatus.jLabel1.text")); // NOI18N
        getContentPane().add(jLabel1);
        jLabel1.setBounds(110, 386, 120, 40);

        org.openide.awt.Mnemonics.setLocalizedText(lblCurrentCleanStatus, org.openide.util.NbBundle.getMessage(UpdateRoomStatus.class, "UpdateRoomStatus.lblCurrentCleanStatus.text")); // NOI18N
        getContentPane().add(lblCurrentCleanStatus);
        lblCurrentCleanStatus.setBounds(310, 400, 300, 30);

        setSize(new java.awt.Dimension(1214, 812));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnGetDetailsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGetDetailsActionPerformed
        displayCustomerDetails();
           
    }//GEN-LAST:event_btnGetDetailsActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
          if(update()){
              this.dispose();
              new Rooms().setVisible(true);
              if(cleanStatus.equals("Dirty"))
                   JOptionPane.showMessageDialog(null, "Room No: "+roomno+" needs to be Cleaned");
              else
                  JOptionPane.showMessageDialog(null, "Room No: "+roomno+" is Cleaned");
          }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        this.dispose();
        new UpdateRoomStatus().setVisible(true);
    }//GEN-LAST:event_btnClearActionPerformed

    private void cbRoomNumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbRoomNumActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbRoomNumActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        this.dispose();
       
    }//GEN-LAST:event_btnCancelActionPerformed

    private void btnUpdateFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_btnUpdateFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_btnUpdateFocusLost

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
            java.util.logging.Logger.getLogger(UpdateRoomStatus.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UpdateRoomStatus.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UpdateRoomStatus.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UpdateRoomStatus.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UpdateRoomStatus().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnGetDetails;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JComboBox<String> cbCleanStatus;
    private javax.swing.JComboBox<String> cbRoomNum;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel lblCheckIn;
    private javax.swing.JLabel lblCheckOut;
    private javax.swing.JLabel lblCurrentCleanStatus;
    private javax.swing.JLabel lblDocNum;
    private javax.swing.JLabel lblName;
    private javax.swing.JLabel lblTotalPrice;
    private javax.swing.JLabel lblcustomerRoomNo;
    // End of variables declaration//GEN-END:variables

}
