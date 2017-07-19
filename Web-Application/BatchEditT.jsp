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
Select:
<form method=get action="BatchEditTT.jsp">
<tr>
<td>
 <input type="radio" name="time" value="am">Morning Batch(A.M.)
 </td></tr>
 <tr><td><center>OR</center></td></tr>
 <tr><td><input type="radio" name="time" value="pm">Evening Batch(P.M.)</td></tr>
<tr><td><input type=submit name=Submit value=Submit></td>
</tr>
</form></h2>
</table>
</div>
</center><br/>
<%@ include file="footer.html" %>
