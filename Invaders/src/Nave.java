
public class Nave extends Elemento {
    final Juego juego;

    public Nave(Juego juego, String ref, int posicionX, int posicionY){
        super(ref, posicionX, posicionY, 3);
        this.juego = juego;
    }

    public void mover(long cambio) {
        if (posicionX+cambio < 0) return;
        if (posicionX+sprite.getAncho()+cambio >= 800) return;
        posicionX += cambio;
    }

    @Override
    public void colision(Elemento otro) {
        if (otro instanceof Nave) return;
        if (otro instanceof Alien) {
            if (vida > 0) vida--;
            else {
                juego.removerElemento(this);
                juego.navesRestantes--;
            }
        }
    }
}
