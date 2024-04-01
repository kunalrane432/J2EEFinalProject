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
import java.util.List;

import com.humber.atm.dao.ServicesDAO;
import com.humber.atm.model.Account;
import com.humber.atm.model.User;

/**
 * Servlet implementation class ViewBalanceServlet
 */
public class ViewBalanceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	ServicesDAO service=new ServicesDAO();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewBalanceServlet() {
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
			int userid=Integer.valueOf(request.getParameter("userid"));
			System.out.println("USer id in view balance" + userid);
			String username=request.getParameter("username");
			System.out.println("USERNAME *******"+username);
			try {
				List<Account> accounts=service.getBalance(userid);
				
				if(accounts.size()>0)
				{
					//HttpSession session = request.getSession();
		            session.setAttribute("username", username);
		            session.setAttribute("userid", userid);	
		            session.setAttribute("accounts", accounts);
		            for(Account a:accounts)
		            {
		            	if(a.getAccount_type().equals("Savings"))
			            {
		            		session.setAttribute("savings_account", a.getAmount());
			            }
		            	else
		            	{
		            		session.setAttribute("chequing_account", a.getAmount());
		            	}
		            }
		            
		            
		            
		            dispatcher = request.getRequestDispatcher("viewbalance.jsp");
				}
				else
				{
					System.out.println("account does not exist");
					//HttpSession session = request.getSession();
		            session.setAttribute("username", username);
					dispatcher = request.getRequestDispatcher("services.jsp");
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				dispatcher = request.getRequestDispatcher("error.jsp");
			}
			finally {
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
		//doGet(request, response);
		
		
		
		
	}

}
