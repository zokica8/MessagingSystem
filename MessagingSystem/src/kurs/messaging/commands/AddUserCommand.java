package kurs.messaging.commands;

// command for adding users
public class AddUserCommand extends Command {

	public AddUserCommand(String jsp) {
		super(jsp);
	}

	@Override
	public String execute() throws Exception {
		request.setAttribute("errorMessage", "");
		return nextPage;
	}

}
