<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${formulaire}</title>

</head>
<link href="UtilisateurCSS.css" rel="stylesheet" type="text/css">
<body>
	<form action="RH" method="post">
		<br> <br>
		<h1 align=center>Veuillez approuver les états suivants</h1>
		<br> <br>
		<div class="container">
			<table align=center>
				<thead>
					<tr>
						<th>Utilisateur</th>
						<th>Psychologue</th>
						<th>l'état</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${RH}" var="formulaire">
						<tr>
							<td><c:out value="${formulaire.getuser()}" /></td>
							<td><c:out value="${formulaire.getpsy()}" /></td>
							<input type="hidden" name="id_formulaire"
								value="${formulaire.returnIdFormulaire()}" />
							<td><select name="etat" class="dropbtn">
									<option value="0">Non</option>
									<option value="1">Oui</option>
							</select></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<h1 align=center><button type="submit">Envoyer</button></h1>
	</form>
</body>
</html>