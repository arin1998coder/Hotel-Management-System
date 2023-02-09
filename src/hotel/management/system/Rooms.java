/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package hotel.management.system;

import java.sql.Statement;
import java.awt.Font;
import java.awt.Image;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author arin1
 */
public class Rooms extends javax.swing.JFrame {

    DefaultTableModel defaultTableModel;
    JLabel image;
    public Rooms() {
        initComponents();
        showRoomTable();
        //setting the image
        ImageIcon addImage = new ImageIcon(ClassLoader.getSystemResource("icons/eight.jpg"));
        //scalling the image to fit the label
        ImageIcon scaledImage = new ImageIcon(addImage.getImage().getScaledInstance(512, 612, Image.SCALE_DEFAULT));
        image = new JLabel(scaledImage);
        image.setBounds(622, 58, 700, 650);
        mainPanel.add(image);
        
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
                String roomNo=rs.getString("RoomId");
                 String availability=rs.getString("Availability");
                  String roomType=rs.getString("RoomType");
                   String cleaningSts=rs.getString("CleaningStatus");
                   String roomCap=rs.getString("RoomCapacity");
                   String bedType=rs.getString("BedType");
                   String price=rs.getString("Price");
                 Object[] obj={roomNo,availability,roomType,cleaningSts,roomCap,bedType,price}; //as add row method takes an obj array, we declare an obj array
                defaultTableModel=(DefaultTableModel)tblRoomDetails.getModel(); //we are getting a model obj that will represent the bookdetails table
                //using this model obj we can add rows to our bookdetails table
                defaultTableModel.addRow(obj);
               
                
                
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        mainPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblRoomDetails = new rojeru_san.complementos.RSTableMetro();
        jLabel1 = new javax.swing.JLabel();
        btnBack = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setLayout(null);

        mainPanel.setBackground(new java.awt.Color(255, 255, 255));

        tblRoomDetails.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Room No", "Availability", "Room Type", "Status", "Room Capacity", "Bed Type", "Price"
            }
        ));
        tblRoomDetails.setFuenteFilas(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tblRoomDetails.setFuenteFilasSelect(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tblRoomDetails.setFuenteHead(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        tblRoomDetails.setRowHeight(30);
        jScrollPane1.setViewportView(tblRoomDetails);
        if (tblRoomDetails.getColumnModel().getColumnCount() > 0) {
            tblRoomDetails.getColumnModel().getColumn(0).setHeaderValue(org.openide.util.NbBundle.getMessage(Rooms.class, "Rooms.tblRoomDetails.columnModel.title0")); // NOI18N
            tblRoomDetails.getColumnModel().getColumn(1).setHeaderValue(org.openide.util.NbBundle.getMessage(Rooms.class, "Rooms.tblRoomDetails.columnModel.title1")); // NOI18N
            tblRoomDetails.getColumnModel().getColumn(2).setHeaderValue(org.openide.util.NbBundle.getMessage(Rooms.class, "Rooms.tblRoomDetails.columnModel.title2")); // NOI18N
            tblRoomDetails.getColumnModel().getColumn(3).setHeaderValue(org.openide.util.NbBundle.getMessage(Rooms.class, "Rooms.tblRoomDetails.columnModel.title3")); // NOI18N
            tblRoomDetails.getColumnModel().getColumn(4).setHeaderValue(org.openide.util.NbBundle.getMessage(Rooms.class, "Rooms.tblRoomDetails.columnModel.title4")); // NOI18N
            tblRoomDetails.getColumnModel().getColumn(5).setHeaderValue(org.openide.util.NbBundle.getMessage(Rooms.class, "Rooms.tblRoomDetails.columnModel.title5")); // NOI18N
            tblRoomDetails.getColumnModel().getColumn(6).setHeaderValue(org.openide.util.NbBundle.getMessage(Rooms.class, "Rooms.tblRoomDetails.columnModel.title6")); // NOI18N
        }

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(Rooms.class, "Rooms.jLabel1.text")); // NOI18N

        btnBack.setBackground(new java.awt.Color(0, 0, 0));
        btnBack.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnBack.setForeground(new java.awt.Color(255, 255, 255));
        org.openide.awt.Mnemonics.setLocalizedText(btnBack, org.openide.util.NbBundle.getMessage(Rooms.class, "Rooms.btnBack.text")); // NOI18N
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(194, 194, 194)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 675, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(558, Short.MAX_VALUE))
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                .addContainerGap(51, Short.MAX_VALUE)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 610, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(441, 441, 441))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        setSize(new java.awt.Dimension(1280, 772));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
       this.dispose();
       new Reception().setVisible(true);
    }//GEN-LAST:event_btnBackActionPerformed

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
            java.util.logging.Logger.getLogger(Rooms.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Rooms.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Rooms.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Rooms.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Rooms().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel mainPanel;
    private rojeru_san.complementos.RSTableMetro tblRoomDetails;
    // End of variables declaration//GEN-END:variables
}
