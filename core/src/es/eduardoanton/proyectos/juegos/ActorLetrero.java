package es.eduardoanton.proyectos.juegos;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class ActorLetrero extends Actor {
Texture texture;
	
	public ActorLetrero(float x, float y){
		texture = SeeSawCircus.asset.get("letrero.png", Texture.class );
		setX(x);
		setY(y);
	}
	
	public void draw(Batch b, float alpha){
		b.draw(texture,this.getX(),this.getY());
	}
}
