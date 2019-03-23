package kurs.messaging.commands;

public class UpdateUserCommand extends Command {

	public UpdateUserCommand(String jsp) {
		super(jsp);
	}

	@Override
	public String execute() throws Exception {
		request.setAttribute("notEqualPasswords", "");
		request.setAttribute("lengthPassword", "");
		return nextPage;
	}

}
