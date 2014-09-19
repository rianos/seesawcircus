package es.eduardoanton.proyectos.juegos;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Trampolin {
	public Vector2 posicion;
	public Vector2 velocidad,aceleracion;
	public Rectangle dimensiones;
	public enum TrampolinState { LEFT, RIGHT};
	public TrampolinState view;
	
	Trampolin (){
		posicion = new Vector2(410,40);
		velocidad = new Vector2(0,0);
		aceleracion = new Vector2(0,0);
		dimensiones = new Rectangle(0,0,170,42);
		view = TrampolinState.RIGHT;
	}
	
	public void setVelocity(int vel){
		velocidad.x= vel;
	}
	
	public void update(float delta ){
		velocidad.add(aceleracion.cpy().scl(delta));
		posicion.add(velocidad.cpy().scl(delta));
		if (posicion.y < 10){
			posicion.y = 10;
			velocidad.x = 0;
		}
		if (posicion.x < 0) {
			posicion.x = 0;
			velocidad.x = 0;
		}
		if (posicion.x > 1024 - dimensiones.width ){
			posicion.x = 1024 - dimensiones.width;
			velocidad.x = 0;
		}
	}
	
	public void flip(){
		if ( view == TrampolinState.LEFT){
			view = TrampolinState.RIGHT;
		}else{
			view = TrampolinState.LEFT;
		}
	}
}
