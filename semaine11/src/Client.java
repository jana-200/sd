public class Client {
    private String nom;
    private int priorite;

    public Client(String nom, int priorite) {
        this.nom = nom;
        this.priorite = priorite;
    }

    public String getNom() {
        return nom;
    }

    public int getPriorite() {
        return priorite;
    }

    public void setPriorite(int priorite) {
        if(priorite<0)
            throw new IllegalArgumentException();
        this.priorite = priorite;
    }

    @Override
    public String toString() {
        return "Client [ nom= " + nom + ", priorite=" + priorite + ']';
    }
}
