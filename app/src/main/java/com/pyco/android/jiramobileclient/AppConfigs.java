package com.pyco.android.jiramobileclient;

import android.content.Context;
import android.os.Debug;
import android.util.Log;

public class AppConfigs {

	/**
	 * Preferences
	 */
	public static final String APPLICATION_PREFERENCES = "jira_client_preferences";
	public static final int PREFERENCES_MODE = Context.MODE_PRIVATE;

	/**
	 * Application Configurations
	 */
	public static final boolean DEBUG_MODE = true;

    /**
     * URLs Request
     */
    public static final String APPLICATION_URL = "https://ssl.pyramid-consulting.com/jira/rest/api/2/";


    public static final String SEARCH_URL = APPLICATION_URL + "search";

	/**
	 * Show memory using to debug memory leak
	 */
	public static void shownMemoryLog() {
		if (AppConfigs.DEBUG_MODE) {

			Debug.MemoryInfo memoryInfo = new Debug.MemoryInfo();
			Debug.getMemoryInfo(memoryInfo);

			String memMessage = String.format("Memory: Pss=%.2f MB, Private=%.2f MB, Shared=%.2f MB",
                    memoryInfo.getTotalPss() / 1024.0, memoryInfo.getTotalPrivateDirty() / 1024.0,
                    memoryInfo.getTotalSharedDirty() / 1024.0);

			Log.e("TAG", memMessage);
		}
	}
	
}
