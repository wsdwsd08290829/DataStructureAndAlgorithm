package com.sidausc.backtracking;
import java.util.ArrayList;
public class NQueens {
	static ArrayList<ArrayList<Integer>> results = new ArrayList<ArrayList<Integer>>();
	/*
	 * whether have result, eg 4 has, 3 has not
	 */
	static boolean hasNQueens(int N){
		int[] result = new int[N];
		return hasNQueens(0, N, result);
	}
	static boolean hasNQueens(int col, int N, int[] result){ //input columen		
		for(int i=0;i<N;i++){  //for each row of that columns
			if(check(result, col, i)){
				result[col] = i;
				if(col==N-1){
					//printArr(result);
					return true;
				}
				if(hasNQueens(col+1, N, result))return true;
				//backtrack here 
			}
		}
		return false;
	}
/*
 * printed out all possible solve of NQueens
 * result is array to indicate where queen should be placed
 * : index means column, value means row 
 */
	static void getNQueens(int N){
		int[] result = new int[N];
		getNQueens(0, N, result);
	}
	static void getNQueens(int col, int N, int[] result){ //input columen		
		for(int i=0;i<N;i++){  //for each row of that columns
			if(check(result, col, i)){
				result[col] = i;
				if(col==N-1){ //if pass check for last column
					printArr(result);
					ArrayList<Integer> list = new ArrayList<Integer>();
					for(int res: result){
						list.add(res);
					}
					results.add(list);
					//add result to list of list
					//I just printed out
					printMatrix(result);
				}
				getNQueens(col+1, N, result);
			}
		}
	}
	//if queen placed at col and row, check if conflict with current result 
	static boolean check(int[] result, int col, int row){
		for(int i=0;i<col;i++){
			if(result[i] == row){
			//	print(result);
				return false;
			}
		} //in same row
		for(int i=0;i<col;i++){
			if(result[i]-row == (col-i) || result[i]-row == (i-col)){
				return false;
			}
		}
		//print(result);
		return true;
	}
	static void printArr(int[] result){
		for(int i :result){
			System.out.print(i+ " ");
		}
		System.out.println();
	}
	//helper func to convert abstract "result[col] = row" to matrix representation
	static void printMatrix(int[] result){
		for(int i=0; i<result.length;i++){
			for(int j=0;j<result.length;j++){
				if(result[j] == i)System.out.print("1 ");
				else System.out.print("0 ");
			}
			System.out.println();
		}
		System.out.println();
	}
	public static void main(String[] args) {
		System.out.println(hasNQueens(12));
		getNQueens(12);
		System.out.println(results.size());
	}
}
