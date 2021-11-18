import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class JuegoRuletaFortuna {
	private static final Scanner scanner = new Scanner(System.in);
	private Niveles modoJuego;
	private int puntuacion;
	private String fraseAdivinar;
	private ArrayList<Character> letrasAcert;
	private ArrayList<Character> letrasNoAcert;
	private int intentos;

	public JuegoRuletaFortuna(Date fechaNacimiento, String fraseAdivinar) throws Exception {
		if (!comprobarEdad(fechaNacimiento)) {
			throw new Exception("EL jugador debe tener al menos 10 años");
		}
		modoJuego = Niveles.Novato;
		this.puntuacion = 0;
		this.fraseAdivinar = fraseAdivinar;
		this.intentos = getIntentosNivel();
	}

	public JuegoRuletaFortuna(Niveles modoJuego, Date fechaNacimiento, String fraseAdivinar) throws Exception {
		if (!comprobarEdad(fechaNacimiento)) {
			throw new Exception("EL jugador debe tener al menos 10 años");
		}
		this.modoJuego = modoJuego;
		this.fraseAdivinar = fraseAdivinar;
		this.puntuacion = 0;
		this.intentos = getIntentosNivel();
	}

	public void jugar(String fraseJuego) {
		this.setFraseAdivinar(Frase.dameFrase(modoJuego));
		System.out.println("La frase a adivinar tiene la siguiente estructura: ");
		System.out.println(showLineas(fraseJuego));

		boolean ganador = false;
		boolean perdedor = false;

		do {
			int posiblePunto = Tirada.tirar();
			System.out.println("Por " + posiblePunto + " escriba una consonante...: ");

			String letra = scanner.nextLine();
			if (Caracter.esConsonante(letra.toCharArray()[0])) {
				if (contieneLetra(letra)) {
					this.puntuacion += posiblePunto;
					this.letrasAcert.add(letra.toCharArray()[0]);
					System.out.println("Quieres comprar una vocal?.(y/n)");
					String respuestaVocal = scanner.nextLine();
					if (respuestaVocal.toLowerCase().equals('y')) {
						if (this.puntuacion > 0) {
							System.out.println("Escribe la vocal...:");
							String vocal = scanner.nextLine();
							if (Caracter.esVocal(vocal.toCharArray()[0])) {
								if (contieneLetra(vocal)) {
									this.letrasAcert.add(vocal.toCharArray()[0]);
								} else {
									this.letrasNoAcert.add(vocal.toCharArray()[0]);
								}
								this.puntuacion -= 10;
							}
						}else {
							System.out.println("No tienes puntos suficientes....");
						}

					}

				} else {
					this.letrasNoAcert.add(letra.toCharArray()[0]);
				}
			} else {
				System.out.println("La letra " + letra + " no es una consonante...");
			}
			this.mostrarInfoResultado();
			System.out.println("Quieres decir la frase completa (y/n):");
			String respuesta = scanner.nextLine();
			if (respuesta.toLowerCase().equals('y')) {
				System.out.println("Escribe la frase...:");
				String frase = scanner.nextLine();
				ganador = this.esGanador(frase);

			}
			this.intentos--;
			perdedor = this.esPerdedor();

		} while (perdedor == true || ganador == true);

	}

	private String showLineas(String frase) {
		String resultado = "";
		char[] caracteresPalabras = frase.toCharArray();
		for (int i = 0; i < caracteresPalabras.length; i++) {
			if (caracteresPalabras[i] == ' ') {
				resultado += "  ";
			} else {
				resultado += "_ ";
			}

		}

		return resultado;
	}

	private boolean contieneLetra(String letra) {

		return fraseAdivinar.contains(letra);

	}

	private String printLetrasAcertadas(String fraseAdivinar) {
		String resultado = "";
		for (int i = 0; i < fraseAdivinar.length(); i++) {
			if (fraseAdivinar.charAt(i) == ' ') {
				resultado += "  ";
			}
			if (this.letrasAcert.contains(fraseAdivinar.charAt(i))) {
				resultado += fraseAdivinar.charAt(i);
			} else {
				resultado += "_ ";
			}
		}

		return resultado;
	}

	private boolean comprobarEdad(Date fechaNacimiento) {
		SimpleDateFormat getYearFormat = new SimpleDateFormat("yyyy");
		String edadAño = getYearFormat.format(fechaNacimiento);
		Date fechaActual = new Date();
		String añoActual = getYearFormat.format(fechaActual);
		return (Integer.parseInt(edadAño) - Integer.parseInt(añoActual)) >= 10;
	}

	public void mostrarInfoResultado() {
		System.out.println("*******************************");
		System.out.println("********Información************");
		System.out.println("*******************************");

		this.printLetrasAcertadas(fraseAdivinar);
		System.out.println("*******************************");

		System.out.println("Letras acertadas: ");

		String letrasAcertadasMostrar = "";
		for (int i = 0; i < this.letrasAcert.size(); i++) {
			if (i + 1 == this.letrasAcert.size()) {
				letrasAcertadasMostrar += this.letrasAcert.get(i) + "";
			} else {
				letrasAcertadasMostrar += this.letrasAcert.get(i) + ", ";
			}
		}

		System.out.println(letrasAcertadasMostrar);
		System.out.println("Letras fallidas: ");

		String letrasFallidasMostrar = "";
		for (int i = 0; i < this.letrasNoAcert.size(); i++) {
			if (i + 1 == this.letrasNoAcert.size()) {
				letrasFallidasMostrar += this.letrasNoAcert.get(i) + "";
			} else {
				letrasFallidasMostrar += this.letrasNoAcert.get(i) + ", ";
			}
		}

		System.out.println("Intentos restantes: ");
		System.out.println(this.intentos);

		System.out.println(letrasFallidasMostrar);
		System.out.println("Puntuación obtenida: ");
		System.out.print(this.getIntentos());
	}

	public boolean esGanador(String fraseComprobar) {
		return fraseAdivinar.equals(fraseComprobar);
	}

	public boolean esPerdedor() {
		return this.getIntentos() <= 0;
	}

	private int getIntentosNivel() {
		int intentos = 0;
		if (Niveles.Novato.equals(modoJuego)) {
			intentos = 10;
		} else if (Niveles.Medio.equals(modoJuego)) {
			intentos = 8;
		} else {
			intentos = 5;
		}
		return intentos;

	}

	public Niveles getModoJuego() {
		return modoJuego;
	}

	public void setModoJuego(Niveles modoJuego) {
		this.modoJuego = modoJuego;
	}

	public int getPuntuacion() {
		return puntuacion;
	}

	public void setPuntuacion(int puntuacion) {
		this.puntuacion = puntuacion;
	}

	public String getFraseAdivinar() {
		return fraseAdivinar;
	}

	public void setFraseAdivinar(String fraseAdivinar) {
		this.fraseAdivinar = fraseAdivinar;
	}

	public ArrayList<Character> getLetrasAcert() {
		return letrasAcert;
	}

	public void setLetrasAcert(ArrayList<Character> letrasAcert) {
		this.letrasAcert = letrasAcert;
	}

	public ArrayList<Character> getLetrasNoAcert() {
		return letrasNoAcert;
	}

	public void setLetrasNoAcert(ArrayList<Character> letrasNoAcert) {
		this.letrasNoAcert = letrasNoAcert;
	}

	public int getIntentos() {
		return intentos;
	}

	public void setIntentos(int intentos) {
		this.intentos = intentos;
	}

	@Override
	public String toString() {
		return "JuegoRuletaFortuna [modoJuego=" + modoJuego + ", puntuacion=" + puntuacion + ", fraseAdivinar="
				+ fraseAdivinar + ", letrasAcert=" + letrasAcert + ", letrasNoAcert=" + letrasNoAcert + ", intentos="
				+ intentos + "]";
	}

}
