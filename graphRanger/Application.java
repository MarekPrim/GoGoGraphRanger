package graphRanger;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import javax.naming.ConfigurationException;

import org.json.simple.parser.ParseException;

public class Application {

	public static void main(String[] args) throws FileNotFoundException, IOException, ParseException, ConfigurationException {
		StableMariage s = new StableMariage();
		
		Scanner sc = new Scanner(System.in);
		int choix;
		boolean sens;
		
		do {
			System.out.println("Faites votre choix");
			System.out.println("1 : Les élèves demandent");
			System.out.println("2 : Les écoles demandent");
			System.out.println("Autre nombre : Quitter");
			choix = sc.nextInt();
			
			if (choix == 1 || choix == 2) {
				s.initialiser();
				s.afficherTableau();
				
				s.resoudre(choix == 1);
			}
			
		} while (choix == 1 || choix == 2);
		sc.close();
		System.exit(0);
	}

}
