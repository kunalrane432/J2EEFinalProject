package com.humber.atm.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import com.humber.atm.model.User;
import com.humber.atm.util.DatabaseUtil;


public class RegisterDAO {

	public boolean registerUser(User user)
	{
		boolean status=false;
		try
		{
			Connection con=DatabaseUtil.getConnection();
			String account_no=UUID.randomUUID().toString();
			PreparedStatement st = con.prepareStatement("insert into user (userid,username,password,firstname,lastname,dateofbirth,account_no,gender,roleid,status,email,phone) values (?,?,?,?,?,?,?,?,?,?,?,?)");
			st.setInt(1, user.getUserid());
			st.setString(2,user.getUsername());
			st.setString(3,user.getPassword());
			st.setString(4,user.getFirstname());
			
			st.setString(5,user.getLastname());
			st.setDate(6,new java.sql.Date(user.getDateofbirth().getTime()));
			st.setString(7, account_no);
			st.setString(8, user.getGender().toString());
			
			st.setInt(9, user.getRoleid());
			st.setBoolean(10, true);
			st.setString(11, user.getEmail());
			st.setString(12,user.getPhone());
			if(st.executeUpdate()>0)
				status=true;
			DatabaseUtil.closeConnection(con);
			if(status)
			{
				status=createAccount(user, "Savings",account_no);
				status=createAccount(user, "Chequing",account_no);
				return status;
			}
			else
				return status;
		}
		catch(Exception e)
		{
			System.out.println("Error while inserting the user details"+e.getMessage());
			return status;
		}
	}
	
	
	public boolean createAccount(User user,String account_Type,String account_no)
	{
		boolean status=false;
		Connection con;
		try {
			con = DatabaseUtil.getConnection();
			PreparedStatement st = con.prepareStatement("insert into Account (userid,account_no,account_type,amount) values (?,?,?,?)");
			
			st.setInt(1,user.getUserid() );
			st.setString(2, account_no );
			st.setString(3, account_Type);
			st.setDouble(4, 0.0);
			if(st.executeUpdate()>0)
				status=true;
			
			return status;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return status;
	}
	
	
	public int getUserCount()
	{
		try
		{
			Connection con=DatabaseUtil.getConnection();
			PreparedStatement st = con.prepareStatement("select count(*) from user");
			
			
			ResultSet rs =st.executeQuery();
			rs.next();
			int count=rs.getInt(1);
			DatabaseUtil.closeConnection(con);
			return count;
		}
		catch(Exception e)
		{
			System.out.println("Error while inserting the payment details"+e.getMessage());
			return -1;
		}
	}
}
