package com.humber.atm.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import com.humber.atm.model.Account;
import com.humber.atm.model.Transactions;
import com.humber.atm.util.DatabaseUtil;

public class ServicesDAO {

	public List<Account> getBalance(int userid) throws SQLException
	{
		ArrayList<Account> accounts=new ArrayList<Account>();
		
		String sql = "SELECT * FROM Account WHERE userid = ?";
		Connection con=DatabaseUtil.getConnection();
		PreparedStatement statement = con.prepareStatement(sql);
		statement.setInt(1, userid);
		
		
		
		ResultSet resultSet = statement.executeQuery();
		//boolean flag=false;
		while (resultSet.next()) {
			System.out.println("Iterating accounts");
			Account acc=new Account();
			
			acc.setUserid(userid);
			acc.setAccount_no(resultSet.getString("account_no"));
			acc.setAccount_type(resultSet.getString("account_type"));
			acc.setAmount(resultSet.getDouble("amount"));
			accounts.add(acc);
		}
		
		resultSet.close();
		statement.close();
		DatabaseUtil.closeConnection(con);
		return accounts;
	}
	
	
	
	public List<Transactions> getTransactions(String account_no,Date from,Date to) throws SQLException
	{
		ArrayList<Transactions> transactions=new ArrayList<Transactions>();
		
		String sql = "SELECT * FROM Transactions WHERE account_no = ? and transaction_date >=? and transaction_date<=?";
		Connection con=DatabaseUtil.getConnection();
		PreparedStatement statement = con.prepareStatement(sql);
		statement.setString(1, account_no);
		statement.setDate(2,new java.sql.Date(from.getTime()));
		statement.setDate(3,new java.sql.Date(to.getTime()));
		
		ResultSet resultSet = statement.executeQuery();
		//boolean flag=false;
		while (resultSet.next()) {
			System.out.println("Iterating accounts");
			Transactions transaction=new Transactions();
			transaction.setTransactionid(resultSet.getString("transactionid"));
			transaction.setAccount_no(account_no);
			transaction.setAmount(resultSet.getDouble("amount"));
			transaction.setTransaction_date(resultSet.getDate("transaction_date"));
			transaction.setTransaction_type(resultSet.getString("transaction_type"));
			transactions.add(transaction);
		}
		
		resultSet.close();
		statement.close();
		DatabaseUtil.closeConnection(con);
		return transactions;
	}
	
	
	public boolean updateAmount(String account_Type,int userid,double amount,String account_no) throws SQLException
	{
		boolean flag=false;
		
		String sql="Update Account Set amount=? where userid=? AND account_type=? AND account_no=?";
		Connection con=DatabaseUtil.getConnection();
		PreparedStatement statement = con.prepareStatement(sql);
		statement.setDouble(1, amount);
		statement.setInt(2, userid);
		statement.setString(3, account_Type);
		statement.setString(4, account_no);
		
		
		
		int result=statement.executeUpdate();
		
		if(result>0)
			flag=true;
		
		statement.close();
		DatabaseUtil.closeConnection(con);
		
		return flag;
	}
	
	public Account getAccountDetails(int userid,String account_no,String account_type) throws SQLException
	{
		Account account=null;
		
		
		String sql="select * from Account where userid=? and account_no=? and account_type=? ";
		Connection con=DatabaseUtil.getConnection();
		PreparedStatement statement = con.prepareStatement(sql);
		statement.setInt(1, userid);
		statement.setString(2, account_no);
		statement.setString(3, account_type);
		
		
		
		
		ResultSet resultSet = statement.executeQuery();
		
		
		while(resultSet.next())
		{
			account=new Account();
			account.setUserid(userid);
			account.setAccount_no(resultSet.getString("account_no"));
			account.setAccount_type(resultSet.getString("account_type"));
			account.setAmount(resultSet.getDouble("amount"));
			
		}
		
		resultSet.close();
		statement.close();
		DatabaseUtil.closeConnection(con);
		
		return account;
	}
	
	
	public boolean addTransaction(Transactions transactions) throws SQLException
	{
		boolean flag=false;
		
		String sql="Insert into Transactions (transactionid,account_no,transaction_type,transaction_date,amount ) values (?,?,?,?,?) ";
		String transactionid=UUID.randomUUID().toString();
		Connection con=DatabaseUtil.getConnection();
		PreparedStatement statement = con.prepareStatement(sql);
		statement.setString(1, transactionid);
		statement.setString(2, transactions.getAccount_no());
		statement.setString(3, transactions.getTransaction_type());
		Date today=new Date();
		
		statement.setDate(4,new java.sql.Date(today.getTime()));
		statement.setDouble(5, transactions.getAmount());
		
		
		
		int result=statement.executeUpdate();
		
		if(result>0)
			flag=true;
		
		statement.close();
		DatabaseUtil.closeConnection(con);
		
		return flag;
		
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
