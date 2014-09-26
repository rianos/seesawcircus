package es.eduardoanton.proyectos.juegos;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class ActorGenerico extends Actor {
	Texture texture;

	
	public ActorGenerico(float x, float y, String textura){
		texture = SeeSawCircus.asset.get(textura, Texture.class );
		setX(x);
		setY(y);
	
	}
	
	public void draw(Batch b, float alpha){
		 b.draw(texture, getX(), getY());
	}
	
	public void setTexture(String text){
		texture = SeeSawCircus.asset.get(text, Texture.class );
	}
}
