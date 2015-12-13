	<!-- directive -->
<%@ include file="includes/header.jsp"%>
	
	<!-- scriptlet -->
	<% 
	String maxKeyLen = request.getParameter("frmMaxKeyLen");  
	String inputText = request.getParameter("frmInputText");  
	String taskNum = request.getParameter("frmTaskNum");  
	String taskTypeNum = request.getParameter("frmTaskType");  
	String keyWord = request.getParameter("frmKeyWord");  
	
	String taskTypeStr;
	
	switch(taskTypeNum){
	case "0":
		taskTypeStr = "ENCRYPT";
		break;
	case "1":
		taskTypeStr = "DECRYPT";
		break;
	case "2":
		taskTypeStr = "CRACK";
		break;
	default:
		// undefined is an error
		taskTypeStr = "UNDEFINED";
		break;
}
	%>

	<!-- declaration -->
	<%! int timeout = 5000; %>

<div class="container">
	<div class="page-header">
		<h1>Task <small> #<%=taskNum%></small></h1>
	</div>
	
	<H2>Currently processing <%=taskTypeStr%> request</H1>
	<br>
	<p>	<!-- expression -->
	<b>Input Text:</b> <%=inputText%> <br>
	<% if(Integer.parseInt(maxKeyLen) != -1  && Integer.parseInt(maxKeyLen) != 0){
		out.print("<b>Maximum Key Length:</b> " + maxKeyLen + "<br>");
	}else{
		out.print("<b>Keyword:</b> " + keyWord + "<br>");
	}
	%>
	</p>
	<br><br>
	<p>Your task is not yet finished.</p>
	<br>
	<p>This page will refresh your request every <%= (timeout / 1000) %> seconds.</p>
</div>

<form name="frmCracker">
	<input name="frmMaxKeyLen" type="hidden" value="<%=maxKeyLen%>">
	<input name="frmInputText" type="hidden" value="<%=inputText%>">
	<input name="frmTaskNum" type="hidden" value="<%=taskNum%>">
	<input name="frmTaskType" type="hidden" value="<%=taskTypeNum%>">
	<input name="frmKeyWord" type="hidden" value="<%=keyWord%>">
</form>
		
<script>
	var wait=setTimeout("document.frmCracker.submit();", <%=timeout%>);
</script>

<%@ include file="includes/footer.jsp"%>

