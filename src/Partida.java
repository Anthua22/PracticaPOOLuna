import java.io.Serializable;

public class Partida implements Serializable{

	private int numPartidas;
	private Jugador jugador;
	private int puntuacion;

	/*
	 * Habrá que crear 2 constructores pq dice que: -Si no se especifican número de
	 * partidas se realizarán por defecto 3. - La puntuación por defecto es 0.
	 */

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

	public int getNumPartidas() {
		return numPartidas;
	}

	public void setNumPartidas(int numPartidas) {
		this.numPartidas = numPartidas;
	}

	public Jugador getJugador() {
		return jugador;
	}

	public void setJugador(Jugador jugador) {
		this.jugador = jugador;
	}

	public int getPuntuacion() {
		return puntuacion;
	}

	public void setPuntuacion(int puntuacion) {
		this.puntuacion = puntuacion;
	}

	@Override
	public String toString() {
		return "Partida [numPartidas=" + numPartidas + ", jugador=" + jugador + ", puntuacion=" + puntuacion + "]";
	}

}
