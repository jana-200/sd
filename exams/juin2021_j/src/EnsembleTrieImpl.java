import java.util.ArrayList;

public class EnsembleTrieImpl<E> implements EnsembleTrie<E>{

	private Noeud racine;
	private int taille;
	
	public EnsembleTrieImpl(){
		racine = null;
		taille = 0;
	}

	public boolean estVide() {
		return racine==null;
	}
	
	public int taille() {
		return taille;
	}
		

	public E elementLePlusGrand() {
		if (estVide()) return null;

		Noeud current = racine;
		while (current.droit != null) {
			current = current.droit;
		}
		return current.element;
	}

	
	public boolean ajoute(E element) {
		if(racine==null){
			racine=new Noeud(element);
			taille++;
			return true;
		}
		//Lisez bien les explications pour l'utilisation de la methode compareTo() dans l'enonce
		return ajoute(element, racine);
	}
	private boolean ajoute(E element, Noeud n){
		if (((Comparable<E>) element).compareTo(n.element) < 0) {
			if (n.gauche == null) {
				n.gauche = new Noeud(element);
				taille++;
				return true;
			} else return ajoute(element, n.gauche);
		}
		if (((Comparable<E>) element).compareTo(n.element) > 0) {
			if (n.droit == null) {
				n.droit = new Noeud(element);
				taille++;
				return true;
			} else return ajoute(element, n.droit);
		}
		return false;

	}
	public E elementSuivant(E element) {
		ArrayList<E> liste=new ArrayList<>();
		remplirListe(racine, liste);
		for (int i = 0; i < liste.size(); i++) {
			if (liste.get(i).equals(element)) {
				if (i + 1 < liste.size()) {
					return liste.get(i + 1);
				}
				return null;
			}
		}
		return null;
	}
	
	private void remplirListe(Noeud noeud,ArrayList<E> liste) {
		if (noeud == null) return;
		remplirListe(noeud.gauche, liste);
		liste.add(noeud.element);
		remplirListe(noeud.droit, liste);
	}
	

	// A NE PAS MODIFIER!!!
	// VA SERVIR POUR LES TESTS!!!
	public String toString () {
		return "[ "+toString(racine)+" ]";
	}

	private String toString (Noeud n) {
		if (n==null) 
			return "";
		if (n.gauche == null && n.droit == null) 
			return ""+n.element;
		if (n.gauche == null) 
			return " [ ] "+n.element+" [ "+toString(n.droit)+" ] ";
		if (n.droit == null) 
			return " [ "+toString(n.gauche)+" ] "+n.element+ " [ ] ";
		return " [ "+toString(n.gauche)+" ] "+n.element+" [ "+toString(n.droit)+" ] ";	
	}

	// A NE PAS MODIFIER! VA SERVIR POUR LES TESTS
	// permet de construire l'ensembleTrie de l'enonce

	public EnsembleTrieImpl(E e1, E e2, E e3, E e4, E e5, E e6, E e7){
		Noeud nG = new Noeud(null,e2,new Noeud(e5));
		Noeud nG1 = new Noeud(new Noeud(e7),e4,new Noeud(e6));
		Noeud nD = new Noeud(nG1,e3, null);
		racine = new Noeud(nG,e1,nD);
		taille=7;
	}


	public class Noeud{

		private E element; 
		private Noeud gauche;
		private Noeud droit;

		private Noeud(E element){
			this.element = element;
			this.gauche = null;
			this.droit = null;
		}

		private Noeud (Noeud gauche, E element, Noeud droit){
			this.element = element;
			this.gauche = gauche;
			this.droit = droit;
		}
	}

}
