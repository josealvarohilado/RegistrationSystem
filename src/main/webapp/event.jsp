<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
	<title>Events</title>
</head>
<body>
	<div class="container">
		<div class="row">
			<nav class="navbar">
				<ul class="nav navbar-nav" id="menu">
					<li class="nav-item"><a href="/">Home</a></li>
					<li class="nav-item"><a href="/newEvent">Events</a></li>
					<li class="nav-item"><a href="/newGuest">Guests</a></li>
				</ul>
			</nav>
		</div>
		<div class="row">
			<div class="col-12 center-div">
				
				<h2>${eventAction }</h2>
				<form:form action="/${action}" modelAttribute="event" method="POST">
					<input type="hidden" name="eid" id="eid" value="${event.eid }">
					<div class="form-group">
						<form:input path="eventName" class="form-control" placeholder="Event Name"/>
					</div>
					<div class="form-group">
						<form:input path="eventDescription" class="form-control" placeholder="Description"/>
					</div>
					<div class="form-group">
					<form:select path="location" class="form-control">
						<form:option value="" label="Please Select Location"/>
						<form:options items="${locations }" itemValue="id" itemLabel="name"/>
					</form:select>
					<div class="form-group">
						<label for="eventDate">Event Date</label>
						<input type="date" name="eventDate" id="eventDate" class="form-control">
					</div>
					</div>
					<input type="submit" value="${eventAction }" class="btn btn-primary">
				</form:form>
				${returnMsg }
				
				<h2>Add Locations</h2>
				<form:form action="/addLocation" modelAttribute="location" method="POST">
					<div class="form-group">
						<form:input path="name" class="form-control" placeholder="Location Name"/>
					</div>
					<div class="form-group">
						<form:input path="description" class="form-control" placeholder="Description"/>
					</div>
					<input type="submit" value="Add Location" class="btn btn-primary">
				</form:form>
			</div>
		</div>
	</div>
</body>
</html>