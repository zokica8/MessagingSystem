package kurs.messaging.beans;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class User {
	
	private int user_id;
	
//	@NotNull(message = "USERNAME can't be NULL!!!")
//	@NotBlank(message = "USERNAME can't be BLANK!!!")
//	//@ValidEmail(min = 12, message = "EMAIL not VALID!!!")
//	@Email(message = "EMAIL not VALID!!!")
	private String username;
	
//	@NotNull(message = "PASSWORD can't be NULL!!!")
//	@NotBlank(message = "PASSWORD can't be BLANK!!!")
//	@Size(min = 10, message = "PASSWORD MUST have at LEAST 10 characters!!!")
	private String password;
	
	@Getter
	private String message;
	
	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	public User(int id, String username, String password) {
		user_id = id;
		this.username = username;
		this.password = password;
	}
	
	public boolean validate() {
		if(username == null) {
			message = "USERNAME can't be NULL!!";
			return false;
		}
		else if(username.isBlank()) {
			message = "USERNAME can't be blank!!!";
			return false;
		}
		else if(!username.matches("\\w+@\\w+\\.\\w+")) {
			message = "EMAIL not VALID!!";
			return false;
		}
		else if(password == null) {
			message = "PASSWORD can't be NULL!!";
			return false;
		}
		else if(password.isBlank()) {
			message = "PASSWORD can't be BLANK!!";
			return false;
		}
		else if(password.isEmpty()) {
			message = "PASSWORD can't be EMPTY!!";
			return false;
		}
		else if(password.matches("\\w*\\s+\\w*")) {
			message = "PASSWORD can't contain SPACES!!!";
			return false;
		}
		else if(password.length() < 10) {
			message = "PASSWORD must have AT LEAST 10 characters!!";
			return false;
		}
		return true;
	}
}
