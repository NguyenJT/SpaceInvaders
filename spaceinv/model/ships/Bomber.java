package spaceinv.model.ships;

import spaceinv.model.Positionable;

import static spaceinv.model.SI.*;

/*
 *   Type of space ship
 */
public class Bomber extends AbstractSpaceship implements Positionable {
    // Default value
    public static final int BOMBER_POINTS = 200;

    public Bomber(double x, double y) {
        super(x, y, SHIP_WIDTH, SHIP_HEIGHT);
    }

    @Override
    public int getShipPoints() {
        return BOMBER_POINTS;
    }



}
