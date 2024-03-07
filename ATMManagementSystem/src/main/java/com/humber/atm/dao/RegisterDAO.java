package com.humber.atm.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.humber.atm.model.User;
import com.humber.atm.util.DatabaseUtil;


public class RegisterDAO {

	public boolean registerUser(User user)
	{
		boolean status=false;
		try
		{
			Connection con=DatabaseUtil.getConnection();
			PreparedStatement st = con.prepareStatement("insert into user values(?,?,?,?,?,?,?,?,?,?,?)");
			st.setInt(1, user.getUserid());
			st.setString(2,user.getUsername());
			st.setString(3,user.getPassword());
			st.setString(4,user.getFirstname());
			st.setString(5,user.getLastname());
			st.setDate(6,new java.sql.Date(user.getDateofbirth().getTime()));
			st.setString(7, user.getAccountNo());
			st.setString(8, user.getGender().toString());
			
			st.setInt(9, user.getRoleid());
			st.setString(10, user.getRolename());
			st.setBoolean(11, true);
			if(st.executeUpdate()>0)
				status=true;
			DatabaseUtil.closeConnection(con);
			return status;
		}
		catch(Exception e)
		{
			System.out.println("Error while inserting the user details"+e.getMessage());
			return status;
		}
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
