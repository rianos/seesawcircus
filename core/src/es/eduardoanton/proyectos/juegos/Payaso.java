package es.eduardoanton.proyectos.juegos;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import es.eduardoanton.proyectos.juegos.GameWorld.GameState;
import es.eduardoanton.proyectos.juegos.Trampolin.TrampolinState;

public class Payaso {
	public Vector2 posicion;
	public Vector2 velocidad;
	public Vector2 aceleracion;
	public Rectangle dimensiones;
	private GameWorld game;
	public enum PayasoState { STANDBYL, STANDBYR, FLYING, JUMPING,MESSDEATH,MESSCRASH, MESSCRY };
	public PayasoState state,oldstate;
	public int PayasoID;
	public Payaso pc; 
	public final float vellimit = -400f;
	private float time= 0f;
	private boolean angelSplayed = false;
	private long tempninossalto;
	
	Payaso (int x, int y, int vx, int vy, PayasoState s, GameWorld game,int id){
		PayasoID = id;
		posicion = new Vector2(x,y);
		velocidad = new Vector2(vx,vy);
		dimensiones = new Rectangle(0,0,64,59);
		aceleracion = new Vector2(0,GameWorld.aceleracion);
		state = s;
		this.game = game;
		tempninossalto = 0;
	}
	
	public void setPayasoCompañero(Payaso p){
		pc = p;
	}

	
	
	public void update(float delta ){
		if ( state == PayasoState.STANDBYR ){
			posicion.y = 50;
			posicion.x = game.trampolin.posicion.x + game.trampolin.dimensiones.width  - dimensiones.width;
			if ( (game.trampolin.posicion.x >= SeeSawCircus.screenwidth - game.trampolin.dimensiones.width*1.125) && game.modechildren ){
				game.paraguas.x = posicion.x - 10;
				game.paraguas.y = posicion.y + 15 ;
			}else{
				game.paraguas.x = -100;
				game.paraguas.y = -100;
			}
		}
		if ( state == PayasoState.STANDBYL ){
			posicion.y = 50;
			posicion.x = game.trampolin.posicion.x  ;
			if ( (game.trampolin.posicion.x <= game.trampolin.dimensiones.width /8 ) && game.modechildren){
				game.paraguas.x = posicion.x - 10;
				game.paraguas.y = posicion.y + 15;
			}else{
				game.paraguas.x = -100;
				game.paraguas.y = -100;
			}
		}
		if ( state == PayasoState.FLYING && game.gamestate == GameState.READY){
			posicion.y-=100*delta;
			if ( posicion.y <= 300){
				posicion.y = 300;
			}
		}
		
		if ( state == PayasoState.FLYING && game.gamestate == GameState.RUNNING){
			velocidad.add(aceleracion.cpy().scl(delta));
			posicion.add(velocidad.cpy().scl(delta));
			if (posicion.y < 500 && velocidad.y < 0 && !game.modechildren && game.muellec > 0 &&((posicion.x + dimensiones.width < game.trampolin.posicion.x )|| 
					(posicion.x > game.trampolin.posicion.x + game.trampolin.dimensiones.width) )){
				
				game.muelle.x = posicion.x;
				game.muelle.y = 0;
			}else{
				game.muelle.x = -100;
			}
			if (posicion.y < 20 && game.modechildren){
				if ( (posicion.x + dimensiones.width < game.trampolin.posicion.x )|| 
						(posicion.x > game.trampolin.posicion.x + game.trampolin.dimensiones.width)) {
						velocidad.y = 800 - tempninossalto;
						tempninossalto+=150;
						posicion.y = 21;
						game.bounS.play();
				}else{
					tempninossalto = 0;
					game.vidas--;
					game.isvelocidad = 1f;
					state = PayasoState.MESSCRASH;
					game.gamestate = GameState.DEATH;
					game.ispremio= false;
					time = delta;
					IngameScreen.statetime = 0f;
					posicion.y = 25;
					game.trampolin.velocidad.y = Math.min(-velocidad.y,800);
					game.trampolin.velocidad.x = velocidad.x;
					game.trampolin.aceleracion.y= GameWorld.aceleracion;
					pc.oldstate = pc.state;
					pc.state = Payaso.PayasoState.MESSCRY;
					pc.velocidad.x = 0f;
					pc.posicion.y = 25;
					pc.velocidad.y =  0f;
					game.hurtS.play();
					game.cryS.play(0.2f);
					game.wowS.play();
					game.crashS.play();
				}
				
			}else if (posicion.y < 20 && !game.modechildren && game.muellec > 0 && game.muelle.x != -100){
				game.muellec--;
				velocidad.y = 800;
				posicion.y = 21;
				game.bounS.play();
				game.muellefalling.x = game.muelle.x;
				game.muellefalling.y = game.muelle.y;
			}else if (posicion.y < 20 && !game.modechildren  ){
				game.vidas--;
				game.isvelocidad = 1f;
				if ( (posicion.x + dimensiones.width < game.trampolin.posicion.x )|| 
							(posicion.x > game.trampolin.posicion.x + game.trampolin.dimensiones.width)) {
					state = PayasoState.MESSDEATH;
					game.gamestate = GameState.DEATH;
					game.ispremio = false;
					game.hurtS.play();
					time = delta;
					velocidad.x = 0f;
					posicion.y = 25;
					velocidad.y =  0f;
					Musica.setVolume(0.2f);		   
				}else{
					state = PayasoState.MESSCRASH;
					game.gamestate = GameState.DEATH;
					game.ispremio= false;
					time = delta;
					IngameScreen.statetime = 0f;
					posicion.y = 25;
					game.trampolin.velocidad.y = Math.min(-velocidad.y,800);
					game.trampolin.velocidad.x = velocidad.x;
					game.trampolin.aceleracion.y= GameWorld.aceleracion;
					pc.oldstate = pc.state;
					pc.state = Payaso.PayasoState.MESSCRY;
					pc.velocidad.x = 0f;
					pc.posicion.y = 25;
					pc.velocidad.y =  0f;
					game.hurtS.play();
					game.cryS.play(0.2f);
					game.wowS.play();
					game.crashS.play();
				}
			}else if (posicion.x <= 0 ){
				velocidad.x = velocidad.x * -1;
				posicion.x = 0;
			}else if (posicion.x >= SeeSawCircus.screenwidth - dimensiones.width ){
				velocidad.x = velocidad.x * -1;
				posicion.x = SeeSawCircus.screenwidth - dimensiones.width;
			}else if (posicion.y < (game.trampolin.dimensiones.height/2 + game.trampolin.posicion.y) && (velocidad.y < vellimit) ){
				if ( game.trampolin.view == TrampolinState.LEFT){
					if ( posicion.x + dimensiones.width > game.trampolin.posicion.x && posicion.x + dimensiones.width/2 < game.trampolin.posicion.x + (game.trampolin.dimensiones.width /2 )){
						tempninossalto = 0;
						game.flip();
						game.scoreboard+=1;
						game.flipsC+=1;
					}
				}else{
					if ( posicion.x  < game.trampolin.posicion.x + game.trampolin.dimensiones.width && (posicion.x + (dimensiones.width/2)) > (game.trampolin.posicion.x + (game.trampolin.dimensiones.width /2 ))){
						tempninossalto = 0;
						game.flip();
						game.scoreboard+=1;
						game.flipsC+=1;
					}
				}		
			}else if ((posicion.y < (game.trampolin.dimensiones.height + game.trampolin.posicion.y + 10) && (true)) && game.paraguasc > 0 && game.modechildren){
				if ( game.trampolin.view == TrampolinState.LEFT){
					if ((game.trampolin.posicion.x >= SeeSawCircus.screenwidth - game.trampolin.dimensiones.width*1.25) &&
							 (posicion.x + (dimensiones.width/2) > game.trampolin.posicion.x + (game.trampolin.dimensiones.width/2))
							){
						tempninossalto = 0;
						game.flipParaguas();
						game.scoreboard+=1;
						game.flipsC+=1;
					}
				}else{
					 if (( game.trampolin.posicion.x <= game.trampolin.dimensiones.width /4 ) &&
							((posicion.x - (dimensiones.width /2)) < game.trampolin.dimensiones.width/4)					 
							 ){
						 tempninossalto = 0;
						game.flipParaguas();
						game.scoreboard+=1;
						game.flipsC+=1;
					 }
				}	
			}
		}
		if ( state == PayasoState.MESSCRASH){
			time+=delta;
			if (time > 2.5){
				game.resetready(this);
			}
		}
		if ( state == PayasoState.MESSDEATH){
			time+=delta;
			if (time > 0.5f && ! angelSplayed){
				game.angelS.play();
				velocidad.y = 200f;
				angelSplayed = true;
			}
			posicion.add(velocidad.cpy().scl(delta));
			
			if (posicion.y > game.worlheight){
				angelSplayed = false;
				game.resetready(this);
			}
		}
	}
}
