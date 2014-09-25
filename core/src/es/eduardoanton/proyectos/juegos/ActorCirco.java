package es.eduardoanton.proyectos.juegos;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class ActorCirco extends Actor{
	Texture texture;
	
	public ActorCirco(float x, float y){
		texture = SeeSawCircus.asset.get("circo.png", Texture.class );
		setX(x);
		setY(y);
	}
	
	public void draw(Batch b, float alpha){
		b.draw(texture,this.getX(),this.getY());
	}

}
