package com.humber.atm.util;

import java.util.Comparator;

import com.humber.atm.model.Transactions;

public class SortbyTransactionDate implements Comparator<Transactions>{

	@Override
	public int compare(Transactions t1, Transactions t2) {
		// TODO Auto-generated method stub
		return -t1.getTransaction_date().compareTo(t2.getTransaction_date());
	}

}
