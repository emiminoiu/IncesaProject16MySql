package IncesaApp3;

import javax.swing.JFrame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.Encoder;
import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.RowSorter;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;
import javax.swing.ImageIcon;

import java.sql.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.Font;
import javax.swing.event.ListSelectionListener;
public class ReplyPage extends JFrame {
	 public JFrame x = new JFrame();
	 public Home home;
	 public String SendTo;
	 public String user;
	 public ReplyPage(Home home,MainContent mc) {
		ReplyPage rp = this;
		x.getContentPane().setLayout(null);
		user = home.getUsername();
		SendTo = mc.getUname();
		JLabel lblNewLabel = new JLabel("ReplyingPage");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setBounds(213, 16, 197, 41);
		x.getContentPane().add(lblNewLabel);
		
		JTextArea textArea = new JTextArea();
		textArea.setFont(new Font("Monospaced", Font.PLAIN, 15));
		textArea.setBounds(67, 108, 509, 130);
		x.getContentPane().add(textArea);
		
		JLabel lblNewLabel_1 = new JLabel("Say something nice");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.ITALIC, 20));
		lblNewLabel_1.setForeground(Color.RED);
		lblNewLabel_1.setBounds(67, 73, 219, 26);
		x.getContentPane().add(lblNewLabel_1);
		
		JButton btnReply = new JButton("Reply");
		btnReply.setFont(new Font("Tahoma", Font.ITALIC, 16));
		btnReply.setBackground(Color.RED);
		btnReply.setForeground(Color.WHITE);
		btnReply.setBounds(240, 277, 127, 29);
		x.getContentPane().add(btnReply);
		btnReply.addActionListener(new ActionListener(){
	         	
	           @Override
	           public void actionPerformed(ActionEvent e) { 
	        	   try {
	        		    String message = textArea.getText();
	        		    Random rand = new Random();        		   
	        			int id = rand.nextInt(50000) + 1;
	        	        System.out.println(SendTo);
	        		    String UserId = null;
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
					    String query1 = "select UserId from users where username = '" + SendTo + "'";
					    Statement stm1 = con.createStatement();
					    ResultSet rs1 =  stm1.executeQuery(query1);
					    while(rs1.next())
					    {
					    	 UserId = rs1.getString("UserId");
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
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon("C:\\Users\\Minoiu Emi\\Pictures\\pexels-photo-42154.jpeg"));
		label.setBounds(0, 0, 632, 423);
		x.getContentPane().add(label);
		x.setSize(652,433);
	    x.setLocationRelativeTo(null);
	     //frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    x.setVisible(true);  
	}
 
}
