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
import static com.javarush.levanov.ver_2.constant.Constants.ALPHABET;

public class Analyze {
    public static ArrayList<Character> alphabet = new ArrayList<>(Arrays.asList(ALPHABET));

    static void main() {
        showMatrix(getMatrix());
    }

    // Построчно считываем файл и строим матрицу биграмм
    public static double[][] getMatrix() {
        int charsCounter = 0;
        int[][] matrix = new int[ALPHABET.length][ALPHABET.length];
        String inPath = "/Users/alevanov/!_Не работа/text.txt";
        try (BufferedReader bufferedReader = Files.newBufferedReader(Path.of(inPath))) {
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

    // Показываем биграмму
    public static void showMatrix(double[][] matrix) {
        for (Character c : ALPHABET) {
            System.out.printf("%8s", c);
        }
        System.out.println();
        for (int i = 0; i < matrix.length; i++) {
            System.out.print(alphabet.get(i) + "\t");
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.printf("%8.5f", matrix[i][j]);
            }
            System.out.println();
        }
    }
}
