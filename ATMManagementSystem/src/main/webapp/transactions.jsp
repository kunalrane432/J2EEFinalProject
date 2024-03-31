<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ATM Management System</title>
<link rel = "stylesheet" type = "text/css" href ="style1.css">
 <script src="trans.js"></script> 
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
		<h1>ATM Management System</h1>
		<h1>Welcome, ${sessionScope.username}!</h1>
		
		<h3>You Can View Transactions Here</h3>
		<form id="myForm" action="ViewBankStatementServlet" method="get">
			<input type="hidden" id="operation" name="operation"
				value="viewstatement" />
			<input type="hidden" id="userid" name="userid"
				value="${sessionScope.userid}" />
			<input type="hidden" id="username" name="username"
				value="${sessionScope.username}" />	
			<div class="flex-container">

				<div class="flex-child magenta">
					<label for="from">
                 	 From:
             		 </label>
            		<input type="date"
                   		id="from"
                  		name="from"
                   		placeholder="Enter your Date From" required>
                   		
                   		
                   	<label for="to">
                 	 To:
             		 </label>
            		<input type="date"
                   		id="to"
                  		name="to"
                   		placeholder="Enter your Date To" required>	
                   		
                   		
                   		
                   		
						<button id="viewstatement" type="button">View Bank
						Statement</button>

				


				</div>
				
				<div id="transaction-table">
					<table border="1">
						<tr>
							<th>Transaction ID</th>
							<th>Transaction Type</th>
							<th>Transaction Date</th>
							<th>Transaction Amount</th>
						</tr>
						
						
						<c:forEach var="transactions" items="${transactions}">
							<tr>
								<td>${transactions.transactionid}</td>
								<td>${transactions.transaction_type}</td>
								<td>${transactions.transaction_date}</td>
								<td>${transactions.amount}</td>	
							</tr>
						</c:forEach>
					</table>
				</div>

				
				
			</div>
		</form>

	</div>
<footer><h1 class="footer">ATM Management System</h1></footer>
	<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>

	<script>
        $(document).ready(function() {
				$('#viewstatement').click(function() {
				
				var myForm = document.getElementById('myForm');
				document.getElementById('operation').value='viewstatement';
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