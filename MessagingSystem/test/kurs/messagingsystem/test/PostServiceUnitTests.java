package kurs.messagingsystem.test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.isA;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import kurs.messaging.beans.Post;
import kurs.messaging.beans.PostsSent;
import kurs.messaging.connection.ConnectionFactory;
import kurs.messaging.connection.ConnectionInterface;
import kurs.messaging.service.testmethods.PostServiceTests;
import kurs.messaging.util.StringUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
class PostServiceUnitTests {
	
	private static ConnectionInterface connect;
	private Post post = new Post("post content", LocalDateTime.now(), 21);
	
	@BeforeAll
	public static void databaseConnection() throws SQLException {
		connect = ConnectionFactory.returnConnection(StringUtil.TEST_DATABASE);
		connect.returnConnection();
	}

	@Test
	void shouldInsertAPostInTheDatabase() throws SQLException {
		PostServiceTests service = new PostServiceTests();
		assertEquals(post, service.insertPost(post));
	}
	
	@Test
	void shouldGetAllPosts() throws SQLException {
		PostServiceTests service = new PostServiceTests();
		List<PostsSent> posts = service.getPosts();
		for(PostsSent post: posts) {
			log.info("Post number: {}", post.getPost_ID());
		}
		assertThat(post.getContent(), isA(String.class));
		assertThat(post.getTimeOfMessage(), isA(LocalDateTime.class));
		assertTrue(posts.size() == 1);
	}
	
	@Test
	void shouldGetIndividualUserPosts() throws SQLException {
		PostServiceTests service = new PostServiceTests();
		List<PostsSent> posts = service.getPosts(post.getUser_ID());
		for(PostsSent onePost : posts) {
			onePost.setPost_ID(post.getPost_ID());
			log.info("Post number: {}", post.getPost_ID());
		}
		assertTrue(posts.size() == 1);
	}
	
	@Test
	void shouldGetMostTrendingLikes() throws SQLException {
		PostServiceTests service = new PostServiceTests();
		List<PostsSent> posts = service.getMostTrendingMessages();
		for(PostsSent onePost : posts) {
			log.info("Number of likes for a post: {}", onePost.getLikesCount());
		}
		assertThat(posts.size(), is(1));
	}
	
	@AfterAll
	public static void close() throws SQLException {
		connect.close();
	}

}
