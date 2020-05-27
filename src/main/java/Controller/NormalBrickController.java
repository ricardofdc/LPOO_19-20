package Controller;
import Model.Position;

public class NormalBrickController extends BrickController
{
    public NormalBrickController(Position pos)
    {
        super(pos);
        this.id = "normal";
        this.score = 5;
        this.brickLevel = 1;
    }
}
