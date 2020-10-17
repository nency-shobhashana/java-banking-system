package banksystem.account;

public class Fixeddeposit extends Account {
	private double depositAmount;
	private double interestRate;
	private double timePeriod;

//constructor
	public Fixeddeposit(String createdDate, String customerName, double depositAmount, double interestRate,
			double timePeriod) {
		super(createdDate, customerName);
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
		return "Fixeddeposit [" + accountNo + ";" + createdDate + ";" + customerName + ";" + depositAmount + ";"
				+ interestRate + ";" + timePeriod + ";" + balance + "]";
	}

	public static Fixeddeposit parseFixeddeposit(String str) throws Exception {
		str = str.replace("Fixeddeposit [", "").replace("]", "");
		String[] fields = str.split(";");
		if (fields.length > 0) {
			Fixeddeposit fixeddeposit = new Fixeddeposit(fields[1], fields[2], Double.parseDouble(fields[3]),
					Double.parseDouble(fields[4]), Double.parseDouble(fields[5]));
			fixeddeposit.accountNo = Long.parseLong(fields[0]);
			fixeddeposit.balance = Double.parseDouble(fields[6]);
			return fixeddeposit;
		} else {
			throw new Exception("");
		}
	}

}