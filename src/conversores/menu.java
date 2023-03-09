package conversores;

import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import org.json.*;

//
public class menu {
			
	public static void main(String[] args) throws IOException {
		initial();	
	}
	
	public static void initial() throws IOException {
		String[] options = { "Conversor de Moneda", "Decinal a Binario" };
		ImageIcon arrows = icons.getArrowsIcon();
		Object response;
		// Ventana inicial de opciones
		response = JOptionPane.showInputDialog(null, "Elije un conversor", "Conversor", JOptionPane.INFORMATION_MESSAGE,
				arrows, options, "Conversor de Moneda");
		if(response == "Conversor de Moneda") choiceCurrency();
		if(response == "Decinal a Binario") showBinary();
	}
	
	static String[] monedas = { "MXN", "USD", "EUR", "GBP", "JPY", "KRW" };
	static Object fromCurrency;
	
	public static void choiceCurrency() throws IOException {
		//Se guarda la moneda de destino a la conversion
		fromCurrency = JOptionPane.showInputDialog(null, "Elije la moneda que quieres convertir", "Monedas",
				JOptionPane.INFORMATION_MESSAGE, null, monedas, monedas[0]);
		showAmount();
	}
	
	static Object amount;
	
	public static void showAmount() throws IOException {
		amount = JOptionPane.showInputDialog("Introduzca el monto que desea convertir");
		if (Auxi.isDouble(amount.toString())) {
			showCurrency();
		}else {
			JOptionPane.showMessageDialog(null, "Entrada no valida, tiene que ser un valor númerico");
		}
	}
	
	static Object toCurrency;
	
	public static void showCurrency() throws IOException {
		//Se quita el elemento seleccionado en choiceCurrency
		monedas = Auxi.takeOff(monedas, fromCurrency);
		//Se guarda la moneda de destino a la conversion
		toCurrency = JOptionPane.showInputDialog(null, "Elije la moneda a la que quieres convertir", "Monedas",
				JOptionPane.INFORMATION_MESSAGE, null, monedas, monedas[0]);
		showResult();
	}
	
	public static void showResult() throws IOException {
		Object result = currencyAPI.requestCurrency(fromCurrency.toString(), toCurrency.toString(), amount.toString());
		JSONObject jsonObj = new JSONObject(result.toString());
		JSONObject ratesObj = jsonObj.getJSONObject("rates");

		String currencyName = ratesObj.getJSONObject(toCurrency.toString()).getString("currency_name");
		String rate = ratesObj.getJSONObject(toCurrency.toString()).getString("rate");
		String rateForAmount = ratesObj.getJSONObject(toCurrency.toString()).getString("rate_for_amount");

		//Panel de informaición
		JOptionPane.showMessageDialog(null, "El monto de " + amount + " " + fromCurrency +" convertido a " + currencyName
				+ " es de: " + rateForAmount + " " + toCurrency);
		showContinue();
	}
	
	public static void showBinary() throws IOException {
		String decimal = JOptionPane.showInputDialog("Introduzca el número que desea convertir");
		String result = binaryConvertered.toBinary(decimal);
		JOptionPane.showMessageDialog(null, "El numero "+decimal+" convertido a binario es: "+result);
		showContinue();
	}
	
	public static void showContinue() throws IOException {
		int goOn;
		goOn=JOptionPane.showConfirmDialog(null, "¿Desea continuar?");
		if(goOn == 0) initial();
		if(goOn == 1 || goOn == 2) showFinish();
	}
	
	public static void showFinish(){
		JOptionPane.showMessageDialog(null, "Programa Terminado");
	}
}











