package ru.sukmanov.actions;

import java.util.Random;

public class Matrix {

    public int[][] randomGenerate(int rows, int columns) {
        int[][] massiv = new int[rows][columns];
        Random rand = new Random();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++){
                massiv[i][j] = rand.nextInt(10);
            }
        }

        return massiv;
    }

    public String printMatrix(int[][] matrix) {
        String res = "\n";

        for (int i = 0; i < matrix.length; i++){
            for (int j = 0; j < matrix[0].length; j++){
                res += matrix[i][j] + "\t";
            }
            res += "\n";
        }
        res += "\n";
        return res;
    }
}
