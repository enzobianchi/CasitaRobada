package ar.edu.unlu.casitarobada.juego;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Casa implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Carta> listadoCartas = new ArrayList<Carta>();

	public void agregar2Cartas(Carta cartaMano, Carta cartaMesa) {
		listadoCartas.add(cartaMesa);
		listadoCartas.add(cartaMano);
	}

	public void agregarCasaOponente(List<Carta> cartasOp, Carta cartaMano) {
		for (Carta c : cartasOp) {
			listadoCartas.add(c);
		}
		listadoCartas.add(cartaMano);
	}

	public List<Carta> getListadoCartas() {
		return listadoCartas;
	}

	public Carta getCartaDeArriba() {
		return listadoCartas.get(listadoCartas.size() - 1);
	}
}