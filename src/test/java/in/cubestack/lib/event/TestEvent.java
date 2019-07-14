package in.cubestack.lib.event;

import in.cubestack.lib.event.core.Event;

public class TestEvent implements Event {
    private final String id;
    private final String name;

    public TestEvent(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "TestEvent{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
