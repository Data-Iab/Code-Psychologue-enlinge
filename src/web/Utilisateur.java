package web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import base_donnees.DB;

@WebServlet("/utilisateur")
public class Utilisateur extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] reponsesExtraits = request.getParameterValues("reponse");
		String[] idQuestionsExtraits = request.getParameterValues("idQuestion");
		
			int[] idQuestions = new int[idQuestionsExtraits.length];
			int[] reponses = new int[reponsesExtraits.length];
			
			for(int i =0 ;i<idQuestionsExtraits.length;i++) {
				idQuestions[i] = Integer.parseInt(idQuestionsExtraits[i]);
				reponses[i] = Integer.parseInt(reponsesExtraits[i]);

			}
			
			DB db_connexion = new DB();
			db_connexion.updateQuestion(idQuestions, reponses);		
			response.sendRedirect("EnvoieSucces.jsp");
	}

}
