package Frames;

import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import Clases.Baraja;
import Clases.Carta;

public class CarrerasPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Baraja baraja;
	private Carta[][] tablero;
	private JLabel fondo;
	private JLabel bandera;
	private ImageIcon imgfondo = new ImageIcon(CarrerasPanel.class.getResource("/Images/Tapete_Largo.jpg"));
	private ImageIcon imgbandera = new ImageIcon(CarrerasPanel.class.getResource("/Images/bandera.png"));
	private Carta[] monton;
	private Carta[] lado;
	private int largo;
	private boolean finish;

	/**
	 * Create the panel.
	 */

	public CarrerasPanel(int n) {
		largo = n;
		setFocusable(true);
		setSize(550, 650);
		addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (!finish)
					robar();
				pintar();
			}
		});
		init();
		pintar();
	}

	public void init() {
		finish = false;
		crearFondo();
		crearBaraja();
		iniciarMonton();
		crearTablero();
		baraja.Barajar(2);
		iniciarLado();
		crearBandera();
	}

	public void pintar() {
		pintarTablero();
		pintarMonton();
		pintarLado();
		pintarBaraja();
		pintarFondo();
	}

	public void crearFondo() {
		fondo = new JLabel();
		fondo.setBounds(0, 0, 550, 650);
		Icon icono = new ImageIcon(imgfondo.getImage().getScaledInstance(550, 650, Image.SCALE_FAST));
		fondo.setIcon(icono);
	}

	public void crearBandera() {
		bandera = new JLabel();
		bandera.setBounds(4, 575, 543, 75);
		Icon icono = new ImageIcon(imgbandera.getImage().getScaledInstance(550, 75, Image.SCALE_FAST));
		bandera.setIcon(icono);
	}

	public void pintarFondo() {
		pintarBandera();
		fondo.setVisible(true);
		add(fondo);
	}

	public void pintarBandera() {
		bandera.setVisible(true);
		add(bandera);
	}

	public void crearBaraja() {
		baraja = new Baraja(48);
	}

	public void crearTablero() {
		tablero = new Carta[largo + 1][4];
		for (int i = 0; i < largo + 1; i++) {
			for (int x = 0; x < 4; x++) {
				tablero[i][x] = null;
			}
		}
		for (int i = 0; i < 4; i++) {
			tablero[0][i] = baraja.extCarta(10 * (i + 1) + i * 2);
			tablero[0][i].destapar();
		}
	}

	public void pintarTablero() {
		int y = 5;
		for (int i = 0; i <= largo; i++) {
			if (i >= 1)
				y = y + 30;
			for (int x = 0; x < 4; x++) {
				if (tablero[i][x] != null) {
					tablero[i][x].setBounds(x * 80 + 225, i * 105 + 35, 70, 100);
					add(tablero[i][x]);
				}
			}
		}
	}

	public void pintarBaraja() {
		for (int i = 0; i < baraja.getBaraja().length; i++) {
			if (baraja.getBaraja()[i] != null) {
				baraja.getBaraja()[i].setBounds(i / 3 + 3, i / 3 + 3, 70, 100);
				add(baraja.getBaraja()[i]);
			}
		}
	}

	public void iniciarLado() {
		lado = new Carta[largo];
		lado[0] = null;
		for (int i = 1; i < largo; i++) {
			lado[i] = baraja.extCartaArriva();
		}
	}

	public void pintarLado() {
		for (int i = 0; i < lado.length; i++) {
			if (lado[i] != null) {
				lado[i].setBounds(60, i * 105 + 35, 70, 100);
				add(lado[i]);
			}
		}
	}

	public void iniciarMonton() {
		monton = new Carta[baraja.getBaraja().length];
		for (int i = 0; i < baraja.getBaraja().length; i++) {
			monton[i] = null;
		}
	}

	public void pintarMonton() {
		for (int i = monton.length - 1; i >= 0; i--) {
			if (monton[i] != null) {
				monton[i].setBounds(i + 90, i / 2 + 5, 70, 100);
				add(monton[i]);
			}
		}
	}

	public void robar() {
		if (!baraja.isEmpty()) {
			Carta rob = baraja.extCartaArriva();
			for (int i = 0; i < monton.length; i++) {
				if (monton[i] == null) {
					monton[i] = rob;
					monton[i].destapar();
					break;
				}
			}
			comprovarCarta(rob);
		} else {
			recogerMonton();
		}
	}

	public void comprovarCarta(Carta c) {
		c.destapar();
		if (c.getNumero() == 1) {
			while (atrasar(c.getPalo().ordinal()) == true)
				;
		} else {
			if (c.getNumero() < 10) {
				avanzar(c.getPalo().ordinal());
			} else {
				atrasar(c.getPalo().ordinal());
			}
		}
	}

	public void comprobarLado(int palo) {
		for (int i = 0; i < largo; i++) {
			if (tablero[i][palo] != null && lado[i] != null && !lado[i].isDestapada()) {
				comprovarCarta(lado[i]);
			}
		}
	}

	public boolean avanzar(int palo) {
		if (tablero[largo][palo] == null) {
			for (int i = largo; i > 0; i--) {
				tablero[i][palo] = tablero[i - 1][palo];
				tablero[i - 1][palo] = null;
			}
			checkFinish();
			comprobarLado(palo);
			return true;
		}
		return false;
	}

	public boolean atrasar(int palo) {
		if (tablero[0][palo] == null) {
			for (int i = 0; i < largo; i++) {
				tablero[i][palo] = tablero[i + 1][palo];
				tablero[i + 1][palo] = null;
			}
			return true;
		}
		return false;
	}

	public void recogerMonton() {
		for (int i = 0; i < monton.length; i++) {
			baraja.getBaraja()[i] = monton[i];
			if (baraja.getBaraja()[i] != null)
				baraja.getBaraja()[i].tapar();
			monton[i] = null;
		}
	}

	public void checkFinish() {
		for (int i = 0; i < 4; i++) {
			if (tablero[largo][i] != null) {
				finish = true;
			}
		}
	}
}
