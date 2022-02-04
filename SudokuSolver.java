import java.util.*;


public class SudokuSolver {
	
	public static int[][] sud =new int[9][9];
	public static int[]maxer = new int[9];
	public static Scanner sc = new Scanner(System.in);
	public static int n,row,col,max=0;
	
	public static void main(String[] args) {

		showSample();
		
		getInfo();
		solveSudoku(0,0);
		beautifyPrint();
		
		
	}
	

	
	public static void  getInfo() {
		
		System.out.print("Number of cell values available : ");
		n = sc.nextInt();
		
		for(int c =0 ;c<n;c++) {
			boolean flag = false;
			do {
				System.out.print("Enter row and column number for input : ");
				row = sc.nextInt();
				col = sc.nextInt();
				try {
					if(row>9 || col>9) {
						throw new Exception("Invalid row and column number ");
					}
					System.out.print("Enter value : ");
					sud[row-1][col-1]=sc.nextInt();
					if(sud[row-1][col-1]>0 && sud[row-1][col-1]<10) {
						maxer[sud[row-1][col-1]-1]++;
						flag=true;
					}
					else {
						throw new Exception("Enter Number between 1 and 9 inclusively");
					}
				}
				catch(Exception e) {
					System.out.println(e);
				}
				
			}while(flag==false);
		}
		
		
		for(int i=0;i<9;i++) {
			if(max>maxer[i]) {
				max=maxer[i];
			}
			max+=1;
		}
		
		System.out.println(max);
	}
	

	public static boolean solveSudoku(int row, int col) {
		
		
		if(row==sud.length) {
			return true;
		}
		
		int traverseRow=0,traverseCol=0;
		
		if(col == sud.length-1) {
			traverseRow=row+1;
			traverseCol=0;
		}
		else {
			traverseRow=row;
			traverseCol=col+1;
		}
		
		if(sud[row][col]!=0) {
			if(solveSudoku(traverseRow,traverseCol)) {
				return true;
			}
		}
		else {
			for(int num=1;num<10;num++) {
				if(numberValidity(row,col,num)) {
					sud[row][col]=num;
					if(solveSudoku(traverseRow,traverseCol)) {
						return true;
					}
					else {
						sud[row][col]=0;
					}
				}
			}
		}
		
		return false;
	}
	
	
	public static boolean numberValidity(int row, int col,int number) {
		
		for(int tester=0;tester<sud.length;tester++) {
			if(sud[tester][col]==number) {
				return false;
			}
			if(sud[row][tester]==number) {
				return false;
			}
		}
		
		int gridRowStart = (row/3)*3, gridColStart=(col/3)*3;
		int gridRowEnd = ((row/3)*3)+3, gridColEnd=((col/3)*3)+3;
		
		
		for(int i=gridRowStart;i<gridRowEnd;i++) {
			for(int j=gridColStart;j<gridColEnd;j++) {
				if(sud[i][j]==number) {
					return false;
				}
			}			
		}
		
		
		return true;
	}
	
	
	public static void  beautifyPrint() {

		System.out.print("\n-----------------------------------------------\n");
		int count=1;
		for(int i=0;i<9;i++) {
			
			for(int j=0;j<9;j++) {

				if(sud[i][j]!=0) {
					System.out.print(" | "+sud[i][j]+" ");
				}
				else {

					System.out.print(" |   ");
				}

			}
			System.out.print("|\n-----------------------------------------------\n");
		}
	}
	

	public static void  showSample() {

		System.out.print("\n----------------------------------------------------------\n");
		int count=1;
		for(int i=0;i<9;i++) {
			
			for(int j=0;j<9;j++) {
				System.out.print(" | "+(i+1)+(j+1)+" ");

			}
			System.out.print("|\n---------------------------------------------------------\n");
		}
		System.out.print("\nAbove shown is sample with row and column number. Use for refrence\n\n");
	}


}
