package com.humber.atm.model;

import java.math.BigDecimal;
import java.util.Date;

public class Transactions {

	
	private String transactionid;
	private String account_no;
	
	private String transaction_type;
	
	private Date transaction_date;
	
	private double amount;

	public String getTransactionid() {
		return transactionid;
	}

	public void setTransactionid(String transactionid) {
		this.transactionid = transactionid;
	}

	public String getAccount_no() {
		return account_no;
	}

	public void setAccount_no(String account_no) {
		this.account_no = account_no;
	}

	public String getTransaction_type() {
		return transaction_type;
	}

	public void setTransaction_type(String transaction_type) {
		this.transaction_type = transaction_type;
	}

	public Date getTransaction_date() {
		return transaction_date;
	}

	public void setTransaction_date(Date transaction_date) {
		this.transaction_date = transaction_date;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "Transactions [transactionid=" + transactionid + ", account_no=" + account_no + ", transaction_type="
				+ transaction_type + ", transaction_date=" + transaction_date + ", amount=" + amount + "]";
	}
	
	
	
}
