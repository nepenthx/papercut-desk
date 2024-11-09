package com.nepenthx.papercut.executor;

import android.os.HandlerThread;

import android.os.Handler;
public class AsyncExecutor {
    private static final String THREAD_GROUP_NAME = "PAPERCUT";
    private static IAsyncExecutor executor;
    public static void init(){
        HandlerThread thread = new HandlerThread(THREAD_GROUP_NAME);
        thread.start();

        final Handler paperCutHandler = new Handler(thread.getLooper());

        AsyncExecutor.executor = new IAsyncExecutor() {
            @Override
            public void execute(Runnable command) {
                paperCutHandler.post(command);
            }


            @Override
            public void executeDelay(Runnable command , long delay) {
                paperCutHandler.postDelayed(command,delay);
            }
        };

    }

    public  static void execute(Runnable command){
        executor.execute(command);
    }

    public static void executeDelay(Runnable command,long delay){
        executor.executeDelay(command,delay);
    }

}
