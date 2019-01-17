package jdbc;
import java.sql.*;


public class JDBC_Connection {

	public static void main(String[] argv) {

		System.out.println("-------- Oracle JDBC Connection Testing ------");

		try {

			Class.forName("oracle.jdbc.driver.OracleDriver");

		} catch (ClassNotFoundException e) {

			System.out.println("Where is your Oracle JDBC Driver?");
			e.printStackTrace();
			return;

		}

		System.out.println("Oracle JDBC Driver Registered!");

		Connection connection = null;

//below include your login in place of <netid login> and your Oracle (not netid) password in place of <oracle pwd>
      try {
                connection = DriverManager.getConnection(
                            "jdbc:oracle:thin:@localhost:1533/cse1.omega.uta.edu", "sxk2046", "Apple123");

         } catch (SQLException e) {
                System.out.println("Connection Failed! Check output console");
                e.printStackTrace();
                return;
                }
                
		if (connection != null) {
			System.out.println("You made it, take control your database now!");
		} else {
			System.out.println("Failed to make connection!");
		}
      try {
          Statement stmt = connection.createStatement();
	       ResultSet rs = stmt.executeQuery("select * from F18_08_RentalStore");
	       while (rs.next())
	         System.out.println(rs.getString("phone")+" "+rs.getString("city"));
	       rs.close();
	       stmt.close();
          connection.close();
      }
      catch (SQLException e) {

			System.out.println("erro in accessing the relation");
			e.printStackTrace();
			return;

		}    
	}


}
