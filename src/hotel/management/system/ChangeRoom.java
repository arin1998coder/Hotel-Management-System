/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package hotel.management.system;
import java.sql.*;
import javax.swing.JOptionPane;
import hotel.management.system.*;
import javax.swing.JFrame;

/**
 *
 * @author arin1
 */
public class ChangeRoom extends javax.swing.JFrame {

    String phoneno;
    String name;
    String idType,IdNum,allocatedRoomId,roomType,bedType,entryDate,leavinDate;
    String newRoomType,newBedType,quantity,newRoomId,price,todayDate;
    String currDate;
    public ChangeRoom() {
        initComponents();
        lblLeavingDate.setVisible(false);
        showAvailableRooms();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    
    public void Update(){
        phoneno=txtPhoneNo.getText();
        if(lblDocNum.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Please search the Customer !");
            return;
        }
        if(lblPrice.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Please select a Room Id before updating !");
            return; 
        }
        int x=JOptionPane.showConfirmDialog(null, "Do you want to Allocate New Room to the Customer?", "Select", JOptionPane.YES_NO_OPTION);
        if(x==1){
           return; 
        }
        lblLeavingDate.setVisible(true);
        name=lblCusName.getText();
        idType=lblIdType.getText();
        IdNum=lblDocNum.getText();
        allocatedRoomId=lblAllocatedRoom.getText();
        roomType=lblRoomType.getText();
        bedType=lblBedType.getText();
        entryDate=lblEntryDate.getText();
        leavinDate=lblLeavingDate.getText();
        newRoomType=cbRoomType.getSelectedItem().toString();
        newBedType=cbBedType.getSelectedItem().toString();
        quantity=cbQuantity.getSelectedItem().toString();
        newRoomId=cbRoomNum.getSelectedItem().toString();
        price=lblPrice.getText();
        todayDate=lblTodayDate.getText();
        
        //geeting the num of days the cursomer stayed in the current allocated room
        long diff=new CheckOut().getDiffOfDatesInHours(entryDate, currDate);
        System.out.println(diff);
        float daysInFloat=(float)diff/24F;
        int daysinInt=Math.round(daysInFloat);
        
        
        try {
            DbConnection c= new DbConnection();
            String query1="Update customer set allocatedRoom='"+newRoomId+"' where docNumber='"+IdNum+"' and checkOutDate is NULL";
            String query2="Update room set Availability = 'Available' where RoomId='"+allocatedRoomId+"'";
            String query3="Update room set Availability ='Occupied' where RoomId='"+newRoomId+"'";
            String query4="Update customer_to_room set outDate='"+leavinDate+"',noOfDays='"+daysinInt+"' where docNumber='"+IdNum+"' and roomID='"+allocatedRoomId+"' and outDate is NULL";
            String query5="Insert into customer_to_room(docNumber,roomID,inDate,price) values ('"+IdNum+"','"+newRoomId+"','"+todayDate+"','"+price+"')";
            
            Statement st=c.con.createStatement();
            st.addBatch(query1);
            st.addBatch(query2);
            st.addBatch(query3);
            st.addBatch(query4);
            st.addBatch(query5);
            //process all the statements
            int[] a=st.executeBatch();
            if(a[0]>0 && a[1]>0 && a[2]>0 && a[3]>0 && a[4]>0){
                JOptionPane.showMessageDialog(null, "Customer Allocated New Room\nNew Room: "+newRoomId);
            }
            else{
                JOptionPane.showMessageDialog(null, "Customer New Room Allocation Failed");
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.dispose();
        new ChangeRoom().setVisible(true);
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        lblCusName = new javax.swing.JLabel();
        txtPhoneNo = new javax.swing.JTextField();
        rSMaterialButtonCircle1 = new necesario.RSMaterialButtonCircle();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lblIdType = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lblDocNum = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lblAllocatedRoom = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        lblRoomType = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        lblBedType = new javax.swing.JLabel();
        lblEntryDate = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        cbRoomNum = new javax.swing.JComboBox<>();
        cbRoomType = new javax.swing.JComboBox<>();
        cbBedType = new javax.swing.JComboBox<>();
        cbQuantity = new javax.swing.JComboBox<>();
        rSMaterialButtonCircle2 = new rojerusan.RSMaterialButtonCircle();
        rSMaterialButtonCircle3 = new rojerusan.RSMaterialButtonCircle();
        jLabel17 = new javax.swing.JLabel();
        lblTodayDate = new javax.swing.JLabel();
        lblLeavingDate = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        lblPrice = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(ChangeRoom.class, "ChangeRoom.jLabel1.text")); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 30, 180, 50));

        lblCusName.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(lblCusName, org.openide.util.NbBundle.getMessage(ChangeRoom.class, "ChangeRoom.lblCusName.text")); // NOI18N
        getContentPane().add(lblCusName, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 280, 230, 30));

        txtPhoneNo.setText(org.openide.util.NbBundle.getMessage(ChangeRoom.class, "ChangeRoom.txtPhoneNo.text")); // NOI18N
        getContentPane().add(txtPhoneNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 140, 210, 30));

        org.openide.awt.Mnemonics.setLocalizedText(rSMaterialButtonCircle1, org.openide.util.NbBundle.getMessage(ChangeRoom.class, "ChangeRoom.rSMaterialButtonCircle1.text")); // NOI18N
        rSMaterialButtonCircle1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSMaterialButtonCircle1ActionPerformed(evt);
            }
        });
        getContentPane().add(rSMaterialButtonCircle1, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 120, 160, 60));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel3, org.openide.util.NbBundle.getMessage(ChangeRoom.class, "ChangeRoom.jLabel3.text")); // NOI18N
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 140, 150, 30));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel4, org.openide.util.NbBundle.getMessage(ChangeRoom.class, "ChangeRoom.jLabel4.text")); // NOI18N
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 220, 130, 30));

        lblIdType.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(lblIdType, org.openide.util.NbBundle.getMessage(ChangeRoom.class, "ChangeRoom.lblIdType.text")); // NOI18N
        getContentPane().add(lblIdType, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 330, 230, 30));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel5, org.openide.util.NbBundle.getMessage(ChangeRoom.class, "ChangeRoom.jLabel5.text")); // NOI18N
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 330, 120, 30));

        lblDocNum.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(lblDocNum, org.openide.util.NbBundle.getMessage(ChangeRoom.class, "ChangeRoom.lblDocNum.text")); // NOI18N
        getContentPane().add(lblDocNum, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 380, 230, 30));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel6, org.openide.util.NbBundle.getMessage(ChangeRoom.class, "ChangeRoom.jLabel6.text")); // NOI18N
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 380, 120, 30));

        lblAllocatedRoom.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(lblAllocatedRoom, org.openide.util.NbBundle.getMessage(ChangeRoom.class, "ChangeRoom.lblAllocatedRoom.text")); // NOI18N
        getContentPane().add(lblAllocatedRoom, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 430, 230, 30));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel7, org.openide.util.NbBundle.getMessage(ChangeRoom.class, "ChangeRoom.jLabel7.text")); // NOI18N
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 430, 140, 30));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel8, org.openide.util.NbBundle.getMessage(ChangeRoom.class, "ChangeRoom.jLabel8.text")); // NOI18N
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 480, 140, 30));

        lblRoomType.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(lblRoomType, org.openide.util.NbBundle.getMessage(ChangeRoom.class, "ChangeRoom.lblRoomType.text")); // NOI18N
        getContentPane().add(lblRoomType, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 480, 230, 30));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel9, org.openide.util.NbBundle.getMessage(ChangeRoom.class, "ChangeRoom.jLabel9.text")); // NOI18N
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 530, 140, 30));

        lblBedType.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(lblBedType, org.openide.util.NbBundle.getMessage(ChangeRoom.class, "ChangeRoom.lblBedType.text")); // NOI18N
        getContentPane().add(lblBedType, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 530, 230, 30));

        lblEntryDate.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(lblEntryDate, org.openide.util.NbBundle.getMessage(ChangeRoom.class, "ChangeRoom.lblEntryDate.text")); // NOI18N
        getContentPane().add(lblEntryDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 580, 230, 30));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel10, org.openide.util.NbBundle.getMessage(ChangeRoom.class, "ChangeRoom.jLabel10.text")); // NOI18N
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 580, 140, 30));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel11, org.openide.util.NbBundle.getMessage(ChangeRoom.class, "ChangeRoom.jLabel11.text")); // NOI18N
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 280, 120, 30));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel12, org.openide.util.NbBundle.getMessage(ChangeRoom.class, "ChangeRoom.jLabel12.text")); // NOI18N
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 330, 120, 30));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel13, org.openide.util.NbBundle.getMessage(ChangeRoom.class, "ChangeRoom.jLabel13.text")); // NOI18N
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 440, 120, 30));

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel14, org.openide.util.NbBundle.getMessage(ChangeRoom.class, "ChangeRoom.jLabel14.text")); // NOI18N
        getContentPane().add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 280, 120, 30));

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel15, org.openide.util.NbBundle.getMessage(ChangeRoom.class, "ChangeRoom.jLabel15.text")); // NOI18N
        getContentPane().add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 220, 130, 30));

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel16, org.openide.util.NbBundle.getMessage(ChangeRoom.class, "ChangeRoom.jLabel16.text")); // NOI18N
        getContentPane().add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 380, 120, 30));

        cbRoomNum.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbRoomNumActionPerformed(evt);
            }
        });
        getContentPane().add(cbRoomNum, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 440, 240, 30));

        cbRoomType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "AC", "Non-AC" }));
        cbRoomType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbRoomTypeActionPerformed(evt);
            }
        });
        getContentPane().add(cbRoomType, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 280, 240, 30));

        cbBedType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Single Bed", "DOuble Bed" }));
        cbBedType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbBedTypeActionPerformed(evt);
            }
        });
        getContentPane().add(cbBedType, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 330, 240, 30));

        cbQuantity.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3" }));
        cbQuantity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbQuantityActionPerformed(evt);
            }
        });
        getContentPane().add(cbQuantity, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 380, 240, 30));

        org.openide.awt.Mnemonics.setLocalizedText(rSMaterialButtonCircle2, org.openide.util.NbBundle.getMessage(ChangeRoom.class, "ChangeRoom.rSMaterialButtonCircle2.text")); // NOI18N
        rSMaterialButtonCircle2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSMaterialButtonCircle2ActionPerformed(evt);
            }
        });
        getContentPane().add(rSMaterialButtonCircle2, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 610, 150, 60));

        org.openide.awt.Mnemonics.setLocalizedText(rSMaterialButtonCircle3, org.openide.util.NbBundle.getMessage(ChangeRoom.class, "ChangeRoom.rSMaterialButtonCircle3.text")); // NOI18N
        rSMaterialButtonCircle3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSMaterialButtonCircle3ActionPerformed(evt);
            }
        });
        getContentPane().add(rSMaterialButtonCircle3, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 610, 150, 60));

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel17, org.openide.util.NbBundle.getMessage(ChangeRoom.class, "ChangeRoom.jLabel17.text")); // NOI18N
        getContentPane().add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 550, 90, 30));

        lblTodayDate.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(lblTodayDate, org.openide.util.NbBundle.getMessage(ChangeRoom.class, "ChangeRoom.lblTodayDate.text")); // NOI18N
        getContentPane().add(lblTodayDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 550, 240, 30));

        lblLeavingDate.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(lblLeavingDate, org.openide.util.NbBundle.getMessage(ChangeRoom.class, "ChangeRoom.lblLeavingDate.text")); // NOI18N
        getContentPane().add(lblLeavingDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 630, 230, 30));

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel18, org.openide.util.NbBundle.getMessage(ChangeRoom.class, "ChangeRoom.jLabel18.text")); // NOI18N
        getContentPane().add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 630, 120, 30));

        jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel19, org.openide.util.NbBundle.getMessage(ChangeRoom.class, "ChangeRoom.jLabel19.text")); // NOI18N
        getContentPane().add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 500, 90, 30));

        lblPrice.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(lblPrice, org.openide.util.NbBundle.getMessage(ChangeRoom.class, "ChangeRoom.lblPrice.text")); // NOI18N
        getContentPane().add(lblPrice, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 500, 240, 30));

        org.openide.awt.Mnemonics.setLocalizedText(jButton1, org.openide.util.NbBundle.getMessage(ChangeRoom.class, "ChangeRoom.jButton1.text")); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 30, 110, 30));

        setSize(new java.awt.Dimension(1314, 747));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void cbQuantityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbQuantityActionPerformed
        showAvailableRooms();
   
    }//GEN-LAST:event_cbQuantityActionPerformed

    private void rSMaterialButtonCircle2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSMaterialButtonCircle2ActionPerformed
        this.dispose();
        new ChangeRoom().setVisible(true);
    }//GEN-LAST:event_rSMaterialButtonCircle2ActionPerformed

    private void rSMaterialButtonCircle3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSMaterialButtonCircle3ActionPerformed
        Update();
    }//GEN-LAST:event_rSMaterialButtonCircle3ActionPerformed
    //search Customer Detaisl based on phone num, and display details of the customer who are staying at the hotel now
    private void rSMaterialButtonCircle1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSMaterialButtonCircle1ActionPerformed
        phoneno=txtPhoneNo.getText();
        if(new NewCustomerForm().isStringContainsCharacter(phoneno)){
            JOptionPane.showMessageDialog(null, "The Entered Phone num is not valid");
            return;
        }
        
        try {
            DbConnection c=new DbConnection();
            //gets me the customer details and the details of room where customer is staying of the customer whose phoneno matches the searched phone no
            String query="select cus.*,room.RoomType,room.BedType,c_to_r.roomID,c_to_r.inDate,c_to_r.outDate from customer as cus join room on cus.AllocatedRoom=room.RoomId join customer_to_room as c_to_r on room.RoomId=c_to_r.roomID where cus.checkOutDate is NULL and c_to_r.outDate is NULL and cus.contact='"+phoneno+"'";
            PreparedStatement pst= c.con.prepareStatement(query);
            ResultSet rs=pst.executeQuery();
            
            int exists=0;
            while(rs.next()){
                txtPhoneNo.setEditable(false);
                exists=1;
                lblCusName.setText(rs.getString(4));
                lblIdType.setText(rs.getString(2));
                lblDocNum.setText(rs.getString(3));
                lblAllocatedRoom.setText(rs.getString(10));
                lblRoomType.setText(rs.getString(16));
                lblBedType.setText(rs.getString(17));
                lblEntryDate.setText(rs.getString(19));
                currDate=new CheckOut().getCurrentDateTimeInString(); //getting currdate time
                lblLeavingDate.setText(currDate);
                lblTodayDate.setText(currDate);
            }
            if(exists==0){
                JOptionPane.showMessageDialog(null, "No Customer is Staying in Hotel with phone no: "+phoneno);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_rSMaterialButtonCircle1ActionPerformed

    private void cbBedTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbBedTypeActionPerformed
        showAvailableRooms();
        
    }//GEN-LAST:event_cbBedTypeActionPerformed

    private void cbRoomTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbRoomTypeActionPerformed
       showAvailableRooms();
       
    }//GEN-LAST:event_cbRoomTypeActionPerformed

    private void cbRoomNumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbRoomNumActionPerformed
        getRoomPrice();
    }//GEN-LAST:event_cbRoomNumActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

      //shows me the available room nums based on the bedtype,room type,and qunatity selected
    public void showAvailableRooms(){
        cbRoomNum.removeAllItems();
        lblPrice.setText("");
        bedType=cbBedType.getSelectedItem().toString();
        quantity=cbQuantity.getSelectedItem().toString();
        roomType=cbRoomType.getSelectedItem().toString();
        
        try {
            DbConnection c=new DbConnection();
            String sql="Select * from room where BedType='"+bedType+"' and RoomCapacity='"+quantity+"' and RoomType='"+roomType+"'and Availability='Available'";
            PreparedStatement pst=c.con.prepareStatement(sql);
            ResultSet rs =pst.executeQuery();
            while(rs.next()){
                cbRoomNum.addItem(rs.getString(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    //method to display the price of the selected room num from  Room num Dropdown 
    public void getRoomPrice(){
        newRoomId=(String)cbRoomNum.getSelectedItem();
        try {
            DbConnection c=new DbConnection();
            String sql="Select Price from room where RoomId='"+newRoomId+"'";
            PreparedStatement pst= c.con.prepareStatement(sql);
            ResultSet rs=pst.executeQuery();
            while(rs.next()){
                lblPrice.setText(rs.getString("Price"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
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
            java.util.logging.Logger.getLogger(ChangeRoom.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ChangeRoom.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ChangeRoom.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ChangeRoom.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ChangeRoom().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cbBedType;
    private javax.swing.JComboBox<String> cbQuantity;
    private javax.swing.JComboBox<String> cbRoomNum;
    private javax.swing.JComboBox<String> cbRoomType;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel lblAllocatedRoom;
    private javax.swing.JLabel lblBedType;
    private javax.swing.JLabel lblCusName;
    private javax.swing.JLabel lblDocNum;
    private javax.swing.JLabel lblEntryDate;
    private javax.swing.JLabel lblIdType;
    private javax.swing.JLabel lblLeavingDate;
    private javax.swing.JLabel lblPrice;
    private javax.swing.JLabel lblRoomType;
    private javax.swing.JLabel lblTodayDate;
    private necesario.RSMaterialButtonCircle rSMaterialButtonCircle1;
    private rojerusan.RSMaterialButtonCircle rSMaterialButtonCircle2;
    private rojerusan.RSMaterialButtonCircle rSMaterialButtonCircle3;
    private javax.swing.JTextField txtPhoneNo;
    // End of variables declaration//GEN-END:variables
}
