package banksystem.transaction;

import banksystem.account.Account;

public class Deposit extends BaseTransaction {

	private String personName;
	private String type;

	// constructor
	public Deposit(double amount, String tranDetail, long accountNo, String date, String personName, String type) {
		super(amount, tranDetail, accountNo, date);
		this.personName = personName;
		this.type = type;
	}

	// constructor for auto-generate
	public Deposit(long tranId, double amount, String tranDetail, long accountNo, String date, String personName,
			String type) {
		super(tranId, amount, tranDetail, accountNo, date);
		this.personName = personName;
		this.type = type;
	}

	public String getPersonName() {
		return personName;
	}
	public void setPersonName(String personName) {
		this.personName = personName;
	}

	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

	// method for calculate amount and save transaction
	@Override
	public long doTransaction() {
		Account account = getAccount();
		if (isAccountValid(account)) {
			account.setBalance(account.getBalance() + amount);
			TransactionRepo.updateAccount(account);
			return TransactionRepo.depositTransaction(this);
		} else {
			System.out.println("Account no is invalid or does not exist");
			return -1;
		}
	}

	@Override
	public String toString() {
		return "Deposit [" + tranID + ";" + accountNo + ";" + amount + ";" + personName + ";" + tranDetail + ";" + type
				+ ";" + date + "]";
	}

	// convert string to Deposit transaction class
	public static Deposit parseDeposit(String str) throws Exception {
		str = str.replace("Deposit [", "").replace("]", "");
		String[] fields = str.split(";");
		if (fields.length > 0) {
			Deposit deposit = new Deposit(Long.parseLong(fields[0]), Double.parseDouble(fields[2]), fields[4],
					Long.parseLong(fields[1]), fields[6], fields[3], fields[5]);
			return deposit;
		} else {
			throw new Exception("");
		}
	}

}
