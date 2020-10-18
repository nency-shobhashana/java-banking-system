package banksystem.transaction;

import banksystem.account.Account;

public class Transfer extends BaseTransaction {

	private long destinationAccountNo;

	// constructor
	public Transfer(double amount, String tranDetail, long accountNo, String date, long destinationAccountNo) {
		super(amount, tranDetail, accountNo, date);
		this.setDestinationAccountNo(destinationAccountNo);
	}

	// constructor for auto-generate
	public Transfer(long tranId, double amount, String tranDetail, long accountNo, String date,
			long destinationAccountId) {
		super(tranId, amount, tranDetail, accountNo, date);
		this.setDestinationAccountNo(destinationAccountId);
	}

	public long getDestinationAccountNo() {
		return destinationAccountNo;
	}

	public void setDestinationAccountNo(long destinationAccountId) {
		this.destinationAccountNo = destinationAccountId;
	}

	private Account getDestAccount() {
		return TransactionRepo.getAccount(destinationAccountNo);
	}

	// method for calculate amount and save transaction
	@Override
	public long doTransaction() {
		Account srcAccount = getAccount();
		Account destAccount = getDestAccount();
		if (!isAccountValid(srcAccount)) {
			System.out.println("Account no is invalid or does not exist");
			return -1;
		}
		if (!isAccountValid(destAccount)) {
			System.out.println("Destination Account no is invalid or does not exist");
			return -1;
		}
		double remainBalance = srcAccount.getBalance() - amount;
		if (remainBalance > 0) {
			srcAccount.setBalance(srcAccount.getBalance() - amount);
			destAccount.setBalance(destAccount.getBalance() + amount);
			TransactionRepo.updateAccount(srcAccount);
			TransactionRepo.updateAccount(destAccount);
			return TransactionRepo.transferTransaction(this);
		} else {
			System.out.println("Not sufficent balance in account; Your account balance is " + srcAccount.getBalance());
			return -1;
		}

	}

	@Override
	public String toString() {
		return "Transfer [" + tranID + ";" + accountNo + ";" + amount + ";" + destinationAccountNo + ";" + tranDetail
				+ ";" + date + "]";
	}

	// convert string to Transfer transaction class
	public static Transfer parseTransfer(String str) throws Exception {
		str = str.replace("Transfer" + " [", "").replace("]", "");
		String[] fields = str.split(";");
		if (fields.length > 0) {
			Transfer transfer = new Transfer(Long.parseLong(fields[0]), Double.parseDouble(fields[2]), fields[4],
					Long.parseLong(fields[1]), fields[5], Long.parseLong(fields[3]));
			return transfer;
		} else {
			throw new Exception("");
		}
	}

}
