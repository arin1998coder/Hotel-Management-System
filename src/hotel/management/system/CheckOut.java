/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

package hotel.management.system;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import javax.swing.JOptionPane;
import java.util.Date;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;


public class CheckOut extends javax.swing.JFrame {

    String customerDocNum="";
    String bedType,roomType,quantity,address;
    long totalamt,refund,pending,deposit,totalDays=0;
    DefaultTableModel model;
    public CheckOut() {
        initComponents();
        txtName.setEditable(false);
        txtContact.setEditable(false);
        txtAmtDeposited.setEditable(false);
        txtAmtToCollect.setEditable(false);
        txtTotalAmt.setEditable(false);
        txtEmail.setEditable(false);
        txtRefund.setEditable(false);
        txtNoOfDays.setEditable(false);
        txtDocIdNo.setEditable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    public String getCurrentDateTimeInString(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");  
        LocalDateTime now = LocalDateTime.now();  
//        System.out.println(dtf.format(now)); 
        return dtf.format(now);
    }
    public void displayCutomerToRoomTable(){
        String docIdNo=txtDocIdNo.getText();
        String currentDate=checkOutLabel.getText();
        if(!docIdNo.equals("")){
         try {
            DbConnection c= new DbConnection();
            String query1="select * from customer where docNumber ='"+docIdNo+"' and checkOutDate is NULL";
            PreparedStatement st=c.con.prepareStatement(query1);
            ResultSet rs =st.executeQuery();
            if(rs.next()){
                deposit=Integer.parseInt(rs.getString("deposit"));
            }
            String query2="select * from customer_to_room where docNumber ='"+docIdNo+"' and inDate between '"+checkInLabel.getText()+"' and '"+currentDate+"'"; 
            
            Statement st2 = c.con.createStatement();
            rs = st.executeQuery(query2);
            while(rs.next()){
                System.out.println(1);
                String roomid=String.valueOf(rs.getInt(2));
                String inDate=rs.getString(3);
                Object dateOut=rs.getObject(4);
                String outDate="";
                if(dateOut==null){
                    outDate=currentDate;
                }
                else{
                    outDate=rs.getString(4);
                }
                String price=rs.getString(6);
                Object days = rs.getObject(5);
                String noOfDays="";
                if(days==null){
                    long diff=getDiffOfDatesInHours(inDate, outDate);
                    System.out.println(diff);
                    float daysInFloat=(float)diff/24F;
                    int daysinInt=Math.round(daysInFloat);
                    if(daysinInt==0){
                        daysinInt=1;
                    }
                    noOfDays=String.valueOf(daysinInt);
                }
                else{
                    noOfDays=String.valueOf(days);
                }
                totalamt+=(Integer.parseInt(price)*Integer.parseInt(noOfDays));
                totalDays+=Integer.parseInt(noOfDays);
                
                Object[] obj={roomid,inDate,outDate,price,noOfDays}; //as add row method takes an obj array, we declare an obj array
                model=(DefaultTableModel)cusToRoomTable.getModel(); //we are getting a model obj that will represent the bookdetails table
                //using this model obj we can add rows to our bookdetails table
                model.addRow(obj);
            }
            //if user deposited amt is more than total amt, then pending is 0 and user gets refund
                if(Long.parseLong(txtAmtDeposited.getText())>totalamt){
                    pending=0;
                    refund=Long.parseLong(txtAmtDeposited.getText())-totalamt;
                }
                else{
                    pending=totalamt-Long.parseLong(txtAmtDeposited.getText());
                    refund=0;
                }
                txtAmtToCollect.setText(String.valueOf(pending));
                txtTotalAmt.setText(String.valueOf(totalamt));
                txtRefund.setText(String.valueOf(refund));
                txtNoOfDays.setText(String.valueOf(totalDays));
            
        } catch (Exception e) {
            e.printStackTrace();
        };
        }
    }
    public long getDiffOfDatesInHours(String dateStart,String dateStop){
        long diffHours=0;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
        Date date1 = null;
        Date date2 = null;
        System.out.println("hello");
        try {
            date1 =  format.parse(dateStop);
            date2 =  format.parse(dateStart);
            
        } catch (ParseException e) {
            e.printStackTrace();
        }
        // Get msec from each, and subtract.
        long diff = date1.getTime() - date2.getTime();
        System.out.println(date1.getTime());
        System.out.println(date2.getTime());
        diffHours = diff / (60 * 60 * 1000);
        
    
        return diffHours;
    }
    
    public void displayCustomerDetails(){
        String roomno=txtRoomNo.getText();
        String currDate=getCurrentDateTimeInString();
        System.out.println(currDate);
        try {
            DbConnection c=new DbConnection();
            String query ="Select * from customer where allocatedRoom='"+roomno+"' and checkOutDate is NULL";
            String query2="Select * from room where RoomId='"+roomno+"'";
            PreparedStatement pst=c.con.prepareStatement(query);
            PreparedStatement pst2=c.con.prepareStatement(query2);
            ResultSet rs=pst.executeQuery();
            ResultSet rs2=pst2.executeQuery();
            if(rs.next() && rs2.next()){
                txtRoomNo.setEditable(false);
                customerDocNum=rs.getString(3);
                txtDocIdNo.setText(rs.getString(3));
                txtName.setText(rs.getString(4));
                String checkInDate=rs.getString(11);
                checkInLabel.setText(checkInDate);
                checkOutLabel.setText(currDate);
                txtAmtDeposited.setText(rs.getString(15));
                txtContact.setText(rs.getString(5));
                
                address=rs.getString(9);
                txtEmail.setText(rs.getString(6));
                
            }
            else{
                JOptionPane.showMessageDialog(null, "Room not allocated to Any Customer");
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void checkOut(){
        int customerId=0;
        String roomno=txtRoomNo.getText();
        
        if(roomno.equals("")){
            JOptionPane.showMessageDialog(null, "Please search the room no to proceed check out");
            return;
        }
        //storing the room swhere our guest who is going to checkout has stayed in roomstayed[]
        Object[] roomsStayed=new Object[model.getRowCount()];
        for(int i=0;i<roomsStayed.length;i++){
            roomsStayed[i]=cusToRoomTable.getValueAt(i, 0);  
        }
        System.out.print(Arrays.toString(roomsStayed));
        
        String total=txtTotalAmt.getText();
        String checkOutDate=checkOutLabel.getText();
        System.out.println(checkOutDate+" cOUT");
        String deposit=txtAmtDeposited.getText();
        try {
            DbConnection c=new DbConnection();
            
            String sql = "select * from customer where allocatedRoom='"+roomno+"' and checkOutDate is NULL";
            PreparedStatement st=c.con.prepareStatement(sql);
            ResultSet rs= st.executeQuery();
            if(rs.next()){
                customerId=Integer.parseInt(rs.getString("customerId"));
            }
            String query1="Update customer set checkOutDate='"+checkOutDate+"',totalPrice='"+total+"',deposit='"+deposit+"' where customerId ='"+customerId+"'";
            String query2="update room set Availability ='Available' where RoomId='"+roomno+"'";
            String query3="update customer_to_room set outDate='"+checkOutLabel.getText()+"',noOfDays='"+model.getValueAt(model.getRowCount()-1,4)+"'";
            PreparedStatement pst1 = c.con.prepareStatement(query1);
            PreparedStatement pst2=c.con.prepareStatement(query2);
            PreparedStatement pst3=c.con.prepareStatement(query3);
            int rowCount1 =pst1.executeUpdate();
            int rowCount2=pst2.executeUpdate();
            int rowCount3=pst3.executeUpdate();
            if(rowCount1>0 && rowCount2>0 && rowCount3>0){
                JOptionPane.showMessageDialog(null, "Check Out Successfull");
            }
            String path="D:\\";
            com.itextpdf.text.Document doc = new com.itextpdf.text.Document();
                PdfWriter.getInstance(doc, new FileOutputStream(path+""+customerId+".pdf"));
                doc.open();
                Paragraph paragraph1=new Paragraph("                                                                      THE PARADISE HOTEL");
                doc.add(paragraph1);
                String stars="";
                for(int i=0;i<112;i++){
                    stars+="*";
                }
                Paragraph paragraph2=new Paragraph(stars);
                doc.add(paragraph2);
                Paragraph paragraph3=new Paragraph("\tBill ID: "+customerId+"\nCustomer Details:\nName: "+txtName.getText()+"\nContact no: "+txtContact.getText()+"\nEmail: "+txtEmail.getText()+"\n");
                doc.add(paragraph3);
                doc.add(paragraph2);
//                Paragraph paragraph4=new Paragraph("\tRoom Details:\nNumber: "+txtRoomNo.getText()+"\nType: "+roomType+"\nBed Type: "+bedType+"\nPrice/Day: "+txtPriceperDay.getText()+"\nQuantity: "+quantity);
//                doc.add(paragraph4);
//                doc.add(paragraph2);
                PdfPTable tb2=new PdfPTable(6); //roomid,roomtype,bedtype,price/day,quantity,numofdays
                Statement st1=c.con.createStatement();
                
                tb2.addCell("Room No");
                tb2.addCell("Type");
                tb2.addCell("Bed Type");
                tb2.addCell("Price/Day");
                tb2.addCell("Quantity");
                tb2.addCell("No Of Days Stay");
               
                for(int i=0;i<roomsStayed.length;i++){
                   ResultSet rs1=st1.executeQuery("Select room.*,ctr.noOfDays from room join customer_to_room as ctr on room.RoomId=ctr.roomID where ctr.inDate between '"+checkInLabel.getText()+"' and '"+checkOutLabel.getText()+"' and ctr.docNumber ='"+txtDocIdNo.getText()+"'and room.RoomId='"+roomsStayed[i]+"'");
                   if(rs1.next()){
                       tb2.addCell(rs1.getString(1));
                       tb2.addCell(rs1.getString(3));
                       tb2.addCell(rs1.getString(6));
                       tb2.addCell(rs1.getString(7));
                       tb2.addCell(rs1.getString(5));
                       tb2.addCell(model.getValueAt(i, 4).toString());
                   }
                }
                doc.add(tb2);
                doc.add(paragraph2);
                //inserting a table in pdf
                PdfPTable tb1 = new PdfPTable(4); //with 4 cols
                tb1.addCell("Check In Date: "+checkInLabel.getText());
                tb1.addCell("Check Out Date: "+checkOutLabel.getText());
                tb1.addCell("No of Days Stay: "+txtNoOfDays.getText());
                tb1.addCell("Total Amount Paid: "+txtTotalAmt.getText());
                
                doc.add(tb1);
                doc.add(paragraph2);
                Paragraph paragraph5=new Paragraph("Thank you, Please Visit Again!");
                doc.add(paragraph5);
                
                
                
           
            doc.close();
            //after check out , asking user do they want to print the pdf of their bill or not
            int a=JOptionPane.showConfirmDialog(null, "Do you want to print the bill","Select",JOptionPane.YES_NO_OPTION);
            if(a==0){
                try {
                    //checking if the file exists or not
                    if((new File("D:\\"+customerId+".pdf")).exists()){
                        Process p = Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler D:\\"+customerId+".pdf");
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "File Does not Exist");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        datePicker1 = new com.github.lgooddatepicker.components.DatePicker();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        txtRoomNo = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtContact = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtTotalAmt = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        txtAmtDeposited = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtAmtToCollect = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        txtRefund = new javax.swing.JTextField();
        checkOutLabel = new javax.swing.JLabel();
        checkInLabel = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txtNoOfDays = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        txtDocIdNo = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        cusToRoomTable = new rojerusan.RSTableMetro();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(CheckOut.class, "CheckOut.jLabel1.text")); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 60, 80, 30));

        org.openide.awt.Mnemonics.setLocalizedText(jButton1, org.openide.util.NbBundle.getMessage(CheckOut.class, "CheckOut.jButton1.text")); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 40, 100, -1));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel2, org.openide.util.NbBundle.getMessage(CheckOut.class, "CheckOut.jLabel2.text")); // NOI18N
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 60, 110, 30));

        txtName.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtName.setText(org.openide.util.NbBundle.getMessage(CheckOut.class, "CheckOut.txtName.text")); // NOI18N
        getContentPane().add(txtName, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 200, 210, 30));

        org.openide.awt.Mnemonics.setLocalizedText(jButton2, org.openide.util.NbBundle.getMessage(CheckOut.class, "CheckOut.jButton2.text")); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 700, 130, 40));

        txtRoomNo.setText(org.openide.util.NbBundle.getMessage(CheckOut.class, "CheckOut.txtRoomNo.text")); // NOI18N
        getContentPane().add(txtRoomNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 60, 120, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel4, org.openide.util.NbBundle.getMessage(CheckOut.class, "CheckOut.jLabel4.text")); // NOI18N
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 160, 120, 30));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel5, org.openide.util.NbBundle.getMessage(CheckOut.class, "CheckOut.jLabel5.text")); // NOI18N
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 260, 120, 30));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel6, org.openide.util.NbBundle.getMessage(CheckOut.class, "CheckOut.jLabel6.text")); // NOI18N
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 260, 160, 30));

        txtContact.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtContact.setText(org.openide.util.NbBundle.getMessage(CheckOut.class, "CheckOut.txtContact.text")); // NOI18N
        getContentPane().add(txtContact, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 200, 210, 30));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel7, org.openide.util.NbBundle.getMessage(CheckOut.class, "CheckOut.jLabel7.text")); // NOI18N
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 160, 120, 30));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel9, org.openide.util.NbBundle.getMessage(CheckOut.class, "CheckOut.jLabel9.text")); // NOI18N
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 660, 110, 30));

        txtTotalAmt.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtTotalAmt.setText(org.openide.util.NbBundle.getMessage(CheckOut.class, "CheckOut.txtTotalAmt.text")); // NOI18N
        getContentPane().add(txtTotalAmt, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 710, 210, 30));

        org.openide.awt.Mnemonics.setLocalizedText(jButton3, org.openide.util.NbBundle.getMessage(CheckOut.class, "CheckOut.jButton3.text")); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 60, -1, -1));

        txtAmtDeposited.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtAmtDeposited.setText(org.openide.util.NbBundle.getMessage(CheckOut.class, "CheckOut.txtAmtDeposited.text")); // NOI18N
        getContentPane().add(txtAmtDeposited, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 300, 210, 30));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel11, org.openide.util.NbBundle.getMessage(CheckOut.class, "CheckOut.jLabel11.text")); // NOI18N
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 260, 100, 30));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel12, org.openide.util.NbBundle.getMessage(CheckOut.class, "CheckOut.jLabel12.text")); // NOI18N
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 660, 240, 30));

        txtAmtToCollect.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtAmtToCollect.setText(org.openide.util.NbBundle.getMessage(CheckOut.class, "CheckOut.txtAmtToCollect.text")); // NOI18N
        getContentPane().add(txtAmtToCollect, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 710, 210, 30));

        org.openide.awt.Mnemonics.setLocalizedText(jButton4, org.openide.util.NbBundle.getMessage(CheckOut.class, "CheckOut.jButton4.text")); // NOI18N
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 700, 130, 40));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel13, org.openide.util.NbBundle.getMessage(CheckOut.class, "CheckOut.jLabel13.text")); // NOI18N
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 660, 240, 30));

        txtRefund.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtRefund.setText(org.openide.util.NbBundle.getMessage(CheckOut.class, "CheckOut.txtRefund.text")); // NOI18N
        getContentPane().add(txtRefund, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 710, 210, 30));

        checkOutLabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(checkOutLabel, org.openide.util.NbBundle.getMessage(CheckOut.class, "CheckOut.checkOutLabel.text")); // NOI18N
        getContentPane().add(checkOutLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 300, 210, 30));

        org.openide.awt.Mnemonics.setLocalizedText(checkInLabel, org.openide.util.NbBundle.getMessage(CheckOut.class, "CheckOut.checkInLabel.text")); // NOI18N
        checkInLabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        getContentPane().add(checkInLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 300, 210, 30));

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel14, org.openide.util.NbBundle.getMessage(CheckOut.class, "CheckOut.jLabel14.text")); // NOI18N
        getContentPane().add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 260, 120, 30));

        txtNoOfDays.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtNoOfDays.setText(org.openide.util.NbBundle.getMessage(CheckOut.class, "CheckOut.txtNoOfDays.text")); // NOI18N
        getContentPane().add(txtNoOfDays, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 300, 210, 30));

        txtEmail.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtEmail.setText(org.openide.util.NbBundle.getMessage(CheckOut.class, "CheckOut.txtEmail.text")); // NOI18N
        getContentPane().add(txtEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 200, 210, 30));

        jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel15, org.openide.util.NbBundle.getMessage(CheckOut.class, "CheckOut.jLabel15.text")); // NOI18N
        getContentPane().add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 160, 100, 30));

        txtDocIdNo.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtDocIdNo.setText(org.openide.util.NbBundle.getMessage(CheckOut.class, "CheckOut.txtDocIdNo.text")); // NOI18N
        getContentPane().add(txtDocIdNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 200, 210, 30));

        jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel16, org.openide.util.NbBundle.getMessage(CheckOut.class, "CheckOut.jLabel16.text")); // NOI18N
        getContentPane().add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 160, 120, 30));

        cusToRoomTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Room No", "In Date", "Out Date", "Price/day", "No Of Days"
            }
        ));
        cusToRoomTable.setAltoHead(35);
        cusToRoomTable.setRowHeight(25);
        jScrollPane1.setViewportView(cusToRoomTable);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 370, 990, 240));

        setSize(new java.awt.Dimension(1136, 855));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        int a=JOptionPane.showConfirmDialog(null, "Do you want to Complete Check Out","Select",JOptionPane.YES_NO_OPTION);
        if(a==0){
            checkOut();
            this.dispose();
            new CheckOut().setVisible(true);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        this.dispose();
        new CheckOut().setVisible(true);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed

        displayCustomerDetails();
        displayCutomerToRoomTable();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
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
            java.util.logging.Logger.getLogger(CheckOut.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CheckOut.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CheckOut.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CheckOut.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CheckOut().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel checkInLabel;
    private javax.swing.JLabel checkOutLabel;
    private rojerusan.RSTableMetro cusToRoomTable;
    private com.github.lgooddatepicker.components.DatePicker datePicker1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txtAmtDeposited;
    private javax.swing.JTextField txtAmtToCollect;
    private javax.swing.JTextField txtContact;
    private javax.swing.JTextField txtDocIdNo;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtNoOfDays;
    private javax.swing.JTextField txtRefund;
    private javax.swing.JTextField txtRoomNo;
    private javax.swing.JTextField txtTotalAmt;
    // End of variables declaration//GEN-END:variables
}
