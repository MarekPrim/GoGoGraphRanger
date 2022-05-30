package graphRanger;

import java.util.List;

public class Ecole extends Participant {

	public Ecole(String nom, int capacite) {
		super(nom, capacite);
	}

	@Override
	public void demander(List<Participant> demandes) {
		int i = 0;
		while (i < this.getCapacite()) {
			// S'enregistre en tant que demandeur auprès des demandés selon sa préférence
			boolean found = false;
			for (int j = 0; j < demandes.size() && !found; j++) {
				if (this.getPreferences().get(i) == demandes.get(j)) {
					demandes.get(j).ajouterDemandeur(this);
					found = true;
				}
			}
			i++;
		}
	}
	
	@Override
	public String toString() {
		return super.toString() + " (" + this.capacite + ")";
	}
}
