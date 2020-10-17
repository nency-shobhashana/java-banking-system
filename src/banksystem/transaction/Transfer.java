package banksystem.transaction;

public class Transfer extends BaseTransaction {

	private long destinationAccountId;

	public Transfer(double amount, String tranDetail, long accountId, String date, long destinationAccountId) {
		super(amount, tranDetail, accountId, date);
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
		return TransactionRepo.transferTransaction(this);
	}

	@Override
	public String toString() {
		return "Transfer [" + destinationAccountId + ";" + tranID + ";" + amount
				+ ";" + tranDetail + ";" + accountId + ";" + date + "]";
	}

	public static Transfer parseTransfer(String str) throws Exception {
		str = str.replace("Transfer"
				+ " [", "").replace("]", "");
		String[] fields = str.split(";");
		if(fields.length > 0) {
			Transfer transfer = new Transfer(Double.parseDouble(fields[2]), fields[3], Long.parseLong(fields[4]), fields[5], Long.parseLong(fields[0]));
			transfer.tranID = Long.parseLong(fields[1]);
			return transfer;
		}else {
			throw new Exception("");
		}
	}
	
}
