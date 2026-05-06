package com.javarush.levanov;

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
                System.out.print("Ключ должен быть целым числом от 0 до " + Integer.MAX_VALUE + ": ");
            }
        } catch (NumberFormatException e) {
            System.out.print("Ключ должен быть целым числом от 0 до " + Integer.MAX_VALUE + ": ");
        }
        return isKeyCorrect;
    }

    public int getKey() {
        return key;
    }
}
