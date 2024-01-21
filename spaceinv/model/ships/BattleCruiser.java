package spaceinv.model.ships;

import spaceinv.model.Positionable;

import static spaceinv.model.SI.*;

/*
 *   Type of space ship
 */
public class BattleCruiser extends AbstractSpaceship implements Positionable {

    // Default value
    public static final int BATTLE_CRUISER = 300;

    public BattleCruiser(double x, double y) {
        super(x, y, SHIP_WIDTH, SHIP_HEIGHT);
    }
    @Override
    public int getShipPoints() {
        return BATTLE_CRUISER;
    }
}
