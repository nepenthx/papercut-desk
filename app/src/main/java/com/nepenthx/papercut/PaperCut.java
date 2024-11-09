package com.nepenthx.papercut;

import android.content.Context;
import android.content.pm.PackageManager;

import com.nepenthx.papercut.InputManager.PaperCutInputManager;
import com.nepenthx.papercut.executor.AsyncExecutor;
import com.nepenthx.papercut.packageManager.PaperCutPackageManager;

public class PaperCut {
    /**
     * 包名称管理器
     */
    private PaperCutPackageManager paperCutPackageManager;

    /**
     * 手柄控制器
     */
    private PaperCutInputManager paperCutInputManager;

    /**
     * 配置管理
     * @param context
     */
    private PaperCutConfig config;


    public void init(Context context){
        paperCutInputManager = new PaperCutInputManager(context);
        AsyncExecutor.init();
        PaperCutPackageManager.getInstance().init(context);
        config.init();
    }


}
