package kurs.messaging.services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import kurs.messaging.beans.User;
import kurs.messaging.connection.ConnectionFactory;
import kurs.messaging.connection.ConnectionInterface;
import kurs.messaging.util.StringUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Service {

	private ConnectionInterface connect = ConnectionFactory.returnConnection(StringUtil.DATABASE);
	protected ConnectionInterface connectTransaction;

	// utility methods
	public ConnectionInterface returnConnection() {
		return ConnectionFactory.returnConnection(StringUtil.DATABASE);
	}

	public User checkIfPasswordForUserExists(String password) throws SQLException {
		User user = new User();
		String sql = "select user_ID, username, password from user where binary password = ?";

		try (PreparedStatement pstmt = connect.returnConnection().prepareStatement(sql)) {
			pstmt.setString(1, password);
			pstmt.execute();

			try (ResultSet rs = pstmt.getResultSet()) {
				while (rs.next()) {
					user.setUser_id(rs.getInt(1));
					user.setUsername(rs.getString(2));
					user.setPassword(rs.getString(3));
				}
			}
		} catch (SQLException e) {
			log.error("Error in the database!!");
			log.error(e.getMessage());
		}
		return user;
	}

	public String getValidationMessage(String password) {
		String message = "";
		if (password.length() < 10) {
			message = "Password must have at least 10 characters!";
		}
		return message;
	}

	public User getUser(int id) throws SQLException {
		User user = new User();
		String sql = "select user_ID, username, password from user where user_ID = ?;";

		try (PreparedStatement pstmt = connect.returnConnection().prepareStatement(sql)) {
			pstmt.setInt(1, id);
			pstmt.execute();

			try (ResultSet rs = pstmt.getResultSet()) {
				while (rs.next()) {
					user.setUser_id(rs.getInt(1));
					user.setUsername(rs.getString(2));
					user.setPassword(rs.getString(3));
				}
			}
		} catch (SQLException e) {
			log.error("Error in the database!!");
			log.error(e.getMessage());
		}
		return user;
	}

	public User getUser(String username) throws SQLException {
		User user = new User();
		String sql = "select user_ID, username, password from user where username = ?;";

		try (PreparedStatement pstmt = connect.returnConnection().prepareStatement(sql)) {
			pstmt.setString(1, username);
			pstmt.execute();

			try (ResultSet rs = pstmt.getResultSet()) {
				while (rs.next()) {
					user.setUser_id(rs.getInt(1));
					user.setUsername(rs.getString(2));
					user.setPassword(rs.getString(3));
				}
			}
		} catch (SQLException e) {
			log.error("Error in the database!!");
			log.error(e.getMessage());
		}
		return user;
	}
}
