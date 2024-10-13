import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.NoSuchElementException;

public class ServeurImpressionsVar{
	private ArrayDeque<DemandeImpression>[] tableFilesDAttente;
	private int id;

	/**
	 * construit une table avec 10 FileAttenteImpression
	 */
	public ServeurImpressionsVar() {
		this.tableFilesDAttente= new ArrayDeque[10];
		for (int i = 0; i < tableFilesDAttente.length; i++) {
			ArrayDeque prio= new ArrayDeque<>();
			tableFilesDAttente[i]=prio;
		}
		this.id=0;
	}

	/**
	 * verifie si toutes les files sont vides
	 * @return true si toutes les files sont vides, false sinon
	 */
	public boolean estVide(){
		for (int i = 0; i < tableFilesDAttente.length; i++) {
			if(!tableFilesDAttente[i].isEmpty())
				return false;
		}
		return true;
	}

	/**
	 * ajoute la demande d impression en fin de la file de priorite correspondante
	 * @param demande la demande a ajouter
	 * @throws IllegalArgumentException si la demande est null
	 */
	public void ajouter(DemandeImpression demande){
		if(demande ==  null) throw new IllegalArgumentException();
		int prio= demande.getPriorite();
		tableFilesDAttente[prio].add(demande);
	}

	/**
	 * retire l'impression en tete de file de priorite la plus haute qui est non vide
	 * @return l'impression qui a ete retiree
	 * @throws FileVideException si aucune demande d impression dans la file
	 */
	public DemandeImpression retirer()throws FileVideException{
		if(estVide()) throw new FileVideException();
		DemandeImpression dem= null;
		if(id>=3){
			DemandeImpression lol;
			for (int i = 0; i < tableFilesDAttente.length-1; i++) {
				if(!tableFilesDAttente[i].isEmpty()){
					lol=tableFilesDAttente[i].getFirst();
					tableFilesDAttente[i].removeFirst();
					tableFilesDAttente[i+1].add(lol);
				}
			}
		}
		for (int i = tableFilesDAttente.length-1; i >=0 ; i--) {
			if(!tableFilesDAttente[i].isEmpty()){
				dem=tableFilesDAttente[i].getFirst();
				tableFilesDAttente[i].removeFirst();
				id++;
				break;
			}
		}
		return dem;
	}

	public String toString() {
		return Arrays.toString(tableFilesDAttente) ;
	}
}