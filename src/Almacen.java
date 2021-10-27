import javax.imageio.ImageIO;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;

/**
 * Almacen: Carga central de recursos para sprites
 * Se utiliza patrón de diseño singleton
 */
public class Almacen {
    private static final Almacen single = new Almacen();
    final HashMap<String, Sprite> sprites = new HashMap<>();

    public static Almacen getSingle() {
        return single;
    }

    public Sprite getSprite(String ref){
        if(sprites.get(ref) != null) return sprites.get(ref);

        BufferedImage fuente;
        try {
            URL url = this.getClass().getClassLoader().getResource(ref);
            if(url == null) {
                fail("Referencia no encontrada " + ref);
                return null;
            }
            fuente = ImageIO.read(url);
        } catch (IOException e){
            fail("No cargó " + ref);
            return null;
        }

        GraphicsConfiguration gc = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();
        Image imagen = gc.createCompatibleImage(fuente.getWidth(), fuente.getHeight(), Transparency.BITMASK);
        imagen.getGraphics().drawImage(fuente, 0,0, null);

        Sprite sprite = new Sprite(imagen);
        sprites.put(ref, sprite);

        return sprite;
    }

    private void fail(String msg){
        System.err.println(msg);
        System.exit(0);
    }
}
