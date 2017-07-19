<%@ page language="java" import="Database.Db,java.util.*"%>
<link rel="stylesheet" href="styles.css">
<jsp:include page="adminheader.jsp"/>
<br/><br/>
<center>
<div class="view" align="center" style="color:black;width:800px;height:auto;background:white ">
<center>
<h1>View/Edit Queries</h1></center>
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
</tr>
<%! String s,h,y;
	int cid,sid,i;
	Vector v;
 %> 
<% s=request.getParameter("hidden");
		if(s.equals("id"))
			{
				y=request.getParameter("id");
				if(y.substring(0,1).equals("Q"))
				{
					h=y.substring(1);
				}
				else
				{
		%>
		
			<jsp:forward page="QuSearch.jsp">
				<jsp:param name="IdProb" value="Query Id must Start With Q" />
				<jsp:param name="group1" value="<%= (String)(application.getAttribute(\"group1\")) %>" />  
			</jsp:forward>
<%
				}
				Db.rs=Db.stmt.executeQuery("select query_id from query where query_id=\""+Integer.parseInt(h)+"\"");
			
			}
			else if(s.equals("name"))
			{
				y=request.getParameter("name");
				Db.rs=Db.stmt.executeQuery("select query_id from query where query_name=\""+y+"\"");
			}
			else if(s.equals("course"))
			{
				y=request.getParameter("course");
				Db.rs=Db.stmt.executeQuery("select course_id from course where course_name=\""+y+"\"");
				Db.rs.next();
				cid=Db.rs.getInt("course_id");
				Db.rs=Db.stmt.executeQuery("select query_id from query where course_id=\""+cid+"\"");
			}
			else if(s.equals("all"))
			{
				Db.rs=Db.stmt.executeQuery("select query_id from query");
			}
%>
<%
			v=new Vector();
			while(Db.rs.next()==true)
			{
				v.add(Db.rs.getInt("query_id"));
			}
%>
<%
			i=0;
			while(i<v.size())
			{
				sid=(Integer)v.get(i);
				Db.rs=Db.stmt.executeQuery("select * from query where query_id=\""+sid+"\"");
				Db.rs.next();
%>
	<tr>
	<td>Q<%=sid%></td>
	<td><%=Db.rs.getString("query_name")%></td>
	<td><%=Db.rs.getString("query_mobile_no")%></td>
	<td><%=Db.rs.getString("query_father_name")%></td>
	<td><%=Db.rs.getString("query_father_mobile_no")%></td>
	<td><%=Db.rs.getString("query_address")%></td>
	<td><%=Db.rs.getString("query_clg")%></td>
	<td><%	cid=Db.rs.getInt("course_id");
			Db.rs=Db.stmt.executeQuery("select course_name from course where course_id=\""+cid+"\"");
			Db.rs.next();%>
			<%=Db.rs.getString("course_name")%>
	</td>
	<td><form method=get action="QueryEdit.jsp"><input type=hidden name=hidden value=<%=sid%>><input type=submit name=submit value=Update></form></td>
</tr>
<%
	i++;
	}
%>
</table>
</div>
</center><br/>
<%@ include file="footer.html" %>