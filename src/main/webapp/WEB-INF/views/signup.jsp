<%@ page session="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<link rel="stylesheet"
	href="http://twitter.github.com/bootstrap/assets/css/bootstrap-1.0.0.min.css" />
<title>Please Signin</title>
</head>
<body>

	<div class="container">
		<h3>Sign Up</h3>

		<c:if test="${not empty message}">
			<div class="${message.type.cssClass}">${message.text}</div>
		</c:if>

		<c:url value="/signup" var="signupUrl" />
		<form id="signup" action="${signupUrl}" method="post"
			modelAttribute="signupForm">


			<fieldset>
				<div class="clearfix"></div>

				<div class="clearfix">
					<label for="firstname">First Name:</label>
					<div class="input">
						<input class="xlarge" id="firstname" name="firstName" size="30"
							type="text" />
					</div>
				</div>
				<div class="clearfix">
					<label for="lastname">Last Name:</label>
					<div class="input">
						<input class="xlarge" id="lastname" name="lastName" size="30"
							type="text" />
					</div>
				</div>
				<div class="clearfix">
					<label for="username">Username:</label>
					<div class="input">
						<input class="xlarge" id="username" name="username" size="30"
							type="text" />
					</div>
				</div>
				<div class="clearfix">
					<label for="password">Password:</label>
					<div class="input">
						<input class="xlarge" id="password" name="password" size="30"
							type="password" />
					</div>
				</div>
				<div class="actions">
					<input type="submit" class="btn primary" value="Sign up"/>
				</div>
			</fieldset>
				
		</form>
	</div>
</body>
</html>