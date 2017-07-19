<%@ page language="java" import="Database.Db,java.util.*"%>
<link rel="stylesheet" href="styles.css">
<jsp:include page="adminheader.jsp"/>
<br/><br/>
<center>
<div class="view" align="center" style="color:black;width:800px;height:auto;background:white ">
<center>
<h1>View/Edit Student Profile</h1></center>
<table border=1 cellpadding="10%">
<tr>
	<th>Id</th>
	<th>Name</th>
	<th>Mob.No.</th>
	<th>Father's Name</th>
	<th>Father's Mob.No.</th>
	<th>Address</th>
	<th>College</th>
	<th>Course</th>
	<th>Batch</th>
</tr>
<%! String s,h,y;
	int cid,sid,i,bid;
	Vector v;
 %> 
<% s=request.getParameter("hidden");
		if(s.equals("id"))
			{
				y=request.getParameter("id");
				if(y.substring(0,1).equals("S"))
				{
					h=y.substring(1);
				}
				else
				{
		%>
		
			<jsp:forward page="StSearch.jsp">
				<jsp:param name="IdProb" value="Student Id must Start With S" />
				<jsp:param name="group1" value="<%= (String)(application.getAttribute(\"group1\")) %>" />  
			</jsp:forward>
<%
				}
				Db.rs=Db.stmt.executeQuery("select stu_id from student where stu_id=\""+Integer.parseInt(h)+"\"");
			
			}
			else if(s.equals("name"))
			{
				y=request.getParameter("name");
				Db.rs=Db.stmt.executeQuery("select stu_id from student where stu_name=\""+y+"\"");
			}
			else if(s.equals("course"))
			{
				y=request.getParameter("course");
				Db.rs=Db.stmt.executeQuery("select course_id from course where course_name=\""+y+"\"");
				Db.rs.next();
				cid=Db.rs.getInt("course_id");
				Db.rs=Db.stmt.executeQuery("select stu_id from student where course_id=\""+cid+"\"");
			}
			else if(s.equals("all"))
			{
				Db.rs=Db.stmt.executeQuery("select stu_id from student");
			}
			else if(s.equals("batch"))
			{
				bid=Integer.parseInt(request.getParameter("bid"));
				cid=Integer.parseInt(request.getParameter("cid"));
				Db.rs=Db.stmt.executeQuery("select stu_id from student where batch_id=\""+bid+"\" and course_id=\""+cid+"\"");
		
			%>
<%
			}
%>
<%
			v=new Vector();
			while(Db.rs.next()==true)
			{
				v.add(Db.rs.getInt("stu_id"));
			}
%>
<%
			i=0;
			while(i<v.size())
			{
				sid=(Integer)v.get(i);
				Db.rs=Db.stmt.executeQuery("select * from student where stu_id=\""+sid+"\"");
				Db.rs.next();
%>
	<tr>
	<td>S<%=sid%></td>
	<td><%=Db.rs.getString("stu_name")%></td>
	<td><%=Db.rs.getString("stu_mobile_no")%></td>
	<td><%=Db.rs.getString("stu_father_name")%></td>
	<td><%=Db.rs.getString("stu_father_mobile_no")%></td>
	<td><%=Db.rs.getString("stu_address")%></td>
	<td><%=Db.rs.getString("stu_clg")%></td>
	<td><%	bid=Db.rs.getInt("batch_id");
			cid=Db.rs.getInt("course_id");
			Db.rs=Db.stmt.executeQuery("select course_name from course where course_id=\""+cid+"\"");
			Db.rs.next();%>
			<%=Db.rs.getString("course_name")%>
	</td>
	<td><%
			Db.rs=Db.stmt.executeQuery("select batch_name from batch where batch_id=\""+bid+"\" and course_id=\""+cid+"\"");
			Db.rs.next();%>
		<%=Db.rs.getString("batch_name")%>
	</td>
	<td><form method=get action="StEdit.jsp"><input type=hidden name=hidden value=<%=sid%>><input type=hidden name=hiddenC value=<%=cid%>><input type=submit name=submit value=Update></form></td>
</tr>
<%
	i++;
	}
%>
</table>
</div>
</center><br/>
<%@ include file="footer.html" %>