package com.javarush.levanov.ver_2.actions;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;

// построчно считываем файл и возвращаем посчитанную матрицу
// рисуем матрицу (для отладки)
// считаем расстояние между биграммами
// делаем перестановку, возвращаем переставленную матрицу и измененный алфавит
// делаем итерацию из N перестановок, считаем расстояние и если оно меньше исходного сохраняем новый алфавит
        /*
        мама#мыла#раму, на пересечении строки и столбца указывается число повторений биграммы [буква строки][буква столбца]
          м а ы л р у #
        м 0 2 1 0 0 1 0
        а 2 0 0 0 0 0 2
        ы 0 0 0 1 0 0 0
        л 0 1 0 0 0 0 0
        р 0 1 0 0 0 0 0
        у 0 0 0 0 0 0 0
        # 1 0 0 0 1 0 0
         */
import static com.javarush.levanov.ver_2.constant.Constants.*;

public class Analyze {
    public static final int TRY_COUNT = 1;
    public static ArrayList<Character> alphabet = new ArrayList<>(Arrays.asList(ALPHABET));
    public static ArrayList<Character> cipheredAlphabet = new ArrayList<>(Arrays.asList(ALPHABET));
    public static ArrayList<Character> bestAlphabet = new ArrayList<>(Arrays.asList(ALPHABET));
    public static String etalonPath = "/Users/alevanov/!_Не работа/war_and_peace.ru.txt";
    public static String testPath = "/Users/alevanov/!_Не работа/text.txt";
    public static String encryptedPath = "/Users/alevanov/!_Не работа/encrypted(a-b).txt";
    public static String decryptedPath = "/Users/alevanov/!_Не работа/decryptedByAnalyze.txt";
//    public static ArrayList<Character> alphabet = new ArrayList<>(Arrays.asList('а', 'б', 'в', 'г', 'д'));
//    public static ArrayList<Character> cipheredAlphabet = new ArrayList<>(Arrays.asList('а', 'б', 'в', 'г', 'д'));
//    public static double[][] testMatrix = {{1, 2, 3, 4, 5}, {6, 7, 8, 9, 0}, {0, 9, 8, 7, 6}, {5, 4, 3, 2, 1}, {0, 0, 0, 0, 0}};

    static void main() {
//        showMatrix(testMatrix);
//        double[][] changedMatrix = changeMatrix(testMatrix);
//        showMatrix(changedMatrix);
//        System.out.println(getDistance(testMatrix,changedMatrix));
//        double[][] changedBackMatrix = changeMatrixBack(changedMatrix);
//        showMatrix(changedBackMatrix);
//        System.out.println(getDistance(testMatrix,changedBackMatrix));
        double[][] etalon = getMatrix(etalonPath);
        double[][] test = getMatrix(testPath);
        double[][] encrypted = getMatrix(encryptedPath);
        double testDistance = getDistance(etalon, test);
        System.out.println("distance etalon-test=" + testDistance);
        double initialDistance = getDistance(etalon, encrypted);
        System.out.println("distance etalon-encrypted=" + initialDistance);

        double[][] matrixBuffer = copy(encrypted);
        double[][] changedMatrix;
        double distanceBuffer = initialDistance;
        double distance;
        int counter = 0;

        while (distanceBuffer > 0.07) {
            changedMatrix = changeMatrix(matrixBuffer);
            distance = getDistance(etalon, changedMatrix);
            counter++;
//            System.out.println(counter + "   ");
            if (distance < distanceBuffer) { //SUCCESS
//                System.out.println("distance etalon-changedMatrix=" + distance);
                matrixBuffer = copy(changedMatrix);
                distanceBuffer = distance;
                for (int i = 0; i < cipheredAlphabet.size(); i++) {
                    bestAlphabet.set(i, cipheredAlphabet.get(i));
                }
                System.out.println("SUCCESS " + distance + " bufferAlphabet " + bestAlphabet);

            } else { // FAIL
//                System.out.println("FAIL " + distance + " cipheredAlphabet " + cipheredAlphabet);
                for (int i = 0; i < cipheredAlphabet.size(); i++) {
                    cipheredAlphabet.set(i, bestAlphabet.get(i));
                }
            }
        }
        System.out.println(alphabet);
        System.out.println(bestAlphabet);
        System.out.println(counter);
//        Coder.setCipher(bestAlphabet);
//        Decrypt.execute(encryptedPath, decryptedPath);

    }

    // Построчно считываем файл и строим матрицу биграмм
    public static double[][] getMatrix(String path) {
        int charsCounter = 0;
        int[][] matrix = new int[ALPHABET.length][ALPHABET.length];
        try (BufferedReader bufferedReader = Files.newBufferedReader(Path.of(path))) {
            while (bufferedReader.read() != -1) {
                char[] lineChars = bufferedReader.readLine().toLowerCase().toCharArray();
                for (int i = 0; i < lineChars.length - 1; i++) {
                    int firstCharIndex = alphabet.indexOf(lineChars[i]);
                    int secondCharIndex = alphabet.indexOf(lineChars[i + 1]);
                    if (firstCharIndex != -1 && secondCharIndex != -1) {
                        charsCounter++;
                        matrix[firstCharIndex][secondCharIndex]++;
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        double[][] normalizedMatrix = new double[ALPHABET.length][ALPHABET.length]; // нормализуем матрицу
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                normalizedMatrix[i][j] = (double) matrix[i][j] / charsCounter;
            }
        }
        return normalizedMatrix;
    }

    // Показываем матрицу биграмм
    public static void showMatrix(double[][] matrix) {
        for (Character c : cipheredAlphabet) {
            System.out.printf("%8s", c);
        }
        System.out.println();
        for (int i = 0; i < matrix.length; i++) {
            System.out.print(cipheredAlphabet.get(i) + "\t");
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.printf("%8.5f", matrix[i][j]);
            }
            System.out.println();
        }
    }

    // вычисляем дистанцию
    private static double getDistance(double[][] etalon, double[][] candidate) {
        double distance = 0;
        double sum = 0;
        for (int i = 0; i < etalon.length; i++) {
            for (int j = 0; j < etalon[i].length; j++) {
//                System.out.println(etalon[i][j]-candidate[i][j]);
                sum += Math.pow(etalon[i][j] - candidate[i][j], 2);
            }
        }
        distance = Math.pow(sum, 0.5);
        return distance;
    }

    /*
       а б в г д
    а  1 2 3 4 5
    б  6 7 8 9 0
    в  0 9 8 7 6
    г  5 4 3 2 1
    д  0 0 0 0 0

    меняем местами 2 и 3
       а в б г д
    а  1 3 2 4 5
    в  0 8 9 7 6
    б  6 8 7 9 0
    г  5 3 4 2 1
    д  0 0 0 0 0
     */


    // делаем копию
    public static double[][] copy (double[][] original){
        double[][] copy = new double[original.length][original.length];
        for (int i = 0; i < copy.length; i++) {
            copy[i]= original[i].clone();
        }
        return copy;
    }


    // делаем TRY_COUNT перестановок, возвращаем измененную матрицу и меняем алфавит в cipheredAlphabet
    public static double[][] changeMatrix(double[][] originalMatrix) {
        double[][] changedMatrix = copy(originalMatrix);
        double[] doubleArrayBuffer = new double[cipheredAlphabet.size()];

        for (int j = 0; j < TRY_COUNT; j++) {
            int k = (int) Math.round((alphabet.size() - 1) * Math.random());
            int m = (int) Math.round((alphabet.size() - 1) * Math.random());

            if (k != m) {
                char charBuffer = cipheredAlphabet.get(k);
                cipheredAlphabet.set(k, cipheredAlphabet.get(m));
                cipheredAlphabet.set(m, charBuffer);

                for (int i = 0; i < doubleArrayBuffer.length; i++) {
                    doubleArrayBuffer[i] = changedMatrix[k][i];
                    changedMatrix[k][i] = changedMatrix[m][i];
                    changedMatrix[m][i] = doubleArrayBuffer[i];
                }
                for (int i = 0; i < doubleArrayBuffer.length; i++) {
                    doubleArrayBuffer[i] = changedMatrix[i][k];
                    changedMatrix[i][k] = changedMatrix[i][m];
                    changedMatrix[i][m] = doubleArrayBuffer[i];
                }
            }
        }
        return changedMatrix;
    }
}
