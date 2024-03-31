package com.humber.atm;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.humber.atm.model.Transactions;
import com.humber.atm.model.User;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

/**
 * Servlet implementation class DownloadStatementServlet
 */
public class DownloadStatementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DownloadStatementServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		HttpSession session = request.getSession();
		User user=(User)session.getAttribute("user");
		ArrayList<Transactions> transactions=(ArrayList<Transactions>) session.getAttribute("transactions");;
		
		if(transactions.size()>0)
		{
			System.out.println("Transactions to be downloaded");
			response.setContentType("application/pdf"); 
			String fileName=user.getUsername()+ "_"+ user.getAccountNo() + "_"+new Date().toString();   
	        response.setHeader( 
	            "Content-disposition", 
	            "inline; filename='Downloaded.pdf'"); 
	  
	        try { 
	        	
	        	 
	            System.out.println("Table created successfully.."); 
	  
	            Document document = new Document(); 
	  
	            PdfWriter.getInstance( 
	                document, response.getOutputStream()); 
	  
	            document.open(); 
	            document.add(new Paragraph("Your Personal Banking Account Statement"));
	            document.add(new Paragraph("Your Account Number : "+ user.getAccountNo())); 
	            
	  
	            document.add(new Paragraph("Details of Your Account Number : "+ user.getAccountNo()));
	            
	            
	            document.close(); 
	        } 
	        catch (DocumentException de) { 
	            throw new IOException(de.getMessage()); 
	        } 
		}
		else
		{
			System.out.println("Transactions is empty :( ");
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
