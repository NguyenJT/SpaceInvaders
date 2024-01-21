package spaceinv.model;


import static spaceinv.model.SI.*;

/*
    The ground where the Gun lives.
    Used to check if projectiles from ships have hit the ground
 */
public class Ground extends AbstractPositionable {

    public Ground(double x, double y, double width, double height, double dx, double dy) {
        super(x, y, width, height, dx, dy);
    }

    public Ground(double x, double y) {
        super(x, y, GAME_WIDTH, GROUND_HEIGHT, 0, 0);

    }
}
