
public class ExpressionCalculator {

	static public double calculate(String str1) {
		if (str1.length() != 1) {
			str1 = str1.replace(" ", "");
			str1 = proverkaSkoba(str1);
			int plusMinusIndex = poiskPlusMinus(str1);
			String str1L, str1R;
			char[] charStr = str1.toCharArray();
			if (plusMinusIndex != -1) {
				str1L = str1.substring(0, plusMinusIndex);
				str1R = str1.substring(plusMinusIndex + 1);
				switch (charStr[plusMinusIndex]) {
				case '+':
					return calculate(str1L) + calculate(str1R);
				case '-':
					return calculate(str1L) - calculate(str1R);
				}
			}
			int dilenieIndex = poiskDilenia(charStr);
			if (dilenieIndex != -1) {
				str1L = str1.substring(0, dilenieIndex);
				str1R = str1.substring(dilenieIndex + 1);
				switch (charStr[dilenieIndex]) {
				case '*':
					return calculate(str1L) * calculate(str1R);
				case '/':
					return calculate(str1L) / calculate(str1R);
				}
			}
		}
		return tryParse(str1);
	}

	public static void main(String args[]) {
		String str1 = ("((2*2)*2/2)");

		System.out.println(ExpressionCalculator.calculate(str1));
	}

	static private int poiskDilenia(char[] charStr) {
		int skoba = 0;
		for (int i = charStr.length - 1; i >= 0; i--) {
			switch (charStr[i]) {
			case '(':
				skoba += 1;
				break;
			case ')':
				skoba -= 1;
			}
			if ((charStr[i] == '*' || charStr[i] == '/') && skoba == 0) {

				return i;
			}
		}
		return -1;
	}

	static private int poiskPlusMinus(String str1) {
		char[] charStr = str1.toCharArray();
			int skoba = 0;
		for (int i = charStr.length - 1; i >= 0; i--) {
			switch (charStr[i]) {
			case '(':
				skoba += 1;
				break;
			case ')':
				skoba -= 1;
			}
			if ((charStr[i] == '+' || charStr[i] == '-') && skoba == 0) {
				return i;
			}
		}
		return -1;
	}

	static private String proverkaSkoba(String str1) {
		if (str1.indexOf('(') == 0 && str1.lastIndexOf(')') == str1.length() - 1) {
			String str2 = str1.substring(1, str1.length() - 1);
			char[] charStr = str2.toCharArray();
			String str3 = "";
			for (int i = 0; i < charStr.length; i++) {
				if (charStr[i] == '(' || charStr[i] == ')') {
					str3 += charStr[i];
				}
			}
			int polovinaStr = str3.length() / 2;
			for (int j = 0; j < polovinaStr; j++) {
				str3 = str3.replaceFirst("[(][)]", "");
			}
			if (str3.equals(""))
				return proverkaSkoba(str2);
		}
		return str1;
	}

	static private double tryParse(String expression) {
		try {
			return Double.parseDouble(expression);
		} catch (NumberFormatException e) {
			System.out.println("Ошибка в выражении: " + expression);
			System.out.println("Список допустимых символов: +-*/0123456789() и пробел.");
			throw e;
		}
	}
}
