package com.javarush.levanov.utilApps;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.javarush.levanov.utilApps.Constants.ALPHABET;

public class Coder {
    private final Map<Character, Character> cipher = new HashMap<>();

    public Map<Character, Character> getCipher() {
        return cipher;
    }

    // Set Ceaser cipher with key for ENCRYPT, DECRYPT and BRUTE FORCE
    public void setCeaserCipher(Coder coder, int key) {
        key = key % ALPHABET.length; //нормируем сдвиг на длину алфавита, чтобы можно было вводить любые значения
        for (int i = 0; i < ALPHABET.length; i++) {
            cipher.put(ALPHABET[i], ALPHABET[(i + key + ALPHABET.length) % ALPHABET.length]);
        }
    }

    // Sert best found cipher from statistic analyze
    public void setCipher(List<Character> bestAlphabet) {
        for (int i = 0; i < ALPHABET.length; i++) {
            cipher.put(bestAlphabet.get(i), ALPHABET[i]);
        }
    }
}
