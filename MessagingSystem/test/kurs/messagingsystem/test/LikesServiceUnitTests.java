package kurs.messagingsystem.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import kurs.messaging.beans.Likes;
import kurs.messaging.beans.PostsSent;
import kurs.messaging.connection.ConnectionFactory;
import kurs.messaging.connection.ConnectionInterface;
import kurs.messaging.service.testmethods.LikesServiceTests;
import kurs.messaging.util.StringUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
class LikesServiceUnitTests {
	
	private static ConnectionInterface connect;
	private Likes like = new Likes();
	
	@BeforeAll
	public static void databaseConnection() throws SQLException {
		connect = ConnectionFactory.returnConnection(StringUtil.TEST_DATABASE);
		connect.returnConnection();
	}

	@Test
	void shouldInsertALikeInTheDatabase() throws SQLException {
		LikesServiceTests service = new LikesServiceTests();
		assertEquals(like, service.insertLike(21, 1));
	}
	
	// more example data
	@Test
	@Disabled
	void shouldReturnCountOfLikesPerPost() throws SQLException {
		LikesServiceTests service = new LikesServiceTests();
		List<PostsSent> counts = service.countLikes(1);
		for(PostsSent count: counts) {
			log.info("Count {}", count);
		}
		
		assertEquals(0, counts.size());
	}
	
	@Test
	void shouldReturnLikes() throws SQLException {
		LikesServiceTests service = new LikesServiceTests();
		PostsSent likes = service.getLikes(1);
		assertEquals(1, likes.getLikesCount());	
	}
	
	@Test
	void shouldDeleteALikeInTheDatabase() throws SQLException {
		LikesServiceTests service = new LikesServiceTests();
		Likes like = new Likes();
		service.deleteLike(21, 1);
		assertTrue(like.getUser_id() != 21 && like.getPost_id() != 1);
	}
	
	@Test
	void shouldReturnLikesPerPerson() throws SQLException {
		LikesServiceTests service = new LikesServiceTests();
		List<PostsSent> likes = service.getLikesPerPerson(1);
		for(PostsSent oneLike : likes) {
			log.info("likes: {}", oneLike);
		}
		assertTrue(likes.size() == 1);
	}
	
	@AfterAll
	public static void close() throws SQLException {
		connect.close();
	}

}
