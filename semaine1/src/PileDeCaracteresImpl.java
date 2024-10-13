// implementation d'une pile en utilisant un tableau de taille variable redimensionnable
/**
 * @author 
 *
 */
public class PileDeCaracteresImpl implements PileDeCaracteres{

	private char[] table;    		// ne modifiez pas cet identifiant, va servir pour les tests
	private int nombreCaracteres; 	// taille logique
								  	//ne mofifiez pas cet identifiant, va servir pour les tests

	public PileDeCaracteresImpl(){
		table = new char[4];
		nombreCaracteres = 0;
	}

	public PileDeCaracteresImpl(int capacite){
		if (capacite <= 0)
			throw new IllegalArgumentException("la taille physique ne peut etre negative ou nulle");
		table = new char[capacite];
		nombreCaracteres = 0;
	}

	public int taille(){
		return nombreCaracteres;
	}

	public boolean estVide(){
		return nombreCaracteres == 0;
	}
	
	public void push(char c){
		if(nombreCaracteres== table.length){
			char[] temp= new char[table.length*2];
			for (int i = 0; i < table.length; i++) {
				temp[i] = table[i];
			}
			table= temp;
		}
		table[nombreCaracteres]= c;
		nombreCaracteres++;
		// PENSEZ A CONSULTER LA JAVADOC (cfr Interface PileDeCaracteres)
	}
	


	public char pop() throws PileVideException{
		if (nombreCaracteres==0) throw new PileVideException();
		nombreCaracteres--;

		return table[nombreCaracteres];

		// PENSEZ A CONSULTER LA JAVADOC (cfr Interface PileDeCaracteres)
	}


	public char sommet()throws PileVideException{
		if (nombreCaracteres==0) throw new PileVideException();
		return table[nombreCaracteres-1];
		// PENSEZ A CONSULTER LA JAVADOC (cfr Interface PileDeCaracteres)
	}

} 
