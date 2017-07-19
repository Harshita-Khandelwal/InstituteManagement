package Database;
import java.sql.*;
public class Query 
{
	private int query_id;
	private String query_name;
	private String query_address;
	private String query_father_name;
	private String query_father_mobile_no;
	private String query_mobile_no;
	private String query_clg;
	private int course_id;
	private Date add_date;
	private String add_by;
	public Query()
	{}
	public int getQuery_id() {
		return query_id;
	}
	public void setQuery_id(int query_id) {
		this.query_id = query_id;
	}
	public String getQuery_name() {
		return query_name;
	}
	public void setQuery_name(String query_name) {
		this.query_name = query_name;
	}
	public String getQuery_address() {
		return query_address;
	}
	public void setQuery_address(String query_address) {
		this.query_address = query_address;
	}
	public String getQuery_mobile_no() {
		return query_mobile_no;
	}
	public void setQuery_mobile_no(String query_mobile_no) {
		this.query_mobile_no = query_mobile_no;
	}
	public String getQuery_father_mobile_no() {
		return query_father_mobile_no;
	}
	public void setQuery_father_mobile_no(String query_father_mobile_no) {
		this.query_father_mobile_no = query_father_mobile_no;
	}
	public String getQuery_father_name() {
		return query_father_name;
	}
	public void setQuery_father_name(String query_father_name) {
		this.query_father_name = query_father_name;
	}
	public String getQuery_clg() {
		return query_clg;
	}
	public void setQuery_clg(String query_clg) {
		this.query_clg = query_clg;
	}
	public int getCourse_id() {
		return course_id;
	}
	public void setCourse_id(int course_id) {
		this.course_id = course_id;
	}
	public Date getAdd_date() {
		return add_date;
	}
	public void setAdd_date(Date add_date) {
		this.add_date = add_date;
	}
	public String getAdd_by() {
		return add_by;
	}
	public void setAdd_by(String add_by) {
		this.add_by = add_by;
	}
	public int addNew()
	{
		try
		{
			Db.pstmt=Db.con.prepareStatement("insert into query values(?,?,?,?,?,?,?,?,?,?)");
			Db.pstmt.setInt(1,query_id);
			Db.pstmt.setString(2,query_name);
			Db.pstmt.setString(3,query_mobile_no);
			Db.pstmt.setString(4,query_father_name);
			Db.pstmt.setString(5,query_father_mobile_no);
			Db.pstmt.setString(6,query_address);
			Db.pstmt.setString(7,query_clg);
			Db.pstmt.setInt(8,course_id);
			Db.pstmt.setString(9,add_by);
			Db.pstmt.setDate(10,add_date);
			Db.pstmt.execute();
			Db.pstmt.close();
			return 1;
		}
		catch(SQLException e)
		{
			return 0;
		}
		
	}
	public int updateQ()
	{
		try
		{
			Db.pstmt=Db.con.prepareStatement("update query set query_id=?,query_name=?,query_mobile_no=?,query_father_name=?,query_father_mobile_no=?,query_address=?,query_clg=?,course_id=?,add_by=?,add_date=? where query_id="+query_id);
			Db.pstmt.setInt(1,query_id);
			Db.pstmt.setString(2,query_name);
			Db.pstmt.setString(3,query_mobile_no);
			Db.pstmt.setString(4,query_father_name);
			Db.pstmt.setString(5,query_father_mobile_no);
			Db.pstmt.setString(6,query_address);
			Db.pstmt.setString(7,query_clg);
			Db.pstmt.setInt(8,course_id);
			Db.pstmt.setString(9,add_by);
			Db.pstmt.setDate(10,add_date);
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
