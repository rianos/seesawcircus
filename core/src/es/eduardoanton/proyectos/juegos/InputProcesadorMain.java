package es.eduardoanton.proyectos.juegos;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;

public class InputProcesadorMain implements InputProcessor{
	
	private OrthographicCamera cam;
	private MainScreen mscreen;
	public InputProcesadorMain(OrthographicCamera cam, MainScreen mscreen){
		this.cam = cam;
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
		Gdx.app.log("POS", "X " + touchpos.x + " Y: " + touchpos.y);
		if (touchpos.x > 400 && touchpos.x < 611 && touchpos.y > 20 && touchpos.y < 214 && mscreen.botoninicio.isVisible()){
			mscreen.botoninicio.setTexture("botoniniciop.png");
			return true;
		}
		if (touchpos.x > 900 && touchpos.x < 1024 && touchpos.y > 480 && touchpos.y < 600 ){
			mscreen.salir.setTexture("salirp.png");
			return true;
		}
		if (touchpos.x > 790 && touchpos.x < 995 && touchpos.y > 224 && touchpos.y < 440 && mscreen.botoncreditos.isVisible()){
			mscreen.botoncreditos.setTexture("botoncreditosp.png");
			return true;
		}
		if (touchpos.x > 900 && touchpos.x < 1020 && touchpos.y > 0 && touchpos.y < 115 && mscreen.botoninfo.isVisible()){
			mscreen.botoninfo.setTexture("botoninfop.png");
			return true;
		}
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		Vector3 touchpos = new Vector3(screenX,screenY,0);
		cam.unproject(touchpos);
		if (touchpos.x > 400 && touchpos.x < 611 && touchpos.y > 20 && touchpos.y < 214 && mscreen.botoninicio.isVisible()){
			SeeSawCircus.gamew.modechildren = false;
			MainScreen.clic.play();
			if ( SeeSawCircus.gamew.modemanual){
				mscreen.game.setScreen(SeeSawCircus.ingamescreen);
			}else{
				mscreen.game.setScreen(SeeSawCircus.instructionscreen);
			}
			return true;
		}
		if (touchpos.x > 900 && touchpos.x < 1024 && touchpos.y > 480 && touchpos.y < 600 ){
			Gdx.app.exit();
			return true;
		}
		if (touchpos.x > 0 && touchpos.x < 104 && touchpos.y > 0 && touchpos.y < 105 ){
			if ( ! SeeSawCircus.igs.estaLoginGS()){
				SeeSawCircus.igs.entrarGS();				
			}else{
				SeeSawCircus.igs.salirGS();
			}
			return true;
		}
		if (touchpos.x > 55 && touchpos.x < 267 && touchpos.y > 227 && touchpos.y < 421 && mscreen.botonninos.isVisible()){
			SeeSawCircus.gamew.modechildren = !SeeSawCircus.gamew.modechildren;
			MainScreen.clic.play();
			if ( SeeSawCircus.gamew.modechildren){
				mscreen.botonninos.setTexture("botonninoson.png");
				SeeSawCircus.prefs.putBoolean("modoninos",true);
				SeeSawCircus.prefs.flush();
			}else{
				mscreen.botonninos.setTexture("botonninosoff.png");
				SeeSawCircus.prefs.putBoolean("modoninos",false);
				SeeSawCircus.prefs.flush();
			}
			return true;
		}
		/*if (touchpos.x > 0 && touchpos.x < 103 && touchpos.y > 490 && touchpos.y < 600 && mscreen.botonmusica.isVisible()){
			GameWorld.modemusic = !GameWorld.modemusic;
			MainScreen.clic.play();
			if ( GameWorld.modemusic){
				mscreen.botonmusica.setTexture("botonmusicaon.png");
				SeeSawCircus.prefs.putBoolean("modomusica",true);
				SeeSawCircus.prefs.flush();
				mscreen.musica.play();
			}else{
				mscreen.botonmusica.setTexture("botonmusicaoff.png");
				SeeSawCircus.prefs.putBoolean("modomusica",false);
				SeeSawCircus.prefs.flush();
				mscreen.musica.stop();
			}
			return true;
		}*/
		
		if (touchpos.x > 790 && touchpos.x < 995 && touchpos.y > 224 && touchpos.y < 440 && mscreen.botoncreditos.isVisible() ){
			MainScreen.clic.play();
			mscreen.game.setScreen(SeeSawCircus.creditsscreen);
			return true;
		}
		if (touchpos.x > 900 && touchpos.x < 1020 && touchpos.y > 0 && touchpos.y < 115 && mscreen.botoninfo.isVisible()){
			MainScreen.clic.play();
			mscreen.game.setScreen(SeeSawCircus.instructionscreen);
			return true;
		}
		playRandomSound();
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
	
	public void playRandomSound(){
		switch(MathUtils.random(1,6)){
			case 1: SeeSawCircus.gamew.horseS.play(); break;
			case 2: SeeSawCircus.gamew.elephantS.play(); break;
			case 3: SeeSawCircus.gamew.dogS.play(); break;
			case 4: SeeSawCircus.gamew.focaS.play(); break;
			case 5: SeeSawCircus.gamew.lionroarS.play(); break;
			case 6: SeeSawCircus.gamew.bearS.play(); break;
		
		}
	}
}
