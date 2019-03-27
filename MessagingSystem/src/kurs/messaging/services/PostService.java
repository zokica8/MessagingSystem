package kurs.messaging.services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import kurs.messaging.beans.Post;
import kurs.messaging.beans.PostsSent;
import kurs.messaging.connection.ConnectionFactory;
import kurs.messaging.connection.ConnectionInterface;
import kurs.messaging.util.StringUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PostService extends Service {

	// instance variables
	private ConnectionInterface connect = ConnectionFactory.returnConnection(StringUtil.DATABASE);

	// methods
	// insert post
	public Post insertPost(Post post) throws SQLException {

		String sql = "insert into post (content, timeOfMessage, user_ID) values (?, ?, ?)";

		try (PreparedStatement pstmt = connect.returnConnection().prepareStatement(sql,
				Statement.RETURN_GENERATED_KEYS)) {
			pstmt.setString(1, post.getContent());
			pstmt.setTimestamp(2, Timestamp.valueOf(post.getTimeOfMessage()));
			pstmt.setInt(3, post.getUser_ID());

			int insert = pstmt.executeUpdate();
			log.info("Record inserted! " + insert + "\n");
			try (ResultSet rs = pstmt.getGeneratedKeys()) {
				rs.next();
				int value = rs.getInt(1);
				log.info("Primary key number: " + value);
			}
		} catch (SQLException e) {
			log.error("Record insert not successful!");
			log.error(e.getMessage());
		}

		return post;
	}

	// getting all posts
	public List<PostsSent> getPosts() throws SQLException {
		List<PostsSent> postsSent = new ArrayList<>();

		String sql = "select u.username, p.post_ID, p.content, p.timeOfMessage, count(l.post_ID), u.imageId "
				+ "from post p "
				+ "left join likes l on l.post_ID = p.post_ID "
				+ "join user u on p.user_ID = u.user_ID "
				+ "group by p.post_ID " 
				+ "order by p.timeOfMessage desc;";

		try (PreparedStatement pstmt = connect.returnConnection().prepareStatement(sql)) {
			try (ResultSet rs = pstmt.executeQuery()) {
				while (rs.next()) {
					PostsSent sent = new PostsSent();
					sent.setUsername(rs.getString(1));
					sent.setPost_ID(rs.getInt(2));
					sent.setContent(rs.getString(3));
					sent.setTimeOfMessage(rs.getTimestamp(4).toLocalDateTime());
					sent.setLikesCount(rs.getInt(5));
					sent.setImageId(rs.getString(6));
					postsSent.add(sent);
				}
			}
		}
		return postsSent;
	}

	// getting posts by user id
	public List<PostsSent> getPosts(int id) throws SQLException {
		List<PostsSent> postsSent = new ArrayList<>();

		String sql = "select u.username, p.post_ID, p.content, p.timeOfMessage, count(l.post_ID), u.imageId "
				+ "from post p "
				+ "left join likes l on l.post_ID = p.post_ID "
				+ "join user u on p.user_ID = u.user_ID "
				+ "where u.user_ID = ? "
				+ "group by p.post_ID " 
				+ "order by p.timeOfMessage desc;";

		try (PreparedStatement pstmt = connect.returnConnection().prepareStatement(sql)) {
			pstmt.setInt(1, id);
			try (ResultSet rs = pstmt.executeQuery()) {
				while (rs.next()) {
					PostsSent sent = new PostsSent();
					sent.setUsername(rs.getString(1));
					sent.setPost_ID(rs.getInt(2));
					sent.setContent(rs.getString(3));
					sent.setTimeOfMessage(rs.getTimestamp(4).toLocalDateTime());
					sent.setLikesCount(rs.getInt(5));
					sent.setImageId(rs.getString(6));
					postsSent.add(sent);
				}
			}
		}
		return postsSent;
	}
	
	public List<PostsSent> getMostTrendingMessages() throws SQLException {
		List<PostsSent> trending = new ArrayList<>();
		
		String sql = "select u.username, p.post_ID, p.content, p.timeOfMessage, count(l.post_ID) as Likes "
				+ "from post p "
				+ "left join likes l on l.post_ID = p.post_ID "
				+ "join user u on p.user_ID = u.user_ID "
				+ "group by p.post_ID "
				+ "order by Likes desc "
				+ "limit 0, 10;";
		
		try(PreparedStatement pstmt = connect.returnConnection().prepareStatement(sql)) {
			try(ResultSet rs = pstmt.executeQuery()) {
				while(rs.next()) {
					PostsSent sent = new PostsSent();
					sent.setUsername(rs.getString(1));
					sent.setPost_ID(rs.getInt(2));
					sent.setContent(rs.getString(3));
					sent.setTimeOfMessage(rs.getTimestamp(4).toLocalDateTime());
					sent.setLikesCount(rs.getInt(5));
					trending.add(sent);
				}
 			}
		}
		
		return trending;
	}
}
