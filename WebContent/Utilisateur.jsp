<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Cities</title>
    </head>
    <body>
        <h2>Questions</h2>
        
        <table>
            <thead>
                <tr>
                    <th>question</th>
                    <th>reponse</th>
                </tr>
            </thead>
            
            <tbody>
                <c:forEach items="${userquestion}" var="question">
                <tr>
                    <td><c:out value="${question.returnQuestion()}" /></td>
                    <td><c:out value="${question.returnReponse()}" /></td>
                </tr>
                </c:forEach>   
            </tbody>
        </table>
    </body>
</html>