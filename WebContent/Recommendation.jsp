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
<h1 align=center>
<table>
<tr>
<form action="login" method="post">
	<button type="submit" class="boutonHaut">Logout</button>
</form>
<form class=login action="login" method="get">
	<button type="submit" class="boutonHaut">Retour</button>
	<input type="hidden" name="nom" value="${psy}" />
</form>
</tr>


</table>
</h1>
	<form action="Recommendation" method="post" >
		<input type="hidden" name="Psychologue" value="${psy}" /> <br> <br>
		<h1 align=center>Nouvelle recommendation</h1>
		<br> <br>
		<div>
			<table align=center>
				<thead>
					<tr>
						<th>Destinataire</th>
						<th width="150"><select name="Destinataire">
								<c:forEach items="${Destinataires}" var="Dest">
									<option value="${Dest}">${Dest}</option>
								</c:forEach>
						</select></th>
					</tr>

					<tr>
						<th><input name="RecommendationText"
							placeholder="Ajoutez une recommendation"></th>
						<th width="150"><button type="submit">Ajouter</button>

							<div class="file-input">
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
			<h1 align=center>
				<button type="submit" name="Recommendations_envoyer" value="Envoyer">Envoyer</button>
			</h1>
		</div>
	</form>
</body>
</html>