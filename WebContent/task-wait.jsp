	<!-- directive -->
<%@ include file="includes/header.jsp"%>
	
	<!-- scriptlet -->
	<% 
	String maxKeyLen = request.getParameter("frmMaxKeyLen");  
	String cipherText = request.getParameter("frmCipherText");  
	String taskNum = request.getParameter("frmTaskNum");  
	%>

	<!-- declaration -->
	<%! int timeout = 5000; %>

<div class="container">
	<div class="page-header">
		<h1>Task <small> #<%=taskNum%></small></h1>
	</div>
	
	<H2>Currently processing request</H1>
	<br>
	<p>	<!-- expression -->
	Maximum Key Length: <%=maxKeyLen%> <br>
	CipherText: <%=cipherText%> 
	</p>
	
	<p>Your task is not yet finished.</p>
	<br>
	<p>This page will refresh your request every <%= (timeout / 1000) %> seconds.</p>
</div>

<form name="frmCracker">
	<input name="frmMaxKeyLen" type="hidden" value="<%=maxKeyLen%>">
	<input name="frmCipherText" type="hidden" value="<%=cipherText%>">
	<input name="frmTaskNum" type="hidden" value="<%=taskNum%>">
</form>
		
<script>
	var wait=setTimeout("document.frmCracker.submit();", <%=timeout%>);
</script>

<%@ include file="includes/footer.jsp"%>

