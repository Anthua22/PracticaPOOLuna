import java.util.Date;
import java.util.Scanner;

public class Jugador {

	Scanner sc= new Scanner(System.in);
	
	private String alias;
	private String password;
	private String nombre;
	private Date fechaNac;
	private numPartidas num;
	private Niveles modoJuego;
	
	
	public Jugador(String alias, String password, Date fechaNac) throws Exception {
		this.alias=alias;
		//TODO Aqui tengo que validar que el password tiene al menos 6 carácteres 
		if(password.length() < 6) {
			throw new Exception("El password tiene que tener al menos 6 carácteres ");
		}
		if(!this.validacionFechaNacimiento(fechaNac)) {
			throw new Exception("La fecha de nacimiento es mayor a la actual");
		}
		this.password = password;
		this.fechaNac = fechaNac;
	}
	

	
	private boolean validacionFechaNacimiento(Date fechaComparar) {
		Date actual = new Date();
		return actual.after(fechaComparar);
	}
	
	
	public int getNumPart() {
		int numeroPartidas=0;
		return numeroPartidas;
	}
	
	public String getAlias() {
		
		alias= sc.next();
		return alias;
	}
	
	public String getNombre() {
		nombre= sc.next();
		return nombre;
	}
	
	public String getPass() {
		password= sc.next();
		return password;
	}
	
	@SuppressWarnings("deprecation")
	public Date getFecha() {
		fechaNac= new Date(sc.nextLine());
		return fechaNac;
	}
	
	
	//Getters y Setters
}
	

	
	