package es.eduardoanton.proyectos.juegos;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class SeeSawCircus extends Game {

	private static AssetManager asset;
	public final static float screenwidth = 1024f;
	public final static float screenheight = 600f;
	
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
		asset.load("payaso22bajando.png", Texture.class);
		asset.load("payaso33bajando.png", Texture.class);
		asset.load("payaso22cruz.png", Texture.class);
		asset.load("payaso33cruz.png", Texture.class);
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
		asset.load("monedalucia.png", Texture.class);
		asset.load("monedasonia.png", Texture.class);
		asset.load("monedaluciap.png", Texture.class);
		asset.load("monedasoniap.png", Texture.class);
		asset.load("billeteedu.png", Texture.class);
		asset.load("globoa.png", Texture.class);
		asset.load("globoz.png", Texture.class);
		asset.load("globor.png", Texture.class);
		asset.load("globov.png", Texture.class);
		asset.load("explosion1.png", Texture.class);
		asset.load("explosion2.png", Texture.class);
		asset.load("explosion3.png", Texture.class);
		asset.load("explosion4.png", Texture.class);
		asset.load("musica.png", Texture.class);
		asset.load("trampolin_sombra.png", Texture.class);
		asset.load("payaso_sombra.png", Texture.class);
		for ( String musica : Musica.lista){
			asset.load(musica, Music.class);
		}
		asset.load("fuente.fnt", BitmapFont.class);
		asset.load("fuenteBerlinSansFBDemi.fnt", BitmapFont.class);
		asset.finishLoading();
	}
	
	public static AssetManager getAsset(){
		return asset;
	}
}
		
