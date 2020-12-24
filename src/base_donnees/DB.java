package base_donnees;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Statement;
import loginsession.*;

public class DB {
	private String dbUrl = "jdbc:mysql://localhost:3306/userdb?useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	private String dbUname = "root";
	private String dbPassword = "0000";
	private String dbDriver = "com.mysql.cj.jdbc.Driver";

	public void loadDriver(String dbDriver) {
		try {
			Class.forName(dbDriver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public Connection getConnection() {
		Connection con = null;
		try {
			con = DriverManager.getConnection(dbUrl, dbUname, dbPassword);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}

	public boolean valider_donees(Session session) {
		boolean status = false;

		loadDriver(dbDriver);
		Connection con = getConnection();
		String sql = "select * from login where nom =? and mot_de_passe =?";
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, session.returnNom());
			ps.setString(2, session.returnPasse());
			ResultSet rs = ps.executeQuery();
			status = rs.next();
			session.affectType(rs.getString("typeuser"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return status;
	}

	public void question_utilisateur(String nomUtilisateur, List<Question> userquestion) {
		boolean status;

		loadDriver(dbDriver);
		Connection con = getConnection();
		String sql = "SELECT * FROM userdb.questions q INNER JOIN userdb.formulaires f ON f.id_formulaire=q.id_formulaire WHERE f.nom=? AND f.etat=1 AND q.reponse is NULL";
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, nomUtilisateur);
			ResultSet rs = ps.executeQuery();
			status = rs.next();
			while (status) {
				Question question = new Question();
				question.affectIdQuestion(rs.getInt("id_question"));
				question.affectIdFormulaire(rs.getInt("id_formulaire"));
				question.affectReponse(rs.getBoolean("reponse"));
				question.affectQuestion(rs.getString("question"));

				userquestion.add(question);
				status = rs.next();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void question_utilisateur_psychologue(String nomUtilisateur, List<Question> userquestion) {
		boolean status;

		loadDriver(dbDriver);
		Connection con = getConnection();
		String sql = "SELECT * FROM userdb.questions q INNER JOIN userdb.formulaires f ON f.id_formulaire=q.id_formulaire WHERE f.nom=?";
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, nomUtilisateur);
			ResultSet rs = ps.executeQuery();
			status = rs.next();
			while (status) {
				Question question = new Question();
				question.affectIdQuestion(rs.getInt("id_question"));
				question.affectIdFormulaire(rs.getInt("id_formulaire"));
				question.affectReponse(rs.getBoolean("reponse"));
				question.affectQuestion(rs.getString("question"));
				question.affectEtatQuestion(rs.getString("reponse"));
				userquestion.add(question);
				status = rs.next();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateQuestion(int[] idQuestions, int[] reponses) {
		loadDriver(dbDriver);
		Connection con = getConnection();
		String sql = "UPDATE `userdb`.`questions` SET `reponse` = ? WHERE (`id_question` = ?);";
		PreparedStatement ps;
		try {
			for (int j = 0; j < idQuestions.length; j++) {
				ps = con.prepareStatement(sql);
				ps.setInt(1, reponses[j]);
				ps.setInt(2, idQuestions[j]);
				ps.executeUpdate();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void liste_formulaire(List<Formulaire> RH) {
		Statement statement = null;
		ResultSet resultat = null;

		loadDriver(dbDriver);
		Connection connexion = getConnection();
		try {
			statement = connexion.createStatement();
			resultat = statement.executeQuery("SELECT * FROM userdb.formulaires;");
			while (resultat.next()) {
				String user = resultat.getString("nom");
				String psy = resultat.getString("psychologue");
				int id_formulaire = resultat.getInt("id_formulaire");
				boolean etat = resultat.getBoolean("etat");
				Formulaire formulaire = new Formulaire();
				formulaire.setuser(user);
				formulaire.affectetat(etat);
				formulaire.setpsy(psy);
				formulaire.affectIdFormulaire(id_formulaire);
				RH.add(formulaire);
				System.out.println(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void updateliste_formulaire(int[] id_formulaire, int[] etat) {
		loadDriver(dbDriver);
		Connection con = getConnection();
		String sql = "UPDATE `userdb`.`formulaires`  SET `etat` = ? WHERE (`id_formulaire` = ?);";
		PreparedStatement ps;
		try {
			for (int j = 0; j < id_formulaire.length; j++) {
				ps = con.prepareStatement(sql);
				ps.setInt(1, etat[j]);
				ps.setInt(2, id_formulaire[j]);
				ps.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void liste_formulaire_psychologue(String nom, List<Formulaire> formulaires) {

		loadDriver(dbDriver);
		PreparedStatement ps;
		Connection con = getConnection();
		String sql = "SELECT * FROM userdb.formulaires f where f.psychologue = ?";

		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, nom);
			ResultSet resultat = ps.executeQuery();
			while (resultat.next()) {
				String user = resultat.getString("nom");
				String psy = resultat.getString("psychologue");
				boolean etat = resultat.getBoolean("etat");
				int id_formulaire = resultat.getInt("id_formulaire");
				Formulaire formulaire = new Formulaire();
				formulaire.setuser(user);
				formulaire.affectetat(etat);
				formulaire.setpsy(psy);
				formulaire.affectIdFormulaire(id_formulaire);
				formulaires.add(formulaire);
			}
		} catch (SQLException e) {
			e.printStackTrace();

		}
	}

	public List<String> liste_destinataires() {
		List<String> liste = new ArrayList<String>();
		loadDriver(dbDriver);
		PreparedStatement ps;
		Connection con = getConnection();
		String sql = "SELECT * FROM userdb.login l Where l.typeuser = 'Utilisateur'";
		try {
			ps = con.prepareStatement(sql);
			ResultSet rst = ps.executeQuery();
			while (rst.next()) {
				String user = rst.getString("nom");
				liste.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return liste;
	}

	public void EntrerFormulaireEtQuestions(String Psychologue, String Utilisateur, List<String> QuestionText) {
		loadDriver(dbDriver);
		Connection con = getConnection();
		String sqlFormulaire = "INSERT INTO `userdb`.`formulaires` (`id_formulaire`, `nom`, `psychologue`, `etat`) VALUES (?, ?, ?, '0')";
		String maxIdFormulaire = "SELECT max(userdb.formulaires.id_formulaire) AS 'maxIdFormulaire' FROM userdb.formulaires";
		String sqlQuestion = "INSERT INTO `userdb`.`questions` (`id_question`, `id_formulaire`, `question`) VALUES (?, ?, ?)";
		String maxIdQuestion = "SELECT max(userdb.questions.id_question) AS 'maxIdQuetion' FROM userdb.questions";
		int IdFormulaire = 0;
		int IdQuestion = 0;
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(maxIdFormulaire);
			ResultSet resultat = ps.executeQuery();
			if (resultat.next())
				IdFormulaire = resultat.getInt("maxIdFormulaire") + 1;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		try {
			ps = con.prepareStatement(maxIdQuestion);
			ResultSet resultat = ps.executeQuery();
			if (resultat.next())
				IdQuestion = resultat.getInt("maxIdQuetion") + 1;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		try {
			ps = con.prepareStatement(sqlFormulaire);
			ps.setInt(1, IdFormulaire);
			ps.setString(2, Utilisateur);
			ps.setString(3, Psychologue);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		try {
			int i = 0;
			for (String question : QuestionText) {
				ps = con.prepareStatement(sqlQuestion);
				ps.setInt(1, IdQuestion + i);
				ps.setInt(2, IdFormulaire);
				ps.setString(3, question);
				ps.executeUpdate();
				i++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
