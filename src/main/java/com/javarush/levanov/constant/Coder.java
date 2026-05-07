package com.javarush.levanov.constant;

import java.util.HashMap;
import java.util.Map;

import static com.javarush.levanov.constant.Constants.ALPHABET;

public class Coder {
    public Map<Character, Character> cipher;

    public Coder(int key) {
        int shift = key % ALPHABET.length; //нормируем сдвиг на длину алфавита, чтобы можно было вводить любые значения
        cipher = new HashMap<>();
        for (int i = 0; i < ALPHABET.length; i++) {
            cipher.put(ALPHABET[i], ALPHABET[(i + shift + ALPHABET.length) % ALPHABET.length]);
        }
    }
}
