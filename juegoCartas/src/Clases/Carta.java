package Clases;

import java.awt.Image;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Carta extends JLabel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int numero;
	private Palos palo;
	private static final char[] letras = { 'O', 'C', 'E', 'B' };
	private ImageIcon imagen;
	private Icon icono1;
	private Icon icono2;
	private boolean destapada;
	private static final ImageIcon detras = new ImageIcon(Carta.class.getResource("/Images/detras.png"));

	public Carta(int num, Palos pal) {
		numero = num;
		palo = pal;
		String nameUrl = "" + num + letras[pal.ordinal()] + ".png";
		imagen = new ImageIcon(Carta.class.getResource("/Images/" + nameUrl));
		icono1 = new ImageIcon(imagen.getImage().getScaledInstance(70, 100, Image.SCALE_FAST));
		icono2 = new ImageIcon(detras.getImage().getScaledInstance(70, 100, Image.SCALE_FAST));
		tapar();
		setVisible(true);
		setFocusable(true);
	}

	public int getNumero() {
		return numero;
	}

	public Palos getPalo() {
		return palo;
	}

	public void destapar() {
		setIcon(icono1);
		destapada=true;
	}

	public void tapar() {
		setIcon(icono2);
		destapada=false;
	}
	
	public String toString(){
		return numero+" "+palo.toString();
	}
	
	public boolean isDestapada() {
		return destapada;
	}
}
