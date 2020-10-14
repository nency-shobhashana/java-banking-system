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
		// TODO Auto-generated method stub
		return 0;
	}
	
}
