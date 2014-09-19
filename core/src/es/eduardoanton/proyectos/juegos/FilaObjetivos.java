package es.eduardoanton.proyectos.juegos;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import es.eduardoanton.proyectos.juegos.GameWorld.GameState;

public class FilaObjetivos {
	public Vector2 posicion;
	public Vector2 velocidad;
	public int ID;
	public int elementos[];
	public int quedan;
	private final static float limitexI = -1024f;
	private final static float limitexD = 1024f;
	public final static long objetowidth = 75;
	public final static long objetoheight = 32;
	private GameWorld game;
	
	
	public FilaObjetivos(int id, float x, float y, float vel,GameWorld gam){
		this.ID = id;
		posicion = new Vector2(x,y);
		velocidad = new Vector2(MathUtils.random(200,400)*vel,0);
	
		//velocidad = new Vector2(0,0);
		elementos = new int[10];
		game = gam;
		generarFila();
	}
	
	public void update (float delta){
		posicion.add(velocidad.cpy().scl(delta));
		if (posicion.x < limitexI || posicion.x > limitexD){
			velocidad.x*=-1;
		}
		if (game.gamestate == GameState.RUNNING){
			Payaso p = game.getPayasoFlying();
			p.dimensiones.x = p.posicion.x;
			p.dimensiones.y = p.posicion.y;
			for (int i=0;i<10;i++){
				if (elementos[i] != -1 ){
					Rectangle r = new Rectangle(posicion.x + (20 + objetowidth)*i,posicion.y,objetowidth,objetoheight);
					if (p.dimensiones.overlaps(r)){
						game.scoreboard+=10;
						quedan--;
						elementos[i]= -1;
						if ( (posicion.y   < p.posicion.y ) && p.velocidad.y < 0){
							p.velocidad.y*=-1;
							//p.velocidad.y+=20;
						}
						if ( (posicion.y   > p.posicion.y ) && p.velocidad.y > 0){
							p.velocidad.y=-10;
							//p.velocidad.y+=20;
						}
						
						game.clanS.play();
					}		
				}
			}
			if (this.quedan == 0){
				Gdx.app.log("CIRCUS", "CERO" + ID + " :" + quedan);
				game.succesS.play();
				game.scoreboard+=100;
				generarFila();
			}
		}
	}
	
	public void generarFila(){
		for (int i=0;i<10;i++){
			this.elementos[i]=MathUtils.random(0,3);
		}
		this.quedan = 10;
	}
}
