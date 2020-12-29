package web;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.opencsv.CSVReader;

import base_donnees.DB;

@WebServlet("/Recommendation")
public class Recommendation extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		DB connexion_db = new DB();
		List<String> RecommendationText = new ArrayList<String>();
		String NouvelleRecommendation = request.getParameter("RecommendationText");
		String[] AnciennesRecommendations = request.getParameterValues("AnciennesRecommendationsText");
		String Psychologue = request.getParameter("Psychologue");
		String action = request.getParameter("Recommendations_envoyer");
		List<String> Destinataires = connexion_db.liste_destinataires();

		String Fichier = request.getParameter("Fichier");
		try (CSVReader reader = new CSVReader(new FileReader(Fichier))) {
			String[] ligneCSV;
			while((ligneCSV = reader.readNext()) != null) {
				RecommendationText.add(ligneCSV[0]);
			}

		}catch(Exception e) {;}
		
		
		try {
			for (String q : AnciennesRecommendations) {
				RecommendationText.add(q);
			}
		} catch (Exception e) {
			;
		}
		
		if ((NouvelleRecommendation != "") && (NouvelleRecommendation != null)) {
			RecommendationText.add(NouvelleRecommendation);
		}

		try {
			int element_supprimer = Integer.parseInt(request.getParameter("SupprimerButton"));
			RecommendationText.remove(element_supprimer);
		} catch (Exception e) {
			;
		}
		
		
		if((action != null) && (RecommendationText.size() > 0)) {
			String Destinatiare = request.getParameter("Destinataire");
			connexion_db.EntrerRecommendation(Destinatiare, RecommendationText);
			while(RecommendationText.size() > 0) {
				RecommendationText.remove(0);
			}
		}
		request.setAttribute("Destinataires", Destinataires);
		request.setAttribute("RecommendationText", RecommendationText);
		request.setAttribute("psy", Psychologue);
		RequestDispatcher rst = request.getRequestDispatcher("Recommendation.jsp");
		rst.forward(request, response);
	}
}
