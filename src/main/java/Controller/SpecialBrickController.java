package Controller;
import Model.Position;

public class SpecialBrickController extends BrickController
{
    public SpecialBrickController(Position pos)
    {
        super(pos);
        this.id = "special";
        this.score = 5; // 2*5 = 10
        this.brickLevel = 3;
    }
}