package ar.edu.unlu.casitarobada.vista;

import java.awt.HeadlessException;
import java.rmi.RemoteException;

public interface IVista {

	void juegoIniciado() throws RemoteException;

	void finJuego() throws RemoteException;

	void notificarMensaje(String string);

	void comenzarJuego() throws RemoteException;
	
	void agregarJugador() throws RemoteException;

	void tirarCarta() throws RemoteException;
	
	void robarCarta() throws RemoteException;
	
	void robarCasa() throws RemoteException;
	
	void soplar() throws RemoteException;
	
	void mostrarCartasJugador() throws RemoteException;
	
	void mostrarCartasMesa() throws RemoteException;

	void actualizarCartas() throws RemoteException;

	void cambiaTurno() throws RemoteException;

	void turno() throws RemoteException;

	void deshabilitarBotones() throws RemoteException;

	void mostrarCartasJugadorOponente() throws RemoteException;

	void habilitarBotonesJugador() throws RemoteException;

	void actualizarCartasJugadorOponente() throws RemoteException;

	void habilitarBotonInicio() throws RemoteException;
	
	void verTop() throws HeadlessException, RemoteException;

	void cargaJuego() throws RemoteException;

	void mostrarCartaCasaOponente() throws RemoteException;

	void mostrarPuntosJugadorOponente() throws RemoteException;

	void mostrarPuntosJugadorActual() throws RemoteException;
}
