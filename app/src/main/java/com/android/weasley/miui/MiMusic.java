package com.android.weasley.miui;

import com.android.weasley.GoddessMode;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XC_MethodReplacement;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

import static com.android.weasley.DirectHookWithoutParams.hookMethodReturnTrue;
import static de.robv.android.xposed.XC_MethodReplacement.returnConstant;
import static de.robv.android.xposed.XposedBridge.log;
import static de.robv.android.xposed.XposedHelpers.findAndHookMethod;

public class MiMusic {
    public static void hookMusic(XC_LoadPackage.LoadPackageParam lpparam) {
        String pkgStr = "com.miui.player", pksSubStr = "com.android.providers.downloads.config", classStr = "com.android.providers.downloads.config.DownloadSettings";
        if (lpparam.packageName.equals(pkgStr)) {
            try {
                if (lpparam.packageName.equals(pksSubStr)) {
                    hookMethodReturnTrue(pksSubStr, classStr, lpparam, "isVip");
                    hookMethodReturnTrue(pksSubStr, classStr, lpparam, "isVipEnable");
                }
                hookMethodReturnTrue(pkgStr, "com.miui.player.vip.AccountPermissionHelper", lpparam, "hasBought");
                findAndHookMethod("com.miui.player.vip.AccountPermissionHelper", lpparam.classLoader, "getVipEndDate", String.class, new XC_MethodHook() {
                    @Override
                    protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                        super.afterHookedMethod(param);
                        param.args[0] = GoddessMode.OUTPUT_DATE_FORMAT.format("2099-12-31");
                        returnConstant(param.args[0]);
                        log("Vip End Date is: " + param.getResult());
                    }
                });
                findAndHookMethod("com.miui.player.vip.AccountPermissionHelper", lpparam.classLoader, "isSongPaid", java.lang.String.class, XC_MethodReplacement.returnConstant(true));
                hookMethodReturnTrue(pkgStr, "com.miui.player.vip.AccountPermissionHelper", lpparam, "isVip");
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        }
    }

}
