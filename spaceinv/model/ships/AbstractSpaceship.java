package spaceinv.model.ships;

import spaceinv.model.AbstractPositionable;
import spaceinv.model.Positionable;
import spaceinv.model.Projectile;
import spaceinv.model.Shootable;

import static spaceinv.model.SI.*;

public abstract class AbstractSpaceship extends AbstractPositionable implements Positionable, Shootable {

    public AbstractSpaceship(double x, double y, double width, double height) {
        super(x, y, width, height, SHIP_MAX_DX, 0);
    }

    public void changeDir() {
        setDx(getDx()*(-1));
    }

    public void shiftDown() {
        setY(getY() + SHIP_MAX_DY);
    }

    abstract public int getShipPoints();


    @Override
    public Projectile fire() {
        Projectile p = new Projectile(-0.5);
        p.setY(-10);
        p.setX(-10);
        return p;
    }


    @Override
    public void align() {
        if (checkLeftCollision()) {
            setX(getX() + SHIP_MAX_DX);
        } else {
            setX(getX() - SHIP_MAX_DX);
        }
    }

    @Override
    public boolean checkLeftCollision() {
        return this.getX() < 10;
    }

    @Override
    public boolean checkRightCollision() {
        return this.getX() > GAME_WIDTH - (this.getWidth() + 10);
    }

}
