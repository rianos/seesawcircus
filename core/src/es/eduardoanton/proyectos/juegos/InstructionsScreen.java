package es.eduardoanton.proyectos.juegos;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;

public class InstructionsScreen implements Screen {
	public SeeSawCircus game;
	private SpriteBatch batch;
	private OrthographicCamera cam;
	private Stage stage;
	private Actor capa,capa2,capa3,capa4,i4,fondo,trampolin,trampolin2;
	private Actor circulo2,circulo,payaso1,payaso2,dedo,dedor,dedogl,dedogr,i1,i2,i3,salto1,salto2,salto3,payaso3;
	public static ActorGenerico botoninicio,botonhome;
	private Group trampolines,trampolines2,info1,info2,info3,info4;
	public InputProcessor iproc;
	
	public InstructionsScreen(SeeSawCircus game){
		this.game = game;
		cam = new OrthographicCamera();
		cam.setToOrtho(false, SeeSawCircus.screenwidth,SeeSawCircus.screenheight);
		cam.update();
		batch = new SpriteBatch();
		batch.setProjectionMatrix(cam.combined);
		stage = new Stage(new StretchViewport(SeeSawCircus.screenwidth, SeeSawCircus.screenheight));
		reset();
		
	}
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0.01f,0.01f ,0.02f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);	
		stage.act(delta);
		stage.draw();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void show() {		
		botonhome.setTexture("botonhomepe.png");
		botoninicio.setTexture("botoninicio.png");
		Gdx.input.setInputProcessor(iproc);
		SeeSawCircus.prefs.putBoolean("modomanual", true);
		SeeSawCircus.prefs.flush();
		SeeSawCircus.gamew.modemanual = true;
		reset();
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

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
		stage.dispose();
		batch.dispose();

	}
	public void reset(){
		fondo= new ActorGenerico(0,0,"fondo.png");
		trampolin = new ActorGenerico(410,40,"trampolin.png");
		trampolin2 = new ActorGenerico(410,40,"trampolin2.png");
		circulo = new ActorGenerico(475,40, "redondo.png");
		circulo2 = new ActorGenerico(475,40, "redondo.png");
		payaso1 = new ActorGenerico(515,50,"payaso33.png");
		payaso2 = new ActorGenerico(410,50,"payaso33.png");
		payaso3 = new ActorGenerico(470,350,"payaso22.png");
		dedo = new ActorGenerico(50,50, "dedo.png");
		dedor = new ActorGenerico(850,50, "dedo.png");
		dedogl = new ActorGenerico(50,50, "dedog.png");
		dedogr = new ActorGenerico(850,50, "dedog.png");
		capa = new ActorGenerico(0,0,"capa.png");
		capa2 = new ActorGenerico(512,0,"capa.png");
		capa3 = new ActorGenerico(256,400,"capa.png");
		capa4 = new ActorGenerico(256,400,"capa.png");
		botonhome = new ActorGenerico(930,500,"botonhomepe.png");
		i1 = new ActorGenerico(50,400,"i1.png");
		i2 = new ActorGenerico(525,400,"i2.png");
		i3 = new ActorGenerico(330,450,"i3.png");
		i4 = new ActorGenerico(330,450,"i4.png");
		salto1 = new ActorGenerico(485,60,"salto1.png");
		salto2 = new ActorGenerico(310,70,"salto2.png");
		salto3 = new ActorGenerico(065,67,"salto3.png");
		botoninicio = new ActorGenerico(430,40,"botoninicio.png");
		botoninicio.addAction(sequence(
				visible(false),delay(28),visible(true)
				));
		trampolines = new Group();
		trampolines.addActor(payaso1);
		trampolines.addActor(trampolin);
		trampolines.addActor(circulo);
		trampolines2 = new Group();
		trampolines2.addActor(payaso2);
		trampolines2.addActor(trampolin2);
		trampolines2.addActor(circulo2);
		info1 = new Group();
		info1.addActor(capa);
		info1.addActor(i1);
		info2 = new Group();
		info2.addActor(capa2);
		info2.addActor(i2);
		info3 = new Group();
		info3.addActor(capa3);
		info3.addActor(i3);
		info4 = new Group();
		info4.addActor(capa4);
		info4.addActor(i4);
		payaso3.addAction((sequence(
				visible(false),
		        delay(19),
		        visible(true),
		        delay(1),
		        moveTo(470,60,1),
		        run(new Runnable(){
					@Override
					public void run() {
						Sound s = SeeSawCircus.asset.get("boingsda.mp3");
						s.play();
					}}),
		        moveTo(490,140,0.3f),
		        moveTo(530,120,0.1f),
		        moveTo(570,60,0.3f),
		        delay(1),
		        moveBy(-150,300),
		        moveTo(420,70,1),
		        run(new Runnable(){
					@Override
					public void run() {
						Sound s = SeeSawCircus.asset.get("boingsda.mp3");
						s.play();
					}}),
		        moveTo(330,300,0.3f),
		        delay(1),
		        moveBy(40,50),
		        moveTo(355,70,1),
		        run(new Runnable(){
					@Override
					public void run() {
						Sound s = SeeSawCircus.asset.get("boingsda.mp3");
						s.play();
					}}),
		        moveTo(100,550,0.3f),
		        delay(0.5f),
		        visible(false)
		              )));
		salto1.addAction((sequence(
				visible(false),
		        delay(19),
		        visible(true),
		        delay(3.7f),
		        visible(false)
		              )));
		salto2.addAction((sequence(
				visible(false),
		        delay(22.7f),
		        visible(true),
		        delay(2.3f),
		        visible(false)
		              )));
		salto3.addAction((sequence(
				visible(false),
		        delay(25),
		        visible(true),
		        delay(2),
		        visible(false)
		              )));
		info1.addAction((sequence(
				visible(false),
		        delay(1),
		        visible(true),
		        delay(4),
		        visible(false),
		        delay(13)
		              )));
		info2.addAction((sequence(
				visible(false),
		        delay(6),
		        visible(true),
		        delay(4),
		        visible(false),
		        delay(8)
		              )));
		info3.addAction((sequence(
				visible(false),
		        delay(11),
		        visible(true),
		        delay(6),
		        visible(false),
		        delay(1)
		              )));
		info4.addAction((sequence(
				visible(false),
		        delay(19),
		        visible(true),
		        delay(8),
		        visible(false)
		 
		              )));
		dedogl.addAction((sequence(
				visible(true),
		        delay(2),
		        visible(false),
		        delay(2),
		        visible(true),
		        delay(13.3f),
		        visible(false)
		              )));
		dedogr.addAction((sequence(
				visible(true),
		        delay(7),
		        visible(false),
		        delay(2),
		        visible(true),
		        delay(2),
		        visible(false),
		        delay(1),
		        visible(true),
		        delay(3),
		        visible(false),
		        delay(1),
		        visible(true),
		        delay(1),
		        visible(false)
		              )));
		dedo.addAction((sequence(
				visible(false),
		        delay(2),
		        visible(true),
		        run(new Runnable(){
					@Override
					public void run() {
						Sound s = SeeSawCircus.asset.get("box_trash_impact_03.mp3");
						s.play();
					}}),
		        delay(2),
		        visible(false),
		        delay(14)
		              )));
		dedor.addAction((sequence(
				visible(false),
		        delay(7),
		        visible(true),
		        run(new Runnable(){
					@Override
					public void run() {
						Sound s = SeeSawCircus.asset.get("box_trash_impact_03.mp3");
						s.play();
					}}),
		        delay(2),
		        visible(false),
		        delay(2),
		        visible(true),
		        moveTo(850,200,1),
		        run(new Runnable(){
					@Override
					public void run() {
						Sound s = SeeSawCircus.asset.get("cartoon_fast_whoosh_good_for_karate_chop_other_fast_movement_or_swipe_001.mp3");
						s.play();
					}}),
		        visible(false),
		        moveBy(0,-200),
		        delay(3),
		        visible(true),
		        moveTo(850,200,1),
		        run(new Runnable(){
					@Override
					public void run() {
						Sound s = SeeSawCircus.asset.get("cartoon_fast_whoosh_good_for_karate_chop_other_fast_movement_or_swipe_001.mp3");
						s.play();
					}}),
		        visible(false),
		        delay(4)
		              )));
		trampolines.addAction((sequence(
				visible(true),
		        delay(2),
		        moveTo(-200,0,2),
		        delay(3),
		        moveTo(0,0,2),
		        delay(3),
		        visible(false),
		        delay(4),
		        visible(true),
		        delay(11),
		        visible(false)
		              )));
		trampolines2.addAction((sequence(
				visible(false),
		        delay(12),
		        visible(true),
		        delay(4),
		        visible(false),
		        delay(2)
		              )));
		stage.addActor(fondo);
		stage.addActor(botonhome);
		stage.addActor(salto1);
		stage.addActor(salto2);
		stage.addActor(salto3);
		stage.addActor(payaso3);
		stage.addActor(trampolines);
		stage.addActor(trampolines2);
		
		stage.addActor(info1);
		stage.addActor(info2);
		stage.addActor(info3);
		stage.addActor(info4);
		stage.addActor(dedo);
		stage.addActor(dedor);
		stage.addActor(dedogl);
		stage.addActor(dedogr);
		stage.addActor(botoninicio);
		iproc = new InputProcesadorInstructions(cam,this);
	}

}
