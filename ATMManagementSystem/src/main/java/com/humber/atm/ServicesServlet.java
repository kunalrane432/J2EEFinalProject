package com.humber.atm;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;

import com.humber.atm.model.User;

/**
 * Servlet implementation class ServicesServlet
 */
public class ServicesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServicesServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		System.out.println("Inside Services Servlet");
		RequestDispatcher dispatcher = null;
		HttpSession session = request.getSession();
		String username=request.getParameter("username");
		//System.out.println("USERNAME :"+username);
		//System.out.println("password :"+password);
		if(session.getAttribute("username")!=null)
		{
			System.out.println(request.getParameter("userid"));
			int userid=Integer.valueOf(request.getParameter("userid"));
			System.out.println("USer id in credit amount" + userid);
			//String username=request.getParameter("username");
			session.setAttribute("username", username);
	        session.setAttribute("userid", userid);	
	        dispatcher = request.getRequestDispatcher("services.jsp");
	        dispatcher.forward(request, response);
		}
		else
		{
			dispatcher = request.getRequestDispatcher("login.jsp");
	        dispatcher.forward(request, response); 
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		
		
	}

}
