package com.javarush.levanov;

import java.util.*;

public class BruteForce {
    private static final String[] KEY_WORDS = {"и", "в", "на", "не", "я", "он", "она", "человк", "время", "год", "был"};

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

    // возвращает число повторений всех ключевых слов в тексте
    public int keyWordsCounter(String text) {
        int keyWordCounter = 0;
        for (String keyWord : KEY_WORDS) {
            keyWordCounter += wordCounter(text, keyWord);
        }
        return keyWordCounter;
    }

    // перебиреаем все ключи, и возвращаем тот, при котором больше всего повторений ключевых слов
    private int findKey(String encryptedText, char[] alphabet) {
        Cipher cipher = new Cipher();

        Comparator<Integer> comparator = new Comparator<>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        };

        Map<Integer, Integer> countersAndKeys = new TreeMap<>(comparator);
        for (int key = 1; key <= alphabet.length; key++) {
            String decryptedText = cipher.decrypt(encryptedText, key);
            int counter = keyWordsCounter(decryptedText);
            countersAndKeys.put(counter, key);
        }

        Iterator<Integer> iterator = countersAndKeys.values().iterator();
//        System.out.println(countersAndKeys);
        return iterator.next();
    }

    // возвращаем расшифрованный текст по значению найденного ключа
    public String decryptByBruteForce(String encryptedText, char[] alphabet) {
        int foundedKey = findKey(encryptedText, alphabet);
        Cipher cipher = new Cipher();
        return cipher.decrypt(encryptedText, foundedKey);
    }
}
