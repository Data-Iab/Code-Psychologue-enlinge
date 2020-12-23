package web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import base_donnees.DB;

@WebServlet("/RH")
public class RH extends HttpServlet {
	
	
protected void doPost(HttpServletRequest request,  HttpServletResponse etats) throws ServletException, IOException {

	
String[] etatExtraits = request.getParameterValues("etat");

String[] id_formulaireExtraits = request.getParameterValues("id_formulaire");

int[] id_formulaire = new int[id_formulaireExtraits.length];

int[] etat = new int[etatExtraits.length];

for(int i =0 ;i<id_formulaireExtraits.length;i++) {
    id_formulaire[i] = Integer.parseInt(id_formulaireExtraits[i]);
    etat[i] = Integer.parseInt(etatExtraits[i]);
   }
DB db_connexion = new DB();

db_connexion.updateliste_formulaire(id_formulaire, etat);	

etats.sendRedirect("EnvoieSucces.jsp");
}

}
