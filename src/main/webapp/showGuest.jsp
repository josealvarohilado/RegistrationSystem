<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
	<style type="text/css">
		.center-div{
			margin: 0 auto;
			width: 50%;
		}
		.pb-2{
			padding-bottom: 20px;
		}
	</style>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
	<title>Events</title>
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

		<div class='center-div'>
			<h1>Guest Details</h1>
			<form action="/updateGuest">
				<input type="hidden" name="aid" value="${guestDetails.aid }">
				<div class="form-group">
					<label for="firstName">First Name</label>
					<input type="text" name="firstName" id="firstName" value="${guestDetails.firstName }" class="form-control">
				</div>
				<div class="form-group">
					<label for="lastName">Last Name</label>
					<input type="text" name="lastName" id="lastName" value="${guestDetails.lastName }"  class="form-control">
				</div>
				<div class="form-group">
					<label for="contactNumber">Contact Number</label>
					<input type="text" name="contactNumber" id="contactNumber" value="${guestDetails.contactNumber }"  class="form-control">
				</div>
				<div class="form-group">
					<label for="address">Address</label>
					<input type="text" name="address" id="address" value="${guestDetails.address }"  class="form-control">
				</div>
				<input type="submit" value="Update Guest Details" class="btn btn-primary">
			</form>
			${updateGuestConfirmation }
		
			<h2>Register Guest to Event</h2>
			<div class="pb-2">
				<form action="/registerGuest" class="form-inline">
					<label for="events">Choose Event: </label>
					<select name="eid" id ="eid" class="form-control">
						<c:forEach var="event" items="${events }">
						<option value="${event.eid}">${event.eventName}</option>
						</c:forEach>
					</select>
					<input type="hidden" name="aid" id="aid" value="${guestDetails.aid }">
					<input type="Submit" value="Register Guest for event" class="btn btn-primary">
				</form >
				${registerConfirmation }
			</div>
			
			<table class='table'>
				<tr>
					<th>Event Name</th>
					<th>Description</th>
					<th>Date Registered</th>
				</tr>
				<c:forEach var='r' items="${registeredEvents }">
				<tr>
					<td>${r.pk.event.eventName }</td>
					<td>${r.pk.event.eventDescription }</td>
					<td>${r.timeRegistered }</td>
				</tr>
				</c:forEach>
			</table>
		</div>
	</div>
</body>
</html>