package spaceinv.event;

/*
       Events passed from model to GUI

       *** Nothing to do here (except possibly add Types) ***
 */
public class ModelEvent {

    // Enumeration of possible events
    public enum Type {
        HAS_LOST,
        HAS_WON,           // Add types of events as you like
        GUN_HIT_SHIP,
        GUN_SHOOT,
        BOMB_HIT_GROUND,
        BOMB_HIT_GUN,
        BOMB_DROPPED;

    }

    public final Type type;
    public final Object data;  // Possibly send some data to GUI

    public ModelEvent(ModelEvent.Type type, Object data) {
        this.type = type;
        this.data = data;
    }

    public ModelEvent(ModelEvent.Type type) {
        this(type, null);
    }

    @Override
    public String toString() {
        return type.toString();
    }
}