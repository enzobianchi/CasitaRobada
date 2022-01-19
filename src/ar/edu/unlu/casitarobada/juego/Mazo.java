package ar.edu.unlu.casitarobada.juego;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Mazo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Carta> mazoCartas = new ArrayList<Carta>();

	public Mazo() {
		for (int i = 1; i <= 12; i++) {
			if (i != 8 && i != 9) {
				mazoCartas.add(new Carta(i, PaloCarta.BASTO));
				mazoCartas.add(new Carta(i, PaloCarta.ESPADA));
				mazoCartas.add(new Carta(i, PaloCarta.COPA));
				mazoCartas.add(new Carta(i, PaloCarta.ORO));
			}
		}
	}

	public void mostrarCartas() {
		for (Carta c : mazoCartas) {
			System.out.println(c.getNumero() + " " + c.getPalo());
		}
	}
	
	public Carta entregarCarta() {
		return mazoCartas.remove(0);
	}

	public List<Carta> getMazoCartas() {
		return mazoCartas;
	}
}