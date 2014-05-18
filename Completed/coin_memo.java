public class coin_memo{
	/*
	Given a set of available coins S={s1, s2, s3,... ,sm}
	And a required amount, N >=0
	Find the number of ways N can be made using coins from s. 
	Note: An unlimited number of each coin is S are available
	*/
 	static int[] S =  {1, 2, 3};
	static int[][] C;

	public static void main(String[] args){
		System.out.println("YES");
		System.out.println(args[0]);
		int N = Integer.parseInt(args[0]);
		C = new int[N+1][ S.length + 1];
		for(int i=0; i<= S.length; i++){
			for(int j =0; j <=N; j++){
				C[j][ i] = -1;
			}
		}
		System.out.println("C = " +  calc_C(N, S.length));
	}

	private static int calc_C(int N, int m){
		if(N == 0) return 1;
		else if (N < 0) return 0;
		else if (m <= 0) return 0;
		else if (C[N][ m] != -1) return C[N][m];
		else C[N][ m] = (calc_C(N, m-1) + calc_C(N-S[m-1], m));
		return C[N][ m];
	}

}
