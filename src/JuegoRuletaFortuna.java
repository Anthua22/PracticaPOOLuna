import java.util.ArrayList;
import java.util.Scanner;

public class JuegoRuletaFortuna {

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

}
