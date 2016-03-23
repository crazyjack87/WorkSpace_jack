
public class rekur {
			int values[];
			rekur(int i) {
		values = new int[i];
		}
		
		void printArray(int i) {
		if(i==0) return;
		else printArray(i-1);
		System.out.println (" [" + (i) + "] " + values[i-1]);
		}
			
	public static void main(String[] args) {
		
		rekur ob = new rekur(10);
	
		ob.printArray(10);
		

	}

}
