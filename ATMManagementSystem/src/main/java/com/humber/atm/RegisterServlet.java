package com.humber.atm;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Random;

import com.humber.atm.dao.RegisterDAO;
import com.humber.atm.enums.Gender;
import com.humber.atm.model.User;

/**
 * Servlet implementation class RegisterServlet
 */
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		RequestDispatcher dispatcher = request.getRequestDispatcher("register.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		
		User user=new User();
		RegisterDAO registerDAO=new RegisterDAO();
		RequestDispatcher dispatcher = null;
		if(registerDAO.getUserCount()!=-1)
		{
			user.setUserid(registerDAO.getUserCount()+1);
			user.setUsername(request.getParameter("username"));
			user.setPassword(request.getParameter("password"));
			user.setFirstname(request.getParameter("firstname"));
			user.setLastname(request.getParameter("lastname"));
			try {
				user.setDateofbirth(new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("dob")));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			user.setAccountNo(generateRandomChars("1010101010101010",16));
			if(request.getParameter("gender").equals("MALE"))
			user.setGender(Gender.MALE);
			else if(request.getParameter("gender").equals("FEMALE"))
				user.setGender(Gender.FEMALE);
			else
				user.setGender(Gender.NOT_SPECIFIED);
			user.setRoleid(2001);
			user.setRolename("USER");
			user.setStatus(true);
			
			
			
			
			if(registerDAO.registerUser(user))
			{
				System.out.println("Registered Successfully");
				dispatcher = request.getRequestDispatcher("login.jsp");
			}
			else
			{
				dispatcher = request.getRequestDispatcher("register.jsp");
				System.out.println("Failed Registration");
			}
			dispatcher.forward(request, response);
		}
		
		
	}
	
	public static String generateRandomChars(String candidateChars, int length) {
	    StringBuilder sb = new StringBuilder();
	    Random random = new Random();
	    for (int i = 0; i < length; i++) {
	        sb.append(candidateChars.charAt(random.nextInt(candidateChars
	                .length())));
	    }

	    return sb.toString();
	}

}
