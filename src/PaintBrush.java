import java.util.Arrays;
import java.util.Scanner;

public class PaintBrush {

	public static void main(String[] args) {
		// the board is 20x30
		char[][] board = fillBoard();
		
		printArray(board);
		
		Scanner sc = new Scanner(System.in);
		boolean exit = false;
		
		while(!exit){
			System.out.println("Choose drawing item (b - brush, f - fill with color, "
					+ "\nh - draw horizontal line, v - draw vertical line, s - 45 degree line, "
					+ "\nr - draw rectangle, t - draw triangle, e - eraser, x - exit)");
			char item = sc.next().charAt(0);
			
			
			switch (item) {
			case 'b':
				System.out.println("enter number of fields to be colored");
				int brush = sc.nextInt();
				System.out.println("Choose color (r -red, g - green, b - blue, etc.)");
				char color = sc.next().charAt(0);
				while (brush > 0) {
					System.out.println("enter pixel X coordiante ");
					int x = checkX(sc, 0);
					System.out.println("enter pixel Y coordiante ");
					int y = checkY(sc, 0);
					drawWithBrush(board,y,x,color);
					printArray(board);
					brush--;
					if (brush == 0) {
						System.out.println("Continue drawing with brush? if yes type number of strokes"
								+ "\n if no type 0");
						brush += sc.nextInt();
					}
				}
				break;
			case 'f':
				boolean drawMore = true;
				while (drawMore) {
					int total = board.length*board[0].length;
					System.out.println("Choose color (r -red, g - green, b - blue, etc.)");
					color = sc.next().charAt(0);
					
					System.out.println("enter pixel X coordiante ");
					int x = checkX(sc, 0);
					System.out.println("enter pixel Y coordiante ");
					int y = checkY(sc, 0);
					
					fillArea(board,y,x,total, color);
					printArray(board);
					
					System.out.println("continue filling (y/n) ?");
					if (sc.next().charAt(0) == 'n') {
						drawMore = false;
					}
				}
				break;
			case 'h':
				System.out.println("How many lines would you draw?");
				int numLines = sc.nextInt();
				while (numLines >0) {
					
					System.out.println("Choose color (r -red, g - green, b - blue, etc.)");
					color = sc.next().charAt(0);
					
					System.out.println("Enter length of the line");
					int length = sc.nextInt();
					System.out.println("enter start of line X coordiante ");
					int x = checkX(sc, 0);
					System.out.println("enter start of line Y coordiante ");
					int y = checkY(sc, 0);
					drawHLine(board,y,x,color, length);
					printArray(board);
					numLines--;
					if (numLines == 0) {
						System.out.println("Need more lines? if yes type number of lines"
								+ "\n if no type 0");
						numLines += sc.nextInt();
					}
				}
				break;
			case 'v':
				System.out.println("How many lines would you draw?");
				int numVLines = sc.nextInt();
				while (numVLines >0) {
					
					System.out.println("Choose color (r -red, g - green, b - blue, etc.)");
					color = sc.next().charAt(0);
					
					System.out.println("Enter length of the line");
					int length = sc.nextInt();
					System.out.println("enter start of line X coordiante ");
					int x = checkX(sc, 0);
					System.out.println("enter start of line Y coordiante ");
					int y = checkY(sc, 0);
					drawVLine(board,y,x,color, length);
					printArray(board);
					numVLines--;
					if (numVLines == 0) {
						System.out.println("Need more lines? if yes type number of lines"
								+ "\n if no type 0");
						numVLines += sc.nextInt();
					}
				}
				break;
			case 's':
				
				//Check if dimensions if length > x,y or else
				
				System.out.println("How many lines would you draw?");
				int numSLines = sc.nextInt();
				while (numSLines >0) {
					
					System.out.println("Choose color (r -red, g - green, b - blue, etc.)");
					color = sc.next().charAt(0);
					
					System.out.println("Enter length of the line");
					int length = sc.nextInt();
					System.out.println("is it clockwise '/' (y/n)?");
					boolean isClockWise = sc.next().charAt(0) == 'y';
					System.out.println("enter start of line X coordiante ");
					int x = checkX(sc, 0);
					System.out.println("enter start of line Y coordiante ");
					int y = checkY(sc, 0);
					drawSLine(board,y,x,color, length, isClockWise);
					printArray(board);
					numSLines--;
					if (numSLines == 0) {
						System.out.println("Need more lines? if yes type number of lines"
								+ "\n if no type 0");
						numSLines += sc.nextInt();
					}
				} 
				
				break;
			case 'r':
				int numRect = 1;
				while (numRect >0) {
					System.out.println("Choose color (r -red, g - green, b - blue, etc.)");
					color = sc.next().charAt(0);
					
					System.out.println("enter X coordiante of top left corner");
					int x = checkX(sc, 0);
					System.out.println("enter Y coordiante of top left corner");
					int y = checkY(sc, 0);
					
					System.out.println("Enter height of the rect");
					int height = sc.nextInt();
					System.out.println("Enter width of the rect");
					int width = sc.nextInt();
					
					drawRect(board,y,x,color, width, height);
					
					printArray(board);
					
					numRect--;
					if (numRect == 0) {
							System.out.println("Need more rectangles? if yes type number of rectangles"
									+ "\n if no type 0");
							numRect += sc.nextInt();
					}
				}
				break;
			case 'e':
				int erase = 1;
				while (erase > 0) {
					System.out.println("enter pixel X coordiante ");
					int x = checkX(sc, 0);
					System.out.println("enter pixel Y coordiante ");
					int y = checkY(sc, 0);
					erase(board, y, x);
					printArray(board);
					erase--;
					if (erase == 0) {
						System.out.println("Continue erasing ? if yes type number of strokes"
								+ "\n if no type 0");
						erase += sc.nextInt();
					}
				}
				break;
			case 'x':
				System.out.println("Goodbye!");
				exit = true;
				break;
			default:
				System.out.println("Wrong input!");
				break;
			}
		}
		
		sc.close();
		
	}
	private static void drawRect(char[][] board, int y, int x, char color, int width, int height) {
		drawHLine(board,y,x,color, width);
		drawVLine(board,y,x,color, height);
		if (x+width-1 >= board[y].length) {
			width = board.length - x;
		}
		if (y+height-1 >= board.length) {
			height = board.length - y;
			
		} else {
			drawHLine(board,y+height-1,x,color, width);
			
			drawVLine(board,y,x+width-1,color, height);
		}
		
	}
	private static int checkY(Scanner sc, int y) {
		y= sc.nextInt();
		if (y >=20 || y <0) {
			System.out.println("Y must be between 0 - 19.");
			return checkY(sc,y);
		} else {
			return y;
		}
	}
	
	private static int checkX(Scanner sc, int x) {
			x = sc.nextInt();
			if (x >= 30 || x < 0) {
				System.out.println("X must be between 0 - 29.");
				return checkX(sc,x);
			}else {
				return x;
			}
		}
	
	private static void drawSLine(char[][] board, int y, int x, char color, int length, boolean isClockWise) {
		
		if (length == 0 ) {
			return;
		}
		if (x + length > board[y].length -1) {
			length = board[y].length -x;
		}
		if (y + length > board.length -1) {
			length = board.length - y;
		}
		if (isClockWise) {
			board[y+length-1][x+length-1] = color;
		} else {
			board[y+length-1][x-length+1] = color;
		}
		
		drawSLine(board, y, x, color, --length,isClockWise);
		
	}

	private static void drawVLine(char[][] board, int y, int x, char color, int length) {
		if (length == 0 ) {
			return;
		}
		if (y + length > board.length -1) {
				length = board.length - y;
		}
		board[y+length-1][x] = color;
		drawVLine(board, y, x, color, --length);
		
	}

	private static void drawHLine(char[][] board, int y, int x, char color, int length) {
		if (length == 0 ) {
			return;
		}
		if (x + length > board[y].length -1) {
				length = board[y].length -x;
		}
		board[y][x+length-1] = color;
		drawHLine(board, y, x, color, --length);
	}

	private static void drawWithBrush(char[][] board, int y, int x, char color) {
		board[y][x] = color;
		
	}

	private static void fillArea(char[][] board, int y, int x, int total, char color) {
		if (board[y][x] == ' ') {
			board[y][x] = color;
			for (int i = 0; i < board.length; i++) {
				for (int j = 0; j < board[i].length; j++) {
					if (board[i][j] != ' ') {
						continue;
					}
					if (checkIfInRadius(board,i,j, color)) {
						fillCells(board, i, j, color);
					}
				}
				if (i == board.length -1) {
					i = -1;
					total--;
				}
				if (total < -1) {
					break;
				}
			}
		} else return;
	}

	private static void erase(char[][] board, int y, int x) {
		board[y][x] = ' ';
		
	}

	private static char[][] fillBoard() {
		char[][] board = new char[20][30];
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				board[i][j] = ' ';
			}
		}
		return board;
	}

	private static boolean checkIfInRadius(char[][] ma3x, int y, int j, char color) {
		boolean isInRange = false;
		if (j-1 >=0 && (ma3x[y][j-1] == ' ' || ma3x[y][j-1] == color) ) {
			if (ma3x[y][j-1] ==color) {
				isInRange = true;
			}
		}
		if (j+1 < ma3x[y].length && (ma3x[y][j+1] == ' ' || ma3x[y][j+1] == color)) {
			if (ma3x[y][j+1]==color) {
				isInRange = true;
			}
		}
		if (y+1 < ma3x.length && (ma3x[y+1][j] == ' '|| ma3x[y+1][j] == color)) {
			if (ma3x[y+1][j] ==color) {
				isInRange = true;
			}
		}
		if (y-1 >= 0 && (ma3x[y-1][j] == ' ' || ma3x[y-1][j] == color )) {
			if (ma3x[y-1][j] ==color) {
				isInRange = true;
			}
		}
		/* Commented so that single diagonal can be used as border
		 * if (y-1 >= 0 && j+1 < ma3x[y].length && (ma3x[y-1][j+1] == ' ' || ma3x[y-1][j+1] == color)) {
			if (ma3x[y-1][j+1] ==color) {
				isInRange = true;
			}
		}
		if (y-1 >= 0 && j-1 >= 0 && (ma3x[y-1][j-1] == ' '  || ma3x[y-1][j-1] == color )) {
			if (ma3x[y-1][j-1] ==color) {
				isInRange = true;
			}
		}
		if (j-1 >= 0 && y+1 < ma3x.length && (ma3x[y+1][j-1] == ' '  || ma3x[y+1][j-1] == color )) {
			if (ma3x[y+1][j-1] ==color) {
				isInRange = true;
			}
		}
		if (y+1 < ma3x.length && j+1 < ma3x[y].length && (ma3x[y+1][j+1] == ' ' || ma3x[y+1][j+1] == color)) {
			if (ma3x[y+1][j+1] ==color) {
				isInRange = true;
			}
		}*/
		return isInRange;
	}

	private static void fillCells(char[][] ma3x, int i, int j, char color) {
		if (ma3x[i][j] == ' ') {
			ma3x[i][j] = color;
			if (j-1 >=0 && (ma3x[i][j-1] == ' ' || ma3x[i][j-1] == color) ) {
				if (ma3x[i][j-1] !=color) {
					ma3x[i][j-1] =color;
				}
			}
			if (j+1 < ma3x[i].length && (ma3x[i][j+1] == ' ' || ma3x[i][j+1] == color)) {
				if (ma3x[i][j+1] !=color) {
					ma3x[i][j+1] =color;
				}
			}
			if (i+1 < ma3x.length && (ma3x[i+1][j] == ' '|| ma3x[i+1][j] == color)) {
				if (ma3x[i+1][j] !=color) {
					ma3x[i+1][j] =color;
				}
			}
			if (i-1 >= 0 && (ma3x[i-1][j] == ' ' || ma3x[i-1][j] == color )) {
				if (ma3x[i-1][j] !=color) {
					ma3x[i-1][j] =color;
				}
			}
			/* Commented so that single diagonal can be used as border
			 * if (i-1 >= 0 && j+1 < ma3x[i].length && (ma3x[i-1][j+1] == ' ' || ma3x[i-1][j+1] == color)) {
				if (ma3x[i-1][j+1] !=color) {
					ma3x[i-1][j+1] =color;
				}
			}
			if (i-1 >= 0 && j-1 >= 0 && (ma3x[i-1][j-1] == ' '  || ma3x[i-1][j-1] == color )) {
				if (ma3x[i-1][j-1] !=color) {
					ma3x[i-1][j-1] =color;
				}
			}
			if (j-1 >= 0 && i+1 < ma3x.length && (ma3x[i+1][j-1] == ' '  || ma3x[i+1][j-1] == color )) {
				if (ma3x[i+1][j-1] !=color) {
					ma3x[i+1][j-1] =color;
				}
			}
			if (i+1 < ma3x.length && j+1 < ma3x[i].length && (ma3x[i+1][j+1] == ' ' || ma3x[i+1][j+1] == color)) {
				if (ma3x[i+1][j+1] !=color) {
					ma3x[i+1][j+1] =color;
				}
			}*/
		} else {
			return;
		}
	}

	private static void printArray(char[][] board) {
		for (int i = 0; i < board.length; i++) {
			System.out.println(Arrays.toString(board[i]));
		}		
	}
}
