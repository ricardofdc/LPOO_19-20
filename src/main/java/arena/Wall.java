package arena;

public class Wall extends Element {
    public Wall(int x, int y) {
        super(x, y);

        this.setColor("#000000");
        this.setCharacter("#");
    }
}
