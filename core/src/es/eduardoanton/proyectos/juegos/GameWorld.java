package es.eduardoanton.proyectos.juegos;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import es.eduardoanton.proyectos.juegos.Payaso.PayasoState;
import es.eduardoanton.proyectos.juegos.Trampolin.TrampolinState;

public class GameWorld {
	public Trampolin trampolin;
	public Payaso payaso1;
	public Payaso payaso2;
	public float redondo = 0;
	public final static int aceleracion = -1380;
	public final static int maxvel = 1250;
	public final static float redimen = 4.5f;
	public final static float redimen2 = 12;
	public Sound boing,crash; 

	GameWorld(){
		trampolin = new Trampolin();
		payaso1 = new Payaso(500,50,0,0,PayasoState.STANDBYL, this);
		payaso2 = new Payaso(500,500,-90,0,PayasoState.FLYING, this);
		boing = Gdx.audio.newSound(Gdx.files.internal("jump.ogg"));
		crash = Gdx.audio.newSound(Gdx.files.internal("crash.ogg"));

	}
	
	
	public void update(float delta){
		trampolin.update(delta);
		payaso1.update(delta);
		payaso2.update(delta);
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
		boing.play();
		
	}
}
