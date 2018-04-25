

public class Rot13 {
	public static void main(String[] args) {
        
		String str = "Shingeki no Kyojin";

		System.out.println(encode(str, 13));
		System.out.println(decode(encode(str, 13), 13));
		
    }   
	public static String decode(String text, int number) {
		return encode(text, 26-number);
	}
	public static String encode(String text, int number) {
		int offset = number % 26;
		
		String string = "";
		for (int i = 0; i < text.length(); i++) {
			if (!Character.isLetter(text.charAt(i))) {
				string += text.charAt(i);
				continue;
			}
			if (Character.isUpperCase(text.charAt(i))) {
				string += (char) ((int) ('A' + (text.charAt(i) + offset - 'A') % 26 ));
			}
			else {
				string += (char) ((int) ('a' + (text.charAt(i) + offset - 'a') % 26 ));
			}
			
		}
		
		
		return string;
	}
}
