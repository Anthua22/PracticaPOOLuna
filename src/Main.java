import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

import com.google.gson.Gson;

public class Main {
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyyy");
	private static final Scanner scan = new Scanner(System.in);
	private static final String fileJugadores = "./datos/jugadores.json";
	private static final String filePartidas = "./datos/partidas.json";

	public static void main(String[] args) throws Exception {

		ArrayList<Jugador> jugadores = new ArrayList<Jugador>();
		jugadores.add(new Jugador("rok", "rockpass", dateFormat.parse("14/05/2002")));
		jugadores.add(new Jugador("pepe", "passPepe1", dateFormat.parse("04/09/1992")));
		jugadores.add(new Jugador("Mari", "passMari2", dateFormat.parse("14/09/1999")));

		ArrayList<Partida> partidas = new ArrayList<Partida>();

		File jugFile = new File(fileJugadores);
		File partsFile = new File(filePartidas);

		if (jugFile.exists()) {
			jugadores = obtenerJugadores();
		} else {
			guardarDatos(jugadores, fileJugadores);
		}

		if (partsFile.exists()) {
			partidas = obtenerPartidas();
		}

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
				jugadores.add(jugador);
				guardarDatos(jugadores, fileJugadores);
				System.out.println("*******************************");
				System.out.println("Bienvenido " + jugador.getAlias());
				System.out.println("*******************************");

			} catch (Exception ex) {
				System.err.println(ex.getMessage());
			}
			break;
		case 3:
			jugador = new Jugador();
			break;
		default:
			jugador = new Jugador();
			break;

		}

		try {
			if (jugador != null) {
				if (jugador.getFechaNac() == null) {
					System.out.println("Escribre tu fecha de nacimiento (dd/MM/yyyy)...:");
					String fech = scan.nextLine();
					jugador.setFechaNac(dateFormat.parse(fech));
					partida = new Partida(jugador);
					
				}
				juego = new JuegoRuletaFortuna(Niveles.Novato, partida.getJugador().getFechaNac());
				System.out.println("Deseas jugar más de 3 partidas o menos?(y/n)");
				String respuesta = scan.nextLine();
				if (respuesta.toLowerCase().equals("y")) {
					System.out.println("Escriba el número de partidas a jugar (3 partidas por defecto)...:");
					int nPartidas = Integer.parseInt(scan.nextLine());
					partida.setNumPartidas(nPartidas);
				}
				Niveles level = obtenerNivel();
				String fraseAdivinar = "";
				juego.setModoJuego(level);

				int cont = 0;
				int numPart = partida.getNumPartidas();
				while (numPart > 0) {
					System.out.println("*****************************");
					System.out.println("Partida " + (cont + 1));
					System.out.println("*****************************");
					if (cont > 0) {
						fraseAdivinar = Frase.dameFrase(level);
						juego = new JuegoRuletaFortuna(partida.getJugador().getFechaNac(), fraseAdivinar);
					}
					juego.jugar();
					partida.setPuntuacion(partida.getPuntuacion() + juego.getPuntuacion());
					cont++;
					numPart--;
				}
				partidas.add(partida);

				System.out.println("*****************************");
				System.out.println("Datos de la partida:");
				System.out.println("*****************************");
				System.out.println("Jugador: " + partida.getJugador().getAlias());
				System.out.println("Numero partidas: " + partida.getNumPartidas());
				System.out.println("Puntuación: " + partida.getPuntuacion());
				System.out.println("****************************");

				System.out.println("¿Deseas guardar la partida?(y/n)");
				String respGuardar = scan.nextLine().toLowerCase();

				if (respGuardar.equals("y")) {
					guardarDatos(partidas, filePartidas);
					System.out.println("Partida guardada exitosamente");
				}
			}

		} catch (Exception e) {
			System.err.println(e.getMessage());
		}

	}

	public static Niveles obtenerNivel() {
		Niveles level;
		System.out.println("Escriba la dificultad del juego: novato, medio, avanzado (novato por defecto)");
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

	public static void guardarDatos(Object ob, String fileName) {
		try {
			Gson gson = new Gson();
			File file = new File(fileName);
			FileOutputStream fos = new FileOutputStream(file);
			OutputStreamWriter osw = new OutputStreamWriter(fos);
			Writer writer = new BufferedWriter(osw);
			// Write data using a String variable
			String jugadoresJson = gson.toJson(ob);

			writer.write(jugadoresJson);

			writer.close();
			osw.close();
			fos.close();

		} catch (Exception e) {
			System.err.println("Ha ocurrido un error guardando los datos...");
		}

	}

	public static ArrayList<Jugador> obtenerJugadores() {
		ArrayList<Jugador> jugadores = null;
		try {
			jugadores = new ArrayList<Jugador>();
			File file = new File(fileJugadores);
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			Gson gson = new Gson();
			String linea;
			String contenido = "";
			while ((linea = br.readLine()) != null) {
				contenido += linea;
			}

			Jugador[] jug = gson.fromJson(contenido, Jugador[].class);
			for (Jugador x : jug) {
				jugadores.add(x);
			}

		} catch (Exception e) {
			System.err.println("Ha ocurrido un error obteniendo los datos...");
		}

		return jugadores;

	}

	public static ArrayList<Partida> obtenerPartidas() {
		ArrayList<Partida> partidas = null;
		try {
			partidas = new ArrayList<Partida>();
			File file = new File(filePartidas);
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			Gson gson = new Gson();
			String linea;
			String contenido = "";
			while ((linea = br.readLine()) != null) {
				contenido += linea;
			}

			Partida[] parts = gson.fromJson(contenido, Partida[].class);
			for (Partida x : parts) {
				partidas.add(x);
			}

		} catch (Exception e) {
			System.err.println("Ha ocurrido un error obteniendo los datos...");
		}

		return partidas;

	}

}
