<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ page session="false"%>
<html>
<head>
<title>Home</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
	<br>
	<div>
		<h2 style="text-align: center">URL Shortener</h2>
	</div>
	<br>

	<form action="${pageContext.request.contextPath}/enterURL"
		method="post" >
		<div class="form-group" style="margin: auto">
			<div class="col-xs-2">
				<label>Your website</label>
			</div>
			<div class="col-xs-6">
				<input type="url" name="originalURL" />
			</div>
			<div class="col-xs-1">
				<input type="submit" value="shorten" class="btn btn-default" />
			</div>
		</div>
	</form>

<%-- 	<sf:form action="${pageContext.request.contextPath}/enterURL"
		method="post" modelAttribute="url">
		<div class="form-group" style="margin: auto">
			<div class="col-xs-2">
				<label>Your website</label>
			</div>
			<div class="col-xs-6">
				<sf:input path="originalURL" id="originalURL" class="form-control" />
			</div>
			<div class="col-xs-1">
				<input type="submit" value="shorten" class="btn btn-default" />
			</div>
		</div>
	</sf:form> --%>

	<br>

	<c:if test="${not empty shortenURL}">
		<div>
			<a href="${shortenURL}"><h3>${shortenURL}</h3></a>
		</div>
	</c:if>

	<!-- 	<script>
		$(document).ready(function() {
			$('#uriForm').formValidation({
				framework : 'bootstrap',
				icon : {
					valid : 'glyphicon glyphicon-ok',
					invalid : 'glyphicon glyphicon-remove',
					validating : 'glyphicon glyphicon-refresh'
				},
				fields : {
					website : {
						validators : {
							uri : {
								message : 'The website address is not valid'
							}
						}
					}
				}
			});
		});
	</script> -->
</body>
</html>
