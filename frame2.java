package jdbc;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.sql.*;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class frame2 {

	private JFrame frame;
	private JTextField rental_start;
	private JTextField rental_end;
	private JTextField odo_end;
	private JTextField odo_start;
	private JTextField rental_cost;
	private JTextField car_pick;
	private JTextField car_drop;
	private JTextField Cust_id;
	private JTextField car_id;
	private JTextField store_id;
	private JButton btnNewButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame2 window = new frame2();
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
	public frame2() {
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
		
		JLabel lblRentalStartDate = new JLabel("Rental start date");
		lblRentalStartDate.setBounds(10, 11, 89, 14);
		frame.getContentPane().add(lblRentalStartDate);
		
		JLabel lblRentalEndDate = new JLabel("Rental end date");
		lblRentalEndDate.setBounds(10, 36, 89, 14);
		frame.getContentPane().add(lblRentalEndDate);
		
		JLabel lblOdometerStart = new JLabel("Odometer end date");
		lblOdometerStart.setBounds(10, 61, 99, 14);
		frame.getContentPane().add(lblOdometerStart);
		
		JLabel lblOdometerStartDate = new JLabel("Odometer start date");
		lblOdometerStartDate.setBounds(10, 86, 99, 14);
		frame.getContentPane().add(lblOdometerStartDate);
		
		JLabel lblRentalCostPer = new JLabel("Rental cost per day");
		lblRentalCostPer.setBounds(10, 111, 99, 14);
		frame.getContentPane().add(lblRentalCostPer);
		
		JLabel lblCarPickupCity = new JLabel("Car pick-up city");
		lblCarPickupCity.setBounds(10, 136, 89, 14);
		frame.getContentPane().add(lblCarPickupCity);
		
		JLabel lblCarDropCity = new JLabel("Car drop city");
		lblCarDropCity.setBounds(10, 161, 89, 14);
		frame.getContentPane().add(lblCarDropCity);
		
		JLabel lblCustomerId = new JLabel("Customer ID");
		lblCustomerId.setBounds(10, 186, 89, 14);
		frame.getContentPane().add(lblCustomerId);
		
		JLabel lblCarId = new JLabel("Car ID");
		lblCarId.setBounds(10, 211, 89, 14);
		frame.getContentPane().add(lblCarId);
		
		JLabel lblStoreId = new JLabel("Store ID");
		lblStoreId.setBounds(10, 236, 89, 14);
		frame.getContentPane().add(lblStoreId);
		
		rental_start = new JTextField();
		rental_start.setBounds(152, 8, 86, 20);
		frame.getContentPane().add(rental_start);
		rental_start.setColumns(10);
		
		rental_end = new JTextField();
		rental_end.setColumns(10);
		rental_end.setBounds(152, 33, 86, 20);
		frame.getContentPane().add(rental_end);
		
		odo_end = new JTextField();
		odo_end.setColumns(10);
		odo_end.setBounds(152, 58, 86, 20);
		frame.getContentPane().add(odo_end);
		
		odo_start = new JTextField();
		odo_start.setColumns(10);
		odo_start.setBounds(152, 83, 86, 20);
		frame.getContentPane().add(odo_start);
		
		rental_cost = new JTextField();
		rental_cost.setColumns(10);
		rental_cost.setBounds(152, 108, 86, 20);
		frame.getContentPane().add(rental_cost);
		
		car_pick = new JTextField();
		car_pick.setColumns(10);
		car_pick.setBounds(152, 133, 86, 20);
		frame.getContentPane().add(car_pick);
		
		car_drop = new JTextField();
		car_drop.setColumns(10);
		car_drop.setBounds(152, 158, 86, 20);
		frame.getContentPane().add(car_drop);
		
		Cust_id = new JTextField();
		Cust_id.setColumns(10);
		Cust_id.setBounds(152, 183, 86, 20);
		frame.getContentPane().add(Cust_id);
		
		car_id = new JTextField();
		car_id.setColumns(10);
		car_id.setBounds(152, 208, 86, 20);
		frame.getContentPane().add(car_id);
		
		store_id = new JTextField();
		store_id.setColumns(10);
		store_id.setBounds(152, 233, 86, 20);
		frame.getContentPane().add(store_id);

		
		try {

			Class.forName("oracle.jdbc.driver.OracleDriver");

		} catch (ClassNotFoundException e) {

			System.out.println("Where is your Oracle JDBC Driver?");
			e.printStackTrace();
			return;

		}

		System.out.println("Oracle JDBC Driver Registered!");

		
		
		btnNewButton = new JButton("reserve");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Connection connection = DriverManager.getConnection(
                            "jdbc:oracle:thin:@localhost:1533/cse1.omega.uta.edu", "sxk2046", "Apple123");
					
					Statement stmt = connection.createStatement();
					int custid = Integer.parseInt(Cust_id.getText());
					int carid = Integer.parseInt(car_id.getText());
					int storeid = Integer.parseInt(store_id.getText());
					int start = Integer.parseInt(odo_start.getText());
					int end = Integer.parseInt(odo_end.getText());
					int cost = Integer.parseInt(rental_cost.getText());
					String sql = "insert into F18_08_RentalReservation(Rental_Start_Date,Rental_End_Date,Odometer_End_Read,Odometer_Start_Read,RentalCost_per_day,CarPickupCity,CarDropCity,Customer_ID,Car_ID,Store_ID)" + "VALUES( '"+ rental_start.getText()+" ', ' "+rental_end.getText()+" ', "+end+",  " +start+" , "+cost+", ' "+car_pick.getText()+" ', ' "+car_drop.getText()+"'," +custid+","+carid+","+storeid+" )";
					System.out.println(sql);
					stmt.executeUpdate(sql);
					stmt.close();
					connection.close();
					JOptionPane.showMessageDialog(null, "reservation added");
					
				}
				catch(Exception e) {
					System.out.println(e);
				}
			
				}
		});
		btnNewButton.setBounds(300, 107, 91, 23);
		frame.getContentPane().add(btnNewButton);
		
			}

}
