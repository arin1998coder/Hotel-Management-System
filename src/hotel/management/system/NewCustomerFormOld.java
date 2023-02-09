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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;
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
public class NewCustomerFormOld extends javax.swing.JFrame {
    Date date;
    private JRadioButton rbMale,rbFemale;
    private JLabel lblimg;
    public NewCustomerFormOld() {
        initComponents();
        
        showAvailableRooms();
       
        //to display the current date and time in the label
        date=new Date();
        
//        lblCheckInTime.setText(""+date);
        
         //radio buttons to select male and female options
        rbMale = new JRadioButton("Male");
        rbMale.setBounds(270,300,70,30);
        rbMale.setFont(new Font("Tahoma",Font.PLAIN,14));
        rbMale.setBackground(Color.white);
        mainPanel.add(rbMale);
        
        rbFemale = new JRadioButton("Female");
        rbFemale.setBounds(370,300,70,30);
        rbFemale.setFont(new Font("Tahoma",Font.PLAIN,14));
        rbFemale.setBackground(Color.white);
        mainPanel.add(rbFemale);
        //radio button group, created so that user can only select one radio button either "Male" or "Female"
        ButtonGroup bg = new ButtonGroup();
        bg.add(rbFemale);
        bg.add(rbMale);
        
        
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    
   
    
    //gets the list of avaialable rooms and shows in the dropdown Allocated Room
    public void showAvailableRooms(){
        
         try{
             DbConnection c=new DbConnection();
             //query to display all the roomId's whose availability status is "available"
             String sql="Select RoomId from room where Availability = '"+"Available"+"'";
             Statement st = c.con.createStatement();
             
             ResultSet rs = st.executeQuery(sql);
             
             while(rs.next()){
                String roomId=rs.getString("RoomId");
                cbAvailableRooms.addItem(roomId);
             }
             
             
         }
         catch(Exception e){
             e.printStackTrace();
         }
    }
    
    //shows the total price of the room selected based on the num of days of stay
    public boolean getTotalPrice(String price){
        
        String checkInDate=this.checkInDate.toString();
        
        String checkOutDate=this.checkOutDate.toString();
        
        
        if(price.equals("")){
            JOptionPane.showMessageDialog(null, "Please Select a Room First");
            lblTotalPrice.setText("");
            return false;
        }
        else if(checkInDate.length()==0 && checkOutDate.length()==0 ){
            JOptionPane.showMessageDialog(null, "Please Select the Check in and Check Out Date ");
            lblTotalPrice.setText("");
            return false;
        }
        else if(checkInDate.length()==0){
            JOptionPane.showMessageDialog(null, "Please Select the Check in Date ");
            lblTotalPrice.setText("");
            return false;
        }
        else if(checkOutDate.length()==0){
            JOptionPane.showMessageDialog(null, "Please Select the Check Out Date ");
            lblTotalPrice.setText("");
            return false;
        }
         
        //breaking down the check in date and checkout date to substrings of YYYY,MM,DD HH,MIN
        String YYYY1=checkInDate.substring(0,4);//YYYY
        String MM1=checkInDate.substring(5,7);//MM
        String DD1=checkInDate.substring(8,10);//DD
        String HH1=checkInDate.substring(11,13);//HH
        String min1=checkInDate.substring(14,16);//Min
        
        
        String YYYY2=checkOutDate.substring(0,4);//YYYY
        String MM2=checkOutDate.substring(5,7);//MM
        String DD2=checkOutDate.substring(8,10);//DD
        String HH2=checkOutDate.substring(11,13);//HH
        String min2=checkOutDate.substring(14,16);//Min
        
        
        //gets me the date in localdate time format
        LocalDateTime from=LocalDateTime.of(Integer.parseInt(YYYY1),Integer.parseInt(MM1),Integer.parseInt(DD1),Integer.parseInt(HH1),Integer.parseInt(min1));
        LocalDateTime to=LocalDateTime.of(Integer.parseInt(YYYY2),Integer.parseInt(MM2),Integer.parseInt(DD2),Integer.parseInt(HH2),Integer.parseInt(min2));
        
        //calculating the duration between the check in date  and checkout date
        Duration duration=Duration.between(from,to);

        // hours between checkindatetime and checkoutdate time
        System.out.println(duration.toHours() + " hours");
        Double hours=(double)duration.toHours();
        
        //when user has checkintime>checkout time
        if(hours<0){
            JOptionPane.showMessageDialog(null, "Check In Date Should be less than Check Out Date");
            lblTotalPrice.setText("");
            return false;
        }
        Double days=0d;
        if(hours<24){
            days=1d;
        }
        else
            days=hours/24d;
        
        long total = (long)Math.round(Double.parseDouble(price) *days);
        lblTotalPrice.setText("$ "+total);
        return true;
        
    }
    //method to display the price of the selected room num from allocated Room Dropdown on the Price label
    public void getRoomPrice(){
        
        String roomId=(String)cbAvailableRooms.getSelectedItem();
        System.out.println(roomId);
        try {
            DbConnection c=new DbConnection();
            String sql="Select Price from room where RoomId =?";
            
            PreparedStatement pst = c.con.prepareStatement(sql);
            pst.setString(1, roomId);
            
            ResultSet rs = pst.executeQuery();
           
            if(rs.next()){                
                 lblPrice.setText("$ "+rs.getString("Price"));
            }         
            
        } catch (Exception e) {
            e.printStackTrace();
        }
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
     //method to add new Customer Info to Customer table
    public boolean addCustomer(){
        boolean customerAdded= false;
        String documentType=(String)cbSelectIdType.getSelectedItem();
        String documentNum = txtIdNum.getText();
        String name = txtName.getText();
        String contact =txtPhoneNo.getText();
        String gender = null;
        //to check either male or female is selected
        //based on that string gender gets the value
        if(rbMale.isSelected()){
            gender ="Male";
        }
        else if(rbFemale.isSelected()){
            gender ="Female";
        }
        String country =txtCountry.getText();
        String allocatedRoom =(String) cbAvailableRooms.getSelectedItem();
        String checkInDate=this.checkInDate.toString();
        System.out.println(checkInDate);
        String checkOutDate=this.checkOutDate.toString();
        System.out.println(checkOutDate);
        
        String price = lblPrice.getText();
        String deposit=txtDeposit.getText();
        String totalPrice="";
        
        
         //validations
         boolean isCheckInDateGreaterOrEqualToCurrentDate=compareDates(checkInDate);
         if(isCheckInDateGreaterOrEqualToCurrentDate==false){
             JOptionPane.showMessageDialog(null, "Check In DateTime should be more than current DateTime");
             return false;
         }
         if(lblPrice.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Please Select a Room First");
            lblTotalPrice.setText("");
            return false;
        }
          else if(documentNum.equals("") || name.equals("")||
                contact.equals("")|| gender==null || country.equals("")|| deposit.equals("") ){
            JOptionPane.showMessageDialog(null, "Please fill all the details");
            return false;
        }
        else if(contact.equals("") || isStringContainsCharacter(contact)){
            JOptionPane.showMessageDialog(null, "Please provide a valid Contact Number");
            return false;
        }
        
        else if(checkInDate.length()==0 && checkOutDate.length()==0 ){
            JOptionPane.showMessageDialog(null, "Please Select the Check in and Check Out Date ");
            return false;
        }
        else if(checkInDate.length()==0){
            JOptionPane.showMessageDialog(null, "Please Select the Check in Date ");
            return false;
        }
        else if(checkOutDate.length()==0){
            JOptionPane.showMessageDialog(null, "Please Select the Check Out Date ");
            return false;
        }
        if(getTotalPrice(lblPrice.getText().substring(2))){
            totalPrice=lblTotalPrice.getText();
        }
        else{
            return false;
        }
          //if the amt deposited greater than total price then it should show a validation, that deposit amt cant be greater than total price
        if(Long.parseLong(deposit)>Long.parseLong(totalPrice.substring(2))){
             JOptionPane.showMessageDialog(null, "Deposit Amount Cannot be more than Total Price");
             return false;
         }
        int a=JOptionPane.showConfirmDialog(null, "Total Price is :"+totalPrice+" DO you want to Book?","Select ?",JOptionPane.YES_NO_OPTION);
        if(a==0){
        try {
            DbConnection c= new DbConnection(); //getting the connection object
            String query = "Insert into customer(document,docNumber,name,contact,gender,country,allocatedRoom,checkInDate,checkOutDate,price,totalPrice,deposit) values(?,?,?,?,?,?,?,?,?,?,?,?)";
            String updateRoomStatus="update room set Availability = ? where RoomId = ?";
            PreparedStatement pst =c.con.prepareStatement(query);
            PreparedStatement pst2 =c.con.prepareStatement(updateRoomStatus); //updates the status of the room allocated to "occupied
            pst.setString(1, documentType);
            pst.setString(2, documentNum);
            pst.setString(3, name);
            pst.setString(4, contact);
            pst.setString(5, gender);
            pst.setString(6, country);
            pst.setString(7, allocatedRoom);
            pst.setString(8, checkInDate);
            pst.setString(9, checkOutDate);
            pst.setString(10, price);
            pst.setString(11, totalPrice);
            pst.setString(12, deposit);
            
            pst2.setString(1, "Occupied");
            pst2.setString(2, allocatedRoom);
            
            int rowCount = pst.executeUpdate(); //execute the query
            int rowCount2 = pst2.executeUpdate();//updates the status of the room allocated to occupied
            if(rowCount>0 && rowCount2>0){
                customerAdded=true;
                JOptionPane.showMessageDialog(null, "Room Allocation To Customer Successfull");
            }
            else{
                customerAdded=false;
                JOptionPane.showMessageDialog(null, "Room allocation to Customer Failed");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        }
        else{
            customerAdded=false;
        }
        return customerAdded;
    }
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel12 = new javax.swing.JLabel();
        btnClose = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        cbIdType = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        cbIdType1 = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();
        cbIdType2 = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        cbIdType3 = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        cbIdType4 = new javax.swing.JComboBox<>();
        jLabel14 = new javax.swing.JLabel();
        cbIdType5 = new javax.swing.JComboBox<>();
        jLabel15 = new javax.swing.JLabel();
        jTextField8 = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel12, org.openide.util.NbBundle.getMessage(NewCustomerFormOld.class, "NewCustomerFormOld.jLabel12.text")); // NOI18N
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 36, 170, 30));

        org.openide.awt.Mnemonics.setLocalizedText(btnClose, org.openide.util.NbBundle.getMessage(NewCustomerFormOld.class, "NewCustomerFormOld.btnClose.text")); // NOI18N
        getContentPane().add(btnClose, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 30, -1, -1));

        jLabel1.setFont(new java.awt.Font("Yu Gothic", 1, 14)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(NewCustomerFormOld.class, "NewCustomerFormOld.jLabel1.text")); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 200, 100, 30));

        cbIdType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Passport", "Voter ID", "Driving License", "Pan Card", "Aadhar" }));
        getContentPane().add(cbIdType, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 150, 280, 30));

        jLabel2.setFont(new java.awt.Font("Yu Gothic", 1, 14)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel2, org.openide.util.NbBundle.getMessage(NewCustomerFormOld.class, "NewCustomerFormOld.jLabel2.text")); // NOI18N
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 120, 100, 30));

        jTextField1.setText(org.openide.util.NbBundle.getMessage(NewCustomerFormOld.class, "NewCustomerFormOld.jTextField1.text")); // NOI18N
        getContentPane().add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 240, 280, 30));

        jTextField2.setText(org.openide.util.NbBundle.getMessage(NewCustomerFormOld.class, "NewCustomerFormOld.jTextField2.text")); // NOI18N
        getContentPane().add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 330, 280, 30));

        jLabel3.setFont(new java.awt.Font("Yu Gothic", 1, 14)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel3, org.openide.util.NbBundle.getMessage(NewCustomerFormOld.class, "NewCustomerFormOld.jLabel3.text")); // NOI18N
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 290, 100, 30));

        jLabel4.setFont(new java.awt.Font("Yu Gothic", 1, 14)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel4, org.openide.util.NbBundle.getMessage(NewCustomerFormOld.class, "NewCustomerFormOld.jLabel4.text")); // NOI18N
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 380, 100, 30));

        jTextField3.setText(org.openide.util.NbBundle.getMessage(NewCustomerFormOld.class, "NewCustomerFormOld.jTextField3.text")); // NOI18N
        getContentPane().add(jTextField3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 420, 280, 30));

        jLabel5.setFont(new java.awt.Font("Yu Gothic", 1, 14)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel5, org.openide.util.NbBundle.getMessage(NewCustomerFormOld.class, "NewCustomerFormOld.jLabel5.text")); // NOI18N
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 470, 100, 30));

        jTextField4.setText(org.openide.util.NbBundle.getMessage(NewCustomerFormOld.class, "NewCustomerFormOld.jTextField4.text")); // NOI18N
        getContentPane().add(jTextField4, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 510, 280, 30));

        jLabel6.setFont(new java.awt.Font("Yu Gothic", 1, 14)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel6, org.openide.util.NbBundle.getMessage(NewCustomerFormOld.class, "NewCustomerFormOld.jLabel6.text")); // NOI18N
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 560, 100, 30));

        cbIdType1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Male", "Female" }));
        getContentPane().add(cbIdType1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 590, 280, 30));

        jLabel7.setFont(new java.awt.Font("Yu Gothic", 1, 14)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel7, org.openide.util.NbBundle.getMessage(NewCustomerFormOld.class, "NewCustomerFormOld.jLabel7.text")); // NOI18N
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 640, 100, 30));

        jTextField5.setText(org.openide.util.NbBundle.getMessage(NewCustomerFormOld.class, "NewCustomerFormOld.jTextField5.text")); // NOI18N
        getContentPane().add(jTextField5, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 680, 280, 30));

        jLabel8.setFont(new java.awt.Font("Yu Gothic", 1, 14)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel8, org.openide.util.NbBundle.getMessage(NewCustomerFormOld.class, "NewCustomerFormOld.jLabel8.text")); // NOI18N
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 200, 170, 30));

        jTextField6.setText(org.openide.util.NbBundle.getMessage(NewCustomerFormOld.class, "NewCustomerFormOld.jTextField6.text")); // NOI18N
        getContentPane().add(jTextField6, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 240, 280, 30));

        jLabel9.setFont(new java.awt.Font("Yu Gothic", 1, 14)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel9, org.openide.util.NbBundle.getMessage(NewCustomerFormOld.class, "NewCustomerFormOld.jLabel9.text")); // NOI18N
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 110, 100, 30));

        jTextField7.setText(org.openide.util.NbBundle.getMessage(NewCustomerFormOld.class, "NewCustomerFormOld.jTextField7.text")); // NOI18N
        getContentPane().add(jTextField7, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 150, 280, 30));

        cbIdType2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Single Bed", "DOuble Bed" }));
        getContentPane().add(cbIdType2, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 330, 280, 30));

        jLabel10.setFont(new java.awt.Font("Yu Gothic", 1, 14)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel10, org.openide.util.NbBundle.getMessage(NewCustomerFormOld.class, "NewCustomerFormOld.jLabel10.text")); // NOI18N
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 300, 100, 30));

        jLabel11.setFont(new java.awt.Font("Yu Gothic", 1, 14)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel11, org.openide.util.NbBundle.getMessage(NewCustomerFormOld.class, "NewCustomerFormOld.jLabel11.text")); // NOI18N
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 390, 100, 30));

        cbIdType3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "AC", "Non-AC" }));
        getContentPane().add(cbIdType3, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 420, 280, 30));

        jLabel13.setFont(new java.awt.Font("Yu Gothic", 1, 14)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel13, org.openide.util.NbBundle.getMessage(NewCustomerFormOld.class, "NewCustomerFormOld.jLabel13.text")); // NOI18N
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 480, 100, 30));

        cbIdType4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3" }));
        getContentPane().add(cbIdType4, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 510, 280, 30));

        jLabel14.setFont(new java.awt.Font("Yu Gothic", 1, 14)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel14, org.openide.util.NbBundle.getMessage(NewCustomerFormOld.class, "NewCustomerFormOld.jLabel14.text")); // NOI18N
        getContentPane().add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 560, 140, 30));

        getContentPane().add(cbIdType5, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 590, 280, 30));

        jLabel15.setFont(new java.awt.Font("Yu Gothic", 1, 14)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel15, org.openide.util.NbBundle.getMessage(NewCustomerFormOld.class, "NewCustomerFormOld.jLabel15.text")); // NOI18N
        getContentPane().add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 640, 100, 30));

        jTextField8.setText(org.openide.util.NbBundle.getMessage(NewCustomerFormOld.class, "NewCustomerFormOld.jTextField8.text")); // NOI18N
        getContentPane().add(jTextField8, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 680, 280, 30));

        org.openide.awt.Mnemonics.setLocalizedText(jButton3, org.openide.util.NbBundle.getMessage(NewCustomerFormOld.class, "NewCustomerFormOld.jButton3.text")); // NOI18N
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 673, 110, 30));

        org.openide.awt.Mnemonics.setLocalizedText(btnClear, org.openide.util.NbBundle.getMessage(NewCustomerFormOld.class, "NewCustomerFormOld.btnClear.text")); // NOI18N
        getContentPane().add(btnClear, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 673, 110, 30));

        setSize(new java.awt.Dimension(1192, 796));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(NewCustomerFormOld.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NewCustomerFormOld.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NewCustomerFormOld.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NewCustomerFormOld.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NewCustomerFormOld().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnClose;
    private javax.swing.JComboBox<String> cbIdType;
    private javax.swing.JComboBox<String> cbIdType1;
    private javax.swing.JComboBox<String> cbIdType2;
    private javax.swing.JComboBox<String> cbIdType3;
    private javax.swing.JComboBox<String> cbIdType4;
    private javax.swing.JComboBox<String> cbIdType5;
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
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    // End of variables declaration//GEN-END:variables
}
