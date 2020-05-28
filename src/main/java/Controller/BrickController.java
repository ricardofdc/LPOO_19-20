package Controller;

import Model.Brick;
import Model.Element;
import Model.Position;

import java.util.*;

public class BrickController
{
    private List<Brick> bricks;

    public BrickController(List<Brick> bricks)
    {
        this.bricks = bricks;
    }


    public void hitBrick(Brick brick) {
        for(Brick b : this.bricks){
            if(b.getPosition().equals(brick.getPosition())){
                //brick atingido
                //reduzir o seu valor
                //eliminar caso o valor atinja 0

                break;
            }
        }
    }
}
