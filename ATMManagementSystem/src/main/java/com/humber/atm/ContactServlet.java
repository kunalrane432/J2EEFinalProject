package com.humber.atm;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.humber.atm.util.EmailUtility;
import com.humber.atm.util.Mail;

/**
 * Servlet implementation class ContactServlet
 */
public class ContactServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ContactServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    private String host;
	private String port;
	private String user;
	private String pass;
    
    public void init() {
		// reads SMTP server setting from web.xml file
		ServletContext context = getServletContext();
		host = context.getInitParameter("host");
		port = context.getInitParameter("port");
		user = context.getInitParameter("user");
		pass = context.getInitParameter("pass");
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
	        dispatcher = request.getRequestDispatcher("contact.jsp");
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
		
		String name=request.getParameter("name");
		String content=request.getParameter("message");
		String recipient=request.getParameter("email");
		RequestDispatcher dispatcher = request.getRequestDispatcher("contact.jsp");
		
		//String status=Mail.sendMail("kunal", "kunalrane234@gmail.com", "kunalrane432@gmail.com", "Test Java email", "Email Body Test", false, "234@Kunal");
		//String recipient = request.getParameter("recipient");
		//String subject = request.getParameter("subject");
		//String content = request.getParameter("content");

		String resultMessage = "";

		try {
			EmailUtility.sendEmail(host, port, user, pass, recipient, "Thank You for Contacting us "+ name,
					content);
			resultMessage = "The e-mail was sent successfully";
		} catch (Exception ex) {
			ex.printStackTrace();
			resultMessage = "There were an error: " + ex.getMessage();
		} finally {
			request.setAttribute("Message", resultMessage);
			getServletContext().getRequestDispatcher("/Result.jsp").forward(
					request, response);
		}
		
		request.setAttribute("message", resultMessage);
		dispatcher.forward(request, response);
	}

}
