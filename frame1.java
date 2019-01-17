package jdbc;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;

public class frame1 {

	private JFrame frame;
	private JTextField name;
	private JTextField dob;
	private JTextField gen;
	private JTextField phn;
	private JTextField ci;
	private JTextField id;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame1 window = new frame1();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public frame1() {
		initialize();
	}
    
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblCustomerName = new JLabel("customer name");
		lblCustomerName.setBounds(10, 11, 80, 14);
		frame.getContentPane().add(lblCustomerName);
		
		name = new JTextField();
		name.setBounds(213, 8, 86, 20);
		frame.getContentPane().add(name);
		name.setColumns(10);
		
		JLabel lblDateOfBirthddmmmyyyy = new JLabel("date of birth(dd-mmm-yyyy)");
		lblDateOfBirthddmmmyyyy.setBounds(10, 47, 136, 14);
		frame.getContentPane().add(lblDateOfBirthddmmmyyyy);
		
		dob = new JTextField();
		dob.setColumns(10);
		dob.setBounds(213, 44, 86, 20);
		frame.getContentPane().add(dob);
		
		JLabel lblGenderMOr = new JLabel("gender( m or f)");
		lblGenderMOr.setBounds(10, 82, 80, 14);
		frame.getContentPane().add(lblGenderMOr);
		
		gen = new JTextField();
		gen.setColumns(10);
		gen.setBounds(213, 79, 86, 20);
		frame.getContentPane().add(gen);
		
		JLabel lblPhoneNumber = new JLabel("Phone number");
		lblPhoneNumber.setBounds(10, 117, 136, 14);
		frame.getContentPane().add(lblPhoneNumber);
		
		phn = new JTextField();
		phn.setColumns(10);
		phn.setBounds(213, 114, 86, 20);
		frame.getContentPane().add(phn);
		
		JLabel lblCity = new JLabel("city");
		lblCity.setBounds(10, 154, 136, 14);
		frame.getContentPane().add(lblCity);
		
		ci = new JTextField();
		ci.setColumns(10);
		ci.setBounds(213, 151, 86, 20);
		frame.getContentPane().add(ci);
		
		JLabel textLabelID = new JLabel("customer ID");
		textLabelID.setBounds(10, 187, 136, 14);
		frame.getContentPane().add(textLabelID);
		
		id = new JTextField();
		id.setColumns(10);
		id.setBounds(213, 184, 86, 20);
		frame.getContentPane().add(id);
		
		
		try {

			Class.forName("oracle.jdbc.driver.OracleDriver");

		} catch (ClassNotFoundException e) {

			System.out.println("Where is your Oracle JDBC Driver?");
			e.printStackTrace();
			return;

		}

		System.out.println("Oracle JDBC Driver Registered!");
		
		JButton submitBtn = new JButton("SUBMIT");
		submitBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Connection connection = DriverManager.getConnection(
                            "jdbc:oracle:thin:@localhost:1533/cse1.omega.uta.edu", "sxk2046", "Apple123");
					
					Statement stmt = connection.createStatement();
					int cid = Integer.parseInt(id.getText());
					
					String sql = "insert into F18_08_Customer(Customer_ID, Name, date_of_birth, gender, phone, city)" + "VALUES( "+cid+" , ' "+ name.getText()+" ', ' "+dob.getText()+" ','"+gen.getText()+"', ' "+phn.getText()+" ',' "+ci.getText()+" ')";
					stmt.executeUpdate(sql);
					stmt.close();
					connection.close();
					JOptionPane.showMessageDialog(null, "Submission done!");
					
					
					
					
				}
				catch(Exception e) {
					System.out.println(e);
				}
				
			}
		});
		submitBtn.setBounds(120, 227, 91, 23);
		frame.getContentPane().add(submitBtn);
		
		
	}
}
