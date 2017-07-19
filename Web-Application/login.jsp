
<link rel="stylesheet" href="styles.css">
<html>
<head>
<style>		<!-- rel="stylesheet" type="text/css" media="screen"-->

label {
display: block;
width: 10em;
float: left;
}
fieldset {
width: 20em;
height: 20em;
}
fieldset {
font: 1em Verdana, Geneva, sans-serif;
text-transform: none;
color: red;
background: white;
border: thin solid #333;
}
#legend {
font-size: 1.4em;
text-transform: uppercase;
}
#abc img{
	width:100%;
}
 </style>
</head>
<body>
<div class="overlay" style="color:white;height:80px">
<br>
<div class="left">
<font size="6" face="Cursive"><strong>THE INSTITUTE</strong></font>
</div>
</div>
	<br>
	<br>
	<br>
	<table>
		<tr>
		<td><div id="abc"><img src="images2.jpg" height="340"/></div></td>
		<td>
		<table width=40% align=right >
			<tr>
			<td>
			<fieldset>
			<table align=center cellpadding=7>
				<tr><td><h2><strong><center>Sign In</center></strong></h2></td>
				</tr>
				<form method=get action="check">
				<tr>
				<td>User Name : </td>
				<td><input type=text name=user></td>
				</tr>
				<tr><br></tr>
				<tr>
				<td> Password :<br> </td>
				<td><input type=password name=pass></td>
				</tr>
				<tr><br></tr>
				<tr>
				<td align=right><br><input type=submit name=Submit value=Submit></td>
				<td align=center><br><input type=reset name=Cancel value=Cancel></td>
				</tr>
				</form>
				<tr>
				<% if((request.getAttribute("UserNull")!=null) && !(request.getAttribute("UserNull").equals("")))
					{
						out.print(request.getAttribute("UserNull"));
					}
					else if((request.getAttribute("PassNull")!=null) && !(request.getAttribute("PassNull").equals("")))
					{
						out.print(request.getAttribute("PassNull"));
					}
					else if((request.getAttribute("PassIncorrect")!=null) && !(request.getAttribute("PassIncorrect").equals("")))
					{
						out.print(request.getAttribute("PassIncorrect"));
					}
					else if((request.getAttribute("UserIncorrect")!=null) && !(request.getAttribute("UserIncorrect").equals("")))
					{
						out.print(request.getAttribute("UserIncorrect"));
					}
				%>
				</tr>
			</table>
			</fieldset>
			</td>
			</tr>
		</table>	
		</td>
		</tr>
	</table>
	<br><br><br><br>
	<%@ include file="footer.html" %>
</body>

</html>