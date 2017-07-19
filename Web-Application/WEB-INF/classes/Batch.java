package Database;
import java.sql.*;
public class Batch implements java.io.Serializable
{
	private int course_id;
	private int batch_id;
	private String batch_name;
	private String batch_start_time;
	private String start_am_pm;
	private String hr;
	private String min;
	private String batch_start_date;
	private int faculty_id;
	private String add_by;
	private String batch_end_time;
	private String end_am_pm;
	public Batch()
	{
	}
	public void setCourse_id(int course_id)
	{
		this.course_id=course_id;
	}
	public void setBatch_id(int batch_id)
	{
		this.batch_id=batch_id;
	}
	public void setBatch_name(String batch_name)
	{
		this.batch_name=batch_name;
	}
	public void setBatch_start_time(String batch_start_time)
	{
		this.batch_start_time=batch_start_time;
	}
	public void setStart_am_pm(String start_am_pm)
	{
		this.start_am_pm=start_am_pm;
	}
	public void setHr(String hr)
	{
		this.hr=hr;
	}
	public void setMin(String min)
	{
		this.min=min;
	}
	public void setBatch_start_date(String batch_start_date)
	{
		this.batch_start_date=batch_start_date;
	}
	public void setFaculty_id(int faculty_id)
	{
		this.faculty_id=faculty_id;
	}
	public void setAdd_by(String add_by)
	{
		this.add_by=add_by;
	}
	public void setBatch_end_time(String batch_end_time)
	{
		this.batch_end_time=batch_end_time;
	}
	public void setEnd_am_pm(String end_am_pm)
	{
		this.end_am_pm=end_am_pm;
	}
	public int getCourse_id()
	{
		return course_id;
	}
	public int getBatch_id()
	{
		return batch_id;
	}
	public String getBatch_name()
	{
		return batch_name;
	}
	public String getBatch_start_time()
	{
		return batch_start_time;
	}
	public String getStart_am_pm()
	{
		return start_am_pm;
	}
	public String getHr()
	{
		return hr;
	}
	public String getMin()
	{
		return min;
	}
	public String getBatch_start_date()
	{
		return batch_start_date;
	}
	public int getFaculty_id()
	{
		return faculty_id;
	}
	public String getAdd_by()
	{
		return add_by;
	}
	public String getBatch_end_time()
	{
		return batch_end_time;
	}
	public String getEnd_am_pm()
	{
		return end_am_pm;
	}
	public int calc_course_id(String course)
	{
		try
		{
		Db.rs=Db.stmt.executeQuery("select course_id from course where course_name=\""+course+"\"");
		Db.rs.next();
		int cid=Db.rs.getInt("course_id");
		return cid;
		}
		catch(Exception r) // handling exception and not SQLException bcoz if there is no entry in resultset then getString returns nothing so null pointer exception will come which need to be handled
		{
		}
		return 0;
	}
	public int calc_batch_id(int cid)
	{
		try
		{
		Db.rs=Db.stmt.executeQuery("select max(batch_id) as highest from batch where course_id=\""+cid+"\"");
		Db.rs.next();
		int bid=Db.rs.getInt("highest");
		bid++;
		return bid;
		}
		catch(Exception r) // handling exception and not SQLException bcoz if there is no entry in resultset then getString returns nothing so null pointer exception will come which need to be handled
		{
		}
		return 0;
	}
	public int addNew()
	{
		try
		{
				String duration;
				if(min.equals("0"))
				{
					min="00";
				}
				duration=hr+":"+min+":00";
				java.sql.Date d1=new Date(System.currentTimeMillis());
				java.sql.Date d2=Date.valueOf(batch_start_date);
				if(d2.before(d1)==true)
				{
					return -1;
				}
				
				Db.pstmt=Db.con.prepareStatement("insert into batch values(?,?,?,?,?,?,?,?,?,?,?)");
				Db.pstmt.setInt(1,course_id);
				Db.pstmt.setInt(2,batch_id);
				Db.pstmt.setString(3,batch_name);
				Db.pstmt.setString(4,batch_start_time);
				Db.pstmt.setString(5,start_am_pm);
				Db.pstmt.setString(6,batch_end_time);
				Db.pstmt.setString(7,end_am_pm);
				Db.pstmt.setString(8,duration);
				Db.pstmt.setString(9,batch_start_date);
				Db.pstmt.setInt(10,faculty_id);
				Db.pstmt.setString(11,add_by);
				Db.pstmt.execute();
				Db.pstmt.close();
				return 1;
		}
		catch(SQLException e)
		{
			return 0;
		}
	}
	public int updateB()
	{
		try
		{
			String duration;
			if(min.equals("0"))
			{
				min="00";
			}
			duration=hr+":"+min+":00";
			Db.pstmt=Db.con.prepareStatement("update batch set course_id=?,batch_id=?,batch_name=?,batch_start_time=?,start_am_pm=?,batch_end_time=?,end_am_pm=?,batch_duration=?,batch_start_date=?,faculty_id=?,add_by=? where course_id="+course_id+" and batch_id="+batch_id);
			Db.pstmt.setInt(1,course_id);
			Db.pstmt.setInt(2,batch_id);
			Db.pstmt.setString(3,batch_name);
			Db.pstmt.setString(4,batch_start_time);
			Db.pstmt.setString(5,start_am_pm);
			Db.pstmt.setString(6,batch_end_time);
			Db.pstmt.setString(7,end_am_pm);
			Db.pstmt.setString(8,duration);
			Db.pstmt.setString(9,batch_start_date);
			Db.pstmt.setInt(10,faculty_id);
			Db.pstmt.setString(11,add_by);
			Db.pstmt.execute();
			Db.pstmt.close();
			return 1;
		}
		catch(SQLException e)
		{
			return 0;
		}
	}

	public java.util.Vector calc_batch_faculty(int cc2)
	{
		try
		{
			String cc1;
			java.util.Vector fn=new java.util.Vector();
			Db.rs=Db.stmt.executeQuery("select faculty_name from faculty where course_id=\""+cc2+"\"");
			if(Db.rs.next()==true)
			{
				Db.rs.previous();
				while(Db.rs.next()==true)
				{
					fn.add(Db.rs.getString("faculty_name"));
				}
			}
			else
			{
				return null;
			}
			return fn;
		}
		catch(SQLException r)
		{
		}
		return null;
	}
	public String calc_batch_end_time()
	{
		String t,endt,temp;
		Integer hr1,min1;
		int h,m;
		t=start_am_pm;
		hr1=new Integer(hr);
		min1=new Integer(min);
		h=Integer.parseInt(batch_start_time.substring(0,batch_start_time.indexOf(":")));
		m=Integer.parseInt(batch_start_time.substring(batch_start_time.indexOf(":")+1,batch_start_time.lastIndexOf(":")));
		h=h+hr1;
		if(min1==30)
		{
			m=0;
			h=h+1;
		}
		if((h>12))
		{
			h=h-12;
			t="P.M.";
		}
		else if(h==12 & m==30)
		{
			t="P.M.";
		}
		else if(h==12 & m==0)
		{
			t="noon";
		}
		if(m==0)
		{
			temp="00";
		}
		else
		{
			temp=Integer.toString(m);
		}
		endt=Integer.toString(h)+":"+temp+":00 "+t;
		return endt;
	}
	
}