package es.eduardoanton.proyectos.juegos;

import com.badlogic.gdx.audio.Music;

import com.badlogic.gdx.math.MathUtils;

public class Musica {
	public static Music musica;
	public static int i;
	public static String[] lista = {"Run Amok.mp3","Monkeys Spinning Monkeys.mp3"
		,"Waunobe March.mp3","Fig Leaf Times Two.mp3","Merry Go.mp3","Plucky Daisy.mp3","Look Busy.mp3",
		"Five Card Shuffle.mp3","Friendly Day.mp3"};
	
	public static void playRandom(){
		i = MathUtils.random(lista.length - 1);
		musica = SeeSawCircus.getAsset().get(lista[i]);
		musica.setVolume(0.5f);
		musica.setLooping(false);
		musica.play();
	}
	
	public static void setVolume(float vol){
		musica.setVolume(vol);
	}


	public static boolean isPlaying() {
		return musica.isPlaying();
		
	}
	
	public static String getName(){
		return lista[i].substring(0, lista[i].lastIndexOf('.'));
	}

	
}
