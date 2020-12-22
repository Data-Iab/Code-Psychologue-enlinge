<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Formulaire</title>
    </head>
    <body>
        <h2>Formulaire</h2>
        
        <table>
            <thead>
                <tr>
                    <th>nom</th>
                    <th>psychiatre</th>
                    <th>état</th>
                </tr>
            </thead>
            
            <tbody>
                <c:forEach items="${RH}" var="formulaire">
                <tr>
                    <td><c:out value="${formulaire.getuser()}" /></td>
                    <td><c:out value="${formulaire.getpsy()}" /></td>
                    <td><c:out value="${formulaire.returnetat()}" /></td>      
                </tr>
                </c:forEach>   
            </tbody>
        </table>
    </body>
</html>