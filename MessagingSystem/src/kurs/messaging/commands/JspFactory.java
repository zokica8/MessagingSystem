package kurs.messaging.commands;

import kurs.messaging.util.CommandsUtil;
import kurs.messaging.util.JspUtil;

// factory for creating adequate commands and forwarding 
// commands to adequate jsp page
public class JspFactory {
	
	public static Command getCommands(String command) {
		if(command.equalsIgnoreCase(CommandsUtil.HOME_PAGE)) {
			return new HomeCommand(JspUtil.HOME_JSP); 
		}
		if(command.equalsIgnoreCase(CommandsUtil.ADD_USER)) {
			return new AddUserCommand(JspUtil.ADD_USER_JSP);
		}
		if(command.equalsIgnoreCase(CommandsUtil.LOGIN_PAGE)) {
			return new LoginCommand(JspUtil.LOGIN_JSP);
		}
		if(command.equalsIgnoreCase(CommandsUtil.USER_CREATED)) {
			return new UserCreatedCommand(JspUtil.USER_CREATED_JSP);
		}
		if(command.equalsIgnoreCase(CommandsUtil.USER_LOGGED_IN)) {
			return new UserLoggedInCommand(JspUtil.USER_LOGGED_IN_JSP);
		}
		if(command.equalsIgnoreCase(CommandsUtil.UPDATE_USER)) {
			return new UpdateUserCommand(JspUtil.UPDATE_USER_JSP);
		}
		if(command.equalsIgnoreCase(CommandsUtil.USER_UPDATED)) {
			return new UserUpdatedCommand(JspUtil.USER_UPDATED_JSP);
		}
		if(command.equalsIgnoreCase(CommandsUtil.USER_DELETED)) {
			return new UserDeletedCommand(JspUtil.USER_DELETED_JSP);
		}
		if(command.equalsIgnoreCase(CommandsUtil.INSERT_POST)) {
			return new InsertPostCommand(JspUtil.INSERT_POST_JSP);
		}
		if(command.equalsIgnoreCase(CommandsUtil.LIST_OF_ALL_POSTS)) {
			return new ListOfPostsCommand(JspUtil.LIST_OF_ALL_POSTS_JSP);
		}
		if(command.equalsIgnoreCase(CommandsUtil.LOGOUT)) {
			return new LogoutCommand(JspUtil.LOGOUT_JSP);
		}
		if(command.equalsIgnoreCase(CommandsUtil.POST_INSERTED)) {
			return new PostInsertedCommand(JspUtil.POST_INSERTED_JSP);
		}
		if(command.equalsIgnoreCase(CommandsUtil.LIKE)) {
			return new LikeCommand(JspUtil.LIKE_JSP);
		}
		if(command.equalsIgnoreCase(CommandsUtil.UPLOAD_PIC)) {
			return new UploadPictureCommand(JspUtil.UPLOAD_PIC_JSP);
		}
		if(command.equalsIgnoreCase(CommandsUtil.PIC_UPLOADED)) {
			return new PictureUploadedCommand(JspUtil.PIC_UPLOADED_JSP);
		}
		if(command.equalsIgnoreCase(CommandsUtil.COMMENT)) {
			return new CommentCommand(JspUtil.COMMENT_JSP);
		}
		if(command.equalsIgnoreCase(CommandsUtil.COMMENT_INSERTED)) {
			return new CommentInsertedCommand(JspUtil.COMMENT_INSERTED_JSP);
		}
		return null;
	}

}
