<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
</head>
<body>
<div align = "center">
	<form action="login" method="post">
		<table>
			<tr>
				<td align ="center">Nom d'utilisateur</td>
			</tr>
			<tr>
				<td><input type="text" name="nom"></td>
			</tr>
			<tr>
				<td align ="center">Mot de passe</td>
			</tr>
			<tr>
				<td><input type="password" name="mot de passe"></td>
			</tr>
			<tr>
				<td align ="center"><input type="submit" value="Login"></td>
			</tr>
		</table>
	</form>
</div>
</body>
</html>