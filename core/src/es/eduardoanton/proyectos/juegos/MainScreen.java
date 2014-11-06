package es.eduardoanton.proyectos.juegos;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

public class MainScreen implements Screen{
	public SeeSawCircus game;
	private SpriteBatch batch;
	private OrthographicCamera cam;
	public Music musica;
	public static Sound clic;
	private InputProcessor iproc;
	private Stage stage;
	private Actor fondoincio,nube1,nube2,circo,letrero,letreroe;
	private Actor payaso1,payaso2,globos1,globos2,gato;
	public  ActorGenerico botoninicio,salir,botonninos,botoncreditos,botonmusica,botoninfo,botongs;
	public BitmapFont marcador;
	private Texture corona;
	
	public MainScreen (SeeSawCircus game){
		this.game = game;
		cam = new OrthographicCamera();
		cam.setToOrtho(false, SeeSawCircus.screenwidth,SeeSawCircus.screenheight);
		cam.update();
		batch = new SpriteBatch();
		batch.setProjectionMatrix(cam.combined);
		corona = SeeSawCircus.asset.get("recordp.png");
		musica = SeeSawCircus.asset.get("Circus Dilemma.ogg");
		clic = SeeSawCircus.asset.get("app_game_interactive_alert_tone_015.mp3");
		iproc = new InputProcesadorMain(cam,this);
		marcador =SeeSawCircus.asset.get("fuente.fnt", BitmapFont.class);
		marcador.setColor(Color.GRAY);
	    stage = new Stage(new StretchViewport(SeeSawCircus.screenwidth, SeeSawCircus.screenheight));
	    if (game.gamew.modemusic){
	    	botonmusica = new ActorGenerico(17f,505f,"botonmusicaon.png");
	    }else{
	    	botonmusica = new ActorGenerico(17f,505f,"botonmusicaoff.png");
	    }
	    if (game.gamew.modechildren){
	    	botonninos = new ActorGenerico(87f,245f,"botonninoson.png");
	    }else{
	    	botonninos = new ActorGenerico(87f,245f,"botonninosoff.png");
	    }
	    botongs = new ActorGenerico(17,12,"botongsoff.png");
	    botongs.addAction(sequence(
				visible(false),delay(11),visible(true)
				));
	    botonninos.addAction(sequence(
				visible(false),delay(11),visible(true)
				));
	    botoncreditos = new ActorGenerico(817f,245f,"botoncreditos.png");
	    botoncreditos.addAction(sequence(
				visible(false),delay(11),visible(true)
				));
	    fondoincio = new ActorGenerico(0f,0f,"fondomain.png");
	    nube1 = new ActorGenerico(0f,400f,"nube.png");
	    nube2 = new ActorGenerico(1024f,300f,"nube.png");
	    nube1.addAction(forever(sequence(
	              moveTo(1024, 400, 10),
	              moveTo(0, 400, 10)
	              )));
	    nube2.addAction(forever(sequence(
	              moveTo(0, 300, 8),
	              moveTo(1024, 300, 8)
	              ))); 
		circo = new ActorGenerico(220f,600f,"circo.png");
		circo.addAction(sequence(delay(4f),moveTo(220f,80f,0.5f),
				run(new Runnable(){
					@Override
					public void run() {
						Sound s = SeeSawCircus.asset.get("box_trash_impact_03.mp3");
						s.play();
					}}),
				moveTo(220f,100f,0.2f),moveTo(220f,80f,0.1f),
				run(new Runnable(){
					@Override
					public void run() {
						Sound s = SeeSawCircus.asset.get("box_trash_impact_03.mp3");
						s.play();
					}})
				));
		letrero = new ActorGenerico(50,900,"letrero.png");
		letrero.addAction(sequence(delay(8),moveTo(50,420f,2f)
				));
		letreroe = new ActorGenerico(-500,450,"letreroe.png");
		letreroe.addAction(sequence(delay(8.5f),moveTo(250,400f,1.5f)
				));
		payaso1 = new ActorGenerico(90,600,"payasoinicio1.png");
		payaso1.addAction(sequence(delay(4f),moveTo(90,30,3f),forever(sequence(
				  moveTo(100,25,0.2f),moveTo(110, 30, 0.2f),moveTo(100,25,0.1f),
	              moveTo(90, 30, 0.2f)
	              )))
				);
		payaso2 = new ActorGenerico(700,700,"payasoinicio2.png");
		payaso2.addAction(sequence(delay(4f),moveTo(700,30,3f),forever(sequence(
				  moveTo(690,25,0.2f),moveTo(680, 30, 0.2f),moveTo(690,25,0.1f),
	              moveTo(700, 30, 0.2f)
	              )))
				);
		globos1 = new ActorGenerico(170,730,"globosinicio.png");
		globos2 = new ActorGenerico(800,730,"globosinicio.png");
		globos1.addAction(sequence(delay(4f),moveTo(170,160,3f),moveTo(170,730,2f)));
		globos2.addAction(sequence(delay(4f),moveTo(800,160,3f),moveTo(800,730,2f)));
		gato = new ActorGenerico(512,50,"gato.png");
		gato.addAction(sequence(visible(false),delay(6),visible(true),
				delay(2),moveTo(512,240,0.3f),moveTo(700,240,1.5f),moveTo(500,360,0.1f),
				run(new Runnable(){
					@Override
					public void run() {
						Sound s = SeeSawCircus.asset.get("cat_meow_human_voice_3.mp3");
						s.play();
					}})
				));
		botoninicio = new ActorGenerico(430,40,"botoninicio.png");
		botoninicio.addAction(sequence(
				visible(false),delay(9),visible(true)
				));
		salir = new ActorGenerico(930,500,"salir.png");
		salir.addAction(sequence(
				visible(false),delay(9),visible(true)
				));
		botoninfo = new ActorGenerico(930,3, "botoninfo.png");
		botoninfo.addAction(sequence(
				visible(false),delay(11),visible(true)
				));
	    stage.addActor(fondoincio);
	    stage.addActor(nube1);
	    stage.addActor(nube2);
	    stage.addActor(circo);
	    stage.addActor(letrero);
	    stage.addActor(letreroe);
	    stage.addActor(payaso1);
	    stage.addActor(payaso2);
	    stage.addActor(globos1);
	    stage.addActor(globos2);
	    stage.addActor(gato);
	    //stage.addActor(botonmusica);
	    stage.addActor(botonninos);
	    stage.addActor(botoninicio);
	    stage.addActor(botoncreditos);
	    stage.addActor(botoninfo);
	    stage.addActor(botongs);
	    stage.addActor(salir);
	
	}
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0.02f,0.22f ,0.22f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);	
		stage.act(delta);
		stage.draw();
		if (gato.isVisible()){
			batch.begin();
			marcador.setColor(Color.GRAY);
			if ( game.gamew.modechildren){
				marcador.draw(batch, "" + SeeSawCircus.prefs.getLong("recordninos",0), 532 - (marcador.getBounds("" + SeeSawCircus.prefs.getLong("recordninos",0)).width/2),45);
				batch.draw(corona, 532 - (marcador.getBounds("" + SeeSawCircus.prefs.getLong("recordninos",0)).width/2) - 50,5);
			}else{
				marcador.draw(batch, "" + SeeSawCircus.prefs.getLong("record",0), 532 - (marcador.getBounds("" + SeeSawCircus.prefs.getLong("record",0)).width/2),45);
				batch.draw(corona, 532 - (marcador.getBounds("" + SeeSawCircus.prefs.getLong("record",0)).width/2) - 50,5);
			}
			batch.end();
		}
		if (SeeSawCircus.igs.estaLoginGS()){
			botongs.setTexture("botongson.png");
		}else{
			botongs.setTexture("botongsoff.png");
		}
	}


	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		botoninicio.setTexture("botoninicio.png");
		botoncreditos.setTexture("botoncreditos.png");
		botoninfo.setTexture("botoninfo.png");
		if ( GameWorld.modemusic){
			this.musica.setVolume(0.2f);
			this.musica.setLooping(true);
			this.musica.play();
		}
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
		stage.dispose();
		batch.dispose();
	}

}
