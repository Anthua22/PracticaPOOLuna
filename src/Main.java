import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {

	public static void main(String[] args) throws ParseException {
		
//		Jugador j1= new Jugador(null, null, null);
//		
//		System.out.println("alias: " );
//		j1.getAlias();
//		System.out.println("nombre: ");
//		j1.getNombre();
//		System.out.println("contraseña: "); 
//		j1.getPass();
//		System.out.println("fecha de nacimiento: ");
//		j1.getFecha();
//
//		System.out.println("numero de partidas: " + j1.getNumPart());
		Date actual = new Date();
		SimpleDateFormat sdformat = new 
                SimpleDateFormat("yyyy-MM-dd");
		Date fechaComparar = sdformat.parse("2020-01-15");
		 System.out.print(actual.after(fechaComparar));
	}

}
