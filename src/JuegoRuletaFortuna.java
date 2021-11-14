import java.util.ArrayList;
import java.util.Scanner;

public class JuegoRuletaFortuna {

	private Scanner sc = new Scanner(System.in);

	private Niveles modoJuego;
	private int puntuacion;
	private String fraseAdivinar;
	private ArrayList<Character> letrasAcert;
	private ArrayList<Character> letrasNoAcert;

	public JuegoRuletaFortuna() {
		modoJuego = Niveles.Novato;
	}

	public JuegoRuletaFortuna(Niveles modoJuego) {
		this.modoJuego = modoJuego;
	}

	public void comprobarEdad(int edad) {
		System.out.println("Cual es tu edad? ");
		edad = sc.nextInt();
	}

	public JuegoRuletaFortuna(int edad) {

		if (edad < 10) {
			System.out.println("No puede jugar");
		}
	}
}
