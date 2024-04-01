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
 * Servlet implementation class MoveMoneyServlet
 */
public class MoveMoneyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	ServicesDAO service=new ServicesDAO();
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MoveMoneyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("MoveMoney Get method called");
		RequestDispatcher dispatcher = null;
		
		
		HttpSession session=request.getSession(false);  
		if(session.getAttribute("username")!=null)
		{
			int userid=Integer.valueOf(request.getParameter("userid"));
			System.out.println("USer id in credit amount" + userid);
			String username=request.getParameter("username");
	        session.setAttribute("username", username);
	        session.setAttribute("userid", userid);	
	        dispatcher = request.getRequestDispatcher("movemoney.jsp");
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
		
		
		HttpSession session = request.getSession();
		
		if(session.getAttribute("username")!=null)
		{
			int userid=Integer.valueOf(request.getParameter("userid"));
			System.out.println("USer id in credit amount" + userid);
			String username=request.getParameter("username");
			
			String account_type1=request.getParameter("account_type1");
			
			String account_type2=request.getParameter("account_type2");
			double amount=Double.parseDouble(request.getParameter("amount"));
			
			
			User user=(User)session.getAttribute("user");
			
			try {
				Account account1=service.getAccountDetails(userid, user.getAccountNo(), account_type1);
				Account account2=service.getAccountDetails(userid, user.getAccountNo(), account_type2);
				if(account_type1.equals(account_type2))
				{
					System.out.println("Cannot Move Money more than your Balance");
					request.setAttribute("message", "Account cannot be same.");
					session.setAttribute("username", username);
		            session.setAttribute("userid", userid);
					dispatcher = request.getRequestDispatcher("movemoney.jsp");
				}
				else if(amount <0)
				{
					System.out.println("Cannot Move Money more than your Balance");
					request.setAttribute("message", "Amount Should be Greater than zero");
					session.setAttribute("username", username);
		            session.setAttribute("userid", userid);
					dispatcher = request.getRequestDispatcher("movemoney.jsp");
				}
				else
				{
					if(account1!=null && account2 !=null)
					{
						double temp=account1.getAmount();
						temp-=amount;
						
						double temp2=account2.getAmount();
						temp2+=amount;
						if(temp>0)
						{
							if(service.updateAmount(account_type1, user.getUserid(),temp , user.getAccountNo()) && service.updateAmount(account_type2, user.getUserid(), temp2, user.getAccountNo()))
							{
								Transactions transactions=new Transactions();
								
								transactions.setAccount_no(user.getAccountNo());
								transactions.setAmount(amount);
								transactions.setTransaction_type("Move Money From "+account_type1 + " To "+account_type2);
								
								if(service.addTransaction(transactions))
								{
									session.setAttribute("username", username);
						            session.setAttribute("userid", userid);	
						            dispatcher = request.getRequestDispatcher("services.jsp");
								}
								else
								{
									dispatcher = request.getRequestDispatcher("movemoney.jsp");
								}
							}
							else
							{
								dispatcher = request.getRequestDispatcher("movemoney.jsp");
							}
						}
						else
						{
							System.out.println("Cannot Move Money more than your Balance");
							request.setAttribute("message", account_type1+" Account Does not have sufficient balance.");
							session.setAttribute("username", username);
				            session.setAttribute("userid", userid);
							dispatcher = request.getRequestDispatcher("movemoney.jsp");
						}
					}
					else
					{
						dispatcher = request.getRequestDispatcher("error.jsp");
					}
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
		else
		{
			dispatcher = request.getRequestDispatcher("login.jsp");
	        dispatcher.forward(request, response);
		}
		
	}

}
