package com.humber.atm;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
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
 * Servlet implementation class DebitAmountServlet
 */
public class DebitAmountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	ServicesDAO service=new ServicesDAO();
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DebitAmountServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("Debit Get method called");
		RequestDispatcher dispatcher = null;
		
		
		HttpSession session=request.getSession(false);
		if(session.getAttribute("username")!=null)
		{
			int userid=Integer.valueOf(request.getParameter("userid"));
			System.out.println("USer id in credit amount" + userid);
			String username=request.getParameter("username");
			session.setAttribute("username", username);
	        session.setAttribute("userid", userid);	
	        dispatcher = request.getRequestDispatcher("withdraw.jsp");
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
		RequestDispatcher dispatcher = null;
		
		
		HttpSession session=request.getSession(false);
		if(session.getAttribute("username")!=null)
		{
			int userid=Integer.valueOf(request.getParameter("userid"));
			System.out.println("USer id in withdraw amount" + userid);
			String username=request.getParameter("username");
			
			String account_type=request.getParameter("account_type");
			double amount=Double.parseDouble(request.getParameter("amount"));
			if(amount<0)
			{
				request.setAttribute("message", "Invalid Amount");
				session.setAttribute("username", username);
	            session.setAttribute("userid", userid);
				dispatcher = request.getRequestDispatcher("withdraw.jsp");
				dispatcher.forward(request, response);
			}
			else
			{
				User user=(User)session.getAttribute("user");
				System.out.println("Account Type : "+userid);
				System.out.println("Account Type : "+account_type);
				System.out.println("account number : "+user.getAccountNo());
				try {
					Account account=service.getAccountDetails(userid, user.getAccountNo(), account_type);
					if(account!=null)
					{
						
						double temp=account.getAmount();
						temp-=amount;
						
						if(temp>0)
						{
							if(service.updateAmount(account_type, user.getUserid(),temp , user.getAccountNo())){
								Transactions transactions=new Transactions();
								
								transactions.setAccount_no(user.getAccountNo());
								transactions.setAmount(amount);
								transactions.setTransaction_type("Withdraw From "+account_type);
								
								if(service.addTransaction(transactions))
								{
									session.setAttribute("username", username);
						            session.setAttribute("userid", userid);	
						            dispatcher = request.getRequestDispatcher("services.jsp");
								}
								else
								{
									dispatcher = request.getRequestDispatcher("withdraw.jsp");
								}
					            
							}
							else
							{
								dispatcher = request.getRequestDispatcher("withdraw.jsp");
							}
						}
						else
						{
							System.out.println("Cannot Withdraw more than your Balance");
							request.setAttribute("message", "Amount Cannot be greater than account balance. Please check you balance.");
							session.setAttribute("username", username);
				            session.setAttribute("userid", userid);
							dispatcher = request.getRequestDispatcher("withdraw.jsp");
						}
						
					}
					else
					{
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
		else
		{
			dispatcher = request.getRequestDispatcher("login.jsp");
	        dispatcher.forward(request, response);
		}
		
	}

}
