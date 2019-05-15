package juego;

import java.io.File;
import java.io.InputStream;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
public class Sonidos {
	File saltar = new File ("Salto.wav");
	File caminar = new File ("Caminar.wav");
	File disparo = new File ("Disparo.wav");
	File gameover = new File ("GameOver.wav");
	
Sonidos(String s){
	try {
		Clip saltar=AudioSystem.getClip();
		saltar.open(AudioSystem.getAudioInputStream(this.saltar));
		Clip caminar=AudioSystem.getClip();
		caminar.open(AudioSystem.getAudioInputStream(this.caminar));
		Clip disparo=AudioSystem.getClip();
		disparo.open(AudioSystem.getAudioInputStream(this.disparo));
		Clip gameover=AudioSystem.getClip();
		gameover.open(AudioSystem.getAudioInputStream(this.gameover));
		if(s.equals("saltar")){
		saltar.start();
		Thread.sleep(saltar.getMicrosecondLength()/1000);
		saltar.stop();
	}else if(s.equals("caminar")){
		caminar.start();
		Thread.sleep(caminar.getMicrosecondLength()/1000);
		caminar.stop();
	}else if(s.equals("disparo")){
		disparo.start();
		Thread.sleep(disparo.getMicrosecondLength()/1000);
		disparo.stop();
	} else{
		gameover.start();
		Thread.sleep(gameover.getMicrosecondLength()/1000);
		gameover.stop();
	}
		
	}catch (Exception e) {
		
	}
}
	public static void main (String [] args) {
		Sonidos s=new Sonidos("saltar");
		
	}
	/*static void reproducir(File sonido) {
		try {
			Clip clip=AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(sonido));
			clip.start();
			Thread.sleep(clip.getMicrosecondLength()/1000);
			clip.stop();
		}catch (Exception e) {
			
		}
	}*/
	
}