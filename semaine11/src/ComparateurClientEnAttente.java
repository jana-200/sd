import java.util.Comparator;

public class ComparateurClientEnAttente implements Comparator<ClientEnAttente> {
    @Override
    public int compare(ClientEnAttente o1, ClientEnAttente o2) {
        if(o1.getPriorite()>o2.getPriorite()) return -1;
        if(o1.getNombreArrivee()<o2.getNombreArrivee()) return -1;
        return 1;
    }
}
