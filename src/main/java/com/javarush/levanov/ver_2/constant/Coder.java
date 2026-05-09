package com.javarush.levanov.ver_2.constant;

import java.util.HashMap;
import java.util.Map;

import static com.javarush.levanov.ver_2.constant.Constants.ALPHABET;

public class Coder {
    private static final Map<Character, Character> cipher = new HashMap<>();

    public static void setKey(int key) {
        key = key % ALPHABET.length; //нормируем сдвиг на длину алфавита, чтобы можно было вводить любые значения
        for (int i = 0; i < ALPHABET.length; i++) {
            cipher.put(ALPHABET[i], ALPHABET[(i + key + ALPHABET.length) % ALPHABET.length]);
        }
    }

    public static Map<Character, Character> getCipher() {
        return cipher;
    }
}
