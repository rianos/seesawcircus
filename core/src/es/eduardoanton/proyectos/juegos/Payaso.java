package es.eduardoanton.proyectos.juegos;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import es.eduardoanton.proyectos.juegos.Trampolin.TrampolinState;

public class Payaso {
	public Vector2 posicion;
	public Vector2 velocidad;
	public Vector2 aceleracion;
	public Rectangle dimensiones;
	private GameWorld game;
	public enum PayasoState { STANDBYL, STANDBYR, FLYING, JUMPING,MESS };
	public PayasoState state;
	public final float vellimit = -600f;
	private float time= 0f;
	
	
	Payaso (int x, int y, int vx, int vy, PayasoState s, GameWorld game){
		posicion = new Vector2(x,y);
		velocidad = new Vector2(vx,vy);
		//dimensiones = new Rectangle(0,0,45,62);
		//dimensiones = new Rectangle(0,0,88,96);
		dimensiones = new Rectangle(0,0,64,59);
		aceleracion = new Vector2(0,GameWorld.aceleracion);
		state = s;
		this.game = game;
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
				if (posicion.x <= 0 || posicion.x >= 1024 - dimensiones.width ){
					velocidad.x = velocidad.x * -1;
				}
				if (posicion.y < (game.trampolin.dimensiones.height/2 + game.trampolin.posicion.y) && (velocidad.y < vellimit) ){
					if ( game.trampolin.view == TrampolinState.LEFT){
						if ( posicion.x + dimensiones.width > game.trampolin.posicion.x && posicion.x + dimensiones.width/2 < game.trampolin.posicion.x + (game.trampolin.dimensiones.width /2 )){
							game.flip();
						}
					}else{
						if ( posicion.x  < game.trampolin.posicion.x + game.trampolin.dimensiones.width && (posicion.x + (dimensiones.width/2)) > (game.trampolin.posicion.x + (game.trampolin.dimensiones.width /2 ))){
							game.flip();
						}
					}
				}
				if (posicion.y < 20 ){
					state = PayasoState.MESS;
					time = delta;
					posicion.y = -100;
					game.crash.play();
				}
			}
		}
		if ( state == PayasoState.MESS){
			time+=delta;
			if (time > 2){
				state=PayasoState.FLYING;
				velocidad.y = 500;
				velocidad.x = 0;
				posicion.y = 400;
				posicion.x = 400;
			}
		}
	}
}
