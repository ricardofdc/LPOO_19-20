package Controller;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SaveScoreController implements StateController {


    public SaveScoreController(String name, int score) {
        ArrayList<String> names = new ArrayList<>();
        ArrayList<Integer> scores = new ArrayList<>();

        String filename = "scores.txt";
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(filename);
        assert inputStream != null;
        Scanner scanner = new Scanner(inputStream);

        while (scanner.hasNext()){
            names.add(scanner.next());
            scores.add(scanner.nextInt());
        }

        for(int i=0; i<names.size(); i++){
            if(score >= scores.get(i)){
                names.add(names.get(names.size()-1));
                scores.add(scores.get(scores.size()-1));
                for(int j=names.size()-2; j>i; j--){
                    names.set(j, names.get(j-1));
                    scores.set(j, scores.get(j-1));
                }
                names.set(i, name);
                scores.set(i, score);
                break;
            }
        }

        try {
            scanner.close();
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            PrintStream printStream = new PrintStream(new FileOutputStream(new File("src/main/resources/scores.txt")));
            for(int i=0; i<names.size(); i++){
                System.out.println(names.get(i) + " " + scores.get(i));
                printStream.println(names.get(i) + " " + scores.get(i));
            }
            printStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }



    }

    @Override
    public StateController processInput(String input) {
        return new MainMenuController();
    }

    @Override
    public String toString() {
        return "SaveScore";
    }
}
