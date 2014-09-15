package es.eduardoanton.proyectos.juegos;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;

public class InputProcesador implements InputProcessor{

	private OrthographicCamera cam;
	private GameWorld gamew;
	private int cuentabotones = 0;
	private int last = 0;
	private final static float rotationSpeed = 360f;
	
	InputProcesador(OrthographicCamera cam, GameWorld gamew){
		this.cam = cam;
		this.gamew = gamew;
	}
	@Override
	public boolean keyDown(int keycode) {
		if (keycode == Input.Keys.LEFT){
			gamew.trampolin.setVelocity(-400);
			gamew.redondo = rotationSpeed;
			//gamew.trampolin.view = 1;
		}
		if (keycode == Input.Keys.RIGHT){
			gamew.trampolin.setVelocity(400);
			gamew.redondo = -rotationSpeed;
			//gamew.trampolin.view = 0;
		}
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		gamew.trampolin.setVelocity(0);
		gamew.redondo = 0f;
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		cuentabotones++;
		Vector3 touchpos = new Vector3(screenX,screenY,0);
		cam.unproject(touchpos);
		Gdx.app.log("CIRCUS", "X: " + touchpos.x);
		if ( touchpos.x <= 512 ){
			gamew.trampolin.setVelocity(-400);
			gamew.redondo = rotationSpeed;
			last = 0;
		}else{
			gamew.trampolin.setVelocity(400);
			gamew.redondo =  -rotationSpeed;
			last = 1;
		}
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		cuentabotones--;
		Vector3 touchpos = new Vector3(screenX,screenY,0);
		cam.unproject(touchpos);
		if (cuentabotones == 0){
			gamew.trampolin.setVelocity(0);
			gamew.redondo = 0f;
		}else{
			if ( (last == 1 && touchpos.x > 512) || (last == 0 && touchpos.x <=512 )){
				gamew.trampolin.velocidad.x = gamew.trampolin.velocidad.x * -1;
			}
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
