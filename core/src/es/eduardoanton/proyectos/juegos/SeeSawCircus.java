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
	public static Screen mainscreen,ingamescreen,gameoverscreen,loadingscreen,creditsscreen;
	public static GameWorld gamew;
	public static Preferences prefs;
	
	
	@Override
	public void create() {
		loadassets();
		prefs = Gdx.app.getPreferences("SEESAWCIRCUS");
		//prefs.putLong("record", 1000);
		//prefs.flush();
		loadingscreen = new LoadingScreen(this);
		this.setScreen(loadingscreen);
	}
	
	public  void mainscreen(){
		gamew = new GameWorld(this);
		mainscreen = new MainScreen(this);
		ingamescreen = new IngameScreen( this );
		gameoverscreen = new GameOverScreen(this);
		creditsscreen = new CreditsScreen(this);
		this.setScreen(mainscreen);
	}
	
	private void loadassets(){
		asset = new AssetManager();
		asset.load("creditsidea.png", Texture.class);
		asset.load("creditsart.png", Texture.class);
		asset.load("creditsmusic.png", Texture.class);
		asset.load("logomusica1.png", Texture.class);
		asset.load("logomusica2.png", Texture.class);
		asset.load("trampolin.png", Texture.class);
		asset.load("nube.png", Texture.class);
		asset.load("gato.png", Texture.class);
		asset.load("botoninicio.png", Texture.class);
		asset.load("botoniniciop.png", Texture.class);
		asset.load("botoncreditos.png", Texture.class);
		asset.load("botoncreditosp.png", Texture.class);
		asset.load("salir.png", Texture.class);
		asset.load("salirp.png", Texture.class);
		asset.load("circo.png", Texture.class);
		asset.load("letrero.png", Texture.class);
		asset.load("letreroe.png", Texture.class);
		asset.load("fondomain.png", Texture.class);
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
		asset.load("regalo.png", Texture.class);
		asset.load("regalo2.png", Texture.class);
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
		asset.load("premiospeed.png", Texture.class);
		asset.load("premiocorazon.png", Texture.class);
		asset.load("premioparaguas.png", Texture.class);
		asset.load("premiogordo.png", Texture.class);
		asset.load("premiohueso.png", Texture.class);
		asset.load("premiomuelle.png", Texture.class);
		asset.load("tesoro.png", Texture.class);
		asset.load("musica.png", Texture.class);
		asset.load("trampolin_sombra.png", Texture.class);
		asset.load("filascompletas.png", Texture.class);
		asset.load("finalscore.png", Texture.class);
		asset.load("paraguas.png", Texture.class);
		asset.load("botonhome.png", Texture.class);
		asset.load("botonreload.png", Texture.class);
		asset.load("botonhomep.png", Texture.class);
		asset.load("botonreloadp.png", Texture.class);
		asset.load("botonninoson.png", Texture.class);
		asset.load("botonninosoff.png", Texture.class);
		asset.load("paraguasp.png", Texture.class);
		asset.load("payaso_sombra.png", Texture.class);
		asset.load("trapecio.png", Texture.class);
		asset.load("red.png", Texture.class);
		asset.load("muelle.png", Texture.class);
		asset.load("muellep.png", Texture.class);
		asset.load("panelvidas.png", Texture.class);
		asset.load("corazonp.png", Texture.class);
		asset.load("paraguaspp.png", Texture.class);
		asset.load("paused.png", Texture.class);
		asset.load("botonreloadpe.png", Texture.class);
		asset.load("botonreloadpep.png", Texture.class);
		asset.load("botonhomepe.png", Texture.class);
		asset.load("botonhomepep.png", Texture.class);
		for ( String musica : Musica.lista){
			asset.load(musica, Music.class);
		}
		asset.load("Circus Dilemma.ogg", Music.class);
		asset.load("fuente.fnt", BitmapFont.class);
		asset.load("app_game_interactive_alert_tone_015.mp3", Sound.class);
		asset.load("record.ogg", Music.class);
		asset.load("fuenteBerlinSansFBDemi.fnt", BitmapFont.class);
		asset.load("cat_meow_human_voice_3.mp3", Sound.class);
		asset.load("182442__qubodup__drum-roll-with-cymbals-crash.ogg", Sound.class);
		asset.load("bonus_1.mp3", Sound.class);
		asset.load("boingsda.mp3",Sound.class);
		asset.load("box_trash_impact_03.mp3", Sound.class);
		asset.load("comedy_trumpet_playing_sad_song_wah_wah_wah_wah.mp3", Sound.class);
		asset.load("jump.ogg", Sound.class);
		asset.load("crash.mp3", Sound.class);
		asset.load("angel.mp3", Sound.class);
		asset.load("hurt.ogg", Sound.class);
		asset.load("cry.ogg", Sound.class);
		asset.load("mareo.ogg", Sound.class);
		asset.load("clan.wav", Sound.class);
		asset.load("succes.wav", Sound.class);
		asset.load("small_crowd_saying_wow.mp3", Sound.class);
		asset.load("comedy_siren_whistle_great_for_slips_and_trips_002.mp3", Sound.class);
		asset.load("multimedia_tone_signifies_end.mp3", Sound.class);
		asset.load("antique_cash_register_punching_single_key.mp3", Sound.class);
		asset.load("comedy_bubble_pop_002.mp3", Sound.class);
		asset.load("horse.ogg", Sound.class);
		asset.load("lionroar.ogg", Sound.class);
		asset.load("bear.ogg", Sound.class);
		asset.load("elephant.ogg", Sound.class);
		asset.load("dog_barking_05.mp3", Sound.class);
		asset.load("animal_chimpanzee_chimp_screams.ogg", Sound.class);
		asset.load("41384__sandyrb__milk-jug-seal-01.ogg", Sound.class);
		asset.load("trampoline_bounce_lite_01.ogg", Sound.class);
		asset.load("cartoon_fast_whoosh_good_for_karate_chop_other_fast_movement_or_swipe_001.mp3", Sound.class);
	}
}
		
