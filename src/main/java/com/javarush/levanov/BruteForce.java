package com.javarush.levanov;

import java.util.*;

public class BruteForce {
    private static final String[] KEY_WORDS = {"и", "в", "на", "не", "я", "он", "она", "человк", "время", "год", "был"};
    private static final String[] PRECEDING_CHARACTERS = {" ", "\n"};
    private static final String[] NEXT_CHARACTERS = {" ", ".", ",", ";", ":", "\"", "!", "?"};

    // возвращаем расшифрованный текст по значению найденного ключа
    public String decryptByBruteForce(String encryptedText, int alphabetLength) {
        Cipher cipher = new Cipher();
        int foundedKey = findKey(encryptedText, alphabetLength, cipher);
        return cipher.decrypt(encryptedText, foundedKey);
    }

    // перебиреаем все ключи, и возвращаем тот, при котором больше всего повторений ключевых слов
    private int findKey(String encryptedText, int alphabetLength, Cipher cipher) {
        Map<Integer, Integer> keysAndCounters = new HashMap<>();
        for (int key = 1; key <= alphabetLength; key++) {
            String decryptedText = cipher.decrypt(encryptedText, key);
            int counter = keyWordsCounter(decryptedText);
            keysAndCounters.put(key, counter);
        }
        System.out.println(keysAndCounters);

        Iterator<Map.Entry<Integer, Integer>> iterator = keysAndCounters.entrySet().iterator();
        int maxCounterValue = 0;
        int key = 0;
        while (iterator.hasNext()) {
            Map.Entry<Integer, Integer> nextEntry = iterator.next();
            if (nextEntry.getValue() > maxCounterValue) {
                maxCounterValue = nextEntry.getValue();
                key = nextEntry.getKey();
            }
        }
        System.out.println("key=" + key);
        return key;
    }

    // возвращает число повторений всех ключевых слов в тексте
    private int keyWordsCounter(String text) {
        int keyWordCounter = 0;
        for (String keyWord : KEY_WORDS) {
            keyWordCounter += wordCounter(text, keyWord);
        }
        return keyWordCounter;
    }

    // перебираем варианты обрамления слова пробелами и знаками препинания и возвращаем число повторений слова в тексте
    private int wordCounter(String text, String word) {
        int wordCounter = 0;
        for (String precedingCharacter : PRECEDING_CHARACTERS) {
            for (String nextCharacter : NEXT_CHARACTERS) {
                int pointer = 0;
                String wordCase = precedingCharacter + word + nextCharacter;
                while (text.toLowerCase().indexOf(wordCase, pointer) != -1) {
                    wordCounter++;
                    pointer = text.toLowerCase().indexOf(wordCase, pointer) + wordCase.length();
                }
            }
        }
        return wordCounter;
    }
}