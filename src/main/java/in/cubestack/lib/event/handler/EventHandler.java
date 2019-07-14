package in.cubestack.lib.event.handler;

import in.cubestack.lib.event.EventRegistry;
import in.cubestack.lib.event.core.Event;

import javax.annotation.PostConstruct;

public abstract class EventHandler<T extends Event> {

    private final EventRegistry eventRegistry;

    protected EventHandler(EventRegistry eventRegistry) {
        this.eventRegistry = eventRegistry;
    }

    public abstract void onEvent(T event);

    @PostConstruct
    private void init() {
        eventRegistry.register(this);
    }
}
