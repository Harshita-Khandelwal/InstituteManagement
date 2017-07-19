<%@ page language="java" import="java.util.*"%>

<link rel="stylesheet" href="styles.css">
<jsp:include page="adminheader.jsp" />
<br/><br/>
<center>
<div align="center" style="color:black;width:800px;height:300px;background:white ">
<table>
<tr>
<h1>Deposit Fee</h1>
</tr><br/>
<form method=get action="FeeNew.jsp">
<%! String g;%>
<%
	g=request.getParameter("IdProb");
	if(g!=null)
	{
%>
<font color=red><%= g %></font>
<%
	}
%>
<h1>
<tr><td>Enter Id:</td>
<td><input type="text" name="id" />
 </td></tr>
<tr><td><input type=submit name=Submit value=Submit></td>
</tr></form></h1>
</table>
</div>
</center><br/>
<%@ include file="footer.html" %>