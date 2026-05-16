package com.javarush.levanov.utils;

import com.javarush.levanov.constant.Constants;

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
    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Constants.LOD_DATE_TIME_FORMAT);
    static BufferedWriter bufferedWriter;

    static {
        try {
            bufferedWriter = Files.newBufferedWriter(Path.of(Constants.LOG_PATH), StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // Добавляем строку
    public void logEvent(com.javarush.levanov.result.Result result) {
        try {
            bufferedWriter.append(constructLogLine(result));
            bufferedWriter.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // Строим строку для журнала
    private String constructLogLine(com.javarush.levanov.result.Result result) {
        String logLine;
        LocalDateTime localDateTime = LocalDateTime.now();
        String formattedDateTime = formatter.format(localDateTime);
        if (result.message != null) {
            logLine = String.format(Constants.LINE_WITH_MESSAGE, formattedDateTime, result.action, result.status, result.message);
        } else {
            logLine = String.format(Constants.LINE_WITHOUT_MESSAGE, formattedDateTime, result.action);
        }
        return logLine;
    }
}
