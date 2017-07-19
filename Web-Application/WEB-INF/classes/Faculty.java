package Database;
import java.sql.*;
public class Faculty 
{
	private int faculty_id;
	private String faculty_name;
	private String faculty_address;
	private String faculty_mobile_no;
	private String fac_qualification;
	private int course_id;
	private Date add_date;
	private String add_by;
	public Faculty()
	{}
	public int getFaculty_id() {
		return faculty_id;
	}
	public void setFaculty_id(int faculty_id) {
		this.faculty_id = faculty_id;
	}
	public String getFaculty_name() {
		return faculty_name;
	}
	public void setFaculty_name(String faculty_name) {
		this.faculty_name = faculty_name;
	}
	public String getFaculty_address() {
		return faculty_address;
	}
	public void setFaculty_address(String faculty_address) {
		this.faculty_address = faculty_address;
	}
	public String getFaculty_mobile_no() {
		return faculty_mobile_no;
	}
	public void setFaculty_mobile_no(String faculty_mobile_no) {
		this.faculty_mobile_no = faculty_mobile_no;
	}
	public String getFac_qualification() {
		return fac_qualification;
	}
	public void setFac_qualification(String fac_qualification) {
		this.fac_qualification = fac_qualification;
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
			Db.pstmt=Db.con.prepareStatement("insert into faculty values(?,?,?,?,?,?,?,?)");
			Db.pstmt.setInt(1,faculty_id);
			Db.pstmt.setString(2,faculty_name);
			Db.pstmt.setString(3,faculty_address);
			Db.pstmt.setString(4,faculty_mobile_no);
			Db.pstmt.setString(5,fac_qualification);
			Db.pstmt.setInt(6,course_id);
			Db.pstmt.setDate(7,add_date);
			Db.pstmt.setString(8,add_by);
			Db.pstmt.execute();
			Db.pstmt.close();
			return 1;
		}
		catch(SQLException e)
		{
			return 0;
		}	
	}
	public int updateF()
	{
		try
		{
			Db.pstmt=Db.con.prepareStatement("update faculty set faculty_id=?,faculty_name=?,faculty_address=?,faculty_mobile_no=?,fac_qualification=?,course_id=?,add_date=?,add_by=? where faculty_id="+faculty_id);
			Db.pstmt.setInt(1,faculty_id);
			Db.pstmt.setString(2,faculty_name);
			Db.pstmt.setString(3,faculty_address);
			Db.pstmt.setString(4,faculty_mobile_no);
			Db.pstmt.setString(5,fac_qualification);
			Db.pstmt.setInt(6,course_id);
			Db.pstmt.setDate(7,add_date);
			Db.pstmt.setString(8,add_by);
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
