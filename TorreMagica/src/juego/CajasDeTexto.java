package juego;

import java.awt.Color;

import entorno.Entorno;

public class CajasDeTexto {
	double x,y,ancho,alto;
	String texto;
	Color colorFondo;
	Color letraColor;
	int tamanio;
	
	CajasDeTexto(String texto,double x, double y, double ancho, double alto, Color letra, Color colorFondo, int tamanio){
		this.x=x;
		this.y=y;
		this.ancho=ancho;
		this.alto=alto;
		this.letraColor=letra;
		this.colorFondo=colorFondo;
		this.tamanio=tamanio;
	}
	
	CajasDeTexto(double x, double y, double ancho, double alto, int tamanio){
		this.x=x;
		this.y=y;
		this.ancho=ancho;
		this.alto=alto;
		this.letraColor=Color.black;
		//this.colorFondo=colorFondo;
		this.tamanio=30;
		this.texto="0:00";
	}
	
	public void dibujar(Entorno e,String texto){
		e.cambiarFont("Arial", 30, this.letraColor);
		e.escribirTexto(texto, x, y);
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

}
