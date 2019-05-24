package juego;

import java.awt.Color;
import java.awt.Image;

import javax.swing.ImageIcon;

import entorno.Entorno;
import entorno.InterfaceJuego;

public class Juego extends InterfaceJuego {
	// El objeto Entorno que controla el tiempo y otros
	private Entorno entorno;
	int ancho = 600, alto = 900, cantEnemigos = 5;
	Personajes [] personajes = new Personajes [cantEnemigos];
	Personajes mago;
	Sprite sprite;
	public DisparoSprite dS;
	Viga[] vigas = new Viga[11];
	boolean mantener = false, salto = false, pausa=false;
	boolean[] contra = new boolean[cantEnemigos];
	Image corazon = new ImageIcon("corazon.png").getImage();
	Image corazonRoto = new ImageIcon("corazon2.png").getImage();
	int contador = 0, vueltasSalto = 23, margen, incremento = 0, cont = 0, puntaje, puntTot = 0, velocidadEnemigo = 0,
			nivel = 1;
	cajasDeTexto tiempo, puntos, nivelT, puntosTotales;
	double milisegundo = 0;
	Animaciones animacionGanar = new Animaciones("ganar", 6, 11);
	int largoLista=-1;

	// Variables y métodos propios de cada grupo
	// ...

	Juego() {
		// Inicializa el objeto entorno
		this.entorno = new Entorno(this, "Torre Magica - Grupo Almada -Lohuandus - V0.01", ancho, alto);

		// Inicializar lo que haga falta para el juego
		// ...
		posPersonajes();
		this.mago = personajes[0];
		estados();
		this.tiempo = new cajasDeTexto((ancho / 2) - 250, alto - 210, ancho + 30, (alto - margen) + 20, 30);
		this.puntos = new cajasDeTexto((ancho / 2) - 50, alto - 210, ancho + 30, (alto - margen) + 20, 30);
		this.nivelT = new cajasDeTexto((ancho / 2) - 50, alto - 150, ancho + 30, (alto - margen) + 20, 30);
		this.puntosTotales = new cajasDeTexto((ancho / 2) - 50, alto - 180, ancho + 30, (alto - margen) + 20, 30);
		this.sprite = new Sprite(mago.getPosX(), mago.getPosY());
		this.dS = new DisparoSprite(mago.getPosX(), mago.getPosY());

		// Inicia el juego!
		this.entorno.iniciar();

	}

	Juego(int puntaje, int nivel, int velEnemigo) {
		// Inicializa el objeto entorno
		this.entorno = new Entorno(this, "Torre Magica - Grupo Apellido1 - Apellido2 -Apellido3 - V0.01", ancho, alto);

		// Inicializar lo que haga falta para el juego
		// ...
		posPersonajes();
		this.mago = personajes[0];
		estados();
		this.tiempo = new cajasDeTexto((ancho / 2) - 250, alto - 210, ancho + 30, (alto - margen) + 20, 30);
		this.puntos = new cajasDeTexto((ancho / 2) - 50, alto - 210, ancho + 30, (alto - margen) + 20, 30);
		this.nivelT = new cajasDeTexto((ancho / 2) - 50, alto - 150, ancho + 30, (alto - margen) + 20, 30);
		this.puntosTotales = new cajasDeTexto((ancho / 2) - 50, alto - 180, ancho + 30, (alto - margen) + 20, 30);
		this.sprite = new Sprite(mago.getPosX(), mago.getPosY());
		this.dS = new DisparoSprite(mago.getPosX(), mago.getPosY());
		this.puntTot = puntaje;
		this.nivel = nivel;
		this.velocidadEnemigo = velEnemigo;

		// Inicia el juego!
		this.entorno.iniciar();
	}

	/**
	 * Durante el juego, el método tick() será ejecutado en cada instante y por lo
	 * tanto es el método más importante de esta clase. Aquí se debe actualizar el
	 * estado interno del juego para simular el paso del tiempo (ver el enunciado
	 * del TP para mayor detalle).
	 */
	public void tick() {
		// Procesamiento de un instante de tiempo
		// ...
		try {
			for (Disparo d : mago.lDisparo) {
				d.Dibujar(this.entorno);
				largoLista = mago.lDisparo.size() - 1;
				if (d.getX() > ancho - 20 ) {
					mago.lDisparo.remove(d);
				} else if (d.getX() < 20) {
					mago.lDisparo.remove(d);
				}
			}
		} catch (Exception e) {

		}
		
		dibujarVigas();
		dibujarPersonajes();
		puntaje();
		if (mago.derecha) {
			dS.animacionDerecha(entorno, mago.getPosX(), mago.getPosY());
		} else {
			dS.animacionIzquierda(entorno, mago.getPosX(), mago.getPosY());
		}

		entorno.dibujarRectangulo(ancho / 2, alto - 110, ancho + 30, (alto - margen) + 20, 0.0, Color.gray);
		tiempo.dibujar(entorno, "Tiempo :" + ((int) milisegundo / 60));
		puntos.dibujar(entorno, "Puntos de nivel :" + puntaje);
		puntosTotales.dibujar(entorno, "Puntos totales :" + puntTot);
		nivelT.dibujar(entorno, "Nivel :" + nivel);
		dibujarCorazones((ancho / 2) - 250, alto - 150);
		if (entorno.sePresiono(entorno.TECLA_ENTER)) {
			if(pausa) {
				pausa=false;
			}else {
				pausa=true;
			}
		}
		if(!pausa) {
			if (mago.isEstado() && !ganar()) {
				milisegundo += 1;
				// System.out.println(puntaje);
				fisicas();
				mover();
				comportamientoEnemigo(ancho);
				mago.contacto(personajes);
				if (mago.vulnerable == false) {
					cont++;
					if (cont > 150) {
						mago.vulnerable = true;
						cont = 0;
					}
				}

				if (mago.isSaltar()) {
					contador++;
					if (contador < vueltasSalto - 5) {
						mago.saltar();
						dS.setearTodo();

					} else if (contador == vueltasSalto) {
						mago.setSaltar(false);
						contador = 0;
					}

				}

			} else {
				if (ganar()) {
					animacionGanar.animar(entorno, 200, 300);
					Carteles.cartel(entorno, (ancho / 2) - 100, alto - 500, "Ganaste");
					Carteles.cartel(entorno, (ancho / 2) - 100, alto - 400, "Siguiente nivel");
					Carteles.cartel(entorno, (ancho / 2) - 100, alto - 300, "Presione enter");
					if (entorno.sePresiono(entorno.TECLA_ENTER)) {
						velocidadEnemigo++;
						nivel++;
						puntTot += puntaje;
						new Juego(puntTot, nivel, velocidadEnemigo);

					}
				} else {
					sprite.animar(entorno, mago.getPosX(), mago.getPosY());
					Carteles.cartel(entorno, (ancho / 2) - 100, alto - 600, "PERDEDOR");
					Carteles.cartel(entorno, (ancho / 2) - 100, alto - 500, "Para reintentar");
					Carteles.cartel(entorno, (ancho / 2) - 100, alto - 400, "Presione enter");
					if (entorno.sePresiono(entorno.TECLA_ENTER)) {
						new Juego();
					}
				}

			}
		}

	}

	public void comprobar() {
		mago.mover = false;
		if (entorno.estaPresionada(entorno.TECLA_IZQUIERDA)) {
			mago.derecha = false;
			mago.mover = true;
		}
		if (entorno.estaPresionada(entorno.TECLA_DERECHA)) {
			mago.derecha = true;
			mago.mover = true;
		}
	}

	public void mover() {
		if (entorno.estaPresionada(entorno.TECLA_IZQUIERDA)) {
			if (mago.getPosX() > 30) {
				for (int i = 0; i < 2; i++) {
					mago.retroceder();
				}
				if (mago.isSaltar()) {
					for (int i = 0; i < 4; i++) {
						mago.retroceder();

					}
				}
			}
		}
		if (entorno.estaPresionada(entorno.TECLA_DERECHA)) {
			if (mago.getPosX() < ancho - 30) {
				for (int i = 0; i < 2; i++) {
					mago.avanzar();
				}

				if (mago.isSaltar()) {
					for (int i = 0; i < 4; i++) {
						mago.avanzar();
					}

				}
			}

		}
		if (entorno.sePresiono(entorno.TECLA_ARRIBA)) {
			mago.setSaltar(true);
			salto = true;
		}
		comprobar();
	}

	public void posPersonajes() {
		double x = 290, y = 70;
		for (int i = 0; i < personajes.length; i++) {
			if (i == 1) {
				x = 196;
				y = 310;
			} else if (i == 2) {
				x = 400;
				y = 310;
			} else if (i == 3) {
				x = 148;
				y = 480;
			} else if (i == 4) {
				x = 444;
				y = 480;
			}
			personajes[i] = new Personajes(x, y, 50.0, 50.0, 0);
		}
	}

	void puntaje() {
		int punta = 0;
		for (int i = 1; i < personajes.length; i++) {
			if (personajes[i].muerte == true) {
				punta += 100;
			}
		}
		this.puntaje = punta;
	}

	public void dibujarPersonajes() {
		for (int i = 0; i < personajes.length; i++) {
			if (i == 0) {
				if (personajes[i].isEstado() == true) {
					if (mago.vulnerable == false && cont < 25) {
						if (mago.dgolpe) {
							mago.retroceder();
							entorno.dibujarImagen(sprite.golpeIz, mago.getPosX(), mago.getPosY(), 0.0);
						} else {
							mago.avanzar();
							entorno.dibujarImagen(sprite.golpeDe, mago.getPosX(), mago.getPosY(), 0.0);
						}

					} else {
						if (mago.isSaltar()) {
							if (mago.mover) {
								entorno.dibujarImagen(sprite.saltoFin2, mago.getPosX(), mago.getPosY(), 0.0);
							} else {
								entorno.dibujarImagen(sprite.saltoFin, mago.getPosX(), mago.getPosY(), 0.0);
							}

						} else {
							sprite.dibujar(entorno, mago.getPosX(), mago.getPosY(), mago.derecha, mago.mover);
						}

					}
				}

			} else {
				if (personajes[i].isEstado() == true) {
					personajes[i].Dibujar(entorno, Color.blue);
				} else {
					personajes[i].Dibujar(entorno, Color.CYAN);
				}
			}

		}

	}

	public void dibujarVigas() {
		int posy = 0, posx = 0, espacio = 120, margen = alto;

		for (int i = 0; i < vigas.length; i++) {
			if (i == 4) {
				posx = 30;
				posy += espacio;
			} else if (i == 8 || i == 3) {
				posx = (ancho / 2) + 50;
			} else if (i == 2) {
				posx = (ancho / 2) - 50;
				posy += espacio;
			} else if (i == 7) {
				posx = (ancho / 2) - 50;
				posy += espacio + 50;
			} else if (i == 1 || i == 5 || i == 10) {
				posx = ancho - 30;
			} else if (i == 6) {
				posx = (ancho / 2);
				posy += espacio;
			} else if (i != 0) {
				posx = 30;
				posy += espacio;
			}
			vigas[i] = new Viga(posx, posy);
			margen = posy;

		}
		this.margen = margen;
		for (int i = 0; i < vigas.length; i++) {
			vigas[i].Dibujar(entorno);
		}
	}

	public void comportamientoEnemigo(int ancho) {
		for (int i = 1; i < personajes.length; i++) {
			if (Fisica.congelar(personajes[i], mago)) {
				personajes[i].setEstado(false);
				puntaje += 5;
				mago.lDisparo.remove(largoLista);
			}
			if (personajes[i].isEstado()) {
				if (i % 2 == 0 && i != 4) {
					compórtamiento2(i);
				} else {
					comportamiento3(i);
				}
			} else if (personajes[i].muerte) {
				if (personajes[i].contacto) {
					if (!mago.derecha) {
						contra[i] = false;
					} else {
						contra[i] = true;
					}
					personajes[i].contacto = false;
				}

				compórtamiento1(i);
				Fisica.colision(personajes, personajes[i], i);
			}
		}

	}

	public void compórtamiento1(int i) {
		if (contra[i] == true) {
			for (int j = 0; j < 2 + velocidadEnemigo; j++) {
				personajes[i].avanzar();
			}

		} else {
			for (int j = 0; j < 2 + velocidadEnemigo; j++) {
				personajes[i].retroceder();
			}

		}
		if (personajes[i].getPosX() < 0 && contra[i] == false) {
			this.contra[i] = true;
		}
		if (contra[i] == true && personajes[i].getPosX() >= ancho) {
			this.contra[i] = false;
		}
		if (personajes[i].getPosY() > margen + 10) {
			personajes[i].inicio(margen + 50);

		}
	}

	public void compórtamiento2(int i) {
		if (Fisica.colision(personajes[i], vigas) == false) {
			if (contra[i] == true) {
				contra[i] = false;
			} else {
				contra[i] = true;
			}

		}
		if (contra[i] == true) {
			for (int j = 0; j < 2 + velocidadEnemigo; j++) {
				personajes[i].avanzar();
			}

		} else {
			for (int j = 0; j < 1 + velocidadEnemigo; j++) {
				personajes[i].retroceder();
			}

		}
		if (personajes[i].getPosX() < 0 && contra[i] == false) {
			this.contra[i] = true;
		}
		if (contra[i] == true && personajes[i].getPosX() >= ancho) {
			this.contra[i] = false;
		}
	}

	public void comportamiento3(int i) {
		if (contra[i] == false) {
			for (int j = 0; j < 1 + velocidadEnemigo; j++) {
				personajes[i].avanzar();
			}

		} else {
			for (int j = 0; j < 1 + velocidadEnemigo; j++) {
				personajes[i].retroceder();
			}

		}
		if (personajes[i].getPosX() < 0 && contra[i] == true) {
			this.contra[i] = false;
		}
		if (contra[i] == false && personajes[i].getPosX() >= ancho) {
			this.contra[i] = true;
		}
		if (Fisica.colision(personajes[i], vigas) == false) {
			if (contra[i] == true) {
				contra[i] = false;
			} else {
				contra[i] = true;
			}

		}
	}

	public void fisicas() {
		for (int i = 0; i < personajes.length; i++) {
			Fisica.margen(margen, personajes[i]);

			if (Fisica.colision(personajes[0], vigas)) {
				mago.aux = 0;
				incremento++;
				if (incremento > 150) {
					dS.setearTodo();
					salto = false;
				}
			}
			if (!Fisica.colision(personajes[i], vigas)) {
				personajes[i].caer();
				if (i == 0 && salto == false) {
					dS.setAnimar(true);
					incremento = 0;
				}
			}
		}

	}

	public void estados() {
		for (int i = 0; i < contra.length; i++) {
			contra[i] = false;
		}
	}

	public boolean ganar() {
		for (int i = 1; i < personajes.length; i++) {
			if (personajes[i].getPosY() < 700) {
				return false;
			}
		}

		return true;
	}

	void dibujarCorazones(double x, double y) {
		double x1 = x;
		for (int i = 0; i < mago.corazones.length; i++) {
			if (mago.corazones[i]) {
				entorno.dibujarImagen(corazon, x1, y, 0.0);
			} else {
				entorno.dibujarImagen(corazonRoto, x1, y, 0.0);
			}

			x1 += 70;
		}

	}

	// @SuppressWarnings("unused")
	public static void main(String[] args) {
		new Juego();
	}

}
