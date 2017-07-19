package Database;
import java.sql.*;
public class Course
{
	private int course_id;
	private String course_name;
	private int course_fees;
	private String approx_duration;
	private String add_by;
	public Course()
	{}
	public int getCourse_id() {
		return course_id;
	}
	public void setCourse_id(int course_id) {
		this.course_id = course_id;
	}
	public String getCourse_name() {
		return course_name;
	}
	public void setCourse_name(String course_name) {
		this.course_name = course_name;
	}
	public int getCourse_fees() {
		return course_fees;
	}
	public void setCourse_fees(int course_fees) {
		this.course_fees = course_fees;
	}
	public String getApprox_duration() {
		return approx_duration;
	}
	public void setApprox_duration(String approx_duration) {
		this.approx_duration = approx_duration;
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
			Db.pstmt=Db.con.prepareStatement("insert into course values(?,?,?,?,?)");
			Db.pstmt.setInt(1,course_id);
			Db.pstmt.setString(2,course_name);
			Db.pstmt.setInt(3,course_fees);
			Db.pstmt.setString(4,approx_duration);
			Db.pstmt.setString(5,add_by);
			Db.pstmt.execute();
			Db.pstmt.close();
			return 1;
		}
		catch(SQLException e)
		{
			return 0;
		}
	}
	
	public int updateC()
	{
		try
		{
			Db.pstmt=Db.con.prepareStatement("update course set course_id=?,course_name=?,course_fees=?,approx_duration=?,add_by=? where course_id="+course_id);
			Db.pstmt.setInt(1,course_id);
			Db.pstmt.setString(2,course_name);
			Db.pstmt.setInt(3,course_fees);
			Db.pstmt.setString(4,approx_duration);
			Db.pstmt.setString(5,add_by);
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
