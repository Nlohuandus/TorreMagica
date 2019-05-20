package juego;

import java.awt.Image;

import javax.swing.ImageIcon;

import entorno.Entorno;

public class Animaciones {
	String[] nombres;
	Image[] imagenes;
	private int contador = 0, mostrador = 0;
	public int velocidad;

	Animaciones(String nombre, int velocidad, int cantFotogramas) {
		this.nombres = new String[cantFotogramas];
		this.imagenes = new Image[cantFotogramas];
		this.velocidad = velocidad;
		setNombres(nombre);
		setImagenes();
	}

	void setNombres(String nombre) {
		String numU = "000";
		String numD = "00";
		String numC = "0";
		for (int i = 0; i < nombres.length; i++) {
			if (i + 1 < 10) {
				nombres[i] = nombre + numU + (i + 1) + ".png";
			} else if (i + 1 > 9) {
				nombres[i] = nombre + numD + (i + 1) + ".png";
			} else if (i + 1 > 99) {
				nombres[i] = nombre + numC + (i + 1) + ".png";
			}
		}
	}

	void setImagenes() {
		for (int i = 0; i < nombres.length; i++) {
			imagenes[i] = new ImageIcon(nombres[i]).getImage();

		}
	}

	void animar(Entorno e, double x, double y) {

		if (mostrador > imagenes.length - 1) {
			e.dibujarImagen(imagenes[imagenes.length - 1], x, y, 0.0);
		} else {
			e.dibujarImagen(imagenes[mostrador], x, y, 0.0);
		}

		this.contador++;
		if (contador > velocidad) {
			this.mostrador++;
			this.contador = 0;
		}
	}

}
