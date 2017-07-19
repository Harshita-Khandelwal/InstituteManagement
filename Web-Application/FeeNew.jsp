<%@ page language="java" import="java.util.*,Database.Db,java.sql.*"%>

<jsp:useBean id="fee" class="Database.Fee" scope="request" />
<link rel="stylesheet" href="styles.css">
<jsp:include page="adminheader.jsp"/>
<br/><br/> 
<center>
<div align="center" style="color:black;width:800px;height:300px;background:white ">
<%! String y,h;
	int cid;%>
<%
				y=request.getParameter("id");
				if(y.substring(0,1).equals("S"))
				{
					h=y.substring(1);
					application.setAttribute("id",h);
				}
				else
				{
		%>
		
			<jsp:forward page="FeeNewS.jsp">
				<jsp:param name="IdProb" value="Student Id must Start With S" />
			</jsp:forward>
<%
				}
				Db.rs=Db.stmt.executeQuery("select * from student where stu_id="+h);
				Db.rs.next();
%>
<table>
<tr>
<h1>Deposit Fee</h1>
</tr><br/>
<h1><form method=get action="CreateFee.jsp">
<%! int slabel,due;
%>
<%
			Db.rs=Db.stmt.executeQuery("select max(receipt_no) as highest from receipt");
			Db.rs.next();
			slabel=(Db.rs.getInt("highest"));
			slabel++;
			Db.rs=Db.stmt.executeQuery("select stu_name,stu_father_name,course_id from student where stu_id="+h);
			Db.rs.next();
%>		

<tr><td>Receipt No:R<%= slabel %></td></tr>
<tr><td>Student Id:S<%= h %></td></tr>
<tr><td>Student Name:</td><td><%=Db.rs.getString("stu_name")%></td><td>Father's Name:</td><td><%=Db.rs.getString("stu_father_name")%></td></tr>
<%				cid=Db.rs.getInt("course_id");
			Db.rs=Db.stmt.executeQuery("select course_name,course_fees from course where course_id=\""+cid+"\"");
			Db.rs.next();
%>
<tr><td>Course Name:</td><td><%=Db.rs.getString("course_name")%></td><td>Course Fees:</td><td><%=Db.rs.getString("course_fees")%></td></tr>
<%due=fee.calc_due(h);%>
<tr><td>Amount Due:</td><td><%= due %></td></tr>
<tr><td>Deposit Amount:</td><td><input type=text name=fee_deposit required></td>
<td>
<%! String g;%>
<%
	g=request.getParameter("prob");
	if(g!=null)
	{
%>
<font color=red><%= g %></font>
<%
	}
%>
</td>
</tr>
<tr><td><input type=submit name=Submit value=Submit>
<input type=hidden name=receipt_no value="<%=slabel%>">
<input type=hidden name=stu_id value="<%=h%>">
<input type=hidden name=due value="<%=due%>">
</td>
</tr></form></h1>
</table>
</div>
</center><br/>
<%@ include file="footer.html" %>