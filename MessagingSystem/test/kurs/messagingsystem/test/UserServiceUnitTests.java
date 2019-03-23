package kurs.messagingsystem.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import kurs.messaging.beans.User;
import kurs.messaging.connection.ConnectionFactory;
import kurs.messaging.connection.ConnectionInterface;
import kurs.messaging.service.testmethods.UserServiceTests;
import kurs.messaging.util.StringUtil;

class UserServiceUnitTests {
	private static ConnectionInterface connect;
	private User user = new User("zokivasilic120@gmail.com", "eraojdanic120");
	

	@BeforeAll
	public static void databaseConnection() throws SQLException {
		connect = ConnectionFactory.returnConnection(StringUtil.TEST_DATABASE);
		connect.returnConnection();
	}

	@Test
	void shouldInsertAUserInTheDatabase() throws SQLException {
		UserServiceTests service = new UserServiceTests();
		assertEquals(user, service.insertUser(user));
	}

	@Test
	void shouldUpdateAUserInTheDatabase() throws SQLException {
		UserServiceTests service = new UserServiceTests();
		User userUpdate = service.getUser(user.getUsername());
		user.setUser_id(userUpdate.getUser_id());
		String newPassword = "eraojdanic100";
		User updated = service.updateUser(userUpdate, newPassword);
		assertEquals(user.getUsername(), updated.getUsername());
		assertEquals(user.getUser_id(), updated.getUser_id());
		assertNotEquals(user.getPassword(), newPassword);
	}
	
	@Test
	void userShouldLogin() throws SQLException {
		UserServiceTests service = new UserServiceTests();
		User updated = service.getUser(user.getUsername());
		User login = service.loginUser(user.getUsername());
		assertEquals(updated.getUsername(), login.getUsername());
		assertEquals(updated.getPassword(), login.getPassword());
	}
	
	@Test
	void shouldDeleteAUserInTheDatabase() throws SQLException {
		UserServiceTests service = new UserServiceTests();
		User delete = service.getUser(user.getUsername());
		user.setUser_id(delete.getUser_id());
		service.deleteUser(user.getUser_id());
		assertTrue(delete.getUser_id() == user.getUser_id());
		assertTrue(delete.getUsername().equals(user.getUsername()));
		assertNotSame(delete.getPassword(), user.getPassword());
	}

	// validation tests
	@Test
	void usernameIsNull() {
		User user = new User(null, "ffffffff");
		assertNull(user.getUsername());
		assertFalse(user.validate());
	}

	@Test
	void passwordIsNull() {
		User user = new User("zoki", null);
		assertNull(user.getPassword());
		assertFalse(user.validate());
	}

	@Test
	void usernameNotValid() {
		User user = new User("zoki", "totti");
		assertFalse(user.getUsername().contains("@"));
		assertFalse(user.validate());
	}

	@Test
	void passwordContainsSpaces() {
		User user = new User("zokivasilic@gmail.com", "tooti toot");
		assertTrue(user.getPassword().contains(" "));
		assertFalse(user.validate());
	}

	@Test
	void passwordLengthNotEnough() {
		User user = new User("zokivasilic@gmail.com", "abcdefg");
		assertTrue(user.getPassword().length() < 10);
		assertFalse(user.validate());
	}

	@Test
	void sholudPassValidation() {
		User user = new User("zokivasilic@gmail.com", "Eraojdanic222!");
		assertTrue(user.validate());
	}
	
	@AfterAll
	public static void close() throws SQLException {
		connect.close();
	}

}
