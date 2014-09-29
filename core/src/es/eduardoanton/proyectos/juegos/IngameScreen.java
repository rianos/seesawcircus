package es.eduardoanton.proyectos.juegos;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.MathUtils;

import es.eduardoanton.proyectos.juegos.Trampolin.TrampolinState;

public class IngameScreen implements Screen{

	private SeeSawCircus game;
	private SpriteBatch batch;
	private OrthographicCamera cam;
	private Texture trampolintexturel,trampolinsombra,payasosombra;
	private Texture payaso,payaso2,fondo,payasodeath,lapida,corazon,musica,payasob,payaso2b,payasoc,payaso2c;
	private Texture carameloa,carameloz,caramelov,caramelor,corona;
	private TextureRegion trampolintexturer,p1llorando[],p2llorando[],p1estrellas[],p2estrellas[],explosion[];
	private Sprite redondo;
	public Animation p1llorandoA,p2llorandoA,p1estrellasA,p2estrellasA,explosionA;
	public BitmapFont marcador,gameover,letrero,informe;
	public static float statetime = 0f;
	public Texture caramelos[];
	private InputProcessor iproc;
	


	private GameWorld gamew;
	private float a = 0f;
	public IngameScreen( SeeSawCircus gam){
		this.game = gam;
		cam = new OrthographicCamera();
		cam.setToOrtho(false, 1024,600);
		cam.update();
		
		batch = new SpriteBatch();
		batch.setProjectionMatrix(cam.combined);	
		trampolintexturel = game.asset.get("trampolin.png", Texture.class );
		trampolinsombra = game.asset.get("trampolin_sombra.png", Texture.class );
		payasosombra = game.asset.get("payaso_sombra.png", Texture.class );
		trampolintexturer = new TextureRegion(trampolintexturel);
		trampolintexturer.flip(true,false);
		payaso = game.asset.get("payaso22.png", Texture.class);
		payaso2 = game.asset.get("payaso33.png", Texture.class);
		payasob = game.asset.get("payaso22bajando.png", Texture.class);
		payaso2b = game.asset.get("payaso33bajando.png", Texture.class);
		payasoc = game.asset.get("payaso22cruz.png", Texture.class);
		payaso2c = game.asset.get("payaso33cruz.png", Texture.class);
		payasodeath =game.asset.get("payasodeath.png", Texture.class);
		lapida =game.asset.get("lapida.png", Texture.class);
		corazon =game.asset.get("corazon.png", Texture.class);
		musica =game.asset.get("musica.png", Texture.class);
		corona = game.asset.get("record.png", Texture.class);
		explosion = new TextureRegion[4];
		explosion[0] = new TextureRegion(game.asset.get("explosion1.png", Texture.class));
		explosion[1] = new TextureRegion(game.asset.get("explosion2.png", Texture.class));
		explosion[2] = new TextureRegion(game.asset.get("explosion3.png", Texture.class));
		explosion[3] = new TextureRegion(game.asset.get("explosion4.png", Texture.class));
		explosionA = new Animation(0.2f,explosion);
		//caramelos = new Texture[4];
		caramelos = new Texture[20];
		caramelos[0] = game.asset.get("carameloa.png", Texture.class);
		caramelos[1] = game.asset.get("caramelov.png", Texture.class);
		caramelos[2] = game.asset.get("caramelor.png", Texture.class);
		caramelos[3] = game.asset.get("carameloz.png", Texture.class);
	
		caramelos[4] = game.asset.get("globoa.png", Texture.class);
		caramelos[5] = game.asset.get("globoz.png", Texture.class);
		caramelos[6] = game.asset.get("globov.png", Texture.class);
		caramelos[7] = game.asset.get("globor.png", Texture.class);
		
		caramelos[15] = game.asset.get("monedaluciap.png", Texture.class);
		caramelos[16] = game.asset.get("monedasoniap.png", Texture.class);
		caramelos[17] = game.asset.get("monedalucia.png", Texture.class);
		caramelos[18] = game.asset.get("monedasonia.png", Texture.class);
		caramelos[19] = game.asset.get("billeteedu.png", Texture.class);
		caramelos[8] = game.asset.get("leon.png", Texture.class);
		caramelos[9] = game.asset.get("caballo.png", Texture.class);
		caramelos[10] = game.asset.get("oso.png", Texture.class);
		caramelos[11] = game.asset.get("elefante.png", Texture.class);
		caramelos[12] = game.asset.get("mono.png", Texture.class);
		caramelos[13] = game.asset.get("foca.png", Texture.class);
		caramelos[14] = game.asset.get("perro.png", Texture.class);
		
		p1llorando = new TextureRegion[2];
		p1llorando[0] =  new TextureRegion(game.asset.get("p1c1.png", Texture.class));
		p1llorando[1] =  new TextureRegion(game.asset.get("p1c2.png", Texture.class));
		p1llorandoA = new Animation(0.1f,p1llorando);
		p1llorandoA.setPlayMode(Animation.PlayMode.LOOP);
		p2llorando = new TextureRegion[2];
		p2llorando[0] =  new TextureRegion(game.asset.get("p2c1.png", Texture.class));
		p2llorando[1] =  new TextureRegion(game.asset.get("p2c2.png", Texture.class));
		p2llorandoA = new Animation(0.1f,p2llorando);
		p2llorandoA.setPlayMode(Animation.PlayMode.LOOP);
		p1estrellas = new TextureRegion[4];
		p1estrellas[0] =  new TextureRegion(game.asset.get("p1e1.png", Texture.class));
		p1estrellas[1] =  new TextureRegion(game.asset.get("p1e2.png", Texture.class));
		p1estrellas[2] =  new TextureRegion(game.asset.get("p1e3.png", Texture.class));
		p1estrellas[3] =  new TextureRegion(game.asset.get("p1e4.png", Texture.class));
		p1estrellasA = new Animation(0.24f,p1estrellas);
		p1estrellasA.setPlayMode(Animation.PlayMode.LOOP);
		p2estrellas = new TextureRegion[4];
		p2estrellas[0] =  new TextureRegion(game.asset.get("p2e1.png", Texture.class));
		p2estrellas[1] =  new TextureRegion(game.asset.get("p2e2.png", Texture.class));
		p2estrellas[2] =  new TextureRegion(game.asset.get("p2e3.png", Texture.class));
		p2estrellas[3] =  new TextureRegion(game.asset.get("p2e4.png", Texture.class));
		p2estrellasA = new Animation(0.24f,p2estrellas);
		p2estrellasA.setPlayMode(Animation.PlayMode.LOOP);		
		fondo = game.asset.get("fondo.png", Texture.class);
		redondo =  new Sprite( (Texture) game.asset.get("redondo.png"));
		gamew = SeeSawCircus.gamew;
		iproc = new InputProcesador(cam,gamew);
		marcador =game.asset.get("fuenteBerlinSansFBDemi.fnt", BitmapFont.class);
		informe =game.asset.get("fuente.fnt", BitmapFont.class);	
		informe.setColor(Color.YELLOW);
		letrero = new BitmapFont();
		letrero.setColor(Color.GRAY);
		
	}
	
	
	@Override
	public void render(float delta) {
	
		a+=delta;
		gamew.update(delta);
		Gdx.gl.glClearColor(0.02f,0.22f ,0.22f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);	
		
		batch.begin();
		batch.draw(fondo,0,0);
		for (int j=0;j<4;j++){
			for (int i=0;i<10;i++){
				if (gamew.arrayFilaObjetivos[j].elementos[i] != -1 ){
					batch.draw(caramelos[(gamew.arrayFilaObjetivos[j].elementos[i])], gamew.arrayFilaObjetivos[j].posicion.x + (caramelos[1].getWidth() + 20)*i,gamew.arrayFilaObjetivos[j].posicion.y);
				}
			}
		}
		
		//marcador.draw(batch,String.format("%d", gamew.scoreboard), 512 - (marcador.getBounds("" + gamew.scoreboard).width)/2, 350);
		if ( gamew.isrecord){
			batch.draw(corona, SeeSawCircus.screenwidth - corona.getWidth() -10, 10);
			if ( gamew.time < 2){
				gamew.time+=delta;
				informe.setColor(Color.YELLOW);
				informe.draw(batch,"NUEVO RECORD", 512 - (informe.getBounds("NUEVO RECORD").width/2), 450);
				informe.draw(batch,"NEW RECORD", 512 - (informe.getBounds("NEW RECORD").width/2), 400);
			}
		}
		marcador.draw(batch,String.format("%d", gamew.scoreboard), 1000 - (marcador.getBounds("" + gamew.scoreboard).width), 600);
		batch.draw(musica, 0, 0);
		letrero.draw(batch, " " + Musica.getName() , 25, 20);
		for ( int i=1;i<=gamew.vidas;i++){
			batch.draw(corazon, -30 + (corazon.getWidth() + 5)*i,550);
		}	
		if (gamew.gamestate == GameWorld.GameState.GAMEOVER){
			marcador.draw(batch, "GAME OVER", 15, 200);
		}
		if (gamew.payaso1.state == Payaso.PayasoState.MESSDEATH){
			renderCrashDeath(gamew.payaso1, gamew.payaso2,delta);
		}
		if (gamew.payaso2.state == Payaso.PayasoState.MESSDEATH){
			renderCrashDeath(gamew.payaso2, gamew.payaso1,delta);
		}
		if (gamew.payaso1.state == Payaso.PayasoState.MESSCRASH){
			statetime +=delta;
			renderCryingDeath(gamew.payaso1, gamew.payaso2,delta);
		}
		if (gamew.payaso2.state == Payaso.PayasoState.MESSCRASH){
			statetime +=delta;
			renderCryingDeath(gamew.payaso2, gamew.payaso1,delta);
		}
		if (gamew.payaso1.state != Payaso.PayasoState.MESSDEATH && gamew.payaso2.state != Payaso.PayasoState.MESSDEATH 
			&&	gamew.payaso1.state != Payaso.PayasoState.MESSCRASH && gamew.payaso2.state != Payaso.PayasoState.MESSCRASH
				){
			if (gamew.payaso1.velocidad.y  <= 300f && gamew.payaso1.velocidad.y >= -300f && gamew.payaso1.state == Payaso.PayasoState.FLYING ){
				batch.draw(payasoc, gamew.payaso1.posicion.x,gamew.payaso1.posicion.y);
		    }else if (gamew.payaso1.velocidad.y >= 0f || gamew.payaso1.state != Payaso.PayasoState.FLYING ){
				batch.draw(payaso, gamew.payaso1.posicion.x,gamew.payaso1.posicion.y);
			
			}else{
				batch.draw(payasob, gamew.payaso1.posicion.x,gamew.payaso1.posicion.y);
			}
			if (gamew.payaso2.velocidad.y  <= 300f && gamew.payaso2.velocidad.y >= -300f && gamew.payaso2.state == Payaso.PayasoState.FLYING ){
				batch.draw(payaso2c, gamew.payaso2.posicion.x,gamew.payaso2.posicion.y);
			}else if (gamew.payaso2.velocidad.y >= 0f  || gamew.payaso2.state != Payaso.PayasoState.FLYING){
				batch.draw(payaso2, gamew.payaso2.posicion.x,gamew.payaso2.posicion.y);
			}else{
				batch.draw(payaso2b, gamew.payaso2.posicion.x,gamew.payaso2.posicion.y);
			}
		
			if ( gamew.trampolin.view == TrampolinState.RIGHT){
				batch.draw(payasosombra, gamew.payaso2.posicion.x,30);
				batch.draw(trampolintexturer,gamew.trampolin.posicion.x,gamew.trampolin.posicion.y);
				batch.draw(trampolinsombra, gamew.trampolin.posicion.x, 30);
			}else{
				batch.draw(payasosombra, gamew.payaso1.posicion.x,30);
				batch.draw(trampolintexturel,gamew.trampolin.posicion.x,gamew.trampolin.posicion.y);
				batch.draw(trampolinsombra, gamew.trampolin.posicion.x, 30);
			}
			batch.draw(caramelos[19],gamew.paraguas.x,gamew.paraguas.y);
			redondo.setX(gamew.trampolin.posicion.x + 62);
			redondo.setY(gamew.trampolin.posicion.y);
			redondo.rotate(gamew.redondo * delta);
			redondo.draw(batch);
			
		}
		batch.end();
			
	}


	public void renderCrashDeath(Payaso payasomuerto, Payaso payasovivo,float delta){
		batch.draw(payasodeath, payasomuerto.posicion.x, payasomuerto.posicion.y);
		batch.draw(lapida, payasomuerto.posicion.x - 20, 20);
		if (payasovivo.PayasoID == 1){
			batch.draw(payaso, payasovivo.posicion.x,payasovivo.posicion.y);
		}else{
			batch.draw(payaso2, payasovivo.posicion.x,payasovivo.posicion.y);
		}
		if ( gamew.trampolin.view == TrampolinState.RIGHT){
			batch.draw(trampolintexturer,gamew.trampolin.posicion.x,gamew.trampolin.posicion.y);
			batch.draw(trampolinsombra, gamew.trampolin.posicion.x, 30);
		}else{
			batch.draw(trampolintexturel,gamew.trampolin.posicion.x,gamew.trampolin.posicion.y);
			batch.draw(trampolinsombra, gamew.trampolin.posicion.x, 30);
		}
		redondo.setX(gamew.trampolin.posicion.x + 62);
		redondo.setY(gamew.trampolin.posicion.y);
		redondo.rotate(gamew.redondo * delta);
		redondo.draw(batch);
	}
	
	public void renderCryingDeath(Payaso payasomuerto, Payaso payasovivo,float delta){
		
		if (payasovivo.PayasoID == 1){
			batch.draw(p2llorandoA.getKeyFrame(statetime), payasomuerto.posicion.x, payasomuerto.posicion.y);
			batch.draw(p1estrellasA.getKeyFrame(statetime), payasovivo.posicion.x,payasovivo.posicion.y);
		}else{
			batch.draw(p1llorandoA.getKeyFrame(statetime), payasomuerto.posicion.x, payasomuerto.posicion.y);
			batch.draw(p2estrellasA.getKeyFrame(statetime), payasovivo.posicion.x,payasovivo.posicion.y);
		}
		if ( gamew.trampolin.view == TrampolinState.RIGHT){
			batch.draw(trampolintexturer,gamew.trampolin.posicion.x,gamew.trampolin.posicion.y);
			batch.draw(trampolinsombra, gamew.trampolin.posicion.x, 10);
		}else{
			batch.draw(trampolintexturel,gamew.trampolin.posicion.x,gamew.trampolin.posicion.y);
			batch.draw(trampolinsombra, gamew.trampolin.posicion.x, 10);
		}
		redondo.setX(gamew.trampolin.posicion.x + 62);
		redondo.setY(gamew.trampolin.posicion.y);
		redondo.rotate(gamew.redondo * delta);
		redondo.draw(batch);
		if ( ! explosionA.isAnimationFinished(statetime)){
			batch.draw(explosionA.getKeyFrame(statetime),payasomuerto.posicion.x - 100, -40);
		}
	}
	
	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		Gdx.input.setInputProcessor(iproc);
		Musica.playRandom();	
		gamew.reset();
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
