package graphRanger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class StableMariage {
	
	public static List<Participant> rejetes = new ArrayList<Participant>();
	

	public static void main(String[] args) {
		
		
		List<Ecole> ecoles = new ArrayList<>();
		List<Eleve> eleves = new ArrayList<>();
		
		ecoles.add(new Ecole("Enseeiht", 3));
		ecoles.add(new Ecole("Insa", 4));
		ecoles.add(new Ecole("A7", 3));
		
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

		
		for (Eleve e : eleves) {
			List<Integer> array = new ArrayList<>();
		    for (int k = 0; k < ecoles.size(); k++) {
		        array.add(k);
		    }
		    Collections.shuffle(array);
		    for (Integer preference : array) {
		    	e.ajouterPreference(ecoles.get(preference));
		    }
		}
		
		for (Ecole ecole : ecoles) {
			List<Integer> array = new ArrayList<>();
		    for (int k = 0; k < eleves.size(); k++) {
		        array.add(k);
		    }
		    Collections.shuffle(array);
		    for (Integer preference : array) {
		    	ecole.ajouterPreference(eleves.get(preference));
		    }
		}
		
		int nbTours = 0;
		
		
		do {
			
			rejetes = new ArrayList<>();
			
//			for (Eleve e : eleves) {
//				System.out.println(e);
//			}
//			
//			for (Ecole ecole : ecoles) {
//				System.out.println(ecole);
//			}
			
			for (Ecole ecole : ecoles) {
				for (Eleve eleve : eleves) {
					if (eleve.getPreferences().get(0) == ecole) {
						ecole.ajouterDemandeur(eleve);
						System.out.println(eleve.getNom() + " a choisi " + ecole.getNom() + " au bout de " + nbTours + " tours");
					}
				}
			}
			
			
			for (Ecole ecole : ecoles) {
				ecole.trierDemandeurs();
			}
//			
//			for (Ecole ecole : ecoles) {
//				ecole.afficherDemandeurs();
//			}
			
			System.out.println(" REJETEES (" + rejetes.size() + ")");
			for (Participant p : rejetes) {
				System.out.println(p.getNom());
			}
			
//			for (Eleve e : eleves) {
//				System.out.println(e);
//			}
			
			nbTours++;
			
		} while(!rejetes.isEmpty());
		
//		Preference[][] preferences = new Preference[eleves.size()][ecoles.size()]; 
//		
//		
//		for (int i = 0; i < eleves.size(); i++) {
//			List<Integer> array = new ArrayList<>();
//		    for (int k = 0; k < ecoles.size(); k++) {
//		        array.add(k);
//		    }
//		    Collections.shuffle(array);
//			 for (int j = 0; j < ecoles.size(); j++) {
//				 preferences[i][j] = new Preference(array.get(0), 0);
//				 array.remove(0);
//			 }
//		 }
//		
//		 for (int i = 0; i < ecoles.size(); i++) {
//			 List<Integer> array = new ArrayList<>();
//			    for (int k = 0; k < eleves.size(); k++) {
//			        array.add(k);
//			    }
//			 Collections.shuffle(array);
//			 for (int j = 0; j < eleves.size(); j++) {
//			    preferences[j][i].setPreferenceEcole(array.get(0));
//		    	array.remove(0);
//			 }
//		 }
//		 
//		 for (int i = 0; i < eleves.size(); i++) {
//			 for (int j = 0; j < ecoles.size(); j++) {
//				 System.out.print(preferences[i][j]);
//				 if (j < ecoles.size()-1) {
//					 System.out.print(" - ");
//				 }
//			 }
//			 System.out.println();
//		 }
		 
		 
		 
	}

}
