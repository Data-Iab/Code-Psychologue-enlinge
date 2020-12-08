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
	<form class=envoie action="envoie" method="post">
		<br>
		<br>
		<h1 align=center>Veuillez répondre aux questions suivantes</h1>
		<br>
		<br>
		<div class="container">
			<table align=center>
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
							<td><select name="reponse" class="dropbtn">
									<option value="false">Oui</option>
									<option value="true">Non</option>
							</select></td>
						</tr>
					</c:forEach>
				</tbody>
				<thead>
					<tr>
						<td></td>
						<th align=center><button type="submit" value="Login">Envoyer</button></th>
					</tr>
				</thead>
			</table>
		</div>
	</form>
</body>
</html>