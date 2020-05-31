package Controller;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class HighScoresController implements StateController {
    ArrayList<String> names;
    ArrayList<Integer> scores;

    HighScoresController(){
        names = new ArrayList<>();
        scores = new ArrayList<>();

        String filename = "scores.txt";
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(filename);
        assert inputStream != null;
        Scanner scanner = new Scanner(inputStream);

        while (scanner.hasNext()){
            names.add(scanner.next());
            scores.add(scanner.nextInt());
        }
    }

    @Override
    public StateController processInput(String input) {
        return new MainMenuController();
    }

    @Override
    public String toString() {
        return "HighScores";
    }

    public ArrayList<String> getNames() {
        return names;
    }

    public ArrayList<Integer> getScores() {
        return scores;
    }
}
