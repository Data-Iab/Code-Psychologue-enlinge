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

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Formulaire> formulaires = new ArrayList<Formulaire>();
		List<Question> userquestion = new ArrayList<Question>();
		DB connexion_db = new DB();
		Session session = new Session();

		session.affecteNom(request.getParameter("nom"));

		connexion_db.liste_formulaire_psychologue(session.returnNom(), formulaires);
		connexion_db.question_utilisateur_psychologue(session.returnNom(), userquestion);
		request.setAttribute("psy", session.returnNom());
		request.setAttribute("userquestion", userquestion);
		request.setAttribute("formulaires", formulaires);
		RequestDispatcher rst = request.getRequestDispatcher("Psychologue.jsp");
		rst.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("ssss");
		String nom = request.getParameter("nom");
		String passe = request.getParameter("mot de passe");

		Session session = new Session();
		session.affecteNom(nom);
		session.affectePasse(passe);
		DB connexion_db = new DB();
		if (connexion_db.valider_donees(session)) {
			if (session.returnType().equals("Psychologue")) {
				doGet(request, response);

			} else if (session.returnType().equals("Utilisateur")) {
				List<Question> userquestion = new ArrayList<Question>();
				List<String> recommendations = new ArrayList<String>();
				connexion_db.recommendations_utilisateur(session.returnNom(), recommendations);
				connexion_db.question_utilisateur(session.returnNom(), userquestion);
				
				if ((userquestion.size() == 0) &&  (recommendations.size() == 0))
					response.sendRedirect("noQuestions.jsp");
				else {
					request.setAttribute("recommendations", recommendations);
					request.setAttribute("userquestion", userquestion);
					request.setAttribute("utilisateur", session.returnNom());
					RequestDispatcher rst = request.getRequestDispatcher("Utilisateur.jsp");
					rst.forward(request, response);
				}
			} else if (session.returnType().equals("RH")) {
				List<Formulaire> RH = new ArrayList<Formulaire>();
				connexion_db.liste_formulaire(RH);
				String[] notification = new String[RH.size()];
				
				int i=0;
				
				for(Formulaire f:RH) {
					notification[i] = "red";
					List<Question> userquestion = new ArrayList<Question>();
					connexion_db.question_utilisateur_par_id(f.getuser(),f.returnIdFormulaire(),userquestion);
					if(userquestion.size()==0)
						notification[i] = "green";
					i++;
				
				}
				
				if (RH.size() == 0)
					response.sendRedirect("noFormulaire.jsp");
				else {
					request.setAttribute("RH", RH);
					request.setAttribute("notification", notification);
					RequestDispatcher rst = request.getRequestDispatcher("RH.jsp");
					rst.forward(request, response);
				}
			}
		} else {
			String loginfailed = "Identifiants incorrectes";
			request.setAttribute("loginfailed", loginfailed);
			RequestDispatcher rst = request.getRequestDispatcher("login.jsp");
			rst.forward(request, response);
		}
	}
}
