<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
	<style type="text/css">
		.center-div{
			margin: 0 auto;
			width: 50%;
		}
	</style>

	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
	<title>Guests</title>
</head>
<body>
	<div class="container">
		<nav class="navbar">
			<ul class="nav navbar-nav" id="menu">
				<li class="nav-item"><a href="/">Home</a></li>
				<li class="nav-item"><a href="/newEvent">Events</a></li>
				<li class="nav-item"><a href="/newGuest">Guests</a></li>
			</ul>
		</nav>
		<div class="center-div">
			<h1>New Guest Registration</h1>
			<form action="/createGuest">
				<div class="form-group"> 
					<input type="text" name="firstName" id="firstName" placeholder="First Name" class="form-control">
				</div>
				<div class="form-group"> 
					<input type="text" name="lastName" id="lastName" placeholder="Last Name" class="form-control">
				</div>
				<div class="form-group"> 
					<input type="text" name="contactNumber" id="contactNumber" placeholder="Contact Number" class="form-control">
				</div>
				<div class="form-group"> 
					<input type="text" name="address" id="address" placeholder="Address" class="form-control">
				</div>
					<input type="submit" value="Register Guest" class="btn btn-primary">
			</form>
		</div>
	</div>
</body>
</html>