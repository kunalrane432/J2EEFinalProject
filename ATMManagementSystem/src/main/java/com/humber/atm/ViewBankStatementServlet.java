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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.humber.atm.dao.ServicesDAO;
import com.humber.atm.model.Transactions;
import com.humber.atm.model.User;

/**
 * Servlet implementation class ViewBankStatementServlet
 */
public class ViewBankStatementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	ServicesDAO service=new ServicesDAO();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewBankStatementServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		RequestDispatcher dispatcher = null;
		HttpSession session=request.getSession(false);
		if(session.getAttribute("username")!=null)
		{
			System.out.println("ViewBankStatament Get method called");
			
			//int userid=Integer.valueOf(request.getParameter("userid"));
			//System.out.println("USer id in credit amount" + userid);
			String username=request.getParameter("username");
			
			
			String operation=request.getParameter("operation");
			
			
			 
			User user=(User)session.getAttribute("user");
	        session.setAttribute("username", username);
	        session.setAttribute("userid", user.getUserid());	
	        System.out.println("USERNAME in TRANSACTIONS : "+username);
	        System.out.println("USERID in TRANSACTIONS : "+user.getUserid());
	        List<Transactions> transactions=null;
	        System.out.println("Operations : "+operation);
	        if(operation.equals("viewstatement"))
	        {
	        	
	        	if(request.getParameter("from").equals("") || request.getParameter("to").equals(""))
	        	{
	        		System.out.println("Cannot Move Money more than your Balance");
					request.setAttribute("message", "Invalid Transaction Dates.");
					session.setAttribute("username", username);
		            session.setAttribute("userid", user.getUserid());
		            session.setAttribute("transactions", transactions);
					dispatcher = request.getRequestDispatcher("transactions.jsp");
					dispatcher.forward(request, response);
	        	}
	        	else
	        	{
	        		try {
						Date from = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("from"));
						Date to =new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("to"));
						System.out.println("from"+from.getDate());
						System.out.println("to"+to.getDate());
						try {
							transactions=service.getTransactions(user.getAccountNo(), from, to);
							System.out.println("Transactions retrieved");
							if(transactions.size()==0)
							{
								transactions=null;
								request.setAttribute("message", "No Transactions in Date Range");
								session.setAttribute("username", username);
					            session.setAttribute("userid", user.getUserid());
					            session.setAttribute("transactions", transactions);
							}
								
							session.setAttribute("transactions", transactions);
							dispatcher = request.getRequestDispatcher("transactions.jsp");
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							System.out.println("inner catch");
							dispatcher = request.getRequestDispatcher("error.jsp");
						}
						
						
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						System.out.println("outter ctach");
						dispatcher = request.getRequestDispatcher("error.jsp");
					}
		        	finally
		        	{
		        		dispatcher.forward(request, response);
		        	}
	        	}
	        	
	        	
	        }
	        else
	        {
	        	System.out.println("operation is null");
	        	dispatcher = request.getRequestDispatcher("transactions.jsp");
	        	session.setAttribute("transactions", transactions);
	        	dispatcher.forward(request, response);
	        }
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
		doGet(request, response);
	}

}
