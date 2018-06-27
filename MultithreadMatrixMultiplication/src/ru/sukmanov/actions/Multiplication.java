package ru.sukmanov.actions;

import ru.sukmanov.excepts.NotConsistencyException;
import ru.sukmanov.excepts.NotCubicException;

/**
 * Class of operations with two matrix
 */
public class Multiplication {

    /**
     * @param a - first matrix
     * @param b - second matrix
     * @return true, if both matrix are consistency
     */
    private boolean matrixConsistency(int[][] a, int[][] b) {
        //number of rows of the second matrix
        int b_row = b.length;
        //number of columns of the first matrix
        int a_col = a[0].length;
        return (a_col == b_row);
    }

    /**
     * @param a - first matrix
     * @param b - second matrix
     * @return true, if both matrix are cubical
     */
    private boolean matrixCubic(int[][] a, int[][] b) {
        return ((a.length == a[0].length) && (b.length == b[0].length));
    }

    /**
     * Simple multiplication of matrices
     * @param a - first matrix
     * @param b - second matrix
     * @return a new matrix, which is the result of multiplying the two original
     * @throws NotConsistencyException, if a and b are not consistency
     */
    public int[][] simplyMultiplication(int[][] a, int[][] b) throws NotConsistencyException {
        if (!matrixConsistency(a, b))
            throw new NotConsistencyException("Matrix is not consistency!");

        //number of rows of the first matrix
        final int a_row = a.length;
        //number of columns of the second matrix
        final int b_col = b[0].length;
        //number of rows of the second matrix(equals number of columns of the first matrix)
        final int b_row = b.length;

        final int[][] resultMatrix = new int[a_row][b_col];

        for (int i = 0; i < a_row; i++) {
            for (int j = 0; j < b_col; j++) {
                for (int k = 0; k < b_row; k++) {
                    resultMatrix[i][j] += a[i][k] * b[k][j];
                }
            }
        }
        return resultMatrix;
    }

    /**
     * Simple multiplication of cubical matrices by use transposition
     * @param a - first matrix
     * @param b - second matrix
     * @return a new matrix, which is the result of multiplying the two original
     * @throws NotCubicException, if a and b are not cubical
     */
    public int[][] simplyTranspositionMultiplication(int[][] a, int[][] b) throws NotCubicException{
        if (!matrixCubic(a, b)) {
            throw new NotCubicException("Matrix is not cubic!");
        }

        //number of rows of the first matrix
        final int a_row = a.length;
        //number of columns of the second matrix
        final int b_col = b[0].length;
        //number of rows of the second matrix(equals number of columns of the first matrix)
        final int b_row = b.length;

        final int[][] resultMatrix = new int[a_row][b_col];

        final int[][] bt = new int[a_row][b_col];
        //Транспонируем матрицу
        for (int i = 0; i < b_col; i++) {
            for (int j = 0; j < a_row; j++) {
                bt[j][i] = b[i][j];
            }
        }

        for (int i = 0; i < a_row; i++) {
            for (int j = 0; j < b_col; j++) {
                for (int k = 0; k < b_col; k++) {
                    resultMatrix[i][j] += a[i][k] * bt[j][k];
                }
            }
        }
        return resultMatrix;
    }

    /**
     * Threads multiplication of matrices
     * @param a - first matrix
     * @param b - second matrix
     * @return a new matrix, which is the result of multiplying the two original
     * @throws NotConsistencyException, if a and b are not consistency
     */
    public int[][] threadMultiplication(int[][] a, int[][] b, int threadsCount) throws NotConsistencyException{
        if (!matrixConsistency(a, b))
            throw new NotConsistencyException("Matrix is not consistency!");

        //number of rows of the first matrix
        final int a_row = a.length;
        //number of columns of the second matrix
        final int b_col = b[0].length;
        //number of rows of the second matrix(equals number of columns of the first matrix)
        final int b_row = b.length;
        //size result matrix
        final int[][] resultMatrix = new int[a_row][b_col];
        //если количество потоков больше чем количество строк - уменьшаем кол-во потоков
        if (threadsCount > a_row) {
            threadsCount = a_row;
        }
        //посчитаем сколько строк результирующей матрицы будет считать каждый поток
        final int count = a_row / threadsCount;
        //если не делится на threadsCount, то добавим к первому потоку
        final int additional = a_row % threadsCount;

        MatrixThread[] matrixThreads = new MatrixThread[threadsCount];

        int start = 0;

        for (int i = 0; i < threadsCount; i++) {
            int cnt = ((i == 0) ? count + additional : count);
            matrixThreads[i] = new MatrixThread(a, b, resultMatrix, start, start + cnt - 1);
            start += cnt;
            matrixThreads[i].start();
        }

        //waitig for finish thread
        try {
            for (MatrixThread matrixThread : matrixThreads) {
                matrixThread.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return resultMatrix;
    }
}
