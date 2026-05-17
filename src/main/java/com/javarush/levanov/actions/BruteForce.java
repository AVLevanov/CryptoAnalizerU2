package com.javarush.levanov.actions;

import com.javarush.levanov.utilApps.Coder;
import com.javarush.levanov.controller.request.ExecuteRequest;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import static com.javarush.levanov.utilApps.Constants.*;

public class BruteForce extends AbstractAction {
    public BruteForce(ExecuteRequest executeRequest) {
        execute(executeRequest);
    }

    // return decrypted text using best found key
    public void execute(ExecuteRequest executeRequest) {
        int bestKey = findBestKey(executeRequest.coder, executeRequest.inPath, executeRequest.outPath);
        executeRequest.coder.setCeaserCipher(executeRequest.coder, bestKey);
        code(executeRequest.coder, executeRequest.inPath, executeRequest.outPath);
    }

    // iterate all keys and return the one with the most repetitions of keywords
    private int findBestKey(Coder coder, Path inPath, Path outPath) {
        Map<Integer, Integer> keysAndCounters = new HashMap<>();
        for (int key = 1; key <= ALPHABET.length; key++) {
            coder.setCeaserCipher(coder, -key);
            code(coder, inPath, outPath);
            int counter = keyWordsCounter(outPath);
            keysAndCounters.put(-key, counter);
        }

        Iterator<Map.Entry<Integer, Integer>> iterator = keysAndCounters.entrySet().iterator();
        int maxCounterValue = 0;
        int bestKey = 0;
        while (iterator.hasNext()) {
            Map.Entry<Integer, Integer> nextEntry = iterator.next();
            if (nextEntry.getValue() > maxCounterValue) {
                maxCounterValue = nextEntry.getValue();
                bestKey = nextEntry.getKey();
            }
        }
        return bestKey;
    }

    // return repetitions of keywords of language
    private int keyWordsCounter(Path inPath) {
        int keyWordCounter = 0;
        for (String keyWord : KEY_WORDS) {
            keyWordCounter += wordCounter(inPath, keyWord);
        }
        return keyWordCounter;
    }

    // return number of word repetitions in the text, considering the characters before and after the word
    private int wordCounter(Path inPath, String word) {
        int wordCounter = 0;
        try (BufferedReader bufferedReader = Files.newBufferedReader(inPath)) {
            String nextLine;
            String wordCase;
            String nextLineToLowerCase;
            while ((nextLine = bufferedReader.readLine()) != null) {
                nextLineToLowerCase = nextLine.toLowerCase();
                for (String nextCharacter : NEXT_CHARACTERS) { // count the word in the beginning of the line
                    wordCase = word + nextCharacter;
                    if (nextLineToLowerCase.indexOf(wordCase, 0, Math.min(wordCase.length(), nextLineToLowerCase.length())) != -1) {
                        wordCounter++;
                    }
                    for (String precedingCharacter : PRECEDING_CHARACTERS) {  // count the word in the middle of the line
                        int pointer = 0;
                        wordCase = precedingCharacter + word + nextCharacter;
                        while (nextLineToLowerCase.indexOf(wordCase, pointer) != -1) {
                            wordCounter++;
                            pointer = nextLineToLowerCase.indexOf(wordCase, pointer) + wordCase.length();
                        }
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return wordCounter;
    }
}