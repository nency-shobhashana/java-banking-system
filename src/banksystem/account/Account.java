package banksystem.account;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import banksystem.customer.Customer;
import banksystem.transaction.TransactionRepo;

public abstract class Account {
	private static long lastAccountNo = 0;
	protected long accountNo = 0;
	protected String createdDate;
	protected String customerNo;
	protected double balance;

	// constructor
	public Account(String createdDate, String customerNo) {
		super();
		this.accountNo = generateAccountNo();
		this.createdDate = createdDate;
		this.customerNo = customerNo;
	}
	
	// constructor for auto-generate
	public Account(long accountNo, String createdDate, String customerNo) {
		super();
		this.accountNo = accountNo;
		this.createdDate = createdDate;
		this.customerNo = customerNo;
	}
	
	// method auto-generate accountNo
	private long generateAccountNo() {
		if(lastAccountNo == 0){
			long transactionIdFromfile = TransactionRepo.getLastAccountNo();
			if(transactionIdFromfile == 0) {
				lastAccountNo = 100000000;
			} else {
				lastAccountNo = transactionIdFromfile;
			}
		}
		return ++lastAccountNo;
	}

	//getters  and setters
	public long getAccountNo() {
		return accountNo;
	}

	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public String getCustomerNo() {
		return customerNo;
	}
	public void setCustomerNo(String customerNo) {
		this.customerNo = customerNo;
	}

	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
}


