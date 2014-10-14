package es.eduardoanton.proyectos.juegos;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
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
		if(keycode == Keys.BACK){
			IngameScreen.botoninicio = SeeSawCircus.asset.get("botoninicio.png", Texture.class);
			IngameScreen.botonreloadpe = SeeSawCircus.asset.get("salir.png", Texture.class);
			IngameScreen.botonhomepe = SeeSawCircus.asset.get("botonhomepe.png", Texture.class);
			Musica.stop();
			gamew.pausedgame = true;
			return true;
		 }
		if (!gamew.pausedgame){
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
		}
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		if (!gamew.pausedgame){
			gamew.trampolin.setVelocity(0);
			gamew.trampolin.rotacion = 0f;
		}
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		if (!gamew.pausedgame){
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
		}else{
			Vector3 touchpos = new Vector3(screenX,screenY,0);
			cam.unproject(touchpos);
			if (touchpos.x > 219 && touchpos.x < 350 && touchpos.y > 320 && touchpos.y < 450 ){
				IngameScreen.botonhomepe = IngameScreen.botonhomepep;
				return true;
			}
			if (touchpos.x > 665 && touchpos.x < 828 && touchpos.y > 327 && touchpos.y < 469 ){
				IngameScreen.botonreloadpe = IngameScreen.botonreloadpep;
				return true;
			}
			if (touchpos.x > 400 && touchpos.x < 620 && touchpos.y > 120 && touchpos.y < 340 ){
				IngameScreen.botoninicio = IngameScreen.botoniniciop;
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		if (!gamew.pausedgame){
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
		}else{
			Vector3 touchpos = new Vector3(screenX,screenY,0);
			cam.unproject(touchpos);
			if (touchpos.x > 219 && touchpos.x < 350 && touchpos.y > 320 && touchpos.y < 450 ){
				MainScreen.clic.play();
				gamew.pausedgame = false;
				gamew.game.setScreen(SeeSawCircus.mainscreen);
				return true;
			}
			if (touchpos.x > 665 && touchpos.x < 828 && touchpos.y > 327 && touchpos.y < 469 ){
				MainScreen.clic.play();
				gamew.pausedgame = false;
				Gdx.app.exit();
				return true;
			}
			if (touchpos.x > 400 && touchpos.x < 620 && touchpos.y > 120 && touchpos.y < 340 ){
				gamew.pausedgame = false;
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		if (!gamew.pausedgame){
			Vector3 touchpos = new Vector3(screenX,screenY,0);
			cam.unproject(touchpos);
			if ( ! dragging){
				firstdragged = touchpos.y;
				dragging = true;
			}
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
