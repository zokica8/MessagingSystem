package kurs.messaging.commands;

import java.util.List;

import kurs.messaging.beans.PostsSent;
import kurs.messaging.services.PostService;

// command for home page 
public class HomeCommand extends Command {
	
	private PostService service;

	public HomeCommand(String jsp) {
		super(jsp);
	}

	@Override
	public String execute() throws Exception {
		service = new PostService();
		service.returnConnection();
		
		List<PostsSent> sent = service.getMostTrendingMessages();
		request.setAttribute("trending", sent);
		return nextPage;
	}

}
