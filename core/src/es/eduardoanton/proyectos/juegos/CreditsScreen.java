package es.eduardoanton.proyectos.juegos;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class CreditsScreen implements Screen {
	public SeeSawCircus game;
	private SpriteBatch batch;
	private OrthographicCamera cam;
	private Texture idea,art,music,sound,beta,code,end,infa,musiclogo1,musiclogo2,soundlogo1,soundlogo2,endlogo1,endlogo2;
	public static Texture botonhome;
	public BitmapFont marcador, informe;
	public InputProcessor iproc;
	
	CreditsScreen (SeeSawCircus game){
		this.game = game;
		cam = new OrthographicCamera();
		cam.setToOrtho(false, SeeSawCircus.screenwidth,SeeSawCircus.screenheight);
		cam.update();
		batch = new SpriteBatch();
		marcador =SeeSawCircus.asset.get("fuenteBerlinSansFBDemi.fnt", BitmapFont.class);
		informe =SeeSawCircus.asset.get("fuente.fnt", BitmapFont.class);
		batch.setProjectionMatrix(cam.combined);
		idea = SeeSawCircus.asset.get("creditsidea.png");
		art = SeeSawCircus.asset.get("creditsart.png");
		music = SeeSawCircus.asset.get("creditsmusic.png");
		sound = SeeSawCircus.asset.get("creditssound.png");
		beta = SeeSawCircus.asset.get("creditsbeta.png");
		code = SeeSawCircus.asset.get("creditscode.png");
		end = SeeSawCircus.asset.get("creditsend.png");
		infa = SeeSawCircus.asset.get("infatigables.png");
		soundlogo1 = SeeSawCircus.asset.get("logofreesound.png");
		soundlogo2 = SeeSawCircus.asset.get("logofreeSFX.png");
		musiclogo1 = SeeSawCircus.asset.get("logomusica1.png");
		musiclogo2 = SeeSawCircus.asset.get("logomusica2.png");
		endlogo1 = SeeSawCircus.asset.get("logolibgdx.png");
		endlogo2 = SeeSawCircus.asset.get("logoart.png");
		botonhome = SeeSawCircus.asset.get("botonhomepe.png");
		iproc = new InputProcesadorCredits(cam,this);
	}
	@Override
	public void render(float delta) {
		long i;
		update(delta);
		Gdx.gl.glClearColor(0.02f,0.22f ,0.22f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);	
		batch.begin();
		batch.draw(botonhome,930, cam.position.y + 200);
		batch.draw(idea,512 - idea.getWidth()/2,0);
		marcador.setScale(0.5f);
		marcador.draw(batch,"EDUARDO ANTON SANTA-MARIA",512 - (marcador.getBounds("EDUARDO ANTON SANTA-MARIA").width/2), -70);
		informe.setScale(0.7f);
		informe.setColor(Color.GRAY);
		informe.draw(batch,"Based on Atari 2600 videogame Circus Atari",512 - (informe.getBounds("Based on Atari 2600 videogame Seesaw Circus").width/2), -120);
		batch.draw(art,512 - art.getWidth()/2,-400);
		marcador.draw(batch,"EDUARDO ANTON SANTA-MARIA",512 - (marcador.getBounds("EDUARDO ANTON SANTA-MARIA").width/2), -470);
		marcador.draw(batch,"SONIA ANTON RIANO",512 - (marcador.getBounds("SONIA ANTON RIANO").width/2), -520);
		marcador.draw(batch,"LUCIA ANTON RIANO",512 - (marcador.getBounds("LUCIA ANTON RIANO").width/2), -570);
		batch.draw(music,512 - art.getWidth()/2,-880);
		marcador.draw(batch,"KEVIN MCLEAOD",512 - (marcador.getBounds("KEVIN MCLEAOD").width/2), -950);
		informe.draw(batch,"http://incompetech.com",512 - (informe.getBounds("http://incompetech.com").width/2), -1010);
		batch.draw(musiclogo1, 512 - musiclogo1.getWidth()/2, -1100);
		informe.setColor(Color.YELLOW);
		informe.draw(batch,"Monkeys Spinning Monkeys",512 - (informe.getBounds("Monkeys Spinning Monkeys").width/2), -1060 - 60);
		informe.draw(batch,"Plucky Daisy",512 - (informe.getBounds("Plucky Daisy").width/2), -1100 - 60);
		informe.draw(batch,"Look Busy",512 - (informe.getBounds("Look Busy").width/2), -1140 - 60);
		informe.draw(batch,"Five Card Shuffle",512 - (informe.getBounds("Five Card Shuffle").width/2), -1180 - 60);
		informe.draw(batch,"Friendly Day",512 - (informe.getBounds("Friendly Day").width/2), -1220 - 60);
		informe.draw(batch,"Hyperfun",512 - (informe.getBounds("Hyperfun").width/2), -1260 - 60);

		marcador.draw(batch,"MATTHEW PABLO",512 - (marcador.getBounds("MATTHEW PABLO").width/2), -1400);
		informe.setColor(Color.GRAY);
		informe.draw(batch,"http://www.matthewpablo.com",512 - (informe.getBounds("http://www.matthewpablo.com").width/2), -1460);
		batch.draw(musiclogo2, 512 - musiclogo2.getWidth()/2, -1620);
		informe.setColor(Color.YELLOW);
		informe.draw(batch,"Title Screen Music - Circus Dilemma",512 - (informe.getBounds("Title Screen Music - Circus Dilemma").width/2), -1650);
		
		batch.draw(sound, 512 - sound.getWidth()/2, -2000);
		marcador.draw(batch, "FREESOUND.ORG",512 - (marcador.getBounds("FREESOUND.ORG").width/2), -2050);
		informe.setColor(Color.GRAY);
		informe.draw(batch,"http://www.freesound.org",512 - (informe.getBounds("http://www.freesound.org").width/2), -2110);
		batch.draw(soundlogo1, 512 - soundlogo1.getWidth()/2,-2230);
		i = - 2180;
		informe.setColor(Color.YELLOW);
		informe.draw(batch,"Angel_chorus1 - Taira Komori",512 - (informe.getBounds("Angel_chorus1 - Taira Komori").width/2), i - 60);
		informe.draw(batch,"Milk jug Seal 01 - sandyrb",512 - (informe.getBounds("Milk jug Seal 01 - sandyrb").width/2), i - 60 - 40*1);
		informe.draw(batch,"Drum Roll with cymbals crash - Iwan Gabovitch",512 - (informe.getBounds("Drum Roll with cymbals crash - Iwan Gabovitch").width/2), i - 60  - 40*2);
		informe.draw(batch,"Crash - bone666138",512 - (informe.getBounds("Crash - bone666138").width/2), i - 60  - 40*3);
		informe.draw(batch,"Noob Battle Cry - PhilipRowland",512 - (informe.getBounds("Noob Battle Cry - PhilipRowland").width/2), i - 60  - 40*4);
		informe.draw(batch,"44 - y89312",512 - (informe.getBounds("44 - y89312").width/2), i - 60  - 40*5);
		informe.draw(batch,"8 bit hurt1 - Timgormly",512 - (informe.getBounds("8 bit hurt1 - Timgormly").width/2), i - 60  - 40*6);
		informe.draw(batch,"Cartoon Jump2 - elektroproleter",512 - (informe.getBounds("Cartoon Jump2 - elektroproleter").width/2), i - 60  - 40*7);
		informe.draw(batch,"Lion Roar - typos",512 - (informe.getBounds("Lion Roar - typos").width/2), i - 60  - 40*8);
		informe.draw(batch,"Slide Whistle Random Slow 1 - Joe DeShon (joedeshon)",512 - (informe.getBounds("Slide Whistle Random Slow 1 - Joe DeShon (joedeshon)").width/2), i - 60  - 40*9);
		informe.draw(batch,"Jarabe Tapatío Mariachi - Lenguaverde",512 - (informe.getBounds("Jarabe Tapatío Mariachi - Lenguaverde").width/2), i - 60  - 40*10);
		informe.draw(batch,"Xylophone for Cartoon 4 - Sergeeo",512 - (informe.getBounds("Xylophone for Cartoon 4 - Sergeeo").width/2), i - 60  - 40*11);
		informe.draw(batch,"Success - grunz",512 - (informe.getBounds("Success - grunz").width/2), i - 60  - 40*12);
		informe.draw(batch,"Neigh2 - acclivity",512 - (informe.getBounds("Neigh2 - acclivity").width/2), i - 60  - 40*13);	
		i = i - 60  - 40*14;
		marcador.draw(batch, "WWW.FREESFX.CO.UK",512 - (marcador.getBounds("WWW.FREESFX.CO.UK").width/2), i - 50);
		informe.setColor(Color.GRAY);
		informe.draw(batch,"http://www.freesfx.co.uk",512 - (informe.getBounds("http://www.freesfx.co.uk").width/2), i - 50 - 60);
		batch.draw(soundlogo2, 512 - soundlogo2.getWidth()/2,i - 50 - 220 );
		i = i - 50 - 220;
		informe.setColor(Color.YELLOW);
		informe.draw(batch,"Antique Cash Register Punching Single Key",512 - (informe.getBounds("Antique Cash Register Punching Single Key").width/2), i - 40*1);
		informe.draw(batch,"App, Game, Interactive Alert Tone 015",512 - (informe.getBounds("App, Game, Interactive Alert Tone 015").width/2), i - 40*2);
		informe.draw(batch,"boingsda",512 - (informe.getBounds("boingsda").width/2), i - 40*3);
		informe.draw(batch,"Bonus 1",512 - (informe.getBounds("Bonus 1").width/2), i - 40*4);
		informe.draw(batch,"Box Trash Impact 03",512 - (informe.getBounds("Box Trash Impact 03").width/2), i - 40*5);
		informe.draw(batch,"Cartoon Fast Whoosh - Swipe 001",512 - (informe.getBounds("Cartoon Fast Whoosh - Swipe 001").width/2), i - 40*6);
		informe.draw(batch,"Cat Meow (Human Voice) 3",512 - (informe.getBounds("Cat Meow (Human Voice) 3").width/2), i - 40*7);
		informe.draw(batch,"Comedy Bubble Pop 2",512 - (informe.getBounds("Comedy Bubble Pop 2").width/2), i - 40*8);
		informe.draw(batch,"Comedy Siren Whistle Great for slips and trips 2",512 - (informe.getBounds("Comedy Siren Whistle Great for slips and trips 2").width/2), i - 40*9);
		informe.draw(batch,"Comedy Trumpet Playing Sad Song Wah Wah wah Wah",512 - (informe.getBounds("Comedy Trumpet Playing Sad Song Wah Wah wah Wah").width/2), i - 40*10);
		informe.draw(batch,"Crash Vehicle Debris Glass 3 - Bingle, Fend",512 - (informe.getBounds("Crash Vehicle Debris Glass 3 - Bingle, Fend").width/2), i - 40*11);
		informe.draw(batch,"Dog Barking 5",512 - (informe.getBounds("Dog Barking 5").width/2), i - 40*12);
		informe.draw(batch,"Mutimedia tone signifies end",512 - (informe.getBounds("Mutimedia tone signifies end").width/2), i - 40*13);
		informe.draw(batch,"Small Crowd Saying Wow",512 - (informe.getBounds("Small Crowd Saying Wow").width/2), i - 40*14);
		informe.draw(batch,"Trampoline Bounce lite 1",512 - (informe.getBounds("Trampoline Bounce lite 1").width/2), i - 40*15);
		informe.draw(batch,"Animal Chimpanzee Chimp Screams",512 - (informe.getBounds("Animal Chimpanzee Chimp Screams").width/2), i - 40*16);
		informe.draw(batch,"Trampoline Bounce Lite 1",512 - (informe.getBounds("Trampoline Bounce Lite 1").width/2), i - 40*17);
		informe.draw(batch,"Wood Stick hit long soft",512 - (informe.getBounds("Wood Stick hit long soft").width/2), i - 40*18);
		informe.draw(batch,"Impact Rock on rubble 6",512 - (informe.getBounds("Impact Rock on rubble 6").width/2), i - 40*19);
		informe.draw(batch,"Impact Rock on rubble 3",512 - (informe.getBounds("Impact Rock on rubble 3").width/2), i - 40*20);
		i = i - 40*20 - 280;
		batch.draw(beta, 512 - beta.getWidth()/2, i);
		marcador.draw(batch, "PABLO ANTON SANTA-MARIA",512 - (marcador.getBounds("PABLO ANTON SANTA-MARIA").width/2), i - 50);
		marcador.draw(batch, "DIEGO CANCELA CARO",512 - (marcador.getBounds("DIEGO CANCELA CARO").width/2), i - 50*2);
		marcador.draw(batch, "EDUARDO ANTON SANTA-MARIA",512 - (marcador.getBounds("EDUARDO ANTON SANTA-MARIA").width/2), i - 50*3);
		marcador.draw(batch, "SONIA ANTON RIANO",512 - (marcador.getBounds("SONIA ANTON RIANO").width/2), i - 50*4);
		marcador.draw(batch, "LUCIA ANTON RIANO",512 - (marcador.getBounds("LUCIA ANTON RIANO").width/2), i - 50*5);
		i = i - 50*5 - 450;
		batch.draw(code, 512 - code.getWidth()/2, i);
		marcador.draw(batch, "EDUARDO ANTON SANTA-MARIA",512 - (marcador.getBounds("EDUARDO ANTON SANTA-MARIA").width/2), i - 50);
		i =  i - 50 - 380;
		batch.draw(end, 512 - end.getWidth()/2, i);
		marcador.draw(batch, "lIBGDX TEAM",512 - (marcador.getBounds("lIBGDX TEAM").width/2), i - 50);
		informe.setColor(Color.GRAY);
		informe.draw(batch,"http://libgdx.badlogicgames.com/",512 - (informe.getBounds("http://libgdx.badlogicgames.com/").width/2), i - 50 - 60);
		batch.draw(endlogo1,512 - endlogo1.getWidth()/2, i - 250);
		
		marcador.draw(batch, "2D GAME ART FOR PROGRAMMERS",512 - (marcador.getBounds("2D GAME ART FOR PROGRAMMERS").width/2), i - 330);
		informe.setColor(Color.GRAY);
		informe.draw(batch,"http://2dgameartforprogrammers.blogspot.com.es/",512 - (informe.getBounds("http://2dgameartforprogrammers.blogspot.com.es/").width/2), i - 330 - 60);
		batch.draw(endlogo2,512 - endlogo2.getWidth()/2, i - 590);
		marcador.draw(batch, "LOS INFATIGABLES",512 - (marcador.getBounds("LOS INFATIGABLES").width/2), i - 590 - 350);
		batch.draw(infa, 512 - infa.getWidth()/2, i - 900);
		informe.setColor(Color.GRAY);
		informe.draw(batch,"Por su gran acogimiento y apoyo. Gracias",512 - (informe.getBounds("Por su gran acogimiento y apoyo. Gracias").width/2), i - 590 - 350 -50);
		
		marcador.draw(batch, "AND YOU / Y A USTED",512 - (marcador.getBounds("AND YOU / Y A USTED").width/2),i - 590 - 350 -50 - 70 - 300);
		informe.setColor(Color.GRAY);
		informe.draw(batch,"For playing this game. Thank you.",512 - (informe.getBounds("For playing this game. Thank you.").width/2), i - 590 - 350 -50 - 130 -300);
				
		batch.end();
		
	}

	public void update (float delta){
		cam.position.y-= delta*80;
		if (cam.position.y < - 6720){
			cam.position.y = - 6720;
		}
		cam.update();
		batch.setProjectionMatrix(cam.combined);
	}
	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		botonhome =SeeSawCircus.asset.get("botonhomepe.png");
		cam.position.y = 300;
		cam.update();
		batch.setProjectionMatrix(cam.combined);
		Gdx.input.setInputProcessor(iproc);
		Musica.playRandom();
	}

	@Override
	public void hide() {
		Musica.stop();
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		batch.dispose();
	}

}
