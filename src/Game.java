import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Game {
    private Word word;
    private HangmanStage currentStage;
    private final int maxAttempts = HangmanStage.values().length - 1;
    private int wrongAttempts;
    private final Scanner textScanner = new Scanner(System.in);
    private Set<Character> guesses;
    private Set<Character> wrongGuesses;

    /// Инициализация игры, запрос имени, выбор слова, начало цикла
    void start() {
        word = new Word();
        currentStage = HangmanStage.STAGE0;
        wrongAttempts = 0;
        guesses = HashSet.newHashSet(20);
        wrongGuesses = HashSet.newHashSet(7);

        while (wrongAttempts < maxAttempts && word.getMaskedWord().contains("_")) {
            printCurrentState();
            playRound();
        }
        end();
    }


    /// Одна итерация: запрос буквы и передача в функцию проверки и обновления
    void playRound() {
        char guess = 0;
        while (!isCharCorrect(guess)) {
            if (guess != 0)
                System.out.println("Подходящими являются буквы Кириллического алфавита в нижнем регистре!");
            try {
                System.out.print("Введите букву: ");
                String curr = textScanner.nextLine();
                if (curr.length() > 1) continue;
                guess = curr.charAt(0);
            } catch (Exception _) {}
        }
        makeGuess(guess);
    }


    /// Отображение текущего состояния слова и изображения виселицы
    void printCurrentState() {
        System.out.println(currentStage.getRepresentation());
        System.out.printf("Текущее слово: %s",word.getMaskedWord());
        if (!wrongGuesses.isEmpty()) System.out.printf(" (неверно: %s)",wrongGuesses);
        System.out.println();
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
        Set<Character> charSet = new HashSet<>();
        for (Character c : word.getWord().toCharArray()) charSet.add(c);
        return guesses.equals(charSet);
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
        String answer = "";
        while (!(answer.equalsIgnoreCase("да") || answer.equalsIgnoreCase("нет"))) {
            System.out.print("Начать новую игру (да/нет) ? ");
            answer = textScanner.nextLine();
        }
        switch (answer) {
            case "да" -> start();
            case "нет" -> {}
        }
    }
}
