package es.eduardoanton.proyectos.juegos;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;

public class InputProcesadorMain implements InputProcessor{
	
	private OrthographicCamera cam;
	private SeeSawCircus game;
	private MainScreen mscreen;
	public InputProcesadorMain(OrthographicCamera cam, SeeSawCircus game, MainScreen mscreen){
		this.cam = cam;
		this.game = game;
		this.mscreen = mscreen;
	}

	@Override
	public boolean keyDown(int keycode) {
		
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
				return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		Vector3 touchpos = new Vector3(screenX,screenY,0);
		cam.unproject(touchpos);
		if (touchpos.x > 400 && touchpos.x < 611 && touchpos.y > 20 && touchpos.y < 214 ){
			mscreen.botoninicio.setTexture("botoniniciop.png");
			return true;
		}
		if (touchpos.x > 900 && touchpos.x < 1024 && touchpos.y > 480 && touchpos.y < 600 ){
			mscreen.salir.setTexture("salirp.png");
			return true;
		}
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		Vector3 touchpos = new Vector3(screenX,screenY,0);
		cam.unproject(touchpos);
		Gdx.app.log("CIRCUS","X:" + touchpos.x + " Y: " + touchpos.y);
		if (touchpos.x > 400 && touchpos.x < 611 && touchpos.y > 20 && touchpos.y < 214 ){
			MainScreen.clic.play();
			this.game.setScreen(SeeSawCircus.ingamescreen);
			return true;
		}
		if (touchpos.x > 900 && touchpos.x < 1024 && touchpos.y > 480 && touchpos.y < 600 ){
			Gdx.app.exit();
			return true;
		}
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}
}
