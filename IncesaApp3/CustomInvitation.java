package IncesaApp3;
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
public class CustomInvitation extends JFrame {
	   
	      public String getUserId()
	      {
	    	  return UserId;
	      }
	      String UserId;
	      static Vector<String> columnNames1 = new Vector<String>();
		  static Vector data1 = new Vector();		  
		  public static Vector<String> myUsers = new Vector<String>();
		  public Home home;
		  JFrame frame1 = new JFrame();
		  private JTable table;
		  public String user;
		  public String date,time,place,description;
		  int i;
		  MainContent mc;
		
	      public CustomInvitation(Home home,MainContent mc) throws IOException {
	    	  user = home.getUsername();
	  	      CustomInvitation custominv = this;
	  	      System.out.println(user);
	  	      date = mc.getDate();
	  	      time = mc.getTime();
	  	      place = mc.getPlace();
	  	      description = mc.getDescription();
	       	  frame1.setTitle("IncesaApp3");
	       	   
	       try {
	            Connection con1 = null;
				Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
	       	    con1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/eventmanagernew?user=student1&password=student1&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
	       	    String sql = "select username from users";
    
	           Statement statement = con1.createStatement();
	           //((PreparedStatement) statement).setString(1, user);
	           ResultSet resultSet = statement.executeQuery(sql);
	           System.out.println(resultSet);
	           ResultSetMetaData metaData = resultSet.getMetaData();
	           columnNames1.removeAllElements();
	           data1.removeAllElements();
	           int columns = metaData.getColumnCount();
	           for (int j = 1; j <= columns; j++) {
	           	
	               columnNames1.addElement(metaData.getColumnName(j));
	           }
	           while (resultSet.next()) {
	               Vector row = new Vector(columns);
	               for (int j = 1; j <= columns; j++) { 		
	                	
	                		myUsers.addElement((String) resultSet.getObject(j));
	               
	                   row.addElement(resultSet.getObject(j));
	               }
	               data1.addElement(row);
	           }
	       
	           JTable table1 = new JTable(data1, columnNames1);
	           DefaultTableModel model = new DefaultTableModel(data1, columnNames1);
	           frame1.getContentPane().setLayout(null);
	         
	          // table1.setColumnSelectionAllowed(true);
	           table1.setCellSelectionEnabled(true);
	           table1.setRowSelectionAllowed(true);
	          
	           
	        
	          

	          JScrollPane scrollPane = new JScrollPane(table1);
	          scrollPane.setBounds(249, 0, 285, 250);
	          frame1.getContentPane().add(scrollPane);       
	          TableColumn column;
	          for (int j = 0; j < table1.getColumnCount(); j++) {
	              column = table1.getColumnModel().getColumn(j);
	              column.setMaxWidth(250);
	          } 
	       	 
	          
	          // Change A JTable Background Color, Font Size, Font Color, Row Height
	          table1.setBackground(Color.BLUE);
	          table1.setForeground(Color.GREEN);
	          Font font = new Font("",1,22);
	          table1.setFont(font);
	          table1.setRowHeight(25);
	         
	    	  
	             
	          JButton btnAdd = new JButton("Send Invitation");
	          btnAdd.setFont(new Font("Rockwell Extra Bold", Font.BOLD, 16));
	          btnAdd.setBackground(Color.RED);
	          btnAdd.setForeground(Color.WHITE);
	          btnAdd.setBounds(249, 272, 283, 46);
	          frame1.getContentPane().add(btnAdd);
	          btnAdd.addActionListener(new ActionListener(){

	              @Override
	              public void actionPerformed(ActionEvent e) {
	            	 
	      			try {
	      				Random rand = new Random();
	      				int  EventId = rand.nextInt(50000) + 1;
	      			    i = table1.getSelectedRow();
	      				Connection con = null;
	      				Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
	      	        	con = DriverManager.getConnection("jdbc:mysql://localhost:3306/eventmanagernew?user=student1&password=student1&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
	      				String query = "select UserId from users where username = '" + myUsers.get(i) + "'";
	      			    Statement stm = con.createStatement();
	      			    ResultSet rs =  stm.executeQuery(query);
	      			    
	      			    while(rs.next())
	      			    {
	      			    	UserId = rs.getString("UserId");
	      			    }
	      		    
	                 // add row to the model
	                
	      		    String sql1 = "insert into `CustomEvents`(`UserId`,`EventId`,`date`,`time`,`place`,`description`,`ProposedBy`) values (" + UserId +","+ EventId  + ", '" + date + "','" + time + "','"+ place + "','" + description + "', '"+ user + "'" +" )";
	                Statement statement1 = con.createStatement();
	                statement1.executeUpdate(sql1);
	                statement1.close();
	                
	                UIManager.put("OptionPane.buttonFont", new FontUIResource(new Font("ARIAL",Font.PLAIN,25)));
	        		UIManager.put("OptionPane.minimumSize",new Dimension(400,200)); 
	        		UIManager.put("OptionPane.messageFont", new FontUIResource(new Font(  
	        		          "Arial", Font.BOLD, 18)));
	        		UIManager.put("OptionPane.background", Color.GREEN);
	        		UIManager.put("Button.background",Color.green); 
	        		JOptionPane.showConfirmDialog(null,  "The invitation was sent successfully!", "IncesaApp3", JOptionPane.OK_CANCEL_OPTION,
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
	          
	          JButton btnBack = new JButton("<-Back");
	          btnBack.setFont(new Font("Verdana", Font.BOLD, 16));
	          btnBack.setBackground(Color.RED);
	          btnBack.setForeground(Color.WHITE);
	          btnBack.setBounds(324, 378, 132, 29);
	          frame1.getContentPane().add(btnBack);
	          
	          JButton btnMessage = new JButton("Send a Message");
	          btnMessage.setBackground(Color.RED);
	          btnMessage.setFont(new Font("Tahoma", Font.ITALIC, 16));
	          btnMessage.setForeground(Color.WHITE);
	          btnMessage.setBounds(308, 334, 160, 28);
	          frame1.getContentPane().add(btnMessage);
	          btnMessage.addActionListener(new ActionListener(){
		          	
	              @Override
	              public void actionPerformed(ActionEvent e) { 
	            
	               
	               try {
	      				Random rand = new Random();
	      				int  EventId = rand.nextInt(50000) + 1;
	      			    i = table1.getSelectedRow();
	      				Connection con = null;
	      				Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
	      	        	con = DriverManager.getConnection("jdbc:mysql://localhost:3306/eventmanagernew?user=student1&password=student1&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
	      				String query = "select UserId from users where username = '" + myUsers.get(i) + "'";
	      			    Statement stm = con.createStatement();
	      			    ResultSet rs =  stm.executeQuery(query);
	      			    
	      			    while(rs.next())
	      			    {
	      			    	UserId = rs.getString("UserId");
	      			    }
	      		    
	                
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
	               MessagePage mp = new MessagePage(home,custominv);
	               mp.setVisible(true);
	              }
	         	  });
	          JLabel label = new JLabel("");
	          label.setIcon(new ImageIcon("C:\\Users\\Minoiu Emi\\Pictures\\pexels-photo.jpg"));
	          label.setBounds(0, 0, 799, 535);
	          frame1.getContentPane().add(label);
	          btnBack.addActionListener(new ActionListener(){
	          	
	              @Override
	              public void actionPerformed(ActionEvent e) { 
	            
	                frame1.dispose();
	              }
	         	  });
	         
	        
	     
			 frame1.setSize(800,500);
		     frame1.setLocationRelativeTo(null);
		   //  frame1.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE );
		     frame1.setVisible(true);   
	       } catch (Exception e) {
	           System.out.println(e);
	       }
	      
	       }
	 }



