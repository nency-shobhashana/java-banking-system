package banksystem.account;

import banksystem.transaction.Deposit;

public class Current extends Account {
	private double openingBalance;

	// constructor
	public Current(String createdDate, String customerNo, double openingBalance) {
		super(createdDate, customerNo);
		this.openingBalance = openingBalance;
		this.balance = openingBalance;
	}
	public Current(long accountNo, String createdDate, String customerNo, double openingBalance) {
		super(accountNo, createdDate, customerNo);
		this.openingBalance = openingBalance;
		this.balance = openingBalance;
	}

	// getters and setters
	public double getOpeningBalance() {
		return openingBalance;
	}

	public void setOpeningBalance(double openingBalance) {
		this.openingBalance = openingBalance;
	}

	@Override
	public String toString() {
		return "Current [" + accountNo + ";" + createdDate + ";" + customerNo + ";" + openingBalance + ";" + balance + "]";
	}
	
	// convert string to Current account class
	public static Current parseCurrent(String str) throws Exception {
		str = str.replace("Current [", "").replace("]", "");
		String[] fields = str.split(";");
		if (fields.length > 0) {
			Current current = new Current(Long.parseLong(fields[0]), fields[1], fields[2], Double.parseDouble(fields[3]));
			current.balance = Double.parseDouble(fields[4]);
			return current;
		} else {
			throw new Exception("");
		}
	}

}
