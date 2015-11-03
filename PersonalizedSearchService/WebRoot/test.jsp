<%@ page language="java"  import="java.awt.*,java.awt.image.*,com.sun.image.codec.jpeg.*,java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean"%> 
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>
 
<html> 
	<head>
		<title>JSP for TestForm form</title>
	</head>
	<body>
		<html:form action="/test">
			input : <html:text property="input"/><html:errors property="input"/><br/>
			<html:submit/><html:cancel/>
		</html:form>
		
		
		

	<div id="container">

<div id="left-container">



        <div class="text">
        <h4>
Force Directed Static Graph    
        </h4> 

            A static JSON Graph structure is used as input for this visualization.<br /><br />
            You can <b>zoom</b> and <b>pan</b> the visualization by <b>scrolling</b> and <b>dragging</b>.<br /><br />
            You can <b>change node positions</b> by <b>dragging the nodes around</b>.<br /><br />
            The clicked node's connections are displayed in a relations list in the right column.<br /><br />
            The JSON static data is customized to provide different node types, colors and widths.
            
        </div>

        <div id="id-list"></div>


<div style="text-align:center;"><a href="example1.code.html">See the Example Code</a></div>
</div>

<div id="center-container">
    <div id="infovis"></div>    
</div>

<div id="right-container">

<div id="inner-details"></div>

</div>

<div id="log"></div>
</div>


	</body>
</html>

