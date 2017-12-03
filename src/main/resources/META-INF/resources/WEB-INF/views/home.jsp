<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title>URL Shortener</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<style type="text/css">
body {
	background: aliceblue;
	font-family:"Comic Sans MS";
}
.center {
	margin-left: auto;
	margin-right: auto;
	text-align: center;
	vertical-align: middle;
}
.table th, td {
   text-align: center;   
}
</style>
</head>
<body>
	<br>

	<div class="continer">
		<div class="center">
			<h2>URL Shortener</h2>
		</div>
	
		<br>
		<br>

		<div class="container">
			<form class="form-horizontal" action="${pageContext.request.contextPath}" method="post">
				<div class="form-group">
					<label class="control-label col-sm-3">URL</label>
					<div class="col-sm-6">
						<input type="text" class="form-control" placeholder="Enter URL" name="originalURL">
					</div>
					<button type="submit" class="btn btn-default">Shorten</button>
				</div>
			</form>
		</div>
	
		<br>
		<br>
	
		<c:if test="${not empty originalURL}">
			<div class="col-sm-3"></div>
			<div class="col-sm-6">
				<table class="table table-hover">
					<thead>
						<tr>
							<th class="col-sm-3">Original URL</th>
							<th class="col-sm-3">Shortened URL</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td><a href="http://${originalURL}">${originalURL}</a></td>
							<td><a href="${shortenedURL}">${shortenedURL}</a></td>
						</tr>
					</tbody>
				</table>
			</div>
		</c:if>
	
		<c:if test="${not empty errorMsg}">
			<script>
				alert('${errorMsg}');
			</script>
		</c:if>
	</div>
</body>
</html>
