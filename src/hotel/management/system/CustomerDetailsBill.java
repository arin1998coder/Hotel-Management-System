/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package hotel.management.system;

import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;
import hotel.management.system.CheckOut;
import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class CustomerDetailsBill extends javax.swing.JFrame {

   DefaultTableModel model;
   String dateTo,dateFrom;
    public CustomerDetailsBill() {
        initComponents();
        checkOutDateFrom.setDateToToday();
        checkOutDateTo.setDateToToday();
        showCheckedOutCustomersTable();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    
    //converts 21 December 2022 to 2022-12-21
    public String convertDateToYYYY_MM_DDFormat(String d){
        java.util.Date date = new Date(d);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String format = formatter.format(date);
        System.out.println(format);
        return format; //YYYY-MM-DD
    }
    
    //to compare the Check In Date with current system date
    public boolean compareDates(String dateFrom,String dateTo)
    { 
        try{
            // If you already have date objects then skip 1

            //1
            // Create 2 dates starts
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date dateF = sdf.parse(dateFrom);
            Date dateT = sdf.parse(dateTo);

            System.out.println("Date1"+sdf.format(dateF));
            System.out.println("Date2"+sdf.format(dateT));
            System.out.println();

            // Create 2 dates ends
            //1

            // Date object is having 3 methods namely after,before and equals for comparing
            // after() will return true if and only if date1 is after date 2
            if(dateF.after(dateT)){
                System.out.println("DateF is after DateT");
                return false;
            }
            // before() will return true if and only if date1 is before date2
            if(dateF.before(dateT)){
                System.out.println("DateF is before DateT");
                return true;
            }

            //equals() returns true if both the dates are equal
            if(dateF.equals(dateT)){
                System.out.println("DateF is equal DateT");
                return true;
            }

            System.out.println();
        }
        catch(ParseException ex){
            ex.printStackTrace();
        }
        return true;
    }
      //fetches information of checkedout customer details from customer table of db and displays in tablemodel in Jframe
    public void showCheckedOutCustomersTable(){
        
        
        try {
            DbConnection c= new DbConnection();
            //selects all the columns from customer table and roomType and BedType cols from room table where RoomId in cus table = roomId in room table
            String query="select cus.*,room.RoomType,room.BedType from customer as cus join room on cus.AllocatedRoom=room.RoomId where cus.checkOutDate is not NULL";
            Statement st = c.con.createStatement();
            ResultSet rs = st.executeQuery(query);
            while(rs.next()){
                System.out.println(1);
                String checkIn=rs.getString(11);
                String checkOut=rs.getString(12);
                //Calculating No. of days stayed
                long diff=new CheckOut().getDiffOfDatesInHours(checkIn, checkOut);
                float daysInFloat=(float)diff/24F;
                int daysinInt=Math.round(daysInFloat);
                if(daysinInt==0){
                    daysinInt=1;
                }
                
                Object[] obj={rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10),rs.getString(16),rs.getString(17),rs.getString(11),rs.getString(12),rs.getString(13),rs.getString(14),String.valueOf(daysinInt)}; //as add row method takes an obj array, we declare an obj array
                model=(DefaultTableModel)customerTable.getModel(); //we are getting a model obj that will represent the bookdetails table
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
        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        checkOutDateFrom = new com.github.lgooddatepicker.components.DatePicker();
        jScrollPane1 = new javax.swing.JScrollPane();
        customerTable = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        checkOutDateTo = new com.github.lgooddatepicker.components.DatePicker();
        jLabel4 = new javax.swing.JLabel();
        rSMaterialButtonCircle1 = new rojerusan.RSMaterialButtonCircle();
        rSMaterialButtonCircle2 = new rojerusan.RSMaterialButtonCircle();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(CustomerDetailsBill.class, "CustomerDetailsBill.jLabel1.text")); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 110, 150, 30));

        org.openide.awt.Mnemonics.setLocalizedText(jButton1, org.openide.util.NbBundle.getMessage(CustomerDetailsBill.class, "CustomerDetailsBill.jButton1.text")); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1310, 20, 100, 30));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel2, org.openide.util.NbBundle.getMessage(CustomerDetailsBill.class, "CustomerDetailsBill.jLabel2.text")); // NOI18N
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 20, 190, 30));
        getContentPane().add(checkOutDateFrom, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 110, 200, 30));

        customerTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Document Type", "Doc ID No", "Name", "Phone", "Email", "Gender", "Country", "Address", "Room", "Room Type ", "Bed Type", "Check In", "Check Out", "Price/day", "Total Amount", "No Of Days"
            }
        ));
        customerTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                customerTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(customerTable);
        if (customerTable.getColumnModel().getColumnCount() > 0) {
            customerTable.getColumnModel().getColumn(0).setHeaderValue(org.openide.util.NbBundle.getMessage(CustomerDetailsBill.class, "CustomerDetailsBill.customerTable.columnModel.title0")); // NOI18N
            customerTable.getColumnModel().getColumn(1).setHeaderValue(org.openide.util.NbBundle.getMessage(CustomerDetailsBill.class, "CustomerDetailsBill.customerTable.columnModel.title1")); // NOI18N
            customerTable.getColumnModel().getColumn(2).setHeaderValue(org.openide.util.NbBundle.getMessage(CustomerDetailsBill.class, "CustomerDetailsBill.customerTable.columnModel.title2")); // NOI18N
            customerTable.getColumnModel().getColumn(3).setHeaderValue(org.openide.util.NbBundle.getMessage(CustomerDetailsBill.class, "CustomerDetailsBill.customerTable.columnModel.title3")); // NOI18N
            customerTable.getColumnModel().getColumn(4).setHeaderValue(org.openide.util.NbBundle.getMessage(CustomerDetailsBill.class, "CustomerDetailsBill.customerTable.columnModel.title4")); // NOI18N
            customerTable.getColumnModel().getColumn(5).setHeaderValue(org.openide.util.NbBundle.getMessage(CustomerDetailsBill.class, "CustomerDetailsBill.customerTable.columnModel.title5")); // NOI18N
            customerTable.getColumnModel().getColumn(6).setHeaderValue(org.openide.util.NbBundle.getMessage(CustomerDetailsBill.class, "CustomerDetailsBill.customerTable.columnModel.title6")); // NOI18N
            customerTable.getColumnModel().getColumn(7).setHeaderValue(org.openide.util.NbBundle.getMessage(CustomerDetailsBill.class, "CustomerDetailsBill.customerTable.columnModel.title7")); // NOI18N
            customerTable.getColumnModel().getColumn(8).setHeaderValue(org.openide.util.NbBundle.getMessage(CustomerDetailsBill.class, "CustomerDetailsBill.customerTable.columnModel.title8")); // NOI18N
            customerTable.getColumnModel().getColumn(9).setHeaderValue(org.openide.util.NbBundle.getMessage(CustomerDetailsBill.class, "CustomerDetailsBill.customerTable.columnModel.title9")); // NOI18N
            customerTable.getColumnModel().getColumn(10).setHeaderValue(org.openide.util.NbBundle.getMessage(CustomerDetailsBill.class, "CustomerDetailsBill.customerTable.columnModel.title15")); // NOI18N
            customerTable.getColumnModel().getColumn(11).setHeaderValue(org.openide.util.NbBundle.getMessage(CustomerDetailsBill.class, "CustomerDetailsBill.customerTable.columnModel.title16")); // NOI18N
            customerTable.getColumnModel().getColumn(12).setHeaderValue(org.openide.util.NbBundle.getMessage(CustomerDetailsBill.class, "CustomerDetailsBill.customerTable.columnModel.title10")); // NOI18N
            customerTable.getColumnModel().getColumn(13).setHeaderValue(org.openide.util.NbBundle.getMessage(CustomerDetailsBill.class, "CustomerDetailsBill.customerTable.columnModel.title11")); // NOI18N
            customerTable.getColumnModel().getColumn(14).setHeaderValue(org.openide.util.NbBundle.getMessage(CustomerDetailsBill.class, "CustomerDetailsBill.customerTable.columnModel.title12")); // NOI18N
            customerTable.getColumnModel().getColumn(15).setHeaderValue(org.openide.util.NbBundle.getMessage(CustomerDetailsBill.class, "CustomerDetailsBill.customerTable.columnModel.title13")); // NOI18N
            customerTable.getColumnModel().getColumn(16).setHeaderValue(org.openide.util.NbBundle.getMessage(CustomerDetailsBill.class, "CustomerDetailsBill.customerTable.columnModel.title14")); // NOI18N
        }

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, 1410, 440));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(204, 0, 204));
        org.openide.awt.Mnemonics.setLocalizedText(jLabel3, org.openide.util.NbBundle.getMessage(CustomerDetailsBill.class, "CustomerDetailsBill.jLabel3.text")); // NOI18N
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 670, -1, 30));
        getContentPane().add(checkOutDateTo, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 110, 200, 30));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel4, org.openide.util.NbBundle.getMessage(CustomerDetailsBill.class, "CustomerDetailsBill.jLabel4.text")); // NOI18N
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 110, 150, 30));

        org.openide.awt.Mnemonics.setLocalizedText(rSMaterialButtonCircle1, org.openide.util.NbBundle.getMessage(CustomerDetailsBill.class, "CustomerDetailsBill.rSMaterialButtonCircle1.text")); // NOI18N
        rSMaterialButtonCircle1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSMaterialButtonCircle1ActionPerformed(evt);
            }
        });
        getContentPane().add(rSMaterialButtonCircle1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1190, 100, 130, 50));

        org.openide.awt.Mnemonics.setLocalizedText(rSMaterialButtonCircle2, org.openide.util.NbBundle.getMessage(CustomerDetailsBill.class, "CustomerDetailsBill.rSMaterialButtonCircle2.text")); // NOI18N
        rSMaterialButtonCircle2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSMaterialButtonCircle2ActionPerformed(evt);
            }
        });
        getContentPane().add(rSMaterialButtonCircle2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 100, 130, 50));

        setSize(new java.awt.Dimension(1462, 757));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void rSMaterialButtonCircle1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSMaterialButtonCircle1ActionPerformed
          try {
            DbConnection c= new DbConnection();
            //selects all the columns from customer table and roomType and BedType cols from room table where RoomId in cus table = roomId in room table
            String query="select cus.*,room.RoomType,room.BedType from customer as cus join room on cus.AllocatedRoom=room.RoomId where cus.checkOutDate is not NULL";
            Statement st = c.con.createStatement();
            ResultSet rs = st.executeQuery(query);
             model.setRowCount(0);
            while(rs.next()){
                System.out.println(1);
                String checkIn=rs.getString(11);
                String checkOut=rs.getString(12);
                //Calculating No. of days stayed
                long diff=new CheckOut().getDiffOfDatesInHours(checkIn, checkOut);
                float daysInFloat=(float)diff/24F;
                int daysinInt=Math.round(daysInFloat);
                if(daysinInt==0){
                    daysinInt=1;
                }
                
                Object[] obj={rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10),rs.getString(16),rs.getString(17),rs.getString(11),rs.getString(12),rs.getString(13),rs.getString(14),String.valueOf(daysinInt)}; //as add row method takes an obj array, we declare an obj array
                model=(DefaultTableModel)customerTable.getModel(); //we are getting a model obj that will represent the bookdetails table
                //using this model obj we can add rows to our bookdetails table
                model.addRow(obj);     
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_rSMaterialButtonCircle1ActionPerformed

    private void rSMaterialButtonCircle2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSMaterialButtonCircle2ActionPerformed
        // TODO add your handling code here:
        dateFrom=checkOutDateFrom.getDateStringOrEmptyString();
        dateTo=checkOutDateTo.getDateStringOrEmptyString();
        //validations
        if(dateFrom.equals("") || dateTo.equals("")){
            JOptionPane.showMessageDialog(null, "Please provide Date From and Date To to search");
            return;
        }
        else if(!compareDates(dateFrom, dateTo)){
            JOptionPane.showMessageDialog(null, "From Date should be less than To Date");
            return;
        }
        try {
            DbConnection c= new DbConnection();
            //selects all the columns from customer table and roomType and BedType cols from room table where RoomId in cus table = roomId in room table
            String query="select cus.*,room.RoomType,room.BedType from customer as cus join room on cus.AllocatedRoom=room.RoomId where cus.checkOutDate is not NULL and DATE(cus.checkOutDate) between '"+dateFrom+"' and '"+dateTo+"'";
            Statement st = c.con.createStatement();
            ResultSet rs = st.executeQuery(query);
            model.setRowCount(0);
            boolean found=false;
            while(rs.next()){
                found=true;
                System.out.println(1);
                String checkIn=rs.getString(11);
                String checkOut=rs.getString(12);
                //Calculating No. of days stayed
                long diff=new CheckOut().getDiffOfDatesInHours(checkIn, checkOut);
                float daysInFloat=(float)diff/24F;
                int daysinInt=Math.round(daysInFloat);
                
                Object[] obj={rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10),rs.getString(16),rs.getString(17),rs.getString(11),rs.getString(12),rs.getString(13),rs.getString(14),String.valueOf(daysinInt)}; //as add row method takes an obj array, we declare an obj array
                model=(DefaultTableModel)customerTable.getModel(); //we are getting a model obj that will represent the bookdetails table
                //using this model obj we can add rows to our bookdetails table
                model.addRow(obj); 
            }
            if(!found){
                JOptionPane.showMessageDialog(null, "No Records Found");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_rSMaterialButtonCircle2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed
    //on clicking on a specific row of the customer table
    //the bill of that customer should get opened and displayed to us
    private void customerTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_customerTableMouseClicked
       int index=customerTable.getSelectedRow();
        model=(DefaultTableModel) customerTable.getModel();
        String cusID=model.getValueAt(index, 0).toString(); //gets the entry at selected row at col 0
        String cusName=model.getValueAt(index, 3).toString(); //gets the cusname of the selected row
        try {
            int a=JOptionPane.showConfirmDialog(null, "Do you want to open the bill of :"+cusName, "Select", JOptionPane.YES_NO_OPTION);
            if(a==0){
            //if the file with name "cusId.pdf" exists then open that file
            if((new File("D:\\"+cusID+".pdf")).exists()){
               Process p = Runtime.getRuntime()
                            .exec("rundll32 url.dll,FileProtocolHandler D:\\"+cusID+".pdf");
            }
            else{
                JOptionPane.showMessageDialog(null, "File Does not Exist");
            }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_customerTableMouseClicked

    
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
            java.util.logging.Logger.getLogger(CustomerDetailsBill.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CustomerDetailsBill.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CustomerDetailsBill.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CustomerDetailsBill.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CustomerDetailsBill().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.github.lgooddatepicker.components.DatePicker checkOutDateFrom;
    private com.github.lgooddatepicker.components.DatePicker checkOutDateTo;
    private javax.swing.JTable customerTable;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private rojerusan.RSMaterialButtonCircle rSMaterialButtonCircle1;
    private rojerusan.RSMaterialButtonCircle rSMaterialButtonCircle2;
    // End of variables declaration//GEN-END:variables
}
