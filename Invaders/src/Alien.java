public class Alien extends Elemento{
    int direccion = 1;
    final Juego juego;
    boolean usado = false;

    public Alien(Juego juego, String ref, int posicionX, int posicionY) {
        super(ref, posicionX, posicionY, 4);
        this.juego = juego;
    }
 
    public void mover(long cambio) {
        if(posicionY > 600) {
            usado= true;
            return;
        }
        if(direccion > 0){ //DERECHA
            double npos = posicionX+sprite.getAncho()+cambio;
            if(npos<800){
                posicionX = npos;
            }else{
                posicionY += sprite.getAlto();
                direccion = -1;
            }
        } else { //IZQUIERDA
            double npos = posicionX-sprite.getAncho()-cambio;
            if(npos>0){
                posicionX = npos;
            }else{
                posicionY += sprite.getAlto();
                direccion = 1;
            }
        }
    }

    @Override
    public void colision(Elemento otro) {
        if(otro instanceof Alien) return;
        if(otro instanceof Disparo)
            vida--;
        if(vida==0 || otro instanceof Nave){
            juego.removerElemento(this);
        }
    }
}
