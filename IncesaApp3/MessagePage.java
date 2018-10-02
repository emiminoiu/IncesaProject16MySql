package IncesaApp3;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;
import java.util.Vector;

import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;
import javax.swing.JButton;
import javax.swing.ImageIcon;

public class MessagePage extends JFrame {
	 JFrame x = new JFrame();
	 String user;
	 public String UserId;
     public MessagePage(Home home,CustomInvitation custominv)
      {
	   user = home.getUsername();
	   MessagePage mp = this;
	   x.getContentPane().setLayout(null);
	   
	   JLabel lblNewLabel = new JLabel("MessagesPage");
	   lblNewLabel.setFont(new Font("Tempus Sans ITC", Font.BOLD, 25));
	   lblNewLabel.setForeground(Color.GREEN);
	   lblNewLabel.setBounds(226, 16, 185, 46);
	   x.getContentPane().add(lblNewLabel);
	   
	   JTextArea textArea = new JTextArea();
	   textArea.setFont(new Font("Monospaced", Font.PLAIN, 16));
	   textArea.setTabSize(16);
	   textArea.setBounds(58, 109, 548, 157);
	   x.getContentPane().add(textArea);
	   
	   JButton btnSendMessage = new JButton("Send Message");
	   btnSendMessage.setBackground(Color.RED);
	   btnSendMessage.setForeground(Color.WHITE);
	   btnSendMessage.setFont(new Font("Tahoma", Font.ITALIC, 16));
	   btnSendMessage.setBounds(245, 309, 166, 29);
	   x.getContentPane().add(btnSendMessage);
	   btnSendMessage.addActionListener(new ActionListener(){
         	
           @Override
           public void actionPerformed(ActionEvent e) { 
        	   try {
        		    String message = textArea.getText();
        		    Random rand = new Random();        		   
        			int id = rand.nextInt(50000) + 1;
        		    UserId = custominv.getUserId();
        		    System.out.println(UserId);
        		    String SenderId = null;
					Connection con = null;
					
   				    Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
   	        	    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/eventmanagernew?user=student1&password=student1&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
					String query = "select UserId from users where username = '" + user + "'";
				    Statement stm = con.createStatement();
				    ResultSet rs =  stm.executeQuery(query);
				    while(rs.next())
				    {
				    	 SenderId = rs.getString("UserId");
				    }
				
               // add row to the model
              
              String sql = "insert into `message`(`id`,`SenderId`,`UserId`,`message`) values ("+ id  + "," + SenderId + "," + UserId +",'" + message + "'" + ")";
              
              Statement statement = con.createStatement();
              statement.executeUpdate(sql);
              statement.close();
              UIManager.put("OptionPane.buttonFont", new FontUIResource(new Font("ARIAL",Font.PLAIN,25)));
      		  UIManager.put("OptionPane.minimumSize",new Dimension(400,200)); 
      		  UIManager.put("OptionPane.messageFont", new FontUIResource(new Font(  
      		          "Arial", Font.BOLD, 18)));
      		  UIManager.put("OptionPane.background", Color.GREEN);
      		  UIManager.put("Button.background",Color.green); 
      		  JOptionPane.showConfirmDialog(null,  "The message was sent successfully!", "IncesaApp3", JOptionPane.OK_CANCEL_OPTION,
                      JOptionPane.PLAIN_MESSAGE);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (InstantiationException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IllegalAccessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}             
           }
      	  });
      
	   JLabel lblNewLabel_1 = new JLabel("Enter your message below");
	   lblNewLabel_1.setForeground(Color.GREEN);
	   lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
	   lblNewLabel_1.setBounds(58, 62, 288, 40);
	   x.getContentPane().add(lblNewLabel_1);
	   
	   JLabel label = new JLabel("");
	   label.setIcon(new ImageIcon("C:\\Users\\Minoiu Emi\\Pictures\\blue_gradient-wallpaper-1280x720.jpg"));
	   label.setBounds(0, 0, 665, 486);
	   x.getContentPane().add(label);
	   x.setSize(676,443);
	   x.setLocationRelativeTo(null);
	     //frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	   x.setVisible(true);  
   }
}
