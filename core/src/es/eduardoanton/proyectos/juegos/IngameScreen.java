package es.eduardoanton.proyectos.juegos;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;


import es.eduardoanton.proyectos.juegos.GameWorld.GameState;
import es.eduardoanton.proyectos.juegos.Trampolin.TrampolinState;

public class IngameScreen implements Screen{
	private SpriteBatch batch;
	private OrthographicCamera cam;
	private Texture trampolintexturel,trampolinsombra,payasosombra,trapecio,red,panelvidas,corazonp,paraguaspp,muelle,muellep,tesoro;
	private Texture payaso,payaso2,fondo,payasodeath,lapida,corazon,musica,payasob,payaso2b,payasoc,payaso2c,paused;
	public static Texture botoninicio,botoniniciop,botonhomepe,botonhomepep,botonreloadpe,botonreloadpep;
	private Texture corona,paraguas,premiospeed,premiocorazon,premioparaguas,premiogordo,premiohueso,premiomuelle;
	private TextureRegion trampolintexturer,p1llorando[],p2llorando[],p1estrellas[],p2estrellas[],explosion[],premio[];
	private Sprite redondo;
	public Animation p1llorandoA,p2llorandoA,p1estrellasA,p2estrellasA,explosionA;
	public BitmapFont marcador,gameover,letrero,informe;
	public static float statetime = 0f;
	public Texture filaitems[];
	private InputProcessor iproc;
	public Color tmp;
	public 	String mensaje[] = {"NUEVA VIDA / NEW LIFE", "NUEVO PARAGUAS / NEW UMBRELLA", "+1000 PUNTOS / POINTS", "+5000 PUNTOS / POINTS","VELOCIDAD 1.5x SPEED", "NUEVO MUELLE / NEW SPRING"
			,"+100 PUNTOS / POINTS", "+200 PUNTOS / POINTS", "+400 PUNTOS / POINTS", "+500 PUNTOS /POINTS"};
	private GameWorld gamew;
	private int ref = 0;
	
	public IngameScreen( SeeSawCircus gam){
		cam = new OrthographicCamera();
		cam.setToOrtho(false, 1024,600);
		cam.update();	
		batch = new SpriteBatch();
		batch.setProjectionMatrix(cam.combined);	
		trampolintexturel = SeeSawCircus.asset.get("trampolin.png", Texture.class );
		trampolinsombra = SeeSawCircus.asset.get("trampolin_sombra.png", Texture.class );
		payasosombra = SeeSawCircus.asset.get("payaso_sombra.png", Texture.class );
		trampolintexturer = new TextureRegion(trampolintexturel);
		trampolintexturer.flip(true,false);
		payaso = SeeSawCircus.asset.get("payaso22.png", Texture.class);
		payaso2 = SeeSawCircus.asset.get("payaso33.png", Texture.class);
		payasob = SeeSawCircus.asset.get("payaso22bajando.png", Texture.class);
		payaso2b = SeeSawCircus.asset.get("payaso33bajando.png", Texture.class);
		payasoc = SeeSawCircus.asset.get("payaso22cruz.png", Texture.class);
		payaso2c = SeeSawCircus.asset.get("payaso33cruz.png", Texture.class);
		payasodeath =SeeSawCircus.asset.get("payasodeath.png", Texture.class);
		lapida =SeeSawCircus.asset.get("lapida.png", Texture.class);
		corazon =SeeSawCircus.asset.get("corazon.png", Texture.class);
		premiospeed  =SeeSawCircus.asset.get("premiospeed.png", Texture.class);
		premiocorazon  =SeeSawCircus.asset.get("premiocorazon.png", Texture.class);
		premioparaguas  =SeeSawCircus.asset.get("premioparaguas.png", Texture.class);
		premiogordo = SeeSawCircus.asset.get("premiogordo.png", Texture.class);
		premiohueso = SeeSawCircus.asset.get("premiohueso.png", Texture.class);
		premiomuelle = SeeSawCircus.asset.get("premiomuelle.png", Texture.class);
		tesoro = SeeSawCircus.asset.get("tesoro.png", Texture.class);
		paused = SeeSawCircus.asset.get("paused.png", Texture.class);
		premio = new TextureRegion[10];
		premio[0] = new TextureRegion(premiocorazon);
		premio[1] = new TextureRegion(premioparaguas);
		premio[2] = new TextureRegion(premiogordo);
		premio[3] = new TextureRegion(premiohueso);
		premio[4] = new TextureRegion(premiospeed);
		premio[5] = new TextureRegion(premiomuelle);
		premio[6] = new TextureRegion(tesoro);
		premio[7] = premio[6];
		premio[8] = premio[6];
		premio[9] = premio[6];
		musica =SeeSawCircus.asset.get("musica.png", Texture.class);
		corona = SeeSawCircus.asset.get("record.png", Texture.class);
		paraguas = SeeSawCircus.asset.get("paraguas.png", Texture.class);
		trapecio = SeeSawCircus.asset.get("trapecio.png", Texture.class);
		red = SeeSawCircus.asset.get("red.png", Texture.class);
		muelle = SeeSawCircus.asset.get("muelle.png", Texture.class);
		panelvidas = SeeSawCircus.asset.get("panelvidas.png", Texture.class);
		corazonp = SeeSawCircus.asset.get("corazonp.png", Texture.class);
		paraguaspp = SeeSawCircus.asset.get("paraguaspp.png", Texture.class);
		muellep = SeeSawCircus.asset.get("muellep.png", Texture.class);
		botoninicio = SeeSawCircus.asset.get("botoninicio.png", Texture.class);
		botoniniciop = SeeSawCircus.asset.get("botoniniciop.png", Texture.class);
		botonreloadpe = SeeSawCircus.asset.get("salir.png", Texture.class);
		botonreloadpep = SeeSawCircus.asset.get("salirp.png", Texture.class);
		botonhomepe = SeeSawCircus.asset.get("botonhomepe.png", Texture.class);
		botonhomepep = SeeSawCircus.asset.get("botonhomepep.png", Texture.class);
		explosion = new TextureRegion[4];
		explosion[0] = new TextureRegion(SeeSawCircus.asset.get("explosion1.png", Texture.class));
		explosion[1] = new TextureRegion(SeeSawCircus.asset.get("explosion2.png", Texture.class));
		explosion[2] = new TextureRegion(SeeSawCircus.asset.get("explosion3.png", Texture.class));
		explosion[3] = new TextureRegion(SeeSawCircus.asset.get("explosion4.png", Texture.class));
		explosionA = new Animation(0.2f,explosion);
		filaitems = new Texture[26];
		filaitems[0] = SeeSawCircus.asset.get("carameloa.png", Texture.class);
		filaitems[1] = SeeSawCircus.asset.get("caramelov.png", Texture.class);
		filaitems[2] = SeeSawCircus.asset.get("caramelor.png", Texture.class);
		filaitems[3] = SeeSawCircus.asset.get("carameloz.png", Texture.class);
		filaitems[4] = SeeSawCircus.asset.get("globoa.png", Texture.class);
		filaitems[5] = SeeSawCircus.asset.get("globoz.png", Texture.class);
		filaitems[6] = SeeSawCircus.asset.get("globov.png", Texture.class);
		filaitems[7] = SeeSawCircus.asset.get("globor.png", Texture.class);
		filaitems[8] = SeeSawCircus.asset.get("leon.png", Texture.class);
		filaitems[9] = SeeSawCircus.asset.get("caballo.png", Texture.class);
		filaitems[10] = SeeSawCircus.asset.get("oso.png", Texture.class);
		filaitems[11] = SeeSawCircus.asset.get("elefante.png", Texture.class);
		filaitems[12] = SeeSawCircus.asset.get("mono.png", Texture.class);
		filaitems[13] = SeeSawCircus.asset.get("foca.png", Texture.class);
		filaitems[14] = SeeSawCircus.asset.get("perro.png", Texture.class);
		filaitems[15] = SeeSawCircus.asset.get("monedaluciap.png", Texture.class);
		filaitems[16] = SeeSawCircus.asset.get("monedasoniap.png", Texture.class);
		filaitems[17] = SeeSawCircus.asset.get("monedalucia.png", Texture.class);
		filaitems[18] = SeeSawCircus.asset.get("monedasonia.png", Texture.class);
		filaitems[19] = SeeSawCircus.asset.get("billeteedu.png", Texture.class);
		filaitems[20] = SeeSawCircus.asset.get("regalo.png", Texture.class);
		filaitems[21] = SeeSawCircus.asset.get("regalo2.png", Texture.class);
		filaitems[22] = SeeSawCircus.asset.get("piedra.png", Texture.class);
		filaitems[23] = SeeSawCircus.asset.get("piedra2.png", Texture.class);
		filaitems[24] = SeeSawCircus.asset.get("piedra3.png", Texture.class);
		filaitems[25] = SeeSawCircus.asset.get("piedra4.png", Texture.class);
		p1llorando = new TextureRegion[2];
		p1llorando[0] =  new TextureRegion(SeeSawCircus.asset.get("p1c1.png", Texture.class));
		p1llorando[1] =  new TextureRegion(SeeSawCircus.asset.get("p1c2.png", Texture.class));
		p1llorandoA = new Animation(0.1f,p1llorando);
		p1llorandoA.setPlayMode(Animation.PlayMode.LOOP);
		p2llorando = new TextureRegion[2];
		p2llorando[0] =  new TextureRegion(SeeSawCircus.asset.get("p2c1.png", Texture.class));
		p2llorando[1] =  new TextureRegion(SeeSawCircus.asset.get("p2c2.png", Texture.class));
		p2llorandoA = new Animation(0.1f,p2llorando);
		p2llorandoA.setPlayMode(Animation.PlayMode.LOOP);
		p1estrellas = new TextureRegion[4];
		p1estrellas[0] =  new TextureRegion(SeeSawCircus.asset.get("p1e1.png", Texture.class));
		p1estrellas[1] =  new TextureRegion(SeeSawCircus.asset.get("p1e2.png", Texture.class));
		p1estrellas[2] =  new TextureRegion(SeeSawCircus.asset.get("p1e3.png", Texture.class));
		p1estrellas[3] =  new TextureRegion(SeeSawCircus.asset.get("p1e4.png", Texture.class));
		p1estrellasA = new Animation(0.24f,p1estrellas);
		p1estrellasA.setPlayMode(Animation.PlayMode.LOOP);
		p2estrellas = new TextureRegion[4];
		p2estrellas[0] =  new TextureRegion(SeeSawCircus.asset.get("p2e1.png", Texture.class));
		p2estrellas[1] =  new TextureRegion(SeeSawCircus.asset.get("p2e2.png", Texture.class));
		p2estrellas[2] =  new TextureRegion(SeeSawCircus.asset.get("p2e3.png", Texture.class));
		p2estrellas[3] =  new TextureRegion(SeeSawCircus.asset.get("p2e4.png", Texture.class));
		p2estrellasA = new Animation(0.24f,p2estrellas);
		p2estrellasA.setPlayMode(Animation.PlayMode.LOOP);		
		fondo = SeeSawCircus.asset.get("fondo.png", Texture.class);
		redondo =  new Sprite( (Texture) SeeSawCircus.asset.get("redondo.png"));
		gamew = SeeSawCircus.gamew;
		iproc = new InputProcesador(cam,gamew);
		marcador =SeeSawCircus.asset.get("fuenteBerlinSansFBDemi.fnt", BitmapFont.class);
		informe =SeeSawCircus.asset.get("fuente.fnt", BitmapFont.class);	
		informe.setColor(Color.YELLOW);
		letrero = new BitmapFont();
		letrero.setColor(Color.GRAY);
	}
	
	@Override
	public void render(float delta) {
		gamew.update(delta);
		Gdx.gl.glClearColor(0.02f,0.22f ,0.22f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);	
		
		batch.begin();
		if (gamew.pausedgame){
			tmp = batch.getColor();
			tmp.a = 0.4f;
			batch.setColor(tmp);
		}
		if (gamew.gamestate == GameWorld.GameState.GAMEOVER){
			tmp = batch.getColor();
			tmp.a = 0.4f;
			batch.setColor(tmp);
		}
		batch.draw(fondo,0,0);
		if ( gamew.modechildren){
			batch.draw(red,0,-20);
		}
		//Dibujamos las filas
		for (int j=0;j<4;j++){
			for (int i=0;i<10;i++){
				if (gamew.arrayFilaObjetivos[j].elementos[i] != -1 ){
					batch.draw(filaitems[(gamew.arrayFilaObjetivos[j].elementos[i])], gamew.arrayFilaObjetivos[j].posicion.x + (filaitems[1].getWidth() + 20)*i,gamew.arrayFilaObjetivos[j].posicion.y);
				}
			}
		}
		//Dibujamos cartel y corona si hay record
		if ( gamew.isrecord){
			batch.draw(corona, SeeSawCircus.screenwidth - corona.getWidth() -10, 10);
			if ( gamew.time < 2){
				gamew.time+=delta;
				informe.setColor(Color.YELLOW);
				informe.draw(batch,"NUEVO RECORD / NEW RECORD", 512 - (informe.getBounds("NUEVO RECORD / NEW RECORD").width/2), 450);
			}
		}
		// Dibujamos puntuación
		marcador.setScale(1.0f);
		marcador.draw(batch,String.format("%d", gamew.scoreboard), 1000 - (marcador.getBounds("" + gamew.scoreboard).width), 600);
		//Dibujamos musica por
		if (gamew.modechildren){
			batch.draw(musica, 0, 45);
			letrero.draw(batch, " " + Musica.getName() , 25, 50);
		}else{
			batch.draw(musica, 0, 0);
			letrero.draw(batch, " " + Musica.getName() , 25, 20);
		}
		ref=0;
		batch.draw(panelvidas, 512 - panelvidas.getWidth()/2, 0 + ref);
		for ( int i=1;i<=gamew.vidas;i++){
			batch.draw(corazonp, (512 - panelvidas.getWidth()/2) - 20 + (corazon.getWidth()- 7)*i,3 + ref);
		}
		if ( gamew.modechildren){
			for ( int i=1;i<=gamew.paraguasc;i++){
				batch.draw(paraguaspp, 490 + 30*i,5 + ref);
			}
		}else{
			for ( int i=1;i<=gamew.muellec;i++){
				batch.draw(muellep, 490 + 30*i,5 + ref);
			}
		}
		if (gamew.gamestate == GameWorld.GameState.GAMEOVER){
			marcador.setColor(Color.YELLOW);
			marcador.draw(batch, "GAME OVER", 200, 350);
		}
		if (gamew.payaso1.state == Payaso.PayasoState.MESSDEATH){
			renderCrashDeath(gamew.payaso1, gamew.payaso2,delta);
		}
		if (gamew.payaso2.state == Payaso.PayasoState.MESSDEATH){
			renderCrashDeath(gamew.payaso2, gamew.payaso1,delta);
		}
		if (gamew.payaso1.state == Payaso.PayasoState.MESSCRASH){
			statetime +=delta;
			renderCryingDeath(gamew.payaso1, gamew.payaso2,delta);
		}
		if (gamew.payaso2.state == Payaso.PayasoState.MESSCRASH){
			statetime +=delta;
			renderCryingDeath(gamew.payaso2, gamew.payaso1,delta);
		}
		if (gamew.payaso1.state != Payaso.PayasoState.MESSDEATH && gamew.payaso2.state != Payaso.PayasoState.MESSDEATH 
			&&	gamew.payaso1.state != Payaso.PayasoState.MESSCRASH && gamew.payaso2.state != Payaso.PayasoState.MESSCRASH
				){
			if (gamew.payaso1.velocidad.y  <= 300f && gamew.payaso1.velocidad.y >= -300f && gamew.payaso1.state == Payaso.PayasoState.FLYING ){
				batch.draw(payasoc, gamew.payaso1.posicion.x,gamew.payaso1.posicion.y);
		    }else if (gamew.payaso1.velocidad.y >= 0f || gamew.payaso1.state != Payaso.PayasoState.FLYING ){
				batch.draw(payaso, gamew.payaso1.posicion.x,gamew.payaso1.posicion.y);
			}else{
				batch.draw(payasob, gamew.payaso1.posicion.x,gamew.payaso1.posicion.y);
			}
			if (gamew.payaso2.velocidad.y  <= 300f && gamew.payaso2.velocidad.y >= -300f && gamew.payaso2.state == Payaso.PayasoState.FLYING ){
				batch.draw(payaso2c, gamew.payaso2.posicion.x,gamew.payaso2.posicion.y);
			}else if (gamew.payaso2.velocidad.y >= 0f  || gamew.payaso2.state != Payaso.PayasoState.FLYING){
				batch.draw(payaso2, gamew.payaso2.posicion.x,gamew.payaso2.posicion.y);
			}else{
				batch.draw(payaso2b, gamew.payaso2.posicion.x,gamew.payaso2.posicion.y);
			}
		
			if ( gamew.trampolin.view == TrampolinState.RIGHT){
				batch.draw(payasosombra, gamew.payaso2.posicion.x,30);
				batch.draw(trampolintexturer,gamew.trampolin.posicion.x,gamew.trampolin.posicion.y);
				batch.draw(trampolinsombra, gamew.trampolin.posicion.x, 30);
			}else{
				batch.draw(payasosombra, gamew.payaso1.posicion.x,30);
				batch.draw(trampolintexturel,gamew.trampolin.posicion.x,gamew.trampolin.posicion.y);
				batch.draw(trampolinsombra, gamew.trampolin.posicion.x, 30);
			}
			if ( gamew.paraguasc > 0){
				batch.draw(paraguas,gamew.paraguas.x,gamew.paraguas.y);
			}
			batch.draw(paraguas,gamew.paraguasfalling.x,gamew.paraguasfalling.y);
			redondo.setX(gamew.trampolin.posicion.x + 62);
			redondo.setY(gamew.trampolin.posicion.y);
			redondo.rotate(gamew.trampolin.rotacion * delta);
			if (!gamew.pausedgame){
				redondo.draw(batch);
			}
			batch.draw(trapecio, gamew.trapecio.x,gamew.trapecio.y);
			batch.draw(muelle, gamew.muelle.x,gamew.muelle.y);
			batch.draw(muelle,gamew.muellefalling.x,gamew.muellefalling.y);
			if ( gamew.ispremio ){
				if ( gamew.timeregalo < 4f && gamew.gamestate == GameState.RUNNING){
					gamew.timeregalo+=delta;
					tmp = batch.getColor();
					tmp.a = 0.65f;
					batch.setColor(tmp);
					batch.draw(premio[gamew.regalo], (SeeSawCircus.screenwidth/2) - (premio[gamew.regalo].getRegionWidth()/2), 200);
					informe.setColor(Color.YELLOW);
					informe.draw(batch,mensaje[gamew.regalo], 512 - (informe.getBounds(mensaje[gamew.regalo]).width/2), 400);
					tmp.a = 1f;
					batch.setColor(tmp);
				}else{
					gamew.ispremio = false;
				}
			}	
		}
		if (gamew.pausedgame){
			tmp = batch.getColor();
			tmp.a = 1f;
			batch.setColor(tmp);
			batch.draw(paused,(SeeSawCircus.screenwidth/2) - (paused.getWidth()/2),100);
			batch.draw(botoninicio,(SeeSawCircus.screenwidth/2) - (botoninicio.getWidth()/2),150);
			batch.draw(botonhomepe,250,350);
			batch.draw(botonreloadpe,700,350);
		}
		batch.end();
	}


	public void renderCrashDeath(Payaso payasomuerto, Payaso payasovivo,float delta){
		batch.draw(payasodeath, payasomuerto.posicion.x, payasomuerto.posicion.y);
		batch.draw(lapida, payasomuerto.posicion.x - 20, 20);
		if (payasovivo.PayasoID == 1){
			batch.draw(payaso, payasovivo.posicion.x,payasovivo.posicion.y);
		}else{
			batch.draw(payaso2, payasovivo.posicion.x,payasovivo.posicion.y);
		}
		if ( gamew.trampolin.view == TrampolinState.RIGHT){
			batch.draw(trampolintexturer,gamew.trampolin.posicion.x,gamew.trampolin.posicion.y);
			batch.draw(trampolinsombra, gamew.trampolin.posicion.x, 30);
		}else{
			batch.draw(trampolintexturel,gamew.trampolin.posicion.x,gamew.trampolin.posicion.y);
			batch.draw(trampolinsombra, gamew.trampolin.posicion.x, 30);
		}
		redondo.setX(gamew.trampolin.posicion.x + 62);
		redondo.setY(gamew.trampolin.posicion.y);
		redondo.rotate(gamew.trampolin.rotacion * delta);
		redondo.draw(batch);
	}
	
	public void renderCryingDeath(Payaso payasomuerto, Payaso payasovivo,float delta){
		
		if (payasovivo.PayasoID == 1){
			batch.draw(p2llorandoA.getKeyFrame(statetime), payasomuerto.posicion.x, payasomuerto.posicion.y);
			batch.draw(p1estrellasA.getKeyFrame(statetime), payasovivo.posicion.x,payasovivo.posicion.y);
		}else{
			batch.draw(p1llorandoA.getKeyFrame(statetime), payasomuerto.posicion.x, payasomuerto.posicion.y);
			batch.draw(p2estrellasA.getKeyFrame(statetime), payasovivo.posicion.x,payasovivo.posicion.y);
		}
		if ( gamew.trampolin.view == TrampolinState.RIGHT){
			batch.draw(trampolintexturer,gamew.trampolin.posicion.x,gamew.trampolin.posicion.y);
			batch.draw(trampolinsombra, gamew.trampolin.posicion.x, 10);
		}else{
			batch.draw(trampolintexturel,gamew.trampolin.posicion.x,gamew.trampolin.posicion.y);
			batch.draw(trampolinsombra, gamew.trampolin.posicion.x, 10);
		}
		redondo.setX(gamew.trampolin.posicion.x + 62);
		redondo.setY(gamew.trampolin.posicion.y);
		redondo.rotate(gamew.trampolin.rotacion * delta);
		redondo.draw(batch);
		if ( ! explosionA.isAnimationFinished(statetime)){
			batch.draw(explosionA.getKeyFrame(statetime),payasomuerto.posicion.x - 100, -40);
		}
	}
	
	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		Gdx.input.setInputProcessor(iproc);
		Gdx.input.setCatchBackKey(true);
		Musica.playRandom();	
		marcador.setColor(Color.WHITE);
		tmp = batch.getColor();
		tmp.a = 1f;
		batch.setColor(tmp);
		if (!gamew.pausedgame){
			gamew.reset();
		}
	}

	@Override
	public void hide() {
		Musica.stop();	
		
	}

	@Override
	public void pause() {
			IngameScreen.botoninicio = SeeSawCircus.asset.get("botoninicio.png", Texture.class);
			IngameScreen.botonreloadpe = SeeSawCircus.asset.get("salir.png", Texture.class);
			IngameScreen.botonhomepe = SeeSawCircus.asset.get("botonhomepe.png", Texture.class);
			gamew.pausedgame = true;
			gamew.jumpiniS.stop();
			Musica.stop();
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
