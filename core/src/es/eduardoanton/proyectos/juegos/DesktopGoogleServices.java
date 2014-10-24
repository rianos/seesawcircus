package es.eduardoanton.proyectos.juegos;

public class DesktopGoogleServices implements IGoogleServices {

	@Override
	public void entrarGS() {
		System.out.println("Entrando en GS");
	}

	@Override
	public void salirGS() {
		System.out.println("Saliendo en GS");

	}

	@Override
	public void enviarPuntosGS(long score, String gameMode) {
		System.out.println("Enviando puntos en GS en modo " + gameMode);

	}

	@Override
	public void mostrarPuntosGS(String gameMode) {
		System.out.println("Mostrando puntos en GS " + gameMode);

	}

	@Override
	public boolean estaLoginGS() {
		System.out.println("Viendo si esta logeado en GS");
		return false;
	}

}
