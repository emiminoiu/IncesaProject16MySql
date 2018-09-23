package IncesaApp3;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Component;
import java.awt.Button;
import java.awt.SystemColor;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.IOException;
import java.sql.*;
import javax.swing.JButton;
import javax.swing.UIManager;
public class Home extends JFrame {

	private JPanel contentPane;
	JTextField username;
	public String getUsername()
	{
	return username.getText();
	}
	private JPasswordField password;
	int ok = 0;
	int xx,xy;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					 Home frame = new Home();
					 frame.setUndecorated(true);
					 frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	
	

	/**
	 * Create the frame.
	 */
	public Home() throws Exception{
		Home home = this;
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 719, 469);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		username = new JTextField();
		username.setBounds(382, 108, 283, 36);
		contentPane.add(username);
		username.setColumns(10);
		
		JLabel lblUsername = new JLabel("USERNAME");
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblUsername.setForeground(Color.GREEN);
		lblUsername.setBounds(382, 78, 114, 14);
		contentPane.add(lblUsername);
		
		JLabel lblPassword = new JLabel("PASSWORD");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblPassword.setForeground(Color.GREEN);
		lblPassword.setBounds(382, 172, 116, 27);
		contentPane.add(lblPassword);
		
		password = new JPasswordField();
		password.setBounds(382, 213, 283, 36);
		contentPane.add(password);
		
		Button button = new Button("Login");
		button.setActionCommand("LogIn");
		button.addActionListener( new ActionListener()
    	{
         public void actionPerformed(ActionEvent x)
         {
        	 Component frame = null;
        	 String uname = username.getText();
        	 String pass = password.getText();
        	 Connection con = null;
     		 PreparedStatement pst = null;
     		 ResultSet rs = null;
     	
        	try {
        		Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
        			// 1. Get a connection to database
        			
        			//DriverManager.registerDriver(new org.gjt.mm.mysql.Driver());
        		//	Class.forName("org.h.jdbcDriver");
        			String sql="select * from users where username=? and password=?";
        			try{
        			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
        			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/eventmanagernew?user=student1&password=student1&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
        			pst=con.prepareStatement(sql);
        			pst.setString(1, username.getText());
        			pst.setString(2,password.getText());
        			rs=pst.executeQuery();
        			
        			}
        			catch(SQLException | HeadlessException ex)
        			{
        			JOptionPane.showMessageDialog(null,ex);
        			}
        			
        			// 2. Create a statement
        			//myStmt = myConn.createStatement();
        			
        			// 3. Execute SQL query
        			//myRs = myStmt.executeQuery("select * from users where username = ? and password = ? ");
        			
        			// 4. Process the result set
        			try {
        				if(rs.next()) {
        					        	    
        					 JOptionPane.showMessageDialog(frame,"You are successfully logged");
        					 //TypeFileSelection tsf = new TypeFileSelection();
        				//	 tsf.setVisible(true);
        					
        						MainContent mc = new MainContent(home);
        						mc.setVisible(true);
        					
        					 
        				     Home.this.setVisible(false);
        				 }
        				 else
        				 {
        					 JOptionPane.showMessageDialog(frame,"Login failed");
        				 }
        			} catch (HeadlessException | SQLException e) {
        				// TODO Auto-generated catch block
        				e.printStackTrace();
        			}
        			
        		}
        		catch (Exception exc) {
        			exc.printStackTrace();
        		}
        		finally {
        			
        			if (rs != null) {
        				try {
							rs.close();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
        			}
        			
        			if (pst != null) {
        				try {
							pst.close();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
        			}
        			
        			if (con != null) {
        				try {
							con.close();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
        			}
        			
        		}
        		
        	
        	 /*if(uname.equals("parsing")&&(pass.equals("emimi98")))
        	 {
        	    
				 JOptionPane.showMessageDialog(frame,"You are successfully logged");
				 //TypeFileSelection tsf = new TypeFileSelection();
			//	 tsf.setVisible(true);
        	     Home.this.setVisible(false);
        	 }
        	 else
        	 {
        		 JOptionPane.showMessageDialog(frame,"Login failed");
        	 }
         }
         */
        	

         }
         
    	});
		button.setForeground(Color.WHITE);
		button.setBackground(new Color(255, 0, 0));
		button.setBounds(382, 277, 283, 36);
		contentPane.add(button);
		
		
		
		JLabel lbl_close = new JLabel("X");
		lbl_close.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				System.exit(0);
			}
		});
		lbl_close.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_close.setForeground(new Color(241, 57, 83));
		lbl_close.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lbl_close.setBounds(691, 0, 37, 27);
		contentPane.add(lbl_close);
		
		JLabel lblIncesaapp = new JLabel("IncesaApp3-mySql");
		lblIncesaapp.setFont(new Font("Times New Roman", Font.BOLD, 30));
		lblIncesaapp.setForeground(Color.RED);
		lblIncesaapp.setBounds(405, 16, 271, 36);
		contentPane.add(lblIncesaapp);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon("C:\\Users\\Minoiu Emi\\Pictures\\pexels-photo.jpg"));
		label.setBounds(0, 0, 354, 556);
		contentPane.add(label);
		
		JButton signUpButton = new JButton("SignUp");
	    signUpButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SignUpPage spage;
				try {
					spage = new SignUpPage();
					spage.setVisible(true);
					Home.this.setVisible(false);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		signUpButton.setBackground(new Color(255, 0, 51));
		signUpButton.setForeground(Color.WHITE);
		signUpButton.setBounds(382, 372, 283, 36);
		contentPane.add(signUpButton);
		
		JLabel lblNewLabel = new JLabel("Don't have an account?");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		lblNewLabel.setBackground(Color.RED);
		lblNewLabel.setForeground(Color.GREEN);
		lblNewLabel.setBounds(382, 329, 283, 27);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\Minoiu Emi\\Pictures\\50-Beautiful-and-Minimalist-Presentation-Backgrounds-02.jpg"));
		lblNewLabel_1.setBounds(351, 0, 377, 493);
		contentPane.add(lblNewLabel_1);
	}
}
