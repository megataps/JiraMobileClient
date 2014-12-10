package com.pyco.android.jiramobileclient;

import android.app.Application;
import android.content.res.Configuration;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.Volley;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

public class BaseApplication extends Application {

	public static final String TAG = BaseApplication.class.getName();

	/**
	 * A singleton instance of the application class for easy access in other
	 * places
	 */
	private static BaseApplication mInstance;

	/**
	 * Volley
	 */
	private RequestQueue mRequestQueue;

	/**
	 * Check devices is tablet or phone
	 */
	private boolean mIsTablet = false;

    /**
     * Session manager
     */
    private SessionManager mSessionMaganger;

	@Override
	public void onCreate() {
		super.onCreate();

		mInstance = this;

		initDeviceInfo();

        initializeUniversalImageLoader();
	}

	public static BaseApplication getInstance() {
		return mInstance;
	}

	private void initDeviceInfo() {
		mIsTablet = false;
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
			Configuration config = getResources().getConfiguration();

			Log.e(TAG, "config.smallestScreenWidthDp:" + config.smallestScreenWidthDp);

			if (config.smallestScreenWidthDp >= 600) {
				mIsTablet = true;
			}
		}
	}

	public boolean isTablet() {
		return mInstance.mIsTablet;
	}

	/**
	 * Create global configuration and initialize ImageLoader with this
	 * configuration
	 */
	private void initializeUniversalImageLoader() {

		DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
		.cacheInMemory(true).cacheOnDisk(true).build();
		ImageLoaderConfiguration imgLoaderConfig = new ImageLoaderConfiguration.Builder(
				getApplicationContext()).defaultDisplayImageOptions(
						defaultOptions).build();
		ImageLoader.getInstance().init(imgLoaderConfig);
	}

	/**
	 * @return The Volley Request queue, the queue will be created if it is null
	 */
	public RequestQueue getRequestQueue() {
		// lazy initialize the request queue, the queue instance will be
		// created when it is accessed for the first time
		if (mRequestQueue == null) {
			mRequestQueue = Volley.newRequestQueue(getApplicationContext());
		}

		return mRequestQueue;
	}

	/**
	 * Adds the specified request to the global queue, if tag is specified
	 * then it is used else Default TAG is used.
	 * 
	 * @param req
	 * @param tag
	 */
	public <T> void addToRequestQueue(Request<T> req, String tag) {
		// set the default tag if tag is empty
		req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);

		VolleyLog.d("Adding request to queue: %s", req.getUrl());

		getRequestQueue().add(req);
	}

	/**
	 * Adds the specified request to the global queue using the Default TAG.
	 * 
	 * @param req
	 */
	public <T> void addToRequestQueue(Request<T> req) {
		// set the default tag if tag is empty
		req.setTag(TAG);

		getRequestQueue().add(req);
	}

	/**
	 * Cancels all pending requests by the specified TAG, it is important
	 * to specify a TAG so that the pending/ongoing requests can be cancelled.
	 * 
	 * @param tag
	 */
	public void cancelPendingRequests(Object tag) {
		if (mRequestQueue != null) {
			mRequestQueue.cancelAll(tag);
		}
	}

    public SessionManager getSessionMaganger() {
        if(mSessionMaganger == null) {
            mSessionMaganger = new SessionManager();
        }
        return mSessionMaganger;
    }
}
