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
	private boolean ganador;
	
	public JuegoRuletaFortuna(Date fechaNacimiento, String frase) throws Exception {
		if (!comprobarEdad(fechaNacimiento)) {
			throw new Exception("EL jugador debe tener al menos 10 años");
		}
		modoJuego = Niveles.Novato;
		this.puntuacion = 0;
		this.fraseAdivinar = frase;
		this.intentos = getIntentosNivel();
		this.letrasAcert = new ArrayList<Character>();
		this.letrasNoAcert = new ArrayList<Character>();
		this.ganador = false;
	}

	public JuegoRuletaFortuna(Niveles modoJuego, Date fechaNacimiento) throws Exception {
		if (!comprobarEdad(fechaNacimiento)) {
			throw new Exception("EL jugador debe tener al menos 10 años");
		}
		this.modoJuego = modoJuego;
		this.fraseAdivinar = Frase.dameFrase(modoJuego);
		this.puntuacion = 0;
		this.intentos = getIntentosNivel();
		this.letrasAcert = new ArrayList<Character>();
		this.letrasNoAcert = new ArrayList<Character>();
		this.ganador = false;
	}
	


	public void jugar() {
		System.out.println("La frase a adivinar tiene la siguiente estructura: ");
		System.out.println(showLineas());
		System.out.println("*********************************************");
		boolean ganador = false;

		do {
			int posiblePunto = Tirada.tirar();
			System.out.println("Por " + posiblePunto + " puntos escriba una consonante...: ");

			String letra = scanner.nextLine();
			if (Caracter.esConsonante(letra.toCharArray()[0])) {
				if (contieneLetra(letra)) {
					this.puntuacion += posiblePunto;
					this.addLetraAcertada(letra.toCharArray()[0]);
					System.out.println("Has acertado. Quieres comprar una vocal?.(y/n)");
					String respuestaVocal = scanner.nextLine();
					if (respuestaVocal.toLowerCase().equals("y")) {
						if (this.puntuacion > 0) {
							System.out.println("Escribe una vocal...:");
							String vocal = scanner.nextLine();
							if (Caracter.esVocal(vocal.toCharArray()[0])) {
								if (contieneLetra(vocal)) {
									this.addLetraAcertada(vocal.toCharArray()[0]);
								} else {
									this.addLetraNoAcertada(vocal.toCharArray()[0]);
									System.out.println("La vocal "+vocal+" no existe");
								}
								this.puntuacion -= 10;
							}else {
								System.out.println("La letra " + vocal + " no es una vocal...");
							}
						} else {
							System.out.println("No tienes puntos suficientes....");
						}

					}

				} else {
					this.addLetraNoAcertada(letra.toCharArray()[0]);
				}
			} else {
				System.out.println("La letra " + letra + " no es una consonante...");
			}
			this.intentos--;
			this.mostrarInfoResultado();
			System.out.println("****************************************");
			System.out.println("Quieres decir la frase completa (y/n):");
			String respuesta = scanner.nextLine();
			System.out.println("****************************************");
			if (respuesta.toLowerCase().equals("y")) {
				System.out.println("Escribe la frase:");
				String frase = scanner.nextLine();
				ganador = this.esGanador(frase);
				if(!ganador) {
					System.out.println("La frase que has escrito no es la correspondiente...");
				
				}else {
					System.out.println("Has ganado la partida. La frase era: "+this.getFraseAdivinar());
					
				}
			}
			

		} while (this.puedeSeguirJugando() && !ganador);

		if(this.esPerdedor()) {
			System.out.println("Has perdido la partida...");
			this.setPuntuacion(0);
		}
	}

	private String showLineas() {
		String resultado = "";
		char[] caracteresPalabras = this.fraseAdivinar.toCharArray();
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
		String fraseAd = fraseAdivinar.toLowerCase();
		return fraseAd.contains(letra.toLowerCase());
	}

	private String printLetrasAcertadas(String fraseAdivinar) {
		String resultado = "";
		char[] letrasFrases = fraseAdivinar.toLowerCase().toCharArray();
		for (int i = 0; i < letrasFrases.length; i++) {
			if (letrasFrases[i] == ' ') {
				resultado += "  ";
			} else {
				if (this.letrasAcert.contains(letrasFrases[i])) {
					resultado += fraseAdivinar.charAt(i);
				} else {
					resultado += "_ ";
				}
			}

		}

		return resultado;
	}

	public boolean comprobarEdad(Date fechaNacimiento) {
		SimpleDateFormat getYearFormat = new SimpleDateFormat("yyyy");
		String edadAño = getYearFormat.format(fechaNacimiento);
		Date fechaActual = new Date();
		String añoActual = getYearFormat.format(fechaActual);
		return (Integer.parseInt(añoActual) - Integer.parseInt(edadAño)) >= 10;
	}

	public void mostrarInfoResultado() {
		System.out.println("*******************************");
		System.out.println("********Información************");
		System.out.println("*******************************");

		String letrasAcertadasMostrar = "";
		for (int i = 0; i < this.letrasAcert.size(); i++) {
			if (i + 1 == this.letrasAcert.size()) {
				letrasAcertadasMostrar += (this.letrasAcert.get(i) + "").toUpperCase();
			} else {
				letrasAcertadasMostrar += (this.letrasAcert.get(i) + ", ").toUpperCase();
			}
		}

		System.out.print("Letras acertadas: ");
		System.out.println(letrasAcertadasMostrar);
		System.out.println("*******************************");

		String letrasFallidasMostrar = "";
		for (int i = 0; i < this.letrasNoAcert.size(); i++) {
			if (i + 1 == this.letrasNoAcert.size()) {
				letrasFallidasMostrar += this.letrasNoAcert.get(i) + "";
			} else {
				letrasFallidasMostrar += this.letrasNoAcert.get(i) + ", ";
			}
		}

		System.out.print("Letras fallidas:");
		System.out.println(letrasFallidasMostrar.toUpperCase());
		System.out.println("*******************************");

		System.out.print("Intentos restantes: ");
		System.out.println(this.intentos);
		System.out.println("*******************************");

		System.out.print("Puntuación obtenida: ");
		System.out.println(this.getPuntuacion());
		System.out.println("*******************************");

		System.out.println("Frase");
		System.out.println(this.printLetrasAcertadas(fraseAdivinar));
		System.out.println("*******************************");
	}

	public boolean esGanador(String fraseComprobar) {
		this.ganador = fraseAdivinar.toLowerCase().equals(fraseComprobar.toLowerCase());
		return ganador;
	}

	public boolean esPerdedor() {
		return this.getIntentos() <= 0 && !this.ganador;
	}

	public boolean puedeSeguirJugando() {
		return this.intentos > 0;
	}

	private void addLetraAcertada(char letra) {
		if (!this.letrasAcert.contains(letra)) {
			this.letrasAcert.add(letra);
		}
	}

	private void addLetraNoAcertada(char letra) {
		if (!this.letrasNoAcert.contains(letra)) {
			this.letrasNoAcert.add(letra);
		}
	}

	private int getIntentosNivel() {
		int intentos = 0;
		if (Niveles.Novato.equals(modoJuego)) {
			intentos = 2;
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
		this.fraseAdivinar = Frase.dameFrase(modoJuego);
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
