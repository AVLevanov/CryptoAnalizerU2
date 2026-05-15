package com.javarush.levanov.ver_2.constant;

public interface Constants {
    Character [] ALPHABET = {'а', 'б', 'в', 'г', 'д', 'е', 'ё', 'ж', 'з',
            'и', 'й', 'к', 'л', 'м', 'н', 'о', 'п', 'р', 'с', 'т', 'у', 'ф', 'х', 'ц', 'ч', 'ш', 'щ',
            'ъ', 'ы', 'ь', 'э', 'ю', 'я', '.', ',', '«', '»', '"', '\'', ':', '!', '?', ' '};

    String[] KEY_WORDS = {"и", "в", "на", "не", "я", "он", "она", "человек", "время", "год", "был"};
    String[] PRECEDING_CHARACTERS = {" ", "\n"};
    String[] NEXT_CHARACTERS = {" ", ".", ",", ";", ":", "\"", "!", "?", "\n"};

    String LOD_DATE_TIME_FORMAT = "dd.MM.yyyy HH:mm:ss";
}
