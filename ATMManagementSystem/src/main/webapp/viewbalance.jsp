<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Online Banking System</title>
<link rel = "stylesheet" type = "text/css" href ="style1.css">
</head>
<body>
<header>
<div class="navbar">
  		
  		<a href="/ATM_Management_System/ServicesServlet?username=<c:out value='${sessionScope.username}'/>&userid=<c:out value='${sessionScope.userid}'/>">Services</a>
  		<a href="/ATM_Management_System/ContactServlet?username=<c:out value='${sessionScope.username}'/>&userid=<c:out value='${sessionScope.userid}'/>">Help</a>
  		<a href="/ATM_Management_System/LogoutServlet" class="logout-link">Logout</a>
  		</div>
</header>

	<div class="main">
		<h1>Online Banking System</h1>
		<h1>Welcome, ${sessionScope.username}!</h1>
		
		<h3>View Balance</h3>
		<form id="myForm" action="ServicesServlet" method="get">
			<input type="hidden" id="operation" name="operation"
				value="viewbalance" />
			<input type="hidden" id="username" name="username"
				value="${sessionScope.username}" />	
			<input type="hidden" id="userid" name="userid"
				value="${sessionScope.userid}" />
			<div class="flex-container">

				<div class="flex-child magenta">
					<h2>Savings </h2> <h2>${sessionScope.savings_account}</h2><h2>$</h2>


				</div>

				<div class="flex-child green">
					<h2>Chequing </h2> <h2>${sessionScope.chequing_account}</h2><h2>$</h2>

				</div>
				
			</div>
		</form>

	</div>
<footer><h1 class="footer">Online Banking System</h1></footer>
	<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>

	<script>
        $(document).ready(function() {
           

           
        });
    </script>


</body>
</html>