package Model;


import java.util.ArrayList;
import java.util.List;

public class Ship extends Element
{
    private Position initPosition;
    private int length = 9; //deve ser sempre um valor ímpar
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

    public int getLifes() {
        return lifes;
    }

    public void decreaseLifes() {
        this.lifes--;
    }

    public void moveLeft(){
        this.setPosition(this.getPosition().left());
        List<Position> newPositions = new ArrayList<>();
        for(Position pos: actualPositions){
            newPositions.add(pos.left());
        }
        actualPositions = newPositions;
    }

    public void moveRight(){
        this.setPosition(this.getPosition().right());
        List<Position> newPositions = new ArrayList<>();
        for(Position pos: actualPositions){
            newPositions.add(pos.right());
        }
        actualPositions = newPositions;
    }

    public void startPosition(){
        this.setPosition(new Position(initPosition.getX(), initPosition.getY()));
        this.actualPositions = new ArrayList<>();
        for(int i= getPosition().getX() - Math.floorDiv(length,2); i <= getPosition().getX() + Math.floorDiv(length,2); i++){
            this.actualPositions.add(new Position(i, getPosition().getY()));
        }
    }

    public int getLength(){
        return this.length;
    }

    public Position getLeftExtreme(){
        return this.actualPositions.get(0);
        //return new Position(position.getX() + Math.floorDiv(length,2), position.getY());
    }

    public Position getRightExtreme(){
        return this.actualPositions.get(length-1);
        //return new Position(position.getX() - Math.floorDiv(length,2), position.getY());
    }

    public List<Position> getActualPositions(){
        return this.actualPositions;
    }
}
