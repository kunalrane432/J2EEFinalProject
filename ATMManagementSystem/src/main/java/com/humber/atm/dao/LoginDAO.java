package com.humber.atm.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.humber.atm.model.User;
import com.humber.atm.util.DatabaseUtil;


public class LoginDAO {

	public User getUserActiveStatus(String username,String password) throws SQLException {
		
		User user=null;
		String sql = "SELECT * FROM User WHERE username = ? and password=? and status=?";
		Connection con=DatabaseUtil.getConnection();
		PreparedStatement statement = con.prepareStatement(sql);
		statement.setString(1, username);
		statement.setString(2, password);
		statement.setBoolean(3, true);
		
		ResultSet resultSet = statement.executeQuery();
		//boolean flag=false;
		if (resultSet.next()) {
			user=new User();
			user.setAccountNo(resultSet.getString("account_no"));
			user.setUserid(resultSet.getInt("userid"));
			
		}
		
		resultSet.close();
		statement.close();
		DatabaseUtil.closeConnection(con);
		return user;
	}
}
