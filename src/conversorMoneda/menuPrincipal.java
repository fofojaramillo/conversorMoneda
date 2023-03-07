package conversorMoneda;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class menuPrincipal {
	public static void main(String[] args) throws IOException {
		String[] options= {"Conversor de Moneda","Texto a binario"};
		// Se cambia el tamaño de la imagen para volverla un icono
        BufferedImage originalImage = ImageIO.read(new File("C:\\Users\\Rodolfo Jaramillo\\eclipse-workspace\\conversorMoneda\\src\\conversorMoneda\\images\\icono.png"));
        BufferedImage resizedImage = new BufferedImage(32, 32, BufferedImage.TYPE_INT_ARGB);
        resizedImage.createGraphics().drawImage(originalImage, 0, 0, 32, 32, null);

        // Se crea el icono
        ImageIcon icon = new ImageIcon(resizedImage);
        // Ventana inicial de opciones
		JOptionPane.showInputDialog(null, "Elije un conversor", "Conversor", JOptionPane.INFORMATION_MESSAGE, icon, options, "Conversor de Moneda");
	}
}
