import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyyy");
	private static final Scanner scan = new Scanner(System.in);

	public static void main(String[] args) throws Exception {

		ArrayList<Jugador> jugadores = new ArrayList<Jugador>();
		jugadores.add(new Jugador("rok", "rockpass", dateFormat.parse("14/05/2002")));
		jugadores.add(new Jugador("pepe", "passPepe1", dateFormat.parse("04/09/1992")));
		jugadores.add(new Jugador("Mari", "passMari2", dateFormat.parse("14/09/1999")));

		Jugador jugador = null;
		Partida partida = null;
		JuegoRuletaFortuna juego = null;

		System.out.println("******************************************");
		System.out.println("********* La Ruleta de la fortuna ********");
		System.out.println("******************************************");

		System.out.println("Escriba 1 opción:\n1. Entrar\n2. Registrarse\n3. Entrar como invitado");
		int opcion = Integer.parseInt(scan.nextLine());
		switch (opcion) {
		case 1:
			System.out.println("Alias:");
			String alias = scan.nextLine();
			System.out.println("Password:");
			String password = scan.nextLine();
			jugador = login(jugadores, alias, password);
			if (jugador == null) {
				System.err.println("Alias o password incorrectos...");
			} else {
				System.out.println("*******************************");
				System.out.println("Bienvenido " + jugador.getAlias());
				System.out.println("*******************************");
			}
			break;
		case 2:
			try {
				System.out.println("Escribe tu alias...:");
				String aliasRegis = scan.nextLine();
				System.out.println("Escribre tu pass...:");
				String passwordRegis = scan.nextLine();
				System.out.println("Escribre tu fecha de nacimiento (dd/MM/yyyy)...:");
				String fechaNacimiento = scan.nextLine();
				jugador = new Jugador(aliasRegis, passwordRegis, dateFormat.parse(fechaNacimiento));

			} catch (Exception ex) {
				System.err.println(ex.getMessage());
			}
			break;
		case 3:
			jugador = new Jugador();
			break;

		}

		try {
			if (jugador != null) {
				if (jugador.getFechaNac() == null) {
					System.out.println("Escribre tu fecha de nacimiento (dd/MM/yyyy)...:");
					String fech = scan.nextLine();
					jugador.setFechaNac(dateFormat.parse(fech));
				}
				System.out.println("Deseas jugar más de 3 partidas o menos?(y/n)");
				String respuesta = scan.nextLine();
				if (respuesta.toLowerCase().equals("y")) {
					System.out.println("Escriba el número de partidas a jugar...:");
					int nPartidas = Integer.parseInt(scan.nextLine());
					partida = new Partida(jugador, nPartidas);
				} else {
					partida = new Partida(jugador);
				}

				Niveles level = Main.obtenerNivel();
				String fraseAdivinar = Frase.dameFrase(level);
				juego = new JuegoRuletaFortuna(level,partida.getJugador().getFechaNac(), fraseAdivinar);

				int cont = 0;

				while (partida.getNumPartidas() > 0) {
					if (cont > 0) {
						fraseAdivinar = Frase.dameFrase(level);
						juego = new JuegoRuletaFortuna(partida.getJugador().getFechaNac(), fraseAdivinar);
					}
					juego.jugar();
					partida.setPuntuacion(partida.getPuntuacion() + juego.getPuntuacion());
					cont++;
				}
			}

		} catch (Exception e) {
			System.err.println(e.getMessage());
		}

	}

	public static Niveles obtenerNivel() {
		Niveles level;
		System.out.println("Escriba la dificultad del juego (novato, medio, avanzado)...:");
		String respuesta = scan.nextLine().toLowerCase();
		switch (respuesta) {
		case "medio":
			level = Niveles.Medio;
			break;
		case "avanzado":
			level = Niveles.Avanzado;
			break;
		default:
			level = Niveles.Novato;
			break;
		}

		return level;

	}

	public static Jugador login(ArrayList<Jugador> jugadores, String alias, String password) {
		Jugador x = null;
		for (int i = 0; i < jugadores.size(); i++) {
			if (jugadores.get(i).getAlias().equals(alias) && jugadores.get(i).getPassword().equals(password)) {
				x = jugadores.get(i);
			}
		}
		return x;
	}
}
