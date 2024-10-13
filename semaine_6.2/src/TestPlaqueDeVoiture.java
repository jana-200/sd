import java.util.Arrays;

public class TestPlaqueDeVoiture {

	public final static int NBRE_LISTES =500;
	public static void main (String args[]) {
		int[] counter= new int[NBRE_LISTES];
		for (char i = 'A'; i <= 'Z' ; i++) {
			for (char j = 'A'; j <= 'Z'; j++) {
				for (char k = 'A'; k <= 'Z'; k++) {
					for (int l = 0; l <= 9; l++) {
						for (int m = 0; m <= 9; m++) {
							for (int n = 0; n <= 9; n++) {
								Voiture voiture=new Voiture("1"+i+j+k+l+m+n,"-");
								int val= Math.abs(voiture.hashCode()%NBRE_LISTES);
								counter[val]++;
							}
						}
					}
				}
			}
		}
		for (int i = 0; i < counter.length ; i++) {
			System.out.println(counter[i]);
		}

		// Attention, la methode hashCode() renvoie un entier
		// Cet entier pourrait etre negatif --> Math.abs()		
		// Cet entier doit correspondre a une liste --> %NBRE_LISTES
	}
}