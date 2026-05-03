package com.javarush.levanov;

import java.util.*;

public class BruteForce {
    private static final String[] KEY_WORDS = {"и", "в", "на", "не", "я", "он", "она", "человк", "время", "год", "был"};

    // возвращаем расшифрованный текст по значению найденного ключа
    public String decryptByBruteForce(String encryptedText, int alphabetLength) {
        Cipher cipher = new Cipher();
        int foundedKey = findKey(encryptedText, alphabetLength);
        return cipher.decrypt(encryptedText, foundedKey);
    }

    // перебиреаем все ключи, и возвращаем тот, при котором больше всего повторений ключевых слов
    private int findKey(String encryptedText, int alphabetLength) {
        Cipher cipher = new Cipher();

        Map<Integer, Integer> countersAndKeys = new HashMap<>();
        for (int key = 1; key <= alphabetLength; key++) {
            String decryptedText = cipher.decrypt(encryptedText, key);
            int counter = keyWordsCounter(decryptedText);
            countersAndKeys.put(key, counter);
        }
//        System.out.println(countersAndKeys);
        Iterator<Map.Entry<Integer, Integer>> iterator = countersAndKeys.entrySet().iterator();

        int maxValue = 0;
        int key = 0;
        while (iterator.hasNext()) {
            Map.Entry<Integer, Integer> nextEntry = iterator.next();
            if (nextEntry.getValue() > maxValue) {
                maxValue = nextEntry.getValue();
                key = nextEntry.getKey();
            }
        }
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

    // возвращает чисто повторений слова в тексте
    private int wordCounter(String text, String word) {
        String[] wordCases = {" " + word + " ", "\n" + word + " ", " " + word + ".", " " + word + ","};
        int wordCounter = 0;
        for (String wordCase : wordCases) {
            int pointer = 0;
            while (text.toLowerCase().indexOf(wordCase, pointer) != -1) {
                wordCounter++;
                pointer = text.toLowerCase().indexOf(wordCase, pointer) + wordCase.length();
            }
        }
        return wordCounter;
    }
}
