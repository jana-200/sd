
import java.util.Arrays;
import java.util.HashSet;

public class Parking {

	HashSet<Voiture> ensembleVoitures;

	// construit un ensembleVoitures vide
	public Parking(){
		ensembleVoitures= new HashSet<>();
	}

	/**
	 * ajoute la voiture dans l ensemble des voitures autorisees
	 * @param voiture la voiture autorisee
	 * @return true si la voiture etait pas encore presente, false sinon
	 */
	public boolean ajouterVoiture(Voiture voiture){
		if(ensembleVoitures.contains(voiture)) return false;
		ensembleVoitures.add(voiture);
		return true;

	}

	
	/**
	 * verifie si la voiture est presente dans l ensemble des voitures autorisees
	 * @param voiture la voiture a verifier
	 * @return true si la voiture est presente, false sinon
	 */
	public boolean voitureAutorisee(Voiture voiture){	
		return ensembleVoitures.contains(voiture);

	}

	/**
	 * retire la voiture de l ensemble des voitures autorisees
	 * @param voiture la voiture non autorisee
	 * @return true si la voiture etait presente, false sinon
	 */
	public boolean retirerVoiture(Voiture voiture){
		if(!ensembleVoitures.contains(voiture)) return false;
		ensembleVoitures.remove(voiture);
		return true;
	}


	/**
	 * remplit une table avec les plaques des voitures autorisees
	 * cette table doit etre triee par ordre alphabetique
	 * @return une table avec les plaques de voitures autorisees
	 */
	public String[] tableTrieePlaques(){
		String[] str= new String[ensembleVoitures.size()];
		int j=0;
		for(Voiture voiture: ensembleVoitures){
			str[j]= voiture.getNumPlaque();
			j++;
		}
		Arrays.sort(str);

		return str;

	}

	//Pour les tests :
	@Override
	public String toString() {
		return ensembleVoitures.toString();
	}
}