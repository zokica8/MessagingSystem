package kurs.messaging.util;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import kurs.messaging.connection.ConnectionFactory;
import kurs.messaging.connection.ConnectionInterface;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CSVFileReader {

	// fields(instance variables, attributes)
	private ConnectionInterface connect = ConnectionFactory.returnConnection(StringUtil.DATABASE);

	// methods
	public void readingFromCSVFile(String file, String table, String columns) throws SQLException {
		String sql = "load data infile 'C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/" + file + "\' "
				+ "into table " + table + " fields terminated by ',' enclosed by '\"' lines terminated by '\\n' "
				+ "ignore 1 rows (" + columns + ")";

		try (PreparedStatement pstmt = connect.returnConnection().prepareStatement(sql,
				Statement.RETURN_GENERATED_KEYS)) {
			pstmt.execute(sql);
			log.info("Data inserted!");
		} catch (SQLException e) {
			log.error("Record insert not successful!!");
			log.error(e.getMessage());
		}
	}

	public static void main(String[] args) throws SQLException {
		CSVFileReader reader = new CSVFileReader();
		reader.readingFromCSVFile(StringUtil.USER_CSV, "User", "username, password");
		reader.readingFromCSVFile(StringUtil.POST_CSV, "Post", "content, timeOfMessage, user_ID");
		reader.readingFromCSVFile(StringUtil.LIKE_CSV, "Likes", "user_id, post_id, timeOfLike");
	}

}
