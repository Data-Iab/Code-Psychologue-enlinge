package loginsession;

public class Session {
	private String nom;
	private String passe;
	private String typeUser;
	
	public void affectType(String typeUser) {
		this.typeUser = typeUser;
	}
	public String returnType() {
		return typeUser;
	}
	public String returnNom() {
		return nom;
	}
	public void affecteNom(String nom) {
		this.nom = nom;
	}
	public String returnPasse() {
		return passe;
	}
	public void affectePasse(String passe) {
		this.passe = passe;
	}
}
