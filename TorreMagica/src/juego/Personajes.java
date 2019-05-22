package juego;

import java.awt.Color;
import java.util.ArrayList;

import entorno.Entorno;

public class Personajes {
	private double x;
	private double y;
	public double ancho;
	private double alto;
	private double angulo;
	private double peso;
	private boolean estado, saltar;
	ArrayList<Disparo> lDisparo = new ArrayList<Disparo>();
	public Disparo d;
	boolean derecha, muerte, contacto = false, mover, vulnerable = true, dgolpe = false;
	int aux, velocidad, cantCorazones = 3;
	double milisegundos;
	boolean[] corazones = { true, true, true };

	Personajes (double x, double y, double ancho, double alto, double angulo) {
		this.x = x;
		this.y = y;
		this.ancho = ancho;
		this.alto = alto;
		this.angulo = angulo;
		this.peso = 10;
		this.estado = true;
		this.mover = false;
		setSaltar(false);
	}

	Disparo disparar() {
		if (y > 310 && y < 480) {
			this.d = new Disparo(this.x, 480, this.angulo, this.derecha);

		} else {
			this.d = new Disparo(this.x, this.y, this.angulo, this.derecha);
		}
		return d;
	}

	void Dibujar(Entorno e) {
		e.dibujarRectangulo(this.x, this.y + 13, this.ancho, this.alto, this.angulo, Color.orange);

	}

	void Dibujar(Entorno e, Color color) {
		e.dibujarRectangulo(this.x, this.y + 13, this.ancho, this.alto, this.angulo, color);
	}

	void avanzar() {
		this.y = y + (float) Math.sin(angulo);
		this.x = x + (float) Math.cos(angulo);
		derecha = true;
	}

	void retroceder() {
		this.y = y - (float) Math.sin(angulo);
		this.x = x - (float) Math.cos(angulo);
		derecha = false;
	}

	void caer() {
		aux += 1;
		this.y = y + peso;
		if (aux > 11 && aux < 13) {
			if (saltar == false) {
				lDisparo.add(disparar());
			}
		}
	}

	void saltar() {
		this.y = y - peso * 2;
	}

	public boolean isSaltar() {
		return saltar;
	}

	public void setSaltar(boolean saltar) {
		this.saltar = saltar;
	}

	void inicio(int i) {
		this.y = i;
	}

	public void setPosX(int x) {
		this.x = x;
	}

	public double getPosX() {
		return x;
	}

	public double getPosY() {
		return y;
	}

	public double getAlto() {
		return alto;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public void contacto(Personajes [] personajes) {// si el mago es tocado por un enemigo

		for (int i = 1; i < personajes.length; i++) {
			if (personajes[i].estado == true) {
				// revisa si el enemigo esta congelado
				if (personajes[i].getPosY() <= getPosY() && personajes[i].getPosY() >= getPosY() - ancho) {
					if (personajes[i].getPosX() <= getPosX() && personajes[i].getPosX() >= getPosX() - ancho) {
						if (vulnerable == true) {
							this.dgolpe = false;
							this.cantCorazones--;
							if (cantCorazones >= 0) {
								corazones[cantCorazones] = false;
							}
						}
						this.vulnerable = false;

						if (cantCorazones == 0) {
							setEstado(false);
						}

					} else if (personajes[i].getPosX() >= getPosX() && personajes[i].getPosX() <= getPosX() + ancho) {

						if (vulnerable == true) {
							this.dgolpe = true;

							this.cantCorazones--;
							if (cantCorazones >= 0) {
								corazones[cantCorazones] = false;

							}
						}

						this.vulnerable = false;

						if (cantCorazones == 0) {
							setEstado(false);
						}

					}
				}

			} else {
				milisegundos++;
				if ((int) milisegundos / 60 == 5) {

					personajes[i].estado = true;
					milisegundos = 0;
				}
				if (personajes[i].getPosY() <= getPosY() && personajes[i].getPosY() >= getPosY() - ancho) {
					if (personajes[i].getPosX() <= getPosX() && personajes[i].getPosX() >= getPosX() - ancho) {
						personajes[i].muerte = true;
						personajes[i].contacto = true;
					} else if (personajes[i].getPosX() >= getPosX() && personajes[i].getPosX() <= getPosX() + ancho) {
						personajes[i].muerte = true;
						personajes[i].contacto = true;
					}
				}

			}

		}
	}

}
