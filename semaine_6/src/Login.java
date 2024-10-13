import java.util.Objects;

public class Login {
   
 	private String login;
	
 	public Login(String login)throws IllegalArgumentException{
		if(login==null || login.length() != 7 || (!login.substring(0,4).equals("info")&&
				!login.substring(0,4).equals("mark")&&!login.substring(0,4).equals("admi")))
			throw new IllegalArgumentException("login incorrect");
		this.login = login;
	}

	public String toString(){
		return "/n"+login +"   "+login;
	}

	// renvoie un entier >= 0
	// cet entier represente de façon unique le login courant!
	public int hashCode() {
		int value=0;
		if(login.substring(0,4).equals("info")){
			String str=login.substring(4,7);
			value=Integer.parseInt(str);
			return value;
		}
		if(login.substring(0,4).equals("mark")){
			String str=login.substring(4,7);
			value=Integer.parseInt(str);
			return value+200;
		}
		if(login.substring(0,4).equals("admi")){
			String str=login.substring(4,7);
			value=Integer.parseInt(str);
			return value+400;
		}
		// OBSERVEZ LES MANIPULATIONS DE CHAINES DE CARACTERES DANS LE CONSTRUCTEUR!
		// Integer.parseInt() permet de convertir une chaine de caractere en un entier!
		return value;
	}
}

