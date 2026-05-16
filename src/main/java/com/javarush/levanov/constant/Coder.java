package com.javarush.levanov.constant;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.javarush.levanov.constant.Constants.ALPHABET;

public class Coder {
    private static final Map<Character, Character> coder = new HashMap<>();

    public static Map<Character, Character> getCoder() {
        return coder;
    }

    // Настраиваем шифратор
    public static void setKey(int key) {
        key = key % ALPHABET.length; //нормируем сдвиг на длину алфавита, чтобы можно было вводить любые значения
        for (int i = 0; i < ALPHABET.length; i++) {
            coder.put(ALPHABET[i], ALPHABET[(i + key + ALPHABET.length) % ALPHABET.length]);
        }
    }

    public static void setCoder(List<Character> bestAlphabet){
        for (int i = 0; i < ALPHABET.length; i++) {
            coder.put(bestAlphabet.get(i), ALPHABET[i]);
        }
    }
}
