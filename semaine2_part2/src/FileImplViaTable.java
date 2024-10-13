import java.util.Arrays;

// implementation de l'interface File via une table circulaire

/**
 * @author 
 *
 */

public class FileImplViaTable<E> implements File<E>{

	private Object[] table;  // ne modifiez pas cet identifiant, la classe test l'utilise					
	private int indiceTete;  // ne modifiez pas cet identifiant, la classe test l'utilise			
	private int taille;		// ne modifiez pas cet identifiant, la classe test l'utilise	
	// N'ajoutez pas d'autres attributs, la classe test risquerait de ne pas fonctionner
	

	public FileImplViaTable(){
		table = new Object[4];
		taille = 0;
		indiceTete = 0;
	}
	

	public boolean estVide(){
		return taille == 0;
	}


	public int taille(){
		return taille;
	}

	public E premier()throws FileVideException{
		if(taille==0) throw new FileVideException();

		return (E) table[indiceTete];
		
	}


	public E defile() throws FileVideException{
		if(taille==0)
			throw new FileVideException();

		E pop= (E) table[indiceTete];
		taille--;
		if(indiceTete==table.length-1)
			indiceTete=0;
		else
			indiceTete++;
		return pop;
	}


	public void enfile(E element){
		if(taille<table.length){
			table[(taille+indiceTete)% table.length] = element;
		}else {
			if(taille>=table.length) {
				Object[] temp = new Object[table.length*2];
				int j =0;
				for (int i = indiceTete; i < table.length; i++) {
					temp[j]=table[i];
					j++;
				}
				for (int i = 0; i < indiceTete; i++) {
					temp[j]=table[i];
					j++;
				}
				table=temp;
				indiceTete=0;
			}
			table[taille-indiceTete] = element;
		}
		taille++;
	}
} 
