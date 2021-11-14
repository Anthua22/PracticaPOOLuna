
public class Partida {

	private int numPartidas;
	private Jugador jugador;
	private int puntuacion;

	/*
	 * Habr� que crear 2 constructores pq dice que: -Si no se especifican n�mero de
	 * partidas se realizar�n por defecto 3. - La puntuaci�n por defecto es 0.
	 */

//	public Partida(int nPartidas) {
//		nPartidas=numPartidas;
//		if(nPartidas=null) {
//			numPartidas=3;
//		}
//		else {
//			nPartidas=numPartidas;
//		}
//		
//		puntuacion=0;
//	}

	public Partida(Jugador jugador, int numPartidas) {
		this.jugador = jugador;
		this.numPartidas = numPartidas;
		puntuacion = 0;
	}

	public Partida(Jugador jugador) {
		this.jugador = jugador;
		numPartidas = 3;
		puntuacion = 0;
	}

}
