import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class StableMariage {

	public static void main(String[] args) {

		List<Ecole> ecoles = new ArrayList<>();
		List<Eleve> eleves = new ArrayList<>();
		
		ecoles.add(new Ecole("Enseeiht", 3));
		ecoles.add(new Ecole("Insa", 4));
		ecoles.add(new Ecole("Insa", 3));
		
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
		
		Preference[][] preferences = new Preference[eleves.size()][ecoles.size()]; 
		
		
		for (int i = 0; i < eleves.size(); i++) {
			List<Integer> array = new ArrayList<>();
		    for (int k = 0; k < ecoles.size(); k++) {
		        array.add(k);
		    }
		    Collections.shuffle(array);
			 for (int j = 0; j < ecoles.size(); j++) {
				 preferences[i][j] = new Preference(array.get(0), 0);
				 array.remove(0);
			 }
		 }
		
		 for (int i = 0; i < ecoles.size(); i++) {
			 List<Integer> array = new ArrayList<>();
			    for (int k = 0; k < eleves.size(); k++) {
			        array.add(k);
			    }
			 Collections.shuffle(array);
			 for (int j = 0; j < eleves.size(); j++) {
			    preferences[j][i].setPreferenceEcole(array.get(0));
		    	array.remove(0);
			 }
		 }
		 
		 for (int i = 0; i < eleves.size(); i++) {
			 for (int j = 0; j < ecoles.size(); j++) {
				 System.out.print(preferences[i][j] + " - ");
			 }
			 System.out.println();
		 }
	}

	//Condition de fin : TOutes les écoles sont remplies

}