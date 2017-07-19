import java.sql.*;
class TheInstituteDatabase
{
	public static Connection con;
	public static Statement stmt;
	public static ResultSet rs; 
	
	static
	{
		try
		{
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/theinstitutedb","root","admin");
		}
		catch(SQLException ex)
		{
			try
			{
				con=DriverManager.getConnection("jdbc:mysql://localhost:3306","root","admin");
				stmt=con.createStatement();
				stmt.execute("create database theinstitutedb");
				stmt.close();
				con.close();
				con=DriverManager.getConnection("jdbc:mysql://localhost:3306/theinstitutedb","root","admin");
			}
			catch(SQLException e)
			{
			}
		}
		try
		{
			stmt=con.createStatement();
			try
			{
				stmt.execute("create table login(login_id smallint,username varchar(10),password varchar(10),name varchar(20),MobileNo varchar(10),designation varchar(20),add_by varchar(10),primary key(login_id),unique(username))");
				stmt.execute("insert into login values('0','myself','admin12','Harshita','9782590536','admin','bydefault')");
				stmt.execute("insert into login values('1','manageinst','manager12','Lakshya','9829244263','manager','bydefault')");
				stmt.execute("insert into login values('2','manageacc','account12','Shipra','8769003391','account_manager','bydefault')");
				
				stmt.execute("create table course(course_id smallint,course_name varchar(10),course_fees smallint,approx_duration varchar(10),add_by varchar(10),primary key(course_id),unique(course_name))");
				stmt.execute("insert into course values('0','C',4000,'3Months','myself')");
				stmt.execute("insert into course values('1','C++',4500,'3Months','myself')");
				stmt.execute("insert into course values('2','java',5500,'3.5Months','myself')");
				
				stmt.execute("create table batch(course_id smallint,batch_id smallint,batch_name varchar(20),batch_start_time time,start_am_pm varchar(5),batch_end_time time,end_am_pm varchar(5),batch_duration time,batch_start_date date,faculty_id smallint,add_by varchar(10),primary key(course_id,batch_id))");
				stmt.execute("insert into batch values('0','1','Pinnacle','5:30:00','P.M.','6:30:00','P.M.','1:00:00','2015-05-3','2','myself')");
				
				stmt.execute("create table student(stu_id smallint,stu_name varchar(30),stu_mobile_no varchar(10),stu_father_name varchar(30),stu_father_mobile_no varchar(10),stu_address varchar(50),stu_clg varchar(20),course_id smallint,batch_id smallint,add_by varchar(10),add_date date,primary key(stu_id))");
				stmt.execute("insert into student values('0','Harshita Khandelwal','9782590536','Ajay Khandelwal','9829244263','63,shivpuri,murlipura,jaipur','Jecrc','0','1','myself','2015-03-15')");
				
				stmt.execute("create table faculty(faculty_id smallint,faculty_name varchar(20),faculty_address varchar(50),faculty_mobile_no varchar(10),fac_qualification varchar(10),course_id smallint,add_date date,add_by varchar(10),primary key(faculty_id))");
				stmt.execute("insert into faculty values('1','P.K.Tiwari','66,Murlipura,jaipur','2587413690','M.tech','1','2013-05-12','myself')");
				
				stmt.execute("create table query(query_id smallint,query_name varchar(30),query_mobile_no varchar(10),query_father_name varchar(30),query_father_mobile_no varchar(10),query_address varchar(50),query_clg varchar(20),course_id smallint,add_by varchar(10),add_date date,primary key(query_id))");
				stmt.execute("insert into query values('0','Lakshya Khandelwal','9782590536','Ajay Khandelwal','9829244263','63,shivpuri,murlipura,jaipur','Jaipur school','1','myself','2015-03-15')");
				
				stmt.execute("create table receipt(receipt_no smallint,fee_deposit smallint,receipt_generated_by varchar(10),rec_generation_date date,rec_generation_time time)");
				stmt.execute("insert into receipt values('1','3000','myself','2015-12-03','3:10:12')");
				
				stmt.execute("create table fee(receipt_no smallint,stu_id smallint)");
				stmt.execute("insert into fee values('1','0')");
			}
			catch(SQLException r)
			{
			}
			/*try
			{
			
			}
			catch(SQLException r)
			{
			}*/
		}
		catch(SQLException exc)
		{
		}
	}
	public void finalize()
	{
		try
		{
			rs.close();
			stmt.close();
			con.close();
		}
		catch(SQLException e)
		{
		}
	}
}