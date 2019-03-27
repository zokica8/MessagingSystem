package kurs.messaging.commands;

import java.io.File;

import org.apache.commons.io.FileUtils;

import kurs.messaging.beans.User;
import kurs.messaging.services.UserService;

public class UserDeletedCommand extends Command {
	
	private UserService service;

	public UserDeletedCommand(String jsp) {
		super(jsp);
	}

	@Override
	public String execute() throws Exception {
		
		service = new UserService();
		//service.returnConnection();
		User user = (User) request.getSession().getAttribute("user");
		user = service.checkIfImageExistsDelete(user.getUsername());
		if(user.getImageId() != null) {
			String imageId = user.getImageId();
			
			File deleteFile = new File(request.getServletContext().getInitParameter("moveDir") +"\\" + imageId);
			FileUtils.forceDelete(deleteFile);
		}
		int id = user.getUser_id();
		service.deleteUser(id);
		
		request.getSession().invalidate();
		return nextPage;
	}

}
