package com.javarush.levanov.actions;

import com.javarush.levanov.constant.Coder;
import com.javarush.levanov.constant.Constants;
import com.javarush.levanov.controller.ExecuteRequest;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.javarush.levanov.constant.Constants.MATRIX_REVERT_COUNT_BY_ANALYZE;

public class Analyze extends AbstractAction {
    private final double precision;
    private final List<Character> alphabet;
    private final Path dictionaryPath;
    private final Path decryptedPath;
    private final Path encryptedPath;

    public Analyze(Character[] alphabet, ExecuteRequest executeRequest) {
        this.alphabet = new ArrayList<>(Arrays.asList(alphabet));
        this.encryptedPath = executeRequest.inPath;
        this.decryptedPath = executeRequest.outPath;
        this.dictionaryPath = executeRequest.dictionaryPath;
        this.precision = executeRequest.precision;
        execute();
    }

    private void execute() {
        List<Character> bestAlphabet = new ArrayList<>(alphabet);
        List<Character> changedAlphabet = new ArrayList<>(alphabet);
        double[][] dictionaryMatrix = getMatrix(dictionaryPath);
        double[][] encryptedMatrix = getMatrix(encryptedPath);
        double[][] bestMatrix = makeMatrixCopy(encryptedMatrix);
        double[][] changedMatrix = makeMatrixCopy(encryptedMatrix);
        double bestDistance = getDistance(dictionaryMatrix, encryptedMatrix);
        double changedDistance;
        int attemptsCounter = 0;
        while (bestDistance > precision) {
            attemptsCounter++;
            changeMatrixAndAlphabet(changedMatrix, changedAlphabet);
            changedDistance = getDistance(dictionaryMatrix, changedMatrix);
            if (changedDistance < bestDistance) {
                bestDistance = changedDistance;
                copyMatrixValues(bestMatrix, changedMatrix);
                copyAlphabetValues(bestAlphabet, changedAlphabet);
                System.out.printf(Constants.BEST_ALPHABET_WAS_FOUND, bestDistance, attemptsCounter);
                System.out.println(alphabet);
                System.out.println(bestAlphabet);
            } else {
                copyMatrixValues(changedMatrix, bestMatrix);
                copyAlphabetValues(changedAlphabet, bestAlphabet);
            }
        }
        Coder.setCoder(bestAlphabet);
        code(encryptedPath, decryptedPath);
    }

    private double[][] getMatrix(Path path) {
        int charsCounter = 0;
        double[][] matrix = new double[alphabet.size()][alphabet.size()];
        try (BufferedReader bufferedReader = Files.newBufferedReader(path)) {
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
        normalizeMatrix(matrix, charsCounter);
        return matrix;
    }

    private void normalizeMatrix(double[][] matrix, int count) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = matrix[i][j] / count;
            }
        }
    }

    private double getDistance(double[][] etalon, double[][] candidate) {
        double sum = 0;
        for (int i = 0; i < etalon.length; i++) {
            for (int j = 0; j < etalon[i].length; j++) {
                sum += Math.pow(etalon[i][j] - candidate[i][j], 2);
            }
        }
        return Math.pow(sum, 0.5);
    }

    private void changeMatrixAndAlphabet(double[][] changedMatrix, List<Character> changedAlphabet) {
        double[] arrayBuffer = new double[changedAlphabet.size()];
        char charBuffer;
        for (int j = 0; j < MATRIX_REVERT_COUNT_BY_ANALYZE; j++) {
            int k = (int) (Math.random() * changedAlphabet.size());
            int m = (int) (Math.random() * changedAlphabet.size());
            if (k != m) {
                charBuffer = changedAlphabet.get(k);
                changedAlphabet.set(k, changedAlphabet.get(m));
                changedAlphabet.set(m, charBuffer);
                for (int i = 0; i < arrayBuffer.length; i++) {
                    arrayBuffer[i] = changedMatrix[k][i];
                    changedMatrix[k][i] = changedMatrix[m][i];
                    changedMatrix[m][i] = arrayBuffer[i];
                }
                for (int i = 0; i < arrayBuffer.length; i++) {
                    arrayBuffer[i] = changedMatrix[i][k];
                    changedMatrix[i][k] = changedMatrix[i][m];
                    changedMatrix[i][m] = arrayBuffer[i];
                }
            }
        }
    }

    private void copyAlphabetValues(List<Character> destination, List<Character> source) {
        destination.clear();
        destination.addAll(source);
    }

    private double[][] makeMatrixCopy(double[][] original) {
        double[][] copy = new double[original.length][original.length];
        for (int i = 0; i < copy.length; i++) {
            copy[i] = original[i].clone();
        }
        return copy;
    }

    private void copyMatrixValues(double[][] destination, double[][] source) {
        for (int i = 0; i < destination.length; i++) {
            for (int j = 0; j < destination[i].length; j++) {
                destination[i][j] = source[i][j];
            }
        }
    }
}
