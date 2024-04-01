<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>ATM Management System - Login</title>
  <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@400;700&display=swap" rel="stylesheet">
 <link rel = "stylesheet" type = "text/css" href ="style1.css">
</head>
<body>
  <div class="login-bg">
    <div class="main">
      <h1>ATM Management System</h1>
      <h3>Enter your login credentials</h3>
      <form action="LoginServlet" method="post" onsubmit="return validateLoginForm()">
        <div class="form-group">
          <label for="username">Username:</label>
          <input type="text" id="username" name="username" placeholder="Enter your Username" required>
        </div>
        <div class="form-group">
          <label for="password">Password:</label>
          <input type="password" id="password" name="password" placeholder="Enter your Password" required>
        </div>
        <div class="wrap">
          <button type="submit">Submit</button>
        </div>
        <c:if test="${message != null}">
            <div class="wrap">
        		<h2><c:out value='${message }'/></h2>
        	</div>
        </c:if>
        
      </form>
      <p>Not registered? <a href="/ATM_Management_System/RegisterServlet" style="text-decoration: none;">Create an account</a></p>
    </div>
  </div>

  <script>
    function validateLoginForm() {
      const username = document.getElementById('username').value.trim();
      const password = document.getElementById('password').value.trim();

      if (username === '' || password === '') {
        alert('Please enter both username and password.');
        return false;
      }

      return true;
    }
  </script>
</body>
</html>