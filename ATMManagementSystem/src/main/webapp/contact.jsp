<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>ATM Management System - Contact Us</title>
  <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@400;700&display=swap" rel="stylesheet">
  <link rel = "stylesheet" type = "text/css" href ="style1.css">
</head>
<body>
  <header>
    <div class="navbar">
      <a href="services.jsp">Services</a>
      <a href="contact.jsp" class="active">Contact Us</a>
      <a href="/ATM_Management_System/LogoutServlet" class="logout-link">Logout</a>
    </div>
  </header>

  <div class="main">
    <h1>Contact Us</h1>
    <div class="contact-info fade-in">
      <div class="flex-child">
        <h3>Address</h3>
        <p>123 Main Street<br>City, State 12345</p>
      </div>
      <div class="flex-child">
        <h3>Email</h3>
        <p>info@atmsystem.com</p>
      </div>
      <div class="flex-child">
        <h3>Phone</h3>
        <p>+1 (555) 123-4567</p>
      </div>
    </div>

    <h3>Send Us a Message</h3>
    <form id="contact-form" action="ContactServlet" method="post" class="fade-in">
      <div class="form-group">
        <label for="name">Name</label>
        <input type="text" id="name" name="name" placeholder="Enter your name" required>
      </div>
      <div class="form-group">
        <label for="email">Email</label>
        <input type="email" id="email" name="email" placeholder="Enter your email" required>
      </div>
      <div class="form-group">
        <label for="message">Message</label>
        <textarea id="message" name="message" rows="5" placeholder="Enter your message" required></textarea>
      </div>
      <div class="wrap">
        <button type="submit">Send Message</button>
      </div>
    </form>
  </div>

  <footer>
    <p>&copy; 2023 ATM Management System. All rights reserved.</p>
  </footer>
</body>
</html>