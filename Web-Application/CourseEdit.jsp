<%@ page language="java" import="Database.Db,java.util.*"%>
<jsp:useBean id="course" class="Database.Course" scope="request" />
<link rel="stylesheet" href="styles.css">
<jsp:include page="adminheader.jsp"/>
<%!int slabel,i; 
String a,app,dur;%>
<% slabel=Integer.parseInt(request.getParameter("hidden"));%>
<br/><br/>
<center>
<div align="center" style="color:black;width:800px;height:300px;background:white ">
<table>
<tr>
<h1>Update Course</h1>
</tr>
<%Db.rs=Db.stmt.executeQuery("select * from course where course_id="+slabel);
Db.rs.next();%>
<%
a=Db.rs.getString("approx_duration");
i=a.indexOf("D");
if(i==-1)
{
	i=a.indexOf("M");
}
if(i==-1)
{
	i=a.indexOf("Y");
}
app=a.substring(0,i);
dur=a.substring(i);
%>
<h2><form method=post action="CreateCourse.jsp">
<tr><td>Course ID:C<%= slabel %></td></tr>
<tr><td>Course Name:</td><td><input type=text class="roundborder" name=course_name required maxlength="10" value="<%=Db.rs.getString("course_name")%>"></td></tr>
<tr><td>Course Fees:</td><td><input type=number class="roundborder" name=course_fees required min="0" max="65535" value="<%=Db.rs.getString("course_fees")%>"></td></tr>
<tr><td>Approx Duration:</td><td><input type=number class="roundborder" step="0.5" name=approx required value="<%=app%>">
<select name="duration" class="roundborder">
<%if(dur.equals("Days"))
	{
	%>
  <option value="Days" selected="selected">Days</option>
  <option value="Months">Months</option>
  <option value="Year">Year</option>
<%
}
else if(dur.equals("Months"))
{
%>
  <option value="Days" >Days</option>
  <option value="Months" selected="selected">Months</option>
  <option value="Year">Year</option>
<%
}
else if(dur.equals("Year"))
{
%>
  <option value="Days" >Days</option>
  <option value="Months">Months</option>
  <option value="Year"  selected="selected">Year</option>
<%
}
%>
</select>
</td></tr>
<tr><td><input type=submit name=Submit value=Update class="roundborder"></td>
<td><input type=reset name=Cancel value=Cancel class="roundborder"></td>
</tr>

		<input type="hidden" name="course_id" value= <%=slabel %> />
<input type="hidden" name="updateC" value="true"/>
	</form></h2>
</table>
</div>
</center><br/>
<%@ include file="footer.html" %>