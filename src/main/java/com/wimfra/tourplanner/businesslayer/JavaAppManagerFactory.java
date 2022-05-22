package com.wimfra.tourplanner.businesslayer;

public final class JavaAppManagerFactory {

    private static JavaAppManager manager;

    public static JavaAppManager GetManager() {
        if (manager == null) {
            manager = new JavaAppManagerImpl();
        }
        return manager;

    }
}
