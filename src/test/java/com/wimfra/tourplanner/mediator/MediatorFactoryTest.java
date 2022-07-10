package com.wimfra.tourplanner.mediator;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MediatorFactoryTest {

    @Test
    public void testMediatorSingletonPattern(){
        assertSame(MediatorFactory.getMediator(), MediatorFactory.getMediator());
    }
}