<%@ include file="includes/header.jsp"%>

	<% 
	String keyWord = request.getParameter("keyWord");  
	String keyLen = request.getParameter("keyLen");  
	String plainText = request.getParameter("plainText"); 
	String taskNum = request.getParameter("taskNum");  
	String cipherText = request.getParameter("cipherText");  
	String maxKeyLen = request.getParameter("maxKeyLen");  
	%>

<div class="container">
	<div class="page-header">
		<h1>Task <small> #<%=taskNum%></small></h1>
	</div>
	
	<H2>Finished processing request</H1>
	<br>
	<p>
	<h3>Data Given:</h2>
	CipherText: <%=cipherText%> <br>
	Maximum Key Length: <%=maxKeyLen%> <br>
	</p> <br>
	<h3>Results:</h2>
	Plain Text: <%=plainText%> <br>
	Actual Key Length: <%=keyLen%> <br>
	Key Word: <%=keyWord%> 
	</p>

</div>

<%@ include file="includes/footer.jsp"%>

