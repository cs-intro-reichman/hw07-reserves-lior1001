
public class SpellChecker {


	public static void main(String[] args) {
		String word = args[0];
		int threshold = Integer.parseInt(args[1]);
		String[] dictionary = readDictionary("dictionary.txt");
		String correction = spellChecker(word, threshold, dictionary);
		System.out.println(correction);
	}

	public static String tail(String str) {
		//return the given word without the first letter
		return(str.substring(1, str.length()));
	}

	public static int levenshtein(String word1, String word2) {
		int distance = 0;
		String strLow1 = word1.toLowerCase();
		String strLow2 = word2.toLowerCase();
		//if |a| = 0
		if(strLow1.length() == 0) {
			distance = strLow2.length();
		}
		//if |b| = 0
		else if (strLow2.length() == 0) {
			distance = strLow1.length();
		}
		//if head(a) = head(b)
		else if(strLow1.charAt(0) == strLow2.charAt(0)) {
			distance = levenshtein(tail(strLow1), tail(strLow2));
		}
		//otherwise
		else {
			distance = 1 + (Math.min(levenshtein(tail(strLow1), strLow2), Math.min(levenshtein(strLow1, tail(strLow2)), levenshtein(tail(strLow1), tail(strLow2)))));
		}
		return distance;
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

	public static String spellChecker(String word, int threshold, String[] dictionary) {
		String closest = word.toLowerCase();
		//find the word with the smallest distance in the dictionary
		for(int i=0; i < dictionary.length; i++) {
			int dis = levenshtein(word, dictionary[i]);
			if(dis <= threshold) {
				closest = dictionary[i];
			}
		}
		return closest;
	}

}
