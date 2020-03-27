package com.example.utillibrary;

import android.util.Log;

public class LogUtil {
    private static final String TAG = "test_log";
    private static final boolean IS_DEBUG = true;

    public static void e(String msg) {
        if (msg != null) {
            if (IS_DEBUG) {
                Log.e(TAG, msg);
            }
        }

    }

    public static void e(String tag, String msg) {
        if (msg != null) {
            if (IS_DEBUG) {
                Log.e(tag, msg);
            }
        }

    }

    public static void w(String msg) {
        if (msg != null) {
            if (IS_DEBUG) {
                Log.w(TAG, msg);
            }
        }

    }

    public static void w(String tag, String msg) {
        if (msg != null) {
            if (IS_DEBUG) {
                Log.w(tag, msg);
            }
        }

    }

    public static void d(String msg) {
        if (msg != null) {
            if (IS_DEBUG) {
                Log.d(TAG, msg);
            }
        }

    }

    public static void d(String tag, String msg) {
        if (msg != null) {
            if (IS_DEBUG) {
                Log.d(tag, msg);
            }
        }

    }

    public static void i(String msg) {
        if (msg != null) {
            if (IS_DEBUG) {
                Log.i(TAG, msg);
            }
        }

    }

    public static void i(String tag, String msg) {
        if (msg != null) {
            if (IS_DEBUG) {
                Log.i(tag, msg);
            }
        }

    }

    public static void v(String msg) {
        if (msg != null) {
            if (IS_DEBUG) {
                Log.v(TAG, msg);
            }
        }

    }

    public static void v(String tag, String msg) {
        if (msg != null) {
            if (IS_DEBUG) {
                Log.v(tag, msg);
            }
        }
    }
}
