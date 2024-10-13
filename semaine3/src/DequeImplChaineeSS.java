public class DequeImplChaineeSS<E> implements Deque<E> {

	private Noeud tete ;
	private Noeud queue ;
	private int taille ;
	
	public DequeImplChaineeSS(){
		tete=null;
		queue=null;
		taille=0;
	}

	public int taille() {
		return this.taille ;
	}

	public boolean estVide() {
		return (taille==0) ;
	}

	public void ajouterEnPremier(E element) {
		if(element==null) throw new IllegalArgumentException();
		Noeud noeud= new Noeud(element, null,tete);
		if(tete!=null)tete.precedent=noeud;
		if(taille==0)queue=noeud;
		tete=noeud;

		taille++;
	}

	public E retirerPremier() {
		if(taille==0) throw new DequeVideException();
		E pop= tete.element;
		if(taille==1){
			tete=null;
			queue=null;
		}else{
			tete.suivant.precedent=null;
			tete=tete.suivant;
		}
		taille--;
		return pop;
	}

	public void ajouterEnDernier(E element) {
		if(element==null) throw new IllegalArgumentException();
		Noeud noeud= new Noeud(element, queue,null);
		if(queue!=null) {
			queue.suivant = noeud;
			noeud.precedent=queue;
		}
		if(taille==0)tete=noeud;
		queue=noeud;
		taille++;

	}
	public E retirerDernier() throws DequeVideException {
		if(taille==0) throw new DequeVideException();
		E pop= queue.element;
		if(taille==1){
			tete=null;
			queue=null;
		}else{
			queue.precedent.suivant=null;
			queue=queue.precedent;
		}
		taille--;
		return pop;
	}

	public E premier()throws DequeVideException {
		if(taille==0) throw new DequeVideException();
		return tete.element;
	}

	public E dernier()throws DequeVideException {
		if(taille==0) throw new DequeVideException();
		return queue.element;
	}

	// A NE PAS MODIFIER --> POUR LES TESTS!!!
	// tete 'a' 'b' 'c' queue : ['a','b','c']
	public DequeImplChaineeSS(Object[] table) {
		if(table == null)
			throw new IllegalArgumentException();
		taille = 0 ;
		tete = null ;
		queue = null ;
		if(table.length==0)
			return;
		for (int i = table.length-1; i>=0;i--) {
			this.ajouterTest((E) table[i]) ;
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
			}
			aRenvoyer+=baladeur.element;
			if (baladeur.suivant !=null)
				aRenvoyer += " " ;
			baladeur=baladeur.suivant;
		}
		return aRenvoyer;
	}

	// A NE PAS MODIFIER --> POUR LES TESTS!!!
	public String parcoursInverse(){
		String aRenvoyer="";
		Noeud baladeur=queue;
		int cpt = 0;
		while(baladeur!=null) {
			cpt++;
			if(cpt>taille){
				aRenvoyer = "boucle infinie dans toString(), chainage a verifier";
			}
			aRenvoyer+=baladeur.element;
			if (baladeur.precedent !=null)
				aRenvoyer += " " ;
			baladeur=baladeur.precedent;
		}
		return aRenvoyer;
	}

	// A NE PAS MODIFIER --> POUR LES TESTS!!!
	private void ajouterTest(E element) {
		if (estVide()) {
			tete = new Noeud(element,null,null) ;
			queue = tete ;
		} else {
			tete.precedent = new Noeud(element,null,tete) ;
			tete = tete.precedent;
		}
		taille = taille + 1 ;
	}

	
	// classe interne
	private class Noeud{
		private E element;
		private Noeud precedent;
		private Noeud suivant;

		private Noeud(E element, Noeud precedent, Noeud suivant){
			this.element = element;
			this.precedent = precedent ;
			this.suivant = suivant;
		}
	}

}
