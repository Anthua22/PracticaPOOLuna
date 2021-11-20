import java.text.SimpleDateFormat;
import java.util.Scanner;

public class Main {
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyyy");

	public static void main(String[] args) throws Exception {
		Scanner scan = new Scanner(System.in);
		Jugador[] jugadores = { new Jugador("rok", "rockpass", dateFormat.parse("14/05/2002")),
				new Jugador("pepe", "passPepe1", dateFormat.parse("04/09/1992")),
				new Jugador("Mari", "passMari2", dateFormat.parse("14/09/1999")) };

		Jugador jugador = null;

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
					jugador = new Jugador(aliasRegis,passwordRegis,dateFormat.parse(fechaNacimiento));
					
				}catch(Exception ex) {
					System.err.println(ex.getMessage());
				}
				break;
			case 3:
				jugador = new Jugador();
				break;

		}
	}

	public static Jugador login(Jugador[] jugadores, String alias, String password) {
		Jugador x = null;
		for (int i = 0; i < jugadores.length; i++) {
			if (jugadores[i].getAlias().equals(alias) && jugadores[i].getPass().equals(password)) {
				x = jugadores[i];
			}
		}
		return x;
	}
}
