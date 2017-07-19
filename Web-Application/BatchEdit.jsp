<%@ page language="java" import="Database.Db,java.sql.*,java.util.*"%>
<jsp:useBean id="batch" class="Database.Batch" scope="request" />

<link rel="stylesheet" href="styles.css">
<jsp:include page="adminheader.jsp"  />

<%! String c,start_am_pm,batch_start_time,batch_duration,hr,min,uptime;
	int course_id,batch_id;
	Vector fna;
%>
<% 
	c=request.getParameter("course_name");
	course_id=Integer.parseInt(request.getParameter("course_id"));
	 batch_id=Integer.parseInt(request.getParameter("batch_id"));
	 Db.rs=Db.stmt.executeQuery("select * from batch where course_id="+course_id+" and batch_id="+batch_id);
	Db.rs.next();
 %>
<br/><br/>
<center>
<div align="center" style="color:black;width:800px;height:300px;background:white ">
<table>
<tr>
	<h1>Update Batch</h1>
</tr>
<h2><form method=post action="CreateBatch.jsp">
<tr><td>Course Name:</td><td><%= c %></td></tr>
<tr><td>Batch ID:</td><td>C<%= course_id %>B<%= batch_id %></td></tr>
<tr><td>Batch Name:</td><td><input type=text name=batch_name maxlength="20" required value="<%= Db.rs.getString("batch_name")%>"></td></tr>
<%	uptime=request.getParameter("uptime");
	if(uptime!=null)
	{
		start_am_pm=request.getParameter("start_am_pm");
		batch_start_time=request.getParameter("batch_start_time");
	}
	else
	{
		start_am_pm=Db.rs.getString("start_am_pm");
		batch_start_time=Db.rs.getString("batch_start_time");
	}
	batch_duration=Db.rs.getString("batch_duration");
%>
<tr>
	<td>Batch Starting Time:</td>
	<td><%= batch_start_time%><%= start_am_pm %></td>
	<td>
		<%application.setAttribute("course_name",c);
		application.setAttribute("course_id",course_id);
		application.setAttribute("batch_id",batch_id);
		%>
		<input type=button onClick="parent.location='BatchEditT.jsp'" value='Update Time'>

	</td>
</tr>
<tr>
<td>Batch Duration:</td>
<% hr=batch_duration.substring(0,batch_duration.indexOf(":"));
	min=batch_duration.substring(batch_duration.indexOf(":")+1,batch_duration.lastIndexOf(":"));
%>
<td>
	<select name=hr>
	<%
		if(hr.equals("01"))
		{
	%>
	<option value=1 selected>1</option>
	<option value=2>2</option>
	<option value=3>3</option>
	<option value=4>4</option>
	<option value=5>5</option>
	<%
		}
		else if(hr.equals("02"))
		{
	%>
	<option value=1>1</option>
	<option value=2 selected>2</option>
	<option value=3>3</option>
	<option value=4>4</option>
	<option value=5>5</option>
	<%
		}
		else if(hr.equals("03"))
		{
	%>
	<option value=1>1</option>
	<option value=2>2</option>
	<option value=3 selected>3</option>
	<option value=4>4</option>
	<option value=5>5</option>
	<%
		}
		else if(hr.equals("04"))
		{
	%><option value=1>1</option>
	<option value=2>2</option>
	<option value=3>3</option>
	<option value=4 selected>4</option>
	<option value=5>5</option>
	<%
		}
		else if(hr.equals("05"))
		{
	%>
	<option value=1>1</option>
	<option value=2>2</option>
	<option value=3>3</option>
	<option value=4>4</option>
	<option value=5 selected>5</option>
	<%
		}
	%>
	</select>hours
</td>
<td>
	<select name=min>
	<%
		if(min.equals("00"))
		{
	%>
	<option value=0 selected>0</option>
	<option value=30>30</option>
	<%
		}
		else if(min.equals("30"))
		{
	%>
	<option value=0>0</option>
	<option value=30 selected>30</option>
	<%
		}
		%>
	</select>minutes
</td>
</tr>
<tr>
<td>Batch Starting Date:</td>
<td><select name=yr>

	<%! java.sql.Date dt;
		String u;
		int a,b,r,k;
	%>
	<%	dt=Db.rs.getDate("batch_start_date");
		u=dt.toString();
		a=Integer.parseInt(u.substring(0,u.indexOf("-")));
		b=Integer.parseInt(u.substring(u.indexOf("-")+1,u.lastIndexOf("-")));
		r=Integer.parseInt(u.substring(u.lastIndexOf("-")+1));
		Integer y[]=new Integer[5];
		y[0]=a;
		for(int k=1;k<5;k++)
		{
			y[k]=y[k-1]+1;
		}
		for(Integer x:y)
		{
	%>
	<option>
<%= x %>
<%
		}
%>
</option></select>
<select name=month><option>
<%
	for(k=0;k<12;k++)
	{
		if(k+1==b)
		{
			%>
			<option selected="selected">
			<%			
		}
		else
		{
%>	<option>
<%  } %>
<%=	k+1 %>
<%	}	%>
</option>
</select>
<select name=date><option>
<%
	for(k=0;k<31;k++)
	{
		if(k+1==r)
		{
			%>
			<option selected="selected">
			<%			
		}
		else
		{
%>	<option>
<%  } %>
<%=	k+1 %>
<%	}	%>
</option>
</select>
</td>
</tr>
<tr><td>Faculty:</td>
<td><select name="faculty_name">
<%! String fac;
	int fid;
%>
<%
	fid=Db.rs.getInt("faculty_id");
	Db.rs=Db.stmt.executeQuery("select faculty_name from faculty where faculty_id="+fid);
	Db.rs.next();
	fac=Db.rs.getString("faculty_name");
%>
<%
fna=batch.calc_batch_faculty(course_id);
if(fna==null)
{
	fna=new Vector();
	fna.add("None");
}
for(Object x:fna)
{
%>
<option>
<%= x %>
<%}%>
</option>
</select>
<font color=red>
<%=fac%> was the faculty of this batch
</font>
</td>
</tr>
<tr><td><input type=submit name=Submit value=Submit></td>
<td><input type=reset name=Cancel value=Cancel></td>
</tr>

		<input type="hidden" name="course_id" value= <%= course_id %> />
		<input type="hidden" name="batch_id" value= <%= batch_id %> />
		<input type="hidden" name="start_am_pm" value= <%= start_am_pm %> />
		<input type="hidden" name="batch_start_time" value= <%= batch_start_time %> />
<input type="hidden" name="updateB" value="true"/>
		
</form></h2>
</table>
</div>
</center><br/>
<%@ include file="footer.html" %>