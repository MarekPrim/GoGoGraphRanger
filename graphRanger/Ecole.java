package graphRanger;

import java.util.ArrayList;
import java.util.List;

public class Ecole extends Participant {

	public Ecole(String nom, int capacite) {
		super(nom, capacite);
	}


	@Override
	public void demander(ArrayList<Participant> demandes, int nbTours) {
		int i = 0;
		while(i<this.getCapacite()){
			//S'enregistre en tant que demandeur auprès des demandés selon sa préférence
			boolean found = false;
			for (int j = 0;j<demandes.size() && !found ;j++) {
				if (this.getPreferences().get(i) == demandes.get(j)) {
					demandes.get(j).ajouterDemandeur(this);
					found = true;
					System.out.println(this.getNom() + " a choisi " + demandes.get(j).getNom() + " au bout de " + nbTours + " tours");
				}
			}
		i++;
		}
	}

}
