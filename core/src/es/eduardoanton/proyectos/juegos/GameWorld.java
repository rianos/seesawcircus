package es.eduardoanton.proyectos.juegos;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

import es.eduardoanton.proyectos.juegos.Payaso.PayasoState;
import es.eduardoanton.proyectos.juegos.Trampolin.TrampolinState;

public class GameWorld {
	public SeeSawCircus game;
	public Trampolin trampolin;
	public Payaso payaso1;
	public Payaso payaso2;
	public Vector2 paraguas,paraguasfalling;
	public Vector2 muelle,muellefalling,muellev;
	public Vector2 paraguasv,trapecio;
	public FilaObjetivos arrayFilaObjetivos[];
	public final static int aceleracion = -1380;
	public final static int maxvel = 1250;
	public final static float redimen = 4.5f;
	public final static float redimen2 = 12;
	public final static int worlheight = 600;
	public final static int worlwidth = 1024;
	public Sound boingS,crashS,angelS,hurtS,cryS,mareoS,clanS,succesS,wowS,jump2S,coinS,billeteS,ballonS,gameoverS;
	public Sound horseS,lionroarS,bearS,elephantS,dogS,focaS,monoS,highscoreS,boingpS,bonusS,jumpiniS,bounS,swapS,piedra1S,piedra2S,piedra3S;
	public long scoreboard = 0,record = 0,flipsC = 0,caramelosC = 0, globosC=0,animalesC=0,mplataC=0,moroC=0,billeteC=0,fcompletaC=0,regalosC = 0;
	public short vidas = 5;
	public short paraguasc = 3;
	public short muellec = 3;
	public float time = 0f,timeready = 0f;
	public float timeregalo = 0f;
	public enum GameState { GAMEOVER, RUNNING, DEATH, READY};
	public GameState gamestate  = GameState.READY;
	public boolean isrecord,playedrecord,ispremio;
	public float isvelocidad = 1;
	public int regalo;
	public boolean modechildren = false;
	public static boolean modemusic = false;
	public static boolean modemanual = false;
	public boolean pausedgame = false;
	public long vidaseach, muelleseach,lastvidas = 0, lastmuelles = 0;


	GameWorld(SeeSawCircus game){
		this.game = game;
		gamestate  = GameState.READY;
		trampolin = new Trampolin();
		payaso1 = new Payaso(400,50,0,0,PayasoState.STANDBYL, this, 1);
		payaso2 = new Payaso(500,50,0,0,PayasoState.FLYING, this, 2);
		payaso1.setPayasoCompañero(payaso2);
		payaso2.setPayasoCompañero(payaso1);
		paraguas = new Vector2(-100,-100);
		paraguasfalling = new Vector2(-100,-100);
		muelle = new Vector2(-100,-100);
		muellefalling = new Vector2(-100,-100);
		muellec = 3;
		paraguasv = new Vector2(0,-200);
		muellev = new Vector2(0,-20);
		arrayFilaObjetivos = new FilaObjetivos[4];
		arrayFilaObjetivos[0] = new FilaObjetivos(0,0,400,-1,this);
		arrayFilaObjetivos[1] = new FilaObjetivos(1,0,450,1,this);
		arrayFilaObjetivos[2] = new FilaObjetivos(2,0,500,-1,this);
		arrayFilaObjetivos[3] = new FilaObjetivos(3,0,550,1,this);
		boingS = SeeSawCircus.asset.get("jump.ogg", Sound.class);
		crashS = SeeSawCircus.asset.get("crash.mp3", Sound.class);
		angelS = SeeSawCircus.asset.get("angel.mp3", Sound.class);
		hurtS = SeeSawCircus.asset.get("hurt.ogg", Sound.class);
		cryS = SeeSawCircus.asset.get("cry.ogg", Sound.class);
		mareoS = SeeSawCircus.asset.get("mareo.ogg", Sound.class);
		clanS = SeeSawCircus.asset.get("clan.wav", Sound.class);
		succesS = SeeSawCircus.asset.get("succes.wav", Sound.class);
		wowS = SeeSawCircus.asset.get("small_crowd_saying_wow.mp3", Sound.class);
		jump2S = SeeSawCircus.asset.get("comedy_siren_whistle_great_for_slips_and_trips_002.mp3", Sound.class);
		coinS = SeeSawCircus.asset.get("multimedia_tone_signifies_end.mp3", Sound.class);
		billeteS = SeeSawCircus.asset.get("antique_cash_register_punching_single_key.mp3", Sound.class);
		ballonS = SeeSawCircus.asset.get("comedy_bubble_pop_002.mp3", Sound.class);
		horseS = SeeSawCircus.asset.get("horse.ogg", Sound.class);
		lionroarS = SeeSawCircus.asset.get("lionroar.ogg", Sound.class);
		bearS = SeeSawCircus.asset.get("bear.ogg", Sound.class);
		elephantS = SeeSawCircus.asset.get("elephant.ogg", Sound.class);
		dogS = SeeSawCircus.asset.get("dog_barking_05.mp3", Sound.class);
		monoS = SeeSawCircus.asset.get("animal_chimpanzee_chimp_screams.ogg", Sound.class);
		focaS = SeeSawCircus.asset.get("41384__sandyrb__milk-jug-seal-01.ogg", Sound.class);
		boingpS = SeeSawCircus.asset.get("boingsda.mp3",Sound.class);
		bonusS = SeeSawCircus.asset.get("bonus_1.mp3",Sound.class);
		jumpiniS = SeeSawCircus.asset.get("182442__qubodup__drum-roll-with-cymbals-crash.ogg", Sound.class);
		gameoverS = SeeSawCircus.asset.get("comedy_trumpet_playing_sad_song_wah_wah_wah_wah.mp3", Sound.class);
		bounS = SeeSawCircus.asset.get("trampoline_bounce_lite_01.ogg", Sound.class);
		swapS = SeeSawCircus.asset.get("cartoon_fast_whoosh_good_for_karate_chop_other_fast_movement_or_swipe_001.mp3", Sound.class);
		piedra1S = SeeSawCircus.asset.get("impact_rock_on_rubble_003.mp3", Sound.class);
		piedra2S = SeeSawCircus.asset.get("impact_rock_on_rubble_006.mp3", Sound.class);
		piedra3S = SeeSawCircus.asset.get("wood_stick_hit_log_soft.mp3", Sound.class);
		ispremio = false;
		timeready = 0f;
		modechildren =  SeeSawCircus.prefs.getBoolean("modoninos", false);
		modemusic =  SeeSawCircus.prefs.getBoolean("modomusica", true);
		modemanual = SeeSawCircus.prefs.getBoolean("modomanual", false);
	}
	
	public void reset(){
		//vidaseach = 25000;
		//muelleseach = 10000;
		vidaseach = 15000;
		muelleseach = 8000;
		lastvidas = 0;
		lastmuelles = 0;
		pausedgame = false;
		modechildren =  SeeSawCircus.prefs.getBoolean("modoninos", false);
		modemusic =  SeeSawCircus.prefs.getBoolean("modomusica", true);
		muelle = new Vector2(-100,-100);
		muellefalling = new Vector2(-100,-100);
		muellec = 3;
		paraguas = new Vector2(-100,-100);
		isvelocidad = 1f;
		paraguasfalling = new Vector2(-100,-100);
		paraguasv = new Vector2(0,-200);
		trampolin = new Trampolin();
		payaso1 = new Payaso(500,50,0,0,PayasoState.STANDBYL, this, 1);
		payaso2 = new Payaso(500,300,0,200,PayasoState.FLYING, this, 2);
		payaso1.setPayasoCompañero(payaso2);
		payaso2.setPayasoCompañero(payaso1);
		scoreboard = 0;
		arrayFilaObjetivos = new FilaObjetivos[4];
		arrayFilaObjetivos[0] = new FilaObjetivos(0,0,400,-1,this);
		arrayFilaObjetivos[1] = new FilaObjetivos(1,0,450,1,this);
		arrayFilaObjetivos[2] = new FilaObjetivos(2,0,500,-1,this);
		arrayFilaObjetivos[3] = new FilaObjetivos(3,0,550,1,this);
		timeready = 0f;
		vidas = 3;
		paraguasc = 3;
		flipsC = 0;
		caramelosC = 0;
		globosC=0;
		animalesC=0;
		mplataC=0;
		moroC=0;
		billeteC=0;
		fcompletaC=0;
		regalosC = 0;
		timeregalo = 0f;
		isrecord = false;
		playedrecord = false;
		ispremio = false;
		trapecio = new Vector2(446,580);
		if ( modechildren ){
			record = SeeSawCircus.prefs.getLong("recordninos", 0);
		}else{
			record = SeeSawCircus.prefs.getLong("record", 0);
		}
		resetready(payaso2);
	}
	
	public void resetready(Payaso p){
		gamestate = GameState.READY;
		p.state = PayasoState.FLYING;
		trapecio.y = 595;
		p.posicion.x = 450;
		p.posicion.y = 600;
		p.velocidad.x = 0;
		p.velocidad.y = 301;
		trampolin.posicion.x = 390;
	    trampolin.velocidad.y = 0;
		trampolin.velocidad.x = 0;
		if ( trampolin.view == TrampolinState.LEFT){
			p.pc.posicion.x = 350;
			p.pc.posicion.y = 50;
			p.pc.state = PayasoState.STANDBYR;
		}else{
			p.pc.posicion.x = 350;
			p.pc.posicion.y = 50;
			p.pc.state = PayasoState.STANDBYL;	
		}
		trampolin.aceleracion.y = 0;
		trampolin.posicion.y = 40;
		jumpiniS.play();
		timeready = 0;
		if ( GameWorld.modemusic){
			Musica.setVolume(1f);
		}
	}
	
	public void update(float delta){
		if (!pausedgame){
			if (gamestate == GameState.READY){
				Musica.stop();	
				timeready+=delta;
				updatetrapecio(delta);
				
				trampolin.update(delta);
				payaso1.update(delta);
				payaso2.update(delta);
				for (FilaObjetivos fila : arrayFilaObjetivos){
					fila.update(delta);
				}
				if (timeready > 5.5){
					gamestate = GameState.RUNNING;
				}
			}
			
			if ( gamestate == GameState.GAMEOVER){
				time+=delta;
				if (time > 3){
					game.setScreen(SeeSawCircus.gameoverscreen);
				}
			}
			if (gamestate == GameState.RUNNING || gamestate == GameState.DEATH){
				updatetrapecio(delta);
				if (GameWorld.modemusic && !Musica.isPlaying() ) {
					Musica.playRandom();
				}
				trampolin.update(delta);
				payaso1.update(delta);
				payaso2.update(delta);
				if ( paraguasfalling.y < -10 ){
					paraguasfalling.x = -100;
					paraguasfalling.y = 0;
				
				}else{
					paraguasfalling.add(paraguasv.cpy().scl(delta));
				}
				if ( muellefalling.y < -40 ){
					muellefalling.x = -100;
					muellefalling.y = 0;
				}else{
					muellefalling.add(muellev.cpy().scl(delta));
				}
				for (FilaObjetivos fila : arrayFilaObjetivos){
					fila.update(delta);
				}
				if ( scoreboard >= record){
					record = scoreboard;
					if (!playedrecord){
						Musica.highscoreplay(0.5f);
						isrecord = true;
						playedrecord = true;
						time = delta;
					}
				}
				calculapremiosespeciales(delta);
				if ( this.vidas == 0){
					this.gamestate = GameState.GAMEOVER;
					time = delta;
					Musica.stop();
					gameoverS.play();
				}
			}
		}
	}
	
	public void calculapremiosespeciales(float delta){
		long rv = (long)(scoreboard / vidaseach);
		if ( rv > lastvidas ){
			if ( vidas < 3){
				bonusS.play();
				vidas++;
				lastvidas = rv;
				timeregalo = delta;
				regalo = 0;
				ispremio = true;
			}else{
				lastvidas = rv;
			}
		}
		rv =  (long)(scoreboard / muelleseach);
		if ( rv > lastmuelles ){
			if (muellec < 3){
				bonusS.play();
				muellec++;
				lastmuelles = rv;
				timeregalo = delta;
				regalo = 5;
				ispremio = true;
			}else{
				lastmuelles = rv;
			}
		}
	}
	
	
	public void updatetrapecio(float delta){
		if (gamestate == GameState.READY ){
			trapecio.y-=100*delta;
			if ( trapecio.y <= 295){
				trapecio.y = 295;
			}
		}
		if ( gamestate == GameState.RUNNING || gamestate == GameState.DEATH ){
			trapecio.y+=300*delta;
			if (trapecio.y > 800){
				trapecio.y = 800;
			}
		}
	}
	
	
	public int generapremio(){
		// premios
		// vida = 0
		// paraguas = 1
		// gran premio = 2
		// hueso = 3
		// velocidad = 4
		int tmp;
		if (vidas < 3  && paraguasc < 3){
			int array[] = {0,0,0,1,1,1,1,1,2,2,3,4};
			tmp =  MathUtils.random(0,11);
			tmp = array[tmp];
		}else if (vidas < 3 && paraguasc == 3){
			int array[] =  {0,0,0,2,3,4};
			tmp =   MathUtils.random(0,5);
			tmp = array[tmp];
		}else if (vidas == 3 && paraguasc < 3){
			int array[] = {1,1,1,1,2,2,3,4};
			tmp =  MathUtils.random(0,7);
			tmp = array[tmp];
		}else{
			int array[] = {2,2,2,3,4};
			tmp =  MathUtils.random(0,4);
			tmp = array[tmp];
		}
		switch (tmp){
			case 0: vidas++;
					break;
			case 1: paraguasc++;
					break;
			case 2: scoreboard+=1000;
					break;
			case 3: scoreboard+=5000;
					dogS.play();
					break;
			case 4: isvelocidad = 1.5f;
					break;
		}
		return tmp;
	}
	
	public int generapremiodos(){
		// premios
		// vida = 0
		// paraguas = 1
		// gran premio = 2
		// hueso = 3
		// velocidad = 4
		// muelle = 5;
		int tmp;
		if (vidas < 5  && paraguasc < 3 && muellec < 3){
			int array[] = {5,5,5,5,5,1,1,1,0,0};
			tmp =  MathUtils.random(0,9);
			tmp = array[tmp];
		}else if (vidas < 5 && paraguasc == 3 && muellec < 3){
			int array[] =  {5,5,5,5,0,0};
			tmp =   MathUtils.random(0,5);
			tmp = array[tmp];
		}else if (vidas == 5 && paraguasc < 3 && muellec == 3){
			int array[] = {1,1};
			tmp =  MathUtils.random(0,1);
			tmp = array[tmp];
		}else if (vidas == 5 && paraguasc == 3 && muellec < 3){
			int array[] = {5,5};
			tmp =  MathUtils.random(0,1);
			tmp = array[tmp];
		}else if (vidas < 5 && paraguasc == 3 && muellec == 3){
			int array[] = {0,0};
			tmp =  MathUtils.random(0,1);
			tmp = array[tmp];
		}else{
			int array[] = {2,4};
			tmp =  MathUtils.random(0,1);
			tmp = array[tmp];
		}
		switch (tmp){
			case 0: vidas++;
					break;
			case 1: paraguasc++;
					break;
			case 2: scoreboard+=1000;
					break;
			case 3: scoreboard+=5000;
					dogS.play();
					break;
			case 4: isvelocidad = 1.5f;
					break;
			case 5: muellec++;
					break;
		}
		return tmp;
	}
	
	
	public int generapremiotres(){
		// premios
		// vida = 0
		// paraguas = 1
		// gran premio = 2
		// hueso = 3
		// velocidad = 4
		// muelle = 5;
		// +100 = 6
		// +200 = 7
		// +400 = 8
		// +500 = 9
		int tmp;
		int  array[] = {3,2,2,4,4,4,4,9,9,9,8,8,8,8,8,7,7,7,7,7,7,7,7,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6};
		tmp =  MathUtils.random(0,40);
		tmp = array[tmp];
		
		switch (tmp){
			case 0: vidas++;
					break;
			case 1: paraguasc++;
					break;
			case 2: scoreboard+=1000;
					break;
			case 3: scoreboard+=5000;
					dogS.play();
					break;
			case 4: isvelocidad = 1.5f;
					break;
			case 5: muellec++;
					break;
			case 6: scoreboard+=100;
					break;
			case 7: scoreboard+=200;
					break;
			case 8: scoreboard+=400;
				    break;
			case 9: scoreboard+=500;
					break;
		}	
		return tmp;
	}
	
	
	
	public void flip(){
		float distancia;
		Payaso pf,ps;
		pf = getPayasoFlying();
		ps = pf.pc;
		if (trampolin.view == TrampolinState.LEFT){
			trampolin.view = TrampolinState.RIGHT;
			distancia = (trampolin.posicion.x + (trampolin.dimensiones.width / 4));
			distancia = distancia - pf.posicion.x - (pf.dimensiones.width / 2);
			pf.state = PayasoState.STANDBYL;
			ps.velocidad.y = pf.velocidad.y*-1  + distancia*redimen2;
			ps.velocidad.y = Math.min(ps.velocidad.y, maxvel);
			ps.velocidad.x = distancia * redimen ;
			ps.state = PayasoState.FLYING;
		}else{
			trampolin.view = TrampolinState.LEFT;
			distancia = (trampolin.posicion.x + (trampolin.dimensiones.width / 4)*3);
			distancia = distancia - pf.posicion.x - (pf.dimensiones.width /2);
			pf.state = PayasoState.STANDBYR;
			ps.velocidad.y = pf.velocidad.y*-1  - distancia*redimen2;
			ps.velocidad.y = Math.min(ps.velocidad.y, maxvel);
			ps.velocidad.x = distancia * redimen ;
			ps.state = PayasoState.FLYING;
		}
		if ((payaso1.velocidad.y > 1200 || payaso2.velocidad.y > 1200) && Math.random() > 0.7f){
			jump2S.play(0.4f);
		}else{
			boingS.play(0.15f);
		}
	}
	
	public void flipParaguas(){
		if (trampolin.view == TrampolinState.LEFT){
			payaso1.velocidad.y = 1000;
			payaso1.velocidad.x = -100 ;
		
		}else{
			payaso2.velocidad.y = 1000;
			payaso2.velocidad.x = 100 ;
		}
		paraguasfalling.x = paraguas.x;
		paraguasfalling.y = paraguas.y; 
		paraguasc--;
		boingpS.play();
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
