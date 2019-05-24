package juego;

import java.awt.Image;

import javax.swing.ImageIcon;

import entorno.Entorno;

public class Sprite {
	Image[] imagenesD, imagenesI, imagenesIde, imagenesMuerte, imagenesSalto;
	Image golpeDe = new ImageIcon("golpeIz.png").getImage();
	Image golpeIz = new ImageIcon("golpeDe.png").getImage();
	Image saltoFin = new ImageIcon("salto0004.png").getImage();
	Image saltoFin2 = new ImageIcon("salto0004.png").getImage();

	String[] derecha = { "derecha0001.png", "derecha0002.png", "derecha0003.png", "derecha0004.png", "derecha0005.png",
			"derecha0006.png", "derecha0007.png" };
	String[] izquierda = { "izquierda0001.png", "izquierda0002.png", "izquierda0003.png", "izquierda0004.png",
			"izquierda0005.png", "izquierda0006.png", "izquierda0007.png" };
	String[] ide = { "ide0001.png", "ide0002.png", "ide0003.png", "ide0004.png", "ide0005.png" };
	String[] muerte = { "muerte0001.png", "muerte0002.png", "muerte0003.png", "muerte0004.png" };
	String[] salto = { "salto0001.png", "salto0002.png" };
	boolean entra = false;
	double x, y;
	public int contador = 0, mostrador = 0;

	public Sprite(double x, double y) {
		this.x = x;
		this.y = y;
		setImagenes();
	}

	public void dibujar(Entorno e, double x, double y, boolean derecha, boolean mover) {

		if (derecha && mover) {
			e.dibujarImagen(imagenesD[mostrador], x, y + 15, 0.0);
		} else if (!derecha && mover) {
			e.dibujarImagen(imagenesI[mostrador], x, y + 15, 0.0);

		}
		if (!mover) {
			if (mostrador > imagenesIde.length - 1) {
				mostrador = 0;
			}
			e.dibujarImagen(imagenesIde[mostrador], x, y + 15, 0.0);
			this.contador++;
			if (contador > 12) {
				this.mostrador++;
				if (mostrador > imagenesIde.length - 1) {
					this.mostrador = 0;
				}
				this.contador = 0;
			}
		} else {
			this.contador++;
			if (contador > 8) {
				this.mostrador++;
				if (mostrador > imagenesD.length - 1) {
					this.mostrador = 0;
				}
				this.contador = 0;
			}
		}
	}

	void animar(Entorno e, double x, double y) {
		if (entra == false) {
			entra = true;
			mostrador = 0;
		}
		if (mostrador > imagenesMuerte.length - 1) {
			e.dibujarImagen(imagenesMuerte[3], x, y + 30, 0.0);
		} else {
			e.dibujarImagen(imagenesMuerte[mostrador], x, y + 15, 0.0);
		}

		this.contador++;
		if (contador > 15) {
			this.mostrador++;
			this.contador = 0;
		}
	}

	void animarSalto(Entorno e, double x, double y) {
		System.out.println(mostrador);
		if (entra == false) {
			this.entra = true;
			mostrador = 0;
		}
		if (mostrador > salto.length - 1) {
			mostrador = 0;
		}
		if (mostrador > salto.length - 1 && entra) {
			entra = false;
			mostrador = 0;
		}
		if (mostrador < salto.length - 1) {
			e.dibujarImagen(imagenesSalto[mostrador], x, y + 15, 0.0);
		}

		this.contador++;
		if (contador > 12) {
			this.mostrador++;
			this.contador = 0;
		}
	}

	void setImagenes() {
		this.imagenesD = new Image[derecha.length];
		this.imagenesI = new Image[izquierda.length];
		this.imagenesIde = new Image[ide.length];
		this.imagenesMuerte = new Image[muerte.length];
		this.imagenesSalto = new Image[salto.length];

		for (int i = 0; i < izquierda.length; i++) {
			imagenesI[i] = new ImageIcon(izquierda[i]).getImage();

		}
		for (int i = 0; i < derecha.length; i++) {
			imagenesD[i] = new ImageIcon(derecha[i]).getImage();

		}
		for (int i = 0; i < ide.length; i++) {
			imagenesIde[i] = new ImageIcon(ide[i]).getImage();

		}
		for (int i = 0; i < muerte.length; i++) {
			imagenesMuerte[i] = new ImageIcon(muerte[i]).getImage();

		}
		for (int i = 0; i < salto.length; i++) {
			imagenesSalto[i] = new ImageIcon(salto[i]).getImage();

		}
	}
}
