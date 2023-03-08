package conversores;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
//Clase para trabajar y agregar iconos
public class icons {
    public static ImageIcon getArrowsIcon() throws IOException {
        // Se cambia el tamaño de la imagen para volverla un icono
        BufferedImage originalImage = ImageIO.read(new File(
            "C:\\Users\\Rodolfo Jaramillo\\eclipse-workspace\\Conversores\\src\\conversores\\images\\icono.png"));
        BufferedImage resizedImage = new BufferedImage(32, 32, BufferedImage.TYPE_INT_ARGB);
        resizedImage.createGraphics().drawImage(originalImage, 0, 0, 32, 32, null);
        // Se crea el icono
        ImageIcon arrows = new ImageIcon(resizedImage);
        return arrows;
    }
}
