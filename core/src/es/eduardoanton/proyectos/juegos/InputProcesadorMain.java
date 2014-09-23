package es.eduardoanton.proyectos.juegos;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class InputProcesadorMain implements InputProcessor{
	
	private OrthographicCamera cam;
	private SeeSawCircus game;
	public InputProcesadorMain(OrthographicCamera cam, SeeSawCircus game){
		this.cam = cam;
		this.game = game;
	}

	@Override
	public boolean keyDown(int keycode) {
		Gdx.app.log("CICUS", "SSS");
		this.game.setScreen(SeeSawCircus.ingamescreen);
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
		Gdx.app.log("CICUS", "SSS");
		this.game.setScreen(SeeSawCircus.ingamescreen);
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
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
