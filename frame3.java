package jdbc;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class frame3 {

	private JFrame frame;
	private JTextField store_id;
	private JTextField phn;
	private JTextField city;
	private JButton btnNewButton_1;
	private JButton btnNewButton;
	private JButton storeBtn;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame3 window = new frame3();
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
	public frame3() {
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
		
		JLabel lblNewLabel = new JLabel("Store ID");
		lblNewLabel.setBounds(10, 21, 46, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblPhoneNumber = new JLabel("Phone number");
		lblPhoneNumber.setBounds(10, 46, 77, 14);
		frame.getContentPane().add(lblPhoneNumber);
		
		JLabel lblCity = new JLabel("city");
		lblCity.setBounds(10, 71, 46, 14);
		frame.getContentPane().add(lblCity);
		
		store_id = new JTextField();
		store_id.setBounds(157, 18, 86, 20);
		frame.getContentPane().add(store_id);
		store_id.setColumns(10);
		
		phn = new JTextField();
		phn.setColumns(10);
		phn.setBounds(157, 43, 86, 20);
		frame.getContentPane().add(phn);
		
		city = new JTextField();
		city.setColumns(10);
		city.setBounds(157, 68, 86, 20);
		frame.getContentPane().add(city);
		
		try {

			Class.forName("oracle.jdbc.driver.OracleDriver");

		} catch (ClassNotFoundException e) {

			System.out.println("Where is your Oracle JDBC Driver?");
			e.printStackTrace();
			return;

		}

		System.out.println("Oracle JDBC Driver Registered!");
		
		storeBtn = new JButton("Add Store");
		storeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Connection connection = DriverManager.getConnection(
                            "jdbc:oracle:thin:@localhost:1533/cse1.omega.uta.edu", "sxk2046", "Apple123");
					
					Statement stmt = connection.createStatement();
					int sid = Integer.parseInt(store_id.getText());
					
					String sql = "insert into F18_08_RentalStore(Store_ID, phone, city)" + "VALUES( "+sid+" , ' "+ phn.getText()+" ', ' "+city.getText()+" ')";
					stmt.executeUpdate(sql);
					stmt.close();
					connection.close();
					JOptionPane.showMessageDialog(null, "Added the Store!");
					
					
				}
				catch(Exception e) {
					System.out.println(e);
				}
			}
		});
		storeBtn.setBounds(73, 120, 91, 23);
		frame.getContentPane().add(storeBtn);
		
		
		
	
	}

}
