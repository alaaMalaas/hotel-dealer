<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Expedia Assignment Home Page</title>
</head>
<body>
	<form action="DealFinder" method="post">
		 <input type="hidden" name="cmd" value="Search">
		<div class="form-group col-md-12">
			<label>Destination Name</label> <input class="form-control"
				type="text" name="shortName">
		</div>
		<div class="form-group col-md-5">
			<label> Minimum Trip Start Date </label> <input
				class="form-control datepicker" type="text" id="minTripStartDate"
				name="minTripStartDate">
		</div>
		<div class="form-group col-md-2"></div>
		<div class="form-group col-md-5">
			<label> Maximum Trip Start Date</label> <input
				class="form-control datepicker" type="text" id="maxTripStartDate"
				name="maxTripStartDate">
		</div>
		<div class="form-group col-md-12">
			<label> Length Of Stay</label> <input class="form-control"
				type="number" name="lengthOfStay">
		</div>
		<div class="form-group col-md-5">
			<label> Minimum Star Rating </label> <input class="form-control"
				step="0.5" type="number" name="minStarRating">
		</div>
		<div class="col-md-2"></div>
		<div class="form-group col-md-5">
			<label> Maximum Star Rating</label> <input class="form-control"
				step="0.5" type="number" name="maxStarRating">
		</div>
		<div class="form-group col-md-5">
			<label> Minimum Total Rate</label> <input class="form-control"
				step="0.5" type="number" name="minTotalRate">
		</div>
		<div class="col-md-2"></div>
		<div class="form-group col-md-5">
			<label> Maximum Total Rate </label> <input class="form-control"
				step="0.5" type="number" name="maxTotalRate">
		</div>
		<div class="form-group col-md-5">
			<label> Minimum Guest Rating</label> <input class="form-control"
				step="0.5" type="number" name="minGuestRating">
		</div>
		<div class="col-md-2"></div>
		<div class="form-group col-md-5">
			<label> Maximum Guest Rating</label> <input class="form-control"
				step="0.5" type="number" name="maxGuestRating">
		</div> 
		<!-- <label> hotel name </label> <input class="form-control"
				step="0.5" type="text" name="hotelName"> -->
		<input type="submit" value="Search">
	</form>
	
	<script>
		$(".datepicker").datepicker({
			dateFormat : 'yy-mm-dd',
			minDate : new Date()
		});
		$("#minTripStartDate").on(
				"change",
				function() {
					$("#maxTripStartDate").datepicker("option", "minDate",
							$(this).val());
				});
		$("#maxTripStartDate").on(
				"change",
				function() {
					$("#minTripStartDate").datepicker("option", "maxDate",
							$(this).val());
				});
		function validateForm() {
			$("input").blur(function() {
				if ($(this).is(":invalid")) {
					return false;
				}
 			});
			return true;
		}
	</script>
</body>
</html>