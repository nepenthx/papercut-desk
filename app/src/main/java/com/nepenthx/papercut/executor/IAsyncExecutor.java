package com.nepenthx.papercut.executor;

public interface IAsyncExecutor {
    void execute(Runnable command);

    void executeDelay(Runnable command , long delay);
}
