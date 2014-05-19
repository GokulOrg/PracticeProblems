import java.io.*;
import java.util.*;



public class codeeval_xad_package{
	
	static boolean isDebug = false;

	public static void main(String[] args){
		if(args.length > 1)
			isDebug = true;
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
			int[] i;
			int[] w;
			int[] v;

			if(lineString.contains(":")){
				String[]  parts = lineString.split(":");
				if(parts.length == 2){
					W =(int)( 100 * Double.parseDouble(parts[0].trim()));
					lineString = parts[1].trim(); 
					StringTokenizer strTokens = new  StringTokenizer(lineString, ")");
					int itemsCount = strTokens.countTokens();
					String tempStr; 
					ArrayList<Item> items = new ArrayList<Item>();
					Item tempItem;
					int tempI, tempW, tempV;
					while(strTokens.hasMoreTokens()){
						tempStr = strTokens.nextToken();
						tempStr = tempStr.replace("(", "");
						tempStr = tempStr.trim();
						parts = tempStr.split(","); 
						if(parts.length == 3){
							tempI = Integer.parseInt(parts[0].trim());
							tempW = (int)( 100 * Double.parseDouble(parts[1].trim()));
							tempV = (int)( 100 * Double.parseDouble(parts[2].trim().replace("$","")));
							tempItem = new Item(tempI, tempW, tempV);
							items.add(tempItem);
						}
					}
					if(isDebug) System.out.println("Before Sorting :");
					for(Item item : items){
						if(isDebug) item.print();
					}

					Collections.sort(items);
					if(isDebug) System.out.println("\nAfter Sorting :");
					for(Item item : items){
						if(isDebug) item.print();
					}
					if(isDebug) System.out.println("");
					int index = 0;
					i = new int[itemsCount];
					w = new int[itemsCount];
					v = new int[itemsCount];
					for(Item item : items){
						i[index] = item.getIndex();
						w[index] = item.getWeight();
						v[index] = item.getValue();
						index++;
					}
					if(isDebug) System.out.println("Item count = " + index);

					int[] sel = knapsack_dp(index, W, w, v);
					if(sel.length == 0)
						System.out.println("-");
					else {
						for(int i2 = 0; i2 < sel.length; i2++){
							if(i2 > 0)
								System.out.print(", ");
							System.out.print("" + i[sel[i2]-1] );
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
		//Reversing the result 
		int[] itemsToReturn = new int[itemsSelected.size()];
		for(i = 0 ; i < itemsSelected.size() ; i++){
itemsToReturn[i] = itemsSelected.get(itemsSelected.size() - 1  - i);
		}
		return itemsToReturn;
	}

}

	class Item
		implements Comparable<Item>
	{
		int index;
		Integer weight;
		Integer value;
		public Item(int i, int w, int v){
			index = i;
			weight = w;
			value = v;
		}
		
		public  int compareTo(Item other){
			if ( this.value.compareTo(other.value)  == 0 )
				return this.weight.compareTo(other.weight);
			else
				return other.value.compareTo(this.value);
		}
		public int getWeight(){
			return this.weight;
		}
		public int getValue(){
			return this.value;
		}
		public int getIndex(){
			return index;
		}
		public  void print(){
			System.out.print("( " + index + ", " + weight + ", " + value + " )");
		}
	}
