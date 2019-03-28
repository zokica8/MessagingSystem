package kurs.messaging.commands;

import java.util.List;

import kurs.messaging.beans.PostsSent;
import kurs.messaging.beans.User;
import kurs.messaging.services.PostService;

public class ListOfPostsCommand extends Command {
	
	private PostService service;

	public ListOfPostsCommand(String jsp) {
		super(jsp);
	}

	@Override
	public String execute() throws Exception {
		service = new PostService();
		service.returnConnection();

		String username = request.getParameter("id");
		User user = service.getUser(username);
		if(username == null || username == "null") {
			// show all messages
			List<PostsSent> posts = service.getPosts();
			request.setAttribute("posts", posts);	
		}
		else {
			// show individual user messages
			List<PostsSent> posts2 = service.getPosts(user.getUser_id());
			request.setAttribute("posts", posts2);
		}
		return nextPage;
	}

}
