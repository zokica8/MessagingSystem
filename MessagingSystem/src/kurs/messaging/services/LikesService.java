package kurs.messaging.services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import kurs.messaging.beans.Likes;
import kurs.messaging.beans.PostsSent;
import kurs.messaging.connection.ConnectionFactory;
import kurs.messaging.connection.ConnectionInterface;
import kurs.messaging.util.StringUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LikesService extends Service {

	// instance variables
	private ConnectionInterface connect = ConnectionFactory.returnConnection(StringUtil.DATABASE);

	// methods
	// insert like
	public Likes insertLike(int user_id, int post_id) throws SQLException {
		
		String sql = String.format("insert into likes (user_id, post_id) values (%d, %d)", user_id, post_id);
		Likes like = new Likes();
		try (PreparedStatement pstmt = connect.returnConnection().prepareStatement(sql,
				Statement.RETURN_GENERATED_KEYS)) {

			int insert = pstmt.executeUpdate();
			log.info("Like inserted! " + insert + "\n");
		} catch (SQLException e) {
			log.error("Like already inserted!!");
			deleteLike(user_id, post_id);
		}

		return like;
	}

	// delete like (unlike)
	public void deleteLike(int user_ID, int post_ID) throws SQLException {
		Likes like = new Likes();
		like.setUser_id(user_ID);
		like.setPost_id(post_ID);

		String sql = "delete from likes where user_ID = ? AND post_ID = ?";

		try (PreparedStatement pstmt = connect.returnConnection().prepareStatement(sql)) {
			pstmt.setInt(1, like.getUser_id());
			pstmt.setInt(2, like.getPost_id());

			int delete = pstmt.executeUpdate();
			log.info("Like deleted from database! " + delete + "\n");
		} catch (SQLException e) {
			log.error("Record not deleted! Error in database!");
			log.error(e.getMessage());
		}
	}

	// count of likes per post
	public List<PostsSent> countLikes(int post_id) throws SQLException {
		List<PostsSent> likes = new ArrayList<>();
		String sql = "select p.post_ID, u.username, p.content, count(l.user_ID) as Likes, "
				+ "p.timeOfMessage from post p "
				+ "left join likes l on l.post_ID = p.post_ID "
				+ "join user u on u.user_ID = p.user_ID "
				+ "where p.post_ID = ?" 
				+ "group by p.post_ID;";

		try (PreparedStatement pstmt = connect.returnConnection().prepareStatement(sql)) {
			pstmt.setInt(1, post_id);
			try (ResultSet rs = pstmt.getResultSet()) {
				while (rs.next()) {
					PostsSent count = new PostsSent();
					count.setPost_ID(rs.getInt(1));
					count.setUsername(rs.getString(2));
					count.setContent(rs.getString(3));
					count.setLikesCount(rs.getInt(4));
					count.setTimeOfMessage(rs.getTimestamp(5).toLocalDateTime());

					likes.add(count);
				}
			}
		}
		return likes;
	}
	
	public PostsSent getLikes(int post_id) throws SQLException {
		PostsSent sent = new PostsSent();
		
		String sql = "select p.post_ID, u.username, p.content, count(l.user_ID) as Likes, "
				+ "p.timeOfMessage from post p "
				+ "left join likes l on l.post_ID = p.post_ID "
				+ "join user u on u.user_ID = p.user_ID "
				+ "where p.post_ID = ? " 
				+ "group by p.post_ID;";
		
		try (PreparedStatement pstmt = connect.returnConnection().prepareStatement(sql)) {
			pstmt.setInt(1, post_id);
			pstmt.execute();
			try (ResultSet rs = pstmt.getResultSet()) {
				while (rs.next()) {
					sent.setPost_ID(rs.getInt(1));
					sent.setUsername(rs.getString(2));
					sent.setContent(rs.getString(3));
					sent.setLikesCount(rs.getInt(4));
					sent.setTimeOfMessage(rs.getTimestamp(5).toLocalDateTime());
				}
			}
		}
		return sent;
	}
	
	public List<PostsSent> getLikesPerPerson(int post_id) throws SQLException {
		List<PostsSent> likes = new ArrayList<>();
		
		String sql = "select u.username from user u "
				+ "join likes l on u.user_ID = l.user_ID "
				+ "where l.post_ID = ?";
		
		try(PreparedStatement pstmt = connect.returnConnection().prepareStatement(sql)) {
			pstmt.setInt(1, post_id);
			pstmt.execute();
			try(ResultSet rs = pstmt.getResultSet()) {
				while(rs.next()) {
					PostsSent sent = new PostsSent();
					sent.setUsername(rs.getString(1));
					likes.add(sent);
				}
			}
		}
		return likes;
	}
}
