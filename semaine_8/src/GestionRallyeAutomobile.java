import java.util.Arrays;

public class GestionRallyeAutomobile {

    private static MonScanner monScanner= new MonScanner("pilotes.txt");
    private static RallyeAutomobile rallyeAutomobile;

    public static void main(String[] args) {

        System.out.println("*****************************************");
        System.out.println("Programme Test pour la classe Rallye Automobile");
        System.out.println("*****************************************");
        int choix = 0;

        System.out.println();
        System.out.println("entrez le nombre de pilotes");
        int nbPilotes=monScanner.nextInt();
        String[] table= new String[nbPilotes];
        for (int i = 0; i < nbPilotes; i++) {
            System.out.println("entrez le nom du pilote numéro "+i);
            String name=monScanner.next();
            table[i]=name;
        }
        rallyeAutomobile= new RallyeAutomobile(table);
        do {
            System.out.println("1 -> Afficher toute la course");
            System.out.println("2 -> Afficher le pilote en tête");
            System.out.println("3 -> Enregistrer un dépassement");
            System.out.println("4 -> Retirer un pilote de la course");
            System.out.println("5 -> Donner la position d’un pilote (encore dans la course)");
            System.out.println("6 -> Faire franchir la ligne d’arrivée au pilote de tête");
            System.out.println("7 -> Remettre un pilote dans la course (après un autre pilote)");
            System.out.println("8 -> Afficher les pilotes hors course");
            System.out.println("9 -> Afficher le classement");
            System.out.println("10 -> Vérifier si un pilote a franchi la ligne d'arrivée");



            System.out.println();
            System.out.print("Entrez votre choix : ");
            choix = monScanner.nextInt();
            switch (choix) {
                case 1:
                    afficherCourse();
                    break;
                case 2:
                    afficherPilote();
                    break;
                case 3:
                    enregistrerDepassement();
                    break;
                case 4:
                    retirerPilote();
                    break;
                case 5:
                    donnerPosition();
                    break;
                case 6:
                    franchirLigneArrivee();
                    break;
                case 7:
                    remettreEnJeu();
                    break;
                case 8:
                    afficherPilotesHorsCourse();
                    break;
                case 9:
                    afficherClassement();
                    break;
                case 10:
                    franchiOuPas();
                    break;
            }
        } while (choix >= 1 && choix <= 10 );
    }
    private static void afficherCourse(){
        System.out.println(rallyeAutomobile.afficherCourse());
    }

    private static void afficherPilote(){
        System.out.println(rallyeAutomobile.donnerPiloteEnTete());
    }

    private static void enregistrerDepassement(){
        System.out.println("Entrez le pilote qui dépasse :");
        String pilote= monScanner.next();
        if(pilote.equals(rallyeAutomobile.donnerPiloteEnTete())){
            System.out.println("ce pilote est déjà en tête");
        }
        rallyeAutomobile.depassement(pilote);
        System.out.println("done");
        System.out.println();

    }

    private static void retirerPilote(){
        System.out.println("Entrez le pilote qui sera retiré :");
        String pilote= monScanner.next();
        if(!rallyeAutomobile.mettreHorsJeu(pilote))
            System.out.println("pilote pas présent dans la course");
        else{
            rallyeAutomobile.mettreHorsJeu(pilote);
            System.out.println("done");
            System.out.println();
        }
    }

    private static void donnerPosition(){
        System.out.println("Entrez le pilote dont vous voulez la position :");
        String pilote= monScanner.next();
        if(rallyeAutomobile.donnerPosition(pilote)==-1)
            System.out.println("pas dans la course");
        else {
            System.out.println(rallyeAutomobile.donnerPosition(pilote));
            System.out.println("done");
            System.out.println();
        }
    }

    private static void franchirLigneArrivee(){
        String pilote=rallyeAutomobile.donnerPiloteEnTete();
        if(!rallyeAutomobile.finirCourse(pilote))
            System.out.println("pilote pas présent dans la course");
        else{
            rallyeAutomobile.finirCourse(pilote);
            System.out.println("done");
            System.out.println();
        }
    }

    private static void remettreEnJeu(){
        System.out.println("qui voulez vous remettre dans la course ?");
        String pilote= monScanner.next();
        System.out.println("aprés quel pilote voulez vous le remettre");
        String apres= monScanner.next();
        if(! rallyeAutomobile.remettreEnjeu(pilote, apres))
            System.out.println("no");
        else{
            rallyeAutomobile.remettreEnjeu(pilote, apres);
            System.out.println("done");
            System.out.println();
        }
    }

    private static void afficherPilotesHorsCourse(){
        if(rallyeAutomobile.affficherPilotesHorsCourse().isEmpty())
            System.out.println("pas de pilotes hors course");
        else{
            System.out.println(rallyeAutomobile.affficherPilotesHorsCourse());
            System.out.println("done");
            System.out.println();
        }
    }

    private static void afficherClassement(){
        System.out.println(rallyeAutomobile.afficherClassement());
        System.out.println("done");
        System.out.println();
    }

    private static void franchiOuPas(){
        System.out.println("Quel pilote voulez vous vérifier ? ");
        String pilote= monScanner.next();
        if(rallyeAutomobile.afficherClassement().contains(pilote))
            System.out.println("oui il a frnachi al ligne");
        else System.out.println("no ....");
    }
}
