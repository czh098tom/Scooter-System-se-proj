package test;

import data.User;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class UserTest {

	@Test
	void testEmail() {
    	assertEquals(User.checkEmail("1@2.3"),true);
    	assertEquals(User.checkEmail("@2.3"),false);
    	assertEquals(User.checkEmail("1@.3"),false);
    	assertEquals(User.checkEmail("1@2."),false);
	}

	@Test
	void testQMID() {
    	assertEquals(User.checkQMID("123456789"),true);
    	assertEquals(User.checkQMID("1234567890"),false);
    	assertEquals(User.checkQMID("abcdefghi"),false);
    	assertEquals(User.checkQMID("12345678"),false);
	}

}
