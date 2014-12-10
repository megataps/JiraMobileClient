package com.pyco.android.jiramobileclient.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class ValidationUtils {

	public static boolean isEmailAddress(String emailAddress) {
		boolean result = false;

		if (emailAddress == null || emailAddress.trim().equals("")) {
			return true;
		}

		String expression = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{1,})$";
		Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
		String[] stringContent = emailAddress.split(" ");
		Matcher matcher;
		for (String partContent : stringContent) {
			matcher = pattern.matcher(partContent);
			if (matcher.matches()) {
				return true;
			}
		}

		return result;
	}

	public static boolean isPhone(String phone) {
		boolean result = false;

		if (phone == null || phone.trim().equals("")) {
			return true;
		}

		String expression = "^(?:\\+?1[-. ]?)?\\(?([0-9]{3})\\)?[-. ]?([0-9]{3})[-. ]?([0-9]{4})$";
		Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(phone);
		if (matcher.matches()) {
			return true;
		}

		return result;
	}

	public static boolean isPasswordValid(String password) {
		boolean result = false;

		if (password == null || password.equals("")) {
			return false;
		}
		// Password must have at least 1 number and 1 capital letter
		String expression = "^.*([A-Z]+.*[0-9]+|[0-9]+.*[A-Z]+).*$";

		Pattern pattern = Pattern.compile(expression);
		Matcher matcher = pattern.matcher(password);
		if (matcher.matches()) {
			return true;
		}

		return result;
	}
}
