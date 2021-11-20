import java.util.Random;

public class Frase {

	/// Añadir mas frases siguiendo la coma y después la cadena
	private static final String[] frasesFaciles = {"Buenos dias", "El invierno ha llegado", "Que vamos a comer hoy", "Que buen tiempo hace hoy"};

	private static final String[] frasesMedias = {"Emosido engañado", "El hombre que teme a la derrota ya ha sido derrotado", "Cree en ti y todo sera posible", "Siempre habra alguien que dude de ti"};

	private static final String[] frasesElevadas = {"No estoy loco mi realidad es distinta a la tuya", "La puntualidad es el arte de adivinar lo  tarde que  el otro compañero llegará", "Una reina que no confia en nadie es tan tonta como una que confia en todo el mundo", "Algunas puertas se cierran para siempre y otras se abren en los lugares menos esperados"};

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
