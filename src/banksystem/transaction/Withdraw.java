package banksystem.transaction;

public class Withdraw extends BaseTransaction {
	
	private String checkNo;

	public Withdraw(double amount, String tranDetail, long accountId, String date, String checkNo) {
		super(amount, tranDetail, accountId, date);
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
		// TODO Auto-generated method stub
		return 0;
	}

	

}
