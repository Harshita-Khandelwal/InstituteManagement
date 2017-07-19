<%@ page language="java" import="Database.Db,java.util.*"%>
<link rel="stylesheet" href="styles.css">
<jsp:include page="adminheader.jsp"/>
<br/><br/>
<center>
<div class="view" align="center" style="color:black;width:800px;height:auto;background:white ">
<center>
<h1>View/Edit Course</h1></center>
<table border=1 cellpadding="10%">
<tr>
	<th>Id</th>
	<th>Name</th>
	<th>Fees</th>
	<th>Duration</th>
</tr>
<%! String s,h,y;
	int cid,sid,i;
	Vector v;
 %> 
<% s=request.getParameter("hidden");
		if(s.equals("id"))
			{
				y=request.getParameter("id");
				if(y.substring(0,1).equals("C"))
				{
					h=y.substring(1);
				}
				else
				{
		%>
		
			<jsp:forward page="CourseSearch.jsp">
				<jsp:param name="IdProb" value="Course Id must Start With C" />
				<jsp:param name="group1" value="<%= (String)(application.getAttribute(\"group1\")) %>" />  
			</jsp:forward>
<%
				}
				Db.rs=Db.stmt.executeQuery("select course_id from course where course_id=\""+Integer.parseInt(h)+"\"");
			
			}
			else if(s.equals("name"))
			{
				y=request.getParameter("name");
				Db.rs=Db.stmt.executeQuery("select course_id from course where course_name=\""+y+"\"");
			}
			else if(s.equals("all"))
			{
				Db.rs=Db.stmt.executeQuery("select course_id from course");
			}
%>
<%
			v=new Vector();
			while(Db.rs.next()==true)
			{
				v.add(Db.rs.getInt("course_id"));
			}
%>
<%
			i=0;
			while(i<v.size())
			{
				sid=(Integer)v.get(i);
				Db.rs=Db.stmt.executeQuery("select * from course where course_id=\""+sid+"\"");
				Db.rs.next();
%>
	<tr>
	<td>C<%=Db.rs.getString("course_id")%></td>
	<td><%=Db.rs.getString("course_name")%></td>
	<td><%=Db.rs.getString("course_fees")%></td>
	<td><%=Db.rs.getString("approx_duration")%></td>
	<td><form method=get action="CourseEdit.jsp"><input type=hidden name=hidden value=<%=sid%>><input type=submit name=submit value=Update></form></td>
</tr>
<%
i++;
	}
%>
</table>
</div>
</center><br/>
<%@ include file="footer.html" %>