<%@ page language="java" import="Database.Db"%>
<jsp:useBean id="stu" class="Database.Student" scope="request" />
<link rel="stylesheet" href="styles.css">
<jsp:include page="adminheader.jsp"/>
<%! String c; 
	int slabel; 
	int cid;
%>
<% slabel=Integer.parseInt(request.getParameter("hidden"));%>
<%cid=Integer.parseInt(request.getParameter("hiddenC"));%>
<%
 Database.Db.rs=Database.Db.stmt.executeQuery("select course_name from course where course_id="+cid);
	Database.Db.rs.next();
	c=Db.rs.getString("course_name");%>
<br/><br/>
<center>
<div align="center" style="color:black;width:800px;height:auto;background:white ">
<table>
<tr>
<h1>Create New Profile</h1>
</tr>
<%Db.rs=Db.stmt.executeQuery("select * from student where stu_id="+slabel);
Db.rs.next();%>
<h2><form method=post action="CreateStu.jsp">
<tr><td>Student ID:S<%= slabel %></td></tr>
<tr><td>Name:</td><td><input type=text name=stname required maxlength="30" value="<%= Db.rs.getString("stu_name")%>"></td></tr>
<tr><td>Mobile No:</td><td><input type="number" name=stmobno required min="999999999" max="9999999999" value="<%= Db.rs.getString("stu_mobile_no")%>"></td></tr>
<tr><td>Father's Name:</td><td><input type=text name=stfname required maxlength="30" value="<%= Db.rs.getString("stu_father_name")%>"></td></tr>
<tr><td>Father's Mobile No:</td><td><input type=number name=stfmobno min="999999999" max="9999999999" required value="<%= Db.rs.getString("stu_father_mobile_no")%>"></td></tr>
<tr><td>Address:</td><td><textarea rows="4" cols="30" name=staddress required maxlength="50"><%= Db.rs.getString("stu_address")%></textarea></td></tr>
<tr><td>College:</td><td><input type=text name=stclg required maxlength="20" value="<%= Db.rs.getString("stu_clg")%>"></td></tr>
<tr><td>Course:</td><td><%= c %></td></tr>
<%! int b;
	String ba;
%>
<% b=Db.rs.getInt("batch_id");
	Db.rs=Db.stmt.executeQuery("select batch_name from batch where course_id="+cid+" and batch_id="+b);
	Db.rs.next();
	ba=Db.rs.getString("batch_name");
	%>
<tr><td>Batch:</td><td> <select name=stbatch >
<%
	Db.rs=Db.stmt.executeQuery("select batch_name from batch where course_id=\""+cid+"\"");
	while(Db.rs.next()==true)
	{
		String s=Db.rs.getString("batch_name");
		if(s.equals(ba))
		{
%>
			<option selected="selected">
			<%= s %>
<%
		}
		else
		{
%>
			<option>
			<%= s %>
<%
		}
	}
%>
	</option>

</select>
</td></tr>
<tr><td><input type=submit name=Submit value=Update></td>
<td><input type=reset name=Cancel value=Cancel></td>
</tr>

		<input type="hidden" name="id" value= <%=slabel %> />
		<input type="hidden" name="cid" value= <%=cid %> />
		<input type="hidden" name="updateP" value="true"/>
</form></h2>


</table>
</div>
</center><br/>
<%@ include file="footer.html" %>