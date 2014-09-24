package es.eduardoanton.proyectos.juegos;

import com.badlogic.gdx.audio.Music;

import com.badlogic.gdx.math.MathUtils;

public class Musica {
	public static Music musica;
	public static int i;
	public static String[] lista = {"Run Amok.ogg","Monkeys Spinning Monkeys.ogg"
		,"Waunobe March.ogg","Fig Leaf Times Two.ogg","Merry Go.ogg","Plucky Daisy.ogg","Look Busy.ogg",
		"Five Card Shuffle.ogg","Friendly Day.ogg"};
	
	public static void playRandom(){
		i = MathUtils.random(lista.length - 1);
		musica = SeeSawCircus.asset.get(lista[i]);
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

	public static void stop(){
		musica.stop();
	}
	
}
