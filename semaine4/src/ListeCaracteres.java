import java.util.List;
import java.util.NoSuchElementException;

import java.util.ArrayList;
public class ListeCaracteres {

	private NoeudCaractere tete;
	// N'ajoutez pas d'autres attributs
	
	public ListeCaracteres() {
		this.tete=null;
	}
	
	// A NE PAS MODIFIER --> POUR LES TESTS!!!
	public ListeCaracteres(char[] tableCaracteres) {
		if(tableCaracteres==null)
			throw new IllegalArgumentException();
		for (int i = tableCaracteres.length-1; i>=0; i--) {
			this.tete=new NoeudCaractere(tableCaracteres[i],tete);
		}	
	}
	
	// A NE PAS MODIFIER --> POUR LES TESTS!!!
	public String toString(){
		String aRenvoyer = "";
		NoeudCaractere baladeur = tete;
		while(baladeur != null) {
			aRenvoyer += " " + baladeur.caractere;
			baladeur = baladeur.suivant;
		}
		return aRenvoyer;
	}
	
	// A NE PAS MODIFIER --> POUR LES TESTS !!!
	public void remplacerToutParX(){
		NoeudCaractere baladeur = tete;
		while(baladeur != null) {
			baladeur.caractere = 'x';
			baladeur = baladeur.suivant;
		}
	}
	
	/**
	 * verifie la presence du caractere passe en parametre dans la liste
	 * @param caractereRecherche
	 * @return true si le caractere est present dans la liste, false sinon
	 */
	public boolean contient(char caractereRecherche){
		NoeudCaractere noeud = this.tete;
		while(noeud!=null){
			if(noeud.caractere==caractereRecherche) return true;
			noeud=noeud.suivant;
		}
		return false;
	}

	
	/**
	 * calcule le nombre de fois qu'apparait le caractere passe en parametre dans la liste
	 * @param caractereRecherche
	 * @return le nombre d'occurrences du caractere
	 */
	public int nombreOccurrences(char caractereRecherche){
		int cpt=0;
		NoeudCaractere noeud = this.tete;
		while(noeud!=null){
			if(noeud.caractere==caractereRecherche) cpt++;
			noeud=noeud.suivant;
		}
		return cpt;
	}

	
	/**
	 * remplace la 1ere occurrences du caractere a remplacer par un nouveau caractere
	 * @param caractereARemplacer le caractere a remplacer
	 * @param nouveauCaractere le nouveau caractere
	 */
	public void remplacer(char caractereARemplacer, char nouveauCaractere){
		NoeudCaractere noeud = this.tete;
		while(noeud!=null){
			if(noeud.caractere==caractereARemplacer) {
				noeud.caractere = nouveauCaractere;
				break;
			}
			noeud=noeud.suivant;
		}
	}

	
	/**
	 * remplace toutes les occurrences du caractere a remplacer par un nouveau caractere
	 * @param caractereARemplacer le caractere a remplacer
	 * @param nouveauCaractere le nouveau caractere
	 */
	public void remplacerTout(char caractereARemplacer, char nouveauCaractere){
		NoeudCaractere noeud = this.tete;
		while(noeud!=null){
			if(noeud.caractere==caractereARemplacer) {
				noeud.caractere = nouveauCaractere;
			}
			noeud=noeud.suivant;
		}

	}
	
	
	/**
	 * recherche le plus grand caractere de la liste ('a'<'b'< ...)
	 * @return le plus grand caractere 
	 * @throws NoSuchElementException si la liste est vide
	 */
	public char max() throws NoSuchElementException{
		if(this.tete==null ) throw new NoSuchElementException();
		NoeudCaractere noeud = this.tete;
		char max=' ';
		while(noeud!=null){
			if(noeud.caractere>max) {
				max=noeud.caractere;
			}
			noeud=noeud.suivant;
		}
		return max;
	}
	
	
	
	
	/**
	 * cree une arrayList contenant les caracteres de la liste 
	 * L'ordre doit etre respecte (une liste est une structure lineaire)
	 * @return l'arrayList cree
	 */
	public ArrayList<Character> enArrayList(){
		ArrayList<Character> arr=new ArrayList<>();
		NoeudCaractere noeud = this.tete;
		while(noeud!=null){
			arr.add(noeud.caractere);
			noeud=noeud.suivant;
		}
		return  arr;
		// add() est en O(1)!
		
	}

	/**
	 * verifie si les 2 listes contiennent les memes caracteres et ceci dans le meme ordre
	 * Une liste est une structure LINEAIRE!
	 * @param l la liste a comparer a la liste courante
	 * @return true si les 2 listes sont les memes, false sinon
	 */
	public boolean estEgalA(ListeCaracteres l){
		NoeudCaractere noeud = this.tete;
		NoeudCaractere liste =l.tete;
		while(noeud!=null && liste!=null ){
			if(noeud.caractere!=liste.caractere) {
				return false;
			}
			noeud=noeud.suivant;
			liste=liste.suivant;
		}
		if((noeud!=null && liste==null) || (noeud==null && liste!=null) ) return false;

		return true;
		// N'utilisez pas la methode toString()!

	}
	
	/**
	 * cree une liste qui est une copie de la liste courante (meme ordre)
	 * @return une copie de la liste courante
	 */
	public ListeCaracteres clone(){
		ListeCaracteres liste= new ListeCaracteres();
		NoeudCaractere dernierNoeud=null;
		NoeudCaractere noeud = this.tete;

		while(noeud!=null){
			NoeudCaractere n=new NoeudCaractere(noeud.caractere, null);

			if(liste.tete==null){
				liste.tete=n;
			}else{
				dernierNoeud.suivant=n;
			}
			dernierNoeud=n;
			noeud=noeud.suivant;

		}
		return liste;
	}

	

	/**
	 * supprime une fois le caractere passe en parametre
	 * si le caractere se trouve plusieurs fois, c est sa premiere occurrence qui sera supprimee
	 * @param caractereASupprimer
	 * @return true si le caractere etait bien present dans la liste, false sinon
	 */
	public boolean supprimerPremiereOccurrence(char caractereASupprimer){
		if(tete==null)
			return false;
		
		if(tete.caractere==caractereASupprimer){
			this.tete = this.tete.suivant;
			return true;
		}
		
		NoeudCaractere baladeur = this.tete;
		while(baladeur.suivant != null) {
			if(baladeur.suivant.caractere == caractereASupprimer){
				baladeur.suivant = baladeur.suivant.suivant;
				return true;
			}
			baladeur = baladeur.suivant;
		}
		
		return false;	
	}

	
	
	/**
	 * supprime le caractere autant de fois qu'il est present dans la liste
	 * @param caractereASupprimer
	 * @return le nombre de suppressions effectuees
	 */

	public int supprimerToutesLesOccurrences(char caractereASupprimer){
		int sup = 0;
		if (tete == null)
			return sup;

		NoeudCaractere baladeur = this.tete;

		// Traitement du premier nœud
		while (baladeur != null && baladeur.caractere == caractereASupprimer) {
			this.tete = this.tete.suivant;
			baladeur = this.tete;
			sup++;
		}

		// Traitement des nœuds suivants
		while (baladeur != null && baladeur.suivant != null) {
			if (baladeur.suivant.caractere == caractereASupprimer) {
				baladeur.suivant = baladeur.suivant.suivant;
				sup++;
			} else {
				baladeur = baladeur.suivant;
			}
		}
		return sup;
	}
	

	

	private class NoeudCaractere{
		private char caractere;
		private NoeudCaractere suivant;

		public NoeudCaractere(char caractere, NoeudCaractere suivant){
			this.caractere = caractere;
			this.suivant = suivant;
		}

	}
}
