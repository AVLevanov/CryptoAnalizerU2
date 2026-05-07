package com.javarush.levanov.constant;

public class Constants {
    public static final char[] ALPHABET = {'а', 'б', 'в', 'г', 'д', 'е', 'ё', 'ж', 'з',
            'и', 'й', 'к', 'л', 'м', 'н', 'о', 'п', 'р', 'с', 'т', 'у', 'ф', 'х', 'ц', 'ч', 'ш', 'щ',
            'ъ', 'ы', 'ь', 'э', 'ю', 'я', 'А', 'Б', 'В', 'Г', 'Д', 'Е', 'Ё', 'Ж', 'З',
            'И', 'Й', 'К', 'Л', 'М', 'Н', 'О', 'П', 'Р', 'С', 'Т', 'У', 'Ф', 'Х', 'Ц', 'Ч', 'Ш', 'Щ',
            'Ы', 'Э', 'Ю', 'Я', '.', ',', '«', '»', '"', '\'', ':', '!', '?', ' '};

    public static final String[] KEY_WORDS = {"и", "в", "на", "не", "я", "он", "она", "человк", "время", "год", "был"};
    public static final String[] PRECEDING_CHARACTERS = {" ", "\n"};
    public static final String[] NEXT_CHARACTERS = {" ", ".", ",", ";", ":", "\"", "!", "?"};

    public static final String ERR_INPUT_KEY = "Ключ должен быть целым числом от 0 до " + Integer.MAX_VALUE + ": ";

}
