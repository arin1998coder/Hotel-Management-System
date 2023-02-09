/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package hotel.management.system;

import java.awt.Color;
import java.awt.Font;
import java.sql.*;

import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.table.DefaultTableModel;



public class Admin extends javax.swing.JFrame {

    DefaultTableModel model;
    JRadioButton notapproved,approved;
    public Admin() {
        initComponents();
        showUsersTable();
        
        
        
    }
      //fetches Rooms details from Room table of db and displays in tablemodel in Jframe
    public void showUsersTable(){
        
        
        try {
            DbConnection c= new DbConnection();
            String query="select userId,name,contact,IdType,IdNumber,age,email,department,address,status from users";
            Statement st = c.con.createStatement();
            ResultSet rs = st.executeQuery(query);
            while(rs.next()){
                System.out.println(1);
                String userid=rs.getString(1);
                String name=rs.getString(2);
                String contact=rs.getString(3);
                String idType=rs.getString(4);
                String idNum=rs.getString(5);
                String age=rs.getString(6);
                String email=rs.getString(7);
                String dept=rs.getString(8);
                String address=rs.getString(9);
                String status=rs.getString(10);
                
                Object[] obj={userid,name,contact,idType,idNum,age,email,dept,address,status}; //as add row method takes an obj array, we declare an obj array
                model=(DefaultTableModel)usersTable.getModel(); //we are getting a model obj that will represent the bookdetails table
                //using this model obj we can add rows to our bookdetails table
                model.addRow(obj);
               
                
                
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
   
    //method to change Status of the selected user in the table
    public void changeUserStatus(){
        int selectedRowIndex = usersTable.getSelectedRow();
        String email = (String) usersTable.getModel().getValueAt(selectedRowIndex, 6); //retrieves the email from the selected row
        String status=(String) usersTable.getModel().getValueAt(selectedRowIndex,9 ); //retrieves the status from the selected row
        
        //if status true would change to false and vice versa
        if(status.equals("Approved")){
            status="Not Approved";
        }
        else{
            status="Approved";
        }
        
        try {
            DbConnection c=new DbConnection();
            int a=JOptionPane.showConfirmDialog(null, "Do you want to change the status of "+email,"Select ?",JOptionPane.YES_NO_OPTION);
            //if user selects yes change the status of the selected record
            if(a==0){
                PreparedStatement pst = c.con.prepareStatement("Update users set status ='"+status+"' where email='"+email+"'");
                int rowcount=pst.executeUpdate();
                if(rowcount>0){
                    JOptionPane.showMessageDialog(null, "Status Changed Successfully");
                    this.dispose();
                    new Admin().setVisible(true);
                }
                else{
                    JOptionPane.showMessageDialog(null, "Status Changed Failed");
                }
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        btnClear = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        txtsearchByText = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        btnSearch = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        usersTable = new javax.swing.JTable();
        cbDeptSelector = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        cbStatusSelector = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        btnAddRoom = new javax.swing.JButton();
        btnAddEmp = new javax.swing.JButton();
        btnAddDriver = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Algerian", 0, 48)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(Admin.class, "Admin.jLabel1.text")); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 40, 400, 50));

        btnClear.setBackground(new java.awt.Color(0, 102, 102));
        btnClear.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnClear.setForeground(new java.awt.Color(255, 255, 255));
        org.openide.awt.Mnemonics.setLocalizedText(btnClear, org.openide.util.NbBundle.getMessage(Admin.class, "Admin.btnClear.text")); // NOI18N
        getContentPane().add(btnClear, new org.netbeans.lib.awtextra.AbsoluteConstraints(1150, 140, 100, 30));

        jButton2.setBackground(new java.awt.Color(0, 0, 0));
        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        org.openide.awt.Mnemonics.setLocalizedText(jButton2, org.openide.util.NbBundle.getMessage(Admin.class, "Admin.jButton2.text")); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 50, 100, 40));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel2, org.openide.util.NbBundle.getMessage(Admin.class, "Admin.jLabel2.text")); // NOI18N
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, 210, 40));

        txtsearchByText.setText(org.openide.util.NbBundle.getMessage(Admin.class, "Admin.txtsearchByText.text")); // NOI18N
        getContentPane().add(txtsearchByText, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 140, 290, 30));

        jButton3.setBackground(new java.awt.Color(0, 0, 0));
        jButton3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        org.openide.awt.Mnemonics.setLocalizedText(jButton3, org.openide.util.NbBundle.getMessage(Admin.class, "Admin.jButton3.text")); // NOI18N
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1220, 50, 100, 40));

        btnSearch.setBackground(new java.awt.Color(0, 102, 102));
        btnSearch.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnSearch.setForeground(new java.awt.Color(255, 255, 255));
        org.openide.awt.Mnemonics.setLocalizedText(btnSearch, org.openide.util.NbBundle.getMessage(Admin.class, "Admin.btnSearch.text")); // NOI18N
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });
        getContentPane().add(btnSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 140, 100, 30));

        usersTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "User ID", "Name", "Contact", "ID Type", "ID Number", "Age", "Email", "Department", "Address", "Status"
            }
        ));
        usersTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                usersTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(usersTable);
        if (usersTable.getColumnModel().getColumnCount() > 0) {
            usersTable.getColumnModel().getColumn(0).setHeaderValue(org.openide.util.NbBundle.getMessage(Admin.class, "Admin.usersTable.columnModel.title0")); // NOI18N
            usersTable.getColumnModel().getColumn(1).setHeaderValue(org.openide.util.NbBundle.getMessage(Admin.class, "Admin.usersTable.columnModel.title1")); // NOI18N
            usersTable.getColumnModel().getColumn(2).setHeaderValue(org.openide.util.NbBundle.getMessage(Admin.class, "Admin.usersTable.columnModel.title7")); // NOI18N
            usersTable.getColumnModel().getColumn(3).setHeaderValue(org.openide.util.NbBundle.getMessage(Admin.class, "Admin.usersTable.columnModel.title5_1")); // NOI18N
            usersTable.getColumnModel().getColumn(4).setHeaderValue(org.openide.util.NbBundle.getMessage(Admin.class, "Admin.usersTable.columnModel.title6_1")); // NOI18N
            usersTable.getColumnModel().getColumn(5).setHeaderValue(org.openide.util.NbBundle.getMessage(Admin.class, "Admin.usersTable.columnModel.title4_1")); // NOI18N
            usersTable.getColumnModel().getColumn(6).setHeaderValue(org.openide.util.NbBundle.getMessage(Admin.class, "Admin.usersTable.columnModel.title2")); // NOI18N
            usersTable.getColumnModel().getColumn(7).setHeaderValue(org.openide.util.NbBundle.getMessage(Admin.class, "Admin.usersTable.columnModel.title3_1")); // NOI18N
            usersTable.getColumnModel().getColumn(8).setHeaderValue(org.openide.util.NbBundle.getMessage(Admin.class, "Admin.usersTable.columnModel.title8")); // NOI18N
            usersTable.getColumnModel().getColumn(9).setHeaderValue(org.openide.util.NbBundle.getMessage(Admin.class, "Admin.usersTable.columnModel.title9")); // NOI18N
        }

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 250, 1320, -1));

        cbDeptSelector.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All", "Reception", "Guest", "Room Cleaner", "Driver", "Kitchen", "Room Service" }));
        cbDeptSelector.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbDeptSelectorActionPerformed(evt);
            }
        });
        getContentPane().add(cbDeptSelector, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 140, 120, 30));

        org.openide.awt.Mnemonics.setLocalizedText(jLabel3, org.openide.util.NbBundle.getMessage(Admin.class, "Admin.jLabel3.text")); // NOI18N
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 140, 70, 30));

        cbStatusSelector.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All", "Approved", "Not Approved" }));
        cbStatusSelector.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbStatusSelectorActionPerformed(evt);
            }
        });
        getContentPane().add(cbStatusSelector, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 140, 120, 30));

        org.openide.awt.Mnemonics.setLocalizedText(jLabel4, org.openide.util.NbBundle.getMessage(Admin.class, "Admin.jLabel4.text")); // NOI18N
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 140, 40, 30));

        btnAddRoom.setBackground(new java.awt.Color(0, 153, 153));
        btnAddRoom.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnAddRoom.setForeground(new java.awt.Color(255, 255, 255));
        org.openide.awt.Mnemonics.setLocalizedText(btnAddRoom, org.openide.util.NbBundle.getMessage(Admin.class, "Admin.btnAddRoom.text")); // NOI18N
        btnAddRoom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddRoomActionPerformed(evt);
            }
        });
        getContentPane().add(btnAddRoom, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 200, 130, 30));

        btnAddEmp.setBackground(new java.awt.Color(0, 153, 153));
        btnAddEmp.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnAddEmp.setForeground(new java.awt.Color(255, 255, 255));
        org.openide.awt.Mnemonics.setLocalizedText(btnAddEmp, org.openide.util.NbBundle.getMessage(Admin.class, "Admin.btnAddEmp.text")); // NOI18N
        btnAddEmp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddEmpActionPerformed(evt);
            }
        });
        getContentPane().add(btnAddEmp, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 200, -1, 30));

        btnAddDriver.setBackground(new java.awt.Color(0, 153, 153));
        btnAddDriver.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnAddDriver.setForeground(new java.awt.Color(255, 255, 255));
        org.openide.awt.Mnemonics.setLocalizedText(btnAddDriver, org.openide.util.NbBundle.getMessage(Admin.class, "Admin.btnAddDriver.text")); // NOI18N
        btnAddDriver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddDriverActionPerformed(evt);
            }
        });
        getContentPane().add(btnAddDriver, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 200, 130, 30));

        setSize(new java.awt.Dimension(1380, 727));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        
        System.out.println("Hello");
        boolean recordFound=false;
        String department=cbDeptSelector.getSelectedItem().toString();
        String text=txtsearchByText.getText();
        String status=cbStatusSelector.getSelectedItem().toString();
        System.out.println(department);
//        System.out.println(chkNotApproved.isSelected());
       
        try {
            DbConnection c= new DbConnection();
            PreparedStatement pst=null;
            String query="";
               //shows all the users
          if(text.equals("") && status.equals("All") && department.equals("All")){
             
            model.setRowCount(0);
            query="select userId,name,contact,IdType,IdNumber,age,email,department,address,status from users";
            pst = c.con.prepareStatement(query);
            ResultSet rs = pst.executeQuery(query);
            while(rs.next()){
                System.out.println("Hello");
                recordFound=true;
                Object[] obj={rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10)}; //as add row method takes an obj array, we declare an obj array
                model=(DefaultTableModel)usersTable.getModel(); //we are getting a model obj that will represent the bookdetails table
                //using this model obj we can add rows to our bookdetails table
                model.addRow(obj);
            }
            System.out.println(recordFound);
            if(!recordFound){
                JOptionPane.showMessageDialog(null, "No Records Found");
            }
          }
          //shows the users from all department whose status is the selected status by user
          else if(text.equals("") && department.equals("All")){
              System.out.print("in");
            model.setRowCount(0);
            query="select userId,name,contact,IdType,IdNumber,age,email,department,address,status from users where status='"+status+"'";
            pst = c.con.prepareStatement(query);
            ResultSet rs = pst.executeQuery(query);
            while(rs.next()){
                recordFound=true;
                Object[] obj={rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10)}; //as add row method takes an obj array, we declare an obj array
                model=(DefaultTableModel)usersTable.getModel(); //we are getting a model obj that will represent the bookdetails table
                //using this model obj we can add rows to our bookdetails table
                model.addRow(obj);
            }
            System.out.println(recordFound);
            if(!recordFound){
                JOptionPane.showMessageDialog(null, "No Records Found");
            }
          }
          //shows  the users with any status whose department is the department selected by the user
          else if(text.equals("") && status.equals("All")){
            
            model.setRowCount(0);
            query="select userId,name,contact,IdType,IdNumber,age,email,department,address,status from users where department='"+department+"'";
            pst = c.con.prepareStatement(query);
            ResultSet rs = pst.executeQuery(query);
            while(rs.next()){
                recordFound=true;
                Object[] obj={rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10)}; //as add row method takes an obj array, we declare an obj array
                model=(DefaultTableModel)usersTable.getModel(); //we are getting a model obj that will represent the bookdetails table
                //using this model obj we can add rows to our bookdetails table
                model.addRow(obj);
            }
            System.out.println(recordFound);
            if(!recordFound){
                JOptionPane.showMessageDialog(null, "No Records Found");
            }
          }
          //shows the users with the selected department and selected status
          else if(text.equals("")){
            
            model.setRowCount(0);
            query="select userId,name,contact,IdType,IdNumber,age,email,department,address,status from users where status='"+status+"' and department='"+department+"'";
            pst = c.con.prepareStatement(query);
            ResultSet rs = pst.executeQuery(query);
            while(rs.next()){
                recordFound=true;
                Object[] obj={rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10)}; //as add row method takes an obj array, we declare an obj array
                model=(DefaultTableModel)usersTable.getModel(); //we are getting a model obj that will represent the bookdetails table
                //using this model obj we can add rows to our bookdetails table
                model.addRow(obj);
            }
            System.out.println(recordFound);
            if(!recordFound){
                JOptionPane.showMessageDialog(null, "No Records Found");
            }
          }
          //show all the users whose search field string matches with the email or name in the users table
          //that means if user searches based on name/email , then thats the only filter based on which the search works
          else{
               model.setRowCount(0);
            query="select userId,name,contact,IdType,IdNumber,age,email,department,address,status from users WHERE name LIKE '"+text+"%' or email like '"+text+"%'";
            pst = c.con.prepareStatement(query);
            ResultSet rs = pst.executeQuery(query);
            while(rs.next()){
                recordFound=true;
                Object[] obj={rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10)}; //as add row method takes an obj array, we declare an obj array
                model=(DefaultTableModel)usersTable.getModel(); //we are getting a model obj that will represent the bookdetails table
                //using this model obj we can add rows to our bookdetails table
                model.addRow(obj);
            }
            System.out.println(recordFound);
            if(!recordFound){
                JOptionPane.showMessageDialog(null, "No Records Found");
            }
          }
          
          
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }//GEN-LAST:event_btnSearchActionPerformed

    private void cbDeptSelectorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbDeptSelectorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbDeptSelectorActionPerformed

    private void cbStatusSelectorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbStatusSelectorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbStatusSelectorActionPerformed

    private void usersTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_usersTableMouseClicked
        changeUserStatus();
        
    }//GEN-LAST:event_usersTableMouseClicked

    private void btnAddRoomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddRoomActionPerformed
        new AddRooms().setVisible(true);
    }//GEN-LAST:event_btnAddRoomActionPerformed

    private void btnAddDriverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddDriverActionPerformed
        new AddDriver().setVisible(true);
    }//GEN-LAST:event_btnAddDriverActionPerformed

    private void btnAddEmpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddEmpActionPerformed
        new AddEmployee().setVisible(true);
    }//GEN-LAST:event_btnAddEmpActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.dispose();
        new Login().setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

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
            java.util.logging.Logger.getLogger(Admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Admin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddDriver;
    private javax.swing.JButton btnAddEmp;
    private javax.swing.JButton btnAddRoom;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnSearch;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JComboBox<String> cbDeptSelector;
    private javax.swing.JComboBox<String> cbStatusSelector;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txtsearchByText;
    private javax.swing.JTable usersTable;
    // End of variables declaration//GEN-END:variables
}
