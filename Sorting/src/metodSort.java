
 public class metodSort {

	static void puzur(int mas[]) {
		for (int j = 0; j < mas.length; j++) {
			for (int i = 0; i < mas.length - 1; i++) {
				if (mas[i] > mas[i + 1]) {
					int z = mas[i];
					mas[i] = mas[i + 1];
					mas[i + 1] = z;
				}
			}
		}
	}

	static void puzur(double mas[]) {
		for (int j = 0; j < mas.length; j++) {
			for (int i = 0; i < mas.length - 1; i++) {
				if (mas[i] > mas[i + 1]) {
					double z = mas[i];
					mas[i] = mas[i + 1];
					mas[i + 1] = z;
				}
			}
		}
	}

	static void peremena(int masiv[]) {
		int imin = 0;
		for (int i = 0; i < masiv.length; i++) {
			int min = masiv[i];
			for (int j = i + 1; j < masiv.length; j++) {

				if (masiv[j] < min) {
					min = masiv[j];
					imin = j;
				}
			}
			if (masiv[i] != min) {
				masiv[imin] = masiv[i];
				masiv[i] = min;
			}
		}
	}

	static void peremena(double masiv[]) {
		int imin = 0;
		for (int i = 0; i < masiv.length; i++) {
			double min = masiv[i];
			for (int j = i + 1; j < masiv.length; j++) {

				if (masiv[j] < min) {
					min = masiv[j];
					imin = j;
				}
			}
			if (masiv[i] != min) {
				masiv[imin] = masiv[i];
				masiv[i] = min;
			}
		}
	}

	static void vstavka(int a[]) {
		int x;
		int i, j;

		for (i = 0; i < a.length; i++) {
			x = a[i];

			for (j = i - 1; j >= 0 && a[j] > x; j--)
				a[j + 1] = a[j];

			a[j + 1] = x;
		}
	}
	static void vstavka(double a[]) {
		double x;
		int i, j;

		for (i = 0; i < a.length; i++) {
			x = a[i];

			for (j = i - 1; j >= 0 && a[j] > x; j--)
				a[j + 1] = a[j];

			a[j + 1] = x;
		}
	}
	static void vuvod( int mas[],String str ){
		for (int k = 0; k < mas.length; k++)
			System.out.print("[" + mas[k] + "] ");
		System.out.println(str);
	}
	static void vuvod( double mas[],String str ){
		for (int k = 0; k < mas.length; k++)
			System.out.print("[" + mas[k] + "] ");
		System.out.println(str);
	}

}
