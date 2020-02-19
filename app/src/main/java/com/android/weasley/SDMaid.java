package com.android.weasley;

import de.robv.android.xposed.XC_MethodReplacement;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

import static de.robv.android.xposed.XposedBridge.log;
import static de.robv.android.xposed.XposedHelpers.findAndHookMethod;

public class SDMaid {
    public static void hookMethod(XC_LoadPackage.LoadPackageParam lpparam) {
        String packageStr = "eu.thedarken.sdm",
                classStr1 = "c.a.a.a.a.a.a.b", methodStr1 = "a",
                classStr2 = "c.a.a.a.a.a.r.a", methodStr2 = "a";
        if (packageStr.equals(lpparam.packageName)) {
            log("Loaded application \"" + lpparam.packageName + "\" ;");
            findAndHookMethod(classStr1, lpparam.classLoader, methodStr1, XC_MethodReplacement.returnConstant(true));
            findAndHookMethod(classStr2, lpparam.classLoader, methodStr2, XC_MethodReplacement.returnConstant(true));
            log("Succeeded in hooking \"" + lpparam.processName + "\" ;");
        }
    }

}
