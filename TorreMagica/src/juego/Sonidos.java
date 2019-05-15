package juego;

import java.io.File;

import javax.print.DocFlavor.STRING;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
public class Sonidos {
	File saltar = new File ("Salto.wav");
	File caminar = new File ("Caminar.wav");
	File disparo = new File ("Disparo.wav");
	File gameover = new File ("GameOver.wav");
Sonidos(String s){
	try {
		if(s.equals("saltar")){
		Clip clip=AudioSystem.getClip();
		clip.open(AudioSystem.getAudioInputStream(this.saltar));
		clip.start();
		Thread.sleep(clip.getMicrosecondLength()/1000);
		clip.stop();
	}else if(s.equals("caminar")){
		Clip clip=AudioSystem.getClip();
		clip.open(AudioSystem.getAudioInputStream(this.caminar));
		clip.start();
		Thread.sleep(clip.getMicrosecondLength()/1000);
		clip.stop();
	}else if(s.equals("disparo")){
		Clip clip=AudioSystem.getClip();
		clip.open(AudioSystem.getAudioInputStream(this.disparo));
		clip.start();
		Thread.sleep(clip.getMicrosecondLength()/1000);
		clip.stop();
	} else{
		Clip clip=AudioSystem.getClip();
		clip.open(AudioSystem.getAudioInputStream(this.gameover));
		clip.start();
		Thread.sleep(clip.getMicrosecondLength()/1000);
		clip.stop();
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