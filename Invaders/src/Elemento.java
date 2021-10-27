import java.awt.*;

public abstract class Elemento {
    double posicionX;
    double posicionY;
    final Sprite sprite;
    int vida;

    public Elemento(String ref, int posicionX, int posicionY, int vida){
        this.sprite = Almacen.getSingle().getSprite(ref);
        this.posicionX = posicionX;
        this.posicionY = posicionY;
        this.vida = vida;
    }

    public void dibujar(Graphics g){
        this.sprite.dibujar(g, (int)posicionX, (int)posicionY);
    }

    public boolean comprobarColision(Elemento otro){
        Rectangle espacioPropio = new Rectangle((int)posicionX, (int)posicionY, sprite.getAncho(), sprite.getAlto());
        Rectangle espacioOtro = new Rectangle((int)otro.posicionX, (int)otro.posicionY, otro.sprite.getAncho(), otro.sprite.getAlto());
        return espacioPropio.intersects(espacioOtro);
    }

    public abstract void colision(Elemento otro);
}
