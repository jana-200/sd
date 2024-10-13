
public class DocumentsLRU {

	private ListeLRU<String> listeLRU;

	/**
	 * construit une liste de nombreDocuments documents : doc1 doc2 ...
	 * @param nombreDocuments
	 * @throws IllegalArgumentException il faut au moins 1 document
	 */
	public DocumentsLRU(int nombreDocuments){
		if(nombreDocuments<1){
			throw new IllegalArgumentException();
		}
		listeLRU=new ListeLRU<>();
		for (int i = 0; i < nombreDocuments; i++){
			int num=nombreDocuments-i;
			String str="doc"+num;
			listeLRU.insererEnTete(str);
		}
	}


	/**
	 * place le document passe en parametre dans la liste selon le mecanisme LRU
	 * @param document le document a ouvrir
	 * @throws IllegalArgumentException si le document est null ou ""
	 */
	public void ouvrirDocument(String document){
		if(document == null || document.equals(""))
			throw new IllegalArgumentException();

		if(!listeLRU.contient(document))
			listeLRU.supprimerDernier();
		else listeLRU.supprimer(document);
		listeLRU.insererEnTete(document);
	}
	
	
	public String toString(){
		return listeLRU.toString();
	}
	
}
