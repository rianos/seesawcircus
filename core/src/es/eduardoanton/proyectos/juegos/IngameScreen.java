package es.eduardoanton.proyectos.juegos;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import es.eduardoanton.proyectos.juegos.Trampolin.TrampolinState;

public class IngameScreen implements Screen{

	private SeeSawCircus game;
	private SpriteBatch batch;
	private OrthographicCamera cam;
	private Texture trampolintexturel;
	private Texture payaso,payaso2,fondo,payasodeath,lapida;
	private TextureRegion trampolintexturer,p1llorando[],p2llorando[],p1estrellas[],p2estrellas[];
	private Sprite redondo;
	public Animation p1llorandoA,p2llorandoA,p1estrellasA,p2estrellasA;
	private float statetime = 0f;

	private GameWorld gamew;
	private float a = 0f;
	public IngameScreen( SeeSawCircus gam){
		this.game = gam;
		cam = new OrthographicCamera();
		cam.setToOrtho(false, 1024,600);
		cam.update();
		batch = new SpriteBatch();
		batch.setProjectionMatrix(cam.combined);	
		trampolintexturel = game.getAsset().get("trampolin.png", Texture.class );
		trampolintexturer = new TextureRegion(trampolintexturel);
		trampolintexturer.flip(true,false);
		payaso = game.getAsset().get("payaso22.png", Texture.class);
		payaso2 = game.getAsset().get("payaso33.png", Texture.class);
		payasodeath =game.getAsset().get("payasodeath.png", Texture.class);
		lapida =game.getAsset().get("lapida.png", Texture.class);
		p1llorando = new TextureRegion[2];
		p1llorando[0] =  new TextureRegion(game.getAsset().get("p1c1.png", Texture.class));
		p1llorando[1] =  new TextureRegion(game.getAsset().get("p1c2.png", Texture.class));
		p1llorandoA = new Animation(0.1f,p1llorando);
		p1llorandoA.setPlayMode(Animation.PlayMode.LOOP);
		p2llorando = new TextureRegion[2];
		p2llorando[0] =  new TextureRegion(game.getAsset().get("p2c1.png", Texture.class));
		p2llorando[1] =  new TextureRegion(game.getAsset().get("p2c2.png", Texture.class));
		p2llorandoA = new Animation(0.1f,p2llorando);
		p2llorandoA.setPlayMode(Animation.PlayMode.LOOP);
		p1estrellas = new TextureRegion[4];
		p1estrellas[0] =  new TextureRegion(game.getAsset().get("p1e1.png", Texture.class));
		p1estrellas[1] =  new TextureRegion(game.getAsset().get("p1e2.png", Texture.class));
		p1estrellas[2] =  new TextureRegion(game.getAsset().get("p1e3.png", Texture.class));
		p1estrellas[3] =  new TextureRegion(game.getAsset().get("p1e4.png", Texture.class));
		p1estrellasA = new Animation(0.24f,p1estrellas);
		p1estrellasA.setPlayMode(Animation.PlayMode.LOOP);
		p2estrellas = new TextureRegion[4];
		p2estrellas[0] =  new TextureRegion(game.getAsset().get("p2e1.png", Texture.class));
		p2estrellas[1] =  new TextureRegion(game.getAsset().get("p2e2.png", Texture.class));
		p2estrellas[2] =  new TextureRegion(game.getAsset().get("p2e3.png", Texture.class));
		p2estrellas[3] =  new TextureRegion(game.getAsset().get("p2e4.png", Texture.class));
		p2estrellasA = new Animation(0.24f,p2estrellas);
		p2estrellasA.setPlayMode(Animation.PlayMode.LOOP);
		//payaso = game.getAsset().get("payaso2.png", Texture.class);
		//payaso2 = game.getAsset().get("payaso3.png", Texture.class);
				
		fondo = game.getAsset().get("fondo.png", Texture.class);
		redondo =  new Sprite( (Texture) game.getAsset().get("redondo.png"));
		gamew = new GameWorld();
		Gdx.input.setInputProcessor(new InputProcesador(cam,gamew));
		
	}
	@Override
	public void render(float delta) {
		a+=delta;
		gamew.update(delta);
		Gdx.gl.glClearColor(0.1f,0.2f ,0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);	
		batch.begin();
		batch.draw(p1estrellasA.getKeyFrame(a), 100,200);
		batch.draw(p2estrellasA.getKeyFrame(a), 200,200);
		//batch.draw(fondo,0,0);
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
			batch.draw(payaso, gamew.payaso1.posicion.x,gamew.payaso1.posicion.y);
			batch.draw(payaso2, gamew.payaso2.posicion.x,gamew.payaso2.posicion.y);
			if ( gamew.trampolin.view == TrampolinState.RIGHT){
				batch.draw(trampolintexturer,gamew.trampolin.posicion.x,gamew.trampolin.posicion.y);
			}else{
				batch.draw(trampolintexturel,gamew.trampolin.posicion.x,gamew.trampolin.posicion.y);
			}
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
		}else{
			batch.draw(trampolintexturel,gamew.trampolin.posicion.x,gamew.trampolin.posicion.y);
		}
		redondo.setX(gamew.trampolin.posicion.x + 62);
		redondo.setY(gamew.trampolin.posicion.y);
		redondo.rotate(gamew.redondo * delta);
		redondo.draw(batch);
	}
	
	public void renderCryingDeath(Payaso payasomuerto, Payaso payasovivo,float delta){
		if (payasovivo.PayasoID == 1){
			batch.draw(p2llorandoA.getKeyFrame(statetime), payasomuerto.posicion.x, payasomuerto.posicion.y);
			batch.draw(payaso, payasovivo.posicion.x,payasovivo.posicion.y);
		}else{
			batch.draw(p1llorandoA.getKeyFrame(statetime), payasomuerto.posicion.x, payasomuerto.posicion.y);
			batch.draw(payaso2, payasovivo.posicion.x,payasovivo.posicion.y);
		}
		if ( gamew.trampolin.view == TrampolinState.RIGHT){
			batch.draw(trampolintexturer,gamew.trampolin.posicion.x,gamew.trampolin.posicion.y);
		}else{
			batch.draw(trampolintexturel,gamew.trampolin.posicion.x,gamew.trampolin.posicion.y);
		}
		redondo.setX(gamew.trampolin.posicion.x + 62);
		redondo.setY(gamew.trampolin.posicion.y);
		redondo.rotate(gamew.redondo * delta);
		redondo.draw(batch);
	}
	
	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
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
