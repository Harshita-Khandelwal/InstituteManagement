<%@ page language="java" import="Database.Db,java.util.*"%>
<link rel="stylesheet" href="styles.css">
<jsp:include page="adminheader.jsp"/>
<br/><br/>
<center>
<div class="view" align="center" style="color:black;width:800px;height:auto;background:white ">
<center>
<h1>View/Edit User Accounts</h1></center>
<table border=1 cellpadding="10%">
<tr>
	<th>Id</th>
	<th>Name</th>
	<th>UserName</th>
	<th>Mobile No</th>
	<th>Designation</th>
</tr>
<%! String s;
	int id,i;
	Vector v;
%>
<% s=request.getParameter("group1");
	if(s.equals("all"))
	{
		Db.rs=Db.stmt.executeQuery("select login_id from login");
	}
	else
	{
		Db.rs=Db.stmt.executeQuery("select login_id from login where designation=\""+s+"\"");
	}
%>
<%	
	v=new Vector();
			while(Db.rs.next()==true)
			{
				v.add(Db.rs.getInt("login_id"));
			}%>
<%
	i=0;
		while(i<v.size())
			{
				id=(Integer)v.get(i);
				Db.rs=Db.stmt.executeQuery("select * from login where login_id=\""+id+"\"");
				while(Db.rs.next()==true)
				{
%>
<tr>
<td><%=Db.rs.getInt("login_id")%></td>
<td><%=Db.rs.getString("name")%></td>
<td><%=Db.rs.getString("username")%></td>
<td><%=Db.rs.getString("MobileNo")%></td>
<td><%=Db.rs.getString("designation")%></td>
<td><form method=get action="UserEditD.jsp"><input type=hidden name=hidden value=<%=id%>><input type=submit name=submit value=Update></form></td>
</tr>
<%
				}
				i++;
			}
%>
</table>
</div>
</center><br/>
<%@ include file="footer.html" %>