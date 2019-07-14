# event_manager
A simple event manager for Spring Apps

# Why
The idea is to create a simple Event manager for apps that use Spring. The framework provides 
decoupled biding between event generator and event listener.

For a lot of production apps, there are scenarios where you might want to perform certain action of same triggers.
For example: 

 - Once a user is created, initially app had to persist details into db and send email
 - What if it now after new app, app needs to send push notification
 - What if it needs to send mails to his Friends / connected graph
 
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
