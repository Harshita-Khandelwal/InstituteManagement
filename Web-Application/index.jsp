<!Doctype html>
<link rel="stylesheet" href="styles.css">
<html>
<head>
<script type="text/javascript">
<!-->
var image1=new Image()
image1.src="image.jpg"
var image2=new Image()
image2.src="images.jpg"
var image3=new Image()
image3.src="images2.jpg"
//-->
</script>
<style>
a{color:white}
</style> 
</head>
<body >
<div class="overlay" style="color:white;height:80px">
<br/>
<div class="left">
<font size="6" face="Cursive"><strong>THE INSTITUTE</strong></font>
</div>
<div class="right">
<font size="6" face="verdana"><a href="login.jsp"> SignIn </a></font>
</div>
</div>
<p><br/><center>
<img src="image.jpg" name="slide" width="1000" height="420"/></center>
<script type="text/javascript">
<!--
var step=1
function slideit()
{
document.images.slide.src=eval("image"+step+".src");
if(step<3)
step++
else
step=1
setTimeout("slideit()",2500)
}
slideit()
//-->
</script>
</p>
<%@ include file="footer.html" %>
</body>
</html>