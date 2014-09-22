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
	public static int teninarow = 0;
	public boolean bonus = false;
	public  float time = 0f;
	//private final static float limitexI = -1024f;
	//private final static float limitexD = 1024f;
	private final static float limitexI = -SeeSawCircus.screenwidth /3;
	private final static float limitexD = SeeSawCircus.screenwidth /3;

	public final static long objetowidth = 75;
	public final static long objetoheight = 32;
	public float puntos[] = {10f,10f,10f,10f,20f,20f,20f,20f,30f,30f,40f,40f,50f};
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
		if (bonus){
			time+=delta;
			if ( time > 4){
				bonus = false;
			}
		}
		if (posicion.x < limitexI){
			velocidad.x*=-1;
			posicion.x = limitexI + 2;
		}
		if ( posicion.x > limitexD){
			velocidad.x*=-1;
			posicion.x = limitexD - 2;
		}
		if (game.gamestate == GameState.RUNNING){
			Payaso p = game.getPayasoFlying();
			p.dimensiones.x = p.posicion.x;
			p.dimensiones.y = p.posicion.y;
			if ( p.posicion.y > 300){
				for (int i=0;i<10;i++){
					if (elementos[i] != -1 ){
						Rectangle r = new Rectangle(posicion.x + (20 + objetowidth)*i,posicion.y,objetowidth,objetoheight);
						if (p.dimensiones.overlaps(r)){
							game.scoreboard+=puntos[elementos[i]];
							quedan--;
							teninarow++;
							if ( (posicion.y   < p.posicion.y ) && p.velocidad.y < 0){
								p.velocidad.y*=-1;
								//p.velocidad.y+=20;
							}
							if ( (posicion.y   > p.posicion.y ) && p.velocidad.y > 0){
								p.velocidad.y=-10;
							 //p.velocidad.y+=20;
							}
							if ( elementos[i] >= 8 && elementos[i] <= 11){
								game.coinS.play();
							}else if ( elementos[i] == 12){
								game.billeteS.play();
							}else if ( elementos[i] >=4 && elementos[i] <= 7){
								game.ballonS.play();
							}else{
								game.clanS.play();
							}
							if (teninarow >= 3){
								teninarow = 0;
								game.scoreboard+=1000;
								game.welldoneS.play();
								bonus = true;
								time = 0;
							}
							elementos[i]= -1;
						}
					}
				}
			}else{
				teninarow = 0;
			}
			if (this.quedan == 0){
				game.succesS.play();
				game.scoreboard+=(100*(ID+1));
				generarFila();
			}
		}
	}
	
	public void generarFila(){
		if ( ID == 3){
			for (int i=0;i<10;i++){
				this.elementos[i]=MathUtils.random(4,12);
			}
		}else if ( ID == 1){
			for (int i=0;i<10;i++){
				this.elementos[i]=MathUtils.random(0,7);
			}
		}else if ( ID == 2){
			for (int i=0;i<10;i++){
				this.elementos[i]=MathUtils.random(0,9);
			}
		}else{
			for (int i=0;i<10;i++){
				this.elementos[i]=MathUtils.random(0,3);
			}
		}
		this.quedan = 10;
	}
}
