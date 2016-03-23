package grep;

public class GrepStr {

	public static void main(String[] args) {
		String searchSequence = "for";
		String text = ("grep is a command-line utility  for searching \n"
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
			int j = text.indexOf('\n', i);
			if (j == -1) {
				j = text.length() - 1;
			}
			if (text.indexOf(searchSequence, i) < j) {
				exp += text.substring(i, j) + '\n';
			}
			if (j > i) {
				i = j;
			}
		}
		return exp;
	}
}
