package com.android.weasley.miui;

import android.content.Context;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XC_MethodReplacement;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

import static com.android.weasley.DirectHookWithoutParams.hookMethodReturnTrue;
import static de.robv.android.xposed.XposedBridge.hookAllMethods;
import static de.robv.android.xposed.XposedBridge.log;
import static de.robv.android.xposed.XposedHelpers.findAndHookMethod;

public class MiHealth {
    public static void hookMiFit(XC_LoadPackage.LoadPackageParam lpparam) {
        final String PACKAGE_NAME = "com.xiaomi.hm.health";
        //过滤包
        if (!PACKAGE_NAME.equals(lpparam.packageName)) return;
        findAndHookMethod(ClassLoader.class, "O00o00", String.class, new XC_MethodHook() {
            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                if (param.hasThrowable()) return;
                String classname = (String) param.args[0]; //步骤1
                if (classname.equals("com.xiaomi.hm.health.O0000oOO.O0000Oo")) { //步骤2
                    // clazz.getClassLoader().loadClass("YYY.YYY.YYY"); // 找其他类
                    // Class.forName("className",true,clazz.getClassLoader());
                    Class<?> clazz = (Class<?>) param.getResult();//步骤3
                    findAndHookMethod(clazz, "O0000Oo0", /*args.class,*/ new XC_MethodHook() {//步骤4
                        @Override
                        protected void beforeHookedMethod(MethodHookParam param) throws Throwable {//步骤5
                            super.beforeHookedMethod(param);
                            param.setResult(true);
                            log("param: " + param.getResult());
                        }

                        @Override
                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {//步骤5
                            super.beforeHookedMethod(param);
                        }
                    });
                }
            }
        });

        hookAllMethods(ClassLoader.class, "com.xiaomi.hm.health.O00oOooo.O000Oo0", new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                if (param.hasThrowable()) return;
                if (param.args.length != 1) return;
                Class<?> cls = (Class<?>) param.getResult();
                String name = cls.getName();

                if (name.equals("com.xiaomi.hm.health.O00oOooo.O000Oo0")) {
                    hookAllMethods(cls, "O0000Oo0", new XC_MethodHook() {
                        protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                            try {
                                param.setResult(XC_MethodReplacement.returnConstant(true));
                                log("param.getResult():" + param.getResult());
                            } catch (Exception e) {
                                log("beforeHookedMethod exception:" + e);
                            }
                        }
                    });
                }
            }
        });

        if (lpparam.packageName.equals("com.xiaomi.hm.health.O0000oOO")) {
            log("Loaded application \"" + lpparam.packageName + "\" ;");
            // public static boolean O00000Oo(Context context, String str)
            findAndHookMethod("com.xiaomi.hm.health.O0000oOO.O00o00", lpparam.classLoader, "O00000oo", Context.class, String.class, new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    super.beforeHookedMethod(param);
                    log("param.args[0]: " + param.args[0].toString());
                    log("param.args[1]: " + param.args[1].toString());
                    param.setResult(XC_MethodReplacement.returnConstant(true));
                    log("Hooking Result is: " + param.getResult());
                }
            });
            //No parameter
            hookMethodReturnTrue("com.xiaomi.hm.health.O0000oOO", "com.xiaomi.hm.health.O0000oOO.O0000Oo", lpparam, "O00o00");
        }
    }
}
