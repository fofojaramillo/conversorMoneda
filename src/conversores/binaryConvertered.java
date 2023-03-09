package conversores;

public class binaryConvertered {
	
	private static int binary = 0;
	private static int exp = 0;
	private static int aux = 0;
	
	public static String toBinary(String predecimal) {
		int decimal;
		decimal = Integer.parseInt(predecimal);
		while(decimal > 0) {
			aux = decimal % 2;
			decimal = (int) (decimal/2);
			binary = binary + (int) (aux * Math.pow(10, exp));
			exp++;
		}
		return String.valueOf(binary);
	}
}
