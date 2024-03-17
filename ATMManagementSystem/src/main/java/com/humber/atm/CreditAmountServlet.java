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

import com.humber.atm.dao.ServicesDAO;
import com.humber.atm.model.Account;
import com.humber.atm.model.Transactions;
import com.humber.atm.model.User;

/**
 * Servlet implementation class CreditAmountServlet
 */
public class CreditAmountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	ServicesDAO service=new ServicesDAO();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreditAmountServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		System.out.println("Credit Get method called");
		RequestDispatcher dispatcher = null;
		int userid=Integer.valueOf(request.getParameter("userid"));
		System.out.println("USer id in credit amount" + userid);
		String username=request.getParameter("username");
		
		HttpSession session = request.getSession();
        session.setAttribute("username", username);
        session.setAttribute("userid", userid);	
        dispatcher = request.getRequestDispatcher("credit.jsp");
        dispatcher.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher dispatcher = null;
		int userid=Integer.valueOf(request.getParameter("userid"));
		System.out.println("USer id in credit amount" + userid);
		String username=request.getParameter("username");
		
		String account_type=request.getParameter("account_type");
		double amount=Double.parseDouble(request.getParameter("amount"));
		
		HttpSession session = request.getSession();
		User user=(User)session.getAttribute("user");
		
		
		
		
		
		try {
			Account account=service.getAccountDetails(userid, user.getAccountNo(), account_type);
			if(account!=null)
			{
				double temp=amount+account.getAmount();
				if(service.updateAmount(account_type, user.getUserid(),temp , user.getAccountNo())){
					Transactions transactions=new Transactions();
					
					transactions.setAccount_no(user.getAccountNo());
					transactions.setAmount(amount);
					transactions.setTransaction_type("Credit To "+account_type);
					
					if(service.addTransaction(transactions))
					{
						session.setAttribute("username", username);
			            session.setAttribute("userid", userid);	
			            dispatcher = request.getRequestDispatcher("services.jsp");
					}
					else
					{
						dispatcher = request.getRequestDispatcher("credit.jsp");
					}
		            
				}
				else
				{
					dispatcher = request.getRequestDispatcher("credit.jsp");
				}
			}
			else
			{
				System.out.println("Error fetching account details");
				dispatcher = request.getRequestDispatcher("error.jsp");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			dispatcher = request.getRequestDispatcher("error.jsp");
		}
		finally
		{
			dispatcher.forward(request, response);
		}
		
		
		
	}

}
