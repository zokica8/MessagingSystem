package kurs.messaging.commands;

import org.apache.commons.codec.digest.DigestUtils;

import kurs.messaging.beans.User;
import kurs.messaging.services.UserService;
import kurs.messaging.util.JspUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserUpdatedCommand extends Command{
	
	private UserService service;

	public UserUpdatedCommand(String jsp) {
		super(jsp);
	}

	@Override
	public String execute() throws Exception {
		String oldPassword = request.getParameter("oldPassword");
		String newPassword = request.getParameter("newPassword");
		service = new UserService();
		service.returnConnection();
		String hexedPassword = DigestUtils.sha512Hex(oldPassword);
		User user = service.checkIfPasswordForUserExists(hexedPassword);
		
		if(hexedPassword.equals(user.getPassword()) && newPassword.length() >= 10) {
			log.info("Passwords match!");
			service.updateUser(user, newPassword);
			return nextPage;
		}
		else {
			request.setAttribute("lengthPassword", service.getValidationMessage(newPassword));
			request.setAttribute("notEqualPasswords", "Password must be the one that is inserted!");
			return JspUtil.UPDATE_USER_JSP;
		}	
	}

}
