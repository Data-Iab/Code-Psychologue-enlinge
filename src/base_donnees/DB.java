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
	public void loadDriver(String dbDriver)
	{
		try {
			Class.forName(dbDriver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	public Connection getConnection()
	{
		Connection con = null;
		try {
			con = DriverManager.getConnection(dbUrl, dbUname, dbPassword);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}
	public boolean valider_donees(Session session)
	{
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
	
	
	
	
	
	
	public void question_utilisateur(String nomUtilisateur, List<Question> userquestion)
	{
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
			while(status) {
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
	
	public void question_utilisateur_psychologue(String nomUtilisateur, List<Question> userquestion)
	{
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
			while(status) {
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
	
	
	
	
	public void updateQuestion(int[] idQuestions, int[] reponses)
	{
		loadDriver(dbDriver);
		Connection con = getConnection();
		String sql = "UPDATE `userdb`.`questions` SET `reponse` = ? WHERE (`id_question` = ?);";
		PreparedStatement ps;
		try {
			for(int j = 0 ; j < idQuestions.length ; j++) {
				ps = con.prepareStatement(sql);
				ps.setInt(1, reponses[j]);
				ps.setInt(2, idQuestions[j]);
				ps.executeUpdate();
			}
			
			} catch (SQLException e) {
				e.printStackTrace();
		}
	}
	
	
	public void liste_formulaire(Session session, List<Formulaire> RH) {
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
                boolean etat = resultat.getBoolean("etat");
                int id_formulaire = resultat.getInt("id_formulaire");
                Formulaire formulaire = new Formulaire();
                formulaire.setuser(user);
                formulaire.affectetat(etat);
                formulaire.setpsy(psy);
                formulaire.affectIdFormulaire(id_formulaire);
                RH.add(formulaire);
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
            ps.setString(1,nom);
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
        	while(rst.next()) {
        		String user = rst.getString("nom");
        		liste.add(user);
        	}
        }catch(SQLException e) {
        	e.printStackTrace();
        }
        
		return liste;
	}
	
	
	
	
	
	
	
	
	
	
	
}
