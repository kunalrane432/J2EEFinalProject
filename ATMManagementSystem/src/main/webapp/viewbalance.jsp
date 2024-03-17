<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ATM Management System</title>
<style>
body {
	display: flex;
	align-items: center;
	justify-content: center;
	font-family: sans-serif;
	line-height: 1.5;
	min-height: 100vh;
	background: #f3f3f3;
	flex-direction: column;
	margin: 0;
}

.main {
	background-color: #fff;
	border-radius: 15px;
	box-shadow: 0 0 20px rgba(0, 0, 0, 0.2);
	padding: 10px 20px;
	transition: transform 0.2s;
	width: 500px;
	text-align: center;
}

h1 {
	color: #4CAF50;
}

label {
	display: block;
	width: 100%;
	margin-top: 10px;
	margin-bottom: 5px;
	text-align: left;
	color: #555;
	font-weight: bold;
}

input {
	display: block;
	width: 100%;
	margin-bottom: 15px;
	padding: 10px;
	box-sizing: border-box;
	border: 1px solid #ddd;
	border-radius: 5px;
}

button {
	padding: 15px;
	border-radius: 10px;
	margin-top: 15px;
	margin-bottom: 15px;
	border: none;
	color: white;
	cursor: pointer;
	background-color: #4CAF50;
	width: 100%;
	font-size: 16px;
}

.wrap {
	display: flex;
	justify-content: center;
	align-items: center;
}

.flex-container {
	display: flex;
}

.flex-child {
	flex: 1;
	border: 2px solid yellow;
}

.flex-child:first-child {
	margin-right: 20px;
}
</style>
</head>
<body>


	<div class="main">
		<h1>ATM Management System</h1>
		<h1>Welcome, ${sessionScope.username}!</h1>
		<a href="/ATM_Management_System/LogoutServlet">Logout</a>
		<h3>View Balance</h3>
		<form id="myForm" action="ServicesServlet" method="get">
			<input type="hidden" id="operation" name="operation"
				value="viewbalance" />
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

	<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>

	<script>
        $(document).ready(function() {
           

           
        });
    </script>


</body>
</html>