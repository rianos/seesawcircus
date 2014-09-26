package es.eduardoanton.proyectos.juegos;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameOverScreen implements Screen{
	private SeeSawCircus game;
	private SpriteBatch batch;
	private OrthographicCamera cam;
	private Texture fondo,corona,botoninicio,salida;
	private Music musica;
	public static Sound clic;
	private InputProcessor iproc;
	public BitmapFont marcador,fuente;
	
	public GameOverScreen (SeeSawCircus game){
		this.game = game;
		cam = new OrthographicCamera();
		cam.setToOrtho(false, 1024,600);
		cam.update();
		batch = new SpriteBatch();
		batch.setProjectionMatrix(cam.combined);	
		fondo = game.asset.get("fondoinicio.png", Texture.class );
		corona = game.asset.get("record.png");
		musica = SeeSawCircus.asset.get("Circus Dilemma.ogg");
		clic = SeeSawCircus.asset.get("app_game_interactive_alert_tone_015.mp3");
		iproc = new InputProcesadorGameOver(cam,game);
		marcador =game.asset.get("fuenteBerlinSansFBDemi.fnt", BitmapFont.class);
		fuente = game.asset.get("fuente.fnt", BitmapFont.class);
		salida = SeeSawCircus.asset.get("salir.png");
		botoninicio = SeeSawCircus.asset.get("botoninicio.png");
	}
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0.02f,0.22f ,0.22f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);	
		batch.begin();
		//batch.draw(fondo,0,0);
		batch.draw(salida,930,500);
		batch.draw(botoninicio, 430, 40);
		fuente.draw(batch,"Saltos: " + game.gamew.flipsC + " x 1 = " + game.gamew.flipsC,0,600);
		fuente.draw(batch,"Caramelos: " + game.gamew.caramelosC + " x 10 = " + game.gamew.caramelosC*10f,0,550);
		fuente.draw(batch,"Globlos: " + game.gamew.globosC + " x 20 = " + game.gamew.globosC*20f,0,500);
		fuente.draw(batch,"Animales: " + game.gamew.animalesC + " x 25 = " + game.gamew.animalesC*25f,0,450);
		fuente.draw(batch,"Monedas plata: " + game.gamew.mplataC + " x 30 = " + game.gamew.mplataC*30f,0,400);
		fuente.draw(batch,"Monedas oro: " + game.gamew.moroC + " x 40 = " + game.gamew.moroC*40f,0,350);
		fuente.draw(batch,"Billetes: " + game.gamew.billeteC + " x 50 = " + game.gamew.billeteC*50f,0,300);
		fuente.draw(batch,"Filas Completas 200 x: " + game.gamew.fcompletaC + " = " + game.gamew.fcompletaC*200f,0,250);
		fuente.draw(batch,"Puntos totales: " + game.gamew.scoreboard, 0,200);
		if (SeeSawCircus.gamew.isrecord){
			fuente.draw(batch,"NUEVO RECORD",100,100);
			batch.draw(corona,0,50 );
		}else{
			fuente.draw(batch,"Record a conseguir: " + game.gamew.record,0,50);
		}
		batch.end();
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		if (SeeSawCircus.gamew.isrecord){
			SeeSawCircus.prefs.putLong("record", SeeSawCircus.gamew.record);
			SeeSawCircus.prefs.flush();
		}
		Gdx.input.setInputProcessor(iproc);
		
	}

	@Override
	public void hide() {
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
