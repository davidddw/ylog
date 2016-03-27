package org.livecloud.ylog.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.jasypt.util.password.ConfigurablePasswordEncryptor;

public class StringHelper {
	public static String dateToString(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(date);
	}

	public static String join(String[] array) {
		return join(array, ";");
	}

	public static String join(String[] array, String delimiter) {
		// Cache the length of the delimiter
		// has the side effect of throwing a NullPointerException if
		// the delimiter is null.
		int delimiterLength = delimiter.length();
		// Nothing in the array return empty string
		// has the side effect of throwing a NullPointerException if
		// the array is null.
		if (array.length == 0)
			return "";

		// Only one thing in the array, return it.
		if (array.length == 1) {
			if (array[0] == null)
				return "";
			return array[0];
		}

		// Make a pass through and determine the size
		// of the resulting string.
		int length = 0;
		for (int i = 0; i < array.length; i++) {
			if (array[i] != null)
				length += array[i].length();
			if (i < array.length - 1)
				length += delimiterLength;
		}

		// Make a second pass through and concatenate everything
		// into a string buffer.
		StringBuffer result = new StringBuffer(length);
		for (int i = 0; i < array.length; i++) {
			if (array[i] != null)
				result.append(array[i]);
			if (i < array.length - 1)
				result.append(delimiter);
		}
		return result.toString();
	}

	public static String encrypt(String plainText) {
		ConfigurablePasswordEncryptor passwordEncryptor = new ConfigurablePasswordEncryptor();
		passwordEncryptor.setAlgorithm("SHA-1");
		passwordEncryptor.setPlainDigest(true);
		return passwordEncryptor.encryptPassword(plainText);
	}
	
	public static boolean checkPassword(String plainText, String encryptedText) {
		ConfigurablePasswordEncryptor passwordEncryptor = new ConfigurablePasswordEncryptor();
		passwordEncryptor.setAlgorithm("SHA-1");
		passwordEncryptor.setPlainDigest(true);
		return passwordEncryptor.checkPassword(plainText, encryptedText) ? true : false;
	}
	
	public static int parseWithDefault(String number, int defaultVal) {
		try {
		    return Integer.parseInt(number)<0? 0 :Integer.parseInt(number);
		} catch (NumberFormatException e) {
		    return defaultVal;
		}
	}
}
