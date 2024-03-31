<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ATM Management System</title>
<link rel = "stylesheet" type = "text/css" href ="style1.css">

</head>
<body>
<header>
<div class="navbar">
  		
  		<a href="services.jsp">Services</a>
  		<a href="contact.jsp">Help</a>
  		<a href="/ATM_Management_System/LogoutServlet" class="logout-link">Logout</a>
  		</div>
</header>

	<div class="main">
	
		
		<h1>Welcome, ${sessionScope.username}!</h1>
		
		<h3>You Can Perform Below Transactions</h3>
		<form id="myForm" action="ServicesServlet" method="get">
			<input type="hidden" id="operation" name="operation"
				value="" />
			<input type="hidden" id="userid" name="userid"
				value="${sessionScope.userid}" />
			<input type="hidden" id="username" name="username"
				value="${sessionScope.username}" />	
			<div class="transaction-buttons">

				<div class="button">
					<button id="viewbalance" type="button">View Balance</button>


				</div>
				
				<div class="button">
					<button id="credit" type="button">Credit</button>

				</div>
				<div class="button">
					<button id="withdraw" type="button">Withdraw</button>

				</div>

				<div class="button">
					<button id="movemoney" type="button">Move Money</button>

				</div>
				<div class="button">
					<button id="viewstatement" type="button">View Bank
						Statement</button>

				</div>
			</div>
		</form>

	</div>
<footer><h1 class="footer">ATM Management System</h1></footer>
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