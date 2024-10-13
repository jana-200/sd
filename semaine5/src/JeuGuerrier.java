
public class JeuGuerrier {
	
	public static void main(String[] args) {
		EquipeGuerriers equipe = new EquipeGuerriers(3, 10);
		int pointsDeVieDuMal = 30;
		int morts=0;

		while(pointsDeVieDuMal>0 && morts<equipe.nombreGuerriersEnVie()){
			System.out.println();
			System.out.println("L'equipe compte " +equipe.nombreGuerriersEnVie()+ " guerriers en vie");
			int perdus= lancerDe();
			Guerrier guerrier=equipe.jouer(perdus);
			int ptsVie=guerrier.getPointsDeVie();
			System.out.println("Suite au combat entre la creature du mal et le guerrier n° "+guerrier.getNumero());
			System.out.println("Le guerrier vient de perdre "+perdus+" points de vie");

			if(ptsVie>0){
				System.out.println("Il lui reste " + guerrier.getPointsDeVie()+" points de vie");
			}else{
				System.out.println("Le guerrier est mort");
				morts++;
			}

			int perdusMal=lancerDe();
			pointsDeVieDuMal=pointsDeVieDuMal-perdusMal;
			System.out.println("La creature du mal vient de perdre "+ perdusMal+" points de vie");
			if(pointsDeVieDuMal>0){
				System.out.println("Il lui reste "+pointsDeVieDuMal+" points de vie");
			}
		}

		if(morts==equipe.nombreGuerriersEnVie()) System.out.println("Tous les guerriers sont morts");
		else System.out.println("La creature du mal est morte");
	}
	
	public static int lancerDe (){
		double nombreReel;
		nombreReel = Math.random();
		return (int) (nombreReel * 6) + 1;
	}
	
}
