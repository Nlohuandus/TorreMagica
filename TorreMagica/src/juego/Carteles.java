package juego;

import java.awt.Color;
import java.awt.Font;

import entorno.Entorno;

public class Carteles {
	static void cartel(Entorno e, double x, double y, String mensaje) {
		e.dibujarRectangulo(x + 100, y, 350, 100, 0, Color.white);
		e.cambiarFont(Font.SERIF, 50, Color.red);
		e.escribirTexto(mensaje, x - 50, y);

	}

	static void cartel(Entorno e, double x, double y, double ancho, double alto, String mensaje) {
		e.dibujarRectangulo(x + 100, y, ancho, alto, 0, Color.white);
		e.cambiarFont(Font.SERIF, 50, Color.red);
		e.escribirTexto(mensaje, x - 50, y);

	}
}
