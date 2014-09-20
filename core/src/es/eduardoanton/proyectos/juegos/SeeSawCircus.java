package es.eduardoanton.proyectos.juegos;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

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
		asset.load("p1c1.png", Texture.class);
		asset.load("p1c2.png", Texture.class);
		asset.load("p2c1.png", Texture.class);
		asset.load("p2c2.png", Texture.class);
		asset.load("p1e1.png", Texture.class);
		asset.load("p1e2.png", Texture.class);
		asset.load("p1e3.png", Texture.class);
		asset.load("p1e4.png", Texture.class);
		asset.load("p2e1.png", Texture.class);
		asset.load("p2e2.png", Texture.class);
		asset.load("p2e3.png", Texture.class);
		asset.load("p2e4.png", Texture.class);
		asset.load("payasodeath.png", Texture.class);
		asset.load("lapida.png", Texture.class);
		asset.load("redondo.png", Texture.class);
		asset.load("fondo.png", Texture.class);
		asset.load("corazon.png", Texture.class);
		asset.load("carameloa.png", Texture.class);
		asset.load("caramelor.png", Texture.class);
		asset.load("carameloz.png", Texture.class);
		asset.load("caramelov.png", Texture.class);
		asset.load("explosion1.png", Texture.class);
		asset.load("explosion2.png", Texture.class);
		asset.load("explosion3.png", Texture.class);
		asset.load("explosion4.png", Texture.class);
		asset.load("fuente.fnt", BitmapFont.class);
		asset.finishLoading();
	}
	
	public AssetManager getAsset(){
		return this.asset;
	}
}
		
