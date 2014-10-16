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
	private final static float limitexI = -SeeSawCircus.screenwidth /3;
	private final static float limitexD = SeeSawCircus.screenwidth /3;

	public final static long objetowidth = 75;
	public final static long objetoheight = 32;
	public float puntos[] = {10f,10f,10f,10f,20f,20f,20f,20f,25f,25f,25f,25f,25f,25f,25f,30f,30f,40f,40f,50f,0f,0f,0f,0f,0f,0f};
	private GameWorld game;

	public FilaObjetivos(int id, float x, float y, float vel,GameWorld gam){
		this.ID = id;
		posicion = new Vector2(x,y);
		velocidad = new Vector2(MathUtils.random(150,320)*vel,0);
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
							if ( elementos[i] == 22 || elementos[i] == 23 || elementos[i] == 24 || elementos[i] == 25){
								if ( p.velocidad.y < 0 && ((p.posicion.y > (r.y + (r.height/2))))){
									game.piedra3S.play();
									p.velocidad.y*= -0.9;
									//p.velocidad.y = Math.max(0,p.velocidad.y - 20);
									p.posicion.y = r.y + r.height;	
								}else if ( p.velocidad.y < 0 && ((p.posicion.y < (r.y + (r.height/2))))){ 						
									 if ( p.posicion.x  > r.x ){
										 if( this.velocidad.x > 0 && p.velocidad.x > 0){
											 p.velocidad.x = Math.max(p.velocidad.x,this.velocidad.x);
										 }else if ( this.velocidad.x > 0 && p.velocidad.x <= 0){
											 p.velocidad.x = this.velocidad.x;
										 }else if ( this.velocidad.x <= 0 && p.velocidad.x > 0){
											 p.velocidad.x= p.velocidad.x;
										 }else {
											 p.velocidad.x = 0;
										 }
										 p.posicion.x = r.x + r.width + 5;
										 p.velocidad.y = 250;
										 game.piedra3S.play();
									 }else{
										 if( this.velocidad.x > 0 && p.velocidad.x > 0){
											 p.velocidad.x =this.velocidad.x;
										 }else if ( this.velocidad.x > 0 && p.velocidad.x <= 0){
											 p.velocidad.x = 0;
										 }else if ( this.velocidad.x <= 0 && p.velocidad.x > 0){
											 p.velocidad.x = 0;
										 }else {
											 p.velocidad.x = Math.min(p.velocidad.x,this.velocidad.x);
										 }
										 p.posicion.x = r.x - p.dimensiones.width - 5;
										 p.velocidad.y = 250;
										 game.piedra3S.play();
									 }
								}else if (p.velocidad.y > 0 && ((p.posicion.y + p.dimensiones.height) < (r.y + (r.height/1)))){
									p.velocidad.y = -10;
									p.posicion.y = r.y - p.dimensiones.height;
									switch (elementos[i]){
										case 22: elementos[i] = 23;
												 game.piedra2S.play();
												 break;
										case 23: elementos[i] = 24;
												 game.piedra2S.play();
												 break;
										case 24: elementos[i] = 25;
												 game.piedra2S.play();
												 break;
										case 25: elementos[i] = -1;
												 game.piedra1S.play();
												 quedan--;
												 break;
									}
								}else if (  p.velocidad.y > 0 && ((p.posicion.y + p.dimensiones.height) > (r.y + (r.height/2)))){	 	
									 p.velocidad.y-=100;
									 if ( p.posicion.x  > r.x ){
										 if( this.velocidad.x > 0 && p.velocidad.x > 0){
											 p.velocidad.x = Math.max(p.velocidad.x,this.velocidad.x);
										 }else if ( this.velocidad.x > 0 && p.velocidad.x <= 0){
											 p.velocidad.x = this.velocidad.x;
										 }else if ( this.velocidad.x <= 0 && p.velocidad.x > 0){
											 p.velocidad.x= p.velocidad.x;
										 }else {
											 p.velocidad.x = 0;
										 }
										 p.posicion.x = r.x + r.width + 5;
										 p.velocidad.y = 0;
										 game.piedra3S.play();
									 }else{
										 if( this.velocidad.x > 0 && p.velocidad.x > 0){
											 p.velocidad.x =this.velocidad.x;
										 }else if ( this.velocidad.x > 0 && p.velocidad.x <= 0){
											 p.velocidad.x = 0;
										 }else if ( this.velocidad.x <= 0 && p.velocidad.x > 0){
											 p.velocidad.x = 0;
										 }else {
											 p.velocidad.x = Math.min(p.velocidad.x,this.velocidad.x);
										 }
										 p.posicion.x = r.x - p.dimensiones.width - 5;
										 p.velocidad.y = 0;
										 game.piedra3S.play();
									 }
								}
								return;
							}
							game.scoreboard+=puntos[elementos[i]];
							quedan--;
							if ( (posicion.y   < p.posicion.y ) && p.velocidad.y < 0){
								p.velocidad.y*=-1;							
							}
							if ( (posicion.y   > p.posicion.y ) && p.velocidad.y > 0){
								p.velocidad.y=-10;
							}
							if ( elementos[i] >= 15 && elementos[i] <= 16){
								game.coinS.play();
								game.mplataC+=1;
							}else if ( elementos[i] >= 17 && elementos[i] <= 18){
								game.coinS.play();
								game.moroC+=1;
							}else if ( elementos[i] == 19){
								game.billeteS.play();
								game.billeteC+=1;
							}else if ( elementos[i] >=4 && elementos[i] <= 7){
								game.ballonS.play();
								game.globosC+=1;
							}else if (elementos[i] == 8){
								game.lionroarS.play();
								game.animalesC+=1;
							}else if (elementos[i] == 10){
								game.bearS.play();
								game.animalesC+=1;
							}else if (elementos[i] == 9){
								game.horseS.play();
								game.animalesC+=1;
							}else if (elementos[i] == 11){
								game.elephantS.play();
								game.animalesC+=1;
							}else if (elementos[i] == 14){
								game.dogS.play();
								game.animalesC+=1;
							}else if (elementos[i] == 12){
								game.monoS.play();
								game.animalesC+=1;
							}else if (elementos[i] == 13){
								game.focaS.play();
								game.animalesC+=1;
							}else if (elementos[i] == 20){
								game.bonusS.play();
								game.timeregalo = delta;
								if ( game.modechildren){
									game.regalo = game.generapremio();
								}else{
									game.regalo = game.generapremiotres();
								}
								game.regalosC++;
								game.ispremio = true;
							}else if (elementos[i] == 21){
								game.bonusS.play();
								game.timeregalo = delta;
								game.regalo = game.generapremiodos();
								game.regalosC++;
								game.ispremio = true;
							}else{
								game.clanS.play();
								game.caramelosC+=1;
							}
							
							elementos[i]= -1;
						}
					}
				}
			}
			if (this.quedan == 0){
				game.succesS.play();
				game.scoreboard+=200;
				game.fcompletaC++;
				generarFila();
			}
		}
	}
	
	public void generarFila(){
		if ( ID == 3){
			for (int i=0;i<10;i++){
				this.elementos[i]=MathUtils.random(8,19);
			}
			this.elementos[MathUtils.random(0,9)] = 20;
		}else if ( ID == 1){
			for (int i=0;i<10;i++){
				this.elementos[i]=MathUtils.random(0,10);
			}
		}else if ( ID == 2){
			for (int i=0;i<10;i++){
				this.elementos[i]=MathUtils.random(0,18);
			}
			generaPiedras();
		}else{
			for (int i=0;i<10;i++){
				this.elementos[i]=MathUtils.random(0,5);
			}
			generaPiedras();
		}
		this.quedan = 10;
	}
	
	public void generaPiedras(){
		long tmp=0;
		int j;
		if ( this.ID == 0){
			tmp = (long) (game.scoreboard / 8000);		
		}else if (this.ID  == 2){
			tmp = (long) (game.scoreboard / 3500);
		}
		tmp = Math.min(tmp, 10);
		for ( int i=1; i <= tmp;i++){
			do{
				j = MathUtils.random(0,9);
			}while (elementos[j] == 22);
			elementos[j] = 22;
		}
	}
}
