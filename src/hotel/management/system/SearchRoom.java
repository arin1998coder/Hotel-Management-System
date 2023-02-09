
package hotel.management.system;

import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.*;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import javax.swing.table.DefaultTableModel;


public class SearchRoom extends javax.swing.JFrame {

    DefaultTableModel model;
    ButtonGroup bg;
    public SearchRoom() {
        initComponents();
        showRoomTable();
        showRoomIDS();
       
        
        AutoCompleteDecorator.decorate(cbRoomId);
        AutoCompleteDecorator.decorate(cbBedType);
        bg = new ButtonGroup();
        bg.add(rbDisplayAvailable);
        bg.add(rbShowAll);
        bg.add(rbOccupied);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
    }

    //fetches Rooms details from Room table of db and displays in tablemodel in Jframe
    public void showRoomTable(){
        
        
        try {
            DbConnection c= new DbConnection();
            String query="select * from room";
            Statement st = c.con.createStatement();
            ResultSet rs = st.executeQuery(query);
            while(rs.next()){
                System.out.println(1);
                String roomid=rs.getString(1);
                String availability=rs.getString(2);
                String roomtype=rs.getString(3);
                String cleaningStatus=rs.getString(4);
                String quantity=rs.getString(5);
                String bedtype=rs.getString(6);
                String price=rs.getString(7);
            
                
                Object[] obj={roomid,availability,roomtype,cleaningStatus,quantity,bedtype,price}; //as add row method takes an obj array, we declare an obj array
                model=(DefaultTableModel)roomsTable.getModel(); //we are getting a model obj that will represent the bookdetails table
                //using this model obj we can add rows to our bookdetails table
                model.addRow(obj);
               
                
                
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
     //gets the list of rooms and shows in the dropdown Room ID
    public void showRoomIDS(){
        
         try{
             DbConnection c=new DbConnection();
             //query to display all the roomId's 
             String sql="Select RoomId from room";
             Statement st = c.con.createStatement();
             
             ResultSet rs = st.executeQuery(sql);
             
             while(rs.next()){
                String roomId=rs.getString("RoomId");
                cbRoomId.addItem(roomId);
             }
             
             
         }
         catch(Exception e){
             e.printStackTrace();
         }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jScrollPane1 = new javax.swing.JScrollPane();
        roomsTable = new rojeru_san.complementos.RSTableMetro();
        jLabel1 = new javax.swing.JLabel();
        cbRoomId = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        cbBedType = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        chkAvailable = new javax.swing.JCheckBox();
        chkOccupied = new javax.swing.JCheckBox();
        btnSearch = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        rbShowAll = new javax.swing.JRadioButton();
        rbDisplayAvailable = new javax.swing.JRadioButton();
        rbOccupied = new javax.swing.JRadioButton();
        cbRoomType = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        roomsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Room ID", "Availability", "Room Type", "Cleaning Status", "Room Capacity", "Bed Type", "Price"
            }
        ));
        roomsTable.setRowHeight(30);
        jScrollPane1.setViewportView(roomsTable);
        if (roomsTable.getColumnModel().getColumnCount() > 0) {
            roomsTable.getColumnModel().getColumn(0).setHeaderValue(org.openide.util.NbBundle.getMessage(SearchRoom.class, "SearchRoom.roomsTable.columnModel.title0")); // NOI18N
            roomsTable.getColumnModel().getColumn(1).setHeaderValue(org.openide.util.NbBundle.getMessage(SearchRoom.class, "SearchRoom.roomsTable.columnModel.title1")); // NOI18N
            roomsTable.getColumnModel().getColumn(2).setHeaderValue(org.openide.util.NbBundle.getMessage(SearchRoom.class, "SearchRoom.roomsTable.columnModel.title2")); // NOI18N
            roomsTable.getColumnModel().getColumn(3).setHeaderValue(org.openide.util.NbBundle.getMessage(SearchRoom.class, "SearchRoom.roomsTable.columnModel.title3")); // NOI18N
            roomsTable.getColumnModel().getColumn(4).setHeaderValue(org.openide.util.NbBundle.getMessage(SearchRoom.class, "SearchRoom.roomsTable.columnModel.title4")); // NOI18N
            roomsTable.getColumnModel().getColumn(5).setHeaderValue(org.openide.util.NbBundle.getMessage(SearchRoom.class, "SearchRoom.roomsTable.columnModel.title5")); // NOI18N
            roomsTable.getColumnModel().getColumn(6).setHeaderValue(org.openide.util.NbBundle.getMessage(SearchRoom.class, "SearchRoom.roomsTable.columnModel.title6")); // NOI18N
        }

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 220, 1430, 550));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(SearchRoom.class, "SearchRoom.jLabel1.text")); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 140, 100, 30));

        cbRoomId.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));
        getContentPane().add(cbRoomId, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 140, 120, 30));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel2, org.openide.util.NbBundle.getMessage(SearchRoom.class, "SearchRoom.jLabel2.text")); // NOI18N
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 30, 210, 30));

        cbBedType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Single Bed", "DOuble Bed" }));
        cbBedType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbBedTypeActionPerformed(evt);
            }
        });
        getContentPane().add(cbBedType, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 140, 140, 30));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel3, org.openide.util.NbBundle.getMessage(SearchRoom.class, "SearchRoom.jLabel3.text")); // NOI18N
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 140, 100, 30));

        org.openide.awt.Mnemonics.setLocalizedText(jButton1, org.openide.util.NbBundle.getMessage(SearchRoom.class, "SearchRoom.jButton1.text")); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 30, 120, 30));

        org.openide.awt.Mnemonics.setLocalizedText(chkAvailable, org.openide.util.NbBundle.getMessage(SearchRoom.class, "SearchRoom.chkAvailable.text")); // NOI18N
        getContentPane().add(chkAvailable, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 140, -1, 30));

        org.openide.awt.Mnemonics.setLocalizedText(chkOccupied, org.openide.util.NbBundle.getMessage(SearchRoom.class, "SearchRoom.chkOccupied.text")); // NOI18N
        getContentPane().add(chkOccupied, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 140, 80, 30));

        org.openide.awt.Mnemonics.setLocalizedText(btnSearch, org.openide.util.NbBundle.getMessage(SearchRoom.class, "SearchRoom.btnSearch.text")); // NOI18N
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });
        getContentPane().add(btnSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 140, 120, 30));

        org.openide.awt.Mnemonics.setLocalizedText(btnClear, org.openide.util.NbBundle.getMessage(SearchRoom.class, "SearchRoom.btnClear.text")); // NOI18N
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });
        getContentPane().add(btnClear, new org.netbeans.lib.awtextra.AbsoluteConstraints(1100, 140, 120, 30));

        org.openide.awt.Mnemonics.setLocalizedText(rbShowAll, org.openide.util.NbBundle.getMessage(SearchRoom.class, "SearchRoom.rbShowAll.text")); // NOI18N
        rbShowAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbShowAllActionPerformed(evt);
            }
        });
        getContentPane().add(rbShowAll, new org.netbeans.lib.awtextra.AbsoluteConstraints(1250, 140, 130, 40));

        org.openide.awt.Mnemonics.setLocalizedText(rbDisplayAvailable, org.openide.util.NbBundle.getMessage(SearchRoom.class, "SearchRoom.rbDisplayAvailable.text")); // NOI18N
        rbDisplayAvailable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbDisplayAvailableActionPerformed(evt);
            }
        });
        getContentPane().add(rbDisplayAvailable, new org.netbeans.lib.awtextra.AbsoluteConstraints(1250, 60, 170, 30));

        org.openide.awt.Mnemonics.setLocalizedText(rbOccupied, org.openide.util.NbBundle.getMessage(SearchRoom.class, "SearchRoom.rbOccupied.text")); // NOI18N
        rbOccupied.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbOccupiedActionPerformed(evt);
            }
        });
        getContentPane().add(rbOccupied, new org.netbeans.lib.awtextra.AbsoluteConstraints(1250, 100, 170, 30));

        cbRoomType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "AC", "Non-AC" }));
        cbRoomType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbRoomTypeActionPerformed(evt);
            }
        });
        getContentPane().add(cbRoomType, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 140, 140, 30));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel4, org.openide.util.NbBundle.getMessage(SearchRoom.class, "SearchRoom.jLabel4.text")); // NOI18N
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 140, 100, 30));

        setSize(new java.awt.Dimension(1465, 782));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void cbBedTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbBedTypeActionPerformed
            System.out.println(cbBedType.getSelectedItem().toString());
        
    }//GEN-LAST:event_cbBedTypeActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        
        bg.clearSelection();
        try {
            DbConnection c= new DbConnection();
            //when room id is selected and searched then show the details of the selected room id only
            if(!cbRoomId.getSelectedItem().toString().equals(" ") )
            {
             model.setRowCount(0);
            String query="select * from room where RoomId='"+cbRoomId.getSelectedItem().toString()+"'";
            Statement st = c.con.createStatement();
            ResultSet rs = st.executeQuery(query);
            int count=0;
            while(rs.next()){
                count++;
                System.out.println(1);
                String roomid=rs.getString(1);
                String availability=rs.getString(2);
                String roomtype=rs.getString(3);
                String cleaningStatus=rs.getString(4);
                String quantity=rs.getString(5);
                String bedtype=rs.getString(6);
                String price=rs.getString(7);
            
                
                Object[] obj={roomid,availability,roomtype,cleaningStatus,quantity,bedtype,price}; //as add row method takes an obj array, we declare an obj array
                model=(DefaultTableModel)roomsTable.getModel(); //we are getting a model obj that will represent the bookdetails table
                //using this model obj we can add rows to our bookdetails table
                model.addRow(obj);
               
                
                
            }
            if(count==0){
                JOptionPane.showMessageDialog(null, "No Records Found");
            }
            }
            //when the room id is not selected and checkbox available and ocupied both selected  or both of them are not selected, then search based on the by default selected rooomtype and bedtype
            else if( cbRoomId.getSelectedItem().toString().equals(" ") && ((!chkAvailable.isSelected() && !chkOccupied.isSelected()) || (chkAvailable.isSelected() && chkOccupied.isSelected())) )
            {
             model.setRowCount(0);
            String query="select * from room where RoomType='"+cbRoomType.getSelectedItem().toString()+"' and BedType='"+cbBedType.getSelectedItem().toString()+"'";
            Statement st = c.con.createStatement();
            ResultSet rs = st.executeQuery(query);
            int count=0;
            while(rs.next()){
                count++;
                System.out.println(1);
                String roomid=rs.getString(1);
                String availability=rs.getString(2);
                String roomtype=rs.getString(3);
                String cleaningStatus=rs.getString(4);
                String quantity=rs.getString(5);
                String bedtype=rs.getString(6);
                String price=rs.getString(7);
            
                
                Object[] obj={roomid,availability,roomtype,cleaningStatus,quantity,bedtype,price}; //as add row method takes an obj array, we declare an obj array
                model=(DefaultTableModel)roomsTable.getModel(); //we are getting a model obj that will represent the bookdetails table
                //using this model obj we can add rows to our bookdetails table
                model.addRow(obj);  
                
            }
            if(count==0){
                JOptionPane.showMessageDialog(null, "No Records Found");
            }
            }
            //when availanle checkbox is selected show all the rooms based on the other selected fields which are available
             else if( chkAvailable.isSelected() )
            {
             model.setRowCount(0);
            String query="select * from room where RoomType='"+cbRoomType.getSelectedItem().toString()+"' and BedType='"+cbBedType.getSelectedItem().toString()+"' and Availability='Available'";
            Statement st = c.con.createStatement();
            ResultSet rs = st.executeQuery(query);
            int count=0;
            while(rs.next()){
                count++;
                System.out.println(1);
                String roomid=rs.getString(1);
                String availability=rs.getString(2);
                String roomtype=rs.getString(3);
                String cleaningStatus=rs.getString(4);
                String quantity=rs.getString(5);
                String bedtype=rs.getString(6);
                String price=rs.getString(7);
            
                
                Object[] obj={roomid,availability,roomtype,cleaningStatus,quantity,bedtype,price}; //as add row method takes an obj array, we declare an obj array
                model=(DefaultTableModel)roomsTable.getModel(); //we are getting a model obj that will represent the bookdetails table
                //using this model obj we can add rows to our bookdetails table
                model.addRow(obj);  
                
            }
            if(count==0){
                JOptionPane.showMessageDialog(null, "No Records Found");
            }
            }
              //when availanle checkbox is selected show all the rooms based on the other selected fields which are available
             else if( chkOccupied.isSelected() )
            {
             model.setRowCount(0);
            String query="select * from room where RoomType='"+cbRoomType.getSelectedItem().toString()+"' and BedType='"+cbBedType.getSelectedItem().toString()+"' and Availability='Occupied'";
            Statement st = c.con.createStatement();
            ResultSet rs = st.executeQuery(query);
            int count=0;
            while(rs.next()){
                count++;
                System.out.println(1);
                String roomid=rs.getString(1);
                String availability=rs.getString(2);
                String roomtype=rs.getString(3);
                String cleaningStatus=rs.getString(4);
                String quantity=rs.getString(5);
                String bedtype=rs.getString(6);
                String price=rs.getString(7);
            
                
                Object[] obj={roomid,availability,roomtype,cleaningStatus,quantity,bedtype,price}; //as add row method takes an obj array, we declare an obj array
                model=(DefaultTableModel)roomsTable.getModel(); //we are getting a model obj that will represent the bookdetails table
                //using this model obj we can add rows to our bookdetails table
                model.addRow(obj);  
                
            }
            if(count==0){
                JOptionPane.showMessageDialog(null, "No Records Found");
            }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnSearchActionPerformed

    private void cbRoomTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbRoomTypeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbRoomTypeActionPerformed

    private void rbDisplayAvailableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbDisplayAvailableActionPerformed
        try {
            DbConnection c= new DbConnection();
            //when room id is selected and searched then show the details of the selected room id only
           
             model.setRowCount(0);
            String query="select * from room where Availability='Available'";
            Statement st = c.con.createStatement();
            ResultSet rs = st.executeQuery(query);
            int count=0;
            while(rs.next()){
                count++;
                System.out.println(1);
                String roomid=rs.getString(1);
                String availability=rs.getString(2);
                String roomtype=rs.getString(3);
                String cleaningStatus=rs.getString(4);
                String quantity=rs.getString(5);
                String bedtype=rs.getString(6);
                String price=rs.getString(7);
            
                
                Object[] obj={roomid,availability,roomtype,cleaningStatus,quantity,bedtype,price}; //as add row method takes an obj array, we declare an obj array
                model=(DefaultTableModel)roomsTable.getModel(); //we are getting a model obj that will represent the bookdetails table
                //using this model obj we can add rows to our bookdetails table
                model.addRow(obj);
               
            }   
                
            
            if(count==0){
                JOptionPane.showMessageDialog(null, "No Records Found");
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }//GEN-LAST:event_rbDisplayAvailableActionPerformed

    private void rbOccupiedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbOccupiedActionPerformed
         try {
            DbConnection c= new DbConnection();
            //when room id is selected and searched then show the details of the selected room id only
           
             model.setRowCount(0);
            String query="select * from room where Availability='Occupied'";
            Statement st = c.con.createStatement();
            ResultSet rs = st.executeQuery(query);
            int count=0;
            while(rs.next()){
                count++;
                System.out.println(1);
                String roomid=rs.getString(1);
                String availability=rs.getString(2);
                String roomtype=rs.getString(3);
                String cleaningStatus=rs.getString(4);
                String quantity=rs.getString(5);
                String bedtype=rs.getString(6);
                String price=rs.getString(7);
            
                
                Object[] obj={roomid,availability,roomtype,cleaningStatus,quantity,bedtype,price}; //as add row method takes an obj array, we declare an obj array
                model=(DefaultTableModel)roomsTable.getModel(); //we are getting a model obj that will represent the bookdetails table
                //using this model obj we can add rows to our bookdetails table
                model.addRow(obj);
               
            }   
                
            
            if(count==0){
                JOptionPane.showMessageDialog(null, "No Records Found");
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }//GEN-LAST:event_rbOccupiedActionPerformed

    private void rbShowAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbShowAllActionPerformed
         try {
            DbConnection c= new DbConnection();
            //when room id is selected and searched then show the details of the selected room id only
           
             model.setRowCount(0);
            String query="select * from room";
            Statement st = c.con.createStatement();
            ResultSet rs = st.executeQuery(query);
            int count=0;
            while(rs.next()){
                count++;
                System.out.println(1);
                String roomid=rs.getString(1);
                String availability=rs.getString(2);
                String roomtype=rs.getString(3);
                String cleaningStatus=rs.getString(4);
                String quantity=rs.getString(5);
                String bedtype=rs.getString(6);
                String price=rs.getString(7);
            
                
                Object[] obj={roomid,availability,roomtype,cleaningStatus,quantity,bedtype,price}; //as add row method takes an obj array, we declare an obj array
                model=(DefaultTableModel)roomsTable.getModel(); //we are getting a model obj that will represent the bookdetails table
                //using this model obj we can add rows to our bookdetails table
                model.addRow(obj);
               
            }   
                
            
            if(count==0){
                JOptionPane.showMessageDialog(null, "No Records Found");
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }//GEN-LAST:event_rbShowAllActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        //makes all the search fields as default
        cbRoomId.setSelectedIndex(0);
        cbRoomType.setSelectedIndex(0);
        cbBedType.setSelectedIndex(0);
        chkAvailable.setSelected(false);
        chkOccupied.setSelected(false);
        bg.clearSelection();
    }//GEN-LAST:event_btnClearActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(SearchRoom.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SearchRoom.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SearchRoom.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SearchRoom.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SearchRoom().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnSearch;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cbBedType;
    private javax.swing.JComboBox<String> cbRoomId;
    private javax.swing.JComboBox<String> cbRoomType;
    private javax.swing.JCheckBox chkAvailable;
    private javax.swing.JCheckBox chkOccupied;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton rbDisplayAvailable;
    private javax.swing.JRadioButton rbOccupied;
    private javax.swing.JRadioButton rbShowAll;
    private rojeru_san.complementos.RSTableMetro roomsTable;
    // End of variables declaration//GEN-END:variables
}
