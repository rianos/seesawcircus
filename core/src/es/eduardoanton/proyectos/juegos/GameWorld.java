package es.eduardoanton.proyectos.juegos;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

import es.eduardoanton.proyectos.juegos.Payaso.PayasoState;
import es.eduardoanton.proyectos.juegos.Trampolin.TrampolinState;

public class GameWorld {
	public Trampolin trampolin;
	public Payaso payaso1;
	public Payaso payaso2;
	public FilaObjetivos arrayFilaObjetivos[];
	public float redondo = 0;
	public final static int aceleracion = -1380;
	public final static int maxvel = 1250;
	public final static float redimen = 4.5f;
	public final static float redimen2 = 12;
	public final static int worlheight = 600;
	public final static int worlwidth = 1024;
	public Sound boingS,crashS,angelS,hurtS,cryS,mareoS,clanS,succesS,wowS,jump2S,coinS,billeteS,ballonS,welldoneS;
	public Sound horseS,lionroarS,bearS,elephantS,dogS,focaS,monoS;
	public long scoreboard = 0;
	public short vidas = 5;
	public enum GameState { GAMEOVER, RUNNING, DEATH};
	public GameState gamestate  = GameState.RUNNING;

	GameWorld(){
		trampolin = new Trampolin();
		payaso1 = new Payaso(400,50,0,0,PayasoState.STANDBYL, this, 1);
		payaso2 = new Payaso(500,300,0,0,PayasoState.FLYING, this, 2);
		payaso1.setPayasoCompañero(payaso2);
		payaso2.setPayasoCompañero(payaso1);
		arrayFilaObjetivos = new FilaObjetivos[4];
		arrayFilaObjetivos[0] = new FilaObjetivos(0,0,400,-1,this);
		arrayFilaObjetivos[1] = new FilaObjetivos(1,0,450,1,this);
		arrayFilaObjetivos[2] = new FilaObjetivos(2,0,500,-1,this);
		arrayFilaObjetivos[3] = new FilaObjetivos(3,0,550,1,this);
		boingS = Gdx.audio.newSound(Gdx.files.internal("jump.ogg"));
		crashS = Gdx.audio.newSound(Gdx.files.internal("crash.mp3"));
		angelS = Gdx.audio.newSound(Gdx.files.internal("angel.mp3"));
		hurtS = Gdx.audio.newSound(Gdx.files.internal("hurt.ogg"));
		cryS = Gdx.audio.newSound(Gdx.files.internal("cry.ogg"));
		mareoS = Gdx.audio.newSound(Gdx.files.internal("mareo.ogg"));
		clanS = Gdx.audio.newSound(Gdx.files.internal("clan.wav"));
		succesS = Gdx.audio.newSound(Gdx.files.internal("succes.wav"));
		wowS = Gdx.audio.newSound(Gdx.files.internal("small_crowd_saying_wow.mp3"));
		jump2S = Gdx.audio.newSound(Gdx.files.internal("comedy_siren_whistle_great_for_slips_and_trips_002.mp3"));
		coinS = Gdx.audio.newSound(Gdx.files.internal("multimedia_tone_signifies_end.mp3"));
		billeteS = Gdx.audio.newSound(Gdx.files.internal("antique_cash_register_punching_single_key.mp3"));
		ballonS = Gdx.audio.newSound(Gdx.files.internal("comedy_bubble_pop_002.mp3"));
		welldoneS = Gdx.audio.newSound(Gdx.files.internal("ringtone_001.mp3"));
		horseS = Gdx.audio.newSound(Gdx.files.internal("horse.ogg"));
		lionroarS = Gdx.audio.newSound(Gdx.files.internal("lionroar.ogg"));
		bearS = Gdx.audio.newSound(Gdx.files.internal("bear.ogg"));
		elephantS = Gdx.audio.newSound(Gdx.files.internal("elephant.ogg"));
		dogS = Gdx.audio.newSound(Gdx.files.internal("dog_barking_05.mp3"));
		monoS = Gdx.audio.newSound(Gdx.files.internal("animal_chimpanzee_chimp_screams.ogg"));
		focaS = Gdx.audio.newSound(Gdx.files.internal("41384__sandyrb__milk-jug-seal-01.ogg"));
		
		

	}
	public void reset(){
		trampolin = new Trampolin();
		payaso1 = new Payaso(500,50,0,0,PayasoState.STANDBYL, this, 1);
		payaso2 = new Payaso(500,500,-90,0,PayasoState.FLYING, this, 2);
		payaso1.setPayasoCompañero(payaso2);
		payaso2.setPayasoCompañero(payaso1);
		arrayFilaObjetivos = new FilaObjetivos[4];
		arrayFilaObjetivos[0] = new FilaObjetivos(0,0,400,-1,this);
		arrayFilaObjetivos[1] = new FilaObjetivos(1,0,450,1,this);
		arrayFilaObjetivos[2] = new FilaObjetivos(2,0,500,-1,this);
		arrayFilaObjetivos[3] = new FilaObjetivos(3,0,550,1,this);
		scoreboard = 0;
		vidas = 5;
		this.gamestate = GameState.RUNNING;
		
	}
	
	public void update(float delta){
		if (!Musica.isPlaying()){
			Musica.playRandom();
		}
		if (gamestate != GameState.GAMEOVER){
			trampolin.update(delta);
			payaso1.update(delta);
			payaso2.update(delta);
			for (FilaObjetivos fila : arrayFilaObjetivos){
				fila.update(delta);
			}
			if ( this.vidas == 0){
				this.gamestate = GameState.GAMEOVER;
			}
		}
	}
	
	public void flip(){
		float distancia;
		if (trampolin.view == TrampolinState.LEFT){
			trampolin.view = TrampolinState.RIGHT;
			distancia = (trampolin.posicion.x + (trampolin.dimensiones.width / 4));
			if (payaso1.state == PayasoState.FLYING){
				distancia = distancia - payaso1.posicion.x - (payaso1.dimensiones.width /2);		
				payaso1.state = PayasoState.STANDBYL;
				payaso2.velocidad.y = payaso1.velocidad.y*-1  + distancia*redimen2;
				payaso2.velocidad.y = Math.min(payaso2.velocidad.y, maxvel);
				payaso2.velocidad.x = distancia * redimen ;
				payaso2.state = PayasoState.FLYING;
			}else{
				distancia = distancia - payaso2.posicion.x - (payaso2.dimensiones.width /2);
				payaso2.state = PayasoState.STANDBYL;
				payaso1.velocidad.y = payaso2.velocidad.y*-1  + distancia*redimen2;
				payaso1.velocidad.y = Math.min(payaso1.velocidad.y, maxvel);
				payaso1.velocidad.x = distancia * redimen ;
				payaso1.state = PayasoState.FLYING;
			}
		}else{
			trampolin.view = TrampolinState.LEFT;
			distancia = (trampolin.posicion.x + (trampolin.dimensiones.width / 4)*3);
			if (payaso1.state == PayasoState.FLYING){
				distancia = distancia - payaso1.posicion.x - (payaso1.dimensiones.width /2);
				payaso1.state = PayasoState.STANDBYR;
				payaso2.velocidad.y = payaso1.velocidad.y*-1  - distancia*redimen2;
				payaso2.velocidad.y = Math.min(payaso2.velocidad.y, maxvel);
				payaso2.velocidad.x = distancia * redimen ;
				payaso2.state = PayasoState.FLYING;
			}else{
				distancia = distancia - payaso2.posicion.x - (payaso2.dimensiones.width /2);
				payaso2.state = PayasoState.STANDBYR;
				payaso1.velocidad.y = payaso2.velocidad.y*-1  - distancia*redimen2;
				payaso1.velocidad.y = Math.min(payaso1.velocidad.y, maxvel);
				payaso1.velocidad.x = distancia * redimen ;
				payaso1.state = PayasoState.FLYING;
			}
		}
		if ((payaso1.velocidad.y > 1200 || payaso2.velocidad.y > 1200) && Math.random() > 0.7f){
			jump2S.play(0.4f);
		}else{
			boingS.play(0.15f);
		}
	}
	
	public Payaso  getPayasoFlying(){
		if (payaso1.state == PayasoState.FLYING){
			return payaso1;
		}else{
			return payaso2;
		}
	}
	
	public Payaso  notgetPayasoFlying(){
		if (payaso1.state == PayasoState.FLYING){
			return payaso2;
		}else{
			return payaso1;
		}
	}
	
}
