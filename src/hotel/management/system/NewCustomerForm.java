/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package hotel.management.system;

import java.awt.Choice;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.sql.*;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.openide.util.Exceptions;

/**
 *
 * @author arin1
 */
public class NewCustomerForm extends javax.swing.JFrame {
    
    String bedType,roomType,roomno,price,quantity;
    String idtype,idnum,name,contact,nationality,gender,email,address,checkInDate,deposit;
    public NewCustomerForm() {
        initComponents();
        txtPrice.setEditable(false);
        CheckInDate.setDateToToday();
        
        showAvailableRooms();
       
        
        
//        lblCheckInTime.setText(""+date);
        
         
        
        
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    
    //shows me the available room nums based on the bedtype,room type,and qunatity selected
    public void showAvailableRooms(){
        cbRoomNum.removeAllItems();
        txtPrice.setText("");
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
        roomno=(String)cbRoomNum.getSelectedItem();
        try {
            DbConnection c=new DbConnection();
            String sql="Select Price from room where RoomId='"+roomno+"'";
            PreparedStatement pst= c.con.prepareStatement(sql);
            ResultSet rs=pst.executeQuery();
            while(rs.next()){
                txtPrice.setText(rs.getString("Price"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //method to Check in a customer and store the customer details in customer table
    public boolean addCustomer(){
        boolean isAdded=false;
        idtype=cbIdType.getSelectedItem().toString();
        idnum=txtIdNum.getText();
        name=txtName.getText();
        contact=txtContact.getText();
        nationality=txtNationality.getText();
        gender=cbGender.getSelectedItem().toString();
        email=txtEmail.getText();
        address=txtAddress.getText();
        //getting the current local time
        Format f = new SimpleDateFormat("HH:mm:ss");
        String currTime = f.format(new Date());
        System.out.println("Time = "+currTime);
        checkInDate=CheckInDate.toString()+" "+currTime;
        System.out.println(checkInDate);
        if( roomno==null){
            JOptionPane.showMessageDialog(null, "Please Select a Room No.");
            return false;
        }
        roomno=cbRoomNum.getSelectedItem().toString();
        price=txtPrice.getText();
        deposit=txtDeposit.getText();
        String regex = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+(?:\\.[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+)*@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$";  
        //Compile regular expression to get the pattern  
         Pattern pattern = Pattern.compile(regex);
         Matcher matcher = pattern.matcher(email);
         
         //validations
        
          if(idnum.equals("") || name.equals("")||
                contact.equals("")|| gender==null || nationality.equals("") || address.equals("") ){
            JOptionPane.showMessageDialog(null, "Please fill all the details");
            return false;
        }
        else if(contact.equals("") || isStringContainsCharacter(contact)){
            JOptionPane.showMessageDialog(null, "Please provide a valid Contact Number");
            return false;
        }
        else if(deposit.equals("")||isStringContainsCharacter(deposit) || Long.parseLong(deposit)<Long.parseLong(price)){
            JOptionPane.showMessageDialog(null, "Please Deposit the amount "+price);
            return false;
        }
        
         else if(!matcher.matches()){
             JOptionPane.showMessageDialog(null, "Please provide a valid email address");
            return false;
         }
        
        try {
            DbConnection c=new DbConnection();
            String sql1="Insert into customer(document,docNumber,name,contact,email,gender,country,address,allocatedRoom,checkInDate,price,totalPrice,deposit) values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
            String sql2="update room set Availability ='Occupied' where RoomId = '"+roomno+"'";
            String sql3="Insert into customer_to_room(docNumber,roomID,inDate,price) values (?,?,?,?)";
            PreparedStatement pst1= c.con.prepareStatement(sql1);
            PreparedStatement pst2= c.con.prepareStatement(sql2);
            PreparedStatement pst3=c.con.prepareStatement(sql3);
            
            pst1.setString(1, idtype);
            pst1.setString(2, idnum);
            pst1.setString(3, name);
            pst1.setString(4, contact);
            pst1.setString(5, email);
            pst1.setString(6, gender);
            pst1.setString(7, nationality);
            pst1.setString(8, address);
            pst1.setString(9, roomno);
            pst1.setString(10, checkInDate);
            pst1.setString(11, price);
            pst1.setString(12, "");
            pst1.setString(13, deposit);
            
            pst3.setString(1, idnum);
            pst3.setString(2, roomno);
            pst3.setString(3, checkInDate);
            pst3.setInt(4,Integer.parseInt(price) );
            
            int rowcount=pst1.executeUpdate();
            int rowcount2=pst2.executeUpdate();
            int rowcount3=pst3.executeUpdate();
            if(rowcount>0 && rowcount2>0 && rowcount3>0){
                isAdded=true;
                JOptionPane.showMessageDialog(null, "Room Allocation To Customer Successfull");
            }
            else{
                isAdded=false;
                JOptionPane.showMessageDialog(null, "Room allocation to Customer Failed");
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isAdded;
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
    
    //to compare the Check In Date with current system date
    public boolean compareDates(String d1)
    {
        Date date = Calendar.getInstance().getTime();  
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");  
        String DateNow = dateFormat.format(date);  
        try{
            // If you already have date objects then skip 1

            //1
            // Create 2 dates starts
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
            Date date1 = sdf.parse(d1);
            Date date2 = sdf.parse(DateNow);

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
     
   
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDatePickerUtil1 = new org.jdatepicker.util.JDatePickerUtil();
        jLabel12 = new javax.swing.JLabel();
        btnClose = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        cbIdType = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        txtIdNum = new javax.swing.JTextField();
        txtName = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtContact = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtNationality = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        cbGender = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtAddress = new javax.swing.JTextField();
        cbBedType = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        cbRoomType = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        cbQuantity = new javax.swing.JComboBox<>();
        jLabel14 = new javax.swing.JLabel();
        cbRoomNum = new javax.swing.JComboBox<>();
        jLabel15 = new javax.swing.JLabel();
        txtPrice = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        txtDeposit = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        CheckInDate = new com.github.lgooddatepicker.components.DatePicker();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel12, org.openide.util.NbBundle.getMessage(NewCustomerForm.class, "NewCustomerForm.jLabel12.text")); // NOI18N
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 36, 170, 30));

        org.openide.awt.Mnemonics.setLocalizedText(btnClose, org.openide.util.NbBundle.getMessage(NewCustomerForm.class, "NewCustomerForm.btnClose.text")); // NOI18N
        btnClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCloseActionPerformed(evt);
            }
        });
        getContentPane().add(btnClose, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 30, -1, -1));

        jLabel1.setFont(new java.awt.Font("Yu Gothic", 1, 14)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(NewCustomerForm.class, "NewCustomerForm.jLabel1.text")); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 200, 100, 30));

        cbIdType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Passport", "Voter ID", "Driving License", "Pan Card", "Aadhar" }));
        getContentPane().add(cbIdType, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 150, 280, 30));

        jLabel2.setFont(new java.awt.Font("Yu Gothic", 1, 14)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel2, org.openide.util.NbBundle.getMessage(NewCustomerForm.class, "NewCustomerForm.jLabel2.text")); // NOI18N
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 120, 100, 30));

        txtIdNum.setText(org.openide.util.NbBundle.getMessage(NewCustomerForm.class, "NewCustomerForm.txtIdNum.text")); // NOI18N
        getContentPane().add(txtIdNum, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 240, 280, 30));

        txtName.setText(org.openide.util.NbBundle.getMessage(NewCustomerForm.class, "NewCustomerForm.txtName.text")); // NOI18N
        getContentPane().add(txtName, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 330, 280, 30));

        jLabel3.setFont(new java.awt.Font("Yu Gothic", 1, 14)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel3, org.openide.util.NbBundle.getMessage(NewCustomerForm.class, "NewCustomerForm.jLabel3.text")); // NOI18N
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 290, 100, 30));

        jLabel4.setFont(new java.awt.Font("Yu Gothic", 1, 14)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel4, org.openide.util.NbBundle.getMessage(NewCustomerForm.class, "NewCustomerForm.jLabel4.text")); // NOI18N
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 380, 100, 30));

        txtContact.setText(org.openide.util.NbBundle.getMessage(NewCustomerForm.class, "NewCustomerForm.txtContact.text")); // NOI18N
        getContentPane().add(txtContact, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 420, 280, 30));

        jLabel5.setFont(new java.awt.Font("Yu Gothic", 1, 14)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel5, org.openide.util.NbBundle.getMessage(NewCustomerForm.class, "NewCustomerForm.jLabel5.text")); // NOI18N
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 470, 100, 30));

        txtNationality.setText(org.openide.util.NbBundle.getMessage(NewCustomerForm.class, "NewCustomerForm.txtNationality.text")); // NOI18N
        getContentPane().add(txtNationality, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 510, 280, 30));

        jLabel6.setFont(new java.awt.Font("Yu Gothic", 1, 14)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel6, org.openide.util.NbBundle.getMessage(NewCustomerForm.class, "NewCustomerForm.jLabel6.text")); // NOI18N
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 560, 100, 30));

        cbGender.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Male", "Female" }));
        getContentPane().add(cbGender, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 590, 280, 30));

        jLabel7.setFont(new java.awt.Font("Yu Gothic", 1, 14)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel7, org.openide.util.NbBundle.getMessage(NewCustomerForm.class, "NewCustomerForm.jLabel7.text")); // NOI18N
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 640, 100, 30));

        txtEmail.setText(org.openide.util.NbBundle.getMessage(NewCustomerForm.class, "NewCustomerForm.txtEmail.text")); // NOI18N
        getContentPane().add(txtEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 680, 280, 30));

        jLabel8.setFont(new java.awt.Font("Yu Gothic", 1, 14)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel8, org.openide.util.NbBundle.getMessage(NewCustomerForm.class, "NewCustomerForm.jLabel8.text")); // NOI18N
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 200, 170, 30));

        jLabel9.setFont(new java.awt.Font("Yu Gothic", 1, 14)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel9, org.openide.util.NbBundle.getMessage(NewCustomerForm.class, "NewCustomerForm.jLabel9.text")); // NOI18N
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 110, 100, 30));

        txtAddress.setText(org.openide.util.NbBundle.getMessage(NewCustomerForm.class, "NewCustomerForm.txtAddress.text")); // NOI18N
        getContentPane().add(txtAddress, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 150, 280, 30));

        cbBedType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Single Bed", "DOuble Bed" }));
        cbBedType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbBedTypeActionPerformed(evt);
            }
        });
        getContentPane().add(cbBedType, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 330, 280, 30));

        jLabel10.setFont(new java.awt.Font("Yu Gothic", 1, 14)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel10, org.openide.util.NbBundle.getMessage(NewCustomerForm.class, "NewCustomerForm.jLabel10.text")); // NOI18N
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 300, 100, 30));

        jLabel11.setFont(new java.awt.Font("Yu Gothic", 1, 14)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel11, org.openide.util.NbBundle.getMessage(NewCustomerForm.class, "NewCustomerForm.jLabel11.text")); // NOI18N
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 390, 100, 30));

        cbRoomType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "AC", "Non-AC" }));
        cbRoomType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbRoomTypeActionPerformed(evt);
            }
        });
        getContentPane().add(cbRoomType, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 420, 280, 30));

        jLabel13.setFont(new java.awt.Font("Yu Gothic", 1, 14)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel13, org.openide.util.NbBundle.getMessage(NewCustomerForm.class, "NewCustomerForm.jLabel13.text")); // NOI18N
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 480, 100, 30));

        cbQuantity.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3" }));
        cbQuantity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbQuantityActionPerformed(evt);
            }
        });
        getContentPane().add(cbQuantity, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 510, 280, 30));

        jLabel14.setFont(new java.awt.Font("Yu Gothic", 1, 14)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel14, org.openide.util.NbBundle.getMessage(NewCustomerForm.class, "NewCustomerForm.jLabel14.text")); // NOI18N
        getContentPane().add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 560, 140, 30));

        cbRoomNum.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbRoomNumActionPerformed(evt);
            }
        });
        getContentPane().add(cbRoomNum, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 590, 280, 30));

        jLabel15.setFont(new java.awt.Font("Yu Gothic", 1, 14)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel15, org.openide.util.NbBundle.getMessage(NewCustomerForm.class, "NewCustomerForm.jLabel15.text")); // NOI18N
        getContentPane().add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 640, 100, 30));

        txtPrice.setText(org.openide.util.NbBundle.getMessage(NewCustomerForm.class, "NewCustomerForm.txtPrice.text")); // NOI18N
        getContentPane().add(txtPrice, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 680, 280, 30));

        org.openide.awt.Mnemonics.setLocalizedText(jButton3, org.openide.util.NbBundle.getMessage(NewCustomerForm.class, "NewCustomerForm.jButton3.text")); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 680, 110, 30));

        org.openide.awt.Mnemonics.setLocalizedText(btnClear, org.openide.util.NbBundle.getMessage(NewCustomerForm.class, "NewCustomerForm.btnClear.text")); // NOI18N
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });
        getContentPane().add(btnClear, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 680, 110, 30));

        txtDeposit.setText(org.openide.util.NbBundle.getMessage(NewCustomerForm.class, "NewCustomerForm.txtDeposit.text")); // NOI18N
        getContentPane().add(txtDeposit, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 460, 280, 30));

        jLabel16.setFont(new java.awt.Font("Yu Gothic", 1, 14)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel16, org.openide.util.NbBundle.getMessage(NewCustomerForm.class, "NewCustomerForm.jLabel16.text")); // NOI18N
        getContentPane().add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 420, 100, 30));
        getContentPane().add(CheckInDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 240, 280, 30));

        setSize(new java.awt.Dimension(1192, 796));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCloseActionPerformed
        this.dispose();
        
    }//GEN-LAST:event_btnCloseActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        this.dispose();
        new NewCustomerForm().setVisible(true);
    }//GEN-LAST:event_btnClearActionPerformed

    private void cbBedTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbBedTypeActionPerformed
        showAvailableRooms();
    }//GEN-LAST:event_cbBedTypeActionPerformed

    private void cbRoomTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbRoomTypeActionPerformed
        showAvailableRooms();
    }//GEN-LAST:event_cbRoomTypeActionPerformed

    private void cbQuantityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbQuantityActionPerformed
        showAvailableRooms();
    }//GEN-LAST:event_cbQuantityActionPerformed

    private void cbRoomNumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbRoomNumActionPerformed
        getRoomPrice();
    }//GEN-LAST:event_cbRoomNumActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
       int a=JOptionPane.showConfirmDialog(null, "Do you want to book room for this customer?","Select?",JOptionPane.YES_NO_OPTION);
       if(a==0){
            if(addCustomer()){
                this.dispose();
                new NewCustomerForm().setVisible(true);
            }
          }
    }//GEN-LAST:event_jButton3ActionPerformed

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
            java.util.logging.Logger.getLogger(NewCustomerForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NewCustomerForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NewCustomerForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NewCustomerForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NewCustomerForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.github.lgooddatepicker.components.DatePicker CheckInDate;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnClose;
    private javax.swing.JComboBox<String> cbBedType;
    private javax.swing.JComboBox<String> cbGender;
    private javax.swing.JComboBox<String> cbIdType;
    private javax.swing.JComboBox<String> cbQuantity;
    private javax.swing.JComboBox<String> cbRoomNum;
    private javax.swing.JComboBox<String> cbRoomType;
    private javax.swing.JButton jButton3;
    private org.jdatepicker.util.JDatePickerUtil jDatePickerUtil1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTextField txtAddress;
    private javax.swing.JTextField txtContact;
    private javax.swing.JTextField txtDeposit;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtIdNum;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtNationality;
    private javax.swing.JTextField txtPrice;
    // End of variables declaration//GEN-END:variables
}
