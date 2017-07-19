package Database;
import java.sql.*;
public class Db
{
	public static Connection con;
	public static Statement stmt;
	public static ResultSet rs; 
	public static PreparedStatement pstmt;
	static
	{
		try
		{
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/theinstitutedb","root","admin");
			stmt=con.createStatement();
		}
		catch(SQLException ex)
		{
			
		}
	}
	public void finalize()
	{
		try
		{
			pstmt.close();
			rs.close();
			stmt.close();
			con.close();
		}
		catch(SQLException e)
		{
		}
	}
}