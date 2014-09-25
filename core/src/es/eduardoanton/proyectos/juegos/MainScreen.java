package es.eduardoanton.proyectos.juegos;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

public class MainScreen implements Screen{
	private SeeSawCircus game;
	private SpriteBatch batch;
	private OrthographicCamera cam;
	private Texture fondo;
	private Music musica;
	public static Sound clic;
	private InputProcessor iproc;
	private Stage stage;
	private Actor fondoincio,nube1,nube2,circo,letrero,letreroe;
	
	public MainScreen (SeeSawCircus game){
		this.game = game;
		cam = new OrthographicCamera();
		cam.setToOrtho(false, 1024,600);
		cam.update();
		//batch = new SpriteBatch();
		//batch.setProjectionMatrix(cam.combined);	
		//fondo = game.asset.get("fondoinicio.png", Texture.class );
		//fondo = game.asset.get("fondomain.png", Texture.class );
		musica = SeeSawCircus.asset.get("Circus Dilemma.ogg");
		clic = SeeSawCircus.asset.get("app_game_interactive_alert_tone_015.mp3");
		iproc = new InputProcesadorMain(cam,game);
	    stage = new Stage(new ScreenViewport());
	    fondoincio = new ActorFondoInicio();
	    nube1 = new ActorNube(0f,400f);
	    nube2 = new ActorNube(1024f,300f);
	    nube1.addAction(forever(sequence(
	              moveTo(1024, 400, 10),
	              moveTo(0, 400, 10)
	              )));
	    nube2.addAction(forever(sequence(
	              moveTo(0, 300, 10),
	              moveTo(1024, 300, 8)
	              ))); 
		circo = new ActorCirco(220f,600f);
		circo.addAction(sequence(moveTo(220f,80f,0.5f),moveTo(220f,100f,0.2f),moveTo(220f,80f,0.1f)
				));
	    //fondoincio.addAction(Actions.moveTo(600, 60, 3f));
		letrero = new ActorLetrero(50,900);
		letrero.addAction(sequence(moveTo(50,420f,2f)
				));
		letreroe = new ActorLetreroe(-500,450);
		letreroe.addAction(sequence(delay(0.5f),moveTo(250,400f,1.5f)
				));
	    stage.addActor(fondoincio);
	    stage.addActor(nube1);
	    stage.addActor(nube2);
	    stage.addActor(circo);
	    stage.addActor(letrero);
	    stage.addActor(letreroe);
	}
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0.02f,0.22f ,0.22f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);	
		stage.act(delta);
		stage.draw();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		this.musica.setVolume(1f);
		this.musica.setLooping(true);
		this.musica.play();
		Gdx.input.setInputProcessor(iproc);
		
	}

	@Override
	public void hide() {
		this.musica.stop();
		
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
