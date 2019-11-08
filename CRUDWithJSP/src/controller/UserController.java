package controller;

import java.io.IOException;
import java.text.ParseException;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.UserInfo;
import dao.UserDAO;

public class UserController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static String INSERT_OR_EDIT = "/user.jsp";
	private static String LIST_USER = "/listUser.jsp";
	private UserDAO dao;
	
	public UserController() {
		super();
		dao = new UserDAO();
	}
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException{
		String forward="";
		String action = request.getParameter("action");
		
		if(action.equalsIgnoreCase("detele")) {
			int id = Integer.parseInt(request.getParameter("id"));
			dao.DeleteUser(id);
			forward= LIST_USER;
			request.setAttribute("users", dao.getAllUsers());
		}
		else if (action.equalsIgnoreCase("edit")) {
			forward= INSERT_OR_EDIT;
			int id = Integer.parseInt(request.getParameter("id"));
			UserInfo user = dao.SelectUserByID(id);
			request.setAttribute("user", user);
		}
		else if (action.equalsIgnoreCase("listUser")) {
			forward = LIST_USER;
            request.setAttribute("users", dao.getAllUsers());
		}
		else {
			forward= INSERT_OR_EDIT;
		}
		RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException{
		UserInfo user = new UserInfo();
		user.setFirstname(request.getParameter("firstname"));
		user.setLastname(request.getParameter("lastname"));
		user.setCountry(request.getParameter("country"));
		user.setPhoneno(request.getParameter("phone"));
		String uid=request.getParameter("id");
		if(uid==""||uid==null) {
			dao.AddUser(user);
		}
		else {
			user.setId(Integer.parseInt(uid));
			dao.UpdateUser(user);
		}
		RequestDispatcher view = request.getRequestDispatcher(LIST_USER);
        request.setAttribute("users", dao.getAllUsers());
        view.forward(request, response);
	}

}
