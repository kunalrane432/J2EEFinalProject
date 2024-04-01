package com.humber.atm;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;

import com.humber.atm.dao.LoginDAO;
import com.humber.atm.model.User;


/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		
		LoginDAO loginDAO=new LoginDAO();
		RequestDispatcher dispatcher = null;
		HttpSession session = request.getSession();
		System.out.println("USERNAME :"+username);
		System.out.println("password :"+password);
		try {
			User user=loginDAO.getUserActiveStatus(username,password);
			if(user!=null)
			{
				System.out.println("Credentials Successful");
				
	            session.setAttribute("username", username);
	            session.setAttribute("userid", user.getUserid());
	            session.setAttribute("user", user);
	            dispatcher = request.getRequestDispatcher("services.jsp");
			}
			else
			{
				System.out.println("User does not exist");
				request.setAttribute("message", "Invalid Username or Password");
				dispatcher = request.getRequestDispatcher("login.jsp");
			}
			//dispatcher.forward(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			dispatcher = request.getRequestDispatcher("error.jsp");
		}
		finally {
			dispatcher.forward(request, response);
		}
		
	}

}
