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
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String nom = request.getParameter("nom");
		String passe = request.getParameter("mot de passe");

		Session session = new Session();
		session.affecteNom(nom);
		session.affectePasse(passe);
		DB connexion_db = new DB();
		if (connexion_db.valider_donees(session)) {
			if (session.returnType().equals("Psychologue")) {
				List<Formulaire> formulaires = new ArrayList<Formulaire>();
				List<Question> userquestion = new ArrayList<Question>();
				connexion_db.liste_formulaire_psychologue(session.returnNom(), formulaires);
				for (Formulaire f : formulaires) {
					connexion_db.question_utilisateur_psychologue(f.getuser(), userquestion);
				}
				request.setAttribute("psy", session.returnNom());
				request.setAttribute("userquestion", userquestion);
				request.setAttribute("formulaires", formulaires);
				RequestDispatcher rst = request.getRequestDispatcher("Psychologue.jsp");
				rst.forward(request, response);

			} else if (session.returnType().equals("Utilisateur")) {
				List<Question> userquestion = new ArrayList<Question>();
				connexion_db.question_utilisateur(session.returnNom(), userquestion);
				if (userquestion.size() == 0)
					response.sendRedirect("noQuestions.jsp");
				else {
					request.setAttribute("userquestion", userquestion);
					request.setAttribute("utilisateur", session.returnNom());
					RequestDispatcher rst = request.getRequestDispatcher("Utilisateur.jsp");
					rst.forward(request, response);
				}
			} 
			
			
			else if (session.returnType().equals("RH")) {
				List<Formulaire> RH = new ArrayList<Formulaire>();
				connexion_db.liste_formulaire(RH);
				if (RH.size() == 0)
					response.sendRedirect("noFormulaire.jsp");
				else {
					request.setAttribute("RH", RH);
					RequestDispatcher rst = request.getRequestDispatcher("RH.jsp");
					rst.forward(request, response);
				}

			}
		} else {
			String loginfailed = "Identifiants incorrectes";
			request.setAttribute("loginfailed", loginfailed);
			RequestDispatcher rst = request.getRequestDispatcher("login.jsp");
			rst.forward(request, response);
			response.sendRedirect("login.jsp");
		}
	}

}
