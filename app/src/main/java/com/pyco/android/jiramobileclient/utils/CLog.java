package com.pyco.android.jiramobileclient.utils;

import android.util.Log;

import com.pyco.android.jiramobileclient.AppConfigs;


public class CLog {

	public static void d(String tag, String msg) {
		if (AppConfigs.DEBUG_MODE) {
			Log.d(tag, msg);
		}
	}

	public static int d(String tag, String msg, Throwable tr) {
		if (AppConfigs.DEBUG_MODE) {
			return Log.d(tag, msg, tr);
		}

		return 0;
	}

	public static int e(String tag, String msg) {
		if (AppConfigs.DEBUG_MODE) {
			return Log.e(tag, msg);
		}

		return 0;
	}

	public static int e(String tag, String msg, Throwable tr) {
		if (AppConfigs.DEBUG_MODE) {
			return Log.e(tag, msg, tr);
		}

		return 0;
	}

	public static String getStackTraceString(Throwable tr) {
		if (AppConfigs.DEBUG_MODE) {
			return Log.getStackTraceString(tr);
		}

		return new String("");
	}

	public static int i(String tag, String msg) {
		if (AppConfigs.DEBUG_MODE) {
			return Log.i(tag, msg);
		}

		return 0;
	}

	public static int i(String tag, String msg, Throwable tr) {
		if (AppConfigs.DEBUG_MODE) {
			return Log.i(tag, msg, tr);
		}

		return 0;
	}

	public static boolean isLoggable(String tag, int level) {
		if (AppConfigs.DEBUG_MODE) {
			return Log.isLoggable(tag, level);
		}

		return false;
	}

	public static int println(int priority, String tag, String msg) {
		if (AppConfigs.DEBUG_MODE) {
			return Log.println(priority, tag, msg);
		}

		return 0;
	}

	public static int v(String tag, String msg) {
		if (AppConfigs.DEBUG_MODE) {
			return Log.v(tag, msg);
		}

		return 0;
	}

	public static int v(String tag, String msg, Throwable tr) {
		if (AppConfigs.DEBUG_MODE) {
			return Log.v(tag, msg, tr);
		}

		return 0;
	}

	public static int w(String tag, Throwable tr) {
		if (AppConfigs.DEBUG_MODE) {
			return Log.w(tag, tr);
		}

		return 0;
	}

	public static int w(String tag, String msg, Throwable tr) {
		if (AppConfigs.DEBUG_MODE) {
			return Log.w(tag, msg, tr);
		}

		return 0;
	}

	public static int w(String tag, String msg) {
		if (AppConfigs.DEBUG_MODE) {
			return Log.w(tag, msg);
		}

		return 0;
	}
}
