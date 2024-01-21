package spaceinv.model.ships;

import spaceinv.model.Positionable;

import static spaceinv.model.SI.*;

/*
 *   Type of space ship
 */
public class Frigate extends AbstractSpaceship implements Positionable {

    // Default value
    public static final int FRIGATE_POINTS = 300;

    public Frigate(double x, double y) {
        super(x, y, SHIP_WIDTH, SHIP_HEIGHT);
    }

    @Override
    public int getShipPoints() {
        return FRIGATE_POINTS;
    }
}
