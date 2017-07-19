<%@ page language="java" import="Database.Db,java.sql.*" %>
<link rel="stylesheet" href="styles.css">
<jsp:include page="adminheader.jsp" />
<jsp:useBean id="batch" class="Database.Batch" scope="request" />
<jsp:setProperty name="batch" property="*" />

<%! String name,add_by,endt,batch_end_time,end_am_pm,batch_start_date,fname;
	int i,faculty_id;
	%>
<%
	name=(String)application.getAttribute("name");
	Database.Db.rs=Database.Db.stmt.executeQuery("select username from login where name=\""+name+"\"");
	Database.Db.rs.next();
	add_by=Database.Db.rs.getString("username");
	batch.setAdd_by(add_by);
%>
<%
	endt=batch.calc_batch_end_time();
	batch_end_time=endt.substring(0,endt.indexOf(" "));
	end_am_pm=endt.substring(endt.indexOf(" ")+1);
	batch.setBatch_end_time(batch_end_time);
	batch.setEnd_am_pm(end_am_pm);
%>
<%
	batch_start_date=request.getParameter("yr")+"-"+request.getParameter("month")+"-"+request.getParameter("date");
	batch.setBatch_start_date(batch_start_date);
%>
<%
	fname=request.getParameter("faculty_name");
	if(fname.equals("None"))
	{
		faculty_id=0;
	}
	else
	{
		Db.rs=Db.stmt.executeQuery("select faculty_id from faculty where faculty_name=\""+fname+"\"");
		Db.rs.next();
		faculty_id=Db.rs.getInt("faculty_id");
	}
	batch.setFaculty_id(faculty_id);
%>
<br/><br/><center>
<div align="center" style="color:black;width:800px;height:300px;background:white ">
<br/><h2>
<%!String u;%>
<%
u=request.getParameter("updateB");
if(u!=null)
{
%>
<% i=batch.updateB(); %>
<% 
		if(i==1)
		{
%>
Database Successfully Updated!!
<%
		}
		else if(i==0)
		{
%>
<br/>Error Occured....Batch Was Not Updated!!
<%
		}
%>
<br/><br/>
<a href="BatchNewC.jsp">Add New Batch</a>
<a href="BatchSearchOpt.jsp">View Batches</a>
<%
}
else
{
%>
	<% i=batch.addNew(); %>
<% 
		if(i==1)
		{
		application.removeAttribute("course");
		application.removeAttribute("start_am_pm");
		
%>
Database Successfully Updated!!
<br/><br/>
<a href="BatchNewC.jsp">Add New Batch</a>
<a href="BatchSearchOpt.jsp">View Batches</a>
<%
		}
		else if(i==0)
		{
%>
<br/>Error Occured....Batch Was Not Created!!
<br/><br/>
<a href="BatchNewC.jsp">Add New Batch</a>
<a href="BatchSearchOpt.jsp">View Batches</a></h2>
<%
		}
		else if(i==-1)
		{
%>
<jsp:forward page="BatchNew.jsp">
				<jsp:param name="dataprob" value="Date selected has already passed!!Select other date." />
				<jsp:param name="course" value="<%= (String)(application.getAttribute(\"course\")) %>" /> 
				<jsp:param name="time" value="<%= (String)(application.getAttribute(\"time\")) %>" />  
</jsp:forward>
<%
		}
}
%>
</div></center>
<%@ include file="footer.html" %>