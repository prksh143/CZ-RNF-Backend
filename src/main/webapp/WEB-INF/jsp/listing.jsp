<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>RoofznFloorz</title>
</head>
<body>
	<jsp:include page="header.jsp" />
	<div class="container-fluid"
		style="top: 10%; position: fixed; height: 90%%;">
		<div class="row">
			<div class="col-3">
				<ul class="nav flex-column vmenu">
					<li class="nav-item"><a class="nav-link active" href="#">Basic
							Details</a></li>
					<li class="nav-item"><a class="nav-link" href="#">Location</a></li>
					<li class="nav-item"><a class="nav-link" href="#">Property
							Details</a></li>
					<li class="nav-item"><a class="nav-link " href="#">Pricing</a></li>
					<li class="nav-item"><a class="nav-link " href="#">Features</a></li>
				</ul>
			</div>
			<div class="col-8">
				<div id="basicdetails" style="display: none">basic details</div>
				<div id="location" style="display: none">location</div>
			</div>
		</div>
	</div>
	<jsp:include page="footer.jsp" />

</body>
</html>