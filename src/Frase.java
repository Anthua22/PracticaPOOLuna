import java.util.Random;

public class Frase {

	/// A�adir mas frases siguiendo la coma y despu�s la cadena
	private static String[] frasesJuego = {"Invierno","ddd"};
	
	public static String dameFrase() {
		Random aletorio = new Random();
		int posicionAleatoria = aletorio.nextInt(4);
		return frasesJuego[posicionAleatoria];
	}
	
}
