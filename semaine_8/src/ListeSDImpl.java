import java.util.HashMap;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ListeSDImpl<E> implements ListeSD<E>,Iterable<E> {

	private Noeud tete, queue;
	private HashMap<E, Noeud> mapElementNoeud;

	public ListeSDImpl () {
		mapElementNoeud= new HashMap<>();
		tete=new Noeud();
		queue=new Noeud();
		tete.suivant=queue;
		queue.precedent=tete;


	}

	public int taille () {
		return mapElementNoeud.size();
	}

	public boolean estVide () {
		return (mapElementNoeud.size()==0);
	
	}

	public boolean contient (E element) {
		return mapElementNoeud.containsKey(element);

	}

	public E premier() {
		return tete.suivant.element;
	}

	public E dernier() {
		return queue.precedent.element;
	}

	public E donnerPrecedent (E element) {
		Noeud noeud=mapElementNoeud.get(element);
		if(noeud==null) return null;
		return noeud.precedent.element;

	}

	public E donnerSuivant (E element) {
		Noeud noeud=mapElementNoeud.get(element);
		if(noeud==null) return null;
		return noeud.suivant.element;

	}

	public boolean insererEnTete (E element){
		if(contient(element))return false;
		Noeud noeud=new Noeud(element);
		mapElementNoeud.put(element,noeud);
		tete.suivant.precedent=noeud;
		noeud.suivant=tete.suivant;
		tete.suivant=noeud;
		noeud.precedent=tete;
		return true;

	}

	public boolean insererEnQueue (E element) {
		if(contient(element))return false;
		Noeud noeud=new Noeud(element);
		mapElementNoeud.put(element,noeud);
		queue.precedent.suivant=noeud;
		noeud.precedent=queue.precedent;
		queue.precedent=noeud;
		noeud.suivant=queue;
		return true;

	}

	public boolean insererApres (E element, E elementAInserer) {
		if(contient(elementAInserer) || !contient(element))return false;
		Noeud noeud=mapElementNoeud.get(element);
		Noeud nouveau=new Noeud(elementAInserer);
		mapElementNoeud.put(elementAInserer, nouveau);

		noeud.suivant.precedent=nouveau;
		nouveau.suivant=noeud.suivant;
		noeud.suivant=nouveau;
		nouveau.precedent=noeud;

		return true;

	}

	public boolean insererAvant (E element, E elementAInserer) {
		if(contient(elementAInserer) || !contient(element))return false;
		Noeud noeud=mapElementNoeud.get(element);
		Noeud nouveau=new Noeud(elementAInserer);
		mapElementNoeud.put(elementAInserer, nouveau);
		noeud.precedent.suivant=nouveau;
		nouveau.precedent=noeud.precedent;
		noeud.precedent=nouveau;
		nouveau.suivant=noeud;
		return true;

	}


	public boolean supprimer (E element) {
		if(!contient(element))return false;
		Noeud aSup= mapElementNoeud.get(element);
		mapElementNoeud.remove(element, aSup);
		aSup.precedent.suivant=aSup.suivant;
		aSup.suivant.precedent=aSup.precedent;
		return true;

	}

	
	public boolean permuter (E element1, E element2) {
		if(!contient(element1) || !contient(element2)) return false;
		Noeud n1=mapElementNoeud.get(element1);
		Noeud n2=mapElementNoeud.get(element2);
		mapElementNoeud.remove(element1);
		mapElementNoeud.remove(element2);
		E el1=n1.element;
		E el2= n2.element;
		n1.element=el2;
		n2.element=el1;
		mapElementNoeud.put(el2,n1);
		mapElementNoeud.put(el1,n2);

		return true;

		// REMARQUE : CE SONT LES VALEURS QUI SONT PERMUTEES, PAS LES NOEUDS!!!
		// Il est donc inutile de revoir le chainage
		// N'oubliez pas de modifier les noeuds associes dans le map

	}

	public Iterator<E> iterator() {
		return new IterateurImpl();

		// il faut renvoyer un objet de type Iterator :
		//return new IterateurImpl();
		// completez la classe interne IterateurImpl !
	}

	public String toString () {
		String aRenvoyer = "";
		int num = 1;
		Noeud baladeur = tete.suivant;
		while (baladeur != queue) {
			aRenvoyer += num + " - " + baladeur.element + "\n";
			baladeur = baladeur.suivant;
			num++;
		}
		return aRenvoyer;   
	}



	// Classe interne Noeud
	private class Noeud{
		private E element;
		private Noeud suivant;
		private Noeud precedent;

		private Noeud() {
			this(null, null, null);
		}

		private Noeud(E element) {
			this(null, element, null);
		}

		private Noeud(Noeud precedent, E element, Noeud suivant) {
			this.element = element;
			this.suivant = suivant;
			this.precedent = precedent;
		}
	}

	

	// Classe interne IterateurImpl
	private class IterateurImpl implements Iterator{

		private Noeud noeudCourant;

		private IterateurImpl() {
			noeudCourant=tete.suivant;

		}

		public boolean hasNext() {
			return (noeudCourant != queue);

		}

		// renvoie l element qui se trouve dans le noeud courant
		// le noeud courant passe au noeud suivant
		public E next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			E element = noeudCourant.element;
			noeudCourant = noeudCourant.suivant;
			return element;

		}

		// A NE PAS COMPLETER : Les suppressions sont interdites
		public void remove() {
			throw new UnsupportedOperationException();			
		}
	}

	// pour les tests
	public ListeSDImpl(E[] tableACopier) {
		mapElementNoeud = new HashMap<E, Noeud>();
		tete = new Noeud();   // sentinelle de tete
		queue = new Noeud();  // sentinelle de queue
		Noeud prec = tete;
		for (int i = 0; i < tableACopier.length; i++) {
			Noeud nouveauNoeud = new Noeud(tableACopier[i]);
			mapElementNoeud.put(tableACopier[i], nouveauNoeud);
			nouveauNoeud.precedent = prec;
			prec.suivant = nouveauNoeud;
			prec = nouveauNoeud;
		}
		prec.suivant = queue;
		queue.precedent = prec;
	}

	// pour les tests
	public String teteQueue(){
		try{
			String aRenvoyer = "(";
			Noeud baladeur = tete.suivant;
			int cpt=0;
			while (baladeur != queue) {
				if(cpt==0)
					aRenvoyer += baladeur.element;
				else
					aRenvoyer += ","+baladeur.element;
				baladeur = baladeur.suivant;
				cpt++;
				if(cpt==100){
					return "boucle infinie";
				}
			}
			return aRenvoyer+")";
		}catch (NullPointerException e){
			return "nullPointerException";
		}
	}

	// pour les tests
	public String queueTete(){
		try{
			String aRenvoyer = "(";
			Noeud baladeur = queue.precedent;
			int cpt=0;
			while (baladeur != tete) {
				if(cpt==0)
					aRenvoyer += baladeur.element;
				else
					aRenvoyer += ","+baladeur.element;
				baladeur = baladeur.precedent;
				cpt++;
				if(cpt==100){
					return "boucle infinie";
				}
			}
			return aRenvoyer+")";
		}catch (NullPointerException e){
			return "nullPointerException";
		}
	}

}
