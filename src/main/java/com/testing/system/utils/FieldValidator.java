package com.testing.system.utils;


/**
 * Used for various field validation
 *
 * @author yuri
 *
 */
public class FieldValidator {

	public static String REGION_NUMBER = "380";


	/**
	 * Check if this phone is valid phone number
	 *
	 * @param phone to validate
	 * @return true if phone is valid, false otherwise
	 */
	public static boolean isValidPhoneNumber(String phone){
		String regex = REGION_NUMBER + "\\d{9}";

		return phone.matches(regex);
	}
}
