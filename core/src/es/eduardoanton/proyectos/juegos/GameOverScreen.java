package es.eduardoanton.proyectos.juegos;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class GameOverScreen implements Screen{
	private SpriteBatch batch;
	private OrthographicCamera cam;
	private Texture corona;
	private Texture caramelo,globo,animal,moneda,monedao,billete,regalo,trampolin,filascompletas,finalscore,corona2;
	private Vector2 caramelop,globop,animalp,monedap,monedaop,billetep,regalop,trampolinp,filacompletap;
	public static Sound clic;
	private InputProcessor iproc;
	public BitmapFont marcador,fuente;
	public static Texture botonhome,botonreload,botonhomep,botonreloadp,salida,salidap,botonleader;
	
	public GameOverScreen (SeeSawCircus game){
		cam = new OrthographicCamera();
		cam.setToOrtho(false, 1024,600);
		cam.update();
		batch = new SpriteBatch();
		batch.setProjectionMatrix(cam.combined);	
		trampolin = SeeSawCircus.asset.get("redondo.png");
		caramelo = SeeSawCircus.asset.get("carameloz.png");
		globo = SeeSawCircus.asset.get("globor.png");
		animal = SeeSawCircus.asset.get("leon.png");
		moneda = SeeSawCircus.asset.get("monedasoniap.png");
		monedao = SeeSawCircus.asset.get("monedalucia.png");
		billete = SeeSawCircus.asset.get("billeteedu.png");
		filascompletas = SeeSawCircus.asset.get("filascompletas.png");
		regalo = SeeSawCircus.asset.get("regalo.png");
		corona = SeeSawCircus.asset.get("record.png");
		finalscore = SeeSawCircus.asset.get("finalscore.png");
		clic = SeeSawCircus.asset.get("app_game_interactive_alert_tone_015.mp3");
		iproc = new InputProcesadorGameOver(cam,game);
		marcador =SeeSawCircus.asset.get("fuenteBerlinSansFBDemi.fnt", BitmapFont.class);
		fuente = SeeSawCircus.asset.get("fuente.fnt", BitmapFont.class);
		salida = SeeSawCircus.asset.get("salir.png");
		salidap = SeeSawCircus.asset.get("salirp.png");
		botonhome = SeeSawCircus.asset.get("botonhome.png");
		botonreload = SeeSawCircus.asset.get("botonreload.png");
		botonhomep = SeeSawCircus.asset.get("botonhomep.png");
		botonreloadp = SeeSawCircus.asset.get("botonreloadp.png");
		botonleader = SeeSawCircus.asset.get("botonleader.png");
		corona2 = SeeSawCircus.asset.get("recordp.png");
		reset();
	}
	
	public void reset(){
		GameOverScreen.botonreload = SeeSawCircus.asset.get("botonreload.png");
		GameOverScreen.botonhome = SeeSawCircus.asset.get("botonhome.png");
		GameOverScreen.salida = SeeSawCircus.asset.get("salir.png");
		trampolinp = new Vector2(SeeSawCircus.screenwidth,530);
		caramelop = new Vector2(SeeSawCircus.screenwidth + 300,470);
		globop = new Vector2(SeeSawCircus.screenwidth + 600,410);
		animalp = new Vector2(SeeSawCircus.screenwidth + 900,350);
		monedap = new Vector2(SeeSawCircus.screenwidth + 1200,290);
		monedaop = new Vector2(SeeSawCircus.screenwidth + 1500,230);
		billetep = new Vector2(SeeSawCircus.screenwidth + 1800,170);
		regalop = new Vector2(SeeSawCircus.screenwidth + 2100,110);
		filacompletap = new Vector2(SeeSawCircus.screenwidth + 2400,50);
	}
	
	public void update(float delta){
		updateitem(trampolinp,delta);
		updateitem(caramelop,delta);
		updateitem(globop,delta);
		updateitem(animalp,delta);
		updateitem(monedap,delta);
		updateitem(monedaop,delta);
		updateitem(billetep,delta);
		updateitem(regalop,delta);
		updateitem(filacompletap,delta);
	}
	
	public void updateitem(Vector2 pos, float delta){
		pos.x -=900*delta;
		if (pos.x < 150){
			pos.x = 150;
		}
	}
	@Override
	public void render(float delta) {
		update(delta);
		Gdx.gl.glClearColor(5f/255f,56f/255f ,84f/255f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		fuente.setColor(Color.BLACK);
		batch.begin();
		batch.draw(finalscore,0,0);
		batch.draw(salida,930,500);
		batch.draw(botonreload, 550, 40);
		batch.draw(botonhome, 800, 40);
		batch.draw(botonleader, 710,230);
		batch.draw(trampolin,trampolinp.x,trampolinp.y);
		fuente.draw(batch,"" + SeeSawCircus.gamew.flipsC ,trampolinp.x + billete.getWidth() + 20, trampolinp.y + 45);
		batch.draw(caramelo,caramelop.x,caramelop.y);
		fuente.draw(batch,"" + SeeSawCircus.gamew.caramelosC ,caramelop.x + caramelo.getWidth() + 20, caramelop.y + 45);
		batch.draw(globo,globop.x,globop.y);
		fuente.draw(batch,"" + SeeSawCircus.gamew.globosC ,globop.x + billete.getWidth() + 20, globop.y + 45);
		batch.draw(animal,animalp.x,animalp.y);
		fuente.draw(batch,"" + SeeSawCircus.gamew.animalesC ,animalp.x + caramelo.getWidth() + 20, animalp.y + 45);
		batch.draw(moneda,monedap.x,monedap.y);
		fuente.draw(batch,"" + SeeSawCircus.gamew.mplataC ,monedap.x + billete.getWidth() + 20, monedap.y + 45);
		batch.draw(monedao,monedaop.x,monedaop.y);
		fuente.draw(batch,"" + SeeSawCircus.gamew.moroC ,monedaop.x + caramelo.getWidth() + 20, monedaop.y + 45);
		batch.draw(billete,billetep.x,billetep.y);
		fuente.draw(batch,"" + SeeSawCircus.gamew.billeteC ,billetep.x + billete.getWidth() + 20, billetep.y + 45);
		batch.draw(regalo,regalop.x,regalop.y);
		fuente.draw(batch,"" + SeeSawCircus.gamew.regalosC ,regalop.x + caramelo.getWidth() + 20, regalop.y + 45);
		batch.draw(filascompletas,filacompletap.x,filacompletap.y);
		fuente.draw(batch,"" + SeeSawCircus.gamew.fcompletaC ,filacompletap.x + caramelo.getWidth() + 20, filacompletap.y + 45);
		marcador.setColor(Color.WHITE);
		marcador.draw(batch,"" + SeeSawCircus.gamew.scoreboard, 744 - (marcador.getBounds("" + SeeSawCircus.gamew.scoreboard).width/2),400);
		fuente.setColor(Color.GRAY);
		fuente.draw(batch, "" + SeeSawCircus.prefs.getLong("record",0), 582 - (fuente.getBounds("" + SeeSawCircus.prefs.getLong("record",0)).width/2),575);
		batch.draw(corona2, 578 - (fuente.getBounds("" + SeeSawCircus.prefs.getLong("record",0)).width/2) - 50,535);
	
		if (SeeSawCircus.gamew.isrecord){
			fuente.setColor(Color.YELLOW);
			//fuente.draw(batch,"NUEVO RECORD",570,265);
			fuente.draw(batch,"NUEVO RECORD",570,465);
			fuente.setColor(Color.BLACK);
			//batch.draw(corona,465,215 );
			batch.draw(corona,465,415 );
		}else{
			fuente.draw(batch,"Record a conseguir: " + SeeSawCircus.gamew.record,0,0);
		}
		batch.end();
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		reset();
		if (SeeSawCircus.gamew.isrecord){
			if ( SeeSawCircus.gamew.modechildren){
				SeeSawCircus.prefs.putLong("recordninos", SeeSawCircus.gamew.record);
			}else{
				SeeSawCircus.prefs.putLong("record", SeeSawCircus.gamew.record);
			}
			SeeSawCircus.prefs.flush();
		}
		if ( SeeSawCircus.igs.estaLoginGS()){
			SeeSawCircus.igs.enviarPuntosGS((long) SeeSawCircus.gamew.record, SeeSawCircus.gamew.modechildren, SeeSawCircus.gamew.isrecord);
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
		batch.dispose();
		
	}

}
