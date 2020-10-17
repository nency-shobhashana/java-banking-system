package banksystem.account;

public class Savings extends Account {
	private double openingBalance;
	private double annualInterest;

//constructor
	public Savings(String createdDate, String customerName, double openingBalance, double annualInterest) {
		super(createdDate, customerName);
		this.openingBalance = openingBalance;
		this.balance = openingBalance;
		this.annualInterest = annualInterest;
	}

//getters and setters
	public double getOpeningBalance() {
		return openingBalance;
	}

	public void setOpeningBalance(double openingBalance) {
		this.openingBalance = openingBalance;
	}

	public double getAnnualInterest() {
		return annualInterest;
	}

	public void setAnnualInterest(double annualInterest) {
		this.annualInterest = annualInterest;
	}

	@Override
	public String toString() {
		return "Savings [" + accountNo + ";" + createdDate + ";" + customerName + ";" + openingBalance + ";"
				+ annualInterest + ";" + balance + "]";
	}

	public static Savings parseSavings(String str) throws Exception {
		str = str.replace("Savings [", "").replace("]", "");
		String[] fields = str.split(";");
		if (fields.length > 0) {
			Savings savings = new Savings(fields[1], fields[2], Double.parseDouble(fields[3]),
					Double.parseDouble(fields[4]));
			savings.accountNo = Long.parseLong(fields[0]);
			savings.balance = Double.parseDouble(fields[5]);
			return savings;
		} else {
			throw new Exception("");
		}
	}

}
