<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  <!-- 
  <body style="background-image:url(./image/home.jpg); background-repeat:repeat-y; background-position: center top">
   -->
   <body>
   <div id="bg" align="center">
		<div id="sadrzaj">
			<div id="zaglavlje">
				<div id="title">
					<center>
						Semantic Personalized Search Engine
					</center>
				</div>

			</div>
			<div id="downbox">
				<table align="center" height="100" border="0" cellspacing="3"
					cellpadding="0" >
					<tr>
						<td colspan="2" align="center">
							<div class="s1">
								<input type="button" value="Log In" onclick="location.href='personalizedhome.jsp'"> </input> 
							</div>
						</td>
					</tr>
					<tr>
						<td>
							<div class="s2">
									<input type="button" value="Trail" onclick="location.href='anonymoushome.jsp'"> </input>
							</div>
						</td>
					</tr>
				</table>
				
             
				 <hr color=" #3A6794">
				<div id="pic">
					<h2>
						About the System
					</h2>
					<p>
						Semantic Personalized Search...
					</p>
					<p>
						
					</p>
				</div>

			</div>

			<div id="footer">
				Copyright &copy; 2012
				<a href="http://bike.snu.ac.kr/">Biomedical Knowledge Engineering Laboratory, Seoul National University</a> > Designed by
				<a href="mailto:zongnansu1982@gmail.com">Nansu Zong</a> >

			</div>
		</div>
	</div>

    
    
    
    
  </body>
</html>
