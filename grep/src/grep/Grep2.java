package grep;

public class Grep2 {

	public static void main(String[] args) {
		char[] text = ("for grep is a command-line utility  for searching \n"
				+ "plain-text data sets For lines matching \n"
				+ "a regular expression. Grep was originally developed fOr \n"
				+ "the Unix operating system, but is \n"
				+ "available today foR all Unix-like systems. Its name comes \n"
				+ "from the ed command g/re/p \n"
				+ "(globally search a regular expression and print), which has\n"
				+ " the same effect: doing a \n"
				+ "global search with the regular expression and \n"
				+ "printing all for matching lines.").toCharArray();

		grep(text, "for".toCharArray());
	}

	private static void grep(char[] text, char[] searchSequence) {
		char lineBreak = '\n';
		int startLine = 0;
		boolean findSequence = false;
		int k = 0;

		for (int i = 0; i < text.length; i++) {
			if (text[i] == lineBreak) {
				startLine = i + 1;
			}
			if (k == searchSequence.length) {
			    findSequence = true;
			    k = 0;
			   } else {
			    if (text[i] == searchSequence[k] || text[i] == searchSequence[k] + 32
			      || text[i] == searchSequence[k] - 32) {
			     ++k;
			    } else {
			     if (k > 0) {
			      --i;
			     }
			     k = 0;
			    }
			   }
		
			
			
			

//			if ((text[i] == searchSequence[k]) || (text[i] == searchSequence[k] + 32)
//					|| (text[i] == searchSequence[k] - 32)) {
//				lab1: for (k = 1; k < searchSequence.length; k++) {
//					++i;
//					if ((text[i] == searchSequence[k]) || (text[i] == searchSequence[k] + 32)
//							|| (text[i] == searchSequence[k] - 32)) {
//						findSequence = true;
//					} else {
//						findSequence = false;
//						break lab1;
//					}
//				}
//			
			if (findSequence) {
				for (int j = startLine; j < text.length; j++) {
					System.out.print(text[j]);

					if (text[j] == lineBreak) {
						i = j - 1;
						break;
					}
					findSequence = false;
				}
//				 int j = startLine;
//				 while ( ++j < text.length) {
//				 System.out.print(text[j]);
//				 if(text[j] == '\n')
//				 break;
//				 }
//				 i = j -1;
			}
		}

	}

}
