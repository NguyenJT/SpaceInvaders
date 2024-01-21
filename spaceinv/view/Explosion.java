package spaceinv.view;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/*
     Animation for an explosion

     *** Nothing to do here ***
 */
public class Explosion extends AnimationTimer {

    private static final Image image = Assets.INSTANCE.explosion;
    private double x;
    private double y;
    private double sx = 0;
    private double sy = 0;
    private int frameCounter = 0;
    private GraphicsContext gc;

    public Explosion(double x, double y, GraphicsContext gc) {
        this.x = x;
        this.y = y;
        this.gc = gc;
    }

    @Override
    public void handle(long now) {
        gc.drawImage(image, sx, sy, 80, 80, x - 20, y - 40, 80, 80);
        sx = (sx + 80) % 640;
        if (sx == 0) {
            sy = (sy + 80) % 480;
        }
        if (frameCounter > 48) {
            stop();
            return;
        }
        frameCounter++;
    }
}
