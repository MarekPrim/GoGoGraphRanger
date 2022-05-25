public class Preference {

	private int numeroPreferenceEtudiant;
	private int numeroPreferenceEcole;
	
	public Preference (int prefEtudiant, int prefEcole) {
		this.numeroPreferenceEtudiant = prefEtudiant;
		this.numeroPreferenceEcole = prefEcole;
	}
	
	public void setPreferenceEcole(int numEleve) {
		this.numeroPreferenceEcole = numEleve;
	}

	@Override
	public String toString() {
		return "[" + numeroPreferenceEtudiant + " - "
				+ numeroPreferenceEcole + "]";
	}
	
}