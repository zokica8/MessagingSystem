package kurs.messaging.commands;

import kurs.messaging.beans.User;
import kurs.messaging.services.LikesService;

public class LikeCommand extends Command {
	
	private LikesService service;

	public LikeCommand(String jsp) {
		super(jsp);
	}

	@Override
	public String execute() throws Exception {
		service = new LikesService();
		service.returnConnection();
		
		User user = (User) request.getSession().getAttribute("user");
		int id = user.getUser_id();
		Integer liked = Integer.parseInt(request.getParameter("liked"));
		service.insertLike(id, liked);
	
		return nextPage;
	}

}
