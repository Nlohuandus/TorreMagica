package juego;


import java.awt.Image;

import javax.swing.ImageIcon;

import entorno.Entorno;

public class Sprite{
	Image [] imagenesD,imagenesI,imagenesIde;
	Image golpeDe = new ImageIcon("golpeIz.png").getImage();
	Image golpeIz = new ImageIcon("golpeDe.png").getImage();
	String [] derecha = {"derecha0001.png","derecha0002.png","derecha0003.png","derecha0004.png","derecha0005.png","derecha0006.png","derecha0007.png"};
	String [] izquierda = {"izquierda0001.png","izquierda0002.png","izquierda0003.png","izquierda0004.png","izquierda0005.png","izquierda0006.png","izquierda0007.png"};
	String [] ide = {"ide0001.png","ide0002.png","ide0003.png","ide0004.png","ide0005.png"};
	double x,y;
	int contador =0,mostrador=0;

	public Sprite(double x,double y) {
		//imagen = new ImageIcon("0001.png").getImage();
		this.x=x;
		this.y=y;
		setImagenes();
	}
	
	public void dibujar(Entorno e,double x,double y, boolean derecha,boolean mover) {

		if(derecha && mover) {
			e.dibujarImagen(imagenesD[mostrador] , x, y+15, 0.0);
		}else if (!derecha && mover) {
			e.dibujarImagen(imagenesI[mostrador] , x, y+15, 0.0);
			
		}
		if(!mover) {
			if(mostrador>imagenesIde.length-1) {
				mostrador=0;
			}
			e.dibujarImagen(imagenesIde[mostrador] , x, y+15, 0.0);
			this.contador++;
			if (contador>12) {
				this.mostrador++;
				if(mostrador>imagenesIde.length-1) {
					this.mostrador=0;
				}
				this.contador=0;
			}
		}else {
			this.contador++;
			if (contador>8) {
				this.mostrador++;
				if(mostrador>imagenesD.length-1) {
					this.mostrador=0;
				}
				this.contador=0;
			}
		}
	}
	
	void setImagenes() {
		this.imagenesD =new Image [derecha.length];
		this.imagenesI =new Image [izquierda.length];
		this.imagenesIde=new Image [ide.length];
		
		for(int i =0; i<izquierda.length;i++) {
			imagenesI[i]=new ImageIcon(izquierda[i]).getImage();
			
		}
		for(int i =0; i<derecha.length;i++) {
			imagenesD[i]=new ImageIcon(derecha[i]).getImage();
			
		}
		for(int i =0; i<ide.length;i++) {
			imagenesIde[i]=new ImageIcon(ide[i]).getImage();
			
		}
	}
	
	
	

	

}
