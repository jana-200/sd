import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * implementation de l'interface ListeSimple avec une liste simplement chainee
 */
public class ListeSimpleImpl<E> implements ListeSimple<E> {
	
	private Noeud tete;
	private int taille;
	

	public ListeSimpleImpl(){
		tete = null;
		taille=0;
	}
	
	public int taille(){
		return taille;
	}
		
	public boolean estVide(){
		return taille==0;
	}


	// renvoie l element contenu dans le noeud de tete
	public E premier()throws ListeVideException{
		if(this.estVide() ) throw new ListeVideException();

		return this.tete.element;
	}


	// insere un nouveau noeud en tete de liste avec l element 
	public void insererEnTete(E element) {
		Noeud nv= new Noeud(element,this.tete);
		this.tete=nv;
		taille++;
	}

	// verifie la presence d un noeud contenant l element passe en parametre
	public boolean contient(E element){
		Noeud noeud = this.tete;
		while(noeud!=null){
			if(noeud.element.equals(element)) return true;
			noeud=noeud.suivant;
		}
		return false;
	}


	// insere un nouveaud noeud apres le noeud contenant la premiere occurrence de l'element passe en parametre
	public boolean insererApres(E element, E elementAInserer){
		Noeud noeud = this.tete;
		Noeud add;
		while(noeud!=null){
			if(noeud.element.equals(element)){
				add=new Noeud(elementAInserer,noeud.suivant);
				noeud.suivant=add;
				taille++;
				return true;
			}
			noeud=noeud.suivant;
		}
		return false;
	}


	//supprime le noeud contenant la premiere occurrence de l'element passe en parametre
	public boolean supprimer(E element){
		if (tete == null) {
			return false;
		}

		if (tete.element.equals(element)) {
			tete = tete.suivant;
			taille--;
			return true;
		}

		Noeud noeud = this.tete;
		while(noeud.suivant!=null){
			if(noeud.suivant.element.equals(element)){
				noeud.suivant = noeud.suivant.suivant;
				taille--;
				return true;
			}
			noeud=noeud.suivant;

		}
		return false;
	}
	
	@Override
	public Iterator<E> iterator() {
		return new IterateurImpl();
	}

	private class IterateurImpl implements Iterator{
		
		private Noeud noeudNext;


		// Au depart le noeud "next" est le noeud de tete
		private IterateurImpl() {
			noeudNext =  tete;
		}
		
		@Override
		// verifie si le noeud "next" est null
		public boolean hasNext() {

			return !(this.noeudNext==null);
		}

		@Override
		// renvoie l element qui se trouve dans le noeud "next"
		// le noeud "next" passe au noeud suivant
		public E next() {
			if(! hasNext()) throw new NoSuchElementException();

			E element = noeudNext.element;
			noeudNext = noeudNext.suivant;
			return element;
		}
	}

	private class Noeud{
		private E element;
		private Noeud suivant;

		public Noeud(E element, Noeud suivant){
			this.element = element;
			this.suivant = suivant;
		}
	}


	// A NE PAS MODIFIER --> POUR LES TESTS!!!
	public String toString(){
		String aRenvoyer="";
		Noeud baladeur=tete;
		int cpt = 0;
		while(baladeur!=null) {
			cpt++;
			if(cpt>taille){
				aRenvoyer = "boucle infinie dans toString(), chainage a verifier";
				return aRenvoyer;
			}
			aRenvoyer+=" "+baladeur.element;
			baladeur=baladeur.suivant;
		}
		return aRenvoyer;
	}


	// A NE PAS MODIFIER --> POUR LES TESTS!!!
	public ListeSimpleImpl(Object[] table) {
		if(table==null)
			throw new IllegalArgumentException();
		this.taille = table.length;
		for (int i = table.length-1; i>=0; i--) {
			E element = (E)table[i];
			this.tete=new Noeud(element,tete);
		}
	}

}
