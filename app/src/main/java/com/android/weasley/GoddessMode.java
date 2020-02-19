package com.android.weasley;

import com.android.weasley.miui.MiDownload;
import com.android.weasley.miui.MiHealth;
import com.android.weasley.miui.MiMusic;
import com.android.weasley.miui.MiuiHome;
import com.android.weasley.miui.ThemeManager;

import java.text.SimpleDateFormat;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.IXposedHookZygoteInit;
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam;

import static de.robv.android.xposed.XposedBridge.log;

public class GoddessMode extends GoddessModeMethod implements IXposedHookLoadPackage, IXposedHookZygoteInit {

    public static final SimpleDateFormat OUTPUT_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    public void handleLoadPackage(LoadPackageParam lpparam) throws Throwable {
        //log("packageName: [" + lpparam.packageName + "]");
        ThemeManager.hookTheme(lpparam);
        MiuiHome.hookHome(lpparam);
        MiMusic.hookMusic(lpparam);
        MiDownload.hookDownload(lpparam);
        MiHealth.hookMiFit(lpparam);
        Snapmod.hookMethod(lpparam);
        SDMaid.hookMethod(lpparam);
    }

    @Override
    public void initZygote(StartupParam startupParam) {
        log("女娲模式：开始Patch...");
        /*
        new XC_MethodHook() {
            protected void afterHookedMethod(XC_MethodHook.MethodHookParam param) {
                GoddessMode.super.afterHookedMethod(param);
                XposedBridge.log("param: " + param);
            }
        };
        */
    }

}
