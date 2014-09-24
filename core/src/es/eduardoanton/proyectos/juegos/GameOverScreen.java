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
	private Texture fondo,corona;
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
		iproc = new InputProcesadorMain(cam,game);
		marcador =game.asset.get("fuenteBerlinSansFBDemi.fnt", BitmapFont.class);
		fuente = game.asset.get("fuente.fnt", BitmapFont.class);
	}
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0.02f,0.22f ,0.22f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);	
		batch.begin();
		//batch.draw(fondo,0,0);
		fuente.draw(batch,"Puntos conseguidos: " + game.gamew.scoreboard,0,400);
		if (SeeSawCircus.gamew.isrecord){
			fuente.draw(batch,"NUEVO RECORD",50,200);
			batch.draw(corona,0,200 );
		}else{
			fuente.draw(batch,"Record a conseguir: " + game.gamew.record,0,200);
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
