package conversorMoneda;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.*;



public class Conversores{
	
	private static String URL_GENERATED;
	
	public static void main(String[] args) throws IOException {
		String[] options= {"Conversor de Moneda","Texto a Binario"};
		// Se cambia el tamaño de la imagen para volverla un icono
        BufferedImage originalImage = ImageIO.read(new File("C:\\Users\\Rodolfo Jaramillo\\eclipse-workspace\\conversorMoneda\\src\\conversorMoneda\\images\\icono.png"));
        BufferedImage resizedImage = new BufferedImage(32, 32, BufferedImage.TYPE_INT_ARGB);
        resizedImage.createGraphics().drawImage(originalImage, 0, 0, 32, 32, null);

        // Se crea el icono
        ImageIcon icon = new ImageIcon(resizedImage);
        
        Object response;
        
        // Ventana inicial de opciones
		response = JOptionPane.showInputDialog(null, "Elije un conversor", "Conversor", JOptionPane.INFORMATION_MESSAGE, icon, options, "Conversor de Moneda");
		
		if (response == "Conversor de Moneda") {
			
			Object monto;
			
			monto = JOptionPane.showInputDialog("Introduzca el monto que desea convertir");
			
			String[] monedas = {"MXN", "USD", "EUR", "GBP", "JPY", "KRW"};
			
	        Object destino;

	        destino = JOptionPane.showInputDialog(null, "Elije la moneda a la que quieres convertir", "Monedas", 
					JOptionPane.INFORMATION_MESSAGE, null, monedas, "USD");
	        
	        //Creamos la URL con los requerimientos
	        URL_GENERATED = "https://api.getgeoapi.com/v2/currency/convert?api_key=bedd97eb7c541d81243b6aaddeb7e66164182ca6&from=MXN&to="+
	        destino+"&amount="+monto+"&format=json";
	        //
            StringBuilder result = new StringBuilder();
	        try {
	            URL url = new URL(URL_GENERATED);
	            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	            conn.setRequestMethod("GET");
	            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	            String line;
	            while ((line = rd.readLine()) != null) {
	                result.append(line);
	            }
	            rd.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }

	        JSONObject jsonObj = new JSONObject(result.toString());
	        JSONObject ratesObj = jsonObj.getJSONObject("rates");
	        
	        String currencyName = ratesObj.getJSONObject(destino.toString()).getString("currency_name");
	        String rate = ratesObj.getJSONObject(destino.toString()).getString("rate");
	        String rateForAmount = ratesObj.getJSONObject(destino.toString()).getString("rate_for_amount");
	        
	        JOptionPane.showMessageDialog(null,"El monto de "+monto+" MXN convertido a "+currencyName+" es de: "+rateForAmount);
	        
	        }

	}
}









