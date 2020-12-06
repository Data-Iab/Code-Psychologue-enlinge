package web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import base_donnees.*;
import loginsession.*;


@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nom = request.getParameter("nom");
		String passe = request.getParameter("mot de passe");
		
	Session session = new Session();
	session.affecteNom(nom);
	session.affectePasse(passe);
	DB connexion_db = new DB();
	if(connexion_db.valider_donees(session)) {
		if(session.returnType().equals("Psychologue")) {
			response.sendRedirect("Pyschologue.jsp");
		}
		else if(session.returnType().equals("Utilisateur")) {
			response.sendRedirect("Utilisateur.jsp");
		}
		else if(session.returnType().equals("RH")) {
			response.sendRedirect("RH.jsp");
		}
	}
	else {
		response.sendRedirect("login.jsp");
	}
	}

}
