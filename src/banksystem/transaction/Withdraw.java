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
		return TransactionRepo.withdrawTransaction(this);
	}
	
	@Override
	public String toString() {
		return "Withdraw [" + checkNo + ";" + tranID + ";" + amount + ";"
				+ tranDetail + ";" + accountId + ";" + date + "]";
	}

	public static Withdraw parseWithdraw(String str) throws Exception {
		str = str.replace("Withdraw [", "").replace("]", "");
		String[] fields = str.split(";");
		if(fields.length > 0) {
			Withdraw withdraw = new Withdraw(Double.parseDouble(fields[2]), fields[3], Long.parseLong(fields[4]), fields[5], fields[0]);
			withdraw.tranID = Long.parseLong(fields[1]);
			return withdraw;
		} else {
			throw new Exception("");
		}
	}

	

}
