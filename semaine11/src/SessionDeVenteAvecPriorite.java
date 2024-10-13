import java.util.*;

public class SessionDeVenteAvecPriorite {

    private PriorityQueue<ClientEnAttente> fileAttente;
    private HashSet<Client> ensembleClientsActuellementDansFile;
    private HashMap<Client, CommandeAvePriorite> mapClientCommande;
    private ArrayList<CommandeAvePriorite> listeCommandes;
    private int nombreCasiersRestants;
    public final static int MAX_CASIERS_CLIENT = 3;

    public SessionDeVenteAvecPriorite(int nombreCasiersMisEnVente) {
        if(nombreCasiersMisEnVente<=0)
            throw new IllegalArgumentException();
        this.nombreCasiersRestants = nombreCasiersMisEnVente;
        ComparateurClientEnAttente comp=new ComparateurClientEnAttente();
        fileAttente = new PriorityQueue<ClientEnAttente>(comp);
        ensembleClientsActuellementDansFile = new HashSet<Client>();
        mapClientCommande = new HashMap<Client, CommandeAvePriorite>();
        listeCommandes = new ArrayList<CommandeAvePriorite>();
    }

    public int getNombreCasiersRestants() {
        return nombreCasiersRestants;
    }

    public boolean placerDansFileAttente(Client client){
        if(client==null || client.equals("")) throw new IllegalArgumentException();
        if(ensembleClientsActuellementDansFile.contains(client)) return false;
        if(nombreCasiersRestants==0) return false;
        if(mapClientCommande.containsKey(client) && mapClientCommande.get(client).getNombreCasiersDemandes()>=MAX_CASIERS_CLIENT) return false;
        ensembleClientsActuellementDansFile.add(client);
        ClientEnAttente clientEnAttente= new ClientEnAttente(client.getNom(), client.getPriorite());
        fileAttente.add(clientEnAttente);
        return true;
    }

    /**
     * retire de la file d'attente le client de tete
     * @return le client de tete ou null si la file est vide
     */
    public Client selectionnerClientSuivant(){
        if(fileAttente.isEmpty())return null;
        Client client=fileAttente.peek();
        fileAttente.remove(client);
        ensembleClientsActuellementDansFile.remove(client);

        return client;

    }

    /**
     * ajoute, si possible, une nouvelle commande
     * le nombre de casiers restants doit etre suffisant pour satisfaire completement la commande
     * (il n'y a pas de commande partielle)
     * le nombre de casiers demandes ne peut depasser le max autorise
     * @param client le client qui fait la demande
     * @param nombreCasiersDemandes le nombre de casiers demandes
     * @return true si la commande a pu etre faite, false sinon
     * @throws IllegalArgumentException si le client est null ou vide
     *  	ou si le nombre de casiers demandés est <=0
     * @throws IllegalStateException si le client a deja fait une commande
     */
    public boolean passerNouvelleCommande(Client client, int nombreCasiersDemandes){
        if(client==null || client.equals("") || nombreCasiersDemandes<=0) throw new IllegalArgumentException();
        if(mapClientCommande.containsKey(client)) throw new IllegalStateException();
        if(nombreCasiersDemandes>MAX_CASIERS_CLIENT || nombreCasiersDemandes>nombreCasiersRestants)return false;
        nombreCasiersRestants-=nombreCasiersDemandes;
        CommandeAvePriorite commande= new CommandeAvePriorite(client,nombreCasiersDemandes);
        mapClientCommande.put(client,commande);
        client.setPriorite(client.getPriorite()-1);
        return listeCommandes.add(commande);
    }


    /**
     * modifie, si possible, une commande existante
     * le nombre de casiers restants doit etre suffisant
     * (il n'y a pas de commande partielle)
     * le nombre total de casiers apres ajout de ce nombre de casiers supplementaires ne peut depasser le max autorise
     * @param client le client qui veut modifier sa commande
     * @param nombreCasiersDemandesEnPlus le nombre de casiers a ajouter au nombre de casiers deja commande
     * @return true si la commande a pu etre modifiee, false sinon
     * @throws IllegalArgumentException si le client est null ou vide
     *  	ou si le nombre de casiers demandes est <= 0
     * @throws IllegalStateException si le client n'a pas encore fait de commande lors de cette session de commande
     */
    public boolean modifierCommande(Client client,int nombreCasiersDemandesEnPlus){
        if(client==null || client.equals("") || nombreCasiersDemandesEnPlus<=0) throw new IllegalArgumentException();
        if(! mapClientCommande.containsKey(client)) throw new IllegalStateException();
        int nbrTot=mapClientCommande.get(client).getNombreCasiersDemandes()+nombreCasiersDemandesEnPlus;
        if(nbrTot>nombreCasiersRestants || nbrTot>MAX_CASIERS_CLIENT)return false;
        nombreCasiersRestants-=nombreCasiersDemandesEnPlus;
        mapClientCommande.get(client).setNombreCasiersDemandes(nbrTot);
        return true;

    }

    public void cloturerSession(){
        for (ClientEnAttente client:fileAttente) {
            if(!mapClientCommande.containsKey(client))
                client.setPriorite(client.getPriorite()+1);
        }
    }

    public String toString(){
        // cette methode ne sera pas evaluee
        // elle peut-etre interessante a appeler en cas de bug
        // n'hesitez pas a la completer
        return "le nombre de casiers restants : "+ nombreCasiersRestants
                + "\nla file d'attente : "+ fileAttente +  "\nles commandes " + listeCommandes;
    }

}
