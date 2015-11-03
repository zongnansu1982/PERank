<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean"%> 
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic"
	prefix="logic"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'AnonymousSearch.jsp' starting page</title>
    
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
  <body style="background-image:url(./image/typechoose.jpg); background-repeat:repeat-y; background-position: center top">
   -->
   <body>
  
    <br>
    <br>
      <br>  <br>
    <br>
      <br>  <br>
    <br>
      <br><br>
      <br>
  
  <center>
  
  
  
  <img src="image/logo.jpg">
  <img width="200" src="image/bike-intro-logo-400.jpg">
  <br>
    <br>
      <br><br>
      <br>
   <html:form action="/anonymousSearch">

<table align="center" height="100" border="0" cellspacing="3"
					cellpadding="0" >
					
					<tr>
					<td colspan="2"><html:text size = "100" style="height:40px;font-size:22px"  property="query"/><html:errors property="query"/></td>
					</tr>
					
					<tr>
					<td colspan="2" align="center"><html:submit style="height:40px;font-size:22px" value="Search"/></td>

					</tr>
					<tr>					
					<td width="60">Topics :</td><td><html:text property="topic"/><html:errors property="topic"/></td>
					</tr>
					<tr>					
					<td width="60">Links :</td><td><html:text property="link"/><html:errors property="link"/></td>
					</tr>
					</table>
					
	</html:form>
		
    </center>
    
  </body>
</html>
