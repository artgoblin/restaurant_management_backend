package crude;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conn {
	
	private static Connection conn;
	private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";

	
	public static void createConnection(String DB_URL, String USER, String PASS) {
		
		try {
			
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			System.out.println("CONNECTION ESTABLISHED SUCCESSFULLY");
			
		} catch(Exception e) {
			
			e.printStackTrace();
			
		}
	}
	
	public static Connection dbconnector() {
		
		return conn;
	}
	
	public static void closeConnection() {
		
		if(conn != null) {
			try {
				conn.close();
				System.out.println("DISCONNECTED SUCCESSFULLY");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}

