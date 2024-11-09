package com.nepenthx.papercut.packageManager;

import android.content.pm.PackageInfo;
import android.graphics.drawable.Drawable;

import com.nepenthx.papercut.executor.AsyncExecutor;

public class AppInfo {
    private String appName;
    private PackageInfo appPackageInfo;
    private Drawable icon;
    public AppInfo(PackageInfo packageInfo){
        appName = PaperCutPackageManager.getInstance().initAppName(packageInfo);
        appPackageInfo = packageInfo;
    }
    public PackageInfo getAppPackageInfo(){
        return this.appPackageInfo;
    }

    public String getAppName(){
        return this.appName;
    }

    public void setIcon(Drawable icon){
        this.icon = icon;
    }

    public Drawable getIcon(){

        return appPackageInfo.applicationInfo.loadIcon(PaperCutPackageManager.getInstance().getPm());
    }
}
