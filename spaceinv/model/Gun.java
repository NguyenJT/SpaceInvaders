package spaceinv.model;


import static spaceinv.model.SI.*;

/*
 *    A Gun for the game
 *    Can only fire one projectile at the time
 */
public class Gun extends AbstractPositionable implements Positionable, Shootable  {

    public Gun(double x, double y) {
        super(x, y, GUN_WIDTH, GUN_HEIGHT, 0, 0);
    }


    @Override
    public Projectile fire() {
        Projectile p = new Projectile(1);
        p.setY(-10);
        p.setX(-10);
        return p;
    }


}
