package graphRanger;

import java.util.ArrayList;

public class Eleve extends Participant {

	public Eleve(String nom) {
		super(nom, 1);
	}

	@Override
	public void demander(ArrayList<Participant> demandes, int nbTours) {
		for (Participant demande : demandes) {
				if (this.getPreferences().get(0) == demande) {
					demande.ajouterDemandeur(this);
					System.out.println(this.getNom() + " a choisi " + demande.getNom() + " au bout de " + nbTours + " tours");
				}
		}
	}
	
}
