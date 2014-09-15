package es.eduardoanton.proyectos.juegos;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;

public class SeeSawCircus extends Game {

	private AssetManager asset;
	
	
	@Override
	public void create() {
		loadassets();
		IngameScreen ingamescreen = new IngameScreen( this );
		this.setScreen( ingamescreen);
		
	}
	
	private void loadassets(){
		asset = new AssetManager();
		asset.load("trampolin.png", Texture.class);	
		asset.load("trampolin2.png", Texture.class);
		asset.load("payaso22.png", Texture.class);
		asset.load("payaso33.png", Texture.class);
		asset.load("payaso2.png", Texture.class);
		asset.load("payaso3.png", Texture.class);
		asset.load("redondo.png", Texture.class);
		asset.load("fondo.png", Texture.class);
		asset.finishLoading();
	}
	
	public AssetManager getAsset(){
		return this.asset;
	}
}
		
