
public class ExpressionCalculator {
	
	 static public double calculate(String str1) {
	 if (str1.length() != 1) {
	 str1 = str1.replace(" ", "");
	 str1 = proverkaSkoba(str1);
	 int KolichestvoParSkoba = KolichestvoParSkoba(str1);
	
	// int plusMinusIndex = poiskPlusMinus(str1);
	// String str1L, str1R;
	// char[] charStr = str1.toCharArray();
	// if (plusMinusIndex != -1) {
	// str1L = str1.substring(0, plusMinusIndex);
	// str1R = str1.substring(plusMinusIndex + 1);
	// switch (charStr[plusMinusIndex]) {
	// case '+':
	// return calculate(str1L) + calculate(str1R);
	// case '-':
	// return calculate(str1L) - calculate(str1R);
	// }
	// }
	// int dilenieIndex = poiskDilenia(charStr);
	// if (dilenieIndex != -1) {
	// str1L = str1.substring(0, dilenieIndex);
	// str1R = str1.substring(dilenieIndex + 1);
	// switch (charStr[dilenieIndex]) {
	// case '*':
	// return calculate(str1L) * calculate(str1R);
	// case '/':
	// return calculate(str1L) / calculate(str1R);
	// }
	// }
	// }
	// return tryParse(str1);
	 }

	public static void main(String args[]) {
		String str1 = ("2*25*4+210*2-1+3");

		String chislaBezZnakov = poiskGlavnogoVurajenia(proverkaSkoba(str1), KolichestvoParSkoba(str1));
		chislaBezZnakov = str1.replaceAll("(\\*)", " ").replaceAll("(\\/)", " ").replaceAll("(\\+)", " ")
				.replaceAll("(\\-)", " ");
		String[] masivChislaBezZnakov = chislaBezZnakov.split(" ");
		String ZnakiBezChisel = str1.replaceAll("0", "").replaceAll("1", "").replaceAll("2", "").replaceAll("3", "")
				.replaceAll("4", "").replaceAll("5", "").replaceAll("6", "").replaceAll("7", "").replaceAll("8", "")
				.replaceAll("9", "").replace(".", "");
		for (int i = 0; i < ZnakiBezChisel.length(); i++) {

			switch (ZnakiBezChisel.charAt(i)) {
			case '*':
				masivChislaBezZnakov[i + 1] = Double
						.toString(tryParse(masivChislaBezZnakov[i]) * tryParse(masivChislaBezZnakov[i + 1]));
				ZnakiBezChisel = ZnakiBezChisel.replaceFirst("(\\*)", " ");
				masivChislaBezZnakov[i] = "";

				break;
			case '/':
				masivChislaBezZnakov[i + 1] = Double
						.toString(tryParse(masivChislaBezZnakov[i]) / tryParse(masivChislaBezZnakov[i + 1]));
				ZnakiBezChisel = ZnakiBezChisel.replaceFirst("(\\/)", " ");
				masivChislaBezZnakov[i] = "";
				break;
			}
		}
		String vurajeniePosleDilen = "";
		for (int i = 0, j = 1; j < masivChislaBezZnakov.length; i++, j++) {
			if (ZnakiBezChisel.charAt(i) == '+') {
				vurajeniePosleDilen += ZnakiBezChisel.charAt(i);
			} else if (ZnakiBezChisel.charAt(i) == '-') {
				vurajeniePosleDilen += ZnakiBezChisel.charAt(i);
			}
			vurajeniePosleDilen += masivChislaBezZnakov[j];
		}
		vurajeniePosleDilen = vurajeniePosleDilen.replaceAll(" ", "");
		String chislaBezZnakovPosleDilen = vurajeniePosleDilen.replaceAll("(\\+)", " ").replaceAll("(\\-)", " ");

		String ZnakiBezChiselPosleDilenia = vurajeniePosleDilen.replaceAll("0", "").replaceAll("1", "")
				.replaceAll("2", "").replaceAll("3", "").replaceAll("4", "").replaceAll("5", "").replaceAll("6", "")
				.replaceAll("7", "").replaceAll("8", "").replaceAll("9", "").replace(".", "");

		String[] masivChislaBezZnakovPosleDilen = chislaBezZnakovPosleDilen.split(" ");
		String result = "";
		for (int i = 0, j = 1; i < ZnakiBezChiselPosleDilenia.length(); i++, ++j) {

			switch (ZnakiBezChiselPosleDilenia.charAt(i)) {
			case '+':
				masivChislaBezZnakovPosleDilen[i + 1] = Double.toString(
						tryParse(masivChislaBezZnakovPosleDilen[i]) + tryParse(masivChislaBezZnakovPosleDilen[i + 1]));
				masivChislaBezZnakovPosleDilen[i] = "";
				break;
			case '-':
				masivChislaBezZnakovPosleDilen[i + 1] = Double.toString(
						tryParse(masivChislaBezZnakovPosleDilen[i]) - tryParse(masivChislaBezZnakovPosleDilen[i + 1]));
				masivChislaBezZnakovPosleDilen[i] = "";
				break;
			}
		}
		 result += masivChislaBezZnakovPosleDilen[masivChislaBezZnakovPosleDilen.length-1];

	}

	static private String PodschetVskobkah(String str1) {
		
		String chislaBezZnakov = poiskGlavnogoVurajenia(proverkaSkoba(str1), KolichestvoParSkoba(str1));
		chislaBezZnakov = str1.replaceAll("(\\*)", " ").replaceAll("(\\/)", " ").replaceAll("(\\+)", " ")
				.replaceAll("(\\-)", " ");
		String[] masivChislaBezZnakov = chislaBezZnakov.split(" ");
		String ZnakiBezChisel = str1.replaceAll("0", "").replaceAll("1", "").replaceAll("2", "").replaceAll("3", "")
				.replaceAll("4", "").replaceAll("5", "").replaceAll("6", "").replaceAll("7", "").replaceAll("8", "")
				.replaceAll("9", "").replace(".", "");
		for (int i = 0; i < ZnakiBezChisel.length(); i++) {

			switch (ZnakiBezChisel.charAt(i)) {
			case '*':
				masivChislaBezZnakov[i + 1] = Double
						.toString(tryParse(masivChislaBezZnakov[i]) * tryParse(masivChislaBezZnakov[i + 1]));
				ZnakiBezChisel = ZnakiBezChisel.replaceFirst("(\\*)", " ");
				masivChislaBezZnakov[i] = "";

				break;
			case '/':
				masivChislaBezZnakov[i + 1] = Double
						.toString(tryParse(masivChislaBezZnakov[i]) / tryParse(masivChislaBezZnakov[i + 1]));
				ZnakiBezChisel = ZnakiBezChisel.replaceFirst("(\\/)", " ");
				masivChislaBezZnakov[i] = "";
				break;
			}
		}
		String vurajeniePosleDilen = "";
		for (int i = 0, j = 1; j < masivChislaBezZnakov.length; i++, j++) {
			if (ZnakiBezChisel.charAt(i) == '+') {
				vurajeniePosleDilen += ZnakiBezChisel.charAt(i);
			} else if (ZnakiBezChisel.charAt(i) == '-') {
				vurajeniePosleDilen += ZnakiBezChisel.charAt(i);
			}
			vurajeniePosleDilen += masivChislaBezZnakov[j];
		}
		vurajeniePosleDilen = vurajeniePosleDilen.replaceAll(" ", "");
		String chislaBezZnakovPosleDilen = vurajeniePosleDilen.replaceAll("(\\+)", " ").replaceAll("(\\-)", " ");

		String ZnakiBezChiselPosleDilenia = vurajeniePosleDilen.replaceAll("0", "").replaceAll("1", "")
				.replaceAll("2", "").replaceAll("3", "").replaceAll("4", "").replaceAll("5", "").replaceAll("6", "")
				.replaceAll("7", "").replaceAll("8", "").replaceAll("9", "").replace(".", "");

		String[] masivChislaBezZnakovPosleDilen = chislaBezZnakovPosleDilen.split(" ");
		String result = "";
		for (int i = 0, j = 1; i < ZnakiBezChiselPosleDilenia.length(); i++, ++j) {

			switch (ZnakiBezChiselPosleDilenia.charAt(i)) {
			case '+':
				masivChislaBezZnakovPosleDilen[i + 1] = Double.toString(
						tryParse(masivChislaBezZnakovPosleDilen[i]) + tryParse(masivChislaBezZnakovPosleDilen[i + 1]));
				masivChislaBezZnakovPosleDilen[i] = "";
				break;
			case '-':
				masivChislaBezZnakovPosleDilen[i + 1] = Double.toString(
						tryParse(masivChislaBezZnakovPosleDilen[i]) - tryParse(masivChislaBezZnakovPosleDilen[i + 1]));
				masivChislaBezZnakovPosleDilen[i] = "";
				break;
			}
		}
		 return result += masivChislaBezZnakovPosleDilen[masivChislaBezZnakovPosleDilen.length-1];


	}

	static private String poiskGlavnogoVurajenia(String str1, int KolichestvoParSkoba) {
		if (KolichestvoParSkoba == 0)
			return str1;
		char[] charStr = str1.toCharArray();
		String str2 = "";
		int Skoba = 0;
		for (int i = 0; i < charStr.length - 1; i++) {
			if (charStr[i] == '(') {
				Skoba += 1;
			}
			if (Skoba == KolichestvoParSkoba) {
				while (charStr[++i] != ')') {
					str2 += charStr[i];
				}
				break;
			}
		}
		return str2;
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

	static private int KolichestvoParSkoba(String str1) {
		int KolichestvoParSkoba;
		char[] charStr = str1.toCharArray();
		String str2 = "";
		for (int i = 0; i < charStr.length; i++) {
			if (charStr[i] == '(' || charStr[i] == ')') {
				str2 += charStr[i];
			}
		}
		KolichestvoParSkoba = str2.length() / 2;
		return KolichestvoParSkoba;
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
