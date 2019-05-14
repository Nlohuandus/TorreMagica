package juego;

import java.io.File;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
public class Sonidos {

	public static void main (String [] args) {
		salto();
	}
	static void reproducir(File sonido) {
		try {
			Clip clip=AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(sonido));
			clip.start();
			Thread.sleep(clip.getMicrosecondLength()/1000);
			clip.stop();
		}catch (Exception e) {
			
		}
	}
	static void gameOver() {
		File gameover = new File ("GameOver.wav");
		reproducir(gameover);
	}
	static void disparo() {
		File disparo = new File ("Disparo.wav");
		reproducir(disparo);
	}
	static void caminar() {
		File caminar = new File ("Caminar.wav");
		reproducir(caminar);
	}
	static void salto() {
		File saltar = new File ("Salto.wav");
		reproducir(saltar);
	}
	
}