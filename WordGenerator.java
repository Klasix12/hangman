import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

public class WordGenerator {
    String filePath;
    int fileLinesCount = 0;

    public WordGenerator(String filePath) {
        this.filePath = filePath;
        this.fileLinesCount = getFileLinesCount();
    }

    public String getRandomWord() {
        int randomInt = new Random().nextInt(fileLinesCount);
        String word = "";
        int linesCount = 0;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
            while (true) {
                linesCount++;
                bufferedReader.readLine();
                if (linesCount == randomInt) {
                    word = bufferedReader.readLine();
                    break;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Ошибка при чтении файла");
        }
        return word;
    }

    public int getFileLinesCount() {
        int linesCount = 0;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
            while (bufferedReader.readLine() != null) {
                linesCount++;
            }
        } catch (IOException e) {
            throw new RuntimeException("Ошибка при подсчете длины файла");
        }
        return linesCount;
    }

}
