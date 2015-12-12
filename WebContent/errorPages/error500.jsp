 <%@ include file="../includes/header.jsp"%>

<%@ page isErrorPage="true"%>

<div class="container">
	<div class="page-header">
		<h1>Error 500<small>whoops</small></h1>
	</div>
	
	<p>An error 500 exception has occurred, details below:</p>
	<br>
	<p><b>Exception:</b><br>
	<%= exception %>
	</p>
</div>

<%@ include file="../includes/footer.jsp"%>

