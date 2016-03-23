
public class ExpressionCalculator {
//
//	static public double calculate(String str1) {
//		if (str1.length() != 1) {
//			str1 = str1.replace(" ", "");
//			str1 = proverkaSkoba(str1);
//			int KolichestvoParSkoba = KolichestvoParSkoba(str1);
//
//			int plusMinusIndex = poiskPlusMinus(str1);
//			String str1L, str1R;
//			char[] charStr = str1.toCharArray();
//			if (plusMinusIndex != -1) {
//				str1L = str1.substring(0, plusMinusIndex);
//				str1R = str1.substring(plusMinusIndex + 1);
//				switch (charStr[plusMinusIndex]) {
//				case '+':
//					return calculate(str1L) + calculate(str1R);
//				case '-':
//					return calculate(str1L) - calculate(str1R);
//				}
//			}
//			int dilenieIndex = poiskDilenia(charStr);
//			if (dilenieIndex != -1) {
//				str1L = str1.substring(0, dilenieIndex);
//				str1R = str1.substring(dilenieIndex + 1);
//				switch (charStr[dilenieIndex]) {
//				case '*':
//					return calculate(str1L) * calculate(str1R);
//				case '/':
//					return calculate(str1L) / calculate(str1R);
//				}
//			}
//		}
//		return tryParse(str1);
//	}

	public static void main(String args[]) {
		String str1 = ("(2*31)+(2*250/3-1*2)-2");
	
		System.out.println(ExpressionCalculator.poiskGlavnogoVurajenia(proverkaSkoba(str1), KolichestvoParSkoba(str1)));
//		System.out.println(ExpressionCalculator.calculate(str1));
		System.out.println(ExpressionCalculator.KolichestvoParSkoba(str1));

		System.out.println(str1);
		String[]str88=str1.split(("\\D"));
		String mmm =str1.replaceAll("\\(\\w*\\)", " ");
		String chislaBezZnakovPLUS= str1.replaceAll("(\\+)"," "); 
		String chislaBezZnakovMinus= str1.replaceAll("(\\-)"," "); 
		String chislaBezZnakovDil= str1.replaceAll("(\\/)"," "); 
		String chislaBezZnakovMnoj= str1.replaceAll("(\\*)"," "); 
		System.out.println(mmm);

//		for (int i=0; i<str88.length;i++)
//		System.out.println(str88[i]);
	}
	static private int poisk_Dil_Ymnoj(String str1) {
//		String chislaBezZnakov= str1.replace("\\D"," "); 
		
		int IndexMnojen =-1;
		int IndexDilen =-1;
		IndexMnojen=str1.indexOf("*",IndexMnojen+1);
		IndexDilen=str1.indexOf("/",IndexDilen+1);
		
		
		str1.split(("\\*"));

		return -1;
	}
	
	static private String poiskGlavnogoVurajenia(String str1, int KolichestvoParSkoba) {
		char[] charStr = str1.toCharArray();
		String str2 = "";
		int Skoba = 0;
		for (int i = 0; i < charStr.length-1; i++) {
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
