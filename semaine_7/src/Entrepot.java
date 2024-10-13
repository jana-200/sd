import javax.net.ssl.SNIHostName;
import java.util.HashSet;
import java.util.HashMap;

/**
 *
 * 													-------> METTEZ ICI VOS NOM ET PRENOM
 *
 *
 */

public class Entrepot {

	Hangar[] tableHangars;
	HashMap<Integer, Societe> mapSociete;
	int nbrHangarsLibres;
	HashSet<String> lesVoitures;

	/**
	 * construit un entrepot contenant nombreHangars
	 * @param nombreHangars le nombre d'hangars
	 * @throws IllegalArgumentException si le nombre d'hangars est negatif ou nul
	 */
	public Entrepot(int nombreHangars) {
		if(nombreHangars<=0)
			throw new IllegalArgumentException();
		tableHangars= new Hangar[nombreHangars];
		mapSociete= new HashMap<>();
		lesVoitures= new HashSet<>();
		nbrHangarsLibres=nombreHangars;

		for (int i = 0; i <nombreHangars ; i++) {
			Hangar hanger= new Hangar(i);
			tableHangars[i]=hanger;
		}
	}

	/**
	 * renvoie le nombre d'hangars libres
	 * @return le nombre d'hangars libres
	 */
	public int nombreHangarsLibres(){
		return nbrHangarsLibres;
	}

	/**
	 * renvoie le nombre de societes presentes
	 * @return le nombre de societes presentes
	 */
	public int nombreSocietesPresentes(){
		return mapSociete.size();
	}

	/**
	 * renvoie la societe dont le numero est passe en parametre
	 * @param numeroSociete le numero de la societe
	 * @return la societe recherchee ou null si aucune societe presente ne possede ce numero
	 */
	public Societe getSociete(int numeroSociete){
		return mapSociete.get(numeroSociete);
	}

	/**
	 * attribue un hangar a la societe passee en parametre
	 * Si l'attribution a pu se faire :
	 * la societe est enregistree comme presente (si pas encore presente)
	 * le hangar lui est ajoute
	 * @param numeroSociete le numero de la societe
	 * @param nomSociete le nom de la societe
	 * @return le numero du hangar attribue ou -1 s'il n'y en a plus de libre
	 */
	public int attribuerHangar(int numeroSociete, String nomSociete) {
		if(nombreHangarsLibres()==0) return -1;
		boolean found=false;
		Societe societe;
		int numHangar=numeroSociete%tableHangars.length;
		for (int i = numHangar; i < tableHangars.length ; i++) {
			if(tableHangars[i].getSociete()==null){
				found=true;
				numHangar=i;
				break;
			}
		}
		if(!found){
			for (int i = 0; i < numHangar; i++) {
				if(tableHangars[i].getSociete()==null){
					numHangar=i;
					break;
				}
			}
		}
		if(getSociete(numeroSociete)==null){
			societe= new Societe(numeroSociete, nomSociete);
			mapSociete.put(numeroSociete,societe);
		}
		else societe=getSociete(numeroSociete);

		tableHangars[numHangar].setSociete(societe);
		societe.ajouterHangar(numHangar);
		nbrHangarsLibres--;
		return numHangar;
	}

	public boolean libererHangar(int numHangar){
		if(numHangar>= tableHangars.length || numHangar<0) return false;
		Societe societe= tableHangars[numHangar].getSociete();
		if(societe==null) return false;
		societe.supprimerHangar(numHangar);
		if(societe.numHangars()==0){
			for(String plaque: societe.lesPlaques()){
				lesVoitures.remove(plaque);
			}
			mapSociete.remove(societe.getNumeroSociete());
		}
		return true;
	}

	public boolean ajouterVoiture(String plaque){
		return lesVoitures.add(plaque);
	}

	public boolean estAutorisee(String numPlaque){
		return lesVoitures.contains(numPlaque);
	}
}
