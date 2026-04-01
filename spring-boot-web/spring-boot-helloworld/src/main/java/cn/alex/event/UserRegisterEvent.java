package cn.alex.event;

import org.springframework.context.ApplicationEvent;

/**
 * Created by WCY on 2026/3/30
 */
public class UserRegisterEvent extends ApplicationEvent {

    /**
     * Create a new {@code ApplicationEvent}.
     * @param source the object on which the event initially occurred or with
     *               which the event is associated (never {@code null})
     */
    public UserRegisterEvent(Object source) {
        super(source);
    }

}
