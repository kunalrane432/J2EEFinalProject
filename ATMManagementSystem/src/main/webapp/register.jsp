<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ATM Management System </title>
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
 
.main{
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

select {
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
</style>
</head>
<body>
	<div class="main">
        <h1>ATM Management System</h1>
        <h3>You Can Register Here</h3>
        <form action="RegisterServlet" method="post">
            <label for="username">
                  Username:
              </label>
            <input type="text"
                   id="username"
                   name="username"
                   placeholder="Enter your Username" required>
 
            <label for="password">
                  Password:
              </label>
            <input type="password"
                   id="password"
                   name="password"
                   placeholder="Enter your Password" required>
                   
             <label for="firstname">
                  Firstname:
              </label>
            <input type="text"
                   id="firstname"
                   name="firstname"
                   placeholder="Enter your First name" required>  
                   
              <label for="lastname">
                  Lastname:
              </label>
            <input type="text"
                   id="lastname"
                   name="lastname"
                   placeholder="Enter your Last name" required>  
                   
              <label for="dob">
                  Date of Birth:
              </label>
            <input type="date"
                   id="dob"
                   name="dob"
                   placeholder="Enter your dob" required>          
                   
              <label for="gender">
                  Choose a Gender:
              </label>
             <select name="gender" id="gender">
    			<option value="MALE">Male</option>
    			<option value="FEMALE">Female</option>
    			<option value="NOT_SPECIFIED">Not_Specified</option>
    		</select>    
    		
    		 <label for="email">
                  Email:
              </label>
            <input type="text"
                   id="email"
                   name="email"
                   placeholder="Enter your Email" required>  
                   
              <label for="phone">
                  Phone Number:
              </label>
            <input type="text"
                   id="phone"
                   name="phone"
                   placeholder="Enter your Phone Number" required>        
                   
                   
                               
 
            <div class="wrap">
                <button type="submit">
                    Submit
                </button>
            </div>
 
        </form>
       
    </div>
</body>
</html>