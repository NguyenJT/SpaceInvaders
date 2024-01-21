package spaceinv.model;


import spaceinv.event.EventBus;
import spaceinv.event.ModelEvent;
import spaceinv.model.ships.AbstractSpaceship;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/*
 *  SI (Space Invader) class representing the overall
 *  data and logic of the game
 *  (nothing about the look/rendering here)
 */
public class SI {

    // Default values (not to use directly). Make program adaptable
    // by letting other programmers set values if they wish.
    // If not, set default values (as a service)
    public static final int GAME_WIDTH = 500;
    public static final int GAME_HEIGHT = 500;
    public static final int LEFT_LIMIT = 50;
    public static final int RIGHT_LIMIT = 450;
    public static final int SHIP_WIDTH = 20;
    public static final int SHIP_HEIGHT = 20;
    public static final int SHIP_MAX_DX = 5;
    public static final int SHIP_MAX_DY = 100;
    public static final int GUN_WIDTH = 20;
    public static final int GUN_HEIGHT = 20;
    public static final double GUN_MAX_DX = 2;
    public static final double PROJECTILE_WIDTH = 5;
    public static final double PROJECTILE_HEIGHT = 5;
    public static final int GROUND_HEIGHT = 20;
    public static final int OUTER_SPACE_HEIGHT = 10;

    public static final long ONE_SEC = 1_000_000_000;
    public static final long HALF_SEC = 500_000_000;
    public static final long TENTH_SEC = 100_000_000;

    private static final Random rand = new Random();

    private final Gun gun;
    private final Projectile gunProjectile;
    private boolean gunHasFired = false;

    private List<AbstractSpaceship> ships;
    private List<AbstractSpaceship> destroyedShips = new ArrayList<>();
    private final List<Projectile> shipBombs = new ArrayList<>();
    private AbstractSpaceship randomShip;
    private Projectile shipProjectile;
    private boolean shipHasFired = false;
    private int points;

    Ground ground = new Ground(0, GAME_HEIGHT);
    OuterSpace outerSpace = new OuterSpace(0, 0);

    public SI(Gun g, List<AbstractSpaceship> ships) {
        this.gun = g;
        this.gunProjectile = gun.fire();
        this.ships = ships;
    }

    public Gun getGun() {
        return gun;
    }

    public boolean gunHasFired() {
        return gunHasFired;
    }

    // Timing. All timing handled here!
    private long lastTimeForMove;
    private long lastTimeForFire;

    private int shipToMove = 0;

    // ------ Game loop (called by timer) -----------------
    public void update(long now) {

        if (ships.size() == 0){
            EventBus.INSTANCE.publish(new ModelEvent(ModelEvent.Type.HAS_WON));
        }

        // Gun movement
        if (gun.getDx() != 0) {
            gun.move();
            if (gun.checkLeftCollision() || gun.checkRightCollision()) {
                gun.align();
            }
        }

        // Gun projectile
        if (gunHasFired && gunProjectile.intersects(outerSpace)) {
            gunProjectile.destroyProjectile();
            gunHasFired = false;
        } else if (gunHasFired) {
            gunProjectile.move();
        }

        // Ship movement
        if (shipToMove >= ships.size()) {
            shipToMove = shipToMove % ships.size();
        }

        ships.get(shipToMove).move();

        if (ships.get(shipToMove).checkLeftCollision() || ships.get(shipToMove).checkRightCollision()) {
            ships.get(shipToMove).align();
            for (var ship : ships) {
                ship.changeDir();
                ship.shiftDown();
            }
        }
        shipToMove = (shipToMove + 1) % ships.size();

        // Ship bombs
        if (!shipHasFired) {
            int index = rand.nextInt(ships.size());
            randomShip = ships.get(index);
            shipProjectile = randomShip.fire();
            shipBombs.add(shipProjectile);
            shipProjectile.setX((randomShip.getX() + SHIP_WIDTH / 2) - shipProjectile.getWidth() / 2);
            shipProjectile.setY(randomShip.getY() + randomShip.getHeight() - shipProjectile.getHeight());
            shipHasFired = true;
        } else if (shipHasFired) {
            shipProjectile.move();
        }


        // Spaceship collision
        for (var ship : ships) {
            if (ship.intersects(gunProjectile)) {
                EventBus.INSTANCE.publish(new ModelEvent(ModelEvent.Type.GUN_HIT_SHIP, ship));
                points += ship.getShipPoints();
                destroyedShips.add(ship);
                gunProjectile.destroyProjectile();
                gunHasFired = false;
            } else if (ship.intersects(ground) || ship.intersects(gun)) {
                EventBus.INSTANCE.publish(new ModelEvent(ModelEvent.Type.HAS_LOST));
                break;
            }
        }
        ships.removeAll(destroyedShips);
        destroyedShips.clear();

        // Spaceship bomb collision
        if (shipProjectile.intersects(gun)) {
            EventBus.INSTANCE.publish(new ModelEvent(ModelEvent.Type.HAS_LOST));
        } else if (shipProjectile.intersects(gunProjectile)) {
            gunProjectile.destroyProjectile();;
            shipProjectile.destroyProjectile();
            shipBombs.remove(shipProjectile);
            shipHasFired = false;
            gunHasFired = false;
        } else if (shipHasFired && shipProjectile.intersects(ground)) {
            shipProjectile.destroyProjectile();
            shipBombs.remove(shipProjectile);
            shipHasFired = false;
        }
    }

    // ---------- Interaction with GUI  -------------------------
    public void fireGun() {
        gunProjectile.setX((gun.getX() + GUN_WIDTH / 2) - gunProjectile.getWidth() / 2);
        gunProjectile.setY(gun.getY() - gunProjectile.getHeight());
        gunHasFired = true;
    }

    public List<Positionable> getPositionables() {
        List<Positionable> ps = new ArrayList<>();
        ps.add(gun);
        if (gunHasFired) {
            ps.add(gunProjectile);
        }
        for (var ship : ships) {
            ps.add(ship);
        }
        for (var bomb : shipBombs) {
            ps.add(bomb);
        }
        return ps;
    }

    public int getPoints() {
        return points;
    }
}
