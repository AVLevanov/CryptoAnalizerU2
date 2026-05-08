package com.javarush.levanov.ver_1;

import com.javarush.levanov.ver_2.constant.Constants;

public class Validator {
    int key;

//    проверяем корректность ввода ключа
    public boolean isKeyCorrect(String key) {
        boolean isKeyCorrect = false;
        try {
            this.key = Integer.parseInt(key);
            if (this.key >= 0) {
                isKeyCorrect = true;
            } else {
                System.out.print(Constants.ERR_INPUT_KEY);
            }
        } catch (NumberFormatException e) {
            System.out.print(Constants.ERR_INPUT_KEY);
        }
        return isKeyCorrect;
    }

    public int getKey() {
        return key;
    }
}
