package es.eduardoanton.proyectos.juegos;

public interface IGoogleServices {
	public void entrarGS();
	public void salirGS();
	public void enviarPuntosGS(long score, boolean gameMode,boolean record);
	public void mostrarPuntosGS(boolean gameMode);
	public boolean estaLoginGS();
}
