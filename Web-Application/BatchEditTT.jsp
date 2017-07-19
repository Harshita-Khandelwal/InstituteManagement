<%@ page language="java" import="java.util.*"%>

<link rel="stylesheet" href="styles.css">
<jsp:include page="adminheader.jsp" />
<br/><br/>
<center>
<div align="center" style="color:black;width:800px;height:300px;background:white ">
<table>
<tr>
<h2>Update Batch Timings:</h1>
</tr><br/>
<h1>
<form method=get action="BatchEdit.jsp">
<tr>
<td>Batch Starting Time:</td></tr>
<tr>
<td><select name="batch_start_time">
<%! String start_am_pm; 
Vector t;%>
<%
start_am_pm=request.getParameter("time");
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
<tr><td>
<input type=submit name=submit value="Update">
</td></tr>
<input type=hidden name="course_name" value="<%= (String)(application.getAttribute("course_name")) %>"  />
<input type=hidden name="course_id" value="<%= (Integer)(application.getAttribute("course_id")) %>" />  
<input type=hidden name="batch_id" value="<%= (Integer)(application.getAttribute("batch_id")) %>" />  
<input type=hidden name="uptime" value="true" />  
<input type=hidden name="start_am_pm" value="<%=start_am_pm%>" />  
</form></h2>
</table>
</div>
</center><br/>
<%@ include file="footer.html" %>
