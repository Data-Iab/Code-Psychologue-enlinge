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
<h1 align=center>
<form action="login" method="post">
	<button type="submit" class="boutonHaut">Logout</button>
</form>
</h1>
	<form action="RH" method="post">
		<br> <br>
		<h1 align=center>Veuillez approuver les états suivants</h1>
		<br> <br>
			<table>
				<thead>
					<tr>
						<th>Utilisateur</th>
						<th>Psychologue</th>
						<th>Approuver</th>
						<th>Etat</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${RH}" var="formulaire" varStatus="loop">
						<tr>
							<td><c:out value="${formulaire.getuser()}" /></td>
							<td><c:out value="${formulaire.getpsy()}" /></td>
							<input type="hidden" name="id_formulaire"
								value="${formulaire.returnIdFormulaire()}" />
							<td><select name="etat" class="dropbtn">
									<option value="${formulaire.returnetat()? '1':'0'}"><c:out value="${formulaire.returnetat()? 'Oui':'Non'}" /></option>
									<option value="${formulaire.returnetat()? '0':'1'}"><c:out value="${formulaire.returnetat()? 'Non':'Oui'}" /></option>
							</select></td>
							<td>
							<button type="button" class="${notification[loop.index]}"  disabled></button>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		<h1 align=center><button type="submit">Envoyer</button></h1>
		<h1 align=center id='text'>${succes}</h1>
	</form>
</body>
</html>