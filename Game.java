import java.util.Arrays;
import java.util.Objects;

public class Game {
    private String[] secretWord;
    private String[] userAnswer;
    private int attempts;

    Game(String secretWord) {
        this.secretWord = secretWord.split("");
        this.userAnswer = new String[secretWord.length()];
        Arrays.fill(userAnswer, "_");
        this.attempts = 1;
    }

    public void addLetterInUserAnswer(String userLetter) {
        if (isUserLetterInWord(userLetter, userAnswer)) {
            System.out.println("Вы уже называли эту букву.");
            return;
        }
        if (isUserLetterInWord(userLetter, secretWord)) {
            for (int i = 0; i < secretWord.length; i++) {
                if (secretWord[i].equals(userLetter)) {
                    userAnswer[i] = secretWord[i];
                }
            }
            System.out.println("Есть такая буква!");
        } else {
            attempts++;
            System.out.println("Этой буквы нет в слове.");
        }


    }

    private boolean isUserLetterInWord(String userLetter, String[] word) {
        for (String letter : word) {
            if (letter.equals(userLetter)) {
                return true;
            }
        }
        return false;
    }

    public boolean isUserGuessWord() {
        return Arrays.equals(secretWord, userAnswer);
    }

    public String getSecretWord() {
        StringBuilder word = new StringBuilder();
        for (String letter : secretWord) {
            word.append(letter);
        }
        return word.toString();
    }

    public String getUserAnswer() {
        StringBuilder word = new StringBuilder();
        for (String letter : userAnswer) {
            word.append(letter);
        }
        return word.toString();
    }

    public int getAttempts() {
        return attempts;
    }
}
