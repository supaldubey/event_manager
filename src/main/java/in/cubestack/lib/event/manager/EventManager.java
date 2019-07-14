package in.cubestack.lib.event.manager;

import in.cubestack.lib.event.EventRegistry;
import in.cubestack.lib.event.core.Event;
import in.cubestack.lib.event.handler.EventHandler;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventManager {

    private final EventRegistry eventRegistry;

    public EventManager(EventRegistry eventRegistry) {
        this.eventRegistry = eventRegistry;
    }

    public void publish(Event event) {
        List<EventHandler> eventHandlers = eventRegistry.getHandlersFor(event.getClass());
        eventHandlers.forEach(eventHandler -> eventHandler.onEvent(event));
    }
}
