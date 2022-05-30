package graphRanger;

import java.util.List;

public class Eleve extends Participant {

	public Eleve(String nom) {
		super(nom, 1);
	}

	@Override
	public void demander(List<Participant> demandes) {
		for (Participant demande : demandes) {
			if (this.getPreferences().get(0) == demande) {
				demande.ajouterDemandeur(this);
			}
		}
	}

}
