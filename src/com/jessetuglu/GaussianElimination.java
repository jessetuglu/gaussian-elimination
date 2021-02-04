package com.jessetuglu;

import java.util.Arrays;
import java.util.Scanner;

public class GaussianElimination {
    public static void main(String args[]) {
        System.out.print("Please specify matrix size(no need to include final col): \n");
        Scanner sc = new Scanner(System.in);
        int rows = sc.nextInt();
        int cols = sc.nextInt();



        double varMatrix[][] = new double[rows][cols];
        double constMatrix[] = new double[rows];

        System.out.println("\nEnter matrix coefficients, starting from index[0][0].\n");

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                varMatrix[i][j] = sc.nextInt();
            }
        }
        System.out.println("\nEnter matrix constants. \n");

        for (int i = 0; i < rows; i++) {
            constMatrix[i] = sc.nextInt();
        }

        System.out.println("\nThe matrix you entered: \n");
        GaussianElimination.printMatrix(varMatrix,constMatrix);
        GaussianElimination.solve(varMatrix, constMatrix);
    }

    public static int getPivot(double varMatrix[][], int pivotMax, int row){
        for (int i = pivotMax + 1; i < varMatrix.length; i++) {
            if (Math.abs(varMatrix[i][row]) > Math.abs(varMatrix[pivotMax][row])){
                pivotMax = i;
            }
        }
        return pivotMax;
    }

    public static void printMatrix(double varMatrix[][],  double constMatrix[]){
        for (int i = 0; i < varMatrix.length; i++) {
            System.out.print("\n" + Arrays.toString(varMatrix[i]) + constMatrix[i] + "\n");
        }
    }
    public static void printSolution(double solution[]){
        for (int i = 0; i < solution.length; i++) {
            System.out.printf("\nX%d = %f\n",i + 1,solution[i]);
        }
    }
    public static void solve(double varMatrix[][],  double constMatrix[]) {
        int matrixLength = constMatrix.length;
        double[] solution = new double[matrixLength];

        for (int row = 0; row < matrixLength; row++) {
            int pivotMax = GaussianElimination.getPivot(varMatrix, row, row);

            double tempConst = constMatrix[row];
            constMatrix[row] = constMatrix[pivotMax];
            constMatrix[pivotMax] = tempConst;
            double[] tempRow = varMatrix[row];
            varMatrix[row] = varMatrix[pivotMax];
            varMatrix[pivotMax] = tempRow;

            for (int i = row + 1; i < matrixLength; i++) {
                double lambda = varMatrix[i][row] / varMatrix[row][row];
                constMatrix[i] -= lambda * constMatrix[row];
                for (int j = row; j < matrixLength; j++) {
                    varMatrix[i][j] -= lambda * varMatrix[row][j];
                }
            }
            // backsolve
            for (int i = matrixLength - 1; i >= 0; i--) {
                double finalVal = 0.0;
                for (int j = i + 1; j < matrixLength; j++) {
                    finalVal += varMatrix[i][j] * solution[j];
                }
                solution[i] = (constMatrix[i] - finalVal) / varMatrix[i][i];
            }
        }
        System.out.print("\n Solution:  \n");
        GaussianElimination.printSolution(solution);
    }
}

