import java.util.Date;
import java.util.Scanner;

public class Jugador {


	private String alias;
	private String password;
	private Date fechaNac;

	public Jugador(String alias, String password, Date fechaNac) throws Exception {
		this.alias = alias;
		// TODO Aqui tengo que validar que el password tiene al menos 6 carácteres
		if (password.length() < 6) {
			throw new Exception("El password tiene que tener al menos 6 carácteres ");
		}
		if (!this.validacionFechaNacimiento(fechaNac)) {
			throw new Exception("La fecha de nacimiento es mayor a la actual");
		}
		this.password = password;
		this.fechaNac = fechaNac;
	}
	
	public Jugador() {
		this.alias = "invitado";
		this.password = "invitado";
	}

	private boolean validacionFechaNacimiento(Date fechaComparar) {
		Date actual = new Date();
		return actual.after(fechaComparar);
	}

	

	public String getAlias() {
		return alias;
	}


	public String getPass() {
		return password;
	}


	public Date getFecha() {
		return fechaNac;
	}

	@Override
	public String toString() {
		return "Jugador [alias=" + alias + ", password=" + password + ", fechaNac=" + fechaNac + "]";
	}

	// Getters y Setters
	
	
	

}
