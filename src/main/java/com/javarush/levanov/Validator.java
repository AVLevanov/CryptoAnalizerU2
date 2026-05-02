package com.javarush.levanov;

public class Validator {
    private static int shift;

    public boolean isKeyCorrect(String key) {
        boolean isKeyCorrect = false;
        try {
            shift = Integer.parseInt(key);
            if (shift >= 0) {
                isKeyCorrect = true;
            } else {
                System.out.println("Ключ должен быть целым числом от 0 до " + Integer.MAX_VALUE);
            }
            return isKeyCorrect;
        } catch (NumberFormatException e) {
            System.out.println("Ключ должен быть целым числом от 0 до " + Integer.MAX_VALUE);
            return isKeyCorrect;
        }
    }

    public int getShift() {
        return shift;
    }
}
