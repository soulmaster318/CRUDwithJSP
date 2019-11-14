package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
 
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;

import entity.UserInfo;
import dao.UserDAO;
import controller.MyUtils;

@WebServlet(urlPatterns = { "/login" })
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	public LoginServlet() {
	        super();
	}
	@Override
	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException {
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/login.jsp");
		dispatcher.forward(request, response);
	}
	@Override
	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException {
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		String rememberMeStr = request.getParameter("rememberMe");
        boolean remember = "Y".equals(rememberMeStr);
        
        UserInfo user=null;
        boolean hasError = false;
        String errorString = null;
        if (email == null || password == null || email.length() == 0 || password.length() == 0) {
            hasError = true;
            errorString = "Required username and password!";
        } else {
        	Connection conn = MyUtils.getStoredConnection(request);
        	try {
        		user=UserDAO.findUser(conn, email);
        		
        		if(user==null||BCrypt.checkpw(password, user.getPassword())==false) {
        			hasError=true;
        			errorString= "email or password is invalid";
        		}
        	} catch (SQLException e) {
        		 e.printStackTrace();
                 hasError = true;
                 errorString = e.getMessage();
        	}
        }
        if (hasError) {
        	user = new UserInfo();
        	user.setEmail(email);
        	user.setPassword(password);
        	request.setAttribute("errorString", errorString);
            request.setAttribute("user", user);
            RequestDispatcher dispatcher  = this.getServletContext().getRequestDispatcher("/homeView.jsp");

            dispatcher.forward(request, response);
        }
        else {
        	HttpSession session = request.getSession();
            MyUtils.storeLoginedUser(session, user);
            if(remember) {
            	MyUtils.storeUserCookie(response, user);
            }
            else {
            	MyUtils.deleteUserCookie(response);
            }
            response.sendRedirect(request.getContextPath() + "/userInfo");
        }
	}
}
