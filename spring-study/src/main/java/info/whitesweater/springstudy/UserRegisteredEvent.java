package info.whitesweater.springstudy;

import org.springframework.context.ApplicationEvent;


public class UserRegisteredEvent extends ApplicationEvent {
    public UserRegisteredEvent(Object source) {
        super(source);
    }
}
