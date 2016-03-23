public class vstavka2 {

	public static void main(String[] args) {

		int a[] = {7,9,3,8,2,1,6,4,5};

		System.out.print("a:   ");
		for (int i = 0; i < a.length; i++) {
			System.out.print(" [ " + a[i] + " ] ");
		}

		long startTime = System.currentTimeMillis();
		
		int x;
		  int i, j;
		  

		  for ( i=0; i < a.length; i++) {  
		    x = a[i];
				
			 
		    for ( j=i-1; j>=0 && a[j] > x; j--)
		      a[j+1] = a[j];  
			
		    a[j+1] = x;
		  }

		  long endTime = System.currentTimeMillis();
		  
		System.out.println();
		System.out.print("a:   ");
		for ( i = 0; i < a.length; i++) {
			System.out.print(" [ " + a[i] + " ] ");
		}

		System.out.println();
		System.out.println(endTime - startTime);
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

	}

}
