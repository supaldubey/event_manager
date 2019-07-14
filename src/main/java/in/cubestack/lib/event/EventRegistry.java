package in.cubestack.lib.event;

import in.cubestack.lib.event.core.Event;
import in.cubestack.lib.event.core.EventExtractionHelper;
import in.cubestack.lib.event.handler.EventHandler;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Component
public class EventRegistry {

    private final EventExtractionHelper eventExtractionHelper;

    public EventRegistry(EventExtractionHelper eventExtractionHelper) {
        this.eventExtractionHelper = eventExtractionHelper;
    }

    private Map<Class, List<EventHandler>> eventHandlerMap = new HashMap<>();

    public void register(EventHandler eventHandler) {
        Class<? extends Event> managedEventClass = eventExtractionHelper.getEventsManagedBy(eventHandler);
        eventHandlerMap.putIfAbsent(managedEventClass, new LinkedList<>());
        eventHandlerMap.get(managedEventClass).add(eventHandler);

    }

    public List<EventHandler> getHandlersFor(Class<? extends Event> eventClass) {
        return eventHandlerMap.getOrDefault(eventClass, List.of());
    }
}
