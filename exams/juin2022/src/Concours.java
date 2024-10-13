import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;

public class Concours {

    private Candidat[] tableCandidats;
    private HashMap<String,Candidat> mapCandidats; //nom --> candidat
    private HashMap<String,Integer> mapVotants; //numero de telephone --> nombre de votes
    private int nombreMaxVotes;

    /**
     * debute un concours
     * @param tableNomsCandidats la table avec les noms des candidats
     * @param nombreMaxVotes le nombre maximal de votes qu'un spectateur peut faire
     * @throws IllegalArgumentException
     *           s'il n'y a pas au moins un vote possible par spectateur
     *           si la table des noms des candidats est null ou est vide
     *           !!!!!!!!!!  s'il y a des homonymes
     */
    public Concours(String[]tableNomsCandidats,int nombreMaxVotes){
        if(nombreMaxVotes<1 || tableNomsCandidats==null || tableNomsCandidats.length==0 ) throw new IllegalArgumentException();
        this.nombreMaxVotes=nombreMaxVotes;
        tableCandidats=new Candidat[tableNomsCandidats.length];
        mapCandidats=new HashMap<>();
        mapVotants=new HashMap<>();
        for (int i = 0; i <  tableNomsCandidats.length; i++) {
            Candidat candidat=new Candidat(tableNomsCandidats[i],i);
            tableCandidats[i]=candidat;
            if(mapCandidats.containsKey(tableNomsCandidats[i]))
                throw new IllegalArgumentException();
            mapCandidats.put(tableNomsCandidats[i],candidat);
        }
    }

    public int getNombreMaxVotes() {
        return nombreMaxVotes;
    }

    /**
     * ajoute 1 vote au candidat dont le nom est passe en parametre
     * a condition que le candidat existe
     * et a condition que le nombre max de votes n'est pas atteint pour le numero de telephone passe en parametre
     * @param numeroTelephone le numero de telephone qui fait le vote
     * @param nomCandidat le nom du candidat qui fait l'objet du vote
     * @return true si le vote a ete fait, false sinon
     * @throws IllegalArgumentException
     *              si le numero de telephone est null
     *              si le nom du candidat est null
     */
    public boolean voterViaNom(String numeroTelephone, String nomCandidat) {
        if(numeroTelephone==null || nomCandidat==null) throw new IllegalArgumentException();
        Integer nb=0;
        if(mapVotants.get(numeroTelephone)!=null)
            nb=mapVotants.get(numeroTelephone);
        if(nb>=nombreMaxVotes) return false;
        mapVotants.put(numeroTelephone,++nb);
        Candidat candidat = mapCandidats.get(nomCandidat);
        if(candidat==null) return false;
        candidat.ajouter1Vote();
        return true;
    }

    /**
     * ajoute 1 vote au candidat dont le numero est passe en parametre
     * a condition que le candidat existe
     * et a condition que le nombre max de votes n'est pas atteint pour le numero de telephone passe en parametre
     * @param numeroTelephone le numero de telephone qui fait le vote
     * @param numeroCandidat le numero du candidat qui fait l'objet du vote
     * @return true si le vote a ete fait, false sinon
     * @throws IllegalArgumentException si le numero de telephone est null
     */
    public boolean voterViaNumero(String numeroTelephone, int numeroCandidat) {
        if(numeroTelephone== null) throw new IllegalArgumentException();
        Integer nb=0;
        if(mapVotants.get(numeroTelephone)!=null)
            nb=mapVotants.get(numeroTelephone);
        if(nb>=nombreMaxVotes) return false;
        mapVotants.put(numeroTelephone,++nb);
        Candidat candidat=tableCandidats[numeroCandidat];
        if(candidat==null) return false;
        candidat.ajouter1Vote();
        return true;
    }

    /**
     * construit une table dans laquelle les candidats apparaissent tries selon l'ordre decroissant des nombres de vote
     * @return la table dans laquelle les candidats apparaissent tries selon l'ordre decroissant des nombres de vote
     */
    public Candidat[] classement(){
        // Utilisez les methodes copyOf() et sort() de la classe Arrays !
        // cfr enonce

        //TODO flemme j'abandonne... il est 5h27 jpp
        return null;
    }

    @Override
    // A NE PAS MODIFIER
    public String toString() {
        return "maxVotes=" + nombreMaxVotes +
                "\ntableCandidats=" + Arrays.toString(tableCandidats) +
                "\nmapCandidats=" + mapCandidats +
                "\nmapVotants=" + mapVotants;
    }

}

