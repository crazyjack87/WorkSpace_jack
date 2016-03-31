
public class ExpressionCalculator2 {

	public static double calculate(String expression) {
		validate(expression);

		if (expression.indexOf(' ') > -1) {
			expression = expression.replace(" ", "");
		}

		expression = stripOuterParenthesis(expression);

		double result = 0d;
		while (Double.NEGATIVE_INFINITY == (result = getDouble(expression))) {
			String innerExpression = expression;
			int parenthesisDepth = getParenthesisDepth(expression);
			if (parenthesisDepth > 0) {
				int parenthesisStartIndex = getIndexOfParenthesis(expression, true, parenthesisDepth);
				int parenthesisEndIndex = getIndexOfParenthesis(expression, false, parenthesisDepth);
				innerExpression = expression.substring(parenthesisStartIndex, parenthesisEndIndex + 1);
			}
			expression = expression.replaceFirst(getRegexExpression(innerExpression),
					calculateInnerExpression(innerExpression));
		}

		return result;
	}

	public static void main(String[] args) {
		String[] expressions = {
				 "2 + 2 *2",
				 "((2+2)*2)",
				 "((2*2+7)+(22-3*3-1))",
				 "1+4*2/5",
				 "1+4*(2+2)",
				 "(2+2)-2*25",
				"10+(2+3*5-1+(2*2+2))",
				 "(10)+(2+3*5-1+(2*2+2))",
				 "10+(2+3*5-1+(2*2+(2)))",
				 "10+(2+(3*5)-1+(2*2+2))",
				 "10+(2+(3)*(5)-1+(2*2+2))",
				 "10+(-2+3*5-1+(2*2+2))",
				 "10+(2+3*5-1+(-2*-2+2))",
				 "10+(2+3*5-1+(-2*2+2))",
				 "10+(2+3*-5-1+(2*2+2))",
				 "10+(2+3*(-5)-1+(2*2+2))",
		};
		for (String expression : expressions) {
			System.out.println(expression + " = " + calculate(expression));
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

	private static String calculateInnerExpression(String innerExpression) {
		while (true) {
			innerExpression = innerExpression.replaceAll(getRegexExpression("--"), "+");
			innerExpression = innerExpression.replaceAll(getRegexExpression("+-"), "-");
			innerExpression = innerExpression.replaceAll(getRegexExpression("-+"), "-");
			char[] expArray = innerExpression.toCharArray();

			int firstMultIndex = innerExpression.indexOf('*');
			int firstDivIndex = innerExpression.indexOf('/');
			int firstOperationIndex = 0 <= firstMultIndex ? firstMultIndex : firstDivIndex;

			if (firstOperationIndex == -1) {
				int firstAddIndex = innerExpression.indexOf('+');
				int firstSubIndex = innerExpression.indexOf('-');
				if(firstSubIndex != -1 && (expArray[0] == '-' || (expArray[0] == '(' && expArray[1] == '-'))){
					firstSubIndex = innerExpression.indexOf('-', firstSubIndex + 1);
				}
				if (firstAddIndex == -1 && firstSubIndex == -1) {
					return stripOuterParenthesis(innerExpression);
				} else if (firstAddIndex != -1 && firstSubIndex == -1) {
					firstOperationIndex = firstAddIndex;
				} else if (firstAddIndex == -1 && firstSubIndex != -1) {
					firstOperationIndex = firstSubIndex;
				} else {
					firstOperationIndex = firstAddIndex < firstSubIndex ? firstAddIndex : firstSubIndex;
				}
			}

			String leftOperand = getOperand(expArray, firstOperationIndex, true);
			String rightOperand = getOperand(expArray, firstOperationIndex, false);

			if (Double.NEGATIVE_INFINITY != getDouble(leftOperand)
					&& Double.NEGATIVE_INFINITY != getDouble(rightOperand)) {
				double operationResult = 0d;

				switch (expArray[firstOperationIndex]) {
				case '*':
					operationResult = Double.parseDouble(leftOperand) * Double.parseDouble(rightOperand);
					break;
				case '/':
					operationResult = Double.parseDouble(leftOperand) / Double.parseDouble(rightOperand);
					break;
				case '+':
					operationResult = Double.parseDouble(leftOperand) + Double.parseDouble(rightOperand);
					break;
				case '-':
					operationResult = Double.parseDouble(leftOperand) - Double.parseDouble(rightOperand);
				}

				String replaceExpression = getRegexExpression(
						leftOperand + expArray[firstOperationIndex] + rightOperand);
				innerExpression = innerExpression.replaceFirst(replaceExpression, String.valueOf(operationResult));
			}
		}
	}

	private static double getDouble(String expression) {
		try {
			return Double.parseDouble(expression);
		} catch (NumberFormatException e) {
			return Double.NEGATIVE_INFINITY;
		}
	}

	private static int getIndexOfParenthesis(String string, boolean isOpenParenthesis, int depth) {
		char[] strArray = string.toCharArray();
		char openParenthesis = '(';
		char closeParenthesis = ')';
		for (int i = 0, balance = 0; i < strArray.length; i++) {
			if (strArray[i] == openParenthesis && ++balance == depth && isOpenParenthesis) {
				return i;
			}
			if (strArray[i] == closeParenthesis && balance-- == depth && !isOpenParenthesis) {
				return i;
			}
		}
		return -1;
	}

	private static String getOperand(char[] arr, int operationIndex, boolean isLeftOperand) {
		int current = operationIndex;
		String doubleChars = ".0123456789";
		String result = "";
		if (isLeftOperand) {
			while (--current >= 0 && doubleChars.indexOf(arr[current]) != -1) {
				result = arr[current] + result;
			}
			if (current >= 0 && arr[current] == '-' && (current == 0 || doubleChars.indexOf(arr[current - 1]) == -1)) {
				result = arr[current] + result;
			}
		} else {
			if (current + 1 < arr.length - 1 && arr[current + 1] == '-'
					&& doubleChars.indexOf(arr[current + 2]) != -1) {
				result += arr[++current];
			}
			while (++current < arr.length && doubleChars.indexOf(arr[current]) != -1) {
				result += arr[current];
			}
		}

		return result;
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

	private static int getParenthesisDepth(String expression) {
		char[] parenthesisSequence = getParenthesisSequence(expression).toCharArray();
		int depth = 0;
		int currentBalance = 0;
		for (int i = 0; i < parenthesisSequence.length; i++) {
			if (parenthesisSequence[i] == '(') {
				++currentBalance;
			}
			if (parenthesisSequence[i] == ')') {
				--currentBalance;
			}
			if (depth < currentBalance) {
				depth = currentBalance;
			}
		}
		return depth;
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

	private static String getRegexExpression(String exp) {
		for (char specialChar : new char[] { '(', ')', '+', '-', '*', '/', '.' }) {
			exp = exp.replaceAll('[' + String.valueOf(specialChar) + ']', "[" + specialChar + "]");
		}
		return exp;
	}

	private static String removeAllOrderedParenthesis(String parenthesisSequence) {
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

}
