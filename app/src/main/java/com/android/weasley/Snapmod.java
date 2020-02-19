package com.android.weasley;

import de.robv.android.xposed.callbacks.XC_LoadPackage;

import static com.android.weasley.DirectHookWithoutParams.hookMethodReturnTrue;

public class Snapmod {
    public static void hookMethod(XC_LoadPackage.LoadPackageParam lpparam) {
        hookMethodReturnTrue("cn.gavinliu.snapmod", "cn.gavinliu.snapmod.util.billing.LicenseManager", lpparam, "isApproved");
        hookMethodReturnTrue("cn.gavinliu.snapmod", "cn.gavinliu.snapmod.util.billing.GooglePlayBillingManager", lpparam, "isPro");
    }
}
