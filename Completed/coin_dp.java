public class coin_dp{
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
		System.out.println("C = " +  calc_C(N, S.length));
	}

	private static int calc_C(int N, int m){
		for(int i =0; i <=N; i++){
			for(int j=0; j<= m; j++){
				if (i == 0) C[i][j] = 1;
				else if (j == 0)  C[i][j] = 0;
				else if (S[j-1] > i) C[i][j] = C[i][j-1];
				else C[i][ j] = C[i-S[j-1]][j] + C[i][j-1];
			}
		}
		return C[N][ m];
	}

}
