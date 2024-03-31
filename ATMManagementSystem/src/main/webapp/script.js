// Function to validate forms
function validateForm() {
  // Get form elements
  var username = document.forms["myForm"]["username"].value;
  var password = document.forms["myForm"]["password"].value;
  var amount = document.forms["myForm"]["amount"].value;
  var from = document.forms["myForm"]["from"].value;
  var to = document.forms["myForm"]["to"].value;

  // Regular expressions for validation
  var usernameRegex = /^[a-zA-Z0-9]+$/;
  var passwordRegex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[a-zA-Z\d]{8,}$/;
  var amountRegex = /^[0-9]+(\.[0-9]{1,2})?$/;

  // Username validation
  if (username == "") {
    alert("Username must be filled out");
    return false;
  } else if (!usernameRegex.test(username)) {
    alert("Username can only contain alphanumeric characters");
    return false;
  }

  // Password validation
  if (password == "") {
    alert("Password must be filled out");
    return false;
  } else if (!passwordRegex.test(password)) {
    alert("Password must be at least 8 characters long and contain at least one uppercase letter, one lowercase letter, and one number");
    return false;
  }

  // Amount validation
  if (amount != "" && !amountRegex.test(amount)) {
    alert("Amount must be a valid number");
    return false;
  }

  // Date validation
  if (from != "" && to != "") {
    var fromDate = new Date(from);
    var toDate = new Date(to);
    if (fromDate > toDate) {
      alert("'From' date cannot be later than 'To' date");
      return false;
    }
  }

  // If all validations pass, return true to submit the form
  return true;
}