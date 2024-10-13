public class ClientEnAttente extends Client {

    private static int nombreSuivant=1;
    private int nombreArrivee;

    public ClientEnAttente(String nom, int priorite) {
        super(nom,priorite);
        nombreArrivee=nombreSuivant;
        nombreSuivant++;
    }


    public int getNombreArrivee() {
        return nombreArrivee;
    }

    @Override
    public String toString() {

        return super.toString() ;
    }
}
