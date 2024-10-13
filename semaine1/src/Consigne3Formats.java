/**
 * @author 
 *
 */

public class Consigne3Formats {
	private Pile[] casiersLibres;
	private Casier[] tousLesCasiers;
	private Pile<Casier> format1;
	private Pile<Casier> format2;
	private Pile<Casier> format3;
	private int[] formats;

	public Consigne3Formats(int[] formats) {
		this.formats=formats;
		tousLesCasiers= new Casier[formats.length];
		casiersLibres= new Pile[3];
		format1= new PileImpl<>();
		format2= new PileImpl<>();
		format3= new PileImpl<>();

		casiersLibres[0]= format1;
		casiersLibres[1]= format2;
		casiersLibres[2]= format3;

		Casier casier ;
		for (int i = 0; i < formats.length; i++) {
			casier = new Casier(i);
			tousLesCasiers[i]= casier;
			casiersLibres[formats[i]-1].push(casier);
		}
	}

	// Retourne true s'il reste un casier libre pour le format demande, false sinon.
	public boolean resteUnCasierLibre(int format)throws IllegalArgumentException {
		if(format>3) throw new IllegalArgumentException();
		return !casiersLibres[format-1].estVide();
	}

	/* S'il reste des casiers libres pour le format demande, un casier sera attribue, et le
	 * mot de passe de ce casier devient le parametre motDePasse.
	 * La methode renverra le numero du casier attribue.
	 * Dans le cas ou il n'y a plus de casier libre, la methode renvoie -1.
	 */
	public int attribuerCasierLibre(int format, String motDePasse) throws IllegalArgumentException{
		if(format>3) throw new IllegalArgumentException();
		Pile<Casier> pile = casiersLibres[format-1];
		if(pile.estVide()) return -1;
		Casier casier;
		casier= pile.pop();
		casier.setMotDePasse(motDePasse);
		// A COMPLETER		
		return casier.getNumero();
	}

	/* La methode libere le casier dont le numero est donne en parametre,
	 * pour autant que le numero existe et que le motDePasse soit le bon. 
	 */
	public boolean libererCasier(int numeroCasier, String motDePasse) {
		if(numeroCasier<0 || numeroCasier >= tousLesCasiers.length) return false;
		Casier casier=tousLesCasiers[numeroCasier];
		if(casier.getMotDePasse().equals(motDePasse)){
			int format = formats[numeroCasier];
			casiersLibres[format-1].push(casier);
			casier.setMotDePasse("");
			return true;
		}

		return false;
	}

}
