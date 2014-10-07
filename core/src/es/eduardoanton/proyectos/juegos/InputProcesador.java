package es.eduardoanton.proyectos.juegos;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;

import es.eduardoanton.proyectos.juegos.GameWorld.GameState;
import es.eduardoanton.proyectos.juegos.Payaso.PayasoState;

public class InputProcesador implements InputProcessor{

	private OrthographicCamera cam;
	private GameWorld gamew;
	private int cuentabotones = 0;
	private int last = 0;
	private final static float rotationSpeed = 360f;
	
	private boolean dragging = false;
	private float firstdragged;
	
	InputProcesador(OrthographicCamera cam, GameWorld gamew){
		this.cam = cam;
		this.gamew = gamew;
	}
	@Override
	public boolean keyDown(int keycode) {
		if (gamew.payaso1.state != Payaso.PayasoState.MESSDEATH && gamew.payaso2.state != Payaso.PayasoState.MESSDEATH 
				&&	gamew.payaso1.state != Payaso.PayasoState.MESSCRASH && gamew.payaso2.state != Payaso.PayasoState.MESSCRASH
					){
			if (keycode == Input.Keys.LEFT){
				gamew.trampolin.setVelocity(-400*gamew.isvelocidad);
				gamew.trampolin.rotacion = rotationSpeed;
			}
			if (keycode == Input.Keys.RIGHT){
				gamew.trampolin.setVelocity(400*gamew.isvelocidad);
				gamew.trampolin.rotacion = -rotationSpeed;
			}
		}
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		gamew.trampolin.setVelocity(0);
		gamew.trampolin.rotacion = 0f;
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
		if ( gamew.payaso1.state != Payaso.PayasoState.MESSDEATH && gamew.payaso2.state != Payaso.PayasoState.MESSDEATH
		  && gamew.payaso1.state != Payaso.PayasoState.MESSCRASH && gamew.payaso2.state != Payaso.PayasoState.MESSCRASH
				){
			if ( touchpos.x <= 512 ){
				gamew.trampolin.setVelocity(-400*gamew.isvelocidad);
				gamew.trampolin.rotacion = rotationSpeed;
				last = 0;
			}else{
				gamew.trampolin.setVelocity(400*gamew.isvelocidad);
				gamew.trampolin.rotacion =  -rotationSpeed;
				last = 1;
			}
		}
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		cuentabotones--;
		Vector3 touchpos = new Vector3(screenX,screenY,0);
		Payaso tmp;
		cam.unproject(touchpos);
		if (cuentabotones == 0){
			gamew.trampolin.setVelocity(0);
			gamew.trampolin.rotacion = 0f;
		}else{
			if ( (last == 1 && touchpos.x > 512) || (last == 0 && touchpos.x <=512 )){
				gamew.trampolin.velocidad.x = gamew.trampolin.velocidad.x * -1;
				gamew.trampolin.rotacion*=-1f;
			}
		}
		if (dragging == true){
			dragging = false;
			if ( touchpos.y - firstdragged > 30 && (gamew.gamestate == GameState.READY || gamew.gamestate == GameState.RUNNING)  ){
				gamew.trampolin.flip();
				tmp = gamew.getPayasoFlying();
				tmp = tmp.pc;
				if ( tmp.state == PayasoState.STANDBYL){
					tmp.state = PayasoState.STANDBYR;
				}else{
					tmp.state = PayasoState.STANDBYL;
				}
				gamew.swapS.play();
			}
		}
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		Vector3 touchpos = new Vector3(screenX,screenY,0);
		cam.unproject(touchpos);
		if ( ! dragging){
			firstdragged = touchpos.y;
			dragging = true;
		}
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
