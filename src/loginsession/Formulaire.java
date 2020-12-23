package loginsession;

public class Formulaire {
	public int id_formulaire;
	public String nom;
	public String psychologue;
	public boolean etat;
	
	public String getuser() {
		return nom;}
		
	public void setuser(String nom) {
	        this.nom = nom;
	    }
	public String getpsy() {
	        return psychologue;
	    }
	 
	 public void setpsy(String psychologue) {
	        this.psychologue = psychologue;
	    }
    public boolean returnetat() {
			return etat;
		}
    public void affectetat(boolean etat) {
			this.etat =etat;
		}
	public void affectIdFormulaire(int id_formulaire) {
        this.id_formulaire = id_formulaire;
    }
    
	public int returnIdFormulaire() {
        return id_formulaire;
    }

}