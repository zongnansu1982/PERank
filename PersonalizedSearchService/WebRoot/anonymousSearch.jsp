<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<jsp:directive.page import="java.awt.image.*"/>
<jsp:directive.page import="com.sun.image.codec.jpeg.*"/>
<jsp:directive.page import="bean.*"/>
<jsp:directive.page import="tool.*"/>


<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean"%> 
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic"
	prefix="logic"%>
	
	

<%


HashMap<String,SearchBean> outwithPreference=(HashMap<String,SearchBean>)request.getAttribute("outwithPreference");
String topicColor="#557EAA";
String linkColor="#83548B";
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String query = (String)request.getAttribute("oldquery");
String[] topics=(String[])request.getAttribute("oldtopcis");
String[] links=(String[]) request.getAttribute("oldlinks");
List<String> queryList = (List<String>)request.getSession().getAttribute("queryList");
Integer sessionId=(Integer)request.getSession().getAttribute("sessionId");
List<SessionPreferenceBean> sessionPreference=(List<SessionPreferenceBean> )request.getSession().getAttribute("sessionPreference");
if(queryList==null)
{
	queryList = new LinkedList<String>();
	request.getSession().setAttribute("queryList",queryList);
	out.println("create new seesionPreference");
	sessionId=0;
	request.getSession().setAttribute("sessionId",sessionId);
	sessionPreference= new LinkedList<SessionPreferenceBean>();
	request.getSession().setAttribute("sessionPreference",sessionPreference);
}
if(sessionPreference==null){
	
}
sessionId++;
queryList.add(query);
SessionPreferenceBean bean = new SessionPreferenceBean();
bean.setLinks(links);
bean.setTopics(topics);
bean.setId(sessionId);
sessionPreference.add(bean);

HashMap<String,Integer> counter= new HashMap<String,Integer>();
for(String q:queryList){
if(!counter.containsKey(q)){
counter.put(q,1);
}else{
int times=counter.get(q)+1;
counter.remove(q);
counter.put(q,times);
}
}
JsonGenerator generator= new JsonGenerator();
HashMap<String,PreferenceBean> preferenceMap=generator.preferenceTrackGenerate(sessionPreference);
String prefernceJson="[";
for(Map.Entry<String,PreferenceBean> entry:preferenceMap.entrySet()){
	prefernceJson+="{\"adjacencies\":[";
	 for(String link:entry.getValue().getInlink()){
	 if(preferenceMap.get(link).getType()=="topic"){
	 prefernceJson+="{\"nodeTo\":\""+link+"\",\"nodeFrom\":\""+entry.getKey()+"\",\"data\":{\"$color\":\""+topicColor+"\"}},";
	 }else{
	 prefernceJson+="{\"nodeTo\":\""+link+"\",\"nodeFrom\":\""+entry.getKey()+"\",\"data\":{\"$color\":\""+linkColor+"\"}},";
	 }
	 }
	 if(preferenceMap.get(entry.getKey()).getType()=="topic"){
	  prefernceJson+="],\"data\":{\"$color\":\""+topicColor+"\",\"$type\":\"circle\",\"$dim\":"+
	Math.pow(Double.valueOf(preferenceMap.get(entry.getKey()).getInlink().length),2)+"},\"id\":\""+entry.getKey()+"\",\"name\":\""+entry.getKey()+"\"}," ;
	 }else{
	 prefernceJson+="],\"data\":{\"$color\":\""+linkColor+"\",\"$type\":\"star\",\"$dim\":"+
	Math.pow(Double.valueOf(preferenceMap.get(entry.getKey()).getInlink().length),2)+"},\"id\":\""+entry.getKey()+"\",\"name\":\""+entry.getKey()+"\"}," ;
	 }
}

prefernceJson+="]";


HashMap<String,ArrayList<String>> queryPreferenceMap= generator.queryPreferenceTrackGenerate(outwithPreference);
String queryPreferecenJson="{ id:\""+query+"\", name:\""+query+"\", children: [";
for(Map.Entry<String,ArrayList<String>> entry:queryPreferenceMap.entrySet()){
	
	queryPreferecenJson+="{ id:\""+entry.getKey()+"\", name:\""+entry.getKey()+"\", children: [";
	 for(String link:entry.getValue()){
	 queryPreferecenJson+="{ id:\""+link+"\", name:\""+link+"\" },";
	 }
	 queryPreferecenJson+="]},";
}
queryPreferecenJson+="]";
queryPreferecenJson+="}";

request.getSession().setAttribute("queryPreferecenJson",queryPreferecenJson);
request.getSession().setAttribute("prefernceJson",prefernceJson);
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'ASearchResults.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<!-- CSS Files -->
<link type="text/css" href="./css/base.css" rel="stylesheet" />
<link type="text/css" href="./css/ForceDirected.css" rel="stylesheet" />

<!--[if IE]><script language="javascript" type="text/javascript" src="../../Extras/excanvas.js"></script><![endif]-->

<!-- JIT Library File -->
<script language="javascript" type="text/javascript" src="jit-yc.js"></script>

<!-- Example File -->
<script language="javascript" type="text/javascript" src="showPreference.js"></script>
<script type="text/javascript" >
var json =<%=prefernceJson%>
</script>


</head>
<!-- 
<body style="background-image:url(./image/results.jpg); background-repeat:repeat-y; background-position: center top" >
 -->
 <body>
 
 
<br><br>
 <html:form action="/anonymousContinueSearch">
 <table>
 
 
 <tr>
 <td> <html:text size = "80"  style="height:40px;font-size:22px" property="query"/><html:errors property="query"/></td><td><html:submit style="height:40px;font-size:22px"  value="Search"/></td>
 </tr>
 </table>
 
 <br>
 <table>
 <tr>
<td>Topics : </td><td><html:text property="topic"/><html:errors property="topic"/><br/></td>
<td>Links :  </td><td><html:text  property="link"/><html:errors property="link"/><br/></td>
</tr>
 </table>
 
 
 	</html:form>	
 	

 	<hr/>
 	<table width="100%">
 	<tr>
 	<td width="80%">We find <b><%=request.getAttribute("totalHits")%></b> results for you. This is page <b><%=request.getAttribute("currentPage") %></b></td>
 	<td>
 	
 <table border="1" style="border-collapse:collapse">
			<tr>
				<td><b>preference</b></td>
				<td><b>number of times</b></td>
			</tr>
		<% for(Map.Entry<String,Integer> entry:counter.entrySet()){%>
			<tr>
				<td><%out.println(entry.getKey());%></td>
				<td><%out.println(entry.getValue());}%></td>
			</tr>
		</table>
 	</td>
 	</tr>
 	
 	</table>


		
  	<hr/>
	 
	 
	 <table width="100%" border="0">
	 <tr>
	 
	 <td rowspan="2"  valign="top">

  			<logic:present name="feedback">

					<logic:iterate id="feedback" name="feedback" >
					<table border="0">
					<tr>
					<td colspan="2"><h3><bean:write name="feedback" property="key"/></h3></td>
					</tr>
						<tr>
							
							
							<td width="10">
							
							</td>
							<td><bean:write name="feedback" property="value" filter="false" />
							</td>
						</tr>
					</table>
					<br>
						<br>
					</logic:iterate>
			</logic:present>

	 </td>
	 
	 <td width="700" valign="top"><iframe src="searchMap.jsp" allowTransparency="true" width="100%" height="700" frameborder="0"></iframe></td>
	 
	 
	 </tr>
	 	 <tr>
	 
	 
	 
	 <td width="700"  valign="top"><iframe src="preferenceMap.jsp" allowTransparency="true" width="100%" height="700"  frameborder="0"></iframe></td>
	 
	 
	 </tr>
	 
	 </table>
	 <br>
		
			

		
		
<%
String pages="";
for(int eachpage:(Integer[])request.getAttribute("pages")){
pages+=eachpage+" ";
}
pages=pages.trim();

System.out.println("currentPage="+request.getAttribute("currentPage"));
Integer c = (Integer)request.getAttribute("currentPage");
Integer m = (Integer)request.getAttribute("maxPage");
 %>
 <hr/>
<div id="footpage" align="center">
	<jsp:include flush="true" page="searchFooter.jsp"> 
	<jsp:param   name= "currentPage"   value= "<%=(c) %>" />
	<jsp:param   name= "maxPage"   value= "<%=m %>"   /> 
	<jsp:param   name= "pages"   value= "<%=(pages) %>"   /> 
	</jsp:include>
</div>

  </body>
</html>
