package utility;

import java.sql.Connection;
import java.sql.DriverManager;

public class JdbcUtility {
	

	public static Connection makeConnection() {
		Connection connection=null;
		try
		{
		Class.forName("org.postgresql.Driver");
		 connection=DriverManager.getConnection("jdbc:postgresql://localhost:5432/VarshaTours","postgres","postgres");
		
		
		
		}catch(Exception e)
		{
			System.out.println(e.getMessage());
		}finally {
			System.out.println("Connection Established");
		}
		
		return connection;
	}

}
