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
	<div class="multi-button">
		<form action="login" method="post">
			<button class="button">Logout</button>
		</form>
		<form action="login" method="get">
			<button type="submit" class="button">Retour</button>
			<input type="hidden" name="nom" value="${psy}" />
		</form>
	</div>

	<form action="NouveauFormulaire" method="post">
		<input type="hidden" name="Psychologue" value="${psy}" /> <br> <br>
		<h1 align=center>Nouveau formulaire</h1>
		<br> <br>

		<table>
			<thead>
				<tr>
					<th>Destinataire</th>
					<th ><select name="Destinataire" class="dropbtn">
							<c:forEach items="${Destinataires}" var="Dest">
								<option value="${Dest}">${Dest}</option>
							</c:forEach>
					</select></th>
				</tr>

				<tr>
					<th><input name="QuestionText"
						placeholder="Ajoutez une question"></th>
					<th width="150"><button type="submit" class="Ajouter">Ajouter</button>

						<div class="file-input" align=center>
							<input type="file" name="Fichier" id="file" class="file">
							<label for="file" >CSV</label>
						</div></th>
				</tr>
				<tr>
					<th>Questions</th>
					<th></th>
				</tr>
			<tbody>
				<c:if test="${QuestionText.size()>0}">
					<c:forEach items="${QuestionText}" var="qst" varStatus="loop">
						<tr>
							<td><c:out value="${qst}" /></td>
							<input type="hidden" name="AncienneQuestionText" value="${qst}" />
							<td width="80"><button type="submit" class="supprimer"
									name="SupprimerButton" value="${loop.index}">Supprimer</button></td>
						</tr>
					</c:forEach>
				</c:if>
			</tbody>
		</table>

		<h2 align=center>
			<button type="submit" name="Formulaire_envoyer" value="Envoyer" class="button">Envoyer</button>
		</h2>
	</form>
</body>
</html>