package grep;

public class GrepStr {

	public static void main(String[] args) {
		String searchSequence = "for";
		String text = ("grep is a command-line utility for searching \n"
				+ "plain-text data sets for lines matching \n"
				+ "a regular expression. Grep was originally developed for \n"
				+ "the Unix operating system, but is \n"
				+ "available today for all Unix-like systems. Its name comes \n"
				+ "from the ed command g/re/p \n"
				+ "(globally search a regular expression and print), which has\n"
				+ " the same effect: doing a \n"
				+ "global search with the regular expression and \n"
				+ "printing all for matching lines.");
		System.out.println(searchGrep(text, searchSequence));
	}

	private static String searchGrep(String text, String searchSequence) {
		String exp = "";

		for (int i = 0; i < text.length(); i++) {
			int searchSequenceIndex = text.indexOf(searchSequence, i);
			int newStrokaIndexLeft = -1;
			int newStrokaIndexRight = -1;
			newStrokaIndexLeft = text.lastIndexOf('\n', searchSequenceIndex);
			newStrokaIndexRight = text.indexOf('\n', searchSequenceIndex);
			if (searchSequenceIndex != -1 && newStrokaIndexLeft != -1 && newStrokaIndexRight != -1) {
				exp += text.substring(newStrokaIndexLeft, newStrokaIndexRight) + '\n';
				i = newStrokaIndexRight;
			} else if (searchSequenceIndex != -1 && newStrokaIndexLeft == -1 && newStrokaIndexRight != -1) {
				exp += text.substring(0, newStrokaIndexRight) + '\n';
				i = newStrokaIndexRight;
			} else if (searchSequenceIndex != -1 && newStrokaIndexLeft != -1 && newStrokaIndexRight == -1) {
				exp += text.substring(newStrokaIndexLeft, text.length()) + '\n';
				i = text.length();
			}
			searchSequenceIndex = -1;
		}
		return exp;
	}
}
