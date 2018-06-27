package ru.sukmanov;

import ru.sukmanov.actions.Matrix;
import ru.sukmanov.actions.Multiplication;
import ru.sukmanov.excepts.NotConsistencyException;
import ru.sukmanov.excepts.NotCubicException;


public class Main {


    public static void main(String[] args) {
        Multiplication multiplication = new Multiplication();
        Matrix matrix = new Matrix();

        int[][] a = matrix.randomGenerate(1001, 1001);
        int[][] b = matrix.randomGenerate(1001, 1001);

        /*int[][] a = {{1, 3}, {5, 2}, {0, 4}};
        int[][] b = {{1, 0}, {2, 1}};*/

        /*int[][] a = {{1, 3}, {5, 2}};
        int[][] b = {{1, 0}, {2, 1}};*/
        try {

            long start = System.nanoTime();
            try {
                int[][] c = multiplication.simplyMultiplication(a, b);
            } catch (NotConsistencyException e){
                System.out.println(e.toString() + ". simplyMultiplication not working");
            }
            long finish = System.nanoTime();

            long startTr = System.nanoTime();
            try {
                int[][] cTr = multiplication.simplyTranspositionMultiplication(a, b);
            } catch (NotCubicException e) {
                System.out.println(e.toString() + ". transpositionMultiplication not working");
            }
            long finishTr = System.nanoTime();

            long startT = System.nanoTime();
            try{
                int[][] cT = multiplication.threadMultiplication(a, b, Runtime.getRuntime().availableProcessors());
            } catch (NotConsistencyException e) {
                System.out.println(e.toString() + ". threadMultiplication not working");
            }
            long finishT = System.nanoTime();

            //System.out.println(matrix.printMatrix(cT));
            System.out.println("============================================================");
            System.out.println("SimpleMethodTime: " + String.format("%,12d",finish - start) + " ns");
            System.out.println("TranspMethodTime: " + String.format("%,12d",finishTr - startTr) + " ns");
            System.out.println("ThreadMethodTime: " + String.format("%,12d",finishT - startT) + " ns");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
