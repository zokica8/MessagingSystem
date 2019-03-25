package kurs.messaging.commands;

import org.apache.commons.codec.digest.DigestUtils;

import kurs.messaging.beans.User;
import kurs.messaging.services.UserService;
import kurs.messaging.util.JspUtil;

public class UserLoggedInCommand extends Command {
	
	private UserService service;

	public UserLoggedInCommand(String jsp) {
		super(jsp);
	}

	@Override
	public String execute() throws Exception {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String errorMessage = "Username and password don't match!";
		
		String hash = DigestUtils.sha512Hex(password);
		service = new UserService();
		service.returnConnection();
		
		User user = service.loginUser(username);
		
		// login functionality - how to implement that?
		// WE HAVE FINALLY IMPLEMENTED LOGIN FUNCTIONALITY!!!
		if(username.equals(user.getUsername()) && user.getPassword().equals(hash)) {
			session = request.getSession();
			session.setAttribute("user", user);
			return nextPage;
		}
		else {
			request.setAttribute("errorMessage", errorMessage);
			return JspUtil.LOGIN_JSP;
		}			
	}
}
