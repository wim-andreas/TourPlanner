package com.wimfra.tourplanner.mediator;

public class MediatorFactory {

    private static Mediator mediator;

    public static Mediator getMediator() {
        if (mediator == null) {
            mediator = new MediatorImpl();
        }
        return mediator;
    }

}
