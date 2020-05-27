package Controller;

import Model.Position;

import java.util.*;

public class ArenaController {

    private List<BrickController> bricks;
    private int width, brickRows, x, y, level;

    public ArenaController(int width, Position initPos, int level)
    {
        brickRows = 8;
        this.width = width;
        this.x = initPos.getX();
        this.y = initPos.getY();
        this.level = level;
        initBricks();
    }

    private void initBricks() {
        bricks = new ArrayList<>();

        if(level == 1) {
            for (int i = 1; i < brickRows; i++) {
                for (int j = 5; j < width - 5; j++) {
                    BrickController block = new NormalBrickController(new Position(x + j, y + i));
                    bricks.add(block);
                }
            }
        }

        else if(level == 2) {
            for (int i = 1; i < brickRows; i++) {
                for (int j = 15; j < width - 15; j++) {
                    BrickController block2 = new SpecialBrickController(new Position(x + j, y + i));
                    bricks.add(block2);
                    bricks.add(block2);
                }
            }
        }
        else if(level == 3) {
            for (int i = 1; i < brickRows; i++) {
                BrickController block = new SpecialBrickController(new Position(x + 2*i - 1, y + i));
                bricks.add(block);
                bricks.add(block);
                BrickController block1 = new SpecialBrickController(new Position(x + width - 2*i, y + i));
                bricks.add(block1);
                bricks.add(block1);
                for (int j = 15; j < width - 15; j++) {
                    BrickController block2 = new NormalBrickController(new Position(x + j, y + i));
                    bricks.add(block2);
                }
            }
        }
    }

    private BrickController getBricks (Position pos)
    {
        Iterator<BrickController> it = bricks.iterator();
        while (it.hasNext())
        {
            BrickController next = it.next();
            if (pos.equals(next.getPosition()))
                return next;
        }
        return null;
    }

    public void collide(Position pos)
    {
        BrickController brick = getBricks(pos);
        int brickLevel = brick.getLevel();
        bricks.remove(brick);

        if(brickLevel == 0) {
            BrickController newBrick = new NormalBrickController(brick.getPosition());
            newBrick.setScore(brick.getScore() + newBrick.getScore());
            bricks.add(newBrick);
        }
        if (brickLevel == 2) {
            BrickController newBrick2 = new SpecialBrickController(brick.getPosition());
            newBrick2.setScore(brick.getScore() + newBrick2.getScore());
            bricks.add(newBrick2);
        }
    }

    public List<BrickController> getBricks(){
        return this.bricks;
    }

}

