import java.awt.Image;
import java.awt.Graphics;

public class Sprite {
    private final Image imagen;

    public Sprite(Image imagen) {
        this.imagen = imagen;
    }

    public int getAncho(){
        return imagen.getWidth(null);
    }

    public int getAlto(){
        return imagen.getWidth(null);
    }

    public void dibujar(Graphics g, int x, int y){
        g.drawImage(imagen, x, y, null);
    }
}
