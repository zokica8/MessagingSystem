package kurs.messaging.commands;

public class LoginCommand extends Command {

	public LoginCommand(String jsp) {
		super(jsp);
	}

	@Override
	public String execute() throws Exception {
	
		request.setAttribute("errorMessage", "");
		return nextPage;
	}

}
