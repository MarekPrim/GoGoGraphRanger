package graphRanger;

import java.util.List;

public class Eleve extends Participant {

	public Eleve(String nom) {
		super(nom, 1);
	}

	@Override
	public void demander(List<Participant> demandes) {
		if(this.getPreferences().size()==0) {
			StableMariage.defRejetes.add(this);
			return;
		}
		for (Participant demande : demandes) {
			if (this.getPreferences().get(0) == demande) {
				demande.ajouterDemandeur(this);
			}
		}
	}

}
