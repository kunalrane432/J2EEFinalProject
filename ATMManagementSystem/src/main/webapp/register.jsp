<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Online Banking System</title>
  <link rel="stylesheet" type="text/css" href="style1.css">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/flatpickr/dist/flatpickr.min.css">
  <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@400;700&display=swap" rel="stylesheet">
  <style>
    body {
      font-family: 'Montserrat', sans-serif;
      background: linear-gradient(to bottom right, #4568dc, #b06ab3);
      color: #fff;
    }

    .main {
      max-width: 600px;
      margin: 40px auto;
      padding: 40px;
      background-color: rgba(255, 255, 255, 0.9);
      box-shadow: 0 0 20px rgba(0, 0, 0, 0.3);
      border-radius: 10px;
      color: #333;
    }

    h1, h3 {
      text-align: center;
      text-transform: uppercase;
      letter-spacing: 2px;
      text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.3);
    }

    .form-group {
      margin-bottom: 20px;
    }

    label {
      display: block;
      font-weight: bold;
      margin-bottom: 5px;
    }

    input[type="text"],
    input[type="password"],
    input[type="email"],
    input[type="tel"],
    select {
      width: 100%;
      padding: 10px;
      border: 1px solid #ccc;
      border-radius: 5px;
      box-sizing: border-box;
      font-family: 'Montserrat', sans-serif;
      font-size: 16px;
      color: #333;
    }

    input[type="text"]:focus,
    input[type="password"]:focus,
    input[type="email"]:focus,
    input[type="tel"]:focus,
    select:focus {
      outline: none;
      border-color: #4568dc;
      box-shadow: 0 0 5px rgba(69, 104, 220, 0.5);
    }

    button[type="submit"] {
      background-color: #4568dc;
      color: #fff;
      padding: 10px 20px;
      border: none;
      border-radius: 5px;
      cursor: pointer;
      transition: background-color 0.3s ease;
      font-family: 'Montserrat', sans-serif;
      font-size: 16px;
      font-weight: bold;
      letter-spacing: 1px;
      width: 100%;
    }

    button[type="submit"]:hover {
      background-color: #2c49b3;
    }

    .wrap {
      text-align: center;
    }

    @keyframes fadeIn {
      0% { opacity: 0; }
      100% { opacity: 1; }
    }

    .fade-in {
      animation: fadeIn 0.5s ease-in-out;
    }
  </style>
</head>
<body>
  <div class="main fade-in">
    <h1>Online Banking System</h1>
    <h3>You Can Register Here</h3>
    <form action="RegisterServlet" method="post" onsubmit="return validateRegistrationForm()">
      <div class="form-group">
        <label for="username">Username:</label>
        <input type="text" id="username" name="username" placeholder="Enter your Username" required>
      </div>

      <div class="form-group">
        <label for="password">Password:</label>
        <input type="password" id="password" name="password" placeholder="Enter your Password" required>
      </div>

      <div class="form-group">
        <label for="firstname">Firstname:</label>
        <input type="text" id="firstname" name="firstname" placeholder="Enter your First name" required>
      </div>

      <div class="form-group">
        <label for="lastname">Lastname:</label>
        <input type="text" id="lastname" name="lastname" placeholder="Enter your Last name" required>
      </div>

      <div class="form-group">
        <label for="dob">Date of Birth:</label>
        <input type="text" id="dob" name="dob" placeholder="Enter your Date of Birth" required>
      </div>

      <div class="form-group">
        <label for="gender">Choose a Gender:</label>
        <select name="gender" id="gender">
          <option value="MALE">Male</option>
          <option value="FEMALE">Female</option>
          <option value="NOT_SPECIFIED">Not_Specified</option>
        </select>
      </div>

      <div class="form-group">
        <label for="email">Email:</label>
        <input type="email" id="email" name="email" placeholder="Enter your Email" required>
      </div>

      <div class="form-group">
        <label for="phone">Phone Number:</label>
        <input type="tel" id="phone" name="phone" placeholder="Enter your Phone Number" required>
      </div>

      <div class="wrap">
        <button type="submit">Submit</button>
      </div>
    </form>
  </div>

  <script src="https://cdn.jsdelivr.net/npm/flatpickr"></script>
  <script>
    flatpickr("#dob", {
      dateFormat: "Y-m-d",
      maxDate: new Date().fp_incr(-18), // Minimum age 18
    });

    function validateRegistrationForm() {
      const username = document.getElementById('username').value.trim();
      const password = document.getElementById('password').value.trim();
      const firstname = document.getElementById('firstname').value.trim();
      const lastname = document.getElementById('lastname').value.trim();
      const dob = document.getElementById('dob').value;
      const email = document.getElementById('email').value.trim();
      const phone = document.getElementById('phone').value.trim();

      // Check if any required field is empty
      if (
        username === '' ||
        password === '' ||
        firstname === '' ||
        lastname === '' ||
        dob === '' ||
        email === '' ||
        phone === ''
      ) {
        alert('Please fill in all required fields.');
        return false;
      }

      // Validate password length
      if (password.length < 8) {
        alert('Password must be at least 8 characters long.');
        return false;
      }

      // Validate email format
      const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
      if (!emailRegex.test(email)) {
        alert('Please enter a valid email address.');
        return false;
      }

      // Validate phone number format
      const phoneRegex = /^\d{10}$/;
      if (!phoneRegex.test(phone)) {
        alert('Please enter a valid 10-digit phone number.');
        return false;
      }

      return true;
    }
  </script>
</body>
</html>