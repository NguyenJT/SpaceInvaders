package spaceinv.view;

import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import spaceinv.model.Gun;
import spaceinv.model.Projectile;
import spaceinv.model.ships.BattleCruiser;
import spaceinv.model.ships.Bomber;
import spaceinv.model.ships.Frigate;
import spaceinv.model.Ground;

import java.util.HashMap;
import java.util.Map;


/*
   Defining all assets used by application
   Assets stored in directory assets.

   *** Nothing to do here ***

*/

public enum Assets {
    INSTANCE;           // Only one object

    private final String IMAGE_DIR = "file:assets/img/";
    private final String SOUND_DIR = "file:assets/sound/";

    // ------------ General look  ------------------------

    public final Color colorFgText = Color.WHITE;
    public final Font font = new Font("Arial", 14);

    // -------------- Audio handling -----------------------------

    // NOTE, some problems with playing different file formats, wav bad .. too big files
    public final AudioClip rocketHitShip = getSound("explosion2.mp3");

    // -------------- Image handling  ----------------------------

    // Keep track of which image belong to which object
    private final Map<Object, Image> objectImage = new HashMap<>();

    public final Image start_screen = getImage("start_screen.png");
    public final Image background = getImage("background.png");
    public final Image explosion = getImage("explosion.png");
    public final Image ground = getImage("ground.png");

    // Bind class object to images
    // All instance objects must be bound elsewhere, not done in this application
    {
        bind(BattleCruiser.class, getImage("battlecruiser.png"));
        bind(Bomber.class, getImage("bomber.png"));
        bind(Frigate.class, getImage("frigate.png"));
        bind(Projectile.class, getImage("projectile.png"));
        bind(Gun.class, getImage("gun2.png"));
        bind(Ground.class, getImage("ground.png"));
    }

    // Not used here
    public <T> void bind(T object, Image image) {
        objectImage.put(object, image);
    }

    // This will bind a class to an image to be used in GUI
    public void bind(Class<?> clazz, Image image) {
        objectImage.put(clazz, image);
    }

    // Get a previously bound image for some class
    public Image get(Class<?> clazz) {
        return objectImage.get(clazz);
    }

    // ---------- Helpers for file handling ------------------------

    private Image getImage(String fileName) {
        return new Image(IMAGE_DIR + fileName);
    }

    public AudioClip getSound(String fileName) {
        return new AudioClip(SOUND_DIR + fileName);
    }
}
