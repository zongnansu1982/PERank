<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<!-- 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

 -->
<jsp:directive.page import="java.awt.image.*"/>
<jsp:directive.page import="com.sun.image.codec.jpeg.*"/>
<jsp:directive.page import="bean.*"/>
<jsp:directive.page import="tool.*"/>


<%

String queryPreferecenJson=(String)request.getSession().getAttribute("queryPreferecenJson");
Enumeration e = request.getAttributeNames();
while(e.hasMoreElements()){
System.out.println(e.nextElement());
}

%>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>RGraph - Tree Animation</title>

<!-- CSS Files -->
<link type="text/css" href="./css/base.css" rel="stylesheet" />
<link type="text/css" href="./css/RGraph.css" rel="stylesheet" />

<!--[if IE]><script language="javascript" type="text/javascript" src="../../Extras/excanvas.js"></script><![endif]-->

<!-- JIT Library File -->
<script language="javascript" type="text/javascript" src="jit-yc.js"></script>

<!-- Example File -->
<script language="javascript" type="text/javascript" src="treeAnimation.js"></script>
<script language="javascript" type="text/javascript">
var json = <%= queryPreferecenJson %>
</script>

</head>

<body onload="init2(json);">

<div id="center-container">
    <div id="infovis"></div>    
</div>



</body>
</html>
