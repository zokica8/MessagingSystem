package kurs.messaging.commands;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import lombok.Setter;
// command for sending requests to adequate jsp page
public abstract class Command {
	
	@Setter
	protected HttpServletRequest request;
	@Setter
	protected HttpServletResponse response;
	@Setter
	protected HttpSession session;
	protected String nextPage;
	protected String dbUrl;
	protected String dbPassword;
	protected String dbUser;
	
	public Command(String jsp) {
		nextPage = jsp;
	}
		
	public void setDbUrl(String dbUrl) {
		this.dbUrl = dbUrl;
	}
	public void setDbPassword(String dbPassword) {
		this.dbPassword = dbPassword;
	}
	public void setDbUser(String dbUser) {
		this.dbUser = dbUser;
	}
	
	public abstract String execute() throws Exception;

}
