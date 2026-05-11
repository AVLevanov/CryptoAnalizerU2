package com.javarush.levanov.ver_2.utils;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

// Логируем результат событий
public class Logger {

    // Создаем единственный поток при инициализации
    static BufferedWriter bufferedWriter;
    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Environment.LOD_DATE_TEME_FORMAT);

    static {
        try {
            bufferedWriter = Files.newBufferedWriter(Path.of(Environment.LOG_PATH), StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // Добавляем строку
    public void logEvent(com.javarush.levanov.ver_2.controller.result.Result result) {
        try {
            bufferedWriter.append(constructLogLine(result));
            bufferedWriter.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // Строим строку для журнала
    private String constructLogLine(com.javarush.levanov.ver_2.controller.result.Result result) {
        LocalDateTime localDateTime = LocalDateTime.now();
        String formattedDateTime = formatter.format(localDateTime);
        return String.format("%s   action: %s   status: %s   message: %s\n", formattedDateTime, result.action, result.status, result.message);
    }
}
