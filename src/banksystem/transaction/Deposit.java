package banksystem.transaction;

import banksystem.account.Account;

public class Deposit extends BaseTransaction {
	
	private String personName;
	private String type;
	
	public Deposit(double amount, String tranDetail, long accountNo, String date, String personName,
			String type) {
		super(amount, tranDetail, accountNo, date);
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

	@Override
	public long doTransaction() {
		Account account = getAccount();
		if(isAccountValid(account)) {
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
		return "Deposit [" + personName + ";" + type + ";" + tranID + ";" + amount + ";" + tranDetail + ";" + accountNo + ";" + date + "]";
	}

	public static Deposit parseDeposit(String str) throws Exception {
		//String[] fields = str.split("^Deposit \\[personName=(.*), type=(.*), tranID=(.*), amount=(.*), tranDetail=(.*), accountId=(.*), date=(.*)\\]$");
		str = str.replace("Deposit [", "").replace("]", "");
		String[] fields = str.split(";");
		if(fields.length > 0) {
			Deposit deposit = new Deposit(Double.parseDouble(fields[3]), fields[4], Long.parseLong(fields[5]), fields[6], fields[0], fields[1]);
			deposit.tranID = Long.parseLong(fields[2]);
			return deposit;
		} else {
			throw new Exception("");
		}
	}
	
	
}
