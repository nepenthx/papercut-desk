package com.nepenthx.papercut.packageManager;

public class PackageManagerConfig {
    private boolean isDebug;

    public PackageManagerConfig setDebug(boolean debug) {
        this.isDebug = debug;
        return this;
    }


    public void init() {
        this.isDebug = true ;
    }

    public boolean isDebug() {
        return this.isDebug;
    }
}
