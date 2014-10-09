package es.eduardoanton.proyectos.juegos;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class CreditsScreen implements Screen {
	private SeeSawCircus game;
	private SpriteBatch batch;
	private OrthographicCamera cam;
	private Texture circo,idea,art,music,musiclogo1,musiclogo2;
	public static Texture botonhome;
	public BitmapFont marcador, informe;
	public InputProcessor iproc;
	
	CreditsScreen (SeeSawCircus game){
		this.game = game;
		cam = new OrthographicCamera();
		cam.setToOrtho(false, SeeSawCircus.screenwidth,SeeSawCircus.screenheight);
		cam.update();
		batch = new SpriteBatch();
		marcador =game.asset.get("fuenteBerlinSansFBDemi.fnt", BitmapFont.class);
		informe =game.asset.get("fuente.fnt", BitmapFont.class);
		batch.setProjectionMatrix(cam.combined);
		circo = SeeSawCircus.asset.get("circo.png");
		idea = SeeSawCircus.asset.get("creditsidea.png");
		art = SeeSawCircus.asset.get("creditsart.png");
		music = SeeSawCircus.asset.get("creditsmusic.png");
		musiclogo1 = SeeSawCircus.asset.get("logomusica1.png");
		musiclogo2 = SeeSawCircus.asset.get("logomusica2.png");
		botonhome = SeeSawCircus.asset.get("botonhomepe.png");
		iproc = new InputProcesadorCredits(cam,game,this);
	}
	@Override
	public void render(float delta) {
		update(delta);
		Gdx.gl.glClearColor(0.02f,0.22f ,0.22f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);	
		batch.begin();
		batch.draw(botonhome,930, cam.position.y + 200);
		batch.draw(idea,512 - idea.getWidth()/2,0);
		marcador.setScale(0.5f);
		marcador.draw(batch,"EDUARDO ANTON SANTA-MARIA",512 - (marcador.getBounds("EDUARDO ANTON SANTA-MARIA").width/2), -70);
		informe.setScale(0.7f);
		informe.setColor(Color.GRAY);
		informe.draw(batch,"Based on Atari 2600 videogame Seesaw Circus",512 - (informe.getBounds("Based on Atari 2600 videogame Seesaw Circus").width/2), -120);
		batch.draw(art,512 - art.getWidth()/2,-400);
		marcador.draw(batch,"EDUARDO ANTON SANTA-MARIA",512 - (marcador.getBounds("EDUARDO ANTON SANTA-MARIA").width/2), -470);
		marcador.draw(batch,"SONIA ANTON RIANO",512 - (marcador.getBounds("SONIA ANTON RIANO").width/2), -520);
		marcador.draw(batch,"LUCIA ANTON RIANO",512 - (marcador.getBounds("LUCIA ANTON RIANO").width/2), -570);
		batch.draw(music,512 - art.getWidth()/2,-880);
		marcador.draw(batch,"KEVIN MCLEAOD",512 - (marcador.getBounds("KEVIN MCLEAOD").width/2), -950);
		informe.draw(batch,"http://incompetech.com",512 - (informe.getBounds("http://incompetech.com").width/2), -1010);
		batch.draw(musiclogo1, 512 - musiclogo1.getWidth()/2, -1100);
		informe.setColor(Color.YELLOW);
		informe.draw(batch,"Monkeys Spinning Monkeys",512 - (informe.getBounds("Monkeys Spinning Monkeys").width/2), -1060 - 60);
		informe.draw(batch,"Plucky Daisy",512 - (informe.getBounds("Plucky Daisy").width/2), -1100 - 60);
		informe.draw(batch,"Look Busy",512 - (informe.getBounds("Look Busy").width/2), -1140 - 60);
		informe.draw(batch,"Five Card Shuffle",512 - (informe.getBounds("Five Card Shuffle").width/2), -1180 - 60);
		informe.draw(batch,"Friendly Day",512 - (informe.getBounds("Friendly Day").width/2), -1220 - 60);
		informe.draw(batch,"Hyperfun",512 - (informe.getBounds("Hyperfun").width/2), -1260 - 60);

		marcador.draw(batch,"MATTHEW PABLO",512 - (marcador.getBounds("MATTHEW PABLO").width/2), -1400);
		informe.setColor(Color.GRAY);
		informe.draw(batch,"http://www.matthewpablo.com",512 - (informe.getBounds("http://www.matthewpablo.com").width/2), -1460);
		batch.draw(musiclogo2, 512 - musiclogo2.getWidth()/2, -1620);
		informe.setColor(Color.YELLOW);
		informe.draw(batch,"Title Screen Music - Circus Dilemma",512 - (informe.getBounds("Title Screen Music - Circus Dilemma").width/2), -1650);
		
		batch.end();
		
	}

	public void update (float delta){
		cam.position.y-= delta*80;
		cam.update();
		batch.setProjectionMatrix(cam.combined);
	}
	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		botonhome =game.asset.get("botonhomepe.png");
		cam.position.y = 300;
		cam.update();
		batch.setProjectionMatrix(cam.combined);
		Gdx.input.setInputProcessor(iproc);
		Musica.playRandom();
	}

	@Override
	public void hide() {
		Musica.stop();
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
