<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<link rel="stylesheet"
	href="http://twitter.github.com/bootstrap/assets/css/bootstrap-1.0.0.min.css" />
<title>Please Signin</title>
</head>
<body>
	<div class="topbar">
	</div>
	<div class="container">
		<form id="signin" action="<c:url value="/signin/authenticate" />"
			method="post">
			<div class="actions"></div>
			<div class="formInfo">
				<c:if test="${param.error eq 'bad_credentials'}">
					<div class="error">
						Your sign in information was incorrect. Please try again or <a
							href="<c:url value="/signup" />">sign up</a>.
					</div>
				</c:if>
				<c:if test="${param.error eq 'multiple_users'}">
					<div class="error">Multiple local accounts are connected to
						the provider account. Try again with a different provider or with
						your username and password.</div>
				</c:if>
			</div>
			<fieldset>
				<div class="clearfix"></div>

				<div class="clearfix">
					<label for="username">Username:</label>
					<div class="input">
						<input class="xlarge" id="username" name="j_username"
							<c:if test="${not empty signinErrorMessage}">value="${SPRING_SECURITY_LAST_USERNAME}"</c:if>
							size="30" type="text" />
					</div>
				</div>
				<div class="clearfix">
					<label for="password">Password:</label>
					<div class="input">
						<input class="xlarge" id="password" name="j_password" size="30"
							type="password" />
					</div>
				</div>
				<div class="actions">
					<input type="submit" class="btn primary" value="Login"/>
				</div>
			</fieldset>
		</form>
		<div class="row">
			<form id="tw_signin" action="<c:url value='/signin/twitter'/>" method="POST">
				<button type="submit">
					<img
						src="<c:url value="/resources/social/twitter/sign-in-with-twitter-d.png"/>" />
				</button>
			</form>
		</div>
	</div>
</body>
</html>