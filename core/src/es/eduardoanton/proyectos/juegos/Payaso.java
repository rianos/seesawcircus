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
	public final float vellimit = -600f;
	private float time= 0f;
	private boolean angelSplayed = false;
	
	
	Payaso (int x, int y, int vx, int vy, PayasoState s, GameWorld game,int id){
		PayasoID = id;
		posicion = new Vector2(x,y);
		velocidad = new Vector2(vx,vy);
		//dimensiones = new Rectangle(0,0,45,62);
		//dimensiones = new Rectangle(0,0,88,96);
		dimensiones = new Rectangle(0,0,64,59);
		aceleracion = new Vector2(0,GameWorld.aceleracion);
		state = s;
		this.game = game;
	}
	
	public void setPayasoCompañero(Payaso p){
		pc = p;
	}

	
	public void update(float delta ){
		if ( state == PayasoState.STANDBYR ){
			posicion.y = 50;
			posicion.x = game.trampolin.posicion.x + game.trampolin.dimensiones.width  - dimensiones.width;
		}
		if ( state == PayasoState.STANDBYL ){
			posicion.y = 50;
			posicion.x = game.trampolin.posicion.x  ;
		}
		if ( state == PayasoState.FLYING){
			velocidad.add(aceleracion.cpy().scl(delta));
			posicion.add(velocidad.cpy().scl(delta));
			if ( state == PayasoState.FLYING ){
				if (posicion.y < 20 ){
					game.vidas--;
					if ( (posicion.x + dimensiones.width < game.trampolin.posicion.x )|| 
							(posicion.x > game.trampolin.posicion.x + game.trampolin.dimensiones.width)) {
						state = PayasoState.MESSDEATH;
						game.gamestate = GameState.DEATH;
						game.hurtS.play();
						time = delta;
						velocidad.x = 0f;
						posicion.y = 25;
						velocidad.y =  0f;
						Musica.setVolume(0.2f);
					   
					}else{
						state = PayasoState.MESSCRASH;
						game.gamestate = GameState.DEATH;
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
				}else if (posicion.x <= 0 || posicion.x >= 1024 - dimensiones.width ){
					velocidad.x = velocidad.x * -1;
				}else if (posicion.y < (game.trampolin.dimensiones.height/2 + game.trampolin.posicion.y) && (velocidad.y < vellimit) ){
					if ( game.trampolin.view == TrampolinState.LEFT){
						if ( posicion.x + dimensiones.width > game.trampolin.posicion.x && posicion.x + dimensiones.width/2 < game.trampolin.posicion.x + (game.trampolin.dimensiones.width /2 )){
							game.flip();
							game.scoreboard+=1;
						}
					}else{
						if ( posicion.x  < game.trampolin.posicion.x + game.trampolin.dimensiones.width && (posicion.x + (dimensiones.width/2)) > (game.trampolin.posicion.x + (game.trampolin.dimensiones.width /2 ))){
							game.flip();
							game.scoreboard+=1;
						}
					}
				}
				
			}
		}
		if ( state == PayasoState.MESSCRASH){
			time+=delta;
			if (time > 2.5){
				state=PayasoState.FLYING;
				game.gamestate = GameState.RUNNING;
				velocidad.y = 500;
				velocidad.x = 0;
				posicion.y = 400;
				posicion.x = 400;
			    game.trampolin.posicion.x = 410;
			    game.trampolin.velocidad.y = 0;
				game.trampolin.velocidad.x = 0;
				pc.posicion.y = 50;
				pc.state = pc.oldstate;
				game.trampolin.aceleracion.y = 0;
				game.trampolin.posicion.y = 40;
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
				state=PayasoState.FLYING;
				game.gamestate = GameState.RUNNING;
				velocidad.y = 500;
				velocidad.x = 0;
				posicion.y = 400;
				posicion.x = 400;
				 game.trampolin.posicion.x = 410;
				 Musica.setVolume(0.5f);
			}
		}
	}
}
