import java.io.*;
import java.util.*;

public class codeeval_xad_package{
	
	static boolean isDebug = false;

	public static void main(String[] args){
		if(isDebug) System.out.println(args[0]);
		String filePath = args[0];
		BufferedReader br = null;
		try{
			String lineString;
			br = new BufferedReader(new FileReader(filePath));
			while ((lineString = br.readLine())!= null ){
				lineString = lineString.trim();
				if( !lineString.equals("") &&  lineString.contains(":")){
					processLine(lineString);
				}
			}
		}catch(IOException e){
			e.printStackTrace();
		}finally{
			try{if(br != null) br.close();}
			catch(Exception Ex){Ex.printStackTrace();}
			br = null;
		}
	}

	private static void processLine(String lineString){
		try{
			int W;
			int[] w;
			int[] v;

			if(lineString.contains(":")){
				String[]  parts = lineString.split(":");
				if(parts.length == 2){
					W =(int)( 100 * Double.parseDouble(parts[0].trim()));
					lineString = parts[1].trim(); 
					StringTokenizer strTokens = new  StringTokenizer(lineString, ")");
					int itemsCount = strTokens.countTokens();
					w = new int[itemsCount];
					v = new int[itemsCount];
					String temp;  int index = 0;
					while(strTokens.hasMoreTokens()){
						temp = strTokens.nextToken();
						temp = temp.replace("(", "");
						temp = temp.trim();
						parts = temp.split(","); 
						if(parts.length == 3){
							w[index] = (int)( 100 * Double.parseDouble(parts[1].trim()));
							v[index] = (int)( 100 * Double.parseDouble(parts[2].trim().replace("$","")));
						}
						index++;
					}
					if(isDebug) System.out.println("Item count = " + index);
					int[] sel = knapsack_dp(index, W, w, v);
					if(sel.length == 0)
						System.out.println("-");
					else {
						for(int i = 0; i < sel.length; i++){
							if(i > 0)
								System.out.print(", ");
							System.out.print("" + sel[i] );
						}
						System.out.println("");
					}
				}
			}

		}catch(Exception Ex){Ex.printStackTrace();}
	}

	private static void knapsack_memo(int n, int W, int[] w, int[] v){

	}

	private static  int[] knapsack_dp(int n, int W, int[] w, int[] v){
		int[][] C = new int[n + 1][W + 1];
		for(int i = 0; i <= n; i++ ){
			C[i][0] = 0;
		}
		for(int j = 0; j <= W; j++ ){
			C[0][j] = 0;
		}
		for(int i = 1; i <= n; i++){
			for(int j = 1; j <= W; j++ ){
				if(w[i-1] <= j) 
		C[i][j] = Math.max( C[i-1][j], C[i-1][j-w[i-1]] + v[i-1]);
				else
					C[i][j] = C[i-1][j];

			}
		}
		//Now, the best value is at C[n][W]
		//Now, reconstructions remains
		ArrayList<Integer> itemsSelected = new ArrayList<Integer>();
		int i = n; 
		int j = W;
		while(i > 0 && j > 0){
			if(C[i][j] != C[i-1][j]){
				itemsSelected.add(i);
				j = j - w[i-1];
				i = i - 1;
			}else{
				i = i - 1;
			}
		}
		//Reversing hte result 
		int[] itemsToReturn = new int[itemsSelected.size()];
		for(i = 0 ; i < itemsSelected.size() ; i++){
itemsToReturn[i] = itemsSelected.get(itemsSelected.size() - 1  - i);
		}
		return itemsToReturn;
	}

}
