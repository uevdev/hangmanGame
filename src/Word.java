import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Word {
    private String word;
    private final StringBuilder maskedWord = new StringBuilder();
    private final List<String> words;
    private final Random randomizer;
    private int wordLength;

    Word() {
        try (Scanner fileScanner = new Scanner(new File("russianNouns.txt"))) {
            words = new ArrayList<>();
            while (fileScanner.hasNextLine()) {
                words.add(fileScanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        randomizer = new Random();

        selectWord();
    }

    /// Выбор слова из списка
    void selectWord() {
        while (word == null) {
            var pretenderWord = words.get( randomizer.nextInt(0, words.size()) );
            if (pretenderWord.length() > 5) {
                word = pretenderWord;
            }
        }

        wordLength = word.length();
        maskedWord.append("_".repeat(wordLength));
    }

    /// Проверка, есть ли буква в слове
    boolean checkGuess(char letter) {
        return word.contains(Character.toString(letter));
    }

    /// Обновление маски слова
    void updateMaskedWord(char letter) {
        for (int i = 0; i < wordLength; i++) {
            if (word.charAt(i) == letter) {
                maskedWord.setCharAt(i, letter);
            }
        }
    }

    /// Вывести маску слова
    String getMaskedWord() {
        return maskedWord.toString();
    }

    /// Вывести загаданное слово
    String getWord() {
        return word;
    }
}
