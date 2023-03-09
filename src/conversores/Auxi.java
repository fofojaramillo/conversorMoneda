package conversores;

public class Auxi {
	
	public static boolean isDouble(String entry) {
		try {
			Double.parseDouble(entry);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
	public static String[] takeOff(String[] words, Object word) {
		String[] newWords = new String[words.length - 1];
		int k = 0;
		for (int i = 0; i < words.length; i++) {
		    if (words[i].equals(word)) {
		        continue;
		    }
		    newWords[k++] = words[i];
		}

		return newWords; 
	}
}
