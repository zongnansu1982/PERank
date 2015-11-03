<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<jsp:directive.page import="java.awt.image.*"/>
<jsp:directive.page import="com.sun.image.codec.jpeg.*"/>
<jsp:directive.page import="bean.*"/>
<jsp:directive.page import="tool.*"/>
<%
String prefernceJson=(String)request.getSession().getAttribute("prefernceJson");
 %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>ForceDirected - Force Directed Static Graph</title>

<!-- CSS Files -->
<link type="text/css" href="./css/base.css" rel="stylesheet" />
<link type="text/css" href="./css/ForceDirected.css" rel="stylesheet" />
<link type="text/css" href="../css/base.css" rel="stylesheet" />
<link type="text/css" href="../css/RGraph.css" rel="stylesheet" />

<!--[if IE]><script language="javascript" type="text/javascript" src="../../Extras/excanvas.js"></script><![endif]-->

<!-- JIT Library File -->
<script language="javascript" type="text/javascript" src="jit-yc.js"></script>

<!-- Example File -->
<script language="javascript" type="text/javascript" src="showPreference.js"></script>

<script type="text/javascript">
var json =<%= prefernceJson %> 
</script>
</head>

<body onload="init(json)">
<div id="center-container">
 <div id="infovis"></div>    
</div>



<script type="text/javascript">

//alert(json);
init(json);
</script>
</body>
</html>
