package com.javarush.levanov.ver_2.actions;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import static com.javarush.levanov.ver_2.constant.Constants.*;

public class BruteForce extends AbstractAction {

    public BruteForce(String inPath, String outPath) {
        this.decryptByBruteForce(inPath, outPath);
    }

    // возвращаем расшифрованный текст по значению найденного ключа
    public void decryptByBruteForce(String inPath, String outPath) {
        int bestKey = findKey(inPath);
        this.code(inPath, outPath, bestKey);
    }

    // перебиреаем все ключи, и возвращаем тот, при котором больше всего повторений ключевых слов
    private int findKey(String inPath) {
        Map<Integer, Integer> keysAndCounters = new HashMap<>();
        for (int key = 1; key <= ALPHABET.length; key++) {
            this.code(inPath, BRUTE_FORCE_WORKING_PATH, -key);
            int counter = keyWordsCounter(BRUTE_FORCE_WORKING_PATH);
            keysAndCounters.put(key, counter);
        }
        System.out.println(keysAndCounters);

        Iterator<Map.Entry<Integer, Integer>> iterator = keysAndCounters.entrySet().iterator();
        int maxCounterValue = 0;
        int bestKey = 0;
        while (iterator.hasNext()) {
            Map.Entry<Integer, Integer> nextEntry = iterator.next();
            if (nextEntry.getValue() > maxCounterValue) {
                maxCounterValue = nextEntry.getValue();
                bestKey = nextEntry.getKey();
            }
        }
        System.out.println("bestKey=" + bestKey);
        return bestKey;
    }

    // возвращает число повторений всех ключевых слов в тексте
    private int keyWordsCounter(String inPath) {
        int keyWordCounter = 0;
        for (String keyWord : KEY_WORDS) {
            keyWordCounter += wordCounter(inPath, keyWord);
        }
        return keyWordCounter;
    }

    // Построчно читая файл перебираем варианты обрамления слова пробелами и знаками препинания. Возвращаем число повторений слова в тексте
    private int wordCounter(String inPath, String word) {
        int wordCounter = 0;
        try (BufferedReader bufferedReader = Files.newBufferedReader(Path.of(inPath))) {
            String nextLine;
            String wordCase;
            String nextLineToLowerCase;
            while ((nextLine = bufferedReader.readLine()) != null) {
                nextLineToLowerCase = nextLine.toLowerCase();
                for (String nextCharacter : NEXT_CHARACTERS) { // считаем появление слова в начале строки
                    wordCase = word + nextCharacter;
                    if (nextLineToLowerCase.indexOf(wordCase, 0, Math.min(wordCase.length(), nextLineToLowerCase.length())) != -1) {
                        wordCounter++;
                    }
                    for (String precedingCharacter : PRECEDING_CHARACTERS) {  // считаем появление слова в середине строки
                        int pointer = 0;
                        wordCase = precedingCharacter + word + nextCharacter;
                        while (nextLineToLowerCase.indexOf(wordCase, pointer) != -1) {
                            wordCounter++;
                            pointer = nextLineToLowerCase.indexOf(wordCase, pointer) + wordCase.length();
                        }
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return wordCounter;
    }
}