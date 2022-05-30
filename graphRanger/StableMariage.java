package graphRanger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class StableMariage {
	
	public static List<Participant> rejetes = new ArrayList<Participant>();
	
	public void creerPreferences(List<? extends Participant> demandeurs, List<? extends Participant> preferences) {
		for (Participant e : demandeurs) {
			List<Integer> ordrePreferences = new ArrayList<>();
		    for (int k = 0; k < preferences.size(); k++) {
		        ordrePreferences.add(k);
		    }
		    Collections.shuffle(ordrePreferences);
		    for (Integer preference : ordrePreferences) {
		    	e.ajouterPreference(preferences.get(preference));
		    }
		}
	}
	
	public void associerDemandeurDemande(List<? extends Participant> demandeurs, List<? extends Participant> demandes, int nbTours) {
		for(Participant demandeur : demandeurs){
			demandeur.demander((ArrayList<Participant>) demandes, nbTours);
		}
	}
	
	public void trierDemandeurs(List<? extends Participant> demandes) {
		for (Participant demande : demandes) {
			demande.trierDemandeurs();
		}
	}
	
	public void resoudre(List<? extends Participant> demandeurs, List<? extends Participant> demandes) {
		
		this.creerPreferences(demandeurs, demandes);
		this.creerPreferences(demandes, demandeurs);
		
		int nbTours = 0;
		
		
		do {
			
			rejetes = new ArrayList<>();
			
			this.associerDemandeurDemande(demandeurs, demandes, nbTours);
			this.trierDemandeurs(demandes);
			
			System.out.println(" REJETEES (" + rejetes.size() + ")");
			for (Participant p : rejetes) {
				System.out.println(p.getNom());
			}
			
			
			
			nbTours++;
			
		} while(!rejetes.isEmpty());
	}

	public static void main(String[] args) {
		
		StableMariage s = new StableMariage();
		
		
		ArrayList<Ecole> ecoles = new ArrayList<>();
		ArrayList<Eleve> eleves = new ArrayList<>();
		
		ecoles.add(new Ecole("Enseeiht", 6));
		ecoles.add(new Ecole("Insa", 8));
		ecoles.add(new Ecole("A7", 6));
		
		eleves.add(new Eleve("Kylian"));
		eleves.add(new Eleve("Keke"));
		eleves.add(new Eleve("Johan"));
		eleves.add(new Eleve("Jordan"));
		eleves.add(new Eleve("Mathias"));
		eleves.add(new Eleve("Christophe"));
		eleves.add(new Eleve("Jonathan"));
		eleves.add(new Eleve("Julien"));
		eleves.add(new Eleve("Emrick"));
		eleves.add(new Eleve("Damien"));
		
		eleves.add(new Eleve("A"));
		eleves.add(new Eleve("B"));
		eleves.add(new Eleve("C"));
		eleves.add(new Eleve("D"));
		eleves.add(new Eleve("E"));
		eleves.add(new Eleve("F"));
		eleves.add(new Eleve("G"));
		eleves.add(new Eleve("H"));
		eleves.add(new Eleve("I"));
		eleves.add(new Eleve("J"));

		s.resoudre(ecoles, eleves);
		 	 
	}

}
