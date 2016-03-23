public class vstavka {

	public static void main(String[] args) {

		int masiv[] = {7,9,3,8,2,1,6,4,5};

		System.out.print("masiv:   ");
		for (int i = 0; i < masiv.length; i++) {
			System.out.print(" [ " + masiv[i] + " ] ");
		}

		System.out.println();
		long startTime = System.currentTimeMillis();

		for (int i = 1; i < masiv.length; i++) {
			int temp = masiv[i];
			for (int j = 0; j < i; j++) {
				if (masiv[i] < masiv[j]) {
					for (int k = i - 1; k >= j; k--) {
						masiv[k + 1] = masiv[k];
					}
					masiv[j] = temp;
				}
			}
		}
		// 5|[1]342
		// 15|342
		// 15|[3]42
		// 135|42
		// 135|[4]2
		// 1345|2
		// 1345|[2]
		// 12345
		
		// 1345|[2]
		// 1*345|[2]
		// temp = [2]
		// 1*345->2
		// 134->55
		// 13->445
		// 13345
		// 12345

		long endTime = System.currentTimeMillis();

		System.out.print("masiv:   ");
		for (int i = 0; i < masiv.length; i++) {
			System.out.print(" [ " + masiv[i] + " ] ");
		}

		System.out.println();
		System.out.println(endTime - startTime);

	}

}
