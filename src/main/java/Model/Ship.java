package Model;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.util.ArrayList;
import java.util.List;

public class Ship extends Element
{
    private Position initPosition;
    private int length = 7; //deve ser sempre um valor ímpar
    private int lifes = 3;
    private List<Position> actualPositions;

    public Ship(Position pos)
    {
        super(pos);
        this.initPosition = new Position(pos.getX(),pos.getY());
        this.actualPositions = new ArrayList<>();
        for(int i= pos.getX() - Math.floorDiv(length,2); i <= pos.getX() + Math.floorDiv(length,2); i++){
            this.actualPositions.add(new Position(i, pos.getY()));
        }
    }

    public void draw(TextGraphics graphics)
    {
       String ship = "";
        for (int i = 0; i < length; i++)
            ship += "\u2580";

        graphics.setForegroundColor(TextColor.Factory.fromString("#993d00"));
        graphics.putString(new TerminalPosition(position.getX(), position.getY()), ship);
    }

    public int getLifes() {
        return lifes;
    }

    public void decreaseLifes() {
        this.lifes--;
    }

    public void moveLeft(){
        this.position = this.position.left();
        for(Position pos: actualPositions){
            pos = pos.left();
        }
    }

    public void moveRight(){
        this.position = this.position.right();
        for(Position pos: actualPositions){
            pos = pos.right();
        }
    }

    public void startPosition(){
        this.position = new Position(initPosition.getX(), initPosition.getY());
        this.actualPositions = new ArrayList<>();
        for(int i= position.getX() - Math.floorDiv(length,2); i <= position.getX() + Math.floorDiv(length,2); i++){
            this.actualPositions.add(new Position(i, position.getY()));
        }
    }

    public int getLength(){
        return this.length;
    }

    public Position getRightExtreme(){
        return this.actualPositions.get(0);
        //return new Position(position.getX() + Math.floorDiv(length,2), position.getY());
    }

    public Position getLeftExtreme(){
        return this.actualPositions.get(length-1);
        //return new Position(position.getX() - Math.floorDiv(length,2), position.getY());
    }

    public List<Position> getActualPositions(){
        return this.actualPositions;
    }
}
