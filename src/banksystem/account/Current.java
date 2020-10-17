package banksystem.account;

import banksystem.transaction.Deposit;

public class Current extends Account {
	private double openingBalance;

	// constructor
	public Current(String createdDate, String customerName, double openingBalance) {
		super(createdDate, customerName);
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
		return "Current [" + accountNo + ";" + createdDate + ";" + customerName + ";" + openingBalance + ";" + balance + "]";
	}
	
	public static Current parseCurrent(String str) throws Exception {
		str = str.replace("Current [", "").replace("]", "");
		String[] fields = str.split(";");
		if (fields.length > 0) {
			Current current = new Current(fields[1], fields[2], Double.parseDouble(fields[3]));
			current.accountNo = Long.parseLong(fields[0]);
			current.balance = Double.parseDouble(fields[4]);
			return current;
		} else {
			throw new Exception("");
		}
	}

}