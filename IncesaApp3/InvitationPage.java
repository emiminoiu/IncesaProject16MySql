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
public class InvitationPage extends JFrame {
	  static Vector<String> columnNames1 = new Vector<String>();
	  static Vector data1 = new Vector();
	  public static Vector<String> myPlaces = new Vector<String>();
	  public static Vector<String> myHours = new Vector<String>();
	  public static Vector<String> myDescription = new Vector<String>();
	  public static Vector<Integer> myEventIds = new Vector<Integer>();
	  public static Vector<String> myDates = new Vector<String>();
	  public static Vector<String> testDublicatetime = new Vector<String>();
	  public static Vector<String> testDublicateday = new Vector<String>();
	  
	  public Home home;
	  
	  JFrame frame1 = new JFrame();
	  private JTable table;
	  public String user;
	 
      public InvitationPage(Home home) throws IOException {
    	  user = home.getUsername();
  	      InvitationPage ipage = this;
  	      System.out.println(user);
  	      frame1.setTitle("IncesaApp3");
          String UserId1 = null;
       	
       try {
            Connection con = null;
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
       	    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/eventmanagernew?user=student1&password=student1&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
       	    String sql = "select * from proposedevents";
       	    String query = "select UserId from users where username = '" + user + "'";
		    Statement stm = con.createStatement();
		    ResultSet rs =  stm.executeQuery(query);
		    while(rs.next())
		    {
		    	 UserId1 = rs.getString("UserId");
		    }
            String sql1 = "select EventId,date,time,place,description,ProposedBy from customevents where UserId = " + UserId1; 
            Statement statement = con.createStatement();
            Statement statement1 = con.createStatement();
            
           //((PreparedStatement) statement).setString(1, user);
           ResultSet resultSet = statement.executeQuery(sql);
           ResultSet resultSet1 = statement1.executeQuery(sql1);
           System.out.println(resultSet);
           ResultSetMetaData metaData = resultSet.getMetaData();
           ResultSetMetaData metaData1 = resultSet1.getMetaData();
           int columns = metaData.getColumnCount();
           int columns1 = metaData1.getColumnCount();
           columnNames1.removeAllElements();
           data1.removeAllElements();
           for (int j = 1; j <= columns; j++) {
           	
               columnNames1.addElement(metaData.getColumnName(j));
           }
           while (resultSet.next()) {
               Vector row = new Vector(columns);
               for (int j = 1; j <= columns; j++) {
            		if(j==1)
                	{
                	
                		myEventIds.addElement((Integer) resultSet.getObject(j));
                	}
                	if(j==2)
                	{
                		myDates.addElement((String) resultSet.getObject(j));
                	}
                	if(j==3)
                	{
                		myHours.addElement((String) resultSet.getObject(j));
                	}
                	if(j==4)
                	{
                		myPlaces.addElement((String) resultSet.getObject(j));
                	}
                	if(j==5)
                	{
                		myDescription.addElement((String) resultSet.getObject(j));
                	}
                	
                  
                   row.addElement(resultSet.getObject(j));
               }
               data1.addElement(row);
           }
           while (resultSet1.next()) {
               Vector row = new Vector(columns);
               for (int j = 1; j <= columns; j++) {
            		if(j==1)
                	{
                	
                		myEventIds.addElement((Integer) resultSet1.getObject(j));
                	}
                	if(j==2)
                	{
                		myDates.addElement((String) resultSet1.getObject(j));
                	}
                	if(j==3)
                	{
                		myHours.addElement((String) resultSet1.getObject(j));
                	}
                	if(j==4)
                	{
                		myPlaces.addElement((String) resultSet1.getObject(j));
                	}
                	if(j==5)
                	{
                		myDescription.addElement((String) resultSet1.getObject(j));
                	}
                	
                  
                   row.addElement(resultSet1.getObject(j));
               }
               data1.addElement(row);
           }
           JTable table1 = new JTable(data1, columnNames1);
           DefaultTableModel model = new DefaultTableModel(data1, columnNames1);
           //frame1.getContentPane().setLayout(null);
          // table1.setColumnSelectionAllowed(true);
           table1.setCellSelectionEnabled(true);
           table1.setRowSelectionAllowed(true);
          
           
        
          

          JScrollPane scrollPane = new JScrollPane(table1);
          scrollPane.setBounds(0, 0, 778, 199);
          frame1.getContentPane().add(scrollPane);       
          TableColumn column;
          for (int j = 0; j < table1.getColumnCount(); j++) {
              column = table1.getColumnModel().getColumn(j);
              column.setMaxWidth(250);
          } 
       	 
          
          // Change A JTable Background Color, Font Size, Font Color, Row Height
          table1.setBackground(Color.RED);
          table1.setForeground(Color.BLUE);
          Font font = new Font("",1,22);
          table1.setFont(font);
          table1.setRowHeight(25);
          
          JButton btnAdd = new JButton("Add this event to my Agenda");
          btnAdd.setFont(new Font("Sitka Small", Font.BOLD, 16));
          btnAdd.setBackground(Color.RED);
          btnAdd.setForeground(Color.WHITE);
          btnAdd.setBounds(30, 270, 283, 46);
          frame1.getContentPane().add(btnAdd);
          btnAdd.addActionListener(new ActionListener(){

              @Override
              public void actionPerformed(ActionEvent e) {
            	  String UserId = null;
            	  
      			try {
      				Random rand = new Random();

      				 int  Nr = rand.nextInt(50000) + 1;
      				 int i = table1.getSelectedRow();
      				Connection con = null;
      				Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
      	        	con = DriverManager.getConnection("jdbc:mysql://localhost:3306/eventmanagernew?user=student1&password=student1&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
      				String query = "select UserId from users where username = '" + user + "'";
      			    Statement stm = con.createStatement();
      			    ResultSet rs =  stm.executeQuery(query);
      			    while(rs.next())
      			    {
      			    	 UserId = rs.getString("UserId");
      			    }
      		     System.out.println(UserId);
                 // add row to the model
                String test = "select time from eventx where UserId = " + UserId;
                Statement s = con.createStatement();
                ResultSet r = s.executeQuery(test);
                while (r.next())
                {
                	String obj = r.getString("time");
  			        testDublicatetime.addElement(obj);
                }
                String test1 = "select date from eventx where UserId = " + UserId;
                Statement s1 = con.createStatement();
                ResultSet r1 = s.executeQuery(test1);
                while (r1.next())
                {
                	String obj = r1.getString("date");
  			        testDublicateday.addElement(obj);
                }
                int ok = 1;
                int ok1 = 1;
                for(String x : testDublicatetime)
                {
                	System.out.println(x);
                	if(myHours.get(i).equals(x))
                	{
                		ok = 0;
                	}
                }
                for(String y : testDublicateday)
                {
                	System.out.println(y);
                	if(myDates.get(i).equals(y))
                	{
                		ok1 = 0;
                	}
                }
                int reply1 = 0;
                if(((ok == 1) || (ok1 == 1)) || ((ok == 1) && (ok1 == 1)))
                {
                String sql1 = "insert into `eventx`(`EventId`,`date`,`time`,`place`,`description`,`UserId`) values ("+ Nr  + ", '" + myDates.get(i) + "','" + myHours.get(i) + "','"+ myPlaces.get(i) + "','" + myDescription.get(i) + "',"+ UserId + " )";
                Statement statement1 = con.createStatement();
                statement1.executeUpdate(sql1);
                
                UIManager.put("OptionPane.buttonFont", new FontUIResource(new Font("ARIAL",Font.PLAIN,25)));
        		UIManager.put("OptionPane.minimumSize",new Dimension(400,200)); 
        		UIManager.put("OptionPane.messageFont", new FontUIResource(new Font(  
        		          "Arial", Font.BOLD, 18)));
        		UIManager.put("OptionPane.background", Color.GREEN);
        		UIManager.put("Button.background",Color.green); 
        	    JOptionPane.showConfirmDialog(null,  "The event has been added successfully to your Agenda!", "IncesaApp3", JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.PLAIN_MESSAGE);
                }
        		
                else
                {
                	UIManager.put("OptionPane.buttonFont", new FontUIResource(new Font("ARIAL",Font.PLAIN,25)));
             		UIManager.put("OptionPane.minimumSize",new Dimension(400,200)); 
             		UIManager.put("OptionPane.messageFont", new FontUIResource(new Font(  
             		          "Arial", Font.BOLD, 18)));
             		UIManager.put("OptionPane.background", Color.RED);
             		UIManager.put("Button.background",Color.RED); 
             		int reply = JOptionPane.showConfirmDialog(null,  "You already have an event in the same day and at same hour in your Agenda!", "IncesaApp3", JOptionPane.OK_CANCEL_OPTION,
                             JOptionPane.PLAIN_MESSAGE);
             		if(reply == JOptionPane.OK_OPTION)
            		{
            			 reply1 = JOptionPane.showConfirmDialog(null,  "Postpone the event with 2 hour?", "IncesaApp3", JOptionPane.YES_NO_OPTION,
                                JOptionPane.PLAIN_MESSAGE);
            		}
             		
            		if(reply1 == JOptionPane.YES_OPTION)
            		{
            			
            			int hour = Integer.parseInt(myHours.get(i).substring(0, 2));
            			int minute = Integer.parseInt(myHours.get(i).substring(3,4));
            			hour += 2;
            			String k = hour + ":" + minute + "0";
            			System.out.println(k);
            			myHours.setElementAt(k, i);
            			String sql2 = "insert into `eventx`(`EventId`,`date`,`time`,`place`,`description`,`UserId`) values ("+ Nr  + ", '" + myDates.get(i) + "','" + myHours.get(i) + "','"+ myPlaces.get(i) + "','" + myDescription.get(i) + "',"+ UserId + " )";
                        Statement statement1 = con.createStatement();
                        statement1.executeUpdate(sql2);
                         
                        UIManager.put("OptionPane.buttonFont", new FontUIResource(new Font("ARIAL",Font.PLAIN,25)));
                 		UIManager.put("OptionPane.minimumSize",new Dimension(400,200)); 
                 		UIManager.put("OptionPane.messageFont", new FontUIResource(new Font(  
                 		          "Arial", Font.BOLD, 18)));
                 		UIManager.put("OptionPane.background", Color.GREEN);
                 		UIManager.put("Button.background",Color.green); 
                 	    JOptionPane.showConfirmDialog(null,  "The event has been added successfully to your Agenda!", "IncesaApp3", JOptionPane.OK_CANCEL_OPTION,
                                 JOptionPane.PLAIN_MESSAGE);
            			
            		}
                    
                }
               
                statement.close();
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
          JButton btnNewButton_1 = new JButton("Delete from ProposedEvents");
          btnNewButton_1.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
          btnNewButton_1.setBackground(Color.RED);
          btnNewButton_1.setForeground(Color.WHITE);
          btnNewButton_1.setBounds(460, 270, 273, 45);
          frame1.getContentPane().add(btnNewButton_1);
          
          JButton btnBack = new JButton("<-Back");
          btnBack.setFont(new Font("Verdana", Font.BOLD, 16));
          btnBack.setBackground(Color.RED);
          btnBack.setForeground(Color.WHITE);
          btnBack.setBounds(322, 368, 132, 29);
          frame1.getContentPane().add(btnBack);
          btnBack.addActionListener(new ActionListener(){
          	
              @Override
              public void actionPerformed(ActionEvent e) { 
              	InvitationPage.this.setVisible(false);
                frame1.dispose();
              }
         	  });
          JLabel label = new JLabel("");
          label.setIcon(new ImageIcon("C:\\Users\\Minoiu Emi\\Pictures\\blue_gradient-wallpaper-1280x720.jpg"));
          label.setBounds(0, 200, 778, 296);
          frame1.getContentPane().add(label);
         
        
     
		 frame1.setSize(800,500);
	     frame1.setLocationRelativeTo(null);
	    // frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	     frame1.setVisible(true);   
       } catch (Exception e) {
           System.out.println(e);
       }
      
       }
 }

