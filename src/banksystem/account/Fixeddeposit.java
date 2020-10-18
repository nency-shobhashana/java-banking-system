package banksystem.account;

public class Fixeddeposit extends Account {
	private double depositAmount;
	private double interestRate;
	private double timePeriod;

//constructor
	public Fixeddeposit(String createdDate, String customerNo, double depositAmount, double interestRate,
			double timePeriod) {
		super(createdDate, customerNo);
		this.depositAmount = depositAmount;
		this.balance = depositAmount;
		this.interestRate = interestRate;
		this.timePeriod = timePeriod;
	}
	
	public Fixeddeposit(long accountNo,String createdDate, String customerNo, double depositAmount, double interestRate,
			double timePeriod) {
		super(accountNo, createdDate, customerNo);
		this.depositAmount = depositAmount;
		this.balance = depositAmount;
		this.interestRate = interestRate;
		this.timePeriod = timePeriod;
	}

	//getters and setters

	public double getDepositAmount() {
		return depositAmount;
	}

	public void setDepositAmount(double depositAmount) {
		this.depositAmount = depositAmount;
	}

	public double getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
	}

	public double getTimePeriod() {
		return timePeriod;
	}

	public void setTimePeriod(double timePeriod) {
		this.timePeriod = timePeriod;
	}

	@Override
	public String toString() {
		return "Fixeddeposit [" + accountNo + ";" + createdDate + ";" + customerNo + ";" + depositAmount + ";"
				+ interestRate + ";" + timePeriod + ";" + balance + "]";
	}
	
	// convert string to Fixed Deposit account class
	public static Fixeddeposit parseFixeddeposit(String str) throws Exception {
		str = str.replace("Fixeddeposit [", "").replace("]", "");
		String[] fields = str.split(";");
		if (fields.length > 0) {
			Fixeddeposit fixeddeposit = new Fixeddeposit(Long.parseLong(fields[0]), fields[1], fields[2], Double.parseDouble(fields[3]),
					Double.parseDouble(fields[4]), Double.parseDouble(fields[5]));
			fixeddeposit.balance = Double.parseDouble(fields[6]);
			return fixeddeposit;
		} else {
			throw new Exception("");
		}
	}

}
