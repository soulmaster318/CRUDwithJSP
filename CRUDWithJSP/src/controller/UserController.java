package controller;

import java.io.IOException;



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
		
		if(action.equalsIgnoreCase("delete")) {
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
		int errorcount = 0;
		String firstname=request.getParameter("firstname");
		String lastname=request.getParameter("lastname");
		String country=request.getParameter("country");
		String phone = request.getParameter("phone");
		
		user.setFirstname(firstname);
		user.setLastname(lastname);
		user.setCountry(country);
		user.setPhoneno(phone);
		String uid=request.getParameter("id");
		if(firstname.length() >20 || !firstname.matches("^[\\w.-]+$")) {
			request.setAttribute("error_message1","First name must have less than 20 chars and not contain any special characters!!");
			errorcount+=1;
			
		}
		if(lastname.length() >20 || !lastname.matches("^[\\w.-]+$")) {
			request.setAttribute("error_message2","Last name must have less than 20 chars and not contain any special characters!!");
			errorcount+=1;
			
		}
		if(country.length() >100 || !country.matches("^[\\w.-]+$")) {
			request.setAttribute("error_message3","Address must have less than 20 chars and not contain any special characters!!");
			errorcount+=1;
			
		}
		if(!phone.matches("^[0][0-9]+$") || phone.length() !=10) {
			request.setAttribute("error_message4","Phone number must start with '0' and only numbers are allowed and it must have 10 character ");
			errorcount+=1;
			
		}
		if (errorcount>=1) {
			request.getRequestDispatcher("/user.jsp").forward(request, response);
			return;
		}
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
