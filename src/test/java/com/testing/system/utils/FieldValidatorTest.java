package com.testing.system.utils;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class FieldValidatorTest {

	@Test
	public void isValidPhoneNumber(){
		String validPhone = "380673255791";

		assertTrue(FieldValidator.isValidPhoneNumber(validPhone));
	}

}
