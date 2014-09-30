package es.eduardoanton.proyectos.juegos;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class SeeSawCircus extends Game {

	public static AssetManager asset;
	public final static float screenwidth = 1024f;
	public final static float screenheight = 600f;
	public static Screen mainscreen,ingamescreen,gameoverscreen,loadingscreen;
	public static GameWorld gamew;
	public static Preferences prefs;
	
	
	@Override
	public void create() {
		loadassets();
		prefs = Gdx.app.getPreferences("SEESAWCIRCUS");
		prefs.putLong("record", 1000);
		prefs.flush();
		loadingscreen = new LoadingScreen(this);
		this.setScreen(loadingscreen);
	}
	
	public  void mainscreen(){
		gamew = new GameWorld(this);
		mainscreen = new MainScreen(this);
		ingamescreen = new IngameScreen( this );
		gameoverscreen = new GameOverScreen(this);
		this.setScreen(mainscreen);
	}
	
	private void loadassets(){
		asset = new AssetManager();
		asset.load("trampolin.png", Texture.class);
		asset.load("fondoinicio.png", Texture.class);
		asset.load("nube.png", Texture.class);
		asset.load("gato.png", Texture.class);
		asset.load("botoninicio.png", Texture.class);
		asset.load("botoniniciop.png", Texture.class);
		asset.load("salir.png", Texture.class);
		asset.load("salirp.png", Texture.class);
		asset.load("circo.png", Texture.class);
		asset.load("letrero.png", Texture.class);
		asset.load("letreroe.png", Texture.class);
		asset.load("fondomain.png", Texture.class);
		asset.load("trampolin2.png", Texture.class);
		asset.load("payasoinicio1.png", Texture.class);
		asset.load("payasoinicio2.png", Texture.class);
		asset.load("globosinicio.png", Texture.class);
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
		asset.load("leon.png", Texture.class);
		asset.load("oso.png", Texture.class);
		asset.load("caballo.png", Texture.class);
		asset.load("perro.png", Texture.class);
		asset.load("mono.png", Texture.class);
		asset.load("foca.png", Texture.class);
		asset.load("record.png", Texture.class);
		asset.load("recordp.png", Texture.class);
		asset.load("elefante.png", Texture.class);
		asset.load("explosion1.png", Texture.class);
		asset.load("explosion2.png", Texture.class);
		asset.load("explosion3.png", Texture.class);
		asset.load("explosion4.png", Texture.class);
		asset.load("musica.png", Texture.class);
		asset.load("trampolin_sombra.png", Texture.class);
		asset.load("paraguas.png", Texture.class);
		asset.load("paraguasp.png", Texture.class);
		asset.load("payaso_sombra.png", Texture.class);
		for ( String musica : Musica.lista){
			asset.load(musica, Music.class);
		}
		asset.load("Circus Dilemma.ogg", Music.class);
		asset.load("fuente.fnt", BitmapFont.class);
		asset.load("app_game_interactive_alert_tone_015.mp3", Sound.class);
		//asset.load("comedy_male_yelling_yee_ha.mp3", Sound.class);
		asset.load("record.ogg", Music.class);
		asset.load("fuenteBerlinSansFBDemi.fnt", BitmapFont.class);
		asset.load("cat_meow_human_voice_3.mp3", Sound.class);
		asset.load("boingsda.mp3",Sound.class);
		asset.load("box_trash_impact_03.mp3", Sound.class);
	}
	
}
		
