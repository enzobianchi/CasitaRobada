package ar.edu.unlu.casitarobada.juego;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import ar.edu.unlu.rmimvc.observer.IObservableRemoto;

public interface IJuego extends IObservableRemoto{
	void repartirAJugadores() throws RemoteException;

	void repartirALaMesa() throws RemoteException;

	void mezclar() throws RemoteException;

	void iniciarJuego() throws RemoteException;

	void cambiarJugador() throws RemoteException;

	void agregarJugador(String j) throws RemoteException;

	List<Carta> mostrarCartasMesa() throws RemoteException;

	List<Carta> mostrarCartasJugador() throws RemoteException;

	Carta cartaArribaCasaOponente() throws RemoteException;

	void tirarCarta(int numero) throws RemoteException;

	boolean robarCarta(int numeroMano, int numeroMesa) throws RemoteException;

	int JugadorOponente() throws RemoteException;

	boolean robarCasa(int numero) throws RemoteException;

	int cantCartasEnMesa() throws RemoteException;

	int cantCartasEnMano() throws RemoteException;

	int cantCartasMazo() throws RemoteException;

	void verificarCartas() throws RemoteException;

	int cantidadCartasCasaOponente() throws RemoteException;

	boolean soplar() throws RemoteException;

	int puntosGanador() throws RemoteException;

	Carta cartaArribaCasa() throws RemoteException;

	void cargar(IJuego cargarObjeto) throws RemoteException;

	void verTop() throws RemoteException;

	List<Carta> getCartasCasaOponente() throws RemoteException;

	List<Carta> getCartasCasa() throws RemoteException;

	Jugador getGanador() throws RemoteException;

	Mesa getMesa() throws RemoteException;

	Mazo getMazo() throws RemoteException;

	List<Jugador> getJugadores() throws RemoteException;

	Jugador getJugadorActual() throws RemoteException;

	ArrayList<Jugador> getTop() throws RemoteException;
}