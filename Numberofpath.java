import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Numberofpath {


    //Configuration of the Matrix 
    //Setting first column and line numbers to 1 or 0

    public static int[][] configMatrix(int[][] matrix){

        matrix[0][0] = 1;
        for(int j = 1; j < matrix[0].length; j++){
            if(matrix[0][j] == 0 && matrix[0][j - 1] != 0) {
              matrix[0][j] = 1;
            }
            else if(matrix[0][j] == 1) {
              matrix[0][j] = 0;
            }
        }
        for(int i = 1; i < matrix.length; i++) {

            if(matrix[i][0] == 0 && matrix[i - 1][0] != 0) {
                matrix[i][0] = 1;
            }
            else if(matrix[i][0] == 1) {
                matrix[i][0] = 0;
            }
        }

        return matrix;
    }


    // Calculation of all the possible path to get to the destination
    // Addition of top and left box each time using matrix[i][j-1] + matrix[i-1][j]
    public static int totalPath(int[][] matrix) {
        for(int i = 1; i < matrix.length; i++) {
            for(int j = 1; j < matrix[0].length; j++) {
                if(matrix[i][j] == 1) {
                    matrix[i][j] = 0;
                }
                else if(matrix[i][j] == 0) {
                    matrix[i][j] = matrix[i][j-1] + matrix[i-1][j];
                }
            }
        }
        return matrix[matrix.length - 1][matrix[0].length - 1];
    }

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int M = in.nextInt();
        int N = in.nextInt();
        if (in.hasNextLine()) {
            in.nextLine();
        }

        // Instanciation of Matrix 
        int[][] matrix = new int[M][N];
        

        //Matrix init and casting of String in Int 
        for (int i = 0; i < M; i++) {
            String ROW = in.nextLine();
            for(int j = 0; j < N; j++) {
              matrix[i][j] = Integer.parseInt(String.valueOf(ROW.charAt(j)));  
            }
        }

        matrix = configMatrix(matrix) ; 

        // Write an answer using System.out.println()
        // To debug: System.err.println("Debug messages...")
        System.err.println("Total numbers of roads to get to Rome are :");
        System.out.println(totalPath(matrix));
    }
}
