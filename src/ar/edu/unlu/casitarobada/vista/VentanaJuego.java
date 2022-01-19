package ar.edu.unlu.casitarobada.vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import ar.edu.unlu.casitarobada.controlador.CasitaRobadaController;
import ar.edu.unlu.casitarobada.vistaGUI.CartaClickeable;
import ar.edu.unlu.casitarobada.vistaGUI.CartaGrafica;
import ar.edu.unlu.casitarobada.vistaGUI.Clickeable;
import java.awt.Font;
import javax.swing.JTable;


public class VentanaJuego extends JFrame implements Clickeable, IVista {

	private static final long serialVersionUID = 1L;
	protected static final int BTN_AGREGAR = 1;
	protected static final int BTN_INICIAR = 2;
	protected static final int BTN_ROBAR = 3;
	protected static final int BTN_TIRAR = 4;
	protected static final int BTN_ROBARCASA = 5;
	protected static final int BTN_VERREGLAS = 6;
	protected static final int BTN_CARGARPARTIDA = 7;
	protected static final int BTN_GUARDARPARTIDA = 8;
	protected static final int BTN_VERTOP5 = 9;
	protected static final int BTN_SOPLAR = 10;
	
	private JMenu mnArchivo;
	private JButton btnAgregarJugador;
	private JMenuBar menuGeneral;
	private CasitaRobadaController miControl;
	private JButton btnRobar;
	private JButton btnSoplar;
	private JButton btnrobarCasa;
	private JTextField txtJugadorActual;
	private JTextField textField;
	private JPanel panelPrincipal;
	private JPanel panelContenedorCartasJugador;
	private JButton btnIniciarJuego;
	private JPanel panelBotones;
	private JMenuItem mntmVerReglas;
	private JMenuItem mntmCargarPartida;
	private JMenuItem mntmGuardarPartida;
	private JMenuItem mntmVerTop;
	private JMenu mnReglas;
	private JMenuItem mnSalir;
	private JPanel panelCartasJugador;
	private JTextField txtMensajes;
	private CartaClickeable[] cartasJugador;
	private JButton btnTirar;
	private JPanel panelCartasMesa;
	private CartaClickeable[] cartasMesa;
	private JPanel panelContenedorCartasMesa;
	private JPanel panelCasaOponente;
	private JLabel lbCasa;
	private TablaTop tablaTop;
	private JTable tblPuntos;
	private JTextField txtPuntos;

	public VentanaJuego(CasitaRobadaController controlador) {
		this.miControl = controlador;
		this.miControl.setVista(this);
		initialize();
	}

	private void initialize() {
		this.setTitle("Casita Robada");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1100, 700);
		
		menuGeneral = new JMenuBar();
		setJMenuBar(menuGeneral);
		mnArchivo = new JMenu("Archivo");
		menuGeneral.add(mnArchivo);
		setJMenuBar(menuGeneral);
		
		mnReglas = new JMenu("Reglas");
		menuGeneral.add(mnReglas);
		
		mntmVerReglas = new JMenuItem("Ver reglas");
		mntmVerReglas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					seleccionarAccion(BTN_VERREGLAS);
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		mnReglas.add(mntmVerReglas);
		
		mntmCargarPartida = new JMenuItem("Cargar Partida");
		mntmCargarPartida.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					seleccionarAccion(BTN_CARGARPARTIDA);
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		mntmGuardarPartida = new JMenuItem("Guardar partida");
		mntmGuardarPartida.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					seleccionarAccion(BTN_GUARDARPARTIDA);
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		
		mntmVerTop = new JMenuItem("Ver Top 5");
		mntmVerTop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					seleccionarAccion(BTN_VERTOP5);
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		mnSalir = new JMenuItem("Salir");
		mnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);;
			}
		});
		
		mnArchivo.add(mntmCargarPartida);
		mnArchivo.add(mntmGuardarPartida);
		mnArchivo.add(mntmVerTop);
		mnArchivo.add(mnSalir);
		
		panelPrincipal = new JPanel();
		panelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		panelPrincipal.setLayout(new BorderLayout(0, 0));
		setContentPane(panelPrincipal);

		JScrollPane scrollPane = new JScrollPane();
		panelPrincipal.add(scrollPane, BorderLayout.EAST);
		
		panelBotones = new JPanel();
		GridBagLayout gbl_panelBotones = new GridBagLayout();
		gbl_panelBotones.columnWidths = new int[] { 113, 0 };
		gbl_panelBotones.rowHeights = new int[] { 23, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_panelBotones.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_panelBotones.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0,
				1.0 };
		panelBotones.setLayout(gbl_panelBotones);
		
		JLabel lblMenuPrincipal = new JLabel("Menu Principal");
		lblMenuPrincipal.setBackground(Color.CYAN);
		lblMenuPrincipal.setOpaque(true);
		
		GridBagConstraints gbc_MenuPrincipal = new GridBagConstraints();
		gbc_MenuPrincipal.fill = GridBagConstraints.HORIZONTAL;
		gbc_MenuPrincipal.insets = new Insets(0, 0, 5, 0);
		gbc_MenuPrincipal.gridx = 0;
		gbc_MenuPrincipal.gridy = 1;
		lblMenuPrincipal.setHorizontalAlignment(SwingConstants.CENTER);
		panelBotones.add(lblMenuPrincipal, gbc_MenuPrincipal);
		
		btnAgregarJugador = new JButton("Agregar Jugador");
		btnAgregarJugador.setEnabled(false);
		btnAgregarJugador.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					seleccionarAccion(BTN_AGREGAR);
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		GridBagConstraints gbc_btnAgregarJugador = new GridBagConstraints();
		gbc_btnAgregarJugador.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnAgregarJugador.insets = new Insets(0, 0, 5, 0);
		gbc_btnAgregarJugador.gridx = 0;
		gbc_btnAgregarJugador.gridy = 2;
		panelBotones.add(btnAgregarJugador, gbc_btnAgregarJugador);
		
		btnIniciarJuego = new JButton("Comenzar Partida");
		btnIniciarJuego.setEnabled(false);
		btnIniciarJuego.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					seleccionarAccion(BTN_INICIAR);
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		GridBagConstraints gbc_btnIniciarJuego = new GridBagConstraints();
		gbc_btnIniciarJuego.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnIniciarJuego.insets = new Insets(0, 0, 5, 0);
		gbc_btnIniciarJuego.gridx = 0;
		gbc_btnIniciarJuego.gridy = 3;
		panelBotones.add(btnIniciarJuego, gbc_btnIniciarJuego);
		
		JLabel lblPuntos = new JLabel("Puntos");
		lblPuntos.setBackground(Color.CYAN);
		lblPuntos.setOpaque(true);
		
		GridBagConstraints gbc_Puntos = new GridBagConstraints();
		gbc_Puntos.fill = GridBagConstraints.HORIZONTAL;
		gbc_Puntos.insets = new Insets(0, 0, 5, 0);
		gbc_Puntos.gridx = 0;
		gbc_Puntos.gridy = 6;
		lblPuntos.setHorizontalAlignment(SwingConstants.CENTER);
		panelBotones.add(lblPuntos, gbc_Puntos);
		
		txtPuntos = new JTextField();
		txtPuntos.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtPuntos.setForeground(Color.BLACK);
		txtPuntos.setBackground(Color.WHITE);
		txtPuntos.setEditable(false);
		txtPuntos.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_txtPuntos = new GridBagConstraints();
		gbc_txtPuntos.insets = new Insets(0, 0, 5, 0);
		gbc_txtPuntos.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtPuntos.gridx = 0;
		gbc_txtPuntos.gridy = 7;
		panelBotones.add(txtPuntos, gbc_txtPuntos);
		
		JLabel lblMenuJugador = new JLabel("Menu Jugador");
		lblMenuJugador.setBackground(Color.CYAN);
		lblMenuJugador.setOpaque(true);
		
		GridBagConstraints gbc_menuJugador = new GridBagConstraints();
		gbc_menuJugador.fill = GridBagConstraints.HORIZONTAL;
		gbc_menuJugador.insets = new Insets(0, 0, 5, 0);
		gbc_menuJugador.gridx = 0;
		gbc_menuJugador.gridy = 9;
		lblMenuJugador.setHorizontalAlignment(SwingConstants.CENTER);
		panelBotones.add(lblMenuJugador, gbc_menuJugador);
		
		
		btnRobar = new JButton("Robar Carta");
		btnRobar.setEnabled(false);
		btnRobar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					seleccionarAccion(BTN_ROBAR);
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		GridBagConstraints gbc_btnRobar = new GridBagConstraints();
		gbc_btnRobar.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnRobar.insets = new Insets(0, 0, 5, 0);
		gbc_btnRobar.gridx = 0;
		gbc_btnRobar.gridy = 10;
		panelBotones.add(btnRobar, gbc_btnRobar);
		
		btnTirar = new JButton("Tirar Carta");
		btnTirar.setEnabled(false);
		btnTirar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					seleccionarAccion(BTN_TIRAR);
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		GridBagConstraints gbc_btnTirar = new GridBagConstraints();
		gbc_btnTirar.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnTirar.insets = new Insets(0, 0, 5, 0);
		gbc_btnTirar.gridx = 0;
		gbc_btnTirar.gridy = 11;
		panelBotones.add(btnTirar, gbc_btnTirar);
		
		btnrobarCasa = new JButton("Robar casa al oponente");
		btnrobarCasa.setEnabled(false);
		btnrobarCasa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					seleccionarAccion(BTN_ROBARCASA);
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		GridBagConstraints gbc_robarCasa = new GridBagConstraints();
		gbc_robarCasa.fill = GridBagConstraints.HORIZONTAL;
		gbc_robarCasa.insets = new Insets(1, 0, 5, 0);
		gbc_robarCasa.gridx = 0;
		gbc_robarCasa.gridy = 12;
		panelBotones.add(btnrobarCasa, gbc_robarCasa);
		
		btnSoplar = new JButton("Soplar");
		btnSoplar.setEnabled(false);
		btnSoplar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					seleccionarAccion(BTN_SOPLAR);
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		GridBagConstraints gbc_btnSoplar = new GridBagConstraints();
		gbc_btnSoplar.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnSoplar.gridx = 0;
		gbc_btnSoplar.gridy = 13;
		panelBotones.add(btnSoplar, gbc_btnSoplar);
		
		scrollPane.setViewportView(panelBotones);
		panelPrincipal.add(scrollPane, BorderLayout.WEST);
		
		JPanel panelJugadorActual = new JPanel();
		GridBagLayout gbl_panelJugadorActual = new GridBagLayout();
		gbl_panelJugadorActual.columnWidths = new int[] { 180, 86, 0, 0, 0, 0, 0, 0, 0 };
		gbl_panelJugadorActual.rowHeights = new int[] { 20, 0 };
		gbl_panelJugadorActual.columnWeights = new double[] { 1.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				Double.MIN_VALUE };
		gbl_panelJugadorActual.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
		panelJugadorActual.setLayout(gbl_panelJugadorActual);
		
		txtJugadorActual = new JTextField();
		txtJugadorActual.setForeground(Color.BLACK);
		txtJugadorActual.setBackground(Color.GREEN);
		txtJugadorActual.setEditable(false);
		txtJugadorActual.setHorizontalAlignment(SwingConstants.CENTER);
		txtJugadorActual.setText("Jugador en turno:");
		GridBagConstraints gbc_txtJugadorActual = new GridBagConstraints();
		gbc_txtJugadorActual.insets = new Insets(0, 0, 0, 5);
		gbc_txtJugadorActual.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtJugadorActual.gridx = 0;
		gbc_txtJugadorActual.gridy = 0;
		txtJugadorActual.setColumns(10);
		panelJugadorActual.add(txtJugadorActual, gbc_txtJugadorActual);
		
		textField = new JTextField();
		textField.setEditable(false);
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.gridwidth = 7;
		gbc_textField.fill = GridBagConstraints.BOTH;
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 0;
		textField.setColumns(10);
		panelJugadorActual.add(textField, gbc_textField);
		
		panelPrincipal.add(panelJugadorActual, BorderLayout.NORTH);
		
		panelCartasJugador = new JPanel();
		GridBagLayout gbl_panelCartasJugador = new GridBagLayout();
		gbl_panelCartasJugador.columnWidths = new int[] { 900, 0 };
		gbl_panelCartasJugador.rowHeights = new int[] { 50, 0, 80, 0 };
		gbl_panelCartasJugador.columnWeights = new double[] { 10, Double.MIN_VALUE };
		gbl_panelCartasJugador.rowWeights = new double[] { 1.0, 1.0, 0.0, Double.MIN_VALUE };
		panelCartasJugador.setLayout(gbl_panelCartasJugador);
		
		txtMensajes = new JTextField();
		GridBagConstraints gbc_txtMensajes = new GridBagConstraints();
		txtMensajes.setEditable(false);
		gbc_txtMensajes.insets = new Insets(0, 0, 5, 0);
		gbc_txtMensajes.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtMensajes.gridx = 0;
		gbc_txtMensajes.gridy = 0;
		panelCartasJugador.add(txtMensajes, gbc_txtMensajes);
		txtMensajes.setColumns(10);
		
		panelContenedorCartasJugador = new JPanel();
		GridBagConstraints gbc_panelContenedorCartasJugador = new GridBagConstraints();
		gbc_panelContenedorCartasJugador.anchor = GridBagConstraints.WEST;
		gbc_panelContenedorCartasJugador.fill = GridBagConstraints.VERTICAL;
		gbc_panelContenedorCartasJugador.gridx = 0;
		gbc_panelContenedorCartasJugador.gridy = 2;
		panelContenedorCartasJugador.setLayout(new GridLayout(0, 10, 5, 25));
		panelCartasJugador.add(panelContenedorCartasJugador, gbc_panelContenedorCartasJugador);
		panelPrincipal.add(panelCartasJugador, BorderLayout.SOUTH);
					
		panelCartasMesa = new JPanel();
		GridBagLayout gbl_panelCartas = new GridBagLayout();
		gbl_panelCartas.columnWidths = new int[] { 900, 0 };
		gbl_panelCartas.rowHeights = new int[] { 50, 0, 80, 0 };
		gbl_panelCartas.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_panelCartas.rowWeights = new double[] { 1.0, 1.0, 0.0, Double.MIN_VALUE };
		panelCartasMesa.setLayout(gbl_panelCartas);
		
		panelCasaOponente = new JPanel();
		GridBagConstraints gbc_panelCasaOponente = new GridBagConstraints();
		gbc_panelCasaOponente.anchor = GridBagConstraints.WEST;
		gbc_panelCasaOponente.insets = new Insets(15, 5, 5, 0);
		gbc_panelCasaOponente.fill = GridBagConstraints.VERTICAL;
		gbc_panelCasaOponente.gridx = 0;
		gbc_panelCasaOponente.gridy = 0;
		
		lbCasa = new JLabel();
		lbCasa.setFont(new Font("Cambria", Font.PLAIN, 13));
		lbCasa.setForeground(Color.BLACK);
		lbCasa.setHorizontalAlignment(SwingConstants.CENTER);
		lbCasa.setText("Casa Oponente");
		GridBagConstraints gbc_lbCasa = new GridBagConstraints();
		gbc_lbCasa.anchor = GridBagConstraints.NORTHWEST;
		gbc_lbCasa.insets = new Insets(0, 0, 0, 5);
		gbc_lbCasa.gridx = 0;
		gbc_lbCasa.gridy = 0;
		txtJugadorActual.setColumns(10);
		panelCartasMesa.add(lbCasa, gbc_lbCasa);
			
		panelCartasMesa.add(panelCasaOponente, gbc_panelCasaOponente);
		
		panelContenedorCartasMesa = new JPanel();
		GridBagConstraints gbc_panelContenedorCartasMesa = new GridBagConstraints();
		gbc_panelContenedorCartasMesa.gridx = 0;
		gbc_panelContenedorCartasMesa.gridy = 2;
		panelContenedorCartasMesa.setLayout(new GridLayout(0, 10, 5, 25));
		panelCartasMesa.add(panelContenedorCartasMesa,gbc_panelContenedorCartasMesa);
		
		panelPrincipal.add(panelCartasMesa, BorderLayout.CENTER);
	}
	
	private void seleccionarAccion(int btnAgregar) throws RemoteException  {
		switch (btnAgregar) {
		case BTN_VERREGLAS:
			VentanaReglas vr = new VentanaReglas();
			vr.setVisible(true);
			break;
		case BTN_CARGARPARTIDA:
			miControl.cargarPartida();
			break;
		case BTN_GUARDARPARTIDA:
			miControl.guardarPartida();
			break;
		case BTN_VERTOP5:
			miControl.verTop();
			break;
		case BTN_AGREGAR:
			agregarJugador();
			break;
		case BTN_INICIAR:
			miControl.iniciarJuego();
			break;
		case BTN_ROBAR:
			txtMensajes.setText("");
			robarCarta();
			break;
		case BTN_TIRAR:
			txtMensajes.setText("");
			tirarCarta();
			break;
		case BTN_ROBARCASA:
			txtMensajes.setText("");
			robarCasa();
			break;
		case BTN_SOPLAR:
			txtMensajes.setText("");
			soplar();
			break;
		}
	}	
	
	@Override
	public void turno() throws RemoteException  {
		textField.setText(miControl.getJugadorEnTurno().getNombre());
	}
	
	private CartaGrafica getImage(String archivo) {
		CartaGrafica imagen_1 = new CartaGrafica(archivo);
		imagen_1.setLayout(new BoxLayout(imagen_1, BoxLayout.X_AXIS));
		return imagen_1;

	}

	private CartaClickeable getClickedImage(String archivo) {
		CartaClickeable imagen_1 = new CartaClickeable(archivo, this);
		imagen_1.setLayout(new BoxLayout(imagen_1, BoxLayout.X_AXIS));
		return imagen_1;
		
	}

	private CartaClickeable manejaCartaClickeada() {
		for (int i = 0; i < cartasJugador.length; i++) {
			if (cartasJugador[i].isClickeada())
				return cartasJugador[i];
		}
		return null;	
	}
	
	@Override
	public void MouseClicked(Object objeto) {
		// TODO Auto-generated method stub
		CartaClickeable cartaClickeada = manejaCartaClickeada();
		if (cartaClickeada != null)
			for (int i = 0; i < cartasJugador.length; i++) {
				if (cartasJugador[i] != cartaClickeada)
					cartasJugador[i].reset();
			}
	}
	
	private int posicionCartaClickeadaJugador() {
		int aux = -1;
		for (int i = 0; i < cartasJugador.length; i++) {
			if (cartasJugador[i].isClickeada())
				aux = i;
		}
		return aux;
	}
	
	private int posicionCartaClickeadaMesa() {
		int aux = -1;
		for (int i = 0; i < cartasMesa.length; i++) {
			if (cartasMesa[i].isClickeada())
				aux = i;
		}
		return aux;
	}
	
	@Override
	public void mostrarCartasJugador() throws RemoteException {
		int cantCartas = miControl.cantidadCartasEnMano(); 
		cartasJugador = new CartaClickeable[cantCartas];
		for (int i = 0; i < cantCartas; i++) {
			cartasJugador[i] = getClickedImage(miControl.getJugadorEnTurno().getCartasEnMano().get(i).getCarta());
			panelContenedorCartasJugador.add(cartasJugador[i]);
		}
		getContentPane().validate();
		getContentPane().repaint();
	}
	
	@Override
	public void mostrarCartasJugadorOponente() throws RemoteException {
		int cantCartas = miControl.getJugadorOponente().getCartasEnMano().size();
		cartasJugador = new CartaClickeable[cantCartas];
		for (int i = 0; i < cantCartas; i++) {
			cartasJugador[i] = getClickedImage(miControl.getJugadorOponente().getCartasEnMano().get(i).getCarta());
			panelContenedorCartasJugador.add(cartasJugador[i]);
		}
		getContentPane().validate();
		getContentPane().repaint();
	}
	
	public void habilitarBotonesJugador() throws RemoteException {
		btnTirar.setEnabled(true);
		btnRobar.setEnabled(true);
		btnSoplar.setEnabled(true);
		btnrobarCasa.setEnabled(true);
	}
	
	@Override
	public void deshabilitarBotones() throws RemoteException {
		btnAgregarJugador.setEnabled(false);
		btnIniciarJuego.setEnabled(false);
		btnTirar.setEnabled(false);
		btnRobar.setEnabled(false);
		btnSoplar.setEnabled(false);
		btnrobarCasa.setEnabled(false);
	}
	
	@Override
	public void habilitarBotonInicio() throws RemoteException {
		btnIniciarJuego.setEnabled(true);
	}
	
	@Override
	public void juegoIniciado() throws RemoteException {
		btnIniciarJuego.setEnabled(false);
		habilitarBotonesJugador();
		mostrarCartasMesa();
		turno();
		mostrarCartasJugador();	
		mostrarPuntosJugadorActual();
		getContentPane().validate();
		getContentPane().repaint();
	}
	
	@Override
	public void mostrarPuntosJugadorActual() throws RemoteException {
		txtPuntos.setText(""+ miControl.getJugadorEnTurno().getPuntos());
	}
	
	@Override
	public void mostrarPuntosJugadorOponente() throws RemoteException {
		txtPuntos.setText(""+ miControl.getJugadorOponente().getPuntos());
	}

	@Override
	public void finJuego() throws RemoteException {
		deshabilitarBotones();
		txtMensajes.setText("");
		txtMensajes.setForeground(Color.GREEN);	
		txtMensajes.setText("Juego Finalizado. El ganador es " + miControl.getGanador() + " .Puntos : " + miControl.puntosGanador());
		getContentPane().validate();
		getContentPane().repaint();
		textField.setText("");
	}

	@Override
	public void notificarMensaje(String mensaje) {
		txtMensajes.setForeground(Color.GREEN);
		txtMensajes.setBackground(Color.WHITE);
		txtMensajes.setText(mensaje);
		getContentPane().validate();
		getContentPane().repaint();
	}

	@Override
	public void comenzarJuego() throws RemoteException {
		this.setVisible(true);
		txtMensajes.setBackground(Color.WHITE);
		txtMensajes.setText("");
		btnAgregarJugador.setEnabled(true);
	}

	@Override
	public void agregarJugador() throws RemoteException {
		String nombre = "";
		txtMensajes.setText("");
		nombre = JOptionPane.showInputDialog(null,"Nombre del Jugador", "Agregar Jugador", JOptionPane.INFORMATION_MESSAGE);
		if (nombre != null) 
			miControl.AgregarJugador(nombre);
		btnAgregarJugador.setEnabled(false);
		if (miControl.getJugadores().size() == 2) 
			btnIniciarJuego.setEnabled(true);
	}
		

	@Override
	public void tirarCarta() throws RemoteException {
		if (manejaCartaClickeada() != null) {
			miControl.tirarCarta(posicionCartaClickeadaJugador()+1);
			miControl.cambiarTurno();
		} else
			notificarError("Debe seleccionar una carta antes de tirar");
		getContentPane().validate();
		getContentPane().repaint();
	}
	
	public void actualizarCartas() throws RemoteException {
		panelContenedorCartasJugador.removeAll();
		panelContenedorCartasMesa.removeAll();
		panelCasaOponente.removeAll();
		mostrarCartasJugador();
		mostrarCartasMesa();
		mostrarPuntosJugadorActual();
		if (miControl.cantidadCartasCasaOponente() > 0 ) {
			panelCasaOponente.removeAll();
			mostrarCartaCasaOponente();
		}
		getContentPane().validate();
		getContentPane().repaint();
	}
	
	@Override
	public void actualizarCartasJugadorOponente() throws RemoteException {
		panelContenedorCartasJugador.removeAll();
		panelContenedorCartasMesa.removeAll();
		panelCasaOponente.removeAll();
		mostrarCartasJugadorOponente();
		mostrarCartasMesa();
		mostrarPuntosJugadorOponente();
		if (miControl.listadoCartasCasa().size() > 0 ) {
			panelCasaOponente.removeAll();
			mostrarCartaCasa();
		}
		getContentPane().validate();
		getContentPane().repaint();
	}

	private void notificarError(String error) {
		txtMensajes.setForeground(Color.RED);
		txtMensajes.setBackground(Color.WHITE);
		txtMensajes.setText(error);
		getContentPane().validate();
		getContentPane().repaint();
	}

	@Override
	public void robarCarta() throws RemoteException {
		if (posicionCartaClickeadaJugador() != -1) {
			if (posicionCartaClickeadaMesa() != -1) {
				if (miControl.robarCarta(posicionCartaClickeadaJugador()+1,posicionCartaClickeadaMesa()+1))
					miControl.cambiarTurno();
				else 
					notificarError("La carta no puede ser robada, el numero no coincide");
			} else
				notificarError("Debe seleccionar una carta de la mesa antes de robar");
		} else
			notificarError("Debe seleccionar una carta de su mano antes de robar");
		getContentPane().validate();
		getContentPane().repaint();
	}

	@Override
	public void robarCasa() throws RemoteException {
		if (manejaCartaClickeada() != null) {
			if (miControl.robarCasa(posicionCartaClickeadaJugador()+1))
				miControl.cambiarTurno();
			else
				notificarError("La casa no puede ser robada, los numeros no coinciden");
		} else
			notificarError("Debe seleccionar una carta antes de robar la casa del oponente");
		getContentPane().validate();
		getContentPane().repaint();
	}

	@Override
	public void soplar() throws RemoteException {
		if (miControl.soplar()) {
			notificarMensaje("Las cartas fueron sopladas con exito");
		} else {
			notificarError("No se pueden soplar cartas, usted perdio 1 punto");
		}
		getContentPane().validate();
		getContentPane().repaint();
	}
	
	public void cambiaTurno() throws RemoteException {
		miControl.verificarCartas();
		actualizarCartas();
		turno();
		getContentPane().validate();
		getContentPane().repaint();
	}
	
	@Override
	public void mostrarCartasMesa() throws RemoteException {
		int cantCartas = miControl.cantidadCartasEnMesa(); 
		cartasMesa = new CartaClickeable[cantCartas];
		for (int i = 0; i < cantCartas; i++) {
			cartasMesa[i] = getClickedImage(miControl.mostrarCartasMesa().get(i).getCarta());
			panelContenedorCartasMesa.add(cartasMesa[i]);
		}
		getContentPane().validate();
		getContentPane().repaint();
	}
	
	public void mostrarCartaCasaOponente () throws RemoteException {
		panelCasaOponente.add(getImage(miControl.cartaArribaCasaOponente().getCarta()));
	}
	
	public void mostrarCartaCasa () throws RemoteException {
		panelCasaOponente.add(getImage(miControl.cartaArribaCasa().getCarta()));
	}

	@Override
	public void verTop() throws HeadlessException, RemoteException {
		tablaTop = new TablaTop(miControl.getTop());
	}

	@Override
	public void cargaJuego() throws RemoteException {
		turno();
		actualizarCartas();
		btnAgregarJugador.setEnabled(false);
		btnIniciarJuego.setEnabled(false);
		habilitarBotonesJugador();
		getContentPane().validate();
		getContentPane().repaint();
	}
}
