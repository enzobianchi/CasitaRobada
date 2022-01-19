package ar.edu.unlu.casitarobada.juego;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import ar.edu.unlu.rmimvc.observer.ObservableRemoto;

public class CasitaRobada extends ObservableRemoto implements Serializable, IJuego {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static CasitaRobada instancia;
	private Mazo mazo = new Mazo();
	private Mesa mesa = new Mesa();
	private Jugador ganador;
	private List<Jugador> jugadores = new ArrayList<>();
	private int jugadorActual;
	private int jugadorOponente;
	private Top topFive = new Top();
	private Serializador serializadorTopFive = new Serializador();
	
	public CasitaRobada (){
		try {
			topFive = (Top) serializadorTopFive.cargarObjeto("TopFive.dat");
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static CasitaRobada getInstancia() {
		if (instancia == null)
			instancia = new CasitaRobada();
		return instancia;
	}
	
	@Override
	public void repartirAJugadores() throws RemoteException {
		for (int i = 0; i < 3; i++) {
			for (Jugador jugador : jugadores) {
				Carta carta = mazo.entregarCarta();
				jugador.recibirCartas(carta);
			}
		}
	}
	
	@Override
	public void repartirALaMesa() throws RemoteException {
		for (int i = 0; i < 4; i++) {
			mesa.agregarCarta(mazo.entregarCarta());
		}
	}
	
	@Override
	public void mezclar() throws RemoteException {
		Collections.shuffle(mazo.getMazoCartas());
	}

	@Override
	public List<Jugador> getJugadores() throws RemoteException {
		return jugadores;
	}

	@Override
	public Jugador getGanador() throws RemoteException {
		if((jugadores.get(jugadorActual).getPuntos() > jugadores.get(JugadorOponente()).getPuntos())) {
			this.ganador = jugadores.get(jugadorActual);
		} else {
			this.ganador = jugadores.get(JugadorOponente());
		}
		return this.ganador;
	}

	@Override
	public void iniciarJuego() throws RemoteException {
		mezclar();
		repartirAJugadores();
		repartirALaMesa();
		jugadorActual = 0;
		this.notificarObservadores(EventosCasitaRobada.JUEGO_INICIADO);	
	}

	@Override
	public void cambiarJugador() throws RemoteException {
		jugadorActual++;
		if (jugadorActual == jugadores.size())
			jugadorActual = 0;
		this.notificarObservadores(EventosCasitaRobada.TURNO_CAMBIADO);
	}
	
	@Override
	public Jugador getJugadorActual() throws RemoteException {
		return jugadores.get(jugadorActual);
	}
	
	@Override
	public void agregarJugador(String j) throws RemoteException {
		this.jugadores.add(new Jugador(j));
		this.notificarObservadores(EventosCasitaRobada.JUGADOR_AGREGADO);
	}

	@Override
	public List<Carta> mostrarCartasMesa() throws RemoteException {
		return mesa.getCartasEnMesa();
	}

	@Override
	public List<Carta> mostrarCartasJugador() throws RemoteException {
		return jugadores.get(jugadorActual).getCartasEnMano();
	}

	@Override
	public Carta cartaArribaCasaOponente() throws RemoteException {
		return jugadores.get(JugadorOponente()).getCasa().getCartaDeArriba();
	}
	
	@Override
	public void tirarCarta(int numero) throws RemoteException {
		Carta carta = jugadores.get(jugadorActual).getCartasEnMano().get(numero - 1);
		jugadores.get(jugadorActual).tirarCarta(carta);
		mesa.agregarCarta(carta);
		notificarObservadores(EventosCasitaRobada.CARTA_TIRADA);
	}
	
	@Override
	public boolean robarCarta(int numeroMano, int numeroMesa) throws RemoteException {
		boolean b = false;
		Carta cartaMano = jugadores.get(jugadorActual).getCartasEnMano().get(numeroMano - 1);
		Carta cartaMesa = mesa.getCartasEnMesa().get(numeroMesa - 1);
		if (cartaMano.getNumero() == cartaMesa.getNumero()) {
			jugadores.get(jugadorActual).robarCarta(cartaMano,cartaMesa);
			mesa.quitarCarta(cartaMesa);
			b = true;
			notificarObservadores(EventosCasitaRobada.CARTA_ROBADA);
		}
		return b;
	}
	
	@Override
	public int JugadorOponente() throws RemoteException {
		if(jugadorActual == 0) {
			jugadorOponente = 1;
		} else {
			jugadorOponente = 0;
		}
		return jugadorOponente;
	}
	
	@Override
	public boolean robarCasa(int numero) throws RemoteException {
		boolean b = false;
		Carta cartaMano = jugadores.get(jugadorActual).getCartasEnMano().get(numero - 1);
		Jugador jugadorOp = jugadores.get(jugadorOponente);
		Jugador jugadorAct = jugadores.get(jugadorActual);
		if (cartaMano.getNumero() == jugadorOp.getCasa().getCartaDeArriba().getNumero()) {
			jugadorAct.robarCasa(cartaMano, jugadorOp.getCasa().getListadoCartas());
			int puntos = jugadorOp.getCasa().getListadoCartas().size();
			jugadorAct.sumarPuntos(puntos);
			jugadorOp.limpiarCasa();
			b = true;
		}
		return b;
	}

	@Override
	public int cantCartasEnMesa() throws RemoteException {
		return mesa.getCartasEnMesa().size();
	}

	@Override
	public int cantCartasEnMano() throws RemoteException {
		return jugadores.get(jugadorActual).getCartasEnMano().size();
	}

	@Override
	public int cantCartasMazo() throws RemoteException {
		return mazo.getMazoCartas().size();
	}
	
	@Override
	public void verificarCartas() throws RemoteException {
		if(mazo.getMazoCartas().size() > 0) {	
			if (cantCartasEnMano() == 0) {
				repartirAJugadores();
			}
		} else if (mazo.getMazoCartas().size() == 0 && (cantCartasEnMano() == 0)) {
			notificarObservadores(EventosCasitaRobada.FIN_DEL_JUEGO);
			topFive.agregar(getGanador());
			serializadorTopFive.guardarObjeto("TopFive.dat", topFive);
		}	
	}

	@Override
	public int cantidadCartasCasaOponente() throws RemoteException {
		return jugadores.get(JugadorOponente()).totalDeCartasDeLaCasa();
	}

	@Override
	public boolean soplar() throws RemoteException {
		int i = 0;
		boolean bandera = false;
		int tamañoMesa = mesa.getCartasEnMesa().size();
		Carta carta1;
		Carta carta2;
		carta1 = jugadores.get(JugadorOponente()).getUltimaCartaTirada();
		while (((tamañoMesa > i) && (bandera == false)) && (carta1 != null)) {
			carta2 = mesa.getCartasEnMesa().get(i);
			if ((carta1.getNumero() == carta2.getNumero()) && (carta1.getPalo() != carta2.getPalo())) {
				Jugador jugador = jugadores.get(jugadorActual);
				jugador.soplarCartas(carta1,carta2);
				mesa.quitarCarta(carta1);
				mesa.quitarCarta(carta2);
				bandera = true;  //Se pudo soplar.
				notificarObservadores(EventosCasitaRobada.CARTAS_SOPLADAS);
			}
			i++;
		}
		if (!bandera) {
			jugadores.get(jugadorActual).restarPunto();
			notificarObservadores(EventosCasitaRobada.CARTAS_NO_SOPLADAS);
		}
		return bandera;
	}
	
	@Override
	public int puntosGanador() throws RemoteException {
		return getGanador().getPuntos();
	}

	@Override
	public Carta cartaArribaCasa() throws RemoteException {
		return jugadores.get(jugadorActual).getCasa().getCartaDeArriba();
	}
	
	@Override
	public void verTop() throws RemoteException {
		topFive = (Top) serializadorTopFive.cargarObjeto("TopFive.dat");
		notificarObservadores(EventosCasitaRobada.VER_TOP);
	}
	
	@Override
	public ArrayList<Jugador> getTop() throws RemoteException {
		return topFive.getTop();
	}
	
	@Override
	public void cargar(IJuego juego) throws RemoteException {
		this.mesa = juego.getMesa();
		this.mazo = juego.getMazo();
		this.jugadores = juego.getJugadores();
		this.jugadorActual = juego.getJugadores().indexOf(juego.getJugadorActual());
		this.notificarObservadores(EventosCasitaRobada.CARGAR_JUEGO);
	}

	@Override
	public Mesa getMesa() throws RemoteException {
		return this.mesa;
	}

	@Override
	public Mazo getMazo() throws RemoteException {
		return this.mazo;
	}

	@Override
	public List<Carta> getCartasCasaOponente() throws RemoteException {
		return jugadores.get(jugadorOponente).getCasa().getListadoCartas();
	}

	@Override
	public List<Carta> getCartasCasa() throws RemoteException {
		return jugadores.get(jugadorActual).getCasa().getListadoCartas();
	}
}
	
	
	
	
	
	