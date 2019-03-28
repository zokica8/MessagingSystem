package kurs.messaging.commands;

public class CommentCommand extends Command {
	
	public CommentCommand(String jsp) {
		super(jsp);
	}

	@Override
	public String execute() throws Exception {
		
		Integer postID = Integer.parseInt(request.getParameter("postId")); 
		request.setAttribute("postID", postID);
		
		return nextPage;
	}

}
