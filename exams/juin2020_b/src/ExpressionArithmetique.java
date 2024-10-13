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
	 * calcule le nombre d'operations que contient l'expression arithmetique
	 * Par ex : exp1 : 4 operations
	 *          exp2 : 5 operations
	 * @return le nombre d'operations
	 */
	public int nombreOperations()  {

		return nombreOperations(racine);
	}
	private int nombreOperations(NoeudCaractere n){
		if(n.caractere=='+' || n.caractere=='-' || n.caractere=='*' || n.caractere=='/')
			return 1+nombreOperations(n.gauche)+nombreOperations(n.droit);
		return 0;
	}
	
	/**
	 * verifie si l'arbre ne contient que des operateurs du type passe en parametre
	 * Par ex l'exp3 ne contient que des +
	 * @param operateur l'operateur verifie
	 * @return true si l'expression arithmetique contient uniquement des operateurs du type passe en parametre, false sinon
	 * @throws IllegalArgumentException si le caractere passe en parametre n'est pas un operateur (+,-,*,/)
	 */
	public boolean uniquementDes(char operateur){
		if(operateur!='+' && operateur!='-' && operateur!='*' && operateur!='/') throw new IllegalArgumentException();

		return uniquementDes(racine,operateur);
	}
	private boolean uniquementDes(NoeudCaractere n, char op){
		if(n.caractere!='+' && n.caractere!='-' && n.caractere!='*' && n.caractere!='/') return true;
		if(n.caractere==op)return uniquementDes(n.droit,op) && uniquementDes(n.gauche,op);
		else return false;
	}
	
	/**
	 * calcule le nombre d'entiers differents contenus dans l'expression arithmetique
	 * Par ex : exp2 contient 3 entiers differents : 1, 4 et 8
	 * @return le nombre d'entiers differents
	 */
	public int nombreEntiersDifferents() {
		HashSet<Character> listeHash = new HashSet<>();
		nombreEntiersDifferentss(racine, listeHash);
		return listeHash.size();
	}

	private void nombreEntiersDifferentss(NoeudCaractere n, HashSet<Character> listeHash) {
		if (n == null) {
			return;
		}
		if (Character.isDigit(n.caractere)) {
			listeHash.add(n.caractere);
		}
		nombreEntiersDifferentss(n.droit, listeHash);
		nombreEntiersDifferentss(n.gauche, listeHash);
	}


} 

