package web;

import java.io.FileReader;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.opencsv.CSVReader;


import java.util.*;

import base_donnees.DB;

@WebServlet("/NouveauFormulaire")
public class NouveauFormulaire extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		DB connexion_db = new DB();
		List<String> QuestionText = new ArrayList<String>();
		String NouvelleQuestion = request.getParameter("QuestionText");
		String[] AnciennesQuestions = request.getParameterValues("AncienneQuestionText");
		String Psychologue = request.getParameter("Psychologue");
		String action = request.getParameter("Formulaire_envoyer");
		List<String> Destinataires = connexion_db.liste_destinataires();

		String Fichier = request.getParameter("Fichier");
		try (CSVReader reader = new CSVReader(new FileReader(Fichier))) {
			String[] ligneCSV;
			while((ligneCSV = reader.readNext()) != null) {
				QuestionText.add(ligneCSV[0]);
			}

		}catch(Exception e) {;}
		
		
		try {
			for (String q : AnciennesQuestions) {
				QuestionText.add(q);
			}
		} catch (Exception e) {
			;
		}
		
		if ((NouvelleQuestion != "") && (NouvelleQuestion != null)) {
			QuestionText.add(NouvelleQuestion);
		}

		try {
			int element_supprimer = Integer.parseInt(request.getParameter("SupprimerButton"));
			QuestionText.remove(element_supprimer);
		} catch (Exception e) {
			;
		}
		
		
		if((action != null) && (QuestionText.size() > 0)) {
			String Destinatiare = request.getParameter("Destinataire");
			connexion_db.EntrerFormulaireEtQuestions(Psychologue, Destinatiare, QuestionText);
			while(QuestionText.size() > 0) {
				QuestionText.remove(0);
			}
		}
		request.setAttribute("Destinataires", Destinataires);
		request.setAttribute("QuestionText", QuestionText);
		request.setAttribute("psy", Psychologue);
		RequestDispatcher rst = request.getRequestDispatcher("NouveauFormulaire.jsp");
		rst.forward(request, response);
	}
}


