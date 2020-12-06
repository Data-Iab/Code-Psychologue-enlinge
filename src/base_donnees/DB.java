package base_donnees;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
}
