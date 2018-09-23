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
import java.util.Vector;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Vector;

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
public class MainContent extends JFrame{
	 public void displayGUI() {
		    JOptionPane.showMessageDialog(null, getPanel(), "Guide : ",
		        JOptionPane.INFORMATION_MESSAGE);
		  }
	  public JPanel getPanel() {
		    JPanel panel = new JPanel(new GridLayout(0, 1, 7, 7));
		    JLabel checkevents = getLabel("CheckEventsButton : Shows you all the events which are taking place in the current day." );
		    JLabel add = getLabel("AddButton : After completing all 4 text fields addbutton will add the event in your Agenda.");
		    JLabel delete = getLabel("DeleteButton : This will delete the selected event from your Agenda.");
		    JLabel update = getLabel("UpdateButton : Modify the text fields that you want,click update and this will update the events for you. ");
		    JLabel export = getLabel("ExportButton : This button will export all your events to an XML file format. ");
		    JLabel part = getLabel("SeeParticipantsButton : This button will show you all the users that are entried to the selected event. ");
		    JLabel propose = getLabel("ProposeButton : This will add the proposed event to every users's Agenda. ");
		    checkevents.setForeground(Color.RED);
	        checkevents.setFont(new Font("Tahoma", Font.BOLD, 20));
	        add.setForeground(Color.RED);
	        add.setFont(new Font("Tahoma", Font.BOLD, 20));
	        delete.setForeground(Color.RED);
	        delete.setFont(new Font("Tahoma", Font.BOLD, 20));
	        update.setForeground(Color.RED);
	        update.setFont(new Font("Tahoma", Font.BOLD, 20));
	        export.setForeground(Color.RED);
	        export.setFont(new Font("Tahoma", Font.BOLD, 20));
	        part.setForeground(Color.RED);
	        part.setFont(new Font("Tahoma", Font.BOLD, 20));
	        propose.setForeground(Color.RED);
	        propose.setFont(new Font("Tahoma", Font.BOLD, 20));
		    panel.add(checkevents);
		    panel.add(add);
		    panel.add(delete);
		    panel.add(update);
		    panel.add(export);
		    panel.add(part);
		    panel.add(propose);
		    return panel;
		  }

		  private JLabel getLabel(String title) {
		    return new JLabel(title);
		  }
	public Home home;
	public String user;
    public static Vector<String> v = new Vector<String>();
    public static Vector<String> myPlaces = new Vector<String>();
    public static Vector<String> myHours = new Vector<String>();
    public static Vector<String> myDescription = new Vector<String>();
    public static Vector<Integer> myEventIds = new Vector<Integer>();
    
	private static final JLabel lblIncesaapp = new JLabel("IncesaApp");
	private String pattern = "dd.MM.yyyy";
	String dateInString =new SimpleDateFormat(pattern).format(new Date());
    public static Vector<String> myDates = new Vector<String>();
    public int Nr = -1;
    public int counter = 0;
    JFrame frame1 = new JFrame();
    JFrame frame = new JFrame();
    public MainContent(Home home) throws IOException{
        MainContent mc = this;
        // create JFrame and JTable
     //   JFrame frame = new JFrame();
        
        System.out.println(dateInString);
        // create a table model and set a Column Identifiers to this model 
        Vector<String> columnNames = new Vector<String>();
        Vector data = new Vector();
        Vector<String> columnNames1 = new Vector<String>();
        Vector data1 = new Vector();
        JPanel panel = new JPanel();   //
        Connection con = null;
        
	    user = home.getUsername();
        try {
        	Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
        	con = DriverManager.getConnection("jdbc:mysql://localhost:3306/eventmanagernew?user=student1&password=student1&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
            //String sql = "SELECT eventx.date, eventx.time,eventx.place,eventx.description \r\n" + 
            //		"from eventx\r\n" + 
            	//	"RIGHT JOIN users ON eventx.UserId = users.UserId where users.username = ?\r\n" + 
            	//	"ORDER BY eventx.EventId;";
        	String sql = "SELECT eventx.date, eventx.time,eventx.place,eventx.description,eventx.EventId from eventx " +
        			"RIGHT JOIN users ON eventx.UserId = users.UserId where users.username = '" + user +
                		"' ORDER BY eventx.EventId";
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
                	if(i==2)
                	{
                		myHours.addElement((String) resultSet.getObject(i));
                	}
                	if(i==3)
                	{
                		myPlaces.addElement((String) resultSet.getObject(i));
                	}
                	if(i==5)
                	{
                		myEventIds.addElement((Integer) resultSet.getObject(i));
                	}
                  
                    row.addElement(resultSet.getObject(i));
                }
                data.addElement(row);
            }
            
       
        
        JTable table = new JTable(data, columnNames);
        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        table.setColumnSelectionAllowed(true);
        table.setCellSelectionEnabled(true);
                
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
        
        // create JTextFields
        JTextField date = new JTextField();
        JTextField time = new JTextField();
        JTextField place = new JTextField();
        JTextField description = new JTextField();
        
        // create JButtons
        JButton btnAdd = new JButton("Add");
        btnAdd.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnAdd.setBackground(Color.RED);
        btnAdd.setForeground(Color.WHITE);
        JButton btnDelete = new JButton("Delete");
        btnDelete.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnDelete.setBackground(Color.RED);
        btnDelete.setForeground(Color.WHITE);
        JButton btnUpdate = new JButton("Update");     
        btnUpdate.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnUpdate.setBackground(Color.RED);
        btnUpdate.setForeground(Color.WHITE);
        JButton export = new JButton("ExportEvents");
        export.setFont(new Font("Tahoma", Font.BOLD, 16));
        date.setBounds(241, 291, 162, 40);
        time.setBounds(15, 291, 170, 40);
        place.setBounds(456, 291, 137, 40);
        description.setBounds(638, 291, 306, 40);
        
        btnAdd.setBounds(638, 349, 131, 37);
        btnUpdate.setBounds(456, 349, 137, 37);
        btnDelete.setBounds(241, 347, 162, 39);
        JButton btnCheckMyEvents = new JButton("Check my Events");
        btnCheckMyEvents.setBackground(Color.RED);
        btnCheckMyEvents.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnCheckMyEvents.setForeground(Color.WHITE);
       
        btnCheckMyEvents.setBounds(15, 347, 170, 39);
        frame.getContentPane().add(btnCheckMyEvents);

        export.setBackground(Color.RED);
        export.setForeground(Color.WHITE);
        export.setBounds(798, 349, 146, 37);
        frame.getContentPane().add(export);
        
        // create JScrollPane
        JScrollPane pane = new JScrollPane(table);
        pane.setBounds(0, 42, 959, 197);
        
        frame.getContentPane().setLayout(null);
        
        frame.getContentPane().add(pane);
        
        // add JTextFields to the jframe
        frame.getContentPane().add(date);
        frame.getContentPane().add(time);
        frame.getContentPane().add(place);
        frame.getContentPane().add(description);
    
        // add JButtons to the jframe
        frame.getContentPane().add(btnAdd);
        frame.getContentPane().add(btnDelete);
        frame.getContentPane().add(btnUpdate);
        
                
        JLabel lblNewLabel = new JLabel("IncesaApp - EventManagerApp");
        lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 25));
        lblNewLabel.setForeground(Color.RED);
        lblNewLabel.setBounds(336, 0, 385, 36);
        frame.getContentPane().add(lblNewLabel);
        
       
        
        JLabel lblDate = new JLabel("Date");
        lblDate.setFont(new Font("Tahoma", Font.BOLD, 25));
        lblDate.setForeground(Color.RED);
        lblDate.setBounds(289, 250, 69, 25);
        frame.getContentPane().add(lblDate);
        
        JLabel lblTime = new JLabel("Time");
        lblTime.setForeground(Color.RED);
        lblTime.setFont(new Font("Tahoma", Font.BOLD, 25));
        lblTime.setBounds(59, 255, 69, 20);
        frame.getContentPane().add(lblTime);
        
        JLabel lblPlace = new JLabel("Place");
        lblPlace.setForeground(Color.RED);
        lblPlace.setFont(new Font("Tahoma", Font.BOLD, 25));
        lblPlace.setBounds(487, 255, 69, 20);
        frame.getContentPane().add(lblPlace);
        
        JLabel lblEventdescription = new JLabel("EventDescription");
        lblEventdescription.setForeground(Color.RED);
        lblEventdescription.setFont(new Font("Tahoma", Font.BOLD, 25));
        lblEventdescription.setBounds(668, 243, 247, 40);
        frame.getContentPane().add(lblEventdescription);
        
        JButton btnSeePart = new JButton("See who will participate in this event");
        btnSeePart.setFont(new Font("Tahoma", Font.BOLD, 18));
        btnSeePart.setBackground(Color.RED);
        btnSeePart.setForeground(Color.WHITE);
        btnSeePart.setBounds(15, 415, 390, 45);
        frame.getContentPane().add(btnSeePart);
        btnSeePart.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
            	
                // i = the index of the selected row
                int i = table.getSelectedRow();
                System.out.println(myEventIds.get(i));
                try {
        			
    			    Connection con = null;
    				Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
    	        	con = DriverManager.getConnection("jdbc:mysql://localhost:3306/eventmanagernew?user=student1&password=student1&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
    	            //String sql = "SELECT eventx.date, eventx.time,eventx.place,eventx.description \r\n" + 
    	            //		"from eventx\r\n" + 
    	            	//	"RIGHT JOIN users ON eventx.UserId = users.UserId where users.username = ?\r\n" + 
    	            	//	"ORDER BY eventx.EventId;";
    	        	String sql = "select users.username from ((user_events inner join users ON user_events.UserId = users.UserId)"
    	        			+ "inner join eventx ON user_events.EventId = eventx.EventId) where eventx.EventId =" + myEventIds.get(i);
    	
    	            Statement statement = con.createStatement();
    	            //((PreparedStatement) statement).setString(1, user);
    	            ResultSet resultSet = statement.executeQuery(sql);
    	            System.out.println(resultSet);
    	            ResultSetMetaData metaData = resultSet.getMetaData();
    	            int columns = metaData.getColumnCount();
    	            for (int j = 1; j <= columns; j++) {
    	            	
    	                columnNames1.addElement(metaData.getColumnName(j));
    	            }
    	            while (resultSet.next()) {
    	                Vector row = new Vector(columns);
    	                for (int j = 1; j <= columns; j++) {
    	                    row.addElement(resultSet.getObject(j));
    	                }
    	                data1.addElement(row);
    	            }
    	            resultSet.close();
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
    	            System.out.println(data1);
    	            JTable table1 = new JTable(data1, columnNames1);
    	            DefaultTableModel model = new DefaultTableModel(data1, columnNames1);
    	            table1.setColumnSelectionAllowed(true);
    	            table1.setCellSelectionEnabled(true);
    	            JScrollPane scrollPane = new JScrollPane(table1);
    	            frame1.getContentPane().add(scrollPane, BorderLayout.CENTER);       
    	            TableColumn column;
    	            for (int j = 0; j < table1.getColumnCount(); j++) {
    	                column = table1.getColumnModel().getColumn(j);
    	                column.setMaxWidth(250);
    	            } 
    	           
    	            
    	            // Change A JTable Background Color, Font Size, Font Color, Row Height
    	            table1.setBackground(Color.GREEN);
    	            table1.setForeground(Color.BLUE);
    	            Font font = new Font("",1,22);
    	            table1.setFont(font);
    	            table1.setRowHeight(25);
    	          
    			
    			 frame1.setSize(300,300);
    		     frame1.setLocationRelativeTo(null);
    		     //frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    		     frame1.setVisible(true);   
 			
            }
        });
        JButton btnPropose = new JButton("Propose the event to everyone");
        btnPropose.setFont(new Font("Tahoma", Font.BOLD, 18));
        btnPropose.setBackground(Color.RED);
        btnPropose.setForeground(Color.WHITE);
        btnPropose.setBounds(456, 415, 313, 45);
        frame.getContentPane().add(btnPropose);
        
        JButton btnLogOut = new JButton("LogOut");
        btnLogOut.setFont(new Font("Tahoma", Font.BOLD, 18));
        btnLogOut.setBackground(Color.RED);
        btnLogOut.setForeground(Color.WHITE);
        btnLogOut.setBounds(800, 415, 144, 45);
        frame.getContentPane().add(btnLogOut);
       
        btnLogOut.addActionListener(new ActionListener(){
        	
            @Override
            public void actionPerformed(ActionEvent e) {
             Home home;
			try {
				home = new Home();
			    home.setVisible(true);
			    MainContent.this.setVisible(false);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		
			
            }
        });
        JLabel label = new JLabel("");
        label.setIcon(new ImageIcon("C:\\Users\\Minoiu Emi\\Pictures\\50-Beautiful-and-Minimalist-Presentation-Backgrounds-02.jpg"));
        label.setBounds(0, 242, 959, 285);
        frame.getContentPane().add(label);
        
        JButton btnGuide = new JButton("App's Guide");
        btnGuide.setBackground(Color.GREEN);
        btnGuide.setForeground(Color.BLUE);
        btnGuide.setBounds(798, 7, 117, 29);
        frame.getContentPane().add(btnGuide);
        btnGuide.addActionListener(new ActionListener(){
        	
            @Override
            public void actionPerformed(ActionEvent e) {
            displayGUI();			
		
			
            }
        });
        
        JLabel label_1 = new JLabel("");
        label_1.setIcon(new ImageIcon("C:\\Users\\Minoiu Emi\\Pictures\\50-Beautiful-and-Minimalist-Presentation-Backgrounds-02.jpg"));
        label_1.setBounds(0, 0, 959, 45);
        frame.getContentPane().add(label_1);
        
        // create an array of objects to set the row data
        Object[] row = new Object[4];
        
        // button add row
        btnAdd.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
             
                row[0] = date.getText();
                row[1] = time.getText();
                row[2] = place.getText();
                row[3] = description.getText();
                
                // add row to the model
                model.addRow(row);
                myDates.addElement((String) row[0]);
                myHours.addElement((String) row[1]);
                myPlaces.addElement((String) (row[2]));
                myDescription.addElement((String) row[3]);
            }
        });
        
        // button remove row
        btnDelete.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
            
                // i = the index of the selected row
                int i = table.getSelectedRow();
                if(i >= 0){
                    // remove a row from jtable
                      model.removeRow(i);
                      myDates.remove(i);
                      counter--;
                }
                else{
                    System.out.println("Delete Error");
                }
            }
        });
      
        export.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		

        		FileOutputStream fos;
				try {
					fos = new FileOutputStream("C:\\Users\\Minoiu Emi\\eclipse-workspace\\go.txt");
				
        		BufferedOutputStream bos = new BufferedOutputStream(fos);
				
        		XMLEncoder xmlEncoder = new XMLEncoder(bos);
				
        		xmlEncoder.writeObject(myHours);
        		xmlEncoder.writeObject(myPlaces);
        		xmlEncoder.writeObject(myDates);
        		xmlEncoder.writeObject(myDescription);
        		
        		xmlEncoder.close();
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
        		 /*
        		    FileOutputStream fos;
					try {
						fos = new FileOutputStream("./go.txt");
					
        		    XMLEncoder encoder = new XMLEncoder(fos);
        		    encoder.setExceptionListener(new ExceptionListener() {
        		            public void exceptionThrown(Exception e) {
        		                System.out.println("Exception! :"+e.toString());
        		            }
        		    });
        		    encoder.writeObject(table);
        		    encoder.close();
        		    fos.close();
        		
        	} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			*/
        	}
        });
        table.addMouseListener(new MouseAdapter(){
            
            @Override
            public void mouseClicked(MouseEvent e){
                
                // i = the index of the selected row
                int i = table.getSelectedRow();
                
                date.setText(model.getValueAt(i, counter).toString());
                counter++;
                time.setText(model.getValueAt(i, counter).toString());
                counter++;
                place.setText(model.getValueAt(i, counter).toString());
                counter++;
                description.setText(model.getValueAt(i, counter).toString());
                counter++;
            }
          
            });
           
            // button update row
            btnUpdate.addActionListener(new ActionListener(){

                @Override
                public void actionPerformed(ActionEvent e) {
                 
                    // i = the index of the selected row
                    int i = table.getSelectedRow();
                    myDates.insertElementAt(date.getText(), i);
                    myHours.insertElementAt(time.getText(), i);
                    myPlaces.insertElementAt(place.getText(), i);
                    myDescription.insertElementAt(description.getText(), i);
                    if(i >= 0) 
                    {
                       model.setValueAt(date.getText(), i, 0);
                       model.setValueAt(time.getText(), i, 1);
                       model.setValueAt(place.getText(), i, 2);
                       model.setValueAt(description.getText(), i, 3);
                    }
                   
                    else{
                        System.out.println("Update Error");
                    }
                      model.fireTableDataChanged();
               //     model.fireTableChanged(null);
                }
            });
        btnCheckMyEvents.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		UIManager.put("OptionPane.buttonFont", new FontUIResource(new Font("ARIAL",Font.PLAIN,25)));
        		UIManager.put("OptionPane.minimumSize",new Dimension(400,200)); 
        		UIManager.put("OptionPane.messageFont", new FontUIResource(new Font(  
        		          "Arial", Font.BOLD, 18)));
        		UIManager.put("OptionPane.background", Color.GREEN);
        		UIManager.put("Button.background",Color.green); 
        		//UIManager.put("OptionPane.foreground", Color.BLUE);
        		
        		for(String i:myDates)
        		{   
        			int reply = 1;
        			Nr++;
        			if (i.equals(dateInString) )
        			{
        			
    				     //System.out.println(i);
            			// System.out.println(dateInString);
      			     reply = JOptionPane.showConfirmDialog(null,  "You have some event/events today!Do you want to show the location and hour?","IncesaApp-EventManagerApp", JOptionPane.YES_NO_OPTION,
                                 JOptionPane.PLAIN_MESSAGE);
      			    
        			 if (reply == JOptionPane.YES_OPTION)
    			       {
    			    	   JOptionPane.showConfirmDialog(null, "Your events are taking place in " + myPlaces.get(Nr) + " at " + myHours.get(Nr), "IncesaApp-EventManagerApp",JOptionPane.OK_CANCEL_OPTION,
                                 JOptionPane.PLAIN_MESSAGE);
    			       }
        			}
        		
        		}
        		Nr = -1;
        		
        	   
        	}
        	
        });
    
        // get selected row data From table to textfields 
        frame.setSize(981,544);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);   
        resultSet.close();
        statement.close();
    } catch (Exception e) {
        System.out.println(e);
    }
       
    
    }
}