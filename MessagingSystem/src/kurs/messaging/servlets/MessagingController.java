package kurs.messaging.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kurs.messaging.commands.Command;
import kurs.messaging.commands.JspFactory;
import kurs.messaging.util.CommandsUtil;
import kurs.messaging.util.JspUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * Servlet implementation class MessagingController
 */
// main central servlet of the application which 
// forwards requests to commands and jsp page
@Slf4j
@MultipartConfig(fileSizeThreshold = 1024 * 1024, 
maxFileSize = 1024 * 1024 * 5,
maxRequestSize = 1024 * 1024 * 5 * 5)
@WebServlet("/MessagingController")
public class MessagingController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Map<String, Command> commands = new HashMap<>();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MessagingController() {
		super();
		// TODO Auto-generated constructor stub
		commands.put(CommandsUtil.HOME_PAGE, JspFactory.getCommands(CommandsUtil.HOME_PAGE));
		commands.put(CommandsUtil.ADD_USER, JspFactory.getCommands(CommandsUtil.ADD_USER));
		commands.put(CommandsUtil.LOGIN_PAGE, JspFactory.getCommands(CommandsUtil.LOGIN_PAGE));
		commands.put(CommandsUtil.USER_CREATED, JspFactory.getCommands(CommandsUtil.USER_CREATED));
		commands.put(CommandsUtil.USER_LOGGED_IN, JspFactory.getCommands(CommandsUtil.USER_LOGGED_IN));
		commands.put(CommandsUtil.UPDATE_USER, JspFactory.getCommands(CommandsUtil.UPDATE_USER));
		commands.put(CommandsUtil.USER_UPDATED, JspFactory.getCommands(CommandsUtil.USER_UPDATED));
		commands.put(CommandsUtil.USER_DELETED, JspFactory.getCommands(CommandsUtil.USER_DELETED));
		commands.put(CommandsUtil.INSERT_POST, JspFactory.getCommands(CommandsUtil.INSERT_POST));
		commands.put(CommandsUtil.LIST_OF_ALL_POSTS, JspFactory.getCommands(CommandsUtil.LIST_OF_ALL_POSTS));
		commands.put(CommandsUtil.LOGOUT, JspFactory.getCommands(CommandsUtil.LOGOUT));	
		commands.put(CommandsUtil.POST_INSERTED, JspFactory.getCommands(CommandsUtil.POST_INSERTED));
		commands.put(CommandsUtil.LIKE, JspFactory.getCommands(CommandsUtil.LIKE));
		commands.put(CommandsUtil.UPLOAD_PIC, JspFactory.getCommands(CommandsUtil.UPLOAD_PIC));
		commands.put(CommandsUtil.PIC_UPLOADED, JspFactory.getCommands(CommandsUtil.PIC_UPLOADED));
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {

			String dbUrl = getServletContext().getInitParameter("dbUrl");
			String dbUser = getServletContext().getInitParameter("dbUser");
			String dbPassword = getServletContext().getInitParameter("dbPassword");

			String page = request.getParameter("page");

			Command comm = (page != null) ? commands.get(page) : JspFactory.getCommands(CommandsUtil.HOME_PAGE);

			comm.setDbUrl(dbUrl);
			comm.setDbUser(dbUser);
			comm.setDbPassword(dbPassword);

			comm.setRequest(request);
			comm.setResponse(response);
			
			String nextPage = comm.execute();
			request.getRequestDispatcher(nextPage).forward(request, response);
		} catch (Exception e) {
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			request.setAttribute(CommandsUtil.ERROR_PAGE, sw.toString());
			request.getRequestDispatcher(JspUtil.ERROR_JSP).forward(request, response);
			log.error(sw.toString());
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
