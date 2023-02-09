
package hotel.management.system;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author arin1
 */
public class HotelManagementSystem extends JFrame implements ActionListener {

    public HotelManagementSystem() {
//        setSize(1366,565);
//        setLocation(100,100);
        //setting pos of the Jframe and its width and height
        setBounds(100, 100, 1366, 565);
        setLayout(null);
        //got the location of the app opening image and loaded it inside imageicon obj
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/first.jpg"));
        //placed the image on the jlabel
        JLabel image = new JLabel(i1);
        image.setBounds(0, 0, 1366, 565);//loc x,loc y,length,breadth wrt to Frame
        add(image);//added the jlabel containing image on the jframe
        
        JLabel text = new JLabel("HOTEL MANAGEMENT SYSTEM");
        text.setBounds(20,465,1000,40); //set the pos and dimensions of where the text is displayed over image
        image.add(text); //added the text over the image
        text.setForeground(Color.WHITE);
        text.setFont(new Font("serif",Font.BOLD,40));
        
        //adds a next button in the bottom right corner of screen to go to the next frame
        JButton next = new JButton("Next");
        next.setBounds(1150, 450, 150, 50);
        next.setBackground(Color.YELLOW);
        next.setFont(new Font("serif",Font.PLAIN,30));
        
        //adding an action listener on "next" button
        next.addActionListener(this);
        image.add(next);
        
        setVisible(true);
//        //implementing the dipper effect of the text "Hotel Manage System"
//        while(true){
//            text.setVisible(false); //hide the label containing text
//            try {
//                Thread.sleep(500); //the execution of code stops for 500ms
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            text.setVisible(true); //show the label containing text after 500ms
//             try {
//                Thread.sleep(500); //the execution of code stops for 500ms
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
        
    }

    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new HotelManagementSystem();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //on click of next, current page closes and login page is displayed
        this.setVisible(false);
        new Login().setVisible(true);
        
    }
    
}
