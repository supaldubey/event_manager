package in.cubestack.lib;

import in.cubestack.lib.event.EventRegistry;
import in.cubestack.lib.event.TestEvent;
import in.cubestack.lib.event.manager.EventManager;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class)
public class EventHandlerTest {

    @Configuration()
    @ComponentScan("in.cubestack")
    public static class AppConfig {
    }

    @Autowired
    private EventRegistry eventRegistry;

    @Autowired
    private EventManager eventManager;

    @Autowired
    private ResultListener resultListener;

    @Test
    public void testDispatch() {
        eventManager.publish(new TestEvent("1", "Test"));
        Assert.assertEquals(resultListener.result, "Test");
    }

    @Test
    public void testSetup() {
        Assert.assertNotNull(eventRegistry);
        Assert.assertNotNull(eventManager);
    }



}
