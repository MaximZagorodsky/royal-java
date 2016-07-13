package com.proxsoftware.webapp.other;

/**
 * Created by Proxima on 13.07.2016.
 */
public class Singleton {
    private static Singleton INSTANCE;

    public Singleton getInstance() {
       return INSTANCE != null ? INSTANCE : new Singleton();
    }
}
