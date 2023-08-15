import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class TicTacToe {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter board size: ");
        int size = sc.nextInt();
        char[][] board = initBoard(size);
        ArrayList<Integer> taken = new ArrayList<Integer>(); // list of taken positions   
        while(true) {
            printBoard(board, size);
            if (playerPlay(board, size, taken)) { 
                printBoard(board, size);
                System.out.println("\nYou win!");
                break; }
            if (machinePlay(board, size, taken)) { 
                printBoard(board, size);
                System.out.println("\nYou lose!");
                break; }
            if (taken.size() == size * size) {
                printBoard(board, size);
                System.out.println("\nIt's a tie!");
                break; }
        }
        sc.close();
    }

    public static char[][] initBoard(int size) {
        char[][] board = new char[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[i][j] = ' ';
            }
        }
        return board;
    }
    
    public static boolean placeMark(char[][] board, int size, int pos, boolean human) {
        int row = pos / size;
        int col = pos % size;
        board[row][col] = human ? 'X' : 'O';
        if (checkWin(board, size, row, col)) {
            return true;
        }
        return false;
    }

    public static boolean checkWin(char[][] board, int size, int row, int col) {
        boolean ver = true, hor = true;
        // vertical
        for (int i = 0; i < size - 1; i++) {
            if ((board[i][col] != board[i+1][col])) { 
                ver = false;
                break; 
            }
        }
        if (ver) { return true; }
        // horizontal
        for (int i = 0; i < size - 1; i++) {
            if ((board[row][i] != board[row][i+1])) { 
                hor = false;
                break;
            }
        }
        if (hor) { return true; }
        // diagonal
        if (row == col) {
            for (int i = 0; i < size - 1; i++) {
                if ((board[i][i] != board[i+1][i+1])) { 
                    return false;
                }
            }
        // anti-diagonal
        } else if (row + col == size - 1) {
            for (int i = 0; i < size - 1; i++) {
                if ((board[i][size - 1 - i] != board[i+1][size - 2 - i])) { 
                    return false;
                }
            }
        } else { return false; }
        return true;
    }
    
    public static boolean machinePlay(char[][] board, int size, ArrayList<Integer> taken) {
        int pos;
        do {
            pos = (int) (Math.random() * size * size);
            if (taken.size() == size * size) { return false; }
        } while (board[pos / size][pos % size] != ' ');
        taken.add(pos);
        if (placeMark(board, size, pos, false)) {
            return true; // game ends
        }
        return false;
    }

    public static boolean playerPlay(char[][] board, int size, ArrayList<Integer> taken) {
        Scanner sc = new Scanner(System.in);
        int pos = askPos(sc, board, size);
        taken.add(pos);
        if (placeMark(board, size, pos, true)) {
            return true; // game ends
        }
        return false;
    }

    public static int askPos(Scanner sc, char[][] board, int size) {
        System.out.print("\nEnter a number between 1 and " + size * size + ": ");
        String input = sc.nextLine();
        // check if input is a number
        Pattern p = Pattern.compile("^[0-9]+$");
        Matcher m = p.matcher(input);
        if (!(m.find())) {
            System.out.print("\nInvalid input. ");
            return askPos(sc, board, size);
        }
        // check if input is in range
        int pos = Integer.parseInt(input) - 1;
        if (pos < 0 || pos >= size * size) {
            System.out.print("\nOut of bounds. ");
            return askPos(sc, board, size);
        }
        // check if position is taken
        boolean repeat = board[pos / size][pos % size] != ' ';
        if (repeat) {
            System.out.print("\nPosition already taken. ");
            return askPos(sc, board, size);
        }
        return pos;
    }

    // Format and print board

    public static void printBoard(char[][] board, int size) {
        System.out.print("\033[H\033[2J"); // clear screen
        for (int i = 0; i < size - 1; i++) {
            printRow(board[i], size);
            printSeparator(size);
        }
        printRow(board[size - 1], size);
    }

    public static void printRow(char[] row, int size) {
        for (int i = 0; i < size - 1; i++) {
            System.out.print(" " + row[i] + " \u2551");
        }
        System.out.println(" " + row[size - 1]);
    }
    
    public static void printSeparator(int size) {
        for (int i = 0; i < size - 1; i++) {
            System.out.print("\u2550\u2550\u2550\u256C");
        }
        System.out.println("\u2550\u2550\u2550");
    }
}