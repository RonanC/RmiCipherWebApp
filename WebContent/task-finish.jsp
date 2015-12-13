<%@ include file="includes/header.jsp"%>

	<% 
	String keyWord = request.getParameter("keyWord");  
	String keyLen = request.getParameter("keyLen"); 
	String resultText = request.getParameter("resultText"); 
	String taskNum = request.getParameter("taskNum");  
	String inputText = request.getParameter("inputText");  
	String maxKeyLen = request.getParameter("maxKeyLen");  
	String taskType = request.getParameter("taskType");
	%>

<div class="container">
	<div class="page-header">
		<h1>Task <small> #<%=taskNum%></small></h1>
	</div>
	
	<H2>Finished processing <%=taskType%> request</H1>
	<br>
	<p>
	<h3>Data Given:</h3>
	<b>Text:</b> <%=inputText%> <br>
	<% if(Integer.parseInt(maxKeyLen) != -1 && Integer.parseInt(maxKeyLen) != 0){
		out.print("<b>Maximum Key Length:</b> " + maxKeyLen + "<br>");
	}else{
		out.print("<b>Keyword:</b> " + keyWord + "<br>");
	}
	%>
	
	</p> <br>
	<h3>Results:</h3>
	<b>Text:</b> <%=resultText%> <br>
	<% if(Integer.parseInt(maxKeyLen) != -1  && Integer.parseInt(maxKeyLen) != 0){
		out.print("<b>Actual Key Length:</b> " + keyLen + "<br>");
	}
	%>
	</p>

</div>

<%@ include file="includes/footer.jsp"%>

