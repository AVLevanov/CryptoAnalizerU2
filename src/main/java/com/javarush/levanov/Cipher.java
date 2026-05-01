package com.javarush.levanov;

import java.util.Map;
import java.util.Set;
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
        Map<Character, Character> encoder = createEncoder(shift);
        String encryptedText = null;
        System.out.println("length=" + text.length());
        char[] textCharArray = new char[text.length()];
        char[] encryptedTextCharArray = new char[text.length()];
        textCharArray = text.toCharArray();
        for (int i = 0; i < encryptedTextCharArray.length; i++) {
            Character c = textCharArray[i];
            char x = encoder.get(c);
            encryptedTextCharArray[i] = x;
        }
        encryptedText = String.valueOf(encryptedTextCharArray);
        return encryptedText;
    }

    public String decrypt(String encryptedText, int shift) {
        return null;
    }
}
