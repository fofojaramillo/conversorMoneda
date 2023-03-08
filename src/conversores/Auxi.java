package conversores;

public class Auxi {
	
	public boolean isDouble(String entry) {
		try {
			Double.parseDouble(entry);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
	
}
