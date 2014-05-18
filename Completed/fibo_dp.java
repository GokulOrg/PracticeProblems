public class fibo_dp{
	
	static int[] values;

	public static void main(String[] args){
		System.out.println("YES");
		System.out.println(args[0]);
		int n = Integer.parseInt(args[0]);
		values = new int[n + 1];
		values[0] = 0;
		values[1] = 1;
		for(int i = 2; i <= n; i++){
			values[i] = values[i-1] + values[i-2];
		}

		System.out.println("Fib = " +  values[n]);
	}

}
