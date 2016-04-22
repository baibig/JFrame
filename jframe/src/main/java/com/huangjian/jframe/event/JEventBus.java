package com.huangjian.jframe.event;

import org.greenrobot.eventbus.EventBus;

/**
 * Description: 包装的EventBus
 * Author: pierce
 * Date: 2016/4/22
 */
public class JEventBus implements IEventBus {

    private static EventBus bus;
    static {
        bus = createEventBus();
    }

    private static EventBus createEventBus() {
        return EventBus.builder().build();
    }

    @Override
    public void registerEvent(Object obj) {
        bus.register(obj);
    }

    @Override
    public void unRegisterEvent(Object obj) {
        bus.unregister(obj);
    }

    @Override
    public void postEvent(Object event) {
        bus.post(event);
    }

    @Override
    public void cancelEventDelivery(Object event) {
        bus.cancelEventDelivery(event);
    }

    @Override
    public void postStickyEvent(Object event) {
        bus.postSticky(event);
    }

    @Override
    public void removeStickyEvent(Object event) {
        bus.removeStickyEvent(event);
    }

    @Override
    public <T> T removeStickyEvent(Class<T> eventType) {
        return bus.removeStickyEvent(eventType);
    }

    @Override
    public void removeAllStickyEvent() {
        bus.removeAllStickyEvents();
    }
}

