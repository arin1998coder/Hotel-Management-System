
package hotel.management.system;

//import com.mysql.cj.jdbc.PreparedStatementWrapper;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.sql.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class AddEmployee extends JFrame implements ActionListener {

    JTextField tfName,tfAge,tfEmail,tfPhone,tfSalary,tfAadhar;
    JRadioButton rbMale,rbFemale;
    JButton submit;
    JComboBox cbJob;
    public AddEmployee() {
        setLayout(null);
        
        //label Name
        JLabel lblname = new JLabel("NAME");
        lblname.setBounds(60, 30, 120, 30);
        lblname.setFont(new Font("Tahoma",Font.PLAIN,17));
        add(lblname);
        //textfield to enter name
         tfName= new JTextField();
        tfName.setBounds(200, 30, 160, 30);
        add(tfName);
          //label Age
        JLabel lblage = new JLabel("AGE");
        lblage.setBounds(60, 80, 120, 30);
        lblage.setFont(new Font("Tahoma",Font.PLAIN,17));
        add(lblage);
        //textfield to enter Age
         tfAge= new JTextField();
        tfAge.setBounds(200, 80, 160, 30);
        add(tfAge);
         //label Gender
        JLabel lblGender = new JLabel("GENDER");
        lblGender.setBounds(60, 130, 120, 30);
        lblGender.setFont(new Font("Tahoma",Font.PLAIN,17));
        add(lblGender);
        //radio buttons to select male and female options
        rbMale = new JRadioButton("Male");
        rbMale.setBounds(200,130,70,30);
        rbMale.setFont(new Font("Tahoma",Font.PLAIN,14));
        rbMale.setBackground(Color.white);
        add(rbMale);
        
        rbFemale = new JRadioButton("Female");
        rbFemale.setBounds(280,130,70,30);
        rbFemale.setFont(new Font("Tahoma",Font.PLAIN,14));
        rbFemale.setBackground(Color.white);
        add(rbFemale);
        //radio button group, created so that user can only select one radio button either "Male" or "Female"
        ButtonGroup bg = new ButtonGroup();
        bg.add(rbFemale);
        bg.add(rbMale);
        
        //label for job
        JLabel lblJob = new JLabel("JOB");
        lblJob.setBounds(60, 180, 120, 30);
        lblJob.setFont(new Font("Tahoma",Font.PLAIN,17));
        add(lblJob);
        
        //dropdown to select the job from the options in str[]
        String[] str = {"Front Desk Clerks","Porters","HouseKeeping","Kitchen Staff","Room Service","Chefs","Waiter/Waitress","Manager","Accountant"};
        cbJob = new JComboBox(str);
        cbJob.setBounds(200, 180, 160, 30);
        cbJob.setBackground(Color.white);
        add(cbJob);
        
        //label salary
         JLabel lblsalary = new JLabel("SALARY");
        lblsalary.setBounds(60, 230, 120, 30);
        lblsalary.setFont(new Font("Tahoma",Font.PLAIN,17));
        add(lblsalary);
        //textfield to enter salary
         tfSalary= new JTextField();
        tfSalary.setBounds(200, 230, 160, 30);
        add(tfSalary);
        
        //label phone
         JLabel lblphone = new JLabel("PHONE");
        lblphone.setBounds(60, 280, 120, 30);
        lblphone.setFont(new Font("Tahoma",Font.PLAIN,17));
        add(lblphone);
        //textfield to enter phone num
         tfPhone= new JTextField();
        tfPhone.setBounds(200, 280, 160, 30);
        add(tfPhone);
        
        //label email
        JLabel lblemail = new JLabel("EMAIL");
        lblemail.setBounds(60, 330, 120, 30);
        lblemail.setFont(new Font("Tahoma",Font.PLAIN,17));
        add(lblemail);
        //textfield to enter email
         tfEmail= new JTextField();
        tfEmail.setBounds(200, 330, 160, 30);
        add(tfEmail);
        
        //label adhaar
         JLabel lbladhaar = new JLabel("Aadhar");
        lbladhaar.setBounds(60, 380, 120, 30);
        lbladhaar.setFont(new Font("Tahoma",Font.PLAIN,17));
        add(lbladhaar);
        //textfield to enter aadhar
         tfAadhar= new JTextField();
        tfAadhar.setBounds(200, 380, 160, 30);
        add(tfAadhar);
        
        //submit button
        submit = new JButton("SUBMIT");
        submit.setBackground(Color.BLACK);
        submit.setForeground(Color.WHITE);
        submit.setBounds(200,450,160,40);
        submit.addActionListener(this); //added action listener to submit button
        add(submit);
        
        //setting the image
        ImageIcon addEmpImage = new ImageIcon(ClassLoader.getSystemResource("icons/tenth.jpg"));
        //scalling the image to fit the label
        ImageIcon scaledEmpImage = new ImageIcon(addEmpImage.getImage().getScaledInstance(450, 450, Image.SCALE_DEFAULT));
        JLabel image = new JLabel(scaledEmpImage);
        image.setBounds(450, 80, 450, 370);
        add(image);
                
        getContentPane().setBackground(Color.white); //frame color
        setBounds(350,200,1000,600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);//to display jrfame in middle
        setVisible(true);
    }
    
    public static void main(String[] args) {
        new AddEmployee();
    }

    //action performed on submit button
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(submitEmpDetails()){
            JOptionPane.showMessageDialog(null, "Details Saved Successfully");
            new AddEmployee();
            
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
    //add the emp details to the database
    public boolean submitEmpDetails(){
        
        String name = tfName.getText();
        String age = tfAge.getText();
        String salary = tfSalary.getText();
        String phone = tfPhone.getText();
        String email = tfEmail.getText();
        String aadhar= tfAadhar.getText();
        
        String gender = null;
        //to check either male or female is selected
        //based on that string gender gets the value
        if(rbMale.isSelected()){
            gender ="Male";
        }
        else if(rbFemale.isSelected()){
            gender ="Female";
        }
        
        //stores the selected value from the combobox into the string
        String job=(String)cbJob.getSelectedItem();
        
        //name validation
        if(name.equals("")){
            JOptionPane.showMessageDialog(null, "Please provide a valid name");
            return false;
        }
       //validate age
        if(age.equals("") || isStringContainsCharacter(age)){
            JOptionPane.showMessageDialog(null, "Please provide a valid age");
            return false;
        }
        //validate salary
        if(salary.equals("") || isStringContainsCharacter(salary)){
            JOptionPane.showMessageDialog(null, "Please provide a valid salary");
            return false;
        }
        //validate phone num
        if(phone.equals("") || isStringContainsCharacter(phone)){
            JOptionPane.showMessageDialog(null, "Please provide a valid phone num");
            return false;
        }
        //validate email
           //Regular Expression   
        String regex = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+(?:\\.[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+)*@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$";  
        //Compile regular expression to get the pattern  
         Pattern pattern = Pattern.compile(regex);
         Matcher matcher = pattern.matcher(email);
         if(!matcher.matches()){
             JOptionPane.showMessageDialog(null, "Please provide a valid email address");
            return false;
         }
        
        if(gender==null || phone.equals("")|| aadhar.equals("")){
            JOptionPane.showMessageDialog(null, "Please Fill All the Fields");
            return false;
        }
        
        try {
            DbConnection c= new DbConnection(); //creating connection obj to establish jdbc connection with db
            String sql ="Insert into employee (name,age,gender,job,salary,phone,email,aadhar) values(?,?,?,?,?,?,?,?)";
            PreparedStatement pst =c.con.prepareStatement(sql);
            
            //set the parameters in the sql query
            pst.setString(1, name);
            pst.setString(2, age);
            pst.setString(3, gender);
            pst.setString(4,job );
            pst.setString(5,salary );
            pst.setString(6,phone );
            pst.setString(7,email );
            pst.setString(8,aadhar );
            
            
            //execute the query
            int rowCount =pst.executeUpdate();
            
            if(rowCount>0){
                return true;
            }
            
                    
        } catch (Exception e) {
            e.printStackTrace();
        }
        JOptionPane.showMessageDialog(null, "Details Submission Failed");
        return false;
    }
    
}
