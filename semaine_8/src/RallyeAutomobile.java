public class RallyeAutomobile {
    private ListeSDImpl<String> liste;
    private ListeSDImpl<String> removed;

    private ListeSDImpl<String> classement;




    public RallyeAutomobile(String[] lesPilotes) {
        liste = new ListeSDImpl<>(lesPilotes);
        removed= new ListeSDImpl<>();
        classement= new ListeSDImpl<>();
    }
    public String afficherCourse(){
        return liste.toString();
    }
    public String affficherPilotesHorsCourse(){
        return removed.toString();
    }

    public String afficherClassement(){
        return classement.toString();
    }

    public String donnerPiloteEnTete(){
        return liste.premier();
    }

    public boolean supprimer(String pilote){
        if(! liste.contient(pilote)) return false;
        return liste.supprimer(pilote);
    }

    public boolean mettreHorsJeu(String pilote){
        if(! liste.contient(pilote)) return false;
        removed.insererEnTete(pilote);
        return liste.supprimer(pilote);
    }

    public boolean finirCourse(String pilote){
        if(!liste.contient(pilote)) return false;
        classement.insererEnTete(pilote);
        return liste.supprimer(pilote);
    }


    public void depassement(String pilote){
        String prec=liste.donnerPrecedent(pilote);
        liste.permuter(prec, pilote);
    }

    public int donnerPosition(String pilote){
        if(!liste.contient(pilote)) return -1;
        int n=1;
        for (String str:liste) {
            if(!str.equals(pilote))
                n++;
            else break;
        }
        return n;
    }

    public boolean remettreEnjeu(String pilote, String apresQui){
        if(removed.contient(pilote) && liste.contient(apresQui)){
            liste.insererApres(pilote, apresQui);
            return true;
        }
        return false;
    }

}
