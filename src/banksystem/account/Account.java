package banksystem.account;

public abstract class Account {
	protected long accountNo;
	protected String createdDate;
	protected String customerName;
	protected double balance;

	private static long firstaccountNo = 100000;

	//constructor
	public Account(String createdDate, String customerName) {
		super();
		this.accountNo =  noGenerator();
		this.createdDate = createdDate;
		this.customerName = customerName;
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

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	private  long noGenerator() {
		if(accountNo==0)
			accountNo=firstaccountNo++;
		else
			accountNo++;
		return accountNo;
	}


}


