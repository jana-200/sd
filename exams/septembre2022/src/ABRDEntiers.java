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


	public int nombrePositifsVI(){
		int count=0;
		//TODO
		//CONTRAINTE : cette methode doit etre iterative
		//Utilisez l'iterateur
		for (int entier : this) {
			if(entier>0) count++;
		}
		return count;
	}

	public int nombrePositifsVR(){
		if(racine==null) return 0;
		//CONTRAINTE : cette methode doit etre recursive
		return nombrePositifsVR(racine);
	}
	private int nombrePositifsVR(NoeudEntier n){
		if(n==null) return 0;
		if(n.entier<0) return nombrePositifsVR(n.droit);
		return 1+ nombrePositifsVR(n.droit)+nombrePositifsVR(n.gauche);
	}

	public boolean auMoins1PositifVI() {
		//CONTRAINTE : cette methode doit etre iterative
		//Utilisez l'iterateur
		//N'utilisez pas une methode nombrePositifs()!
		for(int i: this){
			if(i>0) return true;
		}
		return false;
	}

	public boolean auMoins1PositifVR() {
		//CONTRAINTE : cette methode doit etre recursive
		//N'utilisez pas une methode nombrePositifs()!
		return auMoins1PositifVR(racine);
	}
	private boolean auMoins1PositifVR(NoeudEntier n){
		if(n==null) return false;
		if(n.entier>0) return true;
		return auMoins1PositifVR(n.droit);
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

			//TODO
			// Mettez les instructions suivantes dans le bon ordre
			// pour permettre d'iterer les entiers par ordre decroissant

			remplirFile(n.droit);
			fileDEntiers.addLast(n.entier);
			remplirFile(n.gauche);

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
