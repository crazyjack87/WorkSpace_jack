
public class ExpressionCalculator {

	static public double calculate(String str1) {

		str1 = str1.replace(" ", "");
		str1 = proverkaSkoba(str1);

		int KolichestvoParSkoba = KolichestvoParSkoba(str1);
		if (KolichestvoParSkoba != 0) {
			StringBuffer str1_buffer = new StringBuffer(str1.subSequence(0, str1.length()));
			for (int i = 0; i < KolichestvoParSkoba; i++) {
				int IndexSkobok[] = new int[2];
				IndexSkobok = poiskIndexGlavnogoVurojenie(str1, KolichestvoParSkoba);
				String VurajeniNeVGlavnuhSkobka = "";
				VurajeniNeVGlavnuhSkobka = poiskGlavnogoVurajenia(str1, KolichestvoParSkoba);
				VurajeniNeVGlavnuhSkobka = PodschetVskobkah(VurajeniNeVGlavnuhSkobka);
				str1_buffer = str1_buffer.delete(IndexSkobok[0], IndexSkobok[1]);
				str1_buffer = str1_buffer.insert(IndexSkobok[0], VurajeniNeVGlavnuhSkobka);
				str1_buffer.toString();

			}
		}
		return tryParse(str1);
	}

	public static void main(String args[]) {
		String str1 = new String("2+2+5+(6+7+50+3*10)");
		// int[] y = poiskIndexGlavnogoVurojenie(str1, 2);
		// // String MasivVurajeniVskobka = "====";
		// // StringBuffer strr22;
		// // strr22 = str1.delete(2, 6);
		// // strr22 = poiskIndexGlavnogoVurojenie(str1,2);
		// System.out.println(str1.charAt(y[0]));
		// // int []
		// // index=poiskIndexGlavnogoVurojenie(str1,KolichestvoParSkoba(str1));
		// for (int i = 0; i < y.length; i++) {
		// System.out.println(y[i]);
		// }

		str1 = str1.replace(" ", "");
		str1 = proverkaSkoba(str1);

		int KolichestvoParSkoba = KolichestvoParSkoba(str1);
		int IndexSkobok[] = new int[2];
		StringBuffer str1_buffer = new StringBuffer(str1.subSequence(0, str1.length()));
		String VurajeniNeVGlavnuhSkobka = "";
		if (KolichestvoParSkoba != 0) {
			for (int i = 0; i < KolichestvoParSkoba; i++) {
				IndexSkobok = poiskIndexGlavnogoVurojenie(str1, KolichestvoParSkoba);
				VurajeniNeVGlavnuhSkobka = poiskGlavnogoVurajenia(str1, KolichestvoParSkoba);
				VurajeniNeVGlavnuhSkobka = PodschetVskobkah(VurajeniNeVGlavnuhSkobka);
				// str1_buffer = str1_buffer.delete(IndexSkobok[0],
				// IndexSkobok[1]);
				// str1_buffer = str1_buffer.insert(IndexSkobok[0],
				// VurajeniNeVGlavnuhSkobka);
				// str1_buffer.replace(IndexSkobok[0], IndexSkobok[1],
				// VurajeniNeVGlavnuhSkobka);

				// str1 = str1_buffer.toString();
			}
		}
		System.out.println(VurajeniNeVGlavnuhSkobka);
		// System.out.println(IndexSkobok[0]);
		// System.out.println(IndexSkobok[1]);
	}

	static private String PodschetVskobkah(String str1) {

		String chislaBezZnakov = str1.replaceAll("(\\*)", " ").replaceAll("(\\/)", " ").replaceAll("(\\+)", " ")
				.replaceAll("(\\-)", " ");
		String[] masivChislaBezZnakov = chislaBezZnakov.split(" ");
		String ZnakiBezChisel = str1.replaceAll("[0-9]", "").replace(".", "");
		for (int i = 0; i < ZnakiBezChisel.length(); i++) {

			switch (ZnakiBezChisel.charAt(i)) {
			case '*':
				masivChislaBezZnakov[i + 1] = Double
						.toString(tryParse(masivChislaBezZnakov[i]) * tryParse(masivChislaBezZnakov[i + 1]));
				masivChislaBezZnakov[i] = "";

				break;
			case '/':
				masivChislaBezZnakov[i + 1] = Double
						.toString(tryParse(masivChislaBezZnakov[i]) / tryParse(masivChislaBezZnakov[i + 1]));
				masivChislaBezZnakov[i] = "";
				break;
			}
		}
		String vurajeniePosleDilen = "";
		for (int i = 0; i < masivChislaBezZnakov.length; i++) {
			vurajeniePosleDilen += masivChislaBezZnakov[i];
			if (ZnakiBezChisel.length() != i) {
				if (ZnakiBezChisel.charAt(i) == '+') {
					vurajeniePosleDilen += ZnakiBezChisel.charAt(i);
				} else if (ZnakiBezChisel.charAt(i) == '-') {
					vurajeniePosleDilen += ZnakiBezChisel.charAt(i);
				}
			}
		}
		vurajeniePosleDilen = vurajeniePosleDilen.replaceAll(" ", "");
		String chislaBezZnakovPosleDilen = vurajeniePosleDilen.replaceAll("(\\+)", " ").replaceAll("(\\-)", " ");
		String ZnakiBezChiselPosleDilenia = vurajeniePosleDilen.replaceAll("[0-9]", "").replace(".", "");
		String[] masivChislaBezZnakovPosleDilen = chislaBezZnakovPosleDilen.split(" ");
		String result = "";
		for (int i = 0; i < ZnakiBezChiselPosleDilenia.length(); i++) {

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
		result += masivChislaBezZnakovPosleDilen[masivChislaBezZnakovPosleDilen.length - 1];
		// if (result.indexOf(".") != -1) {
		// result = result.substring(0, result.indexOf("[.]"));
		// }
		return result;
	}

	static private String poiskGlavnogoVurajenia(String str1, int KolichestvoParSkoba) {
		if (KolichestvoParSkoba == 0)
			return str1;
		char[] charStr = str1.toCharArray();
		String str2 = "";
		int Skoba = 0;
		for (int i = charStr.length - 1; i > 0; i--) {
			if (charStr[i] == ')') {
				Skoba += 1;
			}
			if (Skoba == KolichestvoParSkoba) {
				i = str1.lastIndexOf("(", i);
				while (charStr[++i] != ')') {
					str2 += charStr[i];
				}
				break;
			}
		}
		return str2;
	}

	static private int[] poiskIndexGlavnogoVurojenie(String str1, int KolichestvoParSkoba) {
		int[] IndexSkobok = new int[2];
		int OtkrutaiaSkoba = 0;
		char[] charStr = str1.toCharArray();
		for (int i = charStr.length - 1; i > 0; i--) {
			if (charStr[i] == ')') {
				OtkrutaiaSkoba += 1;
			}
			if (OtkrutaiaSkoba == KolichestvoParSkoba) {
				IndexSkobok[1] = i;
				IndexSkobok[0] = str1.lastIndexOf("(", i);
				break;
			}
		}
		return IndexSkobok;
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
