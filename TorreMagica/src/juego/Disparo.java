package juego;

import java.awt.Color;
import java.awt.Image;

import javax.swing.ImageIcon;

import entorno.Entorno;

public class Disparo {
	private double x, y, xdir, ydir;
	private double angulo;
	boolean b;
	boolean b2;
	Image disparoIz = new ImageIcon("poder0006.png").getImage();
	Image disparoDer = new ImageIcon("poder0007.png").getImage();

	Disparo(double x, double y, double a, boolean b) {
		this.x = x;
		this.y = y;
		this.angulo = a;
		this.xdir = (double) Math.cos(this.angulo);
		this.ydir = (double) Math.sin(this.angulo);
		this.b = b;

	}

	void Dibujar(Entorno e) {
		e.dibujarRectangulo(this.x, this.y + 10, 5, 5, this.angulo, Color.cyan);
		if (!b) {
			e.dibujarImagen(disparoIz, x, y + 20, angulo);
		} else {
			e.dibujarImagen(disparoDer, x, y + 20, angulo);
		}
		trayectoria(this.b);
	}

	void trayectoria(boolean b) {
		if (b) {
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
