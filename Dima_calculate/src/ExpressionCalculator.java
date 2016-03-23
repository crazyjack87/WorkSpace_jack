
public class ExpressionCalculator {

	public static double calculate(String expression) {
		if (expression.indexOf(' ') > -1) {
			expression = expression.replace(" ", "");
		}

		expression = stripOuterParenthesis(expression);
		char[] exp = expression.toCharArray();

		int outerPosition = getOuterSymbolIndex(exp, "+-".toCharArray());
		if (outerPosition > -1) {
			String left = expression.substring(0, outerPosition);
			String right = expression.substring(outerPosition + 1, expression.length());
			switch (exp[outerPosition]) {
			case '+':
				return calculate(left) + calculate(right);
			case '-':
				return calculate(left) - calculate(right);
			}
		}

		outerPosition = getOuterSymbolIndex(exp, "*/".toCharArray());
		if (outerPosition > -1) {
			String left = expression.substring(0, outerPosition);
			String right = expression.substring(outerPosition + 1, expression.length());
			switch (exp[outerPosition]) {
			case '*':
				return calculate(left) * calculate(right);
			case '/':
				return calculate(left) / calculate(right);
			}
		}

		return tryParse(expression);
	}

	public static void main(String[] args) {
		String[] expressions = {
				"  ( 2 + 2 * 2 )  ",
				" ( 2 + 2 ) * ( 3 + 3 ) ",
				"2+2*(3+3*3)",
				"(2+2*(3+3*3))",
				"(2+2*3)+3*3",
				"2*3/4*5"
		};
		for(String expression: expressions){
			validate(expression);
			System.out.println(expression + " = " + calculate(expression));
		}
	}

	private static int getOuterSymbolIndex(char[] exp, char[] searchSymbols) {
		String expression = String.valueOf(exp);

		boolean isAnySearchSymbolPresent = false;
		for (int i = 0; i < searchSymbols.length; i++) {
			if (expression.indexOf(searchSymbols[i]) > -1) {
				isAnySearchSymbolPresent = true;
				break;
			}
		}

		if (isAnySearchSymbolPresent) {
			for (int i = exp.length - 1; i >= 0; i--) {
				for (int j = 0; j < searchSymbols.length; j++) {
					if (exp[i] == searchSymbols[j] && getParenthesisBalance(expression.substring(i)) == 0) {
						return i;
					}
				}
			}
		}

		return -1;
	}

	private static int getParenthesisBalance(String expression) {
		char[] exp = expression.toCharArray();
		int balance = 0;
		for (int i = 0; i < exp.length; i++) {
			switch (exp[i]) {
			case '(':
				--balance;
				break;
			case ')':
				++balance;
			}
		}
		return balance;
	}
	
	private static String getParenthesisSequence(String expression) {
		char[] exp = expression.toCharArray();
		String parenthesisSequence = "";
		for (int i = 0; i < exp.length; i++) {
			if (exp[i] == '(' || exp[i] == ')') {
				parenthesisSequence += exp[i];
			}
		}
		return parenthesisSequence;
	}

	private static String removeAllOrderedParenthesis(String parenthesisSequence){
		int loopCount = parenthesisSequence.length() / 2;
		for (int i = 0; i < loopCount; i++) {
			parenthesisSequence = parenthesisSequence.replaceFirst("[(][)]", "");
		}
		return parenthesisSequence;
	}

	private static String stripOuterParenthesis(String expression) {
		if (expression.length() > 0 && expression.indexOf('(') == 0
				&& expression.lastIndexOf(')') == expression.length() - 1) {
			String parenthesisSequence = getParenthesisSequence(expression);
			parenthesisSequence = parenthesisSequence.substring(1, parenthesisSequence.length() - 1);
			parenthesisSequence = removeAllOrderedParenthesis(parenthesisSequence);
			if (parenthesisSequence.length() == 0) {
				expression = expression.substring(1, expression.length() - 1);
			}
		}
		return expression;
	}

	private static double tryParse(String expression) {
		try {
			return Double.parseDouble(expression);
		} catch (NumberFormatException e) {
			System.out.println("Mistake in expression : " + expression);
			System.out.println(
					"Expression should consist of numeric values, proper parenthesis count and '+', '-', '*', '/' operators.");
			throw e;
		}
	}

	public static void validate(String expression) {
		char[] exp = expression.toCharArray();

		String validCharset = "()-+*/ 0123456789";
		String invalidCharacters = "";
		for (int i = 0; i < exp.length; i++) {
			if (validCharset.indexOf(exp[i]) == -1 && invalidCharacters.indexOf(exp[i]) == -1) {
				invalidCharacters += invalidCharacters.length() == 0 ? exp[i] : ", " + exp[i];
			}
		}
		if (invalidCharacters.length() > 0) {
			throw new ArithmeticException("Unexpected character(s) was found: " + invalidCharacters + '.');
		}

		if (expression.indexOf('(') != -1 || expression.indexOf(')') != -1) {
			String parenthesisSequence = getParenthesisSequence(expression);

			if (getParenthesisBalance(parenthesisSequence) != 0) {
				throw new ArithmeticException("Parenthesis count error.");
			}

			parenthesisSequence = removeAllOrderedParenthesis(parenthesisSequence);

			if (parenthesisSequence.length() != 0 || expression.indexOf("()") != -1 || expression.indexOf("(*)") != -1
					|| expression.indexOf("(/)") != -1 || expression.indexOf("(+)") != -1
					|| expression.indexOf("(-)") != -1) {
				throw new ArithmeticException("Parenthesis order error.");
			}
		}
	}

}
