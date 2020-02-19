package com.android.weasley.miui;

import static de.robv.android.xposed.XposedHelpers.findClass;

public class DRM {

    public static Object getDrmResult() {
        Class drmClass = findClass("miui.drm.DrmManager.DrmResult", null);
        return Enum.valueOf(drmClass, "DRM_SUCCESS");
    }
}