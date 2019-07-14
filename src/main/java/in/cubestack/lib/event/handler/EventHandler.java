package in.cubestack.lib.event.handler;

import in.cubestack.lib.event.EventRegistry;
import in.cubestack.lib.event.core.Event;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

public abstract class EventHandler<T extends Event> {

    @Autowired
    private EventRegistry eventRegistry;

    public abstract void onEvent(T event);

    @PostConstruct
    private void init() {
        eventRegistry.register(this);
    }
}
