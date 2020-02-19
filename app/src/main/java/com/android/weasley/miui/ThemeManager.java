package com.android.weasley.miui;

import android.content.Context;

import java.io.File;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XC_MethodReplacement;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

import static com.android.weasley.DirectHookWithoutParams.hookMethodReturnFalse;
import static com.android.weasley.DirectHookWithoutParams.hookMethodReturnTrue;
import static de.robv.android.xposed.XposedBridge.hookAllMethods;
import static de.robv.android.xposed.XposedBridge.log;
import static de.robv.android.xposed.XposedHelpers.findAndHookMethod;
import static de.robv.android.xposed.XposedHelpers.findClass;

public class ThemeManager {
    public static void hookTheme(XC_LoadPackage.LoadPackageParam lpparam) {

        try {
            if (lpparam.packageName.equals("miui.drm")) {
                log(lpparam.packageName + "has loaded;");
                findAndHookMethod("miui.drm.DrmManager", lpparam.classLoader, "isLegal", Context.class, File.class, File.class, XC_MethodReplacement.returnConstant(DRM.getDrmResult()));
                findAndHookMethod("miui.drm.DrmManager", lpparam.classLoader, "isLegal", Context.class, String.class, File.class, XC_MethodReplacement.returnConstant(DRM.getDrmResult()));
                findAndHookMethod("miui.drm.DrmManager", lpparam.classLoader, "isLegal", (XC_MethodHook) XC_MethodReplacement.returnConstant(DRM.getDrmResult()));
                findAndHookMethod("miui.drm.DrmManager", lpparam.classLoader, "getMorePreciseDrmResult", (XC_MethodHook) XC_MethodReplacement.returnConstant(DRM.getDrmResult()));
                findAndHookMethod("miui.drm.DrmManager", lpparam.classLoader, "isPermanentRights", File.class, XC_MethodReplacement.returnConstant(true));
                findAndHookMethod("miui.drm.DrmManager", lpparam.classLoader, "isRightsFileLegal", File.class, XC_MethodReplacement.returnConstant(true));
                findAndHookMethod("miui.drm.DrmManager", lpparam.classLoader, "isSupportAd", Context.class, XC_MethodReplacement.returnConstant(false));
                findAndHookMethod("miui.drm.DrmManager", lpparam.classLoader, "isSupportAd", File.class, XC_MethodReplacement.returnConstant(false));
                log("Hook " + lpparam.packageName + " success !");
                /*
                 miui.drm.ThemeReceiver
                 Context.class, String.class, String.class
                 validateTheme
                */
                Class<?> themeReceiverClazz = findClass("miui.drm.ThemeReceiver", lpparam.classLoader);
                if (themeReceiverClazz.getName().equals("miui.drm.ThemeReceiver")) {
                    hookAllMethods(themeReceiverClazz, "validateTheme", new XC_MethodHook() {
                        @Override
                        protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                            super.beforeHookedMethod(param);
                            param.setResult(true);
                            log("Hooked: " + param.args + " | " + param.getResult());
                        }
                    });
                }
            }
            if (lpparam.packageName.equals("com.miui.system")) {
                log(lpparam.packageName + "has loaded;");
                findAndHookMethod("miui.drm.DrmManager", lpparam.classLoader, "isLegal", Context.class, File.class, File.class, XC_MethodReplacement.returnConstant(DRM.getDrmResult()));
                findAndHookMethod("miui.drm.DrmManager", lpparam.classLoader, "isLegal", Context.class, String.class, File.class, XC_MethodReplacement.returnConstant(DRM.getDrmResult()));
                findAndHookMethod("miui.drm.DrmManager", lpparam.classLoader, "isLegal", (XC_MethodHook) XC_MethodReplacement.returnConstant(DRM.getDrmResult()));
                findAndHookMethod("miui.drm.DrmManager", lpparam.classLoader, "getMorePreciseDrmResult", (XC_MethodHook) XC_MethodReplacement.returnConstant(DRM.getDrmResult()));
                findAndHookMethod("miui.drm.DrmManager", lpparam.classLoader, "isPermanentRights", File.class, XC_MethodReplacement.returnConstant(true));
                findAndHookMethod("miui.drm.DrmManager", lpparam.classLoader, "isRightsFileLegal", File.class, XC_MethodReplacement.returnConstant(true));
                findAndHookMethod("miui.drm.DrmManager", lpparam.classLoader, "isSupportAd", Context.class, XC_MethodReplacement.returnConstant(false));
                findAndHookMethod("miui.drm.DrmManager", lpparam.classLoader, "isSupportAd", File.class, XC_MethodReplacement.returnConstant(false));
                log("Hook " + lpparam.packageName + " success !");
                /*
                 miui.drm.ThemeReceiver
                 Context.class, String.class, String.class
                 validateTheme
                */
                Class<?> themeReceiverClazz = findClass("miui.drm.ThemeReceiver", lpparam.classLoader);
                if (themeReceiverClazz.getName().equals("miui.drm.ThemeReceiver")) {
                    hookAllMethods(themeReceiverClazz, "validateTheme", new XC_MethodHook() {
                        @Override
                        protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                            super.beforeHookedMethod(param);
                            param.setResult(true);
                            log("Hooked: " + param.args + " | " + param.getResult());
                        }
                    });
                }
            }
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        String pkg = "com.android.thememanager", cls1 = "com.android.thememanager.util.ThemeOperationHandler", cls3 = "com.android.thememanager.basemodule.resource.model.ResourceOnlineProperties",
                cls4 = "com.android.thememanager.basemodule.resource.model.Resource", cls5 = "com.android.thememanager.module.detail.presenter.OnlineThemeDetailPresenter";
        if (lpparam.packageName.equals(pkg)) {
            try { //hookReturnTrueMethodWithoutParam(String packageStr, String classStr, LoadPackageParam lpparam, String methodStr)
                hookMethodReturnTrue(pkg, cls1, lpparam, "a");
                hookMethodReturnTrue(pkg, cls1, lpparam, "e");
                hookMethodReturnTrue(pkg, cls1, lpparam, "f");
                hookMethodReturnTrue(pkg, cls1, lpparam, "t");
                hookMethodReturnTrue(pkg, cls1, lpparam, "y");
                hookMethodReturnTrue(pkg, "com.android.thememanager.util.bj", lpparam, "D");
                hookMethodReturnTrue(pkg, "com.android.thememanager.util.ba", lpparam, "D");
                hookMethodReturnTrue(pkg, cls3, lpparam, "isProductBought");
                hookMethodReturnTrue(pkg, cls4, lpparam, "isProductBought");
                hookMethodReturnTrue(pkg, cls4, lpparam, "isAuthorizedResource");
                hookMethodReturnFalse(pkg, cls4, lpparam, "isSupportHomeSearchBar");
                hookMethodReturnTrue(pkg, cls5, lpparam, "h");
                hookMethodReturnTrue(pkg, cls5, lpparam, "i");
                findAndHookMethod(cls5, lpparam.classLoader, "h", Boolean.TYPE, new XC_MethodHook() {
                    @Override
                    protected void beforeHookedMethod(final MethodHookParam param) throws Throwable {
                        super.beforeHookedMethod(param);
                        param.setResult(true);
                        log("Result of h() " + param.getResult() + ";");
                    }
                });
                findAndHookMethod(cls5, lpparam.classLoader, "i", Boolean.TYPE, new XC_MethodHook() {
                    @Override
                    protected void beforeHookedMethod(final MethodHookParam param) throws Throwable {
                        super.beforeHookedMethod(param);
                        param.setResult(true);
                        log("Result of i() " + param.getResult() + ";");
                    }
                });
                /*
                Class<?> oltpClass = findClass(cls5, lpparam.classLoader);
                hookAllMethods(oltpClass, "h", new XC_MethodHook() {
                    @Override
                    protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                        super.beforeHookedMethod(param);
                        param.setResult(true);
                        log("OnlineThemeDetailPresenter: " + param.method);
                    }
                });
                hookAllMethods(oltpClass, "i", new XC_MethodHook() {
                    @Override
                    protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                        super.beforeHookedMethod(param);
                        param.setResult(true);
                        log("OnlineThemeDetailPresenter: " + param.method);
                    }
                });
                */

                log("Succeeded in hooking \"" + lpparam.processName + "\" ;");
                // remove AD method
                findAndHookMethod("com.android.thememanager.basemodule.ad.model.AdInfoResponse", lpparam.classLoader, "isAdValid", new XC_MethodHook() {
                    @Override
                    protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                        super.beforeHookedMethod(param);
                        param.setResult(false);
                        log("Hooked method: " + param.method.getName() + ";");
                    }
                });
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        }
    }

}
