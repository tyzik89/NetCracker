package ru.sukmanov.actions;

import java.util.logging.Logger;

public class MatrixThread extends Thread{
    private static Logger logger = Logger.getLogger(MatrixThread.class.getName());

    private int[][] firstMatrix;
    private int[][] secondMatrix;
    private int[][] resultMatrix;
    private int startRow;
    private int finishRow;
    private int rowcol;

    MatrixThread(int[][] firstMatrix,
                 int[][] secondMatrix,
                 int[][] resultMatrix,
                 int startRow,
                 int finishRow) {

        this.firstMatrix = firstMatrix;
        this.secondMatrix = secondMatrix;
        this.resultMatrix = resultMatrix;
        this.startRow = startRow;
        this.finishRow = finishRow;
        this.rowcol = secondMatrix.length;
    }

    private int calcValueInCell(int row, int col) {
        int cell = 0;
        for (int i = 0; i < rowcol; i++) {
            cell += firstMatrix[row][i] * secondMatrix[i][col];
        }
        return cell;
    }

    @Override
    public void run() {
        System.out.println(currentThread().getName() + " считает со строки " + startRow
                + " до строки " + finishRow + " включительно");

        for (int row = startRow; row <= finishRow ; row++) {
            for (int col = 0; col < resultMatrix[row].length; col++) {
                resultMatrix[row][col] = calcValueInCell(row, col);
            }
        }
    }


}
