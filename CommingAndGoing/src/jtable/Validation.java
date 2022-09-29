package jtable;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {
	public static boolean validTel(String tel) {
		Pattern pattern = Pattern.compile("\\d{3}\\d{4}\\d{4}");
		Matcher right = pattern.matcher(tel);
		if (right.matches()) {
			return true;
		}else {
			return false;
		}
	}
}
