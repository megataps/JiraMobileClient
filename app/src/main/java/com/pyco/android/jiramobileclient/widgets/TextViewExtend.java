package com.pyco.android.jiramobileclient.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import com.pyco.android.jiramobileclient.utils.FontUtil;


public class TextViewExtend extends TextView {

	public TextViewExtend(Context context) {
		super(context);
	}

	public TextViewExtend(Context context, AttributeSet attrs) {
		super(context, attrs);
		FontUtil.setFont(context, this, attrs);
	}

	public TextViewExtend(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		FontUtil.setFont(context, this, attrs);
	}
}