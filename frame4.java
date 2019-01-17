package jdbc;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import net.proteanit.sql.DbUtils;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class frame4 {

	private JFrame frame;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame4 window = new frame4();
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
	public frame4() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 980, 290);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblGenerateAllCustomer = new JLabel("generate all customer names who took premium cars");
		lblGenerateAllCustomer.setBounds(10, 11, 257, 14);
		frame.getContentPane().add(lblGenerateAllCustomer);
		
		try {

			Class.forName("oracle.jdbc.driver.OracleDriver");

		} catch (ClassNotFoundException e) {

			System.out.println("Where is your Oracle JDBC Driver?");
			e.printStackTrace();
			return;

		}

		System.out.println("Oracle JDBC Driver Registered!");
		
		
		JButton gen_1 = new JButton("generate");
		gen_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Connection connection = DriverManager.getConnection(
                            "jdbc:oracle:thin:@localhost:1533/cse1.omega.uta.edu", "sxk2046", "Apple123");
					
				
					String query = "Select DISTINCT c.Name FROM F18_08_Customer c, F18_08_RentalReservation r, F18_08_RentalCar t WHERE c.Customer_ID = r.Customer_ID AND r.Car_ID = t.Car_ID AND t.CarType="+"'Premium'";    
					PreparedStatement pat = connection.prepareStatement(query);
					ResultSet rs = pat.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
					
				}
				catch(Exception e) {
					System.out.println(e);
				}
					
				
			}
				
		});
		gen_1.setBounds(333, 7, 91, 23);
		frame.getContentPane().add(gen_1);
		
		JLabel lblWhichCarsNeed = new JLabel("Which cars need to be upgraded?(assuming 10 years of life time)");
		lblWhichCarsNeed.setBounds(10, 36, 311, 14);
		frame.getContentPane().add(lblWhichCarsNeed);
		
		JButton gen_2 = new JButton("generate");
		gen_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg1) {
				try {
					Connection connection = DriverManager.getConnection(
                            "jdbc:oracle:thin:@localhost:1533/cse1.omega.uta.edu", "sxk2046", "Apple123");
					
				
					String query = "Select Car_ID from F18_08_RentalCar WHERE Production_year <1998";   
					PreparedStatement pat = connection.prepareStatement(query);
					ResultSet rs = pat.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
					
				}
				catch(Exception e) {
					System.out.println(e);
				}
			}
		});
		gen_2.setBounds(333, 32, 91, 23);
		frame.getContentPane().add(gen_2);
		
		JLabel lblWhichLocationHas = new JLabel("which location has more number of car rentals");
		lblWhichLocationHas.setBounds(10, 61, 311, 14);
		frame.getContentPane().add(lblWhichLocationHas);
		
		JButton gen_3 = new JButton("generate");
		gen_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg3) {
				
				try {
					Connection connection = DriverManager.getConnection(
                            "jdbc:oracle:thin:@localhost:1533/cse1.omega.uta.edu", "sxk2046", "Apple123");
					
				
					String query = "Select CarDropCity, COUNT(CarDropCity) c FROM F18_08_RentalReservation GROUP BY CarDropCity ORDER BY c DESC";    
					PreparedStatement pat = connection.prepareStatement(query);
					ResultSet rs = pat.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
					
				}
				catch(Exception e) {
					System.out.println(e);
				}
			}
		});
		gen_3.setBounds(333, 57, 91, 23);
		frame.getContentPane().add(gen_3);
		
		JLabel lblShowTheTotal = new JLabel("show the total percentage change in the amount");
		lblShowTheTotal.setBounds(10, 86, 311, 14);
		frame.getContentPane().add(lblShowTheTotal);
		
		JButton gen_4 = new JButton("generate");
		gen_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg4) {
				try {
					Connection connection = DriverManager.getConnection(
                            "jdbc:oracle:thin:@localhost:1533/cse1.omega.uta.edu", "sxk2046", "Apple123");
					
				
					String query = "SELECT EXTRACT(year from t.Rental_Start_Date) \"Yr\", EXTRACT(month FROM t.Rental_Start_Date) \"Mn\", ROUND((t.RentalCost_per_day- l.RentalCost_per_day) * 100 / t.RentalCost_per_day, 2) percent\r\n" + 
							"FROM F18_08_RentalReservation l\r\n" + 
							"INNER JOIN F18_08_RentalReservation t \r\n" + 
							"ON EXTRACT(month FROM t.Rental_Start_Date) = EXTRACT(month FROM l.Rental_Start_Date) AND EXTRACT(year FROM l.Rental_Start_Date) = EXTRACT(year FROM t.Rental_Start_Date) - 1 where EXTRACT(year FROM t.Rental_Start_Date)=2018";    
					PreparedStatement pat = connection.prepareStatement(query);
					ResultSet rs = pat.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
					
				}
				catch(Exception e) {
					System.out.println(e);
				}
			}
		});
		gen_4.setBounds(333, 82, 91, 23);
		frame.getContentPane().add(gen_4);
		
		JLabel lblHowManySedan = new JLabel("how many sedan car types exist in stock");
		lblHowManySedan.setBounds(10, 111, 311, 14);
		frame.getContentPane().add(lblHowManySedan);
		
		JButton gen_5 = new JButton("generate");
		gen_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg5) {
				
				try {
					Connection connection = DriverManager.getConnection(
                            "jdbc:oracle:thin:@localhost:1533/cse1.omega.uta.edu", "sxk2046", "Apple123");
					
				
					String query = "Select COUNT(*) \"Sedans\" FROM F18_08_RentalCar WHERE CarType ="+"'Sedan'";
					PreparedStatement pat = connection.prepareStatement(query);
					ResultSet rs = pat.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
					
				}
				catch(Exception e) {
					System.out.println(e);
				}
				
				
			}
		});
		gen_5.setBounds(333, 107, 91, 23);
		frame.getContentPane().add(gen_5);
		
		JLabel lblShowCarIds = new JLabel("show car IDs with rental cost greater than $12");
		lblShowCarIds.setBounds(10, 136, 311, 14);
		frame.getContentPane().add(lblShowCarIds);
		
		JButton gen_6 = new JButton("generate");
		gen_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg6) {
				
				try {
					Connection connection = DriverManager.getConnection(
                            "jdbc:oracle:thin:@localhost:1533/cse1.omega.uta.edu", "sxk2046", "Apple123");
					
				
					String query ="Select DISTINCT Car_ID FROM F18_08_RentalReservation WHERE RentalCost_per_day >= 12";    
					PreparedStatement pat = connection.prepareStatement(query);
					ResultSet rs = pat.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
					
				}
				catch(Exception e) {
					System.out.println(e);
				}
			}
		});
		gen_6.setBounds(333, 132, 91, 23);
		frame.getContentPane().add(gen_6);
		
		JLabel lblToFindCar = new JLabel("to find car ID's and car models which have been rented more than 2 times");
		lblToFindCar.setBounds(10, 161, 311, 14);
		frame.getContentPane().add(lblToFindCar);
		
		JButton gen_7 = new JButton("generate");
		gen_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg7) {
				try {
					Connection connection = DriverManager.getConnection(
                            "jdbc:oracle:thin:@localhost:1533/cse1.omega.uta.edu", "sxk2046", "Apple123");
					
				
					String query = " SELECT CAR_ID,CARMODEL\r\n" + 
							" FROM F18_08_RENTALCAR V\r\n" + 
							" WHERE 2<=(SELECT COUNT(*)\r\n" + 
							" FROM F18_08_RENTALRESERVATION B\r\n" + 
							" WHERE V.CAR_ID=B.CAR_ID)";    
					PreparedStatement pat = connection.prepareStatement(query);
					ResultSet rs = pat.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
					
				}
				catch(Exception e) {
					System.out.println(e);
				}
			}
		});
		gen_7.setBounds(333, 157, 91, 23);
		frame.getContentPane().add(gen_7);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(537, 32, 400, 181);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
	}
}
