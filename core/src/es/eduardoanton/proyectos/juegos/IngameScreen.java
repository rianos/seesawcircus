package es.eduardoanton.proyectos.juegos;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import es.eduardoanton.proyectos.juegos.Trampolin.TrampolinState;

public class IngameScreen implements Screen{

	private SeeSawCircus game;
	private SpriteBatch batch;
	private OrthographicCamera cam;
	private Texture trampolintexturel;
	private Texture trampolintexturer;
	private Texture payaso,payaso2,fondo;
	private Sprite redondo;

	private GameWorld gamew;
		
	public IngameScreen( SeeSawCircus gam){
		this.game = gam;
		cam = new OrthographicCamera();
		cam.setToOrtho(false, 1024,600);
		cam.update();
		batch = new SpriteBatch();
		batch.setProjectionMatrix(cam.combined);	
		trampolintexturel = game.getAsset().get("trampolin.png", Texture.class );
		trampolintexturer = game.getAsset().get("trampolin2.png", Texture.class );
		payaso = game.getAsset().get("payaso22.png", Texture.class);
		payaso2 = game.getAsset().get("payaso33.png", Texture.class);
		//payaso = game.getAsset().get("payaso2.png", Texture.class);
		//payaso2 = game.getAsset().get("payaso3.png", Texture.class);
				
		fondo = game.getAsset().get("fondo.png", Texture.class);
		redondo =  new Sprite( (Texture) game.getAsset().get("redondo.png"));
		gamew = new GameWorld();
		Gdx.input.setInputProcessor(new InputProcesador(cam,gamew));
		
	}
	@Override
	public void render(float delta) {
		gamew.update(delta);
		Gdx.gl.glClearColor(0, 0,0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);	
		batch.begin();
		//batch.draw(fondo,0,0);
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
		batch.end();
			
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
