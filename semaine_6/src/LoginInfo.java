
public class LoginInfo {
   
 	private String login;
	
	public LoginInfo(String login)throws IllegalArgumentException{
		if(login==null || login.length() != 7 ||!login.substring(0,4).equals("info"))
			throw new IllegalArgumentException("login incorrect");
		this.login = login;
	}

	public String toString(){
		return "/n"+login +"   "+login;
	}

	// renvoie un entier >= 0
	// cet entier represente de fa�on unique le login courant
	// info001 --> 0 	info002 --> 1	...	info137 --> 136
	public int hashCode(){
		String str=login.substring(4,7);
		int log=Integer.parseInt(str);
		// OBSERVEZ LES MANIPULATIONS DE CHAINES DE CARACTERES DANS LE CONSTRUCTEUR!
		// Integer.parseInt() permet de convertir une chaine de caractere en un entier!
		return log-1;

	}
}

