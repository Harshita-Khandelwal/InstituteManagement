package Database;
import java.sql.*;
public class Student implements java.io.Serializable
{
	private int id;
	private String stname;
	private String stmobno;
	private String stfname;
	private String stfmobno;
	private String staddress;
	private String stclg;
	private int cid;
	private String stbatch;
	private int bid;
	private String add_by;
	private Date add_date;
	public Student()
	{
	}
	public void setId(int id)
	{
		this.id=id;
	}
	public void setStname(String stname)
	{
		this.stname=stname;
	}
	public void setStmobno(String stmobno)
	{
		this.stmobno=stmobno;
	}
	public void setStfname(String stfname)
	{
		this.stfname=stfname;
	}
	public void setStfmobno(String stfmobno)
	{
		this.stfmobno=stfmobno;
	}
	public void setStaddress(String staddress)
	{
		this.staddress=staddress;
	}
	public void setStclg(String stclg)
	{
		this.stclg=stclg;
	}
	public void setCid(int cid)
	{
		this.cid=cid;
	}
	public void setStbatch(String stbatch)
	{
		this.stbatch=stbatch;
	}
	public void setBid(int bid)
	{
		this.bid=bid;
	}
	public void setAdd_by(String add_by)
	{
		this.add_by=add_by;
	}
	public void setAdd_date(Date add_date)
	{
		this.add_date=add_date;
	}
	public int getId()
	{
		return id;
	}
	public String getStname()
	{
		return stname;
	}
	public String getStmobno()
	{
		return stmobno;
	}
	public String getStfname()
	{
		return stfname;
	}
	public String getStfmobno()
	{
		return stfmobno;
	}
	public String getStaddress()
	{
		return staddress;
	}
	public String getStclg()
	{
		return stclg;
	}
	public int getCid()
	{
		return cid;
	}
	public String getStbatch()
	{
		return stbatch;
	}
	public int getBid()
	{
		return bid;
	}
	public String getAdd_by()
	{
		return add_by;
	}
	public Date getAdd_date()
	{
		return add_date;
	}
	public int addNew()
	{
	try
	{
			Db.pstmt=Db.con.prepareStatement("insert into student values(?,?,?,?,?,?,?,?,?,?,?)");
			Db.pstmt.setInt(1,id);
			Db.pstmt.setString(2,stname);
			Db.pstmt.setString(3,stmobno);
			Db.pstmt.setString(4,stfname);
			Db.pstmt.setString(5,stfmobno);
			Db.pstmt.setString(6,staddress);
			Db.pstmt.setString(7,stclg);
			Db.pstmt.setInt(8,cid);
			Db.pstmt.setInt(9,bid);
			Db.pstmt.setString(10,add_by);
			Db.pstmt.setDate(11,add_date);
			Db.pstmt.execute();
			Db.pstmt.close();
			return 1;
	}
	catch(SQLException e)
	{
		return 0;
	}
	}
	public int updateS()
	{
	try
	{
			Db.pstmt=Db.con.prepareStatement("update student set stu_id=?,stu_name=?,stu_mobile_no=?,stu_father_name=?,stu_father_mobile_no=?,stu_address=?,stu_clg=?,course_id=?,batch_id=?,add_by=?,add_date=? where stu_id="+id);
			Db.pstmt.setInt(1,id);
			Db.pstmt.setString(2,stname);
			Db.pstmt.setString(3,stmobno);
			Db.pstmt.setString(4,stfname);
			Db.pstmt.setString(5,stfmobno);
			Db.pstmt.setString(6,staddress);
			Db.pstmt.setString(7,stclg);
			Db.pstmt.setInt(8,cid);
			Db.pstmt.setInt(9,bid);
			Db.pstmt.setString(10,add_by);
			Db.pstmt.setDate(11,add_date);
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