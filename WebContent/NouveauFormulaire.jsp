<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Nouveau formulaire</title>
</head>
<link href="NouveauFormulaireCSS.css" rel="stylesheet">
<body>
	<form action="NouveauFormulaire" method="post">
		<br> <br>
		<h1 align=center>Nouveau formulaire</h1>
		<br> <br>
		<div>
			<table align=center>
				<thead>
					<tr>
						<th>Destinataire</th>
						<th><select name="Destinataire" class="dropbtn">
								<c:forEach items="${Destinataires}" var="Dest">
									<option value="dest">${Dest}</option>
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
					<c:forEach items="${QuestionText}" var="qst">
						<tr>
							<td><c:out value="${qst}" /></td>
							<input type="hidden" name="AncienneQuestionText" value="${qst}" />
							<td><button type="submit" class="supprimer">Supprimer</button></td>
						</tr>
					</c:forEach>
				</tbody>

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