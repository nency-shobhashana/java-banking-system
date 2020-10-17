package banksystem.transaction;

import banksystem.account.Account;
import banksystem.account.Fixeddeposit;

public abstract class BaseTransaction {
	private static long lastTranID = 0;
	protected long tranID = 0;
    protected double amount;
    protected String tranDetail;
    protected long accountNo;
    protected String date;
    
    public abstract long doTransaction();
    
    // constructor
	public BaseTransaction(double amount, String tranDetail, long accountNo, String date) {
		super();
		this.tranID = generateTransactionId();
		this.amount = amount;
		this.tranDetail = tranDetail;
		this.accountNo = accountNo;
		this.date = date;
	}
	
	private long generateTransactionId() {
		if(lastTranID == 0){
			long transactionIdFromfile = TransactionRepo.getLastTransId();
			if(transactionIdFromfile == 0) {
				lastTranID = 100000000;
			} else {
				lastTranID = transactionIdFromfile;
			}
		}
		return lastTranID++;
	}

	// getter and setter
	public long getTranID() {
		return tranID;
	}

	public double getAmount() {
		return amount;
	}
	public void setTranAmount(double amount) {
		this.amount = amount;
	}

	public String getTranDetail() {
		return tranDetail;
	}
	public void setTranDetail(String tranDetail) {
		this.tranDetail = tranDetail;
	}
	
	public long getAccountId() {
		return accountNo;
	}
	public void setAccountId(long accountId) {
		this.accountNo = accountId;
	}

	@Override
	public String toString() {
		return "transcation [tranID=" + tranID + ", Amount=" + amount + ", tranDetail=" + tranDetail + "]";
	}
	
	protected boolean isAccountValid(Account account) {
		return account != null && !(account instanceof Fixeddeposit);
	}
	
	protected Account getAccount() {
		return TransactionRepo.getAccount(accountNo); 
	}
	
	
}
