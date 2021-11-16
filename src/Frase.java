import java.util.Random;

public class Frase {

	/// Añadir mas frases siguiendo la coma y después la cadena
	private static String[] frasesJuego = {"Invierno","ddd"};
	
	public static String dameFrase() {
		Random aletorio = new Random();
		int posicionAleatoria = aletorio.nextInt(4);
		return frasesJuego[posicionAleatoria];
	}
	
}
