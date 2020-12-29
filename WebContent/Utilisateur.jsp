<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${utilisateur}</title>

</head>
<link href="UtilisateurCSS.css" rel="stylesheet" type="text/css">
<body>
<h1 align=center>
<form action="login" method="post">
	<button type="submit" class="boutonHaut">Logout</button>
</form>
</h1>
	<form action="utilisateur" method="post">
		<br>
		<br>
		<c:if test="${userquestion.size()>0}">
		<h1 align=center>Veuillez répondre aux questions suivantes</h1>
		<br>
		<br>
		
			<table>
				<thead>
					<tr>
						<th>Question</th>
						<th>Réponse</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${userquestion}" var="question">
						<tr>
							<td><c:out value="${question.returnQuestion()}" /></td>
							<input type="hidden" name="idQuestion" value="${question.returnIdQuestion()}"/>
							<td ><select name="reponse"  class="dropbtn">
									<option value="1">Oui</option>
									<option value="0">Non</option>
							</select></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<h1 align=center><button type="submit">Envoyer</button></h1>
			</c:if>
			<c:if test="${recommendations.size()>0}">
			<table>
				<thead>
					<tr>
						<th>Recommendations</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${recommendations}" var="req">
						<tr>
							<td><c:out value="${req}" /></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			</c:if>
	</form>
</body>
</html>