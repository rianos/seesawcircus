package es.eduardoanton.proyectos.juegos;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class ActorFondoInicio extends Actor{
	Texture texture;
	
	public ActorFondoInicio(){
		texture = SeeSawCircus.asset.get("fondomain.png", Texture.class );
		setX(0);
		setY(0);
	}
	
	public void draw(Batch b, float alpha){
		b.draw(texture,this.getX(),this.getY());
	}
}
