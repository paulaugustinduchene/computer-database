<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<%@ page import="java.text.*"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- Bootstrap -->
<link href="resources/css/bootstrap.min.css" rel="stylesheet"
	media="screen">
<link href="resources/css/font-awesome.css" rel="stylesheet"
	media="screen">
<link href="resources/css/main.css" rel="stylesheet" media="screen">

<title>Authentification</title>
</head>
<body>

	<section id="main">
		<div class="container">
			<div class="row">
				<div class="col-xs-8 col-xs-offset-2 box">
					<form action="auth" method="POST">
						<fieldset>
							<div class="form-group">
								<label for="username">Username:</label> 
								<input class="form-control" id="username" name="username" placeholder="username" type="text" />
							</div>
							<div class="form-group" >
								<label for="password">Password:</label> 
								<input class="form-control" id="password" name="password" placeholder="password" type="password" />
							</div>
						</fieldset>
						<div class="actions pull-right">
                            <input type="submit" value="Login" class="btn btn-primary">
                        </div>
					</form>
				</div>
			</div>
		</div>
	</section>

</body>
</html>