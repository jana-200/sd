import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ABRDEntiers implements Iterable<Integer> {

	private NoeudEntier racine;

	public ABRDEntiers() {
		racine = null;
	}

	public boolean estVide() {
		return racine == null;
	}


	public int nombreNegatifsVI(){
		int count=0;
		//CONTRAINTE : cette methode doit etre iterative
		//Utilisez l'iterateur
		for (int entier : this) {
			if(entier>0) return count;
			count++;

		}
		return count;
	}

	public int nombreNegatifsVR(){
		//CONTRAINTE : cette methode doit etre recursive
		return nombreNegatifsVR(racine);
	}

	private int nombreNegatifsVR(NoeudEntier n){
		if(n==null) return 0;
		if(n.entier<0 ) return 1+ nombreNegatifsVR(n.gauche)+ nombreNegatifsVR(n.droit);
		return nombreNegatifsVR(n.gauche);
	}

	public boolean tousPositifsVI() {
		//CONTRAINTE : cette methode doit etre iterative
		//Utilisez l'iterateur
		//N'utilisez pas une methode nombrePositifs()!
		for (int entier : this) {
			if(entier<0) return false;

		}
		return true;
	}

	public boolean tousPositifsVR() {
		//CONTRAINTE : cette methode doit etre recursive
		//N'utilisez pas une methode nombrePositifs()!
		return tousPositifsVR(racine);
	}

	private boolean tousPositifsVR(NoeudEntier noeud){
		if(noeud==null)return true;
		if(noeud.entier<0)return false;
		return tousPositifsVR(noeud.gauche);
	}

	@Override
	public Iterator<Integer> iterator() {
		return new Iterateur();
	}

	private class Iterateur implements Iterator<Integer> {
		private ArrayDeque<Integer> fileDEntiers;

		public Iterateur () {
			fileDEntiers = new ArrayDeque<Integer>();
			if(!estVide())
				remplirFile(racine);
		}

		private void remplirFile (NoeudEntier n) {
			if (n == null) return;

			// Choisissez la suite d'instructions qui permettra de parcourir les entiers par ordre croissant

			/*
			fileDEntiers.addLast(n.entier);
			remplirFile(n.gauche);
			remplirFile(n.droit);
			*/


			remplirFile(n.gauche);
			fileDEntiers.addLast(n.entier);
			remplirFile(n.droit);


			/*
			remplirFile(n.gauche);
			remplirFile(n.droit);
			fileDEntiers.addLast(n.entier);
			*/
		}

		public boolean hasNext () {
			return !fileDEntiers.isEmpty();
		}

		public Integer next () {
			if(!hasNext())
				throw new NoSuchElementException();
			return fileDEntiers.removeFirst();
		}

	}

	public class NoeudEntier {

		private int entier;
		private NoeudEntier gauche;
		private NoeudEntier droit;

		private NoeudEntier (int entier) {
			this.entier = entier;
			this.gauche = null;
			this.droit = null;
		}

		private NoeudEntier (NoeudEntier g, int entier, NoeudEntier d) {
			this.entier = entier;
			this.gauche = g;
			this.droit = d;
		}
	}

	// pour les tests

	public ABRDEntiers (int entier) {
		this.racine = new NoeudEntier(entier) ;
	}

	// pour les tests - attention ne verifie pas si tri ABR respecte!
	public ABRDEntiers (ABRDEntiers filsGauche, int entier, ABRDEntiers filsDroit) {
		this.racine = new NoeudEntier(filsGauche.racine, entier, filsDroit.racine);

	}
}
