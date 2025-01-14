import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Game {
    private Word word;
    private HangmanStage currentStage;
    private final int maxAttempts = 6;
    private int wrongAttempts;
    private final Scanner textScanner = new Scanner(System.in);
    private HashSet<Character> guesses;
    private HashSet<Character> wrongGuesses;

    /// Инициализация игры, запрос имени, выбор слова, начало цикла
    void start() {
        word = new Word();
        currentStage = HangmanStage.STAGE0;
        wrongAttempts = 0;
        guesses = HashSet.newHashSet(20);
        wrongGuesses = HashSet.newHashSet(20);

        while (wrongAttempts < maxAttempts && word.getMaskedWord().contains("_")) {
            printCurrentState();
            playRound();
        }
        end();
    }


    /// Одна итерация: запрос буквы и передача в функцию проверки и обновления
    void playRound() {
        System.out.print("Введите букву: ");
        char guess = textScanner.nextLine().charAt(0);
        if (isCharCorrect(guess)) {
            makeGuess(guess);
        } else {
            System.out.println("Подходящими являются буквы Кириллического алфавита в нижнем регистре!");
            playRound();
        }

    }


    /// Отображение текущего состояния слова и изображения виселицы
    void printCurrentState() {
        System.out.println(currentStage.getRepresentation());
        System.out.println( "Текущее слово: " + word.getMaskedWord());
    }


    ///  Проверяет, угадал ли букву и обновляет множество угаданных
    void makeGuess(char letter) {
        if (word.checkGuess(letter)) {
            if (!hasGuessed(letter)) {
                guesses.add(letter);
                word.updateMaskedWord(letter);
                if (hasFullGuessed()) {
                    end();
                }
            }
        } else {
            if (!wrongGuesses.contains(letter)) {
                wrongGuesses.add(letter);
                wrongAttempts++;
                currentStage = currentStage.nextStage();
            }

        }
    }


    /// Валидация символа
    boolean isCharCorrect(char letter) {
        return 'а' <= letter && letter <= 'я';
    }



    /// Проверка на полное угадывание
    boolean hasFullGuessed() {
        return guesses.equals(Set.of(word.getWord()));
    }


    /// Проверка на наличие буквы в множестве угаданных
    boolean hasGuessed(char letter) {
        return guesses.contains(letter);
    }


    /// Завершает игру в любом месте (если слово недоотгадано, но сбрасывает результат последней игры)
    void end() {
        if (wrongAttempts < maxAttempts) {
            System.out.println("Поздравляем, вы выиграли!");
            oneMoreGame();
        } else {
            System.out.println(currentStage.getRepresentation());
            System.out.printf("Вы проиграли. Загаданное слово: %s\n", word.getWord());
            oneMoreGame();
        }
    }


    void oneMoreGame() {
        System.out.print("Начать новую игру (да/нет) ? ");
        switch (textScanner.nextLine()) {
            case "да" -> start();
            case "нет" -> System.out.println();
            default -> oneMoreGame();
        }


    }
}
