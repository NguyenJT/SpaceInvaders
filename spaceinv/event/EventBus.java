package spaceinv.event;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
     Service to send events **from model** to GUI
     GUI must know if there has been a collision in the model etc.

     NOTE: Events **from GUI** to model handled by JavaFX events and keyboard listeners etc.

     *** Nothing to do here ****

     Usage: To send an event from model to the view add the below in model

     EventBus.INSTANCE.publish(new ModelEvent(ModelEvent.Type.MY_EVENT, myData));

     Handle event in view like:

     @Override
     public void onModelEvent(ModelEvent evt) {
        switch (evt.type) {
            case MY_EVENT:  ...update view ...
            case ...
        }
     }


 */
public enum EventBus {

    INSTANCE;

    private final List<EventHandler> handlers
            = Collections.synchronizedList(new ArrayList<>());
    private boolean trace = true;

    public void register(EventHandler handler) {
        handlers.add(handler);
    }

    public void unRegister(EventHandler handler) {
        handlers.remove(handler);
    }

    public void publish(ModelEvent evt) {
        // Tracking all events
        if (trace) {
            System.out.println(evt);
        }
        synchronized (handlers) {
            handlers.stream().forEach((evh) -> {
                evh.onModelEvent(evt);
            });
        }
    }

    public void publish(ModelEvent.Type tag) {
        publish(new ModelEvent(tag, null));
    }

}
