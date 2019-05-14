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
	

}
