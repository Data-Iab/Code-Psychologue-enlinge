package web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.*;

import base_donnees.DB;


@WebServlet("/NouveauFormulaire")
public class NouveauFormulaire extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DB connexion_db = new DB();
		List<String> Destinataires = connexion_db.liste_destinataires();
		request.setAttribute("Destinataires", Destinataires);
		RequestDispatcher rst = request.getRequestDispatcher("NouveauFormulaire.jsp");
		rst.forward(request, response);
	}

}
