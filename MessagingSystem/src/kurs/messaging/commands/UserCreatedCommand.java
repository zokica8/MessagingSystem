package kurs.messaging.commands;

import kurs.messaging.beans.User;
import kurs.messaging.services.UserService;
import kurs.messaging.util.JspUtil;

public class UserCreatedCommand extends Command {
	
	private UserService service;
	
	public UserCreatedCommand(String jsp) {
		super(jsp);
	}
	
	
	@Override
	public String execute() throws Exception {
		String password = request.getParameter("password");
		service = new UserService();
		service.returnConnection();
		User user = new User();
		user.setUsername(request.getParameter("username"));
		user.setPassword(password);
	
		if(user.validate()) {
			service.insertUser(user);
			request.setAttribute("username", user);
			return nextPage;
		}
		else {
			request.setAttribute("errorMessage", user.getMessage());
			return JspUtil.ADD_USER_JSP;
		}	
	}
}
