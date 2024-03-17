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
		<h3>You Can Perform Below Transactions</h3>
		<form id="myForm" action="ServicesServlet" method="get">
			<input type="hidden" id="operation" name="operation"
				value="" />
			<input type="hidden" id="userid" name="userid"
				value="${sessionScope.userid}" />
			<div class="flex-container">

				<div class="flex-child magenta">
					<button id="viewbalance" type="button">View Balance</button>


				</div>

				<div class="flex-child green">
					<button id="credit" type="button">Credit</button>

				</div>
				<div class="flex-child magenta">
					<button id="withdraw" type="button">Withdraw</button>

				</div>

				<div class="flex-child green">
					<button id="movemoney" type="button">Move Money</button>

				</div>
				<div class="flex-child green">
					<button id="viewstatement" type="button">View Bank
						Statement</button>

				</div>
			</div>
		</form>

	</div>

	<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>

	<script>
        $(document).ready(function() {
            // Handle button clicks
            $('#viewbalance').click(function() {
            	
            	var myForm = document.getElementById('myForm');
        		document.getElementById('operation').value='viewbalance';
        		console.log(document.getElementById('operation').value);
                // Update form action and method
                myForm.action = "/ATM_Management_System/ViewBalanceServlet";
                //myForm.method = "GET";
        		console.log("button clicked");
                // Submit the form
                myForm.submit();
                
            });

            
			$('#credit').click(function() {
            	
            	var myForm = document.getElementById('myForm');
        		document.getElementById('operation').value='credit';
        		console.log(document.getElementById('operation').value);
                // Update form action and method
                myForm.action = "/ATM_Management_System/CreditAmountServlet";
                //myForm.method = "GET";
        		console.log("button clicked");
                // Submit the form
                myForm.submit();
                
            });
            
			$('#withdraw').click(function() {
	
				var myForm = document.getElementById('myForm');
				document.getElementById('operation').value='withdraw';
				console.log(document.getElementById('operation').value);
			    // Update form action and method
			    myForm.action = "/ATM_Management_System/DebitAmountServlet";
			    //myForm.method = "GET";
				console.log("button clicked");
			    // Submit the form
			    myForm.submit();
		    
			});
            
			$('#movemoney').click(function() {
				
				var myForm = document.getElementById('myForm');
				document.getElementById('operation').value='movemoney';
				console.log(document.getElementById('operation').value);
			    // Update form action and method
			    myForm.action = "/ATM_Management_System/MoveMoneyServlet";
			    //myForm.method = "GET";
				console.log("button clicked");
			    // Submit the form
			    myForm.submit();
		    
			});
            
			
			$('#viewstatement').click(function() {
				
				var myForm = document.getElementById('myForm');
				//document.getElementById('operation').value='viewstatement';
				console.log(document.getElementById('operation').value);
			    // Update form action and method
			    myForm.action = "/ATM_Management_System/ViewBankStatementServlet";
			    //myForm.method = "GET";
				console.log("button clicked");
			    // Submit the form
			    myForm.submit();
		    
			});

           
        });
    </script>


</body>
</html>