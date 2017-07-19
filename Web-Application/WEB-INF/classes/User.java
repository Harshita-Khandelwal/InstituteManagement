package Database;
import java.sql.*;
public class User
{
	private int login_id;
	private String username;
	private String password;
	private String confirm_password;
	private String name;
	private String mobile_no;
	private String designation;
	private String add_by;
	public User()
	{}
	public int getLogin_id() {
		return login_id;
	}
	public void setLogin_id(int login_id) {
		this.login_id = login_id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getConfirm_password() {
		return confirm_password;
	}
	public void setConfirm_password(String confirm_password) {
		this.confirm_password =confirm_password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name=name;
	}
	public String getMobile_no() {
		return mobile_no;
	}
	public void setMobile_no(String mobile_no) {
		this.mobile_no=mobile_no;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation=designation;
	}
	public String getAdd_by() {
		return add_by;
	}
	public void setAdd_by(String add_by) {
		this.add_by=add_by;
	}
	public int addNew()
	{
		try
		{
			Db.pstmt=Db.con.prepareStatement("insert into login values(?,?,?,?,?,?,?)");
			Db.pstmt.setInt(1,login_id);
			Db.pstmt.setString(2,username);
			Db.pstmt.setString(3,password);
			Db.pstmt.setString(4,name);
			Db.pstmt.setString(5,mobile_no);
			Db.pstmt.setString(6,designation);
			Db.pstmt.setString(7,add_by);
			Db.pstmt.execute();
			Db.pstmt.close();
			return 1;
		}
		catch(SQLException e)
		{
			return 0;
		}
	}
	public int updateU()
	{
		try
		{
			Db.pstmt=Db.con.prepareStatement("update login set login_id=?,username=?,password=?,name=?,MobileNo=?,designation=?,add_by=? where login_id="+login_id);
			Db.pstmt.setInt(1,login_id);
			Db.pstmt.setString(2,username);
			Db.pstmt.setString(3,password);
			Db.pstmt.setString(4,name);
			Db.pstmt.setString(5,mobile_no);
			Db.pstmt.setString(6,designation);
			Db.pstmt.setString(7,add_by);
			Db.pstmt.execute();
			Db.pstmt.close();
			return 1;
		}
		catch(SQLException e)
		{
			return 0;
		}
	}
}
