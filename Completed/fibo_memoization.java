public class fibo_memoization{
	
	static int[] values;

	public static void main(String[] args){
		System.out.println("YES");
		System.out.println(args[0]);
		int n = Integer.parseInt(args[0]);
		values = new int[n + 1];
		values[0] = 0;
		values[1] = 1;
		for(int i=2; i <=n; i++){
			values[i] = -1;
		}
		System.out.println("Fib = " +  fib(n));
	}

	private static int fib(int n){
		if(values[n] == -1 ) 
			values[n] = (fib(n-1) + fib(n-2));
		return values[n];
	}

}
