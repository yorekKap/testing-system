package com.testing.system.dao.parsers;
import com.testing.system.entities.User;
import com.testing.system.entities.enums.Role;
import org.junit.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class ObjectToColumnValueMapParserTest {

	private static final String REGISTRATION_DATE_COLUMN = "registration_date";
	private static final String USER_ROLE_COLUMN = "user_role";
	private static final String BALANCE_COLUMN = "balance";
	private static final String FIRST_NAME_COLUMN = "first_name";

	private static final Long ID =  new Long(1);
	private static final String FIRST_NAME = "Yuriy";
	private static final String LAST_NAME = "Kaplun";
	private static final String USERNAME = "yurikaplun";
	private static final String PASSWORD = "password";
	private static final String PHONE = "380673255791";
	private static final Long TARIFF_ID = new Long(1);
	private static final double BALANCE = 100;
	private static final Role ROLE = Role.STUDENT;


	User user;
	Map<String, Object> expectedMap;

	@Test
	public void parse() {
		initUserAndExpectedMap();

		Map<String, Object> actualMap = ObjectToColumnValueMapParser.parse(user);

		assertEquals(expectedMap.get(FIRST_NAME_COLUMN), actualMap.get(FIRST_NAME_COLUMN) );
		assertEquals(expectedMap.get(REGISTRATION_DATE_COLUMN), actualMap.get(REGISTRATION_DATE_COLUMN));
		assertEquals(expectedMap.get(BALANCE_COLUMN), actualMap.get(BALANCE_COLUMN));
		assertEquals(expectedMap.get(USER_ROLE_COLUMN), expectedMap.get(USER_ROLE_COLUMN));

	}

	public void initUserAndExpectedMap(){
		Date date = new Date();

		user = new User();
		user.setId(ID);
		user.setFirstName(FIRST_NAME);
		user.setLastName(LAST_NAME);
		user.setUsername(USERNAME);
		user.setPhone(PHONE);
		user.setPassword(PASSWORD);
		user.setRole(ROLE);

		expectedMap = new HashMap<>();
		expectedMap.put(FIRST_NAME_COLUMN, FIRST_NAME);
		expectedMap.put(REGISTRATION_DATE_COLUMN, date);
		expectedMap.put(BALANCE_COLUMN, BALANCE);
		expectedMap.put(USER_ROLE_COLUMN, ROLE);
	}

}
