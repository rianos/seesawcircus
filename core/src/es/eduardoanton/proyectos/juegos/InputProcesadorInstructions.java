package es.eduardoanton.proyectos.juegos;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;

public class InputProcesadorInstructions implements InputProcessor {

	private OrthographicCamera cam;
	private InstructionsScreen cscreen;
	
	public InputProcesadorInstructions(OrthographicCamera cam, InstructionsScreen cscreen){
		this.cam = cam;
		this.cscreen = cscreen;
	}
	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
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
		if (touchpos.x > 900 && touchpos.x < 1024 && touchpos.y > 470 && touchpos.y < 600 ){
			InstructionsScreen.botonhome.setTexture("botonhomepep.png");
			return true;
		}
		if (touchpos.x > 400 && touchpos.x < 611 && touchpos.y > 20 && touchpos.y < 214 && cscreen.botoninicio.isVisible()){
			cscreen.botoninicio.setTexture("botoniniciop.png");
			return true;
		}
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		Vector3 touchpos = new Vector3(screenX,screenY,0);
		cam.unproject(touchpos);
		if (touchpos.x > 900 && touchpos.x < 1024 && touchpos.y > 470 && touchpos.y < 600 ){
			MainScreen.clic.play();
			cscreen.game.setScreen(SeeSawCircus.mainscreen);
			return true;
		}
		if (touchpos.x > 400 && touchpos.x < 611 && touchpos.y > 20 && touchpos.y < 214 && cscreen.botoninicio.isVisible()){
			MainScreen.clic.play();
			cscreen.game.setScreen(SeeSawCircus.ingamescreen);
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
