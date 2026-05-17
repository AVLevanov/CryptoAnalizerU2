package com.javarush.levanov.utilApps;

import com.javarush.levanov.controller.result.Result;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {
    private final DateTimeFormatter formatter;
    private final BufferedWriter bufferedWriter;

    public Logger() {
        formatter = DateTimeFormatter.ofPattern(Constants.LOD_DATE_TIME_FORMAT);
        Path logPath = Path.of(System.getProperty("user.dir"), "log.txt");
        try {
            if (!Files.exists(logPath)) {
                Files.createFile(logPath);
            }
            bufferedWriter = Files.newBufferedWriter(logPath, StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void logEvent(Result result) {
        try {
            bufferedWriter.append(constructLogLine(result));
            bufferedWriter.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String constructLogLine(Result result) {
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
