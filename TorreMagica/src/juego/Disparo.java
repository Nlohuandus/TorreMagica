package juego;

import java.awt.Color;

import entorno.Entorno;

public class Disparo {
	private double x, y, xdir, ydir;
	private double angulo;
	boolean b; //nueva version

	Disparo(double x, double y, double a,boolean b) {
		this.x = x;
		this.y = y;
		this.angulo = a;
		this.xdir = (double) Math.cos(this.angulo);
		this.ydir = (double) Math.sin(this.angulo);
		this.b=b;//nueva version
	}

	void Dibujar(Entorno e, boolean b) {
		e.dibujarRectangulo(this.x, this.y, 5, 5, this.angulo, Color.cyan);
		trayectoria(this.b);//nueva version
	}

	void trayectoria(boolean b) {
		if(b) {
		this.x = this.x + 4 * this.xdir;
		this.y = this.y + 4 * this.ydir;
	} else {
		this.x = this.x - 4 * this.xdir;
		this.y = this.y - 4 * this.ydir;
	}
		
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

}
