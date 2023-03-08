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

public class Conversores {

	private static String URL_GENERATED;

	public static boolean isDouble(String entry) {
		try {
			Double.parseDouble(entry);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	public static void main(String[] args) throws IOException {
		String[] options = { "Conversor de Moneda", "Texto a Binario" };
		
		int goOn = 0;
		
		// Se cambia el tamaño de la imagen para volverla un icono
		BufferedImage originalImage = ImageIO.read(new File(
				"C:\\Users\\Rodolfo Jaramillo\\eclipse-workspace\\conversorMoneda\\src\\conversorMoneda\\images\\icono.png"));
		BufferedImage resizedImage = new BufferedImage(32, 32, BufferedImage.TYPE_INT_ARGB);
		resizedImage.createGraphics().drawImage(originalImage, 0, 0, 32, 32, null);

		// Se crea el icono
		ImageIcon icon = new ImageIcon(resizedImage);

		Object response;

		// Ventana inicial de opciones
		response = JOptionPane.showInputDialog(null, "Elije un conversor", "Conversor", JOptionPane.INFORMATION_MESSAGE,
				icon, options, "Conversor de Moneda");

		//Aqui se decide si se continua o no.
		while(goOn==0) {
			//Se Elije el conversor de monedas
			if (response == "Conversor de Moneda") {

				Object amount;
				
				amount = JOptionPane.showInputDialog("Introduzca el monto que desea convertir");
				
				//Se confirma que sea un valor numerico, en este caso un double
				if (isDouble(amount.toString())) {
					String[] monedas = { "MXN", "USD", "EUR", "GBP", "JPY", "KRW" };

					Object currency;

					//Se guarda la moneda de destino a la conversion
					currency = JOptionPane.showInputDialog(null, "Elije la moneda a la que quieres convertir", "Monedas",
							JOptionPane.INFORMATION_MESSAGE, null, monedas, "USD");

					// Creamos la URL con los requerimientos
					URL_GENERATED = "https://api.getgeoapi.com/v2/currency/convert?api_key=bedd97eb7c541d81243b6aaddeb7e66164182ca6&from=MXN&to="
							+ currency + "&amount=" + amount + "&format=json";
					
					//Se consume la API
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
					
					//Se toma el elemento requerido de la API
					JSONObject jsonObj = new JSONObject(result.toString());
					JSONObject ratesObj = jsonObj.getJSONObject("rates");

					String currencyName = ratesObj.getJSONObject(currency.toString()).getString("currency_name");
					String rate = ratesObj.getJSONObject(currency.toString()).getString("rate");
					String rateForAmount = ratesObj.getJSONObject(currency.toString()).getString("rate_for_amount");

					//Panel de informaición
					JOptionPane.showMessageDialog(null, "El monto de " + amount + " MXN convertido a " + currencyName
							+ " es de: " + rateForAmount + " " + currency);
					
					//Ventada para ver que se desea hacer ahora
					goOn=JOptionPane.showConfirmDialog(null, "¿Desea continuar?");
					
					if(goOn == 1 || goOn == 2) {
						JOptionPane.showMessageDialog(null, "Programa Terminado");
					}
					
				}else {
					//Se introdujo un valor incorrecto al monto
					JOptionPane.showMessageDialog(null, "Entrada no valida, tiene que ser un valor númerico");
				}

			}

		}
		
		
	}
}
