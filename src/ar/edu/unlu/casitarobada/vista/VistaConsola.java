package ar.edu.unlu.casitarobada.vista;

import java.awt.HeadlessException;
import java.rmi.RemoteException;
import java.util.Scanner;

import ar.edu.unlu.casitarobada.controlador.CasitaRobadaController;
import ar.edu.unlu.casitarobada.juego.Carta;

public class VistaConsola implements IVista {
	private CasitaRobadaController miControl;
	private Scanner teclado = new Scanner(System.in);
	
	public VistaConsola(CasitaRobadaController controlador) {
		this.miControl = controlador;
		this.miControl.setVista(this);
	}
	
	private int mostrarMenu() {
		teclado.nextLine();
		int opcion = -1;
		while (opcion < 0 || opcion > 3) {
			System.out.println("");
			System.out.println("CASITA ROBADA");
			System.out.println("-------------------");
			System.out.println("1.Agregar Jugador");
			System.out.println("2.Comenzar Partida");
			System.out.println("-------------------");
			System.out.println("0.Salir del Juego");
			opcion = teclado.nextInt();
		}
		return opcion;
	}
	@Override
	public void agregarJugador() throws RemoteException {
		if (miControl.getJugadores().size() < 2) {
			System.out.println("Ingrese nombre del jugador:");
			String jugador = teclado.nextLine();
			miControl.AgregarJugador(jugador);
		} else 
			System.out.println("Ya agrego la cantidad maxima de jugadores.");
	}
	@Override
	public void comenzarJuego() throws RemoteException {
		int opcion = -1;
		while (opcion != 0) {
			opcion = mostrarMenu();
			switch (opcion) {
			case 1:
				agregarJugador();
				break;
			case 2:
				miControl.iniciarJuego();
				break;
			}
		}
	}
	@Override
	public void mostrarCartasMesa() throws RemoteException {
		int i = 1;
		for (Carta c : miControl.mostrarCartasMesa()) {
			System.out.println(i + " = " + c.getNumero() + " " + c.getPalo());
			i++;
		}
	}
	@Override
	public void mostrarCartasJugador() throws RemoteException {
		int i = 1;
		for (Carta c : miControl.mostrarCartasJugador()) {
			System.out.println(i + " = " + c.getNumero() + " " + c.getPalo());
			i++;
		}
	}

	public int mostrarMenuJugador() throws RemoteException {
		System.out.println("--------------------------------------------------------------");
		System.out.println("--------------------Turno de " + miControl.getJugadorEnTurno().getNombre() + " ---------------");
		System.out.println("---------------------------Casa Oponente:");
		if (miControl.cantidadCartasCasaOponente() > 0) {
			System.out.println(miControl.cartaArribaCasaOponente().getCarta());
		} else {
			System.out.println("Casa vacia");
		}
		System.out.println("---------------------------Su Mano:");
		mostrarCartasJugador();
		System.out.println("------------------------------Mesa:");
		mostrarCartasMesa();
		System.out.println("");
		System.out.println("1.Tirar una carta");
		System.out.println("2.Robar una carta");
		System.out.println("3.Robar Casa");
		System.out.println("4.Soplar");
		System.out.println("");
		int opcion = teclado.nextInt();
		return opcion;
	}
	@Override
	public void tirarCarta() throws RemoteException {
		int numero;
		System.out.println("Seleccione posicion de carta a tirar: ");
		numero = teclado.nextInt();
		while ((numero < 1) && (numero > miControl.cantidadCartasEnMano())) {
			System.out.println("Posicion incorrecta, ingrese una posicion valida");
			numero = teclado.nextInt();
		}
		miControl.tirarCarta(numero);
		System.out.println("La carta fue tirada con exito");
		miControl.cambiarTurno();
	}
	@Override
	public void robarCarta() throws RemoteException {
		int numeroMano, numeroMesa;
		System.out.println("Seleccione posicion de su carta con la cual quiere robar: ");
		numeroMano = teclado.nextInt();
		while ((numeroMano < 1) || (numeroMano > miControl.cantidadCartasEnMano())) {
			System.out.println("Posicion incorrecta, ingrese una posicion valida");
			numeroMano = teclado.nextInt();
		}
		System.out.println("Seleccione posicion de la carta a robar: ");
		numeroMesa = teclado.nextInt();
		while ((numeroMesa < 1) || (numeroMesa > miControl.cantidadCartasEnMesa())) {
			System.out.println("Posicion incorrecta, ingrese una posicion valida");
			numeroMesa = teclado.nextInt();
		}
		if (miControl.robarCarta(numeroMano,numeroMesa)) {
			miControl.cambiarTurno();
			System.out.println("La carta fue robada con exito");
		} else {
			System.out.println("No se puede robar esa carta, el numero no coincide.");
		}
	}
	@Override
	public void robarCasa() throws RemoteException {
		if (miControl.listadoCartasCasaOponente().isEmpty() == false) {
			int numero;
			System.out.println("Seleccione posicion de su carta con la cual quiere robar la casa: ");
			numero = teclado.nextInt();
			while ((numero < 1) && (numero > miControl.cantidadCartasEnMano())) {
				System.out.println("Posicion incorrecta, ingrese una posicion valida");
				numero = teclado.nextInt();
			}
			if (miControl.robarCasa(numero)) {
				System.out.println("La casa fue robada con exito");
				miControl.cambiarTurno();
			} else
				System.out.println("La casa no puede ser robada, los numeros no coinciden.");
		} else {
			System.out.println("La casa del oponente se encuentra vacia");	
		}
	}
	@Override
	public void notificarMensaje(String string) {
		System.out.println(string);
	}

	public void notificarError(String string) {
		System.out.println(string);

	}
	@Override
	public void juegoIniciado() throws RemoteException {
		while(miControl.cantidadCartasEnMano() > 0) {
			int opcion = mostrarMenuJugador();
			switch (opcion) {
			case 1:
				tirarCarta();
				break;
			case 2:
				robarCarta();
				break;
			case 3:
				robarCasa();
				break;
			case 4:
				soplar();
				break;
			}
			miControl.verificarCartas();
		}
	}
	@Override
	public void soplar() throws RemoteException {
		if (miControl.soplar()) {
			System.out.println("Las cartas fueron sopladas con exito");
		} else {
			System.out.println("No se pueden soplar cartas, usted perdio 1 punto");
		}
	}
	@Override
	public void finJuego() throws RemoteException {
		System.out.println("Juego finalizado");
		System.out.println("El ganador es " + miControl.getGanador());
		miControl.puntosGanador();
		System.out.println("");
		System.out.println("Las cartas que quedaron en mesa son: ");
		mostrarCartasMesa();
	}

	@Override
	public void actualizarCartas() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void cambiaTurno() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void turno() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deshabilitarBotones() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mostrarCartasJugadorOponente() throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void habilitarBotonesJugador() throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actualizarCartasJugadorOponente() throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void habilitarBotonInicio() throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void verTop() throws HeadlessException, RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void cargaJuego() throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mostrarCartaCasaOponente() throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mostrarPuntosJugadorOponente() throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mostrarPuntosJugadorActual() throws RemoteException {
		// TODO Auto-generated method stub
		
	}	
}