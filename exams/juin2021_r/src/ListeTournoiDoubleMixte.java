import java.util.HashMap;

public class ListeTournoiDoubleMixte {

	private Noeud tete; 
	private Noeud queue; 
	private HashMap<Etudiant, Noeud> mapEtudiantNoeud;

	public ListeTournoiDoubleMixte() {
		mapEtudiantNoeud = new HashMap<Etudiant, Noeud>();
		tete=null;
		queue=null;
	}
	
	/**
	 * determine le nombre d'etudiants 
	 * @return le nombre d'etudiants
	 */
	public int taille () {
		return mapEtudiantNoeud.size();
	}

	/**
	 * verifie si la liste ne contient aucun etudiant
	 * @return true si la liste est vide, false sinon
	 */
	public boolean estVide () {
		return mapEtudiantNoeud.isEmpty();
	}
	
	
	/**
	 * calcule le nombre d'etudiant appartenant au bloc passe en parametre
	 * @param bloc le numero du bloc
	 * @return le nombre d'etudiant appartenant au bloc passe en parametre
	 */
	public int donnerNombreEtudiantsBloc(int bloc){
		int count=0;
		for(Etudiant et: mapEtudiantNoeud.keySet()){
			if(et.getBloc()==bloc)
				count++;
		}
		return count;
	}
	
	
	
	/**
	 * verifie si tous les etudiants sont en section info ("INFO")
	 * @return true si tous les etudiants sont en section INFO
	 */
	public boolean sontTousEnSectionInfo(){

		return sontTousEnSectionInfo(tete);
	}
	private boolean sontTousEnSectionInfo(Noeud n){
		if(n==null)return true;
		if(!n.etudiant.getSection().equals("INFO"))return false;
		return sontTousEnSectionInfo(n.suivant);
	}
	
	
	/**
	 * donne le partenaire de l'etudiant passe en parametre
	 * @param etudiant l'etudiant dont on cherche le partenaire
	 * @return le partenaire ou null si l'etudiant n'est pas present dans la liste
	 * @throws IllegalArgumentException si le parametre est null
	 */
	public Etudiant donnerPartenaire(Etudiant etudiant){
		if(etudiant==null)
			throw new IllegalArgumentException();
		if(!mapEtudiantNoeud.containsKey(etudiant))return null;
		Noeud n=mapEtudiantNoeud.get(etudiant);

		if(etudiant.getSexe()=='F')return n.precedent.etudiant;
		return n.suivant.etudiant;
	}
		
	/**
	 * ajoute les 2 etudiants en fin de liste
	 * les 2 etudiants doivent etre de sexe oppose et ne peuvent pas encore etre presents dans la liste
	 * @param etudiant1 un des etudiants du couple
	 * @param etudiant2 l'autre etudiant du couple
	 * @return true si l'ajout a pu se faire, false sinon
	 * @throws IllegalArgumentException si un des parametres est null
	 * 
	 */
	public boolean ajouterCouple (Etudiant etudiant1, Etudiant etudiant2) {
		if(etudiant1==null||etudiant2==null)
			throw new IllegalArgumentException();
		if(mapEtudiantNoeud.containsKey(etudiant1) || mapEtudiantNoeud.containsKey(etudiant2))return false;
		if(etudiant1.getSexe()==etudiant2.getSexe())return false;
		Noeud h;
		Noeud f;
		if(etudiant1.getSexe()=='F'){
			h=new Noeud(etudiant2);
			f=new Noeud(etudiant1);
			mapEtudiantNoeud.put(etudiant1, f);
			mapEtudiantNoeud.put(etudiant2, h);
		}
		else{
			h=new Noeud(etudiant1);
			f=new Noeud(etudiant2);

			mapEtudiantNoeud.put(etudiant1, h);
			mapEtudiantNoeud.put(etudiant2, f);
		}
		if(queue==null){
			tete=h;
		}
		else{
			queue.suivant=h;
			h.precedent=queue;
		}
		h.suivant=f;
		f.precedent=h;
		queue=f;

		return true;
	}


	// pour les tests
	public ListeTournoiDoubleMixte(Etudiant[] tableACopier) {	
		mapEtudiantNoeud = new HashMap<Etudiant, Noeud>();
		if(tableACopier.length==0)
			return;
		tete = new Noeud(tableACopier[0]);
		mapEtudiantNoeud.put(tableACopier[0], tete);
		Noeud prec = tete;
		for (int i = 1; i < tableACopier.length; i++) {
			Noeud nouveauNoeud = new Noeud(tableACopier[i]);
			mapEtudiantNoeud.put(tableACopier[i], nouveauNoeud);
			nouveauNoeud.precedent = prec;
			prec.suivant = nouveauNoeud;
			prec = nouveauNoeud;
		}
		queue = prec;
	}

	// pour les tests
	public String teteQueue(){
		try{
			String aRenvoyer = "(";
			Noeud baladeur = tete;
			int cpt=0;
			while (baladeur != null) {
				if(cpt==0)
					aRenvoyer += baladeur.etudiant.getNom();
				else
					aRenvoyer += ","+baladeur.etudiant.getNom();
				baladeur = baladeur.suivant;
				cpt++;
				if(cpt==100){
					return "boucle infinie";
				}
			}
			return aRenvoyer+")";
		}catch (NullPointerException e){
			return "nullPointerException";
		}
	}

	// pour les tests
	public String queueTete(){
		try{
			String aRenvoyer = "(";
			Noeud baladeur = queue;
			int cpt=0;
			while (baladeur != null) {
				if(cpt==0)
					aRenvoyer += baladeur.etudiant.getNom();
				else
					aRenvoyer += ","+baladeur.etudiant.getNom();
				baladeur = baladeur.precedent;
				cpt++;
				if(cpt==100){
					return "boucle infinie";
				}
			}
			return aRenvoyer+")";
		}catch (NullPointerException e){
			return "nullPointerException";
		}
	}

	// Classe interne Noeud
	private class Noeud{
		
		private Etudiant etudiant;
		private Noeud suivant;
		private Noeud precedent;

		private Noeud(Etudiant etudiant) {
			this(null, etudiant, null);
		}

		private Noeud(Noeud precedent, Etudiant etudiant, Noeud suivant) {
			this.etudiant = etudiant;
			this.suivant = suivant;
			this.precedent = precedent;
		}
	}

}
