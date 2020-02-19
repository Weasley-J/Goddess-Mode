package com.android.weasley;

import de.robv.android.xposed.XC_MethodReplacement;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

import static de.robv.android.xposed.XposedBridge.log;
import static de.robv.android.xposed.XposedHelpers.findAndHookMethod;

public class DirectHookWithoutParams {

    public static void hookMethodReturnTrue(String packageStr, String classStr, XC_LoadPackage.LoadPackageParam lpparam, String methodStr) {
        if (packageStr.equals(lpparam.packageName)) {
            findAndHookMethod(classStr, lpparam.classLoader, methodStr, XC_MethodReplacement.returnConstant(true));
            log("Succeeded in hooking \"" + lpparam.packageName + "\" ;");
        }
    }

    public static void hookMethodReturnFalse(String packageStr, String classStr, XC_LoadPackage.LoadPackageParam lpparam, String methodStr) {
        if (packageStr.equals(lpparam.packageName)) {
            findAndHookMethod(classStr, lpparam.classLoader, methodStr, XC_MethodReplacement.returnConstant(false));
            log("Succeeded in hooking \"" + lpparam.processName + "\" ;");
        }
    }
}
