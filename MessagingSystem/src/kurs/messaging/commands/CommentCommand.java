package kurs.messaging.commands;

import java.util.List;

import kurs.messaging.beans.PostsSent;
import kurs.messaging.services.PostService;

public class CommentCommand extends Command {
	
	private PostService service;
	

	public CommentCommand(String jsp) {
		super(jsp);
	}

	@Override
	public String execute() throws Exception {
		
		service= new PostService();
		service.returnConnection();
		
		List<PostsSent> posts = service.getPosts();
		request.setAttribute("posts", posts);
		
		return nextPage;
	}

}
