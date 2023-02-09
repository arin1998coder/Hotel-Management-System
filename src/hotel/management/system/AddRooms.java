
package hotel.management.system;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
//import rojerusan.RSTableMetro;


public class AddRooms extends JFrame implements ActionListener{
    
    DefaultTableModel defaultTableModel;//creating object of DefaultTableModel
    JTable table; //Creating object of JTable
    JScrollPane scroll;
//    RSTableMetro table;
    //tf,cb,cb,tf,cb,add room button, cancel button
    
    JLabel title;
    JTextField txtRoomNo,txtPrice;
    JComboBox cbAvailable,cbClnStatus,cbBedTyp,cbRoomType,cbRoomCapacity;
    JButton btnAddRoom,btnCancel;
    public AddRooms() {
        
        setLayout(null);
        
        //label to show title of the Frame "Add ROoms"
        title = new JLabel("Add Rooms");
        title.setBounds(200,30,150,50);
        title.setFont(new Font("Tahoma",Font.BOLD,24));
        add(title);
        
        //label Room num
        JLabel lblRoomNo = new JLabel("Room Number");
        lblRoomNo.setBounds(60, 120, 120, 30);
        lblRoomNo.setFont(new Font("Tahoma",Font.PLAIN,17));
        add(lblRoomNo);
        //textfield to enter Room Num
         txtRoomNo= new JTextField();
        txtRoomNo.setBounds(200, 120, 160, 30);
        add(txtRoomNo);
        
         //label for Available/Not
        JLabel lblJob = new JLabel("Available");
        lblJob.setBounds(60, 170, 120, 30);
        lblJob.setFont(new Font("Tahoma",Font.PLAIN,17));
        add(lblJob);
        
        //dropdown to select if room is available or occupied from the options in str[]
        String[] avlOp = {"Available","Occupied"};
        cbAvailable = new JComboBox(avlOp);
        cbAvailable.setBounds(200, 170, 160, 30);
        cbAvailable.setBackground(Color.white);
        add(cbAvailable);
        
        //label for Room Type
        JLabel lblRoomType = new JLabel("Room Type");
        lblRoomType.setBounds(60, 220, 120, 30);
        lblRoomType.setFont(new Font("Tahoma",Font.PLAIN,17));
        add(lblRoomType);
        
        //dropdown to select if room type from the options in str[]
        String[] rmTyp = {"AC","Non-AC"};
        cbRoomType = new JComboBox(rmTyp);
        cbRoomType.setBounds(200, 220, 160, 30);
        cbRoomType.setBackground(Color.white);
        add(cbRoomType);
        
         
        //label for Cleaning Status
        JLabel lblCleanStatus = new JLabel("Cleaning Status");
        lblCleanStatus.setBounds(60, 270, 120, 30);
        lblCleanStatus.setFont(new Font("Tahoma",Font.PLAIN,17));
        add(lblCleanStatus);
        
        //dropdown to select cleaning status from the options in str[]
        String[] clnStatus = {"Cleaned","Dirty"};
        cbClnStatus = new JComboBox(clnStatus);
        cbClnStatus.setBounds(200, 270, 160, 30);
        cbClnStatus.setBackground(Color.white);
        add(cbClnStatus);
        
         //label for room capacity
        JLabel lblRoomCap = new JLabel("Room Capacity");
        lblRoomCap.setBounds(60, 320, 120, 30);
        lblRoomCap.setFont(new Font("Tahoma",Font.PLAIN,17));
        add(lblRoomCap);
        
        //dropdown to select room capacity from the options in str[]
        String[] roomCap = {"1","2","3"};
        cbRoomCapacity = new JComboBox(roomCap);
        cbRoomCapacity.setBounds(200, 320, 160, 30);
        cbRoomCapacity.setBackground(Color.white);
        add(cbRoomCapacity);
        
         //label for bed Type
        JLabel lblBedType = new JLabel("Bed Type");
        lblBedType.setBounds(60, 370, 120, 30);
        lblBedType.setFont(new Font("Tahoma",Font.PLAIN,17));
        add(lblBedType);
        
        //dropdown to select bed Type from the options in str[]
        String[] bed = {"Single Bed","DOuble Bed"};
        cbBedTyp = new JComboBox(bed);
        cbBedTyp.setBounds(200, 370, 160, 30);
        cbBedTyp.setBackground(Color.white);
        add(cbBedTyp);
        
         //label Price
        JLabel lblPrice = new JLabel("Price");
        lblPrice.setBounds(60, 420, 120, 30);
        lblPrice.setFont(new Font("Tahoma",Font.PLAIN,17));
        add(lblPrice);
        //textfield to enter Price of Room
         txtPrice= new JTextField();
        txtPrice.setBounds(200, 420, 160, 30);
        add(txtPrice);
        
        //Add Room Button
         
        btnAddRoom = new JButton("Add Room");
        btnAddRoom.setBackground(Color.BLACK);
        btnAddRoom.setForeground(Color.WHITE);
        btnAddRoom.setBounds(60,480,160,40);
        btnAddRoom.addActionListener(this); //added action listener to addRoom  button
        add(btnAddRoom);
        
        //Cancel button
        btnCancel = new JButton("Cancel");
        btnCancel.setBackground(Color.BLACK);
        btnCancel.setForeground(Color.WHITE);
        btnCancel.setBounds(250,480,160,40);
        btnCancel.addActionListener(this); //added action listener to addRoom  button
        add(btnCancel);
        
        
        showRoomTable();//shows the rooms in the hotel
        
        
        getContentPane().setBackground(Color.white); //frame color
        setBounds(350,200,1200,640);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);//to display jrfame in middle
        setVisible(true);
        
    }
    
    

    @Override
    public void actionPerformed(ActionEvent e) {
        //IF CLICKED on Cancel button
        if(e.getSource()==btnCancel){
            setVisible(false);
        }
        //if clicked on Add Room Button
        else{
            if(!checkRoomNumExist()){
                addRoom();
                this.dispose();
                new AddRooms().setVisible(true);
//                setVisible(false);
            }
            else{
                JOptionPane.showMessageDialog(null, "Room Number allready Exist");
            }    
           
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
    
    //method to add new Room to Room table
    public boolean addRoom(){
        boolean roomAdded= false;
        String roomNum=txtRoomNo.getText();
        String availability = (String)cbAvailable.getSelectedItem();
        String roomType = (String)cbRoomType.getSelectedItem();
        String cleanStatus =(String)cbClnStatus.getSelectedItem();
        String roomCap =(String)cbRoomCapacity.getSelectedItem();
        String bedType =(String)cbBedTyp.getSelectedItem();
        String price = txtPrice.getText();
        
        if(roomNum.equals("") || roomNum.length()>3){
            JOptionPane.showMessageDialog(null, "Please Provid a Valid Room Number");
            return false;
        }
         if(price.equals("") || isStringContainsCharacter(price)){
            JOptionPane.showMessageDialog(null, "Please provide a valid Price");
            return false;
        }
        
        try {
            DbConnection c= new DbConnection(); //getting the connection object
            String query = "Insert into room values(?,?,?,?,?,?,?)";
            PreparedStatement pst =c.con.prepareStatement(query);
            pst.setString(1, roomNum);
            pst.setString(2, availability);
            pst.setString(3, roomType);
            pst.setString(4, cleanStatus);
            pst.setString(5, roomCap);
            pst.setString(6, bedType);
            pst.setString(7, price);
            
            int rowCount = pst.executeUpdate(); //execute the query
            
            if(rowCount>0){
                roomAdded=true;
                JOptionPane.showMessageDialog(null, "Room Added Successfully");
            }
            else{
                roomAdded=false;
                JOptionPane.showMessageDialog(null, "Room Addition Failed");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return roomAdded;
    }
    
    //fetches Rooms details from Room table of db and displays in tablemodel in Jframe
    public void showRoomTable(){
        defaultTableModel=new DefaultTableModel();
        String[] colNames={"Room No","Availability","Room Type","Status","Room Capacity","Bed Type","Price"};
        defaultTableModel.setColumnIdentifiers(colNames);
        table = new JTable();
        table.setModel(defaultTableModel);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        
        table.setFillsViewportHeight(true);
        table.getColumnModel().getColumn(0).setPreferredWidth(100);
        table.getColumnModel().getColumn(1).setPreferredWidth(100);
        table.getColumnModel().getColumn(2).setPreferredWidth(100);
        table.getColumnModel().getColumn(3).setPreferredWidth(100);
        table.getColumnModel().getColumn(4).setPreferredWidth(150);
        table.getColumnModel().getColumn(5).setPreferredWidth(100);
        table.getColumnModel().getColumn(6).setPreferredWidth(100);
//        table.setBackground(Color.YELLOW);
        table.getTableHeader().setFont(new Font("Arial" , Font.BOLD, 15 ));
         scroll = new JScrollPane(table);
//         table.getTableHeader().setBackground(Color.CYAN);

        scroll.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        
        try {
            DbConnection c= new DbConnection();
            String query="select * from room";
            PreparedStatement pst = c.con.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                System.out.println(1);
                String roomNo=rs.getString("RoomId");
                 String availability=rs.getString("Availability");
                  String roomType=rs.getString("RoomType");
                   String cleaningSts=rs.getString("CleaningStatus");
                   String roomCap=rs.getString("RoomCapacity");
                   String bedType=rs.getString("BedType");
                   String price=rs.getString("Price");
                 Object[] obj={roomNo,availability,roomType,cleaningSts,roomCap,bedType,price}; //as add row method takes an obj array, we declare an obj array
                defaultTableModel=(DefaultTableModel)table.getModel(); //we are getting a model obj that will represent the bookdetails table
                //using this model obj we can add rows to our bookdetails table
                defaultTableModel.addRow(obj);
                scroll.setBounds(450, 120, 700, 400);
                this.add(scroll);
                
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //checks if the room num added allready exist in Room Table
    public boolean checkRoomNumExist(){
        boolean exist=false;
        String roomNo=txtRoomNo.getText();
        
        try {
            DbConnection c= new DbConnection();
            String query="Select * from room where RoomId =?";
            
            PreparedStatement pst = c.con.prepareStatement(query);
            pst.setString(1, roomNo);
            ResultSet rs = pst.executeQuery();
            if(rs.next()){
                exist=true; //room allready exist
            }
            else{
                exist=false; //room doesnt exist
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return exist;
    }
    public static void main(String[] args) {
        new AddRooms();
    }
    
    
}
