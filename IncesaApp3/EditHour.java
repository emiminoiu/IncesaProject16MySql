package IncesaApp3;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
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
public class EditHour extends JFrame {
	public String textField()
	{
	return textField.getText();
	}
	public String user;
	
	JFrame frame2 = new JFrame();
	public static Vector<String> myDates = new Vector<String>();
	private static JTextField textField;
    Vector<String> columnNames = new Vector<String>();
    Vector data = new Vector();
	 JFrame frame1 = new JFrame();
     public EditHour (Home home) {
    	EditHour eh = this;
        frame1.getContentPane().setLayout(null);
        user = home.getUsername();
        JLabel lblFilterPage = new JLabel("Filter Page");
        lblFilterPage.setForeground(Color.RED);
        lblFilterPage.setFont(new Font("Segoe UI Black", Font.BOLD, 18));
        lblFilterPage.setBounds(144, 16, 132, 52);
        frame1.getContentPane().add(lblFilterPage);
        
        JLabel lblEnterTheHour = new JLabel("Enter the hour");
        lblEnterTheHour.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblEnterTheHour.setForeground(Color.RED);
        lblEnterTheHour.setBounds(41, 71, 177, 34);
        frame1.getContentPane().add(lblEnterTheHour);
        
        textField = new JTextField();
        textField.setBounds(41, 104, 138, 41);
        frame1.getContentPane().add(textField);
        textField.setColumns(10);
        
        JButton btnSearch = new JButton("Search");
        btnSearch.setForeground(Color.WHITE);
        btnSearch.setBackground(Color.RED);
        btnSearch.setFont(new Font("Tahoma", Font.BOLD, 18));
        btnSearch.setBounds(144, 191, 132, 37);
        frame1.getContentPane().add(btnSearch);
               btnSearch.addActionListener(new ActionListener(){
        	
            
            @Override
            public void actionPerformed(ActionEvent e) {
            	  try {
            		  Connection con = null;

                  	Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
                  	con = DriverManager.getConnection("jdbc:mysql://localhost:3306/eventmanagernew?user=student1&password=student1&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
                      //String sql = "SELECT eventx.date, eventx.time,eventx.place,eventx.description \r\n" + 
                      //		"from eventx\r\n" + 
                      	//	"RIGHT JOIN users ON eventx.UserId = users.UserId where users.username = ?\r\n" + 
                      	//	"ORDER BY eventx.EventId;";
                  	String sql = "SELECT eventx.date, eventx.time,eventx.place,eventx.description,eventx.EventId from eventx " +
                  			"where UserId = 1 and time = '" + textField.getText() + "'";
                          		
                      Statement statement = con.createStatement();
                      //((PreparedStatement) statement).setString(1, user);
                      ResultSet resultSet = statement.executeQuery(sql);
                      ResultSetMetaData metaData = resultSet.getMetaData();
                      int columns = metaData.getColumnCount();
                      for (int i = 1; i <= columns; i++) {
                      	//if(i != 5)
                      	//{
                          columnNames.addElement(metaData.getColumnName(i));
                      	//}
                      }
                      
                      while (resultSet.next()) {
                          Vector row = new Vector(columns);
                          for (int i = 1; i <= columns; i++) {
                          	if(i==1)
                          	{
                          		System.out.println(resultSet.getObject(i));
                          		myDates.addElement((String) resultSet.getObject(i));
                          	}
                          
                            
                              row.addElement(resultSet.getObject(i));
                          }
                          data.addElement(row);
                      }
                      
                 
                  
                  JTable table = new JTable(data, columnNames);
                  DefaultTableModel model = new DefaultTableModel(data, columnNames);
                  table.setColumnSelectionAllowed(true);
                  table.setCellSelectionEnabled(true);
                  JScrollPane scrollPane = new JScrollPane(table);
  	              frame2.getContentPane().add(scrollPane, BorderLayout.CENTER);          
                  TableColumn column;
                  for (int i = 0; i < table.getColumnCount(); i++) {
                      column = table.getColumnModel().getColumn(i);
                      column.setMaxWidth(250);
                  } 
                  
             
                  
                  // Change A JTable Background Color, Font Size, Font Color, Row Height
                  table.setBackground(Color.GREEN);
                  table.setForeground(Color.BLUE);
                  Font font = new Font("",1,22);
                  table.setFont(font);
                  table.setRowHeight(25);
                  
  	       
  			      frame2.setSize(300,300);
  		          frame2.setLocationRelativeTo(null);
  		     //frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  		          frame2.setVisible(true);   
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
        label.setIcon(new ImageIcon("C:\\Users\\Minoiu Emi\\Pictures\\blue_gradient-wallpaper-1280x720.jpg"));
        label.setBounds(0, 0, 428, 263);
        frame1.getContentPane().add(label);
		JLabel lblMainmenu = new JLabel("MainMenu");
		lblMainmenu.setForeground(Color.RED);
		lblMainmenu.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblMainmenu.setBackground(Color.CYAN);
		lblMainmenu.setBounds(155, 49, 95, -9);
		//contentPane.add(lblMainmenu);
		 frame1.setSize(441,307);
	     frame1.setLocationRelativeTo(null);
	     //frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	     frame1.setVisible(true);  
      }
}
