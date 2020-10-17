package banksystem.transaction;

import banksystem.account.Account;

public class Transfer extends BaseTransaction {

	private long destinationAccountId;

	public Transfer(double amount, String tranDetail, long accountNo, String date, long destinationAccountId) {
		super(amount, tranDetail, accountNo, date);
		this.setDestinationAccountId(destinationAccountId);
	}

	public long getDestinationAccountId() {
		return destinationAccountId;
	}

	public void setDestinationAccountId(long destinationAccountId) {
		this.destinationAccountId = destinationAccountId;
	}

	@Override
	public long doTransaction() {
		Account srcAccount = getAccount();
		Account destAccount = getAccount();
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
		return "Transfer [" + destinationAccountId + ";" + tranID + ";" + amount + ";" + tranDetail + ";" + accountNo
				+ ";" + date + "]";
	}

	public static Transfer parseTransfer(String str) throws Exception {
		str = str.replace("Transfer" + " [", "").replace("]", "");
		String[] fields = str.split(";");
		if (fields.length > 0) {
			Transfer transfer = new Transfer(Double.parseDouble(fields[2]), fields[3], Long.parseLong(fields[4]),
					fields[5], Long.parseLong(fields[0]));
			transfer.tranID = Long.parseLong(fields[1]);
			return transfer;
		} else {
			throw new Exception("");
		}
	}

}
