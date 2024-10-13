import java.util.HashMap;
import java.util.HashSet;


public class ExpressionArithmetique extends ArbreDeCaracteres { 
	
	/**
	 * Cree une expression arithmetique a partir d'un arbre de caracteres
	 * @param a
	 */
	public ExpressionArithmetique (ArbreDeCaracteres a) {
		super(a);
	}

	public ExpressionArithmetique (char c) {
		super(c);
	}
	
	public ExpressionArithmetique (char c, ArbreDeCaracteres ag, ArbreDeCaracteres ad) {
		super(c, ag, ad);
	}
	
	
	/**
	 * calcule le nombre d'operations correspondant au type d'operateur passe en parametre que contient l'expression arithmetique
	 * Par ex : exp1 : + --> 1
	 *                 / --> 1
	 *                 ...
	 *          exp3 : + --> 4 
	 * @param operateur l'operateur verifie
	 * @return le nombre d'operations
	 * @throws IllegalArgumentException si le caractere passe en parametre n'est pas un operateur (+,-,*,/)
	 */
	public int nombreOperations(char operateur)  {
		if(operateur!='+' && operateur!='-' && operateur!='/' && operateur!='*' ) throw new IllegalArgumentException();
		return nombreOperations(racine, operateur);
	}

	private int nombreOperations(NoeudCaractere noeud, char operateur) {
		if(noeud == null) return 0;

		if(noeud.caractere == operateur)
			return 1+nombreOperations(noeud.gauche, operateur)+nombreOperations(noeud.droit, operateur);

		return nombreOperations(noeud.gauche, operateur)+nombreOperations(noeud.droit, operateur);
	}
	
	/**
	 * verifie si l'arbre ne contient que des additions
	 * Par ex : exp3 ne contient que des +
	 * @return true si l'expression arithmetique contient uniquement des additions, false sinon
	 */
	public boolean uniquementDesAdditions(){
		return uniquementDesAdditions(racine);
	}

	private boolean uniquementDesAdditions(NoeudCaractere noeud){
		if (noeud == null) return true;
		if(noeud.caractere=='-' || noeud.caractere=='/' || noeud.caractere=='*' ) return false;
		return uniquementDesAdditions(noeud.gauche) && uniquementDesAdditions(noeud.droit);
	}

	
	/**
	 * calcule le nombre d'entiers differents contenus dans l'expression arithmetique
	 * Par ex : exp2 contient 3 entiers differents : 1, 4 et 8
	 * @return le nombre d'entiers differents
	 */
	public int nombreEntiersDifferents(){
		HashSet<Character> entiers = new HashSet<>();
		nombreEntiersDifferents(racine, entiers);
		return entiers.size();
	}

	private void nombreEntiersDifferents(NoeudCaractere noeud, HashSet<Character> entiers) {
		if (noeud == null) return;
		if (Character.isDigit(noeud.caractere))
			entiers.add(noeud.caractere);

		nombreEntiersDifferents(noeud.gauche, entiers);
		nombreEntiersDifferents(noeud.droit, entiers);
	}


	/**
	 * calcule la valeur de l'expression stockee dans l'arbre
	 * Par ex : exp1 --> 13
	 * @return le resultat 
	 */
	public double resultat() {
		return resultat(racine);
	}

	private double resultat(NoeudCaractere noeud) {
		if (noeud==null) {
			return 0;
		}
		double gauche = resultat(noeud.gauche);
		double droit = resultat(noeud.droit);

		if (noeud.caractere == '+') return gauche + droit;
		if (noeud.caractere == '-') return gauche - droit;
		if (noeud.caractere == '*') return gauche * droit;
		if (noeud.caractere == '/') {
			if (droit == 0) {
				throw new ArithmeticException("Division par zéro");
			}
			return gauche / droit;
		} else {
			return noeud.caractere - '0';
		}
	}
	
	

	/**
	 * renvoie l'expression stockee dans l'arbre en notation infixe
	 * Par exp : exp1 --> ((3-2)+(4*(9/3)))
	 * @return la notation infixe
	 */
	public String notationInfixe() {
		return notationInfixe(racine);
	}

	private String notationInfixe(NoeudCaractere noeud) {
		if (noeud == null) return "";
		if (noeud.gauche==null) return Character.toString(noeud.caractere);

		String gauche = notationInfixe(noeud.gauche);
		String droit = notationInfixe(noeud.droit);

		return "(" + gauche + noeud.caractere + droit + ")";
	}
	
}

