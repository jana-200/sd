import javax.xml.stream.events.EndElement;
import java.util.ArrayList;

public class ABRSDImpl<E> implements ABRSD<E>{

	private Noeud racine;
	private int taille;
	
	public ABRSDImpl(){
		racine = null;
		taille = 0;
	}

	public boolean estVide() {
		return racine==null;
	}
	
	public int taille() {
		return taille;
	}
		

	public E elementLePlusPetit() {
		if(racine==null)return null;
		return elementLePlusPetit(racine);
	}
	private E elementLePlusPetit(Noeud n){
		if(n.gauche!=null)
			return elementLePlusPetit(n.gauche);
		return n.element;
	}

	public boolean insere(E element) {
		if(racine==null){
			racine =new Noeud(element);
			taille++;
			return true;
		}

		return insere(racine,element);
	}
	private boolean insere(Noeud n, E el){
		if(((Comparable<E>)n.element).compareTo(el)>=0){
			if(n.gauche==null){
				n.gauche=new Noeud(el);
				taille++;
				return true;
			}
			else return insere(n.gauche,el);
		}
		if(((Comparable<E>)n.element).compareTo(el)==0)
			return false;
		else {
			if(n.droit==null){
				n.droit=new Noeud(el);
				taille++;
				return true;
			}
			else return insere(n.droit,el);
		}
	}



	public E elementPrecedent(E element) {
		ArrayList<E> liste = new ArrayList<>();
		remplirArrayList(racine, liste);

		for (int i = 1; i < liste.size(); i++) {
			if (((Comparable<E>) liste.get(i)).compareTo(element) == 0) {
				return liste.get(i - 1);
			}
		}
		return null;
	}

	private void remplirArrayList(Noeud noeud, ArrayList<E> liste) {
		if (noeud != null) {
			remplirArrayList(noeud.gauche, liste);
			liste.add(noeud.element);
			remplirArrayList(noeud.droit, liste);
		}
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
	// permet de construire l'ABRSD de l'enonce

	public ABRSDImpl(E e1, E e2, E e3, E e4, E e5, E e6, E e7){
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
