package kurs.messaging.commands;

import kurs.messaging.services.UserService;

public class LoginCommand extends Command {
	
	private UserService service;

	public LoginCommand(String jsp) {
		super(jsp);
	}

	@Override
	public String execute() throws Exception {
		service = new UserService();
		service.returnConnection();
		request.setAttribute("errorMessage", "");
		return nextPage;
	}

}
