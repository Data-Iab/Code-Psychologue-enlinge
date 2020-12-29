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
			<button class="button">Retour</button>
			<input type="hidden" name="nom" value="${psy}" />
		</form>
	</div>

	<form action="Recommendation" method="post" >
		<input type="hidden" name="Psychologue" value="${psy}" /> <br> <br>
		<h1 align=center>Nouvelle recommendation</h1>
		<br> <br>
		<div>
			<table align=center>
				<thead>
					<tr>
						<th>Destinataire</th>
						<th width="150"><select name="Destinataire" class="dropbtn">
								<c:forEach items="${Destinataires}" var="Dest">
									<option value="${Dest}">${Dest}</option>
								</c:forEach>
						</select></th>
					</tr>

					<tr>
						<th><input name="RecommendationText"
							placeholder="Ajoutez une recommendation"></th>
						<th width="150"><button type="submit" class="Ajouter">Ajouter</button>

							<div class="file-input" align=center>
								<input type="file" name="Fichier" id="file" class="file"> <label
									for="file">CSV</label>
							</div>
							</th>
					</tr>
					<tr>
						<th>Recommendations</th>
						<th width="150"></th>
					</tr><tbody>
				<c:if test="${RecommendationText.size()>0}">
					<c:forEach items="${RecommendationText}" var="qst" varStatus="loop">
						<tr>
							<td><c:out value="${qst}" /></td>
							<input type="hidden" name="AnciennesRecommendationsText" value="${qst}" />
							<td width="80"><button type="submit" class="supprimer"
									name="SupprimerButton" value="${loop.index}">Supprimer</button></td>
						</tr>
					</c:forEach>
				</c:if>
				</tbody>
			</table>
			<h2 align=center>
				<button type="submit" class="button" name="Recommendations_envoyer" value="Envoyer">Envoyer</button>
			</h2>
		</div>
	</form>
</body>
</html>