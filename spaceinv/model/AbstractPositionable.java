package spaceinv.model;

import static spaceinv.model.SI.*;

public abstract class AbstractPositionable {
    // position
    private double x;
    private double y;

    // size
    private final double width;
    private final double height;

    // speed
    private double dx;
    private double dy;

    public AbstractPositionable(double x, double y, double width, double height, double dx, double dy) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.dx = dx;
        this.dy = dy;
    }

    //Setters
    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setDx(double dx) {
        this.dx = dx;
    }

    public void setDy(double dy) {
        this.dy = dy;
    }

    //Getters
    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getDx() {
        return dx;
    }

    public double getDy() {
        return dy;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public boolean checkLeftCollision() {
        return this.getX() < 0;
    }

    public boolean checkRightCollision() {
        return this.getX() > GAME_WIDTH - width;
    }

    public void align() {
        if (checkLeftCollision()) {
            setX(0);
        } else {
            setX(GAME_WIDTH - width);
        }
    }
    public void move() {
        x += dx;
        y += dy;
    }

    public boolean intersects(AbstractPositionable other) {
        // treat objects like squares
        boolean a = getY() + getHeight() > other.getY();
        boolean b = getY() < other.getY() + other.getHeight();
        boolean c = getX() + getWidth() > other.getX();
        boolean d = getX() < other.getX() + other.getWidth();
        return (a && b && c && d);
    }

}



