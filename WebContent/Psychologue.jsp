<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<input type="hidden" name="Psychologue" value="${psy}" />
<title>Psychologue ${psy}</title>
</head>
<link href="UtilisateurCSS.css" rel="stylesheet" type="text/css">
<body>
	<h1 align=center>
		<table>
			<tr>
				<form action="login" method="post">
					<button type="submit" class="boutonHaut">Logout</button>
				</form>
			</tr>
		</table>
	</h1>
	<h1 align=center>
		<table>
			<tr>
				<form action="NouveauFormulaire" method="post">
					<button type="submit" class="boutonHaut">Nouveau
						formulaire</button>
					<input type="hidden" name="Psychologue" value="${psy}" />
				</form>
				<form action="Recommendation" method="post">
					<button type="submit" class="boutonHaut">Nouvelle
						recommendation</button>
					<input type="hidden" name="Psychologue" value="${psy}" />
				</form>
			</tr>
		</table>
	</h1>
	<br>
	<c:forEach items="${formulaires}" var="formulaire">
				<table>
					<thead>
						<tr>
							<th><c:out value="${formulaire.getuser()}" /></th>
							<th width = "20%">Reponses</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${userquestion}" var="question">
							<c:if
								test="${question.returnIdformulaire() == formulaire.returnIdFormulaire()}">
								<tr>
									<td><c:out value="${question.returnQuestion()}" /></td>
									<input type="hidden" name="idQuestion"
										value="${question.returnIdQuestion()}" />
									<td width = "20%"><c:choose>
											<c:when test="${formulaire.returnetat()}">
												<c:choose>
													<c:when test="${question.returnEtatQuestion() == null}">
														<c:out value="EN ATTENTE" />
													</c:when>
													<c:otherwise>
														<c:out value="${question.returnReponse() ? 'Oui':'Non'}" />
													</c:otherwise>
												</c:choose>
											</c:when>
											<c:otherwise>
												<c:out value="PAS ENCORE APPROUVÉ" />
											</c:otherwise>
										</c:choose></td>
								</tr>
							</c:if>
						</c:forEach>
					</tbody>
				</table>
				<br>
				
	</c:forEach>
</body>
</html>