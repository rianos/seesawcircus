package es.eduardoanton.proyectos.juegos.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import es.eduardoanton.proyectos.juegos.SeeSawCircus;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 1024;
		config.height = 600;
		new LwjglApplication(new SeeSawCircus(), config);
	}
}
