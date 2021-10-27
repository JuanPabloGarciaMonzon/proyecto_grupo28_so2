public class Disparo extends Elemento{
    final Juego juego;
    boolean usado = false;

    public Disparo(Juego juego, String ref, int posicionX, int posicionY) {
        super(ref, posicionX, posicionY, 1);
        this.juego = juego;
    }

    public void mover(long cambio) {
        if(posicionY < 0) {
            usado = true;
            juego.removerElemento(this);
        }
        posicionY += cambio;
    }

    @Override
    public void colision(Elemento otro) {
        if(usado) return;
        if(otro instanceof Alien){
            juego.puntos++;
            usado = true;
            juego.removerElemento(this);
        }
    }
}
