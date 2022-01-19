package ar.edu.unlu.casitarobada;

import java.awt.EventQueue;
import ar.edu.unlu.casitarobada.controlador.CasitaRobadaController;
import ar.edu.unlu.casitarobada.vista.IVista;
import ar.edu.unlu.casitarobada.vista.VentanaJuego;
import ar.edu.unlu.casitarobada.vista.VistaConsola;

public class Aplicacion {
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				CasitaRobadaController controlador = new CasitaRobadaController();
				IVista vista = new VistaConsola(controlador);
				try {
					vista.comenzarJuego();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
			}
		});
	}
}
