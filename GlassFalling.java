/**
 * Glass Falling
 */
public class GlassFalling {

	// Do not change the parameters!
	public int glassFallingRecur(int floors, int sheets) {
		// Fill in here and change the return
		//base case
		if(sheets == 1 || floors == 0 || floors == 1){
			return floors;
		}

		int minNum = Integer.MAX_VALUE;
		int value;

		//Checks for all of the glass falls and returns minNum+1
		for(int x = 1; x <= floors; x++){
			value = Math.max(glassFallingRecur(x - 1, sheets - 1), 
					glassFallingRecur(floors - x, sheets));
			if(value < minNum){
				minNum = value;
			}
		}
		return minNum+1;
	}


	// Optional:
	// Pick whatever parameters you want to, just make sure to return an int.
	public int glassFallingMemoized(int floors, int sheets) {
		// Fill in here and change the return
		int memoizeArr[][] = new int [floors + 1][sheets + 1];
		for (int x = 0; x <= floors; x++) {
			for(int y = 1; y <= sheets; y++){
				memoizeArr[x][y] = 0;
			}
		}
		return glassFallingHelper(memoizeArr, floors, sheets);
	}

	private int glassFallingHelper(int memoizeArr[][],int floors, int sheets) {
		//base cases
		if((floors <= 1) || (sheets == 1)){
			return memoizeArr[floors][sheets]=floors;
		}if(memoizeArr[floors][sheets]!= -0){
		    return memoizeArr[floors][sheets];
		}

		int value;
		int minNum = 100;
		for (int i = 1; i <= floors; i++) {
			value = Math.max(glassFallingHelper(memoizeArr,i-1,sheets-1),
					glassFallingHelper(memoizeArr,floors-i,sheets));
			if(value < minNum)
				minNum = value;
		}
		return memoizeArr[floors][sheets]= minNum+1;	
	}

	// Do not change the parameters!
	public int glassFallingBottomUp(int floors, int sheets) {
		// Fill in here and change the return
		int [][] minNum = new int[sheets+1][floors+1];
		//base case
		for(int i = 1; i <= sheets; i++){
			minNum[i][1] = 1; //for one floor
			minNum[i][0] = 0; //for zero floors
		}
		for(int i = 1; i <= floors; i++){
			minNum[1][i] = i;
		}

		for(int x = 2; x <= sheets; x++){
			for(int y = 2; y <= floors; y++){
				minNum[x][y] = Integer.MAX_VALUE;
				int ans;
				for(int k = 1; k <= y; k++){
					ans = 1+Math.max(minNum[x-1][k-1], minNum[x][y-k]);
					if(ans < minNum[x][y]){
						minNum[x][y] = ans;
					}
				}
			}
		}
		return minNum[sheets][floors];
	}

	public static void main(String args[]){
		GlassFalling gf = new GlassFalling();
		// in your final turned-in copy, these are the only things printed
		int minTrials1Recur = gf.glassFallingRecur(27, 2);
		int minTrials1Bottom = gf.glassFallingBottomUp(27, 2);
		int minTrials2Memo = gf.glassFallingMemoized(100, 3);
		int minTrials2Bottom = gf.glassFallingBottomUp(100, 3);
		System.out.println(minTrials1Recur + " " + minTrials1Bottom);
		System.out.println(minTrials2Memo + " " + minTrials2Bottom);
	}
}