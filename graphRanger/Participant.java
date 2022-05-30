package graphRanger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Participant {

	private String nom;
	private int capacite;
	
	private List<Participant> demandeurs;
	private List<Participant> preferences;
	
	
	
	public Participant(String nom, int capacite) {
		this.nom = nom;
		this.capacite = capacite;
		this.demandeurs = new ArrayList<>();
		this.preferences = new ArrayList<>();
	}
	
	public void ajouterDemandeur(Participant p) {
		if (!this.demandeurs.contains(p))
			this.demandeurs.add(p);
	}
	
	public void supprimerDemandeur(Participant p) {
		this.demandeurs.remove(p);
	}
	
	public void ajouterPreference(Participant p) {
		this.preferences.add(p);
	}
	
	public void supprimerPreference(Participant p) {
		this.preferences.remove(p);
	}

	public int getCapacite(){
		return this.capacite;
	}

	public void trierDemandeurs() {
		
		List<Participant> choisis = new ArrayList<>();
		int i = 0;
		for (Participant p : this.preferences) {
			if (i < this.capacite && this.demandeurs.contains(p)) {
				choisis.add(p);
				i++;
			}
		}
		
		for (Participant demandeur : this.demandeurs) {
			if (!choisis.contains(demandeur)) {
				StableMariage.rejetes.add(demandeur);
				demandeur.supprimerPreference(this);
			}
		}
		
		this.demandeurs = choisis;
	}
	
	public String getNom() {
		return this.nom;
	}

	public List<Participant> getDemandeurs() {
		return demandeurs;
	}

	public List<Participant> getPreferences() {
		return preferences;
	}
	
	
	public void afficherDemandeurs() {
		System.out.println(this.nom);
		for (Participant p : this.demandeurs) {
			System.out.print(p.nom + " - ");
		}
		System.out.println("\n");
	}

	public abstract void demander(ArrayList<Participant> demandes, int nbTours);

	@Override
	public String toString() {
		String retour = "";
		retour += "Nom : " + this.nom + "\nPreferences : ";
		
		for (Participant p  : this.preferences) {
			retour += p.nom + " - ";
		}
		
		retour += "\nDemandeurs : ";
		
		for (Participant p  : this.demandeurs) {
			retour += p.nom + " - ";
		}
		
		retour += "\n";
		
		return retour;
	}
	
	
}
