package ar.edu.unlu.casitarobada.juego;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Jugador implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nombre;
	private Casa casa = new Casa();
	private List<Carta> cartaEnMano = new ArrayList<Carta>();
	private Carta ultimaCartaTirada;
	private int puntos;
	private int victorias = 0;

	public Jugador(String nombre) {
		this.puntos = 0;
		this.nombre = nombre;
	}
	
	public void sumarPuntos (int numero) {
		this.puntos = puntos + numero;
	}
	
	public void restarPunto() {
		this.puntos = puntos - 1;
	}
	
	public int getPuntos() {
		return puntos;
	}
	public void robarCarta(Carta cartaMano, Carta cartaMesa) {
		casa.agregar2Cartas(cartaMano, cartaMesa);
		cartaEnMano.remove(cartaMano);
		sumarPuntos(2);
	}

	public void robarCasa(Carta cartaMano, List<Carta> casaOp) {
		casa.agregarCasaOponente(casaOp,cartaMano);
		cartaEnMano.remove(cartaMano);
	}

	public void limpiarCasa() {
		puntos = 0;
		casa.getListadoCartas().clear();
	}

	public void tirarCarta(Carta carta) {
		ultimaCartaTirada = carta;
		cartaEnMano.remove(carta);
	}

	public void mostrarCartas() {
		int i = 1;
		for (Carta c : cartaEnMano) {
			System.out.println(i + " = " + c.getNumero() + " " + c.getPalo());
			i++;
		}
	}
	
	public Carta getUltimaCartaTirada() {
		return ultimaCartaTirada;
	}

	public int totalDeCartasDeLaCasa() {
		return casa.getListadoCartas().size();
	}

	public void recibirCartas(Carta carta) {
		cartaEnMano.add(carta);
	}

	public String getNombre() {
		return nombre;
	}

	public Casa getCasa() {
		return casa;
	}

	public int getVictorias() {
		return victorias;
	}
	
	public void sumarVictoria() {
		this.victorias++;
	}

	public void soplarCartas(Carta carta1, Carta carta2) {
		casa.agregar2Cartas(carta1, carta2);
		sumarPuntos(2);
	}

	public List<Carta> getCartasEnMano() {
		return cartaEnMano;
	}
}