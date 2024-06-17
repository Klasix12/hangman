import java.util.Scanner;

public class Hangman {
    private static final int TOTAL_ATTEMPTS = 7;
    private static final String[] HANGMAN_STAGES = {
            """
          +---+
          |   |
              |
              |
              |
              |
        =========""",
            """
          +---+
          |   |
          O   |
              |
              |
              |
        =========""",
            """
          +---+
          |   |
          O   |
          |   |
              |
              |
        =========""",
            """
          +---+
          |   |
          O   |
         /|   |
              |
              |
        =========""",
            """
          +---+
          |   |
          O   |
         /|\\  |
              |
              |
        =========""",
            """
          +---+
          |   |
          O   |
         /|\\  |
         /    |
              |
        =========""",
            """
          +---+
          |   |
          O   |
         /|\\  |
         / \\  |
              |
        ========="""
    };

    public static void main(String[] args) {
        WordGenerator wordGenerator = new WordGenerator("words.txt");
        Scanner scanner = new Scanner(System.in);
        startGame(wordGenerator, scanner);

        while (true) {
            printMenu();
            String userInput = scanner.nextLine();
            switch (userInput) {
                case "1":
                    startGame(wordGenerator, scanner);
                    break;
                case "2":
                    scanner.close();
                    return;
                default:
                    System.out.println("Неизвестная команда");
                    break;
            }
        }
    }

    private static void printMenu() {
        System.out.println("1 - Начало игры");
        System.out.println("2 - Выйти");
    }

    private static void startGame(WordGenerator wordGenerator, Scanner scanner) {
        Game game = new Game(wordGenerator.getRandomWord());
        System.out.println("В загаданном слове " + game.getSecretWord().length() + " букв. Введите букву.");

        while (true) {
            String userInput = scanner.nextLine();
            if (isCorrectUserInput(userInput)) {
                game.addLetterInUserAnswer(userInput);
            } else {
                System.out.println("Неверно введена буква");
                continue;
            }

            if (game.isUserGuessWord()) {
                System.out.println("Вы отгадали слово!");
                return;
            }

            int currentAttempt = game.getAttempts();

            if (currentAttempt == TOTAL_ATTEMPTS) {
                System.out.println("К сожалению вы не смогли отгадать слово. Загаданное слово: "
                        + game.getSecretWord());
                return;
            }
            System.out.println(HANGMAN_STAGES[currentAttempt - 1]);
            System.out.println(game.getUserAnswer());
        }
    }

    private static boolean isCorrectUserInput(String userInput) {
        return userInput.length() == 1 && !(userInput.toLowerCase().equals(userInput.toUpperCase()));
    }
}
