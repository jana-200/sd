
public class Grille9X9 {

	private int[][] table;

	public Grille9X9(int[][] tableARecopier)throws IllegalArgumentException{
		if(tableARecopier==null)
			throw new IllegalArgumentException();
		if(tableARecopier.length!=9)
			throw new IllegalArgumentException();
		for(int i = 0;i<9;i++){
			if(tableARecopier[i]==null||tableARecopier[i].length!=9)throw new IllegalArgumentException();
		}
		table = new int[9][9];
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if(tableARecopier [i][j]<1||tableARecopier[i][j]>9)throw new IllegalArgumentException();
				table[i][j]=tableARecopier[i][j];
			}
		}
	}

	private boolean ligneCorrecte(int ligne){
		Ensemble1A9 ens= new Ensemble1A9();
		for (int i = 0; i < table[ligne].length; i++) {
			ens.ajouter(table[ligne][i]);
		}
		return ens.taille()==table[ligne].length;
		// Cette methode a la visibilite private, 
		// 		--> il est inutile de tester le parametre
		// 		--> premiere ligne ligne 0 ou ligne 1? Comme vous voulez!
		// LE BUT DE CET EXERCICE EST D'UTILISER LA CLASSE ENSEMBLE1A9...
	}

	private boolean colonneCorrecte(int colonne){
		Ensemble1A9 ens= new Ensemble1A9();
		for (int i = 0; i < table.length; i++) {
			ens.ajouter(table[i][colonne]);
		}
		return ens.taille()==table.length;
	}
	
	// verifie le bloc qui commence a la ligne et a la colonne passees en parametre
	private boolean blocCorrect(int ligne, int colonne){
		Ensemble1A9 ens= new Ensemble1A9();
		int startRow = ligne - ligne % 3; // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		int startCol = colonne - colonne % 3; // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

		// Iterate over the block
		for (int i = startRow; i < startRow + 3; i++) {
			for (int j = startCol; j < startCol + 3; j++) {
				ens.ajouter(table[i][j]);
			}
		}
		return ens.taille()== table.length;
	
	}

	private boolean diagonale(){
		Ensemble1A9 ens= new Ensemble1A9();
		Ensemble1A9 ens2= new Ensemble1A9();
		for (int i = 0; i < 9; i++) {
			ens.ajouter(table[i][i]); // Diagonale principale
			ens2.ajouter(table[i][8 - i]); // Diagonale secondaire
		}
		return ens.taille()== table.length && ens2.taille()== table.length;
	}


		
	public boolean estUnSudoku(){
		for (int i = 0; i < 9; i++) {
			if (!ligneCorrecte(i)) {
				return false;
			}
		}

		for (int j = 0; j < 9; j++) {
			if (!colonneCorrecte(j)) {
				return false;
			}
		}

		// +3 car on doit vérifier par bloc (chaque bloc=3cases)
		for (int i = 0; i < 9; i += 3) {
			for (int j = 0; j < 9; j += 3) {
				if (!blocCorrect(i, j)) {
					return false;
				}
			}
		}
		return true;
	}

	
	
	
	public boolean estUnSudokuDiagonal(){
		for (int i = 0; i < 9; i++) {
			if (!ligneCorrecte(i)) {
				return false;
			}
		}

		for (int j = 0; j < 9; j++) {
			if (!colonneCorrecte(j)) {
				return false;
			}
		}

		// +3 car on doit vérifier par bloc de 3
		for (int i = 0; i < 9; i += 3) {
			for (int j = 0; j < 9; j += 3) {
				if (!blocCorrect(i, j)) {
					return false;
				}
			}
		}
		if(!diagonale()) return false;
		return true;
	}
	
	

	public boolean estUnHyperSudoku(){
		// TODO
		// cette methode est proposee en ex supplementaire
		return false;

	}



}
