package kurs.messaging.commands;

import kurs.messaging.beans.User;
import kurs.messaging.services.UserService;

public class UserDeletedCommand extends Command {
	
	private UserService service;

	public UserDeletedCommand(String jsp) {
		super(jsp);
	}

	@Override
	public String execute() throws Exception {
		
		service = new UserService();
		//service.returnConnection();
		User user = (User) request.getSession().getAttribute("user");
		int id = user.getUser_id();
		service.deleteUser(id);
		
		request.getSession().invalidate();
		return nextPage;
	}

}
