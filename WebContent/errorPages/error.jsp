 <%@ include file="../includes/header.jsp"%>

<%@ page isErrorPage="true"%>

<div class="container">
	<div class="page-header">
		<h1>Error Occurred<small>whoops</small></h1>
	</div>
	
	<p>An exception has occurred, details below:</p>
	<br>
	<p><b>Exception is:</b><br>
	<%= exception %>
	</p>
</div>

<%@ include file="../includes/footer.jsp"%>

