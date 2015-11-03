<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<jsp:directive.page import="com.sun.org.apache.xalan.internal.xsltc.util.IntegerArray"/>


<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<%
int currentPage=(Integer)request.getAttribute("currentPage");
int maxPage=(Integer)request.getAttribute("maxPage");
Integer[] pages=(Integer[])request.getAttribute("pages");
String query = (String)request.getAttribute("oldquery");

String[] topics=(String[])request.getAttribute("oldtopcis");
String[] links=(String[])request.getAttribute("oldlinks");
Enumeration e=request.getAttributeNames();

String topic="";
for(String t:topics){
topic+=t+" ";
}
topic=topic.trim();
String link="";
for(String l:links){
link+=l+" ";
}
link=link.trim();
System.out.println(link);
 %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'searchFooter.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
  <table id="foot">
     <html:form action="/footPage" method="post">
     
   <% if(currentPage==maxPage){ %>
    <tr><a href="footPage.do?pageNum=<%=(currentPage-1)%>&amp;query=<%=query%>&amp;topic=<%=topic%>&amp;link=<%=link%>">Previous</a></tr><tr>&nbsp;</tr>
 	 <% for(Integer ipage : pages){%>
   	<tr><a href="footPage.do?pageNum=<%=ipage%>&amp;query=<%=query%>&amp;topic=<%=topic%>&amp;link=<%=link%>"><%=ipage%></a></tr><tr>&nbsp;</tr>
   <% } }%>
   <%if(currentPage==1){ %>
    
 	 <% for(Integer ipage : pages){%>
   	<tr><a href="footPage.do?pageNum=<%=ipage%>&amp;query=<%=query%>&amp;topic=<%=topic%>&amp;link=<%=link%>"><%=ipage%></a></tr><tr>&nbsp;</tr>
   <% }%>
   <tr><a href="footPage.do?pageNum=<%=(currentPage+1)%>&amp;query=<%=query%>&amp;topic=<%=topic%>&amp;link=<%=link%>">Next</a></tr><tr>&nbsp;</tr>
    <% }%>
    
   <%if(1<currentPage&&currentPage<maxPage){ %>
    <tr><a href="footPage.do?pageNum=<%=(currentPage-1)%>&amp;query=<%=query%>&amp;topic=<%=topic%>&amp;link=<%=link%>">Previous</a></tr><tr>&nbsp;</tr>
 	 <% for(Integer ipage : pages){%>
   	<tr><a href="footPage.do?pageNum=<%=ipage%>&amp;query=<%=query%>&amp;topic=<%=topic%>&amp;link=<%=link%>"><%=ipage%></a></tr><tr>&nbsp;</tr>
   <% }%>
   <tr><a href="footPage.do?pageNum=<%=(currentPage+1)%>&amp;query=<%=query%>&amp;topic=<%=topic%>&amp;link=<%=link%>">Next</a></tr><tr>&nbsp;</tr>
    <% }%>
	</html:form>
 </table>  
 </body>
</html>
