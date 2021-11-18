/**
 * 
 */

/**
 * @author User
 *
 */
public class Caracter {

	private static final char[] vocales = { 'A', 'E', 'I', 'O', 'U' };
	private static final char[] consonantes = { 'B', 'C', 'D', 'F', 'G', 'H', 'J', 'K', 'L', 'M', 'N', 'Ñ', 'P', 'Q', 'R',
			'S', 'T', 'V', 'X', 'Z', 'W', 'Y' };

	public static boolean esVocal(char letra) {
		String letraModificada = (letra + "").toUpperCase();
		char letraMayuscula = letraModificada.toCharArray()[0];
		boolean encontrada = false;
		for (int i = 0; i < vocales.length; i++) {
			if (vocales[i] == letraMayuscula) {
				encontrada = true;
			}
		}
		return encontrada;
	}

	public static boolean esConsonante(char consonante) {
		String letraModificada = (consonante + "").toLowerCase();
		char letraMayuscula = letraModificada.toCharArray()[0];
		boolean encontrada = false;
		for (int i = 0; i < consonantes.length; i++) {
			if (consonantes[i] == letraMayuscula) {
				encontrada = true;
			}
		}
		return encontrada;
	}
}
