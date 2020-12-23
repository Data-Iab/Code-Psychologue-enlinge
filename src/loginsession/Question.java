package loginsession;

public class Question {
	public int id_question;
	public int id_formulaire;
	public String questiontext;
	public boolean reponse;
	
	public void affectIdQuestion(int id_question) {
		this.id_question =id_question;
	}
	public int returnIdQuestion() {
		return id_question;
	}
	public void affectIdFormulaire(int id_formulaire) {
		this.id_formulaire =id_formulaire;
	}
	public int returnIdformulaire() {
		return id_formulaire;
	}
	public String returnQuestion() {
		return questiontext;
	}
	public void affectQuestion(String questiontext) {
		this.questiontext = questiontext;
	}
	public boolean returnReponse() {
		return reponse;
	}
	public void affectReponse(boolean reponse) {
		this.reponse =reponse;
	}
	
}
