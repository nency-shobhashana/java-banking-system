package banksystem.transaction;

import banksystem.account.Account;

public class Withdraw extends BaseTransaction {

	private String checkNo;

	public Withdraw(double amount, String tranDetail, long accountNo, String date, String checkNo) {
		super(amount, tranDetail, accountNo, date);
		this.setCheckNo(checkNo);
	}

	public String getCheckNo() {
		return checkNo;
	}

	public void setCheckNo(String checkNo) {
		this.checkNo = checkNo;
	}

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
				System.out.println("Not sufficent balance in account; Your account balance is "+ account.getBalance());
				return -1;
			}
		} else {
			System.out.println("Account no is invalid or does not exist");
			return -1;
		}
	}

	@Override
	public String toString() {
		return "Withdraw [" + checkNo + ";" + tranID + ";" + amount + ";" + tranDetail + ";" + accountNo + ";" + date
				+ "]";
	}

	public static Withdraw parseWithdraw(String str) throws Exception {
		str = str.replace("Withdraw [", "").replace("]", "");
		String[] fields = str.split(";");
		if (fields.length > 0) {
			Withdraw withdraw = new Withdraw(Double.parseDouble(fields[2]), fields[3], Long.parseLong(fields[4]),
					fields[5], fields[0]);
			withdraw.tranID = Long.parseLong(fields[1]);
			return withdraw;
		} else {
			throw new Exception("");
		}
	}

}
