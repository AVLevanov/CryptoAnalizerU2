package com.javarush.levanov;

import java.util.Map;
import java.util.TreeMap;

public class Cipher {
    private static final char[] ALPHABET = {'а', 'б', 'в', 'г', 'д', 'е', 'ё', 'ж', 'з',
            'и', 'й', 'к', 'л', 'м', 'н', 'о', 'п', 'р', 'с', 'т', 'у', 'ф', 'х', 'ц', 'ч', 'ш', 'щ',
            'ъ', 'ы', 'ь', 'э', 'ю', 'я', 'А', 'Б', 'В', 'Г', 'Д', 'Е', 'Ё', 'Ж', 'З',
            'И', 'Й', 'К', 'Л', 'М', 'Н', 'О', 'П', 'Р', 'С', 'Т', 'У', 'Ф', 'Х', 'Ц', 'Ч', 'Ш', 'Щ',
            'Ы', 'Э', 'Ю', 'Я', '.', ',', '«', '»', '"', '\'', ':', '!', '?', ' '};
    public static final int ALPHABET_LENGTH = ALPHABET.length;

    private Map<Character, Character> createEncoder(int shift) {
        Map<Character, Character> encoder = new TreeMap<>();
        for (int i = 0; i < ALPHABET.length; i++) {
            encoder.put(ALPHABET[i], ALPHABET[(i + shift) % ALPHABET.length]);
        }
        return encoder;
    }

    private Map<Character, Character> createDecoder(int shift) {
        Map<Character, Character> decoder = new TreeMap<>();
        for (int i = 0; i < ALPHABET.length; i++) {
            decoder.put(ALPHABET[(i + shift) % ALPHABET.length], ALPHABET[i]);
        }
        return decoder;
    }

    public String encrypt(String text, int shift) {
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

    public String decrypt(String encryptedText, int shift) {
        Map<Character, Character> decoder = createDecoder(shift);
        char[] encryptedTextCharArray = encryptedText.toCharArray();
        char[] decryptedTextCharArray = new char[encryptedText.length()];
        for (int i = 0; i < encryptedTextCharArray.length; i++) {
            char c = encryptedTextCharArray[i];
            if (decoder.get(c) != null) {
                decryptedTextCharArray[i] = decoder.get(c);
            } else {
                decryptedTextCharArray[i] = c;
            }
        }
        return String.valueOf(decryptedTextCharArray);
    }
}
