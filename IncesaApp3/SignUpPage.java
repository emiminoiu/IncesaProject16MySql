package IncesaApp3;


import java.awt.BorderLayout;
import java.util.Random;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.FontUIResource;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
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
public class SignUpPage extends JFrame {

	private JPanel contentPane;
	//public String getUsername()
//	{
	//return username.getText();
	//}
	int ok = 0;
	int xx,xy;
	private JTextField userField;
	private JTextField passField;
	Random rand = new Random();

	int  Nr = rand.nextInt(50000) + 1;
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
	public SignUpPage() throws Exception{
		//Home home = this;
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 652, 447);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		
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
		
		JLabel lblNewLabel = new JLabel("IncesaApp3-mysql");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabel.setForeground(new Color(0, 255, 127));
		lblNewLabel.setBounds(188, 16, 339, 36);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("(RegistrationPage)");
		lblNewLabel_1.setForeground(new Color(0, 255, 0));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblNewLabel_1.setBounds(213, 52, 248, 36);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Username");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblNewLabel_2.setForeground(new Color(0, 250, 154));
		lblNewLabel_2.setBounds(64, 136, 133, 36);
		contentPane.add(lblNewLabel_2);
		
		userField = new JTextField();
		userField.setBounds(212, 136, 266, 42);
		contentPane.add(userField);
		userField.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setForeground(new Color(0, 250, 154));
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblPassword.setBounds(66, 199, 133, 36);
		contentPane.add(lblPassword);
		
		passField= new JTextField();
	    passField.setColumns(10);
		passField.setBounds(212, 199, 266, 42);
		contentPane.add(passField);
		
		JButton btncreate = new JButton("CREATE");
		btncreate.setFont(new Font("Tahoma", Font.ITALIC, 20));
		btncreate.setBackground(new Color(0, 255, 0));
		btncreate.setForeground(new Color(30, 144, 255));
		btncreate.setBounds(212, 257, 266, 42);
		contentPane.add(btncreate);
		
		 btncreate.addActionListener(new ActionListener(){
 
             @Override
             public void actionPerformed(ActionEvent e) {
            	 
            Connection con = null;
            try {
            	String username = userField.getText();
            	String password = userField.getText();
            	Nr++;
            	Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            	con = DriverManager.getConnection("jdbc:mysql://localhost:3306/eventmanagernew?user=student1&password=student1&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
                
            	String sql = "insert into `users`(`UserId`,`username`,`password`) values ("+ Nr  + ", '" + username + "','" + password + "');";
                Statement statement = con.createStatement();
                //((PreparedStatement) statement).setString(1, user);
                statement.executeUpdate(sql);
             
            
            statement.close();
        } catch (Exception x) {
            System.out.println(x);
        }
        	UIManager.put("OptionPane.buttonFont", new FontUIResource(new Font("ARIAL",Font.PLAIN,25)));
    		UIManager.put("OptionPane.minimumSize",new Dimension(400,200)); 
    		UIManager.put("OptionPane.messageFont", new FontUIResource(new Font(  
    		          "Arial", Font.BOLD, 18)));
    		UIManager.put("OptionPane.background", Color.GREEN);
    		UIManager.put("Button.background",Color.green); 
    		JOptionPane.showConfirmDialog(null,  "Your account has been successfully created", "IncesaApp3", JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.PLAIN_MESSAGE);
             }
         });
		JLabel lblNewLabel_3 = new JLabel("Create an account");
		lblNewLabel_3.setForeground(new Color(0, 255, 127));
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_3.setBounds(64, 89, 257, 31);
		contentPane.add(lblNewLabel_3);
		
		JButton btnBack = new JButton("<-Back to Login");
		btnBack.setBackground(new Color(0, 255, 0));
		btnBack.setForeground(new Color(30, 144, 255));
		btnBack.setFont(new Font("Tahoma", Font.ITALIC, 20));
		btnBack.setBounds(213, 315, 265, 45);
		contentPane.add(btnBack);
		 btnBack.addActionListener(new ActionListener(){

             @Override
             public void actionPerformed(ActionEvent e) {
              
               Home home;
			try {
				home = new Home();
				home.setVisible(true);
	            SignUpPage.this.setVisible(false);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
              
               
             }
         });
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon("C:\\Users\\Minoiu Emi\\Pictures\\blue_gradient-wallpaper-1280x720.jpg"));
		label.setBounds(0, 0, 697, 472);
		contentPane.add(label);
	}
}
