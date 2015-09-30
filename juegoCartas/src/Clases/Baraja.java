package Clases;

public class Baraja {

	private Carta[] baraja;
	private final Palos[] palos = Palos.values();

	public Baraja(int q) {
		iniciarBaraja(q);
	}

	public void iniciarBaraja(int q) {
		baraja = new Carta[q];
		int num = 0;
		Palos pal;
		for (int i = 0; i < 4; i++) {
			pal = palos[i];
			for (int x = 1; x <= 12; x++) {
				num = x;
				if (q == 40 && (num == 8 || num == 9))
					continue;
				baraja[(i * 12) + (x - 1)] = new Carta(num, pal);
			}
		}
		if (q > 48) {
			for (int i = 48; i < baraja.length; i++) {
				baraja[i] = new Carta(0, null);
			}
		}
	}

	public void Barajar(int q) {
		int rand;
		Carta inter;
		for (int x = 0; x <= q; x++) {
			for (int i = 0; i < baraja.length; i++) {
				rand = (int) (Math.random() * baraja.length);
				inter = baraja[i];
				baraja[i] = baraja[rand];
				baraja[rand] = inter;
			}
		}
	}

	public Carta[] getBaraja() {
		return baraja;
	}

	public Carta extCartaArriva() {
		Carta ext = null;
		for (int i = 0; i < baraja.length; i++) {
			if (baraja[i] != null) {
				ext = baraja[i];
				baraja[i] = null;
				break;
			}
		}
		return ext;
	}

	public Carta extCartaAbajo() {
		Carta ext = null;
		for (int i = baraja.length; i <= 0; i--) {
			if (baraja[i] != null) {
				ext = baraja[i];
				baraja[i] = null;
				break;
			}
		}
		return ext;
	}

	public Carta extCarta(int n) {
		Carta ext = baraja[n];
		baraja[n] = null;
		return ext;
	}

	public boolean isEmpty() {
		for (int i = 0; i < baraja.length; i++) {
			if (baraja[i] != null) {
				return false;
			}
		}
		return true;
	}
}