package web;

import java.io.IOException;
import java.util.*;
import javax.servlet.RequestDispatcher;
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
			  List<Question> userquestion = new ArrayList<Question>();
			  connexion_db.question_utilisateur(session, userquestion);
			  request.setAttribute("userquestion", userquestion);
			  request.setAttribute("utilisateur", session.returnNom());
			  RequestDispatcher rst = request.getRequestDispatcher("Utilisateur.jsp");
			  rst.forward(request, response);
		}
		else if(session.returnType().equals("RH")) {
			response.sendRedirect("RH.jsp");
		}
	}
	else {
		String loginfailed = "Identifiants incorrectes";
		request.setAttribute("loginfailed", loginfailed);
		RequestDispatcher rst = request.getRequestDispatcher("login.jsp");
		rst.forward(request, response);
		response.sendRedirect("login.jsp");
	}
	}

}
