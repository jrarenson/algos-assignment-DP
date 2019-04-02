/**
 * Rod cutting problem described in Chapter 15 of textbook
 */
public class RodCutting {

	// Do not change the parameters!
	public int rodCuttingRecur(int rodLength, int[] lengthPrices) {
		//initializing cuts to zero
		int rodCut = 0;
		//Base case for rod length of 0
		if(rodLength <= 0){
			return 0;
		}

		for(int x = 0; x < rodLength; x++){
			rodCut = Math.max(rodCut, lengthPrices[x]+rodCuttingRecur(rodLength-x-1,lengthPrices));
		}
		return rodCut;
	}

	// Do not change the parameters!
	public int rodCuttingBottomUp(int rodLength, int[] lengthPrices) {
		int maxCut = 0;
		int values[] = new int[rodLength+1];
		for(int x = 0; x <= rodLength; x++){
			for(int y = 0; y<x; y++){
				//finds the max cut value for the rods
				maxCut = Math.max(maxCut, values[y]+lengthPrices[x-y-1]);
			}
			values[x] = maxCut;
		}
		return values[rodLength];
	}
	
	public static void main(String args[]){
		RodCutting rc = new RodCutting();
		// In your turned in copy, do not touch the below lines of code.
		// Make sure below is your only output.
		int length1 = 7;
		int[] prices1 = {1, 4, 7, 3, 19, 5, 12};
		int length2 = 14;
		int[] prices2 = {2, 5, 1, 6, 11, 15, 17, 12, 13, 9, 10, 22, 18, 26};
		int maxSell1Recur = rc.rodCuttingRecur(length1, prices1);
		int maxSell1Bottom = rc.rodCuttingBottomUp(length1, prices1);
		int maxSell2Recur = rc.rodCuttingRecur(length2, prices2);
		int maxSell2Bottom = rc.rodCuttingBottomUp(length2, prices2);
		System.out.println(maxSell1Recur + " " + maxSell1Bottom);
		System.out.println(maxSell2Recur + " " + maxSell2Bottom);
	}
}