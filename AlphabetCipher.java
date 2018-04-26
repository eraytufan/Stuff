
public class AlphabetCipher {
	
	public AlphabetCipher() {
		
	}
	
	public String encrypt(String keyword, String message) {
		int keywordLength = keyword.length();
		String encrypt = "";
		message = message.toLowerCase();
		keyword = keyword.toLowerCase();
		
		for (int i = 0; i < message.length(); i++) {
			int offset = (int)(keyword.charAt(i % keywordLength)) % (int)('a');
			encrypt += (char) ((int) ('a' + (message.charAt(i) + offset - 'a') % 26 ));
			/*the same code
			 * encrypt += (char) ((int)(keyword.charAt(i % keywordLength) + (int)(message.charAt(i)) - 2 * (int) ('a')) % 26 + (int)('a'));
			*/
		}
		
		return encrypt;
	}
	
	public String decrypt(String keyword, String message) {
		int keywordLength = keyword.length();
		String encrypt = "";
		message = message.toLowerCase();
		keyword = keyword.toLowerCase();
		
		for (int i = 0; i < message.length(); i++) {
			int offset = 26 - (int)(keyword.charAt(i % keywordLength)) % (int)('a');
			encrypt += (char) ((int) ('a' + (message.charAt(i) + offset - 'a') % 26 ));
			//encrypt += (char) ((int)(keyword.charAt(i % keywordLength) + (int)(message.charAt(i)) - 2 * (int) ('a')) % 26 + (int)('a'));
		}
		
		return encrypt;
	}
	
	public static void main(String[] args) {
		
		AlphabetCipher alp = new AlphabetCipher();
		System.out.println(alp.encrypt("bond", "theredfoxtrotsquietlyatmidnight"));
		System.out.println(alp.decrypt("python", "pjphmfamhrcaifxifvvfmzwqtmyswst"));
	
	}
}
