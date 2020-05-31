package Model;

import jdk.nashorn.internal.objects.AccessorPropertyDescriptor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ArenaCreator {

    public Arena createArena(int level, int score){
        String filename = "level" + level + ".lvl";
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(filename);
        assert inputStream != null;
        InputStreamReader inputReader = new InputStreamReader(inputStream);
        BufferedReader buffer = new BufferedReader(inputReader);
        int width = 56;
        int height = 25;

        try {
            Ship ship = new Ship(new Position(width / 2, height - 1));
            Ball ball = new Ball(new Position(width/2, height-2));
            Arena arena = new Arena(ship, ball, width+2, height+2, level, score);

            for(int i=0; i<=width+1; i++){
                arena.addWall(new Wall(new Position(i,0)));
                arena.addWall(new Wall(new Position(i,height+1)));
            }
            for(int i=1; i<=height; i++){
                arena.addWall(new Wall(new Position(0,i)));
                arena.addWall(new Wall(new Position(width+1,i)));
            }

            for(int i=0; i<height; i++ ){
                for(int j=0; j<=width; j++){
                    char valueRead = (char) buffer.read();
                    if(valueRead == '\n')
                        continue;
                    if(valueRead != '.'){
                        Brick brick = new Brick(new Position(j+1, i+1), Character.getNumericValue(valueRead));
                        arena.addBrick(brick);
                    }
                }
            }
            buffer.close();
            inputReader.close();
            inputStream.close();
            return arena;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
