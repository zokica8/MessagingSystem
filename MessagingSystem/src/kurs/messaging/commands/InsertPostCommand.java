package kurs.messaging.commands;

public class InsertPostCommand extends Command {

	public InsertPostCommand(String jsp) {
		super(jsp);
	}

	@Override
	public String execute() throws Exception {
		request.setAttribute("postErrorMessage", "");
		return nextPage;
	}

}
