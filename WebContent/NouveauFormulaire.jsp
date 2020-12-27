<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Nouveau Formulaire</title>
</head>
<link href="NouveauFormulaireCSS.css" rel="stylesheet" type="text/css">
<body>

<form action="login" method="post">
	<button type="submit" class="logout">Logout</button>
</form>
<form class=login action="login" method="get">
	<button type="submit" class="logout">Retour</button>
	<input type="hidden" name="nom" value="${psy}" />
</form>

	<form action="NouveauFormulaire" method="post">
		<input type="hidden" name="Psychologue" value="${psy}" /> <br> <br>
		<h1 align=center>Nouveau formulaire</h1>
		<br> <br>
		<div>
			<table align=center>
				<thead>
					<tr>
						<th>Destinataire</th>
						<th><select name="Destinataire" class="dropbtn">
								<c:forEach items="${Destinataires}" var="Dest">
									<option value="${Dest}">${Dest}</option>
								</c:forEach>
						</select></th>
					</tr>

					<tr>
						<th><input name="QuestionText"
							placeholder="Ajoutez une question"></th>
						<th><button type="submit">Ajouter</button></th>
					</tr>
					<tr>
						<th>Questions</th>
						<th></th>
					</tr>
				<tbody>

					<c:forEach items="${QuestionText}" var="qst" varStatus="loop">
						<tr>
							<td><c:out value="${qst}" /></td>
							<input type="hidden" name="AncienneQuestionText" value="${qst}" />
							<td><button type="submit" class="supprimer"
									name="SupprimerButton" value="${loop.index}">Supprimer</button></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<h1 align=center>
				<button type="submit" name="Formulaire_envoyer" value="Envoyer" onclick="alert('Voulez vous vraiment envoyer ce formulaire ?.')">Envoyer</button>
			</h1>
		</div>
	</form>
</body>
</html>