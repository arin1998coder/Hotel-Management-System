/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package hotel.management.system;

import java.sql.ResultSet;
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class SearchCustomer extends javax.swing.JFrame {
    String docIdNo;
    DefaultTableModel model;
    public SearchCustomer() {
        initComponents();
        showCustomersTable();
    }
    
      //fetches Customer details from customer table of db and displays in tablemodel in Jframe
    public void showCustomersTable(){
        
        
        try {
            DbConnection c= new DbConnection();
            String query="select * from customer";
            Statement st = c.con.createStatement();
            ResultSet rs = st.executeQuery(query);
            while(rs.next()){
                System.out.println(1);
                String docType=rs.getString(2);
                String docNum=rs.getString(3);
                String name=rs.getString(4);
                String contact=rs.getString(5);
                String email=rs.getString(6);
                String gender=rs.getString(7);
                String country=rs.getString(8);
                String address=rs.getString(9);
                String room=rs.getString(10);
                String checkIn=rs.getString("checkInDate");
                Object o=rs.getString("checkOutDate");
                String checkOut="";
                if(o==null){
                    checkOut="";
                }
                else{
                checkOut=o.toString();
                }
                String deposit=rs.getString(15);
                
                
                Object[] obj={docType,docNum,name,contact,email,gender,country,address,room,checkIn,checkOut,deposit}; //as add row method takes an obj array, we declare an obj array
                model=(DefaultTableModel)customerTbl.getModel(); //we are getting a model obj that will represent the bookdetails table
                //using this model obj we can add rows to our bookdetails table
                model.addRow(obj);
               
                
                
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtNameorEmail = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        customerTbl = new rojerusan.RSTableMetro();
        datePickerTo = new com.github.lgooddatepicker.components.DatePicker();
        datePickerFrom = new com.github.lgooddatepicker.components.DatePicker();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        rSMaterialButtonCircle1 = new necesario.RSMaterialButtonCircle();
        rSMaterialButtonCircle2 = new necesario.RSMaterialButtonCircle();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Verdana", 1, 24)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(SearchCustomer.class, "SearchCustomer.jLabel1.text")); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 20, 260, 40));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel2, org.openide.util.NbBundle.getMessage(SearchCustomer.class, "SearchCustomer.jLabel2.text")); // NOI18N
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 110, 70, 30));

        txtNameorEmail.setText(org.openide.util.NbBundle.getMessage(SearchCustomer.class, "SearchCustomer.txtNameorEmail.text")); // NOI18N
        txtNameorEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNameorEmailActionPerformed(evt);
            }
        });
        getContentPane().add(txtNameorEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 110, 200, 30));

        customerTbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Doc Type", "Doc No", "Name", "Contact", "Email", "Gender", "Country", "Address", "Room", "CheckIn", "CheckOut", "Deposit"
            }
        ));
        jScrollPane1.setViewportView(customerTbl);
        if (customerTbl.getColumnModel().getColumnCount() > 0) {
            customerTbl.getColumnModel().getColumn(0).setHeaderValue(org.openide.util.NbBundle.getMessage(SearchCustomer.class, "SearchCustomer.customerTbl.columnModel.title0")); // NOI18N
            customerTbl.getColumnModel().getColumn(1).setHeaderValue(org.openide.util.NbBundle.getMessage(SearchCustomer.class, "SearchCustomer.customerTbl.columnModel.title1")); // NOI18N
            customerTbl.getColumnModel().getColumn(2).setHeaderValue(org.openide.util.NbBundle.getMessage(SearchCustomer.class, "SearchCustomer.customerTbl.columnModel.title2")); // NOI18N
            customerTbl.getColumnModel().getColumn(3).setHeaderValue(org.openide.util.NbBundle.getMessage(SearchCustomer.class, "SearchCustomer.customerTbl.columnModel.title3")); // NOI18N
            customerTbl.getColumnModel().getColumn(4).setHeaderValue(org.openide.util.NbBundle.getMessage(SearchCustomer.class, "SearchCustomer.customerTbl.columnModel.title4")); // NOI18N
            customerTbl.getColumnModel().getColumn(5).setHeaderValue(org.openide.util.NbBundle.getMessage(SearchCustomer.class, "SearchCustomer.customerTbl.columnModel.title5")); // NOI18N
            customerTbl.getColumnModel().getColumn(6).setHeaderValue(org.openide.util.NbBundle.getMessage(SearchCustomer.class, "SearchCustomer.customerTbl.columnModel.title6")); // NOI18N
            customerTbl.getColumnModel().getColumn(7).setHeaderValue(org.openide.util.NbBundle.getMessage(SearchCustomer.class, "SearchCustomer.customerTbl.columnModel.title7")); // NOI18N
            customerTbl.getColumnModel().getColumn(8).setHeaderValue(org.openide.util.NbBundle.getMessage(SearchCustomer.class, "SearchCustomer.customerTbl.columnModel.title8")); // NOI18N
            customerTbl.getColumnModel().getColumn(9).setHeaderValue(org.openide.util.NbBundle.getMessage(SearchCustomer.class, "SearchCustomer.customerTbl.columnModel.title9")); // NOI18N
            customerTbl.getColumnModel().getColumn(10).setHeaderValue(org.openide.util.NbBundle.getMessage(SearchCustomer.class, "SearchCustomer.customerTbl.columnModel.title10")); // NOI18N
            customerTbl.getColumnModel().getColumn(11).setHeaderValue(org.openide.util.NbBundle.getMessage(SearchCustomer.class, "SearchCustomer.customerTbl.columnModel.title11")); // NOI18N
        }

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, 1280, 480));
        getContentPane().add(datePickerTo, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 110, 170, 30));
        getContentPane().add(datePickerFrom, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 110, 160, 30));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel3, org.openide.util.NbBundle.getMessage(SearchCustomer.class, "SearchCustomer.jLabel3.text")); // NOI18N
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 116, 150, 20));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel4, org.openide.util.NbBundle.getMessage(SearchCustomer.class, "SearchCustomer.jLabel4.text")); // NOI18N
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 110, 80, 30));

        org.openide.awt.Mnemonics.setLocalizedText(rSMaterialButtonCircle1, org.openide.util.NbBundle.getMessage(SearchCustomer.class, "SearchCustomer.rSMaterialButtonCircle1.text")); // NOI18N
        rSMaterialButtonCircle1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSMaterialButtonCircle1ActionPerformed(evt);
            }
        });
        getContentPane().add(rSMaterialButtonCircle1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1130, 100, 130, 50));

        org.openide.awt.Mnemonics.setLocalizedText(rSMaterialButtonCircle2, org.openide.util.NbBundle.getMessage(SearchCustomer.class, "SearchCustomer.rSMaterialButtonCircle2.text")); // NOI18N
        rSMaterialButtonCircle2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSMaterialButtonCircle2ActionPerformed(evt);
            }
        });
        getContentPane().add(rSMaterialButtonCircle2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 100, 130, 50));

        setSize(new java.awt.Dimension(1314, 686));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void rSMaterialButtonCircle1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSMaterialButtonCircle1ActionPerformed
        datePickerFrom.setText("");
        datePickerTo.setText("");
        txtNameorEmail.setText("");
        model.setRowCount(0);
        showCustomersTable();
    }//GEN-LAST:event_rSMaterialButtonCircle1ActionPerformed

    private void rSMaterialButtonCircle2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSMaterialButtonCircle2ActionPerformed
        String text=txtNameorEmail.getText();
        String query="";
        String dateFrom=datePickerFrom.getDateStringOrEmptyString();
        String dateTo=datePickerTo.getDateStringOrEmptyString();
        System.out.println(dateFrom);
        System.out.println(dateTo);
        PreparedStatement pst=null;
        model.setRowCount(0);
        try {
            DbConnection c=new DbConnection();
            if(!text.equals("")){
                //show only those records whose name either starts with "text" or email starts with "text"
            query="select * from customer WHERE name LIKE '"+text+"%' or email like '"+text+"%'";
            pst = c.con.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            boolean found =false;
             while(rs.next()){
                 found =true;
                System.out.println(1);
                String docType=rs.getString(2);
                String docNum=rs.getString(3);
                String name=rs.getString(4);
                String contact=rs.getString(5);
                String email=rs.getString(6);
                String gender=rs.getString(7);
                String country=rs.getString(8);
                String address=rs.getString(9);
                String room=rs.getString(10);
                String checkIn=rs.getString("checkInDate");
                Object o=rs.getString("checkOutDate");
                String checkOut="";
                if(o==null){
                    checkOut="";
                }
                else{
                checkOut=o.toString();
                }
                String deposit=rs.getString(15);
                
                
                Object[] obj={docType,docNum,name,contact,email,gender,country,address,room,checkIn,checkOut,deposit}; //as add row method takes an obj array, we declare an obj array
                model=(DefaultTableModel)customerTbl.getModel(); //we are getting a model obj that will represent the bookdetails table
                //using this model obj we can add rows to our bookdetails table
                model.addRow(obj);
               
                
                
            }
              if(!found){
                 JOptionPane.showMessageDialog(null, "No Records Found");
             }
            }
            else if(!dateFrom.equals("") && !dateTo.equals("")){
                query="select * from customer where DATE(checkInDate) between '"+dateFrom+"' and '"+dateTo+"'";
                pst = c.con.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            boolean found=false;
             while(rs.next()){
                 found =true;
                System.out.println(1);
                String docType=rs.getString(2);
                String docNum=rs.getString(3);
                String name=rs.getString(4);
                String contact=rs.getString(5);
                String email=rs.getString(6);
                String gender=rs.getString(7);
                String country=rs.getString(8);
                String address=rs.getString(9);
                String room=rs.getString(10);
                String checkIn=rs.getString("checkInDate");
                Object o=rs.getString("checkOutDate");
                String checkOut="";
                if(o==null){
                    checkOut="";
                }
                else{
                checkOut=o.toString();
                }
                String deposit=rs.getString(15);
                
                
                Object[] obj={docType,docNum,name,contact,email,gender,country,address,room,checkIn,checkOut,deposit}; //as add row method takes an obj array, we declare an obj array
                model=(DefaultTableModel)customerTbl.getModel(); //we are getting a model obj that will represent the bookdetails table
                //using this model obj we can add rows to our bookdetails table
                model.addRow(obj);
             }
             if(!found){
                 JOptionPane.showMessageDialog(null, "No Records Found");
             }
            }
            else{
                JOptionPane.showMessageDialog(null, "Please select valid filter to Search");
            }
            
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_rSMaterialButtonCircle2ActionPerformed

    private void txtNameorEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNameorEmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNameorEmailActionPerformed

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
            java.util.logging.Logger.getLogger(SearchCustomer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SearchCustomer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SearchCustomer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SearchCustomer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SearchCustomer().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rojerusan.RSTableMetro customerTbl;
    private com.github.lgooddatepicker.components.DatePicker datePickerFrom;
    private com.github.lgooddatepicker.components.DatePicker datePickerTo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private necesario.RSMaterialButtonCircle rSMaterialButtonCircle1;
    private necesario.RSMaterialButtonCircle rSMaterialButtonCircle2;
    private javax.swing.JTextField txtNameorEmail;
    // End of variables declaration//GEN-END:variables
}
