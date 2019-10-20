 package DBConnect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class OrclConnect {
	final static String URL="jdbc:mysql://localhost:3306/FeeManagement"; 
	final static String USER_NAME="root";
	final static String PASSWORD="hp15p251nz";
	
	public static Connection getConnection() {
		Connection con=null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection(URL,USER_NAME,PASSWORD);
			System.out.println("DB connected Successfully");
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return con;
	}
}
