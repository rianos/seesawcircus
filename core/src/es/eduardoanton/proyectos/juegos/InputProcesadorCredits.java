package es.eduardoanton.proyectos.juegos;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;

public class InputProcesadorCredits implements InputProcessor {

	
	private OrthographicCamera cam;
	private SeeSawCircus game;
	private CreditsScreen cscreen;
	public InputProcesadorCredits(OrthographicCamera cam, SeeSawCircus game, CreditsScreen cscreen){
		this.cam = cam;
		this.game = game;
		this.cscreen = cscreen;
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
		touchpos.y = touchpos.y - cam.position.y + 300;
		if (touchpos.x > 900 && touchpos.x < 1024 && touchpos.y > 470 && touchpos.y < 600 ){
			cscreen.botonhome = game.asset.get("botonhomepep.png");
			return true;
		}
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		Vector3 touchpos = new Vector3(screenX,screenY,0);
		cam.unproject(touchpos);
		touchpos.y = touchpos.y - cam.position.y + 300;
		if (touchpos.x > 900 && touchpos.x < 1024 && touchpos.y > 470 && touchpos.y < 600 ){
			MainScreen.clic.play();
			this.game.setScreen(SeeSawCircus.mainscreen);
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
