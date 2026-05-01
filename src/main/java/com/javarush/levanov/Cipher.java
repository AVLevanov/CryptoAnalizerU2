package com.javarush.levanov;

import java.util.Map;
import java.util.TreeMap;

public class Cipher {
    private static final char[] ALPHABET = {'а', 'б', 'в', 'г', 'д', 'е', 'ё', 'ж', 'з',
            'и', 'й', 'к', 'л', 'м', 'н', 'о', 'п', 'р', 'с', 'т', 'у', 'ф', 'х', 'ц', 'ч', 'ш', 'щ',
            'ъ', 'ы', 'ь', 'э', 'ю', 'я', 'А', 'Б', 'В', 'Г', 'Д', 'Е', 'Ё', 'Ж', 'З',
            'И', 'Й', 'К', 'Л', 'М', 'Н', 'О', 'П', 'Р', 'С', 'Т', 'У', 'Ф', 'Х', 'Ц', 'Ч', 'Ш', 'Щ',
            'Ы', 'Э', 'Ю', 'Я', '.', ',', '«', '»', '"', '\'', ':', '!', '?', ' '};

    private Map<Character, Character> createEncoder(int shift) {
        Map<Character, Character> encoder = new TreeMap<>();
        for (int i = 0; i < ALPHABET.length; i++) {
            encoder.put(ALPHABET[i], ALPHABET[(i + shift) % ALPHABET.length]);
        }
//        Для отладки. Выводим на консоль шифратор
//        Set<Map.Entry<Character, Character>> encoderEntrySet = encoder.entrySet();
//        for (Map.Entry<Character, Character> characterCharacterEntry : encoderEntrySet) {
//            System.out.println(characterCharacterEntry);
//        }
        return encoder;
    }

    public String encrypt(String text, int shift) {
//        Для отладки. Выводим на консоль длину строки
//        System.out.println("length=" + text.length());
        Map<Character, Character> encoder = createEncoder(shift);
        char[] textCharArray = text.toCharArray();
        char[] encryptedTextCharArray = new char[text.length()];
        for (int i = 0; i < encryptedTextCharArray.length; i++) {
            char c = textCharArray[i];
            if (encoder.get(c) != null) {
                encryptedTextCharArray[i] = encoder.get(c);
            } else {
                encryptedTextCharArray[i] = c;
            }
        }
        return String.valueOf(encryptedTextCharArray);
    }

//    public String decrypt(String encryptedText, int shift) {
//        return null;
//    }
}
