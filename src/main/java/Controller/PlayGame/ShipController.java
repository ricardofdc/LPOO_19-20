package Controller.PlayGame;

import Model.Element;
import Model.Position;
import Model.Ship;

public class ShipController {
    private Ship ship;

    public ShipController(Ship ship) {
        this.ship = ship;
    }

    public boolean lifeLost() {
        ship.startPosition();
        this.ship.decreaseLifes();
        return this.ship.getLifes() == 0;
    }

    public void moveLeft(){
        ship.setPosition(ship.getPosition().left());
    }

    public void moveRight(){
        ship.setPosition(ship.getPosition().right());
    }
}