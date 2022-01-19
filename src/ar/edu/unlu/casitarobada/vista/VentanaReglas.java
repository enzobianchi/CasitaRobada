package ar.edu.unlu.casitarobada.vista;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class VentanaReglas extends JFrame {

	private static final long serialVersionUID = 1L;

	private JLabel label1;
    
    private JTextArea area;
    
    private JScrollPane panel;
    
    
    public VentanaReglas() {
        this.setTitle("Reglas de juego");
        iniciar();
        alinear();
        setSize(830,400);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);
    }
    
    public void alinear() {
        panel.setBounds(0,50,800,300);
        getContentPane().add(panel);
    }
    
    public void iniciar() {
        area = new JTextArea();
        area.setEditable(false);
        area.setText("Objetivo\r\n" 
                + "El juego consiste en ligar cualquiera de las cartas que se tienen en la mano con otra de igual n�mero que se halle sobre la mesa,\r\n"
                + "o en la parte superior del pozo de cada jugador; en este �ltimo caso se le roba todo el pozo..\r\n" 
                + "\r\n"
                + "Jugadores\r\n"
                + "Pueden intervenir 2 o m�s jugadores, en forma individual o por parejas.\r\n"
                + "\r\n"
                + "Inicio del juego\r\n"
                + "Sorteado el jugador que ha de dar, y una vez barajadas y cortadas las cartas, �ste reparte tres a cada jugador,\r\n"
                + "de una en una y de izquierda a derecha, descubriendo luego cuatro cartas boca arriba sobre la mesa.\r\n"
                + "Deja el mazo boca abajo y a su lado, ya que cuando cada jugador haya jugado sus tres cartas, debe repartir otras tres a cada uno.\r\n"
                + "\r\n"
                + "Desarrollo del juego\r\n"
                + "El juego es comenzado por el mano, quien, si tiene entre sus cartas alguna que liga con las de la mesa,\r\n"
                + "baja la suya reuni�ndola con la otra y diciendo qu� cartas liga, por ejemplo: \"cuatro con cuatro\" o \"princesa con princesa\".\r\n"
                + "Si hubiera sobre la mesa dos cartas de igual n�mero, s�lo podr� robar una de ellas.\r\n"
                + "Las dos cartas que as� re�ne las coloca hacia arriba a su lado iniciando as� su tesoro (pozo) y pasa el turno al siguiente;\r\n"
                + "las cartas que en sucesivas manos vaya reuniendo, las ubicar� sobre las que ya tenga.\r\n"
                + "\r\n"
                + "El siguiente jugador podr�, con alguna de sus cartas, ligar con cualquiera de las de la mesa o con la superior del pozo del contrario.\r\n"
                + "Esto �ltimo, siempre y cuando ambas sean de diferente n�mero, ya que si la carta\r\n"
                + "superior del pozo del contrario tuviera, por ejemplo, un cuatro, habiendo otro cuatro sobre la mesa, no podr� robarle al jugador,\r\n"
                + "debi�ndose limitar a ligar su cuatro con el de la mesa.\r\n"
                + "\r\n"
                + "Hecha cada jugada, el turno pasa al siguiente jugador, procediendo de la misma manera. Una vez que todos han jugado sus tres cartas,\r\n"
                + "el que da cartas reparte otras tantas a cada jugador.\r\n"
                + "\r\n"
                + "Acabado el mazo y jugadas las tres �ltimas cartas por cada jugador, finaliza la partida, resultando ganador aqu�l que m�s cartas haya \r\n"
                + "logrado reunir en su tesoro (pozo).\r\n"
                + "\r\n"
              );
        panel = new JScrollPane(area);
}

}
