package com.nepenthx.papercut.packageManager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;

import com.nepenthx.papercut.PaperCutConfig;
import com.nepenthx.papercut.executor.AsyncExecutor;

import java.util.ArrayList;
import java.util.List;


public class PaperCutPackageManager {
    /**
     * 读取已安装应用列表
     */
    private List<PackageInfo> appList = new ArrayList<>();
    /**
     * 获取当前Activity的Context
     */
    private  Context contextProxy ;


    private PackageManager pm;

    public String TAG = "PaperCutPackageManager";

    private List<AppInfo> appInfoList = new ArrayList<>();
    private static final class PaperCutHolder {
        @SuppressLint("StaticFieldLeak")
        static final PaperCutPackageManager instance = new PaperCutPackageManager();
    }

    public static PaperCutPackageManager getInstance() {
        return PaperCutHolder.instance;
    }

    private PaperCutPackageManager() {
    }

    public PackageManager getPm(){
        return this.pm;}




    public void init(Context context) {

        this.contextProxy=context;
        pm = context.getPackageManager();
        this.initAllApp();
    }

    /**
     * 初始化设备列表
     */
    @SuppressLint("QueryPermissionsNeeded")
    public void initAllApp() {
        appList = pm.getInstalledPackages(0);

            for (PackageInfo packageInfo : appList) {
                if((packageInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 0){
                    appInfoList.add(new AppInfo(packageInfo));
                }
        }
        AsyncExecutor.execute(new Runnable() {
            @Override
            public void run() {
                for(AppInfo appInfo : appInfoList){
                    appInfo.setIcon(appInfo.getAppPackageInfo().applicationInfo.loadIcon((pm)));
                }
            }
        });
        if (PaperCutConfig.instance.isDebug()) {
            StringBuilder stringBuilder = new StringBuilder();
            for (PackageInfo packageInfo : appList) {
                stringBuilder.append(packageInfo.packageName).append(", ");
            }
            Log.d(TAG, stringBuilder.toString());
        }
    }

    public String initAppName(PackageInfo packageInfo){
            return pm.getApplicationLabel(packageInfo.applicationInfo).toString();
    }

    public List<AppInfo> getALlApp(){
        return appInfoList;
    }






    public void deleteApplication() {

    }
    public List<PackageInfo> getAppList() {
        return this.appList;
    }

    /**
     * app跳转
     */
    public void startActivity(PackageInfo packageInfo) {
        try {
            if (pm != null) {
                Intent intent = pm.getLaunchIntentForPackage(packageInfo.packageName);
                if (PaperCutConfig.instance.isDebug()) {
                    Log.d(TAG, "Intent to package: " + packageInfo.packageName);
                }
                if (intent != null) {
                    contextProxy.startActivity(intent);
                } else {
                    Log.e(TAG, "Launch intent is null for package: " + packageInfo.packageName);
                }
            } else {
                Log.e(TAG, "PackageManager is null");
            }
        } catch (Exception e) {
            Log.e(TAG, "Intent failed to: " + packageInfo.packageName + ", reason is " + e);
        }
    }


}
