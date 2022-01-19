package ar.edu.unlu.casitarobada.controlador;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import ar.edu.unlu.casitarobada.juego.Carta;
import ar.edu.unlu.casitarobada.juego.CasitaRobada;
import ar.edu.unlu.casitarobada.juego.EventosCasitaRobada;
import ar.edu.unlu.casitarobada.juego.IJuego;
import ar.edu.unlu.casitarobada.juego.Jugador;
import ar.edu.unlu.casitarobada.juego.Serializador;
import ar.edu.unlu.casitarobada.vista.IVista;
import ar.edu.unlu.rmimvc.cliente.IControladorRemoto;
import ar.edu.unlu.rmimvc.observer.IObservableRemoto;


public class CasitaRobadaController implements IControladorRemoto {
		private IJuego miJuego = new CasitaRobada();
		private IVista miVista;
		private String jugador;
		private Serializador serializadorPartida = new Serializador();
		
		public void AgregarJugador(String name) {
			 try {
		            if (jugador == null) {
		                miJuego.agregarJugador(name);
		                if (getJugadores().size() == 1)
		                    this.jugador = name;
		                else
		                    if (getJugadores().size() == 2 && !getJugadores().get(0).getNombre().equals(name))
		                        this.jugador = name;
		                }
		        } catch (RemoteException e) {
		            e.printStackTrace();
		        }
		}
		
		public List<Jugador> getJugadores() {
			try {
				return miJuego.getJugadores();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}

		private boolean manejaTurno() throws RemoteException {
			if (miJuego.getJugadorActual().getNombre().equals(this.jugador))
					return true;
			return false;
		}
		
		@Override
		public void actualizar(IObservableRemoto modelo, Object cambio) throws RemoteException {
			if (cambio instanceof EventosCasitaRobada) {
				EventosCasitaRobada indice = (EventosCasitaRobada) cambio;	
				switch (indice) {
				case JUEGO_INICIADO:
					if (manejaTurno()) {
						miVista.juegoIniciado();
						miVista.notificarMensaje("Juego iniciado");
					} else {
						miVista.notificarMensaje("Juego iniciado");
						miVista.deshabilitarBotones();
						miVista.turno();
						miVista.actualizarCartasJugadorOponente();
					}
					break;
				case FIN_DEL_JUEGO:
					miVista.finJuego();
					break;
				case TURNO_CAMBIADO:
					if (manejaTurno()) {
						miVista.cambiaTurno();
						miVista.actualizarCartas();
						miVista.habilitarBotonesJugador();
					} else {
						miVista.turno();
						miVista.deshabilitarBotones();
						miVista.actualizarCartasJugadorOponente();
					}
					break;
				case JUGADOR_AGREGADO:
					if (miJuego.getJugadores().size() == 2) {
						miVista.notificarMensaje("Jugadores agregados correctamente");
						miVista.habilitarBotonInicio();
					}
					break;
				case CARTA_TIRADA:
					if (manejaTurno())
						miVista.actualizarCartas();
					break;
				case CARTA_ROBADA:
					if (manejaTurno())
						miVista.actualizarCartas();
					break;
				case CASA_ROBADA:
					if (manejaTurno())
						miVista.actualizarCartas();
					break;
				case CARTAS_SOPLADAS:
					if (manejaTurno()) {
						miVista.actualizarCartas();
					} else {
						miVista.actualizarCartasJugadorOponente();
					}
					break;
				case CARTAS_NO_SOPLADAS:
					if (manejaTurno()) {
						miVista.actualizarCartas();
					} else {
						miVista.actualizarCartasJugadorOponente();
					}
					break;
				case CARGAR_JUEGO:
					miVista.cargaJuego();
					break;
				case VER_TOP:
					miVista.verTop();
					break;
				}
			}
		}
		
		public int puntosGanador() {
			try {
				return miJuego.puntosGanador();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return 0;
		}
				
		public void cambiarTurno() {
			try {
				miJuego.cambiarJugador();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		public void iniciarJuego() {
			try {
				miJuego.iniciarJuego();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}

		public List<Carta> mostrarCartasMesa() {
			try {
				return miJuego.mostrarCartasMesa();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}

		public List<Carta> mostrarCartasJugador() {
			try {
				return miJuego.mostrarCartasJugador();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
		
		public Carta cartaArribaCasaOponente() {
			try {
				return miJuego.cartaArribaCasaOponente();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
		
		public int cantidadCartasCasaOponente() {
			try {
				return miJuego.cantidadCartasCasaOponente();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return 0;
			
		}

		public int cantidadCartasEnMano() {
			try {
				return miJuego.cantCartasEnMano();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return 0;
		}

		public void tirarCarta(int numero) {
			try {
				miJuego.tirarCarta(numero);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		public int cantidadCartasEnMesa() {
			try {
				return miJuego.cantCartasEnMesa();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return 0;
		}

		public boolean robarCarta(int numeroMano, int numeroMesa) {
			try {
				return miJuego.robarCarta(numeroMano,numeroMesa);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return false;
		}

		public boolean robarCasa(int numero) {
			try {
				return miJuego.robarCasa(numero);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return false;
		}

		public List<Carta> listadoCartasCasa() {
			try {
				return miJuego.getCartasCasa();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
		
		public List<Carta> listadoCartasCasaOponente() {
			try {
				return miJuego.getCartasCasaOponente();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}

		public int cantCartasMazo() {
			try {
				return miJuego.cantCartasMazo();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return 0;
		}

		public void verificarCartas() {
			try {
				miJuego.verificarCartas();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		public boolean soplar() {
			try {
				return miJuego.soplar();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return false;
		}

		public void setVista(IVista miVista) {
			this.miVista = miVista;
		}

		@Override
		public <T extends IObservableRemoto> void setModeloRemoto(T modeloRemoto) throws RemoteException {
			this.miJuego = (IJuego) modeloRemoto;	
		}

		public Carta cartaArribaCasa() {
			try {
				return miJuego.cartaArribaCasa();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}

		public void cargarPartida() {
			try {
				this.miJuego.cargar( (IJuego) this.serializadorPartida.cargarObjeto("CasitaRobada.dat"));
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}

		public void guardarPartida() {
			try {
				this.serializadorPartida.guardarObjeto("CasitaRobada.dat",this.miJuego);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		public void verTop() {
			try {
				miJuego.verTop();
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}

		public Jugador getJugadorEnTurno() {
			try {
				return miJuego.getJugadorActual();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}

		public Jugador getJugadorOponente() {
			try {
				return miJuego.getJugadores().get(miJuego.JugadorOponente());
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}

		public String getGanador() {
			try {
				return miJuego.getGanador().getNombre();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}

		public ArrayList<Jugador> getTop() {
			try {
				return miJuego.getTop();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
}	

	
	
