package graphRanger;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeSet;

import javax.naming.ConfigurationException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class StableMariage {

	private List<Ecole> ecoles;
	private List<Eleve> eleves;

	private List<Participant> rejetes;
	static List<Participant> defRejetes = new ArrayList<Participant>();

	public void associerDemandeurDemande(List<? extends Participant> rejetes, List<? extends Participant> demandes) {
		for(Participant rejete : this.rejetes){
			rejete.demander((List<Participant>) demandes);
		}
	}

	public void trierDemandeurs(List<? extends Participant> demandes) {
		this.rejetes = new ArrayList<Participant>();
		for (Participant demande : demandes) {
			demande.trierDemandeurs(this);
		}
	}
	
	public void ajouterRejetes(Participant demandeur) {
		this.rejetes.add(demandeur);
	}

	public void resoudre(boolean elevesDemandent) {

		List<? extends Participant> demandeurs;
		List<? extends Participant> demandes;
		
		if (elevesDemandent) {
			demandeurs = this.eleves;
			demandes = this.ecoles;
		} else {
			demandeurs = this.ecoles;
			demandes = this.eleves;
		}

		this.rejetes = (List<Participant>) demandeurs;
		int nbTours = 1;
		
			while (!this.rejetes.isEmpty()) {
				this.associerDemandeurDemande(this.rejetes, demandes);
				this.afficherResultats(demandes, demandeurs, nbTours++);
				this.trierDemandeurs(demandes);
			}
			if(!StableMariage.defRejetes.isEmpty()) {
				System.out.println("Some students won't have a school due to lack of capacity");
				for(Participant p : StableMariage.defRejetes) {
					System.out.println(p);
				}
			}
			
		
		
		System.out.println("Fin de l'association en " + (nbTours-1) + " tours\n\n");
		
	}

	private void afficherResultats(List<? extends Participant> demandes, List<? extends Participant> demandeurs,
			int nbTours) {
		String affichage = "\nTour n° " + nbTours + "\n\n";
		for (Participant demande : demandes) {
			affichage += demande.getNom() + "\n";
			for (Participant demandeur : demande.getDemandeurs()) {
				affichage += demandeur.getNom();
				if (demande.getDemandeurs().get(demande.getDemandeurs().size()-1) != demandeur) {
					affichage += " - ";
				}
			}
			affichage += "\n\n";
		}
		
		System.out.println(affichage);
	}

	public void afficherTableau() {

		int nbMaxNameLengthOfEcoles = this.getMaxLength(this.ecoles);
		int nbMaxNameLengthOfEleves = this.getMaxLength(this.eleves);

		String affichage = "\nVoici le tableau des préférences\n\n";

		for (int i = 0; i < nbMaxNameLengthOfEleves; i++) {
			affichage += " ";
		}

		for (Participant ecole : this.ecoles) {
			affichage += String.format("%" + nbMaxNameLengthOfEcoles + "s", ecole) + " ";
		}

		affichage += "\n";

		String preference;

		for (Participant eleve : this.eleves) {
			affichage += "\n" + String.format("%" + nbMaxNameLengthOfEleves + "s", eleve);

			for (Participant ecole : this.ecoles) {
				preference = "(" + (eleve.getPreferences().indexOf(ecole) + 1) + ","
						+ (ecole.getPreferences().indexOf(eleve) + 1) + ")";
				affichage += String.format("%" + nbMaxNameLengthOfEcoles + "s", preference) + " ";
			}
			affichage += "\n";
		}

		System.out.println(affichage);
	}

	public void initialiser() throws FileNotFoundException, IOException, ParseException, ConfigurationException {
		
		this.ecoles = new ArrayList<>();
		this.eleves = new ArrayList<>();

		JSONParser parser = new JSONParser();
		Object obj = parser.parse(new FileReader("preferences.json"));
		JSONObject jsonObject = (JSONObject) obj;

		/**
		 * Ajout des élèves
		 */
		JSONObject jsonStudents = (JSONObject) jsonObject.get("students");

		Set<Map.Entry<String, List<String>>> students = jsonStudents.entrySet();

		for (Entry<String, List<String>> entry : students) {
			this.eleves.add(new Eleve(entry.getKey()));
		}

		/**
		 * Ajout des écoles
		 */
		JSONObject jsonSchools = (JSONObject) jsonObject.get("schools");

		Set<Map.Entry<String, Object>> schools = jsonSchools.entrySet();

		long capacite;
		
		for (Entry<String, Object> entry : schools) {
			capacite = (long) ((HashMap) entry.getValue()).get("capacity");
			this.ecoles.add(new Ecole(entry.getKey(), (int) capacite));
		}
		
//		int sum = 0;
//		for(Ecole ecole : this.ecoles) {
//			sum+=ecole.getCapacite();
//		}
//		if(sum < this.eleves.size()) {
//			throw new ConfigurationException("La capacité totale des écoles n'est pas égale au nombre d'élèves");
//		}
		
		/**
		 * Ajout préférences écoles des élèves
		 */
		List<String> ecolesToString;
		int i = 0;
		for (Entry<String, List<String>> entry : students) {
			ecolesToString = entry.getValue();
			for (String s : ecolesToString) {
				for (Ecole ecole : this.ecoles) {
					if (s.equals(ecole.getNom())) {
						this.eleves.get(i).ajouterPreference(ecole);
					}
				}
			}
			i++;
		}
		
		/**
		 * Ajout préférences élèves des écoles
		 */
		List<String> elevesToString;
		i = 0;
		for (Entry<String, Object> entry : schools) {
			elevesToString = (List<String>) ((HashMap) entry.getValue()).get("preferences");
			for (String s : elevesToString) {
				for (Eleve eleve : this.eleves) {
					if (s.equals(eleve.getNom())) {
						this.ecoles.get(i).ajouterPreference(eleve);
					}
				}
			}
			i++;
		}
		//Garbage collector cheat
		jsonObject = null;
		parser = null;
	}
	
	private int getMaxLength(List<? extends Participant> participants) {
		int maxLength = 0;
		String name;

		for (Participant p : participants) {
			name = p.toString();
			if (name.length() > maxLength) {
				maxLength = name.length();
			}
		}

		return maxLength;
	}


}
