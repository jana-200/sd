import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Cirque {
	
	// suivez l'implementation imposee dans l'enonce
	
	private int phase = 1; //est en phase de prevente (phase 1)
	private String[] tablePlaces; //table des reservations
	private int premPlaceAdulte; // permet de delimiter les places enfants et les places adultes
	private int derPlaceEnfantAttribuee; //numero de la derniere place attribuee a 1 enfant
	private ArrayDeque<Integer> filePlacesLibres;
	
	// vous pouvez ajouter des attributs si cela peut vous aider
	
	/**
	 * initialise un spectacle de cirque 
	 * @param nombreTotalPlaces le nombre total de places (enfants+adultes) disponibles
	 * @param nombrePlacesEnfants le nombre de places reservees pour les enfants
	 * @throws IllegalArgumentException si un des nombres ne permet pas d'initialiser le spectacle.                 
	 */
	public Cirque(int nombreTotalPlaces, int nombrePlacesEnfants){
		if(nombreTotalPlaces<=0||nombrePlacesEnfants<0||nombrePlacesEnfants>nombreTotalPlaces)
			throw new IllegalArgumentException();
		premPlaceAdulte=nombrePlacesEnfants;
		derPlaceEnfantAttribuee=0;
		tablePlaces=new String[nombreTotalPlaces];


	}
	
	/**
	 * reserve une ou plusieurs places "enfant"
	 * la reservation reussit s'il reste suffisamment de places "enfant"
	 * ATTENTION : PAS de reservation partielle (tout ou rien)
	 * @param nom le nom de la personne qui demande des places
	 * @param nombrePlaces le nombre de places "enfant" souhaite
	 * @return une liste avec les numeros des places reservees ou null si la reservation a echoue
	 * @throws IllegalArgumentException si le nom est null ou vide ou si le nombre de places est negatif ou nul
	 * @throws IllegalStateException si on n'est pas en phase de prevente (phase 1)
	 */
	public ArrayList<Integer> reserverEnfant(String nom, int nombrePlaces){
		if(nom == null||nom.equals(""))
			throw new IllegalArgumentException();
		if(nombrePlaces<=0)
			throw new IllegalArgumentException();
		if(phase!=1)
			throw new IllegalStateException();
		ArrayList<Integer> places=new ArrayList<>();
		if(nombrePlaces>premPlaceAdulte-derPlaceEnfantAttribuee) return null;
		for (int i = 0; i < nombrePlaces; i++) {
			int place = derPlaceEnfantAttribuee + i;
			tablePlaces[place] = nom;
			places.add(place);
		}
		this.derPlaceEnfantAttribuee+=nombrePlaces;
		return places;
	}
	
	
	/**
	 * reserve une ou plusieurs places "adulte"
	 * la reservation reussit si toutes les places demandees sont des places adultes
	 * et si toutes les places sont libres
	 * ATTENTION : PAS de reservation partielle (tout ou rien)
	 * Precondition : (a ne pas verifier!) la liste ne contient pas de doublon
	 * @param nom le nom de la personne qui demande des places
	 * @param numerosPlaces la liste avec les numeros des places demandees
	 * @return true si la reservation a reussi, false sinon
	 * @throws IllegalArgumentException si le nom est null ou vide ou si la liste est null ou vide
	 * @throws IllegalStateException si on n'est pas en phase de prevente (phase 1) 
	 */
	public boolean reserverAdulte(String nom, ArrayList<Integer> numerosPlaces){
	
		if(nom == null||nom.equals(""))
			throw new IllegalArgumentException();
		if(numerosPlaces==null||numerosPlaces.size()==0)
			throw new IllegalArgumentException();
		if(phase!=1)
			throw new IllegalStateException();
		for(Integer i:numerosPlaces){
			if(tablePlaces[i]!=null)
				return false;
			if(i<premPlaceAdulte)return false;
		}
		for(Integer i:numerosPlaces){
			tablePlaces[i]=nom;
		}

		return true;
	}


	/**
	 * a comme effet de passer de la phase 1 a la phase 2
	 * si deja en phase 2, rien ne doit etre fait
	 */
	public void changerPhase(){
		if(phase==1){
			filePlacesLibres=new ArrayDeque<>();
		for (int i = 0; i <tablePlaces.length ; i++) {
			if(tablePlaces[i]==null)
				filePlacesLibres.push(i);
		}
		phase=2;
		}


	}



	/**
	 * attribue automatiquement une place libre
	 * @param nom le nom de la personne qui demande une place
	 * @return le numero de la place attribuee ou -1 si plus de place libre
	 * @throws IllegalArgumentException si le nom est null ou vide 
	 * @throws IllegalStateException si on n'est pas en phase 2
	 */
	public int attribuerAutomatiquementPlace(String nom){
		changerPhase();
		if(nom == null||nom.equals(""))
			throw new IllegalArgumentException();
		if(phase!=2)
			throw new IllegalStateException();

		if(filePlacesLibres.size()==0) return -1;
		int num= filePlacesLibres.removeLast();
		tablePlaces[num]=nom;
		return num;
	}
	
	
	// Va servir pour debugger
	/**
	 * renvoie, sous forme d'une chaine de caracteres, la table des places
	 */
	public String toString(){
		return Arrays.toString(tablePlaces);
		// vous pouvez modifier cette methode comme vous voulez
		// MAIS cette methode ne sera pas evaluee
		// ne perdez pas de temps sur des affichages!
	}
	
	
	
	
	
	

	
}
