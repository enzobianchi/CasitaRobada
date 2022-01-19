package ar.edu.unlu.casitarobada.juego;

import java.io.Serializable;

public class Carta implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int numero;
	private PaloCarta palo;
	private PaloCarta[] palos = { PaloCarta.BASTO, PaloCarta.ESPADA, PaloCarta.ORO, PaloCarta.COPA };

	public Carta(int numero, PaloCarta palo) {
		this.numero = numero;
		this.palo = palo;
	}

	public int getNumero() {
		return numero;
	}

	public PaloCarta getPalo() {
		return palo;
	}

	public String getCarta() {
		return (numero + "-" + palo.toString());
	}
}
