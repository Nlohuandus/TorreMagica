﻿package juego;
import java.awt.Color;

import entorno.Entorno;
import entorno.InterfaceJuego;

public class Juego extends InterfaceJuego 
{
	// El objeto Entorno que controla el tiempo y otros
	private Entorno entorno;
	int ancho=600, alto=900,cantEnemigos=3;
	Mago[] personajes= new Mago[cantEnemigos];
	Mago mago;//new Mago(100.0, 80.0, 50.0,50.0, 0);
	//=new Mago(200.0, 0.0, 50.0,50.0, 0);
	
	//Viga viga= new Viga(150,200);
	//Viga viga2= new Viga(250,300);
	Viga[] vigas=new Viga[11];
	boolean mantener=false;
	boolean[] contra= new boolean [cantEnemigos];
	int contador=0,vueltasSalto=23,margen;
	

	
	// Variables y métodos propios de cada grupo
	// ...
	
	Juego()
	{
		// Inicializa el objeto entorno
		this.entorno = new Entorno(this, "Torre Magica - Grupo Apellido1 - Apellido2 -Apellido3 - V0.01", ancho, alto);
		
		// Inicializar lo que haga falta para el juego
		// ...
		posPersonajes();
		this.mago=personajes[0];
		estados();
		
		// Inicia el juego!
		this.entorno.iniciar();
		
	}

	/**
	 * Durante el juego, el método tick() será ejecutado en cada instante y 
	 * por lo tanto es el método más importante de esta clase. Aquí se debe 
	 * actualizar el estado interno del juego para simular el paso del tiempo 
	 * (ver el enunciado del TP para mayor detalle).
	 */
	public void tick()
	{
		// Procesamiento de un instante de tiempo
		// ...
		for (Disparo d : mago.lDisparo) {//nueva version
			d.Dibujar(this.entorno, mago.derecha);//nueva version
		}
		dibujarVigas();
		mago.Dibujar(entorno);
		dibujarPersonajes();
		entorno.dibujarRectangulo(ancho/2, alto-120, ancho, alto-margen,0.0, Color.gray);
		if(mago.isEstado()) {
			fisicas();
			mover();
			comportamientoEnemigo(ancho);
			mago.contacto(personajes);

			if(mago.isSaltar()) {
				contador++;
				if (contador<vueltasSalto-5) {
					mago.saltar();
					
				}else if(contador==vueltasSalto) {
					mago.setSaltar(false);
					contador=0;
				}

			}
		}
		

		//System.out.println("pos x: "+mago.getPosX());
		//System.out.println("pos y: "+mago.getPosY());
		//System.out.println("pos y viga: "+viga2.posy);
		//System.out.println("pos x viga bordIz: "+viga2.bordeIz);
		//System.out.println("pos x viga bordDer: "+viga2.bordeDer);

	}
	
	
	public void mover() {
		if(entorno.estaPresionada(entorno.TECLA_IZQUIERDA)) {
			if(mago.getPosX()>30) {
				for(int i=0;i<2;i++) {
					mago.retroceder();
					//derecha=false; borrar en nueva version
				}
				if(mago.isSaltar()) {
					for(int i=0;i<4;i++) {
						mago.retroceder();
						//derecha=false; borrar en nueva version
						
					}
				}
			}

		}
		if(entorno.estaPresionada(entorno.TECLA_DERECHA)) {
			if(mago.getPosX()<ancho-30) {//bordes!!! agregar
				for(int i=0;i<2;i++) {
					mago.avanzar();
				}
				
				if(mago.isSaltar()) {
					for(int i=0;i<4;i++) {
						mago.avanzar();
					}
					
				}
			}

		}
		if(entorno.sePresiono(entorno.TECLA_ARRIBA)) {
			mago.setSaltar(true);
		}
	}
	public void posPersonajes() {
		double x=290,y=70;
		for(int i=0;i<personajes.length;i++) {
			if(i==1) {
				x=196;
				y=310;
			}else if(i==2) {
				x=400;
				y=310;
			}else if(i==3) {
				x=148;
				y=480;
			}else if(i==4) {
				x=444;
				y=480;
			}
			personajes[i]=new Mago(x, y, 50.0,50.0, 0);
		}
	}
	
	public void dibujarPersonajes() {
		for(int i=0;i<personajes.length;i++) {
			if(i==0) {
				if(personajes[i].isEstado()==true) {
					personajes[i].Dibujar(entorno);
				}else {
					personajes[i].Dibujar(entorno,Color.green);
				}
				
			}else {
				if(personajes[i].isEstado()==true) {
					personajes[i].Dibujar(entorno,Color.blue);
				}else {
					personajes[i].Dibujar(entorno,Color.CYAN);
				}
			}
			
		}

	}
	
	public void dibujarVigas() {
		int posy=0,posx=0,espacio=120,margen=alto;
		
		for(int i=0; i<vigas.length;i++) {
			if(i==4) {
				posx=30;
				posy+=espacio;
			}else if(i==8 || i==3) {
				posx=(ancho/2)+50;	
			}else if(i==2) {
				posx=(ancho/2)-50;
				posy+=espacio;
			}
			else if(i==7) {
				posx=(ancho/2)-50;
				posy+=espacio+50;
			}
			else if(i==1 || i==5 || i==10) {
				posx=ancho-30;
			}else if(i==6) {
				posx=(ancho/2);
				posy+=espacio;
			}
			else if(i!=0){
				posx=30;
				posy+=espacio;
			}
			vigas[i]=new Viga(posx,posy);
			margen=posy;
				
		}
		this.margen=margen;
		for(int i=0; i<vigas.length;i++) {
			vigas[i].Dibujar(entorno);
		}
	}
	
	public void comportamientoEnemigo(int ancho) {
		for(int i=1; i<personajes.length;i++) {
			if(Fisica.congelar(personajes[i], mago)) {
				personajes[i].setEstado(false);
			}
			
			//System.out.println("posy: "+personajes[i].getPosY());
			if(personajes[i].isEstado()) {
				if(i%2==0 && i!=4) {//los enemigos con i par tendran una comportamiento distinto menos el de i=4
					if(Fisica.colision(personajes[i], vigas)==false) {
						if(contra[i]==true) {
							contra[i]=false;
						}else {
							contra[i]=true;
						}
					
					}
					if (contra[i]==true) {
						personajes[i].avanzar();
						personajes[i].avanzar();
					}else {
						personajes[i].retroceder();
					}
					if(personajes[i].getPosX()<0 && contra[i]==false) {
						this.contra[i]=true;
					}if (contra[i]==true && personajes[i].getPosX()>=ancho) {
						this.contra[i]=false;
					}
				}else {
					if (contra[i]==false) {
						personajes[i].avanzar();
					}else {
						personajes[i].retroceder();
					}
					if(personajes[i].getPosX()<0 && contra[i]==true) {
						this.contra[i]=false;
					}if (contra[i]==false && personajes[i].getPosX()>=ancho) {
						this.contra[i]=true;
					}
					if(Fisica.colision(personajes[i], vigas)==false) {
						if(contra[i]==true) {
							contra[i]=false;
						}else {
							contra[i]=true;
						}
					
					}
				}
			}else if(personajes[i].muerte) {
				
				if (contra[i]==true) {
					personajes[i].avanzar();
					personajes[i].avanzar();
				}else {
					personajes[i].retroceder();
					personajes[i].retroceder();
				}
				if(personajes[i].getPosX()<0 && contra[i]==false) {
					this.contra[i]=true;
				}if (contra[i]==true && personajes[i].getPosX()>=ancho) {
					this.contra[i]=false;
				}
				if(personajes[i].getPosY()>margen+10) {
					personajes[i].inicio(margen+50);
					
				}
			}
		}
				
	}
	
	
	public void fisicas() {
		for(int i=0; i<personajes.length;i++) {
			Fisica.margen(margen, personajes[i]);
			
			if (Fisica.colision(personajes[0], vigas)) {
				mago.aux=0;   //agregar en nueva version
			}

			if(!Fisica.colision(personajes[i], vigas)) {
				personajes[i].caer();

			}
		}
		
	}
	
	public void estados() {
		for(int i=0;i<contra.length;i++) {
			contra[i]=false;
		}
	}
	

	//@SuppressWarnings("unused")
	public static void main(String[] args)
	{
		new Juego();
	}
	
	
}