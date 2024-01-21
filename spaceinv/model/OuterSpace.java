package spaceinv.model;

import static spaceinv.model.SI.*;

/*
    Used to check if projectiles from gun have left our world
    Non visible class
 */
public class OuterSpace extends AbstractPositionable {

    public OuterSpace(double x, double y, double width, double height, double dx, double dy) {
        super(x, y, width, height, dx, dy);
    }

    public OuterSpace(double x, double y) {
        super(x, y, GAME_WIDTH, OUTER_SPACE_HEIGHT, 0, 0);
    }
}
