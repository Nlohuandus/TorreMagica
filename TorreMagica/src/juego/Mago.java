package juego;

import java.awt.Color;
import java.util.ArrayList;

import entorno.Entorno;

public class Mago {
	private double x;
	private double y;
	public double ancho;
	private double alto;
	private double angulo;
	private double peso;
	private boolean estado,saltar;
	ArrayList<Disparo> lDisparo= new ArrayList<Disparo>();//nueva version
	public Disparo d;
	boolean derecha,muerte,contacto=false;//nuevo 
	int aux,velocidad;
	Mago(double x, double y, double ancho,double alto,double angulo){
		this.x=x;
		this.y=y;
		this.ancho=ancho;
		this.alto=alto;
		this.angulo=angulo;
		this.peso=10;
		this.estado=true;
		setSaltar(false);
	}
	Disparo disparar() {
		if(y>310 && y<480) {
			this.d=new Disparo(this.x,480,this.angulo, this.derecha);
		}else {
			this.d=new Disparo(this.x,this.y,this.angulo, this.derecha);//nueva version
		}
		
		return d;
	}

	void Dibujar(Entorno e) {
		e.dibujarRectangulo(this.x, this.y, this.ancho, this.alto, this.angulo, Color.ORANGE);
	}
	void Dibujar(Entorno e,Color color) {
		e.dibujarRectangulo(this.x, this.y, this.ancho, this.alto, this.angulo, color);
	}
	void avanzar() {
		this.y = y + (float) Math.sin(angulo);
		this.x = x + (float) Math.cos(angulo);
		derecha=true;
	}
	void retroceder() {
		this.y = y - (float) Math.sin(angulo);
		this.x = x - (float) Math.cos(angulo);
		derecha=false;
	}
	void caer() {
		aux+=1;
		//System.out.println("aux "+aux);
		this.y = y + peso;
		if (aux>11 && aux<13) {
			if(saltar==false) {
				lDisparo.add(disparar());
			}
			
			//aux=0;                        //  agregar nueva version
			}
	}
	void saltar() {
		this.y = y - peso*2;
	}
	public boolean isSaltar() {
		return saltar;
	}



	public void setSaltar(boolean saltar) {
		this.saltar = saltar;
	}
	void inicio(int i) {
		this.y=i;
	}
	public void setPosX(int x) {
		this.x=x;
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

	public void contacto(Mago[]personajes) {//si el mago es tocado por un enemigo
		
		for(int i=1;i<personajes.length;i++) {
			if(personajes[i].estado==true) {//revisa si el enemigo esta congelado
				if(personajes[i].getPosY()<=getPosY() && personajes[i].getPosY()>=getPosY()-ancho) {
					if(personajes[i].getPosX()<=getPosX() && personajes[i].getPosX()>=getPosX()-ancho) {
						setEstado(false);
					}else if(personajes[i].getPosX()>=getPosX() && personajes[i].getPosX()<=getPosX()+ancho) {
						setEstado(false);
					}
				}
			}else {//nuevo
				if(personajes[i].getPosY()<=getPosY() && personajes[i].getPosY()>=getPosY()-ancho) {
					if(personajes[i].getPosX()<=getPosX() && personajes[i].getPosX()>=getPosX()-ancho) {
						personajes[i].muerte=true;
						personajes[i].contacto=true;
					}else if(personajes[i].getPosX()>=getPosX() && personajes[i].getPosX()<=getPosX()+ancho) {
						personajes[i].muerte=true;
						personajes[i].contacto=true;
					}
				}
				
			}

		}
	}
	
}
