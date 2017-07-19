<%@ page language="java" import="Database.Db,java.sql.*,java.util.*"%>
<jsp:useBean id="batch" class="Database.Batch" scope="request" />

<link rel="stylesheet" href="styles.css">
<jsp:include page="adminheader.jsp"  />

<%! String c,start_am_pm,dataprob;
	int course_id,batch_id;
	Vector t,fna;
%>
<% c=request.getParameter("course");
	start_am_pm=request.getParameter("time");
	application.setAttribute("course",c);
	application.setAttribute("time",start_am_pm);
	course_id=batch.calc_course_id(c);
	batch_id=batch.calc_batch_id(course_id);
 %>
<br/><br/>
<center>
<div align="center" style="color:black;width:800px;height:300px;background:white ">
<table>
<tr>
<h1>Create New Batch</h1>
</tr>
<%dataprob=request.getParameter("dataprob");
	if(dataprob!=null)
	{
%>
<tr><td><font color=red><%= dataprob%></font></td></tr>
<%
	}
%>
<h2><form method=post action="CreateBatch.jsp">
<tr><td>Course Name:</td><td><%= c %></td></tr>
<tr><td>Batch ID:</td><td>C<%= course_id %>B<%= batch_id %></td></tr>
<tr><td>Batch Name:</td><td><input type=text name=batch_name maxlength="20" required></td></tr>
<tr>
<td>Batch Starting Time:</td>
<td><select name="batch_start_time">
<%
t=new Vector();
if(start_am_pm.equals("am"))
{
	for(int i=0;i<t.size();i++)
	{
		t.remove(i);
	}

	for(int k=5;k<12;k++)
	{	
		t.add(k+":30:00");
	}
	for(Object x:t)
	{
%>
<option>
<%= x %>
<%		
	}
}
else if(start_am_pm.equals("pm"))
{
	for(int i=0;i<t.size();i++)
	{
		t.remove(i);
	}
	t.add("12:30:00");
	for(int k=1;k<9;k++)
	{
		t.add(k+":30:00");
	}
	for(Object x:t)
	{
%>
<option>
<%= x %>
<%		
	}
}%>
</option>
</select>
<%= start_am_pm %>
</td>
</tr>
<tr>
<td>Batch Duration:</td>
<td><select name=hr><option value=1>1</option><option value=2>2</option><option value=3>3</option><option value=4>4</option><option value=5>5</option></select>hours</td>
<td><select name=min><option value=0>0</option><option value=30>30</option></select>minutes</td>
</tr>
<tr>
<td>Batch Starting Date:</td>
<td><select name=yr>

	<%! java.sql.Date dt;
		String u;
		int a,b,r,k;
	%>
	<%	dt=new java.sql.Date(System.currentTimeMillis());
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
		if(k+1==r+1)
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
</td>
</tr>
<tr><td><input type=submit name=Submit value=Submit></td>
<td><input type=reset name=Cancel value=Cancel></td>
</tr>

		<input type="hidden" name="course_id" value= <%= course_id %> />
		<input type="hidden" name="batch_id" value= <%= batch_id %> />
		<input type="hidden" name="start_am_pm" value= <%= start_am_pm %> />
		
</form></h2>
</table>
</div>
</center><br/>
<%@ include file="footer.html" %>