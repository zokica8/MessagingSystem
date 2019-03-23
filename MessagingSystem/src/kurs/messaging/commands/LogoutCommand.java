package kurs.messaging.commands;

public class LogoutCommand extends Command {

	public LogoutCommand(String jsp) {
		super(jsp);
	}

	@Override
	public String execute() throws Exception {
		request.logout();
		request.getSession().invalidate();
		return nextPage;
	}

}
