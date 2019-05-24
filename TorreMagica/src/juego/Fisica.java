package juego;

public class Fisica {
	public static boolean colision(Personajes mago, Viga[] vigas) {
		for (int i = 0; i < vigas.length; i++) {
			if (mago.getPosY() + mago.getAlto() == vigas[i].posy) {
				if (mago.getPosX() > vigas[i].bordeIz && mago.getPosX() < vigas[i].bordeDer) {
					return true;
				}
			}
		}
		return false;
	}

	public static void margen(int margen, Personajes mago) {
		if (mago.getPosY() < -5) {
			mago.inicio(margen);
		}
		if (mago.getPosY() > margen && mago.muerte == false) {
			mago.inicio(0);
		}

	}

	public static boolean congelar(Personajes enemigo, Personajes mago) {
		if (!mago.lDisparo.isEmpty()) {
			for (Disparo d : mago.lDisparo) {
				if (enemigo.getPosY() <= d.getY() && enemigo.getPosY() >= d.getY() - 80) {
					if (enemigo.getPosX() <= d.getX() && enemigo.getPosX() >= d.getX() - 50) {
						return true;
					} else if (enemigo.getPosX() <= d.getX() && enemigo.getPosX() >= d.getX() + 50) {
						return true;
					}

				}
			}
		}
		return false;
	}

	public static void colision(Personajes[] personajes, Personajes muerto, int i) {
		for (int j = i + 1; j < personajes.length; j++) {
			if (personajes[j].isEstado()) {
				if (personajes[j].getPosY() <= muerto.getPosY()
						&& personajes[j].getPosY() >= muerto.getPosY() - muerto.ancho) {
					if (personajes[j].getPosX() <= muerto.getPosX()
							&& personajes[j].getPosX() >= muerto.getPosX() - muerto.ancho) {
						personajes[j].setEstado(false);
						personajes[j].muerte = true;
						personajes[j].contacto = true;
					} else if (personajes[j].getPosX() >= muerto.getPosX()
							&& personajes[j].getPosX() <= muerto.getPosX() + muerto.ancho) {
						personajes[j].setEstado(false);
						personajes[j].muerte = true;
						personajes[j].contacto = true;
					}
				}

			}
		}

	}
}
