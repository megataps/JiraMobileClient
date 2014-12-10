package com.pyco.android.jiramobileclient.utils;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pyco.android.jiramobileclient.R;

import java.util.HashMap;
import java.util.Map;


/**
 * https://code.google.com/p/android/issues/detail?id=9904
 * http://milagaia.blogspot.com/2013/06/custom-fonts-in-android-part-3-of-3.html
 */

public class FontUtil {

	private static final String TAG = FontUtil.class.getSimpleName();

	private static Map<String, Typeface> mFonts = new HashMap<String, Typeface>();

	public static Typeface getTypeFace(Context context, String fontPath) {

		if (!mFonts.containsKey(fontPath)) {

			Typeface font = Typeface.createFromAsset(context.getAssets(),
                    fontPath);
			mFonts.put(fontPath, font);
		}

		return mFonts.get(fontPath);
	}

	public static void setFont(View view, Typeface font) {

		if (view instanceof ViewGroup) {
			for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
				setFont(((ViewGroup) view).getChildAt(i), font);
			}
		} else if (view instanceof TextView) {
			((TextView) view).setTypeface(font);
		}
	}

	public static void setFont(Context ctx, TextView view, AttributeSet attrs) {

		TypedArray styleAttrs = ctx.obtainStyledAttributes(attrs,
				R.styleable.TextViewExt);
		String customFont = styleAttrs
				.getString(R.styleable.TextViewExt_customFont);
		setFont(ctx, view, customFont);
		styleAttrs.recycle();
	}

	public static boolean setFont(Context ctx, TextView view, String fontPath) {

		boolean successful = true;

		try {

			Typeface font = getTypeFace(ctx, fontPath);
			view.setTypeface(font);
		} catch (Exception e) {

			Log.e(TAG, "Error to get typeface: " + e.getMessage());
			successful = false;
		}

		return successful;
	}
}
