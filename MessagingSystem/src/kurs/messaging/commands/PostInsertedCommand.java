package kurs.messaging.commands;

import java.time.LocalDateTime;

import kurs.messaging.beans.Post;
import kurs.messaging.beans.User;
import kurs.messaging.services.PostService;
import kurs.messaging.util.JspUtil;

public class PostInsertedCommand extends Command {
	
	private PostService service;

	public PostInsertedCommand(String jsp) {
		super(jsp);
	}

	@Override
	public String execute() throws Exception {
		service = new PostService();
		service.returnConnection();
		int id = Integer.parseInt(request.getParameter("id"));
		User user = service.getUser(id);
		Post post = new Post();
		post.setContent(request.getParameter("textMessage"));
		post.setTimeOfMessage(LocalDateTime.now());
		post.setUser_ID(user.getUser_id());
		if(post.validate()) {
			service.insertPost(post);
			return nextPage;
		}
		else {
			request.setAttribute("postErrorMessage", post.getMessage());
			return JspUtil.INSERT_POST_JSP;
		}
		
	}

}
