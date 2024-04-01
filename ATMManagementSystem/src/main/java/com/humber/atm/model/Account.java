package com.humber.atm.model;

import java.math.BigDecimal;

public class Account {

	private int userid;
	private String account_no;
	private String account_type;
	private double amount;
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String getAccount_no() {
		return account_no;
	}
	public void setAccount_no(String account_no) {
		this.account_no = account_no;
	}
	public String getAccount_type() {
		return account_type;
	}
	public void setAccount_type(String account_type) {
		this.account_type = account_type;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	@Override
	public String toString() {
		return "Account [userid=" + userid + ", account_no=" + account_no + ", account_type=" + account_type
				+ ", amount=" + amount + "]";
	}
	
	
	
	
	
}
