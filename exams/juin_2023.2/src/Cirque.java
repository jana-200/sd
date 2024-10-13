import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class Cirque {
	
	// suivez l'implementation imposee dans l'enonce

	private String[] tableReservations; //table des reservations
	private HashMap<String, HashSet<Integer>> mapEnfants; //cle = enfant, valeur = ensemble de ses places

	/**
	 * initialise un spectacle de cirque
	 * @param nombreTotalPlaces le nombre total de places disponibles
	 * @param tablePrenoms la table avec les prenoms des enfants du village
	 * @throws IllegalArgumentException si un des parametres ne permet pas d'initialiser le spectacle :
	 *         il faut au moins une place
	 *         la table des prenoms ne peut etre null ou vide
	 *         les prenoms ne peuvent etre null ou vides
	 *         il ne peut y avoir des doublons dans la table des prenoms
	 */
	public Cirque(int nombreTotalPlaces, String[] tablePrenoms){
		if(nombreTotalPlaces<1 || tablePrenoms==null || tablePrenoms.length==0)throw new IllegalArgumentException();
		tableReservations=new String[nombreTotalPlaces];
		mapEnfants=new HashMap<>();
		for(String str: tablePrenoms){
			if(str==null || str.equals("") || mapEnfants.containsKey(str))throw new IllegalArgumentException();
			mapEnfants.put(str,new HashSet<>());
		}
	}


	/**
	 * reserve une ou plusieurs places
	 * la reservation reussit si toutes les places demandees sont libres
	 * ATTENTION : PAS de reservation partielle (tout ou rien)
	 * @param prenom le prenom de l'enfant qui demande des places
	 * @param ensemblePlacesDemandees l'ensemble avec les numeros des places demandees
	 * @return true si la reservation a reussi, false sinon
	 * @throws IllegalArgumentException si le prenom est null ou vide
	 * 								 ou si l'enfant n'est pas un enfant du village
	 *                               ou si l'ensemble est null ou vide
	 *                               ou si l'enseble contient un numero de place inexistant
	 */
	public boolean reserver(String prenom, HashSet<Integer> ensemblePlacesDemandees){
		if(prenom==null || prenom.equals("") || ensemblePlacesDemandees==null || ensemblePlacesDemandees.isEmpty()) throw new IllegalArgumentException();
		if(!mapEnfants.containsKey(prenom)) throw new IllegalArgumentException();
		for(Integer entier: ensemblePlacesDemandees){
			if(entier>=tableReservations.length || entier<0) throw new IllegalArgumentException();
			if(tableReservations[entier]!=null)return false;
		}
		for(Integer entier: ensemblePlacesDemandees){
			tableReservations[entier]=prenom;
			mapEnfants.get(prenom).add(entier);
		}
		return true;

	}


	/**
	 * renvoie une table contenant les places reservees par un enfant
	 * cette table doit etre triee selon l'ordre croissant des numeros de place
	 * la table sera de dimension 0, si l'enfant n'a aucune reservation
	 * @param prenom le prenom de l'enfant
	 * @return la table avec les places reservees par un enfant
	 * @throws IllegalArgumentException si le prenom est null ou vide
	 * 								 ou si l'enfant n'est pas un enfant du village
	 */
	public int[] placesReservees (String prenom) {
		if(prenom==null || prenom.equals("") || !mapEnfants.containsKey(prenom)) throw new IllegalArgumentException();

		if(mapEnfants.get(prenom).isEmpty())return new int[0];

		int[] table = new int[mapEnfants.get(prenom).size()];
		int i=0;
		for (int entier:mapEnfants.get(prenom)){
			table[i]=entier;
			i++;
		}
		Arrays.sort(table);
		return table;
		// Pour trier la table, utilisez la methode static sort() de la classe Arrays
	}


	/**
	 * renvoie la table des reservations et le map
	 */
	public String toString(){
		// vous pouvez modifier cette methode comme vous voulez
		// MAIS cette methode ne sera pas evaluee
		// ne perdez pas de temps sur des affichages!
		if(tableReservations ==null)
			return "attention table des reservations est null";
		if(mapEnfants==null)
			return "attention mapEnfants est null";
		return "table des reservations :\n"+Arrays.toString(tableReservations)+"\nmapEnfants :\n"+mapEnfants.toString();
	}
	
}
