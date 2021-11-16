import java.util.Random;

public class Tirada {

	private static int[] puntuaciones = { 10, 20, 30, 40, 0 };

	public static int tirar() {
		Random aletorio = new Random();
		int posicionAleatoria = aletorio.nextInt(4);
		return puntuaciones[posicionAleatoria];
	}
}
