package conversores;

import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import org.json.*;

//
public class menu {
	
	private static Auxi auxi = new Auxi();
	
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
		if(response == "Conversor de Moneda") showAmount();
		if(response == "Decinal a Binario") showBinary();
	}
	static Object amount;
	public static void showAmount() throws IOException {
		amount = JOptionPane.showInputDialog("Introduzca el monto que desea convertir");
		if (auxi.isDouble(amount.toString())) {
			showCurrency();
		}else {
			JOptionPane.showMessageDialog(null, "Entrada no valida, tiene que ser un valor númerico");
		}
	}
	static Object currency;
	public static void showCurrency() throws IOException {
		String[] monedas = { "MXN", "USD", "EUR", "GBP", "JPY", "KRW" };
		//Se guarda la moneda de destino a la conversion
		currency = JOptionPane.showInputDialog(null, "Elije la moneda a la que quieres convertir", "Monedas",
				JOptionPane.INFORMATION_MESSAGE, null, monedas, "USD");
		showResult();
	}
	public static void showResult() throws IOException {
		Object result = currencyAPI.requestCurrency(currency.toString(), amount.toString());
		JSONObject jsonObj = new JSONObject(result.toString());
		JSONObject ratesObj = jsonObj.getJSONObject("rates");

		String currencyName = ratesObj.getJSONObject(currency.toString()).getString("currency_name");
		String rate = ratesObj.getJSONObject(currency.toString()).getString("rate");
		String rateForAmount = ratesObj.getJSONObject(currency.toString()).getString("rate_for_amount");

		//Panel de informaición
		JOptionPane.showMessageDialog(null, "El monto de " + amount + " MXN convertido a " + currencyName
				+ " es de: " + rateForAmount + " " + currency);
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











