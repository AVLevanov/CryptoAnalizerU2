package com.javarush.levanov.actions;

import com.javarush.levanov.controller.ExecuteRequest;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import static com.javarush.levanov.constant.Constants.*;

public class BruteForce extends AbstractAction {
    public BruteForce(ExecuteRequest executeRequest) {
        this.executeRequest = executeRequest;
        execute(executeRequest);
    }

    // возвращаем расшифрованный текст по значению найденного ключа
    public void execute(ExecuteRequest executeRequest) {
        int bestKey = findKey(executeRequest.inPath, executeRequest.outPath);
        codeWithKey(executeRequest.inPath, executeRequest.outPath, bestKey);
    }

    // перебиреаем все ключи, и возвращаем тот, при котором больше всего повторений ключевых слов
    private int findKey(Path inPath, Path outPath) {
        Map<Integer, Integer> keysAndCounters = new HashMap<>();
        for (int key = 1; key <= ALPHABET.length; key++) {
            codeWithKey(inPath, outPath, -key);
            int counter = keyWordsCounter(outPath);
            keysAndCounters.put(-key, counter);
        }

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
        return bestKey;
    }

    // возвращает число повторений всех ключевых слов в тексте
    private int keyWordsCounter(Path inPath) {
        int keyWordCounter = 0;
        for (String keyWord : KEY_WORDS) {
            keyWordCounter += wordCounter(inPath, keyWord);
        }
        return keyWordCounter;
    }

    // Построчно читая файл перебираем варианты обрамления слова пробелами и знаками препинания. Возвращаем число повторений слова в тексте
    private int wordCounter(Path inPath, String word) {
        int wordCounter = 0;
        try (BufferedReader bufferedReader = Files.newBufferedReader(inPath)) {
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