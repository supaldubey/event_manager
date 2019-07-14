# event_manager
A simple event manager for Spring Apps

# What
Simple event manager, which would work in context of a single DB transaction / single thread call.

# What it is not
It is not meant for distributed / multiple JVMs as of now. This is just a smart way to have multiple handlers for a single event and decoupling services on a single JVM.
It could be easily extended for distributed systems by including a Queue and changing implementations for EventRegistry and EventManager to read / write from / to the Queue.  

# Why
The idea is to create a simple Event manager for apps that use Spring. The framework provides 
decoupled biding between event generator and event listener.

For a lot of production apps, there are scenarios where you might want to perform certain action of same triggers.
For example: 

 - Once a user is created, persist information in DB (Current state)
 - Maybe now system needs to create an audit entry in db 
 - And probably as next requirement, app needs to update referer (that connection has joined)
 - And in future needs to perform another DB operation in the same transaction?
 
Refactors for such cases are usually painful, and hence event based models work a lot better.

With above context / idea below is a toy implementation for such a simple event management system.  

# Why Spring
Because its one of the most used frameworks / libraries in Java ecosystem. Also due to dependency Injection,  you could easily have self registering event listeners.
Which removes dependency of remembering to register new event listener every time you create one.  

## Usage
Create an implementation for Event class, which specifies an event to be managed.
#
For example
```java
public class UserCreatedEvent implements Event {
    private final long userId;
    // getter / setter
}
```
#
Create separate handler for each Event, or multiple handlers would also work
```java
@EventConsumer(UserCreatedEvent.class)
public class UserCreatedEventHandler extends EventHandler<UserCreatedEvent> {
    
    @Override
        public void onEvent(UserCreatedEvent event) {
            // Do stuff here
        }
}
```

Later notification  event could be handled simply by creating another handler 
```java
@EventConsumer(UserCreatedEvent.class)
public class UserCreatedEventNotificationHandler extends EventHandler<UserCreatedEvent> {
    
    @Override
        public void onEvent(UserCreatedEvent event) {
            // Send notification here
        }
}
```
