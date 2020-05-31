package Controller.PlayGame;

import Model.Brick;
import Model.Element;
import Model.Position;

import java.util.*;

public class BrickController
{
    private HashMap<Position, Brick> bricks_hm;
    private List<Brick> bricks;

    public BrickController(List<Brick> bricks)
    {
        this.bricks = bricks;
        this.bricks_hm = new HashMap<>();
        for (Brick brick : bricks ) {
            this.bricks_hm.put(brick.getPosition(), brick);
        }
    }


    public int hitBrick(Position brickPos) {
        /*
        return binary xxxx (left, right, up, down)
        0101 -> existe um tijolo à direita e um acima
        1001 -> existe um tijolo à esquerda e um acima
        (...)
         */

        Brick brick = bricks_hm.get(brickPos);
        brick.decreaseValue();
        if(brick.getValue() == 0){
            bricks.remove(brick);
            bricks_hm.remove(brickPos);
        }

        //calcular os vizinhos para definir futura trajetoria da bola
        int neighbours = 0;
        if(bricks_hm.containsKey(new Position(brickPos.getX()-1, brickPos.getY()))){
            neighbours |= 0b1000;
        }
        if(bricks_hm.containsKey(new Position(brickPos.getX()+1, brickPos.getY()))){
            neighbours |= 0b0100;
        }
        if(bricks_hm.containsKey(new Position(brickPos.getX(), brickPos.getY()-1))){
            neighbours |= 0b0010;
        }
        if(bricks_hm.containsKey(new Position(brickPos.getX(), brickPos.getY()+1))){
            neighbours |= 0b0001;
        }
        return neighbours;

    }

    public boolean anyBricksLeft() {
        return bricks.size()>0;
    }
}
