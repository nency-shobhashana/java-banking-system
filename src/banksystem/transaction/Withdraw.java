package banksystem.transaction;

import banksystem.account.Account;

public class Withdraw extends BaseTransaction {

	private String checkNo;

	// constructor
	public Withdraw(double amount, String tranDetail, long accountNo, String date, String checkNo) {
		super(amount, tranDetail, accountNo, date);
		this.setCheckNo(checkNo);
	}

	// constructor for auto-generate
	public Withdraw(long tranId, double amount, String tranDetail, long accountNo, String date, String checkNo) {
		super(tranId, amount, tranDetail, accountNo, date);
		this.setCheckNo(checkNo);
	}

	public String getCheckNo() {
		return checkNo;
	}
	public void setCheckNo(String checkNo) {
		this.checkNo = checkNo;
	}

	// method for calculate amount and save transaction
	@Override
	public long doTransaction() {
		Account account = getAccount();
		if (isAccountValid(account)) {
			double remainBalance = account.getBalance() - amount;
			if (remainBalance > 0) {
				account.setBalance(account.getBalance() - amount);
				TransactionRepo.updateAccount(account);
				return TransactionRepo.withdrawTransaction(this);
			} else {
				System.out.println("Not sufficent balance in account; Your account balance is " + account.getBalance());
				return -1;
			}
		} else {
			System.out.println("Account no is invalid or does not exist");
			return -1;
		}
	}

	@Override
	public String toString() {
		return "Withdraw [" + tranID + ";" + accountNo + ";" + amount + ";" + checkNo + ";" + tranDetail + ";" + date
				+ "]";
	}

	// convert string to Withdraw transaction class
	public static Withdraw parseWithdraw(String str) throws Exception {
		str = str.replace("Withdraw [", "").replace("]", "");
		String[] fields = str.split(";");
		if (fields.length > 0) {
			Withdraw withdraw = new Withdraw(Long.parseLong(fields[0]), Double.parseDouble(fields[2]), fields[4],
					Long.parseLong(fields[1]), fields[5], fields[3]);
			return withdraw;
		} else {
			throw new Exception("");
		}
	}

}
