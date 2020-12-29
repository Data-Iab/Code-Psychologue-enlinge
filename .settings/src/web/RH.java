package web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import base_donnees.DB;
import loginsession.Formulaire;
import loginsession.Question;

@WebServlet("/RH")
public class RH extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String succes = "Envoyés avec succès ";
		String[] etatExtraits = request.getParameterValues("etat");
		String[] id_formulaireExtraits = request.getParameterValues("id_formulaire");
		int[] id_formulaire = new int[id_formulaireExtraits.length];
		int[] etat = new int[etatExtraits.length];
		for (int i = 0; i < id_formulaireExtraits.length; i++) {
			id_formulaire[i] = Integer.parseInt(id_formulaireExtraits[i]);
			etat[i] = Integer.parseInt(etatExtraits[i]);
		}
		DB db_connexion = new DB();
		db_connexion.updateliste_formulaire(id_formulaire, etat);
		
		List<Formulaire> RH = new ArrayList<Formulaire>();
		db_connexion.liste_formulaire(RH);
		String[] notification = new String[RH.size()];
		
		int i=0;
		
		for(Formulaire f:RH) {
			notification[i] = "red";
			List<Question> userquestion = new ArrayList<Question>();
			db_connexion.question_utilisateur_par_id(f.getuser(),f.returnIdFormulaire(),userquestion);
			if(userquestion.size()==0)
				notification[i] = "green";
			i++;
		
		}
		request.setAttribute("RH", RH);
		request.setAttribute("succes", succes);
		request.setAttribute("notification", notification);
		RequestDispatcher rst = request.getRequestDispatcher("RH.jsp");
		rst.forward(request, response);
	}

}
