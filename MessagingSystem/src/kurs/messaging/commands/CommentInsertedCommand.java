package kurs.messaging.commands;

import kurs.messaging.beans.Comments;
import kurs.messaging.beans.User;
import kurs.messaging.services.CommentsService;

public class CommentInsertedCommand extends Command {
	
	private CommentsService service;

	public CommentInsertedCommand(String jsp) {
		super(jsp);
	}

	@Override
	public String execute() throws Exception {
		
		service = new CommentsService();
		service.returnConnection();
		
		User user = (User) request.getSession().getAttribute("user");
		Integer post_Id = Integer.parseInt(request.getParameter("postID"));
		String content = request.getParameter("commentMessage");
		Comments comment = new Comments(user.getUser_id(), post_Id, content);
		service.insertComment(comment);
		
		return nextPage;
	}

}
