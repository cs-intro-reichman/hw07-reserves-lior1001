

public class HashTagTokenizer {

	public static void main(String[] args) {

		String hashTag = args[0];
		String []dictionary = readDictionary("dictionary.txt");
		breakHashTag(hashTag, dictionary);
	}

	public static String[] readDictionary(String fileName) {
		String[] dictionary = new String[3000];

		In in = new In(fileName);
		//go over the new array and insert each word from the file into the String array
		for(int i=0; i < dictionary.length; i++) {
			String str = in.readLine();
			dictionary[i] = str;
		}
		return dictionary;
	}

	public static boolean existInDictionary(String word, String []dictionary) {
		boolean isIn = false;
		for(int i=0; i < dictionary.length; i++) {
			if(dictionary[i].equals(word))
				isIn = true;
		}
		return isIn;
	}

	public static void breakHashTag(String hashtag, String[] dictionary) {

		// Base case: do nothing (return) if hashtag is an empty string.
        if (hashtag.isEmpty()) {
            return;
        }
 
        int N = hashtag.length();
		String newHash = hashtag.toLowerCase(); 

        for(int i = 1; i <= N; i++) {
			String str = newHash.substring(0, i);
			if(existInDictionary(str, dictionary)) {
				System.out.println(str.substring(0, i));
				breakHashTag(hashtag.substring(i, N), dictionary);
				break;
			}
        }

    }

}
