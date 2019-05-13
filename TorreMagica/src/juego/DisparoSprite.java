package juego;

import java.awt.Image;

import javax.swing.ImageIcon;

import entorno.Entorno;

public class DisparoSprite {
	double x,y;
	int contador =0,mostrador=0;
	Image [] imagenesD;
	Image [] imagenesI;
	String [] disparo= {"poder0001.png","poder0002.png","poder0003.png","poder0004.png","poder0005.png"};
	String [] disparoD= {"poderD0001.png","poderD0002.png","poderD0003.png","poderD0004.png","poderD0005.png"};
	boolean animar;
	
	public DisparoSprite(double x,double y) {
		//imagen = new ImageIcon("0001.png").getImage();
		this.x=x;
		this.y=y;
		setImagenes();
		this.animar=false;
	}
	
	void setImagenes() {
		this.imagenesD =new Image [disparoD.length];
		this.imagenesI =new Image [disparo.length];
		
		for(int i =0; i<disparoD.length;i++) {
			imagenesD[i]=new ImageIcon(disparoD[i]).getImage();	
		}
		for(int i =0; i<disparo.length;i++) {
			imagenesI[i]=new ImageIcon(disparo[i]).getImage();	
		}


	}
	
	public void dibujar(Entorno e,double x,double y,boolean derecha) {
		if(animar) {
			if(derecha) {

				e.dibujarImagen(imagenesD[mostrador] , x, y, 0.0);
				
			}else {
				e.dibujarImagen(imagenesI[mostrador] , x, y, 0.0);
			}
			
			this.contador++;
			if (contador>6) {
				this.mostrador++;
				if(derecha) {
					if(mostrador>imagenesD.length-1) {
						this.mostrador=0;
					}
				}else {
					if(mostrador>imagenesI.length-1) {
						this.mostrador=0;
					}
				}

			}
		}
		
	}
	public void dibujar(Entorno e,boolean derecha) {
		if(animar) {
			if(derecha) {
				e.dibujarImagen(imagenesD[mostrador] , x+80, y, 0.0);
			}else {
				e.dibujarImagen(imagenesD[mostrador] , x-80, y, 0.0);
			}
			
			this.contador++;
			if (contador>12) {
				this.mostrador++;
				if(mostrador>imagenesD.length-3) {
					this.mostrador=imagenesD.length-1;
				}
			}
		}
		
	}
	public void dibujar(Entorno e,double x,double y,boolean derecha,int i) {

		e.dibujarImagen(imagenesI[i] , x-50, y, 0.0);


	}
}
