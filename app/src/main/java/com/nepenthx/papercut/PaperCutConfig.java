package com.nepenthx.papercut;

public enum PaperCutConfig {
    instance;
    private boolean isDebug;
    {
        this.isDebug = true ;
    }
    public PaperCutConfig setDebug(boolean debug) {
        this.isDebug = debug;
        return this;
    }

    public boolean isDebug() {
        return this.isDebug;
    }
}
