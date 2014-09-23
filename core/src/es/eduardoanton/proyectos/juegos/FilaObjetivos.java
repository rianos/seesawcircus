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
	//private final static float limitexI = -1024f;
	//private final static float limitexD = 1024f;
	private final static float limitexI = -SeeSawCircus.screenwidth /3;
	private final static float limitexD = SeeSawCircus.screenwidth /3;

	public final static long objetowidth = 75;
	public final static long objetoheight = 32;
	public float puntos[] = {10f,10f,10f,10f,20f,20f,20f,20f,25f,25f,25f,25f,25f,25f,25f,30f,30f,40f,40f,50f};
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
							if ( (posicion.y   < p.posicion.y ) && p.velocidad.y < 0){
								p.velocidad.y*=-1;
								//p.velocidad.y+=20;
							}
							if ( (posicion.y   > p.posicion.y ) && p.velocidad.y > 0){
								p.velocidad.y=-10;
							 //p.velocidad.y+=20;
							}
							if ( elementos[i] >= 15 && elementos[i] <= 18){
								game.coinS.play();
							}else if ( elementos[i] == 19){
								game.billeteS.play();
							}else if ( elementos[i] >=4 && elementos[i] <= 7){
								game.ballonS.play();
							}else if (elementos[i] == 8){
								game.lionroarS.play();
							}else if (elementos[i] == 10){
								game.bearS.play();
							}else if (elementos[i] == 9){
								game.horseS.play();
							}else if (elementos[i] == 11){
								game.elephantS.play();
							}else if (elementos[i] == 14){
								game.dogS.play();
							}else if (elementos[i] == 12){
								game.monoS.play();
							}else if (elementos[i] == 13){
								game.focaS.play();
							}else{
								game.clanS.play();
							}
							
							elementos[i]= -1;
						}
					}
				}
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
				this.elementos[i]=MathUtils.random(8,19);
			}
		}else if ( ID == 1){
			for (int i=0;i<10;i++){
				this.elementos[i]=MathUtils.random(0,13);
			}
		}else if ( ID == 2){
			for (int i=0;i<10;i++){
				this.elementos[i]=MathUtils.random(6,18);
			}
		}else{
			for (int i=0;i<10;i++){
				this.elementos[i]=MathUtils.random(0,5);
			}
		}
		this.quedan = 10;
	}
}
