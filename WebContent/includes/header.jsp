<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" errorPage="error.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<% 
String title = request.getParameter("title");
if(title == null){
	title = "Vigenere Cypher Breaker";
}
%>

<title><%=title%></title>

<!-- Bootstrap -->
<!-- Latest compiled and minified CSS -->
<!-- <link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"
	integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7"
	crossorigin="anonymous"> -->
	<link rel="stylesheet" href="bootstrap_angular/bootstrap.min.css">

<!-- Optional theme -->
<!-- <link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css"
	integrity="sha384-fLW2N01lMqjakBkx3l/M9EahuwpSfeNvV63J5ezn3uZzapT0u7EYsXMjQV+0En5r"
	crossorigin="anonymous"> -->
	<link rel="stylesheet" href="bootstrap_angular/bootstrap-theme.min.css" >

<!-- Latest compiled and minified JavaScript -->
<!-- <script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"
	integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS"
	crossorigin="anonymous"></script>
 -->
 <script src="bootstrap_angular/bootstrap.min.js" ></script>
 
 
<!-- custom styles . -->
<link rel="stylesheet" href="styles/styles.css">
<link rel="stylesheet" href="styles/sticky-footer.css">
<link rel="stylesheet" href="includes/cracker.css">

<!-- favicon -->
<link rel="shortcut icon" href="images/favicon.ico" type="image/x-icon" />
<!-- http://www.favicon.cc/ -->

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<!-- <script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script> -->
	<script
	src="bootstrap_angular/jquery.min.js"></script>
<!-- angular js-->
<!-- <script
	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.0.8/angular.min.js"></script> -->
<script
	src="bootstrap_angular/angular.min.js"></script>

<!-- app logic (js) -->
<script src="scripts/index.js"></script>
</head>

<body  ng-app="myApp">
<%@ include file="navbar.html"%>


	<!-- styles for footer and bootstrap container -->
	<!-- 	<div id="wrap">
 		<div id="main" class="container"> -->

	<center>
		</p>
		&nbsp;
		</p>
		&nbsp;
		</p>