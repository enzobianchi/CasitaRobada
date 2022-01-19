package ar.edu.unlu.casitarobada.juego;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Mesa implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Carta> cartasEnMesa = new ArrayList<Carta>();

	public void agregarCarta(Carta carta) {
		cartasEnMesa.add(carta);
	}

	public void mostrarMesa() {
		int i = 1;
		for (Carta c : cartasEnMesa) {
			System.out.println(i + " = " + c.getNumero() + " " + c.getPalo());
			i++;
		}
	}

	public List<Carta> getCartasEnMesa() {
		return cartasEnMesa;
	}

	public void quitarCarta(Carta cartaMesa) {
		cartasEnMesa.remove(cartaMesa);
	}
}
