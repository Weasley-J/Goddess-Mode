package com.android.weasley.miui;

import android.content.Context;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XC_MethodReplacement;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

import static com.android.weasley.DirectHookWithoutParams.hookMethodReturnFalse;
import static de.robv.android.xposed.XposedBridge.log;
import static de.robv.android.xposed.XposedHelpers.findAndHookMethod;

public class MiuiHome {
    public static void hookHome(XC_LoadPackage.LoadPackageParam lpparam) {
        String pkgStr = "com.miui.home", classStr = "com.miui.home.launcher.DeviceConfig";
        try {
            if (lpparam.packageName.equals(pkgStr)) {
                log("Loaded application \"" + lpparam.packageName + "\" ;");
                hookMethodReturnFalse(pkgStr, classStr, lpparam, "isAppStoreEnabled");
                findAndHookMethod(classStr, lpparam.classLoader, "isCurrentThemeSupportSearchBar", Context.class, XC_MethodReplacement.returnConstant(false));
                findAndHookMethod(classStr, lpparam.classLoader, "isRecommendServerEnable", new XC_MethodHook() {
                    @Override
                    protected void beforeHookedMethod(final MethodHookParam param) throws Throwable {
                        super.afterHookedMethod(param);
                        param.setResult(false);
                        log("Result of isRecommendServerEnable() " + param.getResult() + ";");
                    }
                });
                log("Succeeded in hooking \"" + lpparam.packageName + ": isRecommendServerEnable()\";");
            }
        } catch (Throwable throwable) {
            throwable.getLocalizedMessage();
        }
    }

}
