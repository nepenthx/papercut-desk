package com.nepenthx.papercut;

import android.content.Context;
import android.content.pm.PackageManager;

import com.nepenthx.papercut.executor.AsyncExecutor;
import com.nepenthx.papercut.executor.IAsyncExecutor;
import com.nepenthx.papercut.packageManager.PaperCutPackageManager;

public class PaperCut {
    /**
     * 包名称管理器
     */
    private PaperCutPackageManager paperCutPackageManager;

    public void init(Context context){
        AsyncExecutor.init();
        PaperCutPackageManager.getInstance().init(context);

    }


}
