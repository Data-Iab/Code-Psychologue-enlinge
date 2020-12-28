<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Nouveau formulaire</title>
</head>
<link href="UtilisateurCSS.css" rel="stylesheet" type="text/css">
<body>
	<form action="NouveauFormulaire" method="post">
		<br>
		<br>
		<h1 align=center>Nouveau formulaire</h1>
		<br>
		<br>
		<div>
			<table align=center>
				<thead>
					<tr>
						<th>Destinataire</th>
						<th>
							<select name="Destinataire"  class="dropbtn">
									<c:forEach items="${Destinataires}" var="Dest">
										<option value = "dest">${Dest}</option>
									</c:forEach>
							</select>
						</th>
					</tr>
				<tr>
					<td><input type="text" name="questionText" placeholder="Ajoutez une question"></td>
				</tr>
					<tr>
						<td></td>
						<th align=center><button type="submit">Envoyer</button></th>
					</tr>
				</thead>
			</table>
		</div>
	</form>
</body>
</html>