package com.android.weasley.miui;

import android.util.Log;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XC_MethodReplacement;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

import static com.android.weasley.DirectHookWithoutParams.*;
import static de.robv.android.xposed.XposedBridge.log;
import static de.robv.android.xposed.XposedHelpers.findAndHookMethod;

public class MiDownload {
    public static void hookDownload(XC_LoadPackage.LoadPackageParam lpparam) {
        String pkgStr = "com.android.providers.downloads.ui",
                pkgStr2 = "com.android.providers.downloads",
                classStr1 = "com.android.providers.downloads.ui.auth.AccountInfo",
                classStr2 = "com.android.providers.downloads.ui.config.AppConfig",
                classStr3 = "com.android.providers.downloads.ui.config.RecommendConfig",
                classStr4 = "com.android.providers.downloads.ui.utils.PreferenceUtil";
        try {
            if (lpparam.packageName.equals(pkgStr)) {
                log("Loaded app: " + lpparam.packageName + ".");
                hookMethodReturnFalse(pkgStr, classStr1, lpparam, "isFake");
                hookMethodReturnTrue(pkgStr, classStr1, lpparam, "isVip");

                hookMethodReturnFalse(pkgStr, classStr2, lpparam, "isShowRank");

                hookMethodReturnFalse(pkgStr, classStr3, lpparam, "isShowInfoFlow");
                hookMethodReturnFalse(pkgStr, classStr3, lpparam, "needShowAppRecommend");
                hookMethodReturnFalse(pkgStr, classStr3, lpparam, "needShowAppSubject");
                hookMethodReturnFalse(pkgStr, classStr3, lpparam, "needShowExtraAppRecommend");
                hookMethodReturnFalse(pkgStr, classStr3, lpparam, "needShowInfoFlow");
                hookMethodReturnFalse(pkgStr, classStr3, lpparam, "needShowVideoEntrance");

                hookMethodReturnFalse(pkgStr, classStr4, lpparam, "containsInfoFlow");
            }
            if (lpparam.packageName.equals(pkgStr2)) {
                hookMethodReturnTrue(pkgStr2, "com.android.providers.downloads.api.account.AccountInfo", lpparam, "isAuto");
                hookMethodReturnFalse(pkgStr2, "com.android.providers.downloads.api.account.AccountInfo", lpparam, "isFake");
                hookMethodReturnTrue(pkgStr2, "com.android.providers.downloads.api.account.AccountInfo", lpparam, "isVip");
                hookMethodReturnTrue(pkgStr2, "com.android.providers.downloads.config.DownloadSettings", lpparam, "isVip");
                hookMethodReturnTrue(pkgStr2, "com.android.providers.downloads.config.DownloadSettings", lpparam, "isVipEnable");
                hookMethodReturnFalse(pkgStr2, "com.android.providers.downloads.config.DownloadSettings", lpparam, "isShowRecommendPush");

                hookMethodReturnTrue(pkgStr2, "com.android.providers.downloads.remote.service.DownloadSettingsProviderService", lpparam, "isVip");
                hookMethodReturnTrue(pkgStr2, "com.android.providers.downloads.remote.service.DownloadSettingsProviderService", lpparam, "isVipEnable");
                hookMethodReturnTrue(pkgStr2, "com.android.providers.downloads.remote.service.DownloadSettingsProviderService", lpparam, "changSpeedUpEnable");

                hookMethodReturnFalse(pkgStr2, "com.android.providers.downloads.util.LimitSpeedUtil", lpparam, "isSupportLimitSpeed");
                hookMethodReturnFalse(pkgStr2, "Lcom.android.providers.downloads.util.rBuildUtils", lpparam, "isAlphaVersion");

                hookMethodReturnTrue(pkgStr2, "com.android.providers.downloads.xunlei.speedup.XLSpeedUpManager", lpparam, "isVip");
                findAndHookMethod("com.android.providers.downloads.xunlei.speedup.XLSpeedUpManager", lpparam.classLoader, "isCanSpeedUp", String.class, new XC_MethodHook() {
                    @Override
                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                        super.afterHookedMethod(param);
                        param.args[0] = "is Can Speed Up";
                        Object speedString = param.args[0];
                        Log.d(String.valueOf(true), "afterHookedMethod: " + speedString);
                        param.setResult(true);
                        log(speedString.toString() + param.getResult());
                    }
                });
                findAndHookMethod("com.android.providers.downloads.xunlei.speedup.XLSpeedUpManager", lpparam.classLoader, "isValidMode", Integer.class, XC_MethodReplacement.returnConstant(new Integer(1)));
                hookMethodReturnTrue(pkgStr2, "com.android.providers.downloads.xunlei.speedup.XLSpeedUpManager", lpparam, "getSpeedUpMode");
                hookMethodReturnTrue(pkgStr2, "com.android.providers.downloads.xunlei.speedup.XLSpeedUpManager", lpparam, "isInVipMode");
                hookMethodReturnTrue(pkgStr2, "com.android.providers.downloads.xunlei.speedup.XLSpeedUpManager", lpparam, "isValidMode");
                findAndHookMethod("com.android.providers.downloads.xunlei.speedup.XLSpeedUpManager", lpparam.classLoader, "isValidMode", Integer.class, XC_MethodReplacement.returnConstant(new Integer(1)));
                hookMethodReturnTrue(pkgStr2, "com.android.providers.downloads.xunlei.XLDownloadThread", lpparam, "canAcceleration");
                hookMethodReturnTrue(pkgStr2, "com.android.providers.downloads.xunlei.XLDownloadThread", lpparam, "getSpeedUpQueryMode");
                hookMethodReturnTrue(pkgStr2, "com.android.providers.downloads.xunlei.XLSpeedUpRules", lpparam, "getSpeedUpMode");
            }
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }
}
