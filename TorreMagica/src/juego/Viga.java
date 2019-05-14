package juego;

import java.awt.Color;

import entorno.Entorno;

public class Viga {
	double ancho, alto, posx, posy;
	double bordeIz, bordeDer, posyInicial = 180;

	Viga(double x, double y) {
		setPosx(x);
		setPosy(y);
		setAlto(20);
		setAncho(250);
		setBordeDer();
		setBordeIz();

	}

	public double getBordeIz() {
		return bordeIz;
	}

	public void setBordeIz() {
		this.bordeIz = posx - (ancho / 2) - 25;
	}

	public double getBordeDer() {
		return bordeDer;
	}

	public void setBordeDer() {
		this.bordeDer = (posx + ancho / 2) + 25;
	}

	public double getAncho() {
		return ancho;
	}

	public void setAncho(double ancho) {
		this.ancho = ancho;
	}

	public double getAlto() {
		return alto;
	}

	public void setAlto(double alto) {
		this.alto = alto;
	}

	public double getPosx() {
		return posx;
	}

	public void setPosx(double posx) {
		this.posx = posx;
	}

	public double getPosy() {
		return posy;
	}

	public void setPosy(double posy) {
		this.posy = posy;
	}

	void Dibujar(Entorno e) {

		e.dibujarRectangulo(this.posx, this.posy, this.ancho, this.alto, 0.0, Color.red);

	}
}
