<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
	<title>Registration System</title>
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
	
		<h1>Search for Event</h1>
		<form action="/searchEvent" class="form-inline">
			<input type="text" name="eventName" id="eventName" class="form-control" placeholder="Enter Event Name">
			<input type="submit" value="Search Event" class="btn btn-primary">
			<a href="/newEvent">Create Event</a>
		</form><br>
		
		<table class="table">
			<tr>
				<th>Event Name</th>
				<th>Event Description</th>
				<th>Event Location</th>
				<th>Event Date</th>
				<th> </th>
			</tr>
			<c:forEach var="event" items="${eventSearch}">
				<tr>
					<td><a href="/event?eid=${event.eid}"><c:out value="${event.eventName }"/></a></td>
					<td><c:out value="${event.eventDescription }"/></td>
					<td><c:out value="${event.location.name }"/></td>
					<td><fmt:formatDate value="${event.eventDate }" dateStyle="long"/></td>
					<td><a href="/deleteEvent?eid=${event.eid }" class="btn btn-danger">Delete</a></td>
				</tr>
			</c:forEach>
		</table>
	
		<h1>Search for Guest</h1>
		<form action="/searchGuest" class="form-inline">
			<input type="text" name="guestName" id="guestName" class="form-control" placeholder="Enter Guest Name">
			<input type="Submit" value="Search Guest" class="btn btn-primary">
			<a href="/newGuest">Register new Guest</a>
		</form><br>
		
		<table class="table">
			<tr>
				<th>Name</th>
				<th>Contact Number</th>
				<th>Address</th>
			</tr>
			<c:forEach var="guest" items="${guestSearch}">
				<tr>
					<td><a href="/guest?aid=${guest.aid}"><c:out value="${guest.firstName} ${guest.lastName }"/></a></td>
					<td><c:out value="${guest.contactNumber }"/></td>
					<td><c:out value="${guest.address }"/></td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>