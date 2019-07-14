package in.cubestack.lib.handler;

import in.cubestack.lib.ResultListener;
import in.cubestack.lib.event.TestEvent;
import in.cubestack.lib.event.core.EventConsumer;
import in.cubestack.lib.event.handler.EventHandler;

@EventConsumer(TestEvent.class)
public class TestEventHandler extends EventHandler<TestEvent> {

    private final ResultListener resultListener;

    public TestEventHandler(ResultListener resultListener) {
        this.resultListener = resultListener;
    }

    @Override
    public void onEvent(TestEvent event) {
        resultListener.result = event.getName();
    }
}
