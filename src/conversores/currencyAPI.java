package conversores;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class currencyAPI {
	
	private static String URL_GENERATED;
	
	public static Object requestCurrency(String currency,String amount){
		
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
	return result;
	}
}
