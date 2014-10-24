package es.eduardoanton.proyectos.juegos;

public interface IGoogleServices {
	public void entrarGS();
	public void salirGS();
	public void enviarPuntosGS(long score, String gameMode);
	public void mostrarPuntosGS(String gameMode);
	public boolean estaLoginGS();
}
