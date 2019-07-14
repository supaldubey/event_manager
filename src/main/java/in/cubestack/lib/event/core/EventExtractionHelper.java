package in.cubestack.lib.event.core;

import in.cubestack.lib.event.handler.EventHandler;
import org.springframework.stereotype.Component;

@Component
public class EventExtractionHelper {

    public Class<? extends Event> getEventsManagedBy(EventHandler eventHandler) {
        Class eventHandlerClass = eventHandler.getClass();
        if (!eventHandlerClass.isAnnotationPresent(EventConsumer.class)) {
            throw new RuntimeException("Annotation @EventConsumer required on class " + eventHandler.getClass());
        }

        EventConsumer eventConsumer = (EventConsumer) eventHandlerClass.getAnnotation(EventConsumer.class);
        return eventConsumer.value();
    }

}
