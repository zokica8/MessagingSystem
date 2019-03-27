package kurs.messaging.commands;

public class UploadPictureCommand extends Command {

	public UploadPictureCommand(String jsp) {
		super(jsp);
	}

	@Override
	public String execute() throws Exception {
		
		return nextPage;
	}

}
