import java.util.Random;

public class Frase {

	/// A�adir mas frases siguiendo la coma y despu�s la cadena
	private static final String[] frasesFaciles = { "Invierno", "ddd" };

	private static final String[] frasesMedias = { "Invierno", "ddd" };

	private static final String[] frasesElevadas = { "Invierno", "ddd" };

	public static String dameFrase(Niveles nivel) {
		Random aletorio = new Random();
		int posicionAleatoria = aletorio.nextInt(4);

		String frase = null;

		if (Niveles.Novato.equals(nivel)) {
			frase = frasesFaciles[posicionAleatoria];
		} else if (Niveles.Medio.equals(nivel)) {
			frase = frasesMedias[posicionAleatoria];
		} else {
			frase = frasesElevadas[posicionAleatoria];
		}

		return frase;
	}

}
