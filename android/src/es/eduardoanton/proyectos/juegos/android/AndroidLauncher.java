package es.eduardoanton.proyectos.juegos.android;

import android.content.Intent;
import android.os.Bundle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.google.android.gms.games.Games;
import com.google.example.games.basegameutils.GameHelper;
import com.google.example.games.basegameutils.GameHelper.GameHelperListener;

import es.eduardoanton.proyectos.juegos.IGoogleServices;
import es.eduardoanton.proyectos.juegos.SeeSawCircus;

public class AndroidLauncher extends AndroidApplication implements IGoogleServices {
	
	private GameHelper _gameHelper; 
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		_gameHelper = new GameHelper(this, GameHelper.CLIENT_GAMES);
		_gameHelper.enableDebugLog(false);
		GameHelperListener gameHelperListener = new GameHelper.GameHelperListener() {
			
			@Override
			public void onSignInSucceeded() {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onSignInFailed() {
				// TODO Auto-generated method stub
				
			}
		};
		_gameHelper.setup(gameHelperListener);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		config.useAccelerometer = false;
		config.useCompass = false;
		config.useImmersiveMode = true;
		initialize(new SeeSawCircus(this), config);
	}

	@Override
	public void entrarGS() {
		try{
			runOnUiThread(new Runnable(){
				public void run(){
					_gameHelper.beginUserInitiatedSignIn();
				}
			});
		}
		catch (Exception e){
			Gdx.app.log("CIRUS", "Google Services Login Failed " + e.getMessage());
		}
		
	}

	@Override
	public void salirGS() {
		try{
			runOnUiThread(new Runnable(){
				public void run(){
					_gameHelper.signOut();
				}
			});
		}
		catch (Exception e){
			Gdx.app.log("CIRUS", "Google Services Logout Failed " + e.getMessage());
		}
	}

	@Override
	public void enviarPuntosGS(long score, String gameMode) {
		if ( estaLoginGS() ){
			if ( gameMode.equals("hard")){
				Games.Leaderboards.submitScore(_gameHelper.getApiClient(), getString(R.string.leaderboard_id), score);
				startActivityForResult(Games.Leaderboards.getLeaderboardIntent(_gameHelper.getApiClient(), getString(R.string.leaderboard_id)), 434);
			}else{
				Games.Leaderboards.submitScore(_gameHelper.getApiClient(), getString(R.string.leaderboardnet_id), score);
				startActivityForResult(Games.Leaderboards.getLeaderboardIntent(_gameHelper.getApiClient(), getString(R.string.leaderboardnet_id)), 434);
			}
		}
	}

	@Override
	public void mostrarPuntosGS(String gameMode) {
		if ( estaLoginGS() ){
			if ( gameMode.equals("hard")){
				startActivityForResult(Games.Leaderboards.getLeaderboardIntent(_gameHelper.getApiClient(), getString(R.string.leaderboard_id)), 434);
			}else{
				startActivityForResult(Games.Leaderboards.getLeaderboardIntent(_gameHelper.getApiClient(), getString(R.string.leaderboardnet_id)), 434);
			}
		}
	}

	@Override
	public boolean estaLoginGS() {
		return _gameHelper.isSignedIn();
	}
	
	@Override
	protected void onStart(){
		super.onStart();
		_gameHelper.onStart(this);
	}
	
	@Override
	protected void onStop(){
		super.onStop();
		_gameHelper.onStop();
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data){
		super.onActivityResult(requestCode, resultCode, data);
		_gameHelper.onActivityResult(requestCode, resultCode, data);
	}
}
