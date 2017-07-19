package Database;
import java.sql.*;
public class Fee 
{
	private int receipt_no;
	private int stu_id;
	private int fee_deposit;
	private String receipt_generated_by;
	private Date rec_generation_date;
	private Time rec_generation_time;
	public Fee()
	{}
	public void setReceipt_no(int receipt_no)
	{
		this.receipt_no=receipt_no;
	}
	public int getReceipt_no()
	{
		return receipt_no;
	}
	public void setStu_id(int stu_id)
	{
		this.stu_id=stu_id;
	}
	public int getStu_id()
	{
		return stu_id;
	}
	public void setFee_deposit(int fee_deposit)
	{
		this.fee_deposit=fee_deposit;
	}
	public int getFee_deposit()
	{
		return fee_deposit;
	}
	public void setReceipt_generated_by(String receipt_generated_by)
	{
		this.receipt_generated_by=receipt_generated_by;
	}
	public String getReceipt_generated_by()
	{
		return receipt_generated_by;
	}
	public void setRec_generation_date(Date rec_generation_date)
	{
		this.rec_generation_date=rec_generation_date;
	}
	public Date getRec_generation_date()
	{
		return rec_generation_date;
	}
	public void setRec_generation_time(Time rec_generation_time)
	{
		this.rec_generation_time=rec_generation_time;
	}
	public Time getRec_generation_time()
	{
		return rec_generation_time;
	}
	public int calc_due(String stu_id)
	{
		int due=0;
		try
		{
			java.util.Vector rno=new java.util.Vector();
			int temp,dep=0,i=0;
			Db.rs=Db.stmt.executeQuery("select receipt_no from fee where stu_id=\""+stu_id+"\"");
			while(Db.rs.next()==true)
			{
				rno.add(Db.rs.getString("receipt_no"));
			}
			while(i<rno.size())
			{
			String r=(String)rno.get(i);
			Db.rs=Db.stmt.executeQuery("select fee_deposit from receipt where receipt_no=\""+r+"\"");
			Db.rs.next();
			temp=Db.rs.getInt("fee_deposit");
			dep=dep+temp;
				i++;
			}
			Db.rs=Db.stmt.executeQuery("select course_id from student where stu_id=\""+stu_id+"\"");
			Db.rs.next();
			String yc=Db.rs.getString("course_id");
			Db.rs=Db.stmt.executeQuery("select course_fees from course where course_id=\""+yc+"\"");
			Db.rs.next();
			String ycfee=Db.rs.getString("course_fees");
			due=Integer.parseInt(ycfee)-dep;
		}
		catch(SQLException e)
		{
		}
		return due;
	}
	public int addNew()
	{
		try
		{
			Db.pstmt=Db.con.prepareStatement("insert into receipt values(?,?,?,?,?)");
			Db.pstmt.setInt(1,receipt_no);
			Db.pstmt.setInt(2,fee_deposit);
			Db.pstmt.setString(3,receipt_generated_by);
			Db.pstmt.setDate(4,rec_generation_date);
			Db.pstmt.setTime(5,rec_generation_time);
			Db.pstmt.execute();
			Db.pstmt.close();
			Db.pstmt=Db.con.prepareStatement("insert into fee values(?,?)");
			Db.pstmt.setInt(1,receipt_no);
			Db.pstmt.setInt(2,stu_id);
			Db.pstmt.execute();
			Db.pstmt.close();
			return 1;
		}
		catch(SQLException r)
		{
			return 0;
		}
	}
}